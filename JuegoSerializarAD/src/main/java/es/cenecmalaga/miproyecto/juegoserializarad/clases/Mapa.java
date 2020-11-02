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
    /**
     * Constructor vacio de mapa donde creamos las casillas [][][] y creamos el jugador
     * Y a jugador le metemos una posicion aleatorio en una casilla
     */
    public Mapa(){
        Random r=new Random();
        int tamanioFilas=r.nextInt(11)+5;
        int tamanioColumnas=r.nextInt(11)+5;
        casillas=new Casilla[tamanioFilas][tamanioColumnas];
        jugador=new Jugador("Jugador",(byte)10,(byte)r.nextInt(tamanioFilas),(byte)r.nextInt(tamanioColumnas));
    }
    
    /**
     * Constructor de Mapa con todas sus parametros
     * @param casillas
     * @param jugador
     */
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
    /**
     * Funcion por el escribimos la clase mapa, usando un FileOuput donde le metemos el nombre del archivo.
     * Y mediante el ObjectOuputStream escribimos dicha clase
     */
    public void guardar(){
        FileOutputStream fos;//Usamos para escribir en binarios
		
			
		
			
		
		try {
			fos = new FileOutputStream("./mapa.cenec");
		
	
        ObjectOutputStream oos = new ObjectOutputStream(fos);//metemos el file a un objectOuput
        oos.writeObject(this);//escribimos el objeto
        oos.flush();//aplicamos
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
     * Mediante es funcion creamos un FileInput (llamamos al nombre del archivo), creamos un ObjectInput para poder leer,
     * Finalmente creamos un Mapa auxiliar que vamos a cargarle la lectura del ObjectIpunt
     * @return
     */
    public static Mapa cargar (){
        FileInputStream fis;
		try {
			fis = new FileInputStream("./mapa.cenec");
		
        ObjectInputStream ois = new ObjectInputStream(fis);//Metemos el FileInput a un ObjectInput
        Mapa aux=(Mapa)ois.readObject();//Crea una variable tipo Mapa, y lo lee con el ois.ReadObject lee el objeto  y hace el casting
        //Resumido: a Mapa aux le va a cargar la informacion de ois.ReadObject. Va a coger un archivo y se lo va a cargar a una variable
        ois.close();
        fis.close();
        return aux; //devuelvd el mapa leido
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
