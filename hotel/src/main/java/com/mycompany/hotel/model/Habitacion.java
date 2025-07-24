/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.model;

import java.util.Objects;
import java.math.BigDecimal;

/**
 *
 * @author justcode
 */
public class Habitacion {
    private int id;
    private String numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;


    public Habitacion(int id, String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {
        this.id = id;
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }

 
    public Habitacion(String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {

     this.numero = numero;
     this.camasSimples = camasSimples;
     this.camasDobles = camasDobles;
     this.precioPorNoche = precioPorNoche;
    }

    public Habitacion() {
      
    }

  
     
    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }

    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    public int getId(){
     return id;
    }

    public String getNumero() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.numero);
        hash = 29 * hash + this.camasSimples;
        hash = 29 * hash + this.camasDobles;
        hash = 29 * hash + Objects.hashCode(this.precioPorNoche);
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
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.precioPorNoche, other.precioPorNoche);
    }

    
    
    
    @Override
    public String toString() {
        return "habitacion{" + "numero=" + numero + ", camasSimples=" + camasSimples + ", camasDobles=" + camasDobles + ", precioPorNoche=" + precioPorNoche + '}';
    }
}   
