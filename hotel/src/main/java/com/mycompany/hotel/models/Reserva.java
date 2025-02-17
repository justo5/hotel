/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

import java.util.Objects;

/**
 *
 * @author justcode
 */
public class Reserva {
    private int id; 
    private String chekin;
    private String checkout; 
    private Pasajero oPasajero;
    private Habitacion oHabitacion;
    private float senia;
    
      
    public Reserva(int id,String chekin, String checkout, Pasajero oPasajero, Habitacion oHabitacion, float senia) {
        this.id = id;
        this.chekin = chekin;
        this.checkout = checkout;
        this.oPasajero = oPasajero;
        this.oHabitacion = oHabitacion;
        this.senia = senia;
    }
        
     public Reserva(String chekin, String checkout, Pasajero oPasajero, Habitacion oHabitacion, float senia) {
        this.chekin = chekin;
        this.checkout = checkout;
        this.oPasajero = oPasajero;
        this.oHabitacion = oHabitacion;
        this.senia = senia;
    }

    public int getId() {
        return id;
    }

    public String getChekin() {
        return chekin;
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

    public float getSenia() {
        return senia;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSenia(float senia) {
        this.senia = senia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.chekin);
        hash = 31 * hash + Objects.hashCode(this.checkout);
        hash = 31 * hash + Objects.hashCode(this.oPasajero);
        hash = 31 * hash + Objects.hashCode(this.oHabitacion);
        hash = 31 * hash + Float.floatToIntBits(this.senia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.senia) != Float.floatToIntBits(other.senia)) {
            return false;
        }
        if (!Objects.equals(this.chekin, other.chekin)) {
            return false;
        }
        if (!Objects.equals(this.checkout, other.checkout)) {
            return false;
        }
        if (!Objects.equals(this.oPasajero, other.oPasajero)) {
            return false;
        }
        return Objects.equals(this.oHabitacion, other.oHabitacion);
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", chekin=" + chekin + ", checkout=" + checkout + ", oPasajero=" + oPasajero + ", oHabitacion=" + oHabitacion + ", senia=" + senia + '}';
    }

    public int getPasajero() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getHabitacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
