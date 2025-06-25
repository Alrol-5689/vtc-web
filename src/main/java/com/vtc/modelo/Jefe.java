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
	private static final int NUM_MENSUALIDADES_PLUS_PERMANENCIA = 12;
	private static final int NUM_PAGAS_EXTRA_INCLUIDAS = 2;

	public Jefe(int id, String dni, String nombre, String apellido1, String apellido2, String telefono, String email,
			String nick, String password, LocalDate fechaAlta) {
		super(id, dni, nombre, apellido1, apellido2, telefono, email, nick, password, fechaAlta);
	}

	@Override
	public String getTipo() {
		return "Jefe";
	}
	
	// Otros Pluses
	
	public static double getOtrosPluses(YearMonth mes) {
		return Jefe.otrosPluses.get(mes);
	}
	
	public static void setOtrosPluses(YearMonth mes, double nuevoOtroPlus) {
		Jefe.otrosPluses.put(mes, nuevoOtroPlus);
	}
	
	// Plus Permanencia
	
	public static double getPlusPermanencia_3_meses(YearMonth mes) {
		return 
				Jefe.plusPermanencia_3_meses.getOrDefault(mes, Jefe.plusPermanencia_3_meses_porDef) 
				/ NUM_MENSUALIDADES_PLUS_PERMANENCIA;
	}
	
	public static double getPlusPermanencia_6_meses(YearMonth mes) {
		return 
				Jefe.plusPermanencia_6_meses.getOrDefault(mes, Jefe.plusPermanencia_6_meses_porDef) 
				/ NUM_MENSUALIDADES_PLUS_PERMANENCIA;
	}
	
	public static void setPlusPermanencia_3_meses(YearMonth mes, double nuevoPlus_3_mese) {
		Jefe.plusPermanencia_3_meses.put(mes, nuevoPlus_3_mese);
	}
	
	public static void setPlusPermanencia_6_meses(YearMonth mes, double nuevoPlus_6_mese) {
		Jefe.plusPermanencia_6_meses.put(mes, nuevoPlus_6_mese);
	}
	
	public static void setPlusPermanencia_3_meses(int anio, double nuevoPlus_3_mese) {
		for(int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.plusPermanencia_3_meses.put(ym, nuevoPlus_3_mese);
		}	
	}
	
	public static void setPlusPermanencia_6_meses(int anio, double nuevoPlus_6_mese) {
		for(int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.plusPermanencia_6_meses.put(ym, nuevoPlus_6_mese);
		}	
	}
	
	public static void setPlusPermanencia_3_meses_porDef(double nuevoPlus_3_mese) {
		Jefe.plusPermanencia_3_meses_porDef = nuevoPlus_3_mese;
	}
	
	public static void setPlusPermanencia_6_meses_porDef(double nuevoPlus_6_mese) {
		Jefe.plusPermanencia_6_meses_porDef = nuevoPlus_6_mese;
	}

	// Plus Calidad

	public static void setPlusCalidad_mes(YearMonth mes, double nuevoPlusCalidad) {
		Jefe.plusCalidad.put(mes, nuevoPlusCalidad);
	}
	
	public static double getPlusCalidad(YearMonth mes) {
		return Jefe.plusCalidad.getOrDefault(mes, 0.0);
	}

	// Pus Vestuario

	public static void definirPlusVestuario(YearMonth mes, double nuevoPlusVestuario) {
		Jefe.plusVestuario.put(mes, nuevoPlusVestuario);
	}

	public static void definirPlusVestuario_porDef(double nuevoPlusVestuario) {
		Jefe.plusVestuario_porDef = nuevoPlusVestuario;
	}

	public static double getPlusVestuario_porDef() {
		return plusVestuario_porDef;
	}

	public static double getPlusVestuario(YearMonth mes) {
		return Jefe.plusVestuario.getOrDefault(mes, Jefe.plusVestuario_porDef) / NUM_MENSUALIDADES_PLUS_VESTUARIO;
	}

	// Tiempo para las tareas auxiliares

	public static void definirTareasAuxParaMes(YearMonth mes, Duration duracion) {
		tareasAux.put(mes, duracion);
	}

	public static void definirTareasAuxPorDefecto(Duration duracion) {
		tareasAux_porDef = duracion;
	}

	public static Duration obtenerTareasAuxParaMes(YearMonth mes) {
		return tareasAux.getOrDefault(mes, tareasAux_porDef);
	}

	// Política de comisiones por turno y mes

	public static void definirPoliticaComision(YearMonth mes, Turno turno, PoliticaComision politica) {
		politicasComision.putIfAbsent(mes, new HashMap<>()); // si no existe el Map para ese mes lo crea
		politicasComision.get(mes).put(turno, politica); // una vez creado metemos la política
	}

	public static PoliticaComision obtenerPoliticaComision(YearMonth mes, Turno turno) {
		Map<Turno, PoliticaComision> mapaTurno = politicasComision.get(mes);
		if (mapaTurno == null)
			return null;
		return mapaTurno.get(turno);
	}

	// Salario

	public static Map<YearMonth, Double> getListaSalarioAnualCompleto() {
		return salarioAnual;
	}

	public static double getSalarioAnualCompleto(YearMonth mes) {
		return salarioAnual.get(mes);
	}

	public static void definirSalarioAnualParaAnio(int anio, double salarioAnual) {
		for (int mes = 1; mes <= 12; mes++) {
			YearMonth ym = YearMonth.of(anio, mes);
			Jefe.salarioAnual.put(ym, salarioAnual);
		}
	}

	public static void definirSalarioAnualParaMes(YearMonth mes, double salarioAnual) {
		Jefe.salarioAnual.put(mes, salarioAnual);
	}

	public static double getSalarioAnualPorDefecto() {
		return salarioAnual_porDef;
	}

	public static void setSalarioAnualPorDefecto(double salarioAnualPorDefecto) {
		Jefe.salarioAnual_porDef = salarioAnualPorDefecto;
	}

	public static double getSalarioBase_mes(YearMonth mes) { // Salario base del mes
		return Jefe.salarioAnual.getOrDefault(mes, Jefe.salarioAnual_porDef) / NUM_PAGAS;
	}

	public static double getPPPE_mes(YearMonth mes) { // parte proporcional de las pagas extra del mes
		return Jefe.salarioAnual.getOrDefault(mes, Jefe.salarioAnual_porDef) / NUM_PAGAS * NUM_PAGAS_EXTRA_INCLUIDAS
				/ NUM_MENSUALIDADES_PPPE;
	}

	// Jornada completa

	public static double getJornadaCompletaSemanalPorDefecto() {
		return jornadaCompletaSemanal_porDef;
	}

	public static void setJornadaCompletaSemanalPorDefecto(double jornadaCompletaSemanalPorDefecto) {
		Jefe.jornadaCompletaSemanal_porDef = jornadaCompletaSemanalPorDefecto;
	}

	public static double getHorasJornadaComprletaSemanal(YearMonth mes) {
		return horasJornadaComprletaSemanal.get(mes);
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

	// Métodos de responsabilidad del jefe los agregaremos más adelante:
	// - asignarConductorAGestor(...)
	// - definirComisionPorTurno(...)
	// - establecerDuracionTareasAux(...)
	// - crearGratificacionExtraordinaria(...)
}
