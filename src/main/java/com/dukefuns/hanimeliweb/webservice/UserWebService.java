/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukefuns.hanimeliweb.webservice;

import com.dukefuns.hanimeliweb.dao.PersonDao;
import com.dukefuns.hanimeliweb.dao.CountryDao;
import com.dukefuns.hanimeliweb.dao.TownDao;
import com.dukefuns.hanimeliweb.exclusionstrategy.AdresCountyGetTownExclusionStrategy;
import com.dukefuns.hanimeliweb.model.Country;
import com.dukefuns.hanimeliweb.model.Person;
import com.dukefuns.hanimeliweb.model.Town;
import com.dukefuns.hanimeliweb.util.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gurhan
 */
@Path("/User")
public class UserWebService {

    @EJB
    PersonDao pDao;
    @EJB
    CountryDao cDao;
    @EJB 
    TownDao tDao;

    @GET
    @Path("/checkUserByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message checkUserByEmail(@PathParam("email") String email) {
        Person p = pDao.findUserByMail(email);
        return personIsNullMessage(p, "Email");
    }

    @GET
    @Path("/checkUserByUserName/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message checkUserByUserName(@PathParam("userName") String userName) {
        Person p = pDao.findUserByUserName(userName);
        return personIsNullMessage(p, "Kullanıcı Adı");

    }

    @POST
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Message addUser(String s) {
        System.out.println(s);
        Gson gson = new Gson();
        Message m = new Message();
        try{
            Person user = gson.fromJson(s, Person.class);
            Country c = cDao.findByName(user.getAdres().getTown().getCountry().getName());
            Town t = tDao.findTownByName(user.getAdres().getTown().getName());
            if(c != null && t != null) {
                user.getAdres().getTown().setCountry(c);
                user.getAdres().setTown(t);
            }
            pDao.save(user);
            m.setResult(true);
            m.setContent("Kullanıcı Başarıyla Eklendi");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            m.setResult(false);
            m.setContent("Kullanıcı Eklenemedi Hata:"+e.getMessage());
        }
        
        return m;
    }
    
    @POST
    @Path("/controlMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public void controlMessage(Message message) {
        System.out.println(message.result);
        System.out.println(message.content);
    }
    
    
    
    private Message personIsNullMessage(Person p, String s) {
        Message m = new Message();
        Gson gson = new GsonBuilder().setExclusionStrategies(new AdresCountyGetTownExclusionStrategy()).create();
        if (p != null) {
            System.out.println(p.getName());
            System.out.println(p.getEmail());            
            System.out.println(gson.toJson(p));
            m.setResult(true);
            m.setContent("Bu " + s + " Sistemde Kayıt.");
        } else {
            m.setResult(false);
            m.setContent(s + " uygun.");
        }
        return m;
    }
}
