package gestionAplicacion.proyecciones;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Ticket;

public class SalaCine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int numeroSala;
	private String tipoDeSala;
	private LocalDateTime horarioPeliculaEnPresentacion;
	private Asiento[][] asientos;
	private Pelicula peliculaEnPresentacion;
	private SucursalCine ubicacionSede;
	private boolean horariosPresentacionDia;
	
	//Constructors
	public SalaCine(int nSala, String tipoDeSala, SucursalCine ubicacionSede){
		this.numeroSala = nSala;
		this.tipoDeSala = tipoDeSala;
		this.ubicacionSede = ubicacionSede;
		ubicacionSede.getSalasDeCine().add(this);
		this.asientos = this.crearAsientosSalaDeCine();
		this.horariosPresentacionDia = true;
	}

	//Methods
	/**
	 * Description : Este método se encarga de generar asientos para la sala de cine, facilitando el proceso de crear una sala de cine
	 * */
	public Asiento[][] crearAsientosSalaDeCine() {
		Asiento[][] DistribucionAsientosSalaDeCine = new Asiento[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				DistribucionAsientosSalaDeCine[i][j] = new Asiento(i,j);
			}
		}
		return DistribucionAsientosSalaDeCine;
	}
	
	/**
	 * Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * asientos y su disponiblidad
	 * @return <b>String</b> : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda interactuar con la funcionalidad
	 * */
	public String mostrarAsientos() {
		StringBuilder resultado = new StringBuilder("\n");
		
		resultado.append("Asientos de Cine\n");
	    resultado.append("\n(Fila: distribución horizontal de asientos)\n(Columna: distribución vertical de asientos)\n(Número de asiento: Intersección fila y columna)\n");
	    resultado.append("  --------------------------------- \n              Pantalla\n");
	    resultado.append("    ");
	    
	    // Agregar números de columnas
	    for (int i = 0; i < this.asientos[0].length; i++) {
	        resultado.append(String.format("%-4d", i + 1));
	    }
	    resultado.append("\n");

	    // Mostrar asientos
	    for (int i = 0; i < this.asientos.length; i++) {
	        resultado.append(String.format("%-2d ", i + 1));
	        for (int j = 0; j < this.asientos[i].length; j++) {
	            resultado.append("[");
	            resultado.append(this.asientos[i][j].isDisponibilidad() ? "O" : "X");
	            resultado.append("] ");
	        }
	        resultado.append("\n");
	    }

	    return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es true la cambia a false, se usa para separar un asiento luego de ser comprado.
	 * @param numeroAsiento : Este método recibe como parámetro el numero del asiento seleccionado por el cliente
	 * (De tipo String) durante el proceso de la funcionalidad 1.
	 * */
	public void cambiarDisponibilidadAsientoLibre(String numeroAsiento) {
		for (Asiento[] asientos : this.asientos) {
			for (Asiento asiento : asientos) {
				if (asiento.getNumeroAsiento().equals(numeroAsiento)) {
					asiento.setDisponibilidad(false);
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es true la cambia a false, se usa para cambiar la disponibilidad de un asiento
	 * para actualizar la sala en base a la información de la sala virtual 
	 * (En el método actualizarPeliculaEnPresentacion()).
	 * @param fila : Índice de la fila del asiento que queremos modificar (De tipo Integer).
	 * @param columna : Índice de la columna del asiento que queremos modificar (De tipo Integer).
	 * */
	public void cambiarDisponibilidadAsientoLibre(int fila, int columna) {
		if (this.asientos[fila - 1][columna - 1].isDisponibilidad()) {
			this.asientos[fila - 1][columna - 1].setDisponibilidad(false);	
		}
	}
	
	/**
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es false la cambia a true, es especialmente útil para preparar la sala de cine
	 * para presentar una nueva película (En el método actualizarPeliculaEnPresentacion()).
	 * @param fila : Índice de la fila del asiento que queremos modificar (De tipo Integer).
	 * @param columna : Índice de la columna del asiento que queremos modificar(De tipo Integer).
	 * */
	public void cambiarDisponibilidadAsientoOcupadoParaLibre(int fila, int columna) {
		if (!this.asientos[fila - 1][columna - 1].isDisponibilidad()) {
			this.asientos[fila - 1][columna - 1].setDisponibilidad(true);
		}
	}
	
	/**
	 * Description : Este método se encarga de generar un listado de la salas de cine con información relevante de estas,
	 * con el fin de que el usuario elija una de las opciones disponibles para ingresar.
	 * @param salasDeCine : Este método recibe como parámetro un listado de salas de cine disponibles (De tipo ArrayList<SalaCine>)
	 * @return <b>String</b>: Retorna la lista de las salas de cine disponibles.
	 * */
	public static String mostrarSalaCine(ArrayList<SalaCine> salasDeCine) {
		
		StringBuilder resultado = new StringBuilder();
		int i = 1;
		
		for (SalaCine salaDeCine : salasDeCine) {
			
			resultado.append("\n" + i + ". Número sala de cine: " + salaDeCine.numeroSala 
			+ "; Formato de sala de cine : " + salaDeCine.tipoDeSala
			+ "; Película en presentación : " + salaDeCine.peliculaEnPresentacion.getNombre()
			+ "; Horario película : " + salaDeCine.horarioPeliculaEnPresentacion);
			i++;
			
		}
		
		return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga de filtrar las salas de cine según si su película aún se encuentra en presentación,
	 * para esto verifica que el horario de la película en presentación más su duración no sea menor a la hora actual.
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) en donde se realiza la busqueda desde sus salas de cine
	 * @return  <b>ArrayList<SalaCine></b> : Este método retorna las salas de cine, (De tipo ArrayList<SalaCine>),
	 * que aún tienen su película en presentación, con el fin de ser las únicas que serán mostradas en pantalla durante el proceso de la funcionalidad 1. 
	 * */
	public static ArrayList<SalaCine> filtrarSalasDeCine(SucursalCine sucursalCine) {
		ArrayList<SalaCine> salasDeCineDisponibles = new ArrayList<>();
		
		for ( SalaCine salaDeCine : sucursalCine.getSalasDeCine() ) {
			//Se usa try en caso de que en las salas de cine no se haya setteado una película en presentación, por lo tanto, 
			// ni tampoco un horario en presentacion en estos momentos y devolver un array vacío, para continuar con el proceso.
			try {
				if (salaDeCine.horarioPeliculaEnPresentacion.plus(salaDeCine.peliculaEnPresentacion.getDuracion()).isAfter(SucursalCine.getFechaActual())) {
					salasDeCineDisponibles.add(salaDeCine);
				}
			}catch(NullPointerException e) {
				continue;
			}
		}
		
		return salasDeCineDisponibles;
	}
	
	/**
	 * Description: Este método se encarga de verificar si una persona tiene al menos un ticket registrado en su array que cumpla los
	 * siguientes criterios al mismo tiempo: 
	 * 1. La película asociada a este ticket coincide con la peliculaEnPresentacion de la SalaDeCine.
	 * 2. La fecha actual es anterior a la fecha en que finaliza la película.
	 * 3. La sala de cine asociada al ticket es la misma que la sala de cine que ejecuta este método.
	 * @param cliente : Este método solicita al cliente (De tipo cliente) que va a ingresar a la SalaDeCine.
	 * @return <b>boolean</b> : Este método se encarga de retornar un boolean que será el resultado del proceso de verificación de entrada a la salaDeCine.
	 * */
	public boolean verificarTicket(Cliente cliente) {
		
		boolean verificacionIngresoASala = false;
		boolean verificacionPelicula = false;
		boolean verificacionSalaCine = false;
		boolean verificacionHorario = false;
		Ticket ticketVerificado = null;
		
		//Verificamos si el atributo película de alguno de los tickets que tiene el cliente coinicide con la película en presentación
		//Verificamos si el atributo salaDeCine de ticket tiene asociado esta sala de cine
		//Verificamos si la fecha de actual no excede a la fecha en la que se presentaba la película más la duración de la misma
		for (Ticket ticket : cliente.getTickets()) {
			
			verificacionSalaCine = ticket.getSalaDeCine().equals(this);
			
			verificacionPelicula = ticket.getPelicula().equals(this.peliculaEnPresentacion);
			
			verificacionHorario = ticket.getHorario().equals(this.horarioPeliculaEnPresentacion) &
			SucursalCine.getFechaActual().isBefore(this.horarioPeliculaEnPresentacion.plus( this.peliculaEnPresentacion.getDuracion() ) ); 
			
			verificacionIngresoASala = verificacionPelicula & verificacionHorario & verificacionSalaCine;
			
			//En caso de encontrarlo rompemos el ciclo
			if (verificacionIngresoASala) {
				ticketVerificado = ticket;
				break;
			}
		}
		
		//Eliminamos la referencia del ticket verificado, en caso de que la verificación sea correcta, del array de tickets del cliente
		//Añadimos la película vista al historial de películas del cliente
		//En caso de que sea la primera vez que ve la película, la añadimos al array de películas para calificar
		if (verificacionIngresoASala) {
			
			if (!cliente.getHistorialDePeliculas().contains(ticketVerificado.getPelicula())) {
				cliente.getPeliculasDisponiblesParaCalificar().add(ticketVerificado.getPelicula());
			}

			cliente.getHistorialDePeliculas().add(ticketVerificado.getPelicula());
			
			cliente.getTickets().remove(ticketVerificado);
			
	    }
		
		//Retornamos el resultado de la verificación
		return verificacionIngresoASala;
	}
	
	/**
	 * Description: Este método se encarga actualizar la película en presentación, según los siguientes criterios: 
	 * 1. Alguna de las películas en cartelera de la sucursal de cine coincide con alguna de las salas de cine de esta. 
	 * 2. Revisamos si esa película tiene algún horario cercano o igual a la fecha actual durante el cuál estará o esta siendo presentada.
	 * una vez hecho esto y cumpla con los dos criterios anteriores, limpiamos los asientos de la sala de cine, cambiando su disponibilidad a libre, y
	 * por último actualizamos la información de la disponibilidad de los asientos, tomando la información del array de la sala virtual que 
	 * coincidió en fecha y hora de la película en presentación, además modificamos el atributo horarioPeliculaEnPresentacion
	 * y peliculaEnPresentacion de la sala de cine. 
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine), con el fin de actualizar, únicamente, las salas de cine
	 * con las películas propias de esta sucursal.
	 * */
	public void actualizarPeliculasEnPresentacion(SucursalCine sucursalCine) {
		
		Pelicula peliculaPresentacion = null;
		LocalDateTime horarioPresentacion = null;
		
		LocalDateTime horarioMasCercanoAlActual = null;
		boolean firstTimeComparacionHorario = true;
		
		boolean firstTimePosiblePeliculaPresentacionEncontrada = true;
		
		//Actualizamos la película
		for (Pelicula pelicula : sucursalCine.getCartelera()) {
			
			//Verificamos si la película tiene el mismo número de sala y tipo de formato que la sala de cine que ejecuta el método
			if ( pelicula.getSalaPresentacion().equals(this) ) {
				
				firstTimeComparacionHorario = true;
				
				for (LocalDateTime horario : pelicula.getHorarios()) {
					
					//Si es la primera vez que se realiza la comparación los setteamos como el valor más cercano al actual
					if (firstTimeComparacionHorario) {
						horarioMasCercanoAlActual = horario;
						firstTimeComparacionHorario = false;
					}
					
					//Si el horario es después del más cercano al actual y además no es después a la hora actual
					if ( (horario.isAfter(horarioMasCercanoAlActual)) && !(horario.isAfter(SucursalCine.getFechaActual())) ) {
						//Lo setteamos como el más cercano al actual
						horarioMasCercanoAlActual = horario;
					}
					
				}
				
				//Este try es para dos casos: 
				//1. No hay películas en presentación en el horario actual o la película no tenía horarios disponibles (Error en el if).
				//2. Es la primera vez que se realiza este proceso (Error en el else if).
				try {
					//Si el horarioMasCercanoAlActual es anterior o igual a la fecha actual, y es la primera vez que realizamos el proceso.
					//O si el horarioMasCercanoAlActual es anterior o igual a la fecha actual y este no es anterior a un horario encontrado
					// previmente de una película cuya sala y formato coincidía también. (De esta forma no importa el orden en el que las 
					// películas sean analizadas y se garantiza que siempre estará la película más reciente).
					//Se le da un valor a las variables peliculaPresentacion y horarioPresentacion.
					if ( !(horarioMasCercanoAlActual.isAfter(SucursalCine.getFechaActual()) ) && firstTimePosiblePeliculaPresentacionEncontrada ) {
						horarioPresentacion = horarioMasCercanoAlActual;
						peliculaPresentacion = pelicula;
						firstTimePosiblePeliculaPresentacionEncontrada = false;
					
					}else if( !(horarioMasCercanoAlActual.isAfter(SucursalCine.getFechaActual()) ) && 
							( !(horarioMasCercanoAlActual.isBefore(horarioPresentacion)) ) ){
						horarioPresentacion = horarioMasCercanoAlActual;
						peliculaPresentacion = pelicula;
					}
				}catch (NullPointerException e) {
					continue;
				}
			}
		}
		
		//Ejecutamos esta operación en caso de que se haya encontrado un cambio para la película en presentación
		if (peliculaPresentacion != null) {
			this.setPeliculaEnPresentacion(peliculaPresentacion);
			this.setHorarioPeliculaEnPresentacion(horarioPresentacion);
			
			//Actualizamos los asientos de la sala de cine
			for (int i = 0; i < this.asientos.length; i++) {
		        for (int j = 0; j < this.asientos[i].length; j++) {
		        	//Preparamos los asientos para ser actualizados cambiando su disponibilidad a libre
		        	this.cambiarDisponibilidadAsientoOcupadoParaLibre(i+1, j+1);
		        	//Actualizamos el asiento según la información de la sala de cine virtual
		        	if (!this.peliculaEnPresentacion.isDisponibilidadAsientoSalaVirtual(horarioPresentacion, i+1, j+1)) {
		            this.cambiarDisponibilidadAsientoLibre(i+1, j+1);
		        	}
		        }
		    }
			
			//Eliminamos la sala de cine virtual y su horario
			this.peliculaEnPresentacion.getAsientosVirtuales().remove(this.getPeliculaEnPresentacion().getHorarios().indexOf(horarioPresentacion));
			this.peliculaEnPresentacion.getHorarios().remove(horarioPresentacion);
			
		}
		
	}
	
	/**
	 * Description : Este método se encarga de retornar la disponibilidad de un asiento dada su fila y su columna.
	 * @param fila : Este método recibe la fila del asiento a consultar (De tipo int).
	 * @param columna : Este método recibe la columna del asiento a consultar (De tipo int).
	 * @return <b>boolean</b> : Este método retorna la disponibilidad del asiento consultado.
	 * */
	public boolean isDisponibilidadAsiento(int fila, int columna) {
		return this.asientos[fila - 1][columna - 1].isDisponibilidad();
		
	}
	
	/**
	 * Description : Este método se encarga de generar un string, que se imprimirá en pantalla, para visualizar los
	 * asientos y su número de asiento.
	 * @return <b>String</b> : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda visualizar de mejor forma el proceso de entrada a la sala de cine.
	 * */
	public String mostrarAsientosParaPantalla() {
		StringBuilder resultado = new StringBuilder("\n");
	    resultado.append("  -------------------------------------------------------------- \n                           Pantalla\n");
	    resultado.append("    ");
	    resultado.append("\n");
	    resultado.append("                       Asientos de Sala\n");
	    
	    // Mostrar asientos
	    for (int i = 0; i < this.asientos.length; i++) {
	    	resultado.append("         ");
	        for (int j = 0; j < this.asientos[i].length; j++) {
	            resultado.append("[");
	            resultado.append(this.asientos[i][j].getNumeroAsiento());
	            resultado.append("] ");
	        }
	        resultado.append("\n");
	    }

	    return resultado.toString();
	}
	
	
	
	/**
	 * Description : Este método se encarga de generar un string que se imprimirá en pantalla para visualizar los
	 * la pantalla de la sala de cine con un pequeño mensaje, además se llama al método mostrarAsientosParaPantalla.
	 * @return <b>String</b> : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda visualizar de mejor forma el proceso de entrada a la sala de cine.
	 * */
	public String mostrarPantallaSalaCine () {
		StringBuilder resultado = new StringBuilder("  -------------------------------------------------------------- ");
		
		for (int i = 0; i < 6; i++) {
			resultado.append("\n" + " |                      					|");
		}
		
		resultado.append("\n |             Programación Orientada a objetos			|");
		
		for (int i = 0; i < 6; i++) {
			resultado.append("\n |                      					|");
		}
		
		resultado.append(this.mostrarAsientosParaPantalla());
		
		return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga de revisar si una sala de cine tendrá durante ese día más películas en presentación,
	 * actualizando su atributo de horariosPresentacionDia, con el fin de reducir el número de peticiones de actualización provenientes 
	 * de esta sala de cine.
	 * */
	public void tieneMasHorariosPresentacionHoy() {
		
		LocalDate diaActual = SucursalCine.getFechaActual().toLocalDate();
		boolean horarioEncontrado = false;		
		
		for (Pelicula pelicula : this.ubicacionSede.getCartelera()) {
			if (pelicula.getSalaPresentacion().equals(this)) {
				for (LocalDateTime horario : pelicula.getHorarios()) {
					
					if (horario.toLocalDate().equals(diaActual)) {
						horarioEncontrado = true;
					}
					
					if (horarioEncontrado) {
						break;
					}
				}
				
				if (horarioEncontrado) {
					break;
				}
			}
		}
		
		this.horariosPresentacionDia = horarioEncontrado;
		
	}
	
	// Getters and Setters
	public Pelicula getPeliculaEnPresentacion() {
		return peliculaEnPresentacion;
	}

	public void setPeliculaEnPresentacion(Pelicula peliculaEnPresentacion) {
		this.peliculaEnPresentacion = peliculaEnPresentacion;
	}
	
	public LocalDateTime getHorarioPeliculaEnPresentacion() {
		return horarioPeliculaEnPresentacion;
	}
	
	public void setHorarioPeliculaEnPresentacion(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}
	
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

	public SucursalCine getUbicacionSede() {
		return ubicacionSede;
	}

	public void setUbicacionSede(SucursalCine ubicacionSede) {
		this.ubicacionSede = ubicacionSede;
	}

	public boolean isHorariosPresentacionDia() {
		return horariosPresentacionDia;
	}

	public void setHorariosPresentacionDia(boolean horariosPresentacionDia) {
		this.horariosPresentacionDia = horariosPresentacionDia;
	}

}
