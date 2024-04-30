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
	
	
	public static ArrayList<Bono> verificarBono(Cliente cliente) {
		Bono bonosDelUsuario;
		ArrayList<Bono> bonos = new ArrayList<>();
		for (int i=0;i<Bono.getBonosCreados().size(); i++) {
			for (int j=0;j<cliente.getBonosCliente().size();j++) {
				if (Bono.getBonosCreados().get(i) == cliente.getBonosCliente().get(j)) {
					if (cliente.getBonosCliente().get(j).getTipoServicio().equalsIgnoreCase("Comida")){
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

	public ArrayList<Pedido> getOrdenComida() {
		return ordenComida;
	}

	public void setOrdenComida(ArrayList<Pedido> ordenComida) {
		this.ordenComida = ordenComida;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}
	
}
