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
		this.setBonosCliente(bonosCliente);
		clientes.add(this);
	}
	
	//Metodos
	public void obtenerclientePorCedula() {}
	
	
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

	public ArrayList<Bono> getBonosCliente() {
		return bonosCliente;
	}

	public void setBonosCliente(ArrayList<Bono> bonosCliente) {
		this.bonosCliente = bonosCliente;
	}
	
	
	
	
	
	
	
}
