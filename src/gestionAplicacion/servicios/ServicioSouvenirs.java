package gestionAplicacion.servicios;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.TipoDeDocumento;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public String generarOrden(Cliente cliente1) {
		Scanner sc = new Scanner(System.in);
		boolean casoValido = true;
		boolean casoValido2 = true;
		String Orden = "--------------------ORDEN ACTUAL--------------------";
		int opcionMenu = 0;
		int cantidad;
		int codigo;
		do {
			do {
				System.out.print(Inventario.mostrarInventario());
				try {
					System.out.print("\nSelecciona una opcion: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
					opcionMenu = opcionMenu-1;
					System.out.print("\nIngrese la cantidad de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
					casoValido2 = false;
				}catch(NumberFormatException e){
					System.out.println("\nError, debes ingresar un único dato numérico\n");
					continue;
					}
				if (cantidad > 0){
					codigo = Inventario.getProductosEnInventario().get(opcionMenu).getCodigoProducto();
					Pedido pedido = new Pedido();
					pedido = Pedido.generarPedido(codigo, cantidad);
					if ( null != pedido){
						cliente1.getPedidos().add(pedido);
						System.out.println("Pedido realizado con exito");
						casoValido = false;
					}
					else {
						System.out.println("No hay suficientes productos");
						System.out.println("En el momento hay: "+Inventario.getProductosEnInventario().get(opcionMenu).getCantidadDisponible()+" de "+Inventario.getProductosEnInventario().get(opcionMenu).getNombreProducto());
					}
				}
			}while(casoValido2);
		}while(casoValido);
		for (int i = 0;i < cliente1.getPedidos().size(); i++) {
			int n = i+1;
			Orden = Orden + "\n"+ n + ". " + cliente1.getPedidos().get(i).getCantidad()+" "+cliente1.getPedidos().get(i).getNombreproducto();
		}
		return Orden;
	}
	public void editarPedido() {}
	public void mostrarPedido() {}
	public boolean procesarPago() {return true;}
	public String mostarCarta() {return "Carta";}
	public String factura() {return "Factura";}
	public boolean verificarCodigoProductoFactura() {return false;}
	
	
	
}
