
package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.mapper.ReservaMapper;
import com.mycompany.hotel.model.Reserva;
import com.mycompany.hotel.repository.ReservaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio relacionada con las reservas.
 * <p>
 * Esta clase actúa como intermediaria entre la capa de presentación (o controladores)
 * y la capa de acceso a datos ({@link ReservaDAO}), utilizando un {@link ReservaMapper}
 * para convertir entre entidades y DTOs.
 * </p>
 *
 * <p>Valida datos antes de invocar a la capa DAO, asegurando integridad y consistencia.</p>
 * 
 * @author Rocio
 */
public class ReservaService {
    
     /** Objeto DAO encargado de la persistencia de reservas. */
    private ReservaDAO reservaDAO;
    
    /** Mapper para conversión entre {@link Reserva} y {@link ReservaDTO}. */
    private ReservaMapper reservaMapper;

     /**
     * Constructor que inicializa el DAO y el mapper.
     */
    public ReservaService() {
        this.reservaDAO = ReservaDAO.getInstancia();
        this.reservaMapper = new ReservaMapper(); // Instancia del mapper
    }

    
    /**
     * Convierte una lista de entidades {@link Reserva} en una lista de {@link ReservaDTO}.
     *
     * @param reservas lista de reservas en formato entidad.
     * @return lista de reservas en formato DTO.
     */
    private List<ReservaDTO> mapearLista(List<Reserva> reservas) {
        List<ReservaDTO> dtos = new ArrayList<>();
        for (Reserva reserva : reservas) {
            dtos.add(reservaMapper.toDTO(reserva));
        }
        return dtos;
    }

     /**
     * Crea una nueva reserva en la base de datos.
     *
     * @param dto objeto {@link ReservaDTO} con los datos de la reserva.
     * @throws SQLException si ocurre un error al guardar la reserva.
     * @throws IllegalArgumentException si los datos son nulos o incompletos.
     */
    public void crearReserva(ReservaDTO dto) throws SQLException {
        if (dto == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        if (dto.getChekin() == null || dto.getCheckout() == null) {
            throw new IllegalArgumentException("Las fechas de check-in y check-out son obligatorias.");
        }
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.crear(reserva);
    }
    
    /**
     * Actualiza únicamente la información de una reserva existente.
     *
     * @param dto objeto {@link ReservaDTO} con los nuevos datos.
     * @throws SQLException si ocurre un error en la actualización.
     * @throws IllegalArgumentException si el DTO es nulo o el ID es inválido.
     */
    public void actualizarReservaSola(ReservaDTO dto) throws SQLException {
        if (dto == null || dto.getId() <= 0) {
            throw new IllegalArgumentException("Datos inválidos para actualizar la reserva.");
        }
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, reserva.getId());
    }

     /**
     * Elimina una reserva de la base de datos.
     *
     * @param dto objeto {@link ReservaDTO} que representa la reserva a eliminar.
     * @throws SQLException si ocurre un error en la eliminación.
     * @throws IllegalArgumentException si el DTO es nulo o el ID es inválido.
     */
    public void borrarReserva(ReservaDTO dto) throws SQLException {
        if (dto == null || dto.getId() <= 0) {
            throw new IllegalArgumentException("La reserva a borrar es inválida.");
        }
        reservaDAO.borrar(dto.getId());
    }

     /**
     * Elimina una reserva por su ID.
     *
     * @param id identificador de la reserva.
     * @throws SQLException si ocurre un error en la eliminación.
     * @throws IllegalArgumentException si el ID es inválido.
     */
    public void borrarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("La reserva a borrar es inválida.");
        }
        reservaDAO.borrar(id);
    }
    
     /**
     * Actualiza una reserva identificada por su ID.
     *
     * @param dto objeto {@link ReservaDTO} con los nuevos datos.
     * @param id identificador de la reserva a actualizar.
     * @throws SQLException si ocurre un error en la actualización.
     * @throws IllegalArgumentException si los datos son inválidos.
     */
    public void actualizarReserva(ReservaDTO dto, int id) throws SQLException {
        if (dto == null || id <= 0) {
            throw new IllegalArgumentException("Datos inválidos para actualizar la reserva.");
        }
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, id);
    }
    
    /**
     * Recupera una reserva por su ID.
     *
     * @param id identificador de la reserva.
     * @return la reserva en formato {@link ReservaDTO}.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws IllegalArgumentException si el ID es inválido o no existe la reserva.
     */
    public ReservaDTO recuperarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        Reserva reserva = reservaDAO.recuperarPorId(id);
        if (reserva == null) {
            throw new IllegalArgumentException("No se encontro reserva con ese ID");
        }
        return reservaMapper.toDTO(reserva);
    }
    
    /**
     * Recupera todas las reservas almacenadas.
     *
     * @return lista de {@link ReservaDTO}.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public List<ReservaDTO> recuperarTodos() throws SQLException {
        return mapearLista(reservaDAO.recuperarTodos());
    }

     /**
     * Busca reservas filtrando por nombre o apellido del pasajero.
     *
     * @param termino cadena de búsqueda.
     * @return lista de {@link ReservaDTO} que coincidan con el filtro.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws IllegalArgumentException si el término está vacío o es nulo.
     */
    public List<ReservaDTO> buscarPasajero(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPasajero(termino));
    }
    
    /**
     * Busca reservas por fecha de check-in.
     *
     * @param termino fecha o parte de la fecha en formato {@code yyyy-MM-dd}.
     * @return lista de {@link ReservaDTO} que coincidan con la búsqueda.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws IllegalArgumentException si el término está vacío o es nulo.
     */
    public List<ReservaDTO> buscarPorCheckin(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorCheckin(termino));
    }
    
     /**
     * Busca reservas por fecha de check-out.
     *
     * @param termino fecha o parte de la fecha en formato {@code yyyy-MM-dd}.
     * @return lista de {@link ReservaDTO} que coincidan con la búsqueda.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws IllegalArgumentException si el término está vacío o es nulo.
     */
    public List<ReservaDTO> buscarPorCheckout(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorCheckout(termino));
    }

     /**
     * Busca reservas filtrando por número de habitación.
     *
     * @param termino número o parte del número de habitación.
     * @return lista de {@link ReservaDTO} que coincidan con el filtro.
     * @throws SQLException si ocurre un error en la consulta.
     * @throws IllegalArgumentException si el término está vacío o es nulo.
     */
    public List<ReservaDTO> buscarPorHabitacion(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorHabitacion(termino));
    }
}