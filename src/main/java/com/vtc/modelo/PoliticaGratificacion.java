package com.vtc.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "politica_gratificacion")
public class PoliticaGratificacion {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_politica_gratificacion")
    private Long idPoliticaGratificacion;
    private Conductor conductor;
    private LocalDate desde, hasta;
    private double objetivo;
    private double recompensa;
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
    public Conductor getConductor() { return conductor; }
    public void setConductor(Conductor conductor) {this.conductor = conductor;}
    public LocalDate getHasta() { return hasta;}
    public LocalDate getDesde() { return desde; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}

    public Long getIdPoliticaGratificacion() {
        return idPoliticaGratificacion;
    }

    public void setIdPoliticaGratificacion(Long idPoliticaGratificacion) {
        this.idPoliticaGratificacion = idPoliticaGratificacion;
    }
}
