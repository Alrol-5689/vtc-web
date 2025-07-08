package com.vtc.modelo;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "contrato_anejo", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_contrato", "fecha_inicio"})}
    )
public class ContratoAnejo {

    //===>> ATRIBUTOS <<===//

    @Id //===>> PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // en MariaDB ha de ser auto_increment
    private Long id_anejo_contrato;

    @ManyToOne(optional = false) //===>> No puede hacer anejo sin contrato
    @JoinColumn(name = "id_contrato", nullable = false) //===>> La FK se llama id_contrato ===>> No podrá ser null
    private Contrato contrato;

    @Column(name = "fecha_inicio", nullable = false) //===>> La fecha de inicio no puede ser nula
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "tareas_aux")
    private Duration tareasAux; //===>> DurationToMinutesConverter lo convierte a minutos (Long) en la BD

    @Column(name = "salario_anual")
    private Double salarioAnual;

    @Column(name = "lunes")
    private Duration lunes;

    @Column(name = "martes")
    private Duration martes;

    @Column(name = "miercoles")
    private Duration miercoles;

    @Column(name = "jueves")
    private Duration jueves;

    @Column(name = "viernes")
    private Duration viernes;

    @Column(name = "sabado")
    private Duration sabado;

    @Column(name = "domingo")
    private Duration domingo;

    @OneToMany(mappedBy = "contratoAnejo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PoliticaComision> politicasComision; //===>> Políticas de comisión asociadas a este anejo

    //private final Map<DayOfWeek, Duration> jornada;

    //===>> CONSTRUCTORES <<===//
    
    public ContratoAnejo() {}
    
    //===>> GETTERS <<===//
    
    public Long getId_anejo_contrato() {return id_anejo_contrato;}
    public Contrato getContrato() {return contrato;}
    public LocalDate getFechaInicio() {return fechaInicio;}
    public LocalDate getFechaFin() {return fechaFin;}
    public Duration getTareasAux() {return tareasAux;}
    public Double getSalarioAnual() {return salarioAnual;}
    public List<PoliticaComision> getPoliticasComision() {return politicasComision;}
    //public Map<DayOfWeek, Duration> getJornada() {return jornada;}
    public Map<DayOfWeek, Duration> getJornada_map() {
        Map<DayOfWeek, Duration> jornada = new EnumMap<>(DayOfWeek.class);
        jornada.put(DayOfWeek.MONDAY, this.lunes);
        jornada.put(DayOfWeek.TUESDAY, this.martes);
        jornada.put(DayOfWeek.WEDNESDAY, this.miercoles);
        jornada.put(DayOfWeek.THURSDAY, this.jueves);
        jornada.put(DayOfWeek.FRIDAY, this.viernes);
        jornada.put(DayOfWeek.SATURDAY, this.sabado);
        jornada.put(DayOfWeek.SUNDAY, this.domingo);
        return jornada;
    }

    public Duration getJornada_dur() {
        return getJornada_map().values().stream().filter(d -> d != null).reduce(Duration.ZERO, Duration::plus);
    }
    public Duration getJornadaDia(DayOfWeek dia) {
        return switch (dia) {
            case MONDAY -> lunes;
            case TUESDAY -> martes;
            case WEDNESDAY -> miercoles;
            case THURSDAY -> jueves;
            case FRIDAY -> viernes;
            case SATURDAY -> sabado;
            case SUNDAY -> domingo;
        };
    }
    
    //===>> SETTERS <<===//
    
    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}
    public void setPoliticasComision(List<PoliticaComision> politicaComision) {this.politicasComision = politicaComision;}

    public void setJornada(Map<DayOfWeek, Duration> jornada) {
        if (jornada == null) throw new IllegalArgumentException("La jornada no puede ser nula.");
        if (lunes != null || martes != null || miercoles != null || jueves != null || viernes != null || sabado != null || domingo != null) 
            throw new UnsupportedOperationException("La jornada ya ha sido establecida y no se puede modificar.");
        for (DayOfWeek dia : DayOfWeek.values()) {
            Duration duracion = jornada.getOrDefault(dia, Duration.ZERO);
            if (duracion.isNegative()) throw new IllegalArgumentException(
                "La duración para " + dia + " no puede ser negativa.");
            if (duracion.toHours() > 12) throw new IllegalArgumentException(
                "La duración para " + dia + " supera el límite permitido (12h).");
        }
        this.lunes = jornada.getOrDefault(DayOfWeek.MONDAY, Duration.ZERO);
        this.martes = jornada.getOrDefault(DayOfWeek.TUESDAY, Duration.ZERO);
        this.miercoles = jornada.getOrDefault(DayOfWeek.WEDNESDAY, Duration.ZERO);
        this.jueves = jornada.getOrDefault(DayOfWeek.THURSDAY, Duration.ZERO);
        this.viernes = jornada.getOrDefault(DayOfWeek.FRIDAY, Duration.ZERO);
        this.sabado = jornada.getOrDefault(DayOfWeek.SATURDAY, Duration.ZERO);
        this.domingo = jornada.getOrDefault(DayOfWeek.SUNDAY, Duration.ZERO);
    }

    // public void setJornada(Map<DayOfWeek, Duration> jornada) {
    //     if (jornada == null) throw new IllegalArgumentException("La jornada no puede ser nula.");
    //     for (DayOfWeek dia : DayOfWeek.values()) {
    //         Duration duracion = jornada.getOrDefault(dia, Duration.ZERO);
    //         if (duracion.isNegative()) throw new IllegalArgumentException(
    //             "La duración para " + dia + " no puede ser negativa.");
    //         if (duracion.toHours() > 12) throw new IllegalArgumentException(
    //             "La duración para " + dia + " supera el límite permitido (12h).");
    //     }
    //     this.jornada.clear();
    //     this.jornada.putAll(jornada);
    // }

    public void setSalarioAnual(Double salarioAnual) {
        if (this.salarioAnual != null) throw new UnsupportedOperationException(
            "El salario anual ya está definido y no se puede modificar.");
        if (salarioAnual == null || salarioAnual < 0) throw new IllegalArgumentException(
            "El salario anual no puede ser nulo ni negativo.");
        if (fechaInicio == null) throw new IllegalStateException(
            "Debe establecerse la fecha de inicio antes del salario.");
        LocalDate inicio = fechaInicio;
        LocalDate fin = (this.fechaFin != null) ? this.fechaFin : LocalDate.now().plusYears(2); // límite razonable para validar
        LocalDate actual = inicio;
        while (!actual.isAfter(fin)) {
            ConvenioAnejo anexo = Administrador.getConvenioVigente(actual).getAnexoVigente(actual);
            //ConvenioAnejo anexo = Administrador.getAnexoVigente(actual);
            if (anexo != null && anexo.getSalarioAnual() != null && salarioAnual < anexo.getSalarioAnual()) 
                throw new IllegalArgumentException("El salario anual no puede ser inferior al salario del convenio vigente en " + actual);
            actual = actual.plusMonths(1);
        }
        this.salarioAnual = salarioAnual;
    }

    public void setTareasAux(Duration tareasAux) {
        if (this.tareasAux != null) throw new UnsupportedOperationException(
            "Las tareas auxiliares ya están definidas y no se pueden modificar.");
        if (tareasAux == null || tareasAux.isNegative() || tareasAux.isZero())
            throw new IllegalArgumentException("Duración de tareas auxiliares no válida.");
        LocalDate inicio = this.fechaInicio;
        LocalDate fin = (this.fechaFin != null) ? this.fechaFin : LocalDate.now().plusYears(2); // rango razonable
        LocalDate actual = inicio;
        while (!actual.isAfter(fin)) {
            ConvenioAnejo anexo = Administrador.getConvenioVigente(actual).getAnexoVigente(actual);
            if (anexo != null && anexo.getTareasAux() != null && tareasAux.compareTo(anexo.getTareasAux()) < 0)
                throw new IllegalArgumentException("Las tareas auxiliares deben ser al menos las del convenio vigente en todas las fechas.");
            actual = actual.plusMonths(1);
        }
        this.tareasAux = tareasAux;
    }
    
    public void setContrato(Contrato contrato) {
        if (this.contrato != null) 
            throw new UnsupportedOperationException(
                "El contrato no se puede cambiar una vez asignado.");   
        if (this.contrato == null) this.contrato = contrato;
    }

    //===>> Fecha inicio <<===//

    public void setFechaInicio(LocalDate fechaInicio) {
        if (this.fechaInicio != null) throw new UnsupportedOperationException(
                "La fecha de inicio no se puede cambiar una vez asignada.");
        if (fechaInicio == null) throw new IllegalArgumentException(
                "La fecha de inicio no puede ser nula.");
        if (this.contrato == null) throw new IllegalStateException(
                "Debe establecerse el contrato antes de la fecha de inicio.");
        List<ContratoAnejo> anejos = contrato.getAnejos();
        ContratoAnejo anterior = anejos.stream()
            .filter(a -> a.getContrato().equals(this.contrato))
            .filter(a -> a.getFechaFin() != null)
            .filter(a -> a.getFechaFin().isBefore(fechaInicio))
            .max((a1, a2) -> a1.getFechaFin().compareTo(a2.getFechaFin()))
            .orElse(null);
        if (anterior != null) {
            LocalDate esperado = anterior.getFechaFin().plusDays(1);
            if (!fechaInicio.equals(esperado)) throw new IllegalArgumentException(
                    "La fecha de inicio debe ser el día siguiente a la fecha de fin del anejo anterior.");
        }
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicio_forzarAnterior(LocalDate nuevaFechaInicio) {
        if (this.fechaInicio != null) throw new UnsupportedOperationException(
                "La fecha de inicio no se puede cambiar una vez asignada.");
        if (nuevaFechaInicio == null) throw new IllegalArgumentException(
                "La fecha de inicio no puede ser nula.");
        if (this.contrato == null) throw new IllegalStateException(
                "Debe establecerse el contrato antes de la fecha de inicio.");
        List<ContratoAnejo> anejos = contrato.getAnejos();
        ContratoAnejo anterior = anejos.stream()
            .filter(a -> a.getContrato().equals(this.contrato))
            .filter(a -> a.getFechaInicio().isBefore(nuevaFechaInicio))
            .max((a1, a2) -> a1.getFechaInicio().compareTo(a2.getFechaInicio()))
            .orElse(null);
        if (anterior != null) {
            LocalDate fechaFinEsperada = fechaInicio.minusDays(1);
            if (anterior.getFechaFin() == null || !anterior.getFechaFin().equals(fechaFinEsperada)) 
                anterior.setFechaFin(fechaFinEsperada);
        }
        this.fechaInicio = nuevaFechaInicio;
    }

    //===>> Métodos de utilidad <<===//

    public boolean isTareasAuxContratoAplicable(LocalDate fecha) {
        if (fecha.isBefore(fechaInicio)) return false;
        if (fechaFin != null && fecha.isAfter(fechaFin)) return false;
        if (tareasAux == null || tareasAux.isZero() || tareasAux.isNegative()) return false;
        ConvenioAnejo anexo = Administrador.getConvenioVigente(fecha).getAnexoVigente(fecha);
        if (anexo == null || anexo.getTareasAux() == null) return true;
        return tareasAux.compareTo(anexo.getTareasAux()) >= 0;
    }


}
