package daos;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import configuracion.Ejer2EquipoSessionFactory;

import excepciones.ClavePrimariaDuplicadaException;
import excepciones.ElementoNoEncontradoException;
import pojos.Pojo;

public abstract class DAO {


	protected void insertar(Pojo p) throws ClavePrimariaDuplicadaException {
		try {
			SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
			Session sesion=sf.openSession();
			Transaction t=sesion.beginTransaction();
			sesion.save(p);
			t.commit();
			sesion.close();
		}catch(Exception e) {
			throw new ClavePrimariaDuplicadaException(e.getMessage());
		}
	}
	
	protected void insertarOActualizar(Pojo p) {
		SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
		Session sesion=sf.openSession();
		Transaction t=sesion.beginTransaction();
		sesion.saveOrUpdate(p);
		t.commit();
		sesion.close();
	}
	 
	protected void borrar(Pojo p) {
		SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
		Session sesion=sf.openSession();
		Transaction t=sesion.beginTransaction();
		sesion.delete(p);
		t.commit();
		sesion.close();
	}
	
	protected Pojo recuperar(String nombreClasePojo, String nombreColumnaPK,String valorPK) throws ElementoNoEncontradoException {
		try{
			SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
			Session sesion = sf.openSession();
			Query query=sesion.createQuery("from "+nombreClasePojo+
        		" where "+nombreColumnaPK+"='"+valorPK+"'");
			Pojo ret=(Pojo)query.getSingleResult();
			sesion.close();
			return ret;
		}catch(javax.persistence.NoResultException e) {
			throw new ElementoNoEncontradoException(e.getMessage());
		}
	}
	
	
	protected Pojo recuperar(String nombreClasePojo, String nombreColumnaPK,int valorPK) throws ElementoNoEncontradoException {
		try{
			SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
			Session sesion = sf.openSession();
			Query query=sesion.createQuery("from "+nombreClasePojo+
        		" where "+nombreColumnaPK+"='"+valorPK+"'");
			Pojo ret=(Pojo)query.getSingleResult();
			sesion.close();
			return ret;
		}catch(javax.persistence.NoResultException e) {
			throw new ElementoNoEncontradoException(e.getMessage());
		}
	}
	
	
	
	protected List<Pojo> getTodos(String nombreClasePojo){
		SessionFactory sf =Ejer2EquipoSessionFactory.devolverSessionFactory();
		Session sesion = sf.openSession();
        Query query=sesion.createQuery("from "+nombreClasePojo);
        List<Pojo> todos=query.list();
        sesion.close();
        return todos;
	}
	
}
