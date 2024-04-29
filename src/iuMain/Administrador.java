package iuMain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
import java.time.Duration;

public class Administrador {
	
	private static final boolean True = false;
	static Scanner sc = new Scanner(System.in);
	
	static long readLong() { return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	static ServicioEntretenimiento game1= new ServicioEntretenimiento("Hang Man", 15000, "Accion");
	static ServicioEntretenimiento game2= new ServicioEntretenimiento("Hang Man", 20000, "Terror");
	static ServicioEntretenimiento game3= new ServicioEntretenimiento("Hang Man", 10000, "POO");
	static ServicioEntretenimiento game4= new ServicioEntretenimiento("Hang Man", 30000, "Comedia");
	static ServicioEntretenimiento game5= new ServicioEntretenimiento("Hang Man", 5000, "Drama");
	
	static TarjetaCinemar cuenta1 = new TarjetaCinemar();
	static TarjetaCinemar cuenta2 = new TarjetaCinemar();
	static TarjetaCinemar cuenta3 = new TarjetaCinemar();
	
	static Cliente cliente1 = new Cliente("Andy", 18, 13434, TipoDeDocumento.CC);
	static Cliente cliente2 = new Cliente("Isa", 15, 4254543, TipoDeDocumento.TI);
	static Cliente cliente3 = new Cliente("Samu", 18, 646453523, TipoDeDocumento.CC);
	static Cliente cliente4 = new Cliente("Juanjo", 18 ,1013458547, TipoDeDocumento.CC);
	
	static Pelicula pelicula1 = new Pelicula("KNJ temporada 4 movie", 30000, "Aventura", Duration.ofMinutes(60), "+12", "4D", 3); 
	static Pelicula pelicula2 = new Pelicula("Oppenheimer", 15000, "Drama", Duration.ofMinutes(120), "+18", "2D", 1); 
	static Pelicula pelicula3 = new Pelicula("BNHA temporada 7 movie", 18000, "Acción", Duration.ofMinutes(60), "+18", "3D", 2);
	static Pelicula pelicula4 = new Pelicula("Código Enigma", 12000, "Historia", Duration.ofMinutes(180), "+18", "2D", 2);
	static Pelicula pelicula5 = new Pelicula("Spy x Familiy Código: Blanco", 25000, "Comedia", Duration.ofMinutes(105), "+8", "4D", 3);
	static Pelicula pelicula6 = new Pelicula("Jhon Wick 4", 17000, "Acción", Duration.ofMinutes(180), "+18", "3D", 4);
	
	static SalaCine salaDeCine1 = new SalaCine(1, "2D");
	static SalaCine salaDeCine2 = new SalaCine(2, "3D");
	static SalaCine salaDeCine3 = new SalaCine(3, "4D");
	static SalaCine salaDeCine4 = new SalaCine(4, "3D");

	static Membresia membresia1 = new Membresia("Básico", 1, 5000, 10);
	static Membresia membresia2 = new Membresia("Heróico", 2, 10000, 15);
	static Membresia membresia3 = new Membresia("Global", 3, 15000, 20);
	static Membresia membresia4 = new Membresia("Challenger", 4, 25000, 25);
	static Membresia membresia5 = new Membresia("Radiante", 5, 30000, 30);
	
	static Ticket ticket1 = new Ticket();
	static Ticket ticket2 = new Ticket();
	static Ticket ticket3 = new Ticket();
	static Ticket ticket4 = new Ticket();
	static Ticket ticket5 = new Ticket(cliente4, pelicula1, LocalDateTime.of(2024, 4, 28, 12, 0, 0), "4-8");
	static Ticket ticket6 = new Ticket(cliente4, pelicula2, LocalDateTime.of(2024, 5, 4, 16, 0, 0), "2-2");
	static Ticket ticket7 = new Ticket();
	static Ticket ticket8 = new Ticket();
	
	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 100000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 60000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 150000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",500000, 0);
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		
		
		
//		Bono pruevaBono = new Bono(1234,"Peluche","A","Souvenir");
//		Bono pruevaBono1 = new Bono(1235,"Peluche","A","Souvenir");
//		ArrayList<Bono> Bonosss = new ArrayList<>();
//		Bonosss.add(pruevaBono);
//		Bonosss.add(pruevaBono1);
//		Cliente Rusbel = new Cliente();
//		Rusbel.setBonosCliente(Bonosss);
//		Rusbel.setNombre("Rusbel");
//		Rusbel.setDocumento(1037886240);
		
		//Llamados métodos de instancias para hacer pruebas
		{
			SalaCine.setFecha(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			salaDeCine1.crearAsientosSalaDeCine();
			salaDeCine2.crearAsientosSalaDeCine();
			salaDeCine3.crearAsientosSalaDeCine();
			salaDeCine4.crearAsientosSalaDeCine(); 
			
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00));
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 5, 30, 00));
			pelicula1.setNumeroDeSala(3);
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 16, 30, 00));
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 20, 30, 00));
			pelicula2.setNumeroDeSala(1);
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 16, 30, 00));
			pelicula3.setNumeroDeSala(2);
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 17, 30, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			pelicula6.setNumeroDeSala(4);
			
			salaDeCine3.actualizarPeliculasEnPresentacion();
			salaDeCine1.actualizarPeliculasEnPresentacion();
			salaDeCine2.actualizarPeliculasEnPresentacion();
			salaDeCine4.actualizarPeliculasEnPresentacion();
			
			ticket1.setPelicula(pelicula1);
			ticket1.asignarPrecio();
			ticket1.setSalaDeCine(salaDeCine1);
			ticket1.setDueno(cliente1);
			ticket1.setNumeroAsiento("4-4");
			double precio = ticket1.getPrecio();
			ticket1.realizarPago(precio, metodoPago1, cliente1);
			ticket1.procesarPagoRealizado(cliente1);
			ticket1.setHorario(SalaCine.getFecha());
		    ticket1.factura(cliente1);
			salaDeCine1.cambiarDisponibilidadAsientoLibre(4, 4);
			
			ticket2.setPelicula(pelicula1);
			ticket2.asignarPrecio();
			ticket2.setSalaDeCine(salaDeCine1);
			ticket2.setDueno(cliente2);
			ticket2.setNumeroAsiento("1-4");
			ticket2.setHorario(SalaCine.getFecha());
			precio = ticket2.getPrecio();
			ticket2.realizarPago(precio, metodoPago2, cliente2);
			ticket2.procesarPagoRealizado(cliente2);
			ticket2.factura(cliente2);
			pelicula1.modificarSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00), 1, 4);
			
			ticket3.setPelicula(pelicula2);
			ticket3.asignarPrecio();
			
			ticket4.setPelicula(pelicula1);
			ticket4.asignarPrecio();

			Membresia.asignarTipoMembresia();
