/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.domain.entities.Project;
import calendar.domain.DaySummary;
import calendar.domain.entities.Entry;
import calendar.domain.entities.User;
import calendar.domain.WeekRow;
import calendar.domain.entities.Task;
import calendar.domain.rest.RestEntry;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chriconn
 */
public interface EntryService {
    
    List<WeekRow> getUserRowsForWeek(Date startDate, User user);
    DaySummary getUserAndProjectRowsForDay(Date startDate, User user, Project project);
    DaySummary getUserAndTaskRowsForDay(Date startDate, User user, Task task, Integer index);
    DaySummary getUserAndTaskRowsForDay(Date startDate, User user, Task task);
    List<Entry> updateAll(List<RestEntry> allEntries, User user, Task task);
    Entry updateSingle(RestEntry restEntry, User user, Task task);
    Boolean deleteSingle(Long entryId);
    Entry findById(Long entryId);
}
