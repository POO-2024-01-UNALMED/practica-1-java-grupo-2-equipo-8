package gestionAplicacion.proyecciones;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.time.Duration;

public class Pelicula{
	
	private String nombre;
	private int precio;
	private String genero;
	private Duration duracion;
	private String clasificacion;
	//private ArrayList<Horario> horarios = new ArrayList<>();
	private LinkedHashMap<LocalDateTime, int[][]> horarios = new LinkedHashMap<>();
	private static ArrayList<Pelicula> cartelera = new ArrayList<>();
	private String tipoDeFormato;
	private int numeroDeSala;
	private int idPelicula; 
	private static ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	
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

	public LinkedHashMap<LocalDateTime, int[][]> getHorarios() {
		return horarios;
	}

	public void setHorarios(LinkedHashMap<LocalDateTime, int[][]> horarios) {
		this.horarios = horarios;
	}

	public static ArrayList<Pelicula> getCartelera() {
		return cartelera;
	}

	public static void setCartelera(ArrayList<Pelicula> cartelera) {
		Pelicula.cartelera = cartelera;
	}

	public String getTipoDeFormato() {
		return tipoDeFormato;
	}

	public void setTipoDeFormato(String tipoDeFormato) {
		this.tipoDeFormato = tipoDeFormato;
	}

	public int getNumeroDeSala() {
		return numeroDeSala;
	}

