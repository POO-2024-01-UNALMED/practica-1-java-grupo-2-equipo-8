package gestionAplicacion.usuario;

import gestionAplicacion.servicios.ServicioEntretenimiento;

public class TarjetaCinemar {
	
	//Atributos
	private int saldo;
	private boolean estado;
	private Cliente dueno;
	
	
	//constructores
	public TarjetaCinemar(){ServicioEntretenimiento.getTarjetasEnInventario().add(this);}
	
	public TarjetaCinemar(int saldo, boolean estado, Cliente dueno) {
		super();
		this.saldo = saldo;
		this.estado = estado;
		this.dueno = dueno;
		ServicioEntretenimiento.getTarjetasEnInventario().add(this);
	}
	
	//metodos
	private void ingresarSaldo() {}
	private void mostarSaldo() {}
	public void hacerPago(int saldo) {
		this.saldo-=saldo;
	}
	private static TarjetaCinemar crearTarjeta() {return new TarjetaCinemar();}

	//getters y setters
	public int getSaldo() {
		return saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public Cliente getDueno() {
		return dueno;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}
	
	
	
	
}
