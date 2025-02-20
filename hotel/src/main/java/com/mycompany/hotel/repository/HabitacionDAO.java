<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
package com.mycompany.hotel.repository;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.model.Habitacion;
import java.sql.Connection;
=======
package com.mycompany.hotel.DAO;

import com.mycompany.hotel.interfaces.Icrud;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.models.Habitacion;
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class HabitacionDAO implements Icrud<Habitacion> {
<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
/*
    @Override
    public void crear(Habitacion dato) throws SQLException {
        String INSERT = "INSERT INTO habitaciones(numero, camasSimples, camasDobles, precioPorNoche) VALUES(?,?,?,?);";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setInt(1, dato.getNumero());
=======

    private static HabitacionDAO instancia;
    public static List<Habitacion> habitacion = new ArrayList();
    private static Conexion cnn = Conexion.iniciarConnection();

    private HabitacionDAO() {
    }

    public static HabitacionDAO getInstancia() {
        if (HabitacionDAO.instancia == null) {
            HabitacionDAO.instancia = new HabitacionDAO() {
                @Override
                public void borrar(int id) throws SQLException {
                    String DELETE = "DELETE FROM habitaciones WHERE id = ?;";
                    Conexion cnn = Conexion.iniciarConnection();

                    try (PreparedStatement stat = cnn.getCo().prepareStatement(DELETE)) {
                        stat.setInt(1, id);

                        int rowsAffected = stat.executeUpdate();
                        if (rowsAffected == 0) {
                            throw new SQLException("No se encontró la habitación con el ID proporcionado.");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error al ejecutar la consulta de eliminación: " + ex.getMessage());
                        throw ex;  // Lanzamos la excepción para que el controlador también la maneje
                    } finally {
                        cnn.cerrarConexion();
                    }
                }
            };
        }
        return HabitacionDAO.instancia;
    }

    @Override
    public void crear(Habitacion dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        Conexion cnn = Conexion.iniciarConnection();
        String INSERT = "INSERT INTO habitaciones(numero, camasSimples, camasDobles, precioPorNoche) VALUES(?, ?, ?, ?);";
        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            List<Habitacion> habitacion = new ArrayList();
            stat.setString(1, dato.getNumero());
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setFloat(4, dato.getPrecioPorNoche());

<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo crear la habitación.");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    dato.setNumero(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        String UPDATE = "UPDATE habitaciones SET camasSimples = ?, camasDobles = ?, precioPorNoche = ? WHERE numero = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(UPDATE)) {

            stat.setInt(1, dato.getCamasSimples());
            stat.setInt(2, dato.getCamasDobles());
            stat.setFloat(3, dato.getPrecioPorNoche());
            stat.setInt(4, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("La actualización falló, no se afectó ningún registro.");
            }
        }
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
        String DELETE = "DELETE FROM habitaciones WHERE numero = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(DELETE)) {

            stat.setInt(1, dato.getNumero());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar la habitación.");
            }
=======
            int rowsAffected = stat.executeUpdate(); //actualizacon de BD

            if (rowsAffected == 0) {
                throw new SQLException("No se guardó la habitación.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
            }

            habitacion.add(dato);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
        }
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
        String SELECTONE = "SELECT numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE numero = ?;";
        Habitacion hab = null;

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(SELECTONE)) {

=======
        String SELECTONE = "SELECT numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE id = ?;";
        Habitacion hab = null;
        Conexion cnn = Conexion.iniciarConnection();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECTONE)) {
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
            stat.setInt(1, id);

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    hab = new Habitacion();
<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
                    hab.setNumero(rs.getInt("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                }
            }
=======
                    hab.setId(id);
                    hab.setNumero(rs.getString("numero"));  // Cambié de "Numero" a "numero" según la consulta
                    hab.setCamasSimples(rs.getInt("camasSimples")); // Cambié el nombre de la columna a "camasSimples"
                    hab.setCamasDobles(rs.getInt("camasDobles"));   // Cambié el nombre de la columna a "camasDobles"
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));  // Cambié el nombre de la columna a "precioPorNoche"
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
        }

        return hab;
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
        String SELECTALL = "SELECT numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones;";
        List<Habitacion> habitaciones = new ArrayList<>();

        try (Connection cnn = Conexion.getConnection();
             Statement stat = cnn.createStatement();
             ResultSet rs = stat.executeQuery(SELECTALL)) {

            while (rs.next()) {
                Habitacion hab = new Habitacion();
                hab.setNumero(rs.getInt("numero"));
                hab.setCamasSimples(rs.getInt("camasSimples"));
                hab.setCamasDobles(rs.getInt("camasDobles"));
                hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));

                habitaciones.add(hab);
            }
=======
        String SELECTALL = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones;";
        List<Habitacion> habitaciones = new ArrayList<>();
        Conexion cnn = Conexion.iniciarConnection();

        try (Statement stat = cnn.getCo().createStatement(); ResultSet rs = stat.executeQuery(SELECTALL)) {
            while (rs.next()) {
                Habitacion hab = new Habitacion();
                hab.setId(rs.getInt("id"));
                hab.setNumero(rs.getString("numero"));  // Cambié de "Numero" a "numero" según la consulta
                hab.setCamasSimples(rs.getInt("camasSimples")); // Cambié el nombre de la columna a "camasSimples"
                hab.setCamasDobles(rs.getInt("camasDobles"));   // Cambié el nombre de la columna a "camasDobles"
                hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));  // Cambié el nombre de la columna a "precioPorNoche"
                habitaciones.add(hab);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }

    public Habitacion recuperarPorNumero(String numero) throws SQLException {
        String SELECTONE = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE numero = ?;";
        Habitacion hab = null;
        Conexion cnn = Conexion.iniciarConnection();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECTONE)) {
            stat.setString(1, numero); // Buscamos por el número de habitación

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    hab = new Habitacion();
                    hab.setId(rs.getInt("id"));
                    hab.setNumero(rs.getString("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
        }

        return hab;
    }

    public List<Habitacion> recuperarPorCapacidad(int capacidad) throws SQLException {
        String SELECTBYCAPACIDAD = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE capacidad = ?;";
        List<Habitacion> habitaciones = new ArrayList<>();
        Conexion cnn = Conexion.iniciarConnection();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECTBYCAPACIDAD)) {
            stat.setInt(1, capacidad); // Filtramos por capacidad

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Habitacion hab = new Habitacion();
                    hab.setId(rs.getInt("id"));
                    hab.setNumero(rs.getString("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                    habitaciones.add(hab);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }

    public List<Habitacion> recuperarPorEstado(String estado) throws SQLException {
        String SELECTBYESTADO = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE estado = ?;";
        List<Habitacion> habitaciones = new ArrayList<>();
        Conexion cnn = Conexion.iniciarConnection();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECTBYESTADO)) {
            stat.setString(1, estado);

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Habitacion hab = new Habitacion();
                    hab.setId(rs.getInt("id"));
                    hab.setNumero(rs.getString("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                    habitaciones.add(hab);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }

    public List<Habitacion> recuperarPorPrecio(double precio) throws SQLException {
        String SELECTBYPRECIO = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE precioPorNoche = ?;";
        List<Habitacion> habitaciones = new ArrayList<>();
        Conexion cnn = Conexion.iniciarConnection();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECTBYPRECIO)) {
            stat.setDouble(1, precio);

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Habitacion hab = new Habitacion();
                    hab.setId(rs.getInt("id"));
                    hab.setNumero(rs.getString("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                    habitaciones.add(hab);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
        }

        return habitaciones;
    }

    @Override
<<<<<<< HEAD:hotel/src/main/java/com/mycompany/hotel/repository/HabitacionDAO.java
    public void borrar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
*/
=======
    public void actualizar(Habitacion dato, int id) throws SQLException {
        String UPDATE = "UPDATE habitaciones SET numero = ?, camasSimples = ?, camasDobles = ?, precioPorNoche = ? WHERE id = ?;";

        try (PreparedStatement stat = cnn.getCo().prepareStatement(UPDATE)) {
            stat.setString(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setDouble(4, dato.getPrecioPorNoche());
            stat.setInt(5, dato.getId());

            int rowsAffected = stat.executeUpdate(); // Ejecutamos la actualización

            if (rowsAffected == 0) {
                throw new SQLException("No se pudo actualizar la habitación.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;  // Lanzamos la excepción para manejo superior
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
        PreparedStatement stat;
        Conexion cnn = Conexion.iniciarConnection();
        String DELETE = "DELETE FROM habitaciones WHERE id = ?;";

        try {

            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar la habitación.");
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            throw ex;
        } finally {
            cnn.cerrarConexion();
        }

    }

>>>>>>> 9036a6075f2c5c3e0f2c50195c62d58973e1d4bb:hotel/src/main/java/com/mycompany/hotel/DAO/HabitacionDAO.java
}
