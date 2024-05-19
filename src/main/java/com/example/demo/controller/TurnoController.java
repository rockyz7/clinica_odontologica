package com.example.demo.controller;

import com.example.demo.Model.Turno;
import com.example.demo.Service.OdontologoService;
import com.example.demo.Service.PacienteService;
import com.example.demo.Service.TurnoService;
import com.example.demo.dto.TurnoDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable("id") Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turno) {

        ResponseEntity<Turno> respuesta;

        if (turnoService.buscarTurno(turno.getId()).isPresent()) {
            if (pacienteService.buscarPaciente(turno.getPacienteId()).isPresent() && odontologoService.buscarOdontologo(turno.getOdontologoId()).isPresent()) {
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id: " + turno.getId());
            } else {
                return ResponseEntity.badRequest().body("El paciente u odontólogo no existen en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se puede actualizar un turno que no exista en la base de datos");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable("id") Long id) throws ResourceNotFoundException {

        turnoService.eliminarTurno(id);
        return ResponseEntity.ok().body("Se eliminó el turno con id: " + id);
    }


    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException {

        return ResponseEntity.ok(turnoService.guardarTurno(turno));

    }
}
