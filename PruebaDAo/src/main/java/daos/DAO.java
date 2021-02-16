package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import excepciones.PersonaNoEncontradaException;


/**
 * OJO: ESTE DAO ES SOLO PARA MASCOTA: PERSONA YA ES UN DAO DE POR SI.
 * @author mparamos
 *
 */
public class DAO {

	    //Consultar desde la clave primaria
	    public static SerVivo get(int id, Class clase) throws SQLException{
	    	Statement smt=ConexionBD.conectarBD();
	    	SerVivo sv=null;
	    	if(clase.equals(Mascota.class)) {
	    		ResultSet results=smt.executeQuery("select * from mascota where id='"+id+"'");
	    		if(results.next()) {
	    			try {
						
	    				sv=new Mascota(results.getString("nombre"),
								results.getString("especie"),
								results.getString("raza"),
								id,new Persona(results.getString("propietario")));
					
	    			} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (PersonaNoEncontradaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	}
	    	ConexionBD.desconectarBD();
	    	return sv;
	    }
	    
	    //Consultar desde la clave primaria
	    SerVivo get(String id){
	    	return null;
	    }
	    
	    List<SerVivo> getAll(){
	    	return null;
	    }
	    
	    void save(SerVivo t) {
	    	
	    }
	    
	    void update(SerVivo t, String[] params) {
	    	
	    }
	    
	    void delete(SerVivo t) {
	    	
	    }
	
	
}
