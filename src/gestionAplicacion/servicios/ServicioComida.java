package gestionAplicacion.servicios;
import java.util.ArrayList;

public class ServicioComida {
	
	private int idComida;
	private Carta carta;
	private static int valorVentasTotales;
	private ArrayList<Pedido> ordenComida;
	private double valorPedidos;
	
	public Pedido generarPedido() {return new Pedido();}
	public void editarPedido(Pedido pedido) {}
	public void mostarPedido() {}
	public boolean procesarPago() {return true;}
	public void mostrarCarta() {}
	
}
