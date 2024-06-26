package gestionAplicacion.usuario;
import java.util.ArrayList;
import java.time.Duration;
import java.util.Iterator;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
//import gestionAplicacion.servicios.Servicio;

public class Cliente {
	
	//Atributos
	private String nombre;
	private ArrayList<Pelicula> historialDePeliculas = new ArrayList<>();
	private ArrayList<Pelicula> peliculasDisponiblesParaCalificar = new ArrayList<>();
	private ArrayList<Producto> historialDePedidos = new ArrayList<>();
	private ArrayList<Ticket> tickets = new ArrayList<>();
	private ArrayList<String> facturas = new ArrayList<>();
	private int edad;
	private Membresia membresia;
	private long documento;
	private Duration duracionMembresiaDias;
	private TipoDeDocumento tipoDocumento;
	private TarjetaCinemar cuenta;
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<MetodoPago> metodosDePago = new ArrayList<>();
	private ArrayList<String> codigosDescuento = new ArrayList<>();
	private ArrayList<String> codigosBonos = new ArrayList<>();
	private ArrayList<Bono> bonos = new ArrayList<>();
	private SucursalCine cineActual;
	
	
	//Constructores
	public Cliente(){
		clientes.add(this);
	}

	public Cliente(String nombre, int edad, long documento, TipoDeDocumento tipoDocumento) {
		this();
		this.nombre = nombre;
		this.edad = edad;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
	}
	
	public Cliente(String nombre, ArrayList<Pelicula> historialDePeliculas, ArrayList<Producto> historialDePedidos, ArrayList<Ticket> ticket, ArrayList<String> facturas,
			int edad, Membresia membresia, long documento, Duration duracionMembresiaDias,
			TipoDeDocumento tipoDocumento, TarjetaCinemar cuenta, ArrayList<MetodoPago> metodosDePago,
			ArrayList<String> codigosDescuento) {
		this();
		this.nombre = nombre;
		this.historialDePeliculas = historialDePeliculas;
		this.historialDePedidos = historialDePedidos;
		this.tickets = ticket;
		this.facturas = facturas;
		this.edad = edad;
		this.membresia = membresia;
		this.documento = documento;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipoDocumento = tipoDocumento;
		this.cuenta = cuenta;
		this.metodosDePago = metodosDePago;
		//this.setBonosCliente(bonosCliente);
		this.setCodigosDescuento(codigosDescuento);
	}
	
	//Methods
	
	/**
	*Description: se recibe un parametro long con el numero de cedula de el cliente y se busca en el array
	*de clientes si hay alguno que tiene ese mismo documento asociado, en caso de que si se retorna ese cliente
	*del array y de lo contrario se retorna nulo.
	*@param numero :  sirve para verificar si el usuario ya esta registrado
	*@return <b>Cliente</b> :  se retorna nulo en caso de que no exista el cliente o se retorna el cliente existente.
	*/
	
	public static Cliente revisarDatosCliente(long numero) {
		Cliente cliente1=null;
		for(Cliente cliente : clientes) {
			if (cliente.documento==numero) {
				cliente1=cliente;
			}
		}
		return cliente1;
	}
	
	/**
	*Description: se verifica si el usuario tiene asociada una cuenta de tarjeta cinemar 
	*@return <b>boolean</b> :  retorna true o false dependiendo si cumple o no la condicion
	*/
	
	public boolean verificarCuenta() {
		boolean value = false;
		if (this.cuenta!=null) {
			value = true;
		}
		return value;
	}
	
	
	/**
	*Description: Reestablece los limites de pago de cada metodo de pago que el cliente tenga asociado
	*@param max : limite de pago 1
	*@param max2 : limite de pago 2
	*@param max3 : limite de pago 3
	*@param max4 : limite de pago 4
	*@param max5 : limite de pago 5
	*@return <b>void</b> :  No hay retorno
	*/
	public void restablecerLimiteMaximo(double max, double max2, double max3, double max4,double max5) {
		this.getMetodosDePago().get(0).setLimiteMaximoPago(max);
		this.getMetodosDePago().get(1).setLimiteMaximoPago(max2);
		this.getMetodosDePago().get(2).setLimiteMaximoPago(max3);
		this.getMetodosDePago().get(3).setLimiteMaximoPago(max4);
		if (this.getMetodosDePago().size()>4) {
			this.getMetodosDePago().get(4).setLimiteMaximoPago(max5);
		}
	}
	
