package com.vtc.modelo;

import com.vtc.util.Utilities;

public class Plus {

    
    public enum TipoPlus {
        CALIDAD, PERMANENCIA, VESTUARIO, OTRO
    }
    
    // Atributos
    
    private String nombre, notas;
    private boolean[] seCobraEnMes; // settear esto antes que cantidadAnual
    private double cantidadAnual;
    private int mesesNecesarios; // Solo para pluses de tipo PERMANENCIA
    private TipoPlus tipo;
    
    // constructor
    
    public Plus() {
        
    }
    
    // Getters y Setters...Nombre, Notas, Cantidad Anual, Tipo
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas;}
    public TipoPlus getTipo() { return tipo; }
    public void setTipo(TipoPlus tipo) { this.tipo = tipo; }
    
    //===>> seCobraEnMes <<===// ES LO PRIMERO QUE SE DEBE SETEAR, ANTES QUE CANTIDAD ANUAL

    public boolean[] getSeCobraEnMes() { return seCobraEnMes; }

    public void setSeCobraEnMes(boolean[] seCobraEnMes) {
        if (seCobraEnMes != null && seCobraEnMes.length != 12) {
            throw new IllegalArgumentException("El array seCobraEnMes debe tener 12 elementos.");
        } else if (seCobraEnMes == null) {
            seCobraEnMes = new boolean[12];
            for (int i = 0; i < 12; i++) {
                seCobraEnMes[i] = true; // Por defecto, se cobra en todos los meses
            }
        }
        this.seCobraEnMes = seCobraEnMes;
    }

    public void setSeCobraEnMes_mensual() { // igual que setSeCobraEnMes() pasandole un null
        this.seCobraEnMes = new boolean[12];
        for (int i = 0; i < 12; i++) {
            seCobraEnMes[i] = true; // Por defecto, se cobra en todos los meses
        }
    }

    public void setSeCobraEnMes_trimestral() {
        this.seCobraEnMes = new boolean[12];
        for (int i = 0; i < 12; i++) {
            seCobraEnMes[i] = Utilities.esMesTrimestral(i + 1); // Se cobra en meses trimestrales
        }
    }

    //===>> cantidadAnual <<===//

    public double getCantidadAnual() { return cantidadAnual;}
    public void setCantidadAnual(double cantidad) { this.cantidadAnual = cantidad; }
    public void setCantidadMensual(double cantidadMensual) { 
        int count = 0;
        for(boolean mes : seCobraEnMes) {
            if (mes) count++;
        }
        this.cantidadAnual = cantidadMensual * count;
    }
    
    //===>> mesesNecesarios <<===//

    public int getMesesNecesarios() { return mesesNecesarios; }

    public void setMesesNecesarios(int mesesNecesarios) { 
        if(this.tipo != TipoPlus.PERMANENCIA) {
            throw new IllegalArgumentException("Solo los pluses de tipo PERMANENCIA tienen meses necesarios.");
        }
        if(mesesNecesarios < 0) {
            throw new IllegalArgumentException("Los meses necesarios no pueden ser negativos.");
        }
        this.mesesNecesarios = mesesNecesarios; 
    }

    //===>> Métodos de utilidad <<===//

    public double getCantidadMensual() {
        if (seCobraEnMes == null) return 0.0;
        int meses = 0;
        for (boolean cobra : seCobraEnMes) {
            if (cobra) meses++;
        }
        if (meses == 0) return 0.0;
        return cantidadAnual / meses;
    }
    
}
