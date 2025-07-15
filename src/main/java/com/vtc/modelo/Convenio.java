package com.vtc.modelo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "convenio")
public class Convenio {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_convenio;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "notas")
    private String notas;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "convenio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnexoConvenio> anexos; 
    
    //===>> CONSTRUCTORES <<===//
    
    public Convenio() {
     }
    

    //===>> Fechas <<===//
    
    public LocalDate getFechaInicio() { return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public Long getId_convenio() {return id_convenio;}
    public void setId_convenio(Long id_convenio) {this.id_convenio = id_convenio;}
    public String getNotas() {return notas;}
    public void setNotas(String notas) {this.notas = notas;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public AnexoConvenio getAnexoVigente(LocalDate fecha) {
        if (anexos == null || anexos.isEmpty()) return null;
        return anexos.stream()
            .filter(a -> !a.getFechaInicio().isAfter(fecha))
            .max(Comparator.comparing(AnexoConvenio::getFechaInicio))
            .orElse(null);
    }

    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}



}