	/**
	 * Description: Este método se encarga de mostrar al cliente los tickets que el cliente puede usar
	 * imprimiendo en pantalla información relevante de estos para facilitar
	 * la elección de la sala de cine a ingresar o el ticket a usar para usar la sala de espera.
	 * @param ticketsParaUsar : Este método retorna una lista de los tickets (De tipo ArrayList<Ticket>) que el cliente puede usar.
	 * @return: <b>String</b> : Este método se encarga de retornar un string con el nombre de la película
	 * el número de la sala de cine y la fecha de la película de cada uno de los tickets asociados del cliente.
	 * */
	public String mostrarTicketsParaUsar(ArrayList<Ticket> ticketsParaUsar) {
		
		StringBuilder tickets = new StringBuilder("\n");
		int i = 1;
		
		for (Ticket ticket : ticketsParaUsar) {
			
			tickets.append("\n" + i + ". Película: " + ticket.getPelicula().getNombre() 
			+ ", Número sala de Cine: " + ticket.getSalaDeCine().getNumeroSala() 
			+ ", Hora: " + ticket.getHorario());
			i++;
			
		}
		
		return tickets.toString();
	}
	
	/**
	 * Description: Este método se encarga de eliminar los tickets cuyo horario, más la duración de la película para la cuál fue adquirido 
	 * es menor a la fecha actual, para esto, creamos un array en el cuál almacenamos los tickets que cumplan la condición anterior y posteriormente
	 * los eliminamos.
	 * */
	public void dropTicketsCaducados() {
		//Creamos un apuntador del tipo de la interfaz iterator y le asignamos los tickets del cliente que ejecuta el método
		Iterator<Ticket> iteradorTickets = this.tickets.iterator();
		
		//Iteramos sobre el iterador, preguntando si tiene un elemento siguiente
		while(iteradorTickets.hasNext()) {
			
			//Creamos una variable de tipo ticket que me almacene el ticket sobre el que estoy iterando en estos momentos
			Ticket ticket = (Ticket) iteradorTickets.next();
			
			//Valido si el ticket ya caducó
			if( !(ticket.getHorario().plus(ticket.getPelicula().getDuracion()).isAfter(SucursalCine.getFechaActual())) ){
				//Lo elimino
				iteradorTickets.remove();
			}
		}
		
	}
	
	/**
	 * Description : Este método se encarga de retornar los tickets correspondientes a la sucursal de cine en la que se encuentra el cliente.
	 * @param sucursalCineProceso : Este método recibe como parámetro la sede (De tipo SucursalCine) desde la cuál el cliente esta accediendo a nuestros
	 * servicios, con el fin de hacer el filtrado.
	 * @return ArrayList<Ticket> : Este método retorna el resultado de la verifcación, con el fin de que el cliente solo pueda acceder a las salas de cine
	 * o a la sala de espera si este posee algún ticket de esta sucursal.
	 * */
	public ArrayList<Ticket> filtrarTicketsParaSede() {
		
		ArrayList<Ticket> ticketsParaUsar = new ArrayList<>();
		for (Ticket ticket : this.tickets) {
			if(ticket.getSucursalCompra().equals(this.getCineActual())) {
				ticketsParaUsar.add(ticket);
			}
		}
		return ticketsParaUsar;
	}
	
	/**
	 * Description : Este método se encarga de encontrar el género más visto por un cliente, para realizar este proceso, iteramos sobre su historial
	 * de películas, luego, obtenemos el género de cada una y alamacenamos las veces que se repite este género en un arraylists distinto, conservando
	 * el mismo índice, por último, evaluamos cual género tiene más visualizaciones y se retorna este, en caso de coincidir en visualizaciones con 
	 * otro género, retornamos el género más reciente.
	 * @return <b>String</b> : Este método retorna el género (De tipo String) con más visualizaciones.  
	 * */
	public String generoMasVisto() {
		
		ArrayList<String> generosVistos = new ArrayList<>();
		ArrayList<Integer> cantidadVisualizaciones = new ArrayList<>();
		
		for(Pelicula pelicula : this.historialDePeliculas) {
			//Verificamos si el género se encuentra en el array de generosVistos
			if (generosVistos.contains(pelicula.getGenero())) {
				
				//Buscamos en qué índice se encuentra
				int indiceGenero = generosVistos.indexOf(pelicula.getGenero());
				//Aumentamos en 1 las visualizaciones de ese género
				cantidadVisualizaciones.set(indiceGenero, cantidadVisualizaciones.get(indiceGenero) + 1);
				
			}else {
				//Lo añadimos al array de generos vistos
				generosVistos.add(pelicula.getGenero());
				//Añadimos una visualización en el array de visualizaciones
				cantidadVisualizaciones.add(1);
			}
		}

		boolean firstEntry = true;
		String generoMasVisto = null;
		int valorGeneroMasVisto = 0;
		
		//Revisamos cuál fue el género con mayor cantidad de visualizaciones
		for( String genero : generosVistos ) {

			int auxValorGeneroMasVisto = cantidadVisualizaciones.get(generosVistos.indexOf(genero));
			
			if (firstEntry) {
				//Si es la primera vez que iteramos sobre el array
				generoMasVisto = genero;
				valorGeneroMasVisto =  auxValorGeneroMasVisto;
				firstEntry = false;
			}
			
			//Comparamos las visualizaciones del género más visto con su auxiliar
			if(auxValorGeneroMasVisto >= valorGeneroMasVisto) {
				//Decimos que es el género más visto
				generoMasVisto = genero;
			}

		}
		
		return generoMasVisto;
	}
	
