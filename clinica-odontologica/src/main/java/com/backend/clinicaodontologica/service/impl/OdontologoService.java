package com.backend.clinicaodontologica.service.impl;



import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;


    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        Odontologo odGuardado = odontologoRepository.save(dtoEntradaAEntidad(odontologo));
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odGuardado, OdontologoSalidaDto.class);
        LOGGER.info("Odontologo guardado: {}", odontologoSalidaDto);
        return odontologoSalidaDto;
    }

    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;
        if (odontologoBuscado != null) {
            odontologoSalidaDto = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", odontologoSalidaDto);
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoSalidaDto;
    }

    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OdontologoSalidaDto.class)).toList();

        LOGGER.info("Listado de todos los odontologos: {}", odontologos);

        return odontologos;
    }

    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }

    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException {
        Odontologo odontologoRecibido = modelMapper.map(odontologoModificacionEntradaDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologoModificacionEntradaDto.getId()).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoAActualizar != null) {

            odontologoAActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoSalidaDto.class);

            LOGGER.warn("Odontologo actualizado: {}", odontologoSalidaDto);

        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }


        return odontologoSalidaDto;
    }

    private Odontologo dtoEntradaAEntidad(OdontologoEntradaDto odontologoEntradaDto) {
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }

}
