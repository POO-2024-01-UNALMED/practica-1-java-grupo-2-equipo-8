package gestionAplicacion.usuario;
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
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
			
	 		int categoria = membresia.getCategoria();
	 		double descuento = 0.05;
	 		descuento+=0.05 * categoria;
	 		switch (categoria) {
	 		
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
	public static String mostrarCategoria(Cliente cliente) {
		String resultado = null;
		int i = 1;
		Membresia membresiaActual = cliente.getMembresia();
		String nombreMembresiaActual = null;
		
		//Se actualiza el nombre de la membresia.
		if (membresiaActual == null) {
			nombreMembresiaActual = "Sin membresia";
		} else {
			nombreMembresiaActual = cliente.getMembresia().getNombre();}
		//Se recorre la lista de tipos de membresia.
		for (Membresia membresia : Membresia.getTiposDeMembresia()) {
			if (resultado == null) {
				if (nombreMembresiaActual.equalsIgnoreCase(membresia.getNombre())) {
					i++;
					continue;
				}
				resultado = "Categoria " + i + ". "+ membresia.getNombre() + "\n";
			}else {
				if (nombreMembresiaActual.equalsIgnoreCase(membresia.getNombre())) {
					i++;
					continue;
				}
				resultado = resultado + "Categoria " + i + ". " + membresia.getNombre() + "\n";
			}
			i++;
		}
		/*System.out.println(resultado)*/
		return resultado;
	}

	/**
	*<b>Description</b>: Este método verifica a que categorias puede acceder el cliente.
	*@param cliente : Se pide al cliente para revisar su historial de peliculas para la 
	*verificación. Si tiene X peliculas vistas en el cine, tiene acceso a ciertas categorias.
	*@param categoriaSeleccionada : Se pide el número de la categoria que quiera adquirir.
	*@return <b>boolean</b> : Se retorna un dato booleano que indica si el cliente puede 
	*adquirir la categoria de membresia seleccionada.
	*/
	public static boolean verificarRestriccionMembresia(Cliente cliente, int categoriaSeleccionada) {
		boolean esValido = false;
		
		//Si la categoria es 4 o 5, se revisa si se cumple los requisitos.
		switch (categoriaSeleccionada) {
		
		case 1, 2, 3: esValido = true; break;
		case 4: esValido = (cliente.getHistorialDePeliculas().size() >= 10) ? true : false; break;
		case 5: esValido = (cliente.getHistorialDePeliculas().size() >= 20) ? true : false; break;
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
	public static Membresia asignarMembresiaNueva(Membresia membresia, int categoriaMembresia) {
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
		
		//Al adquirir la membresia, se crea y asigna un método de pago único que permite acumular puntos canjeables con compras en el cine.
		MetodoPago.asignarMetodosDePago(cliente);
		//Se eliminan las referencias de los métodosDePagoUsados
		MetodoPago.getMetodosDePagoUsados().clear();
		//Se pasa la referencia de la membresia al cliente que lo compró y se agrega este último al array de clientes en Membresia
		this.getClientes().add(cliente);
	}
		



	@Override
	public String factura(Cliente cliente) {
		String factura = "=== Factura de compra ===\n" +
				"Fecha de compra: " + SalaCine.getFecha() + "\n" +
				"Nombre dueño: " + cliente.getNombre() + "\n" +
				"Documento: " + cliente.getDocumento() + "\n" +
				"Membresia: " + cliente.getMembresia().getNombre()+ "\n" +
				"Categoria: " + cliente.getMembresia().getCategoria() + "\n" +
				"Tipo: " + cliente.getMembresia().getTipoMembresia() + "\n" +
				"Duración: " + cliente.getMembresia().getDuracionMembresiaDias()+ " dias.\n" +
				"Precio de compra: " + cliente.getMembresia().getValorSuscripcionMensual() + "\n" +
				"Valida hasta: " + SalaCine.getFecha().plusDays(this.duracionMembresiaDias);
		cliente.getFacturas().add(factura);
		return factura;
	}
	
	
}




