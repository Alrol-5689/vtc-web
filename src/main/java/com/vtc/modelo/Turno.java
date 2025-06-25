package com.vtc.modelo;

import java.time.Duration;

public class Turno {
	
	// Atributos 
	
    private int id; // útil para BD
    private String nombre;
    private double horasSemanales;
    private boolean[] trabajaDias; // lunes a domingo
    private boolean activo;
    
    // Constructor

    public Turno(int id, String nombre, double horasSemanales, boolean[] trabajaDias) {
        this.id = id;
        this.nombre = nombre;
        this.horasSemanales = horasSemanales;
        this.trabajaDias = trabajaDias;
        this.activo = true;
    }
    
    // Métodos

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getHorasSemanales() { return horasSemanales; }
    public boolean[] getTrabajaDias() { return trabajaDias; }
    public boolean isActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setHorasSemanales(int horasSemanales) { this.horasSemanales = horasSemanales; }
    public void setTrabajaDias(boolean[] trabajaDias) { this.trabajaDias = trabajaDias; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public boolean trabajaElDia(int dia) {
        return trabajaDias[dia];
    }
    
    public Duration getHorasSemanalesComoDuration() {
        return Duration.ofMinutes((long)(horasSemanales * 60));
    }

    @Override
    public String toString() {
        return nombre + " (" + horasSemanales + "h/semana)";
    }
}
