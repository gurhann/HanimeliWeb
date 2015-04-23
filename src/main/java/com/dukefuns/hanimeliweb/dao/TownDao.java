/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Town;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.Stateless;

/**
 *
 * @author dcimen
 */
@Stateless
public class TownDao extends GenericDaoImp<Town> implements Serializable{

    public TownDao() {
    }
    
    public Town findTownByName(String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        return findNamedQuery("Town.findByName", Town.class, map).get(0);
    }
    
    
}
