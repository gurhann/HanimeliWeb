/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.beans;

import com.dukefuns.hanimeliweb.dao.GenericDaoImp;
import com.dukefuns.hanimeliweb.model.User;
import java.io.Serializable;
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

    @EJB
    GenericDaoImp dao;
    private User user = new User();
    private String password;
    private String warn;
    private boolean contract;

    public RegisterBean() {
    }

    public void RegisterUser() {
        dao.save(user);

    }

    public void equalPass() {
        if (password != null && !user.getPassword().equals(password)) {
            this.warn=" <div class=\"col-lg-4 col-md-4 col-sm-4\"></div>\n" +
"                                    <div class=\"col-lg-8 col-md-8 col-sm-8 \" >\n" +
"                                        <div class=\"alert alert-danger\">\n" +
"                                            <strong>Hata!</strong> Vermiş olduğunuz parolalar uyuşmuyor.\n" +
"                                        </div>\n" +
"                                    </div>\n";
        }

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

}
