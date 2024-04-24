package gestionAplicacion.proyecciones;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Ticket;

public class SalaCine {
	
	private int numeroSala;
	private String tipoDeSala;
	private static String fecha;
	private Asiento[][] asientos;
	private Pelicula peliculaEnPresentacion;
	//private ArrayList<Pelicula> peliculas = new ArrayList<>(); Posiblemente sea innecesario
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private static String hora;
	private static String diaSemana;
	
	//Constructors
	public SalaCine(){
		Pelicula.getSalasDeCine().add(this);
	}
	public SalaCine(int nSala){
		this.numeroSala = nSala;
		Pelicula.getSalasDeCine().add(this);
	}
	public SalaCine(int numeroSala, String tipoDeSala, Asiento[][] asientos, Pelicula peliculaEnPresentacion, ArrayList<Ticket> ticketsCreados) {
		super();
		this.numeroSala = numeroSala;
		this.tipoDeSala = tipoDeSala;
		this.asientos = asientos;
		this.peliculaEnPresentacion = peliculaEnPresentacion;
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
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				DistribucionAsientosSalaDeCine[i][j] = new Asiento();
			}
		}
		this.asientos = DistribucionAsientosSalaDeCine;
	}
	
	/**
	 * @Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * asientos y su disponiblidad
	 * @return : (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda interactuar con la funcionalidad
	 * */
	public String mostrarAsientos() {
	    StringBuilder resultado = new StringBuilder("Asientos de Cine\n");
	    resultado.append("   ");
	   
	    // Agregar números de columnas
	    for (int i = 0; i < this.getAsientos().length; i++) {
	        resultado.append(String.format("%-4d", i + 1));
	    }
	    resultado.append("\n");

	    // Mostrar asientos
	    for (int i = 0; i < this.getAsientos().length; i++) {
	        resultado.append(String.format("%-2d ", i + 1));
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	            resultado.append("[");
	            resultado.append(this.getAsientos()[i][j].isDisponibilidad() ? "O" : "X");
	            resultado.append("] ");
	        }
	        resultado.append("\n");
	    }

	    return resultado.toString();
	}
	/**
	 * @Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es true la cambia a false, se usa para separar un asiento luego de ser comprado
	 * @param fila : Index de la fila del asiento que queremos modificar;
	 * @param columna : Index de la columna del asiento que queremos modificar.
	 * @return : (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine
	 * */
	public void cambiarDisponibilidadAsientoLibre(int fila, int columna) {
		if (this.getAsientos()[fila - 1][columna - 1].isDisponibilidad()) {
			this.getAsientos()[fila - 1][columna - 1].setDisponibilidad(false);	
		}
	}
	
	/**
	 * @Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es false la cambia a true, es especialmente útil para actualizar la sala de cine
	 * @param fila : Index de la fila del asiento que queremos modificar;
	 * @param columna : Index de la columna del asiento que queremos modificar.
	 * @return : (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine
	 * */
	public void cambiarDisponibilidadAsientoOcupadoParaLibre(int fila, int columna) {
		if (this.getAsientos()[fila - 1][columna - 1].isDisponibilidad()) {
		}else {
			this.getAsientos()[fila - 1][columna - 1].setDisponibilidad(true);	
		}	
	}
	
	public boolean verificarFactura() {return true;}
	public boolean verificarTicket(Cliente cliente) {
		return true;
	}
	//public void destriurTicket() {} Este proceso se puede hacer directamente en la verificación
	
	/***/
	public void actualizarPeliculaEnPresentacion() {
		String day = null;
		String hour = null;
		
		//Actualizamos la película
		for (Pelicula pelicula : Pelicula.getCartelera()) {
			if (pelicula.getNumeroDeSala() == this.getNumeroSala()) {
				for (ArrayList<String> horario : pelicula.getHorarios().keySet()) {
					if (horario.get(0).equals(SalaCine.getDiaSemana())& horario.get(1).equals(SalaCine.getHora())){
						this.setPeliculaEnPresentacion(pelicula);
						day = horario.get(0);
						hour = horario.get(1);
						break;
					}
				}
				break;
			}
		}
		
		//Preparamos los asientos para ser actualizados
		for (int i = 0; i < this.getAsientos().length; i++) {
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	            this.cambiarDisponibilidadAsientoOcupadoParaLibre(i+1, j+1);
	        }
	    }
		
		//Actualizamos los asientos de la sala de cine
		for (int i = 0; i < this.getAsientos().length; i++) {
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	        	if (!this.getPeliculaEnPresentacion().isDisponibilidadAsientoSalaVirtual(day, hour, i+1, j+1))
	            this.cambiarDisponibilidadAsientoLibre(i+1, j+1);
	        }
	    }
		
	}

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

	public ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}

	public void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		this.ticketsCreados = ticketsCreados;
	}

	public static String getHora() {
		return hora;
	}

	public static void setHora(String hora) {
		SalaCine.hora = hora;
	}

	public static String getDiaSemana() {
		return diaSemana;
	}

	public static void setDiaSemana(String diaSemana) {
		SalaCine.diaSemana = diaSemana;
	}
	
}
