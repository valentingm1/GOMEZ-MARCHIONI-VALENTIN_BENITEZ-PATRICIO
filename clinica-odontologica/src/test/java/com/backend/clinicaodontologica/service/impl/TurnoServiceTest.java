package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoServiceTest(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @BeforeEach
    public void setUp() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Martin", "Perez", 123456789, LocalDate.of(2023, 12, 12), new DomicilioEntradaDto("Lacalle", 1234, "La Vecindad", "Formosa"));
        pacienteService.registrarPaciente(pacienteEntradaDto);
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("21921872176Y", "Momo", "Benavidez");
        odontologoService.registrarOdontologo(odontologoEntradaDto);
    }



    @Test
    @Order(1)
    void deberiaRegistrarTresTurnosConElPacienteQueTieneId1YElOdontologoQueTieneId1YDevolverElIdDelTurno() throws BadRequestException {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L,1L, LocalDateTime.of(2023,12,25,0,0));
        TurnoSalidaDto turnoSalidaDto =  turnoService.registrarTurno(turnoEntradaDto);
        TurnoEntradaDto turnoEntradaDto2 = new TurnoEntradaDto(1L,1L, LocalDateTime.of(2024,1,1,0,0));
        TurnoSalidaDto turnoSalidaDto2 =  turnoService.registrarTurno(turnoEntradaDto2);
        TurnoEntradaDto turnoEntradaDto3 = new TurnoEntradaDto(1L,1L, LocalDateTime.of(2024,1,6,15,15));
        TurnoSalidaDto turnoSalidaDto3 =  turnoService.registrarTurno(turnoEntradaDto3);
        assertNotNull(turnoSalidaDto);
        assertEquals(1, turnoSalidaDto.getId());
        assertEquals(2,turnoSalidaDto2.getId());
        assertEquals(3,turnoSalidaDto3.getId());
    }

    @Test
    @Order(2)
    void deberiaBorrarTodosLosTurnosCreadosEnElMetodoAnterior(){
       try {
           turnoService.eliminarTurno(1L);
           turnoService.eliminarTurno(2L);
           turnoService.eliminarTurno(3L);
       }catch (ResourceNotFoundException e) {
           e.printStackTrace();
           throw new RuntimeException(e);
       }
       assertNull(turnoService.buscarTurnoPorId(1L));
       assertNull(turnoService.buscarTurnoPorId(2L));
       assertNull(turnoService.buscarTurnoPorId(3L));

    }

    @Test
    @Order(3)
    void alIntentarMostrarTodosLosTurnosDeberiaDevolverUnaListaVacia(){

        List<TurnoSalidaDto> turnosDto = turnoService.listarTurnos();

        assertTrue(turnosDto.isEmpty());

    }




}