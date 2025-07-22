/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.dto;

import java.sql.Date;

/**
 *
 * @author mi pc
 */
public class ReservaDTO {
    
    private int id; 
    private Date chekin;
    private Date checkout; 
    private int id_pasajero;
    private int id_habitacion;
    private float senia;

    public ReservaDTO(Date chekin, Date checkout, int id_pasajero, int id_habitacion, float senia) {
        this.chekin = chekin;
        this.checkout = checkout;
        this.id_pasajero = id_pasajero;
        this.id_habitacion = id_habitacion;
        this.senia = senia;
    }

    public ReservaDTO(int id, Date chekin, Date checkout, int id_pasajero, int id_habitacion, float senia) {
        this.id = id;
        this.chekin = chekin;
        this.checkout = checkout;
        this.id_pasajero = id_pasajero;
        this.id_habitacion = id_habitacion;
        this.senia = senia;
    }

    public ReservaDTO() {
    }

    public int getId() {
        return id;
    }

    public Date getChekin() {
        return chekin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public float getSenia() {
        return senia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChekin(Date chekin) {
        this.chekin = chekin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public void setSenia(float senia) {
        this.senia = senia;
    }
    
    
}
