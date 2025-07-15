package com.vtc.persistencia;

import java.io.Serializable;
import java.util.List;

import com.vtc.modelo.Convenio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class ConvenioJpaController implements Serializable {

    //===>> ATRIBUTOS <<===//

    private EntityManagerFactory emf = null;

    //===>> CONSTRUCTORES <<===//

    public ConvenioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ConvenioJpaController() {
        emf = Persistence.createEntityManagerFactory("vtc-autogestion");
    }

    //===>> MÉTODOS <<===//

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenio convenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(convenio);
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

    public List<Convenio> findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Convenio> query = em.createQuery("SELECT c FROM Convenio c", Convenio.class);
        return query.getResultList();
    }

    public void save(Convenio convenio) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(convenio);
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
