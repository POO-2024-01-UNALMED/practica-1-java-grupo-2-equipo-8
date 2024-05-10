package gestionAplicacion.proyecciones;

import java.time.LocalDateTime;
import java.util.ArrayList;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Ticket;

public class SalaCine {
	
	private int numeroSala;
	private String tipoDeSala;
	private LocalDateTime horarioPeliculaEnPresentacion;
	private Asiento[][] asientos;
	private Pelicula peliculaEnPresentacion;
	private static ArrayList<SalaCine> salasCine = new ArrayList<>();
	private static ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private SucursalCine ubicacionSede;
	
	//Constructors
	public SalaCine(){
		SalaCine.getSalasCine().add(this);
	}
	
	public SalaCine(int nSala, String tipoDeSala, SucursalCine ubicacionSede){
		this();
		this.numeroSala = nSala;
		this.tipoDeSala = tipoDeSala;
		this.ubicacionSede = ubicacionSede;
	}
	
	public SalaCine(int numeroSala, String tipoDeSala, Asiento[][] asientos, Pelicula peliculaEnPresentacion, ArrayList<Ticket> ticketsCreados) {
		this.numeroSala = numeroSala;
		this.tipoDeSala = tipoDeSala;
		this.asientos = asientos;
		this.peliculaEnPresentacion = peliculaEnPresentacion;
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
				DistribucionAsientosSalaDeCine[i][j] = new Asiento(i,j);
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
	    resultado.append("\n(Fila: distribución horizontal de asientos)\n(Columna: distribución vertical de asientos)\n(Número de asiento: Intersección fila y columna)\n");
	    resultado.append("  --------------------------------- \n              Pantalla\n");
	    resultado.append("    ");
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
	 * si su disponibilidad es true la cambia a false, se usa para separar un asiento luego de ser comprado
	 * @param numeroAsiento : Este método recibe como parámetro el numero del asiento seleccionado por el cliente
	 * durante el proceso de la funcionalidad
	 * @return : (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine
	 * */
	public void cambiarDisponibilidadAsientoLibre(String numeroAsiento) {
		for (Asiento[] asientos : this.getAsientos()) {
			for (Asiento asiento : asientos) {
				if (asiento.getNumeroAsiento().equals(numeroAsiento)) {
					asiento.setDisponibilidad(false);
				}
			}
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
	
	/**
	 * Description: Este método se encarga de verificar si una persona tiene al menos un ticket registrado en su array que cumpla los
	 * siguientes criterios al mismo tiempo: 
	 * 1. La película asociada a este ticket se encuentra coincide con la peliculaEnPresentacion de la SalaDeCine
	 * 2. La fecha actual de SalaCine es anterior a la fecha en que finaliza la película
	 * 3. La sala de cine asociada al ticket es la misma que la que ejecuta este método
	 * @param cliente : Este método solicita al cliente que va a ingresar a la SalaDeCine
	 * @return boolean : Este método se encarga de retornar un boolean que será el resultado del proceso de verificación de entrada a la salaDeCine
	 * */
	public boolean verificarTicket(Cliente cliente) {
		
		boolean verificacion = false;
		boolean verificacionPelicula = false;
		boolean verificacionSalaCine = false;
		boolean verificacionHorario = false;
		Ticket ticketVerificado = null;
		
		//Verificamos si el atributo película de alguno de los tickets que tiene el cliente coinicide con la película en presentación
		//Verificamos si el atributo salaDeCine de ticket tiene asociado esta sala de cine
		//Verificamos si la fecha de actual no excede a la fecha en la que se presentaba la película más la duración de la misma
		for (Ticket ticket : cliente.getTickets()) {
			
			try {
				verificacionSalaCine = ticket.getSalaDeCine().equals(this);
				
				verificacionPelicula = ticket.getPelicula().equals(this.peliculaEnPresentacion);
				
				verificacionHorario = ticket.getHorario().equals(this.getHorarioPeliculaEnPresentacion()) &
				SucursalCine.getFechaActual().isBefore(this.getHorarioPeliculaEnPresentacion().plus( this.getPeliculaEnPresentacion().getDuracion() ) ); 
				
			}catch(NullPointerException e) {
				verificacion = false;
			}
			
			verificacion = verificacionPelicula & verificacionHorario & verificacionSalaCine;
			if (verificacion) {
				ticketVerificado = ticket;
				break;
			}
		}
		
		//Eliminamos la referencia del ticket verificado, en caso de que la verificación sea correcta del cliente (Lo destruimos)
		//Añadimos la película vista al historial de películas del cliente
		if (verificacion) {
			cliente.getHistorialDePeliculas().add(ticketVerificado.getPelicula());
			cliente.getTickets().remove(ticketVerificado);
		}
		
		//Retornamos el resultado de la verificación
		return verificacion;
	}
	
	/**
	 * Description: Este método se encarga actualizar la película en presentación según si la película coincide con el número de sala y el formato 
	 * y luego si el día y la hora actual es menor o igual al horario de la película que cumplió los dos criterios anteriores, 
	 * una vez hecho esto, limpiamos los asientos de la sala de cine, cambiando su disponibilidad a libre, y
	 * por último actualizamos la información de la disponibilidad de los asientos, tomando la información del array de la sala virtual que 
	 * coincidió en fecha y hora de la película en presentación, además modificamos el atributo horarioPeliculaEnPresentacion de la salaDeCine
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine), con el fin de actualizar, únicamente, las salas de cine
	 * con las películas propias de esta sucursal.
	 * @return (void): Este método no retorna nada, solo actualiza los asientos de la sala de cine y de la película en presentación
	 * */
	public void actualizarPeliculasEnPresentacion(SucursalCine sucursalCine) {
		Pelicula peliculaPresentacion = null;
		LocalDateTime horarioPresentacion = null;
		//Actualizamos la película
		try {
			for (Pelicula pelicula : sucursalCine.getCartelera()) {
				if ( (pelicula.getNumeroDeSala() == this.getNumeroSala() ) & (pelicula.getTipoDeFormato().equals(this.getTipoDeSala())) ) {
					for (LocalDateTime horario : pelicula.getHorarios().keySet()) {
						if (!(horario.isAfter(SucursalCine.getFechaActual()))){
							horarioPresentacion = horario;
							this.setPeliculaEnPresentacion(pelicula);
							peliculaPresentacion = this.getPeliculaEnPresentacion();
							this.setHorarioPeliculaEnPresentacion(horario);
							break;
						}
					}
				continue;
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
		        	if (!this.getPeliculaEnPresentacion().isDisponibilidadAsientoSalaVirtual(horarioPresentacion, i+1, j+1)) {
		            this.cambiarDisponibilidadAsientoLibre(i+1, j+1);
		        	}
		        }
		    }
			
			//Eliminamos la sala de cine virtual
			this.getPeliculaEnPresentacion().getHorarios().remove(horarioPresentacion);
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
	
	/**
	 * Description: Este método se encarga de validar el asiento seleccionado por el cliente una vez ingresa
	 * a la sala de cine, como última interacción para ingresar a la sala de cine
	 * @param numeroAsiento : Este método se encarga de recibir un número de asiento dado por el cliente
	 * en el formato x+ "-" + y (String)
	 * @return <b>boolean</b> : Este método se encarga de rotornar un boolean luego de verificar que el número de
	 * asiento pasado como parametro es igual al número de asiento de algún asiento en la sala
	 * */
	public boolean validarAsiento(String numeroAsiento) {
		boolean validacion = false;
		
		for(Asiento[] filaAsiento : this.getAsientos()) {
			for (Asiento asiento : filaAsiento) {
				validacion = (asiento.getNumeroAsiento().equals(numeroAsiento)) ? true : false;
			}
		}
		
		return validacion;
	}
	
	/**
	 * Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * asientos y su número de asiento
	 * @return : (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda visualizar de mejor forma el proceso de entrada a la sala de cine
	 * */
	public String mostrarAsientosParaPantalla() {
		StringBuilder resultado = new StringBuilder();
	    resultado.append("  -------------------------------------------------------------- \n                           Pantalla\n");
	    resultado.append("    ");
	    resultado.append("\n");
	    resultado.append("                       Asientos de Sala\n");
	    // Mostrar asientos
	    for (int i = 0; i < this.getAsientos().length; i++) {
	    	resultado.append("         ");
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	            resultado.append("[");
	            resultado.append(this.getAsientos()[i][j].getNumeroAsiento());
	            resultado.append("] ");
	        }
	        resultado.append("\n");
	    }

	    return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * la pantalla de la sala de cine con un pequeño mensaje, además se llama al método mostrarAsientosParaPantalla
	 * @return : (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda visualizar de mejor forma el proceso de entrada a la sala de cine
	 * */
	public String mostrarPantallaSalaCine () {
		String resultado = "  -------------------------------------------------------------- ";
		
		for (int i = 0; i < 6; i++) {
			resultado = resultado + "\n" + " |                      					|";
		}
		
		resultado = resultado + "\n |             Programación Orientada a objetos			|\n";
		
		for (int i = 0; i < 6; i++) {
			resultado = resultado + " |                      					|\n";
		}
		
		resultado = resultado + this.mostrarAsientosParaPantalla();
		return resultado;
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
	
	public void setHorarioPeliculaEnPresentacion(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}

	public static ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}

	public static void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		SalaCine.ticketsCreados = ticketsCreados;
	}

	public SucursalCine getUbicacionSede() {
		return ubicacionSede;
	}

	public void setUbicacionSede(SucursalCine ubicacionSede) {
		this.ubicacionSede = ubicacionSede;
	}

	
	
	
}
