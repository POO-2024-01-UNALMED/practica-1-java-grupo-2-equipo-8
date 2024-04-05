package gestionAplicacion.proyecciones;
import java.util.ArrayList;
import gestionAplicacion.usuario.Ticket;

public class SalaCine {
	
	private int numeroSala;
	private String tipoDeSala;
	private static String fecha;
	private ArrayList<Asiento> asientos = new ArrayList<>();
	private Pelicula peliculaEnPresentacion;
	private ArrayList<Pelicula> peliculas = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	
	public SalaCine(){}
	
	public SalaCine(int numeroSala, String tipoDeSala, ArrayList<Asiento> asientos, Pelicula peliculaEnPresentacion,
			ArrayList<Pelicula> peliculas, ArrayList<Ticket> ticketsCreados) {
		super();
		this.numeroSala = numeroSala;
		this.tipoDeSala = tipoDeSala;
		this.asientos = asientos;
		this.peliculaEnPresentacion = peliculaEnPresentacion;
		this.peliculas = peliculas;
		this.ticketsCreados = ticketsCreados;
	}
	private boolean verificarTicket() {return true;}
	private void destriurTicket() {}
	private void visualizarAsientosDisponibles() {}
	private void actualizarPeliculaEnPresentacion() {}
	
	
}
