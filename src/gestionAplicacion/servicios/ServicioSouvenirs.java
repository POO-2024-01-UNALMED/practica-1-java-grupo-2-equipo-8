package gestionAplicacion.servicios;
import gestionAplicacion.usuario.Ticket;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.TarjetaCinemar;
import java.util.ArrayList;

public class ServicioSouvenirs extends Servicio{
	
	private Cliente cliente;
	private ArrayList<Pedido> ordenSouvenir = new ArrayList<>();
	private int codigoProducto;
	private double valorPedido;
	
	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre, String horario, Cliente cliente, ArrayList<Pedido> ordenSouvenir,
			int codigoProducto, double valorPedido) {
		super(nombre, horario);
		this.cliente = cliente;
		this.ordenSouvenir = ordenSouvenir;
		this.codigoProducto = codigoProducto;
		this.valorPedido = valorPedido;
	}
	
	public Pedido generarPedido() {return new Pedido();}
	public void editarPedido() {}
	public void mostrarPedido() {}
	public boolean procesarPago() {return true;}
	public String mostarCarta() {return "Carta";}
	public boolean verificarBono() {return false;}
	public String factura() {return "Factura";}
	public boolean verificarCodigoProductoFactura() {return false;}
	
	
	
}
