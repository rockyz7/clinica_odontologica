package com.example.demo.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long pacienteId;
    private Long odontologoId;

    public TurnoDTO(LocalDate fecha, Long pacienteId, Long odontologoId) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public TurnoDTO() {
    }

    public TurnoDTO(Long id, LocalDate fecha, Long pacienteId, Long odontologoId) {
        this.id = id;
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }
}
