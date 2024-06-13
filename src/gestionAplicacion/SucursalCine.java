package gestionAplicacion;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Ticket;

public class SucursalCine {
	private String lugar;
	private static LocalDateTime fechaActual = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
	private ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	private ArrayList<Producto> inventarioCine = new ArrayList<>();
	private ArrayList<Pelicula> cartelera = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private static ArrayList<SucursalCine> sucursalesCine = new ArrayList<>();
	private ArrayList<Bono> bonosCreados = new ArrayList<>();
	private static ArrayList<Pelicula> peliculasDisponibles = new ArrayList<>();
	public static final LocalTime FIN_HORARIO_LABORAL = LocalTime.of(21, 00);
	public static final LocalTime INICIO_HORARIO_LABORAL = LocalTime.of(10, 0);
	//private static ArrayList<Cliente> clientes = new ArrayList<>();
	
	//Methods
	
	/**
	 * Description : Este método se encarga de crear un string que se imprimirá en pantalla para visualizar las 
	 * sucursales de nuestra franquicia a nivel nacional
	 * @return <b>String</b> : Retorna un string con el lugar de nuestras distintas dependencias, con el fin de que el cliente elija a cual de estas desea
	 * ingresar
	 * */
	public static String mostrarSucursalCine(){
		String resultado = null;
		int i = 1;
		for (SucursalCine sucursal : SucursalCine.getSucursalesCine()) {
			if (resultado == null) {
				resultado = "\n" + i + ". Sucursal Cinemar en "  + sucursal.getLugar();
			}else {
				resultado = resultado + "\n" + i + ". Sucursal Cinemar en "  + sucursal.getLugar();
			}
			i++;
		}
		return resultado;
		
	}
	
