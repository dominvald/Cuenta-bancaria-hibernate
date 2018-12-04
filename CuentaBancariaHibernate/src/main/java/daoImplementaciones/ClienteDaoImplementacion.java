package daoImplementaciones;

//IMPORTS
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import model.Direccion;
import model.Movimiento;
import resources.ClientesDireccionSaldo;
import resources.Estadisticas;
import resources.HibernateUtil;

/**
 * 
 * @author PracticasSoftware5 DAO cliente
 */
public class ClienteDaoImplementacion {
	// PROPIEDADES
	/**
	 * log: logger para la clase
	 * 
	 */
	private final static Logger lOG = Logger.getLogger("file_connections");
	/**
	 * errorSqlSW : lo utilizamos para poder imprimir los errores SQL, que nos da el
	 * printStackTrace Ej: e.printStackTrace(new PrintWriter(errorSqlSW));
	 */
	StringWriter errorSqlSW = new StringWriter();
	/**
	 * cadenaDatos: la utilizamos para enviar al correspondiente controlador el
	 * texto, que debe enviar a la vista en cade momento
	 */
	List<Cliente> listaClientesOperaciones = new ArrayList<>();

	private static SessionFactory sessFact = HibernateUtil.getSessionFactory();
	// GETTERS Y SETTERS
	private Transaction tx;

	// MÉTODOS
	// CRUD--------------------------------------------------------------------------------//

	// CREATE

