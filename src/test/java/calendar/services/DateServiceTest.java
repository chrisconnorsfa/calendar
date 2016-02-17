/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.CalendarApplication;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author ChriConn
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarApplication.class)
public class DateServiceTest {
    
    @Autowired
    private DateService dateService;

    public DateServiceTest() {
    
    }
    
    @Test
    public void testDateFirstDayOfWeek(){
        
        Calendar cal = Calendar.getInstance();
        cal.set(2015,5,24);
        Date date1 = cal.getTime();
        Date date2 = dateService.calculateWeekBeginning(date1);
        cal.setTime(date2);
        assertEquals(2,cal.getFirstDayOfWeek());
     
    }
    
}
