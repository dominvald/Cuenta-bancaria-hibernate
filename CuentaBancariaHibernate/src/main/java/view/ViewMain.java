package view;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import daoImplementaciones.ClienteDaoImplementacion;
import model.Cliente;
import model.Direccion;
import model.Movimiento;
import resources.ClientesDireccionSaldo;
import resources.Estadisticas;
import resources.Recursos;

/**
 * 
 * @author po Vista
 */
public class ViewMain {
	ClienteDaoImplementacion daoClienteOperaciones = new ClienteDaoImplementacion();

	/**
	 * 
	 * @param cadenaEnviada
	 *            Escribe en consola la cadena que recibe
	 */
	public void escribirEnConsola(String cadenaEnviada) {
		System.out.println(cadenaEnviada);
	}

	// CLIENTES
	/**
	 * Imprime por pantalla una lista de clientes y el el texto varía segun la
	 * opción que se le pase
	 * 
	 * @param clientes
	 * @param opcion
	 */
	public void verClientesEncontrados(List<Cliente> listaClientes) {
		/**
		 * Contador interno de clientes encontrados
		 */
		int contadorInternoClientesEncontrados = 0;

		/**
		 * Cliente de operaciones
		 */
		if (listaClientes.size() > 0) {
			Cliente clienteOperaciones = new Cliente();
			for (Cliente cliente : listaClientes) {
				clienteOperaciones = cliente;
				contadorInternoClientesEncontrados++;
				System.out.println(
						"\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\tCLIENTE SELECCIONADO:\n");
				System.out.printf("\t%-10s%-30s%-15s%-20s%-20s%-20s%-20s%-20s\n", "", "Reg. Nº", "--- Id ---", "Cif",
						"Nombre", "Primer Apellido", "Segundo Apedillo", "Saldo");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("\n\t%-10s%-30s%-15s%-20s%-20s%-20s%-20s%-20s\n\n", "-->",
						contadorInternoClientesEncontrados, " << " + clienteOperaciones.getId() + " >> ",
						clienteOperaciones.getCif(), clienteOperaciones.getNombre(), clienteOperaciones.getApellido1(),
						clienteOperaciones.getApellido2(),
						Recursos.bigDecimalToString(daoClienteOperaciones.consigueSaldo(clienteOperaciones.getId()))
								+ "€");
			}
			System.out.println("Hay " + contadorInternoClientesEncontrados + " coincidencias.");
		} else {
			System.out.println("No hay coincidencias.");
		}
	}

	// NOTA----->MÉTODO NO USADO
	/**
	 * Muestra los datos de un cliente por pantalla
	 */
	public void verCliente(Cliente cliente, String texto) {

		if (cliente != null) {
			System.out.println(
					"\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t" + texto + ":\n");
			System.out.printf("\t%-10s%-30s%-15s%-20s%-20s%-20s%-20s%-20s\n", "", "Reg. Nº", "--- Id ---", "Cif",
					"Nombre", "Primer Apellido", "Segundo Apedillo", "Saldo");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("\n\t%-10s%-30s%-15s%-20s%-20s%-20s%-20s%-20s\n\n", "-->", "1",
					" << " + cliente.getId() + " >> ", cliente.getCif(), cliente.getNombre(), cliente.getApellido1(),
					cliente.getApellido2(),
					Recursos.bigDecimalToString(daoClienteOperaciones.consigueSaldo(cliente.getId())) + "€");
		} else {
			System.out.println("Cliente inexistente");
		}
	}

