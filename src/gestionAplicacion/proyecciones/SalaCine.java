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
	private SucursalCine ubicacionSede;
	
	//Constructors
	public SalaCine(int nSala, String tipoDeSala, SucursalCine ubicacionSede){
		this.numeroSala = nSala;
		this.tipoDeSala = tipoDeSala;
		this.ubicacionSede = ubicacionSede;
		ubicacionSede.getSalasDeCine().add(this);
		this.asientos = this.crearAsientosSalaDeCine();
	}
	
	public SalaCine(int numeroSala, String tipoDeSala, Asiento[][] asientos, Pelicula peliculaEnPresentacion, ArrayList<Ticket> ticketsCreados) {
		this.numeroSala = numeroSala;
		this.tipoDeSala = tipoDeSala;
		this.asientos = asientos;
		this.peliculaEnPresentacion = peliculaEnPresentacion;
	}

	//Methods
	/**
	 * Description : Este método se encarga de generar asientos para la sala de cine, facilitando el proceso de rear una sala de cine
	 * @return : Este método no retorna nada (void), solo crea los asientos de la sala de cine
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
	 * @return : (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda interactuar con la funcionalidad
	 * */
	public String mostrarAsientos() {
		String resultado = "\n";
		resultado = resultado + ("Asientos de Cine\n");
	    resultado = resultado + ("\n(Fila: distribución horizontal de asientos)\n(Columna: distribución vertical de asientos)\n(Número de asiento: Intersección fila y columna)\n");
	    resultado = resultado + ("  --------------------------------- \n              Pantalla\n");
	    resultado = resultado + ("    ");
	    // Agregar números de columnas
	    for (int i = 0; i < this.getAsientos().length; i++) {
	        resultado = resultado + (String.format("%-4d", i + 1));
	    }
	    resultado = resultado + ("\n");

	    // Mostrar asientos
	    for (int i = 0; i < this.getAsientos().length; i++) {
	        resultado = resultado + (String.format("%-2d ", i + 1));
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	            resultado = resultado + ("[");
	            resultado = resultado + (this.getAsientos()[i][j].isDisponibilidad() ? "O" : "X");
	            resultado = resultado + ("] ");
	        }
	        resultado = resultado + ("\n");
	    }

	    return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es true la cambia a false, se usa para separar un asiento luego de ser comprado
	 * @param fila : Índice de la fila del asiento que queremos modificar (De tipo Integer).
	 * @param columna : Índice de la columna del asiento que queremos modificar(De tipo Integer).
	 * */
	public void cambiarDisponibilidadAsientoLibre(int fila, int columna) {
		if (this.getAsientos()[fila - 1][columna - 1].isDisponibilidad()) {
			this.getAsientos()[fila - 1][columna - 1].setDisponibilidad(false);	
		}
	}
	
	/**
	 * Description : Este método se encarga de modificar la disponiblidad de un asiento dada su posición,
	 * si su disponibilidad es true la cambia a false, se usa para separar un asiento luego de ser comprado.
	 * @param numeroAsiento : Este método recibe como parámetro el numero del asiento seleccionado por el cliente
	 * (De tipo String) durante el proceso de la funcionalidad 1.
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
	 * si su disponibilidad es false la cambia a true, es especialmente útil para preparar la sala de cine
	 * para presentar una nueva película.
	 * @param fila : Índice de la fila del asiento que queremos modificar (De tipo Integer).
	 * @param columna : Índice de la columna del asiento que queremos modificar(De tipo Integer).
	 * */
	public void cambiarDisponibilidadAsientoOcupadoParaLibre(int fila, int columna) {
		if (!this.getAsientos()[fila - 1][columna - 1].isDisponibilidad()) {
			this.getAsientos()[fila - 1][columna - 1].setDisponibilidad(true);
		}
	}
	
	/**
	 * Description : Este método se encarga de generar un listado de la salas de cine con información relevante de estas,
	 * con el fin de que el usuario elija una de las opciones disponibles para ingresar.
	 * @return <b>String</b>: Retorna la lista de las salas de cine disponibles.
	 * */
	public static String mostrarSalaCine(ArrayList<SalaCine> salasDeCine) {
		String resultado = null;
		int i = 1;
		
		for (SalaCine salaDeCine : salasDeCine) {
			
			if (salaDeCine.getPeliculaEnPresentacion() == null) {
				continue;
			}
			
			if (resultado == null) {
				resultado = i + ". Número sala de cine: " + salaDeCine.getNumeroSala() 
				+ "; Formato de sala de cine : " + salaDeCine.getTipoDeSala()
				+ "; Película en presentación : " + salaDeCine.getPeliculaEnPresentacion().getNombre()
				+ "; Horario película : " + salaDeCine.getHorarioPeliculaEnPresentacion();
			}else {
				resultado = resultado + "\n" + i + ". Número sala de cine: " + salaDeCine.getNumeroSala() 
				+ "; Formato de sala de cine : " + salaDeCine.getTipoDeSala()
				+ "; Película en presentación : " + salaDeCine.getPeliculaEnPresentacion().getNombre()
				+ "; Horario película : " + salaDeCine.getHorarioPeliculaEnPresentacion();
			}
			
			i++;
			
		}
		
		return resultado;
	}
	
	/**
	 * Description : Este método se encarga de filtrar las salas de cine según si su película aún se encuentra en presentación,
	 * para esto verifica que el horario de la película en presentación más su duración no sea menor a la hora actual
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) en donde se realiza la busqueda desde sus salas de cine
	 * @return  <b>ArrayList<SalaCine></b> : Este método retorna las salas de cine (De tipo ArrayList<SalaCine>)
	 * que aún tienen su película en presentación, con el fin de ser las únicas que serán mostradas en pantalla durante el proceso de la funcionalidad 1 
	 * */
	public static ArrayList<SalaCine> filtrarSalasDeCine (SucursalCine sucursalCine){
		ArrayList<SalaCine> salasDeCineDisponibles = new ArrayList<>();
		
		for (SalaCine salaDeCine : sucursalCine.getSalasDeCine()) {
			//Se usa try en caso de que las salas de cine no tengan ninguna película en presentación en estos momentos y devolver un array vacío, para continuar con el proceso
			try {
				if (salaDeCine.getHorarioPeliculaEnPresentacion().plus(salaDeCine.getPeliculaEnPresentacion().getDuracion()).isAfter(SucursalCine.getFechaActual())) {
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
	 * 1. La película asociada a este ticket se encuentra coincide con la peliculaEnPresentacion de la SalaDeCine.
	 * 2. La fecha actual es anterior a la fecha en que finaliza la película.
	 * 3. La sala de cine asociada al ticket es la misma que la sala de cine que ejecuta este método.
	 * @param cliente : Este método solicita al cliente (De tipo cliente) que va a ingresar a la SalaDeCine.
	 * @return boolean : Este método se encarga de retornar un boolean que será el resultado del proceso de verificación de entrada a la salaDeCine.
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
			
			verificacionSalaCine = ticket.getSalaDeCine().equals(this);
			
			verificacionPelicula = ticket.getPelicula().equals(this.peliculaEnPresentacion);
			
			verificacionHorario = ticket.getHorario().equals(this.getHorarioPeliculaEnPresentacion()) &
			SucursalCine.getFechaActual().isBefore(this.getHorarioPeliculaEnPresentacion().plus( this.getPeliculaEnPresentacion().getDuracion() ) ); 
			
			verificacion = verificacionPelicula & verificacionHorario & verificacionSalaCine;
			
			//En caso de encontrarlo rompemos el ciclo
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
	 * Description: Este método se encarga actualizar la película en presentación, según los siguientes criterios: 
	 * 1. Alguna de las películas en cartelera de la sucursal de cine coincide con alguna de las salas de cine de esta. 
	 * 2. Revisamos si esa película tiene algún horario cercano o igual a la fecha actual durante el cuál estará o esta siendo presentada.
	 * una vez hecho esto cumpla con los dos criterios anteriores, limpiamos los asientos de la sala de cine, cambiando su disponibilidad a libre, y
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
				//2. Es la primera vez que se realiza este proceso(Error en el else if).
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
			
			//Preparamos los asientos para ser actualizados cambiando su disponibilidad a libre
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
			
			//Eliminamos la sala de cine virtual y su horario
			this.getPeliculaEnPresentacion().getAsientosVirtuales().remove(this.getPeliculaEnPresentacion().getHorarios().indexOf(horarioPresentacion));
			this.getPeliculaEnPresentacion().getHorarios().remove(horarioPresentacion);
			
		}
		
	}
	
	/**
	 * Description : Este método se encarga de retornar la disponibilidad de un asiento dada su fila y su columna.
	 * @param fila : Este método recibe la fila del asiento a consultar (De tipo int).
	 * @param columna: Este método recibe la columna del asiento a consultar (De tipo int).
	 * @return <b>boolean</b> : Este método retorna la disponibilidad del asiento consultado.
	 * */
	public boolean isDisponibilidadAsiento(int fila, int columna) {
		return this.getAsientos()[fila - 1][columna - 1].isDisponibilidad();
		
	}
	
	/**
	 * Description: Este método se encarga de validar el asiento seleccionado por el cliente una vez ingresa
	 * a la sala de cine, como última interacción para ingresar a la sala de cine
	 * @param numeroAsiento : Este método se encarga de recibir un número de asiento dado por el cliente
	 * en el formato #fila + "-" + #columna (De tipoString)
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
	 * Description : Este método se encarga de generar un string, que se imprimirá en pantalla, para visualizar los
	 * asientos y su número de asiento
	 * @return (String Resultado) : Este método retorna un string que será impreso en pantalla para que el cliente 
	 * pueda visualizar de mejor forma el proceso de entrada a la sala de cine
	 * */
	public String mostrarAsientosParaPantalla() {
		String resultado = "\n";
	    resultado = resultado + ("  -------------------------------------------------------------- \n                           Pantalla\n");
	    resultado = resultado + ("    ");
	    resultado = resultado + ("\n");
	    resultado = resultado + ("                       Asientos de Sala\n");
	    
	    // Mostrar asientos
	    for (int i = 0; i < this.getAsientos().length; i++) {
	    	resultado = resultado + ("         ");
	        for (int j = 0; j < this.getAsientos()[i].length; j++) {
	            resultado = resultado + ("[");
	            resultado = resultado + (this.getAsientos()[i][j].getNumeroAsiento());
	            resultado = resultado + ("] ");
	        }
	        resultado = resultado + ("\n");
	    }

	    return resultado;
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
		
		resultado = resultado + "\n |             Programación Orientada a objetos			|";
		
		for (int i = 0; i < 6; i++) {
			resultado = resultado + "\n |                      					|";
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
	
	public LocalDateTime getHorarioPeliculaEnPresentacion() {
		return horarioPeliculaEnPresentacion;
	}
	
	public void setHorarioPelicula(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}
	
	public void setHorarioPeliculaEnPresentacion(LocalDateTime horarioPeliculaEnPresentacion) {
		this.horarioPeliculaEnPresentacion = horarioPeliculaEnPresentacion;
	}

	public SucursalCine getUbicacionSede() {
		return ubicacionSede;
	}

	public void setUbicacionSede(SucursalCine ubicacionSede) {
		this.ubicacionSede = ubicacionSede;
	}

}
