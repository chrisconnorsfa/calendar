/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chriconn
 */
public class WeekSummary {
    
    private Date weekBeginning;
    
    private List<WeekRow> weekSummary;

    private BigDecimal day1Total;

    private BigDecimal day2Total;

    private BigDecimal day3Total;

    private BigDecimal day4Total;

    private BigDecimal day5Total;
    
    private BigDecimal day6Total;
    
    private BigDecimal day7Total;
    
    private BigDecimal dayWeekTotal;
    
    public WeekSummary() {
    
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

    public BigDecimal getDayWeekTotal() {
        return dayWeekTotal;
    }

    public void setDayWeekTotal(BigDecimal dayWeekTotal) {
        this.dayWeekTotal = dayWeekTotal;
    }

    public Date getWeekBeginning() {
        return weekBeginning;
    }

    public void setWeekBeginning(Date weekBeginning) {
        this.weekBeginning = weekBeginning;
    }

    public List<WeekRow> getWeekSummary() {
        return weekSummary;
    }

    public void setWeekSummary(List<WeekRow> weekSummary) {
        this.weekSummary = weekSummary;
    }

}
