package unmarsshalling;

import java.util.ArrayList;

public class Biblioteca {
 private String bibliotecaDe;
 private ArrayList<Juego>juegos;
 
public Biblioteca(String bliotecaDe, ArrayList<Juego> juegos) {
	
	this.bibliotecaDe = bliotecaDe;
	this.juegos = juegos;
}

public String getBliotecaDe() {
	return bibliotecaDe;
}

public void setBliotecaDe(String bliotecaDe) {
	this.bibliotecaDe = bliotecaDe;
}

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
