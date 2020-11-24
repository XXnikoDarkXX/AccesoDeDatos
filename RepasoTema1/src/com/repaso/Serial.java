package com.repaso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.objetos.*;


public class Serial {

	public static void main(String[] args) {
		/*
		 * Teoria: Para que un objeto de una clase puedan ser un fichero binario la clase tiene que
		 * implementar la interfaz Serializable
		 * Todas sus variables internas sean serializable
		 * Tener un constructor vacio aunque no haga nada
		 * Tener todos los setter implementados
		 */
		
		//Tenemos para usar de ejemplo: ArchivoSerial
		//Otro ejemplo en JuegoSerializarAD
		
		Mapa prueba=new Mapa();
		prueba.guardar();
	
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
