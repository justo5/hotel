/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.dto;

import java.util.Objects;

/**
 * Clase que representa el DTO (Data Transfer Object) de un Pasajero.
 * Contiene los mismos atributos que la entidad Pasajero pero está diseñada
 * específicamente para transferir datos entre diferentes capas de la aplicación.
 * 
 * @author mi pc
 */
public class PasajeroDTO {

    private int id;
    private String nombre;
    private String apellido;
    private int DNI;
    private int telefono;
    private String email;

    /**
     * Constructor por defecto sin parámetros.
     */
    public PasajeroDTO() {
    }

    /**
     * Constructor con parámetros básicos del pasajero (sin ID).
     * 
     * @param nombre Nombre del pasajero
     * @param apellido Apellido del pasajero
     * @param DNI Documento Nacional de Identidad
     * @param telefono Número de teléfono
     * @param email Dirección de correo electrónico
     */
    public PasajeroDTO(String nombre, String apellido, int DNI, int telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Constructor completo con todos los atributos del pasajero.
     * 
     * @param id Identificador único del pasajero
     * @param nombre Nombre del pasajero
     * @param apellido Apellido del pasajero
     * @param DNI Documento Nacional de Identidad
     * @param telefono Número de teléfono
     * @param email Dirección de correo electrónico
     */
    public PasajeroDTO(int id, String nombre, String apellido, int DNI, int telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el ID del pasajero.
     * @return Identificador único del pasajero
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del pasajero.
     * @return Nombre del pasajero
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del pasajero.
     * @return Apellido del pasajero
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el DNI del pasajero.
     * @return Documento Nacional de Identidad
     */
    public int getDNI() {
        return DNI;
    }

    /**
     * Obtiene el teléfono del pasajero.
     * @return Número de teléfono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Obtiene el email del pasajero.
     * @return Dirección de correo electrónico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el ID del pasajero.
     * @param id Identificador único del pasajero
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece el nombre del pasajero.
     * @param nombre Nombre del pasajero
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el apellido del pasajero.
     * @param apellido Apellido del pasajero
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Establece el DNI del pasajero.
     * @param DNI Documento Nacional de Identidad
     */
    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    /**
     * Establece el teléfono del pasajero.
     * @param telefono Número de teléfono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Establece el email del pasajero.
     * @param email Dirección de correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve una representación en cadena del pasajero (nombre y apellido).
     * @return Cadena que representa al pasajero
     */
    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * Compara este PasajeroDTO con otro objeto para determinar igualdad.
     * La comparación se basa únicamente en el ID del pasajero.
     * 
     * @param o Objeto a comparar
     * @return true si los objetos son iguales (mismo ID), false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PasajeroDTO that = (PasajeroDTO) o;
        return this.getId() == that.getId();
    }

    /**
     * Genera un código hash para el pasajero basado en su ID.
     * @return Código hash del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}