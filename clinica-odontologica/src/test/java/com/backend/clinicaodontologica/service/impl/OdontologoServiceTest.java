package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoDeNombreGeronimoYRetornarElId(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("No tiene matricula","Geronimo","Benavidez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Geronimo", odontologoSalidaDto.getNombre());
    }
    @Test
    @Order(2)
    void deberiaCambiarleLaMatriculaAlOdontologoDelTestAnterior() throws ResourceNotFoundException {
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto(1L,"3333BCGD28002900","Geronimo","Benavidez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto);
        assertNotNull(odontologoSalidaDto.getMatricula());
        assertEquals("3333BCGD28002900",odontologoSalidaDto.getMatricula());

    }
    @Test
    @Order(3)
    void deberiaBorrarAlOdontologoModificadoEnElMetodoAnterior(){
        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        assertNull(odontologoService.buscarOdontologoPorId(1l));
    }

}
