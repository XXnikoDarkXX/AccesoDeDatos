/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nico.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoc
 * Clase archivos el cual contiene un ArrayList de Archivo donde meteremos varios Archivos para luego serializarlos
 */
public class Archivos implements Serializable {

	private ArrayList <Archivo>listaArchivo;//lista de Archivo
	
	/**
	 * Constructor de Archivos vacio
	 */
	public Archivos() {
		this.listaArchivo=new ArrayList();
	}
	
	/**
	 * Getter de listaArchivo
	 * @return la lista
	 */
	public ArrayList<Archivo> getListaArchivo() {
		return listaArchivo;
	}
	/**
	 * Setter de listaArchivo
	 * @param listaArchivo a cambiar
	 */
	public void setListaArchivo(Archivo arc) {
		this.listaArchivo.add(arc);
	}
	
	/**
         * Mediante esta funcion serializamos el arrayList de objetos usando FileOutputStream
         * @param ruta donde crearemos el archivo binario
         */
	public void GuardarSerial(String ruta) {
            try {
                //primero lo metemos en un FileOutputStream que es para escribir en binario
                FileOutputStream fos;
                
                
                
                fos = new FileOutputStream(ruta);
                
                
                //lo metemos a ObjectOutputStream para realizar la serializacion
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
                oos.flush();//escribimos
                oos.close();
                fos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
	
		
	}
	
	
}