	public void setNumeroDeSala(int numeroDeSala) {
		this.numeroDeSala = numeroDeSala;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public static ArrayList<SalaCine> getSalasDeCine() {
		return salasDeCine;
	}

	public static void setSalasDeCine(ArrayList<SalaCine> salasDeCine) {
		Pelicula.salasDeCine = salasDeCine;
	}

	// Constructor
	public Pelicula(){
		Pelicula.getCartelera().add(this);
	}

	public Pelicula(String nombre, int precio, String genero, Duration duracion, String clasificacion,
			String tipoDeFormato, int numeroDeSala) {
		this();
		this.nombre = nombre;
		this.precio = precio;
		this.genero = genero;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.tipoDeFormato = tipoDeFormato;
		this.numeroDeSala = numeroDeSala;
	}

	public Pelicula(String nombre, int precio, String genero, Duration duracion, String clasificacion,
			LinkedHashMap<LocalDateTime, int[][]> horarios, String tipoDeFormato, int numeroDeSala, int idPelicula) {
		this();
		this.nombre = nombre;
		this.precio = precio;
		this.genero = genero;
		this.duracion = duracion;
		this.clasificacion = clasificacion;
		this.horarios = horarios;
		this.tipoDeFormato = tipoDeFormato;
		this.numeroDeSala = numeroDeSala;
		this.idPelicula = idPelicula;
	}

	//Methods
	/**
	 * Description : Este método genera una lista filtrada según el nombre de la película seleccionada por el usuario
	 * @param nombrePelicula : Recibe el nombre de una de las películas disponibles en cartelera
	 * @return filtroPeliculas : Retorna una lista de las películas cuyo nombre coincida con el parámetro.
	 * */
	public ArrayList<Pelicula> filtrarNombrePeliculas(String nombrePelicula){
		ArrayList<Pelicula> filtroPeliculas = new ArrayList<Pelicula>();
		for (Pelicula pelicula : cartelera){
			if (pelicula.getNombre().equals(nombrePelicula)) {
				filtroPeliculas.add(pelicula);
			}
		}
		return filtroPeliculas;
	}
	
	/**
	 * Description : Este método obtiene la película de lista filtrada por nombre que coincide 
	 * con el formato dado por el cliente 
	 * @param peliculas : Lista de películas filtradas por nombre
	 * @param tipoDeFormato : String que contiene el formato seleccionado por el cliente
	 * @return <b>pelicula</b> : Retorna el objeto película que cumple los criterios anteriores
	 * */
	public Pelicula filtrarTipoFormatoPeliculas(ArrayList<Pelicula> peliculas, String tipoDeFormato){
		for (Pelicula pelicula : peliculas){
			if (pelicula.getTipoDeFormato().equals(tipoDeFormato)) {
				return pelicula;
			}
		}
		return null;
	}
	
	//Este método es una versión resumida de los dos anteriores
	/*
	public Pelicula seleccionarPeliculaSolicitada(String nombrePelicula, String TipoDeFormato) {
		for (Pelicula pelicula : cartelera){
			if (pelicula.getNombre().equals(nombrePelicula) & pelicula.getTipoDeFormato().equals(TipoDeFormato)) {
				return pelicula;
			}
		}
		return null;
	}
	*/
	
	/**
	 * Description : Este método se encarga de mostrar un listado con los horarios disponibles de la pelicula 
	 * solicitada cabe destacar que la pelicula es quien se ejecuta su método.
	 * @return <b>String</b> : Retorna un string con las llaves del diccionario enumeradas de 1 a N.
	 * */
	public String mostrarHorarioPelicula() {
		String horarios = null;
		int i = 1;
		for (LocalDateTime Horario : this.getHorarios().keySet()) {
			
			if (horarios == null) {
				horarios = i + ". Día: " + Horario.getDayOfWeek() + ", Fecha : " + Horario.toLocalDate() + ", Hora: " + Horario.toLocalTime() + "\n";
			}else {
				horarios = horarios + i + ". Día: " + Horario.getDayOfWeek() + ", Fecha : " + Horario.toLocalDate() + ", Hora: " + Horario.toLocalTime() + "\n";
			}
			i++;
		}
		return horarios;
	}
	
	/**
	 * Description : Este método se encarga de verificar si la pelicula seleccionada se encuentra en presentación
	 * y en caso de que sí, retorna el objeto salaCine que la esta presentando
	 * @return <b>SalaDeCine</b> : Retorna el objeto de sala de cine que coincide con la pelicula
	 * */
	public SalaCine obtenerSalaDePeliculaEnPresentacion() {
		for (SalaCine SalaDecine : getSalasDeCine()) {
			if (this.equals(SalaDecine.getPeliculaEnPresentacion())){
				return SalaDecine;
			}
		}
		return null;
	}
	
	/**
	 * Description : Este método se encarga de generar un String que se imprimirá en pantalla con el fin de visualizar
	 * el estado de de los asientos en la sala virtual
	 * @param fecha : Recibe un LocalDateTime que corresponde a la llave de horarios que seleccionó el cliente
	 * @return <b>resultado</b> : Retorna un string con las posiciones de los asientos y su disponibilidad
	 * */
	public String mostrarAsientosSalaVirtual(LocalDateTime fecha) {
		StringBuilder resultado = new StringBuilder("Asientos de Cine\n");
	    resultado.append("   ");
	    
	    // Agregar números de columnas
	    for (int i = 0; i < this.getHorarios().get(fecha).length; i++) {
	        resultado.append(String.format("%-4d", i + 1));
	    }
	    resultado.append("\n");

	    // Mostrar asientos
	    for (int i = 0; i < this.getHorarios().get(fecha).length; i++) {
	        resultado.append(String.format("%-2d ", i + 1));
	        for (int j = 0; j < this.getHorarios().get(fecha).length; j++) {
	            resultado.append("[");
	            resultado.append((this.getHorarios().get(fecha)[i][j] == 1) ? "X" : "O");
	            resultado.append("] ");
	        }
	        resultado.append("\n");
	    }

	    return resultado.toString();
	}
	
	/**
	 * Description : Este método se encarga cambiar la desponibilidad de un índice de la salaVirtual
	 * @param fecha : Recibe el dato de la fecha, en formato localDateTime, seleccionado por el cliente que se pasará a la llave de horarios
	 * @param fila : Recibe el número de la fila seleccionada por el cliente
	 * @param columna : Recibe el número de la columna seleccionada por el cliente
	 * */
	public void modificarSalaVirtual(LocalDateTime fecha, int fila, int columna) {
	    
		if (this.getHorarios().get(fecha)[fila - 1][columna - 1] == 0) {
			this.getHorarios().get(fecha)[fila - 1][columna - 1] = 1;	
		}else {
			this.getHorarios().get(fecha)[fila - 1][columna - 1] = 0;	
		}	
	}
	
	/**
	 * Description : Este método se encarga revisar la desponibilidad de un índice de la salaVirtual
	 * @param day : Recibe el dato del localDateTime seleccionado por el cliente con la que se obtendrá su valor del LinkedHashMap
	 * @param fila : Recibe el número de la fila seleccionada por el cliente
	 * @param columna : Recibe el número de la columna seleccionada por el cliente
	 * */
	public boolean isDisponibilidadAsientoSalaVirtual(LocalDateTime fecha, int fila, int columna) {
	    
		if (this.getHorarios().get(fecha)[fila - 1][columna - 1] == 0) {
			return true;	
		}else {
			return false;	
		}	
	}
	
	/**
	 * Description : Este método se encarga de crear una matriz que representa la sala virtual
	 * posteriormente esta se pasa como valor a una llave y se introducen en el diccionario de horarios
	 * @param fecha : Este método recibe del administrador un LocalDateTime para crear la salaDeCineVirtual 
	 * @return <b>Void</b> : No retorna nada, solo añade un par clave, valor a horarios
	 * */
	public void crearSalaVirtual(LocalDateTime fecha) {
		int[][] nuevaSalaVirtual = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				nuevaSalaVirtual[i][j] = 0;
			}
		}
		
