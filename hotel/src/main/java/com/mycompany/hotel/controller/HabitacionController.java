
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.repository.HabitacionDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author justcode
 */
public class HabitacionController implements Icrud<Habitacion> {

    @Override
    public void crear(Habitacion dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   /* private static HabitacionDAO habitacionDAO = HabitacionDAO.getInstancia();
    private Habitacion habitacion;
  

     @Override
      public void crear(Habitacion dato) {
        try{
            habitacionDAO.crear(dato);
            
        }catch(Exception ex){
            System.out.println("No pudo ser creada" + ex);
        }
        
    }
    public void crear(String numero, int camasSimples, int camasDobles, float precioPorNoche)throws SQLException {
        this.habitacion = new Habitacion();
        habitacionDAO.crear(habitacion);
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
     try {
            habitacionDAO.actualizar(dato, id);
           } catch (Exception ex) {
            System.out.println("Error al actualizar la habitación: " + ex);
        }
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
            habitacionDAO.borrar(dato);
    }

    @Override
    public void borrar(int id) throws SQLException {
    habitacionDAO.borrar(id);
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
     return  habitacionDAO.recuperarPorId(id);
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
    return this.habitacionDAO.recuperarTodos();
    }*/
 
}
