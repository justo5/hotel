
package com.mycompany.hotel.model;

import java.util.Date;
import java.util.Objects;

/**
 * Representa una reserva en el sistema del hotel.
 * <p>
 * Una reserva está compuesta por:
 * <ul>
 *   <li>ID único</li>
 *   <li>Fecha de check-in</li>
 *   <li>Fecha de check-out</li>
 *   <li>Pasajero asociado</li>
 *   <li>Habitación asignada</li>
 *   <li>Seña abonada</li>
 * </ul>
 * </p>
 *
 * <p>
 * Esta clase forma parte de la capa de modelo y se utiliza para mapear 
 * los datos almacenados en la base de datos a objetos Java.
 * </p>
 * 
 * @author Rocio
 */
public class Reserva {
    private int id; 
    private Date chekin;
    private Date checkout; 
    private Pasajero oPasajero;
    private Habitacion oHabitacion;
    private float senia;
    
      
    public Reserva(int id,Date chekin, Date checkout, Pasajero oPasajero, Habitacion oHabitacion, float senia) {
        this.id = id;
        this.chekin = chekin;
        this.checkout = checkout;
        this.oPasajero = oPasajero;
        this.oHabitacion = oHabitacion;
        this.senia = senia;
    }
        
     public Reserva(Date chekin, Date checkout, Pasajero oPasajero, Habitacion oHabitacion, float senia) {
        this.chekin = chekin;
        this.checkout = checkout;
        this.oPasajero = oPasajero;
        this.oHabitacion = oHabitacion;
        this.senia = senia;
    }

    public Reserva() {

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

    public void setChekin(Date chekin) {
        this.chekin = chekin;
    }

    public void setCheckout(Date checkout) {
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


}
