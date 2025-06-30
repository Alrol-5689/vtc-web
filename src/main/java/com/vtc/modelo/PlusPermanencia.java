package com.vtc.modelo;

import java.time.YearMonth;

public class PlusPermanencia extends Plus {


    private int mesesNecesarios;
    private static final String TIPO_DE_PLUS = "PERMANENCIA";

    public PlusPermanencia(String nombre, double cantidad, YearMonth inicio, YearMonth fin, int mesesNecesatios) {
        super(nombre, cantidad, inicio, fin);
        this.mesesNecesarios = mesesNecesatios;
    }

    // @Override
    // public String toString() {
    //     return "[ " + TIPO_DE_PLUS + " ] " + nombre + ". Meses en la empresa: " 
    //     + mesesNecesatios + " meses. Cuantía del plus: " + cantidad + "€.";
    // }
    @Override
    public String toString() {
        return "[ " + TIPO_DE_PLUS + " ] " + nombre +
               " | Requiere: " + mesesNecesarios + " meses en la empresa" +
               " | Cuantía: " + cantidad + "€" +
               " | Activo: " + inicio + " - " + (fin == null ? "indefinido" : fin);
    }

    public int getMesesNecesarios() {
        return mesesNecesarios;
    }

    public void setMesesNecesatios(int mesesNecesarios) {
        this.mesesNecesarios = mesesNecesarios;
    }
}
