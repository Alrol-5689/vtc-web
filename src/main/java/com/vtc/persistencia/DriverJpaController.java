package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.excepciones.NonexistentEntityException;
import com.vtc.modelo.Driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class DriverJpaController implements Serializable {

    //===>> ATRIBUTOS <<===//

    private EntityManagerFactory emf = null;

    //===>> CONSTRUCTORES <<===//

    public DriverJpaController(EntityManagerFactory emf) {
        this.emf = emf; //===>> De momento esto no se va a usar. Es para testing... <<===//
    }

    public DriverJpaController() {
        this.emf = JpaUtil.getEntityManagerFactory();
    }

    //===>> MÉTODOS <<===//

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Driver driver) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(driver);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Driver> findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Driver> query = em.createQuery("SELECT c FROM Driver d", Driver.class);
        return query.getResultList();
    }

    public Driver findById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Driver.class, id);
    }

    public void save(Driver driver) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(driver);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Driver driver = em.find(Driver.class, id);
            if (driver == null) {
                throw new NonexistentEntityException("The driver with id " + id + " no longer exists.");
            }
            em.remove(driver);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em != null) em.close();
        }
    }
}
