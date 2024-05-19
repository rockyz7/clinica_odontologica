package com.example.demo.Service;

import com.example.demo.Model.Paciente;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    private static final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente guardarPaciente (Paciente paciente){
        logger.info("Se inició el guardado de un paciente con apellido: " + paciente.getApellido());
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAEliminar = buscarPaciente(id);
        if (pacienteAEliminar.isPresent()){
            pacienteRepository.deleteById(id);
            logger.warn("Se inició la eliminación del paciente con id: " + id);
        } else {
            throw new ResourceNotFoundException("El paciente a eliminar no existe en la base de datos");
        }

    }

    public void actualizarPaciente (Paciente paciente){
        logger.info("Se inició la actualización del paciente con id: " + paciente.getId());
        pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente (Long id){
        logger.info("Se inició la búsqueda del paciente con id: " + id);
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodosPacientes(){
        logger.info("Se están listando los pacientes");
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacienteByEmail (String email){
        return pacienteRepository.findByEmail(email);
    }
}