//			for (MetodoPago metodoPago : MetodoPago.getMetodosDePagoDisponibles()) {
//				MetodoPago.metodoPagoPorTipo(metodoPago);
//			}
			MetodoPago.metodoPagoPorTipo(metodoPago1);
			MetodoPago.metodoPagoPorTipo(metodoPago2);
			MetodoPago.metodoPagoPorTipo(metodoPago3);
			MetodoPago.metodoPagoPorTipo(metodoPago4);
			salaDeCine1.verificarTicket(cliente2);
//			cliente1.setMembresia(membresia1);
//			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
//			/*cliente1.setMembresia(membresia1)*/
			
			//System.out.println();
			SalaCine.setFecha(SalaCine.getFecha().plusMinutes(14));
			
			
		}
			
//			//cliente1.setMembresia(membresia4);
//			MetodoPago pago = ServicioEntretenimiento.encontrarMetodoPagoCliente("Banco Agrario", cliente1);
//			//System.out.println("\n"+cliente1.getMembresia().getTipoMembresia());
//			System.out.println(pago.getNombre()+"\n"+pago.getTipo());

			
			
			cliente4.setMembresia(membresia5);
			cliente3.setMembresia(membresia2);
			cliente4.getTickets().add(ticket5);
//			ticket5.setDueno(cliente4);
//			ticket5.setPelicula(pelicula1);
//			ticket5.setSalaDeCine(salaDeCine1);
			
			MetodoPago.asignarMetodosDePago(cliente1);
			MetodoPago.asignarMetodosDePago(cliente2);
			MetodoPago.asignarMetodosDePago(cliente3);
			MetodoPago.asignarMetodosDePago(cliente4);
			
			
			
			//System.out.println(ticket6.getCodigo());
			
//			for (MetodoPago pago : cliente1.getMetodosDePago()) {
//				System.out.println(pago.getNombre()+"\n"+pago.getLimiteMaximoPago()+"\n"+pago.getDescuentoAsociado()+"\n"+pago.getTipo());
//			}

			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
					

		

		
		
		

		
//			for (ServicioEntretenimiento juego : ServicioEntretenimiento.getJuegos()) {
//				System.out.println(juego.getGeneroServicio());
//			}
		
		//System.out.println(ServicioEntretenimiento.mostrarJuegosSinDescuento());

		System.out.println("Bienvenido al cine de marinilla");
