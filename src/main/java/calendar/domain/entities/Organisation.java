/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain.entities;

import calendar.domain.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author chriconn
 */
@Entity
@Table(name = "Organisation")
public class Organisation extends EntityBase{
    
    @Column
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisation")
    private List<Project> projects;
    
    @Column
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisation")
    private List<User> users;
    
    @Column
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisation")
    private List<User> administrators;
    
    @Column
    private String name;
    
    @Column
    private String shortName;

    public Organisation() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<User> administrators) {
        this.administrators = administrators;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    
}
