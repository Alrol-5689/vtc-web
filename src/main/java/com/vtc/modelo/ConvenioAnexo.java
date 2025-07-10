package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "convenio_anexo")
public class ConvenioAnexo {

    //===>> ATRIBUTOS <<===//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo_convenio")
    private Long idAnexo;
    
    private Convenio convenio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String notas;
    private String nombre;
    private Double salarioAnual;
    private Duration tareasAux;
    private Duration jornadaCompleta;
    private List<PlusConvenio> pluses; 

    public ConvenioAnexo() {
    }

    public Long getIdAnexo() {return idAnexo;}
    public Convenio getConvenio() {return convenio;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public String getNotas() {return notas;}
    public String getNombre() {return nombre;}
    public Double getSalarioAnual() {return salarioAnual;}
    public Duration getJornadaCompleta() {return jornadaCompleta;}
    public Duration getTareasAux() {return tareasAux;}

    public void setIdAnexo(Long id_anexoConvenio) {this.idAnexo = id_anexoConvenio;}
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
