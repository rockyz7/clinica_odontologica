package com.example.demo.Service;


import com.example.demo.Model.Domicilio;
import com.example.demo.Model.Paciente;
import com.example.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("Montero", "Rocky", "2324", LocalDate.of(2022, 12, 11), "hola@gmail.com",
                new Domicilio("Calle", 13, "Aguada", "Santa Fe"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes = pacienteService.buscarTodosPacientes();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, pacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar = new Paciente(1L,"Montero","Juan"
                ,"243535", LocalDate.of(2022,10,18),
                new Domicilio(1L,"Calle Corrales",548,"Salta","Salta"), "prueba@gmail.com");

        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado= pacienteService.buscarPaciente(pacienteAActualizar.getId());

        assertEquals("Juan",pacienteActualizado.get().getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);
        assertFalse(pacienteEliminado.isPresent());
    }

}