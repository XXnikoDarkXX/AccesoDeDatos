/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.pruebasgson.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import unmarsshalling.DiaDeExamen;

/**
 *
 * @author nicoc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JAXBContext  context;
        try {
            context = JAXBContext.newInstance(DiaDeExamen.class);
       
			
		
		//Pasamos a objeto el xml
		Unmarshaller um=context.createUnmarshaller();
		DiaDeExamen telefonoTraido;
            
                telefonoTraido = (DiaDeExamen) um.unmarshal(new FileReader("./dia.xml")); //Simplemente traemos un archivo xml al objeto
            
		System.out.println(telefonoTraido);
        
        
         } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
    }
    
}
