
package com.mycompany.hotel.DAO;

import DAO.DAO;
import com.mycompany.hotel.models.pasajero;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class pasajeroDAO implements DAO<pasajero>{

    private static pasajeroDAO instance;
    private List<pasajero> pasajeros = new ArrayList();
    
    private pasajeroDAO(){
        
    }
    public static pasajeroDAO getInstance(){
        if(pasajeroDAO.instance == null){
            pasajeroDAO.instance = new pasajeroDAO();
            }
        return pasajeroDAO.instance;
           
        
    }
            
    @Override
    public void crear(pasajero dato) throws SQLException {
        
    }

    @Override
    public void actualizar(pasajero dato, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(pasajero dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public pasajero recuperarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<pasajero> recuperarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
