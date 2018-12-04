package controller;

import java.math.BigDecimal;
//IMPORTS
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import daoImplementaciones.ClienteDaoImplementacion;
import model.Cliente;
import model.Direccion;
import model.Movimiento;
import view.ViewMain;
import resources.ClientesDireccionSaldo;
import resources.Estadisticas;
import resources.HibernateUtil;
import resources.Recursos;
import resources.Sonido;
import services.ServiceCliente;
import services.ServiceDireccion;
import services.ServiceMovimiento;

/**
 * 
 * @author Alberto Domínguez Morán
 *
 *         Controlador principal de la aplicación.
 */
public class ControllerPrincipal {
	// ------------------------------------------------------------------------------------------|
	// PROPIEDADES
	// ---------------------------------------------------------------------------|
	// -----------------------------------------------------------------------------------------|

	// LOG
	// -------------------------------------------------------------------------//

	/**
	 * Log4j.
	 */
	private final static Logger LOG = Logger.getLogger("file_data");

	// FIN LOG
	// ---------------------------------------------------------------------------------//

	// VARIABLES PARA LA INTRODUCCIÓN DE DATOS
	// ---------------------------------------//

	// Variables utilizadas para controlar la introducción de datos.

	/**
	 * Sirve para escanear la entrada de carácteres por teclado.
	 */
	private Scanner scanner = new Scanner(System.in);
	/**
	 * Sirva para almacenar los datos de tipo cadena introducidos por el usuario.
	 */
	private String cadenaDatos = "";
	/**
	 * Sirve para saber la opción seleccionada en los menús de la aplicación.
	 * 
	 */
	private String opcionStringSeleccionadaMenu;

	// VARIABLES PARA LA INTRODUCIÓN DE DATOS PARA CLIENTES

	// Variables para guardar los datos antiguos o actuales del cliente
	// seleccionado.
	private int idClienteSeleccionado=0;

	/**
	 * cifSeleccionado: guarda el cif antiguo o actuales de cliente seleccionado.
	 */
	private String cifSeleccionado = "";
	/**
	 * nombreSeleccionado: guarda el nombre seleccionado antiguo o actual del
	 * cliente seleccionado.
	 */
	private String nombreSeleccionado = "";
	/**
	 * apellido1Seleccionado: guarda el primer apellido seleccionado antiguo o
	 * actual del cliente seleccionado.
	 */
	private String apellido1Seleccionado = "";
	/**
	 * apellido2Seleccionado: guarda el segundo apellido seleccionado antiguo o
	 * actual del cliente seleccionado.
	 */
	private String apellido2Seleccionado = "";

	// Variables para guardar los datos a actualizar del cliente o de un cliente
	// nuevo.

	/**
	 * guarda el id dado por la base de datos al cliente, en el momento de crearlo.
	 */
	private int idClienteNuevo = 0;

	/**
	 * cifSeleccionado: guarda el cif a actualizar del cliente o de un cliente
	 * nuevo.
	 */
	private String cifNuevo = "";
	/**
	 * nombreNuevo: guarda el nombre a actualizar del cliente o de un cliente nuevo.
	 */
	private String nombreNuevo = "";
	/**
	 * apellido1Nuevo: guarda el primer apellido a actualizar del cliente o de un
	 * cliente nuevo.
	 */
	private String apellido1Nuevo = "";
	/**
	 * apellido2Actual: guarda el segundo apellido a actualizar del cliente o de un
	 * cliente nuevo.
	 */
	private String apellido2Nuevo = "";

	// VARIABLES PARA DIRECCIONES

	// Variables para guardar los datos antiguos o actuales del cliente
	// seleccionado.

	/**
	 * direccionSeleccionado: guarda la dirección antigua o actual de la dirección
	 * seleccionada.
	 */
	private String direccionSeleccionado = "";
	/**
	 * cpSeleccionado: guarda el código postal antiguo o actual de la dirección
	 * seleccionada.
	 */
	private String cpSeleccionado = "";
	/**
	 * provinciaSeleccionado: guarda la provincia antigua o actual de la dirección
	 * seleccionada.
	 */
	private String provinciaSeleccionado = "";
	/**
	 * poblacionSeleccionado: guarda la población antigua o actual de la dirección
	 * seleccionada.
	 */
	private String poblacionSeleccionado = "";
	/**
	 * paisSeleccionado: guarda el país antiguo o actual de la dirección
	 * seleccionada.
	 */
	private String paisSeleccionado = "";

	// Variables para guardar los datos a actualizar de una direccion o de una
	// direccion a crear o a modificar.

	/**
	 * 
	 * Lo utilizamos para almacenar el id de la dirección creada
	 */
	private long idDireccionNuevo = 0L;
	/**
	 * direccionNuevo: guarda la nueva dirección a actualizar de la dirección de un
	 * cliente o de la dirección de un cliente nuevo.
	 */
	private String direccionNuevo = "";
	/**
	 * cpNuevo: guarda el cp a actualizar de la dirección de un cliente o del cp de
	 * la dirección de un cliente nuevo.
	 */
	private String cpNuevo = "";
	/**
	 * cpNuevo: guarda la provincia a actualizar de la dirección de un cliente o de
	 * la provincia de la dirección de un cliente nuevo.
	 */
	private String provinciaNuevo = "";
	/**
	 * cpNuevo: guarda la población a actualizar de la dirección de un cliente o de
	 * la población de la dirección de un cliente nuevo.
	 */
	private String poblacionNuevo = "";
	/**
	 * Guarda la cadena con el país nuevo a crear o modificar de la tabla dirección.
	 */
	private String paisNuevo = "";
	private int idDireccionOperaciones=0;

	/**
	 * Guarda el Nº de registros a mostrar por página.
	 */
	private static int numeroDeRegistrosMostrarPorPaginaNuevo = 20;
	private static String cadenaCancelar = "#q";

	// FIN VARIABLES PARA LA INTRODUCCIÓN DE DATOS
	// ---------------------------------------------------------------------------------//

	// VARIABLES PARA COMPROBACIONES
	// -------------------------------------------------//

	private Boolean todoCorrecto = true;

	// FIN PARA COMPROBACIONES
	// ---------------------------------------------------------------------------------//

	// SERVICIOS
	// ---------------------------------------------------------------------//
	/**
	 * Servicio cliente.
	 */
	private ServiceCliente serviceCliente = new ServiceCliente();
	/**
	 * Servicio dirección.
	 */
	private ServiceDireccion serviceDireccion = new ServiceDireccion();
	/**
	 * Servicio movimiento.
	 */
	private ServiceMovimiento serviceMovimiento = new ServiceMovimiento();

	// FIN SERVICIOS
	// ---------------------------------------------------------------------------------//

	// ESTADÍSTICAS
	// ------------------------------------------------------------------//

	/**
	 * La utilizaremos para saber si hay que actualizar las estadísticas, estará en
	 * true por ejemplo cuando se cree un movimiento, se añada una dirección, etc
	 */
	private Boolean actualizarEstadisticas = false;
	/**
	 * La utilizaremos para guardar las estadísticas, recién las calculamos.
	 */
	private Estadisticas estadisticasTiempoReal = new Estadisticas();
	/**
	 * La utilizamos para guardar las estadísticas que son pasadas desde
	 * estadisticas.
	 */
	private Estadisticas estadisticasOperaciones = new Estadisticas();
	String idiomaSeleccionado = "Español";
	String cadenaIdiomaOpcion = "";
	String cadenaIdiomaInicio = "";

	// FIN ESTADÍSTICAS.
	// ---------------------------------------------------------------------------------//

	// VISTA
	// -------------------------------------------------------------------------//

	/**
	 * Vista para enviar los resultados
	 */
	ViewMain viewMain = new ViewMain();

	// FIN VISTA
	// ---------------------------------------------------------------------------------//

	// OPERACIONES
	// -------------------------------------------------------------------//
	/**
	 * Lo utilizamos para guardar el cliente con el que estamos operando.
	 */
	Cliente clienteOperaciones = new Cliente();
	/**
	 * Lo utilizamos para guardar la dirección con el que estamos operando.
	 */
	Direccion direccionOperaciones = new Direccion();
	/**
	 * Lo utilizamos para guardar el cliente con el que estamos operando, así como
	 * su dirección y su saldo actual
	 */
	ClientesDireccionSaldo clienteDireccionSaldoOperaciones = new ClientesDireccionSaldo();
	/**
	 * Lo utilizamos para guardar la lista de movimientos con el que estamos
	 * operando.
	 */
	List<Movimiento> movimientoOperaciones = new ArrayList<>();
	Locale defaultLocale;
	Locale englishLocale;
	ResourceBundle bundlePorDefecto;
	ResourceBundle bundleIngles;

	// FIN OPERACIONES
	// ---------------------------------------------------------------------------------//

