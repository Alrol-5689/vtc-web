package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.PoliticaGratificacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class PoliticaGratificacionJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public PoliticaGratificacionJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public PoliticaGratificacionJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(PoliticaGratificacion gratificacion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gratificacion);
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
    
    public List<PoliticaGratificacion> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<PoliticaGratificacion> query = em.createQuery(
                "SELECT pg FROM PoliticaGratificacion pg", PoliticaGratificacion.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public PoliticaGratificacion findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(PoliticaGratificacion.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(PoliticaGratificacion gratificacion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(gratificacion);
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

    public void CreateOrUpdate(PoliticaGratificacion gratificacion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (gratificacion.getId() == null) em.persist(gratificacion); // Nuevo
            else em.merge(gratificacion);   // Actualiza existente          
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
            PoliticaGratificacion driver = em.find(PoliticaGratificacion.class, id);
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
