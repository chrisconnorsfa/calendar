/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.domain.entities.User;

/**
 *
 * @author chriconn
 */
public interface UserService {
    
    User getUserById(Long id);
    
}
