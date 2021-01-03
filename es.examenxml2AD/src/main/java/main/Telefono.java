package main;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author nicoc
 *
 */
@XmlRootElement
public class Telefono {
	
	private String marca;//marca del telefono
	private String modelo;//modelo del telefono
	private short lanzamiento;//fecha de lanzamiento
	private ArrayList<Componente>componente;
	/**
	 * Constructor vacio
	 */
	public Telefono() {
		componente=new ArrayList<Componente>();
	}
	/**
	 * Constructor con todos sus parametros
	 * @param marca
	 * @param modelo
	 * @param lanzamiento
	 * @param com
	 */
	public Telefono(String marca,String modelo,short lanzamiento,ArrayList <Componente>com) {
		this.marca=marca;
		this.modelo=modelo;
		this.lanzamiento=lanzamiento;
		
		componente=com;
	}
	@XmlElement
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	@XmlElement
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@XmlElement
	public short getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(short lanzamiento) {
		this.lanzamiento = lanzamiento;
	}
	
	@XmlElementWrapper(name="componentes")
	@XmlElement(name="componente")
	public ArrayList<Componente> getComponentes() {
		return componente;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componente = componentes;
	}
	@Override
	public String toString() {
		String aux="telefono [marca=" + marca + ", modelo=" + modelo + ", lanzamiento=" + lanzamiento + ", [componentes="
				;
		
		
		for (int i = 0; i < componente.size(); i++) {
			aux+= "nombre " +componente.get(i).getNombre()+" descipcion: "+componente.get(i).getDescripcion()+"]";
		}
		
		
		return aux;
	}
	
	
	
	
	

}
