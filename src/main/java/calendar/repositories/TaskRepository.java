/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.repositories;

import calendar.domain.entities.Project;
import calendar.domain.entities.Task;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ChriConn
 */
public interface TaskRepository extends CrudRepository<Task, Long>{
    List<Task> findByProject(Project project);
}
