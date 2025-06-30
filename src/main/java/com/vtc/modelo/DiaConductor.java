package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;

public class DiaConductor {

    private final Conductor conductor;
    private final LocalDate dia;   
    private Duration jornada;
    private Duration conexion;
    private Duration presencia;
    private Duration tareasAux;
    private Duration balance;
    private double facturacion;
    

    public DiaConductor(Conductor conductor, LocalDate dia, Duration jornada, 
            Duration conexion, Duration presencia, double facturacion) {
        this.conductor = conductor;
        this.dia = dia;
        this.jornada = jornada;
        this.facturacion = facturacion;
        this.conexion = conexion;
        this.presencia = presencia;
        // Buscar duración de tareas auxiliares para el mes correspondiente
        YearMonth mes = YearMonth.from(dia);
        this.tareasAux = CondicionesEmpresa.getTareasAux(YearMonth.from(dia).getYear()); 
    }

    // Getters
    public LocalDate getDia() { return dia; }
    public Duration getConexion() { return conexion; }
    public Duration getPresencia() { return presencia; }
    public Duration getTareasAux() { return tareasAux; }
    public double getFacturacion() { return facturacion; }
    public Conductor getConductor() { return conductor; }


}
