package com.vtc.persistencia;

import java.util.List;

import com.vtc.modelo.Convenio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ConvenioRepository {

    private EntityManager em;

    public ConvenioRepository(EntityManager em) {
        this.em = em;
    }

    public List<Convenio> findAll() {
        TypedQuery<Convenio> query = em.createQuery("SELECT c FROM Convenio c", Convenio.class);
        return query.getResultList();
    }

    public void save(Convenio convenio) {
        em.getTransaction().begin();
        em.persist(convenio);
        em.getTransaction().commit();
    }
}
