package gestionAplicacion.proyecciones;
import java.util.ArrayList;
import gestionAplicacion.usuario.Ticket;

public class SalaCine {
	
	private int numeroSala;
	private String tipoDeSala;
	private static String fecha;
	private Asiento[][] asientos;
	private Pelicula peliculaEnPresentacion;
	private ArrayList<Pelicula> peliculas = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	
	
	//Constructors
	public SalaCine(){}
	
	public SalaCine(int numeroSala, String tipoDeSala, Asiento[][] asientos, Pelicula peliculaEnPresentacion,
			ArrayList<Pelicula> peliculas, ArrayList<Ticket> ticketsCreados) {
		super();
		this.numeroSala = numeroSala;
		this.tipoDeSala = tipoDeSala;
		this.asientos = asientos;
		this.peliculaEnPresentacion = peliculaEnPresentacion;
		this.peliculas = peliculas;
		this.ticketsCreados = ticketsCreados;
	}

	//Methods
	public void crearAsientosSalaDeCine() {
		Asiento[][] DistribucionAsientosSalaDeCine = new Asiento[8][8];
		int k = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				DistribucionAsientosSalaDeCine[i][j] = new Asiento();
			}
		}
		
		this.asientos = DistribucionAsientosSalaDeCine;
	}
	
	//Mejorar forma de mostrar los asientos (cambiar por "X" en uso y "O" libres)
	public String mostrarAsientos() {
		String resultado = "Asientos de Cine" + "\n" + "	1      2      3      4      5      6      7      8" + "\n";
		for (int i = 0; i < this.getAsientos().length; i++) {
			resultado = resultado + i + "    ";
			for (int j = 0; j < this.getAsientos().length; j++) {
				resultado = resultado + "[" + this.getAsientos()[i][j].isDisponibilidad() + "]" + " ";
			}
			resultado = resultado + "\n";
		}
		return resultado;
	}
	
	public void cambiarDisponibilidadAsiento(int fila, int columna) {
		if (this.getAsientos()[fila][columna].isDisponibilidad()) {
			this.getAsientos()[fila][columna].setDisponibilidad(false);	
		}else {
			this.getAsientos()[fila][columna].setDisponibilidad(true);	
		}	
	}
	
	public boolean verificarTicket() {return true;}
	public void destriurTicket() {}
	public void visualizarAsientosSalaDeCine() {
		
	}
	public void actualizarPeliculaEnPresentacion() {}

	// Getters and Setters
	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public String getTipoDeSala() {
		return tipoDeSala;
	}

	public void setTipoDeSala(String tipoDeSala) {
		this.tipoDeSala = tipoDeSala;
	}

	public static String getFecha() {
		return fecha;
	}

	public static void setFecha(String fecha) {
		SalaCine.fecha = fecha;
	}

	
	public Asiento[][] getAsientos() {
		return asientos;
	}

	public void setAsientos(Asiento[][] asientos) {
		this.asientos = asientos;
	}

	public Pelicula getPeliculaEnPresentacion() {
		return peliculaEnPresentacion;
	}

	public void setPeliculaEnPresentacion(Pelicula peliculaEnPresentacion) {
		this.peliculaEnPresentacion = peliculaEnPresentacion;
	}

	public ArrayList<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}

	public void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		this.ticketsCreados = ticketsCreados;
	}
	
	
	
	
	
	
}
