package com.vtc.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "politica_gratificacion")
public class PoliticaGratificacion {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPoliticaGratificacion;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "anejo_id", nullable = false) 
    private AnejoContrato anejoContrato;

    @Column(name = "desde", nullable = false)
    private LocalDate desde;

    @Column(name = "hasta", nullable = false)
    private LocalDate hasta;

    @Column(name = "objetivo", nullable = false)
    private double objetivo;

    @Column(name = "recompensa", nullable = false)
    private double recompensa;

    @Column(name = "reconocida", nullable = false)
    private boolean reconocida = false;

    //===>> CONSTRUCTORES <<===//

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
    public LocalDate getHasta() { return hasta;}
    public LocalDate getDesde() { return desde; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}
    public Long getId() {return idPoliticaGratificacion;}
    public void setId(Long idPoliticaGratificacion) {this.idPoliticaGratificacion = idPoliticaGratificacion;}
    public AnejoContrato getAnejoContrato() {return anejoContrato;}
    public void setAnejoContrato(AnejoContrato anejoContrato) {this.anejoContrato = anejoContrato;}

}
