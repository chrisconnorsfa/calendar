/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.domain.entities;

import calendar.domain.entities.Organisation;
import calendar.domain.entities.EntityBase;
import java.util.List;
import javax.persistence.Column;

/**
 *
 * @author chriconn
 */
public class Organisation_OLD extends EntityBase{

    private List<Organisation> departments;
    
    @Column
    private String name;
    
    @Column
    private String shortname;
    
    public Organisation_OLD() {
    
    }

    public List<Organisation> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Organisation> departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    
    
    
}
