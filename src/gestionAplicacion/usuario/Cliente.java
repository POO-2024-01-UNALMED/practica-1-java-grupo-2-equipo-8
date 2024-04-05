package gestionAplicacion.usuario;
import java.util.ArrayList;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;

public class Cliente {
	
	private String nombre;
	private ArrayList<Pelicula> historialDePeliculas = new ArrayList<>();
	private Ticket ticket;
	private ArrayList<String> factura = new ArrayList<>();
	private String cuidad;
	private Membresia membresia;
	private long documento;
	private int duracionMembresiaDias;
	private TipoDeDocumento tipoDocumento;
	private TarjetaCinemar cuenta;
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private ArrayList<MetodoPago> metodosDePago = new ArrayList<>();
	private ArrayList<Bono> bonosCliente = new ArrayList<>();
	
	public Cliente(){}
	
	public Cliente(String nombre, ArrayList<Pelicula> historialDePeliculas, Ticket ticket, ArrayList<String> factura,
			String cuidad, Membresia membresia, long documento, int duracionMembresiaDias,
			TipoDeDocumento tipoDocumento, TarjetaCinemar cuenta, ArrayList<MetodoPago> metodosDePago,
			ArrayList<Bono> bonosCliente) {
		super();
		this.nombre = nombre;
		this.historialDePeliculas = historialDePeliculas;
		this.ticket = ticket;
		this.factura = factura;
		this.cuidad = cuidad;
		this.membresia = membresia;
		this.documento = documento;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipoDocumento = tipoDocumento;
		this.cuenta = cuenta;
		this.metodosDePago = metodosDePago;
		this.bonosCliente = bonosCliente;
	}
	
	public void obtenerclientePorCedula() {}
	public void revisarDatosCliente() {}
	public Ticket generarTicket() {return new Ticket();}
	public void editarCuenta() {}
	public void modificarMetodosDePago() {}
	
}
