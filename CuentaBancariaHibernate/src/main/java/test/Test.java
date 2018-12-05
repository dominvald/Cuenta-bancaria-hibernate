package test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Cliente;
import model.Direccion;
import model.Movimiento;
import resources.CalculaNif;
import resources.HibernateUtil;

public class Test {

	SessionFactory sessFact = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {

		Test test = new Test();
System.out.println(CalculaNif.isDniValido("51292306Y"));
		/* Añadir un cliente */
		// Direccion direccion = test.addDireccion("Calle Medul", 24750, "Leon", "La
		// Bañeza", "España");
		// for(int i=1;i<96;i++) {
		// Integer cliID1 = test.addCliente("2000", "Alberto"+i, "Domínguez", "Morán",
		// direccion);
		// }
		// Integer cliID1 = test.addCliente("2000", "Alberto", "Domínguez", "Morán",
		// direccion);
		// Integer cliID2 = test.addCliente("1000", "pico", "D", "Mo", null);
		//test.listDirecciones();
		//Direccion direccion1 = test.addDireccion("Calle fffffffMedul", 24750, "Lefffffffon", "La Baffffñeza", "España");
		// test.deleteDireccion(test.findCliente(cliID1).getDireccion().getId());
		//System.out.println("--------------");
		//test.listDirecciones();
		BigDecimal numero1 = new BigDecimal("100");
		BigDecimal numero2 = new BigDecimal("200");
		BigDecimal numero3 = new BigDecimal("300");
		// test.addMovimiento(numero1, test.findCliente(cliID1));
		// test.addMovimiento(numero2, test.findCliente(cliID1));
		
		// test.addMovimiento(numero3, test.findCliente(cliID1));
		// test.consigueSaldo(cliID1);
		// test.listMovimientos();
		// Integer cliID2 = test.addCliente("1000", "Pco", "f", "fff");

		/* Listar todos los clientes */
		// test.listClientes();
		/* Conseguir un cliente por id */
		/* Actualizar cif */
		// test.updateCliente(cliID1, "5000");
		// Direccion direccionNueva=test.addDireccion("Calle Medulcc", 24754, "Leonv",
		// "La Bañezav", "Españav");
		// test.updateDireccion(test.findCliente(cliID1).getDireccion().getId(), "otra
		// calle",0,"","g","");
		// test.deleteDireccion(test.findCliente(cliID1).getDireccion().getId());
		/* Borrar cliente */
		// test.deleteCliente(cliID1);

		/* Listar todos los clientes */
		// test.listClientes();

		System.exit(0);
	}
	// CLIENTES

	/* Método para crear un cliente y la dirección en la bd */
	public Integer addCliente(String cif, String nombre, String apellido1, String apellido2, Direccion direccion) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Integer clienteId = null;

