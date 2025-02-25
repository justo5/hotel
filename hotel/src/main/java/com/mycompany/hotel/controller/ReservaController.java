
/*

package com.mycompany.hotel.controller;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.models.Reserva;
import com.mycompany.hotel.models.Pasajero;
import com.mycompany.hotel.models.Habitacion;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaController implements Icrud<Reserva> {

    @Override
    public void crear(Reserva dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Reserva dato, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(Reserva dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reserva recuperarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reserva> recuperarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*
    private static final ReservaDAO reservaDAO = ReservaDAO.getInstancia();
    private Reserva reserva;

    public ReservaController() {
    }

    
    public void crear(Reserva dato) {
        try {
            reservaDAO.crear(dato);
        } catch (SQLException ex) {
            System.out.println("No pudo ser creada la reserva: " + ex);
        }
    }

    
    public void crear(String chekin, String checkout, Pasajero pasajero, Habitacion habitacion, float senia) {
        try {
            this.reserva = new Reserva(chekin, checkout, pasajero, habitacion, senia);
            reservaDAO.crear(reserva);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void actualizar(Reserva dato, int id) {
        try {
            reservaDAO.actualizar(dato, id);
        } catch (SQLException ex) {
            System.out.println("No pudo ser actualizada la reserva: " + ex);
        }
    }

    
    public void actualizar(int id, String chekin, String checkout, Pasajero pasajero, Habitacion habitacion, float seña) {
        try {
            this.reserva = new Reserva(id, chekin, checkout, pasajero, habitacion, seña);
            reservaDAO.actualizar(reserva, id);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void borrar(Reserva dato) {
        try {
            reservaDAO.borrar(dato);
        } catch (SQLException ex) {
            System.out.println("No pudo ser borrada la reserva: " + ex);
        }
    }

    
    public void borrar(int id) {
        try {
            reservaDAO.borrar(id);
        } catch (SQLException ex) {
            System.out.println("No pudo ser borrada la reserva: " + ex);
        }
    }

    
    public Reserva recuperarPorId(int id) {
        try {
            return reservaDAO.recuperarPorId(id);
        } catch (SQLException ex) {
            System.out.println("No se pudo recuperar la reserva por ID: " + ex);
            return null;
        }
    }

   
    public List<Reserva> recuperarTodos() {
        try {
            return reservaDAO.recuperarTodos();
        } catch (SQLException ex) {
            System.out.println("No se pudieron recuperar las reservas: " + ex);
            return null;
        }
    }

    
    public List<Reserva> buscarPorPasajero(Pasajero pasajero) throws SQLException {
        return reservaDAO.buscarPorPasajero(pasajero);
    }

   
    public List<Reserva> buscarPorFecha(String fecha) throws SQLException {
        return reservaDAO.buscarPorFecha(fecha);
    }
}
*/
