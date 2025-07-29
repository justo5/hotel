/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.mapper.ReservaMapper;
import com.mycompany.hotel.model.Reserva;
import com.mycompany.hotel.repository.ReservaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mi pc
 */
public class ReservaService {

    private ReservaDAO reservaDAO;
    private ReservaMapper reservaMapper;

    public ReservaService() {
        this.reservaDAO = ReservaDAO.getInstancia();
        this.reservaMapper = new ReservaMapper(); // Instancia del mapper
    }

    private List<ReservaDTO> mapearLista(List<Reserva> reservas) {
        List<ReservaDTO> dtos = new ArrayList<>();
        for (Reserva reserva : reservas) {
            dtos.add(reservaMapper.toDTO(reserva));
        }
        return dtos;
    }

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

    public void actualizarReservaSola(ReservaDTO dto) throws SQLException {
        if (dto == null || dto.getId() <= 0) {
            throw new IllegalArgumentException("Datos inválidos para actualizar la reserva.");
        }
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, reserva.getId());
    }

    public void borrarReserva(ReservaDTO dto) throws SQLException {
        if (dto == null || dto.getId() <= 0) {
            throw new IllegalArgumentException("La reserva a borrar es inválida.");
        }
        reservaDAO.borrar(dto.getId());
    }

    public void borrarPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("La reserva a borrar es inválida.");
        }
        reservaDAO.borrar(id);
    }

    public void actualizarReserva(ReservaDTO dto, int id) throws SQLException {
        if (dto == null || id <= 0) {
            throw new IllegalArgumentException("Datos inválidos para actualizar la reserva.");
        }
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, id);
    }

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

    public List<ReservaDTO> recuperarTodos() throws SQLException {
        return mapearLista(reservaDAO.recuperarTodos());
    }

    public List<ReservaDTO> buscarPasajero(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPasajero(termino));
    }

    public List<ReservaDTO> buscarPorCheckin(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorCheckin(termino));
    }

    public List<ReservaDTO> buscarPorCheckout(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorCheckout(termino));
    }

    public List<ReservaDTO> buscarPorHabitacion(String termino) throws SQLException {
        if (termino == null || termino.trim().isEmpty()) {
            throw new IllegalArgumentException("Término de búsqueda vacío.");
        }
        return mapearLista(reservaDAO.buscarPorHabitacion(termino));
    }
}