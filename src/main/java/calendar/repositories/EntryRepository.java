/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.repositories;

import calendar.domain.entities.Project;
import calendar.domain.entities.Entry;
import calendar.domain.entities.Task;
import calendar.domain.entities.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chriconn
 */
public interface EntryRepository extends CrudRepository<Entry, Long>{

    List<Entry> findByDateBetween(Date startDate, Date endDate);
    
    List<Entry> findByUserAndDateBetween(User user, Date startDate, Date endDate);
    
    List<Entry> findByUserAndTaskAndDateBetween(User user, Task Task, Date startDate, Date endDate);
    
}
