/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

/**
 *
 * @author justcode
 */
public class Reserva {
    private String chekin;
    private String checkout; 
    private Pasajero oPasajero;
    private Habitacion oHabitacion;
    private float seña;

    public String getChekin() {
        return chekin;
    }

    public void setChekin(String chekin) {
        this.chekin = chekin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setoPasajero(Pasajero oPasajero) {
        this.oPasajero = oPasajero;
    }

    public void setoHabitacion(Habitacion oHabitacion) {
        this.oHabitacion = oHabitacion;
    }

    public void setSeña(float seña) {
        this.seña = seña;
    }

    public String getCheckout() {
        return checkout;
    }

    public Pasajero getoPasajero() {
        return oPasajero;
    }

    public Habitacion getoHabitacion() {
        return oHabitacion;
    }

    public float getSeña() {
        return seña;
    }

    @Override
    public String toString() {
        return "reserva{" + "chekin=" + chekin + ", checkout=" + checkout + ", oPasajero=" + oPasajero + ", oHabitacion=" + oHabitacion + ", se\u00f1a=" + seña + '}';
    }
    
}
