/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services.transformers;

import calendar.domain.DaySummary;
import calendar.domain.entities.Entry;
import calendar.domain.entities.Project;
import calendar.domain.entities.Task;
import calendar.domain.entities.User;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.collections4.Transformer;

/**
 *
 * @author chriconn
 */
public class EntryToDayTransformer implements Transformer<List<Entry>, DaySummary>{
    
    private Date date;
    private final DaySummary daySummary;
    private User user;
    
    public EntryToDayTransformer(Date date, User user) {
        this.daySummary = new DaySummary();
        this.setDate(date);
        this.setUser(user);
    }

    @Override
    public DaySummary transform(List<Entry> entryList) {
        
        BigDecimal total = new BigDecimal(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        if(! entryList.isEmpty()){       
            daySummary.setEntries(entryList);
            total = entryList.stream().map((x) -> x.getHours()).reduce((x, y) -> x.add(y)).get();
        }
                
        daySummary.setDay(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.UK));
        
        daySummary.setTotal(total);
        
        return daySummary;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setUser(User user) {
        this.user = user;
    }
    
    
    
}


