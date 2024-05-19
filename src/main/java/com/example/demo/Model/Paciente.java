package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String apellido;
    @Column
    private String nombre;
    @Column
    private String DNI;
    @Column
    private LocalDate fechaIngreso;
    @Column
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Paciente() {

    }

    public Paciente(String apellido, String nombre, String DNI, LocalDate fechaIngreso, String email, Domicilio domicilio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
        this.domicilio = domicilio;
    }

    public Paciente(Long id, String apellido, String nombre, String DNI, LocalDate fechaIngreso, Domicilio domicilio, String email) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.DNI = DNI;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
}
