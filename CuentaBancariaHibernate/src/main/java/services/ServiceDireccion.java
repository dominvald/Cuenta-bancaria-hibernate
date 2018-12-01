package services;

import daoImplementaciones.DireccionDaoImplementacion;
import model.Direccion;

/**
 * 
 * @author PracticasSoftware5 Controlador de direcciones secundario
 * 
 *         Controlador de las direcciones con clientes
 */
public class ServiceDireccion {
	/**
	 * Constructor sin argumentos
	 */
	private DireccionDaoImplementacion daoDireccionOperaciones = new DireccionDaoImplementacion();

	public ServiceDireccion() {
	}

	// CRUD
	// CREATE
	// llama al DAO para guardar un cliente
	/**
	 * LLama al Dao para crear una dirección
	 * 
	 * @param direccion
	 * @return boolean
	 */
	public Direccion create(String direccionNuevo,String cpNuevo, String provinciaNuevo, String poblacionNuevo, String paisNuevo) {
		return daoDireccionOperaciones.create(direccionNuevo,cpNuevo, provinciaNuevo, poblacionNuevo, paisNuevo);
	}

	// READ
	// Llama al DAO para buscar un cliente
	/**
	 * Llama al DAO para buscar un cliente
	 * 
	 * @param idBuscar
	 * @return Direccion
	 */
	public void find(int idBuscar) {


	}

	// UPDATE
	// Llama al DAO para actualizar una direccion
	/**
	 * Llama al DAO para actualizar una direccion
	 * 
	 * @param direccion
	 * @return boolean
	 */
	public void actualizar(Direccion direccion) {

	}
	// DELETE
	// No se usa porque no lo necesitamos, cuando se elimina un cliente, se elimina
	// la dirección tambi´rn.
}
