package gestionAplicacion.proyecciones;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.Cliente;

public class Pelicula{
	
	private String nombre;
	private int precio;
	private String genero;
	private Duration duracion;
	private String clasificacion;
	private ArrayList<LocalDateTime> horarios = new ArrayList<>();
	private ArrayList<int[][]> asientosVirtuales = new ArrayList<>();
	private String tipoDeFormato;
	private SalaCine salaPresentacion;
	private int idPelicula; 
	private double valoracion;
	private int totalEncuestasDeValoracionRealizadas;
	
	// Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getTipoDeFormato() {
		return tipoDeFormato;
	}

	public void setTipoDeFormato(String tipoDeFormato) {
		this.tipoDeFormato = tipoDeFormato;
	}

	public SalaCine getSalaPresentacion() {
		return salaPresentacion;
	}

	public void setSalaPresentacion(SalaCine salaPresentacion) {
		this.salaPresentacion = salaPresentacion;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public ArrayList<LocalDateTime> getHorarios() {
		return horarios;
	}

	public void setHorarios(ArrayList<LocalDateTime> horarios) {
		this.horarios = horarios;
	}

	public ArrayList<int[][]> getAsientosVirtuales() {
		return asientosVirtuales;
	}

	public void setAsientosVirtuales(ArrayList<int[][]> asientos) {
		this.asientosVirtuales = asientos;
	}
	
	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	public int getTotalEncuestasDeValoracionRealizadas() {
		return totalEncuestasDeValoracionRealizadas;
	}

	public void setTotalEncuestasDeValoracionRealizadas(int totalEncuestasDeValoracionRealizadas) {
		this.totalEncuestasDeValoracionRealizadas = totalEncuestasDeValoracionRealizadas;
	}

	// Constructor
	public Pelicula(){
		SucursalCine.getPeliculasDisponibles().add(this);
		this.idPelicula = SucursalCine.getPeliculasDisponibles().size();
	}

	public Pelicula(String nombre, int precio, String genero, Duration duracion, String clasificacion,
			String tipoDeFormato, SucursalCine sucursalCine) {
		this();
		this.nombre = nombre;
		this.precio = precio;
		this.genero = genero;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.tipoDeFormato = tipoDeFormato;
		sucursalCine.getCartelera().add(this);
		this.crearPelicula(sucursalCine);
	}
	
	public Pelicula(String nombre, int precio, String genero, Duration duracion, String clasificacion,
			String tipoDeFormato) {
		this();
		this.nombre = nombre;
		this.precio = precio;
		this.genero = genero;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.tipoDeFormato = tipoDeFormato;
	}

	//Methods
	/**
	 * Description : Este método genera una lista filtrada según el nombre de las películas disponibles sin repetición
	 * @param filtroPeliculasPorCliente : Este método recibe como parámetro las peliculas (De tipo ArrayList<Pelicula>) resultantes de realizar el filtro
	 * por cliente (Edad y tiempo en presentación). 
	 * @return filtroNombrePeliculas : Retorna una lista de nombres de las películas distintos entre sí.
	 * */
	public static ArrayList<String> filtrarNombrePeliculas(ArrayList<Pelicula> filtroPeliculasPorCliente){
		ArrayList<String> filtroNombrePeliculas = new ArrayList<>();
		for (Pelicula pelicula : filtroPeliculasPorCliente){
			if (!filtroNombrePeliculas.contains(pelicula.getNombre())) {
				filtroNombrePeliculas.add(pelicula.getNombre());
			}
		}
		return filtroNombrePeliculas;
	}
	
	/**
	 * Description : Este método genera una lista filtrada según el nombre de las películas que coinciden con determinado género, sin repetición.
	 * @param filtroPeliculasPorCliente : Este método recibe como parámetro las peliculas (De tipo ArrayList<Pelicula>) resultantes de realizar el filtro
	 * por cliente (Edad y tiempo en presentación).
	 * @param genero : Este método recibe como parámetro el género (De tipo String) más visualizado por el cliente 
	 * @return filtroNombrePeliculas : Retorna una lista de nombres de las películas distintos entre sí, cuyo género es igual.
	 * */
	public static ArrayList<String> filtrarPorGenero(ArrayList<Pelicula> filtroPeliculasPorCliente, String genero){
		ArrayList<String> filtroNombrePeliculas = new ArrayList<>();
		for (Pelicula pelicula : filtroPeliculasPorCliente){
			if (pelicula.getGenero().equals(genero)) {
				if (!filtroNombrePeliculas.contains(pelicula.getNombre())) {
					filtroNombrePeliculas.add(pelicula.getNombre());
				}
			}
		}
		return filtroNombrePeliculas;
	}
	
	/**
	 * Description : Este método se encarga de mostrar en pantalla el nombre de las películas en cartelera, luego de realizar el filtro por nombre
	 * de peliculas. En caso de que el cliente tenga membresía se realiza la recomendación de las películas.
	 * @param filtroNombrePeliculas : Este método recibe como parámetro los nombres de las películas sin repetición (De tipo ArrayList<Stirng>)
	 * obtenidos de realizar el filtro de peliculas por nombre.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente), con el fin de determinar si tiene o no membresía.
	 * @param nombrePeliculaRecomendadas : Este método recibe como parámetro los nombres de las películas sin repeteción (De tipo ArrayList<String>)
	 * obtenidos luego de realizar el filtro por género de película más visto por el cliente
	 * @return <b>String</b> : Este método retorna una lista de los nombres de las películas para ser presentadas por pantalla, con el fin de que 
	 * el usuario elija una de estas (En caso de que el cliente tenga membresía se realiza una recomendación según el género de película que más ha visto).
	 * */
	public static String showNombrePeliculas(ArrayList<String> filtroNombrePeliculas, Cliente clienteProceso, ArrayList<String> nombrePeliculasRecomendadas) {
		String nombrePeliculas = "\n";
		int i = 1;
		if(clienteProceso.getMembresia() != null) {
			for(String nombrePelicula : filtroNombrePeliculas) {
				
				if (!nombrePeliculas.contains(nombrePelicula)) {
					
					if (nombrePeliculasRecomendadas.contains(nombrePelicula)) {
						nombrePeliculas = nombrePeliculas + "\n" + i + ". RECOMENDADA: " + nombrePelicula;
						i++;
					}else {
						nombrePeliculas = nombrePeliculas + "\n" + i + ". " + nombrePelicula;
						i++;
					}
					
				}
				
			}
			
		}else {
			for (String nombrePelicula : filtroNombrePeliculas) {
				
				if (!nombrePeliculas.contains(nombrePelicula)) {
					nombrePeliculas = nombrePeliculas + "\n" + i + ". " + nombrePelicula;
				}
				i++;
				
			}
		}
		
		return nombrePeliculas;
	}
	
	/**
	 * Description : Este método se encarga de retornar las películas cuyo nombre coincide con el nombre de la película seleccionada por el cliente.
	 * @param nombrePelicula : Este método recibe como parámetro el nombre de la película (De tipo String) con el cuál se realizará el filtrado.
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) desde la cuál se está realizando el proceso de reserva
	 * del ticket para acceder a la cartelera.
	 * @return <b>ArrayList<Pelicula></b> : Este método retorna un ArrayList de las películas cuyo nombre coinciden con el nombre seleccionado 
	 * por el cliente.
	 * */
	public static ArrayList<Pelicula> filtrarPorNombreDePelicula(String nombrePelicula, SucursalCine sucursalCine){
		ArrayList<Pelicula> peliculasEncontradas = new ArrayList<>();
		
		for (Pelicula pelicula : sucursalCine.getCartelera()) {
			if (pelicula.getNombre().equals(nombrePelicula)) {
				peliculasEncontradas.add(pelicula);
			}
		}
		
		return peliculasEncontradas;
	}
	
	/**
	 * Description : Este método retorna un string con los formatos disponibles de las películas que coinciden con el nombre de la película
	 * seleccionada por el cliente.
	 * @param peliculasFiltradasPorNombre : Este método recibe como parámetro una lista de películas (De tipo ArrayList<Pelicula>) 
	 * filtradas previamente por el nombre de la película seleccionado por el usuario 
	 * @return <b>String</b> : Este método retorna un String que contiene los formatos de la película seleccionada por el usuario, 
	 * este será mostrado en pantalla con el fin de que el usuario selecccione uno de estos formatos
	 * */
	public static String showTiposFormatoPeliculaSeleccionada(ArrayList<Pelicula> peliculasFiltradasPorNombre){
		String TiposFormatoPeliculaSeleccionada = null;
		int i = 1;
		for (Pelicula pelicula : peliculasFiltradasPorNombre){
			if (TiposFormatoPeliculaSeleccionada == null) {
				TiposFormatoPeliculaSeleccionada = i + ". " + pelicula.getTipoDeFormato() + ", Precio: " + pelicula.getPrecio();
			}else {
				TiposFormatoPeliculaSeleccionada = TiposFormatoPeliculaSeleccionada + "\n" + i + ". " + pelicula.getTipoDeFormato() + ", Precio: " + pelicula.getPrecio();
			}
			i++;
		}
		return TiposFormatoPeliculaSeleccionada;
	}

	/**
	 * Description : Este método se encarga de filtar las películas en cartelera con los siguientes criterios:  1. Su categoría es menor o igual a 
	 * la edad de un cliente 2. La película tiene al menos 1 horario en el cuál será presentada o se encuentra en presentación y no supera el 
	 * límite de tiempo para comprar un ticket de una película que se encuentra en presentación (15 minutos). Con el fin de mostrar en pantalla, 
	 * posteriormente, el array de las películas que cumplan estos criterios. 
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente), que realizará el proceso de reserva de ticket
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine), para acceder a la cartelera de esta misma
	 * @return <b>ArrayList</b> : Retorna una lista con las peliculas filtradas por el criterio anterior 
	 * */
	public static ArrayList<Pelicula> filtrarCarteleraPorCliente(Cliente clienteProceso, SucursalCine sucursalCine){
		ArrayList<Pelicula> carteleraPersonalizada = new ArrayList<Pelicula>();
		for (Pelicula pelicula : sucursalCine.getCartelera()) {
			if (pelicula.getHorarios().size() > 0 || pelicula.IsPeliculaEnPresentacion(sucursalCine) ) {
				if ((Integer.parseInt(pelicula.getClasificacion())) <= clienteProceso.getEdad()) {
					carteleraPersonalizada.add(pelicula);
				}
			}
		}
		return carteleraPersonalizada;
	}
	
//	/**
//	 * Description : Este método se encarga de verificar si la pelicula seleccionada se encuentra en presentación
//	 * y en caso de que sí, retorna la sala de cine que la esta presentando
//	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine), para acceder a sus salas de cine
//	 * @return <b>SalaDeCine</b> : Retorna el objeto de sala de cine que coincide con la pelicula
//	 * */
//	public SalaCine obtenerSalaDePeliculaEnPresentacion(SucursalCine sucursalCine) {
//		for (SalaCine SalaDecine : sucursalCine.getSalasDeCine()) {
//			if (this.equals(SalaDecine.getPeliculaEnPresentacion())){
//				return SalaDecine;
//			}
//		}
//		return null;
//	}
	
	/**
	 * Description : Este método se encarga de generar un String que se imprimirá en pantalla con el fin de visualizar
	 * el estado de de los asientos en la sala virtual.
	 * @param fecha : Recibe una fecha (De tipo LocalDateTime), que corresponde al horario que seleccionó el cliente, 
	 * para buscar sus asientos y mostrarlos en pantalla
	 * @return <b>String</b> : Retorna un string con las posiciones de los asientos y su disponibilidad
	 * */
	public String mostrarAsientosSalaVirtual(LocalDateTime fecha) {
		String resultado = "\n";
		resultado = resultado + ("Asientos de Cine\n");
		resultado = resultado + ("\n(Fila: distribución horizontal de asientos)\n(Columna: distribución vertical de asientos)\n(Número de asiento: Intersección fila y columna)\n");
		resultado = resultado + ("  --------------------------------- \n              Pantalla\n");
	    resultado = resultado + ("    ");
	    
	    // Agregar números de columnas
	    for (int i = 0; i < this.asientosVirtuales.get(this.horarios.indexOf(fecha)).length; i++) {
	        resultado = resultado + (String.format("%-4d", i + 1));
	    }
	    resultado = resultado + ("\n");

	    // Mostrar asientos
	    for (int i = 0; i < this.asientosVirtuales.get(this.horarios.indexOf(fecha)).length; i++) {
	        resultado = resultado + (String.format("%-2d ", i + 1));
	        for (int j = 0; j < this.asientosVirtuales.get(this.horarios.indexOf(fecha)).length; j++) {
	            resultado = resultado + ("[");
	            resultado = resultado + ((this.asientosVirtuales.get(this.horarios.indexOf(fecha))[i][j] == 1) ? "X" : "O");
	            resultado = resultado + ("] ");
	        }
	        resultado = resultado + ("\n");
	    }

	    return resultado;
	}
	
	/**
	 * Description : Este método se encarga cambiar la disponibilidad de un asiento de la salaVirtual.
	 * @param fecha : Recibe el dato de la fecha, en formato localDateTime, seleccionado por el cliente que se pasará a la llave de horarios
	 * @param fila : Recibe el número de la fila seleccionada por el cliente
	 * @param columna : Recibe el número de la columna seleccionada por el cliente
	 * */
	public void modificarSalaVirtual(LocalDateTime fecha, int fila, int columna) {
	    
		if (this.asientosVirtuales.get(this.horarios.indexOf(fecha))[fila - 1][columna - 1] == 0) {
			this.asientosVirtuales.get(this.horarios.indexOf(fecha))[fila - 1][columna - 1] = 1;	
		}else {
			this.asientosVirtuales.get(this.horarios.indexOf(fecha))[fila - 1][columna - 1] = 0;	
		}	
	}
	
	/**
	 * Description : Este método se encarga revisar la desponibilidad de un índice de la salaVirtual
	 * @param fecha : Recibe el dato del horario (De tipo localDateTime) seleccionado por el cliente con la que se obtendrán sus asientos, 
	 * correspondientes a la sala de cine virtual.
	 * @param fila : Recibe el número de la fila seleccionada por el cliente.
	 * @param columna : Recibe el número de la columna seleccionada por el cliente.
	 * @return <b>boolean</b> : Este método retorna un boolean que representa la disponibilidad del asiento selccionado por el cliente.
	 * */
	public boolean isDisponibilidadAsientoSalaVirtual(LocalDateTime fecha, int fila, int columna) {
	    
		if (this.asientosVirtuales.get(this.horarios.indexOf(fecha))[fila - 1][columna - 1] == 0) {
			return true;	
		}else {
			return false;	
		}	
	}
	
	/**
	 * Description : Este método se encarga de crear una matriz que representa la sala virtual posteriormente esta se añade al array 
	 * de asientos virtuales de la película y se añade el horario al array de horarios.
	 * @param fecha : Este método recibe una fecha (De tipo LocalDateTime) para crear la salaDeCineVirtual.
	 * */
	public void crearSalaVirtual(LocalDateTime fecha) {
		int[][] nuevaSalaVirtual = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				nuevaSalaVirtual[i][j] = 0;
			}
		}
		
		this.horarios.add(fecha);
		this.asientosVirtuales.add(nuevaSalaVirtual);
		
	}
	
	/**
	 * Description : Este método se encarga de filtrar los horarios de la película que no han sido presentados aún y, además, 
	 * tienen asientos disponibles
	 * @return <b>ArrayList<LocalDateTime></b> : Este método se encarga de retornar los primeros 7 horarios que cumplen los criterios de filtrado
	 * */
	public ArrayList<LocalDateTime> filtrarHorariosPelicula(){
		ArrayList<LocalDateTime> horariosPelicula = new ArrayList<>();
		boolean isAsientosDisponibles = false;
		
		for (LocalDateTime horario : this.horarios) {
			for (int[] filaAsientos : this.asientosVirtuales.get(horarios.indexOf(horario))) {
				for (int asiento : filaAsientos) {
					if (asiento == 0) {
						isAsientosDisponibles = true;
					}
				}
			}
			if (SucursalCine.getFechaActual().isBefore(horario) && isAsientosDisponibles && horariosPelicula.size() < 7) {
				horariosPelicula.add(horario);
			}
		}
		
		return horariosPelicula;
	}
	
	/**
	 * Description : Este método se encarga de mostrar un listado con los horarios disponibles de la pelicula solicitada por el cliente.
	 * @return <b>String</b> : Retorna un string que contiene los horarios disponibles de la película de forma enumerada y organizada 
	 * para mostrar en pantalla.
	 * */
	public String mostrarHorarioPelicula(ArrayList<LocalDateTime> horariosPelicula) {
		String horarios = null;
		int i = 1;
		for (LocalDateTime Horario : horariosPelicula) {
			if (horarios == null) {
				horarios = i + ". Día: " + Horario.getDayOfWeek() + ", Fecha : " + Horario.toLocalDate() + ", Hora: " + Horario.toLocalTime() + ", Duración: " + this.duracion.toMinutes() + " Minutos" + "\n";
			}else {
				horarios = horarios + i + ". Día: " + Horario.getDayOfWeek() + ", Fecha : " + Horario.toLocalDate() + ", Hora: " + Horario.toLocalTime() + ", Duración: " + this.duracion.toMinutes() + " Minutos" + "\n";
			}
			i++;
		}
		return horarios;
	}
	
	/**
	 * Description : Este método se encarga de buscar si la pelicula que ejecuta este método se encuentra en presentación, la 
	 * utilidad de este método radica en que, si retorna algo distinto de null, se ejecuta un menú adicional antes de mostrar el
	 * listado de horarios de la pelicula seleccionada por el usuario.
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) en donde se realiza este proceso
	 * para obtener sus salas de cine.
	 * @return <b>SalaCine</b> : Este método retorna la sala de cine donde está siendo proyectada la película, en caso de estarlo,
	 * si la película no se encuentra en presentacion, retorna null.
	 * */
	public SalaCine whereIsPeliculaEnPresentacion(SucursalCine sucursalCine) {
		for (SalaCine salaDeCine : sucursalCine.getSalasDeCine() ) {
			try {
				if (salaDeCine.getPeliculaEnPresentacion().equals(this)) {
					return salaDeCine;
				}
			}catch(NullPointerException e) {
				continue;
			}
			
		}
		return null;
	}
	
	/**
	 * Description : Este método se encarga de buscar si la pelicula que ejecuta este método se encuentra en presentación, la 
	 * utilidad de este método radica en que retornará true en caso de: 1. Encontrarla, 2. No lleva más de 15 minutos en presentación 
	 * y 3. Tenga algún asiento disponible, respecto a este retorno, se ejecutará un menú determinado en el proceso de la funcionalidad 1.
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) en donde se realiza este proceso, para
	 * obtener sus salas de cine.
	 * @return <b>boolean</b> : Este método retorna un boolean, que representa si la película cumple, o no, con los criterios.
	 * */
	public boolean IsPeliculaEnPresentacion(SucursalCine sucursalCine) {;
		for (SalaCine salaDeCine : sucursalCine.getSalasDeCine()) {
			try {
				if (salaDeCine.getPeliculaEnPresentacion().equals(this) && SucursalCine.getFechaActual().isBefore(salaDeCine.getHorarioPeliculaEnPresentacion().plus(Duration.ofMinutes(15)))) {
					for (Asiento[] filaAsientos : salaDeCine.getAsientos()) {
						for (Asiento asiento : filaAsientos) {
							if (asiento.isDisponibilidad()) {
								return true;
							}
						}
					}
				}
			}catch(NullPointerException e) {
				continue;
			}
			
		}
		return false;
	}
	
