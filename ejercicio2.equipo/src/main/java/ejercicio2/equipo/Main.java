package ejercicio2.equipo;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import configuracion.Ejer2EquipoSessionFactory;
import pojos.Equipo;
import pojos.Jugador;
import pojos.Patrocinador;

public class Main {

	public static void main(String[] args) {
		SessionFactory sf = Ejer2EquipoSessionFactory.devolverSessionFactory();
		Session sesion = sf.openSession();
		// (Equipo equipo, String nombre, Date fnacimiento, float peso)
		float peso = 80.5f;

		/*
		 * Parte del ejercicio 1 List<Equipo> equipos =
		 * sesion.createQuery("from Equipo").list(); for (int i = 0; i < equipos.size();
		 * i++) { if
		 * (equipos.get(i).getNombre().equalsIgnoreCase("Bomba de Macarrones CFS")) {
		 * Jugador jugador=new
		 * Jugador(equipos.get(i),"peto",Date.valueOf("2021-01-01"),88.7f); Transaction
		 * t2=sesion.beginTransaction(); sesion.saveOrUpdate(jugador); t2.commit();
		 * 
		 * } }
		 */
		List<Equipo> equipos = sesion.createQuery("from Equipo where nombre like ('%s CFS%')").list();
		// List<Equipo> equipos = sesion.createQuery("from Equipo").list();
		for (int i = 0; i < equipos.size(); i++) {

			System.out.println(equipos.get(i).getNombre());
			Iterator it = equipos.get(i).getJugadors().iterator();
			while (it.hasNext()) {
				System.out.println("\t" + ((Jugador) it.next()).getNombre());
			}
			System.out.println("Patrocinado por");

			Iterator it2 = equipos.get(i).getPatrocinadors().iterator();
			while (it2.hasNext()) {
				Patrocinador patro = (Patrocinador) it2.next();
				System.out.println("\t" + patro.getNombre());
				// System.out.println("\t"+((Patrocinador)it2.next()).getNombre());
			}
			System.out.println("");
		}

		Query query = sesion.createQuery("select max(peso) as max from Jugador");
		List masGordo = query.getResultList();
		System.out.println("Elementos en masGordo: " + masGordo.size());
		for (int i = 0; i < masGordo.size(); i++) {
			System.out.println("masGordo" + masGordo.get(i));
		}

		Patrocinador cocaCola = (Patrocinador) sesion.createQuery("from Patrocinador where nombre='CocaCola'").list()
				.get(0);
		Equipo legends = (Equipo) sesion.createQuery("from Equipo where nombre='Legends CFS'").list().get(0);
		legends.getPatrocinadors().add(cocaCola);
		Patrocinador birraMePirra = new Patrocinador("Birra me pirra", 5000, null);

		Transaction t = sesion.beginTransaction();
		sesion.saveOrUpdate(birraMePirra);
		sesion.saveOrUpdate(legends);
		t.commit();
		sesion.close();

		Session s2 = sf.openSession();
		Transaction t2 = s2.beginTransaction();
		s2.delete(birraMePirra);
		t2.commit();
		s2.close();

		sf.close();

		sf.close();

	}

}
