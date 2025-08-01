package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.PlusConvenio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class PlusConvenioJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public PlusConvenioJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public PlusConvenioJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(PlusConvenio plus) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(plus);
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
    
    public List<PlusConvenio> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<PlusConvenio> query = em.createQuery(
                "SELECT pc FROM PlusConvenio pc", PlusConvenio.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public PlusConvenio findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(PlusConvenio.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(PlusConvenio plus) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(plus);
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

    public void CreateOrUpdate(PlusConvenio plus) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (plus.getId() == null) em.persist(plus); // Nuevo
            else em.merge(plus);   // Actualiza existente
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
            PlusConvenio plus = em.find(PlusConvenio.class, id);
            if (plus == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(plus);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
