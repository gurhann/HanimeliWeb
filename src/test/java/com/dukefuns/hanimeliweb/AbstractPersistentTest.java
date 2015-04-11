/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb;

import java.sql.SQLException;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author gurhan
 */
public class AbstractPersistentTest {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
    protected EntityManager em;
    protected EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws SQLException {
        if (em != null) {
            em.close();
        }
    }

    protected Long getRandomId() {
        return Math.abs(new Random().nextLong());
    }
}
