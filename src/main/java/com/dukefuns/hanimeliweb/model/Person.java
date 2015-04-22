/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author gurhan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.findUserByMail", 
            query = "SELECT p  FROM Person p Where p.email =:email"),
    @NamedQuery(name = "Person.findUserByUserName", 
            query = "SELECT p  FROM Person p Where p.username =:username")
})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    private String name;
    
    @Column(length = 40)
    private String lastname;

    @Column(unique = true, length = 40)
    private String email;
    
    @Column(unique = true, length = 40)
    private String username;
    
    @Column(length = 32)
    private String password;
    
    @Column(length = 11)
    private String telNo;

    @OneToOne(cascade = CascadeType.ALL)
    private Address adres;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Permission permission;
    
    @OneToMany
    private List<Comment> comments;

    public Person() {
        adres = new Address();
        comments = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Address getAdres() {
        return adres;
    }

    public void setAdres(Address adres) {
        this.adres = adres;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   public Comment getCommentByIndex(int index){
       return this.comments.get(index);
   }

    @Override
    public String toString() {
        return "com.dukefuns.hanimeliweb.model.Person[ id=" + id + " ]";
    }

}
