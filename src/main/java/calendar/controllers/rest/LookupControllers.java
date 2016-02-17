/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.controllers.rest;

import calendar.domain.LookupResponse;
import calendar.domain.RestResponse;
import calendar.domain.entities.Organisation;
import calendar.domain.entities.Project;
import calendar.domain.rest.RestEntry;
import calendar.services.ProjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ChriConn
 */
@RestController
@RequestMapping("lookup")
public class LookupControllers {
    
    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @RequestMapping(value="/getProjects", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    private List<LookupResponse> getProjectsForOrganisation(){
        
        // Requires security check to pull organisation out of Security Context. 
        Long organisationId = 1L;
        
        return projectService.getProjectsByOrganisation(organisationId);
    }
    
    @RequestMapping(value="/getTasksByProjectId/{taskId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    private List<LookupResponse> getTasksByProjectId(@PathVariable Long taskId){
        
        // Requires security check to pull organisation out of Security Context. 
        
        return projectService.getTasksByProject(taskId);
    }
    
    
    
}
