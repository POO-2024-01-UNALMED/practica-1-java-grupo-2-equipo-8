package gestionAplicacion.usuario;
import gestionAplicacion.proyecciones.*;

public class Ticket {

	private Cliente dueno;
	private SalaCine salaDeCine;
	private Asiento asiento;
	private double precio;
	private Pelicula pelicula;
	private String horario;
	private int idPelicula;
	
	public Ticket(){}
	
	public Ticket(Cliente dueno, SalaCine salaDeCine, Asiento asiento, double precio, Pelicula pelicula, String horario,
			int idPelicula) {
		super();
		this.dueno = dueno;
		this.salaDeCine = salaDeCine;
		this.asiento = asiento;
		this.precio = precio;
		this.pelicula = pelicula;
		this.horario = horario;
		this.idPelicula = idPelicula;
	}
	private boolean peliculaMitadPrecio() {return true;}
	private void asignarTicket() {}
	private void descuentoDia() {}
	private void clienteSuertudo() {}
	
}
