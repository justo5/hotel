/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.repository.PasajeroDAO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.service.PasajeroService;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author justcode
 */
public class PasajeroController implements Icrud<PasajeroDTO> {
  
    private PasajeroService pasajeroService;
   
    public PasajeroController(){
        this.pasajeroService = new PasajeroService();
    }

    public void actualizarPasajero(PasajeroDTO dato) throws SQLException{
        pasajeroService.actualizarPasajeroSolo(dato);
    }

    @Override
    public void crear(PasajeroDTO dato) throws SQLException {
        pasajeroService.crearPasajero(dato);
    }

    @Override
    public void actualizar(PasajeroDTO dato, int id) throws SQLException {
        pasajeroService.actualizarPasajero(dato, id);
    }

    @Override
    public void borrar(PasajeroDTO dato) throws SQLException {
        pasajeroService.borrarPasajero(dato);
    }

    @Override
    public void borrar(int id) throws SQLException {
       pasajeroService.borrarPorId(id);
    }

    @Override
    public PasajeroDTO recuperarPorId(int id) throws SQLException {
        PasajeroDTO pasajero;
        pasajero = pasajeroService.recuperarPorId(id);
        return pasajero;
    }

    @Override
    public List<PasajeroDTO> recuperarTodos() throws SQLException {
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.recuperarTodos();
        return pasajeros;
    }
   
    public List<PasajeroDTO> buscarPorNombre(String nombre) throws SQLException{
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.buscarPorNombre(nombre);
        return pasajeros;
    }
    
    public List<PasajeroDTO> buscarPorApellido(String apellido) throws SQLException{
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.buscarPorApellido(apellido);
        return pasajeros;
    }
    
    public List<PasajeroDTO> buscarPorCorreo(String correo) throws SQLException{
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.buscarPorCorreo(correo);
        return pasajeros;
    }
    
    public List<PasajeroDTO> buscarPorDni(String dni) throws SQLException{
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.buscarPorDni(dni);
        return pasajeros;
    }
    
     public List<PasajeroDTO> buscarPorTelefono(String telefono) throws SQLException{
        List<PasajeroDTO> pasajeros;
        pasajeros = pasajeroService.buscarPorTelefono(telefono);
        return pasajeros;
    }
    
   
    
    
    
}

