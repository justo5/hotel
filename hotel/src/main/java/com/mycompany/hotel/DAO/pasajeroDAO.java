
package com.mycompany.hotel.DAO;

import DAO.DAO;
import com.mycompany.hotel.models.pasajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class pasajeroDAO implements DAO<pasajero> {

    private static pasajeroDAO instance;
    private List<pasajero> pasajeros = new ArrayList();
    private static Connection cnn = null;

    private pasajeroDAO() {

    }

    public static pasajeroDAO getInstance() {
        if (pasajeroDAO.instance == null) {
            pasajeroDAO.instance = new pasajeroDAO();
        }
        return pasajeroDAO.instance;

    }

    @Override
    public void crear(pasajero dato) throws SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String INSERT = "INSERT INTO pasajeros(Nombre,Apellido,Dni,Telefono,CorreoElectronico) VALUES(?,?,?,?,?);";

        try {

            stat = cnn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
            }
            if (stat.executeUpdate() == 0) {
                throw new SQLException("puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }

        }

    }

    @Override
    public void actualizar(pasajero dato, int id) throws SQLException {
        PreparedStatement stat = null;
        final String UPDATE = "UPDATE pasajeros SET Nombre = ?, Apellido = ?, Dni = ?, Telefono = ?, CorreoElectronico = ? WHERE id = ? ;";
        try {
            stat = cnn.prepareStatement(UPDATE);
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }

        }
    }

    @Override
    public void borrar(pasajero dato) throws SQLException {
         PreparedStatement stat = null;
        String DELETE = "DELETE from pasajeros WHERE id = ? AND Nombre = ? AND Apellido = ? AND Dni = ? AND Telefono = ? AND CorreoElectronico = ? ;";
        try {
            stat = cnn.prepareStatement(DELETE);
            stat.setInt(1, dato.getId());
            stat.setString(2, dato.getNombre());
            stat.setString(3, dato.getApellido());
            stat.setInt(4, dato.getDNI());
            stat.setInt(5, dato.getTelefono());
            stat.setString(6, dato.getEmail());
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }
        }
        
    }

    @Override
    public void borrar(int id) throws SQLException {
       PreparedStatement stat = null;
        String DELETEFORID = "DELETE from pasajeros WHERE id = ?;";
        try {
            stat = cnn.prepareStatement(DELETEFORID);
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public pasajero recuperarPorId(int id) throws SQLException {
       String SELECTONE = "SELECT id,Nombre,Apellido,Dni,Telefono,Email FROM pasajeros WHERE id=?;";
        PreparedStatement stat = null;
        ResultSet rs = null;
        pasajero pasajero = null;
        try {
            stat = cnn.prepareStatement(SELECTONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                pasajero = new pasajero();
                pasajero.setId(rs.getInt("id"));
                pasajero.setNombre(rs.getString("Nombre"));
                pasajero.setApellido(rs.getString("Apellido"));
                pasajero.setDNI(rs.getInt("Dni"));
                pasajero.setTelefono(rs.getInt("Telefono"));
                pasajero.setEmail(rs.getString("Email"));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }

        }

        return pasajero;
    }
    

    @Override
    public List<pasajero> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id,Nombre,Apellido,Dni,Telefono,Email FROM pasajeros";
        Statement stat = null;
        ResultSet rs = null;
        List<pasajero> pasajeros = new ArrayList();
        try {
            stat = cnn.createStatement();
            stat.executeQuery(SELECTALL);

            while (rs.next()) {
                pasajero pasajero = new pasajero();
                pasajero.setId(rs.getInt("id"));
                pasajero.setNombre(rs.getString("Nombre"));
                pasajero.setApellido(rs.getString("Apellido"));
                pasajero.setDNI(rs.getInt("Dni"));
                pasajero.setTelefono(rs.getInt("Telefono"));
                pasajero.setEmail(rs.getString("Email"));

                pasajeros.add(pasajero);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(pasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

                }
            }

            return pasajeros;

        }
    }

}
