package com.example.demo.Service;

import com.example.demo.Model.Odontologo;
import com.example.demo.Model.Paciente;
import com.example.demo.Model.Turno;
import com.example.demo.dto.TurnoDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OdontologoRepository;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.turnoRepository = turnoRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    private static final Logger logger = Logger.getLogger(TurnoService.class);


    public TurnoDTO guardarTurno (TurnoDTO turno) throws BadRequestException {

        if (pacienteRepository.findById(turno.getPacienteId()).isPresent() && odontologoRepository.findById(turno.getOdontologoId()).isPresent()){
            logger.info("Se registró un turno para la fecha " + turno.getFecha());
            Turno turnoAGuardar = turnoDTOaTurno(turno);
            Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
            return turnoATurnoDTO(turnoGuardado);
        } else {
            throw new BadRequestException("No se puede registrar el turno ya que el paciente u odontólogo no existe en la base de datos");
        }


    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            turnoRepository.deleteById(id);
            logger.warn("Se eliminó un turno con id: " + id);
        } else {
            throw new ResourceNotFoundException("El turno a eliminar no existe en la base de datos");
        }
    }


    public void actualizarTurno (TurnoDTO turno){
        logger.info("Se inició la actualización del turno con id: " + turno.getId());
        Turno turnoActualizar = turnoDTOaTurno(turno);
        turnoRepository.save(turnoActualizar);
    }

    public Optional<TurnoDTO> buscarTurno (Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            logger.info("Se inició la búsqueda del turno con id: " + id);
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        } else {
            logger.error("No se encontró un turno con id: " + id);
            return Optional.empty();
        }
    }

    public List<TurnoDTO> buscarTodosTurnos(){
        logger.info("Se inició la búsqueda de los turnos");
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> respuesta = new ArrayList<>();
        for (Turno turno : turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(turno));
        }
        return respuesta;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta = new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }

    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        return turno;
    }


}

