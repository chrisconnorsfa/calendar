/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

import calendar.domain.entities.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author chriconn
 */
public class RestResponse extends RestBase{
  
    private User user;
    private Date weekBeginning;
    private DaySummary daySummary;
    private WeekSummary weekSummary;

    public RestResponse() {
        
    }
    
    public DaySummary getDaySummary() {
        return daySummary;
    }

    public void setDaySummary(DaySummary daySummary) {
        this.daySummary = daySummary;
    }

    public WeekSummary getWeekSummary() {
        return weekSummary;
    }

    public void setWeekSummary(WeekSummary weekSummary) {
        this.weekSummary = weekSummary;
    }

    public Date getWeekBeginning() {
        return weekBeginning;
    }

    public void setWeekBeginning(Date weekBeginning) {
        this.weekBeginning = weekBeginning;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  

}
