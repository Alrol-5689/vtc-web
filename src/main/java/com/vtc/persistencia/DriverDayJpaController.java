package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.DriverDay;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class DriverDayJpaController implements Serializable {

    //\/\/\=========================>> ATRIBUTOS <=========================/\/\/\\

    private EntityManagerFactory emf = null;
    private static final Logger LOGGER = Logger.getLogger(DriverDayJpaController.class.getName());

    //\/\/\=========================>> CONSTRUCTORES <=========================/\/\/\\

    //===>> De momento esto no se va a usar. Es para testing... <<===//
    // public DriverDayJpaController(EntityManagerFactory emf) {this.emf = emf; }

    public DriverDayJpaController() {this.emf = JpaUtil.getEntityManagerFactory();}

    //\/\/\=========================>> MÉTODOS <=========================/\/\/\\

    //===>> EntityManager <<===//

    public EntityManager getEntityManager() {return emf.createEntityManager();}

    // C R U D :

    //===>> (C) CREATE <<===//

    public void create(DriverDay day) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(day);
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
    
    public List<DriverDay> findAll() {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<DriverDay> query = em.createQuery(
                "SELECT d FROM DriverDay d", 
                DriverDay.class);
            return query.getResultList();
        }catch(Exception e) {
            return null;
        }
    }
        
    public DriverDay findById(Long id) {
        try (EntityManager em = getEntityManager()) { 
            return em.find(DriverDay.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    public List<DriverDay> findByDriverId(Long driverId) {
        try (EntityManager em = getEntityManager()) { 
            TypedQuery<DriverDay> query = em.createQuery(
                "SELECT d FROM DriverDay d WHERE d.conductor.id = :driverId", 
                DriverDay.class);
            query.setParameter("driverId", driverId);
            return query.getResultList();
        }catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar los registros del conductor", e);
            return null;
        }
    }

    //===>> (U) UPDATE <<===//

    public void update(DriverDay day) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(day);
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

    public void CreateOrUpdate(DriverDay day) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (day.getId() == null) em.persist(day); // Nuevo
            else em.merge(day);   // Actualiza existente          
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
            DriverDay day = em.find(DriverDay.class, id);
            if (day == null) 
                throw new NonexistentEntityException(
                    "The driver with id " + id + " no longer exists.");           
        em.remove(day);
        em.getTransaction().commit();
        } finally {
            if (em != null) {
                if(em.getTransaction().isActive()) em.getTransaction().rollback();
                em.close();
            }               
        }                          
    }

}
