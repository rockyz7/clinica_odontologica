package com.example.demo.controller;

import com.example.demo.Model.Odontologo;
import com.example.demo.Service.OdontologoService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (odontologoBuscado.isPresent() ) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizó el odontólogo con apellido " + odontologo.getApellido());
        }
        else {
            return ResponseEntity.badRequest().body("El odontólogo con id " + odontologo.getId() + " no existe en la base de datos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo (@PathVariable("id") Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()){
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo (@PathVariable("id") Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se eliminó el odontologo con id: " + id);
    }

    @GetMapping("")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodosOdontologos());
    }



}
