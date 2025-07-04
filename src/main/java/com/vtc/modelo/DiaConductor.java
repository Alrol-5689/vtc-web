package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.DayOfWeek;
import java.util.Map;

public class DiaConductor {

    private final Conductor conductor;
    private final LocalDate dia;   
    private Duration jornada, conexion, presencia, tareasAux;
    private double facturacion;
    

    public DiaConductor(Conductor conductor, LocalDate dia, Duration jornada, 
            Duration conexion, Duration presencia, double facturacion) {
        this.conductor = conductor;
        this.dia = dia;
        this.jornada = jornada;
        this.facturacion = facturacion;
        this.conexion = conexion;
        this.presencia = presencia;
        YearMonth mes = YearMonth.from(dia);

        Duration tareasAuxEmpresa = conductor.contratos.getTareasAux(mes);
        Duration tareasAuxConvenio = Convenio.getTareasAux(mes);

        if (tareasAuxEmpresa == null || tareasAuxEmpresa.compareTo(tareasAuxConvenio) < 0) {
            this.tareasAux = tareasAuxConvenio;
        } else {
            this.tareasAux = tareasAuxEmpresa;
        }
    }

    public Duration getJornada() {
        YearMonth mes = YearMonth.from(dia);
        DayOfWeek diaSemana = dia.getDayOfWeek();
        Map<DayOfWeek, Duration> jornadaMes = conductor.getJornada().get(mes);
        if (jornadaMes != null && jornadaMes.get(diaSemana) != null) {
            return jornadaMes.get(diaSemana);
        } else {
            return conductor.getJornadaDiaria_porDef(); // o lo que consideres por defecto
        }
    }

    // Getters
    public LocalDate getDia() { return dia; }
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