	// MOVIMIENTOS
	/**
	 * Imprime por pantalla una lista con los movimietnos del cliente seleccionado
	 * 
	 * @param ListaMovimientos
	 */
	public void verMovimientosEncontrados(List<Movimiento> ListaMovimientos, long numeroPagina, long numeroDeRegistrosMostrarPorPaginaNuevo) {
		long contadorRegistros = numeroPagina * numeroDeRegistrosMostrarPorPaginaNuevo;
		/**
		 * Contador interno de movimientos encontrados
		 */
		int contadorInternoMovimientosEncontrados = 0;
		/**
		 * Objeto movimiento para las operaciones
		 */
		Movimiento movimientoOperaciones = new Movimiento();
		// Recorremos la lista de movimientos que se nos está pasando como prámetro
		for (Movimiento movimiento : ListaMovimientos) {
			contadorRegistros++;
			// Guardamos cada movimiento en nuestro movimientoOperaciones
			movimientoOperaciones = movimiento;
			// Incrementamos en uno el contador de movimientos.
			contadorInternoMovimientosEncontrados++;
			// Si hay movimientos, imprimimos el título del listado y los encabezados
			if (contadorInternoMovimientosEncontrados == 1) {
				System.out.println("LISTADO MOVIMIENTOS:\n");
				System.out.printf("\t%-10s%20s%15s%-10s%20s%20s\n", "Nº", "Importe", "Saldo", "", "Fecha", "Hora\n");
			}
			/**
			 * Creamos este objeto para dar el formato correcto a la fecha
			 */
			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
			// Imprimos cada movimiento
			System.out.printf("\t%-10s%20s%15s%-10s%20s%20s\n", contadorRegistros,
					Recursos.bigDecimalToString(movimientoOperaciones.getImporte()) + "€",
					Recursos.bigDecimalToString(movimientoOperaciones.getSaldo()) + "€", "",
					formatoFecha.format(movimientoOperaciones.getFecha()),
					formatoHora.format(movimientoOperaciones.getFecha()));
		}
	}

	// DIRECCIONES
	/**
	 * Imprime por pantalla la dirección del cliente seleccionado
	 * 
	 * @param Direccion
	 */
	public void veDireccionEncontrada(Direccion direccion) {
		/**
		 * Objeto movimiento para las operaciones
		 */
		Direccion direccionOperaciones = new Direccion();
		// Guardamos cada dirección en nuestro direccionOperaciones
		direccionOperaciones = direccion;

		// Si no hay movimientos imprimimos el correspondiente mensaje.
		if (direccionOperaciones == null || direccionOperaciones.getDireccion() == "") {
			System.err.println("\nNo tiene una dirección asignada todavía.\n");
		} else {
			// Si hay direcciones, imprimimos el título del listado y los encabezados

			System.out.println("DIRECCION ACTUAL:\n");
			System.out.printf("\t%-10s%-35s%-10s%-30s%-30s%-30s\n", "Nº", "Dirección", "C.P", "Provincia", "Población",
					"País");
			System.out.printf("\t%-10s%-35s%-10s%-30s%-30s%-30s\n", 1, direccionOperaciones.getDireccion(),
					direccionOperaciones.getCp(), direccionOperaciones.getProvincia(),
					direccionOperaciones.getPoblacion(), direccionOperaciones.getPais() + "\n");
		}
	}