		try {
			tx = session.beginTransaction();
			Cliente cliente = new Cliente();
			cliente.setCif(cif);
			cliente.setNombre(nombre);
			cliente.setApellido1(apellido1);
			cliente.setApellido2(apellido2);
			cliente.setDireccion(direccion);
			clienteId = (Integer) session.save(cliente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return clienteId;
	}

	/* Método para listar todos los clientes */
	public void listClientes() {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List clientes = session.createQuery("FROM Cliente").list();
			for (Iterator iterator = clientes.iterator(); iterator.hasNext();) {
				Cliente cliente = (Cliente) iterator.next();
				System.out.print("Id: " + cliente.getId());
				System.out.print(" CIF: " + cliente.getCif());
				System.out.print(" Nombre: " + cliente.getNombre());
				System.out.print(" Primer Apellido: " + cliente.getApellido1());
				System.out.println(" Segundo apellido: " + cliente.getApellido2());
				// System.out.println(" Direccion: " + cliente.getDireccion());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para cambiar el cif ce un cliente */
	public void updateCliente(Integer ClienteID, String cif) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Cliente cliente = (Cliente) session.get(Cliente.class, ClienteID);
			cliente.setCif(cif);
			session.update(cliente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para dar una dirección a un cliente el cif ce un cliente */
	public void updateCliente(Integer ClienteID, Direccion direccion) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Cliente cliente = (Cliente) session.get(Cliente.class, ClienteID);
			cliente.setDireccion(direccion);
			session.update(cliente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para borrar un cliente */
	public void deleteCliente(Integer ClienteID) {

		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Cliente cliente = (Cliente) session.get(Cliente.class, ClienteID);
			session.delete(cliente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para encontrar un cliente por id de cliente */
	public Cliente findCliente(Integer clienteId) {
		Cliente cliente = new Cliente();
		Direccion direccion = new Direccion();
		Movimiento movimiento = new Movimiento();
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cliente = (Cliente) session.get(Cliente.class, clienteId);
			// direccion = (Direccion) session.get(Direccion.class, direccionId);
			// movimiento = (Movimiento) session.get(Movimiento.class, movimientoId);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cliente;

	}

	// DIRECCIONES
	/* Metodo para añadir una dirección en la base de datos */
	public Direccion addDireccion(String direccionCampo, int cp, String provincia, String poblacion, String pais) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Integer direccionID = null;
		Direccion direccion = null;

		try {
			tx = session.beginTransaction();
			// direccion = new Direccion(direccionCampo, cp, provincia, poblacion, pais);
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

	/* Método para listar todas la direcciones */
	public void listDirecciones() {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List direcciones = session.createQuery("FROM Direccion").list();
			for (Iterator iterator = direcciones.iterator(); iterator.hasNext();) {
				Direccion direccion = (Direccion) iterator.next();
				System.out.print("Id: " + direccion.getId());
				System.out.print(" Dirección: " + direccion.getDireccion());
				System.out.print(" C.P: " + direccion.getCp());
				System.out.print(" Provincia: " + direccion.getProvincia());
				System.out.print(" Población: " + direccion.getPoblacion());
				System.out.print(" País: " + direccion.getPais());

			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para cambiar la direccion de un cliente */
	public void updateDireccion(Integer DireccionID, String direccionCampo, int cpCampo, String provinciaCampo,
			String poblacionCampo, String paisCampo) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Direccion direccion = (Direccion) session.get(Direccion.class, DireccionID);
			if (!direccionCampo.equals("")) {
				direccion.setDireccion(direccionCampo);
			}
			if (cpCampo != 0) {
				// direccion.setCp(cpCampo);
			}
			if (!provinciaCampo.equals("")) {
				direccion.setProvincia(provinciaCampo);
			}
			if (!poblacionCampo.equals("")) {
				direccion.setPoblacion(poblacionCampo);
			}
			if (!paisCampo.equals("")) {
				direccion.setPais(paisCampo);
			}
			session.update(direccion);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Método para borrar una dirección */
	public void deleteDireccion(Integer DireccionID) {

		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Direccion direccion = (Direccion) session.get(Direccion.class, DireccionID);
			direccion.setDireccion("");
			// direccion.setCp(0);
			direccion.setProvincia("");
			direccion.setPoblacion("");
			direccion.setPais("");
			session.update(direccion);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// MOVIMIENTOS
	/* Método para crear un movimiento */
	public Integer addMovimiento(BigDecimal importe, Cliente cliente) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		Integer movimientoId = null;
		try {
			tx = session.beginTransaction();
			Movimiento movimiento = new Movimiento(importe, cliente);
			movimiento.setFecha(LocalDateTime.now());
			// movimiento.setSaldo(importe);
			movimiento.ingresar(importe);
			session.save(movimiento);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return movimientoId;
	}

	/**
	 * Consigue el saldo del cliente pasado.
	 */
	public BigDecimal consigueSaldo(int idCliente) {
		BigDecimal saldo = BigDecimal.ZERO;
		// String sql = "SELECT saldo FROM movimientos WHERE cliente_id ='" + idCliente
		// + "' ORDER BY fecha DESC, id desc LIMIT 1;";
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			// List movimientos = session.createQuery("FROM MOVIMIENTO").list();
			// List movimientos = session.createQuery("FROM Cliente").list();
			// Criterio ejemplo
			// CriteriaBuilder cb = session.getCriteriaBuilder();
			// CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
			// Root<Cliente> root = query.from(Cliente.class);
			// query = query.select(root).where(cb.equal(root.get("nombreCliente"),
			// "pico"));
			// TypedQuery<Cliente> tq = session.createQuery(query);
			// List<Cliente> clientes = tq.getResultList();
			// for (Cliente u : clientes) {
			// System.out.println(u.getNombre());
			// }
			// FIN Criterio ejemplo
			// CriteriaBuilder cb = session.getCriteriaBuilder();
			// CriteriaQuery<Movimiento> query = cb.createQuery(Movimiento.class);
			// Root<Movimiento> root = query.from(Movimiento.class);
			// query = query.select(root).where(cb.equal(root.get("cliente").get("id"),
			// 358));
			// TypedQuery<Movimiento> tq = session.createQuery(query);
			// List<Movimiento> movimientos = new ArrayList<Movimiento>();
			// movimientos = tq.getResultList();
			// System.out.println("\n\n " + movimientos.size());
			// //movimientos =
			// tq.setFirstResult(movimientos.size()).setMaxResults(1).getResultList();
			// for (Movimiento u : movimientos) {
			// System.out.println(u + "\n");
			// }
			List movimientos1 = session.createQuery(
					"from Movimiento mov where cliente_id='" + idCliente + "' order by mov.fecha desc, mov.id desc")
					.setMaxResults(1).list();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			for (Iterator iterator = movimientos1.iterator(); iterator.hasNext();) {
				Movimiento movimiento = (Movimiento) iterator.next();
				System.out.print("\nImporte: " + movimiento.getImporte());
				System.out.print(" Saldo: " + movimiento.getSaldo() + " Fecha: "
						+ movimiento.getFecha().format(formatter) + "\n");

			}
			// List movimientos = session.createCriteria(Movimiento.class)
			// .add( Restrictions.like("id", 44) )
			// .list();
			// for (Iterator iterator = movimientos.iterator(); iterator.hasNext();) {
			// Movimiento movimiento = (Movimiento) iterator.next();
			// System.out.print("Id: " + movimiento.getSaldo());

			// }
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return saldo;

	}

	/* Método para listar todos los movimientos */
	public void listMovimientos() {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List movimientos = session.createQuery("FROM Movimiento").list();
			for (Iterator iterator = movimientos.iterator(); iterator.hasNext();) {
				Movimiento movimiento = (Movimiento) iterator.next();
				System.out.print("Importe: " + movimiento.getImporte());
				System.out.print(" Saldo: " + movimiento.getSaldo() + "\n");
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
