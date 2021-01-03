package objetos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author nicoc
 *
 */
@XmlRootElement
public class Coleccion {

	private String nombreColeccion;
	private Direccion direccion;
	private ArrayList <Elemento>elementos;
	
	
	
	
	/**
	 * Constructor de Coleccion vacio inicializo la listaElementos para que no me de nullPointer
	 */
	public Coleccion() {
		elementos=new ArrayList<Elemento>();
	}
	/**
	 * Constructor con todas las variables internas
	 * @param n nombre
	 * @param di objeto direccion
	 * @param lista ArrayList de tipo Elemento
	 */
	public Coleccion(String n,Direccion di,ArrayList<Elemento>lista) {
		this.nombreColeccion=n;
		this.direccion=di;
		this.elementos=lista;
	}

	public String getNombre() {
		return nombreColeccion;
	}

	public void setNombre(String nombre) {
		this.nombreColeccion = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Elemento> getListaElementos() {
		return elementos;
	}
	
	public void setListaElementos(ArrayList<Elemento> listaElementos) {
		this.elementos = listaElementos;
	}
	
	
	/**
	 * Funcion para saber si es coleccion la clase para filtrar estructura, esto se sabe si todas las variables privadas no son nulas
	 * @return true si las variables privadas estan inicializadas y false si alguna es nula
	 */
	public boolean esColeccion() {
		if (this.nombreColeccion==null||this.elementos.size()==0||this.direccion==null) {
			return false;
		}
		return  true;
		
	}

	@Override
	public String toString() {
		String text="";
		text+="Coleccion [nombreColeccion=" + nombreColeccion + ", direccion=" + direccion + ", elementos=[";
		for (int i = 0; i < elementos.size(); i++) {
			text+="nombre: "+elementos.get(i).getNombre()+", ";
				text+="tipo:"+ elementos.get(i).getTipo()+" ";
				
		}
		text+="]";
		return text;
	}
	
	
	
	
}
