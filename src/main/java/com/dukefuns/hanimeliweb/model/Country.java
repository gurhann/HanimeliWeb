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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author gurhan
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c") 
public class Country implements Serializable {
    //ili temsil eder
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;
    @Column(length = 25)
    private String name;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Town> towns ;
    public Country() {
        
    }
    public Country(String name) {
        this.name = name;
    }
    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
    
    

    @Override
    public String toString() {
        return "com.dukefuns.hanimeliweb.model.Country[ id=" + id + " ]";
    }
    
}
