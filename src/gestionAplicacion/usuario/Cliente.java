package gestionAplicacion.usuario;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.time.Duration;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;

public class Cliente {
	
	//Atributos
	private String nombre;
	private ArrayList<Pelicula> historialDePeliculas = new ArrayList<>();
	private ArrayList<Producto> pedidos = new ArrayList<>();
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
	private SucursalCine cine;
	
	
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
	
	public Cliente(String nombre, ArrayList<Pelicula> historialDePeliculas, ArrayList<Ticket> ticket, ArrayList<String> facturas,
			int edad, Membresia membresia, long documento, Duration duracionMembresiaDias,
			TipoDeDocumento tipoDocumento, TarjetaCinemar cuenta, ArrayList<MetodoPago> metodosDePago,
			ArrayList<String> codigosDescuento) {
		this();
		this.nombre = nombre;
		this.historialDePeliculas = historialDePeliculas;
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
	 * Description : Este método genera un String que se imprimirá en pantalla, con el fin de que el usuario
	 * pueda visualizar la información relevante a su perfil.
	 * @param documento : Solicita un Long que corresponde al documento del usuario.
	 * @return String : Retorna la información del cliente, obtenida por el toString() de este.
	 * */
	public static String obtenerClientePorCedula(Long documento) {
		boolean verificacion = false;
		for (Cliente cliente : clientes) {
			verificacion = (cliente.getDocumento() == documento);
			if (verificacion) {
				return cliente.toString();
			}
		}
		return "El usuario no ha sido encontrado";
	}
	
	
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
	
	public Ticket generarTicket() {return new Ticket();}
	public void editarCuenta() {}
	public void modificarMetodosDePago() {}
	
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
	 * Description: Este método se encarga de mostrar al cliente las sala de cine a las que puede ingresar
	 * examinando su array de tickets e imprimiendo en pantalla información relevante de estos para facilitar
	 * la elección de la sala de cine a ingresar
	 * @return: <b>String</b> : Este método se encarga de retornar un string con el nombre de la película
	 * el número de la sala de cine y la fecha de la película de cada uno de los tickets asociados del cliente
	 * */
	public String mostrarTicketsParaUsar() {
		String tickets = null;
		int i = 1;
		for (Ticket ticket : this.getTickets()) {
			
			if (tickets == null) {
				tickets = i + ". Película: " + ticket.getPelicula().getNombre() 
						+ ", Número sala de Cine: " + ticket.getSalaDeCine().getNumeroSala() 
						+ ", Hora: " + ticket.getHorario();
			}else {
				tickets = tickets + "\n" + i + ". Película: " + ticket.getPelicula().getNombre() 
						+ ", Número sala de Cine: " + ticket.getPelicula().getNumeroDeSala() 
						+ ", Hora: " + ticket.getHorario();
			}
			i++;
		}
		return tickets;
	}
	
	/**
	 * Description: Este método se encarga de eliminar los tickets cuyo horario, más la duración de la película para la cuál fue adquirido 
	 * es menor a la fecha actual, para esto, creamos un array en el cuál almacenamos los tickets que cumplan la condición anterior y posteriormente
	 * los eliminamos.
	 * @return <b>void</b> : Este método no retorna nada (void), solo elimina los tickets caducados.
	 * */
	public void dropTicketsCaducados() {
		ArrayList<Ticket> ticketsCaducados = new ArrayList<>();
		
		for (Ticket ticket : this.getTickets()) {
			if( !(ticket.getHorario().plus(ticket.getPelicula().getDuracion()).isAfter(SucursalCine.getFechaActual())) ){
				ticketsCaducados.add(ticket);
			}
		}
		
		for (Ticket ticket : ticketsCaducados) {
			this.getTickets().remove(ticket);
		}
	}
	
	/**
	 * Description : Este método se encarga de verificar si el cliente posee algún ticket correspondiente a alguna de las películas en cartelera
	 * de la sede que se pasa como parámetro
	 * @param sucursalCineProceso : Este método recibe como parámetro la sede (De tipo SucursalCine) desde la cuál el cliente esta accediendo a nuestros
	 * servicios
	 * @return <b>boolean</b> : Este método retorna el resultado de la verifcación, con el fin de que el cliente solo pueda acceder a las salas de cine
	 * o a la sala de espera si este posee algún ticket válido.
	 * */
	public boolean disponibilidadTIcketParaSede(SucursalCine sucursalCineProceso) {
		for (Ticket ticket : this.getTickets()) {
			for (Pelicula peliculaCarteleraSede : sucursalCineProceso.getCartelera()) {
				if(ticket.getPelicula().equals(peliculaCarteleraSede)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Description : Este método se encarga de encontrar el género más visto por un cliente, para realizar este proceso, iteramos sobre su historial
	 * de películas, luego, obtenemos el género de cada una y alamacenamos las veces que se repite este género en un LinkedHashMap, por último,
	 * evaluamos cual género tiene más visualizaciones y se retorna este, en caso de coincidir en visualizaciones con otro género, retornamos el
	 * género más reciente.
	 * @return <b>String</b> : Este método retorna el género (De tipo String) con más visualizaciones.  
	 * */
	public String generoMasVisto() {
		LinkedHashMap<String, Integer> historialGenero = new LinkedHashMap<>();
		for(Pelicula pelicula : this.getHistorialDePeliculas()) {
			if (historialGenero.containsKey(pelicula.getGenero())) {
				historialGenero.put(pelicula.getGenero(), historialGenero.get(pelicula.getGenero()) + 1);
			}else {
				historialGenero.put(pelicula.getGenero(), 1);
			}
		}

		boolean firstEntry = true;
		String generoMasVisto = null;
		int valorGeneroMasVisto = 0;
		for(Map.Entry<String, Integer> entrada : historialGenero.entrySet() ) {
			String auxGeneroMasVisto = entrada.getKey();
			int auxValorGeneroMasVisto = entrada.getValue();
			
			if (firstEntry) {
				generoMasVisto = auxGeneroMasVisto;
				valorGeneroMasVisto =  auxValorGeneroMasVisto;
				firstEntry = false;
			}
			
			if(auxValorGeneroMasVisto >= valorGeneroMasVisto) {
				generoMasVisto = auxGeneroMasVisto;
			}

		}
		
		return generoMasVisto;
	}
	
	
	public void mostrarCodigosDescuento() {
		
		for (int i = 0; i < this.codigosDescuento.size(); i++) {
            System.out.println((i + 1) + ". " + this.codigosDescuento.get(i));
            
        }
		
		System.out.println((this.codigosDescuento.size()+1)+". Ninguno");
		System.out.println((this.codigosDescuento.size()+2)+". Salir");
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


	public ArrayList<Producto> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Producto> pedidos) {
		this.pedidos = pedidos;
	}

	public SucursalCine getCine() {
		return cine;
	}

	public void setCine(SucursalCine cine) {
		this.cine = cine;
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
	
	
	
	
	
	
	
	
}
