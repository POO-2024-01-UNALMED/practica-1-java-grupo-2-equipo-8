package gestionAplicacion;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import baseDatos.Deserializador;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TarjetaCinemar;
import gestionAplicacion.usuario.Ticket;

public class SucursalCine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static LocalDateTime fechaActual = LocalDateTime.now();
	private static LocalDate fechaValidacionNuevoDiaDeTrabajo;
	private static LocalDate fechaRevisionLogicaDeNegocio;
	private static final LocalTime FIN_HORARIO_LABORAL = LocalTime.of(23, 00);
	private static final LocalTime INICIO_HORARIO_LABORAL = LocalTime.of(10, 00);
	private static final Duration LIMPIEZA_SALA_DE_CINE = Duration.ofMinutes(30); 
	
	private static ArrayList<SucursalCine> sucursalesCine = new ArrayList<>();
	private static ArrayList<Pelicula> peliculasDisponibles = new ArrayList<>();
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private static ArrayList<Arkade> juegos = new ArrayList<>();
	private static ArrayList<MetodoPago> metodosDePagoDisponibles = new ArrayList<>();
	
	private String lugar;
	private ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	private ArrayList<Producto> inventarioCine = new ArrayList<>();
	private ArrayList<Pelicula> cartelera = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Bono> bonosCreados = new ArrayList<>();
	private ArrayList<TarjetaCinemar> InventarioTarjetasCinemar = new ArrayList<>();
	
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
		
		for (SucursalCine sucursal : sucursalesCine) {
			
			resultado.append("\n" + i + ". Sucursal Cinemar en "  + sucursal.lugar);
			i++;
			
		}
		
		return resultado.toString();
		
	}
	
	/**
	 * Description : Este método se encarga de actualizar las salas de todas las sedes, para esto, iteramos sobre el ArrayList de las sedes,
	 * luego iteramos sobre el ArrayList de las salas de cine de cada sede y actualizamos la sala de cine en caso de ser necesario.
	 * (Este método es synchronized debido a que existe la posibilidad de que sea llamado en algún punto por ambos hilos al mismo tiempo.
	 * En caso de que el cliente termine de ver una película o use la sala de espera).
	 * */
	public static void actualizarPeliculasSalasDeCine() {
		
		for (SucursalCine sede : sucursalesCine) {
			//Evaluamos si la sala de cine en cuestion necesita un cambio de película en presentación
			for (SalaCine salaDeCine : sede.salasDeCine) {
				if (salaDeCine.isHorariosPresentacionDia()) {
					//try en caso de que sea la primera vez que se realiza este proceso y el horarioPeliculaEnPresentacion sea nulo
					try {
						//Solo actualizamos las salas de cine que estrictamente deban ser actualizadas
						if ( !(salaDeCine.getHorarioPeliculaEnPresentacion().plus(salaDeCine.getPeliculaEnPresentacion().getDuracion().plus(LIMPIEZA_SALA_DE_CINE)).isAfter(fechaActual) ) ) {
							
							salaDeCine.actualizarPeliculasEnPresentacion(sede);
							//Revisamos si la sala de cine tiene más presentaciones durante este día
							salaDeCine.tieneMasHorariosPresentacionHoy();
							
						}
					}catch(NullPointerException e) {
						salaDeCine.actualizarPeliculasEnPresentacion(sede);
						
					}
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de eliminar los horarios que ya no pueden ser presentados luego de un salto de tiempo 
	 * (usar la sala de espera o deserialización) de todas las películas de cada sucursal, para esto, obtenemos la película con la mayor duración, 
	 * con la idea de no eliminar una película que aún puede ser presentada, luego, iteramos sobre los horarios de todas las películas 
	 * y comparamos si es menor al horario actual luego de  que le restamos a este la duración máxima obtenida anteriormente, 
	 * en caso de que sí, lo eliminamos. (Este método es synchronized ya que existe un caso en el cual sea ejecutado por ambos hilos al mismo tiempo). 
	 * */
	public static void dropHorariosVencidos() {
		
		Duration maxDuration;
		int comparacionDuraciones;
		
		//Iteramos sobre las sucursales
		for (SucursalCine sede : sucursalesCine) {
			maxDuration = Duration.ofSeconds(0);
			comparacionDuraciones = 0;
			
			//Obtenemos la película con la duración máxima de las películas en cartelera de la sede
			for (Pelicula pelicula : sede.cartelera) {
				
				comparacionDuraciones = pelicula.getDuracion().compareTo(maxDuration);
				
				if(comparacionDuraciones > 0) {
					maxDuration = pelicula.getDuracion();
				}
				
			}

			//Iteramos sobre las películas en cartelera
			for (Pelicula pelicula : sede.cartelera) {
				
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
	 * Description : Este método se ecanrga de crear 20 horarios por cada película en cartelera de la sucursal de cine, teniendo en cuenta los siguientes
	 * criterios: 
	 * 1. El horario en el que se presentará la película se encuentra entre el horario de apertura y cierre de nuestras instalaciones.
	 * 2. La hora a la que termina la película es menor a la hora de cierre. 
	 * 3. Al finalizar una película se tiene en cuenta el tiempo de limpieza de la sala de cine.
	 * 4. La creación de horarios no exceda una semana (Para ejecutar correctamente la lógica semanal de nuestro cine).
	 * */
	public void crearHorariosPeliculasPorSala() {
		
		ArrayList<Pelicula> peliculasDeSalaDeCine = new ArrayList<>();
		
		final LocalDateTime limiteCreacionHorariosPeliculas =  fechaActual.withMinute(0).withSecond(0).withNano(0).plusWeeks(1);
		
		//Iteramos sobre las salas de cine de esta sucursal
		for(SalaCine salaDeCine : this.salasDeCine) {
			
			LocalDateTime horarioParaPresentar = fechaActual.withMinute(0).withSecond(0).withNano(0);
			
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
						horarioParaPresentar = horarioParaPresentar.plus(LIMPIEZA_SALA_DE_CINE);
						
					}else {
						
						//En caso de ejecutar el programa en la madrugada/mañana (Antes del inicio laboral) no se cumple esta condición
						//El problema de no verficar esto es que se crearían los horarios un día después a partir del horario actual
						//En caso de que sea necesario pasar al día siguiente para crear los próximos horarios
						if (horarioParaPresentar.toLocalTime().isAfter(INICIO_HORARIO_LABORAL)) {
							horarioParaPresentar = horarioParaPresentar.plusDays(1);
						}
							
						//Nos ubicamos en el inicio de la jornada laboral
						horarioParaPresentar = horarioParaPresentar.withHour(INICIO_HORARIO_LABORAL.getHour())
								.withMinute(INICIO_HORARIO_LABORAL.getMinute());
						
						//Creamos el horario y nos preparamos para crear el siguiente horario disponible
						pelicula.crearSalaVirtual(horarioParaPresentar);
						horarioParaPresentar = horarioParaPresentar.plus(pelicula.getDuracion());
						horarioParaPresentar = horarioParaPresentar.plus(LIMPIEZA_SALA_DE_CINE);
						
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
	 * Description: Este método se encarga de realizar la distribución de películas en las salas de cine y la creación de
	 * sus horarios, cada semana, luego de haber efectuado el cambio de películas de sucursal propio de la funcionalidad 3. 
	 * */
	public static void logicaSemanalReservarTicket() {
		for (SucursalCine sede : sucursalesCine) {
			sede.distribuirPeliculasPorSala();
			sede.crearHorariosPeliculasPorSala();
		}
	}
	
	/**
	 * Description: Este método se encarga de ejecutar toda la lógica para realizar reservas de ticket por primera vez,
	 * se compone de 3 puntos principales:
	 * 1. Distribuir las películas en cartelera de cada sucursal de forma equitativa respecto a sus salas de cine.
	 * 2. Una vez realizada la distribución, crear los horarios en los que se presentará cada película.
	 * 3. Por último actualizar las películas cuyo horarios se esta presentando en estos momentos.
	 * */
	public static void logicaSistemaReservarTicket() {
		SucursalCine.logicaSemanalReservarTicket();
		SucursalCine.actualizarPeliculasSalasDeCine();
		fechaValidacionNuevoDiaDeTrabajo = fechaActual.toLocalDate().plusDays(1);
		fechaRevisionLogicaDeNegocio = fechaActual.toLocalDate().plusWeeks(1);
	}
	
	/**
	 * Description : Este método se encarga de revisar la posibilidad de que una sala de cine pueda ser actualizada durante ese día,
	 * Este lógica se ejecuta con el fin de que al inicio del día se revisa si la sala de cine tendrá películas en presentación, para ser actualizada
	 * durante la jornada laboral por el hilo.
	 * */
	public static void actualizarPermisoPeticionActualizacionSalasCine() {
		for (SucursalCine sede : sucursalesCine) {
			for (SalaCine salaDeCine : sede.salasDeCine) {
				salaDeCine.tieneMasHorariosPresentacionHoy();
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de eliminar los tickets caducados de todos los clientes, con el fin de 
	 * liberar espacio en memoria y evitar problemas en la lógica del programa, luego de ejecutar la serialización.
	 * */
	public static void dropTicketsCaducados() {
		for (Cliente cliente : clientes) {
			cliente.dropTicketsCaducados();
		}
	}
	
	/**
	 * @Override
	 * Description : Este método se encarga de avanzar la hora y ejecutar la lógica de negocio en 3 plazos:
	 * 
	 * 1. Durante la jornada laboral: Actualiza las salas de cine, ubicando las películas en presentación en sus respectivas salas.
	 * 
	 * 2. Diariamente: Mejorar documentación
	 * (Limpia el array de tickets generados, con el fin de tener únicamente aquellos tickets que pueden usarse para generar descuentos
	 * y verifica la fecha de expedición de las memebresías de cada uno de los clientes).
	 * 
	 * 3. Semanalmente: Mejorar documentación
	 * (Cambia las películas de sucursal según su rendimiento, distribuye de nuevo las películas en sus salas de cine y crea los horarios de presentación
	 * semanal).
	 * */
	public static void avanzarTiempo() {
		
		//Actualiza la hora a la hora actual
		SucursalCine.setFechaActual(LocalDateTime.now());
		
		if (fechaActual.toLocalTime().isBefore(FIN_HORARIO_LABORAL) 
				&& fechaActual.toLocalTime().isAfter(INICIO_HORARIO_LABORAL) ) {
			//Lógica durante la jornada laboral
			SucursalCine.actualizarPeliculasSalasDeCine();
			
		}
		
		//Esta como after o equal debido a que en caso de serializar y desearilizar un día o más después podamos ejecutar esta lógica
		if (!fechaActual.toLocalDate().isBefore(fechaValidacionNuevoDiaDeTrabajo)) {
			//Lógica a evaluar cada día
			
			fechaValidacionNuevoDiaDeTrabajo = fechaValidacionNuevoDiaDeTrabajo.plusDays(1);
			
			//Se reestablece la posibilidad de consultar si una sala tiene actualizaciones durante este día
			SucursalCine.actualizarPermisoPeticionActualizacionSalasCine();
			
			//Implementar método aquí para eliminar TicketsCreados para aplicar descuentos por productos de forma efectiva (Hecho)
			//Con el serializador presenta problemas, implementar otra solución
			
			
			//Implementar método aquí para revisar estados de membresías y en caso de ser necesario desvincularla del cliente
			
		}
		
		//Esta como after o equal debido a que en caso de serializar y desearilizar un día o más después podamos ejecutar esta lógica
		if(!fechaActual.toLocalDate().isBefore(fechaRevisionLogicaDeNegocio)) {
			//Lógica a evaluar cada semana
			
			fechaRevisionLogicaDeNegocio = fechaRevisionLogicaDeNegocio.plusWeeks(1);
			
			//Implementar método de cambio de película entre sucursales según su valoración
			
			
			//Distribuir películas por salas, crear horarios para las nuevas presentaciones semanales
			SucursalCine.logicaSemanalReservarTicket();
			
		}
		
	}
	
	/**
	 * Description : Este método se encarga de solucionar los problemas que trae la serialización tras no ocuparse
	 * luego de mucho tiempo.
	 * 1. Elimina los horarios vencidos de las películas.
	 * 2. Elimina los tickets caducados de los clientes.
	 * 3. Actualiza los permisos de actualización de las salas de cine.
	 * */
	public static void repararProblemasSerializacion() {
		
		SucursalCine.dropHorariosVencidos();
		SucursalCine.dropTicketsCaducados();
		SucursalCine.actualizarPermisoPeticionActualizacionSalasCine();
		
	}
	
	/**public void cambiarPeliculaSede(Pelicula pelicula){
		
		if (pelicula.getLugar()=="Medellin") {
			setSucursalCartelera("Bucaramanga");
		}
			
		else if (pelicula.getSucursalCartelera()=="Bucaramnga") {
			pelicula.setSucursalCartelera("Marinilla");
		}	
		else if pelicula.getSucursalCartelera()=="Marinilla") {
			pelicula.setSucursalCartelera("Medellin");
	
		}
		
	**/
	
	/**public void eliminarPelicula(Pelicula pelicula) {
	 *  pelicula.remove
	 */
		
	
	
	/**public verificarCambioDeSucursal(Pelicula pelicula) {
		if (pelicula.isStrikeCambio()== false );{
			//cambiarPeliculaSede();
			
		}else {	
			pelicula.eliminarPelicula;
		}
		
		
		
	}
	**/
	
	public void logicaCalificacionPeliculas(Pelicula pelicula){	
		
		ArrayList <Pelicula> peliculasCalificadas = Pelicula.filtrarPorNombreDePelicula(pelicula.getNombre(), this.cartelera);
		double promedio =0;
		double calificacionReal=0;
		
		for(Pelicula peliculas : peliculasCalificadas) {
			promedio = promedio + peliculas.getValoracion();
			calificacionReal = promedio/peliculasCalificadas.size();
		}
		if (calificacionReal<3) {
			
		}
		
	
		
	
		
	}
	
	//Constructor
	public SucursalCine() {
		SucursalCine.sucursalesCine.add(this);
	}
	
	public SucursalCine(String lugar) {
		this.lugar = lugar;
		SucursalCine.getSucursalesCine().add(this);
	}
	
	//Getters and Setters
	public static LocalDateTime getFechaActual() {
		return fechaActual;
	}
	
	public static void setFechaActual(LocalDateTime fechaActual) {
		SucursalCine.fechaActual = fechaActual;
	}
	
	public ArrayList<Pelicula> getCartelera() {
		return cartelera;
	}
	
	public void setCartelera(ArrayList<Pelicula> cartelera) {
		this.cartelera = cartelera;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public ArrayList<SalaCine> getSalasDeCine() {
		return salasDeCine;
	}
	
	public void setSalasDeCine(ArrayList<SalaCine> salasDeCine) {
		this.salasDeCine = salasDeCine;
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

	public ArrayList<TarjetaCinemar> getInventarioTarjetasCinemar() {
		return InventarioTarjetasCinemar;
	}

	public void setInventarioTarjetasCinemar(ArrayList<TarjetaCinemar> inventarioTarjetasCinemar) {
		InventarioTarjetasCinemar = inventarioTarjetasCinemar;
	}

	public static ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(ArrayList<Cliente> clientes) {
		SucursalCine.clientes = clientes;
	}

	public static ArrayList<Arkade> getJuegos() {
		return juegos;
	}

	public static void setJuegos(ArrayList<Arkade> juegos) {
		SucursalCine.juegos = juegos;
	}

	public static ArrayList<MetodoPago> getMetodosDePagoDisponibles() {
		return metodosDePagoDisponibles;
	}

	public static void setMetodosDePagoDisponibles(ArrayList<MetodoPago> metodosDePagoDisponibles) {
		SucursalCine.metodosDePagoDisponibles = metodosDePagoDisponibles;
	}

	public static LocalDate getFechaValidacionNuevoDiaDeTrabajo() {
		return fechaValidacionNuevoDiaDeTrabajo;
	}

	public static void setFechaValidacionNuevoDiaDeTrabajo(LocalDate fechaValidacionNuevoDiaDeTrabajo) {
		SucursalCine.fechaValidacionNuevoDiaDeTrabajo = fechaValidacionNuevoDiaDeTrabajo;
	}

	public static LocalDate getFechaRevisionLogicaDeNegocio() {
		return fechaRevisionLogicaDeNegocio;
	}

	public static void setFechaRevisionLogicaDeNegocio(LocalDate fechaRevisionLogicaDeNegocio) {
		SucursalCine.fechaRevisionLogicaDeNegocio = fechaRevisionLogicaDeNegocio;
	}
	
	
	

	
}
