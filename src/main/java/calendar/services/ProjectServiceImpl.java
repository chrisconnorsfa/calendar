/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.services;

import calendar.domain.LookupResponse;
import calendar.domain.entities.Organisation;
import calendar.domain.entities.Project;
import calendar.domain.entities.Task;
import calendar.repositories.OrganisationRepository;
import calendar.repositories.ProjectRepository;
import calendar.repositories.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chriconn
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    
    private ProjectRepository projectRepository;
    
    private OrganisationRepository organisationRepository;
    
    private TaskRepository taskRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    @Autowired
    public void setOrganisationRepository(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public Project getProjectById(Long id) {
       return projectRepository.findOne(id);
    }
    
    @Override
    public List<LookupResponse> getProjectsByOrganisation(Long id) {
        Organisation organisation = organisationRepository.findOne(id);        
        List<Project> projectList = projectRepository.getByOrganisation(organisation); 
        
        List<LookupResponse> returnList = new ArrayList();
        projectList.stream().forEach(project -> {
            returnList.add(new LookupResponse(project.getName(), project.getId()));
        });
        return returnList;
        //return projectList.stream().collect(Collectors.toList(new LookupResponse(null, id)));
        //return projectList.stream().collect(Collectors.toMap(Project::getId, Project::getName));
    }
    
    @Override
    public List<LookupResponse> getTasksByProject(Long id){
        Project project = projectRepository.findOne(id);
        List<Task> taskList = taskRepository.findByProject(project);
        List<LookupResponse> returnList = new ArrayList();
        taskList.stream().forEach(task -> {
            returnList.add(new LookupResponse(task.getDescription(), task.getId()));
        });
        return returnList;
    }
    
}
