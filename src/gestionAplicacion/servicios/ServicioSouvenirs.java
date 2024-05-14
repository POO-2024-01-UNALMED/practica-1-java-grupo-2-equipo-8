package gestionAplicacion.servicios;
import gestionAplicacion.usuario.Cliente;
import java.util.ArrayList;

public class ServicioSouvenirs extends Servicio{
	
	private Cliente cliente;
	private ArrayList<Producto> ordenSouvenir = new ArrayList<>();
	private int codigoProducto;
	private double valorPedido;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Producto> getOrdenSouvenir() {
		return ordenSouvenir;
	}

	public void setOrdenSouvenir(ArrayList<Producto> ordenSouvenir) {
		this.ordenSouvenir = ordenSouvenir;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre, String horario, Cliente cliente, ArrayList<Producto> ordenSouvenir,
			int codigoProducto, double valorPedido) {
		this.cliente = cliente;
		this.ordenSouvenir = ordenSouvenir;
		this.codigoProducto = codigoProducto;
		this.valorPedido = valorPedido;
	}
}
