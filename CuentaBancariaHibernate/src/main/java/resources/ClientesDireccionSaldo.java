package resources;

import java.math.BigDecimal;

public class ClientesDireccionSaldo {
	// PROPIEDADES
	/**
	 * Sirva para guardar el número de registro encontrado para así poder imprimirlo
	 * en las listas
	 */
	private int numeroRegistro = 0;
	/**
	 * Sirva para guardar el identificador único de cada cliente.
	 */
	private int clienteId = 0;
	/**
	 * Sirva para guardar el CIF de cada cliente
	 */
	private String clienteCif = "";
	/**
	 * Sirva para guardar el nombre de cada cliente
	 */
	private String clienteNombre = "";
	/**
	 * Sirva para guardar el primer apellido de cada cliente
	 */
	private String clienteApellido1 = "";
	/**
	 * Sirva para guardar el segundo apellido de cada cliente
	 */
	private String clienteApellido2 = "";
	/**
	 * Sirva para guardar el identificador único de la dirección en la dirección de
	 * cada cliente
	 */
	private int direccionId = 0;
	/**
	 * Sirva para guardar la dirección de la dirección en la dirección de cada
	 * cliente
	 */
	private String direccionDireccion = "";
	/**
	 * Sirva para guardar el Código Postal de la dirección en la dirección de cada
	 * cliente
	 */
	private String direccionCp = "";
	/**
	 * Sirva para guardar la provincia de la dirección en la dirección de cada
	 * cliente
	 */
	private String direccionProvincia = "";
	/**
	 * Sirva para guardar la población de la dirección en la dirección de cada
	 * cliente
	 */
	private String direccionPoblacion = "";
	/**
	 * Sirva para guardar el país de la dirección en la dirección de cada cliente
	 */
	private String direccionPais = "";
	/**
	 * Sirva para guardar el saldo de cada cliente
	 */
	private BigDecimal saldo = BigDecimal.ZERO;

	// CONSTRUCTORES
	/**
	 * Constructor con todos los atributos
	 * 
	 * @param numeroRegistro
	 * @param clienteId
	 * @param clienteCif
	 * @param clienteNombre
	 * @param clienteApellido1
	 * @param clienteApellido2
	 * @param direccionId
	 * @param direccionDireccion
	 * @param direccionCp
	 * @param direccionProvincia
	 * @param direccionPoblacion
	 * @param direccionPais
	 * @param saldo
	 */
	public ClientesDireccionSaldo(int clienteId, String clienteCif, String clienteNombre, String clienteApellido1,
			String clienteApellido2, int direccionId, String direccionDireccion, String direccionCp,
			String direccionProvincia, String direccionPoblacion, String direccionPais, BigDecimal saldo) {
		super();
		this.clienteId = clienteId;
		this.clienteCif = clienteCif;
		this.clienteNombre = clienteNombre;
		this.clienteApellido1 = clienteApellido1;
		this.clienteApellido2 = clienteApellido2;
		this.direccionId = direccionId;
		this.direccionDireccion = direccionDireccion;
		this.direccionCp = direccionCp;
		this.direccionProvincia = direccionProvincia;
		this.direccionPoblacion = direccionPoblacion;
		this.direccionPais = direccionPais;
		this.saldo = saldo;
	}

	/**
	 * Constructor vacío
	 */
	public ClientesDireccionSaldo() {

	}

