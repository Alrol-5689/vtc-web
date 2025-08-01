package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.PoliticaComision;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class PoliticaComisionJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public PoliticaComisionJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public PoliticaComisionJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(PoliticaComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comision);
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
    
    public List<PoliticaComision> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<PoliticaComision> query = em.createQuery(
                "SELECT pc FROM PoliticaComision pc", PoliticaComision.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public PoliticaComision findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(PoliticaComision.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(PoliticaComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comision);
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

    public void CreateOrUpdate(PoliticaComision comision) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (comision.getId() == null) em.persist(comision); // Nuevo
            else em.merge(comision);   // Actualiza existente          
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
            PoliticaComision comision = em.find(PoliticaComision.class, id);
            if (comision == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(comision);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
