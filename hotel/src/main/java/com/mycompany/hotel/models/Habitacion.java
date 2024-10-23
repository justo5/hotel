/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

/**
 *
 * @author justcode
 */
public class Habitacion {
    private int numero;
    private int camasSimples;
    private int camasDobles;
    private float precioPorNoche;

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
    public String toString() {
        return "habitacion{" + "numero=" + numero + ", camasSimples=" + camasSimples + ", camasDobles=" + camasDobles + ", precioPorNoche=" + precioPorNoche + '}';
    }
}   
