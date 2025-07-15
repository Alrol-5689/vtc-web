package com.vtc.persistencia;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("vtc-autogestion");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
