package resources;

import java.time.LocalDateTime;

import view.ViewMain;

public class ConstruyeMenuInicial {
	enum MenuInicial {
		OPERACIONES(1, "Operaciones con clientes."), LISTAR(2, "Listar todos los clientes."), CONFIGURAR(3,
				"Configurar aplicación."), CERRAR(4, "Cerrar la aplicación."), TITULO(5,
						"PRÁCTICA CUENTAS BANCARIAS HIBERNATE."), SUBTITULO(6, "->>> MENÚ PRINCIPAL. <<<-");
		private int posicion; // posicion
		private String texto;
		// Añadir un Constructor

		MenuInicial(int p, String t) {
			posicion = p;
			texto = t;
		}

		// Añadir un método
		String getTexto() {
			return texto;
		}

		int getPosicion() {
			return posicion;
		}
	}

	public static void main(String[] args) {
		String cadenaIdiomaOpcion = "Elija una opción";
		// Uso de un constructor, una variable de instancia y un método.
		ViewMain viewMain = new ViewMain();
		viewMain.escribirEnConsola("----------------------------------------------");
		viewMain.escribirEnConsola("|     " + MenuInicial.TITULO.getTexto() + "   |");
		viewMain.escribirEnConsola(
				"-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		viewMain.escribirEnConsola("|          " + MenuInicial.SUBTITULO.getTexto() + "          |    Fecha y Hora: "
				+ Recursos.formato(LocalDateTime.now()));
		viewMain.escribirEnConsola(
				"|               ---------------               |--------------------------------------------------------------------------------------------------------------------------------------");
		viewMain.escribirEnConsola("|---------------------------------------------|");
		for (MenuInicial cadena : MenuInicial.values()) {
			if (cadena.getPosicion() < 5) {
				viewMain.escribirEnConsola("|     " + cadena.posicion + ". " + cadena.texto);
				viewMain.escribirEnConsola("----------------------------------------------|");
			}
		}
		viewMain.escribirEnConsola("|  ---------------------");
		viewMain.escribirEnConsola("|  | " + cadenaIdiomaOpcion + "  |---->");
		viewMain.escribirEnConsola("|  ---------------------");

	}

}
