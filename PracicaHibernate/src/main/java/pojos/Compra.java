package pojos;

// default package
// Generated 7 mar. 2021 19:02:22 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Compra generated by hbm2java
 */
@Entity
@Table(name = "compra", catalog = "pruebashibernate")
public class Compra implements java.io.Serializable {

	private int nfactura;
	private Articulo articulo;
	private Usuario usuario;
	private Date fechaCompra;

	public Compra() {
	}

	public Compra(int nfactura) {
		this.nfactura = nfactura;
	}

	public Compra(int nfactura, Articulo articulo, Usuario usuario, Date fechaCompra) {
		this.nfactura = nfactura;
		this.articulo = articulo;
		this.usuario = usuario;
		this.fechaCompra = fechaCompra;
	}

	@Id

	@Column(name = "nFactura", unique = true, nullable = false)
	public int getNfactura() {
		return this.nfactura;
	}

	public void setNfactura(int nfactura) {
		this.nfactura = nfactura;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProducto")
	public Articulo getArticulo() {
		return this.articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaCompra", length = 19)
	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

}
