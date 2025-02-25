package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.mapper.HabitacionMapper;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.repository.HabitacionDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionService {

    private final HabitacionDAO habitacionDAO;

    public HabitacionService() {
        this.habitacionDAO = HabitacionDAO.getInstancia();
    }

    // Método para crear una habitación
    public void crearHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = HabitacionMapper.toEntity(dto); // Convierte DTO a entidad
        habitacionDAO.crear(habitacion); // Llama al DAO para crear la habitación
    }

    // Método para borrar una habitación
    public void borrarHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = HabitacionMapper.toEntity(dto); // Convierte DTO a entidad
        habitacionDAO.borrar(habitacion); // Llama al DAO para borrar la habitación
    }

    // Método para borrar una habitación por su ID
    public void borrarPorId(int id) throws SQLException {
        habitacionDAO.borrar(id); // Llama al DAO para borrar la habitación por ID
    }

    // Método para actualizar una habitación
    public void actualizarHabitacion(HabitacionDTO dto, int id) throws SQLException {
        Habitacion habitacion = HabitacionMapper.toEntity(dto); // Convierte DTO a entidad
        habitacionDAO.actualizar(habitacion, id); // Llama al DAO para actualizar la habitación
    }

    // Método para recuperar una habitación por su ID
    public HabitacionDTO recuperarPorId(int id) throws SQLException {
        Habitacion habitacion = habitacionDAO.recuperarPorId(id); // Recupera la entidad desde el DAO
        HabitacionDTO habitacionDTO = HabitacionMapper.toDTO(habitacion); // Convierte entidad a DTO
        return habitacionDTO;
    }

    // Método para recuperar todas las habitaciones
    public List<HabitacionDTO> recuperarTodos() throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.recuperarTodos(); // Recupera todas las entidades desde el DAO
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>(); // Lista para almacenar los DTOs

        // Convierte cada entidad a DTO y la añade a la lista
        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = HabitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO; // Retorna la lista de DTOs
    }
}