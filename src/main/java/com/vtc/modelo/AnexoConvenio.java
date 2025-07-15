package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import com.vtc.util.DurationToMinutesConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "anexo_convenio")
public class AnexoConvenio {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAnexo;
    
    @ManyToOne(optional = false) 
    @JoinColumn(name = "id_convenio", nullable = false) 
    private Convenio convenio;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @Column(name = "notas")
    private String notas;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "salario_anual", nullable = false)
    private Double salarioAnual;

    @Convert(converter = DurationToMinutesConverter.class)
    @Column(name = "tareasAux")
    private Duration tareasAux;

    @Convert(converter = DurationToMinutesConverter.class)
    @Column(name = "jornadaCompleta")
    private Duration jornadaCompleta;

    @OneToMany(mappedBy = "anexoConvenio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlusConvenio> pluses; 

    public AnexoConvenio() {
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
    public List<PlusConvenio> getPluses() {return pluses;}

    public void setIdAnexo(Long id_anexoConvenio) {this.idAnexo = id_anexoConvenio;}
    public void setConvenio(Convenio convenio) {this.convenio = convenio;}
    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public void setNotas(String notas) {this.notas = notas;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setSalarioAnual(Double salarioAnual) {this.salarioAnual = salarioAnual;}
    public void setTareasAux(Duration tareasAux) {this.tareasAux = tareasAux;}
    public void setJornadaCompleta(Duration jornadaCompleta) {this.jornadaCompleta = jornadaCompleta;}
    public void setPluses(List<PlusConvenio> pluses) {this.pluses = pluses;}


    public Duration getTareasAuxEn(LocalDate fecha) {
        if (fecha == null) return null;
        if (fechaFin != null && fecha.isAfter(fechaFin)) return null;
        if (fecha.isBefore(fechaInicio)) return null;
        return tareasAux;
    }


}
