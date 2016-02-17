/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers.rest;

import calendar.domain.entities.Project;
import calendar.domain.DaySummary;
import calendar.domain.entities.Entry;
import calendar.domain.RestErrorResponse;
import calendar.domain.RestResponse;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.domain.WeekSummary;
import calendar.domain.entities.Task;
import calendar.domain.rest.RestEntry;
import calendar.services.DateUtils;
import calendar.services.ProjectService;
import calendar.services.EntryService;
import calendar.services.TaskService;
import calendar.services.UserService;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author chriconn
 */
@RestController
@RequestMapping("entries")
public class UpdateEntryControllers {
    
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
    
    @RequestMapping(value="/createAll/{userId}/{taskId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createAllEntries(@RequestBody List<RestEntry> allRestEntries, @PathVariable Long userId, @PathVariable Long taskId){
        
        User user = userService.getUserById(userId);
        
        Task task = taskService.getTaskById(taskId);
        
        entryService.updateAll(allRestEntries, user, task);
        
        return buildResponse(user, task, allRestEntries.get(0).getDate());
    }
    
    @RequestMapping(value="/createSingle/{userId}/{taskId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createSingleEntry(@RequestBody RestEntry restEntry, @PathVariable Long userId, @PathVariable Long taskId){
        
        User user = userService.getUserById(userId);
        
        Task task = taskService.getTaskById(taskId);
        
        entryService.updateSingle(restEntry, user, task);
        
        return buildResponse(user, task, restEntry.getDate());
    }
    
    @RequestMapping(value="/deleteSingle/{userId}/{entryId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse deleteSingleEntry(@PathVariable Long userId, @PathVariable Long entryId) throws ForbiddenException{
        
        User user = userService.getUserById(userId);
        Entry entry = entryService.findById(entryId);
        Task task = entry.getTask();
        Date date = entry.getDate();
        
        if(!user.getId().equals(entry.getUser().getId())){
            throw new ForbiddenException("/deleteSingle/"+user.getId()+"/"+entryId);
        }
        
        entryService.deleteSingle(entryId);
        
        return buildResponse(user, task, date);
    }
    
    private RestResponse buildResponse(User user, Task task, Date date){
        RestResponse restResponse = new RestResponse();
        
       
        DaySummary daySummary = entryService.getUserAndTaskRowsForDay(date, user, task);
        
        restResponse.setDaySummary(daySummary);
        restResponse.setWeekSummary(buildWeekSummary(user, task, date));
        restResponse.setWeekBeginning(DateUtils.calculateWeekBeginning(date));
        restResponse.setUser(user);
        
        return restResponse;
    }
    
    private WeekSummary buildWeekSummary(User user, Task task, Date date){
        WeekSummary weekSummary = new WeekSummary();
        List<WeekRow> weekRowList = entryService.getUserRowsForWeek(date, user);
        weekSummary.setWeekSummary(weekRowList);
        
        return weekSummary;
    }
    
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public RestErrorResponse handleForbidden(ForbiddenException ex){
        
        // This method should handle all not authorised exceptions - ensure the correct Exception class is used - and thrown in business logic.
        
         RestErrorResponse restErrorResponse = new RestErrorResponse(); 
         restErrorResponse.setMessage("Forbidden Request "+ex.getMessage());
         return restErrorResponse;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestErrorResponse handleException(Exception ex){
        
        RestErrorResponse restErrorResponse = new RestErrorResponse();
        restErrorResponse.setMessage(ex.getMessage());
        return restErrorResponse;
    }
    
    
}
