package com.vtc.modelo;

import com.vtc.util.BooleanArrayToStringConverter;
import com.vtc.util.Utilities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plus_convenio")
public class PlusConvenio {

    //===>> Atributos <<===//

    @Column(name = "id", nullable = false, unique = true)
    private long id_plus;

    public static enum TipoPlus {CALIDAD, PERMANENCIA, VESTUARIO, OTRO} 
    
    @ManyToOne(optional = false) 
    @JoinColumn(name = "id_anexo", nullable = false) 
    private AnexoConvenio anexoConvenio; // El convenio al que pertenece este plus

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "notas")
    private String notas;
    
    @Column(name = "cantidad_anual", nullable = false)
    private double cantidadAnual;

    @Convert(converter = BooleanArrayToStringConverter.class)
    @Column(name = "se_cobra_en_mes", length = 12, nullable = false)
    private boolean[] seCobraEnMes;

    @Column(name = "meses_necesarios")
    private int mesesNecesarios; // Solo para pluses de tipo PERMANENCIA

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPlus tipo;
    
    // constructor
    
    public PlusConvenio() {
        
        this.seCobraEnMes = new boolean[12]; 
        
    }
    
    // Getters y Setters...Nombre, Notas, Cantidad Anual, Tipo
    
    public AnexoConvenio getAnexoConvenio() {return anexoConvenio;}
    public void setAnexoConvenio(AnexoConvenio anexoConvenio) {this.anexoConvenio = anexoConvenio;}
    public long getId_plus() { return id_plus; }
    public void setId_plus(long id) { this.id_plus = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas;}
    public TipoPlus getTipo() { return tipo; }
    public void setTipo(TipoPlus tipo) { this.tipo = tipo; }
    
    //===>> seCobraEnMes <<===// ES LO PRIMERO QUE SE DEBE SETEAR, ANTES QUE CANTIDAD ANUAL

    public boolean[] getSeCobraEnMes() { return seCobraEnMes; }

    public void setSeCobraEnMes(boolean[] seCobraEnMes) {
        if (seCobraEnMes != null && seCobraEnMes.length != 12) {
            throw new IllegalArgumentException("El array seCobraEnMes debe tener 12 elementos.");
        } else if (seCobraEnMes == null) {
            seCobraEnMes = new boolean[12];
            for (int i = 0; i < 12; i++) {
                seCobraEnMes[i] = true; // Por defecto, se cobra en todos los meses
            }
        }
        this.seCobraEnMes = seCobraEnMes;
    }

    public void setSeCobraEnMes_mensual() { // igual que setSeCobraEnMes() pasandole un null
        this.seCobraEnMes = new boolean[12];
        for (int i = 0; i < 12; i++) {
            seCobraEnMes[i] = true; // Por defecto, se cobra en todos los meses
        }
    }

    public void setSeCobraEnMes_trimestral() {
        this.seCobraEnMes = new boolean[12];
        for (int i = 0; i < 12; i++) {
            seCobraEnMes[i] = Utilities.esMesTrimestral(i + 1); // Se cobra en meses trimestrales
        }
    }

    //===>> cantidadAnual <<===//

    public double getCantidadAnual() { return cantidadAnual;}
    public void setCantidadAnual(double cantidad) { this.cantidadAnual = cantidad; }
    public void setCantidadMensual(double cantidadMensual) { 
        int count = 0;
        for(boolean mes : seCobraEnMes) if (mes) count++;
        this.cantidadAnual = cantidadMensual * count;
    }
    
    //===>> mesesNecesarios <<===//

    public int getMesesNecesarios() { return mesesNecesarios; }

    public void setMesesNecesarios(int mesesNecesarios) { 
        if(this.tipo != TipoPlus.PERMANENCIA)
            throw new IllegalArgumentException("Solo los pluses de tipo PERMANENCIA tienen meses necesarios.");
        if(mesesNecesarios < 0) 
            throw new IllegalArgumentException("Los meses necesarios no pueden ser negativos.");
        this.mesesNecesarios = mesesNecesarios; 
    }

    //===>> Métodos de utilidad <<===//

    public double getCantidadMensual() {
        if (seCobraEnMes == null) return 0.0;
        int meses = 0;
        for (boolean cobra : seCobraEnMes) if (cobra) meses++;
        if (meses == 0) return 0.0;
        return cantidadAnual / meses;
    }

}
