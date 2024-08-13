package gestionAplicacion.servicios.herencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.Serializable;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.IBuyable;
import gestionAplicacion.usuario.MetodoPago;

public abstract class Servicio implements IBuyable, Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String nombre;
	protected Cliente cliente;
	protected ArrayList<Producto> inventario = new ArrayList<>();
	protected ArrayList<Producto> orden = new ArrayList<>();
	protected ArrayList<Bono> bonosCliente = new ArrayList<>();
	protected double valorPedido;
	
	
	public Servicio(){}
	
	public Servicio(String nombre) {
		this.nombre = nombre;
	}
	
	//ligadura estatica
	
	public String  mostrarBonos() {
		int n = 0;
		String bono = "\n ====== Tienes los siguientes bonos disponibles ======\n"+
						"\n0. No reclamar ningun bono.";
		for(int i = 0;i < bonosCliente.size();i++) {
				n = i + 1;
				bono = bono + "\n" + n + ". " + bonosCliente.get(i).getProducto().getNombre() + " " + bonosCliente.get(i).getProducto().getTamaño();
		}
		return bono;
	}
	
	public void actualizarBonos() {
		for(int i = 0;i < cliente.getBonos().size();i++) {
			if (cliente.getBonos().get(i).getTipoServicio().equalsIgnoreCase(nombre)) {
				bonosCliente.add(cliente.getBonos().get(i));
			}
		}
	}
	
	public Producto descuentarPorGenero (SucursalCine cine) {
		for (int i = 0;i < orden.size();i++) {
			for(int j = 0; j < cine.getTicketsCreados().size(); j++) {
				if(orden.get(i).getGenero().equalsIgnoreCase(cine.getTicketsCreados().get(j).getPelicula().getGenero()) && cliente.equals(cine.getTicketsCreados().get(j).getDueno())){
					LocalDate fecha = SucursalCine.getFechaActual().toLocalDate();
					if (fecha.isEqual(cine.getTicketsCreados().get(j).getHorario().toLocalDate()) && cine.getTicketsCreados().get(j).isDescuento()) {
						cine.getTicketsCreados().get(j).setDescuento(false);
						return orden.get(i);
					}
				}
			}
		}
		return null;
	}
	
	// Metodos abstractos y ligadura Dinamica
	
	public abstract boolean descuentarPorCompra (MetodoPago metodo);
	
	public abstract ArrayList<Producto> actualizarInventario();
	
	// Metodos
	
	public double calcularTotal() {
		double total = 0;
		for(int i = 0; i < orden.size();i++) {
			total = total + orden.get(i).getPrecio();
		}
		return total;
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

	//Ligadura Estatica
	
	public Producto validarBono(String codigo , Servicio servicio){
		Producto producto = new Producto();
		for (int i=0; i < Bono.getBonosCreados().size();i++) {
			if (Bono.getBonosCreados().get(i).getCodigo().equalsIgnoreCase(codigo) && Bono.getBonosCreados().get(i).getTipoServicio().equalsIgnoreCase(servicio.nombre)) {
				producto = Bono.getBonosCreados().get(i).getProducto();
				for (int j =0; j < Bono.getBonosCreados().get(i).getCliente().getBonos().size(); j++) {
					if (Bono.getBonosCreados().get(i).getCliente().getBonos().get(j).getCodigo().equalsIgnoreCase(codigo)) {
						Bono.getBonosCreados().get(i).getCliente().getBonos().remove(j);
						ArrayList<Bono> bonosCliente1 = new ArrayList<>();
						bonosCliente = bonosCliente1;
					}
				}
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
		String pedido = "";
		double total = 0;
		for(int i =0;i<orden.size();i++) {
			int n = i + 1;
			pedido = pedido + "\n" + n +" -- " +orden.get(i).getCantidad()+" " + orden.get(i).getNombre() + " " + orden.get(i).getTamaño() +
					" : $" + orden.get(i).getPrecio();
			total = total + orden.get(i).getPrecio();
		}
		
		pedido = pedido + "\n Total: $" + total;
		return pedido;
	}
	
	/**
	*Description: Muestra todos los productos disponibles que hay en el inventario 
	*@return <b>Productos</b> :  Muestra todos los productos disponibles, 
	*para generar la seleccion del producto deseado
	*/
	
	
	public String mostrarInventario() {
		String productos = "\n----------Productos disponibles----------\n\n0. Ningun producto";
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
			producto.setGenero(inventario.get(indice).getGenero());
			return producto;
		}
		else {
			return null;
		}
		
	}
	
	/** Description: Este metodo se encarga de remover los productos que fueron mal calificadas en dos sucursales, por lo
	 * tanto por temas de negocio decidimos eliminar este producto por malas ventas, usando la funcion remove, quitandola
	 * de la cartelera principal de peliculas.
	 *
	 * */
	public void eliminarProducto(ArrayList<Producto> productosEliminar) {
		for(Producto producto:productosEliminar) {
			this.inventario.remove(producto);
		}
	}

 
		 
	public static ArrayList<Producto> filtrarPorNombreDeProducto(String nombreProducto, ArrayList<Producto> Inventario){
		ArrayList<Producto> productosEncontrados = new ArrayList<>();
		
		for (Producto producto : Inventario) {
			if (producto.getNombre().equals(nombreProducto)) {
				productosEncontrados.add(producto);
			}
		}
		
		return productosEncontrados;
	}

/*	
	public void logicaCalificacionProductos(Producto producto){	
		
		ArrayList <Producto> productosCalificados = filtrarPorNombreDeProducto(producto.getNombre(), this.inventario);
		double promedio =0;
		double calificacionReal=0;
		boolean verificacionCambio=true;
		for(Producto productos : productosCalificados) {
			promedio = promedio + productos.getValoracionComida();
			calificacionReal = promedio/productosCalificados.size();
			verificacionCambio=productos.isStrikeCambio();
			
			
		}
		if (calificacionReal<3) {
			if(verificacionCambio) {
				SucursalCine sucursal=seleccionarSucursalAleatoriamente(this);
				for (Producto productos1:productosCalificados) {
					this.inventario.remove(productos1);
					if (productos1.getTipoDeFormato().equals("2D")){
						new Pelicula(pelicula1.getNombre(),(int)(pelicula1.getPrecio()*0.9),pelicula1.getGenero(),pelicula1.getDuracion(),pelicula1.getClasificacion(),pelicula1.getTipoDeFormato(),sucursal);
						
					}
				}
				
			}
			else {
				eliminarProducto(peliculasCalificadas);
			}			
		}
		else if (calificacionReal>4.5) {
			SucursalCine sucursal1=seleccionarSucursalAleatoriamente(this);
			for (Pelicula pelicula2:peliculasCalificadas) {
				if (pelicula2.getTipoDeFormato().equals("2D")){
					new Pelicula(pelicula2.getNombre(),(int)(pelicula2.getPrecio()*1.10),pelicula2.getGenero(),pelicula2.getDuracion(),pelicula2.getClasificacion(),pelicula2.getTipoDeFormato(),sucursal1);
					
				}
				
			}
			
						
		}
	
		
	}
	*/
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

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public ArrayList<Bono> getBonosCliente() {
		return bonosCliente;
	}

	public void setBonosCliente(ArrayList<Bono> bonosCliente) {
		this.bonosCliente = bonosCliente;
	}

	
	
}
