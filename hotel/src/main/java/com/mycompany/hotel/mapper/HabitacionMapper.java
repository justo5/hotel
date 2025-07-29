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
/**
 * Clase que implementa la conversión entre entidades y sus objetos de transferencia de datos. 
 * se encarga de transformar el objeto que 'Habitacion' que viene de la base de datos, a otro tipo
 * 'HabitacionDTO' que se usa para mostrar o transferir datos a otras capas o al usuario). 
 * Utiliza el patrón Mapper para separar la lógica de transformación entre capas.
 *  
 */
public class HabitacionMapper implements IMapper<Habitacion,HabitacionDTO>{

/**
 * Convierte una entidad 'Habitación' en su correspondiente  'HabitacionDTO'.
 * @param habitacion : la entidad de tipo Habitacion a convertir
 * @return : Retorna l DTO correspondiente con los datos de la entidad
 */
    @Override
    public HabitacionDTO toDTO(Habitacion habitacion) {

        return new HabitacionDTO(habitacion.getId(), habitacion.getNumero(), habitacion.getCamasSimples(), habitacion.getCamasDobles(), habitacion.getPrecioPorNoche());
    }
    
/**
 * Convierte un objeto 'HabitacionDTO' en su correspondiente entidad 'Habitacion'.
 * @param dto : el DTO a convertir
 * @return : Retorna la entidad correspondiente con los datos del DTO
 */
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
