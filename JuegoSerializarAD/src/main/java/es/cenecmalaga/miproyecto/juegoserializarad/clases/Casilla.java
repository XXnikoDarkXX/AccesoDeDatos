/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mparamos
 * 
 */
public class Casilla extends ElementoConNombre implements Serializable {
    private String desc;
    private ArrayList<Objeto> objetos;
    /**
     * Constructor de casilla con todos los parametros
     * @param nombre de la casilla
     * @param desc descripcion de la casilla
     * @param objetos de la casilla
     */
    public Casilla( String nombre,String desc, ArrayList<Objeto> objetos) {
        super(nombre);
        this.desc = desc;
        this.objetos = objetos;
    }
    /**
     * Constructor vacio de Casillo lo hacemos porque implementamos un Serializable
     */
    public Casilla() {
    	super("casilla X");
    	
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Objeto> objetos) {
        this.objetos = objetos;
    }
    
    
}
