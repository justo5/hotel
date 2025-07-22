
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
public class HabitacionController implements Icrud<HabitacionDTO> {
    private HabitacionService habitacionService;
    
    public HabitacionController(){
        this.habitacionService = new HabitacionService();
    }
    @Override
    public void crear(HabitacionDTO dato) throws SQLException {
        habitacionService.crearHabitacion(dato);
    }
    

    @Override
    public void actualizar(HabitacionDTO dato, int id) throws SQLException {
       habitacionService.actualizarHabitacion(dato, id);
    }

    @Override
    public void borrar(HabitacionDTO dato) throws SQLException {
       habitacionService.borrarHabitacion(dato);    
    }

    @Override
    public void borrar(int id) throws SQLException {
        habitacionService.borrarPorId(id);
    }

    @Override
    public HabitacionDTO recuperarPorId(int id) throws SQLException {
       HabitacionDTO habitacionDTO;
       habitacionDTO = habitacionService.recuperarPorId(id);
       return habitacionDTO;
    }

    @Override
    public List<HabitacionDTO> recuperarTodos() throws SQLException {
        List<HabitacionDTO> habitacionDTO;
        habitacionDTO = habitacionService.recuperarTodos();
        return habitacionDTO;
    }
    
    public List<HabitacionDTO> buscarPorNumero(String numero) throws SQLException{
         List<HabitacionDTO> habitacionDTO;
         habitacionDTO = habitacionService.buscarPorNumero(numero);
         return habitacionDTO;
    }
    
    public List<HabitacionDTO> buscarPorPrecio(String precio) throws SQLException{
        List<HabitacionDTO> habitacionesDTO;
        habitacionesDTO = habitacionService.buscarPorPrecio(precio);
        return habitacionesDTO;
    }
    
    public List<HabitacionDTO> buscarPorCapacidad(String cantidad){
        List<HabitacionDTO> habitacionesDTO;
        habitacionesDTO = habitacionService.buscarPorCapacidad(cantidad);
        return habitacionesDTO;
    }
    
    public List<HabitacionDTO> buscarDisponibles(Date checkin, Date checkout, int cantidad) {
        List<HabitacionDTO> habitacionesDTO;
        habitacionesDTO = habitacionService.buscarDisponibles(checkin, checkout, cantidad);
        return habitacionesDTO;
    }
   
    public int calcularCapacidad(HabitacionDTO h) {
        int dobles = h.getCamasDobles();
        int simples = h.getCamasSimples();
        return (dobles * 2) + simples;
    }

}
