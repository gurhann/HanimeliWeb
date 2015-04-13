/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.shiro;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author dcimen
 */
@ManagedBean(name = "shiroservice")
@SessionScoped
public class ShiroService implements Serializable {

    private String username;
    private String password;

    public ShiroService() {

    }

    public String login() {
        String returnPage = "";
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(this.username, this.password);
        try {
            user.login(token);
            returnPage = "/index.jsf?faces-redirect=true";
        } catch (UnknownAccountException | IncorrectCredentialsException uae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriş başarısız!! Lütfen kullanıcı adınızı veya parolanızı kontrol ediniz.", "Bu kullanıcı geçersiz yada tanımlı değil."));
            return null;
        } catch (LockedAccountException lae) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriş başarısız2", "Bu kullanıcı Banlanmış."));
            return null;
        } catch (AuthenticationException aex) {
            System.out.println(aex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriş başarısız3", "Bu kullanıcı Yetkisiz"));
            return null;
        }
        return returnPage;
    }

    public String logout() {
        String returnPage = "/index.jsf?faces-redirect=true";
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.logout();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Çıkış başarısız", "Çıkış yapılamadı."));
        }
        return returnPage;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   
}
