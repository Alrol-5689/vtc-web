package com.vtc.modelo;

import java.time.YearMonth;

public abstract class Plus {

    protected String nombre;
    protected double cantidad;
    protected YearMonth inicio, fin;

    // constructor
    public Plus(String nombre, double cantidad, YearMonth inicio, YearMonth fin) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public boolean estaActivoEn(YearMonth mes) {
        boolean empiezaAntes = !mes.isBefore(inicio);
        boolean terminaDespues = (fin == null || !mes.isAfter(fin));
        return empiezaAntes && terminaDespues;
    }

    // Getters y Setters...

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public YearMonth getInicio() {
        return inicio;
    }

    public void setInicio(YearMonth inicio) {
        this.inicio = inicio;
    }

    public YearMonth getFin() {
        return fin;
    }

    public void setFin(YearMonth fin) {
        this.fin = fin;
    }

    
}
