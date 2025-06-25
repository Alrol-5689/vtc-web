package com.vtc.modelo;

import java.time.LocalDate;

public abstract class Integrante {
	
	// Atributos
	
	protected String dni, nombre, apellido1, apellido2, telefono, email, nick, password;
	protected int id;
	protected LocalDate fechaAlta;
	
	// Constructor
	
    public Integrante(int id, String dni, String nombre, String apellido1, String apellido2, 
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
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNick() { return nick; }
    public void setNick(String nick) { this.nick = nick; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    // Método abstracto que podrá implementar cada subclase
    public abstract String getTipo();  // Jefe, Gestor, Conductor
}