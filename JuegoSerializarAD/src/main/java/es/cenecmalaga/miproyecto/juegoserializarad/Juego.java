/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad;



import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import es.cenecmalaga.miproyecto.juegoserializarad.clases.Mapa;

/**
 *
 * @author mparamos
 */
public class Juego {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	FileSystem fs=FileSystems.getDefault();
        Mapa mapa=new Mapa();
       if (Files.exists(fs.getPath("./mapa.cenec"))) {
		mapa=Mapa.cargar();
	}else {
		mapa=new Mapa();
		mapa.guardar();
	}
      System.out.println(mapa);  
    }
    
}
