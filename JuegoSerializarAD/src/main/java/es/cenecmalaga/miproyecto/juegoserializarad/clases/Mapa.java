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
import java.util.Random;

/**
 *
 * @author mparamos
 */
public class Mapa implements Serializable{
    private Casilla[][] casillas;
    private Jugador jugador;

    public Mapa(){
        Random r=new Random();
        int tamanioFilas=r.nextInt(11)+5;
        int tamanioColumnas=r.nextInt(11)+5;
        casillas=new Casilla[tamanioFilas][tamanioColumnas];
        jugador=new Jugador("Jugador",(byte)10,(byte)r.nextInt(tamanioFilas),(byte)r.nextInt(tamanioColumnas));
    }
    
    
    public Mapa(Casilla[][] casillas, Jugador jugador) {
        this.casillas = casillas;
        this.jugador = jugador;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    //Para dibujar el mapa
    public String toString() {
        String ret="";
        //en este for si encontramos al jugador sus coordenadas dibujaremos una j si no dibujamos un □
        for (int i = 0; i < this.casillas.length; i++) {
            for (int j = 0; j < this.casillas[i].length; j++) {
                if(jugador.getPosX()==i&&jugador.getPosY()==j){
                    ret+="J\t";
                }else{
                    ret+="□\t";
                }
            }
            ret+="\n";
        }
        return ret;
    }
    
    public void guardar(){
        FileOutputStream fos;
		
			
		
			
		
		try {
			fos = new FileOutputStream("./mapa.cenec");
		
	
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
