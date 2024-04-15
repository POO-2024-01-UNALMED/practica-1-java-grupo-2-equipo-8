package gestionAplicacion.servicios;

public class Pedido {
	
	private String nombreproducto;
	private int cantidad;
	private double valor;
	private String tamañoProducto;
	
	public Pedido(){}
	
	public Pedido(String nombreproducto, int cantidad, double valor, String tamañoProducto) {
		super();
		this.nombreproducto = nombreproducto;
		this.cantidad = cantidad;
		this.valor = valor;
		this.tamañoProducto = tamañoProducto;
	}



	public void actualizarInventario() {}
	
}
