/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.model;

import com.dukefuns.hanimeliweb.AbstractPersistentTest;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author gurhan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddTests extends AbstractPersistentTest {

    @Test
    public void t1PermissionMustbePersistable() {
        Permission per = new Permission();
        per.setName("deneme");
        tx.begin();
        em.persist(per);
        tx.commit();
        assertNotNull("ID should not be null", per.getId());
        System.out.println(per.getName() + "-" + per.getId());
    }

   

    @Test
    public void t2CountryMustBePersistable() {
        Country c = new Country("İstabul");
        tx.begin();
        em.persist(c);
        tx.commit();
        assertNotNull("id boş olamaz", c.getId());

    }

    @Test
    public void t3TownMusBePersistable() {
        Country c = new Country("Ankara");
        Town t = new Town("Kızılay", c);
        tx.begin();
        em.persist(t);
        tx.commit();
        assertNotNull("id boş olamaz", c.getId());
        assertNotNull("id boş olamaz", t.getId());
    }

    @Test
    public void t4AddressMustBePersistable() {
        Country c = new Country("Erzurum");
        Town t = new Town("Aziziye", c);
        Address addres = new Address(t, "Dadaşkent");
        tx.begin();
        em.persist(addres);
        tx.commit();
        assertNotNull("id boş olamaz", c.getId());
        assertNotNull("id boş olamaz", t.getId());
        assertNotNull("id boş olamaz", addres.getId());
    }

    @Test
    public void t5UserMustBePersistable() {
        Person user = createUser("user-test","user-test");
        tx.begin();
        em.persist(user);
        tx.commit();
        assertNotNull("user id boş olamaz", user.getId());
        Assert.assertEquals("Erzurum", user.getAdres().getTown().getCountry().getName());
    }

   @Test
   public void t7CommentMustBePersistable() {
       Person p = em.find(Person.class, 1);
       Comment c = new Comment(p,"ilk yorum");
       tx.begin();
       em.persist(c);
       tx.commit();
       assertNotNull("Comment id boş olamaz", c.getId());
       assertNotNull("comment tarih boş olamaz", c.getDate());
       System.out.println(c.getDate());
   }


    private Person createUser(String mail, String username) {
        Address address = em.find(Address.class, new Long(1));
        Permission p = em.find(Permission.class, new Integer(1).byteValue());
        Person user = new Person();
        user.setName("gürhan");
        user.setLastname("küçük");
        user.setUsername(username);
        user.setPassword("dada");
        user.setEmail(mail);
        user.setTelNo("dafasf");
        user.setPermission(p);
        user.setAdres(address);
        return user;
    }


}
