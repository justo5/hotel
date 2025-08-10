package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.model.Habitacion;
import java.sql.Connection;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.utils.Conexion;
import com.mycompany.hotel.model.Habitacion;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) para gestionar operaciones CRUD relacionadas con la entidad Habitacion.
 * Implementa la interfaz Icrud<Habitacion> para definir operaciones básicas sobre habitaciones.
 * Interactúa directamente con la base de datos usando JDBC.
 * @author mauro
 */
public class HabitacionDAO implements Icrud<Habitacion> {

    private static HabitacionDAO instancia;
    private List<Habitacion> habitaciones = new ArrayList();
    private static Conexion cnn = Conexion.iniciarConnection();
    
/**
 *  Constructor privado para aplicar el patrón Singleton.
 */
    private HabitacionDAO() {
    }
    
/**
 * Devuelve una instancia única de HabitacionDAO (Singleton).
 * @return : Retorna una instancia única de HabitacionDAO.
 */
    public static HabitacionDAO getInstancia() {
        if (HabitacionDAO.instancia == null) {
            HabitacionDAO.instancia = new HabitacionDAO();
        }
        return HabitacionDAO.instancia;
    }
    
/**
 * Inserta una nueva habitación en la base de datos.
 * @param dato : Objeto Habitacion con los datos a guardar.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de inserción.
 */
    @Override
    public void crear(Habitacion dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        cnn = Conexion.iniciarConnection();
        String INSERT = "INSERT INTO habitacion(numero,camasSimples,camasDobles,precioPorNoche) VALUES(?,?,?,?);";

        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setBigDecimal(4, dato.getPrecioPorNoche());

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
                habitaciones.add(dato);
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
    
/**
 * Actualiza una habitación existente en la base de datos según su ID.
 * @param dato : Objeto Habitacion con los nuevos datos.
 * @param id : Identificador de la habitación a actualizar.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */
    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
        PreparedStatement stat;
        final String UPDATE = "UPDATE habitacion SET numero = ?, camasSimples = ?,camasDobles = ?, precioPorNoche = ? WHERE id = ? ;";
        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setString(1, dato.getNumero());
            stat.setInt(2, dato.getCamasSimples());
            stat.setInt(3, dato.getCamasDobles());
            stat.setBigDecimal(4, dato.getPrecioPorNoche());
            stat.setInt(5, id);
            if (stat.executeUpdate() == 0) {
                throw new SQLException("Puede que no se haya actualizado");
            }
            for (int i = 0; i < habitaciones.size(); i++) {
                if (dato == habitaciones.get(i)) {
                    habitaciones.set(i, dato);
                }
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {

            cnn.cerrarConexion();
        }
    }

/**
 * Elimina una habitación específica según sus atributos.
 * @param dato : Objeto Habitacion a eliminar.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */    
    @Override
    public void borrar(Habitacion dato) throws SQLException {
        PreparedStatement stat = null;
        String DELETE = "DELETE from habitacion WHERE id = ? AND numero = ? AND camasSimples = ? AND camasDobles = ? AND precioPorNoche = ? ;";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, dato.getId());
            stat.setString(2, dato.getNumero());
            stat.setInt(3, dato.getCamasSimples());
            stat.setInt(4, dato.getCamasDobles());
            stat.setBigDecimal(4, dato.getPrecioPorNoche());

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
    
/**
 *  Elimina una habitación según su ID.
 * @param id : Identificador de la habitación a eliminar.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */
    @Override
    public void borrar(int id) throws SQLException {
        PreparedStatement stat = null;
        String DELETE = "DELETE FROM habitacion WHERE id = ?";
        try {
            stat = cnn.getCo().prepareStatement(DELETE);
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

/**
 * Recupera una habitación por su ID.
 * @param id : Identificador de la habitación.
 * @return : Retorna un Objeto Habitacion si se encuentra; null si no existe.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */
    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
        cnn = Conexion.iniciarConnection();
        String SELECTONE = "SELECT id,numero,camasSimples,camasDobles,precioPorNoche FROM habitacion WHERE id=?;";
        PreparedStatement stat;
        ResultSet rs;
        Habitacion habitacion = null;
        try{
            stat = cnn.getCo().prepareStatement(SELECTONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            while (rs.next()) {
                habitacion = new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBigDecimal(5));
            }
            return habitacion;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return habitacion;
    }
    
/**
 * Recupera todas las habitaciones registradas en la base de datos.
 * @return : Retorna una Lista de objetos Habitacion.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */
    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
       cnn = Conexion.iniciarConnection();
       String SELECT = "SELECT id,numero,camasSimples,camasDobles,precioPorNoche FROM habitacion;";
       Statement stat = null;
       ResultSet rs = null;
       List<Habitacion> habitaciones = new ArrayList();
         try {
            stat = cnn.getCo().prepareStatement(SELECT);
            rs = stat.executeQuery(SELECT);

            while (rs.next()) {
                habitaciones.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBigDecimal(5)));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HabitacionDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return habitaciones;

    }

/**
 * Busca habitaciones cuyo número contenga la cadena proporcionada.
 * @param numero : Parte del número de habitación a buscar.
 * @return : Retorna una Lista de habitaciones que coinciden.
 */
    public List<Habitacion> buscarPorNumero(String numero){
        String SELECTBYNUMERO = "SELECT id,numero,camasSimples,camasDobles,precioPorNoche FROM habitacion WHERE numero LIKE ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Habitacion> habitaciones = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYNUMERO);
            stat.setString(1, "%" + numero + "%");
            rs = stat.executeQuery();

            while (rs.next()) {
                   habitaciones.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBigDecimal(5)));
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }
    
/**
 * Busca habitaciones cuyo precio contenga el valor proporcionado.
 * @param precio : Parte del precio a buscar (como texto).
 * @return : Retorna una Lista de habitaciones coincidentes.
 */
    public List<Habitacion> buscarPorPrecio(String precio){
        String SELECTBYPRECIO = "SELECT id,numero,camasSimples,camasDobles,precioPorNoche FROM habitacion WHERE precioPorNoche LIKE ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Habitacion> habitaciones = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYPRECIO);
            stat.setString(1, "%" + precio + "%");
            rs = stat.executeQuery();

            while (rs.next()) {
                   habitaciones.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBigDecimal(5)));
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }
    
/**
 * Busca habitaciones que tengan la capacidad de alojar al menos la cantidad de pasajeros indicada.
 * @param cantidad : Cantidad mínima de pasajeros que debe alojar la habitación.
 * @return :  Retorna una Lista de habitaciones disponibles con esa capacidad.
 */    
    public List<Habitacion> buscarPorCantidadPasajeros(String cantidad){
        String SELECTBYCAPACIDAD = "SELECT id, numero, camasSimples, camasDobles, precioPorNoche FROM habitacion WHERE (camasSimples + (camasDobles * 2)) >= ?";
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Habitacion> habitaciones = new ArrayList();
        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try {
            stat = cnn.getCo().prepareStatement(SELECTBYCAPACIDAD);
            stat.setString(1, "%" + cantidad + "%");
            rs = stat.executeQuery();

            while (rs.next()) {
                   habitaciones.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBigDecimal(5)));
            }
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

