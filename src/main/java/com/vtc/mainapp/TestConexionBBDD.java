package com.vtc.mainapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestConexionBBDD {
    public static void main(String[] args) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("vtc-autogestion");
            EntityManager em = emf.createEntityManager();
            System.out.println("✅ ¡Conexión exitosa a la base de datos!");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println("❌ Error al conectar con la base de datos:");
            e.printStackTrace();
        }


    }
}