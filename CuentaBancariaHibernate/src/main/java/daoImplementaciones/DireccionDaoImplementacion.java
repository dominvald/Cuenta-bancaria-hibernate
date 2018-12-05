
package daoImplementaciones;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Direccion;
import resources.HibernateUtil;

public class DireccionDaoImplementacion {
	private static SessionFactory sessFact = HibernateUtil.getSessionFactory();
	/**
	 * log: logger para la clase
	 */
	@SuppressWarnings("unused")
	private final static Logger lOG = Logger.getLogger("file_connections");
	/**
	 * cadenaDatos: la utilizamos para enviar al correspondiente controlador el
	 * texto, que debe enviar a la vista en cada momento
	 */
	private static String cadenaDatos = "";

	// GETTERS AND SETTERS
	/**
	 * Devuelve la cadena de datos
	 */
	public String getCadenaDatos() {
		return cadenaDatos;
	}

	/**
	 * Cambia la cadenaDatos por la cadena que le pasemos por parámetro.
	 * 
	 * @param cadenaDatos
	 */
	public void setCadenaDatos(String cadenaDatos) {
		DireccionDaoImplementacion.cadenaDatos = cadenaDatos;
	}
	// CREATE

	/* Método para crear una dirección en la bd */
	/**
	 * Método para crear una dirección en la bd.
	 * 
	 * @param direccionNuevo
	 * @param cpNuevo
	 * @param provinciaNuevo
	 * @param poblacionNuevo
	 * @param paisNuevo
	 * @return
	 */
	public Direccion create(String direccionNuevo, String cpNuevo, String provinciaNuevo, String poblacionNuevo,
			String paisNuevo) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Direccion direccion = null;

		try {
			tx = session.beginTransaction();
			direccion = new Direccion(direccionNuevo, cpNuevo, provinciaNuevo, poblacionNuevo, paisNuevo);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return direccion;
	}

}
