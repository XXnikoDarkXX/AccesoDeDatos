package com.objetos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Mapa implements Serializable {
	private String nombre;//nombre del mapa esto es solo un ejemplo
	
	
	public Mapa() {
		nombre="Sur de Europa";
	}

	/**
	 * Getter de nombre
	 * @return el nomre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Funcion para guardar una clase
	 */
	public void guardar(){
        FileOutputStream fos;
		
			
		
			
		
		try {
			 //primero lo metemos en un FileOutputStream que es para escribir en binario
			fos = new FileOutputStream("./mapa.data");
		
			 //lo metemos a ObjectOutputStream para realizar la serializacion
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();//escribimos
        oos.close();
        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	/**
	 * Funcion para cargar un serializable (Mapa) a una variable
	 * @return
	 */
	public static Mapa cargar (){
        FileInputStream fis;
		try {
			fis = new FileInputStream("./mapa.cenec");
		
        ObjectInputStream ois = new ObjectInputStream(fis);
        Mapa aux=(Mapa)ois.readObject();
        ois.close();
        fis.close();
        return aux; 
    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
}	
	
	
}
