/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad.clases;

/**
 *Esta clase padre es para no repetir variables fuerza, extiende de elementoConNombre
 * @author mparamos
 */

public abstract class ElementoConFuerza extends ElementoConNombre{
    private byte fuerza;
    
    /**
     * Constructor de ElementoConFuerza
     * @param nombre 
     * @param fuerza
     */
    public ElementoConFuerza(String nombre,byte fuerza) {
        super(nombre);
        this.fuerza = fuerza;
    }
    
    
    /**
     * Getter de fuerza
     * @return
     */
    public byte getFuerza() {
        return fuerza;
    }
    /**
     * Setter de fuerza
     * @param fuerza
     */
    public void setFuerza(byte fuerza) {
        this.fuerza = fuerza;
    }
}