	/* Método para crear un cliente y la dirección en la bd */
	public Integer create(String cif, String nombre, String apellido1, String apellido2) {
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

	// UPDATE
	/* Método para dar una dirección a un cliente el cif ce un cliente */
	public void updateClienteDatos(int ClienteID, Cliente clienteOperaciones) {

		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Cliente cliente = (Cliente) session.get(Cliente.class, ClienteID);
			cliente.setCif(clienteOperaciones.getCif());
			cliente.setNombre(clienteOperaciones.getNombre());
			cliente.setApellido1(clienteOperaciones.getApellido1());
			cliente.setApellido2(clienteOperaciones.getApellido2());
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

	/* Método para dar una dirección a un cliente */
	public void updateClienteDireccion(int direccionId, Direccion direccionOperaciones, String campo) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Direccion direccion = (Direccion) session.get(Direccion.class, direccionId);
			if (campo.equals("direccion")) {
				direccion.setDireccion(direccionOperaciones.getDireccion());
				direccion.setCp(direccion.getCp());
				direccion.setProvincia(direccion.getProvincia());
				direccion.setPoblacion(direccion.getPoblacion());
				direccion.setPais(direccion.getPais());
			} else if (campo.equals("cp")) {
				direccion.setCp(direccionOperaciones.getCp());
				direccion.setProvincia(direccion.getProvincia());
				direccion.setDireccion(direccion.getDireccion());
				direccion.setPoblacion(direccion.getPoblacion());
				direccion.setPais(direccion.getPais());
			} else if (campo.equals("provincia")) {
				direccion.setProvincia(direccionOperaciones.getProvincia());
				direccion.setCp(direccion.getCp());
				direccion.setDireccion(direccion.getDireccion());
				direccion.setPoblacion(direccion.getPoblacion());
				direccion.setPais(direccion.getPais());
			} else if (campo.equals("poblacion")) {
				direccion.setPoblacion(direccionOperaciones.getPoblacion());
				direccion.setCp(direccion.getCp());
				direccion.setProvincia(direccion.getProvincia());
				direccion.setDireccion(direccion.getDireccion());
				direccion.setPais(direccion.getPais());
			} else if (campo.equals("pais")) {
				direccion.setPais(direccionOperaciones.getPais());
				direccion.setCp(direccion.getCp());
				direccion.setProvincia(direccion.getProvincia());
				direccion.setPoblacion(direccion.getPoblacion());
				direccion.setDireccion(direccion.getDireccion());
			}

			session.update(direccion);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				System.out.println("doy error");
				tx.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
	}

	// UPDATE
	/* Método para dar una dirección a un cliente el cif ce un cliente */
	public void updateClienteSinDireccion(int ClienteID, Direccion direccion) {
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

	// DELETE
	/**
	 * @param: se
	 *             le pasa un cliente a eliminar.
	 * @return: devolverá true, si se eliminó correctamente
	 */
	public boolean delete(int clienteId) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();		
			Cliente cliente = (Cliente) session.get(Cliente.class, clienteId);
			session.delete(cliente);

				tx.commit();

				return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	// LISTADO DE CLIENTES Y DIRECCIÓN CORRESPONDIENTE
	/**
	 * Devuelve una lista con los datos, dirección, y saldo de todos los clientes de
	 * la base de datos.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	/* Método para listar todos los clientes sus datos */
	public List<ClientesDireccionSaldo> listarClientesDireccionSaldo(long numeroPagina,
			int numeroDeRegistrosMostrarPorPaginaNuevo) throws NoSuchFieldException, SecurityException {
		listaClientesOperaciones = null;
		List<ClientesDireccionSaldo> listaClientesDireccionSaldo = new ArrayList<ClientesDireccionSaldo>();
		Session session = sessFact.getCurrentSession();
		long resultado[] = new long[2];
		int contadorDeRegistros = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Cliente");
			query.setFirstResult(numeroDeRegistrosMostrarPorPaginaNuevo * (int) numeroPagina);
			query.setMaxResults(numeroDeRegistrosMostrarPorPaginaNuevo);
			listaClientesOperaciones = query.list();
			// System.out.println("\n\n---------------------------->listaClientesOperaciones.size():
			// "+listaClientesOperaciones.size());
			session.close();
			// java.util.Set<Movimiento> movimientos = new HashSet<Movimiento>();
			for (Iterator iterator = listaClientesOperaciones.iterator(); iterator.hasNext();) {
				Cliente cliente = (Cliente) iterator.next();
				contadorDeRegistros++;
				ClientesDireccionSaldo clientesDireccionSaldo = new ClientesDireccionSaldo();
				clientesDireccionSaldo.setClienteId(cliente.getId());
				clientesDireccionSaldo.setClienteNombre(cliente.getNombre());
				clientesDireccionSaldo.setClienteApellido1(cliente.getApellido1());
				clientesDireccionSaldo.setClienteApellido2(cliente.getApellido2());
				clientesDireccionSaldo.setClienteCif(cliente.getCif());
				try {
					clientesDireccionSaldo.setDireccionDireccion(cliente.getDireccion().getDireccion());
					clientesDireccionSaldo.setDireccionCp(cliente.getDireccion().getCp());
					clientesDireccionSaldo.setDireccionProvincia(cliente.getDireccion().getProvincia());
					clientesDireccionSaldo.setDireccionPoblacion(cliente.getDireccion().getPoblacion());
					clientesDireccionSaldo.setDireccionPais(cliente.getDireccion().getPais());
				} catch (Exception e) {
				}
				clientesDireccionSaldo.setSaldo(consigueSaldo(cliente.getId()));
				listaClientesDireccionSaldo.add(clientesDireccionSaldo);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return listaClientesDireccionSaldo;
	}

	/**
	 * Devuelve una lista con los datos, dirección, y saldo de todos los clientes de
	 * la base de datos.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	/* Método para listar todos los clientes sus datos */
	public ClientesDireccionSaldo datosClienteDireccionSaldo(int clienteId)
			throws NoSuchFieldException, SecurityException {
		listaClientesOperaciones = null;
		int contadorDeRegistros = 0;
		Cliente cliente = find(clienteId);
		contadorDeRegistros++;
		ClientesDireccionSaldo clientesDireccionSaldo = new ClientesDireccionSaldo();
		clientesDireccionSaldo.setClienteId(cliente.getId());
		clientesDireccionSaldo.setClienteNombre(cliente.getNombre());
		clientesDireccionSaldo.setClienteApellido1(cliente.getApellido1());
		clientesDireccionSaldo.setClienteApellido2(cliente.getApellido2());
		clientesDireccionSaldo.setClienteCif(cliente.getCif());
		try {
			clientesDireccionSaldo.setDireccionDireccion(cliente.getDireccion().getDireccion());
			clientesDireccionSaldo.setDireccionCp(cliente.getDireccion().getCp());
			clientesDireccionSaldo.setDireccionProvincia(cliente.getDireccion().getProvincia());
			clientesDireccionSaldo.setDireccionPoblacion(cliente.getDireccion().getPoblacion());
			clientesDireccionSaldo.setDireccionPais(cliente.getDireccion().getPais());
		} catch (Exception e) {
		}
		clientesDireccionSaldo.setSaldo(consigueSaldo(cliente.getId()));
		return clientesDireccionSaldo;
	}

	public Estadisticas estadisticasClientesDireccionSaldo() throws NoSuchFieldException, SecurityException {
		// Ponemos la lista de operaciones con clientes a null, por si acaso.
		listaClientesOperaciones = null;
		/**
		 * Contador de clientes encontrados
		 */
		long contadorInternoClientesEncontrados = 0L;
		/**
		 * Contador de clientes con saldo 0
		 */
		long contadorInternoClientesSaldoCero = 0L;
		/**
		 * Contador de clientes con saldo positivo
		 */
		long contadorInternoClientesSaldoPositivo = 0L;
		/**
		 * Contador de clientes sin dirección
		 */
		long contadorInternoClientesSinDirección = 0L;
		/**
		 * Sirve para almacenar la suma de todos los saldos de los clientes
		 */
		BigDecimal sumaSaldoClientes = BigDecimal.ZERO;
		/**
		 * Sirve para almacenar el saldo medio de los clientes
		 */
		BigDecimal saldoMedio = BigDecimal.ZERO;
		/**
		 * La usamos para comparar con un cero BigDecimal
		 */

		final BigDecimal saldoCero = BigDecimal.ZERO;

		/**
		 * Conseguimos la sesión actual
		 */
		Session session = sessFact.getCurrentSession();

		/**
		 * Creamos una transacción.
		 */
		Transaction tx = null;
		try {
			// Iniciamos la transacción.
			tx = session.beginTransaction();

			Query query = session.createQuery("select id from Cliente");
			// Recogemos el listado de ids que hay en la tabla clientes
			/**
			 * Sirve para guardar las id de los clientes
			 */
			List<Integer> listaIds = query.list();
			// Recuperamos el valor de los clientes encontrados
			contadorInternoClientesEncontrados = listaIds.size();

			// Hacemos esta consulta para obtner los ids de las direcciones de los clientes,
			// Todos pueden tener sólo una, así que calculando el numero de direcciones
			// y el número de clientes podremos obtener el número de clientes sin dirección.
			Query query1 = session.createQuery("select id from Direccion");
			// Recogemos el listado de clientes de toda la
			List<Integer> direcciones = query1.list();
			// Cerramos la sesión
			session.close();

			// Contamos el número de direcciones
			long contadorDirecciones = direcciones.size();
			// Calculamos el número de clientes sin dirección.
			contadorInternoClientesSinDirección = contadorInternoClientesEncontrados - contadorDirecciones;

			// Como ya tenemos los ids de todos los clientes conseguimos sus saldo
			// y realizamos las cuentas correspondientes.
			for (Iterator iterator = listaIds.iterator(); iterator.hasNext();) {
				int id = (int) iterator.next();
				if (consigueSaldo(id).compareTo(saldoCero) == 0) {
					// Incrementamos este contador de clientes sin saldo para las estadísticas
					contadorInternoClientesSaldoCero++;
					// Si es superior a 0
				} else {
					// Incrementamos este contador de clientes con saldo para las estadísticas
					contadorInternoClientesSaldoPositivo++;
				}
				sumaSaldoClientes = sumaSaldoClientes.add(consigueSaldo(id));
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		/**
		 * Lo utilizamos para así poder dividir el saldo total en el banco, entre él
		 * usándolo como denominador o divisor una vez le pasa el valor el contador de
		 * tipo entero contadorInternoClientesEncontrados, y así hacer la cuenta entre
		 * Big Decimals.
		 */

		BigDecimal divisor = new BigDecimal(contadorInternoClientesEncontrados);
		saldoMedio = sumaSaldoClientes.divide(divisor, 2, RoundingMode.HALF_EVEN);
		Estadisticas estadisticas = new Estadisticas(contadorInternoClientesEncontrados,
				contadorInternoClientesSaldoCero, contadorInternoClientesSaldoPositivo,
				contadorInternoClientesSinDirección, saldoMedio, sumaSaldoClientes);
		return estadisticas;
	}

	//
	// // COMPROBACIONES
	// /**
	// * @param String
	// * con el id a comprobar si está en la tabla de clientes.
	// * @return Boolean. Devuelve true si el id es encontrado en la tabla clientes
	// */
	// public Boolean checkPK(String idComprobar) {
	// cadenaDatos = "";
	// errorSQL = false;
	// Statement stm = null;
	// ResultSet rs = null;
	// Boolean encontrado = false;
	//
	// String sql = "SELECT id FROM CLIENTE WHERE id LIKE '%" + idComprobar + "%' ";
	//
	// try {
	// stm = con.createStatement();
	// rs = stm.executeQuery(sql);
	// if (rs.next()) {
	// encontrado = true;
	// }
	// stm.close();
	// rs.close();
	// lOG.debug("Se ha comprobado la Id correctamente.");
	// } catch (SQLException e) {
	// cadenaDatos = "Error: Clase ClienteDaoImplentacion, método obtener";
	// errorSQL = true;
	// e.printStackTrace(new PrintWriter(errorSqlSW));
	// lOG.error("Error: Clase ClienteDaoImplentacion, método comprobarId() " +
	// errorSqlSW.toString());
	// }
	//
	// return encontrado;
	// }
	/// **
	// * Comprueba si el dni que se le pasa como un String se ecuentra en la columna
	// cif
	// * de la tabla Cliente.
	// */
	// public Boolean checkCif(String cifComprobar) {
	// cadenaDatos = "";
	// errorSQL = false;
	// Statement stm = null;
	// ResultSet rs = null;
	// Boolean encontrado = false;
	//
	// String sql = "SELECT cif FROM CLIENTE WHERE cif LIKE '%" + cifComprobar + "%'
	// ";
	//
	// try {
	// stm = con.createStatement();
	// rs = stm.executeQuery(sql);
	// if (rs.next()) {
	// encontrado = true;
	// }
	// stm.close();
	// rs.close();
	// lOG.debug("Se comprueba el Cif correctamente");
	// } catch (SQLException e) {
	// cadenaDatos = "Error: Clase ClienteDaoImplentacion, método comprobarCif";
	// errorSQL = true;
	// e.printStackTrace(new PrintWriter(errorSqlSW));
	// lOG.error("Error: Clase ClienteDaoImplentacion, método comprobarCif() " +
	// errorSqlSW.toString());
	// }
	//
	// return encontrado;
	// }
	//
	// /**
	// * @return int con el valor del mayor id del cliente, que corresponde al
	// último
	// * cliente introducido Lo usamos cuando añadimos un cliente y acto
	// * sequido le queremos agregar la dirección.
	// */

	/* Método para encontrar un cliente por id de cliente */
	public Cliente find(int clienteId) {
		Cliente cliente = new Cliente();
		;
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			cliente = (Cliente) session.get(Cliente.class, clienteId);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cliente;

	}

	/* Método para cambiar el cif ce un cliente */
	public boolean compruebaCif(String cifBuscado) {
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("select c.cifCliente from Cliente c where c.cifCliente = :cifBusqueda");
			query.setParameter("cifBusqueda", cifBuscado);
			List<Cliente> cliente = query.setMaxResults(1).list();
			if (cliente.size() > 0) {
				return false;
			} else {
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	/**
	 * Consigue el saldo del cliente pasado.
	 */
		public BigDecimal consigueSaldo(int clienteId) {
			BigDecimal saldo = BigDecimal.ZERO;
			Session session = sessFact.getCurrentSession();
			List listaMovimientos=new ArrayList<>();
			Movimiento movimientoOperaciones=new Movimiento();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				//Query query= session.createQuery("from Movimiento mov where cliente_id=:clienteId order by mov.fecha desc, mov.id desc");
				//Query query= session.createSQLQuery("SELECT saldo FROM movimientos WHERE cliente_id=:clienteId ORDER BY fecha DESC, id desc");
				Query query=session.getNamedQuery("CONSIGUE_SALDO");
				query.setParameter("clienteId", clienteId);
				listaMovimientos =  query.setMaxResults(1).list();
			for(Object str : listaMovimientos) {
				String saldoString=str.toString();
				saldo=new BigDecimal(saldoString);
			}
				
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return saldo;

		}

	/**
	 * Comprueba que si existe un CIF en la base de datos.
	 */
	// private void existeCIF(String cifAcomprobar) {
	// Query q = session.createQuery("from Customer as c where c.surname =
	// :surname");
	// q.setString("surname", "Frank");
	// List result = q.list();
	//
	// }
	/**
	 * Devuelve la sessionFactory
	 * 
	 * @return
	 */
	public static SessionFactory devolverSesion() {
		return sessFact;
	}

	/* Método para encontrar un cliente por id de cliente */
	public List<Cliente> encuentraTexto(String cadenaDatos) {
		Boolean opcion = false;
		Session session = sessFact.getCurrentSession();
		Transaction tx = null;
		List<Cliente> listaClientesRestoCampos = new ArrayList<Cliente>();
		List<Cliente> listaClientesId = new ArrayList<Cliente>();
		listaClientesRestoCampos = null;
		try {
			tx = session.beginTransaction();
			Query consultaResto = session.createQuery(
					"from Cliente cli where cli.cifCliente like ?1 or cli.nombreCliente like ?1 or cli.apellido1Cliente like ?1 or cli.apellido2Cliente like ?1 order by cli.id desc");
			consultaResto.setParameter(1, "%" + cadenaDatos + "%");
			listaClientesRestoCampos = consultaResto.list();
			if (listaClientesRestoCampos.size() == 0) {
			}
			try {
				Query consultaId = session.createQuery("from Cliente cli where cli.id= ?1 order by cli.id desc");
				consultaId.setParameter(1, Integer.parseInt(cadenaDatos));
				listaClientesId = consultaId.list();
				if (listaClientesId.size() == 1) {
				}
			} catch (Exception e) {
			}
			for (Cliente cliente : listaClientesRestoCampos) {
				listaClientesId.add(cliente);
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listaClientesId;
	}
}
