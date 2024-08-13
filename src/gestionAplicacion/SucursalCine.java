package gestionAplicacion;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Membresia;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TarjetaCinemar;
import gestionAplicacion.usuario.Ticket;

public class SucursalCine implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos estaticos serializables
	private static LocalDateTime fechaActual;
	private static LocalDate fechaValidacionNuevoDiaDeTrabajo;
	private static LocalDate fechaRevisionLogicaDeNegocio; 
	private static ArrayList<Cliente> clientes = new ArrayList<>();
	private static ArrayList<Arkade> juegos = new ArrayList<>();
	private static ArrayList<MetodoPago> metodosDePagoDisponibles = new ArrayList<>();
	private static ArrayList<Ticket> ticketsDisponibles = new ArrayList<>();
	private static ArrayList<Membresia> tiposDeMembresia = new ArrayList<>();
		
	//Atributos de instancia serializables
	private String lugar;
	private ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	private ArrayList<Producto> inventarioCine = new ArrayList<>();
	private ArrayList<Pelicula> cartelera = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	private ArrayList<Servicio> servicios = new ArrayList<>();
	private ArrayList<Bono> bonosCreados = new ArrayList<>();
	private ArrayList<TarjetaCinemar> InventarioTarjetasCinemar = new ArrayList<>();
	private int cantidadTicketsCreados;
	
	//Atributos variables
	private int idSucursal;
	private static int cantidadSucursales;
	private static final LocalTime FIN_HORARIO_LABORAL = LocalTime.of(23, 00);
	private static final LocalTime INICIO_HORARIO_LABORAL = LocalTime.of(10, 00);
	private static final Duration LIMPIEZA_SALA_DE_CINE = Duration.ofMinutes(30);
	private static ArrayList<SucursalCine> sucursalesCine = new ArrayList<>();
	
	//Methods
	
	/**
	 * Description : Este método se encarga de crear un string que se imprimirá en pantalla para visualizar las 
	 * sucursales de nuestra franquicia.
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
	 * luego iteramos sobre el ArrayList de las salas de cine de cada sede y en caso de que tenga que presentar películas ese día,
	 * actualizamos la sala de cine.
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
							
							salaDeCine.actualizarPeliculasEnPresentacion();
							//Revisamos si la sala de cine tiene más presentaciones durante este día
							salaDeCine.tieneMasHorariosPresentacionHoy();
							
						}
					}catch(NullPointerException e) {
						//Llegamos acá en caso de desearialización o primer inicio de programa
						salaDeCine.actualizarPeliculasEnPresentacion();
						//Revisamos si la sala de cine tiene más presentaciones durante este día
						salaDeCine.tieneMasHorariosPresentacionHoy();
						
					}
				}
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de eliminar los horarios que ya no pueden ser presentados luego de una semana 
	 * o luego de la deserialización de todas las películas de cada sucursal (Elimina los horarios anteriores al día 
	 * de la fecha actual). 
	 * */
	public static void dropHorariosVencidos() {
		
		//Iteramos sobre las sucursales
		for (SucursalCine sede : sucursalesCine) {

			//Iteramos sobre las películas en cartelera
			for (Pelicula pelicula : sede.cartelera) {
				
				//Iteramos sobre los horarios de esa película
				Iterator<LocalDateTime> horariosPelicula = pelicula.getHorarios().iterator();
				
				while (horariosPelicula.hasNext()) {
					LocalDateTime horario = (LocalDateTime) horariosPelicula.next();
					//Verificamos si el horarios es anterior a la fecha actual menos la duración
					if (horario.toLocalDate().isBefore(fechaActual.toLocalDate())) {
						//Eliminamos su referencia de la sala de cine virtual (Asientos y horario)
						pelicula.getAsientosVirtuales().remove(pelicula.getHorarios().indexOf(horario));
						horariosPelicula.remove();
					}
				}
			}
		}
		
	}
	
	/**
	 * Description : Este método se encarga de crear 20 horarios por cada película en cartelera de la sucursal de cine, 
	 * teniendo en cuenta los siguientes criterios: 
	 * 1. El horario en el que se presentará la película se encuentra entre el horario de apertura y cierre de nuestras 
	 * instalaciones.
	 * 2. La hora a la que termina la película es menor a la hora de cierre. 
	 * 3. Al finalizar una película se tiene en cuenta el tiempo de limpieza de la sala de cine.
	 * 4. La creación de horarios no exceda una semana (Para ejecutar correctamente la lógica semanal de nuestro cine).
	 * 5. Si varias películas serán presentadas en una sala se presentarán de forma intercalada evitando colisiones.
	 * */
	public void crearHorariosPeliculasPorSala() {
		
		ArrayList<Pelicula> peliculasDeSalaDeCine = new ArrayList<>();
		
		final LocalDate limiteCreacionHorariosPeliculas =  fechaActual.toLocalDate().plusWeeks(1);
		
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
				if (!horarioParaPresentar.toLocalDate().isBefore(limiteCreacionHorariosPeliculas)) {
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
						//El problema de no verficar esto es que se crearían los horarios un día después a partir del horario actual.
						//En caso de que sea necesario pasar al día siguiente para crear los próximos horarios
						if (horarioParaPresentar.toLocalTime().isAfter(INICIO_HORARIO_LABORAL)) {
							horarioParaPresentar = horarioParaPresentar.plusDays(1);
						}
						
						//Verificamos que no se exceda la proyección semanal de películas
						if (!horarioParaPresentar.toLocalDate().isBefore(limiteCreacionHorariosPeliculas)) {
							break;
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
			
			//Limpiamos las películas que se presentarán en la sala para continuar con la siguiente sala de cine
			peliculasDeSalaDeCine.clear();
			
		}
			
	}
		
	/**
	 * Descripition: Este método se encarga de distribuir las películas en cartelera en las distintas salas de cine 
	 * de la sucursal de cine que ejecuta este método, para esta distribución se tienen encuenta 3 casos posibles:
	 * 1. Hay menos películas que salas de cine o igual cantidad de ambas.
	 * 2. Hay más películas que salas de cine, pero caben exactamente la misma cantidad de películas en cada sala.
	 * 3. Hay más películas que salas de cine, pero al menos una sala de cine debe tener 1 película más que todas 
	 * las otras (Principio de Dirichlet o del palomar).
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
				//Distribución exacta o Principio del palomar
				cantidadMaxPeliculasPorSala = grupoPeliculasPorFormato.size() % grupoSalasPorFormato.size() == 0  ? grupoPeliculasPorFormato.size() / grupoSalasPorFormato.size() : grupoPeliculasPorFormato.size() / grupoSalasPorFormato.size() + 1;
				
				//Setteamos la sala de cine en presentación
				for (Pelicula pelicula : grupoPeliculasPorFormato) {
					
					pelicula.setSalaPresentacion(grupoSalasPorFormato.get(indice));
					pelicula.setNumeroSalaPresentacion(grupoSalasPorFormato.get(indice).getNumeroSala());
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
					pelicula.setNumeroSalaPresentacion(grupoSalasPorFormato.get(indice).getNumeroSala());
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
	 * Description: Este método se encarga de realizar los preparativos para ejecutar la lógica de la funcionalidad #3:
	 * 1. Eliminar los horarios de la semana anterior.
	 * 2. Distribución de películas en las salas de cine y la creación de sus horarios.
	 * 3. Eliminar los tickets comprados de películas de la semana anterior.
	 * */
	public static void logicaSemanalReservarTicket() {
		ticketsDisponibles.clear();
		dropHorariosVencidos();
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
	 * 3. Actualizar las películas cuyo horario se esta presentando en estos momentos.
	 * 4. Establecer las fechas cuando se ejecutarán la lógica diaria y semanal del negocio.
	 * */
	public static void logicaInicioSistemaReservarTicket() {
		
		fechaActual = LocalDateTime.now();
		logicaSemanalReservarTicket();
		actualizarPeliculasSalasDeCine();
		fechaValidacionNuevoDiaDeTrabajo = fechaActual.toLocalDate().plusDays(1);
		fechaRevisionLogicaDeNegocio = fechaActual.toLocalDate().plusWeeks(1);
	}
	
	/**
	 * Description : Este método se encarga de revisar la posibilidad de que una sala de cine pueda ser actualizada durante ese día,
	 * Este lógica se ejecuta con el fin de que al inicio del día se revisa si la sala de cine tendrá películas en presentación, 
	 * para ser actualizada durante la jornada laboral.
	 * */
	public static void actualizarPermisoPeticionActualizacionSalasCine() {
		for (SucursalCine sede : sucursalesCine) {
			for (SalaCine salaDeCine : sede.salasDeCine) {
				salaDeCine.tieneMasHorariosPresentacionHoy();
			}
		}
	}
	
	/**
	 * Description : Este método se encarga de retornar la sucursal cuyo id coincida con el pasado como parámetro, 
	 * del array de sucursales cine.
	 * @param idSucursalCine : Este método recibe como parámetro el id de la sucursal (De tipo int).
	 * @return SucursalCine : Este método retorna la sucursal (De tipo SucursalCine) cuyo id coincide con el seleccionada, 
	 * con el fin de realizar las validaciones.
	 * */
	public static SucursalCine obtenerSucursalPorId(int idSucursalCine) {
		
		for (SucursalCine sede : sucursalesCine) {
			if (sede.idSucursal == idSucursalCine) {
				return sede;
			}
		}
		
		return null;
	}
	
	/**
	 * Description : Este método se encarga de retornar la película cuyo id coincida con el pasado como parámetro, 
	 * del array de peliculas en cartelera de la sucursal.
	 * @param idPeliculaCartelera : Este método recibe como parámetro el id de la película a buscar (De tipo int).
	 * @return Pelicula : Este método retorna la pelicula (De tipo Pelicula) cuyo id coincide con el seleccionada, 
	 * con el fin de realizar las validaciones.
	 * */
	public Pelicula obtenerPeliculaPorId(int idPeliculaCartelera) {
		
		for (Pelicula pelicula : this.cartelera) {
			if (pelicula.getIdPelicula() == idPeliculaCartelera) {
				return pelicula;
			}
		}
		
		return null;
	}
	
	/**
	 * Description : Este método se encarga de retornar la sala de cine cuyo id coincida con el pasado como parámetro, 
	 * del array de salas de cine de la sucursal.
	 * @param idSalaCineSucursal : Este método recibe como parámetro el id de la sala de cine a buscar (De tipo int).
	 * @return SalaCine : Este método retorna la sala de cine (De tipo SalaCine) cuyo id coincide con el seleccionada, 
	 * con el fin de realizar las validaciones.
	 * */
	public SalaCine obtenerSalaCinePorId(int idSalaCineSucursal) {
		
		for (SalaCine salaDeCine : this.salasDeCine) {
			if (salaDeCine.getIdSalaCine() == idSalaCineSucursal) {
				return salaDeCine;
			}
		}
		
		return null;
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
	
	/**
	 * Description : Este método se encarga de revisar la validez de la membresia del cliente y,
	 * en caso de que este apunto de expirar, se le notificará con antelación (5 dias) para que pueda
	 * renovar su membresia. En caso de que se expire, se notifica y se desvincula del cliente.
	 * @param cliente : Se usa el cliente para obtener los datos de las membresias
	 * @return String : Se retorna el mensaje de advertencia en caso de que la membresia esta apunto de expirar o ya expiró.
	 */
	public static String notificarFechaLimiteMembresia(Cliente cliente) {
		
		String mensaje = "";
		//Se obtiene el objeto MetodoPago Puntos con apuntador puntos.
		if (cliente.getMembresia()!= null) {
			MetodoPago puntos = null;
			for (MetodoPago metodoPago : cliente.getMetodosDePago()) {
				if (metodoPago.getNombre().equals("Puntos")) {
					puntos = metodoPago;
					break;
				}
			}
			//Se verifica si la fecha actual esta pasada a la fecha limite de la membresia.
			if (!fechaActual.toLocalDate().isBefore(cliente.getFechaLimiteMembresia())) {
				//Se guardan la cantidad de puntos en el atributo de Cliente para no perder la acumulación.
				cliente.setPuntos(cliente.getPuntos()+(int)puntos.getLimiteMaximoPago());
				//Se obtiene el nombre de la membresia y se desvincula del cliente.
				String nombreMembresia = cliente.getMembresia().getNombre();
				cliente.getMembresia().getClientes().remove(cliente);
				cliente.setMembresia(null);
				//Se reinician sus métodos de pago en caso de perder la membresia.
				MetodoPago.asignarMetodosDePago(cliente);
				mensaje = "Su membresia ha expirado. Le invitamos a renovarla para no perder sus beneficios.";
				
				//Para volver a asignar la membresia expirada al stock de inventario, se valida con el nombre.
				for (SucursalCine sucursal : SucursalCine.getSucursalesCine()) {
					if (sucursal.getIdSucursal() == cliente.getOrigenMembresia()) {
						for (Producto productoMembresia : sucursal.getInventarioCine()) {
							if (productoMembresia.getNombre().equals(nombreMembresia)) {
								productoMembresia.setCantidad(productoMembresia.getCantidad()+1);
								break;
							}
						} break;
					}
				}
			} else if (fechaActual.toLocalDate().isAfter(cliente.getFechaLimiteMembresia().minusDays(6))
					&& fechaActual.toLocalDate().isBefore(cliente.getFechaLimiteMembresia())) {
				mensaje = "Estimado cliente, recuerde que le quedan " + 
					ChronoUnit.DAYS.between(fechaActual.toLocalDate(), cliente.getFechaLimiteMembresia()) + 
					" dia(s) para que caduzca su membresía.\nLo invitamos a actualizar su suscripción para poder disfrutar de sus beneficios.";
				
			}
		}
		return mensaje;
	}
	
	//Constructor
	public SucursalCine() {
		sucursalesCine.add(this);
		
	}
	
	public SucursalCine(String lugar) {
		this();
		cantidadSucursales++;
		this.idSucursal = cantidadSucursales;
		this.cantidadTicketsCreados = 1;
		this.lugar = lugar;
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

	public int getCantidadTicketsCreados() {
		return cantidadTicketsCreados;
	}

	public void setCantidadTicketsCreados(int cantidadTicketsCreados) {
		this.cantidadTicketsCreados = cantidadTicketsCreados;
	}

	public static ArrayList<Ticket> getTicketsDisponibles() {
		return ticketsDisponibles;
	}

	public static void setTicketsDisponibles(ArrayList<Ticket> ticketsDisponibles) {
		SucursalCine.ticketsDisponibles = ticketsDisponibles;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public static LocalTime getFinHorarioLaboral() {
		return FIN_HORARIO_LABORAL;
	}

	public static LocalTime getInicioHorarioLaboral() {
		return INICIO_HORARIO_LABORAL;
	}

	public static ArrayList<Membresia> getTiposDeMembresia() {
		return tiposDeMembresia;
	}

	public static void setTiposDeMembresia(ArrayList<Membresia> tiposDeMembresia) {
		SucursalCine.tiposDeMembresia = tiposDeMembresia;
	}
	
	

	
	/*ToDo Andy's list
	0. Optimizar código en serializador y deserializador (Hecho).
	1. Realizar testeos.
	2. Crear reloj para mostrar la hora cada vez que avanza y mover método avanzarTiempo a Administrador (Juan).
	3. Crear validaciones para deserializador (Verificar que guarde todo en el lugar correcto).
	3. Documentar serializador (Pendiente) y deserializador (Hecho).
	4. Documentar InicioDeSistema y FinDeSistema (Hecho).
	5. Hacer Merge con Master (Pendiente) y mejorar estrucutura de paquetes (Rusbel).
	6. Estudiar y crear el ejecutable.
	7. Realizar testeos grupales.
	8. Empezar documentación.
	
	

	*/
}
