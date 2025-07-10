package com.vtc.modelo;

import java.time.YearMonth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "politica_comision")
public class PoliticaComision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_politica_comision;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "anejo_id", nullable = false) 
    private ContratoAnejo contratoAnejo;
    
    @Column(name = "mes", nullable = false)
    private YearMonth mes;
    
    @Column(name = "umbral", nullable = false)
    private double umbral;
    
    @Column(name = "porcentaje", nullable = false)
    private double porcentaje;
    
    @Column(name = "reconocida", nullable = false)
    private boolean reconocida = false; // false -> Lo guardamos en una lista en Conductor, true -> en una lista en Contrato
    
    public PoliticaComision() {}
    
    @Override
    public String toString() {
        return "Umbral: " + umbral + " €, Comisión: " + porcentaje + "%";
    }
    
    public Long getId_politica_comision() {return id_politica_comision;}
    public ContratoAnejo getContratoAnejo() {return contratoAnejo;}
    public void setContratoAnejo(ContratoAnejo contratoAnejo) {this.contratoAnejo = contratoAnejo;}
    public double getUmbral() { return umbral;}
    public void setUmbral(double umbral) { this.umbral = umbral; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public YearMonth getMes() { return mes; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}


}
