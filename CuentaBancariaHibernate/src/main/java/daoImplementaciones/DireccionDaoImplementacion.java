
package daoImplementaciones;

import java.io.StringWriter;
import java.util.List;

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
	private final static Logger lOG = Logger.getLogger("file_connections");
	/**
	 * cadenaDatos: la utilizamos para enviar al correspondiente controlador el
	 * texto, que debe enviar a la vista en cada momento
	 */
	private static String cadenaDatos = "";

	/**
	 * errorSqlSW : lo utilizamos para poder imprimir los errores SQL, que nos da el
	 * printStackTrace Ej: e.printStackTrace(new PrintWriter(errorSqlSW));
	 */
	StringWriter errorSqlSW = new StringWriter();
	/**
	 * errorSQL: la utilizamos para saber si se ha producido algún error en la
	 * ejecuación del SQL
	 * 
	 */
	private static Boolean errorSQL = false;

	// GETTERS AND SETTERS
	/**
	 * No utilizado
	 * 
	 * @return
	 */
	public StringWriter getErrorSqlSW() {
		return errorSqlSW;
	}

	/**
	 * No utilizado
	 * 
	 * @return
	 */
	public void setErrorSqlSW(StringWriter errorSqlSW) {
		this.errorSqlSW = errorSqlSW;
	}

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

	/**
	 * @return errorSQL
	 */
	public Boolean getErrorSQL() {
		return errorSQL;
	}

	/**
	 * 
	 * @param errorSQL
	 */
	public void setErrorSQL(Boolean errorSQL) {
		DireccionDaoImplementacion.errorSQL = errorSQL;
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

	/**
	 * No implementado de momento
	 */

	public List<Direccion> listarDirecciones() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Actualiza la dirección No implementado todavía
	 */

	public void update(Direccion direccion) {

	}

	/**
	 * No implementado todavía
	 */

	public boolean delete(Direccion direccion) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Busca una dirección y la devuelve dado el id de un cliente. No implementado
	 * todavía
	 */
	public void find(int identificador) {
	}

}
