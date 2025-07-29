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

/**
 * Representa una habitación dentro del sistema hotelero.
 * Esta clase modela a una habitación real que puede estar disponible para reserva. Contiene información como su número, 
 * cantidad de camas simples, camas dobles y precio por noche.
 * 
 *  
 * @author mauro
 */
public class Habitacion {
    private int id;
    private String numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;
    

/**
 * Constructor completo con todos los atributos, incluido el ID.
 * @param id : Identificador único de la habitación.
 * @param numero : Número o código de la habitación.
 * @param camasSimples : Cantidad de camas simples.
 * @param camasDobles : Cantidad de camas dobles.
 * @param precioPorNoche : Precio por noche de la habitación.
 */
    public Habitacion(int id, String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {
        this.id = id;
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }

    
/**
 * Constructor sin ID, útil para crear una habitación antes de que sea persistida en base de datos.
 * @param numero : Número o código de la habitación.
 * @param camasSimples : Cantidad de camas simples.
 * @param camasDobles : Cantidad de camas dobles.
 * @param precioPorNoche : Precio por noche de la habitación.
 */
    public Habitacion(String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {

     this.numero = numero;
     this.camasSimples = camasSimples;
     this.camasDobles = camasDobles;
     this.precioPorNoche = precioPorNoche;
    }

/**
 * Constructor vacío, crea una instancia con valores por defecto.
 * Los atributos deben ser establecidos posteriormente mediante los métodos set.
 */
    public Habitacion() {
      
    }

  
  
/**
 *  Establece el ID de la habitación.
 * @param id :  El nuevo identificador.
 */    
    public void setId(int id) {
        this.id = id;
    }
    
/**
 * Establece el número o código de la habitación.
 * @param numero : El nuevo número
 */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
/**
 * Establece la cantidad de camas simples.
 * @param camasSimples : cantidad de camas simples.
 */
    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }
    
/**
 * Establece la cantidad de camas dobles.
 * @param camasDobles:  cantidad de camas dobles
 */
    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }
    
/**
 * Establece el precio por noche de la habitación.
 * @param precioPorNoche : nuevo precio
 */
    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
/**
 * Obtiene el Identificador único de la habitación.
 * @return : Retorna el id.
 */    
    public int getId(){
     return id;
    }
    
/**
 * Obtiene el número o código de la habitación.
 * @return : Retorna el número de habitación.
 */
    public String getNumero() {
        return numero;
    }
    
/**
 * Obtiene la cantidad de camas simples.
 * @return : Retorna el número de camas simples
 */
    public int getCamasSimples() {
        return camasSimples;
    }
    
/**
 *  Obtiene la cantidad de camas dobles.
 * @return : Retorna el número de camas dobles
 */
    public int getCamasDobles() {
        return camasDobles;
    }
    
/**
 * Obtiene el precio por noche de la habitación.
 * @return : Retorna el precio por noche como BigDecimal
 */
    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }
    
/**
 *  Calcula el código hash de la habitación basado en todos sus atributos.
 * @return : Retoran el valor hash.
 */
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
    

   
/**
 * Compara esta habitación con otra para determinar si son iguales.
 * Dos habitaciones son iguales si todos sus atributos coinciden. 
 * @param obj: el objeto a comparar
 * @return : Retorna true si son iguales, false en caso contrario
 */
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
    

    
    
/**
 * Devuelve una representación en texto de la habitación.
 * @return : Retorna una cadena con número, cantidad de camas y precio
 */
    @Override
    public String toString() {
        return "habitacion{" + "numero=" + numero + ", camasSimples=" + camasSimples + ", camasDobles=" + camasDobles + ", precioPorNoche=" + precioPorNoche + '}';
    }
}   
