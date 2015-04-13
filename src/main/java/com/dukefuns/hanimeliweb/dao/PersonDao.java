/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Person;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.Stateless;

/**
 *
 * @author dcimen
 */
@Stateless
public class PersonDao extends GenericDaoImp<Object> implements Serializable {

    public PersonDao() {
    }

    public Person findUserByMail(String mail) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("email", mail);
        try {
            return (Person) findNamedQuery("Person.findUserByMail", Person.class, hash).get(0);
        } catch (Exception e) {

        }
        return null;
    }

    public Person findUserByUserName(String username) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("username", username);
        try {
            return (Person) findNamedQuery("Person.findUserByUserName", Person.class, hash).get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
