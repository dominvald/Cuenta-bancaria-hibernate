package resources;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.log4j.Logger;

//import view.ViewMain;
/**
 * Clase que reproduce un sonido
 * 
 * @author PracticasSoftware5
 *
 */
public final class Sonido {
	/**
	 * errorSqlSW : lo utilizamos para poder imprimir los errores SQL, que nos da el
	 * printStackTrace Ej: e.printStackTrace(new PrintWriter(errorSqlSW));
	 */
	private static StringWriter errorRutaSW = new StringWriter();
	/**
	 * log: logger para la clase
	 */
	private final static Logger LOG = Logger.getLogger("Sonido.class");
	/**
	 * Sirve para saber si está habilitado o deshabilitado el sonido
	 */
	public static Boolean sonar = true;

	/**
	 * Constructor privado que no recibe argumentos
	 */
	private Sonido() {
	}

	/**
	 * Método estático que pone la música en funcionamiento si el atributo estático
	 * sonar está a true
	 */
	public static void sonar() {
		if (sonar == true) {
			try {
				// Creamos el clip de sonido
				Clip sonido = AudioSystem.getClip();
				// Creamos la ruta
				File archivo = new File("src/main/resources/sounds/sounds.wav");
				// Abrimos el clip de sonido
				sonido.open(AudioSystem.getAudioInputStream(archivo));
				// Ponemos el clip de sonido
				sonido.start();
				// Dejamos que se reproduzca medio segundo
				Thread.sleep(500);
				// Destruimos el clip de sonido
				sonido.close();
			} catch (Exception e) {
				// Decimos que ha habido un error
				// Guardamos el printStackTrace en errorRutaSW
				e.printStackTrace(new PrintWriter(errorRutaSW));
				// Imprimimos el error en el log
				LOG.fatal("Error ruta: Clase Sonido, en método sonar()\n\t" + e.getMessage() + "\n\t"
						+ errorRutaSW.toString());
			}
		}
	}

	/**
	 * Devuelve el estado de sonar
	 * 
	 * @return
	 */
	public static Boolean getSonar() {
		return sonar;
	}

	/**
	 * Sirve para cambiar el estado de la propiedad sonar
	 * 
	 * @param sonar
	 */
	public static void setSonar(Boolean sonar) {
		Sonido.sonar = sonar;
	}
}
