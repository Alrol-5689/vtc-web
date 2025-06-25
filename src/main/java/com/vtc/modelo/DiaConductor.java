package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;

public class DiaConductor {

    private LocalDate dia;
    private Duration conexion;
    private Duration presencia;
    private Duration tareasAux;
    private double facturacion;
    private Conductor conductor;

    public DiaConductor(Conductor conductor, LocalDate dia, double facturacion, Duration conexion, Duration presencia) {
        this.conductor = conductor;
        this.dia = dia;
        this.facturacion = facturacion;
        this.conexion = conexion;
        this.presencia = presencia;
        // Buscar duración de tareas auxiliares para el mes correspondiente
        YearMonth mes = YearMonth.from(dia);
        this.tareasAux = Jefe.obtenerTareasAuxParaMes(mes);
    }

    // Getters
    public LocalDate getDia() { return dia; }
    public Duration getConexion() { return conexion; }
    public Duration getPresencia() { return presencia; }
    public Duration getTareasAux() { return tareasAux; }
    public double getFacturacion() { return facturacion; }
    public Conductor getConductor() { return conductor; }


}