	// -----------------------------------------------------------------------------------------|
	// FIN PROPIEDADES
	// -----------------------------------------------------------------------------------------|
	// -----------------------------------------------------------------------------------------|

	// -----------------------------------------------------------------------------------------|
	// CONSTRUCTORES
	// -----------------------------------------------------------------------------------------|
	// -----------------------------------------------------------------------------------------|

	/**
	 * Constructor sin argumentos
	 */
	public ControllerPrincipal() {
	}

	// ------------------------------------------------------------------------------------------|
	// FIN CONSTRUCTORES
	// ------------------------------------------------------------------------------------------|
	// ------------------------------------------------------------------------------------------|

	// ------------------------------------------------------------------------------------------|
	// MÉTODOS
	// ------------------------------------------------------------------------------------------|
	// ------------------------------------------------------------------------------------------|

	// MAIN
	// --------------------------------------------------------------------------//

	/**
	 * Método que configura las properties de log4j, comprueba y crea la
	 * sessionfactory y muestra el menú inicial si está todo OK.
	 * 
	 * @throws Exception
	 */
	public void main() throws Exception {
		// Configuramos las properties de log4j
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		// Comprobamos la conexion e iniciamos la sessionfactory
		if (HibernateUtil.getSessionFactory() != null) {
			// Hay conexión con la base de datos y mostramos el menú inicial
			// Internacionaliación, sólo el saludo
			internacionalizacion();

			cadenaIdiomaInicio = bundlePorDefecto.getString("mi.inicio");
			cadenaIdiomaOpcion = bundlePorDefecto.getString("mi.opcion");

			viewMain.escribirEnConsola(cadenaIdiomaInicio);
			/**
			 * Sirve para mostrar las estadísticas de la base de datos.
			 */
			estadisticasTiempoReal = serviceCliente.calculaEstadisticas();
			// Guardamos las estadísticas aquí, para no andar calculándolas todo el tiempo y
			// así
			// si no hay cambios no se actualizarán
			// estadisticasOperaciones = estadisticasTiempoReal;-->Esto no está bien poruqe
			// unifica los objetos
			// Con esto lo clonamos. Forma correcta de hacerlo.
			estadisticasOperaciones = estadisticasTiempoReal.clone();
			// Así podríamos clonar sólo partes.
			// estadisticasOperaciones.setContadorInternoClientesEncontrados(estadisticasTiempoReal.getContadorInternoClientesEncontrados());

			// Mostramos el menú inicial de la aplicación.
			mostramosMenuInicial();
			// Si la conexión ha fallado mostramos el mensaje de erroe.
		} else {
			System.out.println("No se ha podido iniciar la aplicación. Contacte al teléfono 987654321 ");
			Sonido.sonar();
		}
		// Inicamos la aplicación y mostramos un menú con las opciones iniciales

	}

	// FIN MAIN
	// ---------------------------------------------------------------------------------//

	private void internacionalizacion() {

		defaultLocale = new Locale("es", "ES");
		bundlePorDefecto = ResourceBundle.getBundle("Bundle", defaultLocale);
		// Locale.setDefault(defaultLocale);

		englishLocale = new Locale("en", "US");
		bundleIngles = ResourceBundle.getBundle("Bundle", englishLocale);
		Locale.setDefault(englishLocale);

	}

