/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.FoodCategoryDao;
import com.dukefuns.hanimeliweb.dao.FoodDao;
import com.dukefuns.hanimeliweb.dao.GalleryDao;
import com.dukefuns.hanimeliweb.model.Food;
import com.dukefuns.hanimeliweb.model.FoodCategory;
import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.model.Picture;
import com.dukefuns.hanimeliweb.model.QuantityPrice;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import com.dukefuns.hanimeliweb.tools.UploadImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class FoodManagerBean implements Serializable {

    @EJB
    FoodDao fDao;
    @EJB
    FoodCategoryDao cDao;

    @ManagedProperty("#{shiroservice}")
    ShiroService shiroservice;

    @ManagedProperty("#{foodServiceBean}")
    FoodServiceBean foodservice;
    private String pretime;
    private Food food;
    private UploadImage img;
    private String price;
    private String resultFood;
    private QuantityPrice quantityPrice;
    private short foodCategoryid;

    public FoodManagerBean() {
        food = new Food();
        quantityPrice = new QuantityPrice();
        img = new UploadImage();
    }

    public void save() {
        Picture pic = new Picture();
        Gallery gallery = new Gallery();
        List<Picture> pictures = new ArrayList<Picture>();
        try {
            for (String pName : img.getUploadAttachment()) {
                pic.setPath(pName);
                pic.setGallery(gallery);
                pictures.add(pic);
                pic = new Picture();
            }
            FoodCategory fcat = cDao.find(FoodCategory.class, foodCategoryid);
            gallery.setPictures(pictures);
            food.setGallery(gallery);
            food.setFoodCategory(fcat);
            food.setUser(shiroservice.getPerson());
            quantityPrice.setPrice(Double.parseDouble(price));
            food.setQuantityPrice(quantityPrice);
            fDao.save(food);
            foodservice.init();
            this.resultFood = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-success\">\n"
                    + "                                            <strong>Başarılı!</strong> Yemek  eklendi.\n"
                    + "                                        </div>\n";
            pictures = null;
            return;

        } catch (Exception e) {
            System.out.println("asdsadsadas" + e);
            this.resultFood = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz yemek eklenemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
        }
        System.out.println("Başarılı");

    }

    public void delete(Food deleteFood) {
        try {
            fDao.delete(Food.class, deleteFood.getId());
            this.resultFood = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-success\">\n"
                    + "                                            <strong>Başarılı!</strong> Silme İşlemi gerçekleşti.\n"
                    + "                                        </div>\n";
            foodservice.init();
        } catch (Exception e) {
            this.resultFood = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz yemek silinemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
        }
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public UploadImage getImg() {
        return img;
    }

    public void setImg(UploadImage img) {
        this.img = img;
    }

    public String getPretime() {
        return pretime;
    }

    public void setPretime(String pretime) {
        this.pretime = pretime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getResultFood() {
        return resultFood;
    }

    public void setResultFood(String resultFood) {
        this.resultFood = resultFood;
    }

    public QuantityPrice getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(QuantityPrice quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public short getFoodCategoryid() {
        return foodCategoryid;
    }

    public void setFoodCategoryid(short foodCategoryid) {
        this.foodCategoryid = foodCategoryid;
    }

    public ShiroService getShiroservice() {
        return shiroservice;
    }

    public void setShiroservice(ShiroService shiroservice) {
        this.shiroservice = shiroservice;
    }

    public FoodServiceBean getFoodservice() {
        return foodservice;
    }

    public void setFoodservice(FoodServiceBean foodservice) {
        this.foodservice = foodservice;
    }

}
