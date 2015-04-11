/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Country;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author dcimen
 */
@Stateless
public class CountryDao extends GenericDaoImp<Object> implements Serializable{
  
    public CountryDao() {
    }

    public List findAll() {
        String query = "Country.findAll";
        return findNamedQuery(query,Country.class);
    }

 
  
    
    
  
    
}
