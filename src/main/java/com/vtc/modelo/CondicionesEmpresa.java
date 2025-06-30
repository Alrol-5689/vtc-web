package com.vtc.modelo;

import java.time.Duration;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CondicionesEmpresa {

    private static final TreeMap<YearMonth, Duration> TAREAS_AUXILIARES = new TreeMap<>(); 
    private static final TreeMap<YearMonth, Duration> JORNADA_COMPLETA_SEMANAL = new TreeMap<>(); 
    private static final TreeMap<YearMonth, Double> SALARIO_ANUAL = new TreeMap<>();
    private static final List<Plus> PLUSES = new ArrayList<>();

    private static final int NUM_PAGAS_SALARIO_ANUAL = 14;

    public static Duration getTareasAux(YearMonth mes) {
        return TAREAS_AUXILIARES.getOrDefault(mes, Duration.ofMinutes(0));
    }

    public static void setTareasAux(int year, Duration duracion) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            TAREAS_AUXILIARES.put(ym, duracion);
        }
    }

    public static void setTareasAux(YearMonth mes, Duration duracion) {
        TAREAS_AUXILIARES.put(mes, duracion);
    }

    // ===>> JORNADA COMPLETA SEMANAL

    public static Duration getJornadaCompleta(YearMonth mes) {
        return JORNADA_COMPLETA_SEMANAL.getOrDefault(mes, Duration.ofMinutes(0));
    }

    public static void setJornadaCompleta(YearMonth mes, Duration duracion) {
        JORNADA_COMPLETA_SEMANAL.put(mes, duracion);  
    }

    public static void setJornadaCompleta(int year, Duration duracion) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            JORNADA_COMPLETA_SEMANAL.put(ym, duracion);
        }
    }

    // ===>> SALARIO

    public static double getSalarioAnual(YearMonth mes) {
        return SALARIO_ANUAL.getOrDefault(mes, 0.0);
    }

    public static double getSalarioBase_month(YearMonth mes) {
        return SALARIO_ANUAL.getOrDefault(mes, 0.0) / NUM_PAGAS_SALARIO_ANUAL;
    }

    public static double getPPPE_month(YearMonth mes) {
        return SALARIO_ANUAL.getOrDefault(mes, 0.0) / NUM_PAGAS_SALARIO_ANUAL * 2 / 12;
    }

    public static void setSalarioAnual(int year, double nuevoSalarioAnual) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            SALARIO_ANUAL.put(ym, nuevoSalarioAnual);
        }
    }

    // ===>> PLUSES  <<======>> PLUSES  <<======>> PLUSES  <<======>> PLUSES  <<======>> PLUSES  <<===

    public static void agregarPlus(Plus plus) {
        PLUSES.add(plus);
    }

    public static List<Plus> getPlusesActivosEn(YearMonth mes) {
        return PLUSES.stream()
                .filter(p -> p.estaActivoEn(mes))
                .collect(Collectors.toList());
    }

    public static List<Plus> getPluses() {
        return PLUSES.stream()
                .collect(Collectors.toList());
    }

    private static boolean esMesTrimestral(int mes) {
        return mes == 3 || mes == 6 || mes == 9 || mes == 12;
    }

    public static boolean esMesTrimestral(YearMonth mes) {
        return esMesTrimestral(mes.getMonthValue());
    }

    private static boolean esMesTrimestral_masUno(int mes) {
        return mes == 4 || mes == 7 || mes == 10 || mes == 1;
    }

    public static boolean esMesTrimestral_masUno(YearMonth mes) {
        return esMesTrimestral_masUno(mes.getMonthValue());
    }

    public static double getPlusCalidad(YearMonth mes) {
        return PLUSES.stream()
                .filter(p -> p instanceof PlusCalidad)
                .filter(p -> p.estaActivoEn(mes))
                .map(p -> (PlusCalidad) p)
                .filter(pc -> pc.getSeCobraEnMes()[mes.getMonthValue() - 1])
                .mapToDouble(pc -> pc.getCantidad())
                .sum();
    }

    public static double getPlusVestuario(YearMonth mes) {
        return PLUSES.stream()
                .filter(p -> p instanceof PlusVestuario)
                .filter(p -> p.estaActivoEn(mes))
                .map(p -> (PlusVestuario) p)
                .mapToDouble(pc -> pc.getCantidad())
                .sum();
    }

}
