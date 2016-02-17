/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers;

import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.repositories.EntryRepository;
import calendar.services.DateUtils;
import calendar.services.EntryService;
import calendar.services.UserService;
import java.util.Calendar;
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
@RequestMapping("weeksummary")
public class WeekSummaryController {
    
    @Autowired
    private EntryService entryService;
    
    @Autowired
    private UserService userService;

    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value="/{userId}/{startDate}",method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable Long userId, @PathVariable @DateTimeFormat(iso=ISO.DATE) Date startDate){
        
        System.out.println("Home Page");
        
        User user = userService.getUserById(userId);

        List<WeekRow> allEntries = entryService.getUserRowsForWeek(startDate, user);
        modelMap.addAttribute("allEntries", allEntries);
        modelMap.addAttribute("user", user);
        
        Date weekBeginning = DateUtils.calculateWeekBeginning(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(weekBeginning);
        modelMap.addAttribute("weekBeginning", weekBeginning);
        modelMap.addAttribute("weekBeginningText", cal.get(Calendar.YEAR));
        return "weeksummary";

    }
    
    
}
