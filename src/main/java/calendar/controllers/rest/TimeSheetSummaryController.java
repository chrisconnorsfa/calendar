/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers.rest;

import calendar.domain.DaySummary;
import calendar.domain.WeekRow;
import calendar.domain.entities.Entry;
import calendar.domain.entities.Task;
import calendar.domain.entities.User;
import calendar.domain.rest.RestTimeScaffolding;
import calendar.services.DateUtils;
import calendar.services.EntryService;
import calendar.services.TaskService;
import calendar.services.UserService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ChriConn
 */
@RestController
@RequestMapping("timesheet")
public class TimeSheetSummaryController {
    
    private EntryService entryService; 
    
    private UserService userService;
    
    private TaskService taskService;

    @Autowired
    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value="/getSummary/{userId}/{startDate}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestTimeScaffolding getSummary(@PathVariable Long userId, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startDate){
        RestTimeScaffolding restTimeScaffolding = new RestTimeScaffolding();
        
        User user = userService.getUserById(userId);
        
        Date weekBeginning = DateUtils.calculateWeekBeginning(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekBeginning);
        
        List<WeekRow> allEntries = entryService.getUserRowsForWeek(startDate, user);
        restTimeScaffolding.setWeekRows(allEntries);
        
        restTimeScaffolding.setStartDate(weekBeginning);
        restTimeScaffolding.setUser(user);
        return restTimeScaffolding;
        
    }
    
    @RequestMapping(value="/getEntriesForUserAndStartDate/{userId}/{startDate}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<WeekRow> getEntriesForUserAndStartDate(@PathVariable Long userId, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date startDate){
         User user = userService.getUserById(userId);
         List<WeekRow> allEntries = entryService.getUserRowsForWeek(startDate, user);
         return allEntries;
    }
    
    @RequestMapping(value="/getEntriesForUserAndIndexAndTask/{userId}/{taskId}/{index}/{date}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public DaySummary getEntriesForUserAndTaskAndDate(@PathVariable Long userId, @PathVariable Long taskId, @PathVariable Integer index, @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date){
         
         User user = userService.getUserById(userId);
         Task task = taskService.getTaskById(taskId);                        
         
         DaySummary daySummary = entryService.getUserAndTaskRowsForDay(date, user, task, index);
         
         return daySummary;
    }
}
