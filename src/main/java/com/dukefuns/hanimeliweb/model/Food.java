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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author gurhan
 */
@NamedQueries({
    @NamedQuery(name = "Food.findFoodByUserId",
            query = "SELECT f FROM Food f WHERE f.user.id = :userId"),
    @NamedQuery(name = "Food.findFoodByCategoryId",
            query = "SELECT f FROM Food f WHERE f.foodCategory.id = :catagoryId")
})
@Entity
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String name;
    
    private String description;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Gallery gallery;
   
    
    @OneToOne
    private Person user;
    @OneToOne(cascade = CascadeType.ALL)
    private QuantityPrice quantityPrice;
    @ManyToOne(targetEntity = FoodCategory.class, cascade = CascadeType.PERSIST)
    private FoodCategory foodCategory;
    
    private String materials;
    @Column(columnDefinition = "char(6)")
    private String preparationTime;
    
    @OneToMany
    private List<Comment> comments;

    public Food() {
        comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public void addComment(Comment comment){
        comments.add(comment);
    }

    public QuantityPrice getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(QuantityPrice quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dukefuns.hanimeliweb.model.Food[ id=" + id + " ]";
    }

}
