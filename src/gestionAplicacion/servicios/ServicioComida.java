package gestionAplicacion.servicios;
import java.util.ArrayList;
import gestionAplicacion.usuario.Cliente;

public class ServicioComida extends Servicio{
	
	private Cliente cliente;
	private ArrayList<Pedido> ordenComida;
	private double valorPedido;
	
	public ServicioComida(){}
	
	public ServicioComida(String nombre, String horario, Cliente cliente, ArrayList<Pedido> ordenComida,
			double valorPedido) {
		super(nombre, horario);
		this.cliente = cliente;
		this.ordenComida = ordenComida;
		this.valorPedido = valorPedido;
	}
	public Pedido generarPedido() {return new Pedido();}
	public void editarPedido() {}
	public void mostrarPedido() {}
	public boolean procesarPago() {return true;}
	public String mostrarCarta() {return "Carta";}
	public String factura() {return "factura";}
	public boolean verificarBono() {return true;}
	
}
