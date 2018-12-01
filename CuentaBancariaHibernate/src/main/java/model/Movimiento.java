package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author PracticasSoftware5
 *
 */
@Entity
@Table(name = "movimientos")
public class Movimiento {
	/**
	 * Identificador del movimiento.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id = 0;
	/**
	 * Importe del movimiento.
	 */
	@Column(name = "importe")
	private BigDecimal importe = BigDecimal.ZERO;
	/**
	 * Saldo del movimiento.
	 */
	@Column(name = "saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	/**
	 * ç Fecha del movimiento.
	 */
	@Column(name = "fecha", columnDefinition = "DATETIME")
	private LocalDateTime fecha;
	// NOTA: no existe el campo time en la tabla de movimientos
	// Se usa porque necesitamos saber la hora de cada movimiento
	// y usamos el campo fecha de la tabla de la base de datos
	// y obtenemos ambos, pero a la hora de guardar la información
	// de la fecha este campo no existe. Se usa a la hora de
	// devolver la información a la vista y de recoger esta
	// información en el dao.
	/**
	 * Hora del movimiento.
	 */
	// private Time time;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	/**
	 * 
	 */
	public Movimiento() {
		super();
	}

	/**
	 * 
	 * @param importe
	 * @param clienteId
	 */
	public Movimiento(BigDecimal importe, Cliente cliente) {
		super();
		this.importe = importe;
		this.cliente = cliente;
	}

	/**
	 * 
	 * @param importe
	 * @param saldo
	 * @param clienteId
	 */
	public Movimiento(BigDecimal importe, BigDecimal saldo, Cliente cliente) {
		super();
		this.saldo = saldo;
		this.importe = importe;
		this.cliente = cliente;
	}

	/**
	 * 
	 * @return
	 */
	// public Time getTime() {
	// return time;
	// }

	/**
	 * 
	 * @param time
	 */
	// public void setTime(Time time) {
	// this.time = time;
	// }

	public Movimiento(int id, BigDecimal importe, BigDecimal saldo, LocalDateTime fecha, Cliente cliente) {
		super();
		this.id = id;
		this.importe = importe;
		this.saldo = saldo;
		this.fecha = fecha;
		this.cliente = cliente;
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
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public void ingresar(BigDecimal importe) {
		this.importe = importe;
		this.saldo = saldo.add(importe);
	}

	/**
	 * 
	 * @param importe
	 * @return
	 */
	public Boolean retirar(BigDecimal importe) {
		/**
		 * 
		 */
		int resultado = 0;
		resultado = saldo.compareTo(importe);
		if (resultado == 0 || resultado == 1) {
			saldo = saldo.subtract(importe);
			this.importe = importe.negate();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", importe=" + importe + ", saldo=" + saldo + ", fecha=" + fecha + ", cliente="
				+ cliente.getId() + "]";
	}

}
