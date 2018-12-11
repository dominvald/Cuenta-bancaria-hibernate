package resources;

import view.ViewMain;

/**
 * Esta clase sirve para comprobar si el CIF es correcto.
 * 
 * @author Alberto Domínguez
 *
 */
public class CalculaDni {
	/**
	 * Método estático que devuelve true si está todo correcto y si es incorrecto
	 * devolverá false y un mensaje con el problema detectado
	 * 
	 * @param dni
	 * @return
	 */
	public static boolean comprobarDni(String dni) {
		/**
		 * Sirve para saber si la letra del CIF es una letra realmente.
		 */
		boolean comprobarLetra;
		/**
		 * Sirve para almacenar si el número del Cif es correcto.
		 */
		boolean comprobarNumero;
		/**
		 * Sirve para almacenar la letra introducida.
		 */
		String letra = "";
		/**
		 * Sirve para contar el número de números introducidos correspondientes a la
		 * parte numérica del CIF.
		 */
		int contadorNumeros = 0;
		/**
		 * Sirve para almacenar la parte numérica del CIF introducido.
		 */
		String cadNumeros = "";
		/**
		 * Sirve para guardar el número del Cif.
		 */
		int numeroCif;
		/**
		 * Sirve para guardar el resultado y así obtener la la letra correspondiente.
		 */
		int restoResultado;
		/**
		 * Sirve para devolver si el Cif introducido es válido o no
		 */
		boolean valido = false;
		/**
		 * Sirve para enviar a la vista los resultados del error, si es que se produce.
		 */
		ViewMain viewMain = new ViewMain();
		/**
		 * Este array sirve para guardar las 23 letras posibles para calcular el Cif.
		 */
		//Ejemplos: T->0, M->5
		char[] arrayLetrasParaCif = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S',
				'Q', 'V', 'H', 'L', 'C', 'K', 'E' };

		// Eliminamos los posibles espacios en blanco que haya introducido el usuario.
		dni = Recursos.eliminaEspacios(dni);
		// Comprobamos que la longitud del Cif introducido esté entre 9 y 8 caracteres.
		if (dni.length() == 8 || dni.length() == 9) {
			// Si la longitud es de 8 caracteres.
			if (dni.length() == 8) {
				// Le añadimos un 0 al principio.
				dni = ("0" + dni);
				// Avisamos que se lo hemos añadido
				viewMain.escribirEnConsola(
						"Se ha formateado a: " + dni.toUpperCase() + ". Utilice este formato para sus busquedas.");
			}
			// Si la longitud no es de 9 ó 8 caracteres
		} else {
			// Informamos del error.
			viewMain.escribirEnConsola(
					"La longitud del CIF debe ser de 9 ó 8 caracteres y usted ha introducido: " + dni.length());
			// Devolvemos false indicando que el Cif es no válido.
			return false;
		}
		// Comprobamos que el último dígito sea una letra
		comprobarLetra = Character.isLetter(dni.charAt(8));
		// Si no es una letra
		if (comprobarLetra == false) {
			// Informamos del error y devolverá válido con el valor de false;
			viewMain.escribirEnConsola(
					"El último carácter debe ser una letra y usted ha introducido: " + dni.charAt(8) + " caracteres.");
			// Si es una letra
		} else {
			// La pasamos a mayúsculas y la extraemos para trabajar con ella.
			letra = dni.toUpperCase().substring(8);
			// También extremos la parte numérica del Cif
			cadNumeros = dni.substring(0, 8);
			// y recorremos todas las posiciones de la parte numérica para saber si son
			// todos dígitos.
			for (int i = 0; i <= 7; i++) {
				comprobarNumero = Character.isDigit(dni.charAt(i));
				// Si es un dígito
				if (comprobarNumero == true) {
					// Incrementamos el contador de números.
					contadorNumeros++;
					// Si no es un dígito
				} else {
					// Enviamos a la vista el error y devolveremos valido con valor de false;
					viewMain.escribirEnConsola("El carácter " + (i + 1) + " no es un número.");
				}
			}
			// Si finalmente se consiguieron los 8 dígitos numéricos correctamente
			if (contadorNumeros == 8) {
				// Pasamos la parte numérica del Cif a Integer.
				numeroCif = Integer.valueOf(cadNumeros);
				// Hayamos el resto para obtner la posición de la letra que le correspondería
				restoResultado = numeroCif % 23;
				// Si se corresponde
				if (arrayLetrasParaCif[restoResultado] == letra.charAt(0)) {
					// Devolverá el valor de válido que se true y será un Cif correcto.
					valido = true;
					// Si no devolvemos el mensaje de error a la vista y se devolverá valido con
					// valor de false
					// indicando que Cif es no correcto.
				} else {
					viewMain.escribirEnConsola("La letra correspondiente al número " + numeroCif + " es "
							+ arrayLetrasParaCif[restoResultado] + ".");
				}
			}
		}
		return valido;
	}
}