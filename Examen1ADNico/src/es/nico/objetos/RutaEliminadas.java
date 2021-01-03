package es.nico.objetos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RutaEliminadas implements Serializable {
	private HashMap <Long,String> lista;
	
	
	
	public RutaEliminadas() {
		lista=new HashMap<Long,String>();
		
	}



	public HashMap<Long, String> getLista() {
		return lista;
	}



	public void setLista(long tamanio,String ruta) {
		lista.put(tamanio, ruta);
	}
	
	
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
           
        } catch (IOException ex) {
            
        }
    

	
}
	
	
	
	
	
	
}
