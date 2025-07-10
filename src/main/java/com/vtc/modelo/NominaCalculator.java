package com.vtc.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;

import com.vtc.modelo.PlusConvenio.TipoPlus;

public class NominaCalculator {

    public static Nomina getNomina(Driver conductor, YearMonth mes) {
        double salarioBaseMensual = calcularSalarioBase(conductor, mes);
        double pppe = calcularPPPE(conductor, mes);
        double comision = new ComisionConductor(conductor, mes).getComision();
        double gratificaciones = calcularGratificaciones(conductor, mes);

        double plusVestuario = PlusConvenio.getImporte(conductor, mes, TipoPlus.VESTUARIO);
        double plusPermanencia = calcularPlusPermanencia(conductor, mes);
        double plusCalidad = calcularPlusCalidad(conductor, mes);
        double otrosPluses = PlusConvenio.getImporte(conductor, mes, TipoPlus.OTROS);

        Nomina nomina = new Nomina();
        nomina.setMes(mes);
        nomina.setConductor(conductor);
        nomina.setSalarioBase(salarioBaseMensual);
        nomina.setPppe(pppe);
        nomina.setComision(comision);
        nomina.setGratificaciones(gratificaciones);
        nomina.setPlusVestuario(plusVestuario);
        nomina.setPlusCalidad(plusCalidad);
        nomina.setPlusPermanencia(plusPermanencia);
        nomina.setOtrosPluses(otrosPluses);

        return nomina;
    }

    
        // public Nomina getNomina(Conductor conductor, YearMonth mes) {

    //     double salarioBaseMensual = calcularSalarioBase(conductor, mes);
    //     double pppe = calcularPPPE(conductor, mes);
    //     double comision = new ComisionConductor(conductor, mes).getComision();
    //     double gratificaciones = calcularGratificaciones(conductor, mes);

    //     double plusVestuario = Jefe.getPlusVestuario(mes);
    //     double plusPermanencia = calcularPlusPermanencia(conductor, mes);
    //     double plusCalidad = calcularPlusCalidad(conductor, mes);
    //     double otrosPluses = Jefe.getOtrosPluses(mes);

    //     return new Nomina(conductor, mes, salarioBaseMensual, pppe, comision, gratificaciones,
    //                       plusVestuario, plusCalidad, plusPermanencia, otrosPluses);
    // }

    private static double calcularSalarioBase(Driver conductor, YearMonth mes) {
        AnejoContrato contratoAnejo = conductor.getContratoVigente(mes.atDay(1))
            .getAnejoVigente(mes.atDay(1));
        if (contratoAnejo != null && contratoAnejo.getSalarioAnual() != null) 
            return contratoAnejo.getSalarioAnual() / 14;
        AnexoConvenio convenioAnejo = Administrador.getConvenioVigente(mes.atDay(1))
            .getAnexoVigente(mes.atDay(1));
        if (convenioAnejo != null && convenioAnejo.getSalarioAnual() != null) 
                return convenioAnejo.getSalarioAnual() / 14;
        return 0; // Default if nothing is found
    }
    
    private static double calcularPPPE(Driver conductor, YearMonth mes) {
        return 
        		conductor.getListaTurnos().get(mes).getHorasSemanales() 
        		* Jefe.getPPPE_mes(mes)
        		/ Jefe.getHorasJornadaComprletaSemanal(mes);
    }

    private static double calcularPlusPermanencia(Driver conductor, YearMonth mes) {
        LocalDate fechaAlta = conductor.getFechaAlta();
        LocalDate finMes = mes.atEndOfMonth();
        Period antiguedad = Period.between(fechaAlta, finMes);

        if (antiguedad.getYears() >= 0 && antiguedad.getMonths() >= 6) {
            return Jefe.getPlusPermanencia_6_meses(mes);
        } else if (antiguedad.getYears() >= 0 && antiguedad.getMonths() >= 3) {
            return Jefe.getPlusPermanencia_3_meses(mes);
        } else {
            return 0;
        }
    }

    private static double calcularPlusCalidad(Driver conductor, YearMonth mes) {
        int mesNum = mes.getMonthValue();
        boolean esTrimestre = (mesNum == 3 || mesNum == 6 || mesNum == 9 || mesNum == 12);

        if (!esTrimestre) return 0;

        if (cumpleCondicionesCalidad(conductor, mes)) {
            return Jefe.getPlusCalidad(mes);
        }

        return 0;
    }

    private static boolean cumpleCondicionesCalidad(Driver conductor, YearMonth mes) {
        // Placeholder para lógica real, por ejemplo:
        // - haber trabajado al menos X días
        // - facturación media diaria superior a Y
        // - sin incidencias, etc.
        return true; // Aquí puedes afinarlo más adelante
    }

    private static double calcularGratificaciones(Driver conductor, YearMonth mes) {
        // A implementar luego: sumar gratificaciones extraordinarias obtenidas en ese mes
        return 0;
    }
}
