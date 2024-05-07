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
	static Cliente cliente5= new Cliente("Santiago",18,1125274009,TipoDeDocumento.CC);
	static Cliente cliente4 = new Cliente("Juanjo", 18 ,1013458547, TipoDeDocumento.CC);
	
	static Pelicula pelicula1 = new Pelicula("KNJ temporada 4 movie", 30000, "Aventura", Duration.ofMinutes(60), "+12", "4D", 3); 
	static Pelicula pelicula2 = new Pelicula("Oppenheimer", 15000, "Drama", Duration.ofMinutes(120), "+18", "2D", 1); 
	static Pelicula pelicula3 = new Pelicula("BNHA temporada 7 movie", 18000, "Acción", Duration.ofMinutes(60), "+18", "3D", 2);
	static Pelicula pelicula4 = new Pelicula("Código Enigma", 12000, "Historia", Duration.ofMinutes(180), "+18", "2D", 1);
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
	static Ticket ticket5 = new Ticket(cliente4, salaDeCine3, 50000,  pelicula1, LocalDateTime.of(2024, 4, 28, 12, 0, 0), "4-8");
	static Ticket ticket6 = new Ticket(cliente1, pelicula4, LocalDateTime.of(2024, 4, 28, 13, 30, 00), "2-2");
	static Ticket ticket7 = new Ticket();
	static Ticket ticket8 = new Ticket();
	
	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 100000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 60000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 150000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",500000, 0);
	
	static Inventario camisas = new Inventario ("camisa",50,40000,"XL","Souvenirs",11);
	static Inventario camisas1 = new Inventario ("camisa",50,33000,"L","Souvenirs",11);
	static Inventario crispetas1= new Inventario("crispeta",60,20000,"Grande","Comida",21);
	
	static TarjetaCinemar tarjeta1 = new TarjetaCinemar(32000,false, cliente4);
	
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
			pelicula5.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 11, 00, 00));

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
			cliente1.getTickets().add(ticket6);
			
			ticket2.setPelicula(pelicula1);
			ticket2.asignarPrecio();
			ticket2.setSalaDeCine(salaDeCine3);
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
			
			cliente4.setCuenta(tarjeta1);
			Membresia.asignarTipoMembresia();
//			for (MetodoPago metodoPago : MetodoPago.getMetodosDePagoDisponibles()) {
//				MetodoPago.metodoPagoPorTipo(metodoPago);
//			}
			MetodoPago.metodoPagoPorTipo(metodoPago1);
			MetodoPago.metodoPagoPorTipo(metodoPago2);
			MetodoPago.metodoPagoPorTipo(metodoPago3);
			MetodoPago.metodoPagoPorTipo(metodoPago4);

			salaDeCine3.verificarTicket(cliente2);
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
			
			//Print tests
			System.out.println();
			
		}
		
		//MAIN
		System.out.println("Iniciar sesión");
		Cliente clienteProceso = iniciarSesion();
		
		System.out.println("\nSeleccione la ubicación de una de nuestras instalaciones: ");
		//Implementar método
		
		System.out.println("Hola " + clienteProceso.getNombre() + " Bienvenido al cine de marinilla");
		inicio(clienteProceso);
		
	}
	
      
	
	public static void inicio(Cliente clienteProceso) {
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
			System.out.println("7. Ingresar a sala de espera");
			System.out.println("8. Salir");
			opcion = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
		}
	}while(!(opcion > 0 & opcion <= 8));
	
	
	switch (opcion) {
		case 1: Funcionalidad1.reservarTicket(clienteProceso);inicio(clienteProceso); break;
		case 2: Funcionalidad1.ingresarSalaCineDesdeMenu(clienteProceso); inicio(clienteProceso); break;
		case 3: comprarComida(clienteProceso); inicio(clienteProceso); break;
		case 4: comprarSouvenirs(clienteProceso);inicio(clienteProceso); break;
		case 5: Funcionalidad_4.ingresoZonaJuegos(clienteProceso); inicio(clienteProceso); break;
		case 6: adquirirMembresia(clienteProceso); inicio(clienteProceso); break;
		case 7: Funcionalidad1.salaDeEspera(clienteProceso); inicio(clienteProceso); break;
		case 8: salirDelSistema(); break;
		default: System.out.println("Opción invalida"); inicio(clienteProceso);
	  }
	
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Bloque funcionalidad 1
	
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	static void comprarComida(Cliente clienteProceso) {
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
				case 2: inicio(clienteProceso); casoValido = false; break;
				default: System.out.println("\nOpcion invalida\n"); break;
			}
			
		}while(casoValido);
		
		
		ServicioComida serviC = new ServicioComida();
		
		//Creacion o validacion del cliente
		serviC.setCliente(validarCliente());
		
	
		
		
	} 
		
		
	
	
		
