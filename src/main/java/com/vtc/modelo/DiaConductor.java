package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;

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
    name = "dia_conductor", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fecha", "id_conductor"})
    }
)
public class DiaConductor {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dia_conductor")
    private Long idDia; 

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "jornada")   
    private Duration jornada;

    @Column(name = "conexion")
    private Duration conexion;

    @Column(name = "presencia")
    private Duration presencia;

    @Column(name = "tareas_aux")
    private Duration tareasAux;

    @Column(name = "facturacion")
    private double facturacion;
    
    //===>> CONSTRUCTORES <<===//

    public DiaConductor() {
    }

    //===>> Getters y setters
    public Long getId() {return idDia;}
    public LocalDate getFecha() { return fecha; }
    public Duration getConexion() { return conexion; }
    public Duration getPresencia() { return presencia; }
    public Duration getTareasAux() { return tareasAux; }
    public double getFacturacion() { return facturacion; }
    public Conductor getConductor() { return conductor; }
    public Duration getBalance() { return conexion.plus(presencia).plus(tareasAux).minus(jornada); }

    public void setJornada(Duration jornada) { this.jornada = jornada; }
    public void setConexion(Duration conexion) { this.conexion = conexion; }
    public void setPresencia(Duration presencia) { this.presencia = presencia; }
    public void setTareasAux(Duration tareasAux) { this.tareasAux = tareasAux; }
    public void setFacturacion(double facturacion) { this.facturacion = facturacion; }









}
