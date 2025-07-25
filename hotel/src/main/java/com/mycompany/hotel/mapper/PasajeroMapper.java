/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.interfaz.IMapper;
import com.mycompany.hotel.model.Pasajero;

/**
 * Mapeador que convierte entre entidades Pasajero y DTOs PasajeroDTO.
 * Implementa la interfaz IMapper para proporcionar funcionalidad de conversión bidireccional.
 * 
 * @author mi pc
 */
public class PasajeroMapper implements IMapper<Pasajero, PasajeroDTO> {

    /**
     * Convierte un PasajeroDTO a una entidad Pasajero.
     * 
     * @param dto El objeto DTO a convertir
     * @return La entidad Pasajero resultante
     */
    @Override
    public Pasajero toEntity(PasajeroDTO dto) {
        return new Pasajero(
            dto.getId(), 
            dto.getNombre(), 
            dto.getApellido(), 
            dto.getDNI(), 
            dto.getTelefono(), 
            dto.getEmail()
        );
    }

    /**
     * Convierte una entidad Pasajero a un PasajeroDTO.
     * 
     * @param entity La entidad a convertir
     * @return El DTO PasajeroDTO resultante
     */
    @Override
    public PasajeroDTO toDTO(Pasajero entity) {
        return new PasajeroDTO(
            entity.getId(),
            entity.getNombre(),
            entity.getApellido(),
            entity.getDNI(),
            entity.getTelefono(),
            entity.getEmail()
        );
    }
}