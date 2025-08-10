
package com.mycompany.hotel.dto;

import java.sql.Date;

/**
 * Data Transfer Object (DTO) para la entidad {@link com.mycompany.hotel.model.Reserva}.
 * 
 * <p>Su propósito es trasladar información de una reserva entre las capas del sistema
 * (controlador, servicio, repositorio) sin exponer directamente la entidad del modelo.</p>
 * 
 * <p>En este DTO, en lugar de objetos completos de {@code Pasajero} y {@code Habitacion},
 * se manejan sus identificadores para simplificar la transferencia de datos.</p>
 * 
 * @author Rocio
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
