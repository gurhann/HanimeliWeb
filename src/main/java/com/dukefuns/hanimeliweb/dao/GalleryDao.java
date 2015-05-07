/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import com.dukefuns.hanimeliweb.model.Gallery;
import com.dukefuns.hanimeliweb.model.Person;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.Stateless;

/**
 *
 * @author dcimen
 */
@Stateless
public class GalleryDao extends GenericDaoImp<Gallery> implements Serializable {

    public Gallery findKitchenGalleryByUserId(Person person) {

        HashMap<String, String> hash = new HashMap<>();
        hash.put("userId",  String.valueOf(person.getId()));
        try {
            return findNamedQueryInt("Gallery.findKitchenGalleryByUserId", Gallery.class, hash).get(0);
        } catch (Exception e) {
            System.out.println(e);

        }
        return null;

    }
}
