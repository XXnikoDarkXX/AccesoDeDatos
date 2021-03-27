package es.cenecmalaga.miproyecto.pruebadao;

import java.sql.SQLException;

import daos.DAO;
import daos.Mascota;
import daos.Persona;
import daos.SerVivo;
import excepciones.MascotaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;

public class MainPruebas {

	public static void main(String[] args) {
		Persona p;
		try {
			p = new Persona("1234");
	
		SerVivo mascota=new Mascota("rodolfo","aguila","picuda",3,p);
		DAO dao=new DAO();
	//	SerVivo mascota=dao.get(1, Mascota.class);
		System.out.println(mascota);
		
		//String[]propiedades= {"hola","ff","ff","1"};
		//dao.update(mascota, propiedades);
				//dao.update(mascota,"", "", "", "1234");
	//	dao.save(mascota);
		System.out.println(dao.getAll());
		dao.delete(mascota);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
