/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.model;

import java.util.Objects;

/**
 *
 * @author justcode
 */
public class Habitacion {
    private int id;
    private int numero;
    private int camasSimples;
    private int camasDobles;
    private float precioPorNoche;

    public Habitacion(int numero, int camasSimples, int camasDobles, float precioPorNoche) {
     this.numero = numero;
     this.camasSimples = camasSimples;
     this.camasDobles = camasDobles;
     this.precioPorNoche = precioPorNoche;
    }

    public Habitacion() {
      
    }

    public Habitacion(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }

    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }

    public void setPrecioPorNoche(float precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    public int getId(){
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

    public float getPrecioPorNoche() {
        return precioPorNoche;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.numero);
        hash = 89 * hash + this.camasSimples;
        hash = 89 * hash + this.camasDobles;
        hash = 89 * hash + Float.floatToIntBits(this.precioPorNoche);
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
        final Habitacion other = (Habitacion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.camasSimples != other.camasSimples) {
            return false;
        }
        if (this.camasDobles != other.camasDobles) {
            return false;
        }
        if (Float.floatToIntBits(this.precioPorNoche) != Float.floatToIntBits(other.precioPorNoche)) {
            return false;
        }
        return Objects.equals(this.numero, other.numero);
    }
    
    
    @Override
    public String toString() {
        return "habitacion{" + "numero=" + numero + ", camasSimples=" + camasSimples + ", camasDobles=" + camasDobles + ", precioPorNoche=" + precioPorNoche + '}';
    }
}   
