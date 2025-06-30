package com.vtc.modelo;

import java.time.YearMonth;

public class PlusVestuario extends Plus {

    public PlusVestuario(String nombre, double cantidadAnual, YearMonth inicio, YearMonth fin) {
        super(nombre, cantidadAnual / 12, inicio, fin);
    }

    

}
