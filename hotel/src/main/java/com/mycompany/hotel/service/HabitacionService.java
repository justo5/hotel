/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.mapper.HabitacionMapper;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.repository.HabitacionDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mi pc
 */
public class HabitacionService {

    private HabitacionDAO habitacionDAO;
    private HabitacionMapper habitacionMapper;

    public HabitacionService() {
        this.habitacionDAO = HabitacionDAO.getInstancia();
        this.habitacionMapper = new HabitacionMapper();
    }

    public void crearHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.crear(habitacion);
    }

    public void actualizarHabitacionSola(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.actualizar(habitacion, 0);
    }

    public void borrarHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.borrar(habitacion);
    }

    public void borrarPorId(int id) throws SQLException {
        habitacionDAO.borrar(id);
    }

    public void actualizarHabitacion(HabitacionDTO dto, int id) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.actualizar(habitacion, id);
    }

    public HabitacionDTO recuperarPorId(int id) throws SQLException {
        Habitacion habitacion = habitacionDAO.recuperarPorId(id);
        HabitacionDTO habitacionDTO = habitacionMapper.toDTO(habitacion);
        return habitacionDTO;
    }

    public List<HabitacionDTO> recuperarTodos() throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.recuperarTodos();
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }

    public List<HabitacionDTO> buscarPorNumero(String numero) throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.buscarPorNumero(numero);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }
    
    public List<HabitacionDTO> buscarPorPrecio(String precio) throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.buscarPorPrecio(precio);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }
    
    public List<HabitacionDTO> buscarPorCapacidad(String cantidad){
        List<Habitacion> habitaciones = habitacionDAO.buscarPorCantidadPasajeros(cantidad);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        
       for(Habitacion habitacion : habitaciones){
           HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
           habitacionesDTO.add(dto);
       }
       return habitacionesDTO;
    }
    
    public List<HabitacionDTO> buscarDisponibles(Date checkin, Date checkout, int cantidad) {
     List<Habitacion> habitaciones = habitacionDAO.buscarDisponibles(checkin,checkout,cantidad);
     List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
      
     for(Habitacion habitacion : habitaciones){
           HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
           habitacionesDTO.add(dto);
       }
       return habitacionesDTO;
    }

}
