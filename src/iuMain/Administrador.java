package iuMain;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
import java.time.Duration;

public class Administrador {
	
//	private static final boolean True = false;
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
	static Cliente cliente5= new Cliente("Santiago",18,1125274009,TipoDeDocumento.CC);
	
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
	
	static Inventario camisas = new Inventario ("camisa",50,40000,"XL","Souvenirs",11);
	static Inventario camisas1 = new Inventario ("camisa",50,33000,"L","Souvenirs",11);
	static Inventario crispetas1= new Inventario("crispeta",60,20000,"Grande","Comida",21);
	
	
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

			pelicula4.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula5.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));

			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 17, 30, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			pelicula6.setNumeroDeSala(4);
			

			salaDeCine3.actualizarPeliculasEnPresentacion();
			salaDeCine1.actualizarPeliculasEnPresentacion();
			salaDeCine2.actualizarPeliculasEnPresentacion();
			salaDeCine4.actualizarPeliculasEnPresentacion();

			Pelicula.actualizarSalasDeCine();
			
			ticket1.setPelicula(pelicula1);
			ticket1.asignarPrecio();
			ticket1.setSalaDeCine(salaDeCine3);
			ticket1.setDueno(cliente1);
			ticket1.setHorario(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			ticket1.setNumeroAsiento("4-4");
			double precio = ticket1.getPrecio();
			metodoPago1.realizarPago(precio, cliente1);
			ticket1.procesarPagoRealizado(cliente1);
		    ticket1.factura(cliente1);
			salaDeCine3.cambiarDisponibilidadAsientoLibre(4, 4);
			
			ticket2.setPelicula(pelicula1);
			ticket2.asignarPrecio();
			ticket2.setSalaDeCine(salaDeCine1);
			ticket2.setDueno(cliente2);
			ticket2.setNumeroAsiento("1-4");
			ticket2.setHorario(SalaCine.getFecha());
			precio = ticket2.getPrecio();
			metodoPago2.realizarPago(precio, cliente2);
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

			cliente1.setMembresia(membresia3);

//			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
//			/*cliente1.setMembresia(membresia1)*/
			
			//System.out.println();
			SalaCine.setFecha(SalaCine.getFecha().plusMinutes(14));
			
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
			System.out.println();
			
		}
			


		System.out.println("Bienvenido al cine de marinilla");
		inicio();
		
	}
	
      
	
	static void inicio() {
	int opcion = 0;
	do {
		try {
			opcion = 0;
			System.out.println("\n¿Qué operacion desea realizar?");
			System.out.println("1. Reservar ticket de pelicula");
			System.out.println("2. Ingresar a la sala de cine");
			System.out.println("3. Realizar orden de comida"); 
			System.out.println("4. Realizar compra de souvenirs");
			System.out.println("5. Ingresar a la zona de juegos");
			System.out.println("6. Adquirir o actualizar membresia");
			System.out.println("7. Salir");
			opcion = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
		}
	}while(!(opcion > 0 & opcion <= 7));
	
	
	switch (opcion) {
		case 1: reservarTicket();inicio(); break;
		case 2: ingresarSalaCineDesdeMenu(); inicio(); break;
		case 3: comprarComida(); inicio(); break;
		case 4: comprarSouvenirs();inicio(); break;
		case 5: ingresoZonaJuegos(); inicio(); break;
		case 6: adquirirMembresia(); inicio(); break;
		case 7: salirDelSistema(); break;
		default: System.out.println("Opción invalida"); inicio();
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
				default: System.out.println("Opción invalida");
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
				default: System.out.println("Opción invalida");
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
				clienteProceso.setMetodosDePago(MetodoPago.asignarMetodosDePago(clienteProceso));
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
					default : System.out.println("Opción Invalida"); casoValidoConfirmacion = false; 
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
							precioTicketProceso = ticketProceso.getPrecio() * (1 - metodoPagoProceso.getDescuentoAsociado());
							System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
							+ " ( Precio anterior: " + ticketProceso.getPrecio() + " -> Precio actual: " + precioTicketProceso + " )"
							+ "\n1. Correcto\n2. Cambiar Método de pago");
							opcionMenu = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
						}
						switch(opcionMenu) {
						case 1: casoValidoConfirmacion = true; break;
						case 2: casoValidoConfirmacion = true; precioTicketProceso = ticketProceso.getPrecio(); break;
						default: System.out.println("Opción Invalida"); casoValidoConfirmacion = false;
						}
					}while(!casoValidoConfirmacion);
					
					if (opcionMenu == 2 || opcionMenu == 0) {
						continue;
					}
					
					ticketProceso.setPrecio(precioTicketProceso);
					//Setteamos el precio del ticket como el resultado de ejecutar el método realizar pago con los datos obtenidos
					precioTicketProceso = metodoPagoProceso.realizarPago(ticketProceso.getPrecio(), clienteProceso);
					
					System.out.println("\nEstamos procesando su pago, por favor espere\n");
					try {
						Thread.sleep(3000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					
					//Ponemos la condición para procesar un pago realizado con sus respectivos procesos
					if (precioTicketProceso == 0) {
						System.out.println("Pago realizado, la compra de su ticket fue exitosa\n");
						ticketProceso.procesarPagoRealizado(clienteProceso);
						ticketProceso.factura(clienteProceso);
						ticketProceso.getSalaDeCine().cambiarDisponibilidadAsientoLibre(filaProceso, columnaProceso);
						casoValido = true;
						terminarProceso = true;
					}else {
						precioTicketProceso = metodoPagoProceso.realizarPago(ticketProceso.getPrecio(), clienteProceso);
						System.out.println("Tiene un saldo pendiente de : " + precioTicketProceso);
						casoValido = false;
					}
					
				}while(!casoValido);
				
			}while(!(casoValido));
		}
		
		//Mostramos la factura en pantalla en caso de haber realizado la reserva de un ticket de una película en reservación
		if(terminarProceso) {		
			do {
				opcionMenu = 0;
				try {
					System.out.println("\nPara finalizar, ¿Con cuál de las siguientes opciones desea continuar?" + 
					"\n1. Ingresar a las salas de cine \n2. Volver al menú principal");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
				}
				
			}while(!(opcionMenu == 1 || opcionMenu == 2));
			
			if(opcionMenu == 1) {
				ingresarSalaCine(clienteProceso);
			}
			
			System.out.println("\nFin del proceso reserva de ticket");
			
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
					default : System.out.println("Opción invalida"); casoValidoConfirmacion = false; break;
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
					precioTicketProceso = ticketProceso.getPrecio() * (1 - metodoPagoProceso.getDescuentoAsociado());
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
					+ " ( Precio anterior: " + ticketProceso.getPrecio() + " -> Precio actual: " + precioTicketProceso + " )"
					+ "\n1. Correcto\n2. Cambiar Método de pago");
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
			
			ticketProceso.setPrecio(precioTicketProceso);
			System.out.println("\nEstamos procesando su pago, por favor espere\n");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if (metodoPagoProceso.realizarPago(ticketProceso.getPrecio(), clienteProceso) == 0) {
				System.out.println("Pago realizado, La compra de su ticket fue exitosa");
				ticketProceso.procesarPagoRealizado(clienteProceso);
				ticketProceso.factura(clienteProceso);
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
		
		//Cambiar formato horarios para que se vea más bonito
		//1. Falta añadir casoVerificacion para mostrarCarteleraInfantil
		//4. Implementar barra de carga al momento de procesar pago (antes de ejecutar el método ticket.realizarPago(...))
		//5. Implementar verifcacion NumeroDocumento.unique()
		//2. Posiblemente añadir horarioFinPelicula en SalaCine una vez se establece la película en presentacion 
		// para crear un método que elimine los tickets creados en una salaDeCine cuando se ejecute el método actualizarPeliculaPresentacion !!!
		// Especificar qué es una fila y qué es una columna
	}
	
	static void ingresarSalaCineDesdeMenu() {
		
		System.out.println("Bienvenido al sistema de ingreso a la sala de cine\n");
		
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
				case 2: inicio(); casoValido = true; break;
				case 3: salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida");
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
				default: System.out.println("Opción invalida");
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
				clienteProceso.setMetodosDePago(MetodoPago.asignarMetodosDePago(clienteProceso));
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
		
		
		if (opcionMenu == 1) {ingresarSalaCine(clienteProceso);}
		
	}
	
	static void ingresarSalaCine(Cliente clienteProceso) {
		//Mostramos el listado de las salas de cine disponibles para que el cliente elija una de estas
				SalaCine salaDeCineProceso = null;
				boolean casoValido = false;
				boolean casoValidoConfirmacion = false;
				int opcionMenu;
				do {
					do {
						do {
							opcionMenu = 0;
							try {
								if(clienteProceso.getTickets().size() > 0) {
									System.out.println( "\nFecha actual: "+ SalaCine.getFecha().toLocalDate() 
									+ "Hora actual: " + SalaCine.getFecha().toLocalTime() + "\n"
									+ "Estos son los tickets que actualmente tienes disponibles: \n" 
									+ clienteProceso.mostrarTicketsParaUsar() );
								}else {
									System.out.println("No has comprado ningún ticket, te redireccionaremos al menú principal");
									inicio();
								}
								System.out.println("Este es el listado de las salas de cine disponibles: \n" 
								+ Pelicula.mostrarSalaCine() + "\n" + (Integer.valueOf(Pelicula.getSalasDeCine().size()) + 1) 
								+ ". Salir al menú principal");
								opcionMenu = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
							}
						}while(!(opcionMenu > 0 & opcionMenu <= (Integer.valueOf(Pelicula.getSalasDeCine().size()) + 1) ) );
						
						if (opcionMenu == (Integer.valueOf(Pelicula.getSalasDeCine().size()) + 1)) {
							inicio();
						}else {
							salaDeCineProceso = Pelicula.getSalasDeCine().get(opcionMenu - 1);
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
				        
				        System.out.println("La película ha finalizado, muchas gracias por asistir le desamos un feliz resto de día" + 
				        "\n(Redirigiendo a menú principal...)");
				        
				        try {
							Thread.sleep(3000);
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
				        
				        casoValido = true;
				        
					}else {
						System.out.println("No tienes un ticket válido para ingresar a esta sala de cine" 
						+ "\nSerás redireccionado a la elección de salas de cine");
						casoValido = false;
					}
					
				}while(!casoValido);
	}

//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	static void comprarComida() {
	System.out.println("\nBienvenido al Servicio de Comida\n");
		
		
		boolean casoValido = true;
		int eleccionMenu = 0;
		do {
			try {
				System.out.print("Estas seguro de acceder al servicio de comida?:\n1.SI.\n2.NO.\nSeleccina una opcion:");
				eleccionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			switch (eleccionMenu) {
				case 1: casoValido = false;break;
				case 2: inicio(); casoValido = false; break;
				default: System.out.println("\nOpcion invalida\n"); break;
			}
			
		}while(casoValido);
		
		
		ServicioComida serviC = new ServicioComida();
		
		//Creacion o validacion del cliente
		serviC.setCliente(validarCliente());
		
	
		
		
	} 
		
		
	
	
		
//******************************************************************************************************************************************	   
	
	static void comprarSouvenirs() {
		System.out.println("\n----------------Bienvenido a la tienda de souvenirs--------------\n");
		
		//Reiteramos la eleccion del usuario
		boolean casoValido = true;
		int opcionMenu = 0;
		do {
			try {
				System.out.print("Estas seguro de acceder al servicio de souvenir:\n1.SI.\n2.NO.\nSeleccina una opcion:");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			switch (opcionMenu) {
				case 1: casoValido = false;break;
				case 2: inicio(); casoValido = false; break;
				default: System.out.println("\nOpcion invalida\n"); break;
			}
			
		}while(casoValido);
		
		
		ServicioSouvenirs servicioSouvenirs = new ServicioSouvenirs();
		
		//Creacion o validacion del cliente
		servicioSouvenirs.setCliente(validarCliente());

		
		//Interaccion #1 de la funcionalidad 3 la cual es una busqueda de los procutos disponibles
		servicioSouvenirs.generarOrden(servicioSouvenirs.getCliente());
			
		casoValido = true;
		do {
			//Interaccion #1 de la funcionalidad 3 la cual es una busqueda de los procutos disponibles
			System.out.println(servicioSouvenirs.generarOrden(servicioSouvenirs.getCliente()));
			do {
				try {
					System.out.println("\n¿Deseas hacer otro pedido?\n1.SI\n2.No");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				switch (opcionMenu) {
					case 1: casoValido = false;break;
					case 2: casoValido = false;break;
					default: System.out.println("\nOpcion invalida\n");break;
			}
			}while(casoValido);
			
		}while(opcionMenu != 2);

		
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
		System.out.println("Bienvenido a nuestro plan de membresias en el cine de Marinilla.");
		//System.out.println(Membresia.mostrarCategoria() + "6. Volver");
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
		//Empiza el do while para poner el documento.
		TipoDeDocumento documentoCliente=null;
		casoValido = true;
		do{
			opcionMenu = 0;
			try {
				System.out.println("\nSeleccione el tipo de documento:\n"+ TipoDeDocumento.mostrarTiposDeDocumento() + "4. Regresar\n5. Volver al menú principal\n6. Salir");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Error, debes ingresar un dato numérico");
				continue;
			}
			switch (opcionMenu) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				case 4: adquirirMembresia();casoValido=false;break;
				case 5: inicio();casoValido=false;break;
				case 6: salirDelSistema();
				default: System.out.println("Opcion invalida");break;
			}
		}while(casoValido);	
		
		//Se pide la cédula del cliente para la confirmación
		long numeroDocumentoCliente = 0;
		Cliente cliente = null;
		do {
			opcionMenu = 0;
			try {
			System.out.print("Ingrese el numero de documento: ");
			numeroDocumentoCliente = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e){
				System.out.print("Error, solo se puede ingresar números.\n");
				continue;
			}
			System.out.print("Es el número de documento "+ numeroDocumentoCliente 
			+ "\n1. Correcto \n2. Incorrecto");
			opcionMenu = Integer.parseInt(sc.nextLine());
		} while (opcionMenu != 1);
		
		//Se revisa si la cédula del cliente esta registrada.
		cliente=Cliente.revisarDatosCliente(numeroDocumentoCliente);
		do {
			if (cliente==null) {
				opcionMenu = 0;
				int edadCliente;
				String nombreCliente;
				do {
					System.out.print("Ingrese su edad: ");
					edadCliente = (int)readLong();
					System.out.print("Ingrese su nombre: ");
					nombreCliente = readLn();
					System.out.print("Confirme si sus datos son correctos para el registro.\n" 
					+ "Nombre: " + nombreCliente + "\nEdad: " +edadCliente + "\nNúmero de documento: " + numeroDocumentoCliente
					+ "\n1. Correcto.\n2. Editar datos.");
					opcionMenu = Integer.parseInt(sc.nextLine());
					//Se puede editar todos los datos en caso de error.
					if (opcionMenu == 2) {
						long numeroDocumentoNuevo;
						Cliente clienteNuevo = null;
						do {
							System.out.print("Ingrese el número de documento para la nueva cuenta: ");
							numeroDocumentoNuevo = (int)readLong();
							clienteNuevo=Cliente.revisarDatosCliente(numeroDocumentoNuevo);
							if (clienteNuevo!=null) {
								System.out.println("Este número de documento ya existe en el registro");
							} else {
								numeroDocumentoCliente = numeroDocumentoNuevo;
								clienteNuevo = null;
							}
						} while (clienteNuevo!=null);
					}
					
				} while (opcionMenu == 2);
				
				cliente = new Cliente(nombreCliente,edadCliente,numeroDocumentoCliente,documentoCliente);
				MetodoPago.asignarMetodosDePago(cliente);
				System.out.print("Gracias por su registro.\n");
			} else {
				//Se realiza la verificación del cliente
				opcionMenu = 0;
				System.out.println("¿Eres "+cliente.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				opcionMenu = Integer.parseInt(sc.nextLine());
				if (opcionMenu==2) {
					cliente = null;
					long numeroDocumentoNuevo;
					Cliente clienteNuevo = null;
					do {
						System.out.print("Ingrese el número de documento para la nueva cuenta: ");
						numeroDocumentoNuevo = (int)readLong();
						clienteNuevo=Cliente.revisarDatosCliente(numeroDocumentoNuevo);
						if (clienteNuevo!=null) {
							System.out.println("Este número de documento ya existe en el registro");
						} else {
							numeroDocumentoCliente = numeroDocumentoNuevo;
							clienteNuevo = null;
						}
					} while (clienteNuevo!=null);
					
				}
			}
		} while (cliente == null);
		
		//Se da a escoger al usuario la membresia
		Membresia membresiaNueva = null;
		do {
			opcionMenu = 0;
			System.out.print(Membresia.verificarMembresiaActual(cliente));
			System.out.print(Membresia.mostrarCategoria(cliente) + "6. Volver al inicio. \nIngrese el número de la categoria deseada: ");
			opcionMenu = (int) Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {inicio(); break;}
			else if (opcionMenu >0 && opcionMenu <6) {
				if (cliente.getMembresia()!= null) {
					int categoriaCliente = cliente.getMembresia().getCategoria();
					if (categoriaCliente == opcionMenu) {
						System.out.print("Ya posee esta categoria. Redirigiendo al menú de membresias\n");
						continue;
					}
				}
				boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(cliente, opcionMenu);
					if (requisitosMembresia == false) {
						System.out.print("No puedes adquirir esta membresía debido a que no cumples con los criterios establecidos para ello.\n"
								+ "Redirigiendo al menú de membresias\n");
						continue;
					} else {
						membresiaNueva = Membresia.asignarMembresiaNueva(membresiaNueva, opcionMenu);
					}
			} else {
				continue;
			}
		} while (membresiaNueva == null);
		
		//Una vez se ha escogido la membresia, se pasa a realizar el pago
		double valorAPagar= membresiaNueva.getValorSuscripcionMensual();
		double precio = 0;
		LinkedHashMap<MetodoPago, Double> pagosEnTransaccion = new LinkedHashMap<>();
		Entry<MetodoPago, Double> primerPagoUsado = null;
		String descuentoActivo = "hola";
		do {
			opcionMenu = 0;
			System.out.print("El precio de la membresia es de " + valorAPagar 
			+ ". Por favor, seleccione el método de pago a usar:\n"
			+ MetodoPago.mostrarMetodosDePago(cliente) + "5. Volver al inicio \nIngrese la opción: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 5) {inicio();}
			MetodoPago metodoPagoSeleccionado = MetodoPago.usarMetodopago(cliente, opcionMenu);
			try {
				descuentoActivo = "";
				if (metodoPagoSeleccionado.getDescuentoAsociado() != 0 && valorAPagar == membresiaNueva.getValorSuscripcionMensual()) {
					valorAPagar = valorAPagar - valorAPagar * metodoPagoSeleccionado.getDescuentoAsociado();
					System.out.print("Con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ ", el nuevo monto a pagar es " 
						+ valorAPagar +". Por favor, seleccione una opción: ");
					if (primerPagoUsado != null) {
					descuentoActivo = "El descuento de " + primerPagoUsado.getKey().getDescuentoAsociado()
							+ " ya ha sido aplicado al precio de " + membresiaNueva.getValorSuscripcionMensual();}
				} else {
					System.out.print(descuentoActivo);
					System.out.print("El monto a pagar con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ " es " 
						+ valorAPagar + ". Por favor, seleccione una opción: ");
				}
			precio = Double.parseDouble(sc.nextLine());
			}catch (NumberFormatException e) {
			System.out.print("Por favor, solo ingrese números");}
			if (precio < valorAPagar) {
				System.out.print("Por favor, termine de completar el pago.\n");
				pagosEnTransaccion.put(metodoPagoSeleccionado, precio);
				primerPagoUsado = pagosEnTransaccion.entrySet().iterator().next();
				valorAPagar = valorAPagar - precio;
				continue;
			} else {
				pagosEnTransaccion.put(metodoPagoSeleccionado, precio);
				valorAPagar = metodoPagoSeleccionado.realizarPago(precio, cliente);
				break;
			}
		}while (valorAPagar != 0); 
		membresiaNueva.procesarPagoRealizado(cliente);
		System.out.print(membresiaNueva.factura(cliente));
	}
						
				
	
	static void salirDelSistema() {
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
	}
	
	public static Cliente validarCliente() {
		Scanner sc = new Scanner(System.in);
		TipoDeDocumento documentoCliente = null;
		boolean casoValido = true;
		int opcionMenu;
		do{
			try {
				System.out.println("\n------------------Tipos de documentos-------------------\n"+ 
				TipoDeDocumento.mostrarTiposDeDocumento());
				System.out.print("Seleccione una opcion:");
				opcionMenu = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e){
				System.out.println("\n*****Error, debes ingresar un dato numérico*****\n");
				continue;
			}
			switch (opcionMenu) {
				case 1: documentoCliente = TipoDeDocumento.CC;casoValido=false;break;
				case 2: documentoCliente = TipoDeDocumento.TI;casoValido=false;break;
				case 3: documentoCliente = TipoDeDocumento.CE;casoValido=false;break;
				default: System.out.println("**********Opcion invalida**********");break;
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
				System.out.println("\n*****Error, debes ingresar datos numéricos correspondientes a tu número de documento\n*****");
				continue;
			}
			//Se verficia si el cliente existe
			cliente1=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			
			//En caso de que no exista, lo creamos
			if (cliente1==null) {
				System.out.println("\nHemos detectado que es la primera vez que visita nuestro cine, " +
						"Por políticas de seguridad de nuestra compañia, le solicitamos que amablemente responda las siguientes preguntas");
				int edadCliente = 0;
				
				//Se registra la edad
				do {
					try {
						System.out.print("\nIngrese su edad: ");
						edadCliente = Integer.parseInt(sc.nextLine());
						casoValido2 = false;
					}catch (NumberFormatException e) {
						System.out.println("\nError, debes ingresar datos numéricos correspondientes a tu edad\n");
						continue;
					}
				}while(casoValido2);
				
				//Se registra el nombre
				System.out.print("\nIngrese su nombre: ");
				String nombreCliente = sc.nextLine(); 

				//Se asocia todo a la nueva instancia del cliente
				cliente1 = new Cliente(nombreCliente,edadCliente,numeroDocumentoCliente,documentoCliente);
				return cliente1;
			}
			
			//En caso de que el cliente ya esta registrado
			else{
				do{
					try {
						System.out.println("\n¿Eres "+cliente1.getNombre()+"?");
						System.out.println("1. SI\n2. NO");
						opcionMenu = Integer.parseInt(sc.nextLine());
						casoValido2 = false;
					}catch(NumberFormatException e){
						System.out.println("\nError, debes ingresar un único dato numérico\n");
						continue;
					}
					if (opcionMenu==1) {
						return cliente1;
						}
					else if(opcionMenu==2){
						System.out.println("\nVerifica el numero de documento\n");
					}
					else {
						System.out.println("\nOpcion invalida\n");
					}
				}while(casoValido2);
			}
		}while(casoValido);
		return null;
	}
	
}

