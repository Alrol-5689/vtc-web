package com.vtc.mainapp;

import com.vtc.modelo.Driver;

import com.vtc.modelo.Controladora;

public class TestConexionBBDD {
    public static void main(String[] args) {

        Controladora control = new Controladora();
        Driver conductor = null;

        try {
            conductor = (Driver) control.buscarDriverPorNickYPass("roler", "pas1234");
        } catch (Exception e) {
            System.out.println("Error al crear el conductor: " + e.getMessage());
        } 
        System.out.println("Conductor encontrado: " + conductor.getNick() + ", " + conductor.getPassword());
    }
}