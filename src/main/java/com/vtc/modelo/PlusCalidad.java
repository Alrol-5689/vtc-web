package com.vtc.modelo;

import java.time.YearMonth;

public class PlusCalidad extends Plus {

    private boolean[] seCobraEnMes;

    private static final String TIPO_DE_PLUS = "CALIDAD";

    public PlusCalidad(String nombre, double cantidad, YearMonth inicio, YearMonth fin, boolean[] seCobraEnMes) {
        super(nombre, cantidad, inicio, fin);
        if (seCobraEnMes != null && seCobraEnMes.length != 12) {
            throw new IllegalArgumentException("El array seCobraEnMes debe tener 12 elementos.");
        } else if (seCobraEnMes == null) {
            seCobraEnMes = new boolean[12];
            for (int i = 0; i < 12; i++) {
                seCobraEnMes[i] = true;
            }
        }
        this.seCobraEnMes = seCobraEnMes;
    }

    public boolean[] getSeCobraEnMes() {
        return seCobraEnMes;
    }

    public void setSeCobraEnMes(boolean[] seCobraEnMes) {
        this.seCobraEnMes = seCobraEnMes;
    }

    public static String getTIPO_DE_PLUS() {
        return TIPO_DE_PLUS;
    }


}
