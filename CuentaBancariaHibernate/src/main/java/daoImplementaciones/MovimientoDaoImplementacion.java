package daoImplementaciones;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Cliente;
import model.Movimiento;
import resources.HibernateUtil;

public class MovimientoDaoImplementacion {
	// PROPIEDADES;
	SessionFactory sessFact = HibernateUtil.getSessionFactory();
	/**
	 * log: logger para la clase
	 */
	/**
	 * log: logger para la clase
	 */
	private final static Logger lOG = Logger.getLogger("file_connections");

	// GETTERS Y SETTERS-------------------------------------//

	// FIN GETTERS Y SETTERS-------------------------------------//
	// CREATE
	/**
	 * Sirve para crear un movimiento en la modalidad de ingresar o retirar
	 * 
	 * @param saldo
	 * @param importe
	 * @param cliente
	 * @param modalidad
	 * @return
	 */
	public Integer create(BigDecimal saldo, BigDecimal importe, Cliente cliente, int modalidad) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Integer estado = null;

		try {
			tx = session.beginTransaction();
			Movimiento movimiento = new Movimiento(importe, cliente);
			movimiento.setFecha(LocalDateTime.now());
			movimiento.setSaldo(saldo);
			if (modalidad == 1) {
				movimiento.ingresar(importe);
				session.save(movimiento);
				estado = 1;
			} else if (modalidad == 2) {
				if ((movimiento.retirar(importe)) != false) {
					session.save(movimiento);
					estado = 1;
				} else {
					estado = 2;
				}

			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				estado = -1;
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return estado;
	}

	/**
	 * No implementado todavía
	 * 
	 */
	public boolean delete(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

	/* Método para listar todos los movimientos. */
	/**
	 * Método para listar todos los movimientos.
	 * 
	 * @param clienteId
	 * @return
	 */
	public List<Movimiento> list(int clienteId, int... firstResultAndMaxResult) {
		List<Movimiento> listaMovimientos = new ArrayList<>();
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query<Movimiento> query = session.getNamedQuery("HQL_GET_MOVIMIENTOS");
			
			query.setParameter("clienteId", clienteId);
			query.setFirstResult(startPosition);
			query.setMaxResults(maxResult);
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

	/**
	 * No utilizado
	 * 
	 * @return
	 */
	public boolean update(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return false;
	}

}