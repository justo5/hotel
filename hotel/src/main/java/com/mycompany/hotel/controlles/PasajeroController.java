/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controlles;

import com.mycompany.hotel.DAO.PasajeroDAO;
import com.mycompany.hotel.interfaces.Icrud;
import com.mycompany.hotel.models.Pasajero;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author justcode
 */
public class PasajeroController implements Icrud<Pasajero> {
    private static final PasajeroDAO pasajeroDAO = PasajeroDAO.getInstancia();
    private Pasajero pasajero;
    
    public PasajeroController(){
    }
    
    @Override
    public void crear(Pasajero dato) {
        try{
            pasajeroDAO.crear(dato);
            
        }catch(Exception ex){
            System.out.println("No pudo ser creada" + ex);
        }
        
    }
    public void crear(String nombre,String apellido,int DNI, int tel,String email) throws SQLException{
       
      this.pasajero = new Pasajero(nombre,apellido,DNI,tel,email);
      pasajeroDAO.crear(pasajero);
      
        
    }
    
    @Override
    public void actualizar(Pasajero dato, int id) throws SQLException {
    try{
        pasajeroDAO.actualizar(dato, id);
    } catch(Exception ex){
        System.out.println("No pudo ser actualizada" + ex);
    }
    }
    public void actualizar(int id,String nombre,String apellido,int DNI, int tel,String email){
        try {
            this.pasajero = new Pasajero(nombre,apellido,DNI,tel,email);
            pasajeroDAO.actualizar(pasajero, id);
        } catch (SQLException ex) {
            Logger.getLogger(PasajeroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void borrar(Pasajero dato) throws SQLException {
       pasajeroDAO.borrar(dato);
    }

    @Override
    public void borrar(int id) throws SQLException {
        pasajeroDAO.borrar(id);
    }

    @Override
    public Pasajero recuperarPorId(int id) throws SQLException {
     return pasajeroDAO.recuperarPorId(id);
       
    }

    @Override
    public List<Pasajero> recuperarTodos() throws SQLException {
        return this.pasajeroDAO.recuperarTodos();
    }
    
    
    public Pasajero buscarPorNombre(String nombre) throws SQLException {
       return pasajeroDAO.buscarPorNombre(nombre);
    }
    
    public Pasajero buscarPorDni(int dni) throws SQLException {
        return pasajeroDAO.buscarPorDni(dni);
    }

    
}

