
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.service.ReservaService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaController implements Icrud<ReservaDTO> {

        private static final Logger logger = Logger.getLogger(ReservaController.class.getName());
    private ReservaService reservaService;

    
    public ReservaController() {
        this.reservaService = new ReservaService();
    }

   
    
    

    @Override
    public void crear(ReservaDTO dato) throws SQLException {
         try {
            reservaService.crearReserva(dato);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear la reserva: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void actualizar(ReservaDTO dato, int id) throws SQLException {
        try {
            reservaService.actualizarReserva(dato, id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar la reserva con ID: " + id, e);
            throw e;
        }
    }

    @Override
    public void borrar(ReservaDTO dato) throws SQLException {
         try {
            reservaService.borrarReserva(dato);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al borrar la reserva: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
       try {
            reservaService.borrarPorId(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al borrar la reserva con ID: " + id, e);
            throw e;
        }
    }

    @Override
    public ReservaDTO recuperarPorId(int id) throws SQLException {
        try {
            return reservaService.recuperarPorId(id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al recuperar la reserva con ID: " + id, e);
            throw e;
        }
    }

    @Override
    public List<ReservaDTO> recuperarTodos() throws SQLException {
        try {
            return reservaService.recuperarTodos();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al recuperar todas las reservas", e);
            throw e;
        }
    }
    
    public List<ReservaDTO> buscarPasajero(String terminoBusqueda) throws SQLException {
         try {
            return reservaService.buscarPasajero(terminoBusqueda);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por pasajero: " + terminoBusqueda, e);
            throw e;
        }
    }
     public List<ReservaDTO> buscarPorCheckin(String valor) throws SQLException {
         try {
            return reservaService.buscarPorCheckin(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por check-in: " + valor, e);
            throw e;
        }
     }
     
      public List<ReservaDTO> buscarPorCheckout(String valor) throws SQLException {
          try {
            return reservaService.buscarPorCheckout(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por check-out: " + valor, e);
            throw e;
        }
      }
      
      public List<ReservaDTO> buscarPorHabitacion(String valor) throws SQLException {
          try {
            return reservaService.buscarPorHabitacion(valor);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al buscar reservas por habitación: " + valor, e);
            throw e;
        }
      }
    
   
}

