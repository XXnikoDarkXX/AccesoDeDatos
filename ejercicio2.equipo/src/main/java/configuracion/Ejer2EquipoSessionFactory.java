package configuracion;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pojos.Division;
import pojos.Equipo;
import pojos.Jugador;
import pojos.Patrocinador;

public class Ejer2EquipoSessionFactory {

	
	private static SessionFactory sessionFactory;

	public static SessionFactory devolverSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}

		try {
			// Create the SessionFactory from standard (hibernate.cfg.xml)
			// config file.
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			cfg.addAnnotatedClass(Division.class);
			cfg.addAnnotatedClass(Equipo.class);
			cfg.addAnnotatedClass(Jugador.class);
			cfg.addAnnotatedClass(Patrocinador.class);
			StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
					.applySettings(cfg.getProperties());
			sessionFactory = cfg.buildSessionFactory(srb.build());
			return sessionFactory;

		} catch (

		Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
