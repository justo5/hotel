package com.mycompany.hotel.DAO;
import com.mycompany.hotel.utils.Conexion;

import com.mycompany.hotel.models.Pasajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO implements DAO<Pasajero> {

    @Override
    public void crear(Pasajero dato) throws SQLException {
        String INSERT = "INSERT INTO pasajeros(nombre, apellido, dni, telefono, email) VALUES(?,?,?,?,?);";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo crear el pasajero.");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    dato.setId(rs.getInt(1)); // Asignar el ID generado
                }
            }
        }
    }

    @Override
    public void actualizar(Pasajero dato, int id) throws SQLException {
        String UPDATE = "UPDATE pasajeros SET nombre = ?, apellido = ?, dni = ?, telefono = ?, email = ? WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(UPDATE)) {

            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("La actualización falló, no se afectó ningún registro.");
            }
        }
    }

    @Override
    public void borrar(Pasajero dato) throws SQLException {
        String DELETE = "DELETE FROM pasajeros WHERE id = ?;";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(DELETE)) {

            stat.setInt(1, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo borrar el pasajero.");
            }
        }
    }

    @Override
    public Pasajero recuperarPorId(int id) throws SQLException {
        String SELECTONE = "SELECT id, nombre, apellido, dni, telefono, email FROM pasajeros WHERE id = ?;";
        Pasajero pas = null;

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(SELECTONE)) {

            stat.setInt(1, id);

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    pas = new Pasajero();
                    pas.setId(rs.getInt("id"));
                    pas.setNombre(rs.getString("nombre"));
                    pas.setApellido(rs.getString("apellido"));
                    pas.setDNI(rs.getInt("dni"));
                    pas.setTelefono(rs.getInt("telefono"));
                    pas.setEmail(rs.getString("email"));
                }
            }
        }

        return pas;
    }

    @Override
    public List<Pasajero> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id, nombre, apellido, dni, telefono, email FROM pasajeros;";
        List<Pasajero> pasajeros = new ArrayList<>();

        try (Connection cnn = Conexion.getConnection();
             Statement stat = cnn.createStatement();
             ResultSet rs = stat.executeQuery(SELECTALL)) {

            while (rs.next()) {
                Pasajero pas = new Pasajero();
                pas.setId(rs.getInt("id"));
                pas.setNombre(rs.getString("nombre"));
                pas.setApellido(rs.getString("apellido"));
                pas.setDNI(rs.getInt("dni"));
                pas.setTelefono(rs.getInt("telefono"));
                pas.setEmail(rs.getString("email"));

                pasajeros.add(pas);
            }
        }

        return pasajeros;
    }
}
