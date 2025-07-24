/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.interfaz.IMapper;
import com.mycompany.hotel.model.Habitacion;

/**
 *
 * @author mi pc
 */
public class HabitacionMapper implements IMapper<Habitacion,HabitacionDTO>{

    @Override
    public HabitacionDTO toDTO(Habitacion habitacion) {

        return new HabitacionDTO(habitacion.getId(), habitacion.getNumero(), habitacion.getCamasSimples(), habitacion.getCamasDobles(), habitacion.getPrecioPorNoche());
    }

    @Override
    public  Habitacion toEntity(HabitacionDTO dto) {
        return new Habitacion(
                dto.getId(),
                dto.getNumero(),
                dto.getCamasSimples(),
                dto.getCamasDobles(),
                dto.getPrecioPorNoche());
    }

}
