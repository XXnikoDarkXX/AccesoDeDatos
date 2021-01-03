package es.nicolas.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;

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
	
	
	public void GuardarSerial(String ruta) {
		FileOutputStream fos;
		

		try {
			fos = new FileOutputStream(ruta);
		
		
		
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
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
	
	
}
