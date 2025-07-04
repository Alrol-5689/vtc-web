package com.vtc.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "conductor")
public class Conductor {
	
    //===>> ATRIBUTOS <<===//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_conductor;

    @OneToMany(mappedBy = "conductor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratos;
    
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;
    private String nick;
    private String password;

    //===>> CONSTRUCTORES <<===//

    public Conductor() {
        this.contratos = new ArrayList<>();
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

    public Long getId_conductor() { return id_conductor; }
    public void setId_conductor(Long id) { this.id_conductor = id;}

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<DiaConductor> getDiasTrabajados() {
    List<DiaConductor> d = new ArrayList<>();
    for (Contrato c : contratos) 
        if (c.getDiasTrabajados() != null) d.addAll(c.getDiasTrabajados());
    d.sort(Comparator.comparing(DiaConductor::getDia));
    return d;
}

}


