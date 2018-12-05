package services;

import java.math.BigDecimal;
import java.util.List;

import daoImplementaciones.MovimientoDaoImplementacion;
import model.Cliente;
import model.Movimiento;

/**
 * Controlador de movimietnos
 * 
 * @author PracticasSoftware5
 *
 */
public class ServiceMovimiento {
	/**
	 * Lo usamos para poder así enviar los datos obtenidos a la vista principal.
	 */
	/**
	 * Lo usamos para hacer las operaciones en el dao de movimientos.
	 */
	MovimientoDaoImplementacion daoMovimiento = new MovimientoDaoImplementacion();

	/**
	 * Constructor sin argumentos
	 */
	public ServiceMovimiento() {
	}

	/**
	 * // Llama al DAO para guardar un movimiento y envía a la vista el resultado
	 * 
	 * @param movimiento
	 * @param modalidad
	 * @return
	 */
	public Integer create(BigDecimal saldo, BigDecimal importe, Cliente cliente, int modalidad) {
		return daoMovimiento.create(saldo, importe, cliente, modalidad);
	}

	/**
	 * Lista todos los movimientos de un cliente determinado
	 * 
	 * @param id
	 * @return List<Movimiento>
	 * @throws Exception
	 */
	public List<Movimiento> list(int clienteId, long numeroPagina, int numeroDeRegistrosMostrarPorPaginaNuevo)
			throws Exception {
		return daoMovimiento.list(clienteId, numeroPagina, numeroDeRegistrosMostrarPorPaginaNuevo);

	}

}
