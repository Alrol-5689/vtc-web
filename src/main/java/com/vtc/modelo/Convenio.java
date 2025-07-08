package com.vtc.modelo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Convenio {

    private Long id_convenio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String notas;
    private String nombre;
    private List<ConvenioAnejo> anexos; 
    

    public Convenio() {
     }
    

    //===>> Fechas <<===//
    
    public LocalDate getFechaInicio() { return fechaInicio;}
    public Long getId_convenio() {return id_convenio;}
    public void setId_convenio(Long id_convenio) {this.id_convenio = id_convenio;}
    public String getNotas() {return notas;}
    public void setNotas(String notas) {this.notas = notas;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public LocalDate getFechaFin() {
        return Administrador.getCONVENIOS().stream()
            .filter(c -> c.getFechaInicio().isAfter(this.getFechaInicio()))
            .min((c1, c2) -> c1.getFechaInicio().compareTo(c2.getFechaInicio()))
            .map(c -> c.getFechaInicio().minusDays(1))
            .orElse(null);
    }

    public ConvenioAnejo getAnexoVigente(LocalDate fecha) {
        if (anexos == null || anexos.isEmpty()) return null;
        return anexos.stream()
            .filter(a -> !a.getFechaInicio().isAfter(fecha))
            .max(Comparator.comparing(ConvenioAnejo::getFechaInicio))
            .orElse(null);
    }

}
