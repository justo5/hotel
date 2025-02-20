package com.mycompany.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Connection cnn;
    private static Conexion instancia  = null;
  
    private Conexion() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "root");

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Conexion iniciarConnection() {
        if (instancia == null) {
            try {
                instancia = new Conexion();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instancia;
    }

    public Connection getCo() {
        return cnn;
    }

    public void cerrarConexion() {
        this.instancia = null;
    }
}

