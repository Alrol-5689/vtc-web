package com.vtc.modelo;

import java.time.LocalDate;

public class PoliticaGratificacion {
    
    private Conductor conductor;
    private LocalDate desde, hasta;
    private double objetivo;
    private double recompensa;
    private boolean reconocida = false;

    public PoliticaGratificacion() {
    }

    @Override
    public String toString() {
        return "Umbral: " + objetivo + " €, Comisión: " + recompensa + "%";
    }

    public double getObjetivo() { return objetivo;}
    public void setObjetivo(double umbral) { this.objetivo = umbral; }
    public double getRecompensa() { return recompensa; }
    public void setRecompensa(double porcentaje) { this.recompensa = porcentaje; }
    public Conductor getConductor() { return conductor; }
    public void setConductor(Conductor conductor) {this.conductor = conductor;}
    public LocalDate getHasta() { return hasta;}
    public LocalDate getDesde() { return desde; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}
}
