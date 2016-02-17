/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ChriConn
 */
public interface DateUtils {
    
    public static Date calculateWeekBeginning(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }
}
