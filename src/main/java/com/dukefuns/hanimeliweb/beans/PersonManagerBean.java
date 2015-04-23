/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.model.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dcimen
 */
@ManagedBean
@ViewScoped
public class PersonManagerBean implements Serializable {

    @EJB
    PersonDao personDao;
    private int page = 1;
    private List<Person> persons = new ArrayList<Person>();
    private String warn;

    @PostConstruct
    public void init() {
        persons = personDao.findUserByPage(page);
    }

    public PersonManagerBean() {
    }

    public void deleteUser(int id) {
        try {
            personDao.delete(Person.class, id);
            persons = personDao.findUserByPage(page);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
