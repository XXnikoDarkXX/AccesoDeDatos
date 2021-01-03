package objetos;

public class Direccion {

	private String calle;// nombre de la calle
	private byte numero;//numero de puerta
	private byte planta;//numero de planta
	private String piso;
	
	
	
	/**
	 * Constructor de direccion vacio
	 */
	public Direccion() {
		
	}
	
	
	/**
	 * Constructor de Direccion con todas las variables internas
	 * @param c calle
	 * @param n numero
	 * @param p planta
	 */
	public Direccion(String c,byte n,byte p,String piso) {
		this.calle=c;
		this.numero=n;
		this.planta=p;
		this.piso=piso;
	}

	/**
	 * Getter de calle
	 * @return la calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Setter de calle
	 * @param calle a cambiar
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Getter de numero
	 * @return el numero
	 */
	public byte getNumero() {
		return numero;
	}

	/**
	 * Setter de numero
	 * @param numero a cambiar
	 */
	public void setNumero(byte numero) {
		this.numero = numero;
	}

	/**
	 * Getter de planta
	 * @return la planta en byte
	 */
	public byte getPlanta() {
		return planta;
	}

	/**
	 * Setter de planta
	 * @param planta a cambiar
	 */
	public void setPlanta(byte planta) {
		this.planta = planta;
	}

	/**
	 * Getter de piso
	 * @return el piso
	 */
	public String getPiso() {
		return piso;
	}

	/**
	 * Setter de piso
	 * @param piso a cambiar
	 */
	public void setPiso(String piso) {
		this.piso = piso;
	}


	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", numero=" + numero + ", planta=" + planta +  " piso "+piso+"]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

