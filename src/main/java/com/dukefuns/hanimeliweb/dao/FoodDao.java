/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Food;
import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.model.Person;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author dcimen
 */
@Stateless
public class FoodDao extends GenericDaoImp<Food> implements Serializable {

    public List findFoodByUserId(Person person) {
        HashMap<String, String> hash = new HashMap<>();
        hash.put("userId", String.valueOf(person.getId()));
        try {
            return findNamedQueryInt("Food.findFoodByUserId", Food.class, hash);
        } catch (Exception e) {
            System.out.println(e);

        }
        return null;
    }

}
