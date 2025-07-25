package com.mycompany.hotel.repository;

import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.model.Reserva;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.model.Habitacion;
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

    private static ReservaDAO instancia;
    private List<Reserva> reservas = new ArrayList();
    private static Conexion cnn = Conexion.iniciarConnection();
    private PasajeroDAO pasajeroDAO;
    private HabitacionDAO habitacionDAO;

    private ReservaDAO() {
        this.pasajeroDAO = PasajeroDAO.getInstancia();
        this.habitacionDAO = HabitacionDAO.getInstancia();
    }

    public static ReservaDAO getInstancia() {
        if (ReservaDAO.instancia == null) {
            ReservaDAO.instancia = new ReservaDAO();
        }
        return ReservaDAO.instancia;
    }

    @Override
    public void crear(Reserva dato) throws SQLException {
        PreparedStatement stat;
        ResultSet rs = null;
        cnn = Conexion.iniciarConnection();
        String INSERT = "INSERT INTO reserva(checkin,checkout,id_pasajero,id_habitacion,senia) VALUES(?,?,?,?,?); ";

        try {
            stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setDate(1, (Date) dato.getChekin());
            stat.setDate(2, (Date) dato.getCheckout());
            stat.setInt(3, dato.getoPasajero().getId());
            stat.setInt(4, dato.getoHabitacion().getId());
            stat.setFloat(5, dato.getSenia());

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                dato.setId(rs.getInt(1));
                reservas.add(dato);
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
    public void actualizar(Reserva dato, int id) throws SQLException {
        String UPDATE = "UPDATE reserva SET checkin = ?, checkout = ?, id_pasajero = ?, id_habitacion = ?, senia = ? WHERE id = ?;";
        PreparedStatement stat = null;
        cnn = Conexion.iniciarConnection();

        try {
            stat = cnn.getCo().prepareStatement(UPDATE);
            stat.setDate(1, new java.sql.Date(dato.getChekin().getTime()));
            stat.setDate(2, new java.sql.Date(dato.getCheckout().getTime()));
            stat.setInt(3, dato.getoPasajero().getId());
            stat.setInt(4, dato.getoHabitacion().getId());
            stat.setFloat(5, dato.getSenia());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo actualizar la reserva con id " + id);
            }
        } finally {
            if (stat != null) {
                stat.close();
            }
            cnn.cerrarConexion();
        }
    }

    @Override
    public void borrar(Reserva dato) throws SQLException {
        borrar(dato.getId());
    }

    @Override
    public void borrar(int id) throws SQLException {
        String DELETE = "DELETE FROM reserva WHERE id = ?;";
        PreparedStatement stat = null;
        cnn = Conexion.iniciarConnection();

        try {
            stat = cnn.getCo().prepareStatement(DELETE);
            stat.setInt(1, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se pudo eliminar la reserva con id " + id);
            }
        } finally {
            if (stat != null) {
                stat.close();
            }
            cnn.cerrarConexion();
        }
    }

    @Override
    public Reserva recuperarPorId(int id) throws SQLException {
        cnn = Conexion.iniciarConnection();
        String SELECTONE = "SELECT checkin,checkout,id_pasajero,id_habitacion,senia FROM reserva WHERE id = ?";
        PreparedStatement stat;
        ResultSet rs = null;
        Reserva reserva = null;
        Pasajero pasajero = null;
        Habitacion habitacion = null;

        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }
        try {
            stat = cnn.getCo().prepareStatement(SELECTONE);
            stat.setInt(1, id);
            while (rs.next()) {
                int id_pasajero = rs.getInt(4);
                int id_habitacion = rs.getInt(5);
                pasajero = pasajeroDAO.recuperarPorId(id_pasajero);
                habitacion = habitacionDAO.recuperarPorId(id_habitacion);
                reserva = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), pasajero, habitacion, rs.getFloat(6));
            }

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }
        return reserva;
    }

    @Override
    public List<Reserva> recuperarTodos() throws SQLException {
        String SELECTALL = "SELECT id, checkin, checkout, id_pasajero, id_habitacion, senia FROM reserva;";
        List<Reserva> reservas = new ArrayList<>();
        PreparedStatement stat;
        ResultSet rs = null;
        Reserva reserva = null;
        Pasajero pasajero = null;
        Habitacion habitacion = null;

        if (cnn == null) {
            throw new IllegalStateException("Database connection not initialized");
        }
        try {
            stat = cnn.getCo().prepareStatement(SELECTALL);
            rs = stat.executeQuery(SELECTALL);
            while (rs.next()) {
                int id_pasajero = rs.getInt(4);
                int id_habitacion = rs.getInt(5);
                pasajero = pasajeroDAO.recuperarPorId(id_pasajero);
                habitacion = habitacionDAO.recuperarPorId(id_habitacion);
                reserva = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), pasajero, habitacion, rs.getFloat(6));
                reservas.add(reserva);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PasajeroDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            cnn.cerrarConexion();
        }

        return reservas;
    }

    public List<Reserva> buscarPasajero(String terminoBusqueda) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS pasajero_id, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS habitacion_id, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE CONCAT(p.nombre, ' ', p.apellido) LIKE ?";

        PreparedStatement stmt = cnn.getCo().prepareStatement(sql);
        stmt.setString(1, "%" + terminoBusqueda + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // Crear pasajero
            Pasajero pasajero = new Pasajero();
            pasajero.setId(rs.getInt("pasajero_id"));
            pasajero.setNombre(rs.getString("nombre"));
            pasajero.setApellido(rs.getString("apellido"));
            pasajero.setDNI(rs.getInt("dni"));
            pasajero.setTelefono(rs.getInt("telefono"));
            pasajero.setEmail(rs.getString("email"));

            // Crear habitacion
            Habitacion habitacion = new Habitacion();
            habitacion.setId(rs.getInt("habitacion_id"));
            habitacion.setNumero(rs.getString("numero"));
            habitacion.setCamasSimples(rs.getInt("camassimples"));
            habitacion.setCamasDobles(rs.getInt("camasdobles"));
            habitacion.setPrecioPorNoche(rs.getBigDecimal("preciopornoche"));

            // Crear reserva
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("reserva_id"));
            reserva.setChekin(rs.getDate("checkin"));
            reserva.setCheckout(rs.getDate("checkout"));
            reserva.setSenia(rs.getFloat("senia"));
            reserva.setoPasajero(pasajero);
            reserva.setoHabitacion(habitacion);

            reservas.add(reserva);

        }

        return reservas;
    }

    public List<Reserva> buscarPorCheckin(String valor) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS pasajero_id, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS habitacion_id, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE DATE_FORMAT(r.checkin, '%Y-%m-%d') LIKE ?";

        PreparedStatement stmt = cnn.getCo().prepareStatement(sql);
        stmt.setString(1, "%" + valor + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // Pasajero
            Pasajero pasajero = new Pasajero();
            pasajero.setId(rs.getInt("pasajero_id"));
            pasajero.setNombre(rs.getString("nombre"));
            pasajero.setApellido(rs.getString("apellido"));
            pasajero.setDNI(rs.getInt("dni"));
            pasajero.setTelefono(rs.getInt("telefono"));
            pasajero.setEmail(rs.getString("email"));

            // Habitación
            Habitacion habitacion = new Habitacion();
            habitacion.setId(rs.getInt("habitacion_id"));
            habitacion.setNumero(rs.getString("numero"));
            habitacion.setCamasSimples(rs.getInt("camassimples"));
            habitacion.setCamasDobles(rs.getInt("camasdobles"));
            habitacion.setPrecioPorNoche(rs.getBigDecimal("preciopornoche"));

            // Reserva
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("reserva_id"));
            reserva.setChekin(rs.getDate("checkin"));
            reserva.setCheckout(rs.getDate("checkout"));
            reserva.setSenia(rs.getFloat("senia"));
            reserva.setoPasajero(pasajero);
            reserva.setoHabitacion(habitacion);

            reservas.add(reserva);
        }

        return reservas;
    }

    public List<Reserva> buscarPorCheckout(String valor) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS pasajero_id, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS habitacion_id, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE DATE_FORMAT(r.checkout, '%Y-%m-%d') LIKE ?";

        PreparedStatement stmt = cnn.getCo().prepareStatement(sql);
        stmt.setString(1, "%" + valor + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // Pasajero
            Pasajero pasajero = new Pasajero();
            pasajero.setId(rs.getInt("pasajero_id"));
            pasajero.setNombre(rs.getString("nombre"));
            pasajero.setApellido(rs.getString("apellido"));
            pasajero.setDNI(rs.getInt("dni"));
            pasajero.setTelefono(rs.getInt("telefono"));
            pasajero.setEmail(rs.getString("email"));

            // Habitación
            Habitacion habitacion = new Habitacion();
            habitacion.setId(rs.getInt("habitacion_id"));
            habitacion.setNumero(rs.getString("numero"));
            habitacion.setCamasSimples(rs.getInt("camassimples"));
            habitacion.setCamasDobles(rs.getInt("camasdobles"));
            habitacion.setPrecioPorNoche(rs.getBigDecimal("preciopornoche"));

            // Reserva
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("reserva_id"));
            reserva.setChekin(rs.getDate("checkin"));
            reserva.setCheckout(rs.getDate("checkout"));
            reserva.setSenia(rs.getFloat("senia"));
            reserva.setoPasajero(pasajero);
            reserva.setoHabitacion(habitacion);

            reservas.add(reserva);
        }

        return reservas;
    }
    
     public List<Reserva> buscarPorHabitacion(String terminoBusqueda) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS pasajero_id, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS habitacion_id, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE h.numero LIKE ?";

        PreparedStatement stmt = cnn.getCo().prepareStatement(sql);
        stmt.setString(1, "%" + terminoBusqueda + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // Crear pasajero
            Pasajero pasajero = new Pasajero();
            pasajero.setId(rs.getInt("pasajero_id"));
            pasajero.setNombre(rs.getString("nombre"));
            pasajero.setApellido(rs.getString("apellido"));
            pasajero.setDNI(rs.getInt("dni"));
            pasajero.setTelefono(rs.getInt("telefono"));
            pasajero.setEmail(rs.getString("email"));

            // Crear habitacion
            Habitacion habitacion = new Habitacion();
            habitacion.setId(rs.getInt("habitacion_id"));
            habitacion.setNumero(rs.getString("numero"));
            habitacion.setCamasSimples(rs.getInt("camassimples"));
            habitacion.setCamasDobles(rs.getInt("camasdobles"));
            habitacion.setPrecioPorNoche(rs.getBigDecimal("preciopornoche"));

            // Crear reserva
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("reserva_id"));
            reserva.setChekin(rs.getDate("checkin"));
            reserva.setCheckout(rs.getDate("checkout"));
            reserva.setSenia(rs.getFloat("senia"));
            reserva.setoPasajero(pasajero);
            reserva.setoHabitacion(habitacion);

            reservas.add(reserva);

        }

        return reservas;
    }
}