	// GETTERS AND SETTERS
	/**
	 * Nos dá el número de registro
	 * 
	 * @return un int con el número de registro
	 */
	public int getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * Cambia el número de registro
	 * 
	 * @param numeroRegistro
	 */
	public void setNumeroRegistro(int numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/**
	 * Nos devuelve el identificador del cliente
	 * 
	 * @return
	 */
	public int getClienteId() {
		return clienteId;
	}

	/**
	 * Cambia el identificador del cliente
	 * 
	 * @param clienteId
	 */
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * Nos devuelve el CIF del cliente
	 * 
	 * @return
	 */
	public String getClienteCif() {
		return clienteCif;
	}

	/**
	 * Cambia el CIF
	 * 
	 * @param clienteCif
	 */
	public void setClienteCif(String clienteCif) {
		this.clienteCif = clienteCif;
	}

	/**
	 * Nos devuelve el nombre del cliente
	 * 
	 * @return
	 */
	public String getClienteNombre() {
		return clienteNombre;
	}

	/**
	 * Cambia el nombre
	 * 
	 * @param clienteNombre
	 */
	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	/**
	 * Nos devuelve el primer apellido del cliente.
	 * 
	 * @return
	 */
	public String getClienteApellido1() {
		return clienteApellido1;
	}

	/**
	 * Cambia el primer apellido
	 * 
	 * @param clienteApellido1
	 */
	public void setClienteApellido1(String clienteApellido1) {
		this.clienteApellido1 = clienteApellido1;
	}

	/**
	 * Nos devuelve el segundo apellido del cliente.
	 * 
	 * @return
	 */
	public String getClienteApellido2() {
		return clienteApellido2;
	}

	/**
	 * Cambia el segundo apellido.
	 * 
	 * @param clienteApellido2
	 */
	public void setClienteApellido2(String clienteApellido2) {
		this.clienteApellido2 = clienteApellido2;
	}

	/**
	 * Nos devuelve el identificador de la dirección del cliente.
	 * 
	 * @return
	 */
	public int getDireccionId() {
		return direccionId;
	}

	/**
	 * Cambia el identificador de la dirección del cliente
	 * 
	 * @param direccionId
	 */
	public void setDireccionId(int direccionId) {
		this.direccionId = direccionId;
	}

	/**
	 * Nos devuelve la dirección de direccion del cliente.
	 * 
	 * @return
	 */
	public String getDireccionDireccion() {
		return direccionDireccion;
	}

	/**
	 * Cambia la dirección
	 * 
	 * @param direccionDireccion
	 */
	public void setDireccionDireccion(String direccionDireccion) {
		this.direccionDireccion = direccionDireccion;
	}

	/**
	 * Nos devuelve el Código Postal de la dirección del cliente.
	 * 
	 * @return
	 */
	public String getDireccionCp() {
		return direccionCp;
	}

	/**
	 * Cambia el Código Postal
	 * 
	 * @param direccionCp
	 */
	public void setDireccionCp(String direccionCp) {
		this.direccionCp = direccionCp;
	}

	/**
	 * Nos devuelve la provincia de la dirección del cliente.
	 * 
	 * @return
	 */
	public String getDireccionProvincia() {
		return direccionProvincia;
	}

	/**
	 * Cambia la provincia
	 * 
	 * @param direccionProvincia
	 */
	public void setDireccionProvincia(String direccionProvincia) {
		this.direccionProvincia = direccionProvincia;
	}

	/**
	 * Nos devuelve la población de la dirección del cliente.
	 * 
	 * @return
	 */
	public String getDireccionPoblacion() {
		return direccionPoblacion;
	}

	/**
	 * Cambia la población
	 * 
	 * @param direccionPoblacion
	 */
	public void setDireccionPoblacion(String direccionPoblacion) {
		this.direccionPoblacion = direccionPoblacion;
	}

	/**
	 * Nos devuelve el país de la dirección del cliente.
	 * 
	 * @return
	 */
	public String getDireccionPais() {
		return direccionPais;
	}

	/**
	 * Cambia país
	 * 
	 * @param direccionPais
	 */
	public void setDireccionPais(String direccionPais) {
		this.direccionPais = direccionPais;
	}

	/**
	 * Nos devuelve el saldo de la dirección del cliente.
	 * 
	 * @return
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * Cambia el saldo
	 * 
	 * @param saldo
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteApellido1 == null) ? 0 : clienteApellido1.hashCode());
		result = prime * result + ((clienteApellido2 == null) ? 0 : clienteApellido2.hashCode());
		result = prime * result + ((clienteCif == null) ? 0 : clienteCif.hashCode());
		result = prime * result + clienteId;
		result = prime * result + ((clienteNombre == null) ? 0 : clienteNombre.hashCode());
		result = prime * result + ((direccionCp == null) ? 0 : direccionCp.hashCode());
		result = prime * result + ((direccionDireccion == null) ? 0 : direccionDireccion.hashCode());
		result = prime * result + direccionId;
		result = prime * result + ((direccionPais == null) ? 0 : direccionPais.hashCode());
		result = prime * result + ((direccionPoblacion == null) ? 0 : direccionPoblacion.hashCode());
		result = prime * result + ((direccionProvincia == null) ? 0 : direccionProvincia.hashCode());
		result = prime * result + numeroRegistro;
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientesDireccionSaldo other = (ClientesDireccionSaldo) obj;
		if (clienteApellido1 == null) {
			if (other.clienteApellido1 != null)
				return false;
		} else if (!clienteApellido1.equals(other.clienteApellido1))
			return false;
		if (clienteApellido2 == null) {
			if (other.clienteApellido2 != null)
				return false;
		} else if (!clienteApellido2.equals(other.clienteApellido2))
			return false;
		if (clienteCif == null) {
			if (other.clienteCif != null)
				return false;
		} else if (!clienteCif.equals(other.clienteCif))
			return false;
		if (clienteId != other.clienteId)
			return false;
		if (clienteNombre == null) {
			if (other.clienteNombre != null)
				return false;
		} else if (!clienteNombre.equals(other.clienteNombre))
			return false;
		if (direccionCp == null) {
			if (other.direccionCp != null)
				return false;
		} else if (!direccionCp.equals(other.direccionCp))
			return false;
		if (direccionDireccion == null) {
			if (other.direccionDireccion != null)
				return false;
		} else if (!direccionDireccion.equals(other.direccionDireccion))
			return false;
		if (direccionId != other.direccionId)
			return false;
		if (direccionPais == null) {
			if (other.direccionPais != null)
				return false;
		} else if (!direccionPais.equals(other.direccionPais))
			return false;
		if (direccionPoblacion == null) {
			if (other.direccionPoblacion != null)
				return false;
		} else if (!direccionPoblacion.equals(other.direccionPoblacion))
			return false;
		if (direccionProvincia == null) {
			if (other.direccionProvincia != null)
				return false;
		} else if (!direccionProvincia.equals(other.direccionProvincia))
			return false;
		if (numeroRegistro != other.numeroRegistro)
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientesDireccionSaldo [numeroRegistro=" + numeroRegistro + ", clienteId=" + clienteId + ", clienteCif="
				+ clienteCif + ", clienteNombre=" + clienteNombre + ", clienteApellido1=" + clienteApellido1
				+ ", clienteApellido2=" + clienteApellido2 + ", direccionId=" + direccionId + ", direccionDireccion="
				+ direccionDireccion + ", direccionCp=" + direccionCp + ", direccionProvincia=" + direccionProvincia
				+ ", direccionPoblacion=" + direccionPoblacion + ", direccionPais=" + direccionPais + ", saldo=" + saldo
				+ "]";
	}

}