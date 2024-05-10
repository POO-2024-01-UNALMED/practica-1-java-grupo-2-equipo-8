package gestionAplicacion.servicios;

import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;

public class Servicio {
	
	private String nombre;
	private Cliente cliente;
	private ArrayList<Producto> inventario = new ArrayList<>();
	
	public Servicio(){}
	
	public Servicio(String nombre) {
		this.setNombre(nombre);
	}
	
	public String mostrarInventario() {
		String productos = "----------Productos disponibles----------";
		if(0 == inventario.size()) {
			productos = "NO HAY PRODUCTOS DISPONIBLES :(";
		}
		for(int i=0;i<inventario.size();i++) {
			productos = productos + "\n" + inventario.get(i).getNombre() + " " + inventario.get(i).getTamaño();
		}
		return productos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Producto> getInventario() {
		return inventario;
	}
	
	public void setInventario(ArrayList<Producto> inventario) {
		this.inventario = inventario;
	}
	
	
}
