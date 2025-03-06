/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.mapper.PasajeroMapper;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.repository.PasajeroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mi pc
 */
public class PasajeroService {

    private PasajeroDAO pasajeroDAO;

   
    
    public PasajeroService() {
    this.pasajeroDAO = PasajeroDAO.getInstancia();
}
    
    public void actualizarPasajeroSolo(PasajeroDTO dto) throws SQLException{
        Pasajero pasajero = PasajeroMapper.toEntity(dto);
        pasajeroDAO.actualizarPasajero(pasajero);
    }

    public void crearPasajero(PasajeroDTO dto) throws SQLException {
        Pasajero pasajero = PasajeroMapper.toEntity(dto);
        pasajeroDAO.crear(pasajero);
    }

    public void borrarPasajero(PasajeroDTO dto) throws SQLException {
        Pasajero pasajero = PasajeroMapper.toEntity(dto);
        pasajeroDAO.borrar(pasajero);
    }
    public void borrarPorId(int id) throws SQLException{
        pasajeroDAO.borrar(id);
    }

    public void actualizarPasajero(PasajeroDTO dto, int id) throws SQLException {
        Pasajero pasajero = PasajeroMapper.toEntity(dto);
        pasajeroDAO.actualizar(pasajero, id);
    }

    public PasajeroDTO recuperarPorId(int id) throws SQLException {
        Pasajero pasajero = pasajeroDAO.recuperarPorId(id);
        PasajeroDTO pasajeroDTO = PasajeroMapper.toDTO(pasajero);
        return pasajeroDTO;
    }

    public List<PasajeroDTO> recuperarTodos() throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.recuperarTodos();
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>(); 

        for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }

        return pasajerosDTO; 
    }
    public List<PasajeroDTO> buscarPorNombre(String nombre) throws SQLException{
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorNombre(nombre);
        List<PasajeroDTO> pasajerosDTO =new ArrayList<>();
          for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }
                
        return pasajerosDTO;    
    }
     public List<PasajeroDTO> buscarPorApellido(String apellido) throws SQLException{
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorApellido(apellido);
        List<PasajeroDTO> pasajerosDTO =new ArrayList<>();
          for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }
                
        return pasajerosDTO;    
    }
      public List<PasajeroDTO> buscarPorDni(String dni) throws SQLException{
           List<Pasajero> pasajeros = pasajeroDAO.buscarPorDni(dni);
        List<PasajeroDTO> pasajerosDTO =new ArrayList<>();
          for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }
                
        return pasajerosDTO;    
           
    }
    
     public List<PasajeroDTO> buscarPorCorreo(String correo) throws SQLException{
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorCorreo(correo);
        List<PasajeroDTO> pasajerosDTO =new ArrayList<>();
          for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }
                
        return pasajerosDTO;        
    }
     
    public List<PasajeroDTO> buscarPorTelefono(String telefono) throws SQLException{
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorTelefono(telefono);
        List<PasajeroDTO> pasajerosDTO =new ArrayList<>();
          for (Pasajero pasajero : pasajeros) {
            PasajeroDTO dto = PasajeroMapper.toDTO(pasajero);
            pasajerosDTO.add(dto);
        }
                
        return pasajerosDTO;        
    }
}
