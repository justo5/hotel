
package com.mycompany.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db"; // Cambia el nombre de tu base de datos
    private static final String USER = "root"; // Usuario de tu base de datos
    private static final String PASSWORD = "password"; // Contraseña de tu base de datos
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver MySQL

    private Conexion() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(DRIVER); // Cargar el driver de la base de datos
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error al conectar con la base de datos", e);
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
