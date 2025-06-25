package com.vtc.modelo;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Conductor extends Integrante {
    
    // Puedes agregar atributos propios del Conductor aquí en el futuro, como Turno, gestor asignado, etc.
	
	private List<DiaConductor> diasTrabajados;
	private Map<YearMonth, Gestor> gestorAsignado;
	private Map<YearMonth, Turno> listaTurnos;

    public Conductor(int id, String dni, String nombre, String apellido1, String apellido2, 
    		String telefono, String email, String nick, String password, 
    			Gestor gestorAsignado, Turno turno, LocalDate fechaAlta) {   	
        super(id, dni, nombre, apellido1, apellido2, telefono, email, nick, password, fechaAlta);       
        this.diasTrabajados = new ArrayList<>();
        this.gestorAsignado = new TreeMap<>();
        this.listaTurnos = new TreeMap<>();         
        YearMonth mesActual = YearMonth.now();
        this.gestorAsignado.put(mesActual, gestorAsignado);
        this.listaTurnos.put(mesActual, turno);        
    }
    
    @Override
    public String getTipo() {
        return "Conductor";
    }
    
    // Getters y setters
    public List<DiaConductor> getDiasTrabajados() {return diasTrabajados;}

    public Map<YearMonth, Gestor> getGestorAsignado() {return gestorAsignado;}

    public Map<YearMonth, Turno> getListaTurnos() {return listaTurnos;}
}


