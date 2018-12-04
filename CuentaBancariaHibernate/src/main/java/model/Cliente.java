package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author PracticasSoftware5
 *
 */

@Entity
@Table(name = "cliente")
public class Cliente {
	/**
	 * identificador del cliente.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	/**
	 * Cif del Cliente.
	 */
	@Column(name = "cif")
	private String cifCliente;
	/**
	 * Nombre del Cliente.
	 */
	@Column(name = "nombre")
	private String nombreCliente;
	/**
	 * Primer apellido del Cliente.
	 */
	@Column(name = "apellido1")
	private String apellido1Cliente;
	/**
	 * Segundo apellido del Cliente.
	 */
	@Column(name = "apellido2")
	private String apellido2Cliente;
	/**
	 * Constructor sin argumentos
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_direccion")
	private Direccion direccion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Movimiento> movimientos = new HashSet<Movimiento>(0);

	public Cliente() {
		super();
	}

	public Cliente(String cif, String nombre, String apellido1, String apellido2) {
		this.cifCliente = cif;
		this.nombreCliente = nombre;
		this.apellido1Cliente = apellido1;
		this.apellido2Cliente = apellido2;
	}

	/**
	 * Constructor que recibe 4 argumentos, este lo usamos cuando queremos crear un
	 * cliente nuevo, no necesitamos el id porque lo genera automáticamente la base
	 * de datos
	 * 
	 * @param cif
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 */
	public Cliente(String cif, String nombre, String apellido1, String apellido2, Direccion direccion) {
		super();
		this.cifCliente = cif;
		this.nombreCliente = nombre;
		this.apellido1Cliente = apellido1;
		this.apellido2Cliente = apellido2;
		this.direccion = direccion;
	}

	/**
	 * Constructor con todos loa argumentos, este constructor lo usaremos a la hora
	 * de buscar clientes, necesitamos su id para poder gardarlo
	 * 
	 * @param id
	 * @param cif
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 */
	public Cliente(int id, String cif, String nombre, String apellido1, String apellido2, Direccion direccion) {
		super();
		this.id = id;
		this.cifCliente = cif;
		this.nombreCliente = nombre;
		this.apellido1Cliente = apellido1;
		this.apellido2Cliente = apellido2;
		this.direccion = direccion;
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
	 * @return the cif
	 */
	public String getCif() {
		return cifCliente;
	}

	/**
	 * @param cif
	 *            the cif to set
	 */
	public void setCif(String cif) {
		this.cifCliente = cif;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombreCliente;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombreCliente = nombre;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1Cliente;
	}

	/**
	 * @param apellido1
	 *            the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1Cliente = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2Cliente;
	}

	/**
	 * @param apellido2
	 *            the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2Cliente = apellido2;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido1Cliente == null) ? 0 : apellido1Cliente.hashCode());
		result = prime * result + ((apellido2Cliente == null) ? 0 : apellido2Cliente.hashCode());
		result = prime * result + ((cifCliente == null) ? 0 : cifCliente.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + id;
		result = prime * result + ((movimientos == null) ? 0 : movimientos.hashCode());
		result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apellido1Cliente == null) {
			if (other.apellido1Cliente != null)
				return false;
		} else if (!apellido1Cliente.equals(other.apellido1Cliente))
			return false;
		if (apellido2Cliente == null) {
			if (other.apellido2Cliente != null)
				return false;
		} else if (!apellido2Cliente.equals(other.apellido2Cliente))
			return false;
		if (cifCliente == null) {
			if (other.cifCliente != null)
				return false;
		} else if (!cifCliente.equals(other.cifCliente))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id != other.id)
			return false;
		if (movimientos == null) {
			if (other.movimientos != null)
				return false;
		} else if (!movimientos.equals(other.movimientos))
			return false;
		if (nombreCliente == null) {
			if (other.nombreCliente != null)
				return false;
		} else if (!nombreCliente.equals(other.nombreCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cifCliente=" + cifCliente + ", nombreCliente=" + nombreCliente
				+ ", apellido1Cliente=" + apellido1Cliente + ", apellido2Cliente=" + apellido2Cliente + ", direccion="
				+ direccion + ", movimientos=" + movimientos + "]";
	}

}
