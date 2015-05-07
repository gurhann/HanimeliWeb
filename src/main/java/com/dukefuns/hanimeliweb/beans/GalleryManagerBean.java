/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.GalleryDao;
import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.model.Picture;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import com.dukefuns.hanimeliweb.tools.UploadImage;
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
public class GalleryManagerBean implements Serializable {

    private UploadImage img;
    private Gallery gallery;
    private List<Picture> pictures;
    private String resultGallery;
    @EJB
    GalleryDao gDao;
    @ManagedProperty("#{shiroservice}")
    ShiroService service;

    public GalleryManagerBean() {
        img = new UploadImage();
        gallery = new Gallery();
    }

    @PostConstruct
    public void init() {

    }

    public void kitchenGalleryMultiSave() {
        Picture pic = new Picture();
        try {
            gallery = gDao.findKitchenGalleryByUserId(service.getPerson());
            pictures = gallery.getPictures();
            for (String pName : img.getUploadAttachment()) {
                pic.setPath(pName);
                pic.setGallery(gallery);
                pictures.add(pic);
                pic = new Picture();
            }
            gallery.setPictures(pictures);
            gDao.save(gallery);
            this.resultGallery = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-success\">\n"
                    + "                                            <strong>Başarılı!</strong> Resim  eklendi.\n"
                    + "                                        </div>\n";
            pictures = null;
            return;

        } catch (Exception e) {
            this.resultGallery= " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz resim eklenemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
        }
        System.out.println("Başarılı");

    }

    public UploadImage getImg() {
        return img;
    }

    public void setImg(UploadImage img) {
        this.img = img;
    }

    public ShiroService getService() {
        return service;
    }

    public void setService(ShiroService service) {
        this.service = service;
    }

    public String getResultGallery() {
        return resultGallery;
    }

    public void setResultGallery(String resultGallery) {
        this.resultGallery = resultGallery;
    }

}
