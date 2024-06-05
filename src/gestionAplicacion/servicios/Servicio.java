package gestionAplicacion.servicios;

import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;

public class Servicio {
	
	private String nombre;
	private Cliente cliente;
	private ArrayList<Producto> inventario = new ArrayList<>();
	private ArrayList<Producto> orden = new ArrayList<>();
	
	public Servicio(){}
	
	public Servicio(String nombre) {
		this.nombre=nombre;
	}
	
	public void agregarOrden (Producto producto) {
		if(0 < orden.size()) {
			for (int i = 0; i < orden.size(); i++) {
				if ((producto.getNombre() == orden.get(i).getNombre()) && (producto.getTamaño() == orden.get(i).getTamaño())) {
					orden.get(i).setCantidad(orden.get(i).getCantidad() + producto.getCantidad());
					orden.get(i).setPrecio(orden.get(i).getPrecio() + producto.getPrecio() );
					break;
				}
				else if ((i+1) == orden.size()) {
					orden.add(producto);
					break;
				}
			}
		}
		else {
			orden.add(producto);
		}
	}
	
	public void descontarProducto (Producto producto) {
		for(int i=0; i< orden.size(); i++) {
			if(orden.get(i).getNombre() == producto.getNombre() && orden.get(i).getTamaño() == producto.getTamaño()) {
				orden.get(i).setPrecio(orden.get(i).getPrecio()-producto.getPrecio());
				break;
			}
		}
	}
	
	public Producto validarBono(int codigo , String servicio){
		Producto producto = new Producto();
		for (int i=0; i < Bono.getBonosCreados().size();i++) {
			if (Bono.getBonosCreados().get(i).getCodigo() == codigo && Bono.getBonosCreados().get(i).getTipoServicio().equalsIgnoreCase(servicio)) {
				producto = Bono.getBonosCreados().get(i).getProducto();
				Bono.getBonosCreados().remove(i);
				return producto;
			}
		}
		return null;
	}
	
	/**
	*Description: Recorre el ArrayList y muestra todos los pedidos que 
	*ha hecho hasta el momento
	*@return <b>Pedido</b> :  Genera un pedido para que el usuario lo logre visualizar y 
	*tenga conocimiento de lo que ha pedido
	*/
	
	public String mostrarOrden() {
		String pedido = "\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n";
		int r;
		float total = 0;
		for(int i =0;i<orden.size();i++) {
			r = i+1;
			pedido = pedido + "\n" + " -- " +orden.get(i).getCantidad()+" " + orden.get(i).getNombre() + " " + orden.get(i).getTamaño() +
					" : $" + orden.get(i).getPrecio();
			total = total + orden.get(i).getPrecio();
		}
		
		pedido = pedido + "\n Total a pagar: $" + total;
		return pedido;
	}
	
	/**
	*Description: Muestra todos los productos disponibles que hay en el inventario 
	*@return <b>Productos</b> :  Muestra todos los productos disponibles, 
	*para generar la seleccion del producto deseado
	*/
	
	
	public String mostrarInventario() {
		String productos = "\n----------Productos disponibles----------\n0. Ningun producto";
		int r;
		if(0 == inventario.size()) {
			productos = "\nNO HAY PRODUCTOS DISPONIBLES :(\n";
		}
		for(int i=0;i<inventario.size();i++) {
			r = i + 1;
			if (inventario.get(i).getCantidad()==0) {
				productos = productos + "\n" + r +". "+ inventario.get(i).getNombre() + " " + inventario.get(i).getTamaño() + " $" + inventario.get(i).getPrecio() + "NO HAY EN EL MOMENTO DE ESTE PRODUCTO";
			}
			else {
				productos = productos + "\n" + r +". "+ inventario.get(i).getNombre() + " " + inventario.get(i).getTamaño() + " $" + inventario.get(i).getPrecio();
			}
		}
		return productos;
	}
	
	/**
	*Description: Genera un pedido segun los parametros del usuario y 
	*se descuenta la cantidad del producto que se genero
	*@param indice : Sirve para ubicar el producto en el inventario segun lo que el cliente escogio
	*@param cantidad :  Sirve para saver cuantos de esos productos se van a descontar del inventario y 
	*cuantos productos de estos se cobraran al momento de pagar
	*@return <b>producto</b> : Genera el producto para agregarlo al arrayList de Orden y poder validar su compra
	*/
	
	public Producto hacerPedido (int indice, int cantidad) {
		if (inventario.get(indice).getCantidad() >= cantidad) {
			inventario.get(indice).setCantidad(inventario.get(indice).getCantidad()-cantidad);
			Producto producto = new Producto(inventario.get(indice).getNombre(),inventario.get(indice).getTamaño(),cantidad);
			producto.setPrecio(inventario.get(indice).getPrecio()*cantidad);
			return producto;
		}
		else {
			return null;
		}
		
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

	public ArrayList<Producto> getOrden() {
		return orden;
	}

	public void setOrden(ArrayList<Producto> orden) {
		this.orden = orden;
	}
	
	
}
