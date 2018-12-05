package resources;

/**
 * Clase que calcula o valida un documento de identificación del reino de
 * España. (DNI,NIE,CIF).
 * 
 * @author Agárimo
 */
public final class CalculaNif {

	private final static String letrasNif = "TRWAGMYFPDXBNJZSQVHLCKE";
	private final String letrasCif = "ABCDEFGHJKLMNPQRSUVW";
	private final String letrasNie = "XYZ";
	private final static String digitoControlCif = "JABCDEFGHI";
	// private final String cifNumero = "ABEH";
	private final static String cifLetra = "KPQRSNW";

	public CalculaNif() {
	}

	/**
	 * Calcula el dígito o letra de control de un documento de identificación del
	 * reino de España
	 *
	 * @param nif
	 *            documento a calcular
	 * @return devuelve el documento con el dígito o letra de control calculado.
	 */
	public String calcular(String nif) {
		nif = nif.toUpperCase();
		String a = nif.substring(0, 1);

		if (letrasCif.contains(a)) {
			return calculaCif(nif);
		} else if (letrasNie.contains(a)) {
			return calculaNie(nif);
		} else {
			return calculaDni(nif);
		}
	}

	/**
	 * Valida un documento de identificación del reino de España
	 *
	 * @param nif
	 *            documento a validar
	 * @return true si es válido, false si no lo es.
	 */
	public boolean isvalido(String nif) {
		nif = nif.toUpperCase();
		String a = nif.substring(0, 1);

		if (letrasCif.contains(a)) {
			return isCifValido(nif);
		} else if (letrasNie.contains(a)) {
			return isNieValido(nif);
		} else {
			return isDniValido(nif);
		}
	}

	private static String calculaDni(String dni) {
		String str = completaCeros(dni, 8);

		if (str.length() == 9) {
			str = str.substring(0, dni.length() - 1);
		}
		return str + calculaLetra(str);
	}

	private static String calculaNie(String nie) {
		String str = null;

		if (nie.length() == 9) {
			nie = nie.substring(0, nie.length() - 1);
		}

		if (nie.startsWith("X")) {
			str = nie.replace('X', '0');
		} else if (nie.startsWith("Y")) {
			str = nie.replace('Y', '1');
		} else if (nie.startsWith("Z")) {
			str = nie.replace('Z', '2');
		}

		return nie + calculaLetra(str);
	}

	private static String calculaCif(String cif) {
		return cif + calculaDigitoControl(cif);
	}

	private static int posicionImpar(String str) {
		int aux = Integer.parseInt(str);
		aux = aux * 2;
		aux = (aux / 10) + (aux % 10);

		return aux;
	}

	public static boolean isDniValido(String dni) {
		if (dni.length() == 9) {
			String aux = dni.substring(0, 8);
			aux = calculaDni(aux);
			return dni.equals(aux);
		} else {
			return false;
		}

	}

	public static boolean isNieValido(String nie) {
		String aux = nie.substring(0, 8);
		aux = calculaNie(aux);

		return nie.equals(aux);
	}

	public static boolean isCifValido(String cif) {
		String aux = cif.substring(0, 8);
		aux = calculaCif(aux);

		return cif.equals(aux);
	}

	private static char calculaLetra(String aux) {
		return letrasNif.charAt(Integer.parseInt(aux) % 23);
	}

	private static String calculaDigitoControl(String cif) {
		String str = cif.substring(1, 8);
		String cabecera = cif.substring(0, 1);
		int sumaPar = 0;
		int sumaImpar = 0;
		int sumaTotal;

		for (int i = 1; i < str.length(); i += 2) {
			int aux = Integer.parseInt("" + str.charAt(i));
			sumaPar += aux;
		}

		for (int i = 0; i < str.length(); i += 2) {
			sumaImpar += posicionImpar("" + str.charAt(i));
		}

		sumaTotal = sumaPar + sumaImpar;
		sumaTotal = 10 - (sumaTotal % 10);

		if (sumaTotal == 10) {
			sumaTotal = 0;
		}

		if (cifLetra.contains(cabecera)) {
			str = "" + digitoControlCif.charAt(sumaTotal);
		} else {
			str = "" + sumaTotal;
		}

		return str;
	}

	private static String completaCeros(String str, int num) {
		while (str.length() < num) {
			str = "0" + str;
		}
		return str;
	}
}