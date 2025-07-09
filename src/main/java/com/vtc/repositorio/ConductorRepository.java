package com.vtc.repositorio;

import java.util.List;

import com.vtc.modelo.Conductor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ConductorRepository {

    private EntityManager em;

    public ConductorRepository(EntityManager em) {
        this.em = em;
    }

    public List<Conductor> findAll() {
        TypedQuery<Conductor> query = em.createQuery("SELECT c FROM Conductor c", Conductor.class);
        return query.getResultList();
    }

    public Conductor findById(Long id) {
        return em.find(Conductor.class, id);
    }

    public void save(Conductor conductor) {
        em.getTransaction().begin();
        em.persist(conductor);
        em.getTransaction().commit();
    }
}
