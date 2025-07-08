package com.vtc.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador {

    private static final List<Conductor> CONDUCTORES = new ArrayList<>();
    private static final List<Convenio> CONVENIOS = new ArrayList<>(); 
    private static String nombre = "admin", contrasenia = "admin123"; // Credenciales por defecto

    //===>> getters y setters <<===//

    public static List<Convenio> getCONVENIOS() { return CONVENIOS;}
    public static List<Conductor> getCONDUCTORES() { return CONDUCTORES; }
    public static String getContrasenia() { return contrasenia; }
    public static String getNombre() { return nombre; }

    public static void setNombre(String nombre) { Administrador.nombre = nombre; }
    public static void setContrasenia(String contrasenia) { Administrador.contrasenia = contrasenia; }

    //===>> CONVENIOS <<===//

    public static Convenio getConvenioVigente(LocalDate fecha) {
        return CONVENIOS.stream()
            .filter(c -> !c.getFechaInicio().isAfter(fecha))
            .max((c1, c2) -> c1.getFechaInicio().compareTo(c2.getFechaInicio()))
            .orElse(null);
    }
    
    // public static double getPlusConvenioPorTipo(YearMonth mes, PlusConvenio.TipoPlus tipo) {
    // Convenio convenio = getConvenioVigente(mes.atDay(1));
    // if (convenio == null) return 0.0;

    // return convenio.getPlusesPorTipo(tipo).stream()
    //     .filter(p -> p.getSeCobraEnMes()[mes.getMonthValue() - 1])
    //     .mapToDouble(PlusConvenio::getCantidadAnual)
    //     .sum();
    // }



}
