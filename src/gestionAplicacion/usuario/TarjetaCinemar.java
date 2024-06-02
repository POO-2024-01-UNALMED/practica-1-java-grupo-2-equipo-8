package gestionAplicacion.usuario;

import gestionAplicacion.servicios.Arkade;

public class TarjetaCinemar implements IBuyable {
	
	//Atributos
	private double saldo;
	private boolean estado;
	private Cliente dueno;
	
	
	//constructores
	public TarjetaCinemar(){Arkade.getTarjetasEnInventario().add(this);}
	
	public TarjetaCinemar(int saldo, boolean estado, Cliente dueno) {
		super();
		this.saldo = saldo;
		this.estado = estado;
		this.dueno = dueno;
		Arkade.getTarjetasEnInventario().add(this);
	}
	
	//metodos
	/**
	 * Description : Este método se encarga de ingresar el saldo dado a la tarjeta cinemar
	 * @param saldo : Este método recibe como parámetro el saldo a ingresar
	 * (De tipo double)
	 * @return <b>void</b> : No hay retorno
	 * */
	public void ingresarSaldo(double saldo) {this.saldo+=saldo;}
	
	
	/**
	*Description: se le descuenta a la tarjeta cinemar el monto pasado en el parametro
	*@param saldo : se le pasa el monto a ser descontado
	*(De tipo double)
	*@return <b>void</b> : No hay retorno
	*/
	public void hacerPago(double saldo) {
		this.saldo-=saldo;
	}
	//private static TarjetaCinemar crearTarjeta() {return new TarjetaCinemar();}

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

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
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
