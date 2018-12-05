package demo;

import java.sql.SQLException;

import controller.ControllerPrincipal;

public class Demo {
	/**
	 * @param args
	 * @throws Exception
	 * @throws SQLException
	 */
	public static void main(String... args) throws SQLException, Exception {
		/**
		 * Creamos el controlador principal, para invocar sus métodos
		 */
		ControllerPrincipal controllerPrincipal = new ControllerPrincipal();
		// Pone en funcionamiento la aplicación con su método main().
		controllerPrincipal.main();
	}

}
