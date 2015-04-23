/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.model.Person;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author dcimen
 */
@ViewScoped
@ManagedBean
public class PersonServiceBean implements Serializable {

    @ManagedProperty(value = "#{shiroservice}")
    ShiroService shiroservice;
    private String username;
    private Person person;
    @EJB
    PersonDao personDao;

    @PostConstruct
    public void init() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            person = personDao.findUserByUserName(shiroservice.getUsername());
        } else {
           person = personDao.findUserByUserName(username); 
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ShiroService getShiroservice() {
        return shiroservice;
    }

    public void setShiroservice(ShiroService shiroservice) {
        this.shiroservice = shiroservice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

}
