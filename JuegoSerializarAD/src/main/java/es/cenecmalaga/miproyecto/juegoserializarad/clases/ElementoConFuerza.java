/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad.clases;

/**
 *
 * @author mparamos
 */
public abstract class ElementoConFuerza extends ElementoConNombre{
    private byte fuerza;

    public ElementoConFuerza(String nombre,byte fuerza) {
        super(nombre);
        this.fuerza = fuerza;
    }
    
    

    public byte getFuerza() {
        return fuerza;
    }

    public void setFuerza(byte fuerza) {
        this.fuerza = fuerza;
    }
}
