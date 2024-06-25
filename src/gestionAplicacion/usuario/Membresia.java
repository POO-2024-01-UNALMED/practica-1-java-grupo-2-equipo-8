package gestionAplicacion.usuario;
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import iuMain.Administrador;
import gestionAplicacion.proyecciones.SalaCine;

public class Membresia implements IBuyable{
	
	//Atrbutos
	private String nombre;
	private int categoria;
	private static ArrayList<Membresia> tiposDeMembresia = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private double descuentoAsociado;
	private int valorSuscripcionMensual;
	private int duracionMembresiaDias;
	private int tipoMembresia;
	
	
	//Constructores
	public Membresia(String nombre, int categoria, int valorSuscripcionMensual, int duracionMembresiaDias) {
		this();
		this.nombre = nombre;
		this.categoria = categoria;
		this.valorSuscripcionMensual = valorSuscripcionMensual;
		this.duracionMembresiaDias = duracionMembresiaDias;
	}

	public Membresia(){
		tiposDeMembresia.add(this);
	}
	
	public Membresia(String nombre, int categoria, ArrayList<Cliente> clientes, double descuentoAsociado,
			int valorSuscripcionMensual, int duracionMembresiaDias, int tipo) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.clientes = clientes;
		this.descuentoAsociado = descuentoAsociado;
		this.valorSuscripcionMensual = valorSuscripcionMensual;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipoMembresia = tipo;
		tiposDeMembresia.add(this);
	}
	
	
	public void crearBonoRecargaTarjetaCinemar() {
		
	}
	
	/**
	*<b>Description</b>: Este método se encarga de verificar si el cliente tiene membresia activa
	*@param cliente : Se pide al cliente para revisar su atributo de tipo Membresia
	*@return <b>string</b> : Se retorna un texto personalizado indicando si tiene membresia
	*o no.
	*/
	public static String verificarMembresiaActual(Cliente cliente) {
		//Se crea las instancias
		String mensaje = null;
		Membresia membresiaActual = cliente.getMembresia();
		String nombreMembresiaActual = null;
		
		//Se actualiza el nombre de la membresia.
		if (membresiaActual == null) {
			mensaje = "Bienvenido, " + cliente.getNombre() 
			+".\nActualmente, no tiene membresia activa en el sistema.\nPor favor, seleccione la membresia que desea adquirir:\n";
		} else {
			nombreMembresiaActual = cliente.getMembresia().getNombre();
			mensaje = "Bienvenido, " + cliente.getNombre() 
			+".\nActualmente, su membresia es " + nombreMembresiaActual
			+ " de categoria " + cliente.getMembresia().getCategoria() + "\nPor favor, seleccione la membresia que desea adquirir/actualizar:\n";
		}
		return mensaje;
	}
		
	/**
	*<b>Description</b>: Este método se encarga de asignar los descuentos dependiendo de la
	* categoria de la membresia.
	*@param none : No se necesitan parametros.
	*@return <b>void</b> : No realiza retorno. El sistema asigna el correspondiente descuento
	*dependiendo de la categoria recorrida en el array.
	*/
	public static void asignarDescuento() {
		//Se realiza un ciclo y se toma la categoria de cada membresia para asignar el descuento con switch.
		for (Membresia membresia : Membresia.getTiposDeMembresia()) {
			//Se realiza el ciclo tomando la categoria de cada membresia.
	 		int categoria = membresia.getCategoria();
	 		double descuento = 0.05;
	 		descuento+=0.05 * categoria;
	 		switch (categoria) {
	 		
	 		//Por cada iteración del ciclo, se aumenta el número de la categoria y su descuento para usar el set.
	 		case 1: membresia.setDescuentoAsociado(descuento); break;
	 		case 2: membresia.setDescuentoAsociado(descuento); break;
	 		case 3: membresia.setDescuentoAsociado(descuento); break;
	 		case 4: membresia.setDescuentoAsociado(descuento); break;
	 		case 5: membresia.setDescuentoAsociado(descuento); break;
	 		}
		}	
 	}	
	
	public boolean actualizarSuscripcion() {
		
		return true;}
	
	
	public void adquirirSuscripcion() {
		
	}
	
	public ArrayList<Pelicula> recomendacionPeliculas(){
		
		return new ArrayList<>();
		}
	
	public double aumentaLimiteDePago() {
		
		return 2.73;}
	
	
	public void recargarRegaloTarjetaCinemar() {
		
	}
	/**
	*<b>Description</b>: Este método se encarga de mostrar las categorias de membresias disponibles
	*@param none : No se pide parametros
	*@return <b>string</b> : Se retorna un texto mostrando el nombre de las categorias.
	*/
	public static String mostrarCategoria(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		String resultado = null;
		int i = 1;
		Membresia membresiaActual = clienteProceso.getMembresia();
		String nombreMembresiaActual = null;
		
		//Se actualiza el nombre de la membresia.
		if (membresiaActual == null) {
			nombreMembresiaActual = "Sin membresia";
		} else {
			nombreMembresiaActual = clienteProceso.getMembresia().getNombre();}
		//Se recorre la lista de tipos de membresia.
		for (Producto membresia : sucursalCineProceso.getInventarioCine()) {
			//Se ignora los productos que no sean de tipo Membresia.
			if (!membresia.getTipoProducto().equalsIgnoreCase("Membresia")) {
				continue;
			}
				if (resultado == null) {
					if (membresia.getCantidad() == 0) {
						resultado = "Categoria " + i + ". " + membresia.getNombre() + " (AGOTADA)\n";
						i++;
						continue;
					}
					else if (nombreMembresiaActual.equalsIgnoreCase(membresia.getNombre())) {
						i++;
						continue;
					} else {
				resultado = "Categoria " + i + ". "+ membresia.getNombre() + ". Disponibles: " + membresia.getCantidad() +"\n";}
			}else {
				if (membresia.getCantidad() == 0) {
					resultado = resultado + "Categoria " + i + ". " + membresia.getNombre() + " (AGOTADA)\n";
					i++;
					continue;
				}
				else if (nombreMembresiaActual.equalsIgnoreCase(membresia.getNombre())) {
					i++;
					continue;
				} else {
			resultado = resultado + "Categoria " + i + ". " + membresia.getNombre() + ". Disponibles: " + membresia.getCantidad() +"\n";}
			}
			i++;
		}
		return resultado;
	}

	/**
	*<b>Description</b>: Este método verifica a que categorias puede acceder el cliente. Se revisa
	*si hay membresias disponibles y si el cliente tiene la cantidad de puntos e historial de peliculas como requisitos.
	*@param cliente : Se pide al cliente para revisar su historial de peliculas para la 
	*verificación. Si tiene X peliculas vistas en el cine, tiene acceso a ciertas categorias.
	*@param categoriaSeleccionada : Se pide el número de la categoria que quiera adquirir.
	*@param sucursalCineProceso : Se pide la sucursal de cine para revisar la cantidad de membresias.
	*@return <b>boolean</b> : Se retorna un dato booleano que indica si el cliente puede 
	*adquirir la categoria de membresia seleccionada.
	*/
	public static boolean verificarRestriccionMembresia(Cliente clienteProceso, int categoriaSeleccionada, SucursalCine sucursalCineProceso) {
		Membresia membresiaProceso = Membresia.asignarMembresiaNueva(categoriaSeleccionada);
		boolean esValido = false;
		
		//Se obtiene los puntos que posea el cliente.
		double puntos = 0.0;
		for (MetodoPago metodoPago : clienteProceso.getMetodosDePago()) {
			if (metodoPago.getNombre() == "Puntos") {
				puntos = metodoPago.getLimiteMaximoPago();
				break;
			}
		}
		//Se obtiene la cantidad de la membresia que hayan en el cine.
		int membresiaStock = 0;
		for (Producto membresiaInventario : sucursalCineProceso.getInventarioCine()) {
			if (membresiaInventario.getNombre() == membresiaProceso.getNombre()) {
				membresiaStock = membresiaInventario.getCantidad();
				break;
			}
		}
		//Si la categoria es 4 o 5, se revisa si se cumple los requisitos.
		switch (categoriaSeleccionada) {
		
		case 1: esValido = (membresiaStock > 0) ? true : false; break;
		case 2: esValido = (membresiaStock > 0 && puntos >= 5000) ? true : false; break;
		case 3: esValido = (membresiaStock > 0 && puntos >= 10000) ? true : false; break;
		case 4: esValido = (membresiaStock > 0 && clienteProceso.getHistorialDePeliculas().size() >= 10 && puntos >= 15000) ? true : false; break;
		case 5: esValido = (membresiaStock > 0 && clienteProceso.getHistorialDePeliculas().size() >= 20 && puntos >= 20000) ? true : false; break;
		}
		
		return esValido;
	}
	/**
	*<b>Description</b>: Este método asigna el tipo a la categoria de la membresia para
	*ser usado posteriormente en otras funcionalidades.
	*@param none : No se solicitan parametros.
	*@return <b>void</b> : No retorna ningún dato ya que solo actualiza el tipo 
	*de las membresias existentes.
	*/
	public static void asignarTipoMembresia () {
		//Se revisa la lista de Tipos de Membresia y se asigna el tipo
		for (Membresia membresia : Membresia.getTiposDeMembresia()) {
			if (membresia.getCategoria() > 0 && membresia.getCategoria() <= 3) {
				membresia.setTipoMembresia(1);
			} 
			else if (membresia.getCategoria() > 3 && membresia.getCategoria() <= 5){
				membresia.setTipoMembresia(2);
			}
		}
	}
	
	/**
	*<b>Description</b>: Este método se encarga de asignar la nueva membresia con un apuntador
	*de Membresia que coincida con la opción seleccionada durante el proceso de compra.
	*@param membresia : Se pide una instancia de tipo de membresia para usarlo como apuntador.
	*@param categoriaMembresia : Se pide un entero que es la selección de la membresia.
	*@return <b>Membresia</b> : Se retorna un dato de tipo Membresia que contiene el apuntador de
	*tipo Membresia que coincide con la categoria deseada.
	*/
	public static Membresia asignarMembresiaNueva(int categoriaMembresia) {
		//Se crea una instancia de tipo Membresia null
		Membresia membresiaNueva = null;
		//Se busca las instancias de tipo Membresia en Tipos de Membresia y si la categoria coincide, la instancia anterior apunta a este resultado
		for (Membresia membresia2 : Membresia.getTiposDeMembresia()) {
			if (membresia2.getCategoria() == categoriaMembresia) {
					membresiaNueva = membresia2;
					break;
			}
		}
		return membresiaNueva;
	}
	/**
	*<b>Description</b>: Este método se encarga de añadir al inventario de cada sucursal de cine, 
	*los productos de tipo Membresia que se usarán para limitar las membresias que se puede adquirir en cada sucursal.
	*Por cada sucursal de cine en el lista, se crean los productos que corresponden a cada membresia con una cantidad limitada.
	*Esto se usa para tener un control sobre el número de membresia que se pueden adquirir en cada cine.
	*@param sucursalesCine : Se pide la lista que contiene las sucursales de cine creadas para acceder a su inventario
	*/
	public static void stockMembresia(ArrayList<SucursalCine> sucursalesCine) {
		int i = 50;
		int puntos = 0;
		//Se revisa la lista de las sucursales de cine creadas.
		for (SucursalCine sucursalCine : sucursalesCine) {
			//Por cada membresia, se crea un producto de este tipo y se añade al inventario de la sucursal.
			for (Membresia membresia : Membresia.getTiposDeMembresia()) {
					Producto membresiaSucursal = new Producto("Membresia", membresia.getNombre(), puntos, i);
					sucursalCine.getInventarioCine().add(membresiaSucursal);
					puntos+=5000;
					i-=10;
			}
			//Se reinicia el contador de cantidad cada vez que se itere a una nueva sucursal de la lista.	
			i = 50;
		}
	}

	//Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracionMembresiaDias() {
		return duracionMembresiaDias;
	}
	
	public void setDuracionMembresiaDias(int duracionMembresiaDias) {
		this.duracionMembresiaDias = duracionMembresiaDias;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public static ArrayList<Membresia> getTiposDeMembresia() {
		return tiposDeMembresia;
	}

	public static void setTiposDeMembresia(ArrayList<Membresia> tiposDeMembresia) {
		Membresia.tiposDeMembresia = tiposDeMembresia;
	}

	public int getTipoMembresia() {
		return tipoMembresia;
	}

	public void setTipoMembresia(int tipo) {
		this.tipoMembresia = tipo;
	}

	public double getDescuentoAsociado() {
		return descuentoAsociado;
	}

	public void setDescuentoAsociado(double descuentoAsociado) {
		this.descuentoAsociado = descuentoAsociado;
	}

	public int getValorSuscripcionMensual() {
		return valorSuscripcionMensual;
	}

	public void setValorSuscripcionMensual(int valorSuscripcionMensual) {
		this.valorSuscripcionMensual = valorSuscripcionMensual;
	}
	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	//Métodos implementados por la interfaz.
	@Override
	public void procesarPagoRealizado(Cliente cliente) {
		//Se asigna la referencia de la membresia adquirida en el cliente y se actualizan sus métodos de pago.
		cliente.setMembresia(this);
		cliente.setDuracionMembresiaDias(Duration.ofDays(this.duracionMembresiaDias));
		
		//Se va al inventario del cine para restar la cantidad de membresias.
		for (Producto membresiaStock : cliente.getCine().getInventarioCine()) {
			if (membresiaStock.getNombre() == this.getNombre()) {
				membresiaStock.setCantidad(membresiaStock.getCantidad() - 1);
				break;
			}
		}
		//Al adquirir la membresia, se crea y asigna un método de pago único que permite acumular puntos canjeables con compras en el cine.
		MetodoPago.asignarMetodosDePago(cliente);
		
//		//Se eliminan las referencias de los métodosDePagoUsados
//		MetodoPago.getMetodosDePagoUsados().clear();
		
		//Se pasa la referencia de la membresia al cliente que lo compró y se agrega este último al array de clientes en Membresia
		this.getClientes().add(cliente);
		
		//Creamos la factura y se la asociamos al cliente
		this.factura(cliente);
	}
		



	@Override
	public void factura(Cliente cliente) {
		String factura = null;
		factura = "=== Factura de compra ===\n" +
				"Nombre dueño: " + cliente.getNombre() + "\n" +
				"Documento: " + cliente.getDocumento() + "\n" +
				"Membresia: " + cliente.getMembresia().getNombre()+ "\n" +
				"Categoria: " + cliente.getMembresia().getCategoria() + "\n" +
				"Tipo: " + cliente.getMembresia().getTipoMembresia() + "\n" +
				"Duración: " + cliente.getMembresia().getDuracionMembresiaDias()+ " dias.\n" +
				"Precio de compra: " + cliente.getMembresia().getValorSuscripcionMensual() + "\n" +
		cliente.getFacturas().add(factura);
	}
	
	
}


