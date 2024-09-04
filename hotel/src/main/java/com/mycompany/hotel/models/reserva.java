/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

/**
 *
 * @author justcode
 */
public class reserva {
    private String chekin;
    private String checkout; 
    private pasajero oPasajero;
    private habitacion oHabitacion;

    public String getChekin() {
        return chekin;
    }

    public void setChekin(String chekin) {
        this.chekin = chekin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setoPasajero(pasajero oPasajero) {
        this.oPasajero = oPasajero;
    }

    public void setoHabitacion(habitacion oHabitacion) {
        this.oHabitacion = oHabitacion;
    }

    public void setSeña(float seña) {
        this.seña = seña;
    }

    public String getCheckout() {
        return checkout;
    }

    public pasajero getoPasajero() {
        return oPasajero;
    }

    public habitacion getoHabitacion() {
        return oHabitacion;
    }

    public float getSeña() {
        return seña;
    }
    private float seña;
}
