/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.dao.TownDao;
import com.dukefuns.hanimeliweb.model.Person;
import com.dukefuns.hanimeliweb.model.Town;
import com.dukefuns.hanimeliweb.shiro.ShiroService;
import com.dukefuns.hanimeliweb.tools.UploadImage;
import java.io.IOException;
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
public class PersonManagerBean implements Serializable {

    @EJB
    PersonDao personDao;
    private int page = 1;
    private List<Person> persons = new ArrayList<Person>();
    private String warn;
    private UploadImage img;
    private short idtown;
    private Person person;
    private String password;
    
    private String result;
    @ManagedProperty(value = "#{shiroservice}")
    ShiroService servis;

    @EJB
    TownDao tDao;

    @PostConstruct
    public void init() {
        person = servis.getPerson();
        persons = personDao.findUserByPage(page);
        
    }

    public PersonManagerBean() {

        img = new UploadImage();
    }

    public void deleteUser(int id) {
        try {
            personDao.delete(Person.class, id);
            persons = personDao.findUserByPage(page);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void equalPass() {
        if (!password.isEmpty() && !person.getPassword().equals(password)) {
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

    public void update() throws IOException {
     
        try {
            person.getAdres().setTown((Town) tDao.find(Town.class, idtown));
            if (!img.getUploadAttachment().isEmpty()) {
                person.setAvatarPath(img.getUploadAttachment().get(0));
            }
            person = personDao.update(person);
          
        } catch (Exception e) {
            this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz profil güncelleme gerçekleşemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
            return;
        }
        System.out.println("Result Çıktsı");
        this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                + "                                        <div class=\"alert alert-success\">\n"
                + "                                            <strong>Başarılı!</strong> Profiliniz güncellendi.\n"
                + "                                        </div>\n";
    }
       public void updatePassword() throws IOException {
        try {
            person = personDao.update(person);
          
        } catch (Exception e) {
            this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                    + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                    + "                                        <div class=\"alert alert-danger\">\n"
                    + "                                            <strong>Hata!</strong> Üzgünüz parola güncelleme gerçekleşemiyor. Lütfen sonra tekrar deneyiniz..\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n";
            return;
        }
        System.out.println("Result Çıktsı");
        this.result = " <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n"
                + "                                    <div class=\"col-lg-12 col-md-12 col-sm-12 \" >\n"
                + "                                        <div class=\"alert alert-success\">\n"
                + "                                            <strong>Başarılı!</strong> Parolanız güncellendi.\n"
                + "                                        </div>\n";
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public UploadImage getImg() {
        return img;
    }

    public void setImg(UploadImage img) {
        this.img = img;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdtown(short idtown) {
        this.idtown = idtown;
    }

    public short getIdtown() {
        return idtown;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ShiroService getServis() {
        return servis;
    }

    public void setServis(ShiroService servis) {
        this.servis = servis;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

}
