package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.AnejoContrato;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AnejoJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public AnejoJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public AnejoJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(AnejoContrato anejo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(anejo);
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
    
    public List<AnejoContrato> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<AnejoContrato> query = em.createQuery(
                "SELECT ac FROM AnejoContrato ac", AnejoContrato.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public AnejoContrato findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(AnejoContrato.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(AnejoContrato anejo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(anejo);
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

    public void CreateOrUpdate(AnejoContrato anejo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (anejo.getId() == null) em.persist(anejo); // Nuevo
            else em.merge(anejo);   // Actualiza existente          
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
            AnejoContrato anejo = em.find(
                AnejoContrato.class, 
                id);
            if (anejo == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(anejo);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
