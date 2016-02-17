/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain.rest;

import calendar.domain.WeekRow;
import calendar.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ChriConn
 */
public class RestTimeScaffolding {
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    
    private User user;
    
    private List<WeekRow> weekRows;

    public RestTimeScaffolding() {
        
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<WeekRow> getWeekRows() {
        return weekRows;
    }

    public void setWeekRows(List<WeekRow> weekRows) {
        this.weekRows = weekRows;
    }
    
}
