package resources;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import excepciones.ExcepcionDemasiadosDecimales;
import excepciones.ExcepcionIntervalo;
import view.ViewMain;

/**
 * Clase estática con los recursos
 * 
 * @author PracticasSoftware5
 *
 */
public final class Recursos {
	/**
	 * Log4j.
	 */
	private final static Logger LOG = Logger.getLogger("file_data");
	/**
	 * Vista
	 */
	public static ViewMain viewMain = new ViewMain();

	/**
	 * Constructor privado para impedir la creación de instancias de la clase.
	 */
	// CONSTRUCTORES
	// Constructor vacío
	private Recursos() {

	}

	/**
	 * Sirva para convertir un BigDecimal a un String con un formato que deseemos
	 * 
	 * @param numeroBigDecimalAConvertir
	 * @return
	 */
	// MÉTODOS
	// Método para dar formato
	/**
	 * Devuelve una cadena formateada sin los espacios iniciales y quita tb si hay
	 * varios espacios seguidos.
	 * 
	 * @param cadenaDatos
	 * @return
	 */
	public static String eliminaEspacios(String cadenaDatos) {
		return cadenaDatos.trim().replaceAll("(\\s)+", "$1");
	}

	/**
	 * Se le pasa un Big Decimal y devuelve un String formateado así: "#,#00.00"
	 * 
	 * @param numeroBigDecimalAConvertir
	 * @return
	 */
	public static String bigDecimalToString(BigDecimal numeroBigDecimalAConvertir) {

		double datoDoubleD = 0;
		// se verifica que sean correctos los argumentos recibidos
		if (numeroBigDecimalAConvertir != null) {
			// Lo pasamos a double para poder darle formato
			datoDoubleD = numeroBigDecimalAConvertir.doubleValue();
		}
		/**
		 * Creamos nuestro formato Los # indican valores no obligatorios. Los 0 indican
		 * que si no hay valor se pondrá un cero
		 */
		NumberFormat formatter = new DecimalFormat("#,#00.00");
		// Devolvemos un String con el BigDecimal ya formateado y listo para enviar
		// posteriormente a la vista.
		return formatter.format(datoDoubleD);
	}

	public static String formato(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return formatter.format(fecha);
	}

	public static String truncate(String value, int length) {
		if (value.length() > length) {
			return value.substring(0, length) + "...";
		} else {
			return value;
		}
	}

	public static String generaBarras(int numeroBarras) {
		String resultadoBarras = "";
		for (int i = 0; i < numeroBarras; i++) {
			resultadoBarras += "-";
		}
		return resultadoBarras;
	}

	public static String generaEspaciosEnBlanco(int numeroEspaciosEnBlanco) {
		String resultadoBarras = "";
		for (int i = 0; i < numeroEspaciosEnBlanco; i++) {
			resultadoBarras += " ";
		}
		return resultadoBarras;
	}

	// Métodos para comprobaciones numéricas y aritméticas.
	/**
	 * Comprueba que sólo se haya introducido 2 decimales
	 * 
	 * @param numeroAcomprobar
	 * @throws ExcepcionDemasiadosDecimales
	 */
	public static void comprobarSiMasDeDosDecimales(String numeroAcomprobar) throws ExcepcionDemasiadosDecimales {
		/**
		 * Lo utilizamos para almacenar la posición del punto decimaal
		 */
		int intIndex = numeroAcomprobar.indexOf(".");
		// Si no existe el punto decimal
		if (intIndex == -1) {
			// No hacer nada
		}
		// Si no
		else {
			// Almacenamos la longitud del número introducido
			/**
			 * Almacena la longitud del numero introducido
			 */
			int longitudNumeroAcomprobar = numeroAcomprobar.length();
			// Nos quedamos con la parte decimal, de la cual queremos saber su longitud
			/**
			 * Guarda la parte de los decimales del numeroAcomprobar
			 */
			String parteDecimal = numeroAcomprobar.substring(intIndex + 1, longitudNumeroAcomprobar);
			// Si la longitud de la parte decimal es superior a 2, significará que el
			// usuario ha introducido
			// demasiados decimales con lo cual devolveremos un error del tipo
			// ExcepcionDemasiadosDecimales.
			if (parteDecimal.length() > 2) {
				throw new ExcepcionDemasiadosDecimales("Sólo se admiten dos decimales");
			}

		}
	}

