package es.nicolas.serializable;

import java.io.Serializable;

public class Archivo implements Serializable {
	
	private String ruta;//ruta del archivo
	
	/**
	 * Constructor Archivo vacio
	 */
	public Archivo() {
		
	}
	/**
	 *Constructor de archivo con todas sus variables internas
	 * @param ruta ruta del archivo
	 */
	public Archivo(String ruta) {
	this.ruta=ruta;
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


}
