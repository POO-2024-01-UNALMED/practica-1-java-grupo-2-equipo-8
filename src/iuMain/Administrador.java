package iuMain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.servicios.herencia.Servicio;
import gestionAplicacion.usuario.*;
import baseDatos.Deserializador;
import baseDatos.Serializador;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Administrador {
	
	static Scanner sc = new Scanner(System.in);
	
	static long readLong() {return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	public static void main(String[] args) {
		
		System.out.println("CINEMAR\n");
		
		//MAIN
		inicioDelSistema();
		
		System.out.println("Iniciar sesión");
		Cliente clienteProceso = iniciarSesion();

		System.out.println("\nIngresar a una de nuestras sedes");
		clienteProceso.setCineActual(ingresarASucursal());
		
		System.out.println("\nHola " + clienteProceso.getNombre() + " Bienvenido a Cinemar");
		inicio(clienteProceso);

		salirDelSistema();
		
	}
	      
	/**
	 * Description : Este método se encarga de iniciar el programa mostrando las opciones de las funcionalidades en
	 * un menú.
	 * @param clienteProceso : Se usa a un objeto de tipo Cliente para que pueda ser usado en las funcionalidades
	 */
	private static void inicio(Cliente clienteProceso) {
		int opcion = 0;
		
		//Método de avanzar días
		avanzarDia(clienteProceso);
		
		//Avance de tiempo, se ejecuta cada vez que regresamos al menú inicial
		avanzarTiempo();
		
		//Cada vez que se va al inicio, se verifica si ya ha pasado el dia para revisar la validez de la membresia.
		logicaMembresia(clienteProceso);
		
		do {
			
			try {
				
				opcion = 0;
				System.out.println("\n¿Qué operacion desea realizar?");
				System.out.println("1. Ingresar a sistema de proyecciones de películas");
				System.out.println("2. Ingresar a los servicios de compra"); 
				System.out.println("3. Ingresar a la zona de juegos");
				System.out.println("4. Adquirir o actualizar membresia");
				System.out.println("5. Hacer calificacion");
				System.out.println("6. Cambiar de sucursal");
				System.out.println("7. Guardar y Salir");
				opcion = Integer.parseInt(sc.nextLine());
				
			}catch(NumberFormatException e) {
				System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
			}
			
		}while(!(opcion > 0 & opcion <= 7));
		
		
		switch (opcion) {
			case 1: ingresarASistemaDeProyecciones(clienteProceso); break;
			case 2: compras(clienteProceso); inicio(clienteProceso); break;
			case 3: ingresoZonaJuegos(clienteProceso); inicio(clienteProceso); break;
			case 4: adquirirMembresia(clienteProceso); inicio(clienteProceso); break;
			case 5: calificacion(clienteProceso);inicio(clienteProceso); break;
			case 6: cambiarSucursalCine(clienteProceso); inicio(clienteProceso); break;
			case 7: salirDelSistema(); break;
			default: System.out.println("Opción invalida"); inicio(clienteProceso);
		  }
	
	}
	
	/**
	 * Description : Este método se encarga de ejecutar la lógica de aranque del programa, deserializa toda la información
	 * primero deserializa los atributos de instancia y luego deserializa los atributos estáticos, y corrige la referencia
	 * a la que apuntan los objetos deserializados, para que las funcionalidades que realizan validaciones a partir de 
	 * referencias puedan realizarlas de forma segura.
	 * */
	private static void inicioDelSistema() {
		
		//Deserializa
		Deserializador.deserializar();
		Deserializador.deserializarEstaticos();
		//Renueva referencias a los objetos deserializados
		Deserializador.asignarReferenciasDeserializador();
		
		
	}
	
	/**
	 * Description: Este método se encarga de serializar toda la información del programa y salir del sistema.
	 * */
	private static void salirDelSistema() {
		//Serialización
		
		//Atributos estáticos
		Serializador.serializar();
		
		//Atributos de instancia
		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
			Serializador.serializar(sede);
		}
		
		//Fin del programa
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
		
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
	 * 
	 * */
	private static void avanzarTiempo() {
		
		//Avanza lo hora 20 segundos
		SucursalCine.setFechaActual(SucursalCine.getFechaActual().plusSeconds(20)); 
		relojDigital(SucursalCine.getFechaActual());
		
		//Esta como after o equal debido a que en caso de serializar y desearilizar un día o más después podamos ejecutar esta lógica
		if(!SucursalCine.getFechaActual().toLocalDate().isBefore(SucursalCine.getFechaRevisionLogicaDeNegocio())) {
			//Lógica a evaluar cada semana
			
			SucursalCine.setFechaRevisionLogicaDeNegocio(SucursalCine.getFechaActual().toLocalDate().plusWeeks(1)); 
			
			//Ejecutamos la lógica semanal
			SucursalCine.logicaSemanalSistemaNegocio();
			
		}
			
		//Esta como after o equal debido a que en caso de serializar y desearilizar un día o más después podamos ejecutar esta lógica
		if (!SucursalCine.getFechaActual().toLocalDate().isBefore(SucursalCine.getFechaValidacionNuevoDiaDeTrabajo())) {
			//Lógica a evaluar cada día
			
			SucursalCine.setFechaValidacionNuevoDiaDeTrabajo(SucursalCine.getFechaActual().toLocalDate().plusDays(1));
			
			SucursalCine.logicaDiariaReservarTicket();		
		}
		
		if (SucursalCine.getFechaActual().toLocalTime().isBefore(SucursalCine.getFinHorarioLaboral()) 
				&& SucursalCine.getFechaActual().toLocalTime().isAfter(SucursalCine.getInicioHorarioLaboral()) ) {
			//Lógica durante la jornada laboral
			SucursalCine.actualizarPeliculasSalasDeCine();
			
		}
		
	}
	/**
	 * Description : Este método se encarga de ejecutar el proceso de revisar y notificar la validez de la membresía de cliente
	 * con respecto a su fecha de caducidad. Si la membresia esta por expirar en 5 días o menos, se arroja un mensaje en pantalla.
	 * @param clienteProceso : Se pide un objeto de tipo Cliente para obtener los datos necesarios en la ejecución de la lógica.
	 */
	private static void logicaMembresia(Cliente clienteProceso) {
		System.out.println(SucursalCine.notificarFechaLimiteMembresia(clienteProceso));
	}
	
	/**
	 * Description : Este método se encarga de avanzar de día en el programa cuando no hayan horarios.
	 * En caso de que falte un día para cumplir la semana laboral, se avanzará de día automáticamente.
	 * @param clienteProceso : Se pide un cliente para poder acceder a la sucursal de Cine
	 * y poder evaluar los horarios de sus salas de Cine.
	 */
	private static void avanzarDia(Cliente clienteProceso) {
		
		//Se crean variables para obtener la sucursal actual y una booleano que indica si hay horarios.
		SucursalCine sucursalActual = clienteProceso.getCineActual();
		Boolean hayHorarios = false;
		
		//Se revisa todas las salas de cine para ver si tienes horarios. De no ser el caso, se cambia el booleano a false.
		for (SalaCine salaCine : sucursalActual.getSalasDeCine()) {
			
			if (salaCine.tieneHorariosPresentacionHoy()) {
				hayHorarios = true;
				break;
			}
		}
		//El avance de dia se realiza automáticamente en caso de que no hayan horarios y falte 1 día para cumplir la semana de trabajo.
		if (!hayHorarios && SucursalCine.getFechaRevisionLogicaDeNegocio().minusDays(1).equals(SucursalCine.getFechaActual().toLocalDate())) {
			System.out.println("Hemos detectado que han concluido todas las presentaciones semanales, por lo tanto,\n"
					+ "Se ejecutará la lógica semanal del sistema de negocio y se pasará al dia siguiente de forma automática.\n"
					+ "Gracias por su compresión\n");
			SucursalCine.setFechaActual(SucursalCine.getFechaActual().plusDays(1).withHour(SucursalCine.getInicioHorarioLaboral().getHour()).withMinute(SucursalCine.getInicioHorarioLaboral().getMinute()).withSecond(0).withNano(0));
			
		//El avance de día se preguntará al usuario cuando ya no haya más peliculas por presentar.	
		} else if (!hayHorarios) {
			int opcionMenu = 0;
			try {
			System.out.println("Ya no hay más presentaciones de películas el día de hoy. ¿Desea avanzar al siguiente dia?\n1. Si.\n2. No.");
			opcionMenu = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} switch (opcionMenu) {
				case 1: SucursalCine.setFechaActual(SucursalCine.getFechaActual().plusDays(1).withHour(SucursalCine.getInicioHorarioLaboral().getHour()).withMinute(SucursalCine.getInicioHorarioLaboral().getMinute()).withSecond(0).withNano(0)); break;
				case 2: break;
			}
		}
	}

	/**
	 * Description : Este método se encarga de iniciar sesión, para esto se le pregunta al cliente el tipo de documento y el número de documento,
	 * en caso de que se encuentre registrado, se verfica su nombre y se retorna ese cliente, en caso de que no, se crea un nuevo cliente, solicitando
	 * su nombre y edad, luego se asignan sus métodos de pago y se retorna este nuevo cliente.
	 * @return <b>Cliente</b> : Este método retorna el cliente que realizó este proceso de forma exitosa.
	 * */
	private static Cliente iniciarSesion() {
		//Pedimos el tipo de documento al usuario
		TipoDeDocumento documentoCliente = null;
		boolean casoValidoConfirmacion = false;
		boolean casoValido = false;
		int opcionMenu;
		do{
			opcionMenu = 0;
			try {
				System.out.println("Seleccione el tipo de documento:\n"+ TipoDeDocumento.mostrarTiposDeDocumento());
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
				
			switch (opcionMenu) {
				case 1: documentoCliente = TipoDeDocumento.CC; casoValido=true; break;
				case 2: documentoCliente = TipoDeDocumento.TI; casoValido=true; break;
				case 3: documentoCliente = TipoDeDocumento.CE; casoValido=true; break;
				default: System.out.println("Opción invalida");
			}
				
		}while(!casoValido);	
				
		//Obtenemos al cliente que hará el proceso de interactuar con las funcionalidades
		Cliente clienteProceso = null;
		
		//Se pide al usuario su número de documento
		long numeroDocumentoCliente = 0;
		casoValido = false;
		casoValidoConfirmacion = false;
		do {
			do {
				try {
					System.out.print("\nIngrese el número de documento: ");
					numeroDocumentoCliente = Long.parseLong(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar datos numéricos correspondientes a tu número de documento");
					continue;
				}
				
				//Confirmamos si es un dato correcto
				do {
					opcionMenu = 0;
					try {
						System.out.println("Tu número de documento es: " + numeroDocumentoCliente + " \n1. Correcto \n2. Cambiar número de documento");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				switch(opcionMenu) {
					case 1: casoValidoConfirmacion = true; break;
					case 2: casoValidoConfirmacion = false; break;
					default: casoValidoConfirmacion = false; System.out.println("Opción invalida");
				}
				
				}while(!casoValidoConfirmacion);
				
				//Se verficia si el cliente existe
				clienteProceso = Cliente.revisarDatosCliente(numeroDocumentoCliente);
				
				//En caso de que no exista, lo creamos
				if (clienteProceso == null) {
					System.out.println("Hemos detectado que es la primera vez que visita nuestro cine, " +
					"Por políticas de seguridad de nuestra compañia," + 
					"le solicitamos que amablemente responda las siguientes preguntas para completar su registro");
					
					//Pedimos la edad del cliente
					int edadCliente = 0;
					casoValidoConfirmacion = false;
					do{
						try {
							System.out.println("( Edad mínima para hacer uso de nuestras instalaciones: 5 )" + "\n"
							+ "( Edad máxima para hacer uso de nuestras instalaciones : 100 )");
							System.out.print("Ingrese su edad: ");
							edadCliente = Integer.parseInt(sc.nextLine());
						}catch (NumberFormatException e) {
							System.out.println("Error, debes ingresar datos numéricos correspondientes a tu edad");
							continue;
						}
						
						//Verificamos si la edad seleccionada por el cliente es acorde a su número de documento
						if ( (documentoCliente.equals(TipoDeDocumento.CC) && edadCliente < 18) || 
						   ( (documentoCliente.equals(TipoDeDocumento.TI) && (edadCliente > 18 || edadCliente < 5) ) ) ||
						   ( (documentoCliente.equals(TipoDeDocumento.CE) && edadCliente < 5) ) ||
						   ( (edadCliente > 100) ) ){
							System.out.println("Error, debes ingresar una edad válida o una apropiada para un documento tipo: " + documentoCliente.getNombre());
							continue;
						}
						//Confirmamos si la edad ingresada es correcta
						do {
							opcionMenu = 0;
							try {
								System.out.println("Tu edad es: " + edadCliente + " \n 1. Correcto \n 2. Cambiar edad");
								opcionMenu = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("Error, debes ingresar un único dato numérico");
							}
							
						}while(!(opcionMenu == 1 || opcionMenu == 2));
						
						switch(opcionMenu) {
							case 1: casoValidoConfirmacion = true; break;
							case 2: casoValidoConfirmacion = false; break;
							default: casoValidoConfirmacion = false; System.out.println("Opción invalida");
						}
					}while(!casoValidoConfirmacion);
					
					//Pedimos el nombre del cliente
					String nombreCliente = null;
					casoValido = false;
					do {
						System.out.println("Ingrese su nombre: ");
						nombreCliente = stringMayuscula(sc.nextLine()); 
						
						//Confirmamos si el nombre ingresado es correcto
						do {
							opcionMenu = 0;
							try {
								System.out.println("Su nombre es: " + nombreCliente + "\n1. Correcto \n2. Cambiar nombre");
								opcionMenu = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("Error, debe ingresar un único dato numérico");
							}
						}while(!(opcionMenu == 1 || opcionMenu == 2));
						
						switch(opcionMenu) {
							case 1: casoValidoConfirmacion = true; break;
							case 2: casoValidoConfirmacion = false; break;
							default: casoValidoConfirmacion = false; System.out.println("Opción invalida");
						}
						
					}while(!casoValidoConfirmacion);
					
					//Creamos un nuevo cliente con la información dada
					clienteProceso = new Cliente(nombreCliente,edadCliente,numeroDocumentoCliente,documentoCliente);
					clienteProceso.setMetodosDePago(MetodoPago.asignarMetodosDePago(clienteProceso));
					casoValido = true;
					System.out.println("\nEstos son sus datos de registro: " 
					+ "\nNombre: " + clienteProceso.getNombre() 
					+ "\nIdentificacion: "+ clienteProceso.getDocumento() 
					+ "\nEdad: " + clienteProceso.getEdad() + "\n");
				}
				//En caso de que el cliente exista
				else {
					do {
						opcionMenu = 0;
						try {
							System.out.println("¿Eres " + clienteProceso.getNombre() + "?\n1. SI\n2. NO");
							opcionMenu = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("Error, debes ingresar un único dato numérico");
						}
						
						switch(opcionMenu) {
							case 1: 
								System.out.println("\nEstos son sus datos de registro: " + 
								"\nNombre: " + clienteProceso.getNombre() 
								+ "\nIdentificacion: "+ clienteProceso.getDocumento() 
								+ "\nEdad: " + clienteProceso.getEdad() + "\n");
								casoValido = true;
								casoValidoConfirmacion = true;
								break;
							case 2:
								System.out.println("Verifica el numero de documento\n");
								casoValidoConfirmacion = true;
								casoValido = false;
								break;
							default: 
								System.out.println("Digite una opción valida"); 
								casoValidoConfirmacion = false;
						}
					}while(!casoValidoConfirmacion);
				}
		}while(!casoValido);	
	
		return clienteProceso;
		
	}
	
	/**
	 * Description: Este metodo se encarga de convertir las primeras letras de las palabras de un String en mayuscula
	 * @param input : Es el string que el usuario proporciona
	 * @return String : retorna el String convertido.
	 */
	private static String stringMayuscula(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
	
	/**
	 * Description : Este método se encarga de realizar el proceso de selección de alguna de nuestras dependencias según los datos 
	 * ingresados por el cliente, para esto, mostramos en pantalla nuestras distintas sedes, el cliente elige una de estas y una vez confirmemos
	 * su elección, retornamos la sucursal de nuestra cine a la cual desea acceder.
	 * @return <b>SucursalCine</b> : Este método se encarga de retornar la sucursal de nuestro cine (De tipo SucursalCine) a la cuál el cliente
	 * intenta acceder, con el fin de que el proceso de las funcionalidades ocurra en el contexto de alguna de nuestras sucursales.
	 * */
	private static SucursalCine ingresarASucursal() {
		
		boolean casoValido = false;
		int opcionMenu = 0;
		
		SucursalCine sucursalCineProceso = null;
		
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("A continuación se presentará la ubicación de nuestras distintas sedes\n" + 
				    "Por favor elige a cuál de las siguientes deseas acceder: " + SucursalCine.mostrarSucursalCine());
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
				}
			}while(!(opcionMenu > 0 & opcionMenu < Integer.valueOf(SucursalCine.getSucursalesCine().size()) + 1 ) );
			
			sucursalCineProceso = SucursalCine.getSucursalesCine().get(opcionMenu - 1);
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("Usted ha seleccionado nuestra sede ubicada en " + sucursalCineProceso.getLugar() +
					" ¿Es esto correcto?\n1. Correcto\n2. Cambiar sucursal");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
				}
			}while(!(opcionMenu == 1 || opcionMenu == 2));
			
			casoValido = (opcionMenu == 1) ? true : false;
			
		}while(!casoValido);
			
		return sucursalCineProceso;
	}

	/**
	 * Description : Este método se encarga de cambiar la sucursal en la cual se encuentra el cliente
	 * @param clienteProceso : Este método recibe al cliente (De tipo cliente) que desea cambiar de sucursal
	 * */
	private static void cambiarSucursalCine(Cliente clienteProceso) {
		
		System.out.println("\n==============================");
		System.out.println("Sistema de cambio de sucursal");
		
		int opcionMenu;
		do {
			opcionMenu = 0;
			
			try {
				System.out.println("\n¿Desea cambiar de " + clienteProceso.getCineActual().getLugar() + " a otra de nuestras sucursales? \n1. Si\n2. No");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debe ingresar un único dato numérico entre las opciones disponibles");
			}
			
		}while(!(opcionMenu == 1 || opcionMenu == 2));
		
		if (opcionMenu == 1) {
			clienteProceso.setCineActual(ingresarASucursal());
		}else {
			System.out.println("\nRegresando al menú principal...");
		}
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Bloque funcionalidad 1
	
	
	// _____  _   _   _   _    _____   _    ____    _   _       __       _       _   _____       __       _____  	    __
	//|  __| | | | | | \ | |  / ____| | |  / __ \  | \ | |     /  \     | |     | | |  __ \     /  \     |  __ \ 	   /  |
	//| |__  | | | | |  \| | | |	  | | | |  | | |  \| |    /    \    | |     | | | |  | |   /    \    | |  | |	  /   |
	//|  __| | | | | |	  \| | |      | | | |  | | |    \|   /  __  \   | |     | | | |  | |  /  __  \   | |  | |	  \/| |
	//| |    | |_| | | |\  | | \____  | | | |__| | | |\  |  / ______ \  | |___  | | | |__| | / ______ \  | |__| |	   _| |_
	//|_|     \___/  |_| \_|  \_____| |_|  \____/  |_| \_| /_/      \_\ |_____| |_| |_____/ /_/      \_\ |_____/ 	  |_____| 
	
	/**
	 * Description: Este método se encarga mostrar en pantalla los procesos de la funcionalidad 1, para que el cliente elija uno de ellos, 
	 * una vez termine su interacción, el cliente regresará a este mismo menú, en caso de que quiera regresar al menú principal termina el
	 * ciclo y se ejecuta el menú inicial.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo cliente) que realizará algún proceso
	 * del sistema de proyeciones.
	 * */
	private static void ingresarASistemaDeProyecciones(Cliente clienteProceso) {
		
		int opcionMenu;
		boolean opcionValida = false;
		do {
			opcionMenu = 0;
			try {
				System.out.println("\nBienvenido al sistema de proyecciones de películas\n"
						+ "Estos son nuestros servicios disponibles:\n"
						+ "1. Reservar ticket de película\n"
						+ "2. Ingresar a sala de cine\n"
						+ "3. Ingresar a sala de espera\n"
						+ "4. Volver al menú principal\n");
				System.out.print("Elige una de las opciones disponibles para continuar con el proceso: ");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un único dato númerico entre los disponibilidad");
				continue;
			}
			
			switch(opcionMenu) {
				case 1: reservarTicket(clienteProceso); break;
				case 2: ingresarSalaCine(clienteProceso); break;
				case 3: ingresarSalaDeEspera(clienteProceso); break;
				case 4: System.out.println("\nRegresando al menú principal..."); opcionValida = true; break;
				default: System.out.println("Digite una única opción entre las disponibles"); 
			}
		}while(!opcionValida);
		
		Administrador.inicio(clienteProceso);
		
	}
	
	/**
	 * Description: Este método se encarga de realizar el proceso de reserva de ticket de la funcionalidad 1.
	 * Para llevar su cometido, se muestran las películas en cartelera de la franquicia a la que accedió previamente, 
	 * el cliente selecciona una de estas, luego se busca si la película seleccionada se encuentra en presentación en alguna de las salas de cine 
	 * de la sucursal y no lleve más de 15 minutos en presentación:
	 * <ol>
	 * <li>En caso de que sí, se le pregunta al cliente si quiere comprar la película en ese horario, dada una respuesta positiva, con la sala de cine
	 * previamente encontrada realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine, 
	 * le pedimos al cliente que seleccione el asiento, se valida su disponibilidad, se realiza el proceso de pago y se asigna el ticket al cliente)
	 * (Para este proceso cuenta con un tiempo límite de 20 minutos).</li>
	 * 
	 * <li>En caso de que haya decidido no comprar en ese horario o directamente la película no estaba en presentación, mostramos los horarios 
	 * de la película, el usuario selecciona uno de ellos y realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de 
	 * cine virtual asociada al horario previamente seleccionado, el cliente selecciona el asiento deseado, se valida su disponibilidad, 
	 * se realiza el proceso de pago y se asigna el ticket al cliente).</li>
	 * </ol>
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo cliente) que desea realizar la reserva de un ticket.
	 * */
	private static void reservarTicket(Cliente clienteProceso) {
		
		boolean finalizarProcesoReservaTicket = false;
		do {
			
			System.out.println("\nSistema de Reserva de ticket para película");
			
			//Elección menu inicial
			boolean casoValidoIniciarFuncionalidad = false;
			int opcionMenu = 0;
			do {			
				try {
					System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú anterior");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un dato numérico");
					continue;
				}
				
				switch (opcionMenu) {
					case 1: casoValidoIniciarFuncionalidad = true; break;
					case 2: casoValidoIniciarFuncionalidad = true; break;
					default: System.out.println("Opción invalida");
				}
				
			}while(!casoValidoIniciarFuncionalidad);
			
			//En caso de que el cliente elija volver al menú principal
			if (opcionMenu == 2) {
				break;
			}
			
			//Funcionalidad reserva de ticket
			
			//Avance de tiempo para ejecutar los filtros de películas correctamente
			avanzarTiempo();
			
			//Mostramos una cartelera personalizada de acuerdo a la edad del cliente, si la película tiene horarios disponibles o se encuentra en presentación
			ArrayList<Pelicula> carteleraPersonalizadaProceso = Pelicula.filtrarCarteleraPorCliente(clienteProceso, clienteProceso.getCineActual());
			
			//Verificamos si el cliente tiene acceso para al menos una película
			if (carteleraPersonalizadaProceso.size() == 0) {
				System.out.println("No hay películas disponibles para reservar (Redireccionando al menú principal...)\n");
				SucursalCine.setFechaActual(SucursalCine.getFechaActual().withHour(SucursalCine.getFinHorarioLaboral().getHour()).withMinute(0));
				avanzarDia(clienteProceso);
				break;
			}
			
			//Tomamos los nombres de las películas para mostrarlos en pantalla
			ArrayList<String> nombresPeliculasCarteleraPersonalizadaProceso = Pelicula.filtrarNombrePeliculas(carteleraPersonalizadaProceso);
			
			//Tomamos los nombres de las películas cuyo género coincide con el género más visto por el cliente con el fin de realizar
			//el proceso de recomendación de películas en caso de que el cliente tenga membresía.
			ArrayList<String> peliculasRecomendadas = Pelicula.filtrarPorGenero(carteleraPersonalizadaProceso, clienteProceso.generoMasVisto());
			
			//Seleccionamos una película
			boolean volverAlMenu = false;
			boolean casoValidoSeleccionPelicula = false;
			boolean casoValido = false;
			boolean casoValidoConfirmacion = false;
			Pelicula peliculaProceso = null;
			String nombrePelicula = null;
			
			do {
				//Mostramos los nombres de las películas en cartelera y le pedimos al usuario elegir una de estas
				do {
					
					opcionMenu = 0;
					System.out.println("\nHola " + clienteProceso.getNombre() + ", bienvenido al sistema de reserva de ticket\n"
					+ "================================================================\n"
					+ "Este es el listado de los nombres de las películas en cartelera:"
					+ Pelicula.showNombrePeliculas(nombresPeliculasCarteleraPersonalizadaProceso, clienteProceso, peliculasRecomendadas) + "\n"
					+ ( Integer.valueOf(nombresPeliculasCarteleraPersonalizadaProceso.size()) + 1 ) + ". Regresar al menú de sistema de proyecciones");
					
					try {
						System.out.print("\nElige una de las películas disponibles para ver más información: " );
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("\nError, debes ingresar un único dato numérico");
					}
					
					if ((opcionMenu > 0 && opcionMenu <= nombresPeliculasCarteleraPersonalizadaProceso.size())) {
						//Obtenemos el nombre de la película seleccionada por el cliente
						nombrePelicula = nombresPeliculasCarteleraPersonalizadaProceso.get(opcionMenu - 1);
						casoValido = true;
					}else if (opcionMenu == Integer.valueOf(nombresPeliculasCarteleraPersonalizadaProceso.size()) + 1) {
						//Volvemos al menú del sistema de proyecciones
						casoValido = true;
						volverAlMenu = true;
					}else {
						System.out.println("\nOpción inválida");
					}
					
				}while(!casoValido);
				
				//Cerramos el bucle de la lógica de selección de película
				if (volverAlMenu) {
					break;
				}
				
				//Buscamos las películas que coinciden con el nombre de película seleccionado con el cliente
				ArrayList<Pelicula> peliculasProceso = Pelicula.filtrarPorNombreDePelicula(nombrePelicula, carteleraPersonalizadaProceso);
				
				//Mostramos información del nombre de la película seleccionada
				System.out.println("\nInformación película seleccionada -> \nNombre: " + peliculasProceso.get(0).getNombre() 
				+ "; Género: " + peliculasProceso.get(0).getGenero()
				+ ", Duración: " + peliculasProceso.get(0).getDuracion().toMinutes() + " Minutos.\n" 
				+"\n========================================================================================");
				
				//Mostramos en pantalla los formatos del nombre de la película seleccionada
				casoValido = false;
				boolean casoVolverASeleccionarPelicula = false;
				do {
					
					opcionMenu = 0;
					System.out.println("\nEste es el listado de los formatos de la película:\n" 
					+ Pelicula.showTiposFormatoPeliculaSeleccionada(peliculasProceso)+ "\n"
					+ ( Integer.valueOf(peliculasProceso.size()) + 1 ) + ". Seleccionar otra película");
							
					try {
						System.out.print("\nElige uno de los formatos disponibles: " );
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("\nError, debes ingresar un único dato numérico");
					}
					
					if ( opcionMenu > 0 && opcionMenu <= Integer.valueOf(peliculasProceso.size()) ) {
						//Seleccionamos la película con el formato seleccionado por el cliente
						peliculaProceso = peliculasProceso.get(opcionMenu - 1);
						casoValido = true;
					}else if (opcionMenu == Integer.valueOf(peliculasProceso.size()) + 1) {
						//Volver al menú anterior
						casoVolverASeleccionarPelicula = true;
						casoValido = true;
					}else {
						System.out.println("\nOpción invalida");
					}
					
				}while( !casoValido );
				
				//En caso de que el cliente haya elegido regresar y cambiar película
				if ( casoVolverASeleccionarPelicula ) {
					continue;
				}
				
				//Confirmamos la elección del cliente
				do {
					
					opcionMenu = 0;
					try {
						System.out.println("\nHas elegido la película " + peliculaProceso.getNombre() 
						+ " en formato " + peliculaProceso.getTipoDeFormato()
						+ "\n1. Correcto \n2. Cambiar Pelicula");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
					
					switch(opcionMenu) {
						case 1: casoValidoSeleccionPelicula = true; casoValidoConfirmacion = true; break;
						case 2: casoValidoSeleccionPelicula = false; casoValidoConfirmacion = true; break;
						default : System.out.println("Opción Invalida"); casoValidoConfirmacion = false; 
					}
					
				}while(!casoValidoConfirmacion);
					
			}while (!casoValidoSeleccionPelicula);
			
			//Rompemos la lógica de reserva de ticket para regresar al menú principal, en caso de que el cliente lo haya solicitado en el proceso de
			// seleccionar la película
			if (volverAlMenu) {
				break;
			}
			
			//Creamos el espacio en memoria para almacenar la información dada por el cliente luego de solicitarla
			SalaCine salaDeCineProceso = null;
			String numeroAsientoProceso = null;
			LocalDateTime horarioProceso = null;
			
			//Avance de tiempo para realizar los filtros de horarios correctamente
			avanzarTiempo();
			
			//Filtramos los primeros 7 horarios con asientos disponibles desde la fecha actual
			ArrayList<LocalDateTime> horariosPeliculaProceso = peliculaProceso.filtrarHorariosPelicula();
			boolean disponibilidadHoraria = horariosPeliculaProceso.size() > 0;
			boolean realizarReservaDePeliculaEnPresentacion = false;
			
			//Mostramos este menú en caso de que la película se encuentre en presentación en alguna sala de cine y 
			//además la película no lleva más de 15 minutos en presentación
			if (peliculaProceso.IsPeliculaEnPresentacion(clienteProceso.getCineActual())) {
				
				//Se busca en que sala se encuentra la película en presentación
				salaDeCineProceso = peliculaProceso.whereIsPeliculaEnPresentacion(clienteProceso.getCineActual());
				casoValidoConfirmacion = false;
				
				//Preguntamos si desea ver la película a la hora de esta presentación o en un horario diferente en caso de tener más horarios disponibles
				do {
					
					opcionMenu = 0;
					try {
						System.out.println("\nHemos detectado que la película seleccionada se encuentra en presentación. \ninicio de proyección: " 
						+ salaDeCineProceso.getHorarioPeliculaEnPresentacion() + "\n¿Desea reservar un ticket para este horario? " 
						+" (Hora actual: " + SucursalCine.getFechaActual().withNano(0) + ")\n1. Comprar en este horario" 
						+ disponibilidadHorariaFuncionalidad1(horariosPeliculaProceso));
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Error, debes ingresar un único dato númerico entre los disponibles");
					}
					
					//En caso de que la película tenga horarios disponibles, se usa un menú de 3 opciones, en caso de que no, uno de 2 opciones
					if (disponibilidadHoraria) {
						
						switch(opcionMenu) {
							case 1: casoValidoConfirmacion = true;
								//Se piden los datos de reserva de ticket en la sala de cine en cuestión
								
								//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
								numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso, peliculaProceso);
								//Revisamos que el asiento haya sido seleccionado con éxito
								if (numeroAsientoProceso == null) {
									volverAlMenu = true;
									System.out.println("\nNo se ha podido seleccionar correctamente el asiento, serás redireccionado al menú reserva de ticket");
								}
								
								//Obtenemos el horario de la película seleccionada
								horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
								
								realizarReservaDePeliculaEnPresentacion = true;
								
								break;
									
							case 2: casoValidoConfirmacion = true; 							
									//Se piden los datos de reserva de ticket de la película en otro horario
									
									//El cliente elige el horario de la película seleccionada 
									horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, horariosPeliculaProceso);
									if (horarioProceso == null) {
										volverAlMenu = true;
										System.out.println("\nNo se ha podido seleccionar correctamente el horario, serás redireccionado al menú del sistema de proyecciones");
									}
									
									//El cliente elige el asiento de la película seleccionada
									numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso);
									//Revisamos que el asiento haya sido seleccionado con éxito
									if (numeroAsientoProceso == null) {
										volverAlMenu = true;
										System.out.println("\nNo se ha podido seleccionar correctamente el asiento, serás redireccionado al menú del sistema de proyecciones");
									}
									
									break;
									
							case 3: casoValidoConfirmacion = true; volverAlMenu = true; break;
							
							default: casoValidoConfirmacion = false; System.out.println("Digite un número válido");
						}
						
					}else {
						switch(opcionMenu) {
							case 1: casoValidoConfirmacion = true;
									//Se piden los datos de reserva de ticket en la sala de cine en cuestión
									
									//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
									numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso, peliculaProceso);
									//Revisamos que el asiento haya sido seleccionado con éxito
									if (numeroAsientoProceso == null) {
										volverAlMenu = true;
										System.out.println("\nNo se ha podido seleccionar correctamente el asiento, serás redireccionado al menú del sistema de proyecciones");
									}
									
									//Obtenemos el horario de la película seleccionada
									horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
									
									realizarReservaDePeliculaEnPresentacion = true;
									
									break;
									
							case 2: casoValidoConfirmacion = true; volverAlMenu = true; break;
							
							default: casoValidoConfirmacion = false; System.out.println("Digite un número válido");
						}
					}
				}while(!(casoValidoConfirmacion));
				
			}else {
				
				//Se verifica que tenga horarios disponibles
				if(disponibilidadHoraria) {
					//Compra película en otro horario
					
					//El cliente elige el horario de la película seleccionada 
					horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, horariosPeliculaProceso);
					if (horarioProceso == null) {
						volverAlMenu = true;
						System.out.println("\nNo se ha podido seleccionar correctamente el horario, serás redireccionado al menú del sistema de proyecciones");
					}
					
					//El cliente elige el asiento de la película seleccionada
					numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso);
					//Revisamos que el asiento haya sido seleccionado con éxito
					if (numeroAsientoProceso == null) {
						volverAlMenu = true;
						System.out.println("\nNo se ha podido seleccionar correctamente el asiento, serás redireccionado al menú del sistema de proyecciones");
					}
					
				}else {
					System.out.println("La película seleccionada se encuentra únicamente en presentación o no tiene asientos disponibles." + 
					"\n(Serás redireccionado al menú inicial de este proceso...)");
					continue;
				}
				
			}
			
			//Rompemos la lógica de la reserva de ticket y regresamos al menú principal
			// para llegar aquí hay 2 caminos: 
			//1. El cliente estaba seleccionando un asiento y durante el proceso, el hilo modifico la sala virtual o 
			//sala presencial, en dónde se estaba haciendo este proceso
			// 2. El cliente eligió regresar al menú del sistema de proyecciones
			if (volverAlMenu) {
				break;
			}
			
			//Se genera el último mensaje con posibilidad de regresar al menú principal en caso de que no se quiera reservar la película
			boolean casoContinuarProcesoPago = false;
			casoValidoConfirmacion = false;
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nVamos a empezar con el proceso de pago\n1. Continuar\n2. Volver al menú del sistema de proyecciones");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
				}
				
				switch(opcionMenu) {
					case 1: casoContinuarProcesoPago = true; casoValidoConfirmacion = true; break;
					case 2: casoValidoConfirmacion = true; break;
					default : System.out.println("Opción Invalida"); casoValidoConfirmacion = false; 
				}
				
			}while(!casoValidoConfirmacion);
			
			//Verificar integridad datos seleccionados, en caso de que el cliente tarde mucho en ingresar la confirmación de continuar con el proceso
			if ( realizarReservaDePeliculaEnPresentacion ) {
				
				//Revisamos si el horario de la película en presentación ha sido modificado, comparando que el nombre sea el mismo
				// y el horario de presentación más la duración de la película no exceda a la fecha actual
				if ( !verificarIntegridadHorarioSeleccionado(salaDeCineProceso, peliculaProceso) ) {
					System.out.println("\nEl tiempo límite de compra de una película en presentación ha sido excedido (20 minutos a partir del inicio de la proyección)." 
					+ "\nLe solicitamos esperar a la siguiente presentación, serás redirigido al menú del sistema de proyecciones. ");
					break;
				}

				
			}else {
				
				//Revisamos si el horario de la película seleccionado desde la sala de cine virtual ha sido modificado, verificando
				// si el horario se encuentra en el array de horarios de la película
				if ( !verificarIntegridadHorarioSeleccionado(peliculaProceso, horarioProceso)) {
					System.out.println("\nEl horario seleccionado ha sido actualizado, actualmente se encuentra en presentación,"
					+ "\nserás redirigido al menú del sistema de proyecciones.");
					break;
				}
				
			}
			
			//Creamos el apuntador del ticket
			Ticket ticketProceso = null;
			
			if (casoContinuarProcesoPago) {
				
				//Creamos el ticket con su respectivo precio e informamos al cliente en caso de recibir un descuento
				ticketProceso = new Ticket(peliculaProceso, horarioProceso, numeroAsientoProceso, clienteProceso.getCineActual());
				//Mostramos un mensaje en pantalla en caso de recibir el descuento
				if ( ticketProceso.getPrecio() != peliculaProceso.getPrecio() ) {
					
					System.out.println("\n🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
					
					if (peliculaProceso.getTipoDeFormato().equals("3D") || peliculaProceso.getTipoDeFormato().equals("4D") ) {
						System.out.println("Felicidades, por ser nuestro cliente número " + clienteProceso.getCineActual().getCantidadTicketsCreados() 
						+ " has recibido un descuento del 50% por la compra de tu ticket\n"
						+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
					}else {
						System.out.println("Felicidades, por ser nuestro cliente número: " + clienteProceso.getCineActual().getCantidadTicketsCreados() 
						+ " has recibido un descuento del 80% por la compra de tu ticket\n"
						+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
					}

					System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
				}
				
			}else {
				//Volvemos al menú del sistema de proyecciones
				//Rompemos la lógica de la reserva de ticket
				break;
				
			}
			
			//Iniciamos el proceso de pago
			System.out.println("\n		Proceso de pago");
			System.out.println("=====================================================");
			
			boolean pagoRealizado = false;
			casoValido = false;
			casoValidoConfirmacion = false;
			
			MetodoPago metodoPagoProceso = null;
			double precioTicketProceso = ticketProceso.getPrecio();
			double precioAcumuladoTicketProceso = 0;
			
			//Selccionar el método de pago para realizar el pago y realizar el pago
			do {
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nEl valor a pagar por el ticket es: " + precioTicketProceso
						+ "\nEste es el listado de los métodos de pago disponibles:\n" 
						+ MetodoPago.mostrarMetodosDePago(clienteProceso));
						System.out.print("\nElige una de las opciones disponibles para realizar el pago: " );
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nError, debe ingresar un único dato númerico entre los disponibles");
					}
					
					if (opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size()) {
						//Se selecciona el método de pago
						metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
						casoValido = true;
						
					}else {
						System.out.println("\nSeleccione un método de pago entre los disponibles");
						
					}
					
				}while( !casoValido );
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nEl método de pago escogido es: " + metodoPagoProceso.getNombre() 
						+ " ( Precio anterior: " + precioTicketProceso + " -> Precio actual: " + precioTicketProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )"
						+ "\n1. Correcto\n2. Cambiar Método de pago");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
					
					switch(opcionMenu) {
					case 1: casoValidoConfirmacion = true; break;
					case 2: casoValidoConfirmacion = true; break;
					default: System.out.println("Opcion Invalida"); casoValidoConfirmacion = false;
					}
					
				}while(!casoValidoConfirmacion);
				
				if (opcionMenu == 2 || opcionMenu == 0) {
					continue;
				}
				
				//Verificar integridad datos seleccionados, en caso de que el cliente tarde mucho tiempo en seleccionar el método de pago y confirmarlo
				if ( realizarReservaDePeliculaEnPresentacion ) {
					
					//Revisamos si el horario de la película en presentación ha sido modificado, comparando que el nombre sea el mismo
					// y el horario de presentación más la duración de la película no exceda a la fecha actual
					if ( !verificarIntegridadHorarioSeleccionado(salaDeCineProceso, peliculaProceso) ) {
						System.out.println("\nEl proceso de compra no se ha llevado a cabo, debido a que ha excedido el tiempo límite " + 
						"de compra de una película en presentación\n(20 minutos a partir del inicio de la proyección).\nTerminando proceso de compra...");
						break;
					}

					
				}else {
					
					//Revisamos si el horario de la película seleccionado desde la sala de cine virtual ha sido modificado, verificando
					// si el horario se encuentra en el array de horarios de la película
					if ( !verificarIntegridadHorarioSeleccionado(peliculaProceso, horarioProceso)) {
						System.out.println("\nEl proceso de compra no se ha llevado a cabo, debido a que el horario seleccionado ha sido actualizado,"
						+ "\nactualmente se encuentra en presentación.\nTerminando proceso de compra...");
						break;
					}
					
				}
				
				//Realizamos el pago y sumamos el precio acumulado para mostrar el valor real del ticket
				precioAcumuladoTicketProceso = precioAcumuladoTicketProceso + precioTicketProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
				precioTicketProceso = metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso);
				
				//Ponemos un delay en pantalla
				System.out.println("\nEstamos procesando su pago, por favor espere...\n");
				try {
					Thread.sleep(3000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			
				//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
				if ( realizarReservaDePeliculaEnPresentacion ) {
					
					//Verificamos si el pago fue cubierto en su totalidad
					if (precioTicketProceso == 0) {
						
						System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
						
						//Setteamos el precio del ticket
						ticketProceso.setPrecio(precioAcumuladoTicketProceso);
						
						//Realizamos el proceso correspondiente luego de ser verificado
						ticketProceso.procesarPagoRealizado(clienteProceso);
						salaDeCineProceso.cambiarDisponibilidadAsientoLibre(numeroAsientoProceso);
						
						//Generamos la fila y la columna a partir del número de asiento seleccionado para modificar su disponibilidad
						//Nota : Esto es para preservar la persistencia, en caso de reservar un ticket de una película en presentación,
						//también debemos añadir este cambio a la sala virtual, ya que luego de deserializar se ponen falsos todas las
						//disponibilidades de asientos y renovamos los valores con los asientos virtuales de la película que debe estar 
						//en presentación en ese momento del tiempo, es por esto que debemos actualizar también la matriz de asientos
						//en película, ya que sin esto, se pierde este dato de la compra y permitiría comprar varios tickets para el
						//mismo asiento.
						int filaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(0));
						int columnaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(2));
						peliculaProceso.modificarSalaVirtual( salaDeCineProceso.getHorarioPeliculaEnPresentacion(), filaProceso, columnaProceso );
						
						System.out.println( ticketProceso.factura() );
						pagoRealizado = true;
						
					}else {
						
						//Repetimos el proceso hasta validar el pago
						System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
						
					}
					
				}else {
					
					//Verificamos si el pago fue cubierto en su totalidad
					if (precioTicketProceso == 0) {
						
						System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
						
						//Setteamos el precio del ticket
						ticketProceso.setPrecio(precioAcumuladoTicketProceso);
						
						//Realizamos el proceso correspondiente a realizar el pago
						ticketProceso.procesarPagoRealizado(clienteProceso);
						
						//Generamos la fila y la columna a partir del número de asiento seleccionado para modificar su disponibilidad
						int filaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(0));
						int columnaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(2));
						peliculaProceso.modificarSalaVirtual(horarioProceso, filaProceso, columnaProceso);
						
						System.out.println( ticketProceso.factura() );
						pagoRealizado = true;
						
					}else {
						
						//Repetimos el proceso hasta validar el pago
						System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
						
					}
				
				}
			
			}while(!pagoRealizado);
			
			System.out.println("\nFin del proceso reserva de ticket");
			System.out.println("(Redireccionando al menú del sistema de proyecciones...)");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			finalizarProcesoReservaTicket = true;
			
		}while(!finalizarProcesoReservaTicket);
			
	}

	/**
	 * Description : Este método se encarga de seleccionar un horario a partir de la pelicula seleccionada por el cliente, para realizar este proceso,
	 * Se muestra en pantalla los de horarios de la película seleccionada, para que el cliente pueda seleccionar uno de estos.
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente), con el fin de que, 
	 * dado el caso, el cliente quiera regresar al menú del sistema de proyeccciones, pueda hacerlo.
	 * @param peliculaProceso : Este método recibe como parámetro una película (De tipo película) obtenido durante el proceso de la reserva de ticket,
	 * para que de esta podamos obtener su array de horarios y su correspondiente sala virtual obtenida del array de asientos virtuales.
	 * @param horariosPeliculaProceso : Este método recibe como parámetro los horarios disponibles de la película (De tipo ArrayList<LocalDateTime>)
	 * obtenido durante el proceso de la reserva de ticket.
	 * @return <b>LocalDateTime</b> : Este método retorna el horario seleccionado por el cliente, para continuar con el proceso de la reserva de ticket.
	 * */
	private static LocalDateTime seleccionarHorarioPelicula(Cliente clienteProceso, Pelicula peliculaProceso, ArrayList<LocalDateTime> horariosPeliculaProceso) {
		
		boolean casoValido = false;
		boolean casoValidoEleccionHorario = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		LocalDateTime horarioProceso = null;
		
		do {
			System.out.println("\n		Selección de horario");
			System.out.println("=====================================================");
			//Mostramos en pantalla los horarios disponibles
			do {
				opcionMenu = 0;
				System.out.println("\nLos horarios de la película " + peliculaProceso.getNombre() 
				+ " son:\n" + peliculaProceso.mostrarHorarioPelicula(horariosPeliculaProceso));
				
				try {
					System.out.print("\nElige un horario entre los disponibles: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un único dato numérico");
					continue;
				}
				
				if( opcionMenu > 0 && opcionMenu <= Integer.valueOf(horariosPeliculaProceso.size()) ) {
					horarioProceso = horariosPeliculaProceso.get(opcionMenu - 1);
					casoValidoEleccionHorario = true;
				}else {
					System.out.println("\nOpción inválida");
				}
				
			}while(!casoValidoEleccionHorario);
			
			//Confirmamos el horario seleccionado
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nElegiste la película el día: " + horarioProceso.getDayOfWeek() +  " fecha: "
					+ horarioProceso.toLocalDate() + ", A las: " + horarioProceso.toLocalTime() + "\n1. Correcto \n2. Cambiar horario");
					opcionMenu = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico");
				}
				
				switch(opcionMenu) {
					case 1: casoValidoConfirmacion = true; casoValido = true; break;
					case 2: casoValidoConfirmacion = true; casoValido = false; break;
					default : System.out.println("Opción invalida"); casoValidoConfirmacion = false; break;
				}
				
			}while(!casoValidoConfirmacion);
			
			//Avance de tiempo para verificar la integridad del horario seleccionado
			avanzarTiempo();
			
			//Revisar integridad del horario seleccionado en caso de que el cliente tarde mucho tiempo en confirmar su elección
			if (!horarioProceso.isAfter(SucursalCine.getFechaActual())) {
				System.out.println("\nEl horario seleccionado ha sido actualizado, actualmente se encuentra en presentación");
				horarioProceso = null;
			}
			
		}while(!casoValido);
		
		return horarioProceso;
	}
	
	/**
	 * Description: Este método se encarga de seleccionar el número de asiento del cliente para ver una película en un horario previamente seleccionado
	 * por el cliente, para hacer esto, se muestra en pantalla los asientos de la sala de cine virtual, con su respectiva disponibilidad,
	 * tras esto, el cliente elige la fila, luego la columna, se valida si el asiento en cuestión 
	 * se encuentra disponible y una vez cumplida la verificación, retornamos el número del asiento seleccionado.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) seleccionado en el proceso de login.
	 * @param horarioProceso : Este método recibe como parámetro el horario (De tipo LocalDateTime) seleccionado durante el proceso de reserva de ticket.
	 * @param horarioProceso : Este método recibe como parámetro la película (De tipo Pelicula) seleccionada durante el proceso de reserva de ticket.
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente.
	 * */
	private static String seleccionarAsiento(Cliente clienteProceso, LocalDateTime horarioProceso, Pelicula peliculaProceso) {
		
		boolean casoSeleccionExpirada = false;
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		String numeroAsientoProceso = null;
		int filaProceso = 0;
		int columnaProceso = 0;
		
		System.out.println("\n		Selección de asiento");
		System.out.println("=====================================================");
		
		//Elegimos el asiento
		do {
			System.out.println("\nEsta es la distribución de asientos, con su disponibilidad \nactual, de la película en el horario seleccionado" 
		    + "\n X : Ocupado\n O : Disponible\n" + peliculaProceso.mostrarAsientosSalaVirtual(horarioProceso) );
			
			//Elegimos la fila del asiento
			do {
				try {
					System.out.print("\nDigite la fila de su asiento deseado: ");
					filaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles");
					continue;
				}
				
				//Avance de tiempo para verificar si el horario aún se encuentra disponible en la sala de cine virtual (No ha sido actualizado)
				avanzarTiempo();
				
				//Verificamos si el horario aún se encuentra disponible
				try {
					
					if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso)).length))){
						System.out.println("\nLa fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
						continue;
					}
					
				}catch (IndexOutOfBoundsException e) {
					casoSeleccionExpirada = true;
					System.out.println("\nEl horario seleccionado ha sido actualizado, actualmente se encuentra en presentación");
					break;
				}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nLa fila seleccionada es: " + filaProceso + "\n1. Correcto \n2. Cambiar fila");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("\nError, debe ingresar un único dato numérico entre los disponibles");
						continue;
					}
					
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
				
			}while(!(casoValidoConfirmacion));
			
			//Rompemos la lógica de la selección de asientos
			if (casoSeleccionExpirada) {
				break;
			}
			
			//Elegimos la columna del asiento
			casoValidoConfirmacion = false;
			do {
				try {
					System.out.print("\nDigite la columna de su asiento deseado: ");
					columnaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
				
				//Avance de tiempo para verificar si el horario aún se encuentra disponible en la sala de cine virtual (No ha sido actualizado)
				avanzarTiempo();
				
				//Revisamos si el horario aún se encuentra disponible
				try {
					
					if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso))[filaProceso - 1].length))){
						System.out.println("\nLa columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
						continue;
					}
					
				}catch (IndexOutOfBoundsException e) {
					casoSeleccionExpirada = true;
					System.out.println("\nEl horario seleccionado ha sido actualizado, actualmente se encuentra en presentación");
					break;
				}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nLa columna seleccionada es: " + columnaProceso + "\n1. Correcto \n2. Cambiar columna");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("\nError, debe ingresar un único dato numérico entre los disponibles");
						continue;
					}
					
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
				
			}while(!(casoValidoConfirmacion));
			
			//Rompemos la lógica de la selección de asientos
			if (casoSeleccionExpirada) {
				break;
			}
			
			//Modificamos el numeroAsientoProceso que se usa posteriormente al crear el ticket
			numeroAsientoProceso = filaProceso + "-" + columnaProceso;
			
			if(peliculaProceso.isDisponibilidadAsientoSalaVirtual(horarioProceso, filaProceso, columnaProceso)) {
				casoValido = true;
				System.out.println("\nEl asiento " + numeroAsientoProceso + " ha sido seleccionado con éxito");
			}else {
				casoValido = false;
				System.out.println("\nEl asiento " + numeroAsientoProceso + " no se encuentra disponible actualmente.\n" + 
				"Se le solicita amablemente que seleccione uno de los asientos disponibles para disfrutar de su película.\n" + 
				"A continuación se mostrarán en pantalla nuevamente los asientos con su respectiva disponibilidad\n");
			}
			
		}while(!casoValido);
		
		return numeroAsientoProceso;
	}
	
	/**
	 * Description: Este método se encarga de seleccionar el número de asiento del cliente para ver una película que en estos momentos se encuentra en
	 * presentación, para hacer esto, se muestra en pantalla los asientos de la sala de cine con su respectiva disponibilidad, el cliente elige la fila
	 * luego la columna, se valida si el asiento en cuestión se encuentra disponible y también si la pelicula aún se encuentra en presentación y
	 * no supera el tiempo límite de realizar la reserva de ticket de una película en presentación (20 minutos) y una vez cumplida la verificación,
	 * retornamos el número del asiento seleccionado.
	 * @param salaDeCinePresentacionProceso : Este método recibe como parámetro la sala de cine en la cuál se encuentra la película seleccionada durante
	 * el proceso de la reserva de ticket.
	 * @param peliculaProceso: Este método recibe como parámetro la película (De tipo Película) seleccionada por el usuario durante el proceso de reserva,
	 * con el fin de validar si esta aún se encuentra en presentación.
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente.
	 * */
	private static String seleccionarAsiento(SalaCine salaDeCinePresentacionProceso, Pelicula peliculaProceso) {
		
		boolean casoValidoConfirmacion = false;
		boolean casoValido = false;
		int opcionMenu = 0;
		
		String numeroAsientoProceso = null;
		int filaProceso = 0;
		int columnaProceso = 0;
		
		System.out.println("\n		Selección de asiento");
		System.out.println("=====================================================");
		
		do {
			System.out.println("\nEsta es la distribución de asientos con su disponibilidad \nactual de la película en el horario seleccionado" 
			+ "\n X : Ocupado\n O : Disponible\n" + salaDeCinePresentacionProceso.mostrarAsientos());
					
			//Elegimos la fila del asiento
			do {
				try {
					System.out.print("\nDigite la fila de su asiento deseado: ");
					filaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
						System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles");
						continue;
				}
						
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos().length))){
					System.out.println("\nLa fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
					continue;
					}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nLa fila seleccionada es: " + filaProceso + "\n1. Correcto \n2. Cambiar fila");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("\nError, debe ingresar un único dato numérico entre los disponibles");
					}
					
				}while(!(opcionMenu == 1 || opcionMenu == 2));
						
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
						
			}while(!(casoValidoConfirmacion));
					
			//Elegimos la columna del asiento
			casoValidoConfirmacion = false;
			do {
				try {
					System.out.print("\nDigite la columna de su asiento deseado: ");
					columnaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
						
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos()[filaProceso - 1].length))){
					System.out.println("\nLa columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
					continue;
				}
					
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nLa columna seleccionada es: " + columnaProceso + "\n1. Correcto \n2. Cambiar columna");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("\nError, debe ingresar un único dato numérico entre los disponibles");
					}
					
				}while(!(opcionMenu == 1 || opcionMenu == 2));
						
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
						
			}while(!(casoValidoConfirmacion));
		
			//Revisamos si el horario de la película en presentación ha sido modificado, comparando que el nombre sea el mismo
			// y el horario de presentación más la duración de la película no exceda a la fecha actual
			if(!verificarIntegridadHorarioSeleccionado(salaDeCinePresentacionProceso, peliculaProceso)) {
				System.out.println("\nEl tiempo límite de compra de una película en presentación ha sido excedido (20 minutos a partir del inicio de la proyección).\nLe solicitamos esperar a la siguiente presentación ");
				break;
			}
			
			numeroAsientoProceso = filaProceso + "-" + columnaProceso;
			
			if(salaDeCinePresentacionProceso.isDisponibilidadAsiento(filaProceso, columnaProceso)) {
				casoValido = true;
				System.out.println("\nEl asiento " + numeroAsientoProceso + " ha sido seleccionado con éxito");
			}else {
				casoValido = false;
				System.out.println("\nEl asiento " + numeroAsientoProceso + " no se encuentra disponible actualmente.\n" + 
				"Se le solicita amablemente que seleccione uno de los asientos disponibles para disfrutar de su película.\n" + 
				"A continuación se mostrarán en pantalla los asientos con su respectiva disponibilidad\n");
			}
			
		}while(!casoValido);
		
		return numeroAsientoProceso;
		
	}
	
	/**
	 * Description: <p>Este método se encarga de realizar el proceso para que un usario pueda ingresar a una de las salas de cine,
	 * para esto debemos cumplir un requisito fundamental, el cual es que el cliente tenga al menos un ticket asociado correspondiente a la
	 * sucursal desde la cual está intentando acceder, dado el caso de que no, el cliente será redirigido al menú del sistema de proyecciones,
	 * en caso de que sí podemos continuar con el proceso.</p>
	 * <p>Mostramos en pantalla las salas de cine disponibles con información relevante de estas (Número de sala, película en presentación y horario),
	 * el cliente selecciona una de estas, verificamos que alguno de los tickets del cliente, cumpla con los requisitos necesarios para 
	 * poder ingresar a la sala de cine, en caso de que sí, se muestra en pantalla una representación de la sala de cine y una vez termina 
	 * la película se redirecciona al cliente al menú del sistema de proyecciones, en caso de que no, se vuelve a mostrar por pantalla las 
	 * salas de cine disponibles.</p>
	 * @param clienteProceso : Este método recibe como parámetro al cliente (De tipo Cliente), que ingresó desde el menú
	 * del sistema de proyecciones.
	 * */
	private static void ingresarSalaCine(Cliente clienteProceso) {
		
		boolean finalizarLogicaIngresarSalaCine = false;
		do {
			
			System.out.println("\nSistema de ingreso a la sala de cine\n");
			
			//Elección menú inicial
			boolean volverAlMenu = false;
			boolean casoValido = false;
			int opcionMenu;
			do {
				opcionMenu = 0;
				try {
					System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú anterior");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un dato numérico");
					continue;
				}
				
				switch (opcionMenu) {
					case 1: casoValido = true; break;
					case 2: volverAlMenu = true; casoValido = true; break;
					default: System.out.println("\nOpción invalida");
				}
				
			}while(!casoValido);
			
			if(volverAlMenu) {
				//Rompemos la lógica de ingresar a las salas de cine
				break;
			}
			
			//Avance de tiempo para tomar las salas de cine actualizadas
			avanzarTiempo();
			
			//Tomamos las salas de cine que aún tienen películas en presentación y no han finalizado
			ArrayList<SalaCine> salasDeCineDisponibles = SalaCine.filtrarSalasDeCine(clienteProceso.getCineActual());
			
			//En caso de no encontrar salas de cine, regresamos al menú principal
			if (salasDeCineDisponibles.isEmpty()) {
				System.out.println("\nNo hay películas en presentación, redireccionando al menú del sistema de proyeccion de películas...");
				break;
			}
			
			//Eliminamos los tickets caducados sin consumir que el cliente tenga asociados 
			clienteProceso.dropTicketsCaducados();
			
			//Filtramos los tickets que puede usar el cliente
			ArrayList<Ticket> ticketsDisponiblesParaUsar = clienteProceso.filtrarTicketsParaSede();
			
			//Verificamos que el cliente tenga al menos un ticket de esta sucursal para continuar con el proceso
			if(ticketsDisponiblesParaUsar.size() > 0) {
				System.out.println("\nBienvenido al sistema de ingreso a las sala de cine");
			}else {
				System.out.println("No has comprado ningún ticket o no tienes un ticket de una película de esta sede, te redireccionaremos al menú principal");
				break;
			}
			//Iniciamos el proceso para ingresar a una sala de cine
			SalaCine salaDeCineProceso = null;
			casoValido = false;
			boolean casoValidoEleccionSala = false;
			boolean casoValidoConfirmacion = false;
			do {
				do {
					do {
						
						opcionMenu = 0;
						//Mostramos el listado de los tickets disponibles
						System.out.println("\n		Hola " + clienteProceso.getNombre());
						System.out.println("==================================================\n");
						System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
						+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime().withNano(0) + "\n\n"
						+ "Estos son los tickets que actualmente tienes disponibles:" 
						+ clienteProceso.mostrarTicketsParaUsar(ticketsDisponiblesParaUsar) + "\n");
						//Mostramos el listado de las salas de cine disponibles para que el cliente elija una de estas
						System.out.println("Este es el listado de las salas de cine disponibles: \n" 
						+ SalaCine.mostrarSalaCine(salasDeCineDisponibles) + "\n" + (Integer.valueOf(salasDeCineDisponibles.size()) + 1) 
						+ ". Regresar al menú de sistema de proyecciones");
						
						try {
							
							System.out.print("\nElige una de las salas de cine disponibles: ");
							opcionMenu = Integer.parseInt(sc.nextLine());
							
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un único dato numérico entre los disponibles");
						}
						
						if (opcionMenu > 0 & opcionMenu <= (Integer.valueOf(salasDeCineDisponibles.size()) ) ) { 
							//Obtenemos la sala de cine seleccionada
							salaDeCineProceso = salasDeCineDisponibles.get(opcionMenu - 1);
							casoValidoEleccionSala = true;
							
						}else if (opcionMenu == (Integer.valueOf(salasDeCineDisponibles.size()) + 1)) {
							//Regresamos al menú principal
							volverAlMenu = true;
							casoValidoEleccionSala = true;
			
						}else {
							System.out.println("\nOpción inválida");
						}
						
					}while( !casoValidoEleccionSala );
					
					if(volverAlMenu) {
						//Rompemos la lógica de caso valido confirmacion de ingreso a sala seleccionada
						break;
					}
					
					//Confirmamos la seleccion de sala
					do {
						opcionMenu = 0;
						try {
							System.out.println("\nHas seleccionado la sala de cine número: " + salaDeCineProceso.getNumeroSala() 
							+ "\n1. Correcto\n2. Cambiar sala de cine");
							opcionMenu = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
						}
						
					}while(!(opcionMenu == 1 || opcionMenu == 2));
					
					casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
					
				}while(!casoValidoConfirmacion);
				
				//Rompemos la lógica de caso válido de ingreso a la sala de cine
				if(volverAlMenu) {
					break;
				}
				
				//Avance de tiempo para aplicar correctamente la verificación de ingreso a la sala de cine
				avanzarTiempo();
				
				//Realizamos la verificación y evaluamos si el cliente puede ingresar a la sala de cine
				if (salaDeCineProceso.verificarTicket(clienteProceso)) {
					
					//Mostramos un dibujo en consola de la sala de cine
			        System.out.println("\n" + salaDeCineProceso.mostrarPantallaSalaCine());
			        System.out.println("\n¡Bienvenido al cine! 🎥🍿, Disfrute de la película");
			        try {
						Thread.sleep(3000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
			        
			        System.out.println("La película ha finalizado, muchas gracias por asistir le deseamos un feliz resto de día" + 
			        "\n(Redirigiendo al menú del sistema de proyecciones...)");
			        try {
						Thread.sleep(3000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
			        
			        casoValido = true;
			        
			        //Actualizamos el estado de la fecha actual, de las películas y las salas de cine 
			        SucursalCine.setFechaActual(salaDeCineProceso.getHorarioPeliculaEnPresentacion().plus(salaDeCineProceso.getPeliculaEnPresentacion().getDuracion()));
			        avanzarTiempo();
			        
				}else {
					System.out.println("\nNo tienes un ticket válido o no cumple con los requisitos para ingresar a esta sala de cine" 
					+ "\nSerás redireccionado a la elección de salas de cine");
					
					//Filtramos de nuevo las salas de cine disponibles
					salasDeCineDisponibles = SalaCine.filtrarSalasDeCine(clienteProceso.getCineActual());
					
					//Eliminamos los tickets caducados sin consumir que el cliente tenga asociados 
					clienteProceso.dropTicketsCaducados();
					
					//Filtramos los tickets que puede usar el cliente
					ticketsDisponiblesParaUsar = clienteProceso.filtrarTicketsParaSede();
					
					if (ticketsDisponiblesParaUsar.isEmpty()) {
						System.out.println("No tienes tickets disponibles para ingresar a las salas de cine");
						casoValido = true;
					}

				}
				
			}while(!casoValido);
			
			finalizarLogicaIngresarSalaCine = true;
			
		}while(!finalizarLogicaIngresarSalaCine);
				
	}
	
	/**
	 * Description : Este método se encarga de avanzar el tiempo del programa según el horario de la película del ticket del cliente, 
	 * para que este pueda verla. Para hacer esto, mostramos en pantalla los tickets disponibles del cliente, el cliente selecciona uno de ellos,
	 * setteamos la hora actual con la hora de la película y actualizamos las salas de cine (Un cliente sin tickets o sin tickets de esta sucursal 
	 * no puede ingresar a esta sala, además antes de verficar eso, eliminamos los tickets que ya han caducado).
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) que realizó el proceso de login.
	 * */
	private static void ingresarSalaDeEspera(Cliente clienteProceso) {
		boolean finalizarLogicaSalaDeEspera = false;
		do {
			
			System.out.println("\nIngreso a sala de espera");
			
			//Elección menu inicial
			boolean volverAlMenu = false;
			boolean casoValido = false;
			boolean casoValidoConfirmacion = false;
			int opcionMenu = 0;
			do {
				try {
					System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú anterior");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un dato numérico");
					continue;
				}
				
				switch (opcionMenu) {
					case 1: casoValido = true; break;
					case 2: volverAlMenu = true; casoValido = true; break;
					default: System.out.println("Opción invalida");
				}
				
			}while(!casoValido);
			
			if(volverAlMenu) {
				//Rompemos la lógica de ingresar a la sala de espera
				break;
			}
			
			//Avance de tiempo para tomar los tickets más recientes
			avanzarTiempo();
			
			//Validamos si el cliente tiene tickets disponibles
			clienteProceso.dropTicketsCaducados();
			//Filtramos los tickets que el cliente puede usar
			ArrayList<Ticket> ticketsDisponiblesParaUsar = clienteProceso.filtrarTicketsParaSede();
			
			if ((ticketsDisponiblesParaUsar.size() == 0)) {
				System.out.println("Debes tener al menos un ticket de alguna película de esta sede para hacer uso de esta sala (Redireccionando al menú principal...)");
				break;
			}
			
			Ticket ticketParaUsar = null;
			casoValido = false;
			
			System.out.println("\nBienvenido a la sala de espera, "+ clienteProceso.getNombre() + ",\n" +
			"===============================================================\n" +
			"Aquí puedes esperar a que pase el tiempo para poder" + 
			"\ningresar a la película de alguno de los tickets que adquriste");
			
			//Mostramos en pantalla los tickets disponibles y el usuario selecciona uno de estos
			do {
				opcionMenu = 0;
				System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
				+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime().withNano(0) + "\n\n"
				+ "Estos son los tickets que actualmente tienes disponibles:" 
				+ clienteProceso.mostrarTicketsParaUsar(ticketsDisponiblesParaUsar)
				+ "\n" + (ticketsDisponiblesParaUsar.size() + 1) + ". Volver al menú del sistema de proyecciones\n");
				
				try {	
					System.out.print("Selecciona un ticket entre los disponibles: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					continue;
				}
				
				if (opcionMenu > 0 && opcionMenu <= ticketsDisponiblesParaUsar.size()) {
					ticketParaUsar = ticketsDisponiblesParaUsar.get(opcionMenu - 1);
				}else if (opcionMenu == ticketsDisponiblesParaUsar.size() + 1) {
					volverAlMenu = true;
					break;
				}else {
					System.out.println("Error, por favor elige uno de los tickets disponibles");
					continue;
				}
				
				System.out.println("\n⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️"
				+ "\nADVERTENCIA: Los tickets entre el horario que intenta omitir y el actual, en caso de tenerlos, serán eliminados" 
				+ "\n⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️");
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nEl ticket seleccionado es para la película " + ticketParaUsar.getPelicula().getNombre()
						+ "; El día " + ticketParaUsar.getHorario().getDayOfWeek() + ";\nfecha " + ticketParaUsar.getHorario().toLocalDate() 
						+ "; A las " + ticketParaUsar.getHorario().toLocalTime()
						+"\n¿Es esto correcto?\n1. Correcto\n2. Cambiar ticket");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
						continue;
					}
					
					if((opcionMenu == 1 || opcionMenu == 2 )) {
						casoValidoConfirmacion = true;
					}else {
						System.out.println("Opción inválida");
					}
					
				}while(!casoValidoConfirmacion);
				
				casoValido = (opcionMenu == 1) ? true : false;
				
			}while(!casoValido);
			
			if (volverAlMenu) {
				//Rompemos la lógica del sistema de ingresar a la sala de espera
				break;
			}
			
			//Avance de tiempo para hacer la verificación de caducidad correctamente
			avanzarTiempo();
			
			//Verificamos si el ticket no ha caducado
			if (ticketParaUsar.getHorario().isBefore(SucursalCine.getFechaActual())) {
				System.out.println("\nEl ticket seleccionado no puede ser usado, debido a que ha caducado o su película se encuentra en presentación");
				continue;
			}
			
			//Mostramos en pantalla el resultado del proceso
			System.out.println("\nEsperando...");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			//Actualizamos el estado de la fecha actual, de las películas y las salas de cine 
			SucursalCine.setFechaActual(ticketParaUsar.getHorario());
			avanzarTiempo();
			System.out.println("La fecha actual ha sido actualizada con éxito ( " + SucursalCine.getFechaActual() + " )\n(Redireccionando al menú principal...)");
			
			finalizarLogicaSalaDeEspera = true;
			
		}while(!finalizarLogicaSalaDeEspera);
	}
	
	/**
	 * Description : Este método se encarga de generar las dos opciones extra luego de evaluar si una película se encuentra en presentación
	 * @param horariosPeliculaProceso : Este método recibe como parámetro los horarios de la pelicula seleccionada por el usuario durante el proceso
	 * (De tipo ArrayList<LocalDateTime>)
	 * @return <b>String</b> : Este método retorna, en caso de tener horarios disponibles, la opción de comprar en otro horario o volver al menú
	 * principal, sino, la opcion de volver al menú principal.
	 * */
	private static String disponibilidadHorariaFuncionalidad1(ArrayList<LocalDateTime> horariosPeliculaProceso) {
		String resultado = null;
		if (horariosPeliculaProceso.size() > 0) {
			resultado = "\n2. Comprar en otro horario\n3. Volver al menú del sistema de proyecciones";
		}else {
			resultado = "\n2. Volver al menú del sistema de proyecciones";
		}
		
		return resultado;
	}
	
	/**
	 * Description : Este método se encarda de verificar si el proceso de reserva del ticket, desde una película en presentación, ha sido afectado 
	 * por el avance del tiempo, para esto revisamos dos cosas: 
	 * <ol>
	 * <li>El horario de la película en presentación de la sala de cine más 20 minutos (15 minutos de la posibilidad de compra y 5 minutos 
	 * para realizar el proceso) es anterior a la fecha actual.</li>
	 * <li>La película seleccionada por el usuario durante el proceso corresponde a la película que se encuentra en presentación.</li>
	 * </ol>
	 * @param salaDeCineProceso : Este método recibe como parámetro la sala de cine (De tipo SalaCine) donde está siendo presentada la película 
	 * seleccionada por el cliente.
	 * @param peliculaProceso : Este método recibe como parámetro la pelicula (De tipo Pelicula) seleccionada por el usuario durante el proceso
	 * de reserva de ticket.
	 * @return <b>boolean</b> : Este método retorna el estado de la validación para tomar determinadas acciones respecto a esta.
	 * */
	private static boolean verificarIntegridadHorarioSeleccionado(SalaCine salaDeCineProceso, Pelicula peliculaProceso) {
		
		//Avance de tiempo
		avanzarTiempo();
		
		if((salaDeCineProceso.getPeliculaEnPresentacion().equals(peliculaProceso)) 
		&& (salaDeCineProceso.getHorarioPeliculaEnPresentacion().plus(Duration.ofMinutes(20)).isAfter(SucursalCine.getFechaActual()))) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Description : Este método se encarga de verificar si el proceso de reserva de ticket, desde un horario que no se encuentra en presentación,
	 * ha sido afectado por el avance del tiempo, para esto, se verifica si en el array de horarios de la película, se encuentra el horario seleccionado
	 * por el cliente.
	 * @param peliculaProceso : Este método recibe como parámetro la pelicula (De tipo Pelicula) seleccionada por el usuario durante el proceso
	 * de reserva de ticket.
	 * @param horarioProceso : Este método recibe como parámetro el horario (De tipo LocalDateTime) seleccionado por el usuario durante el proceso 
	 * de reserva de ticket.
	 * @return <b>boolean</b> : Este método retorna el estado de la validación para tomar determinadas acciones respecto a esta.
	 * */
	private static boolean verificarIntegridadHorarioSeleccionado(Pelicula peliculaProceso, LocalDateTime horarioProceso) {
		
		//Avance de tiempo
		avanzarTiempo();
		
		if (peliculaProceso.getHorarios().contains(horarioProceso)) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	* Description: Este metodo se encarga de mostrar por pantalla la hora actual de una manera mas organzida y estética.
	* @params date: Se pasa un localDateTime para ejecutar la logica del método.
	* */
	private static void relojDigital(LocalDateTime date) {
		
		// Formatear la hora en formato hh:mm:ss a AM/PM
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = date.format(timeFormatter);

        // Formatear la fecha en un formato amigable
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        String formattedDate = date.format(dateFormatter);
        
        
     // Imprimir la hora en un estilo más decorativo
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     ⏰⏰Current Date⏰⏰      ║");
        System.out.println("    •" + formattedDate + "     ");
        System.out.println("║                              ║");
        System.out.println("         •" + formattedTime +"        ");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Bloque funcionalidad 2
		
	// _____  _   _   _   _    _____   _    ____    _   _       __       _       _   _____       __       _____  	    ______
	//|  __| | | | | | \ | |  / ____| | |  / __ \  | \ | |     /  \     | |     | | |  __ \     /  \     |  __ \ 	   / __   |
	//| |__  | | | | |  \| | | |	  | | | |  | | |  \| |    /    \    | |     | | | |  | |   /    \    | |  | |	  |_/ /   /
	//|  __| | | | | |	  \| | |      | | | |  | | |    \|   /  __  \   | |     | | | |  | |  /  __  \   | |  | |	     /   /
	//| |    | |_| | | |\  | | \____  | | | |__| | | |\  |  / ______ \  | |___  | | | |__| | / ______ \  | |__| |	    /	/_
	//|_|     \___/  |_| \_|  \_____| |_|  \____/  |_| \_| /_/      \_\ |_____| |_| |_____/ /_/      \_\ |_____/ 	   |_______|
	
	private static void compras(Cliente clienteProceso){
		// Seleccion del servicio que se desea acceder
		
		Servicio serviciProceso;
		boolean verificacion = true;
		int servicio = 0;
		int cantidad = 0;
		
		//////////////////////////////////////   Seleccion del servicio   ///////////////////////////////////////////
		
		System.out.println("\n ====== Bienvenido a los servicios de compra ====== \n");
		
		//Le pedimos el servicio al cual desea acceder
		
		do {
			try {
				for(int i = 0;i<clienteProceso.getCineActual().getServicios().size();i++) {
					int n = i+1; 
					System.out.println(n+". "+"Servicio "+clienteProceso.getCineActual().getServicios().get(i).getNombre()+" 🏪🏪🏪🏪🏪");
				}
				System.out.print("0.Volver al menu.\n\nSeleccione una opcion: ");
				servicio = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			if (servicio == 0) {
				Administrador.inicio(clienteProceso);
				break;
			}
			else if (servicio == 1 || servicio == 2) {
				servicio = servicio - 1;
				verificacion = false;
			}
			
		}while(verificacion);
		
		//Servicio de comida
		
			
		//Filtramos el inventario segun el servicio y la sucursal del cine
		serviciProceso = clienteProceso.getCineActual().getServicios().get(servicio);
		System.out.print("\n Bienvenido al servicio "+ serviciProceso.getNombre()+" 📽️📽️📽️");
		serviciProceso.setCliente(clienteProceso);
		serviciProceso.setInventario(serviciProceso.actualizarInventario()); //Ligadura dinamica
		
		/////////////////////////////////  Busqueda inteligente de los productos disponibles segun el pedido   ///////////////////////////////////////////
		
		//Mostramos los productos que hay disponibles en la sucursal y 
		//le pedimos que seleccione uno y la cantidad de producto que desea
		
		verificacion = true;
		boolean verificacion2 = true;
		int eleccion = 0;
		do {
			try {
				System.out.print("\n"+serviciProceso.mostrarInventario());
				if(serviciProceso.getOrden().size()>0) {
					System.out.print("\n"+(serviciProceso.getInventario().size()+1)+". Eliminar un producto de la orden");
				}
				if (serviciProceso.mostrarInventario() == "\nNO HAY PRODUCTOS DISPONIBLES :(\n") {
					Administrador.inicio(clienteProceso);
				}
				System.out.print("\n\nSelecciones una opcion de los productos: ");
				eleccion = Integer.parseInt(sc.nextLine());
				if (eleccion == 0) {
					break;
				}
				
				if (eleccion > serviciProceso.getInventario().size()+1 || eleccion < 1) {
					System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
					continue;
				}
				
				if (eleccion < serviciProceso.getInventario().size()+1 ) {
					eleccion = eleccion - 1;
					System.out.print("\nIngrese el numero de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
				}
				
				if (eleccion == serviciProceso.getInventario().size()+1) {
					
					int eleccion2;
					verificacion = true;
					do {
						try {
							System.out.print("\n"+serviciProceso.mostrarOrden());
							System.out.print("\nSeleccione el producto que quieres eliminar: ");
							eleccion2 = Integer.parseInt(sc.nextLine());
							System.out.print("\n\nSeleccione la cantidad de productos que deasea quitar de su orden: ");
							cantidad = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion2 > serviciProceso.getOrden().size()+1 ) {
							System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
							continue;
						}
						else if(cantidad > serviciProceso.getOrden().get(eleccion2-1).getCantidad()) {
							System.out.print("ERROR EN LA CANTIDAD DE PRODUCTOS QUE DESEA ELIMINAR");
						}
						else if (eleccion2 == 0) {
							verificacion = false;
						}
						else {
							if (cantidad == serviciProceso.getOrden().get(eleccion2-1).getCantidad()) {
								serviciProceso.getOrden().remove(eleccion2-1);
								verificacion = false;
							}
							else {
								double total;
								total = serviciProceso.getOrden().get(eleccion2-1).getPrecio() / serviciProceso.getOrden().get(eleccion2-1).getCantidad();
								serviciProceso.getOrden().get(eleccion2-1).setCantidad(serviciProceso.getOrden().get(eleccion2-1).getCantidad() - cantidad);
								serviciProceso.getOrden().get(eleccion2-1).setPrecio(total * serviciProceso.getOrden().get(eleccion2-1).getCantidad());
								verificacion = false;
								Producto producto = serviciProceso.getOrden().get(eleccion2-1);
								for (Producto producto2 : serviciProceso.getInventario()) {
									if (producto2.getNombre() == producto.getNombre() && producto2.getTamaño() == producto.getTamaño()) {
										producto2.setCantidad(producto2.getCantidad() + cantidad);
									}
								}
							}
						}
						
					}while(verificacion);
				}
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			
			verificacion = true;
			// Se verifica si hay suficientes productos segun la cantidad que pidio el cliente
			
			if (eleccion < serviciProceso.getInventario().size() ) {
				
				Producto producto = serviciProceso.hacerPedido(eleccion, cantidad);
				if(producto == null) {
					System.out.print("\nNo hay suficientes productos de: "+serviciProceso.getInventario().get(eleccion).getNombre()+
							serviciProceso.getInventario().get(eleccion).getTamaño()+" (╥_╥)(╥_╥)(╥_╥) \n\nEn el momento solo hay disponible: "+
							serviciProceso.getInventario().get(eleccion).getCantidad());
				}
				else {
					System.out.print("  --------------------------------------------------- \n");
					System.out.print(" | 🎉🎉🎉🎉El pedido fue realizado con exito🎉🎉🎉🎉 |\n");
					System.out.print("  ---------------------------------------------------  \n");
					serviciProceso.agregarOrden(producto);
					System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
					System.out.print(serviciProceso.mostrarOrden());
					do {
						try {
							System.out.print("\n\n"+"¿Quieres hacer otro pedido? \n1.SI \n2.NO"+
						"\nSeleccione una opcion: ");	
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						verificacion2 = false;
						if(eleccion == 2){
							verificacion = false;
						}
						
					}while(verificacion2);
				}
			}
			
			if (eleccion == serviciProceso.getInventario().size()+1) {
				System.out.print("  --------------------------------------------------- \n");
				System.out.print(" |  🎉🎉🎉🎉 El pedido eliminado con exito 🎉🎉🎉🎉  |\n");
				System.out.print("  ---------------------------------------------------  \n");
				System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
				System.out.print(serviciProceso.mostrarOrden());
				do {
					try {
						System.out.print("\n\n"+"¿Quieres hacer otro pedido? \n1.SI \n2.NO"+
					"\nSeleccione una opcion: ");	
						eleccion = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					verificacion2 = false;
					if(eleccion == 2){
						verificacion = false;
					}
					
				}while(verificacion2);
			}
		}while(verificacion);
		
		//////////////////////////////////////   Descuento asociado a las peliculas   ///////////////////////////////////////////
		
		/* Se verifica si tiene un producto con el mismo genero de un ticket y si es asi se le aplica el descuento,
		 * solo se hace un decuento por compra
		 */
		
		if(serviciProceso.getOrden().size()>0) {
			Producto productoDescuento = serviciProceso.descuentarPorGenero(clienteProceso.getCineActual());
			
			if (productoDescuento != null){
				
				System.out.print("                ------------------------------------------------------------------- \n");
				System.out.print("               |            🎉🎉Felicidades obtuviste un descuento 🎉🎉            |\n");
				System.out.print("               |Por comprar un producto del mismo genero que el tiket que compraste|\n");
				System.out.print("                ------------------------------------------------------------------- \n");
				System.out.print("Todos los produtos de :"+ productoDescuento.getNombre() + productoDescuento.getTamaño() +" Obtuvieron un descuento del 10% en esta compra \n");
				System.out.print("Precio anterior --> $"+productoDescuento.getPrecio()+" Precio actual --> $");
				productoDescuento.setPrecio(productoDescuento.getPrecio()*0.9);
				System.out.println(productoDescuento.getPrecio());
				System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
				System.out.print(serviciProceso.mostrarOrden());
				
				
			}
		}
		
		//////////////////////////////////////  Validacion de los bonos  y codigos de bonos   ///////////////////////////////////////////
		
		//Validacion de los bonos
		boolean verificacionR = true;
		verificacion = true;
		do {
			serviciProceso.actualizarBonos();
			if (0 < serviciProceso.getBonosCliente().size()) {
				do {
					try {
						System.out.println(Servicio.mostrarBonos(serviciProceso));
						System.out.print("Seleccione una opcion: ");
						eleccion = Integer.parseInt(sc.nextLine());
						if (eleccion > serviciProceso.getBonosCliente().size()) {
							System.out.println("\nError, debes escoger una opcion correcta\n");
							continue;
						}
					}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
					verificacion = false;
				}while(verificacion);
				
				if (eleccion == 0) {
					break;
				}
				
				Producto productoBono1 = Servicio.validarBono(serviciProceso.getBonosCliente().get(eleccion-1).getCodigo(),serviciProceso);
				
				if(productoBono1.comprobarBonoEnOrden(serviciProceso)) {
					verificacion = true;
					do {
						try {
							System.out.print("\n"+"¿Que deseas hacer con el producto?\n1.Desea agregarlo al pedido"+
									"\n2.Desea descontarlo del pedido\nSelecciona una opcion:");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}

						if (eleccion == 1) {
							productoBono1.setPrecio(0);
							productoBono1.setNombre("Regalo de Bono "+productoBono1.getNombre());
							serviciProceso.getOrden().add(productoBono1);
							System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
							System.out.print(serviciProceso.mostrarOrden());
							verificacion = false;
						}
						else if (eleccion == 2){
							serviciProceso.descontarProducto(productoBono1);
							System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
							System.out.print(serviciProceso.mostrarOrden());
							verificacion = false;
						}
						else {
							System.out.print("\n\\nSELECCIONE UNA OPCION VALIDA\\n\n");
						}
					}while(verificacion);
				}
				else if (productoBono1 != null){
					productoBono1.setPrecio(0);
					productoBono1.setNombre("Regalo de bono "+productoBono1.getNombre());
					serviciProceso.getOrden().add(productoBono1);
					System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
					System.out.println(serviciProceso.mostrarOrden());
				}
				if ( 0 < serviciProceso.getBonosCliente().size()-1) {
					do {
						try {
							
							System.out.print("\n\n¿Deseas reclamar otro Bono?\n1.SI\n2.NO\nSelecciona una opcion:");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion == 2) {
							verificacionR = false;
						}
						else if (eleccion != 1 && eleccion != 2) {
							System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
						}
					}while(eleccion != 1 && eleccion != 2);
				}
				else {
					verificacionR = false;
				}
			}
			else {
				verificacionR = false;
			}
			
		}while(verificacionR);
		//////////////////////////////////////   Proceso de pago y descuento por valor de compras   ///////////////////////////////////////////
		
		serviciProceso.setValorPedido(serviciProceso.calcularTotal());
		
		if (serviciProceso.getValorPedido()>0) {
			
			double valor = 0;
			double valor1 = 0;
			double descuento = 0;
			verificacion = true;
			boolean condicion = true;
			System.out.print("\n------EL PEDIDO ESTA LISTO SOLO FALTA PAGAR: $"+serviciProceso.getValorPedido()+" ------\n");
			do {
				try {
					
					System.out.println("\nMETODOS DE PAGO DISPONIBLES:\n");
					System.out.println(MetodoPago.mostrarMetodosDePago(clienteProceso));
					System.out.print("Seleccione una opcion: ");
					eleccion = Integer.parseInt(sc.nextLine());
					
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				
				MetodoPago metodoDePago = MetodoPago.usarMetodopago(clienteProceso, eleccion);
				System.out.println("\n----------------------------------------------------------------------------------");
				System.out.println("\n         Gracias por utilizar: "+ metodoDePago.getNombre() +" para hacer tu pago");
				valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
				System.out.println("          Ahora el valor a pagar es de: $"+valor+"\n");
				
				
				if (condicion) {
					condicion = false;
					valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
				//Aqui se hace la ligadura dinamica
					if (serviciProceso.descuentarPorCompra(metodoDePago)) {
						System.out.print("        ------------------------------------------------------------------- \n");
						System.out.print("       |  🎉🎉Felicidades obtuviste un descuento sorpresa en tu compra🎉🎉 |\n");
						System.out.print("        ------------------------------------------------------------------- \n");
						valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
						System.out.println("       Ahora tu cuenta quedo en: $" + valor);
					}
					valor1 = serviciProceso.getValorPedido();
				}
				descuento = descuento + (serviciProceso.getValorPedido() * metodoDePago.getDescuentoAsociado());
				serviciProceso.setValorPedido(metodoDePago.realizarPago(serviciProceso.getValorPedido(),clienteProceso));
				
				
				if (serviciProceso.getValorPedido() == 0) {
					valor1 = valor1 - descuento;
					serviciProceso.setValorPedido(valor1);
					System.out.println("LA CUOTA FUE CUBIERTA EN SU TOTALIDAD 🎉🎉🎉🎉");
					System.out.println("\nEstamos generando su factura, por favor espere...\n");
					try {
						Thread.sleep(3000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print(serviciProceso.factura());
					System.out.print("\n\n          Redireccionando al menu principal\n\n");
					try {
						Thread.sleep(3000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					serviciProceso.procesarPagoRealizado(clienteProceso);
					verificacion = false;
				}
				else {
					System.out.println("\n----------------------------------------------------------------------------------");
					System.out.println("\nFALTA POR TERMINAR DE PAGAR : $" + serviciProceso.getValorPedido() + " (╥_╥)(╥_╥)(╥_╥)");
					continue;
				}
				
			}while(verificacion);
			
		}
		
		else {
			
			System.out.println("\n ******************** Redireccionando al menu principal ******************** \n");
			
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
		
//******************************************************************************************************************************************	   
	
	//Bloque funcionalidad 3
	
	// _____  _   _   _   _    _____   _    ____    _   _       __       _       _   _____       __       _____  	   ________
	//|  __| | | | | | \ | |  / ____| | |  / __ \  | \ | |     /  \     | |     | | |  __ \     /  \     |  __ \ 	  |___    /
	//| |__  | | | | |  \| | | |	  | | | |  | | |  \| |    /    \    | |     | | | |  | |   /    \    | |  | |	     /   /
	//|  __| | | | | |	  \| | |      | | | |  | | |    \|   /  __  \   | |     | | | |  | |  /  __  \   | |  | |	    |    \
	//| |    | |_| | | |\  | | \____  | | | |__| | | |\  |  / ______ \  | |___  | | | |__| | / ______ \  | |__| |      __\	  \        
	//|_|     \___/  |_| \_|  \_____| |_|  \____/  |_| \_| /_/      \_\ |_____| |_| |_____/ /_/      \_\ |_____/ 	  |_______|
	
	private static void calificacion(Cliente clienteProceso) {
		
		
			
				boolean verificar = true;
				int eleccion = 0;
				int eleccion1=0;
				int eleccion2=0;
				
				/** Description: Esta funcionalidad 3 se va a encargar de hacer la respectiva calificacion de peliculas y productos dependiendo
				 * de los gustos del cliente, ya que con estas calificaciones vamos a hacer un proceso interno de logica de negocio 
				 * dentro del cine, para poder saber que peliculas o productos estan funcionando bien o por consecuencia, cuales 
				 * estan funcionando mal
				*/
				//Le damos la bienvenida al cliente
				System.out.println("********Bienvenido a la calificacion de productos*********");
				
				/** Description: Este ciclo es para preguntarle al cliente que es lo que desea hacer dentro de la funcionalidad, si calificar
				 * una pelicula, un producto o volver al menu principal
				 */
				do {
					try {
						System.out.print("\n1.Calificar Comida.\n2.Calificar Pelicula\n3.Volver al menu.\nSeleccione una opcion: ");
						eleccion = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					if (eleccion == 3) {
						Administrador.inicio(clienteProceso);
						break;
					}
					else if (eleccion == 1 || eleccion == 2) {
						verificar = false;
					}
				}while(verificar);
				/**Description: En esta parte hacemos un condicional dependiendo dla opcion que el cliente escoja, si el cliente escoje
				 * la eleccion uno se le da la bienvenida para la calificacion de comidas y por el contrario si la eleccion es dos,
				 * se le da la bienvenida al apartado de calificacion de peliculas.
				 * Metodo mostrarHsitorialDePedidos: Este metodo le muestra a los usuarios los pedidos que han hecho, y por ende tambien verifica
				 * si el cliente si ha hecho almenos una compra, esto es para evitar que un cliente pueda calificar un producto que no ha 
				 * consumido. Cuando se verifica que el cliente ha consumido un producto se le da a escojer que producto desea calificar,
				 * y el cliente le da una valoracion del 1 al 5 dependiendo de los gustos del usuario.
				 *  
				 */
				if (eleccion==1) {
					System.out.println("\n********Bienvenido al apartado de calificacion de comida********");
					if(clienteProceso.getProductosDisponiblesParaCalificar().size() > 0) {
						System.out.println("\n********Estos son los pedidos que has adquirido**********" + "\n" + clienteProceso.mostrarProductosParaCalificar());
						Producto opcionPedido=null;
						int calificacion1=0;
						do {
							try {
								System.out.print("\n*********Seleccione el producto que deseas calificar********");
								eleccion = Integer.parseInt(sc.nextLine());
								if (eleccion == 0) {
									break;
								}
								if (eleccion > clienteProceso.getProductosDisponiblesParaCalificar().size() || eleccion < 1) {
									System.out.print("\n******Error en la seleccion del producto******");
									continue;
								}
								opcionPedido = clienteProceso.getProductosDisponiblesParaCalificar().get(eleccion-1);
								System.out.print("\nIngrese la calificacion del 1 al 5 que le vas a dar a este producto: ");
								calificacion1 = Integer.parseInt(sc.nextLine());
								if (calificacion1>=3) {
									System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + " por lo tanto esta comida esta catalogada como bien calificada" + "***********");
									
								}
								
								else if(calificacion1<=2.99) {
									System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + " por lo tanto esta comida esta catalogada como mal calificada" + "***********");
									
									}
								else {
									System.out.println("Error al calificar la comida, recuerda que es del 1 al 5");
								}
								
								//Eliminamos el producto de los productos disponibles para calificar
								clienteProceso.getProductosDisponiblesParaCalificar().remove(opcionPedido);
								
								Producto prueba = opcionPedido;
								//En este apartado setteamos las nuevas valoraciones hechas por el cliente y tambien aumentamos el numero de valoraciones realizadas
								
								double calificacionGlobalPedidos;
								calificacionGlobalPedidos= (prueba.getValoracionComida() * prueba.getTotalEncuestasDeValoracionRealizadasComida()+calificacion1)/(prueba.getTotalEncuestasDeValoracionRealizadasComida()+1);
								prueba.setTotalEncuestasDeValoracionRealizadasComida(prueba.getTotalEncuestasDeValoracionRealizadasComida()+1);
								prueba.setValoracionComida(calificacionGlobalPedidos);
								
								if (!prueba.verificarInventarioProducto(clienteProceso.getCineActual())== true ) {
									continue;
								}
								
								else {
									System.out.println("De esta comida todavia hay unidades en inventario");
								}
								
								/**Description: En esta parte del codigo, en modo de agradecimiento con el cliente por haber hecho la respectiva calificacion de 
								 * comida o de peliculas le ofrecemos un combo a un precio muy especial, y ya, esta a opcion del cliente si quiere adquirir este
								 * combo especial o no
								 */
									System.out.println("\nComo calificaste un producto te queremos hacer la oferta de un combo especial, deseas verlo?\n1.Si\n2.No");
									eleccion1 = Integer.parseInt(sc.nextLine());
									if (eleccion1==1) {
										Pelicula peliculaCombo=clienteProceso.getCineActual().peorPelicula();
										LocalDateTime opcionHorarioPelicula=peliculaCombo.seleccionarHorarioMasLejano();
										String numAsientoProceso= peliculaCombo.seleccionarAsientoAleatorio(opcionHorarioPelicula);
										Producto productoCombo1=clienteProceso.getCineActual().mejorProducto();
										String codigoBono=productoCombo1.generarCodigoAleatorio(7);
										/**Description: En esta parte del codigo, se le ofrece al cliente el combo especial, este combo especial en este caso, tiene la 
										 * mejor pelicula con el peor producto, esto lo hacemos con el fin de logica de negocio, y podamos tener mejores resultados con 
										 * los productos y peliculas.
										 */
										System.out.println("Estos son los productos escogidos para darte el combo especial: " + "La pelicula " +
										peliculaCombo.getNombre() + " en formato " + peliculaCombo.getTipoDeFormato() + "\nen el horario " + opcionHorarioPelicula + " en el asiento " + numAsientoProceso 
										+"\ny el producto " + productoCombo1.getNombre() + " " + productoCombo1.getTamaño());
										
										double precioTotal=0;
										precioTotal=peliculaCombo.getPrecio()+productoCombo1.getPrecio();
										System.out.println("Este combo tiene un precio de: " + precioTotal + ",deseas adquirirlo? \n1.Si\n2.No");
										eleccion2 = Integer.parseInt(sc.nextLine());
										if(eleccion2==1) {
											//Iniciamos el proceso de pago
											System.out.println("\n		Proceso de pago");
											System.out.println("=====================================================");
											
											boolean pagoRealizado = false;
											boolean casoValido = false;
											boolean casoValidoConfirmacion = false;
											
											MetodoPago metodoPagoProceso = null;
											double precioComboProceso = productoCombo1.getPrecio()+peliculaCombo.getPrecio();
											double precioAcumuladoComboProceso = 0;
											int opcionMenu=0;
											//Selccionar el método de pago para realizar el pago y realizar el pago
											do {
												do {
													opcionMenu = 0;
													try {
														System.out.println("\nEl valor a pagar por el combo es: " + precioComboProceso
														+ "\nEste es el listado de los métodos de pago disponibles:\n" 
														+ MetodoPago.mostrarMetodosDePago(clienteProceso));
														System.out.print("\nElige una de las opciones disponibles para realizar el pago: " );
														opcionMenu = Integer.parseInt(sc.nextLine());
													}catch(NumberFormatException e) {
														System.out.println("\nError, debe ingresar un único dato númerico entre los disponibles");
													}
													
													if (opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size()) {
														//Se selecciona el método de pago
														metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
														casoValido = true;
														
													}else {
														
														System.out.println("\nSeleccione un método de pago entre los disponibles");
														
													}
												}while( !casoValido );
												
												do {
													opcionMenu = 0;
													try {
														System.out.println("\nEl método de pago escogido es: " + metodoPagoProceso.getNombre() 
														+ " ( Precio anterior: " + precioComboProceso + " -> Precio actual: " + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )"
														+ "\n1. Correcto\n2. Cambiar Método de pago");
														opcionMenu = Integer.parseInt(sc.nextLine());
													}catch(NumberFormatException e) {
														System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
													}
													
													switch(opcionMenu) {
													case 1: casoValidoConfirmacion = true; break;
													case 2: casoValidoConfirmacion = true; break;
													default: System.out.println("Opcion Invalida"); casoValidoConfirmacion = false;
													}
													
												}while(!casoValidoConfirmacion);
												
												if (opcionMenu == 2 || opcionMenu == 0) {
													continue;
												}
												
												
												
												//Realizamos el pago y sumamos el precio acumulado para mostrar el valor real del ticket
												precioAcumuladoComboProceso = precioAcumuladoComboProceso + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
												precioComboProceso = metodoPagoProceso.realizarPago(precioComboProceso, clienteProceso);
												
												//Ponemos un delay en pantalla
												System.out.println("\nEstamos procesando su pago, por favor espere...\n");
												try {
													Thread.sleep(3000);
												}catch(InterruptedException e) {
													e.printStackTrace();
												}
											
												//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
												
													
													//Verificamos si el pago fue cubierto en su totalidad
													if (precioComboProceso == 0) {
														
														System.out.println("\nPago realizado, La compra de su ticket fue exitosa\n");
														
														//Creamos nuevas instancias
														Ticket ticketProceso=new Ticket(peliculaCombo,opcionHorarioPelicula,numAsientoProceso,clienteProceso.getCineActual());
														new Bono(codigoBono,new Producto(productoCombo1.getNombre(),productoCombo1.getTamaño(),1),productoCombo1.getTipoProducto(),clienteProceso);
														//Realizamos el proceso correspondiente luego de ser verificado
														ticketProceso.procesarPagoRealizado(clienteProceso);
														int filaProceso = Character.getNumericValue(numAsientoProceso.charAt(0));
														int columnaProceso = Character.getNumericValue(numAsientoProceso.charAt(2));
														peliculaCombo.modificarSalaVirtual(opcionHorarioPelicula, filaProceso-1, columnaProceso-1);

														System.out.println("-----------------------Factura--------------------------");
														System.out.println("------------------Este es tu combo!!---------------------");
														System.out.println(peliculaCombo.getNombre()+ " y " + productoCombo1.getNombre()+ " " + productoCombo1.getTamaño()) ;;
														System.out.println("------Felicidades, gracias por confiar en nosotros----");
														System.out.println("\n");
														pagoRealizado = true;
														
													}else {
														
														//Repetimos el proceso hasta validar el pago
														System.out.println("Tiene un saldo pendiente de : " + precioComboProceso);
														
													}
													
												
											
											}while(!pagoRealizado);
										}
									}
									else {
										System.out.println("Gracias por tu tiempo... Adios ");
									}
									
								
								
								
								
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							
						}while(verificar);
					}
						
					
					else {
						System.out.println("\n******Lastimosamente no has hecho compra de ningun alimento, por lo tanto no puedes calificar ninguno*******");
					}
				}  
				
				/**Description: En esta parte hacemos un condicional dependiendo dla opcion que el cliente escoja, si el cliente escoje
				 * la eleccion uno se le da la bienvenida para la calificacion de comidas y por el contrario si la eleccion es dos,
				 * se le da la bienvenida al apartado de calificacion de peliculas.
				 * Metodo mostrarHistorialDePeliculas: Este metodo le muestra a los usuarios los pedidos que han hecho, y por ende tambien verifica
				 * si el cliente si ha hecho almenos una compra, esto es para evitar que un cliente pueda calificar una pelicula que no ha 
				 * visto. Cuando se verifica que el cliente ha visto una pelicula se le da a escojer que pelicula desea calificar,
				 * y el cliente le da una valoracion del 1 al 5 dependiendo de los gustos del usuario.
				 */
				else if (eleccion==2) {
					System.out.println("\n********Bienvenido al apartado de calificacion de peliculas********");
					if(clienteProceso.getPeliculasDisponiblesParaCalificar().size() > 0) {
						System.out.println("\n********Estas son las peliculas que has visto**********" + "\n" + clienteProceso.mostrarPeliculaParaCalificar());
						Pelicula opcionPelicula=null;
						int calificacion=0;
						do {
							try {
								System.out.print("\n*********Seleccione la pelicula que deseas calificar********");
								eleccion = Integer.parseInt(sc.nextLine());
								if (eleccion == 0) {
									break;
								}
								if (eleccion > clienteProceso.getPeliculasDisponiblesParaCalificar().size() || eleccion < 1) {
									System.out.print("\n******Error en la seleccion de la pelicula******");
									continue;
								}
								opcionPelicula = clienteProceso.getPeliculasDisponiblesParaCalificar().get(eleccion-1);
								System.out.print("\nIngrese la calificacion del 1 al 5 que le vas a dar a esta pelicula: ");
								calificacion = Integer.parseInt(sc.nextLine());
								if (calificacion>=3) {
									System.out.println("\n*********Escogiste la pelicula: " + opcionPelicula.getNombre()+ " " + opcionPelicula.getTipoDeFormato()+ "  y le diste una valoracion de " + calificacion + ", por lo tanto esta pelicula esta catalogada como bien calificada" +"***********");
								}
								else if	(calificacion<=2.99) {
								System.out.println("\n*********Escogiste la pelicula: " + opcionPelicula.getNombre()+ " " + opcionPelicula.getTipoDeFormato()+ "  y le diste una valoracion de " + calificacion +", por lo tanto esta pelicula esta catalogada como mal calificada" + "***********");
								}
								else {
									System.out.println("Error al calificar la pelicula, recuerda que es del 1 al 5");
								}
								
								//Eliminamos la película de las películas disponibles para calificar del cliente
								clienteProceso.getPeliculasDisponiblesParaCalificar().remove(opcionPelicula);
								
								//En este apartado setteamos las nuevas valoraciones hechas por el cliente y tambien aumentamos el numero de valoraciones realizadas
								
								Pelicula prueba1 = opcionPelicula;
								double calificacionGlobalPeliculas;
								calificacionGlobalPeliculas= (prueba1.getValoracion() * prueba1.getTotalEncuestasDeValoracionRealizadas()+calificacion)/(prueba1.getTotalEncuestasDeValoracionRealizadas()+1);
								prueba1.setTotalEncuestasDeValoracionRealizadas(prueba1.getTotalEncuestasDeValoracionRealizadas()+1);
								prueba1.verificarHorariosPeliculas();
								prueba1.setValoracion(calificacionGlobalPeliculas);
								
								if ( !prueba1.verificarHorariosPeliculas()== true ) {
									continue;
								}
								else {
									System.out.println("Esta pelicula todavia tiene horarios");
								}
								/**Description: En esta parte del codigo, en modo de agradecimiento con el cliente por haber hecho la respectiva calificacion de 
								 * comida o de peliculas le ofrecemos un combo a un precio muy especial, y ya, esta a opcion del cliente si quiere adquirir este
								 * combo especial o no
								 */
								System.out.print("\nComo calificaste una pelicula te queremos hacer la oferta de un combo especial, deseas verlo?\n1.Si\n2.No : ");
								eleccion1 = Integer.parseInt(sc.nextLine());
								if (eleccion1==1) {
									
									Pelicula peliculaCombo=clienteProceso.getCineActual().mejorPelicula();
									LocalDateTime opcionHorarioPelicula=peliculaCombo.seleccionarHorarioMasLejano();
									String numAsientoProceso= peliculaCombo.seleccionarAsientoAleatorio(opcionHorarioPelicula);
									Producto productoCombo1=clienteProceso.getCineActual().peorProducto();
									String codigoBono=productoCombo1.generarCodigoAleatorio(5);
									/**Description: En esta parte del codigo, se le ofrece al cliente el combo especial, este combo especial en este caso, tiene la 
									 * mejor pelicula con el peor producto, esto lo hacemos con el fin de logica de negocio, y podamos tener mejores resultados con 
									 * los productos y peliculas.
									 */
									System.out.println("Estos son los productos escogidos para darte el combo especial: " + "La pelicula " +
											peliculaCombo.getNombre() + " en formato " + peliculaCombo.getTipoDeFormato() + "\nen el horario " + opcionHorarioPelicula + " en el asiento " + numAsientoProceso 
											+"\ny el producto " + productoCombo1.getNombre() + " " + productoCombo1.getTamaño());
									
									double precioTotal=0;
									precioTotal=peliculaCombo.getPrecio()+productoCombo1.getPrecio();
									System.out.println("Este combo tiene un precio de: " + precioTotal + ",deseas adquirirlo? \n1.Si\n2.No:  ");
									eleccion2 = Integer.parseInt(sc.nextLine());
									if(eleccion2==1) {
										//Iniciamos el proceso de pago
										System.out.println("\n		Proceso de pago");
										System.out.println("=====================================================");
										
										boolean pagoRealizado = false;
										boolean casoValido = false;
										boolean casoValidoConfirmacion = false;
										
										MetodoPago metodoPagoProceso = null;
										double precioComboProceso = productoCombo1.getPrecio()+peliculaCombo.getPrecio();
										double precioAcumuladoComboProceso = 0;
										int opcionMenu=0;
										//Selccionar el método de pago para realizar el pago y realizar el pago
										do {
											do {
												opcionMenu = 0;
												try {
													System.out.println("\nEl valor a pagar por el combo es: " + precioComboProceso
													+ "\nEste es el listado de los métodos de pago disponibles:\n" 
													+ MetodoPago.mostrarMetodosDePago(clienteProceso));
													System.out.print("\nElige una de las opciones disponibles para realizar el pago: " );
													opcionMenu = Integer.parseInt(sc.nextLine());
												}catch(NumberFormatException e) {
													System.out.println("\nError, debe ingresar un único dato númerico entre los disponibles");
												}
												
												if (opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size()) {
													//Se selecciona el método de pago
													metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
													casoValido = true;
													
												}else {
													
													System.out.println("\nSeleccione un método de pago entre los disponibles");
													
												}
											}while( !casoValido );
											
											do {
												opcionMenu = 0;
												try {
													System.out.println("\nEl método de pago escogido es: " + metodoPagoProceso.getNombre() 
													+ " ( Precio anterior: " + precioComboProceso + " -> Precio actual: " + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )"
													+ "\n1. Correcto\n2. Cambiar Método de pago");
													opcionMenu = Integer.parseInt(sc.nextLine());
												}catch(NumberFormatException e) {
													System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
												}
												
												switch(opcionMenu) {
												case 1: casoValidoConfirmacion = true; break;
												case 2: casoValidoConfirmacion = true; break;
												default: System.out.println("Opcion Invalida"); casoValidoConfirmacion = false;
												}
												
											}while(!casoValidoConfirmacion);
											
											if (opcionMenu == 2 || opcionMenu == 0) {
												continue;
											}
											
											
											
											//Realizamos el pago y sumamos el precio acumulado para mostrar el valor real del ticket
											precioAcumuladoComboProceso = precioAcumuladoComboProceso + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
											precioComboProceso = metodoPagoProceso.realizarPago(precioComboProceso, clienteProceso);
											
											//Ponemos un delay en pantalla
											System.out.println("\nEstamos procesando su pago, por favor espere...\n");
											try {
												Thread.sleep(3000);
											}catch(InterruptedException e) {
												e.printStackTrace();
											}
										
											//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
											
												
												//Verificamos si el pago fue cubierto en su totalidad
												if (precioComboProceso == 0) {
													
													System.out.println("\nPago realizado, La compra de su ticket fue exitosa\n");
													
													//Setteamos el precio del ticket
													Ticket ticketProceso=new Ticket(peliculaCombo,opcionHorarioPelicula,numAsientoProceso,clienteProceso.getCineActual());
													new Bono(codigoBono,new Producto(productoCombo1.getNombre(),productoCombo1.getTamaño(),1),productoCombo1.getTipoProducto(),clienteProceso);
													//Realizamos el proceso correspondiente luego de ser verificado
													ticketProceso.procesarPagoRealizado(clienteProceso);
													int filaProceso = Character.getNumericValue(numAsientoProceso.charAt(0));
													int columnaProceso = Character.getNumericValue(numAsientoProceso.charAt(2));
													peliculaCombo.modificarSalaVirtual(opcionHorarioPelicula, filaProceso, columnaProceso);
													
													System.out.println("-----------------------Factura--------------------------");
													System.out.println("------------------Este es tu combo!!---------------------");
													System.out.println(peliculaCombo.getNombre()+ " y " + productoCombo1.getNombre()+ productoCombo1.getTamaño());
													System.out.println("------Felicidades, gracias por confiar en nosotros----");
													System.out.println("\n");
													pagoRealizado = true;
												}else {
													
													//Repetimos el proceso hasta validar el pago
													System.out.println("Tiene un saldo pendiente de : " + precioComboProceso);
													
												}
												
											
										
										}while(!pagoRealizado);
									}
											
								
								else {
									System.out.println("Gracias por tu tiempo... Adios ");
									Administrador.inicio(clienteProceso);
								}
							}
							
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							
							
						}while(verificar);
						}
					else {
						System.out.println("******Lastimosamente no has visto ninguna pelicula, por lo tanto no puedes calificar ninguna*******");
					}
						
				}
				
		        //Si la eleccion es 3, se devuelve al menu principal
				
				else if (eleccion==3) {
					Administrador.inicio(clienteProceso);
					verificar=true;
						
			     }
				else {
					calificacion(clienteProceso);
				}
				

					}
				

		
	
	 
		
		
//------------------------------------------------------------------------------------------------------------------		
		
	//BLOQUE PARA LA FUNCIONALIDAD 4
	
	
	
//  _____  _   _   _   _    _____   _    ____    _   _       __       _       _   _____       __       _____  	     ____
// |  __| | | | | | \ | |  / ____| | |  / __ \  | \ | |     /  \     | |     | | |  __ \     /  \     |  __ \ 	    /    |
// | |__  | | | | |  \| | | |	   | | | |  | | |  \| |    /    \    | |     | | | |  | |	/    \    | |  | |	   / /|  |
// |  __| | | | | |	   \| | |      | | | |  | | |    \|   /  __  \   | |     | | | |  | |  /  __  \   | |  | |	  / /_|  |_
// | |    | |_| | | |\	| | |____  | | | |__| | | |\  |  / ______ \  | |___  | | | |__| | / ______ \  | |__| |	 |___     _|
// |_|     \___/  |_| \_|  \_____| |_|  \____/  |_| \_| /_/      \_\ |_____| |_| |_____/ /_/      \_\ |_____/ 	     |___|     
	
		
		
		
/**
 * Description : Este método se encarga de toda la gestion de la funcionalidad 4, que incluye adquisicion y recarga de la tarjeta cinemar, ingreso a zona de juegos, ejecucion de juegos, generacion de bonos, etc.
 * @param clienteActual : Este método recibe como parámetro el cliente actual que esta ejecutando el programa
 * (De tipo Cliente)
 * @param sucursalCineProceso : Este método recibe como parámetro la sucursal de cine en la que se esta ejecutando el programa
 * (De tipo SucursalCine)
 * @return <b>void</b> : No hay retorno
 * */
private static void ingresoZonaJuegos(Cliente ClienteActual) {
	
	//ClienteActual.getCineActual().getBonosCreados().clear();
	
	//Se muestra el menu de opciones que el usurio desea realizar 
	try {
		System.out.println("\n😁-😁-😁-Recuerde que para entrar a los juegos es necesario tener la tarjeta cinemar-😁-😁-😁\nDesea:\n1.Ingresar\n2.Volver al menú principal\n3.Salir y Guardar");
		int opcion = (int)Administrador.readLong();
		if (opcion==2) {barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual);}//volver
		else if (opcion==1) {barraCarga("Ingresando");}//ingresar
		else if (opcion==3) {barraCarga("Saliendo");Administrador.salirDelSistema();}//salir
		else {System.out.println("\n👎Opcion Invalida👎");ingresoZonaJuegos(ClienteActual);}	
	
	}catch(InputMismatchException e) {
		System.out.println("❌-Error, debe ingresar un único dato numérico entre los disponibles-❌");
		Administrador.sc.nextLine(); 
		ingresoZonaJuegos(ClienteActual);
	}

	boolean casoValido = true;
	
	do {
		if (!ClienteActual.verificarCuenta()) {
			try {
				System.out.println("\n•No tienes una Tarjeta Cinemar asociada, ¿Deseas Adquirirla?  🤔 -> 💳❔\n1. SI\n2. NO");
				espera(1000);
				int option = (int)Administrador.readLong();
				if (option==1) {
					if (Arkade.verificarTarjetasEnInventario(ClienteActual.getCineActual())) { // se verifica si hay tarjetas disponibles por vender en el array
						barraCarga("Adquiriendo tarjeta");
						System.out.println("\n•💸💸💸El precio de la tarjeta Cinemar es de $5000💸💸💸\n•Este valor sera descontado al saldo de su tarjeta");
						espera(2000);
						//
						//Aca se asocia la primera tarjeta en el array de disponibles al cliente, se le descuenta el valor de la tarjeta y se imprime por pantalla
						Arkade.asociarTarjetaCliente(ClienteActual);
						ClienteActual.getCuenta().hacerPago(5000);
						System.out.println("\n•Su tarjeta 💳 :");
						espera(1000);
						imprimirTarjeta(ClienteActual.getCuenta().getDueno().getNombre(),ClienteActual.getCuenta().getSaldo());
						casoValido = false;
						//
					}
					else {System.out.println("\n•😞😞Lo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde😞😞");Administrador.sc.nextLine(); Administrador.inicio(ClienteActual);}
				
				}
				else if (option ==2) {ingresoZonaJuegos(ClienteActual);}
				else {System.out.println("\n👎•Opcion Invalida👎");}
			}catch(InputMismatchException e) {
				System.out.println("❌-Error, debe ingresar un único dato numérico entre los disponibles-❌");
				Administrador.sc.nextLine();
			}
		}
		else {
			System.out.println("\n•Su tarjeta 💳 :");
			espera(1000);
			imprimirTarjeta(ClienteActual.getCuenta().getDueno().getNombre(),ClienteActual.getCuenta().getSaldo());
			casoValido = false;
		}
	}while(casoValido);
					
	
	int eleccion1 = 0;
	boolean finCiclo = true;
	
	boolean Vcase = true;
	do {
		do {
			
			try {
				System.out.println("•¿Deseas recargar la tarjeta?");
				System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir y Guardar");
				eleccion1 = (int)Administrador.readLong();
				if (eleccion1==1 || eleccion1==2 || eleccion1==3 || eleccion1==4) {
					casoValido = false;
				}
				else {
					System.out.println("👎•Opcion Invalida👎");
					casoValido = true;
				}

			}catch(InputMismatchException e) {
				System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
				Administrador.sc.nextLine();
				casoValido = true;
			}
		}while(casoValido);

		
		//Inicio proceso de pago
		double recargaMaxima = 0;
		double valorRecarga = 0;
		
		for (MetodoPago metodo : ClienteActual.getMetodosDePago()) {
			recargaMaxima+= metodo.getLimiteMaximoPago();
		}
	
	
	
		if(eleccion1 == 1) {
			do {
				try {
					
						
						valorRecarga = 0;
						
						System.out.println("•El valor maximo a recargar por proceso es: $"+recargaMaxima+" intente no superar este valor");
						System.out.print("•Digite el valor a recargar 💰💰💰: ");
						valorRecarga = Administrador.readLong();
						
						if (!(valorRecarga<= recargaMaxima & valorRecarga>0)) {
							System.out.println("\n•El valor ingresado supera el limite maximo de recarga 😠😠");
						}
						
					
				}catch(InputMismatchException e) {
					System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
					Administrador.sc.nextLine();
					casoValido = true;
				}
			}while(!(valorRecarga<= recargaMaxima & valorRecarga>0)); 
		
			casoValido = true;
			int opcionPago = 0;
			double precioRecargaProceso = valorRecarga;
			double totalPagado = 0;
			do {
				do {
					try {
						System.out.println("\n•Escoja su metodo de pago");
						System.out.println("•Cada metodo de pago tiene un monto maximo para recargar y un descuento asociado, en caso de superar este monto debera elegir otro metodo de pago para completar la recarga");
						System.out.println(MetodoPago.mostrarMetodosDePago(ClienteActual));
						opcionPago = (int)Administrador.readLong();
						
						if(!(opcionPago > 0 & opcionPago <= ClienteActual.getMetodosDePago().size() )) {
							System.out.println("👎•Opcion Invalida👎");
						}
					}catch(InputMismatchException e) {
						System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
						Administrador.sc.nextLine();
						casoValido = true;
						opcionPago = 0;
					}
				}while(!(opcionPago > 0 & opcionPago <= ClienteActual.getMetodosDePago().size() ));
				
				MetodoPago metodoPagoProceso = null;
				metodoPagoProceso = ClienteActual.getMetodosDePago().get(opcionPago - 1);
				
				
				
				System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
				+ " ( Precio anterior: " + precioRecargaProceso+ " -> Precio actual: " + precioRecargaProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )");
	
				espera(2000);
				
				//Realizamos lógica de pago 
				//En caso de que el método de pago seleccionado cumpla con el pago, le sumamos al total pagado el precio de recarga actual luego de aplicarle el descuento, en caso de que no le sumamos el pago realizado
				totalPagado += (precioRecargaProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) - metodoPagoProceso.getLimiteMaximoPago() <= 0) ? precioRecargaProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) : metodoPagoProceso.getLimiteMaximoPago(); 
				precioRecargaProceso = metodoPagoProceso.realizarPago(precioRecargaProceso, ClienteActual); //Realizamos el proceso de pago a partir del método de pago
				
				if( precioRecargaProceso == 0) {
					
					barraCarga("Procesando pago");
					System.out.println("Pago exitoso 💯💯💯, se han recargado "+ valorRecarga+" y usted ha pagado "+ totalPagado+" equivalente a un descuento de "+ String.format("%.2f",(100-((totalPagado*100)/valorRecarga)))+ "%");
					MetodoPago.asignarMetodosDePago(ClienteActual);
					ClienteActual.getCuenta().ingresarSaldo(valorRecarga);
					System.out.println("\n•Su tarjeta 💳 :");
					imprimirTarjeta(ClienteActual.getNombre(), ClienteActual.getCuenta().getSaldo());
					totalPagado =0;
					int eleccionUser = 0;
					do {
						try {
							eleccionUser = 0;
							System.out.println("\nDesea: \n1. Ingresar a los juegos\n2. Volver a recargar tarjeta\n3. Volver al inicio\n4. Salir y Guardar");
							eleccionUser = (int)Administrador.readLong();
							switch(eleccionUser) {
							case 1: barraCarga("Ingresando");casoValido = false; Vcase = false; break;
							case 2: barraCarga("Redirigiendo"); casoValido = false; Vcase = true ; break;
							case 3: barraCarga("Volviendo"); Administrador.inicio(ClienteActual); break;
							case 4: barraCarga("Saliendo"); Administrador.salirDelSistema(); break;
							}
						}catch(InputMismatchException e) {
							System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
							Administrador.sc.nextLine();
							eleccionUser = 0;
						}
					}while(eleccionUser !=1 & eleccionUser !=2 & eleccionUser !=3 & eleccionUser !=4);
					
				}
				else {
					espera(1000);
					barraCarga("Procesando Pago");
					
					System.out.println("•Proceso exitoso 👍👍👍, sin embargo, tiene un saldo pendiente por recargar de : " + precioRecargaProceso);
					System.out.println("⚠️•Nota: si no culmina con el pago completo, no se recargara nada a la tarjeta⚠️");
					casoValido = true;
				}
			}while(casoValido);
		}

		

		else if (eleccion1==2) {
			System.out.println("\n-----------------------------------------------------------------------------");
	        System.out.println("😇•Recuerde que debe tener saldo para acceder a los diferentes juegos•😇");
	        System.out.println("-----------------------------------------------------------------------------");
	        System.out.println("\n•Su tarjeta 💳 :");
	        espera(2000);
			imprimirTarjeta(ClienteActual.getNombre(),ClienteActual.getCuenta().getSaldo());
			finCiclo = true;
			while(finCiclo) {
				try {
					System.out.println("\nDesea: ");
					System.out.println("1. Ingresar a los juegos\n2. Recargar tarjeta cinemar\n3. Volver al menu principal\n4. Salir y Guardar");
					int eleccion4 = (int)Administrador.readLong();
					switch (eleccion4) {
					case 1: barraCarga("Ingresando");Vcase= false;finCiclo= false; break;
					case 2: barraCarga("Redirigiendo");Vcase = true;finCiclo= false; break;
					case 3: barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual);
					case 4: barraCarga("Saliendo");Administrador.salirDelSistema();
					default: finCiclo = true; break; 
					}
					
				}catch(InputMismatchException e) {
					System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
					Administrador.sc.nextLine();
				}
			}
		}
		else if (eleccion1==3) {
			barraCarga("Volviendo");
			Administrador.sc.nextLine();
			Administrador.inicio(ClienteActual);
		}
		else if (eleccion1==4) {
			barraCarga("Saliendo");
			Administrador.salirDelSistema();
		}
		
		
	}while(Vcase);
	
	//fin proceso de pago
	
	
	
	
	//Inicio proceso de codigos de descuento
	boolean caso = true;
	boolean redimioCodigo = false;
	String generoCodigoPelicula = null;
	
	do {
		
		
		try {
			espera(1500);
			redimioCodigo = false;
			generoCodigoPelicula = null;
			Arkade.reestablecerPrecioJuegos();
			if (ClienteActual.getCodigosDescuento().size()==0){
				System.out.println("\n😞😞😞•No tienes codigos de descuento asociados😞😞😞\n");
				caso = false;
				break;
			}
			System.out.println("\n🥳🥳🥳•Estos son los codigos de descuento que tienes por la compra de tiquetes en nuestro cine🥳🥳🥳\n¿Cual deseas redimir?\n");
			System.out.println(ClienteActual.mostrarCodigosDescuento());
			int eleccion6 = (int)Administrador.readLong();
			
			
			if (eleccion6 > 0 && eleccion6 <= ClienteActual.getCodigosDescuento().size()) {
				generoCodigoPelicula = Ticket.encontrarGeneroCodigoPelicula(ClienteActual.getCodigosDescuento().get(eleccion6-1));
				ClienteActual.getCodigosDescuento().remove(eleccion6-1);
				System.out.println("•Perfecto 😁, se le asignará un descuento del 20% al precio de los juegos con categoría "+ generoCodigoPelicula);
				espera(2500);
				barraCarga("Aplicando descuento");
				Arkade.AplicarDescuentoJuegos(generoCodigoPelicula);
				caso = false;
				redimioCodigo = true;
			}
			else if (eleccion6 == (ClienteActual.getCodigosDescuento().size()+1)) {
				barraCarga("Ingresando a juegos");
				caso = false;
			}
			else if (eleccion6 == (ClienteActual.getCodigosDescuento().size()+2)){
				barraCarga("Saliendo");
				Administrador.salirDelSistema();
			}

			else {
				System.out.println("Opcion invalida");
			}
		}catch(InputMismatchException e) {
			System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
			Administrador.sc.nextLine();
		}

	}while(caso);
	
	
	
	//inicio proceso de juegos
	String game = null;
	String generoJuego = null;
	
	do {
		int eleccion7 = 0;
		try {
			generoJuego = null;
			System.out.println(Arkade.mostrarJuegos());
			eleccion7 =(int)Administrador.readLong();
			

		}catch(InputMismatchException e) {
			System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
			Administrador.sc.nextLine();
		}
		
		if (eleccion7 > 0 & eleccion7<=SucursalCine.getJuegos().size()) {
			if (ClienteActual.getCuenta().getSaldo()>=SucursalCine.getJuegos().get(eleccion7-1).getValorServicio()) {
				
				ClienteActual.getCuenta().hacerPago(SucursalCine.getJuegos().get(eleccion7-1).getValorServicio());
				System.out.println("•🎮🎮El juego esta por comenzar🎮🎮, el nuevo saldo de tu tarjeta cinemar es : $" + ClienteActual.getCuenta().getSaldo());
				espera(2000);
				barraCarga("Iniciando");
				generoJuego = SucursalCine.getJuegos().get(eleccion7-1).getGeneroServicio();
				System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+generoJuego);
				switch(generoJuego) {
				
				case "Acción": game = juego(new String[]{"BAM","ZAP","GUN"}); break;
				case "Terror": game = juego(new String[]{"BOO","FEAR","EVIL"}); break;
				case "Tecnología" : game = juego(new String[]{"POO","JAVA","5.0"}); break;
				case "Comedia": game = juego(new String[]{"JAJA", "FUN", "LOL"}); break;
				case "Drama": game = juego(new String[]{"CRY","GIRL", "LOVE"}); break;
					
				}
				
				boolean finWhile1 = true;
				try {
					while (finWhile1) {
						System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
						int eleccion8 = (int)Administrador.readLong();
						if (eleccion8==1) {
							finWhile1 = false;
						}
						else if (eleccion8==2) {
							caso = true;
							finWhile1= false;
						}
					}	
				}catch(InputMismatchException e) {
					System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
					Administrador.sc.nextLine();
				}
				
				
			}
			else {
				
				boolean finWhile = true;
				try {
					while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver a ingresar para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine();Administrador.inicio(ClienteActual);
							case 2: ingresoZonaJuegos(ClienteActual);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
				}catch(InputMismatchException e) {
					System.out.println("\n❌-Error, debe ingresar un único dato numérico entre los disponibles-❌, vuelva a realizar el proceso");
					Administrador.sc.nextLine();
				}
				
				
			}
		}
		else if (eleccion7 == (SucursalCine.getJuegos().size()+1)) {
			barraCarga("Volviendo");
			Administrador.sc.nextLine();
			Administrador.inicio(ClienteActual);
		}
		else if (eleccion7 ==(SucursalCine.getJuegos().size()+2)) {
			barraCarga("Saliendo");
			Administrador.salirDelSistema();
		}
	}while(!caso);
	
	
	
	

	//inicio proceso de generacion de bonos
	Random random = new Random();
	double puntuacion = 0;
	if (game.equals("win")) {
		puntuacion = Arkade.getPuntuacionMaxima();
	}
	else if (game.equals("defeat")) {
		puntuacion = Math.round((random.nextDouble() * 10) * 100.0) / 100.0;
	}
	
	System.out.println("•Tu puntuacion es: "+puntuacion);
	
	Bono bonoCliente = null;
	String codigoBono = null;
	
	if (puntuacion==10.0) {
		if (redimioCodigo) {
			if (generoJuego.equals(generoCodigoPelicula)) {
				System.out.println("\n🥇🥇🥇Ganas un bono de comida por obtener la puntuacion maxima en un juego de tipo "+generoJuego+" y redimir un codigo de pelicula del mismo genero🥇🥇🥇");
				barraCarga("Generando bono");
				espera(3000);
				bonoCliente = Bono.generarBonoComidaJuegos(ClienteActual.getCineActual(), ClienteActual);
				
				
				if (!(bonoCliente == null)) {
					bonoCliente.setCliente(ClienteActual);
					codigoBono = bonoCliente.getCodigo();
					ClienteActual.getCodigosBonos().add(codigoBono);
					
					
					System.out.println("•Reclama el bono con el codigo en nuestro 🍕servicio de comida🍔");
				}
				
				else {
					System.out.println("•😭Error al asignar bono debido a que no hay productos de comida disponibles😭");
				}
			}
			else {
				System.out.println("\n🥇🥇🥇Ganas un bono de souvenirs por obtener la puntuacion maxima, !Felicidades¡🥇🥇🥇");
				barraCarga("Generando bono");
				espera(3000);
				bonoCliente = Bono.generarBonoSouvenirJuegos(ClienteActual.getCineActual(), ClienteActual);
				
				if (!(bonoCliente == null)) {
					bonoCliente.setCliente(ClienteActual);
					codigoBono = bonoCliente.getCodigo();
					ClienteActual.getCodigosBonos().add(codigoBono);
					
					
					System.out.println("•Reclama el bono con el codigo en nuestro servicio de 🎁souvenirs🎁");
				}
				
				else {
					System.out.println("•😭Error al asignar bono debido a que no hay productos de souvenir disponibles😭");
				}

			}
		}
		else {
			System.out.println("\\n🥇🥇🥇Ganas un bono de souvenirs por obtener la puntuacion maxima, !Felicidades¡🥇🥇🥇");
			barraCarga("Generando bono");
			espera(3000);
			bonoCliente = Bono.generarBonoSouvenirJuegos(ClienteActual.getCineActual(),ClienteActual);
			
			if (!(bonoCliente == null)) {
				bonoCliente.setCliente(ClienteActual);
				codigoBono = bonoCliente.getCodigo();
				ClienteActual.getCodigosBonos().add(codigoBono);

				
				
				System.out.println("•Reclama el bono con el codigo en nuestro servicio de 🎁souvenirs🎁");
			}
			
			else {
				System.out.println("•😭Error al asignar bono debido a que no hay productos de souvenir disponibles😭");
			}
		}
	}
	

	System.out.println("\n☻☻☻Gracias por jugar con nosotros☻☻☻\n");
	barraCarga("Redireccionando al menú principal");
	Administrador.sc.nextLine();
	Administrador.inicio(ClienteActual);
	
}
	
	
//Fin de la funcionalidad
	
//---------------------------------------------------------------------------------------------------------------	
	
//Inicio de metodos auxiliares de la funcionalidad 4
	
/**
 * Description : Este método se encarga de simular una barra de carga haciendo una serie de prints por pantalla
 * @param word : Este método recibe como parámetro una palabra que se muestra en la barra de carga
 * (De tipo String)
 * @return <b>void</b> : No hay retorno
 * */
private static void barraCarga(String word) {
	System.out.println("               "+word+"....");
    
    for (int i=0;i<42 ;i++ ) {
    	try {
            Thread.sleep(19);
            System.out.print("|");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println(" ");
}


/**
 * Description : Este método se encarga de imprimir por pantalla la tarjetaCinemar del usuario, con su nombre y saldo correspondiente
 * @param nombre : Este método recibe como parámetro el nombre del usuario
 * (De tipo String)
 * @param saldo : Este método recibe como parámetro el saldo de la tarjeta del usuario
 * (De tipo double)
 * @return <b>void</b> : No hay retorno
 * */
private static void imprimirTarjeta(String nombre, double saldo) {
	System.out.println("\n        ╔══════════════════════════╗");
    System.out.println("        ║      Tarjeta Cinemar     ║");
    System.out.println("        ╠══════════════════════════╣");
    String linea = "        ║ Dueño: "+nombre;
    for (int i = linea.length();i<36; i++) {
    	if (i ==35) {
    		linea = linea+"║";
    	}
    	else {linea = linea+" ";} 
    }
    System.out.println(linea);
    String line = "        ║ Saldo: $"+saldo;
    for (int i = line.length();i<38; i++) {
    	if (i ==35) {
    		line = line+"║";
    	}
    	else {line = line+" ";} 
    }
    System.out.println(line);
    System.out.println("        ╚══════════════════════════╝\n");
}


/**
 * Description : Este método se encarga de retrasar determinado tiempo la ejecucion del programa mediante una espera
 * @param time : Este método recibe como parámetro el numero de milisegundos a esperar
 * (De tipo int)
 * @return <b>void</b> : No hay retorno
 * */
private static void espera(int time) {
	try {
        Thread.sleep(time);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

/**
 * Description : Este método se encarga de ejecutar el juego de la funcionalidad 4, el cual es un HangMan en donde se escoje una palabra aleatoria del parametro
 * @param PALABRAS : Este método recibe como parámetro una lista de la palabras con las que se jugara para adivinarlas
 * (De tipo String)
 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si el usuario acertó palabra o no
 * */
private static String juego(String[] PALABRAS) {
    String match = null;

    sc.nextLine();
    String palabraSecreta = PALABRAS[(int) (Math.random() * PALABRAS.length)];
    char[] palabraAdivinada = new char[palabraSecreta.length()];
    String letrasUsadas = ""; // Para rastrear letras ya usadas
    int intentosRestantes = 7;

    for (int i = 0; i < palabraAdivinada.length; i++) {
        palabraAdivinada[i] = '_';
    }

    while (intentosRestantes > 0 && !adivinado(palabraAdivinada)) {
        System.out.println("Intentos restantes: " + intentosRestantes);
        System.out.print("Palabra: ");
        for (char c : palabraAdivinada) {
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Ingresa una letra: ");
        String input = sc.nextLine().toUpperCase();
        if (input.length() != 1 || !esLetraValida(input.charAt(0))) {
            System.out.println("Carácter inválido. Intenta otra vez.");
            intentosRestantes--;
            continue;
        }

        char letra = input.charAt(0);

        // Verificar si la letra ya fue usada
        if (letrasUsadas.contains(String.valueOf(letra))) {
            System.out.println("Ya has usado esa letra. Intenta otra.");
            continue;
        }

        letrasUsadas += letra;

        if (palabraSecreta.indexOf(letra) >= 0) {
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra) {
                    palabraAdivinada[i] = letra;
                }
            }
        } else {
            System.out.println("¡Incorrecto! La letra '" + letra + "' no está en la palabra.");
            intentosRestantes--;
        }
    }

    if (adivinado(palabraAdivinada)) {
        System.out.println("¡Felicidades! ¡Has adivinado la palabra!: " + palabraSecreta);
        match = "win";
    } else {
        System.out.println("¡Oh no! Te has quedado sin intentos. La palabra era: " + palabraSecreta);
        match = "defeat";
    }
    return match;
}



/**
 * Description : Este método se encarga verificar si la lista de char dada en el parametro no contiene el caracter "_" para verificar si la palabra fue adivinada
 * @param palabraAdivinada : Este método recibe como parámetro una lista
 * (De tipo char)
 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si cumple la condicion
 * */
private static boolean adivinado(char[] palabraAdivinada) {
    for (char c : palabraAdivinada) {
        if (c == '_') {
            return false;
        }
    }
    return true;
}


/**
 * Description : Este método se encarga de verificar si el parametro es una letra del alfabeto español, incluyendo ñ
 * @param caracter : Este método recibe como parámetro un caracter que es ingresado por el usuario
 * (De tipo char)
 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si cumple la condicion de ser una letra del alfabeto
 * */
private static boolean esLetraValida(char caracter) {
    return (caracter >= 'A' && caracter <= 'Z') || caracter == 'Ñ' ||
           (caracter >= '0' && caracter <= '9') || caracter == '.';
}


/**
 * Description : Este método se encarga de mostrar por pantalla el bono que fue generado al cliente, ya sea de souvenir o de comida.
 * @param productos : Es el arrayList de los productos disponibles a ser escogidos.
 * @param numeroAleatorio : Es un numero generado aleatoreamente para ser usado en la escogencia del producto del bono
 * @param name : Es un string con el nombre del tipo de bono.
 * @param String : Es un String con el codigo de identificacion del bono
 * @return <b>void</b> : No hay retorno
 * */
public static void mostrarBono(ArrayList<Producto> productos, int numeroAleatorio, String name, String code) {
	
	System.out.println("\n        ╔══════════════════════════╗");
	if (name.equals("Comida")){
		System.out.println("        ║        Bono "+name+"       ║");
	}
	else if(name.equals("Souvenir")) {
		System.out.println("        ║        Bono "+name+"     ║");
	}
    
    System.out.println("        ╠══════════════════════════╣");
    String linea = "        ║ Producto: " + productos.get(numeroAleatorio).getNombre();
    for (int i = linea.length(); i < 36; i++) {
        if (i == 35) {
            linea = linea + "║";
        } else {
            linea = linea + " ";
        }
    }
    System.out.println(linea);
    String line = "        ║ Codigo:   " + code;
    for (int i = line.length(); i < 38; i++) {
        if (i == 35) {
            line = line + "║";
        } else {
            line = line + " ";
        }
    }
    System.out.println(line);
    System.out.println("        ╚══════════════════════════╝\n");
}

//------------------------------------------------------------------------------------------------------------------		
	
	//Bloque funcionalidad 5
	
// _____  _   _   _   _    _____   _    ____    _   _       __       _       _   _____       __       _____  	   ______  
//|  __| | | | | | \ | |  / ____| | |  / __ \  | \ | |     /  \     | |     | | |  __ \     /  \     |  __ \ 	  |   ___|
//| |__  | | | | |  \| | | |	  | | | |  | | |  \| |    /    \    | |     | | | |  | |   /    \    | |  | |	  |  |___
//|  __| | | | | |	  \| | |      | | | |  | | |    \|   /  __  \   | |     | | | |  | |  /  __  \   | |  | |	  |___   |
//| |    | |_| | | |\  | | \____  | | | |__| | | |\  |  / ______ \  | |___  | | | |__| | / ______ \  | |__| |	   ___|  |
//|_|     \___/  |_| \_|  \_____| |_|  \____/  |_| \_| /_/      \_\ |_____| |_| |_____/ /_/      \_\ |_____/ 	  |______|    

	
	/**
	 * Description : Este método se encarga de gestionar la funcionalidad 5: Sistema de membresías.
	 * El cliente accede a nuestro menú donde se muestran las distintas opciones disponibles a escoger en la sucursal actual. Cada
	 * opción tiene consigo sus respectivos beneficios y requisitos. Una vez se ha seleccionado, se realiza el pago de esta
	 * y se asigna un apuntador de tipo Membresia en el atributo del cliente. Al realizar este proceso, los métodos de pago se ven
	 * modificados, se activa la acumulación de puntos y se obtiene una cartelera personalizada.
	 * @param clienteProceso : Se pide al cliente para realizar la búsqueda de membresías disponibles en el inventario de la sucursal actual.
	 * Además, en base al tipo de membresía seleccionada, se modifican atributos del cliente como membresia, fechaLimiteMembresia, la lista de
	 * métodos de pago, etc.
	 */
	private static void adquirirMembresia(Cliente clienteProceso) {
		System.out.println("Bienvenido a nuestro plan de membresias en el cine de Marinilla, " + clienteProceso.getNombre() + ".");
		boolean casoValido = false;
		int opcionMenu = 0;
		do {
			try {
				System.out.println("¿Desea ingresar o volver?" +"\n1.Ingresar" + "\n2.Volver al menú principal" + "\n3.Salir");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			
			switch (opcionMenu) {
				case 1: casoValido = true; break;
				case 2: Administrador.inicio(clienteProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida."); break;
			}
			
		}while(!casoValido);
		//Se da a escoger al usuario la membresia
		Membresia membresiaNueva = null;
		do {
			opcionMenu = 0;
			System.out.print(Membresia.verificarMembresiaActual(clienteProceso));
			System.out.print(Membresia.mostrarCategoria(clienteProceso, clienteProceso.getCineActual()) + "6. Volver al inicio.\n \nIngrese el número de la categoria deseada o volver al inicio: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso); break;}
			else if (opcionMenu >0 && opcionMenu <6) {
				//Se revisa si el cliente esta intentando seleccionar la misma categoria pero aún no es tiempo de renovarla.
				if (clienteProceso.getMembresia()!=null && opcionMenu == clienteProceso.getMembresia().getCategoria() && clienteProceso.getFechaLimiteMembresia().minusDays(6).isAfter(SucursalCine.getFechaActual().toLocalDate())) {
					System.out.println("Usted ya posee esta categoría.\nPor favor seleccionar otra opción habilitada o esperar hasta el periodo de renovación (5 días).\n");
					continue;
				}
				//Se verifica si se cumple con los requisitos para adquirir la membresia.
				boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(clienteProceso, opcionMenu, clienteProceso.getCineActual());
				System.out.print("\nCargando...\n");
				try {
				Thread.sleep(3000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
					if (requisitosMembresia == false) {
						System.out.print("\n⚠️•No puedes adquirir esta membresía debido a que no cumples con \nlos criterios establecidos para ello o no hay unidades en el momento.️•⚠️\n" +
								"Puntos actuales: " + clienteProceso.getPuntos() + "\n" +
								"Peliculas vistas: " + clienteProceso.getHistorialDePeliculas().size() + "\n" +
								"\nRedirigiendo al menú de membresias\n");
						continue;
					} else {
						membresiaNueva = Membresia.asignarMembresiaNueva(opcionMenu);
					}
			} else {
				continue;
			}
		} while (membresiaNueva == null);
		
		//Una vez se ha escogido la membresia, se pasa a realizar el pago
		double valorAPagar = membresiaNueva.getValorSuscripcionMensual();
		do {
			opcionMenu = 0;
			System.out.print("El precio de la membresia es de " + valorAPagar 
			+ ". Por favor, seleccione el método de pago a usar:\n"
			+ MetodoPago.mostrarMetodosDePago(clienteProceso) + "\n6. Volver al inicio \nIngrese la opción: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso);}
			//En caso de que el cliente intente escoger la opción de puntos pero no tenga membresía, se advierte y se vuelve a la selección de pagos.
			else if (clienteProceso.getMembresia()==null && opcionMenu==5) {
				System.out.println("\nPor favor, seleccione una de las opciones habilitadas.");
				continue;
			}
			//Se obtiene le método de pago seleccionado en el menú anterior y se valida el nuevo precio en caso de tener descuento.
			MetodoPago metodoPagoSeleccionado = MetodoPago.usarMetodopago(clienteProceso, opcionMenu);
			try {
				if (metodoPagoSeleccionado.getDescuentoAsociado() != 0 && valorAPagar == membresiaNueva.getValorSuscripcionMensual()) {
					valorAPagar = valorAPagar - valorAPagar * metodoPagoSeleccionado.getDescuentoAsociado();
					System.out.print("Con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ ", el nuevo monto a pagar es " 
						+ valorAPagar +".\n1. Confirmar pago. \n2. Cambiar método de pago. \nPor favor, seleccione una opción: ");
					
				}else {
					System.out.print("\nEl monto a pagar con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ " es " 
						+ valorAPagar + ". \n1. Confirmar pago. \n2. Cambiar método de pago. \nPor favor, seleccione una opción: ");
				}
			opcionMenu = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
			System.out.print("Error, debes ingresar un dato numérico");}
			
			if (opcionMenu == 1) {
				valorAPagar = metodoPagoSeleccionado.realizarPago(valorAPagar, clienteProceso);
			} else if (opcionMenu == 2) {
				valorAPagar = membresiaNueva.getValorSuscripcionMensual();
				continue;
			}
		}while (valorAPagar != 0); 
		
		//Una vez confirmada la totalidad del pago, se procede realizar la asignación de membresía con sus beneficios.
		System.out.print("\nEstamos procesando su pago...\n");
		try {
			Thread.sleep(3000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		membresiaNueva.procesarPagoRealizado(clienteProceso);
		System.out.print(
				"=== Factura de compra ===\n" +
				"Nombre dueño: " + clienteProceso.getNombre() + "\n" +
				"Documento: " + clienteProceso.getDocumento() + "\n" +
				"Duración: " + clienteProceso.getFechaLimiteMembresia() + "\n" +
				"Lugar de compra: " + clienteProceso.getCineActual().getLugar()	 + "\n" +
				membresiaNueva.factura());
		
		//Cada vez que se adquiera/renueva una membresía, se dará una asignación/recarga a la tarjeta Cinemar que es usada en la funcionalidad 4.
		TarjetaCinemar tarjetaCinemarActual = clienteProceso.getCuenta();
		int tipoMembresia = 0;
		if (tarjetaCinemarActual != null) {
			tipoMembresia = clienteProceso.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				tarjetaCinemarActual.ingresarSaldo(10000);
			}else {
				tarjetaCinemarActual.ingresarSaldo(20000);
			}
		}else {
			boolean finalizarCompra = false;
			double saldoCuenta = 0.0;
			tipoMembresia = clienteProceso.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				saldoCuenta = 5000.0;
			} else {
				saldoCuenta = 20000.0;
			}
			do {
				try{
					opcionMenu = 0;
					System.out.print("\nGracias por adquirir el programa de membresia. Su nueva membresia es " + clienteProceso.getMembresia().getNombre() + " de categoria" + clienteProceso.getMembresia().getCategoria()+"\nComo regalo, le otorgamos una tarjeta cinemar con "
							+ (int)saldoCuenta + " recargados.\n1. Confirmar.\n2. Rechazar. \nPor favor, seleccione una opción: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch (NumberFormatException e){
					System.out.print("Error. Por favor, escriba un dato numérico");}
				if (opcionMenu == 1) {
					Arkade.asociarTarjetaCliente(clienteProceso);
					clienteProceso.getCuenta().ingresarSaldo(saldoCuenta);
					System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+clienteProceso.getCuenta().getDueno().getNombre()+"\nSaldo: $"+clienteProceso.getCuenta().getSaldo());
					System.out.print("\nGracias por su compra. Redirigiendo al menú principal.");
					finalizarCompra = true;
				} else {
					try {
						opcionMenu = 0;
						System.out.print("Recuerde que esta oferta es única. ¿Esta seguro? \n1. Si. \n2. No. \nIngrese la opción: ");	
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e){
						System.out.print("Error. Por favor, escriba un dato numérico");}
					if (opcionMenu == 1 ) {
						System.out.print("Gracias por su compra. Redirigiendo al menú principal.");
						finalizarCompra = true;
					} else {
						continue;
					}
				}
				try {
					Thread.sleep(3000);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
			} while (!finalizarCompra);
		}
	}
}

	
//------------------------------------------------------------------------------------------------------------------	
	
	


