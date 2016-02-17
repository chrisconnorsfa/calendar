/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers;

import calendar.domain.entities.Project;
import calendar.domain.DaySummary;
import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.domain.entities.Task;
import calendar.repositories.EntryRepository;
import calendar.services.ProjectService;
import calendar.services.DateUtils;
import calendar.services.EntryService;
import calendar.services.TaskService;
import calendar.services.UserService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author chriconn
 */
@Controller
@RequestMapping("daysummary")
public class DaySummaryController {
    
    
    private EntryService entryService;
    
    private UserService userService;
    
    private TaskService taskService;

    @Autowired
    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @RequestMapping(value="/{userId}/{taskId}/{startDate}",method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable Long userId, @PathVariable Long taskId, @PathVariable @DateTimeFormat(iso=ISO.DATE) Date startDate){
        
        System.out.println("Home Page");
        
        User user = userService.getUserById(userId);
        
        //Project project = categoryService.getProjectById(projectId);
        //Project project = categoryService.getProjectById(projectId);
        Task task = taskService.getTaskById(taskId);

        DaySummary daySummary = entryService.getUserAndTaskRowsForDay(startDate, user, task );
        modelMap.addAttribute("daySummary", daySummary);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("weekBeginning", DateUtils.calculateWeekBeginning(startDate));
        return "daysummary";

    }

    
}