        return habitaciones;
    }
    
/**
 * Busca habitaciones disponibles entre dos fechas, que puedan alojar al menos cierta cantidad de personas.
 * @param checkin : Fecha de entrada.
 * @param checkout : Fecha de salida.
 * @param cantidad : Cantidad de personasa a alojar.
 * @return : Retorna una Lista de habitaciones disponibles.
 */
    public List<Habitacion> buscarDisponibles(Date checkin, Date checkout, int cantidad) {
     List<Habitacion> habitaciones = new ArrayList<>();
     String sql = "{CALL obtenerHabitacionesDisponibles(?, ?, ?)}";

    try (PreparedStatement stat = cnn.getCo().prepareStatement(sql)) {
        stat.setDate(1,  new java.sql.Date(checkin.getTime()));
        stat.setDate(2, new java.sql.Date(checkout.getTime())); // salida
        stat.setInt(3,cantidad);  

        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            habitaciones.add(new Habitacion(
                rs.getInt("id"),
                rs.getString("numero"),
                rs.getInt("camasSimples"),
                rs.getInt("camasDobles"),
                rs.getBigDecimal("precioPorNoche")
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        cnn.cerrarConexion();
    }

    return habitaciones;
}

/**
 * Devuelve el número de habitación correspondiente al ID proporcionado.
 * @param idHabitacion : Identificador de la habitación.
 * @return : Retorna Número de habitación como String, o null si no se encuentra.
 * @throws SQLException : Utilizado por si ocurre un error en la operación de actualización.
 */
    public String obtenerNumeroHabitacionPorId(int idHabitacion) throws SQLException {
    cnn = Conexion.iniciarConnection();
    String numeroHabitacion = null;
    String sql = "SELECT numero FROM habitacion WHERE id = ?";

    try (PreparedStatement stmt = cnn.getCo().prepareStatement(sql)) {
        stmt.setInt(1, idHabitacion);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            numeroHabitacion = rs.getString("numero");
        }
    }

    return numeroHabitacion;
}
}