	//No se puede usar System.out.println(), Cambiar lógica del método.
	public void mostrarCodigosDescuento() {
		
		for (int i = 0; i < this.codigosDescuento.size(); i++) {
            System.out.println((i + 1) + ". " + this.codigosDescuento.get(i));
            
        }
		
		System.out.println((this.codigosDescuento.size()+1)+". Ninguno");
		System.out.println((this.codigosDescuento.size()+2)+". Salir");
	}
	
	public String  mostrarHistorialDePelicula() {
		String peliculas = null;
		int i = 1;
		for ( Pelicula pelicula : peliculasDisponiblesParaCalificar ) {
			
			if (peliculas == null) {
				peliculas =i + "." + pelicula.getNombre() + " " + pelicula.getTipoDeFormato();  
						
			}else {
				peliculas = peliculas+" \n"+ i + "." + pelicula.getNombre()+ " " + pelicula.getTipoDeFormato();
			}
			i++;
		}
		return peliculas;
		
		}
	
	public String  mostrarHistorialDePedidos() {
		String pedidos = null;
		int i = 1;
		for ( Producto producto : historialDePedidos  ) {
			
			if (pedidos == null) {
				pedidos =i + "." + producto.getNombre() + " " + producto.getTamaño();  
						
			}else {
				pedidos = pedidos+" \n"+ i + "." + producto.getNombre()+ " " + producto.getTamaño();
			}
			i++;
		}
		return pedidos;
		
		}
	
	//Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TarjetaCinemar getCuenta() {
		return cuenta;
	}

	public void setCuenta(TarjetaCinemar cuenta) {
		this.cuenta = cuenta;
	}

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<Cliente> clientes) {
		Cliente.clientes = clientes;
	}

	public ArrayList<Pelicula> getHistorialDePeliculas() {
		return historialDePeliculas;
	}

	public void setHistorialDePeliculas(ArrayList<Pelicula> historialDePeliculas) {
		this.historialDePeliculas = historialDePeliculas;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<String> getFacturas() {
		return facturas;
	}

	public void setFacturas(ArrayList<String> facturas) {
		this.facturas = facturas;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

	public Duration getDuracionMembresiaDias() {
		return duracionMembresiaDias;
	}

	public void setDuracionMembresiaDias(Duration duracionMembresiaDias) {
		this.duracionMembresiaDias = duracionMembresiaDias;
	}

	public TipoDeDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDeDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public ArrayList<MetodoPago> getMetodosDePago() {
		return metodosDePago;
	}

	public void setMetodosDePago(ArrayList<MetodoPago> metodosDePago) {
		this.metodosDePago = metodosDePago;
	}

	public SucursalCine getCineActual() {
		return cineActual;
	}

	public void setCineActual(SucursalCine cineActual) {
		this.cineActual = cineActual;
	}

	public ArrayList<String> getCodigosDescuento() {
		return codigosDescuento;
	}

	public void setCodigosDescuento(ArrayList<String> codigosDescuento) {
		this.codigosDescuento = codigosDescuento;
	}

	public ArrayList<String> getCodigosBonos() {
		return codigosBonos;
	}

	public void setCodigosBonos(ArrayList<String> codigosBonos) {
		this.codigosBonos = codigosBonos;
	}

	public ArrayList<Bono> getBonos() {
		return bonos;
	}

	public void setBonos(ArrayList<Bono> bonos) {
		this.bonos = bonos;
	}

	public ArrayList<Producto> getHistorialDePedidos() {
		return historialDePedidos;
	}

	public void setHistorialDePedidos(ArrayList<Producto> historialDePedidos) {
		this.historialDePedidos = historialDePedidos;
	}
	
	
	public ArrayList<Pelicula> getPeliculasDisponiblesParaCalificar() {
		return peliculasDisponiblesParaCalificar;
	}

	public void setPeliculasParaCalificiar(ArrayList<Pelicula> peliculasDisponiblesParaCalificiar) {
		this.peliculasDisponiblesParaCalificar = peliculasDisponiblesParaCalificiar;
	}
	
}
