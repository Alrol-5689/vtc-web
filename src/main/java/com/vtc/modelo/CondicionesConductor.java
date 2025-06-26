package com.vtc.modelo;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.util.Map;

public class CondicionesConductor {
    private Conductor conductor;
    private Convenio convenio;
    private LocalDate inicioVigencia;
    private Map<DayOfWeek, Duration> jornadaPorDiaSemana;
    private double umbralMensualComision;
    private double porcentajeComision;

    public Duration getTareasAuxParaFecha(LocalDate fecha) {
        return convenio.getTareasAux(Year.from(fecha));
    }
}
