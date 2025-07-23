/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel.models;

/** Representa una habitación de hotel con sus características principales.
  Contiene información sobre el número de habitación, cantidad de camas simples y dobles,
  y el precio por noche. Además, genera un ID para guardar en la base de datos.
 */

/** 
 *
 * @author justcode
 */
public class Habitacion {
    private int id;
    private String numero;
    private int camasSimples;
    private int camasDobles;
    private float precioPorNoche;
    
    /**
     * Se crea una habitación con los detalles especificos a traves de los paráametros.
     * @param numero: representa el numero de la habitación.
     * @param camasSimples : representa cantidad de las camas simples de la habitación.
     * @param camasDobles : representa la cantidad de camas dobles de la habitación.
     * @param precioPorNoche : representa el precio por noche de cada habitación.
     */

    public Habitacion(String numero, int camasSimples, int camasDobles, float precioPorNoche) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     /**
    * Constructor vacio para crear una habitación sin inicializar sus atributos.
    */
    public Habitacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * Establece el ID único para la habitación creada.
     * @param id : respresenta el identificador único.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Establece el número identificador de la habitación.
     * @param numero : representa  el número de la habitación.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     *  Establece la cantidad de camas simples en la habitación.
     * @param camasSimples : representa el número de camas simples.
     */
    public void setCamasSimples(int camasSimples) {
        this.camasSimples = camasSimples;
    }
    /**
     * Establece la cantidad de camas dobles en la habitación.
     * @param camasDobles : representa el número de camas dobles.
     */
    public void setCamasDobles(int camasDobles) {
        this.camasDobles = camasDobles;
    }
    /**
     * Establece el precio por noche de la habitación.
     * @param precioPorNoche : represneta el precio por noche de cada habitación.
     */
    public void setPrecioPorNoche(float precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    /**
     * Obtiene el ID único de la habitación.
     * @return : retorna el ID de la habitación.
     */
    public int getId(){
     return id;
    }
    /**
     * Obtiene el número identificador de la habitación.
     * @return : retorna el número de la habitación.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Obtiene la cantidad de camas simples en la habitación.
     * @return : retorna el número de camas simples.
     */
    public int getCamasSimples() {
        return camasSimples;
    }
    /**
     * Obtiene la cantidad de camas dobles en la habitación.
     * @return : retorna el numero de camas dobles.
     */
    public int getCamasDobles() {
        return camasDobles;
    }
     /**
      * Obtiene el precio por noche de la habitación.
      * @return : retorna el Precio por noche de la habitación.
      */
    public float getPrecioPorNoche() {
        return precioPorNoche;
    }
    /**
     *  Método que sobrescribe (override) el método toString() de la clase Object.
     * Se utiliza para devolver una representación en forma de texto de la habitación,
     * mostrando sus principales atributos (número, cantidad de camas y precio).
     * @return : retorna una cadena de texto con número, camas y precio por noche.
     */
    @Override
    public String toString() {
        return "habitacion{" + "numero=" + numero + ", camasSimples=" + camasSimples + ", camasDobles=" + camasDobles + ", precioPorNoche=" + precioPorNoche + '}';
    }
}   
