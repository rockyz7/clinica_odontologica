package com.example.demo.controller;

import com.example.demo.Model.Paciente;
import com.example.demo.Service.PacienteService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado.isPresent() ) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizó el paciente con apellido " + paciente.getApellido());
        }
        else {
            return ResponseEntity.badRequest().body("El paciente con id " + paciente.getId() + " no existe en la base de datos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente (@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new ResourceNotFoundException("No se encuentra un paciente con id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente (@PathVariable("id") Long id) throws ResourceNotFoundException {

            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se eliminó el paciente con id: " + id);

    }


    @GetMapping("")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    }

}