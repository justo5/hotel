package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.models.Habitacion;

import java.math.BigDecimal;

public class HabitacionMapper {

    // Convierte una entidad Habitacion a un DTO HabitacionDTO
    public static HabitacionDTO toDTO(Habitacion habitacion) {
        return new HabitacionDTO(
            habitacion.getId(),
            habitacion.getNumero(), 
            habitacion.getCamasSimples(),
            habitacion.getCamasDobles(),
            habitacion.getPrecioPorNoche() // Convertir float a BigDecimal
        );
    }

    // Convierte un DTO HabitacionDTO a una entidad Habitacion
    public static Habitacion toEntity(HabitacionDTO dto) {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId());
        habitacion.setNumero(dto.getNumero()); // Convertir String a int
        habitacion.setCamasSimples(dto.getCamasSimples());
        habitacion.setCamasDobles(dto.getCamasDobles());
        habitacion.setPrecioPorNoche(dto.getPrecioPorNoche()); // Convertir BigDecimal a float
        return habitacion;
    }
}
