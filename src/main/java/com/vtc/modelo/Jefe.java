package com.vtc.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Jefe extends Integrante {

	/*
	 * SI PERMITMOS QUE HAYA MÁS DE UN JEFE (Entendemos un jefe como una empresa)
	 * habria que eliminar los static, ya que cada empresa tendría sus criterios.
	 */

	private static Map<YearMonth, Double> salarioAnual = new TreeMap<>(); // en euros brutos
	private static Map<YearMonth, Double> horasJornadaComprletaSemanal = new TreeMap<>();
	private static Map<YearMonth, Map<Turno, PoliticaComision>> politicasComision = new TreeMap<>();
	private static Map<YearMonth, Duration> tareasAux = new TreeMap<>();
	private static Map<YearMonth, Double> plusVestuario = new TreeMap<>();
	private static Map<YearMonth, Double> plusCalidad = new TreeMap<>();
	private static Map<YearMonth, Double> plusPermanencia_3_meses = new TreeMap<>();
	private static Map<YearMonth, Double> plusPermanencia_6_meses = new TreeMap<>();
	private static Map<YearMonth, Double> otrosPluses = new TreeMap<>();

	private static double salarioAnual_porDef = 17000;
	private static double jornadaCompletaSemanal_porDef = 40;
	private static Duration tareasAux_porDef = Duration.ofMinutes(20);
	private static double plusVestuario_porDef = 100;
	private static double plusPermanencia_3_meses_porDef = 100;
	private static double plusPermanencia_6_meses_porDef = 100;

	private static final int NUM_PAGAS = 14;
	private static final int NUM_MENSUALIDADES_PPPE = 12;
	private static final int NUM_MENSUALIDADES_PLUS_VESTUARIO = 12;
	private static final int NUM_PAGAS_EXTRA_INCLUIDAS = 2;

    

    

	// CONSTRUCTOR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public Jefe(int id, String dni, String nombre, String apellido1, String apellido2, String telefono, String email,
			String nick, String password, LocalDate fechaAlta) {
		super(id, dni, nombre, apellido1, apellido2, telefono, email, nick, password, fechaAlta);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@Override
	public String getTipo() {
		return "Jefe";
	}
	
	// OTROS PLUSES ======================================================================================================================================
	
	public static Map<YearMonth, Double> getOtrosPluses() {
        return otrosPluses;
    }

	public static double getOtrosPluses(YearMonth mes) {
		return Jefe.otrosPluses.getOrDefault(mes, 0.0);
	}

    public static void setOtrosPluses(Map<YearMonth, Double> otrosPluses) {
        Jefe.otrosPluses = otrosPluses;
    }

	
	
	public static void setOtrosPluses(YearMonth mes, double nuevoOtroPlus) {
		Jefe.otrosPluses.put(mes, nuevoOtroPlus);
	}

	// PLUS PERMANENCIA 3 MESES ===========================================================================================================================
	
	// plusPermanencia_3_meses -- getters

	public static Map<YearMonth, Double> getPlusPermanencia_3_meses() {
        return plusPermanencia_3_meses;
    }

	public static double getPlusPermanencia_3_meses(YearMonth mes) {
		return Jefe.plusPermanencia_3_meses.getOrDefault(mes, Jefe.plusPermanencia_3_meses_porDef);
	}

	// plusPermanencia_3_meses -- setters

    public static void setPlusPermanencia_3_meses(Map<YearMonth, Double> plusPermanencia_3_meses) {
        Jefe.plusPermanencia_3_meses = plusPermanencia_3_meses;
    }

	public static void setPlusPermanencia_3_meses(YearMonth mes, double nuevoPlus_3_mese) {
		Jefe.plusPermanencia_3_meses.put(mes, nuevoPlus_3_mese);
	}
	
	public static void setPlusPermanencia_3_meses(int anio, double nuevoPlus_3_mese) {
		for(int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.plusPermanencia_3_meses.put(ym, nuevoPlus_3_mese);
		}	
	}

	// plusPermanencia_3_meses_porDef -- setter && getter

	public static double getPlusPermanencia_3_meses_porDef() {
		return Jefe.plusPermanencia_3_meses_porDef;
	}

	public static void setPlusPermanencia_3_meses_porDef(double nuevoPlus_3_mese) {
		Jefe.plusPermanencia_3_meses_porDef = nuevoPlus_3_mese;
	}

	// PLUS PERMANENCIA 6 MESES ===========================================================================================================================
	
	// plusPermanencia_6_meses -- getters

	public static Map<YearMonth, Double> getPlusPermanencia_6_meses() {
        return plusPermanencia_6_meses;
    }

	public static double getPlusPermanencia_6_meses(YearMonth mes) {
		return Jefe.plusPermanencia_6_meses.getOrDefault(mes, Jefe.plusPermanencia_6_meses_porDef);
	}

	// plusPermanencia_6_meses -- setters

    public static void setPlusPermanencia_6_meses(Map<YearMonth, Double> plusPermanencia_6_meses) {
        Jefe.plusPermanencia_6_meses = plusPermanencia_6_meses;
    }
	
	public static void setPlusPermanencia_6_meses(YearMonth mes, double nuevoPlus_6_mese) {
		Jefe.plusPermanencia_6_meses.put(mes, nuevoPlus_6_mese);
	}
	
	public static void setPlusPermanencia_6_meses(int anio, double nuevoPlus_6_mese) {
		for(int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.plusPermanencia_6_meses.put(ym, nuevoPlus_6_mese);
		}	
	}

	// plusPermanencia_6_meses_porDef -- setter && getter

	public static double getPlusPermanencia_6_meses_porDef() {
		return Jefe.plusPermanencia_3_meses_porDef;
	}
	
	public static void setPlusPermanencia_6_meses_porDef(double nuevoPlus_6_mese) {
		Jefe.plusPermanencia_6_meses_porDef = nuevoPlus_6_mese;
	}

	// PLUS CALIDAD ======================================================================================================================================

	// plusCalidad -- getters

	public static Map<YearMonth, Double> getPlusCalidad() {
        return plusCalidad;
    }

	public static double getPlusCalidad(YearMonth mes) {
		return Jefe.plusCalidad.getOrDefault(mes, 0.0);
	}

	// plusCalidad -- setters

    public static void setPlusCalidad(Map<YearMonth, Double> plusCalidad) {
        Jefe.plusCalidad = plusCalidad;
    }

	public static void setPlusCalidad_mes(YearMonth mes, double nuevoPlusCalidad) {
		Jefe.plusCalidad.put(mes, nuevoPlusCalidad);
	}

	// PLUS VESTUARIO ======================================================================================================================================

	// Pus Vestuario -- getters

    public static Map<YearMonth, Double> getPlusVestuario() {
        return plusVestuario;
    }

	public static double getPlusVestuario(YearMonth mes) {
		return Jefe.plusVestuario.getOrDefault(mes, Jefe.plusVestuario_porDef);
	}

	public static double getPlusVestuario_month(YearMonth mes) {
		return Jefe.plusVestuario.getOrDefault(mes, Jefe.plusVestuario_porDef) / NUM_MENSUALIDADES_PLUS_VESTUARIO;
	}

	// Pus Vestuario -- setters

    public static void setPlusVestuario(Map<YearMonth, Double> plusVestuario) {
        Jefe.plusVestuario = plusVestuario;
    }

	public static void setPlusVestuario(YearMonth mes, double nuevoPlusVestuario) {
		Jefe.plusVestuario.put(mes, nuevoPlusVestuario);
	}

	// plusVestuario_porDef -- setter && getter

	public static void setPlusVestuario_porDef(double nuevoPlusVestuario_porDef) {
		Jefe.plusVestuario_porDef = nuevoPlusVestuario_porDef;
	}

	public static double getPlusVestuario_porDef() {
		return plusVestuario_porDef;
	}

	// TAREAS AUXILIARES ======================================================================================================================================

	// tareasAux -- getters

    public static Map<YearMonth, Duration> getTareasAux() {
        return tareasAux;
    }

	public static Duration getTareasAux_month(YearMonth mes) {
		return tareasAux.getOrDefault(mes, tareasAux_porDef);
	}

	// tareasAux -- setters

    public static void setTareasAux(Map<YearMonth, Duration> tareasAux) {
        Jefe.tareasAux = tareasAux;
    }

	public static void setTareasAux(YearMonth mes, Duration duracion) {
		tareasAux.put(mes, duracion);
	}

	// tareasAux_porDef -- getters && setters

	public static void setTareasAux_porDef(Duration duracion) {
		tareasAux_porDef = duracion;
	}

	// POLITICAS DE COMISION ======================================================================================================================================

	// politicasComision -- getters

    public static Map<YearMonth, Map<Turno, PoliticaComision>> getPoliticasComision() {
        return politicasComision;
    }

	public static PoliticaComision getPoliticasComision(YearMonth mes, Turno turno) {
		Map<Turno, PoliticaComision> mapaTurno = politicasComision.get(mes);
		if (mapaTurno == null)
			return null;
		return mapaTurno.get(turno);
	}

	// politicasComision -- setters

    public static void setPoliticasComision(Map<YearMonth, Map<Turno, PoliticaComision>> politicasComision) {
        Jefe.politicasComision = politicasComision;
    }

	public static void setPoliticasComision(YearMonth mes, Turno turno, PoliticaComision politica) {
		politicasComision.putIfAbsent(mes, new HashMap<>()); // si no existe el Map para ese mes lo crea
		politicasComision.get(mes).put(turno, politica); // una vez creado metemos la política
	}

	// SALARIO ======================================================================================================================================

	// salarioAnual -- getters

    public static Map<YearMonth, Double> getSalarioAnual() {
        return salarioAnual;
    }

	public static double getSalarioAnual(YearMonth mes) {
		return salarioAnual.get(mes);
	}

	// salarioAnual - setters

	public static void setSalarioAnual(Map<YearMonth, Double> nuevoSalarioAnual) {
		Jefe.salarioAnual = nuevoSalarioAnual;
	}

	public static void setSalarioAnual(int anio, double salarioAnual) {
		for (int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.salarioAnual.put(ym, salarioAnual);
		}
	}

	public static void setSalarioAnual(YearMonth mes, double salarioAnual) {
		Jefe.salarioAnual.put(mes, salarioAnual);
	}

	// salarioAnual_porDef

	public static double getSalarioAnual_porDef() {
		return salarioAnual_porDef;
	}

	public static void setSalarioAnual_porDef(double salarioAnualPorDefecto) {
		Jefe.salarioAnual_porDef = salarioAnualPorDefecto;
	}

	// salario del mes 

	public static double getSalarioBase_mes(YearMonth mes) { // Salario base del mes
		return Jefe.salarioAnual.getOrDefault(mes, Jefe.salarioAnual_porDef) / NUM_PAGAS;
	}

	public static double getPPPE_mes(YearMonth mes) { // parte proporcional de las pagas extra del mes
		return Jefe.salarioAnual.getOrDefault(mes, Jefe.salarioAnual_porDef) / NUM_PAGAS * NUM_PAGAS_EXTRA_INCLUIDAS
				/ NUM_MENSUALIDADES_PPPE;
	}

	// JORNADA COMPLETA ======================================================================================================================================

	// horasJornadaComprletaSemanal -- getters

    public static Map<YearMonth, Double> getHorasJornadaComprletaSemanal() {
        return horasJornadaComprletaSemanal;
    }

	public static double getHorasJornadaComprletaSemanal(YearMonth mes) {
		return horasJornadaComprletaSemanal.getOrDefault(mes, salarioAnual_porDef);
	}

	// horasJornadaComprletaSemanal -- setters

    public static void setHorasJornadaComprletaSemanal(Map<YearMonth, Double> horasJornadaComprletaSemanal) {
        Jefe.horasJornadaComprletaSemanal = horasJornadaComprletaSemanal;
    }

	public static void setHorasJornadaComprletaSemanal(YearMonth mes, double horas) {
		Jefe.horasJornadaComprletaSemanal.put(mes, horas);
	}

	public static void setHorasJornadaComprletaSemanal(int anio, double horas) {
		for (int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.horasJornadaComprletaSemanal.put(ym, horas);
		}
	}

	// jornadaCompletaSemanal_porDef -- getter && setter

	public static double getJornadaCompletaSemanal_porDef() {
		return jornadaCompletaSemanal_porDef;
	}

	public static void setJornadaCompletaSemanal_porDef(double jornadaCompletaSemanalPorDefecto) {
		Jefe.jornadaCompletaSemanal_porDef = jornadaCompletaSemanalPorDefecto;
	}





	// Métodos de responsabilidad del jefe los agregaremos más adelante:
	// - asignarConductorAGestor(...)
	// - definirComisionPorTurno(...)
	// - establecerDuracionTareasAux(...)
	// - crearGratificacionExtraordinaria(...)
}
