/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.juegoserializarad.clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author mparamos
 */
public class Jugador extends ElementoConFuerza implements Serializable{
    private byte posX; //Fila del tablero donde está
    private byte posY; //Columna del tablero donde está

    /**
     * Constructor vacio 
     */
 public Jugador() {
    	super("Anonimo", (byte)10);
    }
    /**
     * Constructor Jugador con todos los parametros
     * @param nombre del jugador
     * @param fuerza fuerza en byte del jugador
     * @param posX fila del tablero
     * @param posY columna del tablero
     */
    public Jugador(String nombre, byte fuerza,byte posX, byte posY) {
        super(nombre, fuerza);
        this.posX = posX;
        this.posY = posY;
    }
   

    public byte getPosX() {
        return posX;
    }

    public void setPosX(byte posX) {
        this.posX = posX;
    }

    public byte getPosY() {
        return posY;
    }

    public void setPosY(byte posY) {
        this.posY = posY;
    }
    
  /*  
   * Ya no hace falta porque tengo el mapa cargar
    public void guardar(){
        FileOutputStream fos;
		
			
		
			
		
		try {
			fos = new FileOutputStream("./jugador.cenec");
		
	
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
    }*/
		
 
}
    


    
    

