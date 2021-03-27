package compras.hibernate;



import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import configuracion.ComprasSessionFactory;
import pojos.Usuario;

public class Main {

	public static void main(String[] args) {
		
		
		  SessionFactory sf = ComprasSessionFactory.devolverSessionFactory();	        
	        Session sesion = sf.openSession();
	        
	        
	      Usuario u=new Usuario("pe@p2","señor a",Date.valueOf("2000-01-01"),"45756",null);  
	       
	
	        //Inserción básica
	        //Lo persisto en base de datos
	        Transaction t2=sesion.beginTransaction();
	        sesion.saveOrUpdate(u);
	        t2.commit();
	        	
	        
	        sesion.close();
	        
	        
	        
		
	}

}
