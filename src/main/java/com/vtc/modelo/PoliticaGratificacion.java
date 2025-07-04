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

    public void insertarPoliticaGratificacion() {
        if (conductor == null || desde == null || hasta == null) {
            throw new IllegalStateException("Conductor y fechas no pueden ser nulos.");
        }
        if (reconocida) {
            // Buscar contratos del conductor que estén activos durante el rango [desde, hasta]
            var contratos = conductor.getContratos().stream()
                .filter(c -> {
                    LocalDate inicio = c.getFechaInicio();
                    LocalDate fin = c.getFechaFin();
                    boolean empiezaAntesDeFin = !inicio.isAfter(hasta);
                    boolean terminaDespuesDeInicio = (fin == null) || !fin.isBefore(desde);
                    return empiezaAntesDeFin && terminaDespuesDeInicio;
                })
                .toList();

            if (contratos.size() == 1) {
                contratos.get(0).getPoliticaGratificacions().add(this);
            } else if (contratos.size() > 1) {
                System.out.println("Hay varios contratos en ese periodo. Elige uno:");
                for (int i = 0; i < contratos.size(); i++) {
                    System.out.println((i + 1) + ": " + contratos.get(i));
                }
                throw new IllegalStateException("Selección de contrato no implementada.");
            } else {
                throw new IllegalStateException("No se encontró contrato para ese periodo.");
            }
        } else {
            conductor.getPoliticaGratificacion_NO_reconocida().add(this);
        }
    }
}
