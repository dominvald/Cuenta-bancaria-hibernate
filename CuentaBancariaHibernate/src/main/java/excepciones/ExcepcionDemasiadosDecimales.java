package excepciones;

public class ExcepcionDemasiadosDecimales extends Exception {
	/**
	 * Esta excepción es lanzada cuando se introducen más de dos decimales.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que recibe un parámetro y se lo pasa al super, es decir, a la
	 * clase padre, que en este caso es Excepción, la clase de la que heredan todas
	 * las excepciones
	 * 
	 * @param mensaje
	 */
	public ExcepcionDemasiadosDecimales(String mensaje) {
		super(mensaje);
	}
}
