package ejercicio2.equipo;


import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import configuracion.Ejer2EquipoSessionFactory;
import daos.JugadorDAO;
import daos.PatrocinadorDAO;
import excepciones.ClavePrimariaDuplicadaException;
import excepciones.ElementoNoEncontradoException;
import excepciones.ElementoYaExisteException;
import pojos.Equipo;
import pojos.Jugador;

public class MainExamen {

	public static void main(String[] args) {
		

		
		try {
			
			
			PatrocinadorDAO p;
			
				p = new PatrocinadorDAO("CocaCola");
			
			//	p.setNombre("Santander");
				//p.eliminar();
			p.setPresupuesto(5000);
			System.out.println(p.getNombre());
			
			
			
			
			
			
			JugadorDAO j=new JugadorDAO(8);
			


			j.setNombre("pepsinoesbuena");
			System.out.println(j.getNombre());
			Date d=Date.valueOf("2021-01-04");
			j.setFnacimiento(d);
			j.setPeso(55.5f);
		//	j.setEquipo(new Equipo(2));
			j.setNregistro(16);
			
			
			
			
		
			
			
			} catch (ElementoNoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} catch (ElementoYaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
