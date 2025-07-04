package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;

public class DiaConductor {

    private Long id_dia_conductor; 
    //===>> conductor - contrato - dia ===>> serán UNIQUE
    private Conductor conductor;
    private Contrato contrato; 
    private LocalDate dia;   
    private Duration jornada, conexion, presencia, tareasAux;
    private double facturacion;
    

    public DiaConductor() {
    }

    //===>> Getters y setters
    public Long getId_dia_conductor() {return id_dia_conductor;}
    public LocalDate getDia() { return dia; }
    public Duration getConexion() { return conexion; }
    public Duration getPresencia() { return presencia; }
    public Duration getTareasAux() { return tareasAux; }
    public double getFacturacion() { return facturacion; }
    public Conductor getConductor() { return conductor; }
    public Duration getBalance() { return conexion.plus(presencia).plus(tareasAux).minus(jornada); }

    public void setId_dia_conductor(Long id_dia_conductor) {this.id_dia_conductor = id_dia_conductor;}
    public void setJornada(Duration jornada) { this.jornada = jornada; }
    public void setConexion(Duration conexion) { this.conexion = conexion; }
    public void setPresencia(Duration presencia) { this.presencia = presencia; }
    public void setTareasAux(Duration tareasAux) { this.tareasAux = tareasAux; }
    public void setFacturacion(double facturacion) { this.facturacion = facturacion; }







}
