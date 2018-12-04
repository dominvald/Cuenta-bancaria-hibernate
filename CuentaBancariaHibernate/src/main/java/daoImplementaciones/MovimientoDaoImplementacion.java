package daoImplementaciones;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import controller.ControllerPrincipal;
import model.Cliente;
import model.Movimiento;
import resources.HibernateUtil;
import resources.Recursos;;

public class MovimientoDaoImplementacion {
	// PROPIEDADES;
	SessionFactory sessFact = HibernateUtil.getSessionFactory();
	/**
	 * log: logger para la clase
	 */
	/**
	 * log: logger para la clase
	 */
	private final static Logger LOG = Logger.getLogger("MovimientoDaoImplementacion.class");
	/**
	 * errorSqlSW : lo utilizamos para poder imprimir los errores SQL, que nos da el
	 * printStackTrace Ej: e.printStackTrace(new PrintWriter(errorSqlSW));
	 */
	StringWriter errorSqlSW = new StringWriter();

	/**
	 * idActual: guarda el id del cliente seleccionado en cada momento, aprovechando
	 * que siempre que realizamos alguna de las operaciones siempre llamamos
	 * normalmente al método buscarClientes que se encuentra en la clase
	 * ClienteDaoImplementación, es decir, se encuentra en esta misma clase
	 */
	/**
	 * cadenaDatos: la utilizamos para enviar al correspondiente controlador el
	 * texto, que debe enviar a la vista en cade momento
	 */
	private static String cadenaDatos = "";
	/**
	 * errorSQL: la utilizamos para saber si se ha producido algún error en la
	 * ejecuación del SQL
	 * 
	 */
	/**
	 * errorSQL: la utilizamos para saber si se ha producido algún error en la
	 * ejecuación del SQL
	 * 
	 */
	private static Boolean errorSQL = false;

	// GETTERS Y SETTERS-------------------------------------//
	public String getCadenaDatos() {
		return cadenaDatos;
	}

	public void setCadenaDatos(String cadenaDatos) {
		this.cadenaDatos = cadenaDatos;
	}

	/**
	 * @return errorSQL
	 */
	public boolean getErrorSQL() {
		return errorSQL;
	}

	/**
	 * 
	 * @param errorSQL
	 */
	public void setErrorSQL(Boolean errorSQL) {
		MovimientoDaoImplementacion.errorSQL = errorSQL;
	}

	// FIN GETTERS Y SETTERS-------------------------------------//

	public Integer create(BigDecimal saldo,BigDecimal importe, Cliente cliente, int modalidad) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Integer estado = null;
		
		try {
			tx = session.beginTransaction();
			Movimiento movimiento = new Movimiento(importe, cliente);
			movimiento.setFecha(LocalDateTime.now());
			movimiento.setSaldo(saldo);
			if(modalidad==1) {
				movimiento.ingresar(importe);
				session.save(movimiento);
				estado=1;
			} else if(modalidad==2){
				if((movimiento.retirar(importe))!=false) {
					session.save(movimiento);
					estado=1;
				}else {
					estado=2;
				}
				
			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				estado=-1;
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return estado;
	}
/**
 * No implementado todavía

	public boolean actualizar(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * No implementado todavía
	 */

	public boolean delete(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Método para listar todos los movimientos */
	public List<Movimiento> list(int clienteId) {
		List<Movimiento> listaMovimientos=new ArrayList<>();
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query=session.getNamedQuery("HQL_GET_MOVIMIENTOS");
			query.setParameter("clienteId", clienteId);
			listaMovimientos = query.list();
			tx.commit();
			return listaMovimientos;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			
		}
		return listaMovimientos;
	}

	public boolean update(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

}