/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 *
 * @author gurhan
 */
@Entity
@NamedQueries({
    @NamedQuery(
        name = "Town.findByName",
        query = "SELECT t FROM Town t WHERE t.name = :name"
    )
})
public class Town implements Serializable {

    // İlçeyi temsil eder

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    
    private String name;
    
    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.PERSIST)
    private Country country;

    public Town() {

    }

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;

    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "com.dukefuns.hanimeliweb.model.Town[ id=" + id + " ]";
    }

}