//******************************************************************************************************************************************	   
	
	static void comprarSouvenirs(Cliente clienteProceso) {
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
				case 2: inicio(clienteProceso); casoValido = false; break;
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
		
	//BLOQUE PARA LA FUNCIONALIDAD 4

//------------------------------------------------------------------------------------------------------------------		
	
	static void adquirirMembresia(Cliente clienteProceso) {
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
				case 2: inicio(clienteProceso); casoValido = true; break;
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
				case 4: adquirirMembresia(clienteProceso);casoValido=false;break;
				case 5: inicio(clienteProceso);casoValido=false;break;
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
			if (opcionMenu == 6) {inicio(clienteProceso); break;}
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
		String descuentoActivo = null;
		do {
			opcionMenu = 0;
			System.out.print("El precio de la membresia es de " + valorAPagar 
			+ ". Por favor, seleccione el método de pago a usar:\n"
			+ MetodoPago.mostrarMetodosDePago(cliente) + "5. Volver al inicio \nIngrese la opción: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 5) {inicio(clienteProceso);}
			MetodoPago metodoPagoSeleccionado = MetodoPago.usarMetodopago(cliente, opcionMenu);
			try {
				if (metodoPagoSeleccionado.getDescuentoAsociado() != 0 && valorAPagar == membresiaNueva.getValorSuscripcionMensual()) {
					valorAPagar = valorAPagar - valorAPagar * metodoPagoSeleccionado.getDescuentoAsociado();
					System.out.print("Con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ ", el nuevo monto a pagar es " 
						+ valorAPagar +". Por favor, seleccione una opción: ");
					
				}else {
					System.out.print(descuentoActivo + "\nEl monto a pagar con el método de pago " 
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
				descuentoActivo = "El descuento de " + (int)(primerPagoUsado.getKey().getDescuentoAsociado()*100)
				+ "% ya ha sido aplicado al precio original de " + membresiaNueva.getValorSuscripcionMensual();
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
		TarjetaCinemar tarjetaCinemarActual = cliente.getCuenta();
		int tipoMembresia = 0;
		if (tarjetaCinemarActual != null) {
			tipoMembresia = cliente.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				tarjetaCinemarActual.ingresarSaldo(10000);
			}else {
				tarjetaCinemarActual.ingresarSaldo(20000);
			}
		}else {
			boolean finalizarCompra = false;
			double saldoCuenta = 0.0;
			tipoMembresia = cliente.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				saldoCuenta = 5000.0;
			} else {
				saldoCuenta = 20000.0;
			}
			do {
				try{
					opcionMenu = 0;
					System.out.print("\nGracias por adquirir el programa de membresia. \nComo regalo, le otorgamos una tarjeta cinemar con "
							+ (int)saldoCuenta + " recargados.\n1. Confirmar.\n2. Rechazar. \nPor favor, seleccione una opción: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch (NumberFormatException e){
					System.out.print("Error. Por favor, escriba un dato numérico");}
				if (opcionMenu == 1) {
					ServicioEntretenimiento.asociarTarjetaCliente(cliente);
					cliente.getCuenta().ingresarSaldo(saldoCuenta);
					System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente.getCuenta().getDueno().getNombre()+"\nSaldo: $"+cliente.getCuenta().getSaldo());
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
				
			} while (!finalizarCompra);
		}
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
	
	/**
	 * Description : Este método se encarga de iniciar sesión, para esto se le pregunta al cliente el tipo de documento y el número de documento,
	 * en caso de que se encuentre registrado, se verfica su nombre y se retorna ese cliente, en caso de que no, se crea un nuevo cliente, solicitando
	 * su nombre y edad, luego se asignan sus métodos de pago y se retorna este nuevo cliente.
	 * @return <b>Cliente</b> : Este método retorna el cliente que realizó este proceso de forma exitosa.
	 * */
	public static Cliente iniciarSesion() {
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
							System.out.println("( Edad mínima para hacer uso de nuestras instalaciones: 5 )");
							System.out.print("Ingrese su edad: ");
							edadCliente = Integer.parseInt(sc.nextLine());
						}catch (NumberFormatException e) {
							System.out.println("Error, debes ingresar datos numéricos correspondientes a tu edad");
							continue;
						}
						
						//Verificamos si la edad seleccionada por el cliente es acorde a su número de documento
						if ( (documentoCliente.equals(TipoDeDocumento.CC) && edadCliente < 18) || 
						   ( (documentoCliente.equals(TipoDeDocumento.TI) && (edadCliente > 18 || edadCliente < 5) ) ) ||
						   ( (documentoCliente.equals(TipoDeDocumento.CE) && edadCliente < 5) ) ){
							System.out.println("Error, debes ingresar un edad apropiada para un documento tipo: " + documentoCliente.getNombre());
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
						nombreCliente = sc.nextLine(); 
						
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
	
	
	
	
}