//	/**
//	 * Description: Este método se encarga de buscar la salaDeCine en el array de salasDeCine que tiene el mismo numero de sala de 
//	 * la película para luego retornarlo.
//	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) para realizar la busqueda en su array de salas de cine.
//	 * @return : Este método retorna la salaDeCine que tiene el mismo número de sala.
//	 * */
//	public SalaCine obtenerSalaDeCineConCodigo(SucursalCine sucursalCine) {
//		for (SalaCine salaDeCine : sucursalCine.getSalasDeCine()) {
//			try {
//				if (this.numeroDeSala == salaDeCine.getNumeroSala()) {
//					return salaDeCine;
//				}
//			}catch(NullPointerException e) {
//				continue;
//			}
//			
//		}
//		return null;
//	}
	
	/**
	 * Description : Este método se encarga de automatizar la creación de películas en varios formatos con distinto precio, para hacer esto
	 * se evalua si el género de la película que ejecuta este método se encuentra en los géneros que tienen permitido el 3D y 4D o únicamente 3D, 
	 * en caso de ser así, crea una película nueva con toda su misma información, a excepción del tipo de formato y precio, para finalizar
	 * se añaden las películas creadas a la cartelera de la sucursal que se pasa como parámetro.
	 * @param sucursalCine : Este método recibe como parámetro la sede (De tipo SucursalCine) en la que será presentada la película.
	 * */
	public void crearPelicula(SucursalCine sucursalCine) {
		
		ArrayList<String> generos4D = new ArrayList<>();
		generos4D.add("Aventura");
		generos4D.add("Acción");
		generos4D.add("Ciencia ficción");
		generos4D.add("Terror");
		generos4D.add("Infantil");
		
		ArrayList<String> generos3D = new ArrayList<>();
		generos3D.add("Historia");
		generos3D.add("Comedia");
		
		if (generos4D.contains(this.genero)) {
			sucursalCine.getCartelera().add(new Pelicula(this.nombre, this.precio + 15000, this.genero, this.duracion, this.clasificacion, "3D"));
			sucursalCine.getCartelera().add(new Pelicula(this.nombre, this.precio + 50000, this.genero, this.duracion, this.clasificacion, "4D"));
		}else if (generos3D.contains(this.genero)) {
			sucursalCine.getCartelera().add(new Pelicula(this.nombre, this.precio + 15000, this.genero, this.duracion, this.clasificacion, "3D"));
		}
	}
		
}
