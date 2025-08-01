package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.Driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class DriverJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public DriverJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public DriverJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(Driver driver) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(driver);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            } 
        }
    }

    //===>> (R) READ <<===//
    
    public List<Driver> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<Driver> query = em.createQuery(
                "SELECT d FROM Driver d", Driver.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public Driver findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(Driver.class, id);
        }catch(Exception e) {
            return null;
        }
    }
        
    public Driver findByNick(String nick) { // try-with-resources "solo" para SELECT (NO CON TRANSACTIONS)
        // try-with-resources porque EntityManager implementa la interfaz AutoCloseable
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<Driver> query = em.createQuery(
                "SELECT d FROM Driver d WHERE d.nick = :nick", Driver.class);
            query.setParameter("nick", nick);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Driver findByNickAndPassword(String nick, String password) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Driver> query = em.createQuery(
                "SELECT d FROM Driver d WHERE d.nick = :nick AND d.password = :password", Driver.class);
            query.setParameter("nick", nick);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(Driver driver) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(driver);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            } 
        }
    }

    public void CreateOrUpdate(Driver driver) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (driver.getId() == null) em.persist(driver); // Nuevo
            else em.merge(driver);   // Actualiza existente          
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();           
            throw e;
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            } 
        }
    }

    //===>> (D) DELETE <<===//
        
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Driver driver = em.find(Driver.class, id);
            if (driver == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(driver);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