	// MENUS
	// -------------------------------------------------------------------------//
	/**
	 * Muestra el menú inicial
	 * 
	 * @throws Exception
	 */
	private void mostramosMenuInicial() throws Exception {
		while (true) {
			// Ya hemos calculado las estadísticas en el main. Al comenzar la aplicación
			// actualizarEstadisticas estará a false, así que aquí no entrará hasta que
			// después de ralizar alguna operación lo cambiemos a true
			if (actualizarEstadisticas) {
				// Calculamos las stadísticas y las guardamos
				estadisticasTiempoReal = serviceCliente.calculaEstadisticas();
				// Pasamos las estadísticas de tiempo real al de operaciones.
				estadisticasOperaciones = estadisticasTiempoReal.clone();
				// Mostramos a que hora se han actualizado las estadísticas
				viewMain.escribirEnConsola("Ha habido cambios: desde la última vez.\nActualizado a: "
						+ Recursos.formato(LocalDateTime.now()));
			}
			// Mostramos las estadísticas
			viewMain.imprimeEstadisticas(estadisticasOperaciones);

			// MENÚ
			// Comienza a imprimirse el menú
			viewMain.escribirEnConsola("----------------------------------------------");
			viewMain.escribirEnConsola("|     PRÁCTICA CUENTAS BANCARIAS HIBERNATE.   |");
			viewMain.escribirEnConsola(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|          ->>> MENÚ PRINCIPAL. <<<-          |    Fecha y Hora: "
					+ Recursos.formato(LocalDateTime.now()));
			viewMain.escribirEnConsola(
					"|               ---------------               |--------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   1. Operaciones con clientes.	      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   2. Listar todos los clientes.	      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   3. Configurar aplicación.		      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   4. Cerrar la aplicación.                  |");
			viewMain.escribirEnConsola("| ---------------------------------------------");
			viewMain.escribirEnConsola("|  ---------------------");
			viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + " |---->");
			viewMain.escribirEnConsola("|  ---------------------");
			// FIN MENÚ

			// Pedimos que seleccione una de las opciones.
			opcionStringSeleccionadaMenu = scanner.nextLine();
			// Eliminamos los espacios, para evitar errores.
			opcionStringSeleccionadaMenu = Recursos.eliminaEspacios(opcionStringSeleccionadaMenu);

			// Comprobamos la opción seleccionada.
			switch (opcionStringSeleccionadaMenu) {
			// 1. Operaciones con clientes.
			case "1":
				mostramosMenuSeleccionarCrearClientes();
				break;
			// 2. Listar todos los clientes.
			case "2":
				String[] resultadoEs = new String[5];
				long[] resultado = new long[2];
				resultado = Recursos.paginacion(serviceCliente.devolverSesion(), "Cliente",
						numeroDeRegistrosMostrarPorPaginaNuevo);
				// resultado[0]--> Número total de registros
				// resultado[1]--> Número total de páginas
				long numeroTotalRegistros = resultado[0];
				long numeroTotalPaginas = resultado[1];
				for (int numeroPagina = 0; numeroPagina <= resultado[1]; numeroPagina++) {
					if (paginacion(numeroTotalPaginas, numeroPagina, numeroTotalRegistros)) {
						listarTodosLosClientes(numeroPagina);
						actualizarEstadisticas = false;
					} else {
						actualizarEstadisticas = false;
						mostramosMenuInicial();
					}
				}
				actualizarEstadisticas = false;
				break;
			// 3. Configurar aplicación.
			case "3":
				mostramosMenuConfiguracion();
				mostrarConfiguracionSonido();
				actualizarEstadisticas = false;
				break;
			// 4. Cerrar la aplicación.
			case "4":
				salirDeLaAplicacion();
				actualizarEstadisticas = false;
				mostramosMenuInicial();
				break;
			default:
				viewMain.escribirEnConsola("Opción no válida. Introduzca sólo números entre 1 y 4.");
				Sonido.sonar();
				actualizarEstadisticas = false;
			}
		}
	}

	/**
	 * Muestra el menú seleccionar y crear clientes
	 * 
	 * @throws Exception
	 */
	private void mostramosMenuSeleccionarCrearClientes() throws Exception {
		while (true) {
			viewMain.escribirEnConsola("----------------------------------------------");
			viewMain.escribirEnConsola("|     PRÁCTICA CUENTAS BANCARIAS HIBERNATE.   |");
			viewMain.escribirEnConsola(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("| ->>> MENÚ SELECCIONAR Y CREAR CLIENTE. <<<- |    Fecha y Hora: "
					+ Recursos.formato(LocalDateTime.now()));
			viewMain.escribirEnConsola(
					"|       ------------------------------        |--------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|---------------------------------------------|");

			viewMain.escribirEnConsola("|   1. Seleccionar cliente.		      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   2. Crear cliente.			      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   3. Volver atrás.			      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   4. Cerrar la aplicación.                  |");
			viewMain.escribirEnConsola("| ---------------------------------------------");
			viewMain.escribirEnConsola("|  ---------------------");
			viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + " |---->");
			viewMain.escribirEnConsola("|  ---------------------");
			opcionStringSeleccionadaMenu = scanner.nextLine();
			opcionStringSeleccionadaMenu = Recursos.eliminaEspacios(opcionStringSeleccionadaMenu);

			switch (opcionStringSeleccionadaMenu) {
			// 1. Seleccionar cliente.
			case "1":
				seleccionarClientePrincipal();
				break;
			// 2. Crear cliente.
			case "2":
				solicitudDatosClienteGeneral();

				// crearCliente();
				break;
			// 3. Volver atrás.
			case "3":
				mostramosMenuInicial();
				break;
			// 4. Cerrar la aplicación.
			case "4":
				salirDeLaAplicacion();
				mostramosMenuSeleccionarCrearClientes();
				break;
			default:
				viewMain.escribirEnConsola("Opción no válida. Introduzca sólo números entre 1 y 4.");
				Sonido.sonar();
			}
		}
	}

	/**
	 * Muestra el menú operaciones con clientes.
	 * 
	 * @throws Exception
	 */
	private void mostrarMenuOperacionesConClientes() throws Exception {
		while (true) {
			viewMain.verClienteDireccionSaldo(
					clienteDireccionSaldoOperaciones=serviceCliente.datosClientesDireccionSaldo(idClienteSeleccionado));
			viewMain.escribirEnConsola("------------------------------------------");
			viewMain.escribirEnConsola("|   PRÁCTICA CUENTAS BANCARIAS HIBERNATE  |");
			viewMain.escribirEnConsola(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("| ->>> MENÚ OPERACIONES CON CLIENTE. <<<- |    Fecha y Hora: "
					+ Recursos.formato(LocalDateTime.now()));

			viewMain.escribirEnConsola(
					"|     -----------------------------       |------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  1. Añadir movimiento.                  |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  2. Mostrar movimientos del cliente.    |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  3. Modificar datos del cliente.        |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  4. Eliminar cliente.                   |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  5. Volver atrás.                       |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  6. Cerrar la aplicación.               |");
			viewMain.escribirEnConsola("|------------------------------------------");
			viewMain.escribirEnConsola("|  ---------------------");
			viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + " |---->");
			viewMain.escribirEnConsola("|  ---------------------");
			opcionStringSeleccionadaMenu = scanner.nextLine();
			opcionStringSeleccionadaMenu = Recursos.eliminaEspacios(opcionStringSeleccionadaMenu);
			switch (opcionStringSeleccionadaMenu) {
			// 1. Mostrar movimientos del cliente.
			case "1":
				datosMovimientoNuevo();
				// System.out.println("Opción no implementada");
				// viewMain.verClienteDireccionSaldo(clienteDireccionSaldoOperaciones);
				// mostrarMenuOperacionesConClientes();
				break;
			// 2. Mostrar movimientos del cliente.
			case "2":
				movimientoOperaciones = serviceMovimiento.list(idClienteSeleccionado);
				viewMain.verMovimientosEncontrados(movimientoOperaciones);
				mostrarMenuOperacionesConClientes();
				break;
			// 3. Modificar datos del cliente.
			case "3":
				//viewMain.verClienteDireccionSaldo(clienteDireccionSaldoOperaciones);
				//System.out.println("clienteDireccionSaldoOperaciones: "+clienteDireccionSaldoOperaciones.getDireccionDireccion());
				mostrarMenuModificarDatosPersonalesDireccionClientes();
				break;
			// 4. Eliminar cliente.
			case "4":
				deleteCliente();
				break;
			// 5. Volver atrás.
			case "5":
				//TODO hacer función de "RESETEO"
				clienteDireccionSaldoOperaciones=null;
				mostramosMenuSeleccionarCrearClientes();
				break;
			// 6. Cerrar la aplicación.
			case "6":
				salirDeLaAplicacion();
				mostrarMenuOperacionesConClientes();
				break;
			default:
				viewMain.escribirEnConsola("Opción no válida. Introduzca sólo números entre 1 y 6.");
				Sonido.sonar();
			}

		}
	}

	private void mostrarMenuModificarDatosPersonalesDireccionClientes() throws Exception {
		while (true) {

			viewMain.verClienteDireccionSaldo(
					clienteDireccionSaldoOperaciones=serviceCliente.datosClientesDireccionSaldo(idClienteSeleccionado));
			viewMain.escribirEnConsola("------------------------------------------");
			viewMain.escribirEnConsola("|   PRÁCTICA CUENTAS BANCARIAS HIBERNATE. |");
			viewMain.escribirEnConsola(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|    ->>> MENÚ MODIFICAR CLIENTE. <<<-    |    Fecha y Hora: "
					+ Recursos.formato(LocalDateTime.now()));
			viewMain.escribirEnConsola(
					"|     -----------------------------       |------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  1. Modificar datos del cliente.        |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  2. Modificar dirección del cliente.    |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  3. Eliminar cliente.                   |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  4. Volver atrás.                       |");
			viewMain.escribirEnConsola("|-----------------------------------------|");
			viewMain.escribirEnConsola("|  5. Salir.                              |");
			viewMain.escribirEnConsola("|------------------------------------------");
			viewMain.escribirEnConsola("|  ---------------------");
			viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + " |---->");
			;
			viewMain.escribirEnConsola("|  ---------------------");
			opcionStringSeleccionadaMenu = scanner.nextLine();
			opcionStringSeleccionadaMenu = Recursos.eliminaEspacios(opcionStringSeleccionadaMenu);

			switch (opcionStringSeleccionadaMenu) {
			// 1. Modificar datos del cliente.
			case "1":
				solicitudDatosClienteModificar();
				break;
			// 2. Modificar dirección.
			case "2":
				solicitudDireccionClienteModificar();
				break;
			// 3. Volver atrás.
			case "3":
				mostrarMenuOperacionesConClientes();
				break;
			// 4. Volver atrás.
			case "4":
				viewMain.verClienteDireccionSaldo(clienteDireccionSaldoOperaciones);
				mostrarMenuOperacionesConClientes();
				break;
			// 5. Cerrar la aplicación.
			case "5":
				salirDeLaAplicacion();
				mostrarMenuModificarDatosPersonalesDireccionClientes();
				break;
			default:
				viewMain.escribirEnConsola("Opción no válida. Introduzca sólo números entre 1 y 4.");
				Sonido.sonar();
			}

		}
	}

	/**
	 * Muestra el menú seleccionar y crear clientes
	 * 
	 * @throws Exception
	 */
	private void mostramosMenuConfiguracion() throws Exception {
		while (true) {
			viewMain.escribirEnConsola("----------------------------------------------");
			viewMain.escribirEnConsola("|     PRÁCTICA CUENTAS BANCARIAS HIBERNATE.   |");
			viewMain.escribirEnConsola(
					"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|         ->>> MENÚ CONFIGURACIÓN <<<-        |    Fecha y Hora: "
					+ Recursos.formato(LocalDateTime.now()));
			viewMain.escribirEnConsola(
					"|         -----------------------------       |-------------------------------------------------------------------------------------------------------------------");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   1. Configurar sonido.                     |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   2. Configurar Nº Reg. a mostrar.	      |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   3. Configurar cadena 'Cancelar'.          |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   4. Configurar idioma.                     |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   5. Volver atrás.                          |");
			viewMain.escribirEnConsola("|---------------------------------------------|");
			viewMain.escribirEnConsola("|   6. Cerrar la aplicación.                  |");
			viewMain.escribirEnConsola("| ---------------------------------------------");
			viewMain.escribirEnConsola("|  ---------------------");
			viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + " |---->");
			viewMain.escribirEnConsola("|  ---------------------");
			opcionStringSeleccionadaMenu = scanner.nextLine();
			opcionStringSeleccionadaMenu = Recursos.eliminaEspacios(opcionStringSeleccionadaMenu);

			switch (opcionStringSeleccionadaMenu) {
			// 1. Seleccionar cliente.
			case "1":
				mostrarConfiguracionSonido();
				break;
			// 2. Crear cliente.
			case "2":
				configurarNumeroRegistrosMostrar();
				// crearCliente();
				break;
			// 3. Volver atrás.
			case "3":
				configurarCadenaCancelar();
				break;
			// 4. Cambiar idioma.
			case "4":
				configurarIdioma();
				break;
			// 5. Volver atrás.
			case "5":
				mostramosMenuInicial();
				break;
			// 6. Cerrar la aplicación.
			case "6":
				salirDeLaAplicacion();
				mostramosMenuSeleccionarCrearClientes();
				break;
			default:
				viewMain.escribirEnConsola("Opción no válida. Introduzca sólo números entre 1 y 4.");
				Sonido.sonar();
			}
		}
	}

	// FIN MENUS
	// -----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------
	// COMPROBACIONES

	/**
	 * Compueba si el usuario a pulsado la cadena #q, con lo cual pasa a true. Este
	 * valor lo puede cambiar el usuario en el menú de configuración.
	 * 
	 * @param cadena
	 * @return
	 */
	private Boolean verSiCancelado(String cadena, String nombreOperacion) {
		if (cadena.toLowerCase().equals(cadenaCancelar)) {
			Boolean pulsadoCanceladoInterno = false;
			do {
				viewMain.escribirEnConsola("¿Está seguro que quiere salir de:\nNombre de la operación: "
						+ nombreOperacion + "\nSi<s> No<n>");
				cadena = scanner.nextLine().toLowerCase();
				if (cadena.equals(cadenaCancelar)) {
					viewMain.escribirEnConsola("Operación cancelar no está disponible en esta operación");
					pulsadoCanceladoInterno = false;
				} else if (cadena.equals("s")) {
					viewMain.escribirEnConsola("Operación: " + nombreOperacion + " CANCELADA");
					return true;
				} else if (cadena.equals("n")) {
					return false;
				} else {
					viewMain.escribirEnConsola("Opción no válida.\nRecuerde: Si<s> No<n>.");
					Sonido.sonar();
					pulsadoCanceladoInterno = false;
				}
			} while (!pulsadoCanceladoInterno);

		} else {
			return false;
		}
		return false;
	}

	// FIN COMPROBACIONES.
	// -------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------
	// OPCIONES.
	// ------------------------------------------------------------------
	// OPCION LISTAR TODOS LOS CLIENTES CON PAGINACIÓN.
	// --------------------------------------------------------------
	private void listarTodosLosClientes(long numeroPagina) throws Exception, Exception {
		viewMain.verListadoClientesDireccionSaldo(
				serviceCliente.listClientesDireccionSaldo(numeroPagina, numeroDeRegistrosMostrarPorPaginaNuevo),
				numeroPagina);

	}

	// ----DELETE----OPCION BORRAR CLIENTE
	// --------------------------------------------------------------
	private void deleteCliente() throws Exception {
		if (serviceCliente.delete(idClienteSeleccionado)) {
			viewMain.escribirEnConsola("Se ha eliminado al cliente correctamente");
		} else {
			viewMain.escribirEnConsola("No se ha podido eliminar al cliente correctamente");
			Sonido.sonar();
		}
		//mostrarMenuOperacionesConClientes();

	}

	// FIN----DELETE----OPCION BORRAR CLIENTE
	// --------------------------------------------------------------
	// FIN OPCION LISTAR TODOS LOS CLIENTES.
	// --------------------------------------------------------------
	// OPCION SALIR.
	// --------------------------------------------------------------
	private void salirDeLaAplicacion() throws SQLException {
		if (mostrarOpcionSalir()) {
			System.exit(0);
		} else {
			viewMain.escribirEnConsola("Operación: Cerrar aplicación - CANCELADA POR EL USUARIO./n");
		}
	}

	private Boolean mostrarOpcionSalir() {
		Boolean pulsadoInterno = false;
		do {
			viewMain.escribirEnConsola(
					"¿Está seguro que quiere cerrar la aplicación?\nSi<s> No<n> Cancelar<" + cadenaCancelar + ">.");
			cadenaDatos = scanner.nextLine().toLowerCase();
			if (!verSiCancelado(cadenaDatos, "Cerrar la aplicación.")) {
				if (cadenaDatos.equals("s")) {
					viewMain.escribirEnConsola("Has salido de la aplicación (-__-)");
					System.exit(0);
				} else if (cadenaDatos.equals("n")) {
					pulsadoInterno = true;
				} else {
					if (!cadenaDatos.equals(cadenaCancelar)) {
						viewMain.escribirEnConsola(
								"Opción no válida. Recuerde: Si<s> No<n> Cancelar<" + cadenaCancelar + ">.");
						Sonido.sonar();
						pulsadoInterno = false;
					}
				}
			} else {
				return false;
			}
		} while (!pulsadoInterno);
		return false;
	}

	/**
	 * Devolverá true si es la primera página, y el resto dependerá si el usuario
	 * presiona s o n
	 * 
	 * @param numeroPagina
	 * @return
	 * @throws Exception
	 */

	// FIN OPCION SALIR.
	// --------------------------------------------------------------

	// OPCIONES DE CONFIGURACION.
	/**
	 * Sirve para activar o desactivar el sonido.
	 * 
	 * @throws Exception
	 */
	private void mostrarConfiguracionSonido() throws Exception {
		/**
		 * La usamos para saber si se seleccionado alguna opción.
		 */
		Boolean seleccionadoActivadoOdesactivado = false;
		do {
			cadenaDatos = "";
			if (Sonido.getSonar()) {
				viewMain.escribirEnConsola("El sonido está activado");
				viewMain.escribirEnConsola(
						"¿Desea desactivar el sonido?\nSi<s> No<n> Cancelar<" + cadenaCancelar + ">.");
				cadenaDatos = scanner.nextLine();
				if (verSiCancelado(cadenaDatos, "Configurar sonido.")) {
					mostramosMenuInicial();
				} else {
					if (cadenaDatos.toLowerCase().equals("s")) {
						Sonido.setSonar(false);
						viewMain.escribirEnConsola("El sonido ha sido desactivado.");
						seleccionadoActivadoOdesactivado = true;
						break;
					}
					if (cadenaDatos.toLowerCase().equals("n")) {
						viewMain.escribirEnConsola("Fin de la configuración. El estado del sonido es activado.");
						mostramosMenuInicial();
						seleccionadoActivadoOdesactivado = true;
						break;
					} else if (!cadenaDatos.toLowerCase().equals(cadenaCancelar)) {
						Sonido.sonar();
						viewMain.escribirEnConsola(
								"Opción no válida. Recuerde: Si<s> No<n> Cancelar<" + cadenaCancelar + ">.");
					}
				}

			} else {
				viewMain.escribirEnConsola("El sonido está desactivado");
				viewMain.escribirEnConsola("¿Desea activar el sonido?\nSi<s> No<n> Cancelar<" + cadenaCancelar + ">.");
				cadenaDatos = scanner.nextLine();
				if (verSiCancelado(cadenaDatos, "Configurar sonido")) {
					mostramosMenuInicial();
				} else {
					if (cadenaDatos.toLowerCase().equals("s")) {
						Sonido.setSonar(true);
						viewMain.escribirEnConsola("El sonido se ha activado correctamente.");
						seleccionadoActivadoOdesactivado = true;
						break;
					}
					if (cadenaDatos.toLowerCase().equals("n")) {
						Sonido.setSonar(false);
						viewMain.escribirEnConsola("Fin de la configuración. El estado del sonido es desactivado.");
						mostramosMenuInicial();
						seleccionadoActivadoOdesactivado = true;
						break;
					} else if (!cadenaDatos.toLowerCase().equals(cadenaCancelar)) {
						Sonido.sonar();
						viewMain.escribirEnConsola(
								"Opción no válida. Recuerde: Si<s> No<n> Cancelar<" + cadenaCancelar + ">.");
					}
				}

			}
		} while (seleccionadoActivadoOdesactivado == false);
	}

	private void configurarNumeroRegistrosMostrar() throws Exception {
		Boolean noCorrecto = true;
		viewMain.escribirEnConsola("Nº Reg. por página actualmente está configurado a: "
				+ numeroDeRegistrosMostrarPorPaginaNuevo + " registros por página.");
		while (noCorrecto) {
			if (noCorrecto && solicitarDatosCorrectosOno("Nº Reg. por página", "Configurar Nº Reg. por página", 1, 100,
					true, false, "El Nº Reg. por página debe ser inferior a 100")) {
				viewMain.escribirEnConsola("Actualizado a: " + numeroDeRegistrosMostrarPorPaginaNuevo);
				noCorrecto = false;
			} else {
				noCorrecto = true;
			}
		}
		mostramosMenuConfiguracion();

	}

	private void configurarCadenaCancelar() throws Exception {
		Boolean noCorrecto = true;
		viewMain.escribirEnConsola(
				"Cadena cancelar actualmente está configurado a: " + cadenaCancelar + " Cadena para'Cancelar'.");
		while (noCorrecto) {
			if (noCorrecto && solicitarDatosCorrectosOno("Cadena para'Cancelar", "Configurar cadena para'Cancelar", 1,
					2, false, false, "Máximo 2 dígitos. Ej: '#?'.Cancelar<" + cadenaCancelar + ">.")) {
				viewMain.escribirEnConsola("Actualizado a: " + cadenaCancelar);
				noCorrecto = false;
			} else {
				noCorrecto = true;
			}
		}
		mostramosMenuConfiguracion();

	}

	private void configurarIdioma() throws Exception {
		Boolean noCorrecto = true;
		viewMain.escribirEnConsola("Idioma actualmente está configurado a: " + idiomaSeleccionado + ".");
		while (noCorrecto) {
			if (noCorrecto && solicitarDatosCorrectosOno("Idioma", "Configurar Idioma", 1, 1, true, false,
					"Espanol<1> Inglés<2>")) {
				viewMain.escribirEnConsola("Actualizado a: " + idiomaSeleccionado);
				noCorrecto = false;
			} else {
				noCorrecto = true;
			}
		}
		mostramosMenuConfiguracion();

	}

	// FIN OPCION CONFIGURACION.
	// --------------------------------------------------------------
	// OPCIÓN SELECCIONAR CLIENTE
	private void seleccionarClientePrincipal() throws Exception {
		/**
		 * Sirve para almacenar los clientes que coinciden con la cadena de texto pasada
		 * en seleccionarCliente().
		 */
		Cliente clienteEncontrado = new Cliente();
		/**
		 * Sirve para almacenar los clientes que coinciden con la cadena de texto pasada
		 * en seleccionarClienteOclientes().
		 */
		List<Cliente> listaClientesEncontrados = new ArrayList();
		/**
		 * Sirve para manejar los bucles, cuando esten a true saldrán
		 */
		Boolean encontrado = false;
		try {
			do {
				// Pido los clientes que coinciden con la cadena que le paso en
				// seleccionarClienteOclientes()
				// y los almaceno en listaClientesEncontrados;
				listaClientesEncontrados = seleccionarClienteOclientes();

				// Si no devuelve null, que significaría que el usuario ha cancelado la
				// operación y que ya no
				// quiere buscar más y saldría al menu
				// En este caso sería dististo de null si no hemos cancelado.
				if (listaClientesEncontrados != null) {
					// Si se ha encontrado algún cliente.
					//Si ha encontrado uno que no impima nada, ya lo impmimos en los menús.
					if (listaClientesEncontrados.size() != 1) {
						if (listaClientesEncontrados.size() != 0) {
							// Enviamos a imprimir la lista.
							viewMain.verClientesEncontrados(listaClientesEncontrados);
							// Salimos de este dowhile.
							encontrado = true;
							// Si no encuentra ningún cliente coincidente con la cadena de texto pasada.
						} else {
	
							// Mostramos la lista, donde nos dirá que no hay coincidencias.
							viewMain.verClientesEncontrados(listaClientesEncontrados);
							// Continuamos con el bucle.
							encontrado = false;
						}
					} else {
						encontrado=true;
					}
					// En el caso de que el usuario haya cancelado, devolverá null, así que
					// pondremos encontrado
					// a true para que salga de este bucle y no entrará en el siguiente porque para
					// ello
					// listaClientesEncontrados no debería ser null, con lo cual ya mostrariamos el
					// menú.
				} else {
					encontrado = true;
				}
			} while (!encontrado);
			// Si hemos llegado aquí es porque ha habido coincidencias con la cadena dada y
			// se ha
			// devuelto un listado de clientes o bien se ha cancelado
			// listaClientesEncontrados es null y
			// no entrará en este do while.
			// Así que ahora vamos a pedir que introduzca el Id buscado
			if (listaClientesEncontrados != null) {
				encontrado = false;
				do {

					if (!cadenaDatos.equals(cadenaCancelar) && !cadenaDatos.equals("s")) {
						clienteEncontrado = null;
						/**
						 * Sirve para almacenar el cliente que coincide con la cadena de texto pasada en
						 * seleccionarCliente().
						 */
						if (listaClientesEncontrados.size() == 1) {
							for (Cliente cliente : listaClientesEncontrados) {

								clienteOperaciones.setId(cliente.getId());
								clienteOperaciones.setCif(cliente.getCif());
								clienteOperaciones.setNombre(cliente.getNombre());
								clienteOperaciones.setApellido1(cliente.getApellido1());
								clienteOperaciones.setApellido2(cliente.getApellido2());
								clienteOperaciones.setDireccion(cliente.getDireccion());
								clienteOperaciones.setMovimientos(cliente.getMovimientos());
								idClienteSeleccionado=clienteOperaciones.getId();
								try {
									direccionOperaciones=clienteOperaciones.getDireccion();
									idDireccionOperaciones=clienteOperaciones.getDireccion().getId();
								} catch (Exception e) {
									direccionOperaciones=null;
									idDireccionOperaciones=-1;
								}

								// Salimos del dowhile.
					
								mostrarMenuOperacionesConClientes();
							}

						}
						clienteEncontrado = seleccionarCliente();
						// Si se ha encontrado algún cliente.
						if (clienteEncontrado != null) {
							clienteOperaciones = clienteEncontrado;
							clienteOperaciones.setId(clienteEncontrado.getId());
							clienteOperaciones.setCif(clienteEncontrado.getCif());
							clienteOperaciones.setNombre(clienteEncontrado.getNombre());
							clienteOperaciones.setApellido1(clienteEncontrado.getApellido1());
							clienteOperaciones.setApellido2(clienteEncontrado.getApellido2());
							clienteOperaciones.setDireccion(clienteEncontrado.getDireccion());
							clienteOperaciones.setMovimientos(clienteEncontrado.getMovimientos());
							idClienteSeleccionado=clienteOperaciones.getId();
							try {
								direccionOperaciones=clienteOperaciones.getDireccion();
								idDireccionOperaciones=clienteOperaciones.getDireccion().getId();
							} catch (Exception e) {
								direccionOperaciones=null;
								idDireccionOperaciones=-1;
							}
							// Salimos del dowhile.
							mostrarMenuOperacionesConClientes();
							// Si no encuentra ningún cliente coincidente con la cadena de texto pasada.
						} else {
							// Mostramos no hay coincidencias.
							viewMain.escribirEnConsola("No hay coincidencias");
							// Continuamos con el bucle.
							encontrado = false;
						}
					} else {
						encontrado = true;
					}
				} while (!encontrado);
			} else {
				mostramosMenuSeleccionarCrearClientes();
			}

		} catch (Exception e) {
		}

	}

	/**
	 * Pide la cadena a buscar y devuelve la lista de clientes encontrados,
	 * devolverá null si el usuario ha cancelado la operación
	 * 
	 * @return
	 */

	private List<Cliente> seleccionarClienteOclientes() {
		List listaClientes = new ArrayList();
		todoCorrecto = true;
		do {
			viewMain.escribirEnConsola("Introduzca el texto a buscar\n");
			viewMain.escribirEnConsola("Cancelar<" + cadenaCancelar + ">");
			cadenaDatos = scanner.nextLine().toLowerCase();
			// Miramos a ver si el cliente a introducido #q para cancelar la operación.
			// y si continua con la operación cancelar o la cancela
			if (!verSiCancelado(cadenaDatos, "Buscar cliente")) {
				// Si no fue cancelado finalmente y cadenaDatos es diferente a #q
				if (!cadenaDatos.equals(cadenaCancelar)) {
					listaClientes = serviceCliente.encuentraTexto(cadenaDatos);
					return listaClientes;
					// Si primero canceló y luego canceló cancelar, devolvemos true para que vuelva
					// a pedir la dirección.
				} else {
					todoCorrecto = true;
				}
				// Si ha cancelado devolvemos false para salir del do while
				// cadenaDatos debe valer #q.
			} else {
				return null;
			}
		} while (todoCorrecto);
		return listaClientes;

	}

	private Cliente seleccionarCliente() throws NumberFormatException, SQLException, Exception {

		do {
			// Pedimos el Id a buscar
			viewMain.escribirEnConsola("INTRODUCIR ID CLIENTE");
			viewMain.escribirEnConsola("Introduzca Id del cliente:\nCancelar<" + cadenaCancelar + ">.");
			cadenaDatos = scanner.nextLine().toLowerCase();
			// Miramos a ver si el cliente a introducido #q para cancelar la operación.
			if (!verSiCancelado(cadenaDatos, "Introducir Id del cliente.")) {
				// Si no ha cancelado la operación, obtenemos el cliente coincidente
				// con la cadena pasada.
				// Máximo 99.999 clientes.
				if (cadenaDatos.equals(cadenaCancelar)) {
					cadenaDatos = "";
					return null;
				}
				try {
					Cliente cliente = serviceCliente.find(Integer.parseInt(cadenaDatos));
					// Devolvemos el cliente
					return cliente;

				} catch (Exception e) {
					return null;
				}
				// Si ha cancelado devolvemos null
			} else {
				return null;
			}
		} while (true);
	}

	// FIN OPCIONES.
	// PAGINACION
	/**
	 * 
	 * @param numeroTotalPaginas
	 * @param numeroPagina
	 * @param numeroTotalRegistros
	 * @return
	 * @throws Exception
	 */
	private boolean paginacion(long numeroTotalPaginas, long numeroPagina, long numeroTotalRegistros) throws Exception {
		Boolean pulsadoInterno = false;
		// En la primera página devolveremos true, para que no muestre el diálogo de
		// mostrar más registros
		if (numeroPagina != 0) {
			do {
				if (numeroTotalPaginas != numeroPagina) {
					viewMain.escribirEnConsola("¿Mostrar Página: Nº: " + (numeroPagina + 1L) + " de "
							+ (numeroTotalPaginas + 1L) + " páginas?\nTotal registros: " + numeroTotalRegistros
							+ "\nSi.<s> No.<n> <" + cadenaCancelar + ">Cancelar.");
					cadenaDatos = scanner.nextLine().toLowerCase();
				}
				if (!verSiCancelado(cadenaDatos, "Mostrar siguientes 20 clientes.")) {
					// Si presiona salgo del do while y paginacion() devolverá true
					if (cadenaDatos.equals("s")) {
						pulsadoInterno = true;
						// Si presiona n directamente vamos al menú principal, no necesitamos devolver
						// nada.
					} else if (cadenaDatos.equals("n")) {
						mostramosMenuInicial();
						// Si presiona #q paginación continuará dentro del bucle y entrará en el
						// verSiCancelado()
					} else {
						if (!cadenaDatos.equals(cadenaCancelar)) {
							viewMain.escribirEnConsola(
									"Opción no válida. Recuerde: Si<s> No<n> Cancelar<" + cadenaCancelar + ">.");
							Sonido.sonar();
							pulsadoInterno = false;
						}
					}
				} else {
					return false;
				}
			} while (!pulsadoInterno);
			if (pulsadoInterno) {
				return true;

			} else {
				return false;
			}
		}
		viewMain.escribirEnConsola("Número Total de registros: " + numeroTotalRegistros);
		viewMain.escribirEnConsola("Página Nº: " + (numeroPagina + 1L) + " de " + (numeroTotalPaginas + 1));
		// Devuelve true si es la primera página, para que no salga el diálogo de
		// mostrar más registros
		return true;
	}

	// -----------------------------------------------------------------------------------
	// EXTRAER DATOS CLIENTE SELECCIONADO
	private void extraerDatosClienteSeleccionado() {
		cifSeleccionado = clienteDireccionSaldoOperaciones.getClienteCif();
		nombreSeleccionado = clienteDireccionSaldoOperaciones.getClienteNombre();
		apellido1Seleccionado = clienteDireccionSaldoOperaciones.getClienteApellido1();
		apellido2Seleccionado = clienteDireccionSaldoOperaciones.getClienteApellido2();
	}

	// IMPRIMIR DATOS DE CLIENTE SELECCIONADO

	private void imprimirDatosClienteSeleccionado() {
		viewMain.muestraDatosClienteSeleccionado(cifSeleccionado, nombreSeleccionado, apellido1Seleccionado,
				apellido2Seleccionado);

	}

	private void imprimirDatosDireccionSeleccionado() {
		viewMain.muestraDatosDireccionSeleccionado(direccionSeleccionado, cpSeleccionado, provinciaSeleccionado,
				poblacionSeleccionado, paisSeleccionado);

	}

	private void imprimirDatosDireccionNuevo() {
		viewMain.muestraDatosDireccionSeleccionado(direccionNuevo, cpNuevo, provinciaNuevo, poblacionNuevo, paisNuevo);

	}

	// SOLICITAR DATOS DE CLIENTE(Todos menos id y dirección), DIRECCIÓN(Todos menos
	// id) Y MOVIMIENTO(Todos menos id, fecha y saldo)
	/**
	 * 
	 * @return true si está todo correcto y no ha sido cancelado por el usuario.
	 */
	private boolean solicitarDatosCorrectosOno(String nombre, String cadenaVerSiCancelado, int longitudMinima,
			int longitudMaxima, Boolean numerico, Boolean importe, String mensajeFormato) {
		/**
		 * La utilizamos para saber si debería volver a pedir el dato
		 */
		// Lo ponemos a false para que entre en el do while.
		Boolean seleccionado = false;
		// Comenzamos el bucle y no saldrá de este hasta que seleccionado sea true;
		do {
			viewMain.escribirEnConsola("<<< Formato: " + mensajeFormato + " >>>");
			viewMain.escribirEnConsola(nombre + ":\n");
			cadenaDatos = scanner.nextLine().toLowerCase();
			// Miramos a ver si el cliente ha introducido #q para cancelar la operación.
			if (!verSiCancelado(cadenaDatos, cadenaVerSiCancelado)) {
				if (cadenaDatos.equals(cadenaCancelar)) {
					cadenaDatos = "";
				}
				if (!cadenaDatos.equals("")) {
					// Si no hay errores en los datos.
					if (Recursos.noHayErroresEnDatos(cadenaDatos, longitudMinima, longitudMaxima, numerico, importe,
							mensajeFormato)) {
						// Recogemos los datos por orden de preferencia desde los menos utilizados
						// a los más utilizados.
						if (nombre.equals("Importe")) {
							try {
								/**
								 * La utilizamos para introducir el importe de los movimientos.
								 */
								BigDecimal importeNuevo = new BigDecimal(cadenaDatos);

							} catch (Exception e) {
								// No debería llegar hasta aquí si la función comprobarImporte() está bien.
								return false;
							}
						} else if (nombre.equals("CIF")) {
							if (serviceCliente.compruebaCif(cadenaDatos)) {
								cifNuevo = cadenaDatos;
							} else {
								viewMain.escribirEnConsola("Cif no válido. No puede haber cifs duplicados.");
								solicitarDatosCorrectosOno(nombre, cadenaVerSiCancelado, longitudMinima, longitudMaxima,
										numerico, importe, mensajeFormato);
							}
						} else if (nombre.equals("Nombre")) {
							nombreNuevo = cadenaDatos;
						} else if (nombre.equals("Primer apellido")) {
							apellido1Nuevo = cadenaDatos;
						} else if (nombre.equals("Segundo apellido")) {
							apellido2Nuevo = cadenaDatos;
						} else if (nombre.equals("Dirección")) {
							direccionNuevo = cadenaDatos;
						} else if (nombre.equals("C.P.")) {
							cpNuevo = cadenaDatos;
						} else if (nombre.equals("Provincia")) {
							provinciaNuevo = cadenaDatos;
						} else if (nombre.equals("Población")) {
							poblacionNuevo = cadenaDatos;
						} else if (nombre.equals("País")) {
							paisNuevo = cadenaDatos;
						} else if (nombre.equals("Nº Reg. por página")) {
							numeroDeRegistrosMostrarPorPaginaNuevo = Integer.parseInt(cadenaDatos);
							viewMain.escribirEnConsola(
									"numeroDeRegistrosMostrarPorPaginaNuevo:" + numeroDeRegistrosMostrarPorPaginaNuevo);
						} else if (nombre.equals("Cadena para'Cancelar")) {
							cadenaCancelar = cadenaDatos;
							viewMain.escribirEnConsola("Cadena para'Cancelar: " + cadenaCancelar);
						} else if (nombre.equals("Idioma")) {
							if (cadenaDatos.equals("1")) {
								idiomaSeleccionado = "Espanol";
								cadenaIdiomaInicio = bundlePorDefecto.getString("mi.inicio");
								cadenaIdiomaOpcion = bundlePorDefecto.getString("mi.opcion");
							} else if (cadenaDatos.equals("2")) {
								idiomaSeleccionado = "Ingles";
								cadenaIdiomaInicio = bundleIngles.getString("mi.inicio");
								cadenaIdiomaOpcion = bundleIngles.getString("mi.opcion");
							}
							viewMain.escribirEnConsola("Idioma: " + idiomaSeleccionado);
						}
						// La función termina aquí y devuelve true. Datos correctos.
						return true;
						// Si no
					} else {
						viewMain.escribirEnConsola("     >>>ERROR<<<");
						viewMain.escribirEnConsola(">>>Datos no válidos<<<");
						viewMain.escribirEnConsola("RECUERDE: " + mensajeFormato + "\n");
						// Sonido de error.
						Sonido.sonar();
						// Ponemos seleccionado a false, para que vuelva a pedir los datos.
						seleccionado = false;
					}
				}
				// Si ha cancelado devolvemos false.
			} else {
				return false;
			}
			// Si hemos llegado hasta aquí es por que o bien los datos introducidos no son
			// los correctos y los volverá a pedir
			seleccionado = false;
		} while (!seleccionado);
		// Si hubiera algo no controlado devolvería false
		return false;
	}

	// SOLICITAR DATOS CLIENTE
	private void solicitudDatosClienteGeneral() {
		todoCorrecto = true;
		while (todoCorrecto) {
			if (todoCorrecto && solicitarDatosCorrectosOno("CIF", "Introducir CIF", 9, 9, false, false,
					"El CIF debe contener: 9 dígitos")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("Nombre", "Introducir nombre", 1, 30, false, false,
					"Longitud mínima: 1. Longitud máxima: 30")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("Primer apellido", "Introducir primer apellido", 1, 30,
					false, false, "Longitud mínima: 1. Longitud máxima: 30")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("Segundo apellido", "Introducir segundo apellido", 1, 30,
					false, false, "Longitud mínima: 1. Longitud máxima: 30") && todoCorrecto) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto) {
				idClienteNuevo = serviceCliente.create(cifNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo);
				if (idClienteNuevo != -1) {
					viewMain.escribirEnConsola("\n\tCliente creado correctamente");
					viewMain.muestraDatosClienteNuevo(cifNuevo, nombreNuevo, apellido1Nuevo, apellido2Nuevo);
					ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere añadir una dirección?",
							"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Introducir dirección.");
					// Para que salga
					todoCorrecto = false;
				} else {
					viewMain.escribirEnConsola("\n\n\tCLIENTE NO CREADO porque devolvió -1");
				}
			} else {
				Sonido.sonar();
				viewMain.escribirEnConsola(
						"\n\n\tCLIENTE NO CREADO porque no está todo correcto o el usuario ha cancelado la operación.");
			}
		}

	}

	private void solicitudDatosDirecciónGeneral(long idClienteNuevo) {
		todoCorrecto = true;
		while (todoCorrecto) {
			if (todoCorrecto && solicitarDatosCorrectosOno("Dirección", "Introducir Dirección", 1, 30, false, false,
					"Longitud mínima: 1. Longitud máxima: 30")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("C.P.", "Introducir C.P.", 5, 5, false, false,
					"El C.P. debe contener: 5 dígitos y deben ser numéricos")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("Provincia", "Introducir provincia", 1, 30, false, false,
					"Longitud mínima: 1. Longitud máxima: 30")) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("Población", "Introducir población", 1, 30, false, false,
					"Longitud mínima: 1. Longitud máxima: 30") && todoCorrecto) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}
			if (todoCorrecto && solicitarDatosCorrectosOno("País", "Introducir país", 1, 30, false, false,
					"Longitud mínima: 1. Longitud máxima: 30") && todoCorrecto) {
				todoCorrecto = true;
			} else {
				todoCorrecto = false;
			}

			if (todoCorrecto) {
				direccionOperaciones = serviceDireccion.create(direccionNuevo, cpNuevo, provinciaNuevo, poblacionNuevo,
						paisNuevo);
				idDireccionNuevo = direccionOperaciones.getId();
				if (direccionOperaciones != null) {
					viewMain.escribirEnConsola("\n\tDirección creada correctamente");
					viewMain.muestraDatosDireccionNuevo(direccionNuevo, cpNuevo, provinciaNuevo, poblacionNuevo,
							paisNuevo);
					// Para que salga
					todoCorrecto = false;
				} else {
					viewMain.escribirEnConsola("\n\n\tDIRECCIÓN NO CREADA");
				}

			} else {
				Sonido.sonar();
				viewMain.escribirEnConsola("\n\n\tDIRECCIÓN NO CREADA");
			}
		}

	}

	private void solicitudDatosClienteModificar() {

		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el CIF?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar CIF.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el Nombre?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Nombre.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el Primer apellido?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Primer apellido.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el Segundo apellido?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Segundo apellido.");

	}
	private void solicitudDireccionClienteModificar() {

		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar la Dirección?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Dirección.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el C.P.?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar C.P.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar la Provincia?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Provincia.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar la Población?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar Población.");
		ejecutarOrdenIntroducciónDatosSiNoCancelar("¿Quiere modificar el País?",
				"<<< Formato: Sí<s> No<n> Cancelar<" + cadenaCancelar + "> >>>", "Modificar País.");

	}

	// MOVIMIENTOS
	private void datosMovimientoNuevo() throws SQLException, Exception {
		/**
		 * Almacena el saldo actual del cliente seleccionado
		 */
		BigDecimal saldoClienteSeleccionado = BigDecimal.ZERO;
		saldoClienteSeleccionado =clienteDireccionSaldoOperaciones.getSaldo();
		/**
		 * Controla que sean correctos los datos introducidos, la inicializamos a true
		 * para recorra el do while
		 */
		Boolean errorDatosIntroducidos = true;
		/**
		 * Lo usamos para pasar los datos a Big Decimal y hacer las operaciones
		 * correspondientes.
		 */
		BigDecimal datosBigDecimal = BigDecimal.ZERO;
		viewMain.escribirEnConsola("Nuevo movimiento:");
		Boolean retirar = false;
		do {
			// Comprobamos que el saldo es mayor de 0.00 €
			if (saldoClienteSeleccionado.compareTo(datosBigDecimal) == 1) {
				retirar = true;
				do {
					viewMain.escribirEnConsola("¿Ingresar <i> o Retirar<r>. Cancelar<" + cadenaCancelar + ">");
					cadenaDatos = scanner.nextLine().toLowerCase();
					if (verSiCancelado(cadenaDatos, "Hacer un ingreso")) {
						cadenaDatos = "###";
						mostrarMenuOperacionesConClientes();
					} else {
						if (cadenaDatos.equals(cadenaCancelar)) {
							cadenaDatos = "%#%";
							errorDatosIntroducidos = false;
						} else {
							if (cadenaDatos.equals("i")) {
								errorDatosIntroducidos = true;
							} else if (cadenaDatos.equals("r")) {
								errorDatosIntroducidos = true;
							} else {
								viewMain.escribirEnConsola("Datos no válidos");
								errorDatosIntroducidos = false;
							}
						}
					}
				} while (errorDatosIntroducidos == false);
				// Si el saldo es 0.00 €, sólo podrá ingresar
			} else {
				retirar = false;
				do {
					viewMain.escribirEnConsola(
							"Su saldo es de 0.00€. Sólo podrá hacer ingresos. ¿Desea hacer un ingreso? <s/n>. \n<<< Formato. Cancelar<"
								+ cadenaCancelar + "> Sí<s>. No<n>. Cancelar<+cadenaCancelar+>  >>>");
					cadenaDatos = scanner.nextLine().toLowerCase();
					if (verSiCancelado(cadenaDatos, "Hacer un ingreso")) {
						cadenaDatos = "###";
						mostrarMenuOperacionesConClientes();
					} else {
						if (cadenaDatos.equals(cadenaCancelar)) {
							cadenaDatos = "%#%";
							errorDatosIntroducidos = false;
						} else {
							if (cadenaDatos.toLowerCase().equals("n")) {
								viewMain.escribirEnConsola("Operación cancelada por el cliente");
								mostrarMenuOperacionesConClientes();
							} else if (cadenaDatos.toLowerCase().equals("s")) {
								cadenaDatos = "i";
								errorDatosIntroducidos = true;
							} else {
								viewMain.escribirEnConsola("Datos no válidos");
								errorDatosIntroducidos = false;
							}
						}
					}
				} while (errorDatosIntroducidos == false);

			}
			if (cadenaDatos.toLowerCase().equals("i")) {
				do {
					do {
						viewMain.escribirEnConsola("Introduzca la cantidad a ingresar.  \n<<< Formato. Cancelar<"
								+ cadenaCancelar + ">  >>>");
						cadenaDatos = scanner.nextLine().toLowerCase();
						if (verSiCancelado(cadenaDatos, "Hacer un ingreso")) {
							cadenaDatos = "###";
							errorDatosIntroducidos = true;
							mostrarMenuOperacionesConClientes();
						} else {
							if (cadenaDatos.toLowerCase().equals(cadenaCancelar)) {
								cadenaDatos = "%#%";
								errorDatosIntroducidos = false;
							} else {
								if (Recursos.compruebaCantidadIntroducida(cadenaDatos)) {
									errorDatosIntroducidos = true;
								} else {
									errorDatosIntroducidos = false;
								}
							}
						}
					} while (errorDatosIntroducidos == false);
					BigDecimal importeParaIngresar = new BigDecimal(cadenaDatos);
					int estado = serviceMovimiento.create(saldoClienteSeleccionado, importeParaIngresar,
							clienteOperaciones, 1);
					if (estado == 1) {
						viewMain.escribirEnConsola("Movimiento agregado correctamente");
						errorDatosIntroducidos = false;
					} else {
						viewMain.escribirEnConsola(
								"Movimiento no agregado.\nMotivo: Ha habido un error en la base de datos");
						errorDatosIntroducidos = true;
					}

				} while (errorDatosIntroducidos == true);

			} else if (cadenaDatos.toLowerCase().equals("r") && retirar == true) {
				do {
					do {
						viewMain.escribirEnConsola("Introduzca la cantidad a retirar.  \n<<< Formato. Cancelar<"
								+ cadenaCancelar + ">  >>>");
						cadenaDatos = scanner.nextLine();
						if (verSiCancelado(cadenaDatos, "Retirar importe")) {
							cadenaDatos = "###";
							errorDatosIntroducidos = true;
							mostrarMenuOperacionesConClientes();
						} else {
							if (cadenaDatos.equals(cadenaCancelar)) {
								cadenaDatos = "%#%";
								errorDatosIntroducidos = false;
							} else {
								if (Recursos.compruebaCantidadIntroducida(cadenaDatos)) {
									errorDatosIntroducidos = true;
								} else {
									errorDatosIntroducidos = false;
								}
							}
						}

					} while (errorDatosIntroducidos == false);

					BigDecimal importeParaRetirar = new BigDecimal(cadenaDatos);
					int estado = serviceMovimiento.create(saldoClienteSeleccionado, importeParaRetirar,
							clienteOperaciones, 2);
					if (estado == 1) {
						viewMain.escribirEnConsola("Movimiento agregado correctamente");
						errorDatosIntroducidos = false;
					} else if (estado == 2) {
						viewMain.escribirEnConsola("Movimiento no agregado.\nMotivo: Su saldo actual es de: "
								+ saldoClienteSeleccionado + " y usted está intentando retirar: " + importeParaRetirar);
						errorDatosIntroducidos = true;
					} else {
						viewMain.escribirEnConsola(
								"Movimiento no agregado.\nMotivo: Ha habido un error en la base de datos");
						errorDatosIntroducidos = true;
					}

				} while (errorDatosIntroducidos == true);
			} else {
				if (cadenaDatos.equals("###")) {
					errorDatosIntroducidos = false;
				} else {
					if (!cadenaDatos.equals("%#%")) {
						System.out.println("cadenaDatos vale:" + cadenaDatos);
						viewMain.escribirEnConsola("Datos no válidos.");
						errorDatosIntroducidos = true;
					}
				}
			}
		} while (errorDatosIntroducidos == true);
		mostrarMenuOperacionesConClientes();
	}

	private void ejecutarOrdenIntroducciónDatosSiNoCancelar(String orden, String mensajeFormato,
			String mensajeCancelado) {
		todoCorrecto = true;
		do {
			viewMain.escribirEnConsola(orden + "\n");
			viewMain.escribirEnConsola(mensajeFormato);
			cadenaDatos = scanner.nextLine().toLowerCase();
			// Miramos a ver si el cliente a introducido #q para cancelar la operación.
			// y si continua con la operación cancelar o la cancela
			if (!verSiCancelado(cadenaDatos, mensajeCancelado)) {
				if (cadenaDatos.equals(cadenaCancelar)) {
					cadenaDatos = "";
				}
				// Si no fue cancelado finalmente y cadenaDatos es diferente a #q
				if (cadenaDatos.equals("s")) {
					if (orden.equals("¿Quiere añadir una dirección?")) {
						solicitudDatosDirecciónGeneral(idClienteNuevo);
						serviceCliente.updateClienteSinDireccion(idClienteNuevo, direccionOperaciones);
						todoCorrecto = false;
					} else if (orden.equals("¿Quiere modificar el CIF?")) {
						viewMain.escribirEnConsola("CIF actual:" + clienteOperaciones.getCif());
						solicitarDatosCorrectosOno("CIF", "Introducir CIF", 9, 9, false, false,
								"El CIF debe contener: 9 dígitos");
						clienteOperaciones.setCif(cadenaDatos);
						serviceCliente.updateClienteDatos(idClienteSeleccionado, clienteOperaciones);
						viewMain.escribirEnConsola("CIF actualizado correctamente");
						todoCorrecto = false;
					} else if (orden.equals("¿Quiere modificar el Nombre?")) {
						viewMain.escribirEnConsola("Nombre actual:" + clienteOperaciones.getNombre());
						solicitarDatosCorrectosOno("Nombre", "Introducir Nombre", 1, 30, false, false,
								"Nombre: Longitud mínima: 1. Longitud máxima: 30");
						clienteOperaciones.setNombre(cadenaDatos);
						serviceCliente.updateClienteDatos(idClienteSeleccionado, clienteOperaciones);
						viewMain.escribirEnConsola("Nombre actualizado correctamente");
						todoCorrecto = false;
					} else if (orden.equals("¿Quiere modificar el Primer apellido?")) {
						viewMain.escribirEnConsola("Primer apellido actual:" + clienteOperaciones.getApellido1());
						solicitarDatosCorrectosOno("Primer apellido", "Introducir Primer apellido", 1, 30, false, false,
								"Primer apellido: Longitud mínima: 1. Longitud máxima: 30");
						clienteOperaciones.setApellido1(cadenaDatos);
						serviceCliente.updateClienteDatos(idClienteSeleccionado, clienteOperaciones);
						viewMain.escribirEnConsola("Primer apellido actualizado correctamente");
						todoCorrecto = false;
					} else if (orden.equals("¿Quiere modificar el Segundo apellido?")) {
						viewMain.escribirEnConsola("Segundo apellido actual:" + clienteOperaciones.getApellido2());
						solicitarDatosCorrectosOno("Segundo apellido", "Introducir Primer apellido", 1, 30, false,
								false, "Segundo apellido: Longitud mínima: 1. Longitud máxima: 30");
						clienteOperaciones.setApellido2(cadenaDatos);
						serviceCliente.updateClienteDatos(idClienteSeleccionado, clienteOperaciones);
						viewMain.escribirEnConsola("Segundo apellido actualizado correctamente");
						todoCorrecto = false;
					}else if (orden.equals("¿Quiere modificar la Dirección?")) {
						viewMain.escribirEnConsola("Dirección actual:" + clienteDireccionSaldoOperaciones.getDireccionDireccion());
						solicitarDatosCorrectosOno("Dirección", "Introducir Dirección", 1, 30, false,
								false, "Dirección: Longitud mínima: 1. Longitud máxima: 30");
						direccionOperaciones.setDireccion(cadenaDatos);
						serviceCliente.updateClienteDireccion(direccionOperaciones.getId(), direccionOperaciones,"direccion");
						viewMain.escribirEnConsola("Dirección actualizada correctamente");
						todoCorrecto = false;
					}else if (orden.equals("¿Quiere modificar el C.P.?")) {
						viewMain.escribirEnConsola("C.P actual:" + clienteDireccionSaldoOperaciones.getDireccionCp());
						solicitarDatosCorrectosOno("C.P", "Introducir C.P:", 5, 5, true,
								false, "C.P: Longitud mínima: 5. Longitud máxima: 5");
						direccionOperaciones.setCp(cadenaDatos);
						serviceCliente.updateClienteDireccion(direccionOperaciones.getId(), direccionOperaciones,"cp");
						viewMain.escribirEnConsola("C.P actualizado correctamente");
						todoCorrecto = false;
					}else if (orden.equals("¿Quiere modificar la Provincia?")) {
						viewMain.escribirEnConsola("Provincia actual:" + clienteDireccionSaldoOperaciones.getDireccionProvincia());
						solicitarDatosCorrectosOno("Provincia", "Introducir Provincia:", 1, 30, false,
								false, "Provincia: Longitud mínima: 1. Longitud máxima: 30");
						direccionOperaciones.setProvincia(cadenaDatos);
						serviceCliente.updateClienteDireccion(direccionOperaciones.getId(), direccionOperaciones,"provincia");
						viewMain.escribirEnConsola("Provincia actualizada correctamente");
						todoCorrecto = false;
					}else if (orden.equals("¿Quiere modificar la Población?")) {
						viewMain.escribirEnConsola("Población actual:" + clienteDireccionSaldoOperaciones.getDireccionPoblacion());
						solicitarDatosCorrectosOno("Población", "Introducir Población:", 1, 30, false,
								false, "Población: Longitud mínima: 1. Longitud máxima: 30");
						direccionOperaciones.setPoblacion(cadenaDatos);
						serviceCliente.updateClienteDireccion(direccionOperaciones.getId(), direccionOperaciones,"poblacion");
						viewMain.escribirEnConsola("Población actualizada correctamente");
						todoCorrecto = false;
					}else if (orden.equals("¿Quiere modificar el País?")) {
						viewMain.escribirEnConsola("País actual:" + clienteDireccionSaldoOperaciones.getDireccionPais());
						solicitarDatosCorrectosOno("País", "Introducir País:", 1, 30, false,
								false, "País: Longitud mínima: 1. Longitud máxima: 30");
						direccionOperaciones.setPais(cadenaDatos);
						serviceCliente.updateClienteDireccion(direccionOperaciones.getId(), direccionOperaciones,"pais");
						viewMain.escribirEnConsola("País actualizado correctamente");
						todoCorrecto = false;
					}
					// Si dice que no, salimos del do while
				} else if (cadenaDatos.equals("n")) {
					todoCorrecto = false;
				}
				// Si introduce otra cosa vuelve a preguntar
				else {
					viewMain.escribirEnConsola("     >>>ERROR<<<");
					viewMain.escribirEnConsola(">>>Datos no válidos<<<");
					viewMain.escribirEnConsola("RECUERDE: " + mensajeFormato + "\n");
					todoCorrecto = true;
				}
				// Si ha cancelado devolvemos false para salir del do while
				// cadenaDatos debe valer #q.
			} else {
				todoCorrecto = false;
			}
		} while (todoCorrecto);
	}

}