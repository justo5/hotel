/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

/**
 *
 * @author justcode
 */
public class Pasajero {
    private int id;
    private String nombre;
    private String apellido;
    private int DNI;
    private int telefono;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
   
    @Override
    public String toString() {
        return "pasajero{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI + ", telefono=" + telefono + ", email=" + email + '}';
    }
}
