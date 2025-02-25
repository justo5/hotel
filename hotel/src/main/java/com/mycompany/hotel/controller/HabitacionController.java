package com.mycompany.hotel.controller;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.mapper.HabitacionMapper;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.service.HabitacionService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author justcode
 */
public class HabitacionController implements Icrud<Habitacion> {

    private HabitacionService habitacionService;

    public HabitacionController() {
        this.habitacionService = new HabitacionService(); // Inicializa el servicio
    }

    // Método para obtener una habitación por su ID y devolverla como DTO
    public HabitacionDTO obtenerHabitacion(int id) {
        try {
            return habitacionService.recuperarPorId(id); // Usa el servicio para recuperar la habitación
        } catch (SQLException ex) {
            System.out.println("Error al obtener la habitación: " + ex);
            return null;
        }
    }

    // Método para guardar una habitación a partir de un DTO
    public void guardarHabitacion(HabitacionDTO dto) {
        try {
            habitacionService.crearHabitacion(dto); // Usa el servicio para crear la habitación
        } catch (SQLException ex) {
            System.out.println("Error al guardar la habitación: " + ex);
        }
    }

    @Override
    public void crear(Habitacion dato) {
        try {
            HabitacionDTO dto = HabitacionMapper.toDTO(dato); // Convierte la entidad a DTO
            habitacionService.crearHabitacion(dto); // Usa el servicio para crear la habitación
        } catch (SQLException ex) {
            System.out.println("No pudo ser creada: " + ex);
        }
    }

    // Método para crear una habitación con parámetros específicos
    public void crear(int numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) throws SQLException {
        HabitacionDTO dto = new HabitacionDTO();
        dto.setNumero(numero); // Convierte el número de String a int
        dto.setCamasSimples(camasSimples);
        dto.setCamasDobles(camasDobles);
        dto.setPrecioPorNoche(precioPorNoche);
        habitacionService.crearHabitacion(dto); // Usa el servicio para crear la habitación
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        try {
            HabitacionDTO dto = HabitacionMapper.toDTO(dato); // Convierte la entidad a DTO
            habitacionService.actualizarHabitacion(dto, id); // Usa el servicio para actualizar la habitación
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la habitación: " + ex);
        }
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
        try {
            HabitacionDTO dto = HabitacionMapper.toDTO(dato); // Convierte la entidad a DTO
            habitacionService.borrarHabitacion(dto); // Usa el servicio para borrar la habitación
        } catch (SQLException ex) {
            System.out.println("Error al borrar la habitación: " + ex);
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        try {
            habitacionService.borrarPorId(id); // Usa el servicio para borrar la habitación por ID
        } catch (SQLException ex) {
            System.out.println("Error al borrar la habitación: " + ex);
        }
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        try {
            HabitacionDTO dto = habitacionService.recuperarPorId(id); // Usa el servicio para recuperar la habitación
            return HabitacionMapper.toEntity(dto); // Convierte el DTO a entidad
        } catch (SQLException ex) {
            System.out.println("Error al recuperar la habitación: " + ex);
            return null;
        }
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
        try {
            List<HabitacionDTO> dtos = habitacionService.recuperarTodos(); // Usa el servicio para recuperar todas las habitaciones
            List<Habitacion> habitaciones = new ArrayList<>();

            // Convierte cada DTO a entidad
            for (HabitacionDTO dto : dtos) {
                habitaciones.add(HabitacionMapper.toEntity(dto));
            }

            return habitaciones;
        } catch (SQLException ex) {
            System.out.println("Error al recuperar todas las habitaciones: " + ex);
            return null;
        }
    }
}