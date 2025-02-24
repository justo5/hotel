package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.models.Pasajero;

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
