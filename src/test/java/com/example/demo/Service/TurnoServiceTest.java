package com.example.demo.Service;

import com.example.demo.Model.Domicilio;
import com.example.demo.Model.Odontologo;
import com.example.demo.Model.Paciente;
import com.example.demo.dto.TurnoDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    public void cargarDatos() {
        Paciente paciente = new Paciente("Montero", "Rocky", "234", LocalDate.of(2022, 11, 11), "rocky@gmail.com", new Domicilio("calle", 2, "Aguada", "Hobbiton"));
        Odontologo odontologo = new Odontologo(123, "Rocky", "Montero");
        pacienteService.guardarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
    }

    @Test
    @Order(1)
    public void registrarTurnoTest() throws BadRequestException {
        cargarDatos();
        TurnoDTO turno = new TurnoDTO(LocalDate.of(2022, 11, 11), 1L, 1L);
        turnoService.guardarTurno(turno);
        assertNotNull(turnoService.buscarTurno(1L));

    }

    @Test
    @Order(2)
    public void buscarTurnos(){
        List<TurnoDTO> turnos = turnoService.buscarTodosTurnos();
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, turnos.size());
    }

    @Test
    @Order(3)
    public void buscarTurnoPorId(){
        Long id = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        TurnoDTO turnoAActualizar = new TurnoDTO(1L, LocalDate.of(2022, 12, 11), 1L, 1L);
        turnoService.actualizarTurno(turnoAActualizar);
        Optional<TurnoDTO> turnoActualizado = turnoService.buscarTurno(turnoAActualizar.getId());
        assertEquals(LocalDate.of(2022, 12, 11), turnoActualizado.get().getFecha());
    }

    @Test
    @Order(5)
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);
        assertFalse(turnoEliminado.isPresent());
    }

}