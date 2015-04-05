/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

/**
 *
 * @author gurhan
 */
public interface GenericDao<T> {
    public T save(T t);

    public T find(Class type, Object id);

    public T update(T t);

    public void delete(Class type, Object id);

}
