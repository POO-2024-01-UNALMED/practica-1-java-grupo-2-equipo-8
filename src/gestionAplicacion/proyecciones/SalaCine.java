package gestionAplicacion.proyecciones;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Ticket;

public class SalaCine {
	
	private int numeroSala;
	private String tipoDeSala;
	private static LocalDateTime fecha;
	private LocalDateTime horarioPeliculaEnPresentacion;
	private Duration DuracionPeliculaEnPresentacion;
	private Asiento[][] asientos;
	private Pelicula peliculaEnPresentacion;
	//private ArrayList<Pelicula> peliculas = new ArrayList<>(); Posiblemente sea innecesario
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private static ArrayList<SalaCine> salasCine = new ArrayList<>();
	
	//Constructors
	public SalaCine(){
		Pelicula.getSalasDeCine().add(this);
		SalaCine.getSalasCine().add(this);
	}
	public SalaCine(int nSala, String tipoDeSala){
		this();
		this.numeroSala = nSala;
		this.tipoDeSala = tipoDeSala;
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
	 * Description : Este método se encarga de generar asientos para la sala de cine, facilitando el proceso de
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
	 * Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
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
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
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
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es false la cambia a true, es especialmente útil para actualizar la sala de cine
	 * @param fila : Index de la fila del asiento que queremos modificar;
	 * @param columna : Index de la columna del asiento que queremos modificar.
	 * @return (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine
	 * */
	public void cambiarDisponibilidadAsientoOcupadoParaLibre(int fila, int columna) {
		if (this.getAsientos()[fila - 1][columna - 1].isDisponibilidad()) {
		}else {
			this.getAsientos()[fila - 1][columna - 1].setDisponibilidad(true);	
		}	
	}
	
	
	// public boolean verificarFactura() {return true;} ¿Para volver a entrar a la Sala de Cine?
	
	/**
	 * Description: Este método se encarga de verificar si una persona tiene al menos un ticket registrado en su array que cumpla los
	 * siguientes criterios al mismo tiempo: 
	 * 1. Se encuentra en el arrayList de ticketsCreados de la salaDeCine
	 * 2. La película asociada a este ticket se encuentra coincide con la peliculaEnPresentacion de la SalaDeCine
	 * 3. La fecha actual de SalaCine es anterior a la fecha en que finaliza la película
	 * 4. La sala de cine asociada al ticket es la misma que la que ejecuta este método
	 * @param cliente : Este método solicita al cliente que va a ingresar a la SalaDeCine
	  * @return boolean : Este método se encarga de retornar un boolean que será el resultado del proceso de verificación
	 * */
	public boolean verificarTicket(Cliente cliente) {
		
		boolean verificacion = false;
		boolean verificacionPelicula = false;
		boolean verificacionHorario = false;
		Ticket ticketVerificado = null;
		
		//Verificamos si el atributo película de alguno de los tickets que tiene el cliente coinicide con la película en presentación
		//Verificamos si el ticket se encuetra en el arrayList de tickets creados de esta sala de cine
		//Veriificamos si el ticket tiene asociada la salaDeCine que ejecuta este método
		//Verificamos si la fecha de actual no excede a la fecha en la que se presentaba la película más la duración de la misma
		for (Ticket ticket : cliente.getTickets()) {
			verificacionPelicula = (ticket.getPelicula().equals(this.peliculaEnPresentacion) & this.getTicketsCreados().contains(ticket) ) & ( ticket.getSalaDeCine().equals(this)) ;
			try {
				verificacionHorario = SalaCine.getFecha().isBefore(this.getHorarioPeliculaEnPresentacion().plus(this.getDuracionPeliculaEnPresentacion())); 
			}catch(NullPointerException e) {
				verificacionHorario = false;
			}
			verificacion = verificacionPelicula & verificacionHorario;
			if (verificacion) {
				ticketVerificado = ticket;
				break;
			}
		}
		
		//Eliminamos la referencia del ticket verificado, en caso de que la verificación sea correcta del cliente y de la sala de cine (Lo destruimos)
		//Añadimos la película vista al historial de películas del cliente
		if (verificacion) {
			cliente.getTickets().remove(ticketVerficado);
			this.getTicketsCreados().remove(ticketVerficado);
		}
		
		//Retornamos el resultado de la verificación
		return verificacion;
	}
	
	//Podría mejorar este método implementando un try en caso de que no haya ninguna película para actualizar, con esto podría implementar
	//Un método estático que actualice a todas las salas de cine directamente.
	/**
	 * Description: Este método se encarga actualizar la película en presentación según si la película coincide con el número de sala y el formato y luego
	 * respecto al día y la hora actual, una vez hecho esto, limpiamos los asientos de la sala de cine, cambiando su disponibilidad a libre, y
	 * por último actualizamos la información de la disponibilidad de los asientos, tomando la información del array de la sala virtual que 
	 * coincidió en fecha y hora de la película en presentación, además modificamos el atributo horarioPeliculaEnPresentacion de la salaDeCine
	 * @return (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine y de la película en presentación
	 * */
	public void actualizarPeliculasEnPresentacion() {
		Pelicula peliculaPresentacion = null;
		//Actualizamos la película
		try {
			for (Pelicula pelicula : Pelicula.getCartelera()) {
				if ( (pelicula.getNumeroDeSala() == this.getNumeroSala() ) && (pelicula.getTipoDeFormato().equals(this.getTipoDeSala())) ) {
					for (LocalDateTime horario : pelicula.getHorarios().keySet()) {
						if (horario.equals(SalaCine.getFecha())){
							this.setPeliculaEnPresentacion(pelicula);
							peliculaPresentacion = this.getPeliculaEnPresentacion();
							this.setHorarioPeliculaEnPresentacion(horario);
							break;
						}
					}
					break;
				}
			}
		}catch(NullPointerException e) {
			//Continua con el siguiente proceso
		}
		
		//Ejecutamos esta operacion en caso de que se cambie la película en presentación
		if (peliculaPresentacion != null) {
			//Preparamos los asientos para ser actualizados
			for (int i = 0; i < this.getAsientos().length; i++) {
		        for (int j = 0; j < this.getAsientos()[i].length; j++) {
		            this.cambiarDisponibilidadAsientoOcupadoParaLibre(i+1, j+1);
		        }
		    }
			
			//Actualizamos los asientos de la sala de cine
			for (int i = 0; i < this.getAsientos().length; i++) {
		        for (int j = 0; j < this.getAsientos()[i].length; j++) {
		        	if (!this.getPeliculaEnPresentacion().isDisponibilidadAsientoSalaVirtual(SalaCine.getFecha(), i+1, j+1)) {
		            this.cambiarDisponibilidadAsientoLibre(i+1, j+1);
		        	}
		        }
		    }
			
			//Eliminamos la sala de cine virtual
			this.getPeliculaEnPresentacion().getHorarios().remove(SalaCine.getFecha());
		}
		
	}
	
	/**
	 * Description : Este método se encarga de retornar la disponibilidad de un asiento dada su fila y su columna
	 * @param fila : Este método recibe la fila del asiento a consultar
	 * @param columna: Este método recibe la columna del asiento a consultar
	 * @return <b>boolean</b> : Este método retorna la disponibilidad del asiento consultado
	 * */
	public boolean isDisponibilidadAsiento(int fila, int columna) {
		return this.getAsientos()[fila - 1][columna - 1].isDisponibilidad();
		
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

	public static LocalDateTime getFecha() {
		return fecha;
	}

	public static void setFecha(LocalDateTime fecha) {
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
		this.setDuracionPeliculaEnPresentacion(peliculaEnPresentacion.getDuracion());
	}

	public ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}

	public void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		this.ticketsCreados = ticketsCreados;
	}
	
	public static ArrayList<SalaCine> getSalasCine() {
		return salasCine;
	}
	
	public static void setSalasCine(ArrayList<SalaCine> salasCine) {
		SalaCine.salasCine = salasCine;
	}
	
	public LocalDateTime getHorarioPeliculaEnPresentacion() {
		return horarioPeliculaEnPresentacion;
	}
	
	public void setHorarioPelicula(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}
	
	public Duration getDuracionPeliculaEnPresentacion() {
		return DuracionPeliculaEnPresentacion;
	}
	
	public void setDuracionPeliculaEnPresentacion(Duration duracionPeliculaEnPresentacion) {
		DuracionPeliculaEnPresentacion = duracionPeliculaEnPresentacion;
	}
	
	public void setHorarioPeliculaEnPresentacion(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}
	
	
	
}
