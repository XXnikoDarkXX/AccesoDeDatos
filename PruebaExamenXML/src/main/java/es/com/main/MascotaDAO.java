package es.com.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import excepciones.IDYaExisteException;
import excepciones.MascotaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;


public class MascotaDAO {
	
	private int id;
	private String nombre;
	private String especie;
	private String raza;
	private String propietario;
	
	public MascotaDAO(int id,String nombre,String especie,String raza,String propietario) throws IDYaExisteException {
		this.id=id;
		this.nombre=nombre;
		this.especie=especie;
		this.raza=raza;
		this.propietario=propietario;
		
		Statement smt=ConexionBD.conectarBD();
    	try {
			smt.executeUpdate("insert into mascota values('" +this.getId()+ "','" 
					 + this.getNombre() + "','" 
					+this.getEspecie()+ "','"
					 + this.getRaza() + "','" 
					+ this.getPropietario()+ "')");
			ConexionBD.desconectarBD();
			
			}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
	    		throw new IDYaExisteException("El id  "+this.getId()+" ya existe en la base de datos");
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
		
	}
	public MascotaDAO() {
		
	}
	
	public MascotaDAO(int id) throws SQLException, PersonaNoEncontradaException, MascotaNoEncontradaException {
	
		Statement smt=ConexionBD.conectarBD();
		ResultSet results=smt.executeQuery("select * from mascota where id='"+id+"'");
		if(results.next()) {
			try {
				
				this.nombre=results.getString("nombre");
				this.especie=results.getString("especie");
						this.raza=results.getString("raza");
						this.propietario=results.getString("propietario");
						this.id=id;
			
						ConexionBD.desconectarBD();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	if (this==null) {
		throw	new  MascotaNoEncontradaException("Mascota no ha sido encontrada");
	}
	
	}
	public int getId() {
		return id;
	}
	
	public void guardar() throws IDYaExisteException {
		try {
			Statement smt=ConexionBD.conectarBD();
			smt.executeUpdate("insert into mascota values('" +this.getId()+ "','" 
					 + this.getNombre() + "','" 
					+this.getEspecie()+ "','"
					 + this.getRaza() + "','" 
					+ this.getPropietario()+ "')");
			ConexionBD.desconectarBD();
		
			
		}catch(java.sql.SQLIntegrityConstraintViolationException ex) {
    		throw new IDYaExisteException("El id  "+this.getId()+" ya existe en la base de datos");
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setId(int id) throws IDYaExisteException {

		  Statement stm=ConexionBD.conectarBD();
		
		  ResultSet contadorID;
		try {
			contadorID = stm
						.executeQuery("select count(*) apariciones from mascota where " + "id = " + id + "");
		
			contadorID.next();

			if (contadorID.getInt("apariciones") > 0) {

				throw new IDYaExisteException("No puedes ponerle a esta mascota el id " + id+ ", ya existe en la bd.");

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
				ConexionBD.desconectarBD();
				Statement smt =ConexionBD.conectarBD();
				try {
					smt.executeUpdate("update mascota set id = '" + id
							+ "' " + "where id = '" + this.id + "'");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ConexionBD.desconectarBD();
				
				
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		
		Statement stm=ConexionBD.conectarBD();

		try {
			stm.executeUpdate("update mascota set nombre ='" +nombre + "'" + "where id='" + this.getId()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectarBD();
		
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		
		
		
		Statement stm=ConexionBD.conectarBD();

		try {
			stm.executeUpdate("update mascota set especie ='" +especie + "'" + "where id='" + this.getId()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectarBD();
		
		
		
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		
		
		Statement stm=ConexionBD.conectarBD();

		try {
			stm.executeUpdate("update mascota set raza ='" +raza + "'" + "where id='" + this.getId()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectarBD();
		
		
		this.raza = raza;
	}

	public String getPropietario() {
		
		return propietario;
	}

	public void setPropietario(String propietario) {
		
		Statement stm=ConexionBD.conectarBD();

		try {
			stm.executeUpdate("update mascota set propietario ='" +propietario + "'" + "where id='" + this.getId()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectarBD();
		
		
		this.propietario = propietario;
	}
	@Override
	public String toString() {
		return "MascotaDAO [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", raza=" + raza
				+ ", propietario=" + propietario + "]";
	}
	
	
}
