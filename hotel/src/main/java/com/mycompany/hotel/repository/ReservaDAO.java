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
       if (dato == null || dato.getoPasajero() == null || dato.getoHabitacion() == null) {
            throw new IllegalArgumentException("La reserva, pasajero o habitación no pueden ser nulos.");
        }

        String INSERT = "INSERT INTO reserva(checkin,checkout,id_pasajero,id_habitacion,senia) VALUES(?,?,?,?,?)";

        try (
               PreparedStatement stat = cnn.getCo().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setDate(1, new java.sql.Date(dato.getChekin().getTime()));
            stat.setDate(2, new java.sql.Date(dato.getCheckout().getTime()));
            stat.setInt(3, dato.getoPasajero().getId());
            stat.setInt(4, dato.getoHabitacion().getId());
            stat.setFloat(5, dato.getSenia());

            int filas = stat.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo guardar la reserva.");
            }

            try (ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    dato.setId(rs.getInt(1));
                }
            }
        }
       

    }

    @Override
    public void actualizar(Reserva dato, int id) throws SQLException {
        if (dato == null) {
            throw new IllegalArgumentException("La reserva a actualizar no puede ser nula.");
        }

        String UPDATE = "UPDATE reserva SET checkin = ?, checkout = ?, id_pasajero = ?, id_habitacion = ?, senia = ? WHERE id = ?";

        try ( PreparedStatement stat = cnn.getCo().prepareStatement(UPDATE)) {
            stat.setDate(1, new java.sql.Date(dato.getChekin().getTime()));
            stat.setDate(2, new java.sql.Date(dato.getCheckout().getTime()));
            stat.setInt(3, dato.getoPasajero().getId());
            stat.setInt(4, dato.getoHabitacion().getId());
            stat.setFloat(5, dato.getSenia());
            stat.setInt(6, id);

            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se encontró la reserva con id " + id + " para actualizar.");
            }
        }
    }

    @Override
    public void borrar(Reserva dato) throws SQLException {
        borrar(dato.getId());
    }

    @Override
    public void borrar(int id) throws SQLException {
        String DELETE = "DELETE FROM reserva WHERE id = ?";

        try (PreparedStatement stat = cnn.getCo().prepareStatement(DELETE)) {
            stat.setInt(1, id);
            if (stat.executeUpdate() == 0) {
                throw new SQLException("No se encontró la reserva con id " + id + " para eliminar.");
            }
        }
    }

    @Override
    public Reserva recuperarPorId(int id) throws SQLException {
        String SQL = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE r.id = ?";

        if (cnn == null || cnn.getCo() == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SQL)) {
            stat.setInt(1, id);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return mapearReserva(rs);
                }
            }
        }
        return null; // Si no encuentra la reserva
    }

    @Override
    public List<Reserva> recuperarTodos() throws SQLException {
        String SQL = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id ";

        if (cnn == null || cnn.getCo() == null) {
            throw new IllegalStateException("Database connection not initialized");
        }

        List<Reserva> reservas = new ArrayList<>();

        try (PreparedStatement stat = cnn.getCo().prepareStatement(SQL); ResultSet rs = stat.executeQuery()) {

            while (rs.next()) {
                reservas.add(mapearReserva(rs));
            }
            return reservas;
        }
    }

    public List<Reserva> buscarPasajero(String termino) throws SQLException {
        if (termino == null || termino.length() > 100) {
            throw new IllegalArgumentException("Término de búsqueda inválido.");
        }
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE CONCAT(p.nombre, ' ', p.apellido) LIKE ?";

        try (PreparedStatement stmt = cnn.getCo().prepareStatement(sql)) {
            stmt.setString(1, "%" + termino + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        }
        return reservas;
    }

    public List<Reserva> buscarPorCheckin(String termino) throws SQLException {

        if (termino == null || termino.length() > 100) {
            throw new IllegalArgumentException("Término de búsqueda inválido.");
        }
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE DATE_FORMAT(r.checkin, '%Y-%m-%d') LIKE ?";

        try (PreparedStatement stmt = cnn.getCo().prepareStatement(sql)) {
            stmt.setString(1, "%" + termino + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        }
        return reservas;

    }

    public List<Reserva> buscarPorCheckout(String termino) throws SQLException {
        if (termino == null || termino.length() > 100) {
            throw new IllegalArgumentException("Término de búsqueda inválido.");
        }
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE DATE_FORMAT(r.checkout, '%Y-%m-%d') LIKE ?";

        try (PreparedStatement stmt = cnn.getCo().prepareStatement(sql)) {
            stmt.setString(1, "%" + termino + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        }
        return reservas;
    }

    public List<Reserva> buscarPorHabitacion(String termino) throws SQLException {

        if (termino == null || termino.length() > 100) {
            throw new IllegalArgumentException("Término de búsqueda inválido.");
        }
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT r.id AS reserva_id, r.checkin, r.checkout, r.senia, "
                + "p.id AS id_pasajero, p.nombre, p.apellido, p.dni, p.telefono, p.email, "
                + "h.id AS id_habitacion, h.numero, h.camassimples, h.camasdobles, h.preciopornoche "
                + "FROM reserva r "
                + "JOIN pasajero p ON r.id_pasajero = p.id "
                + "JOIN habitacion h ON r.id_habitacion = h.id "
                + "WHERE h.numero LIKE ?";

        try (PreparedStatement stmt = cnn.getCo().prepareStatement(sql)) {
            stmt.setString(1, "%" + termino + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapearReserva(rs));
                }
            }
        }
        return reservas;
    }

    public Reserva mapearReserva(ResultSet rs) throws SQLException {
        // Crear pasajero
        Pasajero pasajero = new Pasajero();
        pasajero.setId(rs.getInt("id_pasajero"));
        pasajero.setNombre(rs.getString("nombre"));
        pasajero.setApellido(rs.getString("apellido"));
        pasajero.setDNI(rs.getInt("dni"));
        pasajero.setTelefono(rs.getInt("telefono"));
        pasajero.setEmail(rs.getString("email"));

        // Crear habitacion
        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getInt("id_habitacion"));
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

        return reserva;
    }
}
