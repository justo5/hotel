/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.service;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.mapper.PasajeroMapper;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.repository.PasajeroDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que maneja la lógica relacionada con los pasajeros.
 * Utiliza PasajeroDAO para acceder a la base de datos y PasajeroMapper para
 * transformar entre entidades y DTOs.
 * 
 * @author mi pc
 */
public class PasajeroService {

    private PasajeroDAO pasajeroDAO;
    private PasajeroMapper pasajeroMapper;

    /**
     * Constructor que inicializa el DAO y el Mapper.
     */
    public PasajeroService() {
        this.pasajeroDAO = PasajeroDAO.getInstancia();
        this.pasajeroMapper = new PasajeroMapper();
    }

    /**
     * Actualiza un pasajero sin cambiar su ID.
     * 
     * @param dto Datos del pasajero a actualizar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void actualizarPasajeroSolo(PasajeroDTO dto) throws SQLException {
        Pasajero pasajero = pasajeroMapper.toEntity(dto);
        pasajeroDAO.actualizarPasajero(pasajero);
    }

    /**
     * Crea un nuevo pasajero.
     * 
     * @param dto Datos del pasajero a crear.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void crearPasajero(PasajeroDTO dto) throws SQLException {
        Pasajero pasajero = pasajeroMapper.toEntity(dto);
        pasajeroDAO.crear(pasajero);
    }

    /**
     * Borra un pasajero utilizando un DTO.
     * 
     * @param dto Datos del pasajero a borrar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void borrarPasajero(PasajeroDTO dto) throws SQLException {
        Pasajero pasajero = pasajeroMapper.toEntity(dto);
        pasajeroDAO.borrar(pasajero);
    }

    /**
     * Borra un pasajero por su ID.
     * 
     * @param id ID del pasajero a eliminar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void borrarPorId(int id) throws SQLException {
        pasajeroDAO.borrar(id);
    }

    /**
     * Actualiza un pasajero con un nuevo ID.
     * 
     * @param dto Datos actualizados.
     * @param id ID del pasajero a actualizar.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public void actualizarPasajero(PasajeroDTO dto, int id) throws SQLException {
        Pasajero pasajero = pasajeroMapper.toEntity(dto);
        pasajeroDAO.actualizar(pasajero, id);
    }

    /**
     * Recupera un pasajero por su ID.
     * 
     * @param id ID del pasajero.
     * @return Objeto DTO del pasajero.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public PasajeroDTO recuperarPorId(int id) throws SQLException {
        Pasajero pasajero = pasajeroDAO.recuperarPorId(id);
        return pasajeroMapper.toDTO(pasajero);
    }

    /**
     * Recupera todos los pasajeros registrados.
     * 
     * @return Lista de objetos PasajeroDTO.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> recuperarTodos() throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.recuperarTodos();
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();

        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }

        return pasajerosDTO;
    }

    /**
     * Busca pasajeros por nombre.
     * 
     * @param nombre Nombre a buscar.
     * @return Lista de pasajeros con ese nombre.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> buscarPorNombre(String nombre) throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorNombre(nombre);
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }
        return pasajerosDTO;
    }

    /**
     * Busca pasajeros por apellido.
     * 
     * @param apellido Apellido a buscar.
     * @return Lista de pasajeros con ese apellido.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> buscarPorApellido(String apellido) throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorApellido(apellido);
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }
        return pasajerosDTO;
    }

    /**
     * Busca pasajeros por número de DNI.
     * 
     * @param dni Número de documento.
     * @return Lista de pasajeros que coincidan con ese DNI.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> buscarPorDni(String dni) throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorDni(dni);
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }
        return pasajerosDTO;
    }

    /**
     * Busca pasajeros por correo electrónico.
     * 
     * @param correo Correo electrónico a buscar.
     * @return Lista de pasajeros con ese correo.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> buscarPorCorreo(String correo) throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorCorreo(correo);
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }
        return pasajerosDTO;
    }

    /**
     * Busca pasajeros por número de teléfono.
     * 
     * @param telefono Teléfono a buscar.
     * @return Lista de pasajeros con ese número.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<PasajeroDTO> buscarPorTelefono(String telefono) throws SQLException {
        List<Pasajero> pasajeros = pasajeroDAO.buscarPorTelefono(telefono);
        List<PasajeroDTO> pasajerosDTO = new ArrayList<>();
        for (Pasajero pasajero : pasajeros) {
            pasajerosDTO.add(pasajeroMapper.toDTO(pasajero));
        }
        return pasajerosDTO;
    }

    /**
     * Obtiene el nombre completo de un pasajero dado su ID.
     * 
     * @param id ID del pasajero.
     * @return Nombre completo del pasajero.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public String obtenerNombreCompletoPorId(int id) throws SQLException {
        return pasajeroDAO.obtenerNombreCompletoPorId(id);
    }
}