/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.interfaz.IMapper;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.model.Reserva;
import java.sql.Date;

/**
 *
 * @author mi pc
 */
public class ReservaMapper implements IMapper<Reserva,ReservaDTO> {

    @Override
    public ReservaDTO toDTO(Reserva entity) {
        if (entity == null) {
            return null;
        }

            ReservaDTO dto = new ReservaDTO();
            dto.setId(entity.getId());
            dto.setChekin((Date) entity.getChekin());
            dto.setCheckout((Date) entity.getCheckout());
            dto.setSenia(entity.getSenia());

            // Extraer solo los IDs de las entidades relacionadas
            if (entity.getoPasajero() != null)
                dto.setId_pasajero(entity.getoPasajero().getId());

            if (entity.getoHabitacion() != null)
                dto.setId_habitacion(entity.getoHabitacion().getId());

        return dto;
    }

    @Override
    public Reserva toEntity(ReservaDTO dto) {
        if (dto == null) return null;

        Reserva entity = new Reserva();
        entity.setId(dto.getId());
        entity.setChekin(dto.getChekin());
        entity.setCheckout(dto.getCheckout());
        entity.setSenia(dto.getSenia());

        // Crear objetos mínimos con solo el ID (podés cargar el resto después si hace falta)
        Pasajero pasajero = new Pasajero();
        pasajero.setId(dto.getId_pasajero());
        entity.setoPasajero(pasajero);

        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId_habitacion());
        entity.setoHabitacion(habitacion);

        return entity;
    }
    
}
