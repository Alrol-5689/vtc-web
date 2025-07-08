package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class ConvenioAnejo {

    private Long id_anexoConvenio;
    private Convenio convenio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String notas;
    private String nombre;
    private Double salarioAnual;
    private Duration tareasAux;
    private Duration jornadaCompleta;
    private List<PlusConvenio> pluses; 

    public ConvenioAnejo() {
    }

    public Long getId_anexoConvenio() {return id_anexoConvenio;}
    public Convenio getConvenio() {return convenio;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public String getNotas() {return notas;}
    public String getNombre() {return nombre;}
    public Double getSalarioAnual() {return salarioAnual;}
    public Duration getJornadaCompleta() {return jornadaCompleta;}
    public Duration getTareasAux() {return tareasAux;}

    public void setId_anexoConvenio(Long id_anexoConvenio) {this.id_anexoConvenio = id_anexoConvenio;}
    public void setConvenio(Convenio convenio) {this.convenio = convenio;}
    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public void setNotas(String notas) {this.notas = notas;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setSalarioAnual(Double salarioAnual) {this.salarioAnual = salarioAnual;}
    public void setTareasAux(Duration tareasAux) {this.tareasAux = tareasAux;}
    public void setJornadaCompleta(Duration jornadaCompleta) {this.jornadaCompleta = jornadaCompleta;}


    public Duration getTareasAuxEn(LocalDate fecha) {
        if (fecha == null) return null;
        if (fechaFin != null && fecha.isAfter(fechaFin)) return null;
        if (fecha.isBefore(fechaInicio)) return null;
        return tareasAux;
    }
}
