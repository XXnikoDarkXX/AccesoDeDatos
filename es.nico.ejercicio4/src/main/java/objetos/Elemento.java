package objetos;

public class Elemento {

	private String nombre;// nombre
	private String tipo;// tipo

	/**
	 * Contructor de Elemento vacio
	 */
	public Elemento() {

	}

	/**
	 * Constructor de Elementos con todas sus variables internas
	 * 
	 * @param n nombre
	 * @param t tipo
	 */
	public Elemento(String n, String t) {
		this.nombre = n;
		this.tipo = t;
	}
	
	/**
	 * Getter de nombre
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter de nombre
	 * @param nombre a cambair
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter de tipo
	 * @return el tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * Setter de tipo
	 * @param tipo a cambiar
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Elemento [nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
	

}
