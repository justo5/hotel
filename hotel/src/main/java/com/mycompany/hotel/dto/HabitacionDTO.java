
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

/**
 * Clase DTO (Data Transfer Object) que representa una habitación del hotel.
 * Esta clase se utiliza para encapsular los datos de una habitación
 * y facilitar su transferencia entre las distintas partes de la aplicación
 * como interfaces gráficas o controladores.
 * 
 * Contiene información como número de habitación, cantidad de camas y precio por noche.
 * 
 */

public class HabitacionDTO {

    private int id;
    private String numero;
    private int camasSimples;
    private int camasDobles;
    private BigDecimal precioPorNoche;


/**
 * Crea una instancia de HabitacionDTO con valores por defecto.
 * Los atributos deben ser establecidos posteriormente mediante los métodos set.
 */
    public HabitacionDTO() {
    }


 
/**
 * Constructor que inicializa todos los campos de la habitación.
 * @param id : Identificador único de la habitación.
 * @param numero : Número identificador de la habitación.
 * @param camasSimples : Cantidad de camas simples.
 * @param camasDobles : Cantidad de camas dobles.
 * @param precioPorNoche  : Precio por noche de la habitación. 
 */
    public HabitacionDTO(int id, String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {

        this.id = id;
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }
  
/**
 * Constructor sin ID, usado cuando el ID aún no está asignado (por ejemplo, al crear una nueva habitación).
 * @param numero : Número de la habitación.
 * @param camasSimples : Cantidad de camas simples.
 * @param camasDobles : Cantidad de camas dobles.
 * @param precioPorNoche : Precio por noche de la habitación.
 */
    public HabitacionDTO(String numero, int camasSimples, int camasDobles, BigDecimal precioPorNoche) {
        this.numero = numero;
        this.camasSimples = camasSimples;
        this.camasDobles = camasDobles;
        this.precioPorNoche = precioPorNoche;
    }


/**
 *  Obtiene el ID de la habitación.
 * @return : Retorna el id. 
 */
    public int getId() {
        return id;
    }


/**
 * Obtiene el número de la habitación.
 * @return : Retorna el número.
 */    
    public String getNumero() {
        return numero;
    }
   
/**
 * Obtiene la cantidad de camas simples.
 * @return : Retorna la cantidad de camas simples.
 */
    public int getCamasSimples() {
        return camasSimples;
    }
   
/**
 * Obtiene la cantidad de camas dobles.
 * @return : Retorna la cantidad de camas dobles.
 */
    public int getCamasDobles() {
        return camasDobles;
    }
    
/**
 * Obtiene el precio por noche de cada habitación.
 * @return : Retorna el precio por noche.
 */
    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }
    
/**
 * Establece el ID de la habitación.
 * @param id : Nuevo id.
 */    
    public void setId(int id) {
        this.id = id;
    }
    
/**
 * Establece el número de la habitación.
 * @param numero :  Nuevo número.
 */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
/**
 * Establece la cantidad de camas simples de la habitación.
 * @param camasSimples : Nueva cantidad de camas simples.
 */
    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }
    
/**
 * Establce la cantidad de camas dobles de la habitación.
 * @param camasDobles : Nueva cantidad de camas dobles.
 */
    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }
    
/**
 * Establece el precio por noche de la habitación.
 * @param precioPorNoche : Nuevo precio por noche de la habitación.
 */
    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    
/**
 * Devuelve una representación en texto de la habitación, mostrando su número y precio por noche.
 * @return : Retorna una cadena de texto con la información básica de la habitación.
 */
    @Override
    public String toString() {
        return "Hab. " + numero + " - $" + precioPorNoche + "/Noche";
    }
    
/**
 * Compara dos objetos HabitacionDTO según su ID.
 * @param o : Objeto a comparar.
 * @return : Retorna true si los IDs son iguales.
 */
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
    
/**
 * El método hashCode() devuelve un número entero (int) que representa de forma compacta un objeto en memoria. 
 * Es como una "firma numérica" del objeto que se usa principalmente para almacenar y buscar objetos rápidamente.
 * En este caso, devuelve el hash basado en el ID de la habitación.
 * @return : Retorna Código hash.
 */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    

    
}
