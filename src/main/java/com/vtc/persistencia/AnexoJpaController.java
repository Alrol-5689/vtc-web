package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.AnexoConvenio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AnexoJpaController implements Serializable {

     //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    public AnexoJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public AnexoJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(AnexoConvenio anexo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(anexo);
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
    
    public List<AnexoConvenio> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<AnexoConvenio> query = em.createQuery(
                "SELECT ac FROM AnexoConvenio ac", 
                AnexoConvenio.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public AnexoConvenio findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(AnexoConvenio.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(AnexoConvenio anexo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(anexo);
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

    public void CreateOrUpdate(AnexoConvenio anexo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (anexo.getId() == null) em.persist(anexo); // Nuevo
            else em.merge(anexo);   // Actualiza existente          
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
            AnexoConvenio anexo = em.find(AnexoConvenio.class, id);
            if (anexo == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(anexo);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
