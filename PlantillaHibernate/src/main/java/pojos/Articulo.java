package pojos;

// default package
// Generated 7 mar. 2021 19:02:22 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Articulo generated by hbm2java
 */
@Entity
@Table(name = "articulo", catalog = "pruebashibernate")
public class Articulo implements java.io.Serializable {

	private int id;
	private String nombre;
	private String descripcion;
	private Float precio;
	private Set<Compra> compras = new HashSet<Compra>(0);

	public Articulo() {
	}

	public Articulo(int id) {
		this.id = id;
	}

	public Articulo(int id, String nombre, String descripcion, Float precio, Set<Compra> compras) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.compras = compras;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "precio", precision = 12, scale = 0)
	public Float getPrecio() {
		return this.precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "articulo")
	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

}