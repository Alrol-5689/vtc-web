package com.vtc.modelo;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Contrato {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contrato;

    @ManyToOne
    @JoinColumn(name = "conductor_id", nullable = false)
    private Conductor conductor; 

    private LocalDate fechaInicio, fechaFin, fechaAltaEnEmpresa;
    private String empresa, notas; 
    private Duration tareasAux; //===>> Crear boolean forzarTareasAuxContrato? para aplicarlo aunque en el convenio sea superior
    private Double salarioAnual;
    private Integer numeroPagasAnio;
    private Boolean pagasExtrasProrrateadas; 
    private Map<DayOfWeek, Duration> jornada;
    private List<DiaConductor> diasTrabajados;
    private List<Plus> pluses;
    private List<PoliticaComision> politicaComision; 
    private List<PoliticaGratificacion> politicaGratificacions; 

    //===>> CONSTRUCTORES <<===//

    public Contrato() {
        this.jornada = new EnumMap<>(DayOfWeek.class);
        this.pluses = new ArrayList<>();
        this.diasTrabajados = new ArrayList<>();
        this.politicaComision = new ArrayList<>();
        this.politicaGratificacions = new ArrayList<>();
    }

    //===>> GETTERS <<===//

    public boolean isContratoReady() { //===>> Verifica si el contrato está listo para ser usado
        return 
            conductor != null && 
            fechaInicio != null && 
            pagasExtrasProrrateadas != null &&
            salarioAnual != null && salarioAnual > 0 &&
            numeroPagasAnio != null && numeroPagasAnio > 0 &&
            !jornada.isEmpty();
        }
        
    public Long getId_contrato() {return id_contrato;}
    public Conductor getConductor() {return conductor;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public LocalDate getFechaAltaEnEmpresa() {return fechaAltaEnEmpresa;}
    public String getEmpresa() {return empresa;}
    public String getNotas() {return notas;}
    public Duration getTareasAux() {return tareasAux;}
    public double getSalarioAnual() {return salarioAnual;}
    public int getNumeroPagasAnio() {return numeroPagasAnio;}
    public boolean isPagasExtrasProrrateadas() {return pagasExtrasProrrateadas;}
    public Map<DayOfWeek, Duration> getJornadaSemanal() {return jornada;}
    public List<DiaConductor> getDiasTrabajados() {return diasTrabajados;}
    public List<Plus> getPluses() {return pluses;}
    public List<PoliticaComision> getPoliticaComision() {return politicaComision;}
    public List<PoliticaGratificacion> getPoliticaGratificacions() {return politicaGratificacions;}

    public boolean isTareasAuxContratoAplicable() {
        if (tareasAux == null || tareasAux.isZero() || tareasAux.isNegative()) return false;
        Convenio convenio = Administrador.getConvenioVigente(fechaInicio);
        if (convenio == null || convenio.getTareasAux() == null) return true;
        return tareasAux.compareTo(convenio.getTareasAux()) > 0;
    }
    
    public Duration getJornada_horasSemanales() {
        Duration total = Duration.ZERO;
        for (Duration d : jornada.values()) if (d != null) total = total.plus(d);        
        return total;
    }
    
    public double getSalarioBase_month() {
        if (!isContratoReady())
            throw new IllegalStateException("El contrato no está listo.");
        return salarioAnual / numeroPagasAnio;
    }

    public double getPPPE_month() {
        if(!isContratoReady())
            throw new IllegalStateException("El contrato no está listo.");
        if(!isPagasExtrasProrrateadas())
            throw new IllegalStateException("Las pagas extras no están prorrateadas.");
        return salarioAnual / numeroPagasAnio * 2 / 12;
    }

    public double getPagaExtra_month() {
        if(!isContratoReady())
            throw new IllegalStateException("El contrato no está listo.");
        if(isPagasExtrasProrrateadas())
            throw new IllegalStateException("Las pagas extras están prorrateadas.");
        return salarioAnual / numeroPagasAnio;
    }
    
    //===>> SETTERS <<===//

    protected void setId_contrato(Long id_contrato) {this.id_contrato = id_contrato;}
    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}
    public void setNotas(String notas) {this.notas = notas;}
    public void setPluses(List<Plus> pluses) {this.pluses = pluses;}
    public void agregarPlus(Plus plus) {pluses.add(plus);}
    public void eliminarPlusContrato(Plus plus) {pluses.removeIf(p -> p.getId_plus() == plus.getId_plus());}
    public void setPoliticaComision(PoliticaComision polCom) {this.politicaComision.add(polCom);}
    public void setPoliticaGratificacions(PoliticaGratificacion polGrat) {this.politicaGratificacions.add(polGrat);}

    public void setConductor(Conductor conductor) {
        if(this.conductor != null) 
            throw new UnsupportedOperationException("El conductor no se puede cambiar una vez asignado.");
        this.conductor = conductor;
    }
    
    public void setTareasAux(Duration tareasAux) {
        if(Administrador.getConvenioVigente(fechaInicio).getTareasAux().compareTo(tareasAux) > 0) 
            throw new IllegalArgumentException("Mínimo se ha de equiparar con el convenio.");
        this.tareasAux = tareasAux;
    }

    public void setJornadaSemanal(Map<DayOfWeek, Duration> jornadaSemana) {
        if(this.jornada != null && !this.jornada.isEmpty()) 
            throw new UnsupportedOperationException("La jornada semanal no se puede cambiar.");
        this.jornada = jornadaSemana;
    }

    public void setSalarioAnual(double salarioAnual) {
        if (this.salarioAnual != null && this.salarioAnual > 0) 
            throw new UnsupportedOperationException("El salario anual no se puede modificarse.");
        if(Administrador.getConvenioVigente(fechaInicio).getSalarioAnual().compareTo(salarioAnual) > 0) 
            throw new IllegalArgumentException("El salario anual debe ser al menos el salario mínimo del convenio.");
        this.salarioAnual = salarioAnual;
    }

    public void setNumeroPagasAnio(int nPagas) {
        if (this.numeroPagasAnio != null && this.numeroPagasAnio > 0) 
            throw new UnsupportedOperationException("El número de pagas no se puede modificarse.");
        this.numeroPagasAnio = nPagas;
    }

    public void setPagasExtrasProrrateadas(boolean pagasExtrasProrrateadas) {
        if (this.pagasExtrasProrrateadas != null) 
            throw new UnsupportedOperationException("El estado de las pagas extras no se puede modificarse.");
        this.pagasExtrasProrrateadas = pagasExtrasProrrateadas;
    }

    public void setPoliticaComision(List<PoliticaComision> politicaComision) {
        this.politicaComision = politicaComision;
    }

    public void setPoliticaGratificacions(List<PoliticaGratificacion> politicaGratificacions) {
        this.politicaGratificacions = politicaGratificacions;
    }


    public void setFechaAltaEnEmpresa(LocalDate fechaAltaEnEmpresa) {this.fechaAltaEnEmpresa = fechaAltaEnEmpresa;}

    public void setDiasTrabajados(List<DiaConductor> diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }


}