	/**
	 * Description : Este método se encarga de añadir las salas de cine a las sucursales de cine correspondientes
	 * @return <b>void</b> : Este método no retorna nada, solo se encarga de añadir las salas de cine a las sucursales de cine correspondientes
	 * */
	public static void añadirSalasCineSede () {
		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
			for (SalaCine salaCine : SalaCine.getSalasCine()) {
				if (salaCine.getUbicacionSede().equals(sede)) {
					sede.getSalasDeCine().add(salaCine);
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de actualizar las salas de todas las sedes, para esto, iteramos sobre el ArrayList de las sedes,
	 * luego iteramos sobre el ArrayList de las salas de cine de cada sala y la sede la pasamos
	 * como parámetro al método actualizarPeliculasEnPresentacion de la clase SalaCine.
	 * */
	public static void actualizarPeliculasSalasDeCine() {
		
		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
			//Evaluamos si la sala de cine en cuestion necesita un cambio de película en presentación
			for (SalaCine salaDeCine : sede.getSalasDeCine()) {
				try {
					if ( !(salaDeCine.getHorarioPeliculaEnPresentacion().plus(salaDeCine.getPeliculaEnPresentacion().getDuracion()).isAfter(SucursalCine.getFechaActual()) ) ) {
						salaDeCine.actualizarPeliculasEnPresentacion(sede);
					}
				}catch(NullPointerException e) {
					salaDeCine.actualizarPeliculasEnPresentacion(sede);
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de eliminar los horarios que ya no pueden ser presentados luego de un salto de tiempo (Usar la sala de espera)
	 * de todas las películas, para esto, obtenemos la película con la mayor duración, con la idea de no eliminar una película que aún puede ser
	 * presentada, luego, iteramos sobre los horarios de todas las películas y comparamos si es menor al horario actual luego de 
	 * que le restamos a este la duración máxima obtenida anteriormente, en caso de que sí, lo eliminamos.  
	 * */
	public static void dropHorariosVencidos() {
		Duration maxDuration = Duration.ofSeconds(0);
		Duration auxMaxDuration = Duration.ofSeconds(0);
		int comparacionDuraciones = 0;
		
		//Obtenemos la película con la duración máxima
		for (Pelicula pelicula : SucursalCine.getPeliculasDisponibles()) {
			auxMaxDuration = pelicula.getDuracion();
			comparacionDuraciones = auxMaxDuration.compareTo(maxDuration);
			
			if(comparacionDuraciones > 0) {
				maxDuration = auxMaxDuration;
			}
			
		}
		
		ArrayList<LocalDateTime> horariosPeliculaAEliminar = new ArrayList<>();
		//Eliminamos con certeza todos los horarios que fueron omitidos
		for (Pelicula pelicula : SucursalCine.getPeliculasDisponibles()) {
			for (LocalDateTime horario : pelicula.getHorarios().keySet()) {
				if (horario.isBefore(fechaActual.minus(maxDuration))) {
					horariosPeliculaAEliminar.add(horario);
				}
			}
			for (LocalDateTime horarioAEliminar : horariosPeliculaAEliminar) {
				pelicula.getHorarios().remove(horarioAEliminar);
			}
			
			horariosPeliculaAEliminar.clear();
		}
	}
	
	/**
	 * Description : Este método se ecanrga de crear 20 horarios por cada película en cartelera de la sucursal de cine, teniendo en cuenta los siguientes
	 * criterios: 1. El horario en el que se presentará la película se encuentra entre el horario de apertura y cierre de nuestras instalaciones
	 * 2. La hora a la que termina la película es menor a la hora de cierre.
	 * @return <b>void</b>: Este método no retorna nada.
	 * */
	public void crearHorariosPeliculasPorSala() {
		
		ArrayList<Pelicula> peliculasDeSalaDeCine = new ArrayList<>();
		
		final Duration LIMPIEZA_DE_SALA = Duration.ofMinutes(30);
		
		for(SalaCine salaDeCine : this.salasDeCine) {
			
			LocalDateTime horarioParaPresentar = SucursalCine.fechaActual;
			LocalDateTime auxHorarioParaPresentar = SucursalCine.fechaActual.plusDays(1);
			
			for(Pelicula pelicula : this.cartelera) {
				if (salaDeCine.getNumeroSala() == pelicula.getNumeroDeSala()) {
					peliculasDeSalaDeCine.add(pelicula);
				}
			}
			
			for (int i = 1; i <= 20; i++) {
				
				for (Pelicula pelicula : peliculasDeSalaDeCine) {
					
					if (horarioParaPresentar.toLocalTime().isBefore(FIN_HORARIO_LABORAL) &&
							horarioParaPresentar.toLocalTime().isAfter(INICIO_HORARIO_LABORAL) &&
							horarioParaPresentar.plus(pelicula.getDuracion()).toLocalTime().isBefore(FIN_HORARIO_LABORAL) &&
							horarioParaPresentar.plus(pelicula.getDuracion()).toLocalDate().equals(horarioParaPresentar.toLocalDate()) ) {
						
						pelicula.crearSalaVirtual(horarioParaPresentar);
						horarioParaPresentar = horarioParaPresentar.plus(pelicula.getDuracion());
						horarioParaPresentar = horarioParaPresentar.plus(LIMPIEZA_DE_SALA);
						
					}else {
						
						if (horarioParaPresentar.toLocalDate().isEqual(auxHorarioParaPresentar.toLocalDate())) {
							horarioParaPresentar = horarioParaPresentar.withHour(INICIO_HORARIO_LABORAL.getHour())
									.withMinute(INICIO_HORARIO_LABORAL.getMinute());
						}else {
							horarioParaPresentar = horarioParaPresentar.plusDays(1);
							horarioParaPresentar = horarioParaPresentar.withHour(INICIO_HORARIO_LABORAL.getHour())
									.withMinute(INICIO_HORARIO_LABORAL.getMinute());
						}
						
						auxHorarioParaPresentar = horarioParaPresentar.plusDays(1);
						pelicula.crearSalaVirtual(horarioParaPresentar);
						horarioParaPresentar = horarioParaPresentar.plus(pelicula.getDuracion());
						horarioParaPresentar = horarioParaPresentar.plus(LIMPIEZA_DE_SALA);
						
					}
				}
			}
			
			peliculasDeSalaDeCine.clear();
			
		}
		
	}
	
	/**
	 * Description : Este método se encarga de iterar sobre el array de Sucursales, con el fin de que cada sucursal se encargue de llamar al método 
	 * crearHorarioPeliculasPorSala().
	 * @return <b>void</b> : Este método no retorna nada. 
	 * */
	public static void crearHorariosPeliculasPorSucursal() {
		for (SucursalCine sede : SucursalCine.sucursalesCine) {
			sede.crearHorariosPeliculasPorSala();
		}
	}
	
	
	//Constructor
	
	public SucursalCine(String lugar) {
		this.lugar = lugar;
		SucursalCine.getSucursalesCine().add(this);
	}
	
	public SucursalCine(String lugar, LocalDateTime fechaActual, ArrayList<SalaCine> salasDeCine,
			ArrayList<Producto> inventarioCine, ArrayList<Pelicula> peliculasDisponibles,
			ArrayList<Ticket> ticketsCreados, ArrayList<Bono> bonosCreados) {
		super();
		this.lugar = lugar;
		SucursalCine.fechaActual = fechaActual;
		this.salasDeCine = salasDeCine;
		this.setInventarioCine(inventarioCine);
		this.cartelera = peliculasDisponibles;
		this.ticketsCreados = ticketsCreados;
		this.bonosCreados = bonosCreados;
		SucursalCine.getSucursalesCine().add(this);
	}
	
	//Getters and Setters
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public static LocalDateTime getFechaActual() {
		return fechaActual;
	}
	public static void setFechaActual(LocalDateTime fechaActual) {
		SucursalCine.fechaActual = fechaActual;
	}
	public ArrayList<SalaCine> getSalasDeCine() {
		return salasDeCine;
	}
	public void setSalasDeCine(ArrayList<SalaCine> salasDeCine) {
		this.salasDeCine = salasDeCine;
	}

	public ArrayList<Pelicula> getCartelera() {
		return cartelera;
	}
	public void setCartelera(ArrayList<Pelicula> cartelera) {
		this.cartelera = cartelera;
	}
	public ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}
	public void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		this.ticketsCreados = ticketsCreados;
	}
	public static ArrayList<SucursalCine> getSucursalesCine() {
		return sucursalesCine;
	}
	public static void setSucursalesCine(ArrayList<SucursalCine> sucursalesCine) {
		SucursalCine.sucursalesCine = sucursalesCine;
	}
	public ArrayList<Bono> getBonosCreados() {
		return bonosCreados;
	}
	public void setBonosCreados(ArrayList<Bono> bonosCreados) {
		this.bonosCreados = bonosCreados;
	}

	public static ArrayList<Pelicula> getPeliculasDisponibles() {
		return peliculasDisponibles;
	}

	public static void setPeliculasDisponibles(ArrayList<Pelicula> peliculasDisponibles) {
		SucursalCine.peliculasDisponibles = peliculasDisponibles;
	}
	public ArrayList<Producto> getInventarioCine() {
		return inventarioCine;
	}

	public void setInventarioCine(ArrayList<Producto> inventarioCine) {
		this.inventarioCine = inventarioCine;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	
	

}
