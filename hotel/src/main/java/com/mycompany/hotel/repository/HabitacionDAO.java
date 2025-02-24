package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.utils.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO implements Icrud<Habitacion> {

    private static HabitacionDAO instancia;
    public static List<Habitacion> habitaciones = new ArrayList<>();
    private static Conexion cnn = Conexion.iniciarConnection();

    private HabitacionDAO() {
    }

    public static HabitacionDAO getInstancia() {
        if (HabitacionDAO.instancia == null) {
            HabitacionDAO.instancia = new HabitacionDAO();
        }
        return HabitacionDAO.instancia;
    }

    @Override
    public void crear(Habitacion dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        cnn = Conexion.iniciarConnection();
        String INSERT = "INSERT INTO habitaciones(numero, camasSimples, camasDobles, precioPorNoche) VALUES(?, ?, ?, ?);";
        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setFloat(4, dato.getPrecioPorNoche());
            stat.executeUpdate();
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
                habitaciones.add(dato);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        PreparedStatement stat;
        final String UPDATE = "UPDATE habitaciones SET numero = ?, camasSimples = ?, camasDobles = ?, precioPorNoche = ? WHERE id = ?;";
        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setInt(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setFloat(4, dato.getPrecioPorNoche());
            stat.setInt(5, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }
            for (int i = 0; i < habitaciones.size(); i++) {
                if (dato.getId() == habitaciones.get(i).getId()) {
                    habitaciones.set(i, dato);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public void borrar(Habitacion dato) throws SQLException {
        PreparedStatement stat = null;
        String DELETE = "DELETE FROM habitaciones WHERE id = ?;";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, dato.getId());
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }
            habitaciones.remove(dato);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public void borrar(int id) throws SQLException {
        PreparedStatement stat = null;
        String DELETEFORID = "DELETE FROM habitaciones WHERE id = ?;";
        try {
            stat = cnn.getCo().prepareStatement(DELETEFORID);
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        cnn = Conexion.iniciarConnection();
        String SELECTONE = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones WHERE id = ?;";
        PreparedStatement stat;
        ResultSet rs;
        Habitacion habitacion = null;
        try {
            stat = cnn.getCo().prepareStatement(SELECTONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                habitacion = new Habitacion(
                    rs.getInt("id"),
                    rs.getInt("numero"),
                    rs.getInt("camasSimples"),
                    rs.getInt("camasDobles"),
                    rs.getFloat("precioPorNoche")
                );
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return habitacion;
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitaciones;";
        Statement stat = null;
        ResultSet rs = null;
        List<Habitacion> habitaciones = new ArrayList<>();
        try {
            stat = cnn.getCo().createStatement();
            rs = stat.executeQuery(SELECTALL);
            while (rs.next()) {
                habitaciones.add(new Habitacion(
                    rs.getInt("id"),
                    rs.getInt("numero"),
                    rs.getInt("camasSimples"),
                    rs.getInt("camasDobles"),
                    rs.getFloat("precioPorNoche")
                ));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return habitaciones;
    }
}