	// COMPROBACIONES
	// Método para contar registros y paginación
	/**
	 * Sirve para consultar el número de registros y las páginas que necesitaremos
	 * para mostrar el resultado de la consulta.
	 * 
	 * @param sessFact
	 * @param entityName
	 * @param numeroRegistrosPorPagina
	 * @return
	 */
	public static boolean numeroDeClientesEsCero(SessionFactory sessFact) {
		Session session = sessFact.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(1) from Cliente");
		long numTotalObjetos = (long) query.getSingleResult();
		session.close();
		if (numTotalObjetos > 0) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Comprueba si la cantidad introducida es correcta. Si devuelve true,significa
	 * que los datos son correctos
	 * 
	 * @param datos
	 * @return
	 */
	public static Boolean compruebaCantidadIntroducida(String datos) {
		/**
		 * Lo utilizamos para almacenar los datos provisonalmente, pues al hacer la
		 * conversión desde un String, debemos almacenar el resultado.
		 */
		Double datosCorrectos = 0.0;
		try {
			datosCorrectos = Double.parseDouble(datos);
			Recursos.comprobarSiMasDeDosDecimales(datos);
			Recursos.rango(datosCorrectos);
			return true;
		} catch (NumberFormatException e) {
			viewMain.escribirEnConsola("Ha introducido:" + datos + "\nNo ha introducido números");
			LOG.error("No ha introducido números");
			return false;
		} catch (ExcepcionDemasiadosDecimales e) {
			viewMain.escribirEnConsola("Ha introducido:" + datos + "\n" + e.getMessage());
			LOG.error(e.getMessage());
			return false;
		} catch (ExcepcionIntervalo e) {
			viewMain.escribirEnConsola(e.getMessage());
			LOG.error("Ha introducido:" + datos + "\n" + e.getMessage());
			return false;
		}

	}

	/**
	 * Comprueba que los valores introducidos están dentro del rango desde 0.01€
	 * hasta 99.999€
	 * 
	 * @param numeroAcomprobar
	 * @throws ExcepcionIntervalo
	 */
	public static void rango(double numeroAcomprobar) throws ExcepcionIntervalo {
		// Si el número a comprobar es menor que 0.01 o mayor a 99999 se lanzará
		// una excepción de tipo ExcepciónIntervalo.
		if ((numeroAcomprobar < 0.01) || (numeroAcomprobar >= 99999)) {
			throw new ExcepcionIntervalo(
					"Números fuera de rango. Introduzca cantidades mayores que 0.01€ y menores que 99,999.00€");
		}
	}

	/**
	 * Comprueba que los valores introducidos están dentro del rango desde 1 hasta
	 * 100
	 * 
	 * @param numeroAcomprobar
	 * @throws ExcepcionIntervalo
	 */
	public static boolean rangoCadenaCancelar(String numeroAcomprobar) throws ExcepcionIntervalo {
		int numeroOperaciones = 0;
		try {
			numeroOperaciones = Integer.parseInt(numeroAcomprobar);
		} catch (Exception e) {
			return false;
		}
		if ((numeroOperaciones < 1) || (numeroOperaciones > 100)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Quita todos espacios, tabuladores y retornos de carro tanto del principio
	 * como del final y asu vez si hay varios espacios en blanco, lo deja en uno.
	 * Después comprueba que la longitud sea la correcta.
	 * 
	 * @param cadena
	 * @param longitudMinima
	 * @param longitudMaxima
	 * @return
	 * @throws ExcepcionIntervalo
	 */
	public static Boolean noHayErroresEnDatos(String cadena, int longitudMinima, int longitudMaxima, Boolean numerico,
			Boolean importe, String mensajeFormato, boolean comprobarCif, boolean rango) throws ExcepcionIntervalo {
		@SuppressWarnings("unused")
		BigDecimal bigDecimal = BigDecimal.ZERO;
		if (comprobarCif) {
			if (CalculaCif.comprobarDni(cadena)) {
				return true;
			} else {
				return false;
			}
		}
		if (rango) {
			if (rangoCadenaCancelar(cadena)) {
				return true;
			} else {
				return false;
			}
		}
		// Comprobamos que cumple con la longitud mínima es mayor o igual que la que se
		// le pasa
		// que la longitud máxima es menor o igual que longitudMaxima que se nos pasa
		// por parámetro.
		if (cadena.length() >= longitudMinima && cadena.length() <= longitudMaxima) {
			if (numerico) {
				try {
					bigDecimal = new BigDecimal(cadena);
					return true;
				} catch (Exception e) {
					return false;
				}
			}

		} else {
			// No cumple, devuelve false
			return false;
		}
		return true;

	}

	// PAGINACION
	// Método para contar registros de clientes y paginación
	/**
	 * Sirve para consultar el número de registros y las páginas que necesitaremos
	 * para mostrar el resultado de la consulta.
	 * 
	 * @param sessFact
	 * @param entityName
	 * @param numeroRegistrosPorPagina
	 * @return
	 */
	public static long[] paginacion(SessionFactory sessFact, long numeroRegistrosPorPagina) {
		long resultado[] = new long[2];
		long numTotalObjetos = 0;
		long numPaginas = 0;
		Session session = sessFact.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(1) from Cliente");
		numTotalObjetos = (long) query.getSingleResult();
		session.close();
		numPaginas = (long) Math.ceil(numTotalObjetos / numeroRegistrosPorPagina);
		resultado[0] = numTotalObjetos;
		resultado[1] = numPaginas;
		return resultado;
	}

	// Método para contar registros de movimientos y paginación
	/**
	 * Sirve para consultar el número de registros y las páginas que necesitaremos
	 * para mostrar el resultado de la consulta.
	 * 
	 * @param sessFact
	 * @param entityName
	 * @param numeroRegistrosPorPagina
	 * @return
	 */
	public static long[] paginacionMovimientos(SessionFactory sessFact, String clienteId,
			long numeroRegistrosPorPagina) {
		long resultado[] = new long[2];
		long numTotalObjetos = 0;
		long numPaginas = 0;
		Session session = sessFact.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(1) from  Movimiento mov where cliente_id=:clienteId");
		query.setParameter("clienteId", clienteId);
		numTotalObjetos = (long) query.getSingleResult();
		session.close();
		numPaginas = (long) Math.ceil(numTotalObjetos / numeroRegistrosPorPagina);
		resultado[0] = numTotalObjetos;
		resultado[1] = numPaginas;
		return resultado;
	}
}