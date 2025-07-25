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

    public void crearReserva(ReservaDTO dto) throws SQLException {
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.crear(reserva);
    }

    public void actualizarReservaSola(ReservaDTO dto) throws SQLException {
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, 0);
    }

    public void borrarReserva(ReservaDTO dto) throws SQLException {
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.borrar(reserva);
    }

    public void borrarPorId(int id) throws SQLException {
        reservaDAO.borrar(id);
    }

    public void actualizarReserva(ReservaDTO dto, int id) throws SQLException {
        Reserva reserva = reservaMapper.toEntity(dto);
        reservaDAO.actualizar(reserva, id);
    }

    public ReservaDTO recuperarPorId(int id) throws SQLException {
        Reserva reserva = reservaDAO.recuperarPorId(id);
        ReservaDTO dto = reservaMapper.toDTO(reserva);
        return dto;
    }

    public List<ReservaDTO> recuperarTodos() throws SQLException {
        List<Reserva> reservas = reservaDAO.recuperarTodos();
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDTO dto = reservaMapper.toDTO(reserva);
            reservasDTO.add(dto);
        }

        return reservasDTO;
    }
    
    public List<ReservaDTO> buscarPasajero(String terminoBusqueda) throws SQLException {
        List<Reserva> reservas = reservaDAO.buscarPasajero(terminoBusqueda);
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDTO dto = reservaMapper.toDTO(reserva);
            reservasDTO.add(dto);
        }

        return reservasDTO;
    }
    
    public List<ReservaDTO> buscarPorCheckin(String valor) throws SQLException {
        List<Reserva> reservas = reservaDAO.buscarPorCheckin(valor);
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDTO dto = reservaMapper.toDTO(reserva);
            reservasDTO.add(dto);
        }

        return reservasDTO;
    }
    
    public List<ReservaDTO> buscarPorCheckout(String valor) throws SQLException {
        List<Reserva> reservas = reservaDAO.buscarPorCheckout(valor);
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDTO dto = reservaMapper.toDTO(reserva);
            reservasDTO.add(dto);
        }

        return reservasDTO;
    }
    public List<ReservaDTO> buscarPorHabitacion(String valor) throws SQLException {
        List<Reserva> reservas = reservaDAO.buscarPorHabitacion(valor);
        List<ReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservas) {
            ReservaDTO dto = reservaMapper.toDTO(reserva);
            reservasDTO.add(dto);
        }

        return reservasDTO;
    }
}
