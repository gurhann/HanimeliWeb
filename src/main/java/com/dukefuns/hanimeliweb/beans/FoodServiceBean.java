/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.FoodDao;
import com.dukefuns.hanimeliweb.model.Food;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dcimen
 */
@ManagedBean
@SessionScoped
public class FoodServiceBean implements Serializable{
    @EJB
    FoodDao fDao;
    private List<Food> foodList;
    @ManagedProperty("#{shiroservice}")
    ShiroService service;
    
    
    @PostConstruct
    public void init(){
    foodList =fDao.findFoodByUserId(service.getPerson());
    
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public ShiroService getService() {
        return service;
    }

    public void setService(ShiroService service) {
        this.service = service;
    }
    
    
}
