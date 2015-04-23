/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Country;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author dcimen
 */
@Stateless
public class CountryDao extends GenericDaoImp<Country> implements Serializable{
  
    public CountryDao() {
    }

    public List findAll() {
        String query = "Country.findAll";
        return findNamedQuery(query,Country.class);
    }
    
    public Country findByName(HashMap<String, String> hash) {
        Query query = em.createNamedQuery("Country.findByName", Country.class);
       
        if (hash != null && hash.size() > 0) {
            for (String key : hash.keySet()) {
                query.setParameter(key, hash.get(key));
            }
        }
        List<Country> ls = query.getResultList();
        System.out.println("sizei:"+ls.size());
        for(Country c : ls) {
            System.out.println("sss"+c.getId());
        }
        return  (Country)query.getSingleResult();
    }

    public Country findByName(String name) {
        Query query2 = em.createNamedQuery("Country.findByName", Country.class);
        query2.setParameter("name", name);
        List<Country> clist = query2.getResultList();
        System.out.println("metodiçiinde:"+isObjectManaged(clist.get(0)));
        return clist.get(0);
    }  
}
