/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.GalleryDao;
import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dcimen
 */
@SessionScoped
@ManagedBean
public class GalleryServiceBean implements Serializable {

    private Gallery kithckengallery;
    @EJB
    GalleryDao gDao;
    @ManagedProperty("#{personServiceBean}")
    PersonServiceBean service;

    @PostConstruct
    public void init() {
        kithckengallery = gDao.findKitchenGalleryByUserId(service.getPerson());
        
    }

    public PersonServiceBean getService() {
        return service;
    }

    public void setService(PersonServiceBean service) {
        this.service = service;
    }

    public Gallery getKithckengallery() {
        return kithckengallery;
    }

    public void setKithckengallery(Gallery kithckengallery) {
        this.kithckengallery = kithckengallery;
    }
    
}
