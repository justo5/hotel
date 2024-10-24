/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hotel.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mi pc
 */
public interface Icrud<T> {
    public abstract void crear(T dato) throws SQLException;
    

    public abstract void actualizar(T dato, int id)throws SQLException;

    public abstract void borrar(T dato)throws SQLException;

    public abstract void borrar(int id)throws SQLException;

    public abstract T recuperarPorId(int id)throws SQLException;

    public abstract List<T> recuperarTodos()throws SQLException;
}

