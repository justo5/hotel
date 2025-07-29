
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.HabitacionDTO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.repository.HabitacionDAO;
import com.mycompany.hotel.service.HabitacionService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author justcode
 */
/**
 * Controlador de habitaciones del sistema hotelero.
 * Encapsula la lógica de manejo entre la capa de presentación (por ejemplo, vistas o interfaces gráficas)
 * Implementa la interfaz 'Icrud' para asegurar operaciones comunes sobre objetos 'HabitacionDTO'.
 * Permite crear, actualizar, borrar y consultar habitaciones, así como realizar búsquedas
 * por número, precio, capacidad y disponibilidad. 
 */
public class HabitacionController implements Icrud<HabitacionDTO> {
    private HabitacionService habitacionService;
    
/**
 *  Constructor por defecto que inicializa el servicio de habitaciones.
 */
    public HabitacionController(){
        this.habitacionService = new HabitacionService();
    }
/**
 * Crea una nueva habitación en el sistema.
 * @param dato : El DTO de la habitación a crear.
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */    
    
    @Override
    public void crear(HabitacionDTO dato) throws SQLException {
        habitacionService.crearHabitacion(dato);
    }
    
    
/**
 * Actualiza una habitación existente por ID.
 * @param dato : DTO con los datos nuevos.
 * @param id : Identificador de la habitación a actualizar.
 * @throws SQLException :  Utilizado si ocurre un error de base de datos.
 */
    @Override
    public void actualizar(HabitacionDTO dato, int id) throws SQLException {
       habitacionService.actualizarHabitacion(dato, id);
    }

/**
 * Borra una habitación utilizando su DTO.
 * @param dato : DTO de la habitación a eliminar.
 * @throws SQLException :  Utilizado si ocurre un error de base de datos.
 */    
    @Override
    public void borrar(HabitacionDTO dato) throws SQLException {
       habitacionService.borrarHabitacion(dato);    
    }
    
/**
 * Borra una habitación por su ID.
 * @param id : Identificador de la habitación.
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */    
    @Override
    public void borrar(int id) throws SQLException {
        habitacionService.borrarPorId(id);
    }
    
/**
 * Recupera una habitación específica por su ID.
 * @param id : Identificador de la habitación.
 * @return : Retorna DTO de la habitación.
 * @throws SQLException : Utilizado si ocurre un error de base de datos
 */    
    @Override
    public HabitacionDTO recuperarPorId(int id) throws SQLException {
       return habitacionService.recuperarPorId(id);
    }
    
/**
 * Recupera todas las habitaciones registradas.
 * @return : Retorna una lista de habitaciones en formato DTO
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */
    @Override
    public List<HabitacionDTO> recuperarTodos() throws SQLException {
        return habitacionService.recuperarTodos();
        
    }
    
/**
 *  Busca habitaciones cuyo número coincida con el proporcionado.
 * @param numero : Número de la habitación.
 * @return : Retorna lista de habitaciones encontradas.
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */
    public List<HabitacionDTO> buscarPorNumero(String numero) throws SQLException{
         return habitacionService.buscarPorNumero(numero);
    }
     
/**
 * Busca habitaciones cuyo precio coincida con el proporcionado.
 * @param precio : Precio como string.
 * @return : Retorna una Lista de habitaciones encontradas
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */    
    public List<HabitacionDTO> buscarPorPrecio(String precio) throws SQLException{
        return  habitacionService.buscarPorPrecio(precio);
        
    }
    
/**
* Busca habitaciones que coincidan con una capacidad total de personas.
* @param cantidad : Capacidad deseada.
* @return : Retorna una Lista de habitaciones encontradas.
*/
    public List<HabitacionDTO> buscarPorCapacidad(String cantidad){
        return habitacionService.buscarPorCapacidad(cantidad);
        
    }
    
/**
 *  Busca habitaciones disponibles entre dos fechas y con capacidad mínima requerida.
 * @param checkin : Fecha de entrada.
 * @param checkout : Fecha de salida.
 * @param cantidad : Capacidad mínima requerida.
 * @return  : Retorna una Lista de habitaciones disponibles.
 */
    public List<HabitacionDTO> buscarDisponibles(Date checkin, Date checkout, int cantidad) {
          return habitacionService.buscarDisponibles(checkin, checkout, cantidad);
        
    }
   
/**
 * Calcula la capacidad total de una habitación
 * @param h : DTO de la habitación.
 * @return : Retrona la capacidad total en personas.
 */
    public int calcularCapacidad(HabitacionDTO h) {
        int dobles = h.getCamasDobles();
        int simples = h.getCamasSimples();
        return (dobles * 2) + simples;
    }
    
/**
 * Obtiene el número de habitación a partir de su ID.
 * @param idHabitacion : Identificador de la habitación
 * @return : Retorna el número de habitación como string
 * @throws SQLException : Utilizado si ocurre un error de base de datos.
 */    
    public String obtenerNumeroHabitacionPorId(int idHabitacion) throws SQLException{
        return habitacionService.obtenerNumeroHabitacionPorId(idHabitacion);
    }

}
