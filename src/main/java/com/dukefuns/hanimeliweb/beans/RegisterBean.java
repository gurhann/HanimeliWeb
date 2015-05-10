/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.CountryDao;
import com.dukefuns.hanimeliweb.dao.GalleryDao;
import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.dao.TownDao;
import com.dukefuns.hanimeliweb.model.Country;
import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.model.Person;
import com.dukefuns.hanimeliweb.model.Picture;
import com.dukefuns.hanimeliweb.model.Town;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dcimen
 */
@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @EJB
    CountryDao cDao;
    
    @EJB
    PersonDao pDao;
    
    @EJB
    TownDao tDao;
    @EJB
    GalleryDao gDao;
    
    private Person user = new Person();
    private String password;
    private String warn;
    private String result;
    private boolean contract;
    private List<Country> countries;
    private Country country = new Country();
    private byte idCountry;
    private short idtown;
    private Gallery gallery;
    
    public RegisterBean() {
    }
    
    public void init() {
        countries = cDao.findAll();
        country = countries.get(0);
    }
    
    public void updateTown() {
        country = (Country) cDao.find(Country.class, idCountry);
    }
    
    public void registerUser() {
        try {
            user.getAdres().setTown((Town) tDao.find(Town.class, idtown));
            user.setAvatarPath("default.jpg");
            gallery = new Gallery();  
            gallery.setName("Mutfak");
            gallery.setType(false);
            pDao.save(user);
             gallery.setUser(user);
            gDao.save(gallery);
        } catch (Exception e) {
            this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-8 col-md-8 col-sm-8 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz sisteme kaydınız gerçekleşemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
            return;
        }
        this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                + "                                        <div class=\"alert alert-success\">\n"
                + "                                            <strong>Başarılı!</strong> Aramıza hoşgeldiniz. Kullanıcı girişi yaparak online olabililirsiniz.\n"
                + "                                        </div>\n"
                + "                                    </div> <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n";
        user = new Person();
    }
    
    public void equalPass() {
        if (!password.isEmpty() && !user.getPassword().equals(password)) {
            this.warn = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Vermiş olduğunuz parolalar uyuşmuyor.\n"
                    + "                                        </div>\n"
                    + "                                    </div> <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n";
        } else {
            this.warn = null;
        }
        
    }
    
    public Person getUser() {
        return user;
    }
    
    public void setUser(Person user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isContract() {
        return contract;
    }
    
    public void setContract(boolean contract) {
        this.contract = contract;
    }
    
    public String getWarn() {
        return warn;
    }
    
    public void setWarn(String warn) {
        this.warn = warn;
    }
    
    public List<Country> getCountries() {
        return countries;
    }
    
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
    
    public byte getIdCountry() {
        return idCountry;
    }
    
    public void setIdCountry(byte idCountry) {
        this.idCountry = idCountry;
    }
    
    public Country getCountry() {
        return country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    
    public short getIdtown() {
        return idtown;
    }
    
    public void setIdtown(short idtown) {
        this.idtown = idtown;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public String getResult() {
        return result;
    }
    
}
