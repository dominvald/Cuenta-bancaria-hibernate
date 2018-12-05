package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import daoImplementaciones.ClienteDaoImplementacion;
import model.Cliente;
import model.Direccion;
import resources.ClientesDireccionSaldo;
import resources.Estadisticas;
import resources.Recursos;

/**
 * 
 * @author PracticasSoftware5 Controlador de clientes secundario
 * 
 *         Controlador de las operaciones con clientes
 */
public class ServiceCliente {

	/**
	 * Sirve para almacenar el número de clientes encontrados en una consulta, es
	 * protected porque se usa también en el ControllerPrincipal que está en el
	 * mismo paquete.
	 */
	protected int contadorClientesEncontrados = 0;
	/**
	 * Crea un dao para realizar las operaciones sobre él.
	 */
	private ClienteDaoImplementacion daoClienteOperaciones = new ClienteDaoImplementacion();
	/**
	 * Constructor sin argumentos
	 */
	List<Cliente> listaClientesOperaciones = new ArrayList<Cliente>();

	public ServiceCliente() {
	}

	// CRUD
	// CREATE
	// Llama al DAO para guardar un cliente
	/**
	 * Llama al DAO para guardar un cliente
	 * 
	 * @param cliente
	 * @return boolean
	 * @throws SQLException
	 */

	public Integer create(String cif, String nombre, String apellido1, String apellido2) {
		return daoClienteOperaciones.create(cif, nombre, apellido1, apellido2);

	}

	// READ
	/**
	 * llama al DAO para obtener todos los clientes que coinciden con el string
	 * introducido y luego los muestra en la vista.
	 * 
	 * @param clienteBuscado
	 * @param opcion
	 * @return Cliente
	 * @throws SQLException
	 * @throws Exception
	 */
	public Cliente find(int clienteId) throws SQLException, Exception {
		contadorClientesEncontrados = 0;
		/**
		 * Lo creamos para almacenar una lista de clientes tipo List<Cliente>
		 */
		Cliente cliente = new Cliente();
		cliente = daoClienteOperaciones.find(clienteId);
		return cliente;

	}

	// UPDATE
	public void updateClienteDatos(int ClienteID, Cliente cliente) {
		daoClienteOperaciones.updateClienteDatos(ClienteID, cliente);
	}
	public void updateClienteDireccion(int ClienteID, Direccion direccion, String campo) {
		daoClienteOperaciones.updateClienteDireccion(ClienteID, direccion,campo);
	}
	public void updateClienteSinDireccion(int ClienteID, Direccion direccion) {
		daoClienteOperaciones.updateClienteSinDireccion(ClienteID, direccion);
	}

	// DELETE
	/**
	 * llama al DAO para eliminar un cliente
	 * 
	 * @param cliente
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean delete(int clienteId) {
		return daoClienteOperaciones.delete(clienteId);
	}




	// MÉTODOS PAGINACIÓN.
	/**
	 * Devuelve una lista de clientes con su dirección y saldo actual y recibe como parámetro
	 * el número de la página a listar.
	 * @param numeroPagina
	 * @return
	 * @throws SQLException
	 * @throws NoSuchFieldException
	 * @throws Exception
	 */
	public List<ClientesDireccionSaldo> listClientesDireccionSaldo(long numeroPagina, int numeroDeRegistrosMostrarPorPaginaNuevo)
			throws SQLException, NoSuchFieldException, Exception {
		List<ClientesDireccionSaldo> listaClientes = new ArrayList<ClientesDireccionSaldo>();
		listaClientes = daoClienteOperaciones.listarClientesDireccionSaldo(numeroPagina, numeroDeRegistrosMostrarPorPaginaNuevo);
		return listaClientes;
	}
	/**
	 * Devuelve la SessionFactory, para hacer la paginación
	 * 
	 * @return
	 */
	public static SessionFactory devolverSesion() {
		return ClienteDaoImplementacion.devolverSesion();
	}

	/**
	 * Devuelve un array con los valores del número de objetos y las páginas
	 * necesarias para mostrar los resultados.
	 * 
	 * @param sessFact
	 * @param entityName
	 * @param tamanyoPagina
	 * @return
	 */
	public static long[] paginacion(SessionFactory sessFact, String entityName, long tamanyoPagina) {
		return Recursos.paginacion(sessFact, tamanyoPagina);
	}

	// ESTADÍSTICAS
	public Estadisticas calculaEstadisticas() throws Exception, SecurityException {

		return daoClienteOperaciones.estadisticasClientesDireccionSaldo();

	}

	//COMPROBACIONES
	public boolean compruebaCif(String cifBuscado) {
		return daoClienteOperaciones.compruebaCif(cifBuscado);
	}
	//LISTADOS
	/**
	 * Devuelve un ClientesDireccionSaldo y la envía a la
	 * vista
	 * 
	 * @return List<ClaseClientesDireccionSaldo>
	 * @throws SQLException
	 * @throws Exception
	 * @throws NoSuchFieldException
	 * @throws Exception
	 */


	public ClientesDireccionSaldo datosClientesDireccionSaldo(int clienteId)
			throws SQLException, NoSuchFieldException, Exception {
		ClientesDireccionSaldo cliente = new ClientesDireccionSaldo();
		cliente = daoClienteOperaciones.datosClienteDireccionSaldo(clienteId);
		return cliente;
	}
	/**
	 * Devuelve un listado de clientes, que contenga la cadena de datos que se le pasa
	 * en cualquiera de los campos del cliente excepto el id de la diirección.
	 * @param cadenaDatos
	 * @return
	 */
	public List<Cliente> encuentraTexto(String cadenaDatos) {
		return daoClienteOperaciones.encuentraTexto(cadenaDatos);

	}

}
