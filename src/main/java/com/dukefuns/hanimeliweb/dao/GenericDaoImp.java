package com.dukefuns.hanimeliweb.dao;

import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author gurhan
 */
public class GenericDaoImp<T> implements GenericDao<T> {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    @PostConstruct
    public void init() {
        emf = PersistenceSingleton.getEntityManagerFactory();
        em = PersistenceSingleton.getEntityManager();
        tx = em.getTransaction();
    }

    @PreDestroy
    public void destroy() {
        emf.close();
        em.close();

    }

    @Override
    public T save(T t) {
        tx.begin();
        em.persist(t);
        tx.commit();
        return t;
    }

    @Override
    public T update(T t) {
        tx.begin();
        em.merge(t);
        tx.commit();
        return t;
    }

    @Override
    public T find(Class type, Object id) {
        return (T) this.em.find(type, id);
    }

    @Override
    public void delete(Class type, Object id) {
        Object ref = this.em.getReference(type, id);
        tx.begin();
        em.remove(ref);
        tx.commit();
    }

    @Override
    public List<T> findNamedQuery(String queryName, Class type) {
        return this.em.createNamedQuery(queryName, type).getResultList();
    }

    @Override
    public List<T> findNamedQuery(String queryName, Class type, HashMap<String, String> hash) {
        TypedQuery query = this.em.createNamedQuery(queryName, type);
        if (hash != null && hash.size() > 0) {
            for (String key : hash.keySet()) {
                query.setParameter(key, hash.get(key));
            }
        }
        return query.getResultList();
    }
      @Override
    public List<T> findNamedQueryInt(String queryName, Class type, HashMap<String, String> hash) {
        TypedQuery query = this.em.createNamedQuery(queryName, type);
        if (hash != null && hash.size() > 0) {
            for (String key : hash.keySet()) {
                query.setParameter(key, Integer.valueOf(hash.get(key)));
            }
        }
        return query.getResultList();
    }
    
    @Override
    public boolean isObjectManaged(T t) {
        return em.contains(t);
    }

    @Override
    public List findPagination(Class type, int start, int finish) {
        Query query = this.em.createQuery("From " + type.getSimpleName() + " c");
        query.setFirstResult((start - 1) * finish);
        query.setMaxResults(finish);
        return query.getResultList();
    }

}
