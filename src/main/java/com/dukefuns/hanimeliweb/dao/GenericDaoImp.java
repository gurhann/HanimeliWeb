/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gurhan
 */
public class GenericDaoImp<T> implements GenericDao<T>{
    
    @PersistenceContext(unitName = "hanimeli-pu")
    EntityManager em;
    @Override
    public T save(T t) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
    }

     @Override
    public T update(T t) {
        return (T) this.em.merge(t);
    }

   @Override
    public T find(Class type, Object id) {
        return (T) this.em.find(type, id);
    }

    @Override
    public void delete(Class type, Object id) {
        Object ref = this.em.getReference(type, id);
        this.em.remove(ref);
    }

    
}
