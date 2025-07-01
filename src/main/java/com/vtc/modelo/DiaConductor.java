package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;

public class DiaConductor {

    private final Conductor conductor;
    private LocalDate dia;   
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

        Duration tareasAuxEmpresa = CondicionesEmpresa.getTareasAux(mes);
        Duration tareasAuxConvenio = Convenio.getTareasAux(mes);

        if (tareasAuxEmpresa == null || tareasAuxEmpresa.compareTo(tareasAuxConvenio) <= 0) {
            this.tareasAux = tareasAuxConvenio;
        } else {
            this.tareasAux = tareasAuxEmpresa;
        }
    }

    public Duration getBalance() {
    return conexion.plus(presencia).plus(tareasAux).minus(jornada);
    }

    // Getters
    public LocalDate getDia() { return dia; }
    public Duration getConexion() { return conexion; }
    public Duration getPresencia() { return presencia; }
    public Duration getTareasAux() { return tareasAux; }
    public double getFacturacion() { return facturacion; }
    public Conductor getConductor() { return conductor; }


}
