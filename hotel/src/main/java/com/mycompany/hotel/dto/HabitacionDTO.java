package com.mycompany.hotel.dto;

import java.math.BigDecimal;

public class HabitacionDTO {
    private int id;
    private int numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;

    public HabitacionDTO() {
    }

    public HabitacionDTO(int id, int numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {
        this.id = id;
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCamasSimples() {
        return camasSimples;
    }

    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }

    public int getCamasDobles() {
        return camasDobles;
    }

    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public String toString() {
        return "HabitacionDTO{" +
                "id=" + id +
                ", numero=" + numero +
                ", camasSimples=" + camasSimples +
                ", camasDobles=" + camasDobles +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }
}
