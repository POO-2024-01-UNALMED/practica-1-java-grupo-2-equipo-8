package gestionAplicacion.proyecciones;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pelicula{
	
	private String nombre;
	private int precio;
	private String genero;
	private String duracion;
	private String clasificacion;
	//private ArrayList<Horario> horarios = new ArrayList<>();
	private Map<String, int[][]> horarios = new HashMap<>();
	private static ArrayList<Pelicula> cartelera = new ArrayList<>(); //Listas únicas?
	private static ArrayList<Pelicula> historialPeliculas = new ArrayList<>();
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

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Map<String, int[][]> getHorarios() {
		return horarios;
	}

	public void setHorarios(Map<String, int[][]> horarios) {
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
	public Pelicula(){}

	public Pelicula(String nombre, int precio, String genero, String duracion, String clasificacion,
			Map<String, int[][]> horarios, String tipoDeFormato, int numeroDeSala, int idPelicula) {
		super();
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
	
	public ArrayList<Pelicula> filtrarNombrePeliculas(String nombrePelicula){
		ArrayList<Pelicula> filtroPeliculas = new ArrayList<Pelicula>();
		for (Pelicula pelicula : cartelera){
			if (pelicula.getNombre().equals(nombrePelicula)) {
				filtroPeliculas.add(pelicula);
			}
		}
		return filtroPeliculas;
	}
	
	public Pelicula filtrarTipoFormatoPeliculas(ArrayList<Pelicula> peliculas, String TipoDeFormato){
		for (Pelicula pelicula : peliculas){
			if (pelicula.getTipoDeFormato().equals(TipoDeFormato)) {
				return pelicula;
			}
		}
		return null;
	}
	
	public Pelicula seleccionarPeliculaSolicitada(String nombrePelicula, String TipoDeFormato) {
		for (Pelicula pelicula : cartelera){
			if (pelicula.getNombre().equals(nombrePelicula) & pelicula.getTipoDeFormato().equals(TipoDeFormato)) {
				return pelicula;
			}
		}
		return null;
	}
	
	/**
	 * @Descripcion : dfhasdjfjkajfajflksdjfksajflksajflksj
	 * @params: dklajflkfjdlkaj aksdjflkasdjlkfj jañdjflksadj
	 * @return: akjsdfklasjdfkl aksdjfsajflkajsfkj asdlkfjdsafl
	 * */
	public String mostrarHorarioPelicula() {
		String horarios = null;
		int i = 1;
		for (String Horario : this.getHorarios().keySet()) {
			if (horarios == null) {
				horarios = i + ". " + Horario + "\n";
			}else {
				horarios = horarios + i + ". " + Horario + "\n";
			}
		}
		return horarios;
	}
	
	public SalaCine obtenerSalaDePeliculaEnPresentacion() {
		for (SalaCine SalaDecine : getSalasDeCine()) {
			if (this.getNombre().equals(SalaDecine.getPeliculaEnPresentacion().getNombre())) {
				return SalaDecine;
			}
		}
		return null;
	}
	
	public String mostrarAsientosSalaVirtual(String horario) {
		String resultado = "Asientos de Cine" + "\n" + "      1   2   3   4   5   6   7   8" + "\n";
		for (int i = 0; i < this.getHorarios().get(horario).length; i++) {
			resultado = resultado + i + "    ";
			for (int j = 0; j < this.getHorarios().get(horario).length; j++) {
				resultado = resultado + "[" + this.getHorarios().get(horario)[i][j] + "]" + " ";
			}
			resultado = resultado + "\n";
		}
		return resultado;
	}
	public void modificarSalaVirtual(String horario, int fila, int columna) {
		if (this.getHorarios().get(horario)[fila][columna] == 0) {
			this.getHorarios().get(horario)[fila][columna] = 1;	
		}else {
			this.getHorarios().get(horario)[fila][columna] = 0;	
		}	
	}
	
	public void crearSalaVirtual(String Horario) {
		int[][] nuevaSalaVirtual = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				nuevaSalaVirtual[i][j] = 0;
			}
		}
		this.getHorarios().put(Horario, nuevaSalaVirtual);
	}
	
	public ArrayList<Pelicula> mostrarCarteleraInfantil(){
		ArrayList<Pelicula> carteleraInfantil = new ArrayList<Pelicula>();
		for (Pelicula pelicula : cartelera) {
			if ((Integer.parseInt(pelicula.clasificacion)) < 18) {
				carteleraInfantil.add(pelicula);
			}
		}
		return carteleraInfantil;
	}
	
	
}
