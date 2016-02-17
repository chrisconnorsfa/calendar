/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.domain.LookupResponse;
import calendar.domain.entities.Project;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chriconn
 */
public interface ProjectService {
    
    Project getProjectById(Long id);
    List<LookupResponse> getProjectsByOrganisation(Long id);
    List<LookupResponse> getTasksByProject(Long id);
    
}
