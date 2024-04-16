package gestionAplicacion.servicios;
import gestionAplicacion.usuario.Cliente;
import java.util.ArrayList;

public class ServicioSouvenirs extends Servicio{
	
	private Cliente cliente;
	private ArrayList<Pedido> ordenSouvenir = new ArrayList<>();
	private int codigoProducto;
	private double valorPedido;
	
	public ArrayList<Bono> verificarBono(Cliente cliente) {
		Bono bonosDelUsuario;
		ArrayList<Bono> bonos = new ArrayList<>();
		for (int i=0;i<Bono.getBonosCreados().size(); i++) {
			for (int j=0;j<cliente.getBonosCliente().size();j++) {
				if (Bono.getBonosCreados().get(i) == cliente.getBonosCliente().get(j)) {
					if (cliente.getBonosCliente().get(j).getTipoServicio().equalsIgnoreCase("Souvenir")){
						bonosDelUsuario = cliente.getBonosCliente().get(j);
						bonos.add(bonosDelUsuario);
					}
				}
			}
		}
		return bonos;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Pedido> getOrdenSouvenir() {
		return ordenSouvenir;
	}

	public void setOrdenSouvenir(ArrayList<Pedido> ordenSouvenir) {
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
	public String factura() {return "Factura";}
	public boolean verificarCodigoProductoFactura() {return false;}
	
	
	
}
