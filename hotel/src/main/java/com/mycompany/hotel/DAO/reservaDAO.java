package com.mycompany.hotel.DAO;

import com.mycompany.hotel.models.reserva;
import com.mycompany.hotel.models.pasajero;
import com.mycompany.hotel.models.habitacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class reservaDAO implements DAO<reserva> {

    @Override
    public void crear(reserva dato) throws SQLException {
        String INSERT = "INSERT INTO reservas(chekin, checkout, idPasajero, idHabitacion, senia) VALUES(?,?,?,?,?);";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setDate(1, Date.valueOf(dato.getChekin()));
            stat.setDate(2, Date.valueOf(dato.getCheckout()));
            stat.setInt(3, dato.getoPasajero().getId());  // Usar el id del pasajero
            stat.setInt(4, dato.getoHabitacion().getId()); // Usar el id de la habitación
            stat.setFloat(5, dato.getSeña());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo crear la reserva.");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    dato.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void actualizar(reserva dato, int id) throws SQLException {
        String UPDATE = "UPDATE reservas SET chekin = ?, checkout = ?, idPasajero = ?, idHabitacion = ?, senia = ? WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(UPDATE)) {

            stat.setDate(1, Date.valueOf(dato.getChekin()));
            stat.setDate(2, Date.valueOf(dato.getCheckout()));
            stat.setInt(3, dato.getoPasajero().getId());  // Usar el id del pasajero
            stat.setInt(4, dato.getoHabitacion().getId()); // Usar el id de la habitación
            stat.setFloat(5, dato.getSeña());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("La actualización falló, no se afectó ningún registro.");
            }
        }
    }

    @Override
    public void borrar(reserva dato) throws SQLException {
        String DELETE = "DELETE FROM reservas WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(DELETE)) {

            stat.setInt(1, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar la reserva.");
            }
        }
    }

    @Override
    public reserva recuperarPorId(int id) throws SQLException {
        String SELECTONE = "SELECT id, chekin, checkout, idPasajero, idHabitacion, senia FROM reservas WHERE id = ?;";
        reserva res = null;

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(SELECTONE)) {

            stat.setInt(1, id);

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    res = new reserva();
                    res.setChekin(rs.getString("chekin"));
                    res.setCheckout(rs.getString("checkout"));

                    // Aquí recuperaríamos los objetos `pasajero` y `habitacion` usando sus respectivos DAOs
                    pasajeroDAO pasajeroDao = pasajeroDAO.getInstance();
                    habitacionDAO habitacionDao = habitacionDAO.getInstance();

                    res.setoPasajero(pasajeroDao.recuperarPorId(rs.getInt("idPasajero")));
                    res.setoHabitacion(habitacionDao.recuperarPorId(rs.getInt("idHabitacion")));
                    res.setSeña(rs.getFloat("senia"));
                }
            }
        }

        return res;
    }

    @Override
    public List<reserva> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id, chekin, checkout, idPasajero, idHabitacion, senia FROM reservas;";
        List<reserva> reservas = new ArrayList<>();

        try (Connection cnn = Conexion.getConnection();
             Statement stat = cnn.createStatement();
             ResultSet rs = stat.executeQuery(SELECTALL)) {

            while (rs.next()) {
                reserva res = new reserva();
                res.setChekin(rs.getString("chekin"));
                res.setCheckout(rs.getString("checkout"));

                // Usar los DAOs de pasajero y habitacion para obtener los objetos correspondientes
                pasajeroDAO pasajeroDao = pasajeroDAO.getInstance();
                habitacionDAO habitacionDao = habitacionDAO.getInstance();

                res.setoPasajero(pasajeroDao.recuperarPorId(rs.getInt("idPasajero")));
                res.setoHabitacion(habitacionDao.recuperarPorId(rs.getInt("idHabitacion")));
                res.setSeña(rs.getFloat("senia"));

                reservas.add(res);
            }
        }

        return reservas;
    }
}
