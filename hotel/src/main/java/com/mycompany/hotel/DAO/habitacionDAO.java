package com.mycompany.hotel.DAO;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.models.Habitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class habitacionDAO implements DAO<Habitacion> {

    @Override
    public void crear(Habitacion dato) throws SQLException {
        String INSERT = "INSERT INTO habitaciones(numero, camasSimples, camasDobles, precioPorNoche) VALUES(?,?,?,?);";

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setInt(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setFloat(4, dato.getPrecioPorNoche());

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
        }
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        String SELECTONE = "SELECT numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE numero = ?;";
        Habitacion hab = null;

        try (Connection cnn = Conexion.getConnection();
             PreparedStatement stat = cnn.prepareStatement(SELECTONE)) {

            stat.setInt(1, id);

            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    hab = new Habitacion();
                    hab.setNumero(rs.getInt("numero"));
                    hab.setCamasSimples(rs.getInt("camasSimples"));
                    hab.setCamasDobles(rs.getInt("camasDobles"));
                    hab.setPrecioPorNoche(rs.getFloat("precioPorNoche"));
                }
            }
        }

        return hab;
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
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
        }

        return habitaciones;
    }
}
