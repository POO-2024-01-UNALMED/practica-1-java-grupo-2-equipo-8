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
	 * Description: Este método se encarga de realizar el proceso de reserva de ticket de la funcionalidad 1.
	 * Para llevar su cometido, se muestran las películas en cartelera de la franquicia a la que accedió previamente, 
	 * el cliente selecciona una de estas, luego se busca si la película seleccionada se encuentra en presentación en alguna de las salas de cine 
	 * de la sucursal y no lleve más de 15 minutos en presentación, en caso de que sí, se le pregunta al cliente si quiere comprar 
	 * la película en ese horario, dada una respuesta positiva, con la sala de cine
	 * previamente encontrada realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine, 
	 * le pedimos al cliente que seleccione el asiento, se valida su disponibilidad, se realiza el proceso de pago y se asigna el ticket al cliente), 
	 * dada una respuesta negativa, mostramos los horarios de la película, el usuario selecciona uno de ellos y 
	 * realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine virtual asociada al horario previamente seleccionado, 
	 * el cliente selecciona el asiento deseado, se valida su disponibilidad, se realiza el proceso de pago y se asigna el ticket al cliente)
	 * @param clienteProceso : Este método recibe como parámetro el cliente que efectuó el proceso de login
	 * @param sucursalCineProceso: Este método recibe como parámetro el cine que se seleccionó luego del proceso de login y con este
	 * se hacen las busquedas para mostrar en pantalla las películas en cartelera y las salas de cine disponibles
	 * */
	static void reservarTicket(Cliente clienteProceso) {
		System.out.println("\nSistema de Reserva de ticket para película");
		
		//Elección menu inicial
		boolean casoValido = false;
		int opcionMenu = 0;
		do {
			
			try {
				System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú principal" + "\n3. Salir");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			
			switch (opcionMenu) {
				case 1: casoValido = true; break;
				case 2: Administrador.inicio(clienteProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		//Funcionalidad reserva de ticket
		
		//Mostramos una cartelera personalizada de acuerdo a la edad del cliente, si la película tiene horarios disponibles o se encuentra en presentación 
		// Y las recomendaciones en caso de que el cliente tenga membresía(futuramente la membresía)
		ArrayList<Pelicula>carteleraPersonalizadaProceso = Pelicula.filtrarCarteleraPorCliente(clienteProceso, clienteProceso.getCineActual());
		
		if (carteleraPersonalizadaProceso.size() == 0) {
			System.out.println("No hay películas disponibles para reservar (Redireccionando al menú principal...)");
			Administrador.inicio(clienteProceso);
		}
		ArrayList<String> nombresPeliculasCarteleraPersonalizadaProceso = Pelicula.filtrarNombrePeliculas(carteleraPersonalizadaProceso);

		ArrayList<String> peliculasRecomendadas = Pelicula.filtrarPorGenero(carteleraPersonalizadaProceso, clienteProceso.generoMasVisto());
		
		//Seleccionamos una película
		casoValido = false;
		boolean casoValidoConfirmacion = false;
		Pelicula peliculaProceso = null;
		do {
			do {
				//Mostramos los nombres de las películas en cartelera y le pedimos al usuario elegir una de estas
				do {
					opcionMenu = 0;
					try {
						System.out.println("\nHola " + clienteProceso.getNombre() + ", bienvenido al sistema de reserva de ticket\n"
						+ "================================================================\n"
						+ "Este es el listado de los nombres de las películas en cartelera,\n"
						+ "elige una de las siguientes opciones para ver más información:" 
						+ Pelicula.showNombrePeliculas(nombresPeliculasCarteleraPersonalizadaProceso, clienteProceso, peliculasRecomendadas) + "\n"
						+ ( Integer.valueOf(nombresPeliculasCarteleraPersonalizadaProceso.size()) + 1 ) + ". Salir al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
				}while(!(opcionMenu > 0 && opcionMenu <= nombresPeliculasCarteleraPersonalizadaProceso.size() + 1));
				
				if (opcionMenu == Integer.valueOf(nombresPeliculasCarteleraPersonalizadaProceso.size()) + 1) {
					Administrador.inicio(clienteProceso);
				}
				
				//Obtenemos el nombre de la película seleccionada por el cliente
				String nombrePelicula = nombresPeliculasCarteleraPersonalizadaProceso.get(opcionMenu - 1);
				
				//Buscamos las películas que coinciden con el nombre seleccionado con el cliente
				ArrayList<Pelicula> peliculasProceso = new ArrayList<>();
				peliculasProceso = Pelicula.filtrarPorNombreDePelicula(nombrePelicula, clienteProceso.getCineActual());
				
				//Mostramos información del nombre de la película seleccionada
				System.out.println("\nInformación película seleccionada -> \nNombre: " + peliculasProceso.get(0).getNombre() 
				+ "; Género: " + peliculasProceso.get(0).getGenero()
				+ ", Duración: " + peliculasProceso.get(0).getDuracion().toMinutes() + " Minutos.\n" 
				+"\n========================================================================================");
				
				//Mostramos en pantalla los formatos del nombre de la película seleccionada
				do {
					opcionMenu = 0;
					
					try {
						System.out.println("\nEste es el listado de los formatos de la película, elige una de las siguientes opciones:\n" 
						+ Pelicula.showTiposFormatoPeliculaSeleccionada(peliculasProceso)+ "\n"
						+ ( Integer.valueOf(peliculasProceso.size()) + 1 ) + ". Seleccionar otra película");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
					
				}while( !( opcionMenu > 0 && opcionMenu <= Integer.valueOf(peliculasProceso.size()) + 1 ) );
				
				if (opcionMenu == Integer.valueOf(peliculasProceso.size()) + 1) {
					continue;
				}
				
				//Seleccionamos la película con el formato seleccionado por el cliente
				peliculaProceso = peliculasProceso.get(opcionMenu - 1);
				
				//Confirmamos la elección del cliente
				do {
					opcionMenu = 0;
					try {
						System.out.println("Has elegido la película " + peliculaProceso.getNombre() 
						+ " en formato " + peliculaProceso.getTipoDeFormato()
						+ "\n1. Correcto \n2. Cambiar Pelicula");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
				}while(!(opcionMenu == 1 | opcionMenu == 2));
				
				switch(opcionMenu) {
					case 1: casoValido = true; casoValidoConfirmacion = true; break;
					case 2: casoValido = false; casoValidoConfirmacion = true; break;
					default : System.out.println("Opción Invalida"); casoValidoConfirmacion = false; 
				}
				
			}while (!casoValidoConfirmacion);
		}while (!casoValido);
		
		
		//Creamos el espacio en memoria para almacenar la información dada por el cliente luego de solicitarla
		SalaCine salaDeCineProceso = null;
		String numeroAsientoProceso = null;
		LocalDateTime horarioProceso = null;
		
		//Filtramos los primeros 7 horarios con asientos disponibles desde la fecha actual
		ArrayList<LocalDateTime> horariosPeliculaProceso = new ArrayList<>();
		horariosPeliculaProceso = peliculaProceso.filtrarHorariosPelicula();
		boolean disponibilidadHoraria = horariosPeliculaProceso.size()>0;
		
		//Mostramos este menú en caso de que la película se encuentre en presentación en alguna sala de cine y 
		//además la película no lleva más de 15 minutos en presentación
		if (peliculaProceso.IsPeliculaEnPresentacion(clienteProceso.getCineActual())) {
			salaDeCineProceso = peliculaProceso.whereIsPeliculaEnPresentacion(clienteProceso.getCineActual());
			casoValido = false;
			casoValidoConfirmacion = false;
			
			//Preguntamos si desea ver la película a la hora de esta presentación o en un horario diferente en caso de tener más horarios disponibles
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nHemos detectado que la película seleccionada se encuentra en presentación. \ninicio de proyección: " + 
					salaDeCineProceso.getHorarioPeliculaEnPresentacion() + "\n¿Desea reservar un ticket para este horario? " +
					" (Hora actual: " + SucursalCine.getFechaActual().withNano(0) + ")\n1. Comprar en este horario" + 
					Funcionalidad1.disponibilidadHoraria(horariosPeliculaProceso));
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Error, debes ingresar un único dato númerico entre los disponibles");
				}
				
				//En caso de que la película tenga horarios disponibles, significa que se usó un menú de 3 opciones en caso de que no, uno de 2 opciones
				if (disponibilidadHoraria) {
					
					switch(opcionMenu) {
						case 1: casoValidoConfirmacion = true;
							//Compra película en este horario
							
							//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
							numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso);
							
							//Obtenemos el horario de la película seleccionada
							horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
							break;
								
					case 2: casoValidoConfirmacion = true; 							
							//Compra película en otro horario
							
							//El cliente elige el horario de la película seleccionada 
							horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, horariosPeliculaProceso);
							
							//El cliente elige el asiento de la película seleccionada
							numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso);
							
							break;
					case 3: casoValidoConfirmacion = true; Administrador.inicio(clienteProceso); break;
					default: casoValidoConfirmacion = false; System.out.println("Digite un número válido");
					}
				}else {
					switch(opcionMenu) {
						case 1: casoValidoConfirmacion = true; 
								//Compra película en este horario
								
								//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
								numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso);
								
								//Obtenemos el horario de la película seleccionada
								horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
								
								break;
								
						case 2: casoValidoConfirmacion = true; Administrador.inicio(clienteProceso); break;
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
		
		//Se genera el último mensaje con posibilidad de regresar al menú principal
		do {
			opcionMenu = 0;
			try {
				System.out.println("\nVamos a empezar con el proceso de pago\n1. Continuar\n2. Volver al menú principal");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
			}
		}while(!(opcionMenu == 1 || opcionMenu == 2));
		
		//Creamos el apuntador del ticket
		Ticket ticketProceso = null;
		
		if (opcionMenu == 1) {
			
			//Creamos el ticket con su respectivo precio e informamos al cliente en caso de recibir un descuento
			ticketProceso = new Ticket(peliculaProceso, horarioProceso, numeroAsientoProceso, clienteProceso.getCineActual());

			if(!(ticketProceso.getPrecio() == peliculaProceso.getPrecio())) {
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
			Administrador.inicio(clienteProceso);
		}
		
		//Iniciamos el proceso de pago
		System.out.println("\n		Proceso de pago");
		System.out.println("=====================================================");
		
		//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
		if (!peliculaProceso.getHorarios().contains(horarioProceso)) {
			pagarTicket(clienteProceso, salaDeCineProceso, numeroAsientoProceso, ticketProceso);
		}else {
			pagarTicket(clienteProceso, peliculaProceso, horarioProceso, numeroAsientoProceso, ticketProceso);
		}
		
	}
	
	/**
	 * Description : Este método se encarga de efectuar el proceso de pago del ticket durante la funcionalidad de reservarTicket,
	 * en caso de que el cliente compre el ticket de una película que se encuentra en presentación en estos momentos, 
	 * para esto, recibe los parámetros necesarios para realizar el pago y procesarlo.
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente),
	 * que realizará el pago y luego de verificarlo, se le asociará el ticket comprado.
	 * @param salaDeCine : Este método recibe como parámetro la sala de cine (De tipo SalaCine),
	 * con el fin de modificar la disponibilidad del asiento seleccionado por el cliente.
	 * @param numeroAsientoProceso : Este método recibe como parámetro un String, que corresponde al numero del asiento seleccionado, 
	 * con la idea de cambiar la disponibilidad del asiento en esa posición.
	 * @param ticketProceso : Este método recibe como parámetro un ticket (De tipo Ticket), que corresponde al ticket generado durante el proceso de la
	 * funcionalidad reservarTicket, del cuál se obtendrá el valor a pagar, se realizará el proceso de pago y una vez verificado, se le asociará al cliente   
	 * */
	static void pagarTicket(Cliente clienteProceso, SalaCine salaDeCineProceso, String numeroAsientoProceso, Ticket ticketProceso) {
		
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu = 0;
		
		
		MetodoPago metodoPagoProceso = null;
		double precioTicketProceso = ticketProceso.getPrecio();
		
		//Selccionar el método de pago para realizar el pago y realizar el pago
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nEl valor a pagar por el ticket es: " + precioTicketProceso
					+ "\nEste es el listado de los métodos de pago disponibles:\n" 
					+ MetodoPago.mostrarMetodosDePago(clienteProceso));
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un único dato númerico entre los disponibles");
				}
			}while(!(opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size() ) );
			
			//Se selecciona el método de pago
			metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
			
			boolean primerSetPrecioTicket = true;
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
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
			
			//Setteamos el precio del ticket luego de aplicar el primer descuento
			if (primerSetPrecioTicket) {
				ticketProceso.setPrecio(precioTicketProceso);
				primerSetPrecioTicket = false;
			}
			
			//Ponemos un delay en pantalla
			System.out.println("\nEstamos procesando su pago, por favor espere...\n");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			//Verificamos si el pago fue cubierto en su totalidad
			if (metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso) == 0) {
				
				System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
				
				//Realizamos el proceso correspondiente luego de ser verificado
				ticketProceso.procesarPagoRealizado(clienteProceso);
				salaDeCineProceso.cambiarDisponibilidadAsientoLibre(numeroAsientoProceso);
				
				System.out.println( clienteProceso.getFacturas().get(clienteProceso.getFacturas().size() - 1) );
				casoValido = true;
				
			}else {
				
				//Repetimos el proceso hasta validar el pago
				precioTicketProceso = metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso);
				casoValido = false;
				
				System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
				
			}
			
		}while(!casoValido);
		
		System.out.println("\nFin del proceso reserva de ticket");
		System.out.println("(Redireccionando al menu principal...)");
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Description : Este método se encarga de efectuar el proceso de pago del ticket creado por funcionalidad reservarTicket,
	 * dado el caso de que el cliente compre el ticket en un horario determinado, ya sea porque seleccionó comprar en otro horario o su película no se
	 * encontraba en presentación en ese momento, para esto, recibe los parámetros necesarios para realizar el pago y procesarlo
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente) 
	 * que realizará el pago y luego de ser verificado, se le asociará el ticket comprado con el método procesarPago.
	 * @param peliculaProceso : Este método recibe como parámetro una película (De tipo película),
	 * con el fin de cambiar la disponibilidad del asiento comprado por el cliente luego de verificar el pago.
	 * @param horarioProceso : Este método recibe como parámetro el horario de la película (De tipo LocalDateTime), 
	 * con el propósito de cambiar la disponibilidad del asiento seleccionado por el cliente en la sala virtual correspondiente a este horario
	 * @param numeroAsientoProceso : Este método recibe como parámetro un String, que corresponde al numero del asiento seleccionado, 
	 * con la idea de obtener la fila y la columna seleccionada por el cliente para cambiar la disponibilidad del asiento en esa posición.
	 * @param ticketProceso : Este método recibe como parámetro un ticket (De tipo Ticket), que corresponde al ticket generado durante el proceso de
	 * reserva de ticket, del cuál se obtendrá el valor a pagar, se realizará el proceso de pago y una vez verificado, se le asociará al cliente   
	 * @param sucursalCineProceso : Este método recibe como parámetro la sede (De tipo SucursalCine), que corresponde al lugar desde donde el cliente
	 * esta realizando la compra, con el fin de asignarle a este ticket la sala de cine correspondiente a su película
	 * */
	static void pagarTicket(Cliente clienteProceso, Pelicula peliculaProceso, LocalDateTime horarioProceso, String numeroAsientoProceso, Ticket ticketProceso) {
		
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu = 0;
		
		MetodoPago metodoPagoProceso = null;
		double precioTicketProceso = ticketProceso.getPrecio();
		
		//Selccionar el método de pago para realizar el pago y realizar el pago
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nEl valor a pagar por el ticket es: " + precioTicketProceso
					+ "\nEste es el listado de los métodos de pago disponibles:\n" 
					+ MetodoPago.mostrarMetodosDePago(clienteProceso));
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un único dato númerico entre los disponibles");
				}
			}while(!(opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size() ) );
			
		
			metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
			
			boolean primerSetPrecioTicket = true;
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
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
			
			if (primerSetPrecioTicket) {
				ticketProceso.setPrecio(precioTicketProceso);
				primerSetPrecioTicket= false;
			}
			
			System.out.println("\nEstamos procesando su pago, por favor espere...\n");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			//Verificamos si el pago fue cubierto en su totalidad
			if (metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso) == 0) {
				
				System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
				
				//Realizamos el proceso correspondiente a realizar el pago
				ticketProceso.procesarPagoRealizado(clienteProceso);
				
				//Generamos la fila y la columna a partir del número de asiento seleccionado para modificar su disponibilidad
				int filaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(0));
				int columnaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(2));
				peliculaProceso.modificarSalaVirtual(horarioProceso, filaProceso, columnaProceso);
				
				System.out.println( clienteProceso.getFacturas().get( clienteProceso.getFacturas().size() - 1 ) );
				casoValido = true;
				
			}else {
				
				precioTicketProceso = metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso);
				casoValido = false;
				
				System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
				
			}
			
		}while(!casoValido);
		
		System.out.println("\nFin del proceso reserva de ticket");
		System.out.println("(Redireccionando al menu principal...)");
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Description : Este método se encarga de seleccionar un horario a partir de la pelicula seleccionada por el cliente, para realizar este proceso,
	 * Se muestra en pantalla los de horarios de la película seleccionada, para que el cliente pueda seleccionar uno de estos
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente), con el fin de que, 
	 * dado el caso, el cliente quiera regresar al menú principal, pueda hacerlo.
	 * @param peliculaProceso : Este método recibe como parámetro una película (De tipo película) obtenido durante el proceso de la reserva de ticket,
	 * para que de allí podamos mostrar en pantalla las llaves de su diccionario de horarios.
	 * @param horariosPeliculaProceso : Este método recibe como parámetro los horarios disponibles de la película (De tipo ArrayList<LocalDateTime>)
	 * obtenido durante el proceso de la reserva de ticket
	 * */
	static LocalDateTime seleccionarHorarioPelicula(Cliente clienteProceso, Pelicula peliculaProceso, ArrayList<LocalDateTime> horariosPeliculaProceso) {
		
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		LocalDateTime horarioProceso = null;
		
		System.out.println("\n		Selección de horario");
		System.out.println("=====================================================");
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nLos horarios de la película " + peliculaProceso.getNombre() 
					+ " son:\n" + peliculaProceso.mostrarHorarioPelicula(horariosPeliculaProceso) 
					+ (Integer.valueOf(horariosPeliculaProceso.size()) + 1) + ". Volver al menú principal");
					opcionMenu = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico");
					continue;
				}
				
			}while(!(opcionMenu > 0 && opcionMenu <= Integer.valueOf(horariosPeliculaProceso.size()) + 1));
			
			if(opcionMenu == Integer.valueOf(horariosPeliculaProceso.size()) + 1) {
				Administrador.inicio(clienteProceso);
			}else {
				horarioProceso = horariosPeliculaProceso.get(opcionMenu - 1);
			}
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("Elegiste la película el día: " + horarioProceso.getDayOfWeek() +  " fecha: "
					+ horarioProceso.toLocalDate() + ", A las: " + horarioProceso.toLocalTime() + "\n1. Correcto \n2. Cambiar horario");
					opcionMenu = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico");
					continue;
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
	 * Description: Este método se encarga de seleccionar el número de asiento del cliente para ver una película en el horario previamente seleccionado, 
	 * para hacer esto, Se muestra en pantalla los asientos de la sala de cine virtual con su respectiva disponibilidad del horario
	 * de la película seleccionado por el cliente, tras esto, el cliente elige la fila,
	 * luego la columna, se valida si el asiento en cuestión se encuentra disponible y una vez cumplida la verificación,
	 * retornamos el número del asiento seleccionado.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) seleccionado en el proceso de login
	 * @param horarioProceso : Este método recibe como parámetro el horario (De tipo LocalDateTime) seleccionado durante el proceso de reserva de ticket
	 * @param horarioProceso : Este método recibe como parámetro la película (De tipo Pelicula) seleccionada durante el proceso de reserva de ticket
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente
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
					System.out.println("Digite la fila de su asiento deseado");
					filaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles");
					continue;
				}
				
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso)).length))){
					System.out.println("La fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
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
					System.out.println("\nDigite la columna de su asiento deseado");
					columnaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
				
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(peliculaProceso.getAsientosVirtuales().get(peliculaProceso.getHorarios().indexOf(horarioProceso)).length))){
					System.out.println("La columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
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
	 * presentación, para hacer esto, Se muestra en pantalla los asientos de la sala de cine con su respectiva disponibilidad, el cliente elige la fila
	 * luego la columna, se valida si el asiento en cuestión se encuentra disponible y una vez cumplida la verificación,
	 * retornamos el número del asiento seleccionado
	 * @param salaDeCinePresentacionProceso : Este método recibe como parámetro la sala de cine en la cuál se encuentra la película seleccionada durante
	 * el proceso de la reserva de ticket
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente
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
			System.out.println("\nEsta es la distribución de asientos con su disponibilidad actual de la película en el horario seleccionado" 
				    + "\n X : Ocupado\n O : Disponible\n" + salaDeCinePresentacionProceso.mostrarAsientos());
					
			//Elegimos la fila del asiento
			do {
				try {
					System.out.println("Digite la fila de su asiento deseado");
					filaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
						System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las filas disponibles");
						continue;
				}
						
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos().length))){
					System.out.println("La fila seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
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
					System.out.println("\nDigite la columna de su asiento deseado");
					columnaProceso = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un dato numérico correspondiente a alguna de las columnas disponibles");
					continue;
				}
						
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(salaDeCinePresentacionProceso.getAsientos().length))){
					System.out.println("La columna seleccionada no se encuentra disponible, le sugerimos que eliga una entre las disponibles");
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
			
		}while(!(casoValido));
		
		return numeroAsientoProceso;
		
	}
	
	/**
	 * Description : Este método se encarga de gestionar el ingreso a la sala de cine desde el menú, mostrando un mensaje donde
	 * se pregunta al usuario si desea ingresar y luego ejecuta el método ingresarSalaCine()
	 * @param clienteProceso : Este método recibe como parámetro el cliente (Clase cliente) que realizó el proceso de login
	 * */
	static void ingresarSalaCineDesdeMenu(Cliente clienteProceso) {
		
		System.out.println("Sistema de ingreso a la sala de cine\n");
		
		//Elección menú inicial
		boolean casoValido = false;
		int opcionMenu = 0;
		do {
			try {
				System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú principal" + "\n3. Salir");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			
			switch (opcionMenu) {
				case 1: casoValido = true; break;
				case 2: Administrador.inicio(clienteProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		if (opcionMenu == 1) {ingresarSalaCine(clienteProceso);}
		
	}
	
	/**
	 * Description: Este método se encarga de realizar el proceso para que un usario pueda ingresar a una de las salas de cine,
	 * para esto debemos cumplir un requisito fundamental, el cual es que el cliente tenga al menos un ticket asociado correspondeiente a la
	 * sucursal desde la cual está intentando acceder, dado el caso de que no, el cliente será redirigido al menú principal,
	 * en caso de que sí podemos continuar con el proceso.
	 * Mostramos en pantalla las salas de cine disponibles con información relevante de estas (Número de sala, película en presentación y horario),
	 * el cliente selecciona una de estas, verificamos que alguno de los tickets del cliente, cumpla con los requisitos necesarios para 
	 * poder ingresar a la sala de cine, en caso de que sí, se muestra en pantalla una representación de la sala de cine y una vez termina 
	 * la película se redirecciona al cliente al menú principal, en caso de que no, se vuelve a mostrar por pantalla las salas de cine disponibles.
	 * @param clienteProceso : Este método recibe como parámetro al cliente (De tipo Cliente), que ingresó desde el menú
	 * principal o compró un ticket de una película en presentación y desea verla directamente 
	 * */
	static void ingresarSalaCine(Cliente clienteProceso) {
		
		SalaCine salaDeCineProceso = null;
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		//Tomamos las salas de cine que aún tienen películas en presentación y no han finalizado
		ArrayList<SalaCine> salasDeCineDisponibles = SalaCine.filtrarSalasDeCine(clienteProceso.getCineActual());
		
		//En caso de no encontrar salas de cine, regresamos al menú principal
		if (salasDeCineDisponibles.isEmpty()) {
			System.out.println("\nNo hay películas en presentación, redireccionando al menú principal...");
			Administrador.inicio(clienteProceso);
		}
		
		//Eliminamos los tickets caducados sin consumir que el cliente tenga asociados 
		clienteProceso.dropTicketsCaducados();
		
		//Verificamos que el cliente tenga al menos un ticket asociado para continuar con el proceso
		if(clienteProceso.getTickets().size() > 0 && clienteProceso.disponibilidadTicketParaSede(clienteProceso.getCineActual())) {
			System.out.println("\n		Hola " + clienteProceso.getNombre());
			System.out.println("==================================================\n");
			System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
			+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime() + "\n\n"
			+ "Estos son los tickets que actualmente tienes disponibles: \n" 
			+ clienteProceso.mostrarTicketsParaUsar() + "\n");
		}else {
			System.out.println("No has comprado ningún ticket o no tienes un ticket de una película de esta sede, te redireccionaremos al menú principal");
			Administrador.inicio(clienteProceso);
		}
		//Iniciamos el proceso para ingresar a una sala de cine
		do {
			do {
				do {
					
					opcionMenu = 0;
					try {
						
						//Mostramos el listado de las salas de cine disponibles para que el cliente elija una de estas
						System.out.println("Este es el listado de las salas de cine disponibles: \n" 
						+ SalaCine.mostrarSalaCine(salasDeCineDisponibles) + "\n" + (Integer.valueOf(salasDeCineDisponibles.size()) + 1) 
						+ ". Salir al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
						
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
					
				}while(!(opcionMenu > 0 & opcionMenu <= (Integer.valueOf(salasDeCineDisponibles.size()) + 1) ) );
				
				if (opcionMenu == (Integer.valueOf(salasDeCineDisponibles.size()) + 1)) {
					Administrador.inicio(clienteProceso);
				}else {
					//Obtenemos la sala de cine seleccionada
					salaDeCineProceso = salasDeCineDisponibles.get(opcionMenu - 1);
				}
				
				//Confirmamos la seleccion de sala
				do {
					opcionMenu = 0;
					try {
						System.out.println("Has seleccionado la sala de cine número: " + salaDeCineProceso.getNumeroSala() 
						+ "\n1. Correcto\n2. Cambiar sala de cine");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				casoValidoConfirmacion = (opcionMenu == 1) ? true : false;
				
			}while(!casoValidoConfirmacion);
			
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
		        "\n(Redirigiendo al menú principal...)");
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
				casoValido = false;
			}
			
		}while(!casoValido);
				
	}
	
	/**
	 * Description : Este método se encarga de avanzar el tiempo del programa según el horario de la película del ticket del cliente, 
	 * para que este pueda verla. Para hacer esto, mostramos en pantalla los tickets disponibles del cliente, el cliente selecciona uno de ellos,
	 * setteamos la hora actual con la hora de la película y actualizamos las salas de cine (Un cliente sin tickets no puede ingresar a esta sala,
	 * además antes de verficar eso, eliminamos los tickets que ya han caducado).
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) que realizó el proceso de login
	 * */
	static void salaDeEspera(Cliente clienteProceso) {
		System.out.println("\nIngreso a sala de espera");
		
		//Elección menu inicial
		boolean casoValido = false;
		int opcionMenu = 0;
		do {
			try {
				System.out.println("¿Desea ingresar o volver?" +"\n1. Ingresar" + "\n2. Volver al menú principal" + "\n3. Salir");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			
			switch (opcionMenu) {
				case 1: casoValido = true; break;
				case 2: Administrador.inicio(clienteProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		//Validamos si el cliente tiene tickets disponibles
		clienteProceso.dropTicketsCaducados();
		if (!(clienteProceso.getTickets().size() > 0 && clienteProceso.disponibilidadTicketParaSede(clienteProceso.getCineActual()))) {
			System.out.println("Debes tener al menos un ticket de alguna película de esta sede para hacer uso de esta sala (Redireccionando al menú principal...)");
			Administrador.inicio(clienteProceso);
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
			try {
				System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
				+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime().withNano(0) + "\n\n"
				+ "Estos son los tickets que actualmente tienes disponibles: \n" 
				+ clienteProceso.mostrarTicketsParaUsar() );
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
				continue;
			}
			
			try {
				ticketParaUsar = clienteProceso.getTickets().get(opcionMenu - 1);
			}catch(NullPointerException e) {
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
			}while(!(opcionMenu == 1 || opcionMenu == 2 ));
			
			switch(opcionMenu) {
				case 1: casoValido = true; break;
				case 2: casoValido = false; break;
				default : casoValido = false; System.out.println("ha ocurrido un error, Elige el ticket de nuevo");
			}
		}while(!casoValido);
		
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

//0. Cambiar el map de Horarios por un Array (Obligatorio) (Hecho, hacer tests)
//0.1. Revisar error que no permite ejecutar actualizar horarios sin dropear los horarios vencidos, debe resolverse sin dropear (Hecho)
//0.2. La película puede tener directamente la sala de cine de su sucursal en la que puede ser presentada, esto permitiría automatizar la
// distribución de forma equitativa de películas por sala de cine (Hecho)
//0.3. Posible problema con el serializador y asignar al cliente antes de realizar un pago (Hecho)

//0.4. Automatizar la creación de películas a partir de crear una película 2D, crear estas mismas pero con 3D y 4D según su género (Hecho)
//0.5. Mejorar vista en consola de las salas de cine y sala de espera (Hecho)
//0.6. Solucionar error en vista de salas de cine de películas que ya finalizaron su presentación (Ver el filtro) (Hecho)
//0.7. Limitar la creación de horarios en una semana (Hecho)
//0.8. Usar el setter de cliente de cineActual, crear opción menú para cambiar de sucursal (Hecho)
//0.9. Depurar el código de la clase Funcionalidad 1 (Hecho)

//0. método de avanzar la hora automáticamente (Investigar el uso de threads y Hablar con David)
//1. Mejorar abstracción de métodos, revisar todo el código hecho y mejorar documentación
//2. Incluir el código de la clase Funcionaliadad 1 en la clase Administrador y Estudiar serialización
//3. Hacer Tests
//4. Serializar
//5. Empezar el Google Document con el manual de usuario y la documentación



