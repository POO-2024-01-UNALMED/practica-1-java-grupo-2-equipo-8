package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Scanner;

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
	public Pedido generarPedido() {
		Scanner sc = new Scanner(System.in);
		boolean casoValido = true;
		boolean casoValido2 = true;
		String Orden = "Los productos que llevas son:";
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
				if ((cantidad > 0) && (cantidad < Inventario.getProductosEnInventario().size())){
					codigo = Inventario.getProductosEnInventario().get(opcionMenu).getCodigoProducto();
					if ( null != Pedido.generarPedido(codigo, cantidad)){
						cliente1.getPedidos().add(Pedido.generarPedido(opcionMenu, cantidad));
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
			Orden = Orden + "\n" + cliente1.getPedidos().get(i).getCantidad()+" "+cliente1.getPedidos().get(i).getNombreproducto();
		}
		return Orden;
	}
	
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
