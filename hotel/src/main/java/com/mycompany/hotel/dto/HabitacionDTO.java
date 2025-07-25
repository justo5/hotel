
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.dto;

import java.util.Objects;

import java.math.BigDecimal;
/**
 *
 * @author mi pc
 */



public class HabitacionDTO {

    private int id;
    private String numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;


    public HabitacionDTO() {
    }



    public HabitacionDTO(int id, String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {

        this.id = id;
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }
  
    public HabitacionDTO(String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }


    public int getId() {
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
    

    @Override
    public String toString() {
        return "Hab. " + numero + " - $" + precioPorNoche + "/Noche";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HabitacionDTO that = (HabitacionDTO) o;
        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    

    
}
