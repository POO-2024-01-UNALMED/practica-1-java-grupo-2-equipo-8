package gestionAplicacion.usuario;
import java.util.ArrayList;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;

public class Cliente {
	
	//Atributos
	private String nombre;
	private ArrayList<Pelicula> historialDePeliculas = new ArrayList<>();
	private Ticket ticket;
	private ArrayList<String> factura = new ArrayList<>();
	private int edad;
	private Membresia membresia;
	private long documento;
	private int duracionMembresiaDias;
	private TipoDeDocumento tipoDocumento;
	private TarjetaCinemar cuenta;
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<MetodoPago> metodosDePago = new ArrayList<>();
	private ArrayList<Bono> bonosCliente = new ArrayList<>();
	
	
	//Constructores
	public Cliente(){}
	
	public Cliente(String nombre, int edad, long documento, TipoDeDocumento tipoDocumento) {
		this.nombre = nombre;
		this.edad = edad;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		clientes.add(this);
	}

	public Cliente(String nombre, ArrayList<Pelicula> historialDePeliculas, Ticket ticket, ArrayList<String> factura,
			int edad, Membresia membresia, long documento, int duracionMembresiaDias,
			TipoDeDocumento tipoDocumento, TarjetaCinemar cuenta, ArrayList<MetodoPago> metodosDePago,
			ArrayList<Bono> bonosCliente) {
		super();
		this.nombre = nombre;
		this.historialDePeliculas = historialDePeliculas;
		this.ticket = ticket;
		this.factura = factura;
		this.edad = edad;
		this.membresia = membresia;
		this.documento = documento;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipoDocumento = tipoDocumento;
		this.cuenta = cuenta;
		this.metodosDePago = metodosDePago;
		this.bonosCliente = bonosCliente;
		clientes.add(this);
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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public ArrayList<String> getFactura() {
		return factura;
	}

	public void setFactura(ArrayList<String> factura) {
		this.factura = factura;
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

	public int getDuracionMembresiaDias() {
		return duracionMembresiaDias;
	}

	public void setDuracionMembresiaDias(int duracionMembresiaDias) {
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

	public ArrayList<Bono> getBonosCliente() {
		return bonosCliente;
	}

	public void setBonosCliente(ArrayList<Bono> bonosCliente) {
		this.bonosCliente = bonosCliente;
	}
	
	
	
	
	
	
}
