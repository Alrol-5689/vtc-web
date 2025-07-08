package com.vtc.mainapp;

import com.vtc.modelo.Conductor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConexionBBDD {
    public static void main(String[] args) {
    EntityManagerFactory emf = null;
    EntityManager em = null;

    try {
        emf = Persistence.createEntityManagerFactory("vtc-autogestion");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        // Crear un conductor de prueba
        Conductor conductor = new Conductor();
        conductor.setNombre("Pedro");
        conductor.setApellido1("Pérez");
        conductor.setApellido2("Gómez");
        conductor.setDni("12345678A");
        conductor.setNick("pedrop");
        conductor.setTelefono("600123123");
        conductor.setEmail("pedro@example.com");
        
        em.persist(conductor); // Guardar en la base de datos

        em.getTransaction().commit();
        System.out.println("✅ ¡Conductor insertado y tablas generadas correctamente!");

    } catch (Exception e) {
        System.err.println("❌ Error durante la prueba:");
        e.printStackTrace();
    } finally {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
}