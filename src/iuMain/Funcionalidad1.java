package iuMain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.Ticket;

public class Funcionalidad1 {
	
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * Description: Este método se encarga mostrar en pantalla los procesos de la funcionalidad 1, para que el cliente elija uno de ellos, 
	 * una vez termine su interacción, el cliente regresará a este mismo menú, en caso de que quiera regresar al menú principal termina el
	 * ciclo y se ejecuta el menú inicial.
	 * */
	static void ingresarASistemaDeProyecciones(Cliente clienteProceso) {
		
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
	 * 
	 * 1. En caso de que sí, se le pregunta al cliente si quiere comprar la película en ese horario, dada una respuesta positiva, con la sala de cine
	 * previamente encontrada realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine, 
	 * le pedimos al cliente que seleccione el asiento, se valida su disponibilidad, se realiza el proceso de pago y se asigna el ticket al cliente).
	 * 
	 * 2. En caso de que haya decidido no comprar en ese horario o directamente la película no estaba en presentación, mostramos los horarios 
	 * de la película, el usuario selecciona uno de ellos y realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de 
	 * cine virtual asociada al horario previamente seleccionado, el cliente selecciona el asiento deseado, se valida su disponibilidad, 
	 * se realiza el proceso de pago y se asigna el ticket al cliente).
	 * 
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo cliente) que desea realizar la reserva de un ticket.
	 * @param sucursalCineProceso: Este método recibe como parámetro el cine (De tipo SucursalCine) que se seleccionó luego del proceso de login 
	 * y con este se hacen las búsquedas para mostrar en pantalla las películas en cartelera y las salas de cine disponibles.
	 * */
	static void reservarTicket(Cliente clienteProceso) {
		
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
			
			if (opcionMenu == 2) {
				break;
			}
			
			//Funcionalidad reserva de ticket
			
			//Mostramos una cartelera personalizada de acuerdo a la edad del cliente, si la película tiene horarios disponibles o se encuentra en presentación 
			// Y las recomendaciones en caso de que el cliente tenga membresía(futuramente la membresía)
			ArrayList<Pelicula> carteleraPersonalizadaProceso = Pelicula.filtrarCarteleraPorCliente(clienteProceso, clienteProceso.getCineActual());
			
			if (carteleraPersonalizadaProceso.size() == 0) {
				System.out.println("No hay películas disponibles para reservar (Redireccionando al menú principal...)");
				Administrador.inicio(clienteProceso);
			}
			ArrayList<String> nombresPeliculasCarteleraPersonalizadaProceso = Pelicula.filtrarNombrePeliculas(carteleraPersonalizadaProceso);

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
						//Rompemos el bucle
						casoValido = true;
					}else if (opcionMenu == Integer.valueOf(nombresPeliculasCarteleraPersonalizadaProceso.size()) + 1) {
						//Volvemos al menú del sistema de proyecciones
						casoValido = true;
						volverAlMenu = true;
					}else {
						System.out.println("\nOpción inválida");
					}
					
				}while(!casoValido);
				
				if (volverAlMenu) {
					//Volvemos del menú sistema de proyecciones y cerramos el bucle de la lógica de selección de película
					break;
				}
				
				//Buscamos las películas que coinciden con el nombre seleccionado con el cliente
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
				
				//En caso de que el cliente elija regresar y cambiar película
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
			
			if (volverAlMenu) {
				//Rompemos la lógica de reserva de ticket para regresar al menú principal
				break;
			}
			
			
			//Creamos el espacio en memoria para almacenar la información dada por el cliente luego de solicitarla
			SalaCine salaDeCineProceso = null;
			String numeroAsientoProceso = null;
			LocalDateTime horarioProceso = null;
			
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
						+ Funcionalidad1.disponibilidadHoraria(horariosPeliculaProceso));
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
								numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso);
								
								//Obtenemos el horario de la película seleccionada
								horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
								
								realizarReservaDePeliculaEnPresentacion = true;
								
								break;
									
							case 2: casoValidoConfirmacion = true; 							
									//Se piden los datos de reserva de ticket de la película en otro horario
									
									//El cliente elige el horario de la película seleccionada 
									horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, horariosPeliculaProceso);
									
									//El cliente elige el asiento de la película seleccionada
									numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso);
									break;
									
							case 3: casoValidoConfirmacion = true; volverAlMenu = true; break;
							
							default: casoValidoConfirmacion = false; System.out.println("Digite un número válido");
						}
						
					}else {
						switch(opcionMenu) {
							case 1: casoValidoConfirmacion = true;
									//Se piden los datos de reserva de ticket en la sala de cine en cuestión
									salaDeCineProceso = peliculaProceso.whereIsPeliculaEnPresentacion(clienteProceso.getCineActual());
									
									//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
									numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso);
									
									//Obtenemos el horario de la película seleccionada
									horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
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
					
					//El cliente elige el asiento de la película seleccionada
					numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso);
				}else {
					System.out.println("La película seleccionada se encuentra únicamente en presentación o no tiene asientos disponibles." + 
					"\n(Serás redireccionado al menú inicial de este proceso...)");
					reservarTicket(clienteProceso);
				}
				
			}
			
			if (volverAlMenu) {
				//Rompemos la lógica de la reserva de ticket y regresamos al menú principal
				break;
			}
			
			//Se genera el último mensaje con posibilidad de regresar al menú principal
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
			
			//Creamos el apuntador del ticket
			Ticket ticketProceso = null;
			
			if (casoContinuarProcesoPago) {
				
				//Creamos el ticket con su respectivo precio e informamos al cliente en caso de recibir un descuento
				ticketProceso = new Ticket(peliculaProceso, horarioProceso, numeroAsientoProceso, clienteProceso.getCineActual());
				//Mostramos un mensaje en pantalla en caso de recibir el descuento
				if ( ticketProceso.getPrecio() != peliculaProceso.getPrecio() ) {
					
					System.out.println("\n🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
					
					if (peliculaProceso.getTipoDeFormato().equals("3D") || peliculaProceso.getTipoDeFormato().equals("4D") ) {
						System.out.println("Felicidades, por ser nuestro cliente número " + Ticket.getCantidadTicketsCreados() 
						+ " has recibido un descuento del 50% por la compra de tu ticket\n"
						+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
					}else {
						System.out.println("Felicidades, por ser nuestro cliente número: " + Ticket.getCantidadTicketsCreados() 
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
							
				//Realizamos el pago y sumamos 
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
						
						System.out.println( clienteProceso.getFacturas().get(clienteProceso.getFacturas().size() - 1) );
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
						
						System.out.println( clienteProceso.getFacturas().get( clienteProceso.getFacturas().size() - 1 ) );
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
	 * para que de allí podamos mostrar en pantalla las llaves de su diccionario de horarios.
	 * @param horariosPeliculaProceso : Este método recibe como parámetro los horarios disponibles de la película (De tipo ArrayList<LocalDateTime>)
	 * obtenido durante el proceso de la reserva de ticket.
	 * @return <b>LocalDateTime</b> : Este método retorna el horario seleccionado por el cliente, para continuar con el proceso de la reserva de ticket.
	 * */
	static LocalDateTime seleccionarHorarioPelicula(Cliente clienteProceso, Pelicula peliculaProceso, ArrayList<LocalDateTime> horariosPeliculaProceso) {
		
		boolean casoValido = false;
		boolean casoValidoEleccionHorario = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		LocalDateTime horarioProceso = null;
		
		System.out.println("\n		Selección de horario");
		System.out.println("=====================================================");
		do {
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
			
		}while(!casoValido);
		
		return horarioProceso;
	}
	
	/**
	 * Description: Este método se encarga de seleccionar el número de asiento del cliente para ver una película en un horario previamente seleccionado, 
	 * para hacer esto, Se muestra en pantalla los asientos de la sala de cine virtual, con su respectiva disponibilidad en el horario
	 * de la película seleccionado por el cliente, tras esto, el cliente elige la fila, luego la columna, se valida si el asiento en cuestión 
	 * se encuentra disponible y una vez cumplida la verificación, retornamos el número del asiento seleccionado.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) seleccionado en el proceso de login.
	 * @param horarioProceso : Este método recibe como parámetro el horario (De tipo LocalDateTime) seleccionado durante el proceso de reserva de ticket.
	 * @param horarioProceso : Este método recibe como parámetro la película (De tipo Pelicula) seleccionada durante el proceso de reserva de ticket.
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente.
	 * */
	static String seleccionarAsiento(Cliente clienteProceso, LocalDateTime horarioProceso, Pelicula peliculaProceso) {
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
					System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles\n");
					continue;
				}
				
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso)).length))){
					System.out.println("\nLa fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles\n");
					continue;
				}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("La fila seleccionada es: " + filaProceso + "\n1. Correcto \n2. Cambiar fila");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
						continue;
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
					System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
				
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso))[0].length))){
					System.out.println("\nLa columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles\n");
					continue;
				}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("La columna seleccionada es: " + columnaProceso + "\n1. Correcto \n2. Cambiar columna");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
						continue;
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
				
			}while(!(casoValidoConfirmacion));
			
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
	 * luego la columna, se valida si el asiento en cuestión se encuentra disponible y una vez cumplida la verificación,
	 * retornamos el número del asiento seleccionado.
	 * @param salaDeCinePresentacionProceso : Este método recibe como parámetro la sala de cine en la cuál se encuentra la película seleccionada durante
	 * el proceso de la reserva de ticket.
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente.
	 * */
	static String seleccionarAsiento(SalaCine salaDeCinePresentacionProceso) {
		
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
						System.out.println("\nError, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles\n");
						continue;
				}
						
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos().length))){
					System.out.println("\nLa fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles\n");
					continue;
					}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("La fila seleccionada es: " + filaProceso + "\n1. Correcto \n2. Cambiar fila");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
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
					System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
						
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos()[0].length))){
					System.out.println("\nLa columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles\n");
					continue;
				}
					
				do {
					opcionMenu = 0;
					try {
						System.out.println("La columna seleccionada es: " + columnaProceso + "\n1. Correcto \n2. Cambiar columna");
						opcionMenu = Integer.parseInt(sc.nextLine()); 
					}catch (NumberFormatException e) {
						System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
						
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
						
			}while(!(casoValidoConfirmacion));
					
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
	 * Description: Este método se encarga de realizar el proceso para que un usario pueda ingresar a una de las salas de cine,
	 * para esto debemos cumplir un requisito fundamental, el cual es que el cliente tenga al menos un ticket asociado correspondiente a la
	 * sucursal desde la cual está intentando acceder, dado el caso de que no, el cliente será redirigido al menú del sistema de proyecciones,
	 * en caso de que sí podemos continuar con el proceso.
	 * Mostramos en pantalla las salas de cine disponibles con información relevante de estas (Número de sala, película en presentación y horario),
	 * el cliente selecciona una de estas, verificamos que alguno de los tickets del cliente, cumpla con los requisitos necesarios para 
	 * poder ingresar a la sala de cine, en caso de que sí, se muestra en pantalla una representación de la sala de cine y una vez termina 
	 * la película se redirecciona al cliente al menú del sistema de proyecciones, en caso de que no, se vuelve a mostrar por pantalla las 
	 * salas de cine disponibles.
	 * @param clienteProceso : Este método recibe como parámetro al cliente (De tipo Cliente), que ingresó desde el menú
	 * del sistema de proyecciones.
	 * */
	static void ingresarSalaCine(Cliente clienteProceso) {
		
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
					default: System.out.println("Opción invalida");
				}
				
			}while(!casoValido);
			
			if(volverAlMenu) {
				//Rompemos la lógica de ingresar a las salas de cine
				break;
			}
			
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
				System.out.println("\n		Hola " + clienteProceso.getNombre());
				System.out.println("==================================================\n");
				System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
				+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime().withNano(0) + "\n\n"
				+ "Estos son los tickets que actualmente tienes disponibles:" 
				+ clienteProceso.mostrarTicketsParaUsar(ticketsDisponiblesParaUsar) + "\n");
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
				
				if(volverAlMenu) {
					//Rompemos la lógica de caso válido de ingreso a la sala de cine
					break;
				}
				
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
			        SucursalCine.dropHorariosVencidos();
			        SucursalCine.actualizarPeliculasSalasDeCine();
			        
				}else {
					System.out.println("\nNo tienes un ticket válido o no cumple con los requisitos para ingresar a esta sala de cine" 
					+ "\nSerás redireccionado a la elección de salas de cine");

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
	static void ingresarSalaDeEspera(Cliente clienteProceso) {
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
				
				if (opcionMenu == ticketsDisponiblesParaUsar.size() + 1) {
					volverAlMenu = true;
					break;
				}
				
				try {
					ticketParaUsar = ticketsDisponiblesParaUsar.get(opcionMenu - 1);
				}catch(IndexOutOfBoundsException e) {
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
			
			//Actualizamos el estado de la fecha actual, de las películas y las salas de cine 
			SucursalCine.setFechaActual(ticketParaUsar.getHorario());
			SucursalCine.dropHorariosVencidos();
			SucursalCine.actualizarPeliculasSalasDeCine();
			
			//Mostramos en pantalla el resultado del proceso
			System.out.println("\nEsperando...");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
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
	static String disponibilidadHoraria(ArrayList<LocalDateTime> horariosPeliculaProceso) {
		String resultado = null;
		if (horariosPeliculaProceso.size() > 0) {
			resultado = "\n2. Comprar en otro horario\n3. Volver al menú principal";
		}else {
			resultado = "\n2. Volver al menú principal";
		}
		
		return resultado;
	}
	
}

//1. Método de avanzar la hora automáticamente (Investigar el uso de threads e implementarlo) (Hecho, investigar más, (Hecho) y mejorar la lógica durante
// la jornada laboral, investigar una forma de que esta se ejecute solo cuando sea necesaria no cada segundo (imposible), añadir a la verificación de tiempo
// de actualizarSalasDeCine el tiempo de limpieza de sala (Hecho) y hacer testeos) (Hecho)
//1.1. Solucionar error de actualización de horarios a las 10am cuando arranca el código (Solución parcial en el método crearHorarios SucursalCine, revisar lógica) (Hecho)
//1.2. Depurar código en la clase SucursalCine (Revisar utilidad del método peliculaMenorDuracion (Eliminado)) (Hecho)
//1.3. Resolver búcle luego de acceder a los servicios de sistema de proyecciones de película, sistema de reserva y luego regresar (Hecho)
//1.4. Resolver errores de actualización de película de forma innecesaria, revisar si es error en la creación de horarios o en la lógica de la actualización (Hecho)
//1.5. Poner un interruptor para sala de cine en caso de que no tenga más horarios para presentar ese día y ahorrar peticiones en el hilo. (Hecho)

//Próxima tarea:
//1.6. Crear método que se encarga de validar el tiempo que lleva un usuario dentro de un menú en la UI para evitar problemas con el avance de tiempo.
// Alternativa: Antes de usar un ticket para usar en sala de espera y sala de cine verificar si no ha caducado, posiblemente hacer esto con los 
// horarios de las películas y con las salas de cine que presentan una película para en caso de encontrar una inconsistencia respecto a esto, volver a
// evaluar la posibilidad de uso de este servicio y reiniciar su lógica.

//2. Hacer Tests de la lógica de la funcionalidad 1
//3. Incluir el código de la clase Funcionaliadad 1 en la clase Administrador y Estudiar serialización
//4. Serializar
//5. Hacer test de serialización
//6. Empezar el Google Document con el manual de usuario y la documentación



