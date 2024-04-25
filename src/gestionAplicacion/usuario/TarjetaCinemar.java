package gestionAplicacion.usuario;

import gestionAplicacion.servicios.ServicioEntretenimiento;

public class TarjetaCinemar implements IBuyable {
	
	//Atributos
	private double saldo;
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
	/**
	*Description: suma al saldo de la tarjeta Cinemar
	*@param saldo :  Es el valor a sumar en la tarjeta
	*/
	public void ingresarSaldo(double saldo) {this.saldo+=saldo;}
	
	private void mostarSaldo() {}
	
	/**
	*Description: se le descuenta a la tarjeta cinemar el monto pasado en el parametro
	*@param saldo : se le pasa el monto a ser descontado
	*/
	public void hacerPago(double saldo) {
		this.saldo-=saldo;
	}
	private static TarjetaCinemar crearTarjeta() {return new TarjetaCinemar();}

	//getters y setters
	public double getSaldo() {
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

	@Override
	public double realizarPago(double precio, MetodoPago metodoDePago, Cliente cliente) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void procesarPagoRealizado(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String factura(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
