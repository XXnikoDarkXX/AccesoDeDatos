/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import excepciones.DNIYaExisteException;
import excepciones.PersonaNoEncontradaException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author mparamos
 */
public class Persona extends SerVivo{
    private String nombre;
    private String apellidos;
    private LocalDate fNacimiento;
    private String dni;

    public Persona(String nombre, String apellidos, LocalDate fNacimiento, String dni) throws SQLException {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fNacimiento = fNacimiento;
        this.dni = dni;
        Statement smt=conectarBD();
        smt.executeUpdate("insert into persona values('"+this.dni+"','"+
                this.nombre+"','"+this.apellidos+"','"+
                Timestamp.valueOf(this.fNacimiento.atStartOfDay())+"')");
        desconectarBD();
    }
    
    public Persona(String dni) throws SQLException, PersonaNoEncontradaException{
        
        Statement smt = conectarBD();
        ResultSet res = smt.executeQuery("select * from persona where dni='"+dni+"'");
        
        if(res.next()){
            
            this.nombre = res.getString("nombre");
            this.dni = res.getString("dni");
            this.apellidos = res.getString("apellidos");
            this.fNacimiento = LocalDate.parse(res.getTimestamp("fNacimiento").toString().substring(0,10));
        
        }else{
            
            throw new PersonaNoEncontradaException("El dni" + dni + " no encontrado en la base de datos");
            
        }
        desconectarBD();
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws SQLException {
        Statement smt = conectarBD();
        smt.executeUpdate("update persona set nombre ='" + nombre + "'" + "where dni='"+this.dni+"'");
        desconectarBD();
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws SQLException {
         Statement smt = conectarBD();
        smt.executeUpdate("update persona set apellidos ='" + apellidos + "'" + "where dni='"+this.dni+"'");
        desconectarBD();
        this.apellidos = apellidos;
    }

    public LocalDate getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(LocalDate fNacimiento) throws SQLException {
        
         Statement smt = conectarBD();
        smt.executeUpdate("update persona set fNacimiento ='"  + Timestamp.valueOf(fNacimiento.atStartOfDay()) + "' where dni = '" + this.dni+"'");
        desconectarBD();
        this.fNacimiento = fNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws SQLException, DNIYaExisteException {
        
         Statement smtConsulta = conectarBD();
         ResultSet contadorDNI = 
        		 smtConsulta.executeQuery("select count(*) apariciones from persona where "
        		 		+ "dni = '" + dni+ "'");
         contadorDNI.next();
       
         
         if(contadorDNI.getInt("apariciones")>0){
             
             throw new DNIYaExisteException("No puedes ponerle a esta persona el dni " + dni + ", ya existe en la bd.");
             
             
         }
         
         desconectarBD();
         Statement smt = conectarBD();
         smt.executeUpdate("update persona set dni = '" + dni + "' "
         		+ "where dni = '" + this.dni+"'");
         desconectarBD();
         this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", fNacimiento=" + fNacimiento + ", dni=" + dni + '}';
    }
    
    public static ArrayList<Persona> todas(){
    	ArrayList<Persona> ret=new ArrayList<Persona>();
    	Statement smt=conectarBD();
    	try {
			ResultSet results=smt.executeQuery("select dni from persona");
			while(results.next()) {
				try {
					ret.add(new Persona(results.getString("dni")));
				} catch (PersonaNoEncontradaException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	desconectarBD();
    	return ret;
    }
    
    
    
}
