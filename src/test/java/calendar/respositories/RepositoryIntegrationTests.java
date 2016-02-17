/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.respositories;

import calendar.CalendarApplication;
import calendar.domain.entities.Project;
import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.repositories.ProjectRepository;
import calendar.repositories.OrganisationRepository;
import calendar.repositories.EntryRepository;
import calendar.repositories.UserRepository;
import calendar.services.DateService;
import calendar.services.transformers.EntryToWeekRowTransformer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author chriconn
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarApplication.class)
public class RepositoryIntegrationTests {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private OrganisationRepository departmentRepository;
    
    @Autowired
    private EntryRepository entryRepository;
    
    @Autowired
    private UserRepository userRepository;

    public RepositoryIntegrationTests() {
    
    }
    
    @Test
    @Transactional
    public void testAllContainSomeData(){
        
        Project category = projectRepository.findOne(1L);
        assertNotNull(category);
        
        User user = userRepository.findOne(1L);
        assertNotNull(user);
        assertNotNull(user.getOrganisation());
        
        Entry entry = entryRepository.findOne(1L);
        assertNotNull(entry);
        assertNotNull(entry.getUser());
        assertNotNull(entry.getTask());
        assertNotNull(entry.getDate());
        
        Iterable<Entry> allEntries = entryRepository.findAll();
        assertNotNull(allEntries);
    }
    
    @Test
    @Transactional
    public void testFindAllDateRange(){
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2015,5,14,0,0,0);
        startDate = cal.getTime();
        cal.set(Calendar.DATE, 17);
        Date endDate = cal.getTime();
        
        List<Entry> entryList = entryRepository.findByDateBetween(startDate, endDate);
        assertEquals(3, entryList.size());
        
    }
    
    @Test
    @Transactional
    public void testFindUserWithDateRange(){
        User user1 = userRepository.findOne(1L);
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2015,6,25,0,0,0);
        startDate = cal.getTime();
        cal.set(Calendar.DATE, 30);
        Date endDate = cal.getTime();
        
        List<Entry> entryList = entryRepository.findByUserAndDateBetween(user1, startDate, endDate);
        assertEquals(3, entryList.size());
        
    }
    
    @Test
    @Transactional
    public void testMappingProcess(){
        
        //Date beginOfWeek = dateService.calculateWeekBeginning(new Date());
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(beginOfWeek);
        calendar.set(2015, 6, 6);
        Date beginOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE, 7);
        Date endOfWeek = calendar.getTime();
        
        List entries = entryRepository.findByDateBetween(beginOfWeek, endOfWeek);
        
        // Need to remove this logic and have it tested a layer above
        
        Map<User, List<Entry>> map = (Map<User, List<Entry>>) entries.stream().
                collect(groupingBy(Entry::getUser));
        
        List<WeekRow> weekRowList = new ArrayList();
        
        map.entrySet().stream().forEach((mapEntry) -> {
             EntryToWeekRowTransformer entryToWeekRowTransformer = new EntryToWeekRowTransformer();            
             weekRowList.add(entryToWeekRowTransformer.transform(mapEntry.getValue()));
        });
        
        // Logic above
                
        assertNotNull(weekRowList);
    }
    
    
}
