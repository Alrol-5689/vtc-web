package com.vtc.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "conductor")
public class Driver {
	
    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long idConductor;

    @Column(name = "nik", nullable = false, unique = true)
    private String nick;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "dni", nullable = false, unique = true)
    private String dni;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "apellido1", nullable = true)
    private String apellido1;
    
    @Column(name = "apellido2", nullable = true)
    private String apellido2;
    
    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    // "conductor" es el nombre del ATRIBUTO en la clase Contrato, no el nombre de esta tabla ni el @Column(name = "conductor_id") del atributo conductor.
    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Contrato> contratos;

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriverDay> diasTrabajados;

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nomina> nominas;

    //===>> CONSTRUCTORES <<===//

    public Driver() {
        this.contratos = new ArrayList<>();
        this.diasTrabajados = new ArrayList<>();
    }

    //===>> Getters y setters

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() {return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) {this.apellido2 = apellido2; }

    public String getTelefono() { return telefono;}
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}

    public String getNick() { return nick;}
    public void setNick(String nick) { this.nick = nick;}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni;}

    public Long getIdConductor() { return idConductor; }
    public void setIdConductor(Long id) { this.idConductor = id;}

    public List<Contrato> getContratos() {return contratos;}
    public void setContratos(List<Contrato> contratos) {this.contratos = contratos;}

    public Contrato getContratoVigente(LocalDate fecha) {
        return contratos.stream()
            .filter(c -> !c.getFechaInicio().isAfter(fecha))
            .max((c1, c2) -> c1.getFechaInicio().compareTo(c2.getFechaInicio()))
            .orElse(null);
        }
        
    public List<DriverDay> getDiasTrabajados() {return diasTrabajados;}
    public void setDiasTrabajados(List<DriverDay> diasTrabajados) {this.diasTrabajados = diasTrabajados;}
        
    public List<Nomina> getNominas() {return nominas;}
    public void setNominas(List<Nomina> nominas) {this.nominas = nominas;}

}


