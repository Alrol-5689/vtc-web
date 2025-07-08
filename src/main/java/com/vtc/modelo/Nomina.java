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
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "nomina", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_conductor", "mes"}),
    }
)
public class Nomina {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_nomina; 

    @ManyToOne
    @JoinColumn(name = "id_conductor", nullable = false)
    private Conductor conductor;

    @Column(name = "mes", nullable = false)
    private YearMonth mes;

    @Column(name = "salarioBase", nullable = false)
    private double salarioBase;

    @Column(name = "pppe", nullable = false)
    private double pppe;

    @Column(name = "comision", nullable = false)
    private double comision;

    @Column(name = "gratificaciones", nullable = false)
    private double gratificaciones;

    @Column(name = "plusVestuario", nullable = false)
    private double plusVestuario;

    @Column(name = "plusCalidad", nullable = false)
    private double plusCalidad;

    @Column(name = "plusPermanencia", nullable = false)
    private double plusPermanencia; // único campo

    @Column(name = "otrosPluses", nullable = false)
    private double otrosPluses;

    //===>> CONSTRUCTORES <<===//

    public Nomina() {}

    //===>> GETTERS <<===//

    public Long getId_nomina() {return id_nomina;}
    public Conductor getConductor() {return conductor;}
    public double getPlusPermanencia() { return plusPermanencia; }
    public double getPlusCalidad() { return plusCalidad; }
    public YearMonth getMes() {return mes;}
    public double getSalarioBase() {return salarioBase;}
    public double getPppe() {return pppe;}
    public double getGratificaciones() {return gratificaciones;}
    public double getPlusVestuario() {return plusVestuario;}
    public double getOtrosPluses() {return otrosPluses;}
    public double getComision() {return comision;}
    
    public void setMes(YearMonth mes) {this.mes = mes;}
    public void setOtrosPluses(double otrosPluses) {this.otrosPluses = otrosPluses;}
    public void setPlusPermanencia(double plusPermanencia) {this.plusPermanencia = plusPermanencia;}
    public void setPlusVestuario(double plusVestuario) {this.plusVestuario = plusVestuario;}
    public void setSalarioBase(double salarioBase) {this.salarioBase = salarioBase;}
    public void setPppe(double pppe) {this.pppe = pppe;}
    public void setComision(double comision) {this.comision = comision;}
    public void setGratificaciones(double gratificaciones) {this.gratificaciones = gratificaciones;}
    public void setPlusCalidad(double plusCalidad) {this.plusCalidad = plusCalidad;}
    public void setConductor(Conductor conductor) {this.conductor = conductor;}
}
