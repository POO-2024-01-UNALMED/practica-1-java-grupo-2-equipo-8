package gestionAplicacion.servicios;

public class Pedido {
	
	private String nombreproducto;
	private int cantidad;
	private double valor;
	
    public Pedido(){}
	
	public Pedido(String nombreproducto, int cantidad, double valor, String tamañoProducto) {
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.valor = valor;
		this.tamañoProducto = tamañoProducto;
	}

	
	
	
	public String getNombreproducto() {
		return nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTamañoProducto() {
		return tamañoProducto;
	}

	public void setTamañoProducto(String tamañoProducto) {
		this.tamañoProducto = tamañoProducto;
	}



	private String tamañoProducto;
	
	


	public void actualizarInventario() {}
	
}
