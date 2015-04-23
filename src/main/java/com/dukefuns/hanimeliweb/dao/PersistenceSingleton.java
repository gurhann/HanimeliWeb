/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gurhan
 */
public class PersistenceSingleton {

    /**
     * The singleton EntityManagerFactory instance that is used across the
     * application.
     */
    private static EntityManagerFactory m_entityManagerFactory;

    /**
     * A singleton EntityManager instance that is only used during application
     * initialization
     */
    private static EntityManager m_entityManager;

    /**
     * Obtains the singleton EntityManagerFactory instance.
     *
     * @return a EntityManagerFactory instance
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (m_entityManagerFactory == null) {
            try {
                m_entityManagerFactory = Persistence.createEntityManagerFactory("hanimeli-pu");
            } catch (Exception e) {
            }

        }
        return m_entityManagerFactory;
    }

    /**
     * Obtains the singleton EntityManager instance.
     *
     * @return a EntityManager instance
     */
    public static EntityManager getEntityManager() {
        if (m_entityManager == null) {
            try {
                m_entityManager = getEntityManagerFactory().createEntityManager();
            } catch (Exception e) {
            }

        }
        return m_entityManager;
    }

}
