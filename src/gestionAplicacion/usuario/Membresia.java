package gestionAplicacion.usuario;
import java.util.ArrayList;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;

public class Membresia {
	
	private String nombre;
	private int categoria;
	private static ArrayList<Membresia> tiposDeMembresia = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private double descuentoAsociado;
	private int valorSuscripcionMensual;
	private int valorDescuentoAplicado;
	private int duracionMembresiaDias;
	private int tipo;
	
	public Membresia(){}
	
	public Membresia(String nombre, int categoria, ArrayList<Cliente> clientes, double descuentoAsociado,
			int valorSuscripcionMensual, int valorDescuentoAplicado, int duracionMembresiaDias, int tipo) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.clientes = clientes;
		this.descuentoAsociado = descuentoAsociado;
		this.valorSuscripcionMensual = valorSuscripcionMensual;
		this.valorDescuentoAplicado = valorDescuentoAplicado;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipo = tipo;
	}
	public void crearBonoRecargaTarjetaCinemar() {}
	public boolean verificarMembresia() {return true;}
	public double asignarDescuento() {return 2.73;} 
	public boolean actualizarSuscripcion() {return true;}
	public void adquirirSuscripcion() {}
	public ArrayList<Pelicula> recomendacionPeliculas(){return new ArrayList<>();}
	public double aumentaLimiteDePago() {return 2.73;}
	public void recargarRegaloTarjetaCinemar() {}
	
}
