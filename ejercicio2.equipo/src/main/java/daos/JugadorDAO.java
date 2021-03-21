package daos;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import configuracion.Ejer2EquipoSessionFactory;

import excepciones.ClavePrimariaDuplicadaException;
import excepciones.ElementoNoEncontradoException;
import excepciones.ElementoYaExisteException;
import pojos.Equipo;
import pojos.Jugador;
import pojos.Patrocinador;
import pojos.Pojo;

public class JugadorDAO extends DAO{
	
		private Jugador pojo;

		
		
		private JugadorDAO(Pojo p) {
			this.pojo=(Jugador)p;
		}
		
		
		public JugadorDAO(int nRegistro) throws ElementoNoEncontradoException {
			this.pojo=(Jugador)recuperar("Jugador","nRegistro",nRegistro);
		}

		public JugadorDAO(Equipo equipo, String nombre, Date fnacimiento, float peso) throws ClavePrimariaDuplicadaException {
			
			
			this.pojo=new Jugador(equipo,nombre,fnacimiento,peso);
			this.insertar(pojo);

		}

		
		
		public Integer getNregistro() {
		return this.pojo.getNregistro();
		}

		public void setNregistro(Integer nregistro) throws ElementoYaExisteException {
	
		
			
			
			
				try {
				//Si el nRegistro nuevo que queremos poner ya existe, no lo debo dejar cambiar. Si existe, no va a reventar
				//en este new.
				//Por tanto, solo lo cambio, si llega a este catch.
				new JugadorDAO(nregistro);
				throw new ElementoYaExisteException("El nuevo registro ("+nregistro+")que le intentas poner al jugador "
						+ this.pojo.getNombre()+" ya existe. No se puede realizar la operacion");
			
			
			}catch(ElementoNoEncontradoException ex) {
				this.pojo.setNregistro(nregistro);
				this.insertarOActualizar(this.pojo);
			}
		
		}

		
		public Equipo getEquipo() {
			return this.pojo.getEquipo();
		}

		public void setEquipo(Equipo equipo) {
			this.pojo.setEquipo(equipo);
			this.insertarOActualizar(this.pojo);
		}

		
		public String getNombre() {
		return this.pojo.getNombre();
		}

		public void setNombre(String nombre) {
			this.pojo.setNombre(nombre);
			this.insertarOActualizar(this.pojo);
		}

	
		public Date getFnacimiento() {
			return this.pojo.getFnacimiento();
			
		}

		public void setFnacimiento(Date fnacimiento) {
		this.pojo.setFnacimiento(fnacimiento);
		this.insertarOActualizar(this.pojo);
		}

	
		public float getPeso() {
			return this.pojo.getPeso();
		}

		public void setPeso(float peso) {
		this.pojo.setPeso(peso);
		this.insertarOActualizar(this.pojo);
		}
		
		public void eliminar() {
			this.borrar(this.pojo);
			this.pojo=null;
		}


	}