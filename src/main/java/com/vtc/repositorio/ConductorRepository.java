package com.vtc.repositorio;

import java.util.List;

import com.vtc.modelo.Driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ConductorRepository {

    private EntityManager em;

    public ConductorRepository(EntityManager em) {
        this.em = em;
    }

    public List<Driver> findAll() {
        TypedQuery<Driver> query = em.createQuery("SELECT c FROM Conductor c", Driver.class);
        return query.getResultList();
    }

    public Driver findById(Long id) {
        return em.find(Driver.class, id);
    }

    public void save(Driver conductor) {
        em.getTransaction().begin();
        em.persist(conductor);
        em.getTransaction().commit();
    }
}
