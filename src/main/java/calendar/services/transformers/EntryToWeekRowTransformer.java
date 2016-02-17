/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services.transformers;

import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.services.DateUtils;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import static javafx.scene.input.KeyCode.R;
import org.apache.commons.collections4.Transformer;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author chriconn
 */
public class EntryToWeekRowTransformer implements Transformer<List<Entry>, WeekRow>{

    private User user;
    
    @Override
    public WeekRow transform(List<Entry> entries) {
        
        WeekRow row = new WeekRow();
        row.setProject(entries.get(0).getTask().getProject());
        row.setTask(entries.get(0).getTask());
        row.setStartDate(DateUtils.calculateWeekBeginning(entries.get(0).getDate()));
        
        row = processAllEntriesForWeekRow(entries, row);
        
        return row;
        
    }    
    
    private WeekRow processAllEntriesForWeekRow(List<Entry> entries, WeekRow weekRow){
        
        entries.stream().forEach(entry  -> {
            setEntryToDayInWeekRow(entry, weekRow);
        });
        
        BigDecimal weekTotal =entries.stream().map(
                (x) -> x.getHours())
                .reduce((x, y) -> x.add(y))
                .get();
        
        weekRow.setWeekTotal(weekTotal);
        
        return weekRow;
    
    }

    private BigDecimal setEntryToDayInWeekRow(Entry entry, WeekRow weekRow){
            Date date = entry.getDate();        
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            BigDecimal entryHours = entry.getHours();
             switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:  weekRow.setDay7Total(weekRow.getDay7Total().add(entryHours));
                         weekRow.setDay7(entry.getDate());
                         break;
                case Calendar.MONDAY:  weekRow.setDay1Total(weekRow.getDay1Total().add(entryHours));
                         weekRow.setDay1(entry.getDate());
                         break;
                case Calendar.TUESDAY:  weekRow.setDay2Total(weekRow.getDay2Total().add(entryHours));
                         weekRow.setDay2(entry.getDate());
                         break;
                case Calendar.WEDNESDAY:  weekRow.setDay3Total(weekRow.getDay3Total().add(entryHours));
                         weekRow.setDay3(entry.getDate());
                         break;
                case Calendar.THURSDAY:  weekRow.setDay4Total(weekRow.getDay4Total().add(entryHours));
                         weekRow.setDay4(entry.getDate());
                         break;
                case Calendar.FRIDAY:  weekRow.setDay5Total(weekRow.getDay5Total().add(entryHours));
                         weekRow.setDay5(entry.getDate());
                         break;
                case Calendar.SATURDAY:  weekRow.setDay6Total(weekRow.getDay6Total().add(entryHours));
                         weekRow.setDay6(entry.getDate());
                         break;

            }
             
            return entryHours; 
    }
  
    
}
