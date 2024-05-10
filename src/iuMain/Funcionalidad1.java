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
	 * Para llevar su cometido, Se solicita al cliente que hizo el inicio de sesión y tras esto se muestran las películas
	 * en cartelera de la franquicia a la que accedió previamente, el cliente selecciona una de estas, luego se busca si la película
	 * seleccionada se encuentra en presentación en alguna de las salas de cine del cine (clase Cine) y no lleve más de 15 minutos en presentación,
	 * en caso de que sí, se le pregunta al cliente si quiere comprar la película en ese horario, dada una respuesta positiva, con la sala de cine
	 * previamente encontrada realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine, 
	 * le pedimos al cliente que seleccione el asiento, se valida su disponibilidad y efectuamos el pago de forma exitosa), 
	 * dada una respuesta negativa, mostramos los horarios de la película, el usuario selecciona uno de ellos y 
	 * realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de cine virtual asociada al horario previamente seleccionado, 
	 * el cliente selecciona el asiento deseado, se valida su disponibilidad y efectuamos el pago de forma exitosa)
	 * @param clienteProceso : Este método recibe como parámetro el cliente que efectuó el proceso de login
	 * @param cineProceso: Este método recibe como parámetro el cine que se seleccionó luego del proceso de login y con este
	 * se hacen las busquedas para mostrar en pantalla las películas en cartelera y las salas de cine disponibles
	 * */
	static void reservarTicket(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		System.out.println("\nBienvenido al Sistema de Reserva de ticket para película");
		
		//Elección menu inicial
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
				case 2: Administrador.inicio(clienteProceso, sucursalCineProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		//Funcionalidad reserva de ticket
		
		//Aquí se puede añadir el caso en el que el cliente tenga membresía para mostrar la cartelera personalizada en caso de no poder evitar repetidos cambiar por LinkedHashSet
		//Mostramos una cartelera personalizada de acuerdo a la edad del cliente, si la película tiene horarios disponibles o se encuentra en presentación y (futuramente la membresía)
		ArrayList<Pelicula> carteleraPersonalizadaProceso = new ArrayList<>();
		carteleraPersonalizadaProceso = Pelicula.filtrarCarteleraPorCliente(clienteProceso, sucursalCineProceso);
		
		//Seleccionamos una película
		casoValido = false;
		boolean casoValidoConfirmacion = false;
		Pelicula peliculaProceso = null;
		do {
			System.out.println("\nHola " + clienteProceso.getNombre() + ", Bienvenido al sistema de reserva de ticket");
			System.out.println("=====================================================================================");
			//Mostramos las películas en cartelera y le pedimos al usuario elegir una de estas
			do {
				do {
					opcionMenu = 0;
					try {
						System.out.println("Este es el listado de las películas en cartelera, elige una de las siguientes opciones\n" 
						+ Pelicula.mostrarCartelera(carteleraPersonalizadaProceso) + ( Integer.valueOf(carteleraPersonalizadaProceso.size()) + 1 ) + ". Salir al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
				}while(!(opcionMenu > 0 && opcionMenu <= carteleraPersonalizadaProceso.size() + 1));
				
				if (opcionMenu == Integer.valueOf(carteleraPersonalizadaProceso.size()) + 1) {
					Administrador.inicio(clienteProceso, sucursalCineProceso);
				}
				
				peliculaProceso = carteleraPersonalizadaProceso.get(opcionMenu - 1);
				
				//Validamos si el cliente puede ver la película seleccionada
				if (Integer.valueOf(peliculaProceso.getClasificacion()) > clienteProceso.getEdad()) {
					System.out.println("Error, no tienes la edad suficiente, para ver esta película" 
				    + ", elige otra película a la que si tengas acceso");
					//continue SelecPeli;
				}
				
				do {
					opcionMenu = 0;
					try {
						System.out.println("Has elegido la película " + peliculaProceso.getNombre() + "\n1.Correcto \n2.Cambiar Pelicula");
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
		
		//Mostramos este menú en caso de que la película se encuentre en presentación en alguna sala de cine y 
		//además la película no lleva más de 15 minutos en presentación
		if (peliculaProceso.IsPeliculaEnPresentacion(sucursalCineProceso)) {
			salaDeCineProceso = peliculaProceso.whereIsPeliculaEnPresentacion(sucursalCineProceso);
			casoValido = false;
			casoValidoConfirmacion = false;
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("Hemos detectado que la película seleccionada se encuentra en presentación. \ninicio de proyección: " + 
					salaDeCineProceso.getHorarioPeliculaEnPresentacion() + "\n¿Desea reservar un ticket para este horario? " +
					" (Hora actual: " + SucursalCine.getFechaActual() + ")\n1. Comprar en este horario\n2. Comprar en otro horario");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Error, debes ingresar un único dato númerico entre los disponibles");
				}
			}while(!(opcionMenu == 1 || opcionMenu == 2));
			
			if (opcionMenu == 1) {
				//Compra película en este horario
				
				//El cliente elige el asiento de la sala de cine que tiene la película seleccionada en presentación
				numeroAsientoProceso = seleccionarAsiento(salaDeCineProceso, sucursalCineProceso);
				
				//Obtenemos el horario de la película seleccionada
				horarioProceso = salaDeCineProceso.getHorarioPeliculaEnPresentacion();
			}else {
				//En caso de que la película se encuentre en presentación y ya supere los 15 mins
				if(peliculaProceso.getHorarios().size() > 0) {
					//Compra película en otro horario
					
					//El cliente elige el horario de la película seleccionada 
					horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, sucursalCineProceso);
					
					//El cliente elige el asiento de la película seleccionada
					numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso, sucursalCineProceso);
				}else {
					System.out.println("La película seleccionada se encuentra únicamente en presentación" + 
					", es decir, no tiene otros horarios disponibles.\n(Serás redireccionado al menú principal...)");
					Administrador.inicio(clienteProceso, sucursalCineProceso);
				}
			}
			}else {
				if(peliculaProceso.getHorarios().size() > 0) {
					//Compra película en otro horario
					
					//El cliente elige el horario de la película seleccionada 
					horarioProceso = seleccionarHorarioPelicula(clienteProceso, peliculaProceso, sucursalCineProceso);
					
					//El cliente elige el asiento de la película seleccionada
					numeroAsientoProceso = seleccionarAsiento(clienteProceso, horarioProceso, peliculaProceso, sucursalCineProceso);
				}else {
					System.out.println("La película seleccionada se encuentra únicamente en presentación" + 
					", es decir, no tiene otros horarios disponibles.\n(Serás redireccionado al menú inicial de este proceso...)");
					reservarTicket(clienteProceso, sucursalCineProceso);
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
		
		//Creamos el ticket y asignamos su precio evaluando la posibilidad de aplicar un descuento especial
		Ticket ticketProceso = null;
		if (opcionMenu == 1) {
			//Creamos el ticket con su respectivo precio e informamos al cliente en caso de recibir un descuento
			ticketProceso = new Ticket(clienteProceso, peliculaProceso, horarioProceso, numeroAsientoProceso, sucursalCineProceso);
			ticketProceso.asignarPrecio();
			if(!(ticketProceso.getPrecio() == peliculaProceso.getPrecio())) {
				if (peliculaProceso.getTipoDeFormato().equals("3D") || peliculaProceso.getTipoDeFormato().equals("4D") ) {
					System.out.println("\nFelicidades, por ser nuestro cliente número " + Ticket.getCantidadTicketsCreados() 
					+ " has recibido un descuento del 50% por la compra de tu ticket\n"
					+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
				}else {
					System.out.println("\nFelicidades, por ser nuestro cliente número: " + Ticket.getCantidadTicketsCreados() 
					+ " has recibido un descuento del 80% por la compra de tu ticket\n"
					+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
				}
			}
		}else {
			Administrador.inicio(clienteProceso, sucursalCineProceso);
		}
		
		//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
		if (peliculaProceso.getHorarios().get(horarioProceso) == null) {
			pagarTicket(clienteProceso, salaDeCineProceso, numeroAsientoProceso, ticketProceso);
		}else {
			pagarTicket(clienteProceso, peliculaProceso, horarioProceso, numeroAsientoProceso, ticketProceso, sucursalCineProceso);
		}
	}
	
	/**
	 * Description : Este método se encarga de efectuar el proceso de pago del ticket creado por el método reservarTicket durante la funcionalidad 1,
	 * dado el caso de que el cliente compre el ticket de una película que se encuentra en presentación en estos momentos, 
	 * para esto, recibe los parámetros necesarios para realizar el pago y procesarlo.
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente) seleccionado durante el proceso del login, 
	 * que realizará el pago y luego de ser verificado, se le asociará el ticket comprado con el método procesarPago.
	 * @param salaDeCine : Este método recibe como parámetro la sala de cine (De tipo SalaCine) seleccionada durante el proceso de la funcionalidad 1,
	 * con el fin de modificar la disponibilidad del asiento seleccionado por el cliente
	 * @param numeroAsientoProceso : Este método recibe como parámetro un String, que corresponde al numero del asiento seleccionado por el cliente durante el
	 * proceso de la funcionalidad 1, con la idea de cambiar la disponibilidad del asiento
	 * en esa posición.
	 * @param ticketProceso : Este método recibe como parámetro un ticket (De tipo Ticket), que corresponde al ticket generado durante el proceso de la
	 * funcionalidad 1, del cuál se obtendrá el valor a pagar, se realizará el proceso de pago y una vez verificado, se le asociará al cliente   
	 * */
	static void pagarTicket(Cliente clienteProceso, SalaCine salaDeCineProceso, String numeroAsientoProceso, Ticket ticketProceso) {
		
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu = 0;
		
		//Seleccionar Método de pago y realizar pago
		MetodoPago metodoPagoProceso = null;
		casoValido = false;
		casoValidoConfirmacion = false;
		double precioTicketProceso = 0;
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nEl valor a pagar por el ticket es: " + ticketProceso.getPrecio()
					+ "\nEste es el listado de los métodos de pago disponibles:\n" 
					+ MetodoPago.mostrarMetodosDePago(clienteProceso));
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un único dato númerico entre los disponibles");
				}
			}while(!(opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size() ) );
			
		
			metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
			
			boolean primerProcesoDePago = true;
			boolean primerSetPrecioTicket = true;
			do {
				opcionMenu = 0;
				try {
					if (primerProcesoDePago) {
						precioTicketProceso = ticketProceso.getPrecio() * (1 - metodoPagoProceso.getDescuentoAsociado());
						primerProcesoDePago = false;
					}
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
					+ " ( Precio anterior: " + ticketProceso.getPrecio() + " -> Precio actual: " + precioTicketProceso + " )"
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
				primerSetPrecioTicket = false;
			}
			
			System.out.println("\nEstamos procesando su pago, por favor espere...\n");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if (metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso) == 0) {
				System.out.println("Pago realizado, La compra de su ticket fue exitosa");
				ticketProceso.procesarPagoRealizado(clienteProceso);
				ticketProceso.setSalaDeCine(salaDeCineProceso);
				ticketProceso.factura(clienteProceso);
				salaDeCineProceso.cambiarDisponibilidadAsientoLibre(numeroAsientoProceso);
				System.out.println( clienteProceso.getFacturas().get(Integer.valueOf(clienteProceso.getFacturas().size()) - 1) );
				casoValido = true;
			}else {
				precioTicketProceso = metodoPagoProceso.realizarPago(ticketProceso.getPrecio(), clienteProceso);
				System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
				casoValido = false;
				//actualizarPrecio = true; (Se cancela el volver a pagar con descuento)
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
	 * Description : Este método se encarga de efectuar el proceso de pago del ticket creado por el método reservarTicket durante la funcionalidad 1,
	 * dado el caso de que el cliente compre el ticket en un horario determinado, ya sea porque seleccionó comprar en otro horario o su película no se
	 * encontraba en presentación en ese momento, para esto, recibe los parámetros necesarios para realizar el pago y procesarlo
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente) seleccionado durante el proceso del login, 
	 * que realizará el pago y luego de ser verificado, se le asociará el ticket comprado con el método procesarPago.
	 * @param peliculaProceso : Este método recibe como parámetro la película (De tipo película) seleccionada durante el proceso de la funcionalidad 1,
	 * con el fin de cambiar la disponibilidad del asiento comprado por el cliente luego de verificar el pago en el horario seleccionado por el cliente
	 * @param horarioProceso : Este método recibe como parámetro el horario de la película (De tipo LocalDateTime) seleccionado durante el proceso de la
	 * funcionalidad 1, con el propósito de cambiar la disponibilidad del asiento seleccionado por el cliente, de la película seleccionada por este, en
	 * este horario
	 * @param numeroAsientoProceso : Este método recibe como parámetro un String, que corresponde al numero del asiento seleccionado por el cliente durante el
	 * proceso de la funcionalidad 1, con la idea de obtener la fila y la columna seleccionada por el cliente para cambiar la disponibilidad del asiento
	 * en esa posición.
	 * @param ticketProceso : Este método recibe como parámetro un ticket (De tipo Ticket), que corresponde al ticket generado durante el proceso de la
	 * funcionalidad 1, del cuál se obtendrá el valor a pagar, se realizará el proceso de pago y una vez verificado, se le asociará al cliente   
	 * @param sucursalCineProceso : Este método recibe como parámetro la sede (De tipo SucursalCine), que corresponde al lugar desde donde el cliente
	 * esta realizando la compra, con el fin de asignarle a este ticket la sala de cine correspondiente a su película
	 * */
	static void pagarTicket(Cliente clienteProceso, Pelicula peliculaProceso, LocalDateTime horarioProceso, String numeroAsientoProceso, Ticket ticketProceso, SucursalCine sucursalCineProceso) {
		
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu = 0;
		
		//Seleccionar Método de pago y realizar pago
		MetodoPago metodoPagoProceso = null;
		casoValido = false;
		casoValidoConfirmacion = false;
		double precioTicketProceso = 0;
		do {
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nEl valor a pagar por el ticket es: " + ticketProceso.getPrecio() 
					+ "\nEste es el listado de los métodos de pago disponibles:\n" 
					+ MetodoPago.mostrarMetodosDePago(clienteProceso));
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debe ingresar un único dato númerico entre los disponibles");
				}
			}while(!(opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size() ) );
			
		
			metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
			
			boolean primerProcesoDePago = true;
			boolean primerSetPrecioTicket = true;
			do {
				opcionMenu = 0;
				try {
					if (primerProcesoDePago) {
						precioTicketProceso = ticketProceso.getPrecio() * (1 - metodoPagoProceso.getDescuentoAsociado());
						primerProcesoDePago = false;
					}
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
					+ " ( Precio anterior: " + ticketProceso.getPrecio() + " -> Precio actual: " + precioTicketProceso + " )"
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
			
			if (metodoPagoProceso.realizarPago(precioTicketProceso, clienteProceso) == 0) {
				System.out.println("Pago realizado, La compra de su ticket fue exitosa");
				ticketProceso.procesarPagoRealizado(clienteProceso);
				ticketProceso.setSalaDeCine(peliculaProceso.obtenerSalaDeCineConCodigo(sucursalCineProceso));
				ticketProceso.factura(clienteProceso);
				//Generamos la fila y la columna a partir del número de asiento seleccionado
				int filaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(0));
				int columnaProceso = Character.getNumericValue(numeroAsientoProceso.charAt(2));
				peliculaProceso.modificarSalaVirtual(horarioProceso, filaProceso, columnaProceso);
				System.out.println( clienteProceso.getFacturas().get(Integer.valueOf(clienteProceso.getFacturas().size()) - 1) );
				casoValido = true;
			}else {
				precioTicketProceso = metodoPagoProceso.realizarPago(ticketProceso.getPrecio(), clienteProceso);
				System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
				casoValido = false;
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
	 * Se muestra en pantalla las llaves del diccionario de horarios de la película seleccionada, para que el cliente pueda seleccionar uno de estos
	 * @param clienteProceso : Este método recibe como parámetro un cliente (De tipo cliente) obtenido desde el login, con el fin de, 
	 * dado el caso, el cliente quiera regresar al menú principal, pueda hacerlo.
	 * @param peliculaProceso : Este método recibe como parámetro una película (De tipo película) obtenido durante el proceso de la funcionalidad 1,
	 * para que de allí podamos mostrar en pantalla las llaves de su diccionario de horarios.
	 * */
	static LocalDateTime seleccionarHorarioPelicula(Cliente clienteProceso, Pelicula peliculaProceso, SucursalCine sucursalCineProceso) {
		
		boolean casoValido;
		boolean casoValidoConfirmacion;
		int opcionMenu;
		
		LocalDateTime horarioProceso = null;
		do {
			casoValido = false;
			casoValidoConfirmacion = false;
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nLos horarios de la película " + peliculaProceso.getNombre() 
					+ " son:\n" + peliculaProceso.mostrarHorarioPelicula() 
					+ (Integer.valueOf(peliculaProceso.getHorarios().size()) + 1) + ". Volver al menú principal");
					opcionMenu = Integer.parseInt(sc.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico");
					continue;
				}
				
			}while(!(opcionMenu > 0 && opcionMenu <= Integer.valueOf(peliculaProceso.getHorarios().size()) + 1));
			
			if(peliculaProceso.obtenerHorario(opcionMenu) == null) {
				Administrador.inicio(clienteProceso, sucursalCineProceso);
			}else {
				horarioProceso = peliculaProceso.obtenerHorario(opcionMenu);
			}
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("Elegiste la película el día: " + horarioProceso.getDayOfWeek() +  " fecha: "
					+ horarioProceso.toLocalDate() + ", A las: " + horarioProceso.toLocalTime() + "\n1.Correcto \n2.Cambiar horario");
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
	 * Description: Este método se encarga de seleccionar el número de asiento del cliente para ver una película en el horario preciamente seleccionado, 
	 * para hacer esto, Se muestra en pantalla los asientos de la sala de cine con su respectiva disponibilidad desde el diccionario de horarios
	 * asociado a la película seleccionado pasando como llave el horario seleccionado, tras esto, el cliente elige la fila,
	 * luego la columna, se valida si el asiento en cuestión se encuentra disponible y una vez cumplida la verificación,
	 * retornamos el número del asiento seleccionado.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) seleccionado en el proceso de login
	 * @param horarioProceso : Este método recibe como parámetro el horario (De tipo LocalDateTime) seleccionado durante el proceso de la funcionalidad 1
	 * @param horarioProceso : Este método recibe como parámetro la película (De tipo Pelicula) seleccionada durante el proceso de la funcionalidad 1
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente
	 * */
	static String seleccionarAsiento(Cliente clienteProceso, LocalDateTime horarioProceso, Pelicula peliculaProceso, SucursalCine sucursalCineProceso) {
		boolean casoValido;
		boolean casoValidoConfirmacion;
		int opcionMenu;
		
		
		//Elegimos el asiento
		casoValido = false;
		casoValidoConfirmacion = false;
		String numeroAsientoProceso = null;
		int filaProceso = 0;
		int columnaProceso = 0;
		do {
			System.out.println("\nEsta es la distribución de asientos con su disponibilidad actual de la película en el horario seleccionado" 
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
				
				if(!(filaProceso > 0 & filaProceso <= Integer.valueOf(peliculaProceso.getHorarios().get(horarioProceso).length))){
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
				
				if(!(columnaProceso > 0 & columnaProceso <= Integer.valueOf(peliculaProceso.getHorarios().get(horarioProceso).length))){
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
				"A continuación se mostrarán en pantalla los asientos con su respectiva disponibilidad\n");
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
	 * el proceso de la funcionalidad 1
	 * @return <b>String</b> : Este método retorna un String que corresponde al número de asiento seleccionado por el cliente
	 * */
	static String seleccionarAsiento(SalaCine salaDeCinePresentacionProceso, SucursalCine sucursalCineProceso) {
		
		boolean casoValidoConfirmacion;
		boolean casoValido = false;
		int opcionMenu = 0;
		
		String numeroAsientoProceso = null;
		int filaProceso = 0;
		int columnaProceso = 0;
		
		do {
			System.out.println("\nEsta es la distribución de asientos con su disponibilidad actual de la película en el horario seleccionado" 
				    + "\n X : Ocupado\n O : Disponible\n" + salaDeCinePresentacionProceso.mostrarAsientos());
					
			//Elegimos la fila del asiento
			casoValidoConfirmacion = false;
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
	static void ingresarSalaCineDesdeMenu(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		
		System.out.println("\nBienvenido al sistema de ingreso a la sala de cine\n");
		
		//Elección menú inicial
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
				case 2: Administrador.inicio(clienteProceso, sucursalCineProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		if (opcionMenu == 1) {ingresarSalaCine(clienteProceso, sucursalCineProceso);}
		
	}
	
	/**
	 * Description: Este método se encarga de realizar el proceso para que un usario pueda ingresar a una de las salas de cine,
	 * para esto debemos cumplir un requisito fundamental, el cual es que el cliente tenga al menos un ticket asociado, dado el caso de que no,
	 * el cliente será redirigido al menú principal, en caso de que sí podemos continuar con el proceso.
	 * Mostramos en pantalla las salas de cine disponibles con información relevante de estas (Número de sala, película en presentación y horario),
	 * el cliente selecciona una de estas, verificamos con el método verificarTicket() que alguno de los tickets del cliente, cumpla con los
	 * requisitos necesarios para poder ingresar a la sala de cine, en caso de que sí, se muestra en pantalla una representación de la sala de cine
	 * y una vez termina la película se redirecciona al cliente al menú principal, en caso de que no, se vuelve a mostrar por pantalla
	 * las salas de cine disponibles.
	 * @param clienteProceso : Este método recibe como parámetro al cliente (De tipo Cliente), que ingresó desde el menú
	 * principal o compró un ticket de una película en presentación y desea verla directamente 
	 * */
	static void ingresarSalaCine(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		//Verificamos que el cliente tenga al menos un ticket asociado para continuar con el proceso
		//Mostramos el listado de las salas de cine disponibles para que el cliente elija una de estas
		SalaCine salaDeCineProceso = null;
		boolean casoValido = false;
		boolean casoValidoConfirmacion = false;
		int opcionMenu;
		
		//Tomamos las salas de cine que aún tienen películas en presentación y no han finalizado
		ArrayList<SalaCine> salasDeCineDisponibles = Pelicula.filtrarSalasDeCine(sucursalCineProceso);
		do {
			do {
				do {
					opcionMenu = 0;
					try {
						clienteProceso.dropTicketsCaducados();
						if(clienteProceso.getTickets().size() > 0 && clienteProceso.disponibilidadTIcketParaSede(sucursalCineProceso)) {
							System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
							+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime() + "\n\n"
							+ "Estos son los tickets que actualmente tienes disponibles: \n" 
							+ clienteProceso.mostrarTicketsParaUsar() );
						}else {
							System.out.println("No has comprado ningún ticket o no tienes un ticket de una película de esta sede, te redireccionaremos al menú principal");
							Administrador.inicio(clienteProceso, sucursalCineProceso);
						}
						System.out.println("Este es el listado de las salas de cine disponibles: \n" 
						+ Pelicula.mostrarSalaCine(salasDeCineDisponibles) + "\n" + (Integer.valueOf(salasDeCineDisponibles.size()) + 1) 
						+ ". Salir al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
				}while(!(opcionMenu > 0 & opcionMenu <= (Integer.valueOf(salasDeCineDisponibles.size()) + 1) ) );
				
				if (opcionMenu == (Integer.valueOf(salasDeCineDisponibles.size()) + 1)) {
					Administrador.inicio(clienteProceso, sucursalCineProceso);
				}else {
					salaDeCineProceso = salasDeCineDisponibles.get(opcionMenu - 1);
				}
				
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
		        "\n(Redirigiendo a menú principal...)");
		        
		        try {
					Thread.sleep(3000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		        
		        casoValido = true;
		        
		        SucursalCine.setFechaActual(salaDeCineProceso.getHorarioPeliculaEnPresentacion().plus(salaDeCineProceso.getPeliculaEnPresentacion().getDuracion()));
		        SucursalCine.actualizarPeliculasSalasDeCine();
		        
			}else {
				System.out.println("\nNo tienes un ticket válido o no cumple con los requisitos para ingresar a esta sala de cine" 
				+ "\nSerás redireccionado a la elección de salas de cine");
				casoValido = false;
			}
			
		}while(!casoValido);
		
		//Añadir calificación película
		
	}
	
	/**
	 * Description : Este método se encarga de avanzar el tiempo del programa según el horario de la película del ticket del cliente, 
	 * para que este pueda verla. Para hacer esto, mostramos en pantalla los tickets disponibles del cliente, el cliente selecciona uno de ellos,
	 * seteamos la hora actual con la hora de la película y actualizamos las salas de cine (Un cliente sin tickets no puede ingresar a esta sala,
	 * además antes de verficar eso, eliminamos los tickets que ya han caducado).
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo Cliente) que realizó el proceso de login
	 * */
	static void salaDeEspera(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		System.out.println("\nBienvenido a la sala de espera,\n" 
		+ "Aquí puedes esperar a que pase el tiempo para poder ingresar a la película de alguno de los tickets que adquriste\n");
		
		//Elección menu inicial
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
				case 2: Administrador.inicio(clienteProceso, sucursalCineProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
			}
			
		}while(!casoValido);
		
		//Validamos si el cliente tiene tickets disponibles
		clienteProceso.dropTicketsCaducados();
		
		if (!(clienteProceso.getTickets().size() > 0 && clienteProceso.disponibilidadTIcketParaSede(sucursalCineProceso))) {
			System.out.println("Debes tener al menos un ticket de alguna película de esta sede para hacer uso de esta sala (Redireccionando al menú principal...)");
			Administrador.inicio(clienteProceso, sucursalCineProceso);
		}
		
		//Mostramos en pantalla los tickets disponibles y el usuario selecciona uno de estos
		Ticket ticketParaUsar = null;
		casoValido = false;
		do {
			opcionMenu = 0;
			try {
				System.out.println( "\nFecha actual: "+ SucursalCine.getFechaActual().toLocalDate() 
				+ "; Hora actual: " + SucursalCine.getFechaActual().toLocalTime() + "\n\n"
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
			
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("El ticket seleccionado es para la película " + ticketParaUsar.getPelicula().getNombre()
					+ "; El día " + ticketParaUsar.getHorario().getDayOfWeek() + ";\nfecha " + ticketParaUsar.getHorario().toLocalDate() 
					+ "; A las " + ticketParaUsar.getHorario().toLocalTime() + "\nTenga en cuenta que, en caso de tener un ticket en un horario que " 
					+ "intenta omitir, este será eliminado" +"\n\n¿Es esto correcto?\n1. Correcto\n2. Cambiar ticket");
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
		
		//Adelantamos la hora actual al horario asignado al ticket seleccionado por el usuario
		SucursalCine.setFechaActual(ticketParaUsar.getHorario());
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
}
	
//1. Automatizar el proceso de actualizar las salas de cine automáticamente, acompañado del método de avanzar la hora automáticamente (Hablar con David)
//2. Al ejecutar actualizarSalaDeCine establecer un estado de salaDeCine, para determinar si esta tiene alguna película en presentación o esta siendo limpiada
// Con esto podemos verificar que efectivamente una película no se encuentra en presentación (Solución parcial: El método isPeliculaEnPresentacion()
// se encarga de validar que una película se encuentra en presentación y podemos comprar tickets (osea no superamos los 15 minutos de presentación)
// evitando así el conflicto de validar una pelicula en presentación, cuando esta ya había finalizado (Posiblemente solución definitiva))

//0. Automatizar la creación de horarios consecutivos de una película determinada y que no colisionen con los de otra película en presentación de la misma sala
//1. Limpiar código en Funcionalidad1 y Administrador(A la hora de crear los objetos)
//2. Mejorar abstracción de métodos
//3. Hacer Tests