	// Lista ClaseClientesDireccionSaldo
	/**
	 * Imprime por pantalla una lista de clientes y el el texto varía segun la
	 * opción que se le pase
	 * 
	 * @param clientes
	 * @param opcion
	 */
	public void verListadoClientesDireccionSaldo(List<ClientesDireccionSaldo> listaClaseClientesDireccionSaldo,
			long numeroPagina, long numeroDeRegistrosMostrarPorPaginaNuevo) {
		long contadorRegistros = numeroPagina * numeroDeRegistrosMostrarPorPaginaNuevo;
		/**
		 * Lo usamos para contar el número de clientes en la base de datos
		 */
		int contadorInternoClientesEncontrados = 0;
		/**
		 * Sirve para almacenar el número de clientes cuyo saldo es 0
		 */
		@SuppressWarnings("unused")
		int contadorInternoClientesSaldoCero = 0;
		/**
		 * Sirve para almacenar el número de clientes cuyo saldo no es 0
		 */
		@SuppressWarnings("unused")
		int contadorInternoClientesSaldoPositivo = 0;
		/**
		 * Sirve para almacenar el número de clientes no tienen dirección
		 */
		@SuppressWarnings("unused")
		int contadorInternoClientesSinDirección = 0;
		/**
		 * Sirve para almacenar la suma de todos los saldos de los clientes
		 */
		BigDecimal sumaSaldoClientes = BigDecimal.ZERO;
		/**
		 * Lo usamos para hacer las operaciones
		 */
		/**
		 * Lo usamos para comparar el saldo con 0
		 */
		BigDecimal saldoCero = BigDecimal.ZERO;
		System.out.println(Recursos.generaBarras(180));
		System.out.println(Recursos.generaBarras(180) + "\n");
		if (listaClaseClientesDireccionSaldo != null) {

			ClientesDireccionSaldo claseClientesDireccionSaldoOperaciones = new ClientesDireccionSaldo();
			// Recorremos la lista
			for (ClientesDireccionSaldo claseClientesDireccionSaldo : listaClaseClientesDireccionSaldo) {
				// Guardamos cada registro en nuestro objeto de operaciones
				claseClientesDireccionSaldoOperaciones = claseClientesDireccionSaldo;
				// Incrementamos en uno nuestro contador de clientes encontrados
				contadorInternoClientesEncontrados++;
				// Vamos sumando el saldo, para las estadísticas, en este caso para saber la
				// suma de todos
				// los saldos de los clientes.
				sumaSaldoClientes = sumaSaldoClientes.add(claseClientesDireccionSaldoOperaciones.getSaldo());
				// Si el saldo es 0
				if (claseClientesDireccionSaldoOperaciones.getSaldo().compareTo(saldoCero) == 0) {
					// Incrementamos este contador de clientes sin saldo para las estadísticas
					contadorInternoClientesSaldoCero++;
					// Si es superior a 0
				} else {
					// Incrementamos este contador de clientes con saldo para las estadísticas
					contadorInternoClientesSaldoPositivo++;
				}
				// En el momento que encontramos un registro ya podemos imprimir el nombre de la
				// lista
				// así como todos los encabezados

				if (contadorInternoClientesEncontrados == 1) {
					System.out.println(Recursos.generaEspaciosEnBlanco(82) + "LISTADO CLIENTES:");
					System.out.println(Recursos.generaBarras(190));
					System.out.printf("\t%-10s%-10s%-15s%-10s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n", "Reg. Nº",
							"Id", "Saldo", "CIF", "Nombre", "Primer Apellido", "Segundo Apedillo", "Dirección", "C.P.",
							"Provincia", "Población", "País");

				}
				System.out.println(Recursos.generaBarras(190));
				// Si el cliente tiene dirección, recogemos todos sus datos
				if (claseClientesDireccionSaldoOperaciones.getDireccionDireccion() != null) {
					contadorRegistros++;
					System.out.printf("\t%-10s%-10s%-15s%-10s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n",
							contadorRegistros, claseClientesDireccionSaldoOperaciones.getClienteId(),
							Recursos.bigDecimalToString(claseClientesDireccionSaldoOperaciones.getSaldo()) + "€",
							claseClientesDireccionSaldoOperaciones.getClienteCif(),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteNombre(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteApellido1(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteApellido2(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getDireccionDireccion(), 21),
							claseClientesDireccionSaldoOperaciones.getDireccionCp(),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getDireccionProvincia(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getDireccionPoblacion(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getDireccionPais(), 11));
					System.out.println(Recursos.generaBarras(190));
					// Si no tiene dirección
				} else {
					// Incrementamos en uno el contador para las estadísticas de clientes que
					// todavía
					// no han introducido la dirección.
					// Cabe destacar, que para ello, sólo se consulta el campo de la tabla dirección
					// pues es el único campo necesario para crear una dirección tanto en la base
					// de datos como en nuestra aplicación.
					contadorInternoClientesSinDirección++;
					// Imprimimos todos los registros, pero en este caso como la consulta viene de
					// un left join,
					// existen null, y para no imprimirlos, a la hora de imprimirlos por pantalla
					// cambiamos sus
					// valores por "---".
					System.out.printf("\t%-10s%-10s%-15s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n", contadorRegistros,
							claseClientesDireccionSaldoOperaciones.getClienteId(),
							Recursos.bigDecimalToString(claseClientesDireccionSaldoOperaciones.getSaldo()) + "€",
							claseClientesDireccionSaldoOperaciones.getClienteCif(),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteNombre(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteApellido1(), 11),
							Recursos.truncate(claseClientesDireccionSaldoOperaciones.getClienteApellido2(), 11), "---",
							"---", "---", "---", "---");
					System.out.println(Recursos.generaBarras(190));
				}

			}
		} else {
			System.out.println(Recursos.generaBarras(80));
			System.err.println("No hay conexión con la base de datos");
		}
		System.out.println(Recursos.generaBarras(190));
		System.out.println(Recursos.generaBarras(190) + "\n");
	}

	public void verClienteDireccionSaldo(ClientesDireccionSaldo clienteDireccionSaldo) {
		long contadorRegistros = 0;
		System.out.println(Recursos.generaBarras(190));
		System.out.println(Recursos.generaBarras(190) + "\n");
		if (clienteDireccionSaldo != null) {
			System.out.println(Recursos.generaEspaciosEnBlanco(82) + "CLIENTE SELECCIONADO:");
			System.out.println(Recursos.generaBarras(190));
			System.out.printf("\t%-10s%-10s%-15s%-10s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n", "Reg. Nº", "Id",
					"Saldo", "CIF", "Nombre", "Primer Apellido", "Segundo Apedillo", "Dirección", "C.P.", "Provincia",
					"Población", "País");

			System.out.println(Recursos.generaBarras(190));
			// Si el cliente tiene dirección, recogemos todos sus datos
			if (clienteDireccionSaldo.getDireccionDireccion() != null) {
				contadorRegistros++;
				System.out.printf("\t%-10s%-10s%-15s%-10s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n", contadorRegistros,
						clienteDireccionSaldo.getClienteId(),
						Recursos.bigDecimalToString(clienteDireccionSaldo.getSaldo()) + "€",
						clienteDireccionSaldo.getClienteCif(),
						Recursos.truncate(clienteDireccionSaldo.getClienteNombre(), 11),
						Recursos.truncate(clienteDireccionSaldo.getClienteApellido1(), 11),
						Recursos.truncate(clienteDireccionSaldo.getClienteApellido2(), 11),
						Recursos.truncate(clienteDireccionSaldo.getDireccionDireccion(), 11),
						clienteDireccionSaldo.getDireccionCp(),
						Recursos.truncate(clienteDireccionSaldo.getDireccionProvincia(), 11),
						Recursos.truncate(clienteDireccionSaldo.getDireccionPoblacion(), 11),
						Recursos.truncate(clienteDireccionSaldo.getDireccionPais(), 11));
				System.out.println(Recursos.generaBarras(190));
				// Si no tiene dirección
			} else {
				// valores por "---".
				System.out.printf("\t%-10s%-10s%-15s%-10s%-15s%-20s%-20s%-25s%-10s%-15s%-15s%-15s\n", contadorRegistros,
						clienteDireccionSaldo.getClienteId(),
						Recursos.bigDecimalToString(clienteDireccionSaldo.getSaldo()) + "€",
						clienteDireccionSaldo.getClienteCif(),
						Recursos.truncate(clienteDireccionSaldo.getClienteNombre(), 11),
						Recursos.truncate(clienteDireccionSaldo.getClienteApellido1(), 11),
						Recursos.truncate(clienteDireccionSaldo.getClienteApellido2(), 11), "---", "---", "---", "---",
						"---");
				System.out.println(Recursos.generaBarras(190));
			}

		} else {
			System.out.println(Recursos.generaBarras(80));
			System.err.println("clienteDireccionSaldo es null");
		}
		System.out.println(Recursos.generaBarras(190));
		System.out.println(Recursos.generaBarras(190) + "\n");
	}

	public void imprimeEstadisticas(Estadisticas estadisticas) {
		Estadisticas estadisticasOperaciones = estadisticas;
		System.out.println(Recursos.generaBarras(80));
		// Imprimimos el encabezado de las estadísticas
		System.out.println("\n" + Recursos.generaEspaciosEnBlanco(34) + "ESTADÍSTICAS");
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Total clientes en base de datos
		System.out.printf("\t%-60s%s\n", "Total clientes en base de datos:",
				estadisticasOperaciones.getContadorInternoClientesEncontrados());
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Número de clientes en base de datos con saldo 0
		System.out.printf("\t%-60s%s\n", "Número de clientes en base de datos con saldo 0:",
				estadisticasOperaciones.getContadorInternoClientesSaldoCero());
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Número de clientes en base de datos con saldo positivo
		System.out.printf("\t%-60s%s\n", "Número de clientes en base de datos con saldo positivo:",
				estadisticasOperaciones.getContadorInternoClientesSaldoPositivo());
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Número de clientes sin dirección
		System.out.printf("\t%-60s%s\n", "Número de clientes sin dirección:",
				estadisticasOperaciones.getContadorInternoClientesSinDirección());
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Saldo medio clientes en base de datos
		System.out.printf("\t%-60s%s\n", "Saldo medio clientes en base de datos:",
				Recursos.bigDecimalToString(estadisticasOperaciones.getSaldoMedio()) + "€");
		System.out.println(Recursos.generaBarras(80));
		// Imprimos el Saldo Total de los clientes en base de datos
		System.out.printf("\n\t%-60s%s\n", "Saldo Total de los clientes en base de datos:",
				Recursos.bigDecimalToString(estadisticasOperaciones.getSumaSaldoClientes()) + "€");
		System.out.println(Recursos.generaBarras(80));

	}

	public void muestraDatosClienteNuevo(String cifNuevo, String nombreNuevo, String apellido1Nuevo,
			String apellido2Nuevo) {
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%56s\n", "DATOS CLIENTE:         ", "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "CIF: ", "|", cifNuevo, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Nombre: ", "|", nombreNuevo, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Primer apellido: ", "|", apellido1Nuevo, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Segundo apellido: ", "|", apellido2Nuevo, "|");
		System.out.println("\t" + Recursos.generaBarras(81));

	}

	public void muestraDatosClienteSeleccionado(String cifSeleccionado, String nombreSeleccionado,
			String apellido1Seleccionado, String apellido2Seleccionado) {
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%56s\n", "DATOS CLIENTE:        ", "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "CIF: ", "|", cifSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Nombre: ", "|", nombreSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Primer apellido: ", "|", apellido1Seleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Segundo apellido: ", "|", apellido2Seleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));

	}

	public void muestraDatosDireccionNuevo(String direccionSeleccionado, String cpSeleccionado,
			String provinciaSeleccionado, String poblacionSeleccionado, String paisSeleccionado) {
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%54s\n", "DIRECCIÓN CLIENTE:        ", "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Dirección: ", "|", direccionSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "C.P: ", "|", cpSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Provincia: ", "|", provinciaSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Población: ", "|", poblacionSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "País: ", "|", paisSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));

	}

	public void muestraDatosDireccionSeleccionado(String direccionSeleccionado, String cpSeleccionado,
			String provinciaSeleccionado, String poblacionSeleccionado, String paisSeleccionado) {
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%54s\n", "DIRECCIÓN CLIENTE:        ", "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Dirección: ", "|", direccionSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "C.P: ", "|", cpSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Provincia: ", "|", provinciaSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "Población: ", "|", poblacionSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));
		System.out.printf("\t| %-20s%-7s%-51s%-1s\n", "País: ", "|", paisSeleccionado, "|");
		System.out.println("\t" + Recursos.generaBarras(81));

	}
}
