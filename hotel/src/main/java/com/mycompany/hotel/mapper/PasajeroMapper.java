/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.model.Pasajero;

/**
 *
 * @author mi pc
 */
public class PasajeroMapper {
    
    public static PasajeroDTO toDTO(Pasajero pasajero){
        
        return new PasajeroDTO(pasajero.getId(),pasajero.getNombre(),
        pasajero.getApellido(),pasajero.getDNI(),pasajero.getTelefono(),pasajero.getEmail());
    }
     public static Pasajero toEntity(PasajeroDTO dto) {
        return new Pasajero(
            dto.getId(), 
            dto.getNombre(), 
            dto.getApellido(), 
            dto.getDNI(), 
            dto.getTelefono(), 
            dto.getEmail()
        );
    }
    
}
