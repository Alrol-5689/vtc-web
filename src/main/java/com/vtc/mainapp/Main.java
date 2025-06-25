package com.vtc.mainapp;

import java.util.ArrayList;
import java.util.List;

import com.vtc.modelo.Integrante;
import com.vtc.modelo.Jefe;

public class Main {
    public static void main(String[] args) {

        List<Integrante> integrantes = new ArrayList<>();

        // Crear jefe inicial (esto en el futuro vendrá de base de datos)
        Jefe jefe = new Jefe(1, "00000000X", "Admin", "600000000", "jefe@empresa.com", "jefe", "admin", null, null, null);
        integrantes.add(jefe);
    }
}
