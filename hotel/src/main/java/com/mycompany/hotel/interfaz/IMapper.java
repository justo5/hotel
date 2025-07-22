/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.hotel.interfaz;

/**
 *
 * @author mi pc
 */
public interface IMapper<E,D> {
   
    public abstract  D toDTO(E entity);
    
    public abstract  E toEntity(D dto);
    
    
}
