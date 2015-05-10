/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.model.Person;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dcimen
 */
@SessionScoped
@ManagedBean
public class PersonServiceBean implements Serializable {

    private String username;
    private Person person;

    @EJB
    PersonDao personDao;

    public PersonServiceBean() {

    }

    public void init() {
        if (username != null) {
            person = personDao.findUserByUserName(username);
          
        } else {
            person = null;
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
