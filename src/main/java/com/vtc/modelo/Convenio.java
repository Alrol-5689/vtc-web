package com.vtc.modelo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "convenio")
public class Convenio {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_convenio;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String notas;
    private String nombre;
    private List<ConvenioAnexo> anexos; 
    
    
    public Convenio() {
     }
    

    //===>> Fechas <<===//
    
    public LocalDate getFechaInicio() { return fechaInicio;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public Long getId_convenio() {return id_convenio;}
    public void setId_convenio(Long id_convenio) {this.id_convenio = id_convenio;}
    public String getNotas() {return notas;}
    public void setNotas(String notas) {this.notas = notas;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public ConvenioAnexo getAnexoVigente(LocalDate fecha) {
        if (anexos == null || anexos.isEmpty()) return null;
        return anexos.stream()
            .filter(a -> !a.getFechaInicio().isAfter(fecha))
            .max(Comparator.comparing(ConvenioAnexo::getFechaInicio))
            .orElse(null);
    }


}
