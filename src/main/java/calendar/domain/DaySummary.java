/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain;

import calendar.domain.entities.Entry;
import calendar.domain.entities.Project;
import calendar.domain.entities.Task;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chriconn
 */
public class DaySummary {
    
    private List<Entry> entries;
    
    private BigDecimal total;
    
    private String day;

    public DaySummary() {
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
       
}
