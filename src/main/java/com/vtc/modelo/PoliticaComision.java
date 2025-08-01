package com.vtc.modelo;

import java.time.YearMonth;

import com.vtc.util.YearMonthToStringConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    @Column(name = "id", nullable = false)
    private Long idPoliticaComision;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "anejo_id", nullable = false) 
    private AnejoContrato anejoContrato;
    
    @Convert(converter = YearMonthToStringConverter.class)
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
    
    public Long getId() {return idPoliticaComision;}
    public AnejoContrato getAnejoContrato() {return anejoContrato;}
    public void setAnejoContrato(AnejoContrato contratoAnejo) {this.anejoContrato = contratoAnejo;}
    public double getUmbral() { return umbral;}
    public void setUmbral(double umbral) { this.umbral = umbral; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public YearMonth getMes() { return mes; }
    public boolean isReconocida() {return reconocida;}
    public void setReconocida(boolean reconocida) {this.reconocida = reconocida;}


}
