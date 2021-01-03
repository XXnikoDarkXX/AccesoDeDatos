package unmarsshalling;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author nicoc
 *
 */
@XmlRootElement(name="MiBiblioteca")
public class Biblioteca {
 private String bibliotecaDe;
 private ArrayList<Juego>juegos;
 
 public Biblioteca() {
	 bibliotecaDe="";
	 juegos=new ArrayList<Juego>();
 }
 
public Biblioteca(String bliotecaDe, ArrayList<Juego> juegos) {
	
	this.bibliotecaDe = bliotecaDe;
	this.juegos = juegos;
}

@XmlElement(name="nombreBiblioteca")
public String getBliotecaDe() {
	
	return bibliotecaDe;
}

public void setBliotecaDe(String bliotecaDe) {
	this.bibliotecaDe = bliotecaDe;
}
@XmlElementWrapper(name="misJuegos")
@XmlElement(name="juego")
public ArrayList<Juego> getJuegos() {
	return juegos;
}

public void setJuegos(ArrayList<Juego> juegos) {
	this.juegos = juegos;
}

@Override
public String toString() {
	return "Biblioteca [bliotecaDe=" + bibliotecaDe + ", juegos=" + juegos + "]";
}
 
 
 
 
 
 
}
