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
	public SalaCine(){
		Pelicula.getSalasDeCine().add(this);
	}
	
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
	/**
	 * @Description : Este método se encarga de generar asientos para la sala de cine, facilitando el proceso de
	 * crear una sala de cine
	 * @return : Este método no retorna nada (void), solo actualiza los asientos de la sala de cine
	 * */
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
	/**
	 * @Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * asientos y su disponiblidad
	 * @return : (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda interactuar con la funcionalidad
	 * */
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
	
	/**
	 * @Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es false, la cambia a true, en caso de que sea true, la cambia a false.
	 * @params : (int fila): Index de la fila del asiento que queremos modificar;
	 * (int columna): Index de la columna del asiento que queremos modificar.
	 * @return : (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine
	 * */
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
