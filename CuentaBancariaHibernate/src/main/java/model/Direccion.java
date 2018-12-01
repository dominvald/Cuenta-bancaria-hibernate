/**
 * 
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author PracticasSoftware5 Clase modelo direccion
 */
@Entity
@Table(name = "direcciones")
public class Direccion {
	

	/**
	 * Identificador de la dirección.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id = 0;
	/**
	 * Campo para la dirección en la dirección.
	 */
	@Column(name = "direccion")
	private String direccionDireccion = "";
	/**
	 * Código Postal de la dirección.
	 */
	@Column(name = "cp")
	private String cpDireccion = "";
	/**
	 * Provincia de la dirección.
	 */
	@Column(name = "provincia")
	private String provinciaDireccion = "";
	/**
	 * Población de la dirección.
	 */
	@Column(name = "poblacion")
	private String poblacionDireccion = "";
	/**
	 * País de la dirección.
	 */
	@Column(name = "pais")
	private String paisDireccion = "";

	/**
	 * Constructor sin argumentos
	 */
	public Direccion() {
		super();
	}

	/**
	 * Constructor con todos los argumentos
	 * 
	 * @param idCliente
	 * @param direccion
	 * @param cp
	 * @param provincia
	 * @param poblacion
	 * @param pais
	 */
	public Direccion(String direccion, String cp, String provincia, String poblacion, String pais) {
		super();

		this.direccionDireccion = direccion;
		this.cpDireccion = cp;
		this.provinciaDireccion = provincia;
		this.poblacionDireccion = poblacion;
		this.paisDireccion = pais;
	}

	public Direccion(int id, String direccion, String cp, String provincia, String poblacion, String pais) {
		super();
		this.id = id;
		this.direccionDireccion = direccion;
		this.cpDireccion = cp;
		this.provinciaDireccion = provincia;
		this.poblacionDireccion = poblacion;
		this.paisDireccion = pais;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccionDireccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccionDireccion = direccion;
	}

	/**
	 * @return the cp
	 */
	public String getCp() {
		return cpDireccion;
	}

	/**
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cpDireccion = cp;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provinciaDireccion;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provinciaDireccion = provincia;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacionDireccion;
	}

	/**
	 * @param poblacion
	 *            the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacionDireccion = poblacion;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return paisDireccion;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.paisDireccion = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpDireccion == null) ? 0 : cpDireccion.hashCode());
		result = prime * result + ((direccionDireccion == null) ? 0 : direccionDireccion.hashCode());
		result = prime * result + id;
		result = prime * result + ((paisDireccion == null) ? 0 : paisDireccion.hashCode());
		result = prime * result + ((poblacionDireccion == null) ? 0 : poblacionDireccion.hashCode());
		result = prime * result + ((provinciaDireccion == null) ? 0 : provinciaDireccion.hashCode());
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
		Direccion other = (Direccion) obj;
		if (cpDireccion == null) {
			if (other.cpDireccion != null)
				return false;
		} else if (!cpDireccion.equals(other.cpDireccion))
			return false;
		if (direccionDireccion == null) {
			if (other.direccionDireccion != null)
				return false;
		} else if (!direccionDireccion.equals(other.direccionDireccion))
			return false;
		if (id != other.id)
			return false;
		if (paisDireccion == null) {
			if (other.paisDireccion != null)
				return false;
		} else if (!paisDireccion.equals(other.paisDireccion))
			return false;
		if (poblacionDireccion == null) {
			if (other.poblacionDireccion != null)
				return false;
		} else if (!poblacionDireccion.equals(other.poblacionDireccion))
			return false;
		if (provinciaDireccion == null) {
			if (other.provinciaDireccion != null)
				return false;
		} else if (!provinciaDireccion.equals(other.provinciaDireccion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", direccionDireccion=" + direccionDireccion + ", cpDireccion=" + cpDireccion
				+ ", provinciaDireccion=" + provinciaDireccion + ", poblacionDireccion=" + poblacionDireccion
				+ ", paisDireccion=" + paisDireccion + "]";
	}


}
