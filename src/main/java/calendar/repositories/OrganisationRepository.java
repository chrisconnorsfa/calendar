/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.repositories;

import calendar.domain.entities.Organisation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author chriconn
 */
public interface OrganisationRepository extends CrudRepository<Organisation, Long>{
    
}
