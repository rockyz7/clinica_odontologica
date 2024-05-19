package com.example.demo.Service;


import com.example.demo.Model.Odontologo;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {


    private OdontologoRepository odontologoRepository;
    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    @Autowired

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo (Odontologo odontologo){
        logger.info("Se inició el guardado de un odontólogo con apellido: " + odontologo.getApellido());
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoAEliminar = buscarOdontologo(id);
        if (odontologoAEliminar.isPresent()) {
            odontologoRepository.deleteById(id);
            logger.warn("Se inició la eliminación del odontólogo con id: " + id);
        } else {
            throw new ResourceNotFoundException("El odontólogo a eliminar no existe en la base de datos");
        }
    }


    public void actualizarOdontologo (Odontologo odontologo){
        logger.info("Se inició la actualización del odontólogo con id: " + odontologo.getId());
        odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologo (Long id){
        logger.info("Se inició la búsqueda del odontólogo con id: " + id);
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodosOdontologos(){
        logger.info("Se están listando los odontólogos");
        return odontologoRepository.findAll();
    }


}
