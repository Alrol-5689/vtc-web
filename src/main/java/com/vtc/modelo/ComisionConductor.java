package com.vtc.modelo;

import java.time.YearMonth;

public class ComisionConductor {

    private Conductor conductor;
    private YearMonth mes;
    private double umbral;
    private double porcentaje;
    private double totalFacturado;
    private double comision;

    public ComisionConductor(Conductor conductor, YearMonth mes) {
        this.conductor = conductor;
        this.mes = mes;

        Turno turno = conductor.getListaTurnos().get(mes);
        PoliticaComision politica = Jefe.getPoliticasComision(mes, turno);

        if (politica == null) {
            throw new IllegalArgumentException("No hay política de comisión definida para el mes " + mes + " y el turno " + turno);
        }

        this.umbral = politica.getUmbral();
        this.porcentaje = politica.getPorcentaje();

        calcularTotalFacturado();
        calcularComision();
    }

    private void calcularTotalFacturado() {
        this.totalFacturado = conductor.getDiasTrabajados().stream() // recorre DiaConductor del conductor 
                .filter(dia -> YearMonth.from(dia.getDia()).equals(mes)) // donde DiaConductor.dia está en el mes de esta ComisionConductor
                .mapToDouble(DiaConductor::getFacturacion) // cogemos lo que ha facturado cada día
                .sum(); // lo sumamos
    }

    private void calcularComision() {
        if (totalFacturado > umbral) {
            this.comision = (totalFacturado - umbral) * (porcentaje / 100.0);
        } else {
            this.comision = 0;
        }
    }

    // Getters
    public Conductor getConductor() { return conductor; }
    public YearMonth getMes() { return mes; }
    public double getTotalFacturado() { return totalFacturado; }
    public double getUmbral() { return umbral; }
    public double getPorcentaje() { return porcentaje; }
    public double getComision() { return comision; }

    @Override
    public String toString() {
        return "Comisión para " + conductor.getNombre() + " en " + mes + ": " + comision + " €";
    }
}
