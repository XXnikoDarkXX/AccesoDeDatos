package daos;

public class Mascota extends SerVivo {
	private String nombre;
	private String especie;
	private String raza;
	private int id;
	private Persona propietario;
	
	public Mascota(String nombre, String especie, String raza, int id, Persona propietario) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.id = id;
		this.propietario = propietario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "Mascota [nombre=" + nombre + ", especie=" + especie + ", raza=" + raza + ", id=" + id + ", propietario="
				+ propietario + "]";
	}
	
	
	
}
