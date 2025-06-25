package com.vtc.modelo;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class Gestor extends Integrante {

    private Map<YearMonth, List<Conductor>> conductoresAsignados;
    private Map<YearMonth, List<Conductor>> plusCalidadAprobado;

    public Gestor(int id, String dni, String nombre, String apellido1, String apellido2, 
    		String telefono, String email, String nick, String password, LocalDate fechaAlta) {
        super(id, dni, nombre, apellido1, apellido2, telefono, email, nick, password, fechaAlta);
        this.conductoresAsignados = new TreeMap<>();
        this.plusCalidadAprobado = new TreeMap<>();
    }
 // Aprobaciones manuales del plus de calidad
    

    // Aprueba el plus para un conductor en un mes
    public void aprobarPlusCalidad(Conductor conductor, YearMonth mes) {
        plusCalidadAprobado.putIfAbsent(mes, new ArrayList<>());
        if (!plusCalidadAprobado.get(mes).contains(conductor)) {
            plusCalidadAprobado.get(mes).add(conductor);
        }
    }

    // Verifica si fue aprobado
    public boolean tienePlusCalidadAprobado(Conductor conductor, YearMonth mes) {
        return plusCalidadAprobado
        		.getOrDefault(mes, Collections.emptyList())
        		.contains(conductor);
    }

    @Override
    public String getTipo() {
        return "Gestor";
    }

    // Método para asignar un conductor a un mes concreto
    public void asignarConductor(Conductor conductor, YearMonth mes) {
        conductoresAsignados.putIfAbsent(mes, new ArrayList<>());
        conductoresAsignados.get(mes).add(conductor);
    }

    // Getters
    public Map<YearMonth, List<Conductor>> getConductoresAsignados() {
        return conductoresAsignados;
    }
 
    
    // Puedes añadir más lógica según lo necesites, como eliminar conductor, listar, etc.
}
