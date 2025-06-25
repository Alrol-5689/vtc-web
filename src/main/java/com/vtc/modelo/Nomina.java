package com.vtc.modelo;

import java.time.YearMonth;

public class Nomina {

    private Conductor conductor;
    private YearMonth mes;

    private double salarioBase;
    private double pppe;
    private double comision;
    private double gratificaciones;

    private double plusVestuario;
    private double plusCalidad;
    private double plusPermanencia; // único campo
    private double otrosPluses;

    private double totalBruto;

    public Nomina(Conductor conductor, YearMonth mes,
                  double salarioBase, double pppe,
                  double comision, double gratificaciones,
                  double plusVestuario, double plusCalidad,
                  double plusPermanencia, double otrosPluses) {

        this.conductor = conductor;
        this.mes = mes;

        this.salarioBase = salarioBase;
        this.pppe = pppe;
        this.comision = comision;
        this.gratificaciones = gratificaciones;

        this.plusVestuario = plusVestuario;
        this.plusCalidad = plusCalidad;
        this.plusPermanencia = plusPermanencia;
        this.otrosPluses = otrosPluses;

        this.totalBruto = salarioBase + pppe + comision + gratificaciones +
                          plusVestuario + plusCalidad + plusPermanencia + otrosPluses;
    }

    // Getters
    public double getPlusPermanencia() { return plusPermanencia; }
    public double getPlusCalidad() { return plusCalidad; }
    // ... resto igual

    @Override
    public String toString() {
        return "Nómina de " + conductor.getNombre() + " - " + mes + "\n" +
               "Salario base: " + salarioBase + "\n" +
               "PPPE: " + pppe + "\n" +
               "Comisión: " + comision + "\n" +
               "Gratificaciones: " + gratificaciones + "\n" +
               "Plus Vestuario: " + plusVestuario + "\n" +
               "Plus Calidad: " + plusCalidad + "\n" +
               "Plus Permanencia: " + plusPermanencia + "\n" +
               "Otros pluses: " + otrosPluses + "\n" +
               "TOTAL BRUTO: " + totalBruto;
    }
}
