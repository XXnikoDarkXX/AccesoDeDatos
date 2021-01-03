package main;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
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
	private ArrayList<Componente>componentes;
	/**
	 * Constructor vacio
	 */
	public Telefono() {
		componentes=new ArrayList<Componente>();
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
		
		componentes=com;
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
	@XmlElement
	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}
	@Override
	public String toString() {
		String aux="telefono [marca=" + marca + ", modelo=" + modelo + ", lanzamiento=" + lanzamiento + ", [componentes="
				;
		
		
		for (int i = 0; i < componentes.size(); i++) {
			aux+= "nombre " +componentes.get(i).getNombre()+" descipcion: "+componentes.get(i).getDescripcion()+"]";
		}
		
		
		return aux;
	}
	
	
	
	
	

}
