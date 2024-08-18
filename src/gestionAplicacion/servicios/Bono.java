package gestionAplicacion.servicios;
import java.io.Serializable;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;

public class Bono implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
//	private static ArrayList<Bono> bonosCreados = new ArrayList<>();
	private Producto producto;
	private String tipoServicio;
	private Cliente cliente;
	
	public Bono(){}
	

	public Bono(String code, Producto producto, String tipoServicio, Cliente cliente) {
		this.codigo = code;
		this.producto = producto;
		this.tipoServicio = tipoServicio;
		this.cliente = cliente;
		cliente.getCineActual().getBonosCreados().add(this);
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

//	public static ArrayList<Bono> getBonosCreados() {
//		return bonosCreados;
//	}
//
//	public static void setBonosCreados(ArrayList<Bono> bonosCreados) {
//		Bono.bonosCreados = bonosCreados;
//	}


	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}




