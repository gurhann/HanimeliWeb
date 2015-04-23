/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.FoodCategoryDao;
import com.dukefuns.hanimeliweb.model.FoodCategory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dcimen
 */
@ManagedBean
@ViewScoped
public class CategoryManagerBean implements Serializable {
    @ManagedProperty("#{categoryServiceBean}")
    CategoryServiceBean serviceBean;
    @EJB
    FoodCategoryDao categoryDao;
    private String warn;
    private List<FoodCategory> categories = new ArrayList<FoodCategory>();
    private FoodCategory category = new FoodCategory();
    private FoodCategory parentCategory;
    private short parentId;
    private int id;

    @PostConstruct
    public void init() {
        categories = categoryDao.findPagination(FoodCategory.class, 1, 100);
        

    }

    public void findCategory() {
        try {
            if (id != 0) {
                category = categoryDao.find(FoodCategory.class, (short)id);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteCategory(int id) {
        try {
            categoryDao.delete(FoodCategory.class, (short)id);
            categories = categoryDao.findPagination(FoodCategory.class, 1, 100);
            serviceBean.init();
            this.warn = "<div class=\"alert alert-success\">\n"
                    + "							<strong>Başarılı!</strong> Kategori silindi.\n"
                    + "						</div>";
        } catch (Exception e) {
            this.warn = "<div class=\"alert alert-danger\">\n"
                    + "					    	<strong>Başarısız!</strong> Beklemediğimiz bir problem yüzünden silme işlemini yapamadınız. Lütfen daha sonra tekrar deneyiniz.\n"
                    + "					    </div>";
            System.out.println(e);
        }

    }

    public void addOrUpdateCategory() {
        try {
            if (category.getId() == null) {
                if (parentId == 0) {
                    categoryDao.save(category);
                } else {
                    parentCategory = categoryDao.find(FoodCategory.class, (short)parentId);
                  
                    category.setParentCategory(parentCategory);
                    categoryDao.save(category);
                }
                categories = categoryDao.findPagination(FoodCategory.class, 1, 100);
                this.warn = "<div class=\"alert alert-success\">\n"
                        + "							<strong>Başarılı!</strong> Kategori başarılı bir şekilde eklendi..\n"
                        + "						</div>";
            } else {
                if (parentId == 0) {
                    category.setParentCategory(null);
                } else {
                    parentCategory = categoryDao.find(FoodCategory.class, (short)parentId);
                    //parentMenu.getParentMenus().add(menu);
                    category.setParentCategory(parentCategory);
                }
                categoryDao.update(category);
                categories = categoryDao.findPagination(FoodCategory.class, 1, 100);
                this.warn = "<div class=\"alert alert-success\">\n"
                        + "							<strong>Başarılı!</strong> Kategori başarılı bir şekilde güncellendi..\n"
                        + "						</div>";
            }
            category = new FoodCategory();
            serviceBean.init();
        } catch (Exception e) {
            this.warn = "<div class=\"alert alert-danger\">\n"
                    + "					    	<strong>Başarısız!</strong> Beklemediğimiz bir problem yüzünden silme işlemini yapamadınız. Lütfen daha sonra tekrar deneyiniz.\n"
                    + "					    </div>";
            System.out.println(e);
        }

    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public List<FoodCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FoodCategory> categories) {
        this.categories = categories;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public FoodCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(FoodCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public short getParentId() {
        return parentId;
    }

    public void setParentId(short parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setServiceBean(CategoryServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }

    public CategoryServiceBean getServiceBean() {
        return serviceBean;
    }

    

}