//		
		inicio();
		
		
		
		
	}
	
	static void inicio() {
		int opcion;
		System.out.println("\n¿Qué operacion desea realizar?");
		System.out.println("1. Reservar ticket de pelicula");
		System.out.println("2. Realizar orden de comida"); 
		System.out.println("3. Realizar compra de souvenirs");
		System.out.println("4. Ingresar a la zona de juegos");
		System.out.println("5. Adquirir o actualizar membresia");
		System.out.println("6. Salir");
		opcion = Integer.parseInt(sc.nextLine());
		
		switch (opcion) {
		
		case 1: reservarTicket();inicio(); break;
		case 2: comprarComida(); inicio(); break;
		case 3: comprarSouvenirs();inicio(); break;
		case 4: ingresoZonaJuegos(); inicio(); break;
		case 5: adquirirMembresia(); inicio(); break;
		case 6: salirDelSistema();break;
		default: System.out.println("Opcion invalida"); inicio(); break;
		
		}
		
	}
	static void reservarTicket() {
		System.out.println("Bienvenido al Sistema de Reserva de ticket para película");
		
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
				case 2: inicio(); casoValido = true; break;
				case 3: salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opcion invalida"); break;
			}
			
		}while(!casoValido);
		
		//Pedimos el tipo de documento al usuario
		TipoDeDocumento documentoCliente = null;
		boolean casoValidoConfirmacion = false;
		casoValido = false;
		do{
			opcionMenu = 0;
			try {
				System.out.println("\nSeleccione el tipo de documento:\n"+ TipoDeDocumento.mostrarTiposDeDocumento());
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			
			switch (opcionMenu) {
				case 1: documentoCliente = TipoDeDocumento.CC; casoValido=true; break;
				case 2: documentoCliente = TipoDeDocumento.TI; casoValido=true; break;
				case 3: documentoCliente = TipoDeDocumento.CE; casoValido=true; break;
				default: System.out.println("Opcion invalida");
			}
			
		}while(!casoValido);	
		
		//Obtenemos al cliente que hará el proceso de reserva de ticket
		Cliente clienteProceso = null;
		
		//Se pide al usuario su número de documento
		long numeroDocumentoCliente = 0;
		casoValido = false;
		casoValidoConfirmacion = false;
		do {
			do {
				try {
					System.out.print("\nIngrese el numero de documento: ");
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
			if (clienteProceso==null) {
				System.out.println("Hemos detectado que es la primera vez que visita nuestro cine, " +
				"Por políticas de seguridad de nuestra compañia, le solicitamos que amablemente responda las siguientes preguntas");
				//Pedimos la edad del cliente
				int edadCliente = 0;
				do {
					try {
						System.out.print("Ingrese su edad: ");
						edadCliente = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar datos numéricos correspondientes a tu edad");
						continue;
					}
					
					//Confirmamos si es un dato correcto
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
					nombreCliente = sc.nextLine(); 
					
					//Confirmamos si el dato es correcto
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
				casoValido = true;
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
							System.out.println("\nEstos son sus datos personales: " + 
							"\nNombre: " + cliente1.getNombre() + "\nIdentificacion: "+ cliente1.getDocumento() + "\nEdad: " + cliente1.getEdad());
							casoValido = true;
							casoValidoConfirmacion = true;
							break;
						case 2:
							System.out.println("Verifica el numero de documento\n");
							casoValidoConfirmacion = true;
							break;
						default: 
							System.out.println("Digite una opción valida"); 
							casoValidoConfirmacion = false;
					}
				}while(!casoValidoConfirmacion);
			}
		}while(!casoValido);
		
		//Funcionalidad reserva de ticket
		casoValido = false;
		casoValidoConfirmacion = false;
		Ticket ticketProceso = null;
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
						+ Pelicula.mostrarCartelera() + ( Integer.valueOf(Pelicula.getCartelera().size()) + 1 ) + ". Salir al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico");
					}
				}while(!(opcionMenu > 0 && opcionMenu <= Pelicula.getCartelera().size() + 1));
				
				
				try {
					peliculaProceso = Pelicula.getCartelera().get(opcionMenu - 1);
				}catch(IndexOutOfBoundsException e) {
					inicio();
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
					default : System.out.println("Opcion Invalida"); casoValidoConfirmacion = false; 
				}
				
			}while (!casoValidoConfirmacion);
		}while (!casoValido);
		
		//Mostramos este menú en caso de que la película se encuentre en presentación en alguna sala de cine y 
		//además la película no lleva más de 15 minutos en presentación
		boolean terminarProceso = false;
		if(!(peliculaProceso.whereIsPeliculaEnPresentacion().equals(null)) & SalaCine.getFecha().isBefore(peliculaProceso.whereIsPeliculaEnPresentacion().getHorarioPeliculaEnPresentacion().plusMinutes(15))) {
			SalaCine salaDeCinePresentacionProceso = peliculaProceso.whereIsPeliculaEnPresentacion();
			casoValido = false;
			casoValidoConfirmacion = false;
			do {
				do {
					opcionMenu = 0;
					try {
						System.out.println("Hemos detectado que la película seleccionada se encuentra en presentación. \ninicio de proyección: " + 
						salaDeCinePresentacionProceso.getHorarioPeliculaEnPresentacion() + "\n¿Desea reservar un ticket para este horario? " +
						" (Hora actual: " + SalaCine.getFecha() + ")\n1. Comprar en este horario\n2. Comprar en otro horario");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Error, debes ingresar un único dato númerico entre los disponibles");
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				if (opcionMenu == 2) {
					break;
				}
				
				String numeroAsientoProceso = null;
				int filaProceso = 0;
				int columnaProceso = 0;
				do {
					System.out.println("\nEsta es la distribución de asientos con su disponibilidad actual de la película en el horario seleccionado" 
						    + "\n X : Ocupado\n O: Disponible\n" + salaDeCinePresentacionProceso.mostrarAsientos());
							
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
						System.out.println("\nEl asiento " + numeroAsientoProceso + " ha sido seleccionado con exito");
					}else {
						casoValido = false;
						System.out.println("\nEl asiento " + numeroAsientoProceso + " no se encuentra disponible actualmente.\n" + 
						"Se le solicita amablemente que seleccione uno de los asientos disponibles para disfrutar de su película.\n" + 
						"A continuación se mostrarán en pantalla los asientos con su respectiva disponibilidad\n");
					}
					
				}while(!(casoValido));
				
				//Compra película en otro horario
				//Realiza pago
				do {
					opcionMenu = 0;
					try {
						System.out.println("Vamos a empezar con el proceso de pago\n1. Continuar\n2. Volver al menú principal");
						opcionMenu = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					}
				}while(!(opcionMenu == 1 || opcionMenu == 2));
				
				if (opcionMenu == 1) {
					//Creamos el ticket con su respectivo precio
					ticketProceso = new Ticket(clienteProceso, salaDeCinePresentacionProceso.getPeliculaEnPresentacion(), salaDeCinePresentacionProceso.getHorarioPeliculaEnPresentacion(), numeroAsientoProceso);
					ticketProceso.asignarPrecio();
					if(!(ticketProceso.getPrecio() == peliculaProceso.getPrecio())) {
						if (peliculaProceso.getTipoDeFormato().equals("3D") || peliculaProceso.getTipoDeFormato().equals("4D") ) {
							System.out.println("\nFelicidades, por ser nuestro cliente número " + Ticket.getCantidadTicketsCreados() 
							+ " has recibido un descuento del 50% por la compra de tu ticket\n" 
							+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
						}else {
							System.out.println("\nFelicidades, por ser nuestro cliente número " + Ticket.getCantidadTicketsCreados() 
							+ " has recibido un descuento del 80% por la compra de tu ticket\n"
							+ "(Precio anterior :" + peliculaProceso.getPrecio() + " -> Precio actual: " + ticketProceso.getPrecio() + " )");
						}
					}
				}else {
					inicio();
				}

				//Seleccionar Método de pago y realizar pago
				MetodoPago metodoPagoProceso = null;
				casoValido = false;
				casoValidoConfirmacion = false;
				double precioTicketProceso = ticketProceso.getPrecio();
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
					
					do {
						opcionMenu = 0;
						try {
							System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() + "\n1. Correcto\n2. Cambiar Método de pago");
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
					
					//Setteamos el precio del ticket como el resultado de ejecutar el método realizar pago con los datos obtenidos
					precioTicketProceso = ticketProceso.realizarPago(ticketProceso.getPrecio(), metodoPagoProceso, clienteProceso);
					
					System.out.println("\nEstamos procesando su pago, por favor espere\n");
					
					//Ponemos la condición para procesar un pago realizado con sus respectivos procesos
					if (precioTicketProceso == 0) {
						System.out.println("Pago realizado, la compra de su ticket fue exitosa\n");
						ticketProceso.procesarPagoRealizado(clienteProceso);
						ticketProceso.factura(clienteProceso);
						ticketProceso.getSalaDeCine().cambiarDisponibilidadAsientoLibre(filaProceso, columnaProceso);
						casoValido = true;
						terminarProceso = true;
					}else {
						precioTicketProceso = ticketProceso.realizarPago(ticketProceso.getPrecio(), metodoPagoProceso, clienteProceso);
						System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
						casoValido = false;
					}
					
				}while(!casoValido);
				
			}while(!(casoValido));
		}
		
		//Mostramos la factura en pantalla en caso de haber realizado la reserva de un ticket de una película en reservación
		if(terminarProceso) {
			System.out.println(clienteProceso.getFacturas().get(Integer.valueOf(clienteProceso.getFacturas().size()) - 1));
			inicio();
		}
		
		//Elegimos el horario de la película seleccionada 
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
				inicio();
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
					default : System.out.println("Opcion invalida"); casoValidoConfirmacion = false; break;
				}
			}while(!casoValidoConfirmacion);
			
		}while(!casoValido);
		
		//Elegimos el asiento
		casoValido = false;
		casoValidoConfirmacion = false;
		String numeroAsientoProceso = null;
		int filaProceso = 0;
		int columnaProceso = 0;
		do {
			System.out.println("\nEsta es la distribución de asientos con su disponibilidad actual de la película en el horario seleccionado" 
		    + "\n X : Ocupado\n O: Disponible\n" + peliculaProceso.mostrarAsientosSalaVirtual(horarioProceso) );
			
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
				System.out.println("\nEl asiento " + numeroAsientoProceso + " ha sido seleccionado con exito");
			}else {
				casoValido = false;
				System.out.println("\nEl asiento " + numeroAsientoProceso + " no se encuentra disponible actualmente.\n" + 
				"Se le solicita amablemente que seleccione uno de los asientos disponibles para disfrutar de su película.\n" + 
				"A continuación se mostrarán en pantalla los asientos con su respectiva disponibilidad\n");
			}
			
		}while(!casoValido);
		
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
		
		//Creamos el ticket y asignamos su precio evaluando la posibilidad del descuento
		if (opcionMenu == 1) {
			//Creamos el ticket con su respectivo precio e informamos al cliente en caso de recibir un descuento
			ticketProceso = new Ticket(clienteProceso, peliculaProceso, horarioProceso, numeroAsientoProceso);
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
			inicio();
		}

		//Seleccionar Método de pago y realizar pago
		MetodoPago metodoPagoProceso = null;
		casoValido = false;
		casoValidoConfirmacion = false;
		double precioTicketProceso = ticketProceso.getPrecio();
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
			
			do {
				opcionMenu = 0;
				try {
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() + "\n1. Correcto\n2. Cambiar Método de pago");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
					continue;
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
			
			System.out.println("\nEstamos procesando su pago, por favor espere\n");
			
			if (ticketProceso.realizarPago(ticketProceso.getPrecio(), metodoPagoProceso, clienteProceso) == 0) {
				System.out.println("Pago realizado, La compra de su ticket fue exitosa");
				ticketProceso.procesarPagoRealizado(clienteProceso);
				ticketProceso.factura(clienteProceso);
				peliculaProceso.modificarSalaVirtual(horarioProceso, filaProceso, columnaProceso);
				System.out.println( clienteProceso.getFacturas().get(Integer.valueOf(clienteProceso.getFacturas().size()) - 1) );
				casoValido = true;
			}else {
				precioTicketProceso = ticketProceso.realizarPago(ticketProceso.getPrecio(), metodoPagoProceso, clienteProceso);
				System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
				casoValido = false;
			}
			
		}while(!casoValido);
		
		// 1. Falta añadir casoVerificacion y mostrarCarteleraInfantil !!!
		// 2. Posiblemente añadir horarioFinPelicula en SalaCine una vez se establece la película en presentacion 
		// para crear un método que elimine los tickets creados en una salaDeCine cuando se ejecute el método actualizarPeliculaPresentacion !!!
		// 3. Crear un apartado en el menú principal para ingresar a la salaDeCine
		// 4. Solucinar error métodos de pago de cliente al crear un nuevo cliente
		
	}
	
	static void comprarComida() {
		System.out.println("\nPara ordenar comida debes ingresar el tipo y numero de documento\n¿Deseas ingresar, volver, o salir?\n1.Ingresar\n2.Volver\n3.Salir");
		int opcion=(int)readLong();
		if (opcion==2) {inicio();}
		else if (opcion==3) {salirDelSistema();}
		else if (opcion==1) {}
		else {System.out.println("\nOpcion Invalida");comprarComida();}
		TipoDeDocumento documentoCliente=null;
		boolean casoValido = true;
		do{
			System.out.println("Seleccione el tipo de documento:\n1."+TipoDeDocumento.CC+"-"+TipoDeDocumento.CC.getNombre()+"\n2."+TipoDeDocumento.TI+"-"+TipoDeDocumento.TI.getNombre()+"\n3."+TipoDeDocumento.CE+"-"+TipoDeDocumento.CE.getNombre()+"\n4.Volver");
			int opcion1 = (int)readLong();
			switch (opcion1) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				case 4: ingresoZonaJuegos();casoValido=false;break;
				default: System.out.println("Opcion invalida");break;
			}
		}while(casoValido);	
		
		do {
			System.out.print("Ingrese el numero de documento: ");
			long numeroDocumentoCliente = readLong();
			Cliente cliente1=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			if (cliente1==null) {
				System.out.print("Ingrese su edad: ");
				int edadCliente = (int)readLong();
				System.out.print("Ingrese su nombre: ");
				String nombreCliente = readLn();
				cliente1 = new Cliente(nombreCliente,null,null,null,edadCliente,null,numeroDocumentoCliente,0,documentoCliente,null,null,null);
				
			}
			else {
				System.out.println("¿Eres "+cliente1.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				int eleccion = (int)readLong();
				if (eleccion==1) {
					System.out.println("\nEstos son sus datos personales:\nNombre: "+cliente1.getNombre()+"\nIdentificacion: "+cliente1.getDocumento()+"\nEdad: "+cliente1.getEdad());
					
					casoValido=true;
				}
				else if (eleccion==2) {
					System.out.println("Verifica el numero de documento\n");
				}
				else {System.out.println("Opcion invalida\n");}
			}
		}while(!casoValido);
		do{
			System.out.print("Que deseas hacer:\n1.Reclamar un Bono.\n2.Hacer un pedido.\nSeleccione una opcion:");
			int eleccion2 = (int)readLong();
			if(eleccion2 == 1) {
				ArrayList <Bono> bonos;
				bonos = ServicioComida.verificarBono(cliente1);
				if (bonos.size() == 0) {
					System.out.println("No tienes bonos disponibles");
				}
				else {
					for (int i=0;i<bonos.size();i++) {
						System.out.println(i + "." + bonos.get(i).getProducto());
					}
					System.out.println("Seleccione el producto del bono que deseas");
					int eleccion3 = (int)readLong();
					if (bonos.get(eleccion3).validarbono()) {
						System.out.println("Todo salio bien");
						inicio();
					}
				}
			}
			else if(eleccion2 == 2) {
				casoValido = false;
			}
			else {
				
			}
		}while(casoValido);
		
	
	}
		
		
	
	
		
