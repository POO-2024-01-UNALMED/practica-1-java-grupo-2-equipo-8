package gestionAplicacion.proyecciones;
import java.util.ArrayList;

public class Pelicula{
	
	private String nombre;
	private int precio;
	private String genero;
	private String duracion;
	private String clasificacion;
	private ArrayList<Horario> horarios = new ArrayList<>();
	private static ArrayList<Pelicula> cartelera = new ArrayList<>();
	private String tipoDeFormato;
	private int numeroDeSala;
	private int idPelicula; 
	private static ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	
	public Pelicula(){}
	
	public Pelicula(String nombre, int precio, String genero, String duracion, String clasificacion,
			ArrayList<Horario> horarios, String tipoDeFormato, int numeroDeSala, int idPelicula) {
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
	
	private Pelicula mostarPeliculaSolicitada() {return new Pelicula();}
	private void modificarSalaVirtual() {}
}
