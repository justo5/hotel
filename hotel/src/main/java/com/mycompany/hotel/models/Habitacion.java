package com.mycompany.hotel.models;

import java.util.Objects;
import java.math.BigDecimal;
/**
 *
 * @author justcode
 */
public class Habitacion {
    private int id;
    private int numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;

    public Habitacion(int numero, int camasSimples, int camasDobles, int aInt3, BigDecimal precioPorNoche) {
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }

    public Habitacion() {
        // Constructor vacío
    }

    // Setters con validación
    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número de habitación no puede ser negativo.");
        }
        this.numero = numero;
    }

    public void setCamasSimples(int camasSimples) {
        if (camasSimples < 0) {
            throw new IllegalArgumentException("El número de camas simples no puede ser negativo.");
        }
        this.camasSimples = camasSimples;
    }

    public void setCamasDobles(int camasDobles) {
        if (camasDobles < 0) {
            throw new IllegalArgumentException("El número de camas dobles no puede ser negativo.");
        }
        this.camasDobles = camasDobles;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        if (precioPorNoche.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio por noche no puede ser negativo.");
        }
        this.precioPorNoche = precioPorNoche;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public int getCamasSimples() {
        return camasSimples;
    }

    public int getCamasDobles() {
        return camasDobles;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    // hashCode y equals corregidos
    @Override
    public int hashCode() {
        return Objects.hash(id, numero, camasSimples, camasDobles, precioPorNoche);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Habitacion that = (Habitacion) obj;
        return id == that.id &&
               numero == that.numero &&
               camasSimples == that.camasSimples &&
               camasDobles == that.camasDobles &&
               precioPorNoche.compareTo(that.precioPorNoche) == 0;
    }

    // toString mejorado
    @Override
    public String toString() {
        return "Habitacion{" +
               "id=" + id +
               ", numero=" + numero +
               ", camasSimples=" + camasSimples +
               ", camasDobles=" + camasDobles +
               ", precioPorNoche=" + precioPorNoche +
               '}';
    }
}