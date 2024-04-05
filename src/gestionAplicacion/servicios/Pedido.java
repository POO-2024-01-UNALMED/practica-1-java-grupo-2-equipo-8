package gestionAplicacion.servicios;

public class Pedido {
	
	private String producto;
	private int cantidad;
	private double valor;
	private String tamanoProducto;
	
	public Pedido(){}
	
	public Pedido(String producto, int cantidad, double valor, String tamanoProducto) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.valor = valor;
		this.tamanoProducto = tamanoProducto;
	}



	public void actualizarInventario() {}
	
}
