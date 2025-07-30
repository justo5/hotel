
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.service.ReservaService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador de reservas que actúa como intermediario entre la capa de vista
 * (UI) y la capa de servicio ({@link ReservaService}).
 * 
 * <p>
 * Implementa la interfaz {@link Icrud} para gestionar operaciones CRUD sobre
 * {@link ReservaDTO}. Además, provee métodos de búsqueda específicos.
 * </p>
 * 
 * <p>
 * Este controlador encapsula la llamada a la lógica de negocio y maneja el
 * registro de errores mediante {@link Logger}.
 * </p>
 * 
 * @author Rocio
 */

public class ReservaController implements Icrud<ReservaDTO> {

    
    /** Logger para registrar eventos y errores. */
    private static final Logger logger = Logger.getLogger(ReservaController.class.getName());
    
    /** Servicio que contiene la lógica de negocio para reservas. */
    private ReservaService reservaService;

    
    /**
     * Constructor por defecto que inicializa el servicio de reservas.
     */
    public ReservaController() {
        this.reservaService = new ReservaService();
    }

   
    
    
    /**
     * Crea una nueva reserva.
     *
     * @param dato objeto {@link ReservaDTO} con la información de la reserva.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public void crear(ReservaDTO dato) throws SQLException {
         try {
            reservaService.crearReserva(dato);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear la reserva: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Actualiza una reserva existente por su ID.
     *
     * @param dato objeto {@link ReservaDTO} con los nuevos datos.
     * @param id identificador de la reserva a actualizar.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public void actualizar(ReservaDTO dato, int id) throws SQLException {
        try {
            reservaService.actualizarReserva(dato, id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar la reserva con ID: " + id, e);
            throw e;
        }
    }

    /**
     * Elimina una reserva usando un objeto {@link ReservaDTO}.
     *
     * @param dato reserva a eliminar.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public void borrar(ReservaDTO dato) throws SQLException {
         try {
            reservaService.borrarReserva(dato);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al borrar la reserva: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Elimina una reserva por su ID.
     *
     * @param id identificador de la reserva.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public void borrar(int id) throws SQLException {
       try {
            reservaService.borrarPorId(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al borrar la reserva con ID: " + id, e);
            throw e;
        }
    }

    /**
     * Recupera una reserva por su ID.
     *
     * @param id identificador de la reserva.
     * @return la reserva correspondiente en formato {@link ReservaDTO}.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public ReservaDTO recuperarPorId(int id) throws SQLException {
        try {
            return reservaService.recuperarPorId(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al recuperar la reserva con ID: " + id, e);
            throw e;
        }
    }

     /**
     * Recupera todas las reservas existentes.
     *
     * @return lista de {@link ReservaDTO}.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    @Override
    public List<ReservaDTO> recuperarTodos() throws SQLException {
        try {
            return reservaService.recuperarTodos();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al recuperar todas las reservas", e);
            throw e;
        }
    }
    
    /**
     * Busca reservas filtrando por nombre o apellido del pasajero.
     *
     * @param terminoBusqueda texto de búsqueda.
     * @return lista de reservas que coinciden con el filtro.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public List<ReservaDTO> buscarPasajero(String terminoBusqueda) throws SQLException {
         try {
            return reservaService.buscarPasajero(terminoBusqueda);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por pasajero: " + terminoBusqueda, e);
            throw e;
        }
    }
    
    /**
     * Busca reservas por fecha de check-in.
     *
     * @param valor fecha o parte de la fecha en formato {@code yyyy-MM-dd}.
     * @return lista de reservas que coinciden con el filtro.
     * @throws SQLException si ocurre un error en la base de datos.
     */
     public List<ReservaDTO> buscarPorCheckin(String valor) throws SQLException {
         try {
            return reservaService.buscarPorCheckin(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por check-in: " + valor, e);
            throw e;
        }
     }
     
      /**
     * Busca reservas por fecha de check-out.
     *
     * @param valor fecha o parte de la fecha en formato {@code yyyy-MM-dd}.
     * @return lista de reservas que coinciden con el filtro.
     * @throws SQLException si ocurre un error en la base de datos.
     */
      public List<ReservaDTO> buscarPorCheckout(String valor) throws SQLException {
          try {
            return reservaService.buscarPorCheckout(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por check-out: " + valor, e);
            throw e;
        }
      }
      
      /**
     * Busca reservas filtrando por número de habitación.
     *
     * @param valor número o parte del número de habitación.
     * @return lista de reservas que coinciden con el filtro.
     * @throws SQLException si ocurre un error en la base de datos.
     */
      public List<ReservaDTO> buscarPorHabitacion(String valor) throws SQLException {
          try {
            return reservaService.buscarPorHabitacion(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por habitación: " + valor, e);
            throw e;
        }
      }
    
   
}

