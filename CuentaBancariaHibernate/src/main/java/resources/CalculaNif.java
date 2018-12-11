package resources;

/**
 * Clase que calcula o valida un documento de identificación del reino de España. (DNI,NIE,CIF).
 * @author Alberto Domínguez
 */
public class CalculaNif {

    private static final String LETRAS_NIF = "TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String lETRAS_NIE = "XYZ";
    private static final String LETRAS_CIF = "ABCDEFGHJKLMNPQRSUVW";
    private static final String DIGITO_CONTROL_CIF = "JABCDEFGHI";
    private static final String CIF_LETRA = "KPQRSNW";

    
    public CalculaNif() {
    }

    /**
     * Calcula el dígito o letra de control de un documento de identificación
     * del reino de España
     *
     * @param nif documento a calcular
     * @return devuelve el documento con el dígito o letra de control calculado.
     */
    public static String calcular(String nif) {
        nif = nif.toUpperCase();
        String a = nif.substring(0, 1);
        if (LETRAS_CIF.contains(a)) {
            return calculaCif(nif);
        } else if (lETRAS_NIE.contains(a)) {
            return calculaNie(nif);
        } else {
            return calculaDni(nif);
        }
    }

    /**
     * Valida un documento de identificación del reino de España
     *
     * @param nif documento a validar
     * @return true si es válido, false si no lo es.
     */
    public static boolean isValido(String nif) {
    	String nifOperaciones="";
        nifOperaciones = nif.toUpperCase();
    	if(nifOperaciones.length()==8) {
    		nifOperaciones = "0"+nifOperaciones;
    	}
        String a = nifOperaciones.substring(0, 1);

        if (LETRAS_CIF.contains(a)) {
            return isCifValido(nifOperaciones);
        } else if (lETRAS_NIE.contains(a)) {
            return isNieValido(nifOperaciones);
        } else {
            return isDniValido(nifOperaciones);
        }
    }

    private static String calculaDni(String dni) {

        return dni + calculaLetra(dni);
    }

    private static String calculaNie(String nie) {
        String str = null;
        
        if(nie.length()==9){
            nie=nie.substring(0, nie.length()-1);
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

    private static boolean isDniValido(String dni) {
        String aux = dni.substring(0, 8);
        aux = calculaDni(aux);

        return dni.equals(aux);
    }

    private static boolean isNieValido(String nie) {
        String aux = nie.substring(0, 8);
        aux = calculaNie(aux);

        return nie.equals(aux);
    }

    private static boolean isCifValido(String cif) {
        String aux = cif.substring(0, 8);
        aux = calculaCif(aux);

        return cif.equals(aux);
    }

    private static char calculaLetra(String aux) {
        return LETRAS_NIF.charAt(Integer.parseInt(aux) % 23);
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
        
        if(sumaTotal==10){
            sumaTotal=0;
        }

        if (CIF_LETRA.contains(cabecera)) {
            str = "" + DIGITO_CONTROL_CIF.charAt(sumaTotal);
        } else {
            str = "" + sumaTotal;
        }

        return str;
    }

}