		this.getHorarios().put(fecha, nuevaSalaVirtual);
		
	}
	
	/**
	 * Description : Este método se encarga de filtar las películas en cartelera cuya categoría es para niños
	 * @return <b>ArrayList</b> : Retorna una lista con las peliculas filtradas por el criterio anterior 
	 * */
	public ArrayList<Pelicula> mostrarCarteleraInfantil(){
		ArrayList<Pelicula> carteleraInfantil = new ArrayList<Pelicula>();
		for (Pelicula pelicula : cartelera) {
			if ((Integer.parseInt(pelicula.clasificacion)) < 18) {
				carteleraInfantil.add(pelicula);
			}
		}
		return carteleraInfantil;
	}
	
	//Crear método para generar menú cartelera
	/**
	 * Description : Este método se encarga de crear un string que se imprimirá en pantalla para visualizar las 
	 * películas en cartelera
	 * @return <b>String</b> : Retorna un string con el nombre, duración y formato de las películas en cartelera
	 * */
	public static String mostrarCartelera(){
		String resultado = null;
		int i = 1;
		for (Pelicula pelicula : cartelera) {
			if (resultado == null) {
				resultado = i + ". Película: " + pelicula.getNombre() + "; Duración: " + pelicula.getDuracion().toMinutes() + " Minutos" 
				+ "; Formato: " + pelicula.getTipoDeFormato() + "; Precio: " + pelicula.getPrecio() + "\n";
			}else {
				resultado = resultado + i + ". Película: " + pelicula.getNombre() + "; Duración: " 
				+ pelicula.getDuracion().toMinutes() + " Minutos"  + "; Formato: " + pelicula.getTipoDeFormato() + 
				"; Precio: " + pelicula.getPrecio() + "\n";
			}
			i++;
		}
		return resultado;
		
	}
	
	/**
	 * Description : Este método se encarga de retornar la llave del LinkedHashMap
	 * a partir de la posición pasada como parámetro
	 * @param posicion : Recibe un entero que representa la posición del horario 
	 * seleccionado por el usuario luego de mostrarHorarios de esta película
	 * @return <b>LocalDateTime</b> : Retorna la localDateTime correspondiente a la elcción hecha por el usuario
	 * el formato linkedHashMap garantiza que siempre retorna la localDateTime deseada
	 * */
	public LocalDateTime obtenerHorario(int posicion){
		int i = 0;
		LocalDateTime horarioDeseado = null;
		for (LocalDateTime horario : this.getHorarios().keySet()) {
			if (i == (posicion - 1)) {
				horarioDeseado = horario;
			}
			i++;
		}
		
		return horarioDeseado;
	}
	
	/**
	 * Description : Este método se encarga de buscar si la pelicula que ejecuta este método se encuentra en presentación, la 
	 * utilidad de este método radica en que si retorna algo distinto de null se ejecuta un menú adicional antes de mostrar el
	 * horario de la pelicula seleccionada por el usuario
	 * @return <b>SalaCine</b> : Este método retorna la salaDeCine donde está siendo proyectada la película, en caso de estarlo,
	 * si la película no se encuentra en presentacion, retorna null.
	 * */
	public SalaCine whereIsPeliculaEnPresentacion() {
		for (SalaCine salaDeCine : Pelicula.getSalasDeCine()) {
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
	 * Description: Este método se encarga de buscar la salaDeCine en el array de salasDeCine
	 *  que tiene el mismo numero de sala de la película para luego retornarlo
	 * @return : Este método retorna la salaDeCine que tiene el mismo número de sala.
	 * */
	public SalaCine obtenerSalaDeCineConCodigo() {
		for (SalaCine salaDeCine : Pelicula.getSalasDeCine()) {
			try {
				if (this.getNumeroDeSala() == salaDeCine.getNumeroSala()) {
					return salaDeCine;
				}
			}catch(NullPointerException e) {
				continue;
			}
			
		}
		return null;
	}
	
	
}
