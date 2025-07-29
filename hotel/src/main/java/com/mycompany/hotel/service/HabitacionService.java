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
/**
 * Contiene la lógica de negocio relacionada con habitaciones.
 * Se encarga de convertir entre entidades 'Habitacion' y DTOs (HabitacionDTO),
 * e interactúa con la capa de persistencia {HabitacionDAO}.
 * @author mauro
 */
public class HabitacionService {

    private HabitacionDAO habitacionDAO;
    private HabitacionMapper habitacionMapper;
    
/**
 * Constructor que inicializa el DAO y el Mapper.
 */
    public HabitacionService() {
        this.habitacionDAO = HabitacionDAO.getInstancia();
        this.habitacionMapper = new HabitacionMapper();
    }
    
/**
 * rea una nueva habitación en la base de datos.
 * @param dto : Datos de la habitación.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */    
    public void crearHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.crear(habitacion);
    }

/**
 * Actualiza los datos de una habitación.
 * @param dto : DTO con los datos actualizados.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */    
    public void actualizarHabitacionSola(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.actualizar(habitacion, 0);
    }

/**
 * Borra una habitación en base al DTO recibido.
 * @param dto : Datos de la habitación a eliminar.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */    
    public void borrarHabitacion(HabitacionDTO dto) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.borrar(habitacion);
    }

/**
 * Borra una habitación por su ID.
 * @param id : ID de la habitación a borrar.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */
    public void borrarPorId(int id) throws SQLException {
        habitacionDAO.borrar(id);
    }

/**
* Actualiza una habitación identificada por su ID.
* @param dto :  Datos actualizados.
* @param id : ID de la habitación a actualizar.
* @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
*/
    public void actualizarHabitacion(HabitacionDTO dto, int id) throws SQLException {
        Habitacion habitacion = habitacionMapper.toEntity(dto);
        habitacionDAO.actualizar(habitacion, id);
    }

/**
 *  Recupera una habitación por su ID.
 * @param id : ID de la habitación.
 * @return : Retorna el DTO de la habitación recuperada.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */    
    public HabitacionDTO recuperarPorId(int id) throws SQLException {
        Habitacion habitacion = habitacionDAO.recuperarPorId(id);
        HabitacionDTO habitacionDTO = habitacionMapper.toDTO(habitacion);
        return habitacionDTO;
    }

/**
 * Recupera todas las habitaciones registradas.
 * @return :  Retorna una Lista de habitaciones como DTO.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */
    public List<HabitacionDTO> recuperarTodos() throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.recuperarTodos();
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();

        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }

/**
 * Busca habitaciones por número.
 * @param numero : Número de la habitación.
 * @return : Retorna una Lista de habitaciones coincidentes.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */    
    public List<HabitacionDTO> buscarPorNumero(String numero) throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.buscarPorNumero(numero);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }
    
/**
 *  Busca habitaciones por precio.
 * @param precio : Precio como parametro.
 * @return : Retorna una Lista de habitaciones coincidentes.
 * @throws SQLException :  Utilizada si ocurre un error al consultar en la base de datos.
 */
    public List<HabitacionDTO> buscarPorPrecio(String precio) throws SQLException {
        List<Habitacion> habitaciones = habitacionDAO.buscarPorPrecio(precio);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
            habitacionesDTO.add(dto);
        }

        return habitacionesDTO;
    }
    
/**
 * Busca habitaciones por capacidad mínima requerida.
 * @param cantidad : cantidad Cantidad mínima de huéspedes.
 * @return :  Retorna una Lista de habitaciones que cumplen con la capacidad.
 */
    public List<HabitacionDTO> buscarPorCapacidad(String cantidad){
        List<Habitacion> habitaciones = habitacionDAO.buscarPorCantidadPasajeros(cantidad);
        List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
        
       for(Habitacion habitacion : habitaciones){
           HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
           habitacionesDTO.add(dto);
       }
       return habitacionesDTO;
    }
    
/**
 * Busca habitaciones disponibles entre dos fechas con capacidad suficiente.
 * @param checkin : Fecha de ingreso.
 * @param checkout : Fecha de salida.
 * @param cantidad :  Cantidad de personas a alojarse.
 * @return : Retorna una Lista de habitaciones disponibles.
 */
    public List<HabitacionDTO> buscarDisponibles(Date checkin, Date checkout, int cantidad) {
     List<Habitacion> habitaciones = habitacionDAO.buscarDisponibles(checkin,checkout,cantidad);
     List<HabitacionDTO> habitacionesDTO = new ArrayList<>();
      
     for(Habitacion habitacion : habitaciones){
           HabitacionDTO dto = habitacionMapper.toDTO(habitacion);
           habitacionesDTO.add(dto);
       }
       return habitacionesDTO;
    }
    
/**
 *  Obtiene el número de habitación según su ID.
 * @param idHabitacion : ID de la habitación.
 * @return : Retorna el número de habitación como cadena.
 * @throws SQLException : Utilizada si ocurre un error al consultar en la base de datos.
 */
     public String obtenerNumeroHabitacionPorId(int idHabitacion) throws SQLException{
         return habitacionDAO.obtenerNumeroHabitacionPorId(idHabitacion);
     }

}
