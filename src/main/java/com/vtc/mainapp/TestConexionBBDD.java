package com.vtc.mainapp;

import com.vtc.modelo.Controladora;

public class TestConexionBBDD {
    public static void main(String[] args) {

        Controladora control = new Controladora();

        try {
            control.eliminarDriver(4L);
        } catch (Exception e) {
            System.out.println("Error al crear el conductor: " + e.getMessage());
        } 
        
    }
}