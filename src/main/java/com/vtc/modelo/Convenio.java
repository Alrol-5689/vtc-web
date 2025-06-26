package com.vtc.modelo;

import java.time.Duration;
import java.time.Year;
import java.time.YearMonth;
import java.util.TreeMap;

public class Convenio {
    private String nombreConvenio;
    private TreeMap<Year, Duration> tareasAuxPorAño;
    private TreeMap<YearMonth, Duration> jornadaCompletaSemanal;
    private Duration jornadaSemanalReferencia;
    // otros posibles valores: festivos, pluses, etc.

    public Duration getTareasAux(Year year) {
        return tareasAuxPorAño.getOrDefault(year, Duration.ofMinutes(0));
    }
}
