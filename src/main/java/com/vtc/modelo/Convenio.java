package com.vtc.modelo;

import java.time.Duration;
import java.time.YearMonth;
import java.util.TreeMap;

public class Convenio {

    private static final TreeMap<YearMonth, Duration> tareasAux = new TreeMap<>(); 
    private static final TreeMap<YearMonth, Duration> jornadaCompleta = new TreeMap<>(); 
    private static final TreeMap<YearMonth, Double> salarioAnual = new TreeMap<>();
    private static final TreeMap<YearMonth, Double> plusCalidad = new TreeMap<>();
	private static final TreeMap<YearMonth, Double> plusPermanencia_3_meses = new TreeMap<>();
	private static final TreeMap<YearMonth, Double> plusPermanencia_6_meses = new TreeMap<>();
    private static final TreeMap<YearMonth, Double> plusVestuario = new TreeMap<>();
    private static final int NUM_PAGAS_SALARIO_ANUAL = 14;

    public static Duration getTareasAux(YearMonth mes) {
        return tareasAux.getOrDefault(mes, Duration.ofMinutes(0));
    }

    public static void setTareasAux(int year, Duration duracion) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            tareasAux.put(ym, duracion);
        }
    }

    public static void setTareasAux(YearMonth mes, Duration duracion) {
        tareasAux.put(mes, duracion);
    }

    public static Duration getJornadaCompleta(YearMonth mes) {
        return jornadaCompleta.getOrDefault(mes, Duration.ofMinutes(0));
    }

    public static void setJornadaCompleta(int year, Duration duracion) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            jornadaCompleta.put(ym, duracion);
        }
    }

    // SALARIO

    public static double getSalarioAnual(YearMonth mes) {
        return salarioAnual.getOrDefault(mes, 0.0);
    }

    public static double getSalarioBase_month(YearMonth mes) {
        return salarioAnual.getOrDefault(mes, 0.0) / NUM_PAGAS_SALARIO_ANUAL;
    }

    public static double getPPPE_month(YearMonth mes) {
        return salarioAnual.getOrDefault(mes, 0.0) / NUM_PAGAS_SALARIO_ANUAL * 2 / 12;
    }

    public static void setSalarioAnual(int year, double nuevoSalarioAnual) {
        for(int mes = 1; mes <= 12; mes++) {
            YearMonth ym = YearMonth.of(year, mes);
            salarioAnual.put(ym, nuevoSalarioAnual);
        }
    }

    // PLUSCALIDAD

    public static double getPlusCalidad(YearMonth mes) {
        return plusCalidad.getOrDefault(mes, 0.0);
    }

    private static boolean esMesTrimestral(int mes) {
    return mes == 3 || mes == 6 || mes == 9 || mes == 12;
}

    // public static void setPlusCalidad(int year, double nuevoPlusCalidad) {
    //     for(int mes = 3; mes <= 12; mes+=3) {
    //         YearMonth ym = YearMonth.of(year, mes);
    //         plusCalidad.put(ym, nuevoPlusCalidad);
    //     }
    // }

    public static void setPlusCalidad(int year, double nuevoPlusCalidad) {
        for(int mes = 1; mes <= 12; mes++) {
            if(esMesTrimestral(mes)) {
                YearMonth ym = YearMonth.of(year, mes);
                plusCalidad.put(ym, nuevoPlusCalidad);
            }          
        }
    }

    

    


}
