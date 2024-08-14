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
	/**
	*Description: Me muestra los bonos que tengo disponible para decidir si reclamo uno de esos
	*@param servicio : Recibe un parametro de tipo servicio para poder ver los 
	*@return <b>bono</b> :Retorna un string con los tipos de bonos que tengo disponible por reclamar
	*/
	public static String  mostrarBonos(Servicio servicio) {
		int n = 0;
		String bono = "\n ====== Tienes los siguientes bonos disponibles ======\n"+
						"\n0. No reclamar ningun bono.";
		for(int i = 0;i < servicio.getBonosCliente().size();i++) {
				n = i + 1;
				bono = bono + "\n" + n + ". " + servicio.getBonosCliente().get(i).getProducto().getNombre() + " " + servicio.getBonosCliente().get(i).getProducto().getTamaño();
		}
		return bono;
	}
	
	/**
	*Description: Me filtra los bonos dependiendo del servicio al cual se esta accediendo, asi separando 
	*los distintos tipos de bonos y solo mostrando los requeridos para este servicio
	*/
	public void actualizarBonos() {
		for(int i = 0;i < cliente.getBonos().size();i++) {
			if (cliente.getBonos().get(i).getTipoServicio().equalsIgnoreCase(nombre)) {
				bonosCliente.add(cliente.getBonos().get(i));
			}
		}
	}
	
	/**
	*Description: Me verifica si tiene un producto del mismo genero que un ticke que hallas comprado y ademas
	*que fecha de la pelicula sea la misma del dia de la compra
	*@param cine : Recibe un parametro de tipo sucursalCine para poder ver los tickes creados y hacer 
	*la comparacion
	*@return <b>orden</b> :Retorna el primer producto que coincida con la condicion para poder generarle
	*su respectivo descuento
	*/
	
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
	
	public static Producto validarBono(String codigo , Servicio servicio){
		Producto producto;
		for (int i=0; i < Bono.getBonosCreados().size();i++) {
			if (Bono.getBonosCreados().get(i).getCodigo().equalsIgnoreCase(codigo) && Bono.getBonosCreados().get(i).getTipoServicio().equalsIgnoreCase(servicio.nombre)) {
				producto = Bono.getBonosCreados().get(i).getProducto();
				for (int j =0; j < Bono.getBonosCreados().get(i).getCliente().getBonos().size(); j++) {
					if (Bono.getBonosCreados().get(i).getCliente().getBonos().get(j).getCodigo().equalsIgnoreCase(codigo)) {
						Bono.getBonosCreados().get(i).getCliente().getBonos().remove(j);
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

 
	/**
	 * Description : Este método se encarga de retornar los productos cuyo nombre coincide con el nombre del producto seleccionada por el cliente.
	 * @param nombreProducto : Este método recibe como parámetro el nombre del producto (De tipo String) con el cuál se realizará el filtrado.
	 * @param Inventario : Este método recibe como parámetro una lista (De tipo ArrayList<Producto>) que contiene 
	 * los productos previamente filtrados según los datos del cliente y su disponibilidad horaria.
	 * @return <b>ArrayList<Producto></b> : Este método retorna un ArrayList de los productos cuyo nombre coinciden con el nombre seleccionado 
	 * por el cliente.
	 * */	 
	public static ArrayList<Producto> filtrarPorNombreDeProducto(String nombreProducto, ArrayList<Producto> Inventario){
		ArrayList<Producto> productosEncontrados = new ArrayList<>();
		
		for (Producto producto : Inventario) {
			if (producto.getNombre().equals(nombreProducto)) {
				productosEncontrados.add(producto);
			}
		}
		
		return productosEncontrados;
	}

	/** Description: Este metodo se encarga de seleccionar las sucursales del arrayList y con el uso de la funcion random de la libreria math,
	 * se selecciona una sucursal aleatoriamente, ya que esto nos permetira mas adelante el cambio de sucursal de un producto a otra
	 * 
	 * */	
	public  SucursalCine seleccionarSucursalAleatoriamente(SucursalCine sucursalCine) {
		while(true) {
			int numeroAleatorio= (int)(Math.random()*10)%(SucursalCine.getSucursalesCine().size());
			SucursalCine sucursalSeleccionada=SucursalCine.getSucursalesCine().get(numeroAleatorio);
			if(sucursalCine.equals(sucursalSeleccionada)) {
				continue;
			}
			
			return sucursalSeleccionada;
		}
	    
	
	}
	/** Description: Este metodo se encarga de analizar por semana que productos han sido bien o mal calificadas, evaluando
	 * las calificaciones de los clientes, si un producto es calificado por debajo de 3, lo consideramos como mal calificado
	 * y lo cambiamos de sede, y si la valoracion del producto esta por encima de 3 esta catalogada como bien, ya en el caso en que el 
	 * bono este calificado como mayor a 4.5, lo cambiamos de sede, ya que consideramos que es un muy buen producto, y 
	 * nos hara ganar mayor rentabilidad.Tambien se encarga de cambiar productos de sede, ya que en nuestra logica de negocio implementamos
	 * el sistema de calificaciones, entonces tenemos que estar constantemente pendientes de que productos han sido
	 * bien o mal recibidos por los clientes, y cambiandolos de sede, esperamos que su calificacion mejore, si esto
	 * no se da, el producto es eliminado del inventario, ya que se considera como malo
	 * */
	public void logicaCalificacionProductos(Producto producto){	
		
		ArrayList <Producto> productosCalificados = filtrarPorNombreDeProducto(producto.getNombre(), this.inventario);
		
		
		boolean verificacionCambio=true;
		
			
			
		
		if (producto.getValoracionComida()<3) {
			if(verificacionCambio) {
				SucursalCine sucursal=seleccionarSucursalAleatoriamente(producto.getSucursalSede());
				for (Producto productos1:productosCalificados) {
					this.inventario.remove(productos1);
					if (productos1.getTipoProducto().equals("comida")){
						new Producto(productos1.getNombre(),productos1.getTamaño(),productos1.getTipoProducto(),(productos1.getPrecio()*0.9),productos1.getCantidad(),productos1.getGenero(),sucursal);
						if (productos1.getTipoProducto().equals("souvenir")){
							new Producto(productos1.getNombre(),productos1.getTamaño(),productos1.getTipoProducto(),(productos1.getPrecio()*0.9),productos1.getCantidad(),productos1.getGenero(),sucursal);
					}
				   }
				}
			}
			else {
				eliminarProducto(productosCalificados);
			}			
		}
		else if (producto.getValoracionComida()>4.5) {
			SucursalCine sucursal1=seleccionarSucursalAleatoriamente(producto.getSucursalSede());
			for (Producto productos2:productosCalificados) {
				if (productos2.getTipoProducto().equals("comida")){
					new Producto(productos2.getNombre(),productos2.getTamaño(),productos2.getTipoProducto(),(productos2.getPrecio()*1.10),productos2.getCantidad(),productos2.getGenero(),sucursal1);
					if (productos2.getTipoProducto().equals("souvenir")){
						new Producto(productos2.getNombre(),productos2.getTamaño(),productos2.getTipoProducto(),(productos2.getPrecio()*1.10),productos2.getCantidad(),productos2.getGenero(),sucursal1);
						
					}
				}
				
			}
			
						
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
