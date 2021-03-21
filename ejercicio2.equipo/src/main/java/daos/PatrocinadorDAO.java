package daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import excepciones.ClavePrimariaDuplicadaException;
import excepciones.ElementoNoEncontradoException;
import excepciones.ElementoYaExisteException;
import pojos.Equipo;
import pojos.Patrocinador;
import pojos.Pojo;

public class PatrocinadorDAO extends DAO{
	private Patrocinador pojo;

	public PatrocinadorDAO(String nombre) throws ElementoNoEncontradoException {
			this.pojo=(Patrocinador)recuperar("Patrocinador","nombre",nombre);
		
	}
	
	private PatrocinadorDAO(Pojo p) {
		this.pojo=(Patrocinador)p;
	}

	public PatrocinadorDAO(String nombre, Integer presupuesto, Set<Equipo> equipos) throws ClavePrimariaDuplicadaException  {
		
		this.insertar(this.pojo);
		this.pojo=new Patrocinador(nombre,presupuesto,equipos);
		
		
	}

	public String getNombre() {
		return this.pojo.getNombre();
		

	}

	public void setNombre(String nombre) throws ElementoYaExisteException {
		try {
			//Si el nombre nuevo que queremos poner ya existe, no lo debo dejar cambiar. Si existe, no va a reventar
			//en este new.
			//Por tanto, solo lo cambio, si llega a este catch.
			new PatrocinadorDAO(nombre);
			throw new ElementoYaExisteException("El nuevo nombre ("+nombre+")que le intentas poner al patrocinador "
					+ this.pojo.getNombre()+" ya existe. No se puede realizar la operacion");
		}catch(ElementoNoEncontradoException ex) {
			this.pojo.setNombre(nombre);
			this.insertarOActualizar(this.pojo);
		}
	
	}

	public Integer getPresupuesto() {
		return this.pojo.getPresupuesto();
		
	}

	public void setPresupuesto(Integer presupuesto) {
		this.pojo.setPresupuesto(presupuesto);
		this.insertarOActualizar(this.pojo);
	}

	public Set<Equipo> getEquipos() {
		return this.pojo.getEquipos();
	}

	public void setEquipos(Set<Equipo> equipos) {
			this.pojo.setEquipos(equipos);
			this.insertarOActualizar(this.pojo);
	}
	
	public List <PatrocinadorDAO>getTodos(){
		List<Pojo>patrocinadores=this.getTodos("Patrocinador");
		List<PatrocinadorDAO>todos=new ArrayList<PatrocinadorDAO>();
		for (int i = 0; i <patrocinadores.size(); i++) {

			todos.add(new PatrocinadorDAO(patrocinadores.get(i)));
		}
		
		
		return todos;
	}
	
	public void eliminar() {
		this.borrar(this.pojo);
		this.pojo=null;
	}

}
