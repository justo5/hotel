<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/controller/HabitacionController.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;
=======
package com.mycompany.hotel.controlles;
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/controlles/HabitacionController.java

import com.mycompany.hotel.DAO.HabitacionDAO;
import com.mycompany.hotel.interfaces.Icrud;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.models.Pasajero;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author justcode
 */
public class HabitacionController implements Icrud<Habitacion> {
    
    private static HabitacionDAO habitacionDAO = HabitacionDAO.getInstancia();
    private Habitacion habitacion;
    public HabitacionController(){}

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
    }
 
}
