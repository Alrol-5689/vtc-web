package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Convenio {

    private Duration tareasAux; 
    private Duration jornadaCompleta;
    private LocalDate fechaInicio;
    private Double salarioAnual;
    private int nPagas;
    private List<Plus> pluses;
    private LocalDate fechaFin;
    private String notas;

    public Convenio() { } // Constructor vacío para serialización o uso genérico

    public Convenio(LocalDate fechaInicio, Duration tareasAux, 
            Duration jornadaCompleta, double salarioAnual) {
        this.tareasAux = tareasAux;
        this.jornadaCompleta = jornadaCompleta;
        this.pluses = new ArrayList<>();
        this.salarioAnual = salarioAnual;
        this.fechaInicio = fechaInicio;

        // Buscar convenio anterior por fechas
        Convenio anterior = Administrador.getCONVENIOS().stream()
            .filter(c -> c.getFechaFin() != null && c.getFechaFin().isBefore(fechaInicio))
            .max((c1, c2) -> c1.getFechaFin().compareTo(c2.getFechaFin()))
            .orElse(null);

        if (anterior != null) {
            if (anterior.getFechaFin() == null) {
                throw new IllegalArgumentException("El convenio anterior debe tener fecha fin antes de crear uno nuevo.");
            }
            if (!fechaInicio.equals(anterior.getFechaFin().plusDays(1))) {
                throw new IllegalArgumentException("El nuevo convenio debe empezar el día siguiente de que termine el anterior.");
            }
        } else if (!Administrador.getCONVENIOS().isEmpty()) {
            // Si hay convenios pero ninguno termina antes de este inicio, hay solapamiento o error de fechas
            throw new IllegalArgumentException("No se ha encontrado un convenio anterior válido para enlazar fechas.");
        }

        Administrador.getCONVENIOS().add(this);
    }
    
    //===>> PLUSES  <<===//
    
    public static double getPlusCalidad(YearMonth mes) {
        return pluses.stream()
        .filter(p -> p instanceof PlusCalidad)
        .filter(p -> p.estaActivoEn(mes))
        .map(p -> (PlusCalidad) p)
        .filter(pc -> pc.getSeCobraEnMes()[mes.getMonthValue() - 1])
        .mapToDouble(pc -> pc.getCantidadAnual())
        .sum();
    }
    
    public static double getPlusVestuario(YearMonth mes) {
        return pluses.stream()
        .filter(p -> p instanceof PlusVestuario)
        .filter(p -> p.estaActivoEn(mes))
        .map(p -> (PlusVestuario) p)
        .mapToDouble(pc -> pc.getCantidadAnual())
        .sum();
    }
    
    
    public int getnPagas() {
        return nPagas;
    }

    //===>> Tareas Auxiliares <<===//
    
    public Duration getTareasAux() { return tareasAux; }
    public void setTareasAux(Duration tareasAux) { this.tareasAux = tareasAux; }

    //===>> Fechas <<===//
    
    public LocalDate getFechaInicio() { return fechaInicio;}
    public LocalDate getFechaFin() { return fechaFin;}

    public void setFechaFin(LocalDate fechaFin) {
        // Comprobar que no hay otro convenio que empiece antes o el mismo día que la nueva fechaFin
        boolean solapa = Administrador.getCONVENIOS().stream()
            .filter(c -> c != this)
            .anyMatch(c -> c.getFechaInicio() != null && !c.getFechaInicio().isAfter(fechaFin));
        if (solapa) {
            throw new IllegalArgumentException("No se puede establecer una fecha fin que solape con el inicio de otro convenio.");
        }
        this.fechaFin = fechaFin;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        // Buscar convenio anterior por fechas
        Convenio anterior = Administrador.getCONVENIOS().stream()
            .filter(c -> c != this)
            .filter(c -> c.getFechaFin() != null && c.getFechaFin().isBefore(fechaInicio))
            .max((c1, c2) -> c1.getFechaFin().compareTo(c2.getFechaFin()))
            .orElse(null);

        if (anterior != null) {
            if (anterior.getFechaFin() == null) {
                throw new IllegalArgumentException("El convenio anterior debe tener fecha fin antes de crear uno nuevo.");
            }
            if (!fechaInicio.equals(anterior.getFechaFin().plusDays(1))) {
                throw new IllegalArgumentException("El nuevo convenio debe empezar el día siguiente de que termine el anterior.");
            }
        } else if (!Administrador.getCONVENIOS().isEmpty()) {
            throw new IllegalArgumentException("No se ha encontrado un convenio anterior válido para enlazar fechas.");
        }

        this.fechaInicio = fechaInicio;
    }

    public Double getSalarioAnual() {
        return salarioAnual;
    }

    public void setSalarioAnual(Double salarioAnual) {
        this.salarioAnual = salarioAnual;
    }

}
