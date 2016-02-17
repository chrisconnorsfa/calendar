/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

import calendar.domain.entities.Project;
import calendar.domain.entities.Task;
import calendar.domain.entities.User;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author chriconn
 */
public class WeekRow {
    
    private Project project;
    
    private Task task;
    
    private User user;
    
    private Date startDate;
    
    private String summery;
    
    private Date day1;

    private Date day2;

    private Date day3;

    private Date day4;

    private Date day5;

    private Date day6;
    
    private Date day7;
    
    private BigDecimal day1Total;
    
    private BigDecimal day2Total;
    
    private BigDecimal day3Total;
    
    private BigDecimal day4Total;
    
    private BigDecimal day5Total;
    
    private BigDecimal day6Total;
    
    private BigDecimal day7Total;
      
    private BigDecimal weekTotal;
    
    
    public WeekRow() {
        day1Total = new BigDecimal(0);
        day2Total = new BigDecimal(0);
        day3Total = new BigDecimal(0);
        day4Total = new BigDecimal(0);
        day5Total = new BigDecimal(0);
        day6Total = new BigDecimal(0);
        day7Total = new BigDecimal(0);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

       
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDay1() {
        return day1;
    }

    public void setDay1(Date day1) {
        this.day1 = day1;
    }

    public Date getDay2() {
        return day2;
    }

    public void setDay2(Date day2) {
        this.day2 = day2;
    }

    public Date getDay3() {
        return day3;
    }

    public void setDay3(Date day3) {
        this.day3 = day3;
    }

    public Date getDay4() {
        return day4;
    }

    public void setDay4(Date day4) {
        this.day4 = day4;
    }

    public Date getDay5() {
        return day5;
    }

    public void setDay5(Date day5) {
        this.day5 = day5;
    }

    public Date getDay6() {
        return day6;
    }

    public void setDay6(Date day6) {
        this.day6 = day6;
    }

    public Date getDay7() {
        return day7;
    }

    public void setDay7(Date day7) {
        this.day7 = day7;
    }
    
    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public BigDecimal getWeekTotal() {
        return weekTotal;
    }

    public void setWeekTotal(BigDecimal weekTotal) {
        this.weekTotal = weekTotal;
    }

    public BigDecimal getDay1Total() {
        return day1Total;
    }

    public void setDay1Total(BigDecimal day1Total) {
        this.day1Total = day1Total;
    }

    public BigDecimal getDay2Total() {
        return day2Total;
    }

    public void setDay2Total(BigDecimal day2Total) {
        this.day2Total = day2Total;
    }

    public BigDecimal getDay3Total() {
        return day3Total;
    }

    public void setDay3Total(BigDecimal day3Total) {
        this.day3Total = day3Total;
    }

    public BigDecimal getDay4Total() {
        return day4Total;
    }

    public void setDay4Total(BigDecimal day4Total) {
        this.day4Total = day4Total;
    }

    public BigDecimal getDay5Total() {
        return day5Total;
    }

    public void setDay5Total(BigDecimal day5Total) {
        this.day5Total = day5Total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public BigDecimal getDay6Total() {
        return day6Total;
    }

    public void setDay6Total(BigDecimal day6Total) {
        this.day6Total = day6Total;
    }

    public BigDecimal getDay7Total() {
        return day7Total;
    }

    public void setDay7Total(BigDecimal day7Total) {
        this.day7Total = day7Total;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
}
