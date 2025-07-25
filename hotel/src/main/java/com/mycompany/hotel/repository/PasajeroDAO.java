package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.model.Pasajero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar operaciones CRUD de la entidad Pasajero.
 * Implementa la interfaz Icrud<Pasajero>.
 * Utiliza JDBC para la conexión con la base de datos.
 */
public class PasajeroDAO implements Icrud<Pasajero> {

    /**
     * Instancia única de la clase PasajeroDAO (Singleton).
     */
    private static PasajeroDAO instancia;

    /**
     * Lista en memoria que contiene los pasajeros registrados.
     */
    public static List<Pasajero> pasajeros = new ArrayList<>();

    /**
     * Objeto para la conexión con la base de datos.
     */
    private static Conexion cnn = Conexion.iniciarConnection();

    /**
     * Constructor privado para evitar instanciación externa (patrón Singleton).
     */
    private PasajeroDAO() {}

    /**
     * Devuelve la instancia única de PasajeroDAO.
     * @return instancia única de PasajeroDAO.
     */
    public static PasajeroDAO getInstancia() {
        if (instancia == null) {
            instancia = new PasajeroDAO();
        }
        return instancia;
    }

    /**
     * Actualiza los datos de un pasajero existente en la base de datos y en la lista en memoria.
     * @param dato Objeto Pasajero con los nuevos datos.
     * @throws SQLException si ocurre un error en la actualización.
     */
    public void actualizarPasajero(Pasajero dato) throws SQLException {
        String UPDATE = "UPDATE pasajero SET nombre = ?, apellido = ?, DNI = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement stat = cnn.getCo().prepareStatement(UPDATE)) {
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());
            stat.setInt(6, dato.getId());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }
            for (int i = 0; i < pasajeros.size(); i++) {
                if (dato == pasajeros.get(i)) {
                    pasajeros.set(i, dato);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    /**
     * Inserta un nuevo pasajero en la base de datos y lo agrega a la lista en memoria.
     * @param dato Pasajero a crear.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    @Override
    public void crear(Pasajero dato) throws SQLException {
        String INSERT = "INSERT INTO pasajero(nombre,apellido,DNI,telefono,email) VALUES(?,?,?,?,?)";
        try (PreparedStatement stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setString(1, dato.getNombre());
            stat.setString(2, dato.getApellido());
            stat.setInt(3, dato.getDNI());
            stat.setInt(4, dato.getTelefono());
            stat.setString(5, dato.getEmail());

            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya guardado");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    dato.setId(rs.getInt(1));
                    pasajeros.add(dato);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    /**
     * Actualiza los datos de un pasajero identificado por su ID.
     * @param dato Objeto Pasajero con los nuevos datos.
     * @param id ID del pasajero a actualizar.
     * @throws SQLException si ocurre un error durante la operación.
     */
    @Override
    public void actualizar(Pasajero dato, int id) throws SQLException {
        String UPDATE = "UPDATE pasajero SET nombre = ?, apellido = ?, DNI = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement stat = cnn.getCo().prepareStatement(UPDATE)) {
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
                if (dato == pasajeros.get(i)) {
                    pasajeros.set(i, dato);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }
    
    /**
     * Elimina un pasajero específico de la base de datos y de la lista en memoria.
     * @param dato Pasajero a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    @Override
    public void borrar(Pasajero dato) throws SQLException {
        String DELETE = "DELETE FROM pasajero WHERE id = ? AND nombre = ? AND apellido = ? AND DNI = ? AND telefono = ? AND email = ?";
        try (PreparedStatement stat = cnn.getCo().prepareStatement(DELETE)) {
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

    /**
     * Elimina un pasajero de la base de datos según su ID.
     * @param id ID del pasajero a eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    @Override
    public void borrar(int id) throws SQLException {
        String DELETE = "DELETE FROM pasajero WHERE id = ?";
        try (PreparedStatement stat = cnn.getCo().prepareStatement(DELETE)) {
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya borrado");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
    }

    /**
     * Recupera un pasajero desde la base de datos según su ID.
     * @param id ID del pasajero a buscar.
     * @return Objeto Pasajero si se encuentra, null en caso contrario.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    public Pasajero recuperarPorId(int id) throws SQLException {
        String SELECT = "SELECT id,nombre,apellido,DNI,telefono,email FROM pasajero WHERE id=?";
        Pasajero pasajero = null;
        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECT)) {
            stat.setInt(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    pasajero = new Pasajero(
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("apellido"), rs.getInt("DNI"),
                        rs.getInt("telefono"), rs.getString("email")
                    );
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return pasajero;
    }

    /**
     * Recupera todos los pasajeros almacenados en la base de datos.
     * @return Lista de todos los pasajeros.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    public List<Pasajero> recuperarTodos() throws SQLException {
        List<Pasajero> pasajeros = new ArrayList<>();
        String SELECT = "SELECT id,nombre,apellido,DNI,telefono,email FROM pasajero";
        try (Statement stat = cnn.getCo().createStatement();
             ResultSet rs = stat.executeQuery(SELECT)) {
            while (rs.next()) {
                pasajeros.add(new Pasajero(
                    rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getInt("DNI"),
                    rs.getInt("telefono"), rs.getString("email")
                ));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return pasajeros;
    }

    /**
     * Busca pasajeros por coincidencia parcial en el nombre.
     * @param nombre Nombre a buscar.
     * @return Lista de pasajeros cuyo nombre coincida parcialmente.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public List<Pasajero> buscarPorNombre(String nombre) throws SQLException {
        String SELECT = "SELECT * FROM pasajero WHERE nombre LIKE ?";
        List<Pasajero> pasajeros = new ArrayList<>();
        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECT)) {
            stat.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    pasajeros.add(new Pasajero(
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("apellido"), rs.getInt("DNI"),
                        rs.getInt("telefono"), rs.getString("email")
                    ));
                }
            }
        } finally {
            cnn.cerrarConexion();
        }
        return pasajeros;
    }

    /**
     * Busca pasajeros que coincidan con un DNI específico.
     * @param dni DNI a buscar.
     * @return Lista de pasajeros con ese DNI.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public List<Pasajero> buscarPorDni(String dni) throws SQLException {
        String SELECT = "SELECT * FROM pasajero WHERE DNI = ?";
        List<Pasajero> pasajeros = new ArrayList<>();
        try (PreparedStatement stat = cnn.getCo().prepareStatement(SELECT)) {
            stat.setString(1, dni);
            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    pasajeros.add(new Pasajero(
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("apellido"), rs.getInt("DNI"),
                        rs.getInt("telefono"), rs.getString("email")
                    ));
                }
            }
        } finally {
            cnn.cerrarConexion();
        }
        return pasajeros;
    }

    /**
     * Busca pasajeros por coincidencia parcial en el apellido.
     * @param apellido Apellido a buscar.
     * @return Lista de pasajeros cuyo apellido coincida parcialmente.
     */
    public List<Pasajero> buscarPorApellido(String apellido) {
        String SELECTBYAPELLIDO = "SELECT id,nombre,apellido,DNI,telefono,email FROM pasajero WHERE apellido LIKE ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Pasajero> pasajeros = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYAPELLIDO);
            stat.setString(1, "%" + apellido+ "%");
            rs = stat.executeQuery();

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

    /**
     * Busca pasajeros por coincidencia parcial en el correo electrónico.
     * @param correo Correo a buscar.
     * @return Lista de pasajeros cuyo email coincida parcialmente.
     */
    public List<Pasajero> buscarPorCorreo(String correo) {
        String SELECTBYCORREO = "SELECT id,nombre,apellido,DNI,telefono,email FROM pasajero WHERE email LIKE ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Pasajero> pasajeros = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYCORREO);
            stat.setString(1, "%" + correo + "%");
            rs = stat.executeQuery();

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

    /**
     * Busca pasajeros por número de teléfono exacto.
     * @param telefono Teléfono a buscar.
     * @return Lista de pasajeros con ese número de teléfono.
     */
    public List<Pasajero> buscarPorTelefono(String telefono) {
        String SELECTBYCORREO = "SELECT id,nombre,apellido,DNI,telefono,email FROM pasajero WHERE telefono = ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Pasajero> pasajeros = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYCORREO);
            stat.setString(1, telefono);
            rs = stat.executeQuery();

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

    /**
     * Obtiene el nombre completo (nombre + apellido) de un pasajero por su ID.
     * @param id ID del pasajero.
     * @return Nombre completo del pasajero si se encuentra, null en caso contrario.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public String obtenerNombreCompletoPorId(int id) throws SQLException {
        cnn = Conexion.iniciarConnection();
        String SELECT = "SELECT nombre, apellido FROM pasajero WHERE id = ?;";
        PreparedStatement stat;
        ResultSet rs;
        String nombreCompleto = null;

        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECT);
            stat.setInt(1, id);
            rs = stat.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                nombreCompleto = nombre + " " + apellido;
            }

            return nombreCompleto;

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

        return nombreCompleto;
    }
}
