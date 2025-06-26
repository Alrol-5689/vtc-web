package com.vtc.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conductor {
    
    // Puedes agregar atributos propios del Conductor aquí en el futuro, como Turno, gestor asignado, etc.
	
	private List<DiaConductor> diasTrabajados;
    protected String dni, nombre, apellido1, apellido2, telefono, email, nick, password;
	protected int id;
	protected LocalDate fechaAlta;

    public Conductor(int id, String dni, String nombre, String apellido1, String apellido2, 
    		String telefono, String email, String nick, String password, 
    			Gestor gestorAsignado, Turno turno, LocalDate fechaAlta) {   	
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
}


