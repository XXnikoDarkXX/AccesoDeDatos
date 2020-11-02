/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad.clases;

import java.io.Serializable;

/**
 *Clase padre que sirve para no repetir variable de nombre
 * @author mparamos
 * 
 */
public abstract class ElementoConNombre implements Serializable {
    private String nombre;

    //Constructor de nombre
    public ElementoConNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Constructor vacio sirve para implementar correctamente Serializable
     */
    public ElementoConNombre() {
    	
    }
    
    
    //Getter de nombre
    public String getNombre() {
        return nombre;
    }
    //Setter de nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
