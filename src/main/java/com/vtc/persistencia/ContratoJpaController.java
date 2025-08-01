package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.Contrato;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class ContratoJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public ContratoJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public ContratoJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(Contrato contrato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contrato);
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
    
    public List<Contrato> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<Contrato> query = em.createQuery(
                "SELECT c FROM Contrato c", Contrato.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public Contrato findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(Contrato.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(Contrato contrato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(contrato);
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

    public void CreateOrUpdate(Contrato contrato) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (contrato.getId() == null) em.persist(contrato); // Nuevo
            else em.merge(contrato);   // Actualiza existente          
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
            Contrato contrato = em.find(Contrato.class, id);
            if (contrato == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(contrato);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
