/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.controller;

import com.mycompany.hotel.dto.PasajeroDTO;
import com.mycompany.hotel.interfaz.Icrud;
import com.mycompany.hotel.service.PasajeroService;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar las operaciones CRUD de pasajeros.
 * Implementa la interfaz Icrud<PasajeroDTO> y delega la lógica de negocio al PasajeroService.
 * 
 * @author justcode
 */
public class PasajeroController implements Icrud<PasajeroDTO> {
  
    private PasajeroService pasajeroService;
   
    /**
     * Constructor que inicializa el servicio de pasajeros.
     */
    public PasajeroController() {
        this.pasajeroService = new PasajeroService();
    }

    /**
     * Actualiza los datos de un pasajero existente.
     * 
     * @param dato Objeto PasajeroDTO con los datos actualizados
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public void actualizarPasajero(PasajeroDTO dato) throws SQLException {
        pasajeroService.actualizarPasajeroSolo(dato);
    }

    /**
     * Crea un nuevo pasajero en el sistema.
     * 
     * @param dato Objeto PasajeroDTO con los datos del nuevo pasajero
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public void crear(PasajeroDTO dato) throws SQLException {
        pasajeroService.crearPasajero(dato);
    }

    /**
     * Actualiza los datos de un pasajero identificado por su ID.
     * 
     * @param dato Objeto PasajeroDTO con los datos actualizados
     * @param id ID del pasajero a actualizar
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public void actualizar(PasajeroDTO dato, int id) throws SQLException {
        pasajeroService.actualizarPasajero(dato, id);
    }

    /**
     * Elimina un pasajero del sistema.
     * 
     * @param dato Objeto PasajeroDTO que representa al pasajero a eliminar
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public void borrar(PasajeroDTO dato) throws SQLException {
        pasajeroService.borrarPasajero(dato);
    }

    /**
     * Elimina un pasajero del sistema según su ID.
     * 
     * @param id ID del pasajero a eliminar
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public void borrar(int id) throws SQLException {
       pasajeroService.borrarPorId(id);
    }

    /**
     * Recupera un pasajero por su ID.
     * 
     * @param id ID del pasajero a recuperar
     * @return PasajeroDTO con los datos del pasajero encontrado
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public PasajeroDTO recuperarPorId(int id) throws SQLException {
        return pasajeroService.recuperarPorId(id);
    }

    /**
     * Recupera todos los pasajeros del sistema.
     * 
     * @return Lista de PasajeroDTO con todos los pasajeros
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public List<PasajeroDTO> recuperarTodos() throws SQLException {
        return pasajeroService.recuperarTodos();
    }
   
    /**
     * Busca pasajeros por coincidencia parcial en el nombre.
     * 
     * @param nombre Nombre o parte del nombre a buscar
     * @return Lista de PasajeroDTO que coinciden con el criterio
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public List<PasajeroDTO> buscarPorNombre(String nombre) throws SQLException {
        return pasajeroService.buscarPorNombre(nombre);
    }
    
    /**
     * Busca pasajeros por coincidencia parcial en el apellido.
     * 
     * @param apellido Apellido o parte del apellido a buscar
     * @return Lista de PasajeroDTO que coinciden con el criterio
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public List<PasajeroDTO> buscarPorApellido(String apellido) throws SQLException {
        return pasajeroService.buscarPorApellido(apellido);
    }
    
    /**
     * Busca pasajeros por coincidencia parcial en el correo electrónico.
     * 
     * @param correo Correo electrónico o parte del mismo a buscar
     * @return Lista de PasajeroDTO que coinciden con el criterio
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public List<PasajeroDTO> buscarPorCorreo(String correo) throws SQLException {
        return pasajeroService.buscarPorCorreo(correo);
    }
    
    /**
     * Busca pasajeros por número de DNI exacto.
     * 
     * @param dni DNI a buscar
     * @return Lista de PasajeroDTO que coinciden con el criterio
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public List<PasajeroDTO> buscarPorDni(String dni) throws SQLException {
        return pasajeroService.buscarPorDni(dni);
    }
    
    /**
     * Busca pasajeros por número de teléfono exacto.
     * 
     * @param telefono Número de teléfono a buscar
     * @return Lista de PasajeroDTO que coinciden con el criterio
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public List<PasajeroDTO> buscarPorTelefono(String telefono) throws SQLException {
        return pasajeroService.buscarPorTelefono(telefono);
    }
     
    /**
     * Obtiene el nombre completo (nombre + apellido) de un pasajero por su ID.
     * 
     * @param id ID del pasajero
     * @return Nombre completo del pasajero
     * @throws SQLException Si ocurre un error al acceder a la base de datos
     */
    public String obtenerNombreCompletoPorId(int id) throws SQLException {
        return pasajeroService.obtenerNombreCompletoPorId(id);
    }
}