package gestionAplicacion;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Ticket;

public class SucursalCine {
	
	private static LocalDateTime fechaActual = LocalDateTime.now().withMinute(14);
	private static ArrayList<SucursalCine> sucursalesCine = new ArrayList<>();
	private static ArrayList<Pelicula> peliculasDisponibles = new ArrayList<>();
	
	public static final LocalTime FIN_HORARIO_LABORAL = LocalTime.of(23, 00);
	public static final LocalTime INICIO_HORARIO_LABORAL = LocalTime.of(10, 00);
	
	private String lugar;
	private ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	private ArrayList<Producto> inventarioCine = new ArrayList<>();
	private ArrayList<Pelicula> cartelera = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Bono> bonosCreados = new ArrayList<>();
	
	
	//private static ArrayList<Cliente> clientes = new ArrayList<>();
	
	//Methods
	
	/**
	 * Description : Este método se encarga de crear un string que se imprimirá en pantalla para visualizar las 
	 * sucursales de nuestra franquicia a nivel nacional.
	 * @return <b>String</b> : Retorna un string con el lugar de nuestras distintas dependencias, con el fin de que el cliente 
	 * elija a cual de estas desea ingresar.
	 * */
	public static String mostrarSucursalCine(){
		
		StringBuilder resultado = new StringBuilder();
		int i = 1;
		
		for (SucursalCine sucursal : SucursalCine.getSucursalesCine()) {
			
			resultado.append("\n" + i + ". Sucursal Cinemar en "  + sucursal.getLugar());
			i++;
			
		}
		
		return resultado.toString();
		
	}
	
