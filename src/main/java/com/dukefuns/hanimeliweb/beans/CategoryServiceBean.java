/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.FoodCategoryDao;
import com.dukefuns.hanimeliweb.model.FoodCategory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dcimen
 */
@ManagedBean
@SessionScoped
public class CategoryServiceBean implements Serializable{
    @EJB
    FoodCategoryDao categoryDao;
    
    private List<FoodCategory> categories ;
    
    @PostConstruct
    public void init(){
    categories = categoryDao.findPagination(FoodCategory.class, 1, 100);
    
    }

    public List<FoodCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FoodCategory> categories) {
        this.categories = categories;
    }
    
    
    
}
