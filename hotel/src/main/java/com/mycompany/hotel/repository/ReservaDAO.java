/*
package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.models.Reserva;
import com.mycompany.hotel.models.Pasajero;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.utils.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements Icrud<Reserva> {

    @Override
    public void crear(Reserva dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Reserva dato, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(Reserva dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void borrar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Reserva recuperarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Reserva> recuperarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


 /* 
    @Override
    public void crear(Reserva dato) throws SQLException {
      String INSERT = "INSERT INTO reservas(chekin, checkout, idPasajero, idHabitacion, senia) VALUES(?,?,?,?,?);";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setDate(1, Date.valueOf(dato.getChekin()));
            stat.setDate(2, Date.valueOf(dato.getCheckout()));
           // stat.setInt(3, dato.getoPasajero().getId());  // Usar el id del pasajero
            //stat.setInt(4, dato.getoHabitacion().getId()); // Usar el id de la habitación
            stat.setFloat(5, dato.getSeña());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo crear la reserva.");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    //dato.setId(rs.getInt(1));
                }
            }

package com.mycompany.hotel.DAO;

import com.mycompany.hotel.interfaces.Icrud;
import com.mycompany.hotel.models.Reserva;
import com.mycompany.hotel.models.Pasajero;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.utils.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements Icrud<Reserva> {

    private static ReservaDAO instancia;
    public static List<Reserva> reserva = new ArrayList();
    private static final Conexion cnn = Conexion.iniciarConnection();

     private ReservaDAO() {
    }

    public static ReservaDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReservaDAO();
        }
        return ReservaDAO.instancia;
    }

    @Override
    public void crear(Reserva dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        String INSERT = "INSERT INTO reservas (chekin, checkout, pasajero_id, habitacion_id, senia) VALUES (?, ?, ?, ?, ?)";
        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, dato.getChekin());
            stat.setString(2, dato.getCheckout());
            stat.setInt(3, dato.getPasajero()); 
            stat.setInt(4, dato.getHabitacion());
            stat.setFloat(5, dato.getSenia());

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
                reserva.add(dato); 
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al crear la reserva", ex);
        } finally {
            cnn.cerrarConexion();

        }
    }

    @Override
    public void actualizar(Reserva dato, int id) throws SQLException {

        String UPDATE = "UPDATE reservas SET chekin = ?, checkout = ?, idPasajero = ?, idHabitacion = ?, senia = ? WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(UPDATE)) {

            stat.setDate(1, Date.valueOf(dato.getChekin()));
            stat.setDate(2, Date.valueOf(dato.getCheckout()));
            stat.setInt(3, dato.getoPasajero().getId());  // Usar el id del pasajero
           // stat.setInt(4, dato.getoHabitacion().getId()); // Usar el id de la habitación
            stat.setFloat(5, dato.getSeña());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("La actualización falló, no se afectó ningún registro.");
            }

        PreparedStatement stat;
        String UPDATE = "UPDATE reservas SET chekin = ?, checkout = ?, pasajero_id = ?, habitacion_id = ?, senia = ? WHERE id = ?";
        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setString(1, dato.getChekin());
            stat.setString(2, dato.getCheckout());
            stat.setInt(3, dato.getPasajero()); 
            stat.setInt(4, dato.getHabitacion());
            stat.setFloat(5, dato.getSenia());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo actualizar la reserva");
            }

           
            for (int i = 0; i < reserva.size(); i++) {
                if (reserva.get(i).getId() == id) {
                    reserva.set(i, dato); // Actualizamos la reserva en memoria
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("Error al actualizar la reserva", ex);
        } finally {
            cnn.cerrarConexion();

        }
    }

    @Override
    public void borrar(Reserva dato) throws SQLException {

        String DELETE = "DELETE FROM reservas WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(DELETE)) {

           // stat.setInt(1, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar la reserva.");
            }

        PreparedStatement stat;
        String DELETE = "DELETE FROM reservas WHERE id = ?";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar la reserva");
            }

            reserva.remove(dato); // Eliminamos de la lista temporal

        } catch (SQLException ex) {
            throw new SQLException("Error al borrar la reserva", ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        PreparedStatement stat;
        String DELETE = "DELETE FROM reservas WHERE id = ?";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, id);

            int filasBorradas = stat.executeUpdate();
            if (filasBorradas == 0) {
                throw new SQLException("No se pudo borrar la reserva");
            }

            // Eliminar de la lista temporal
            reserva.removeIf(reserva -> reserva.getId() == id);

        } catch (SQLException ex) {
            throw new SQLException("Error al borrar la reserva", ex);
        } finally {
            cnn.cerrarConexion();

        }
    }

    @Override
    public Reserva recuperarPorId(int id) throws SQLException {

        String SELECTONE = "SELECT id, chekin, checkout, idPasajero, idHabitacion, senia FROM reservas WHERE id = ?;";
        Reserva res = null;

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(SELECTONE)) {

            stat.setInt(1, id);

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    res = new Reserva();
                    res.setChekin(rs.getString("chekin"));
                    res.setCheckout(rs.getString("checkout"));

                    // Aquí recuperaríamos los objetos `pasajero` y `habitacion` usando sus respectivos DAOs
                    //PasajeroDAO pasajeroDao = PasajeroDAO.getInstance();
                    //HabitacionDAO habitacionDao = HabitacionDAO.getInstance();

                    //res.setoPasajero(pasajeroDao.recuperarPorId(rs.getInt("idPasajero")));
                    //res.setoHabitacion(habitacionDao.recuperarPorId(rs.getInt("idHabitacion")));
                    res.setSeña(rs.getFloat("senia"));
                }
            }
        }

        return res;

        PreparedStatement stat;
        ResultSet rs;
        String SELECT = "SELECT * FROM reservas WHERE id = ?";
        Reserva reserva = null;
        try {
            stat = cnn.getCo().prepareStatement(SELECT);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getString("chekin"),
                        rs.getString("checkout"),
                        new Pasajero(rs.getInt("pasajero_id")), // Cargar el pasajero
                        new Habitacion(rs.getInt("habitacion_id")), // Cargar la habitación
                        rs.getFloat("senia")
                );
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al recuperar la reserva por ID", ex);
        } finally {
            cnn.cerrarConexion();
        }
        return reserva;

    }

    @Override
    public List<Reserva> recuperarTodos() throws SQLException {

        String SELECTALL = "SELECT id, chekin, checkout, idPasajero, idHabitacion, senia FROM reservas;";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection cnn = Conexion.getConnection();
             Statement stat = cnn.createStatement();
             ResultSet rs = stat.executeQuery(SELECTALL)) {

            while (rs.next()) {
                Reserva res = new Reserva();
                res.setChekin(rs.getString("chekin"));
                res.setCheckout(rs.getString("checkout"));

                // Usar los DAOs de pasajero y habitacion para obtener los objetos correspondientes
               // PasajeroDAO pasajeroDao = PasajeroDAO.getInstance();
                //HabitacionDAO habitacionDao = HabitacionDAO.getInstance();

                //res.setoPasajero(pasajeroDao.recuperarPorId(rs.getInt("idPasajero")));
                //res.setoHabitacion(habitacionDao.recuperarPorId(rs.getInt("idHabitacion")));
                res.setSeña(rs.getFloat("senia"));

                reservas.add(res);
            }
        }

        return reservas;
    }

    @Override
    public void borrar(int id) throws SQLException { 
        Statement stat;
        ResultSet rs;
        List<Reserva> listaReservas = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM reservas";
        try {
            stat = cnn.getCo().createStatement();
            rs = stat.executeQuery(SELECT_ALL);
            while (rs.next()) {
                listaReservas.add(new Reserva(
                        rs.getInt("id"),
                        rs.getString("chekin"),
                        rs.getString("checkout"),
                        new Pasajero(rs.getInt("pasajero_id")),
                        new Habitacion(rs.getInt("habitacion_id")),
                        rs.getFloat("senia")
                ));
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al recuperar todas las reservas", ex);
        } finally {
            cnn.cerrarConexion();
        }
        return listaReservas;
    }

    // Método para buscar reservas por el nombre del pasajero
    public List<Reserva> buscarPorPasajero(Pasajero pasajero) throws SQLException {
        PreparedStatement stat;
        ResultSet rs;
        List<Reserva> reservasPorPasajero = new ArrayList<>();
        String SELECT = "SELECT * FROM reservas WHERE pasajero_id = ?";
        try {
            stat = cnn.getCo().prepareStatement(SELECT);
            stat.setInt(1, pasajero.getId());
            rs = stat.executeQuery();
            while (rs.next()) {
                reservasPorPasajero.add(new Reserva(
                        rs.getInt("id"),
                        rs.getString("chekin"),
                        rs.getString("checkout"),
                        new Pasajero(rs.getInt("pasajero_id")),
                        new Habitacion(rs.getInt("habitacion_id")),
                        rs.getFloat("senia")
                ));
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al buscar reservas por pasajero", ex);
        } finally {
            cnn.cerrarConexion();
        }
        return reservasPorPasajero;
    }

    // Método para buscar reservas por fecha (chekin o checkout)
    public List<Reserva> buscarPorFecha(String fecha) throws SQLException {
        PreparedStatement stat;
        ResultSet rs;
        List<Reserva> reservasPorFecha = new ArrayList<>();
        String SELECT = "SELECT * FROM reservas WHERE chekin = ? OR checkout = ?";
        try {
            stat = cnn.getCo().prepareStatement(SELECT);
            stat.setString(1, fecha);
            stat.setString(2, fecha);
            rs = stat.executeQuery();
            while (rs.next()) {
                reservasPorFecha.add(new Reserva(
                        rs.getInt("id"),
                        rs.getString("chekin"),
                        rs.getString("checkout"),
                        new Pasajero(rs.getInt("pasajero_id")),
                        new Habitacion(rs.getInt("habitacion_id")),
                        rs.getFloat("senia")
                ));
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al buscar reservas por fecha", ex);
        } finally {
            cnn.cerrarConexion();
        }
        return reservasPorFecha;
    }
}
*/