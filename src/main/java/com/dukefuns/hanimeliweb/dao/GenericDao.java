/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import java.util.HashMap;

import java.util.List;

/**
 *
 * @author gurhan
 */
public interface GenericDao<T> {

    public T save(T tz);

    public T find(Class type, Object id);

    public T update(T t);

    public void delete(Class type, Object id);

    public List findNamedQuery(String queryName, Class type);
    
    public List findPagination(Class type, int start, int finish);

    public List findNamedQuery(String queryName, Class type, HashMap<String,String> hash);
}
