package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.utils.Conexion;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HabitacionDAO implements Icrud<Habitacion> {

    private static HabitacionDAO instancia;
    private static Conexion cnn = Conexion.iniciarConnection();

    private HabitacionDAO() {
    }

    public static HabitacionDAO getInstancia() {
        if (instancia == null) {
            instancia = new HabitacionDAO();
        }
        return instancia;
    }

    @Override
    public void crear(Habitacion dato) throws SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String INSERT = "INSERT INTO habitaciones(numero, camas_simples, camas_dobles, precio_por_noche) VALUES(?, ?, ?, ?);";
        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setInt(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setBigDecimal(4, dato.getPrecioPorNoche());
            stat.executeUpdate();
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1)); // Asigna el ID generado automáticamente
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        }
    }

    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        PreparedStatement stat = null;
        final String UPDATE = "UPDATE habitaciones SET numero = ?, camas_simples = ?, camas_dobles = ?, precio_por_noche = ? WHERE id = ?;";
        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setInt(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setBigDecimal(4, dato.getPrecioPorNoche());
            stat.setInt(5, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (stat != null) stat.close();
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
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (stat != null) stat.close();
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
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (stat != null) stat.close();
        }
    }

    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        String SELECTONE = "SELECT id, numero, camas_simples, camas_dobles, precio_por_noche FROM habitaciones WHERE id = ?;";
        PreparedStatement stat = null;
        ResultSet rs = null;
        Habitacion habitacion = null;
        try {
            stat = cnn.getCo().prepareStatement(SELECTONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                habitacion = new Habitacion(
                    rs.getInt("id"),
                    rs.getInt("numero"),
                    rs.getInt("camas_simples"),
                    rs.getInt("camas_dobles"),
                    rs.getBigDecimal("precio_por_noche")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        }
        return habitacion;
    }

    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id, numero, camas_simples, camas_dobles, precio_por_noche FROM habitaciones;";
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
                    rs.getInt("camas_simples"),
                    rs.getInt("camas_dobles"),
                    rs.getBigDecimal("precio_por_noche")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Propaga la excepción para que el servicio la maneje
        } finally {
            if (rs != null) rs.close();
            if (stat != null) stat.close();
        }
        return habitaciones;
    }
}