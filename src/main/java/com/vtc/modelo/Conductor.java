package com.vtc.modelo;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Conductor {
    
    // Puedes agregar atributos propios del Conductor aquí en el futuro, como Turno, gestor asignado, etc.
	
	private List<DiaConductor> diasTrabajados;
    private List<CondicionesConductor> historialCondiciones;
    private String dni, nombre, apellido1, apellido2, telefono, email, nick, password;
	private int id;
	private LocalDate fechaAlta;
    private TreeMap<YearMonth, Map<DayOfWeek, Duration>> jornada;
    private TreeMap<YearMonth, Duration> tareasAux;
    private Duration tareasAux_porDef, jornadaDiaria_porDef, jornadaSemanal_porDef;

    public Conductor(int id, String dni, String nombre, String apellido1, String apellido2, 
    		String telefono, String email, String nick, String password, LocalDate fechaAlta) {   	
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.email = email;
        this.nick = nick; 
        this.password = password;
        this.fechaAlta = fechaAlta;    
        this.diasTrabajados = new ArrayList<>();           
    }
    
    // Getters y setters
    public List<DiaConductor> getDiasTrabajados() {return diasTrabajados;}

    public void setDiasTrabajados(List<DiaConductor> diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public Duration getTareasAux_porDef() {
        return tareasAux_porDef;
    }

    public void setTareasAux_porDef(Duration tareasAux_porDef) {
        this.tareasAux_porDef = tareasAux_porDef;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Duration getJornadaDiaria_porDef() {
        return jornadaDiaria_porDef;
    }

    public void setJornadaDiaria_porDef(Duration jornada_porDef) {
        this.jornadaDiaria_porDef = jornada_porDef;
    }

    public Duration getJornadaSemanal_porDef() {
        return jornadaSemanal_porDef;
    }

    public void setJornadaSemanal_porDef(Duration jornadaSemanal_porDef) {
        this.jornadaSemanal_porDef = jornadaSemanal_porDef;
    }
}


