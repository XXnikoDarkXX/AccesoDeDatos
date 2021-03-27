package configuracion;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import pojos.Articulo;
import pojos.Compra;
import pojos.Usuario;

public class ComprasSessionFactory {

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
			cfg.addAnnotatedClass(Articulo.class);
			cfg.addAnnotatedClass(Compra.class);
			cfg.addAnnotatedClass(Usuario.class);
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
