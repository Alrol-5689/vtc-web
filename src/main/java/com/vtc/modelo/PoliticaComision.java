package com.vtc.modelo;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class PoliticaComision {
    
    private Conductor conductor;
    private YearMonth mes;
    private double umbral;
    private double porcentaje;
    private boolean reconocida = false; // false -> Lo guardamos en una lista en Conductor, true -> en una lista en Contrato

    public PoliticaComision() {}

    @Override
    public String toString() {
        return "Umbral: " + umbral + " €, Comisión: " + porcentaje + "%";
    }

    public double getUmbral() { return umbral;}
    public void setUmbral(double umbral) { this.umbral = umbral; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public Conductor getConductor() { return conductor; }
    public YearMonth getMes() { return mes; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}

    public void insertarPoliticaComision() {

        if (conductor == null || mes == null) {
            throw new IllegalStateException("Conductor y mes no pueden ser nulos.");
        }
        if (reconocida) {
            // Buscar contratos del conductor que estén activos en ese mes
            List<Contrato> contratosEnMes = conductor.getContratos().stream()
                .filter(c -> {
                    LocalDate inicio = c.getFechaInicio();
                    LocalDate fin = c.getFechaFin();
                    LocalDate primerDiaMes = mes.atDay(1); // del plus
                    LocalDate ultimoDiaMes = mes.atEndOfMonth(); // del plus
                    // comprobar si el contrato empieza antes de que temrine esta comisión
                    boolean empiezaAntesDeFinMes = !inicio.isAfter(ultimoDiaMes); 
                    // comprobar si el contrato no termina antes de que empiece esta comisión
                    boolean terminaDespuesDeInicioMes = (fin == null) || !fin.isBefore(primerDiaMes);
                    return empiezaAntesDeFinMes && terminaDespuesDeInicioMes; // devuelve contratos activos en el mes
                })
                .toList();

            if (contratosEnMes.size() == 1) {
                contratosEnMes.get(0).getPoliticaComision().add(this);
            } else if (contratosEnMes.size() > 1) {
                // Si hay más de uno, puedes mostrar por consola o lanzar excepción, aquí solo los listamos
                System.out.println("Hay varios contratos en ese mes. Elige uno:");
                for (int i = 0; i < contratosEnMes.size(); i++) {
                    System.out.println((i + 1) + ": " + contratosEnMes.get(i));
                }
                // Aquí deberías pedir al usuario que elija, por ejemplo con un Scanner (no implementado)
                // contratosEnMes.get(indiceElegido).getPoliticaComision().add(politica);
            } else {
                throw new IllegalStateException("No se encontró contrato para ese mes.");
            }
        } else {
            conductor.getPoliticaComision().add(this);
        }
    }

}
