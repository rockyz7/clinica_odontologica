package com.example.demo;

import com.example.demo.Model.Domicilio;
import com.example.demo.Model.Odontologo;
import com.example.demo.Model.Paciente;
import com.example.demo.Service.OdontologoService;
import com.example.demo.Service.PacienteService;
import com.example.demo.Service.TurnoService;
import com.example.demo.dto.TurnoDTO;
import com.example.demo.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoInicial() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Calle Boulevard",23,"Cantabria","Belvedere");
        Paciente paciente = new Paciente("Charlie","Montero","5435345",
                LocalDate.of(2022,12,11),"prueba@gmail.com", domicilio);
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        Odontologo odontologo = new Odontologo(1,"Rocky","Montero");
        Odontologo odontologoGuardado = odontologoService.registrarOdontologo(odontologo);
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(LocalDate.of(2022,12, 7));
        turnoDTO.setPacienteId(pacienteGuardado.getId());
        turnoDTO.setOdontologoId(odontologoGuardado.getId());
        turnoService.guardarTurno(turnoDTO);
    }

    @Test
    public void listadoTurnoTest() throws Exception {
        cargarTurnoInicial();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders
                        .get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }

}
