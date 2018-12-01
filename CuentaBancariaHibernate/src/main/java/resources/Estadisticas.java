package resources;

import java.math.BigDecimal;

public class Estadisticas {
	/**
	 *  Contador de clientes encontrados
	 */
	private long contadorInternoClientesEncontrados=0L;
	/**
	 * Contador de clientes con saldo 0
	 */
	private long contadorInternoClientesSaldoCero=0L;
	/**
	 * Contador de clientes con saldo positivo
	 */
	private long contadorInternoClientesSaldoPositivo=0L;
	/**
	 * Contador de clientes sin dirección
	 */
	private long contadorInternoClientesSinDireccion=0L;
	/**
	 * Sirve para almacenar el saldo medio de los clientes
	 */
	private BigDecimal saldoMedio = BigDecimal.ZERO;
	/**
	 * Sirve para almacenar la suma de todos los saldos de los clientes
	 */
	private BigDecimal sumaSaldoClientes = BigDecimal.ZERO;
	
	public Estadisticas() {}
	
	public Estadisticas(long contadorInternoClientesEncontrados, long contadorInternoClientesSaldoCero,
			long contadorInternoClientesSaldoPositivo, long contadorInternoClientesSinDirección, BigDecimal saldoMedio,
			BigDecimal sumaSaldoClientes) {
		super();
		this.contadorInternoClientesEncontrados = contadorInternoClientesEncontrados;
		this.contadorInternoClientesSaldoCero = contadorInternoClientesSaldoCero;
		this.contadorInternoClientesSaldoPositivo = contadorInternoClientesSaldoPositivo;
		this.contadorInternoClientesSinDireccion = contadorInternoClientesSinDirección;
		this.saldoMedio = saldoMedio;
		this.sumaSaldoClientes = sumaSaldoClientes;
	}

	public long getContadorInternoClientesEncontrados() {
		return contadorInternoClientesEncontrados;
	}

	public void setContadorInternoClientesEncontrados(long contadorInternoClientesEncontrados) {
		this.contadorInternoClientesEncontrados = contadorInternoClientesEncontrados;
	}

	public long getContadorInternoClientesSaldoCero() {
		return contadorInternoClientesSaldoCero;
	}

	public void setContadorInternoClientesSaldoCero(long contadorInternoClientesSaldoCero) {
		this.contadorInternoClientesSaldoCero = contadorInternoClientesSaldoCero;
	}

	public long getContadorInternoClientesSaldoPositivo() {
		return contadorInternoClientesSaldoPositivo;
	}

	public void setContadorInternoClientesSaldoPositivo(long contadorInternoClientesSaldoPositivo) {
		this.contadorInternoClientesSaldoPositivo = contadorInternoClientesSaldoPositivo;
	}

	public long getContadorInternoClientesSinDirección() {
		return contadorInternoClientesSinDireccion;
	}

	public void setContadorInternoClientesSinDirección(long contadorInternoClientesSinDirección) {
		this.contadorInternoClientesSinDireccion = contadorInternoClientesSinDirección;
	}

	public BigDecimal getSaldoMedio() {
		return saldoMedio;
	}

	public void setSaldoMedio(BigDecimal saldoMedio) {
		this.saldoMedio = saldoMedio;
	}

	public BigDecimal getSumaSaldoClientes() {
		return sumaSaldoClientes;
	}

	public void setSumaSaldoClientes(BigDecimal sumaSaldoClientes) {
		this.sumaSaldoClientes = sumaSaldoClientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (contadorInternoClientesEncontrados ^ (contadorInternoClientesEncontrados >>> 32));
		result = prime * result + (int) (contadorInternoClientesSaldoCero ^ (contadorInternoClientesSaldoCero >>> 32));
		result = prime * result
				+ (int) (contadorInternoClientesSaldoPositivo ^ (contadorInternoClientesSaldoPositivo >>> 32));
		result = prime * result
				+ (int) (contadorInternoClientesSinDireccion ^ (contadorInternoClientesSinDireccion >>> 32));
		result = prime * result + ((saldoMedio == null) ? 0 : saldoMedio.hashCode());
		result = prime * result + ((sumaSaldoClientes == null) ? 0 : sumaSaldoClientes.hashCode());
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
		Estadisticas other = (Estadisticas) obj;
		if (contadorInternoClientesEncontrados != other.contadorInternoClientesEncontrados)
			return false;
		if (contadorInternoClientesSaldoCero != other.contadorInternoClientesSaldoCero)
			return false;
		if (contadorInternoClientesSaldoPositivo != other.contadorInternoClientesSaldoPositivo)
			return false;
		if (contadorInternoClientesSinDireccion != other.contadorInternoClientesSinDireccion)
			return false;
		if (saldoMedio == null) {
			if (other.saldoMedio != null)
				return false;
		} else if (!saldoMedio.equals(other.saldoMedio))
			return false;
		if (sumaSaldoClientes == null) {
			if (other.sumaSaldoClientes != null)
				return false;
		} else if (!sumaSaldoClientes.equals(other.sumaSaldoClientes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estadisticas [contadorInternoClientesEncontrados=" + contadorInternoClientesEncontrados
				+ ", contadorInternoClientesSaldoCero=" + contadorInternoClientesSaldoCero
				+ ", contadorInternoClientesSaldoPositivo=" + contadorInternoClientesSaldoPositivo
				+ ", contadorInternoClientesSinDirección=" + contadorInternoClientesSinDireccion + ", saldoMedio="
				+ saldoMedio + ", sumaSaldoClientes=" + sumaSaldoClientes + "]";
	}

	public Estadisticas clone() {
		Estadisticas clone = new Estadisticas();
		
		clone.contadorInternoClientesEncontrados = this.contadorInternoClientesEncontrados;
		clone.contadorInternoClientesSaldoCero = this.contadorInternoClientesSaldoCero;
		clone.contadorInternoClientesSaldoPositivo = this.contadorInternoClientesSaldoPositivo;
		clone.contadorInternoClientesSinDireccion = this.contadorInternoClientesSinDireccion;
		clone.saldoMedio = this.saldoMedio;
		clone.sumaSaldoClientes = this.sumaSaldoClientes;
		
		return clone;
	}
	
}