//******************************************************************************************************************************************	   
	
	static void comprarSouvenirs() {
		System.out.println("Bienvenido a la tienda de souvenirs");
		
		//Reiteramos la eleccion del usuario
		boolean casoValido = true;
		int opcionMenu = 0;
		do {
			try {
				System.out.print("Estas seguro de acceder al servicio de souvenir:\n1.SI.\n2.NO.\nSeleccina una opcion:");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			switch (opcionMenu) {
				case 1: casoValido = false;break;
				case 2: inicio(); casoValido = false; break;
				default: System.out.println("Opcion invalida"); break;
			}
			
		}while(casoValido);
		
		//Pedimos el tipo de documento al usuario
		ServicioSouvenirs servicioSouvenirs = new ServicioSouvenirs();
		TipoDeDocumento documentoCliente = null;
		casoValido = true;
		do{
			try {
				System.out.println("Seleccione el tipo de documento:\n"+ TipoDeDocumento.mostrarTiposDeDocumento());
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			switch (opcionMenu) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				default: System.out.println("Opcion invalida");break;
			}
		}while(casoValido);
		
		//Se le pide el numero de documento y se verifica si ya esta registrado como cliente
		Cliente cliente1 = null;
		long numeroDocumentoCliente = 0;
		casoValido = true;
		boolean casoValido2 = true;
		do {
			try {
				System.out.print("Ingrese el numero de documento: ");
				numeroDocumentoCliente = Long.parseLong(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Error, debes ingresar datos numéricos correspondientes a tu número de documento");
				continue;
			}
			//Se verficia si el cliente existe
			cliente1=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			
			//En caso de que no exista, lo creamos
			if (cliente1==null) {
				System.out.println("Hemos detectado que es la primera vez que visita nuestro cine, " +
						"Por políticas de seguridad de nuestra compañia, le solicitamos que amablemente responda las siguientes preguntas");
				int edadCliente = 0;
				
				//Se registra la edad
				do {
					try {
						System.out.print("Ingrese su edad: ");
						edadCliente = Integer.parseInt(sc.nextLine());
						casoValido2 = false;
					}catch (NumberFormatException e) {
						System.out.println("Error, debes ingresar datos numéricos correspondientes a tu edad");
						continue;
					}
				}while(casoValido2);
				
				//Se registra el nombre
				System.out.print("Ingrese su nombre: ");
				String nombreCliente = sc.nextLine(); 

				//Se asocia todo a la nueva instancia del cliente
				cliente1 = new Cliente(nombreCliente,null,null,null,edadCliente,null,numeroDocumentoCliente,0,documentoCliente,null,null,null);
				servicioSouvenirs.setCliente(cliente1);
				casoValido=false;
			}
			
			//En caso de que el cliente ya esta registrado
			else{
				do{
					try {
						System.out.println("¿Eres "+cliente1.getNombre()+"?");
						System.out.println("1. SI\n2. NO");
						opcionMenu = Integer.parseInt(sc.nextLine());
						casoValido2 = false;
					}catch(NumberFormatException e){
						System.out.println("Error, debes ingresar un único dato numérico");
						continue;
					}
					if (opcionMenu==1) {
						servicioSouvenirs.setCliente(cliente1);
						casoValido=false;
						casoValido2 = false;
						}
					else if(opcionMenu==2){
						System.out.println("Verifica el numero de documento\n");
					}
					else {
						System.out.println("Opcion invalida\n");
					}
				}while(casoValido2);
			}
		}while(casoValido);
		
		//Interaccion #1 de la funcionalidad 3 la cual es una busqueda de los procutos disponibles
		casoValido = true;
		casoValido2 = true;
		int cantidad;
		int codigo;
		do {
			do {
				System.out.print(Inventario.mostrarInventario());
				try {
					System.out.print("Selecciona una opcion: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
					System.out.print("Ingrese la cantidad de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
					casoValido2 = false;
				}catch(NumberFormatException e){
					System.out.println("Error, debes ingresar un único dato numérico");
					continue;
					}
				if ((cantidad > 0) && (cantidad < Inventario.getProductosEnInventario().size())){
					codigo = Inventario.getProductosEnInventario().get(opcionMenu).getCodigoProducto();
					if ( null != Pedido.generarPedido(codigo, cantidad)){
						cliente1.getPedidos().add(Pedido.generarPedido(opcionMenu, cantidad));
					}
					else {
						System.out.println("No hay suficientes productos");
						System.out.println("En el momento hay: "+Inventario.getProductosEnInventario().get(opcionMenu).getCantidadDisponible()+" de "+Inventario.getProductosEnInventario().get(opcionMenu).getNombreProducto());
					}
			}while(casoValido2);
		}while(casoValido);
			
			
			int eleccion2 = (int) readLong();
			eleccion2 = eleccion2-1;
			if ((eleccion2 >= 0) && (eleccion2 < Inventario.getProductosEnInventario().size())){
				System.out.print("Ingrese la cantidad de productos que deseas llevar: ");
				int eleccion3 = (int) readLong();
				if (null != (Pedido.generarPedido(Inventario.getProductosEnInventario().get(eleccion2).getCodigoProducto(), eleccion3))){
					cliente1.getPedidos().add(Pedido.generarPedido(eleccion2, eleccion3));
				}
				else {
					System.out.println("No hay suficientes productos");
					System.out.println("En el momento hay: "+Inventario.getProductosEnInventario().get(eleccion2).getCantidadDisponible()+" de "+Inventario.getProductosEnInventario().get(eleccion2).getNombreProducto());
				}
			}
			else {
				System.out.println("Te equivocaste en la eleccion");
				continue;
			}
			int eleccion3 = 0;
			while (eleccion3 != 2) {
				System.out.print("Deseas hacer otro pedido:\n1.SI\n2.NO\nSeleccione una opcion: ");
				eleccion3 = (int) readLong();
				if (eleccion3 == 1) {
					break;
				}
				if (eleccion3 == 2) {
					casoValido = false;
				}
				else {
					System.out.println("Te equivocaste en la eleccion");
				}
			}
			
		}while(casoValido);
		
		System.out.println("Lo que pediste fue: ");
		for (int i = 0;i < cliente1.getPedidos().size(); i++) {
			System.out.println(cliente1.getPedidos().get(i).getNombreproducto());
		}
		
	} 
		
		
//------------------------------------------------------------------------------------------------------------------		
		
		

	static void ingresoZonaJuegos() {

		System.out.println("\nPara entrar a los juegos es necesario tener la tarjeta cinemar\n¿Desea ingresar o volver?\n1.Ingresar\n2.Volver al menú principal\n3.Salir");
		int opcion = (int)readLong();
		if (opcion==2) {inicio();}
		else if (opcion==1) {}
		else if (opcion==3) {salirDelSistema();}
		else {System.out.println("\nOpcion Invalida");ingresoZonaJuegos();}
		TipoDeDocumento documentoCliente=null;
		boolean casoValido = true;
		do{
			System.out.println("\nSeleccione el tipo de documento:\n1."+TipoDeDocumento.CC+"-"+TipoDeDocumento.CC.getNombre()+"\n2."+TipoDeDocumento.TI+"-"+TipoDeDocumento.TI.getNombre()+"\n3."+TipoDeDocumento.CE+"-"+TipoDeDocumento.CE.getNombre()+"\n4.Regresar\n5.Volver al menú principal\n6.Salir");
			int opcion1 = (int)readLong();
			switch (opcion1) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				case 4: ingresoZonaJuegos();casoValido=false;break;
				case 5: inicio();casoValido=false;break;
				case 6: salirDelSistema();
				default: System.out.println("Opcion invalida");break;
			}
		}while(casoValido);	
			
		do {
			System.out.print("Ingrese el numero de documento: ");
			long numeroDocumentoCliente = readLong();
			Cliente cliente=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			if (cliente==null) {
				System.out.print("Ingrese su edad: ");
				int edadCliente = (int)readLong();
				System.out.print("Ingrese su nombre: ");
				String nombreCliente = readLn();
				cliente = new Cliente(nombreCliente,edadCliente,numeroDocumentoCliente,documentoCliente);
				if (ServicioEntretenimiento.verificarTarjetasEnInventario()) {
					System.out.println("\nEl precio de la tarjeta Cinemar es de 5000 pesos\nEste valor sera descontado al saldo de su tarjeta");
					ServicioEntretenimiento.asociarTarjetaCliente(cliente);
					cliente.getCuenta().hacerPago(5000);
					System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente.getCuenta().getDueno().getNombre()+"\nSaldo: $"+cliente.getCuenta().getSaldo());
				}
				else {System.out.println("\nLo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde"); inicio();}
				casoValido=true;
			}
			else {
				System.out.println("¿Eres "+cliente.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				int eleccion = (int)readLong();
				if (eleccion==1) {
					if (!cliente.verificarCuenta()) {
						if (ServicioEntretenimiento.verificarTarjetasEnInventario()) {
							System.out.println("\nEl precio de la tarjeta Cinemar es de $5000 pesos\nEste valor sera descontado al saldo de su tarjeta");
							ServicioEntretenimiento.asociarTarjetaCliente(cliente);
							cliente.getCuenta().hacerPago(5000);
							System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente.getCuenta().getDueno().getNombre()+"\nSaldo: $"+cliente.getCuenta().getSaldo());
						}
						else {System.out.println("Lo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde"); inicio();}
					}
					else {
						System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente.getCuenta().getDueno().getNombre()+"\nSaldo: $"+cliente.getCuenta().getSaldo());
					}
					casoValido=true;
				}
				else if (eleccion==2) {
					System.out.println("Verifica el numero de documento\n");
				}
				else {System.out.println("Opcion invalida\n");}
			}
		}while(!casoValido);
		Cliente clienteActual = Cliente.getClientes().get(Cliente.getClientes().size()-1);
		System.out.println(clienteActual.getNombre()+"\n"+clienteActual.getDocumento()+"\n");
		do {
			System.out.println("¿Deseas recargar la tarjeta?");
			System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir");
			int eleccion1 = (int)readLong();
			boolean finCiclo = true;
			if (eleccion1==1) {
				
				System.out.println("Cada metodo de pago tiene un monto maximo para recargar, en caso de superar este monto debera elegir otro metodo de pago");
				MetodoPago.asignarMetodosDePago(clienteActual);
				double maximo = clienteActual.getMetodosDePago().get(0).getLimiteMaximoPago();
				double maximo2 = clienteActual.getMetodosDePago().get(1).getLimiteMaximoPago();
				double maximo3 = clienteActual.getMetodosDePago().get(2).getLimiteMaximoPago();
				double maximo4 = clienteActual.getMetodosDePago().get(3).getLimiteMaximoPago();
				System.out.println(MetodoPago.mostrarMetodosDePago(clienteActual.getMetodosDePago()));
				int eleccion2 = (int)readLong();
				System.out.println("¿Cuanto desea recargar?\n");
				double eleccion3 = (double)readLong();
				while (finCiclo) {
					
					switch (eleccion2) {
					
					case 1:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave = (int) readLong();
						
						MetodoPago metodoPagoCliente = ServicioEntretenimiento.encontrarMetodoPagoCliente("Bancolombia", clienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente.getLimiteMaximoPago()) {
							clienteActual.getCuenta().ingresarSaldo(metodoPagoCliente.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente.getLimiteMaximoPago();
							metodoPagoCliente.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(clienteActual.getMetodosDePago()));
							eleccion2 = (int)readLong();
						}
						else {
							clienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 2:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave2 = (int) readLong();
						
						MetodoPago metodoPagoCliente2 = ServicioEntretenimiento.encontrarMetodoPagoCliente("AV Villas", clienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente2.getLimiteMaximoPago()) {
							clienteActual.getCuenta().ingresarSaldo(metodoPagoCliente2.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente2.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente2.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente2.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente2.getLimiteMaximoPago();
							metodoPagoCliente2.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(clienteActual.getMetodosDePago()));
							eleccion2 = (int)readLong();
						}
						else {
							clienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 3:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave3 = (int) readLong();
						
						MetodoPago metodoPagoCliente3 = ServicioEntretenimiento.encontrarMetodoPagoCliente("Banco Agrario", clienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente3.getLimiteMaximoPago()) {
							clienteActual.getCuenta().ingresarSaldo(metodoPagoCliente3.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente3.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente3.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente3.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente3.getLimiteMaximoPago();
							metodoPagoCliente3.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(clienteActual.getMetodosDePago()));
							eleccion2 = (int)readLong();
						}
						else {
							clienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 4:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave4 = (int) readLong();
						
						MetodoPago metodoPagoCliente4 = ServicioEntretenimiento.encontrarMetodoPagoCliente("Efectivo", clienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente4.getLimiteMaximoPago()) {
							clienteActual.getCuenta().ingresarSaldo(metodoPagoCliente4.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente4.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente4.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente4.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente4.getLimiteMaximoPago();
							metodoPagoCliente4.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(clienteActual.getMetodosDePago()));
							eleccion2 = (int)readLong();
						}
						else {
							clienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					default:
						System.out.println("Opcion invalida");
						break;
					}
					
						
						
				}
				
				clienteActual.restablecerLimiteMaximo(maximo, maximo2, maximo3, maximo4);
				System.out.println("\nEl saldo de su tarjeta cinemar es: "+clienteActual.getCuenta().getSaldo());
				while(!finCiclo) {
					System.out.println("Desea ingresar a los juegos, Volver a recargar la tarjeta, volver al inicio o Salir");
					System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
					int eleccion4 = (int)readLong();
					switch (eleccion4) {
					case 1: casoValido= false;finCiclo= true; break;
					case 2: finCiclo= true; break;
					case 3: inicio();
					case 4: salirDelSistema();
					default: finCiclo = false; break; 
				}
				}
				
			}
			else if (eleccion1==2) {
				System.out.println("Recuerde que debe tener saldo para acceder a los diferentes juegos\nSu saldo en Tarjeta Cinemar: "+clienteActual.getCuenta().getSaldo());
				while(finCiclo) {
					System.out.println("Desea ingresar a los juegos, recargar la tarjeta, volver al inicio o Salir");
					System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
					int eleccion4 = (int)readLong();
					switch (eleccion4) {
					case 1: casoValido= false;finCiclo= false; break;
					case 2: finCiclo= false; break;
					case 3: inicio();
					case 4: salirDelSistema();
					default: finCiclo = true; break; 
					}
				}
			}
			else if (eleccion1==3) {
				inicio();
			}
			else if (eleccion1==4) {
				salirDelSistema();
			}
			else {System.out.println("Opcion invalida");}
		}while(casoValido);
		
		boolean caso = true;
		do {
			System.out.println("\n¿Has realizado alguna compra de un tiquete de cine y no has redimido su codigo?\n1.SI\n2.NO\n3.Salir");
			int eleccion6 = (int)readLong();
			if (eleccion6==1) {
				System.out.println("\nIngrese el codigo asociado a la factura de su ticket\nRecuerde que el codigo es el formato de la pelicula pegado a su tipo de documento y el numero de la salaDecine de el tiquete\nEjemplo: 4DCC12 (Todo en mayuscula)");
				String codigo = readLn();
				if (ServicioEntretenimiento.comprobarCodigo(codigo)) {
					System.out.println("Codigo correcto, Se le asignó un descuento a el precio de los juegos");
					ServicioEntretenimiento.AplicarDescuentoJuegos();
					caso = false;
				}
				else {System.out.println("Codigo incorrecto, verifique el codigo");}
				
			}
			else if (eleccion6==2) {
				caso = false;
			}
			else if (eleccion6==3) {
				salirDelSistema();
			}
			else {
				System.out.println("Opcion invalida");
			}
		}while(caso);
		
		do {
			System.out.println(ServicioEntretenimiento.mostrarJuegos());
			int eleccion7 =(int)readLong();
			switch(eleccion7) {
			case 1:
				if (clienteActual.getCuenta().getSaldo()>=game1.getValorServicio()) {
					clienteActual.getCuenta().hacerPago(game1.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+game1.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"ARMA", "COMBATE", "EXPLOSION","GUERRA","ADRENALINA"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+clienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: ingresoZonaJuegos();
						case 3: salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile1 = true;
				while (finWhile1) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)readLong();
					if (eleccion8==1) {
						finWhile1 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile1= false;
					}
				}	
				break;
			case 2:
				if (clienteActual.getCuenta().getSaldo()>=game2.getValorServicio()) {
					clienteActual.getCuenta().hacerPago(game2.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+game2.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"FANTASMA", "MOUNSTRUO", "MUERTE","ZOMBI","CEMENTERIO"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+clienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: ingresoZonaJuegos();
						case 3: salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile2 = true;
				while (finWhile2) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)readLong();
					if (eleccion8==1) {
						finWhile2 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile2= false;
					}
				}	
				break;
			
			case 3:
				if (clienteActual.getCuenta().getSaldo()>=game3.getValorServicio()) {
					clienteActual.getCuenta().hacerPago(game3.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+game3.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"OBJETO", "GUZMAN", "HERENCIA","CONSTRUCTOR","JAVA"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+clienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: ingresoZonaJuegos();
						case 3: salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile3 = true;
				while (finWhile3) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)readLong();
					if (eleccion8==1) {
						finWhile3 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile3= false;
					}
				}	
				break;
			case 4:
				if (clienteActual.getCuenta().getSaldo()>=game4.getValorServicio()) {
					clienteActual.getCuenta().hacerPago(game4.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+game4.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"DIVERSION", "RISAS", "CHISTE","PAYASO","GRACIOSO"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+clienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: ingresoZonaJuegos();
						case 3: salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile4 = true;
				while (finWhile4) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)readLong();
					if (eleccion8==1) {
						finWhile4 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile4= false;
					}
				}	
				break;
			case 5:
				if (clienteActual.getCuenta().getSaldo()>=game5.getValorServicio()) {
					clienteActual.getCuenta().hacerPago(game5.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+game5.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"SHOW", "EMOCIONES", "MUJERES","LAGRIMAS","CONMOVEDOR"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+clienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: ingresoZonaJuegos();
						case 3: salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile5 = true;
				while (finWhile5) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)readLong();
					if (eleccion8==1) {
						finWhile5 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile5= false;
					}
				}	
				break;
			default: break;
			}	
		}while(!caso);
	}
	
	static void adquirirMembresia() {
		System.out.println("Bienvenido a nuestro plan de membresias en el cine de Marinilla. ¿Desea continuar en la adquisición? \n1. Si \n2. No \n3. Salir");
		//System.out.println(Membresia.mostrarCategoria() + "6. Volver");
		int opcion = (int)readLong();
		if (opcion == 1) {}
		else if (opcion == 2) {inicio();}
		else if (opcion == 3 ) {salirDelSistema();}
		else {System.out.println("\nOpcion Invalida");adquirirMembresia();}
		TipoDeDocumento documentoCliente=null;
		boolean casoValido = true;
		do{
			System.out.println("Seleccione el tipo de documento:\n1."+TipoDeDocumento.CC+"-"+TipoDeDocumento.CC.getNombre()+"\n2."+TipoDeDocumento.TI+"-"+TipoDeDocumento.TI.getNombre()+"\n3."+TipoDeDocumento.CE+"-"+TipoDeDocumento.CE.getNombre()+"\n4.Regresar\n5.Volver al menú principal\n6.Salir");
			int opcion1 = (int)readLong();
			switch (opcion1) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				case 4: adquirirMembresia();casoValido=false;break;
				case 5: inicio();casoValido=false;break;
				case 6: salirDelSistema();
				default: System.out.println("Opcion invalida");break;
			}
		}while(casoValido);	
			
		do {
			System.out.print("Ingrese el numero de documento: ");
			long numeroDocumentoCliente = readLong();
			Cliente cliente=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			if (cliente==null) {
				System.out.print("Ingrese su edad: ");
				int edadCliente = (int)readLong();
				System.out.print("Ingrese su nombre: ");
				String nombreCliente = readLn();
				cliente = new Cliente(nombreCliente,edadCliente,numeroDocumentoCliente,documentoCliente);
				System.out.print("Gracias por su registro, " + cliente.getNombre()
				+ ". Por favor, seleccione la membresia que desea adquirir.\n" 
				+ Membresia.mostrarCategoria() + "6. Volver \nIngrese la opción: ");
				int categoriaMembresia = (int)readLong();
				if (categoriaMembresia == 6) {inicio();}
				else if (categoriaMembresia >0 && categoriaMembresia <6) {
					boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(cliente, categoriaMembresia);
					if (requisitosMembresia == false) {
						System.out.print("No puedes adquirir esta membresía debido a que no cumples con los criterios establecidos para ello: Por favor seleccione una opción:\n1. Volver al menú principal.\n2. Volver a la selección de membresias.\n3. Salir");
						int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: adquirirMembresia();
						case 3: salirDelSistema();
						default:  break;
						}
						
					} else {
						Membresia membresiaNueva = null;
						for (Membresia membresia : Membresia.getTiposDeMembresia()) {
							if (membresia.getCategoria() == categoriaMembresia) {
								membresiaNueva = membresia;
							}
						}
						System.out.print("El precio de la membresia es de " + membresiaNueva.getValorSuscripcionMensual() 
						+ ". Por favor, seleccione el método de pago a usar: "
						+ MetodoPago.mostrarMetodosDePago(cliente) + "4. Volver \nIngrese la opción: ");
					}
				
				casoValido = true;
				}
			} else {
				System.out.println("¿Eres "+cliente.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				int eleccion = (int)readLong();
				if (eleccion==2) {
					System.out.print("Por favor, escriba nuevamente su documento para continuar. \n");
					adquirirMembresia();
				} else {
					Membresia membresiaActual = cliente.getMembresia();
					String nombreMembresiaActual = null;
					if (membresiaActual == null) {
						nombreMembresiaActual = "Sin membresia";
					} else {
						nombreMembresiaActual = membresiaActual.getNombre();
					}
				System.out.print("Bienvenido, " + cliente.getNombre()
				+ ". Actualmente su membresia es " + nombreMembresiaActual 
				+ ". Por favor, seleccione la membresia que desea adquirir/actualizar.\n"
				+ Membresia.mostrarCategoria() + "6. Volver \nIngrese la opción: ");
				int categoriaMembresia = (int)readLong();
				if (categoriaMembresia == 6) {inicio();}
				else if (categoriaMembresia == membresiaActual.getCategoria()) {
					System.out.print("Ya posee esta membresia.");
				}
				else if (categoriaMembresia >0 && categoriaMembresia <6) {
					boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(cliente, categoriaMembresia);
					if (requisitosMembresia == false) {
						System.out.print("No puedes adquirir esta membresía debido a que no cumples con los criterios establecidos para ello: Por favor seleccione una opción:\n1. Volver al menú principal.\n2. Volver a la selección de membresias.\n3. Salir");
						int option = (int)readLong();
						switch(option) {
						case 1: inicio();
						case 2: adquirirMembresia();
						case 3: salirDelSistema();
						default:  break;
						}

					} else {
						Membresia membresiaNueva = null;
						for (Membresia membresia : Membresia.getTiposDeMembresia()) {
							if (membresia.getCategoria() == categoriaMembresia) {
								membresiaNueva = membresia;
							}
						}
						
						System.out.print("El precio de la membresia es de " + membresiaNueva.getValorSuscripcionMensual() 
						+ ". Por favor, seleccione el método de pago a usar: \n"
						+ MetodoPago.mostrarMetodosDePago(cliente) + "4. Volver \nIngrese la opción: ");
					}
				}
				
				}
			}
		}while (!casoValido); }
	
	static void salirDelSistema() {
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
		

		
	}
}
