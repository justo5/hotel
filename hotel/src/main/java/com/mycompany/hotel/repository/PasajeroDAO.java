package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.utils.Conexion;

import com.mycompany.hotel.model.Pasajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PasajeroDAO implements Icrud<Pasajero> {

    private static PasajeroDAO instancia;
    public static List<Pasajero> pasajeros = new ArrayList();
    private static Conexion cnn = Conexion.iniciarConnection();

    private PasajeroDAO() {
    }

    public static PasajeroDAO getInstancia() {
        if (PasajeroDAO.instancia == null) {
            PasajeroDAO.instancia = new PasajeroDAO();
        }
        return PasajeroDAO.instancia;
    }

    @Override

    public void crear(Pasajero dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        cnn = Conexion.iniciarConnection();
        String INSERT = "INSERT INTO pasajeros(Nombre,Apellido,Dni,Telefono,CorreoElectronico) VALUES(?,?,?,?,?);";
        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
                pasajeros.add(dato);
            }
            if (stat.executeUpdate() == 0) {
                throw new SQLException("puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

    }

    @Override
    public void actualizar(Pasajero dato, int id) throws SQLException {
        PreparedStatement stat;
        final String UPDATE = "UPDATE pasajeros SET Nombre = ?, Apellido = ?, Dni = ?, Telefono = ?, CorreoElectronico = ? WHERE id = ? ;";
        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }
           for (int i = 0; i < pasajeros.size(); i++) {
                if(dato == pasajeros.get(i)){
                   pasajeros.set(i, dato);
                 }
        }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {

            cnn.cerrarConexion();
        }

    }

    @Override
    public void borrar(Pasajero dato) throws SQLException {
        PreparedStatement stat = null;
        String DELETE = "DELETE from pasajeros WHERE id = ? AND Nombre = ? AND Apellido = ? AND Dni = ? AND Telefono = ? AND CorreoElectronico = ? ;";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, dato.getId());
            stat.setString(2, dato.getNombre());
            stat.setString(3, dato.getApellido());
            stat.setInt(4, dato.getDNI());
            stat.setInt(5, dato.getTelefono());
            stat.setString(6, dato.getEmail());
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }
            pasajeros.remove(dato);

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

    }

    @Override
    public void borrar(int id) throws SQLException {
        PreparedStatement stat = null;
        String DELETEFORID = "DELETE from pasajeros WHERE id = ?;";
        try {
            stat = cnn.getCo().prepareStatement(DELETEFORID);
            stat.setInt(1, id);
            int FilaABorrar = stat.executeUpdate();

            if (FilaABorrar == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    @Override
    public Pasajero recuperarPorId(int id) throws SQLException {
        cnn = Conexion.iniciarConnection();
        String SELECTONE = "SELECT id,Nombre,Apellido,Dni,Telefono,CorreoElectronico FROM pasajeros WHERE id=?;";
        PreparedStatement stat;
        ResultSet rs;
        Pasajero pasajero = null;
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }
        try {
            stat = cnn.getCo().prepareStatement(SELECTONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                pasajero = new Pasajero(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
            }
            return pasajero;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return pasajero;
    }

    @Override
    public List<Pasajero> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id,Nombre,Apellido,Dni,Telefono,CorreoElectronico FROM pasajeros";
        Statement stat = null;
        ResultSet rs = null;
        List<Pasajero> pasajeros = new ArrayList();
        try {
            stat = cnn.getCo().prepareStatement(SELECTALL);
            rs = stat.executeQuery(SELECTALL);

            while (rs.next()) {
                pasajeros.add(new Pasajero(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return pasajeros;

    }
}
