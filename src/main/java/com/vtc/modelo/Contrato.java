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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "contrato")
public class Contrato {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idContrato;
    
    @ManyToOne(optional = false) 
    @JoinColumn(name = "conductor_id", nullable = false)
    private Driver conductor; 

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_alta_en_empresa")
    private LocalDate fechaAltaEnEmpresa; // no cambia con el contrato si es con la misma empresa

    @Column(name = "empresa", nullable = false)
    private String empresa;

    @Column(name = "notas")
    private String notas; 

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnejoContrato> anejos;
    
    //===>> CONSTRUCTORES <<===//

    public Contrato() {

    }

    //===>> GETTERS <<===//

    public boolean isContratoReady() { //===>> Verifica si el contrato está listo para ser usado
        return 
            conductor != null && 
            fechaInicio != null;
    }
        
    public Long getId() {return idContrato;}
    public Driver getConductor() {return conductor;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public LocalDate getFechaAltaEnEmpresa() {return fechaAltaEnEmpresa;}
    public String getEmpresa() {return empresa;}
    public String getNotas() {return notas;}
    public List<AnejoContrato> getAnejos() {return anejos;}

    public AnejoContrato getAnejoVigente(LocalDate fecha) {
        if (anejos == null || anejos.isEmpty()) return null;
        return anejos.stream()
            .filter(a -> !a.getFechaInicio().isAfter(fecha))
            .filter(a -> a.getFechaFin() == null || !a.getFechaFin().isBefore(fecha))
            .max(Comparator.comparing(AnejoContrato::getFechaInicio))
            .orElse(null);
    }
    
    //===>> SETTERS <<===//

    protected void setId(Long id_contrato) {this.idContrato = id_contrato;}
    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}
    public void setNotas(String notas) {this.notas = notas;}

    public void setConductor(Driver conductor) {
        if(this.conductor != null) 
            throw new UnsupportedOperationException("El conductor no se puede cambiar una vez asignado.");
        this.conductor = conductor;
    }

    public void setFechaAltaEnEmpresa(LocalDate fechaAltaEnEmpresa) {this.fechaAltaEnEmpresa = fechaAltaEnEmpresa;}

}