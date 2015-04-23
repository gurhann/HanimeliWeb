/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author gurhan
 */
@Entity
public class FoodCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short id;
    
    private byte row;
    
    @Column(length = 40, unique = true)
    private String name;
    @Column(length = 40)
    private String defination;
    
    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foods;
    
    @OneToMany(mappedBy="parentCategory")
    private List<FoodCategory> parentCategories;
    
    @ManyToOne
    private FoodCategory parentCategory;
    
    
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodCategory)) {
            return false;
        }
        FoodCategory other = (FoodCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    
    public void addFood(Food food) {
        foods.add(food);
    }

    public List<FoodCategory> getParentCategories() {
        return parentCategories;
    }

    public FoodCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategories(List<FoodCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public void setParentCategory(FoodCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public String getDefination() {
        return defination;
    }

    public void setDefination(String defination) {
        this.defination = defination;
    }
    
    
    

    @Override
    public String toString() {
        return "com.dukefuns.hanimeliweb.model.FoodCategory[ id=" + id + " ]";
    }
    
}
