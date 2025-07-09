package com.vtc.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {

    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_administrador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    //===>> getters y setters <<===//

    public Long getId_administrador() {return id_administrador;}
    public String getNombre() { return nombre; }
    public String getContrasenia() { return contrasenia; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    //===>> CONVENIOS <<===//
    
    // public static double getPlusConvenioPorTipo(YearMonth mes, PlusConvenio.TipoPlus tipo) {
    // Convenio convenio = getConvenioVigente(mes.atDay(1));
    // if (convenio == null) return 0.0;

    // return convenio.getPlusesPorTipo(tipo).stream()
    //     .filter(p -> p.getSeCobraEnMes()[mes.getMonthValue() - 1])
    //     .mapToDouble(PlusConvenio::getCantidadAnual)
    //     .sum();
    // }




}
