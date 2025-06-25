package com.vtc.modelo;

public class PoliticaComision {
    private double umbral;
    private double porcentaje;

    public PoliticaComision(double umbral, double porcentaje) {
        this.umbral = umbral;
        this.porcentaje = porcentaje;
    }

    public double getUmbral() {
        return umbral;
    }

    public void setUmbral(double umbral) {
        this.umbral = umbral;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Umbral: " + umbral + " €, Comisión: " + porcentaje + "%";
    }
}
