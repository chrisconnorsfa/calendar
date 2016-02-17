/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.services.transformers.EntryToWeekRowTransformer;
import calendar.services.transformers.EntryToDayTransformer;
import calendar.domain.entities.Project;
import calendar.domain.DaySummary;
import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.domain.entities.Task;
import calendar.domain.rest.RestEntry;
import calendar.repositories.EntryRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author chriconn
 */
@Service
public class EntryServiceImpl implements EntryService{

    private EntryRepository entryRepository;   

    @Autowired
    public void setEntryRepository(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    
    @Override
    @Transactional
    public List<WeekRow> getUserRowsForWeek(Date startDate, User user) {
        Date beginOfWeek = DateUtils.calculateWeekBeginning(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginOfWeek);
        calendar.add(Calendar.DATE, -1);
        beginOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE, 7);
        Date endOfWeek = calendar.getTime();
        List entries = entryRepository.findByUserAndDateBetween(user, beginOfWeek, endOfWeek);
        
        Map<User, List<Entry>> map = (Map<User, List<Entry>>) entries.stream().
                collect(groupingBy(Entry::getTask));
        List<WeekRow> weekRowList = new ArrayList();
        
        map.entrySet().stream().forEach((mapEntry) -> {
             EntryToWeekRowTransformer entryToWeekRowTransformer = new EntryToWeekRowTransformer();            
             weekRowList.add(entryToWeekRowTransformer.transform(mapEntry.getValue()));
        });

        //return entryToWeekRowTransformer.transform(map.);
        return weekRowList;
    }

    @Override
    @Transactional
    public DaySummary getUserAndTaskRowsForDay(Date startDate, User user, Task task, Integer index) {
       
        Date weekBeginningDate = DateUtils.calculateWeekBeginning(startDate);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(weekBeginningDate);
        calendar.set(Calendar.HOUR, 0);
        calendar.add(Calendar.DATE, index);
        startDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 24);
        calendar.add(Calendar.SECOND, -1);
        
        Date endDate = calendar.getTime();
        
        List<Entry> entries = entryRepository.findByUserAndTaskAndDateBetween(user, task, startDate, endDate);
            
        EntryToDayTransformer entryToDayTransformer = new EntryToDayTransformer(startDate, user); 
                
        return entryToDayTransformer.transform(entries);
        
    }
    
        @Override
    @Transactional
    public DaySummary getUserAndTaskRowsForDay(Date startDate, User user, Task task) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR, 0);        
        startDate = calendar.getTime();
        calendar.add(Calendar.HOUR, 24);
        calendar.add(Calendar.SECOND, -1);
        Date endDate = calendar.getTime();
        List<Entry> entries = entryRepository.findByUserAndTaskAndDateBetween(user, task, startDate, endDate);
            if(entries.isEmpty()){
                return null;
            }
        EntryToDayTransformer entryToDayTransformer = new EntryToDayTransformer(startDate, user);

        return entryToDayTransformer.transform(entries);
        
    }

    private Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public List<Entry> updateAll(List<RestEntry> allEntries, User user, Task task) {
        
        List<Entry> entryList = new ArrayList<>();
        allEntries.stream().forEach(entry -> {
            entryList.add(buildEntry(entry, user, task));
        });
        
        return entryList;
    }

    @Override
    @Transactional
    public Entry updateSingle(RestEntry restEntry, User user, Task task) {
        return buildEntry(restEntry, user, task);
    }

    @Override
    @Transactional
    public Boolean deleteSingle(Long entryId) {
        
        Entry entry = entryRepository.findOne(entryId);
        
        if(entry == null){
            return false;
        }
        
        entryRepository.delete(entry);
        
        return true;
        
    }
    
    private Entry buildEntry(RestEntry restEntry, User user, Task task){
        
        Entry entry = new Entry();
        entry.setDate(restEntry.getDate());
        entry.setHours(restEntry.getHours());
        entry.setNote(restEntry.getNote());
        entry.setReference(restEntry.getReference());
        entry.setTask(task);
        entry.setUser(user);
        entry.setCreatedDate(new Date());
        
        return saveEntry(entry);
    
    }

    @Override
    @Transactional
    public Entry findById(Long entryId) {
        return entryRepository.findOne(entryId);
    }

    @Override
    public DaySummary getUserAndProjectRowsForDay(Date startDate, User user, Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
