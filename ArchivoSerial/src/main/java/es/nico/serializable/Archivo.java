/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nico.serializable;

import java.io.Serializable;

/**
 *
 * @author nicoc
 * Clase Archivo donde metemos un String ruta
 */
public class Archivo implements Serializable {
	
	private String ruta;//ruta del archivo
	private int tamanio;//tamaño del archivo
	/**
	 * Constructor Archivo vacio
	 */
	public Archivo() {
		
	}
	/**
	 *Constructor de archivo con todas sus variables internas
	 * @param ruta ruta del archivo
         * @param tamanio tamaño del archivo
	 */
	public Archivo(String ruta,int tamanio) {
	this.ruta=ruta;
        this.tamanio=tamanio;
	}
	/**
	 * Getter de ruta
	 * @return la ruta del archivo
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * Setter de ruta
	 * @param ruta a cambiar
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
        //getter de tamaño del archivo
    public int getTamanio() {
        return tamanio;
    }


}
