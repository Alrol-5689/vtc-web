package com.vtc.modelo;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Administrador {

    private static final List<Convenio> CONVENIOS = new ArrayList<>(); 
    private static final List<Conductor> CONDUCTORES = new ArrayList<>();
    private static String nombre = "admin", contrasenia = "admin123";

    public static List<Convenio> getCONVENIOS() { return CONVENIOS;}
    public static List<Conductor> getCONDUCTORES() { return CONDUCTORES; }

    public static String getNombre() { return nombre; }
    public static void setNombre(String nombre) { Administrador.nombre = nombre; }
    public static String getContrasenia() { return contrasenia; }
    public static void setContrasenia(String contrasenia) { Administrador.contrasenia = contrasenia; }
    
    public static Convenio getConvenioVigente(LocalDate fecha) {
        return CONVENIOS.stream()
            .filter(c -> (c.getFechaInicio().compareTo(fecha) <= 0) &&
                         (c.getFechaFin() == null || c.getFechaFin().compareTo(fecha) >= 0))
            .findFirst()
            .orElse(null);
    }

}