	/**
	 * Description : Este método se encarga de actualizar las salas de todas las sedes, para esto, iteramos sobre el ArrayList de las sedes,
	 * luego iteramos sobre el ArrayList de las salas de cine de cada sede y actualizamos la sala de cine en caso de ser necesario.
	 * */
	public static void actualizarPeliculasSalasDeCine() {
		
		for (SucursalCine sede : SucursalCine.sucursalesCine) {
			//Evaluamos si la sala de cine en cuestion necesita un cambio de película en presentación
			for (SalaCine salaDeCine : sede.salasDeCine) {
				//try en caso de que sea la primera vez que se realiza este proceso y el horarioPeliculaEnPresentacion sea nulo
				try {
					//Solo actualizamos las salas de cine que estrictamente deban ser actualizadas
					if ( !(salaDeCine.getHorarioPeliculaEnPresentacion().plus(salaDeCine.getPeliculaEnPresentacion().getDuracion()).isAfter(SucursalCine.fechaActual) ) ) {
						salaDeCine.actualizarPeliculasEnPresentacion(sede);
					}
				}catch(NullPointerException e) {
					salaDeCine.actualizarPeliculasEnPresentacion(sede);
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de eliminar los horarios que ya no pueden ser presentados luego de un salto de tiempo 
	 * (Usar la sala de espera o usar un thread) de todas las películas de una sucursal, para esto, obtenemos la película con la mayor duración, 
	 * con la idea de no eliminar una película que aún puede ser presentada, luego, iteramos sobre los horarios de todas las películas 
	 * y comparamos si es menor al horario actual luego de  que le restamos a este la duración máxima obtenida anteriormente, 
	 * en caso de que sí, lo eliminamos.  
	 * */
	public static void dropHorariosVencidos() {
		
		Duration maxDuration;
		Duration auxMaxDuration;
		int comparacionDuraciones;
		
		//Iteramos sobre las sucursales
		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
			maxDuration = Duration.ofSeconds(0);
			auxMaxDuration = Duration.ofSeconds(0);
			comparacionDuraciones = 0;
			
			//Obtenemos la película con la duración máxima de las películas en cartelera de la sede
			for (Pelicula pelicula : sede.getCartelera()) {
				auxMaxDuration = pelicula.getDuracion();
				comparacionDuraciones = auxMaxDuration.compareTo(maxDuration);
				
				if(comparacionDuraciones > 0) {
					maxDuration = auxMaxDuration;
				}
				
			}
			
			//Iteramos sobre las películas en cartelera
			for (Pelicula pelicula : sede.getCartelera()) {
				
				//Iteramos sobre los horarios de esa película
				Iterator<LocalDateTime> horariosPelicula = pelicula.getHorarios().iterator();
				
				while (horariosPelicula.hasNext()) {
					LocalDateTime horario = (LocalDateTime) horariosPelicula.next();
					//Verificamos si el horarios es anterior a la fecha actual menos la duración
					if (horario.isBefore(fechaActual.minus(maxDuration))) {
						//Eliminamos su referencia de la sala de cine virtual (Asientos y horario)
						pelicula.getAsientosVirtuales().remove(pelicula.getHorarios().indexOf(horario));
						horariosPelicula.remove();
					}
				}
				
			}
			
		}
		
	}
	
	/**
	 * Description : Este método se ecanrga de crear 15 horarios por cada película en cartelera de la sucursal de cine, teniendo en cuenta los siguientes
	 * criterios: 
	 * 1. El horario en el que se presentará la película se encuentra entre el horario de apertura y cierre de nuestras instalaciones
	 * 2. La hora a la que termina la película es menor a la hora de cierre 
	 * 3. Al finalizar una película se tiene en cuenta el tiempo de limpieza de la sala de cine.
	 * */
	public void crearHorariosPeliculasPorSala() {
		
		ArrayList<Pelicula> peliculasDeSalaDeCine = new ArrayList<>();
		
		final Duration LIMPIEZA_DE_SALA = Duration.ofMinutes(30);
		final LocalDateTime limiteCreacionHorariosPeliculas =  SucursalCine.fechaActual.withMinute(0).withSecond(0).withNano(0).plusWeeks(1);
		
		//Iteramos sobre las salas de cine de esta sucursal
		for(SalaCine salaDeCine : this.salasDeCine) {
			
			LocalDateTime horarioParaPresentar = SucursalCine.fechaActual.withMinute(0).withSecond(0).withNano(0);
			
			//Buscamos las películas de la cartelera a las cuales les corresponde esta sala de cine
			for(Pelicula pelicula : this.cartelera) {
				if (salaDeCine.equals(pelicula.getSalaPresentacion())) {
					peliculasDeSalaDeCine.add(pelicula);
				}
			}
			
			//Creamos 20 horarios por convención
			for (int i = 1; i <= 20; i++) {
				
				//Verificamos que no se exceda la proyección semanal de películas
				if (horarioParaPresentar.isAfter(limiteCreacionHorariosPeliculas)) {
					break;
				}
				
				//Iteramos sobre las películas que comparten sala de cine
				for (Pelicula pelicula : peliculasDeSalaDeCine) {
					
					//1 y 2 verifican que se encuentre en el horario laboral, 3 y 4 que la duración no exceda el final del horario laboral y no pase al
					//siguiente día
					if (horarioParaPresentar.toLocalTime().isBefore(FIN_HORARIO_LABORAL) &&
							horarioParaPresentar.toLocalTime().isAfter(INICIO_HORARIO_LABORAL) &&
							horarioParaPresentar.plus(pelicula.getDuracion()).toLocalTime().isBefore(FIN_HORARIO_LABORAL) &&
							horarioParaPresentar.plus(pelicula.getDuracion()).toLocalDate().equals(horarioParaPresentar.toLocalDate()) ) {
						
						//Creamos el horario y nos preparamos para crear el siguiente horario disponible
						pelicula.crearSalaVirtual(horarioParaPresentar);
						horarioParaPresentar = horarioParaPresentar.plus(pelicula.getDuracion());
						horarioParaPresentar = horarioParaPresentar.plus(LIMPIEZA_DE_SALA);
						
					}else {
						
						//Pasamos de día y nos ubicamos en el inicio de la jornada laboral 
						horarioParaPresentar = horarioParaPresentar.plusDays(1);
						horarioParaPresentar = horarioParaPresentar.withHour(INICIO_HORARIO_LABORAL.getHour())
								.withMinute(INICIO_HORARIO_LABORAL.getMinute());
						
						//Creamos el horario y nos preparamos para crear el siguiente horario disponible
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
	 * Descripition: Este método se encarga de distribuir las películas en cartelera en las distintas salas de cine de la sucursal de cine que
	 * ejecuta este método, para esta distribución se tienen encuenta 3 casos posibles:
	 * 1. Hay menos películas que salas de cine o igual cantidad de ambas.
	 * 2. Hay más películas que salas de cine, pero caben exactamente la misma cantidad de películas en cada sala.
	 * 3. Hay más películas que salas de cine, pero al menos una sala de cine debe tener 1 película más que todas las otras (Principio de Dirichlet).
	 * */
	public void distribuirPeliculasPorSala() {
		
		String[] formatos = {"2D", "3D", "4D"};
		
		ArrayList<SalaCine> grupoSalasPorFormato = new ArrayList<>();
		ArrayList<Pelicula> grupoPeliculasPorFormato = new ArrayList<>();

		int cantidadMaxPeliculasPorSala = 0;
		int indice = 0;
		int contador = 0;
		
		//Iteramos sobre los distintos formatos de películas disponibles
		for (String formato : formatos) {
			
			//Guardamos en un ArrayList las salas de cine que coinciden con el formato
			//Sobre el que estamos iterando
			for (SalaCine salaDeCine : this.salasDeCine) {
				if (salaDeCine.getTipoDeSala().equals(formato)) {
					grupoSalasPorFormato.add(salaDeCine);
				}
			}
			
			//Guardamos en un ArrayList las películas que coinciden con el formato
			//Sobre el que estamos iterando
			for (Pelicula pelicula : this.cartelera) {
				if(pelicula.getTipoDeFormato().equals(formato)) {
					grupoPeliculasPorFormato.add(pelicula);
				}
			}
			
			//Evaluamos esto con el fin de determinar si debemos distribuir de forma especial o no
			if (grupoPeliculasPorFormato.size() > grupoSalasPorFormato.size()) {
				
				//Hallamos el número máximo de películas que pueden presentarse en cada sala de cine
				// Distribución exacta o Principio del palomar
				cantidadMaxPeliculasPorSala = grupoPeliculasPorFormato.size() % grupoSalasPorFormato.size() == 0  ? grupoPeliculasPorFormato.size() / grupoSalasPorFormato.size() : grupoPeliculasPorFormato.size() / grupoSalasPorFormato.size() + 1;
				
				//Setteamos la sala de cine en presentación
				for (Pelicula pelicula : grupoPeliculasPorFormato) {
					
					pelicula.setSalaPresentacion(grupoSalasPorFormato.get(indice));
					contador++;
					
					//En caso de que el contador sea igual al número máximo de películas por sala, cambiamos de sala
					// Y reiniciamos el contador
					if (contador == cantidadMaxPeliculasPorSala) {
						contador = 0;
						indice++;
					}
					
				}
				
			}else {
				
				for (Pelicula pelicula : grupoPeliculasPorFormato) {
					
					pelicula.setSalaPresentacion(grupoSalasPorFormato.get(indice));
					indice++;
					
				}
				
			}
			
			//Reiniciamos las variables para el próximo formato
			indice = 0;
			contador = 0;
			grupoPeliculasPorFormato.clear();
			grupoSalasPorFormato.clear();
			
		}
		
	}
	
	/**
	 * Description: Este método se encarga de ejecutar toda la lógica para realizar reservas de ticket, se compone de 3 puntos principales:
	 * 1. Distribuir las películas en cartelera de cada sucursal de forma equitativa respecto a sus salas de cine
	 * 2. Una vez realizada la distribución, crear los horarios en los que se presentará cada película
	 * 3. Por último actualizar las películas cuyo horarios se esta presentando en estos momentos
	 * */
	public static void logicaSistemaReservarTicket() {
		
		for (SucursalCine sede : SucursalCine.sucursalesCine) {
			sede.distribuirPeliculasPorSala();
			sede.crearHorariosPeliculasPorSala();
		}
		
		SucursalCine.actualizarPeliculasSalasDeCine();
	}
	
	
	//Constructor
	
	public SucursalCine(String lugar) {
		this.lugar = lugar;
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
