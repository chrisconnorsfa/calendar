/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.repositories;

import calendar.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chriconn
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
}
