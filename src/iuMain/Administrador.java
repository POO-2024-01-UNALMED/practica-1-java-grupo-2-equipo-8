package iuMain;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
import java.time.Duration;

public class Administrador {
	
//	private static final boolean True = false;
	static Scanner sc = new Scanner(System.in);
	
	static long readLong() {return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	static SucursalCine sucursalCine1 = new SucursalCine("Bucaramanga");
	static SucursalCine sucursalCine2 = new SucursalCine("Marinilla");
	static SucursalCine sucursalCine3 = new SucursalCine("San Andrés");
	
	static ArrayList<Producto> inventarioBucaramanga = new ArrayList<Producto>();
	static ArrayList<Producto> inventarioMarinilla = new ArrayList<Producto>();
	static ArrayList<Producto> inventarioSanAndrés = new ArrayList<Producto>();
	
	static Producto producto1 = new Producto("Hamburguesa","Grande","comida",25000,200,null);
	static Producto producto2 = new Producto("Hamburguesa","Cangreburger","comida",30000,200,"Comedia");
	static Producto producto3 = new Producto("Perro caliente","Grande","comida",20000,200,null);
	static Producto producto4 = new Producto("Perro caliente","Don salchica","comida",30000,200,"Comedia");
	static Producto producto5 = new Producto("Crispetas","cazador de Demonios","comida",15000,200,"Accion");
	static Producto producto6 = new Producto("Crispetas","Grandes","comida",16000,200,null);
	static Producto producto7 = new Producto("Gaseosa","Grande","comida",6000,200,null);
	static Producto producto8 = new Producto("Gaseosa","Pequeña","comida",3000,200,null);
	
	
	static Producto productoBono = new Producto("Hamburguesa","Cangreburger","comida",30000,1,"Comedia");
	static Bono bono1 = new Bono(1234,productoBono,"comida");
	
	static Arkade game1= new Arkade("Hang Man", 15000, "Accion");
	static Arkade game2= new Arkade("Hang Man", 20000, "Terror");
	static Arkade game3= new Arkade("Hang Man", 10000, "POO");
	static Arkade game4= new Arkade("Hang Man", 30000, "Comedia");
	static Arkade game5= new Arkade("Hang Man", 7500, "Drama");
	
	//static TarjetaCinemar cuenta1 = new TarjetaCinemar();
	//static TarjetaCinemar cuenta2 = new TarjetaCinemar();
	//static TarjetaCinemar cuenta3 = new TarjetaCinemar();
	
	static Cliente cliente1 = new Cliente("Andy", 18, 13434, TipoDeDocumento.CC);
	static Cliente cliente2 = new Cliente("Isa", 15, 4254543, TipoDeDocumento.TI);
	static Cliente cliente3 = new Cliente("Samu", 18, 646453523, TipoDeDocumento.CC);
	static Cliente cliente5= new Cliente("Santiago",18,1125274009,TipoDeDocumento.CC);
	static Cliente cliente4 = new Cliente("Juanjo", 18 ,987, TipoDeDocumento.CC);
	
	static SalaCine salaDeCine1 = new SalaCine(1, "2D", sucursalCine1);
	static SalaCine salaDeCine2 = new SalaCine(2, "3D", sucursalCine1);
	static SalaCine salaDeCine3 = new SalaCine(3, "4D", sucursalCine1);
	static SalaCine salaDeCine4 = new SalaCine(4, "3D", sucursalCine1);
	static SalaCine salaDeCine13 = new SalaCine(5, "2D", sucursalCine1);
	static SalaCine salaDeCine14 = new SalaCine(6, "3D", sucursalCine1);
	static SalaCine salaDeCine15 = new SalaCine(7, "4D", sucursalCine1);
	static SalaCine salaDeCine16 = new SalaCine(8, "3D", sucursalCine1);
	static Pelicula pelicula13 = new Pelicula("Star Wars: Episode 1", 30000, "Aventura", Duration.ofMinutes(90), "+7", "4D", 3); 
	static Pelicula pelicula14 = new Pelicula("El conjuro 3", 20000, "Terror", Duration.ofMinutes(120), "+18", "3D", 4); 
	static Pelicula pelicula15 = new Pelicula("Godzilla x Kong", 18000, "Ciencia ficción", Duration.ofMinutes(125), "+7", "3D", 2);
	static Pelicula pelicula16 = new Pelicula("Misión Imposible 4", 32000, "Acción", Duration.ofMinutes(150), "+18", "3D", 2);
	static Pelicula pelicula17 = new Pelicula("Your name", 15000, "Romance", Duration.ofMinutes(120), "+8", "2D", 1);
	static Pelicula pelicula18 = new Pelicula("Spy x Familiy Código: Blanco", 19000, "Infantil", Duration.ofMinutes(105), "+5", "3D", 4);
	
	static SalaCine salaDeCine5 = new SalaCine(1, "2D", sucursalCine2);
	static SalaCine salaDeCine6 = new SalaCine(2, "3D", sucursalCine2);
	static SalaCine salaDeCine7 = new SalaCine(3, "4D", sucursalCine2);
	static SalaCine salaDeCine8 = new SalaCine(4, "3D", sucursalCine2);
	static SalaCine salaDeCine17 = new SalaCine(5, "2D", sucursalCine2);
	static SalaCine salaDeCine18 = new SalaCine(6, "3D", sucursalCine2);
	static SalaCine salaDeCine19 = new SalaCine(7, "4D", sucursalCine2);
	static SalaCine salaDeCine20 = new SalaCine(8, "3D", sucursalCine2);
	static Pelicula pelicula7 = new Pelicula("Jujutsu Kaisen Cero", 30000, "Acción", Duration.ofMinutes(60), "+12", "4D", 3); 
	static Pelicula pelicula8 = new Pelicula("El pájaro loco", 15000, "Infantil", Duration.ofMinutes(120), "+5", "2D", 1); 
	static Pelicula pelicula9 = new Pelicula("Challengers", 18000, "Drama", Duration.ofMinutes(132), "+12", "3D", 2);
	static Pelicula pelicula10 = new Pelicula("The First Omen", 12000, "Terror", Duration.ofMinutes(114), "+18", "2D", 1);
	static Pelicula pelicula11 = new Pelicula("One Life", 25000, "Historia", Duration.ofMinutes(110), "+8", "4D", 3);
	static Pelicula pelicula12 = new Pelicula("Civil War", 17000, "Acción", Duration.ofMinutes(109), "+18", "3D", 4);
	
	static SalaCine salaDeCine9 = new SalaCine(1, "2D", sucursalCine3);
	static SalaCine salaDeCine10 = new SalaCine(2, "3D", sucursalCine3);
	static SalaCine salaDeCine11 = new SalaCine(3, "4D", sucursalCine3);
	static SalaCine salaDeCine12 = new SalaCine(4, "3D", sucursalCine3);
	static SalaCine salaDeCine21 = new SalaCine(5, "2D", sucursalCine3);
	static SalaCine salaDeCine22 = new SalaCine(6, "3D", sucursalCine3);
	static SalaCine salaDeCine23 = new SalaCine(7, "4D", sucursalCine3);
	static SalaCine salaDeCine24 = new SalaCine(8, "3D", sucursalCine3);
	static Pelicula pelicula1 = new Pelicula("KNJ temporada 4 movie", 30000, "Aventura", Duration.ofMinutes(60), "+12", "4D", 3); 
	static Pelicula pelicula2 = new Pelicula("Oppenheimer", 15000, "Drama", Duration.ofMinutes(120), "+18", "2D", 1); 
	static Pelicula pelicula3 = new Pelicula("BNHA temporada 7 movie", 18000, "Acción", Duration.ofMinutes(60), "+18", "3D", 2);
	static Pelicula pelicula4 = new Pelicula("Código Enigma", 12000, "Historia", Duration.ofMinutes(180), "+18", "2D", 1);
	static Pelicula pelicula5 = new Pelicula("Garfield", 17000, "Infantil", Duration.ofMinutes(105), "+5", "3D", 4);
	static Pelicula pelicula6 = new Pelicula("Jhon Wick 4", 17000, "Acción", Duration.ofMinutes(180), "+18", "3D", 4);
//	static Pelicula pelicula19 = new Pelicula("KNJ temporada 4 movie", 30000, "Aventura", Duration.ofMinutes(60), "+12", "3D", 6); 
//	static Pelicula pelicula20 = new Pelicula("Oppenheimer", 15000, "Drama", Duration.ofMinutes(120), "+18", "3D", 8); 
//	static Pelicula pelicula21 = new Pelicula("BNHA temporada 7 movie", 18000, "Acción", Duration.ofMinutes(60), "+18", "4D", 7);
//	static Pelicula pelicula22 = new Pelicula("Código Enigma", 12000, "Historia", Duration.ofMinutes(180), "+18", "3D", 8);
//	static Pelicula pelicula23 = new Pelicula("Garfield", 17000, "Infantil", Duration.ofMinutes(105), "+5", "2D", 5);
//	static Pelicula pelicula24 = new Pelicula("Jhon Wick 4", 17000, "Acción", Duration.ofMinutes(180), "+18", "2D", 5);

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
	static Ticket ticket6 = new Ticket(cliente1, pelicula2, LocalDateTime.of(2024, 4, 29, 20, 30, 00), "2-2", sucursalCine3);
	static Ticket ticket7 = new Ticket();
	static Ticket ticket8 = new Ticket();
	
	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 100000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 60000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 150000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",500000, 0);
	
	static TarjetaCinemar tarjeta1 = new TarjetaCinemar(32000,false, cliente4);
	
	public static void main(String[] args) {
		
		//Llamados métodos de instancias para hacer pruebas
		{
			
			inventarioMarinilla.add(producto1);
			inventarioMarinilla.add(producto2);
			inventarioMarinilla.add(producto3);
			inventarioMarinilla.add(producto4);
			inventarioMarinilla.add(producto5);
			inventarioMarinilla.add(producto6);
			inventarioMarinilla.add(producto7);
			inventarioMarinilla.add(producto8);
			
			sucursalCine1.setInventarioCine(inventarioBucaramanga);
			sucursalCine2.setInventarioCine(inventarioMarinilla);
			sucursalCine3.setInventarioCine(inventarioSanAndrés);
			
			SucursalCine.setFechaActual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			salaDeCine1.crearAsientosSalaDeCine();
			salaDeCine2.crearAsientosSalaDeCine();
			salaDeCine3.crearAsientosSalaDeCine();
			salaDeCine4.crearAsientosSalaDeCine();
			
			salaDeCine5.crearAsientosSalaDeCine();
			salaDeCine6.crearAsientosSalaDeCine();
			salaDeCine7.crearAsientosSalaDeCine();
			salaDeCine8.crearAsientosSalaDeCine(); 
			
			salaDeCine9.crearAsientosSalaDeCine();
			salaDeCine10.crearAsientosSalaDeCine();
			salaDeCine11.crearAsientosSalaDeCine();
			salaDeCine12.crearAsientosSalaDeCine(); 
			
			SucursalCine.añadirSalasCineSede();
			
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00));
			pelicula1.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 5, 30, 00));
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 16, 30, 00));
			pelicula2.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 19, 30, 00));
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula3.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 16, 30, 00));

			pelicula4.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula5.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 11, 00, 00));

			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 17, 30, 00));
			pelicula6.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			
			sucursalCine3.getCartelera().add(pelicula1);
			sucursalCine3.getCartelera().add(pelicula2);
			sucursalCine3.getCartelera().add(pelicula3);
			sucursalCine3.getCartelera().add(pelicula4);
			sucursalCine3.getCartelera().add(pelicula5);
			sucursalCine3.getCartelera().add(pelicula6);
//			sucursalCine3.getCartelera().add(pelicula19);

			pelicula7.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula7.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00));
			pelicula7.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 5, 30, 00));
			pelicula8.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula8.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 16, 30, 00));
			pelicula8.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 20, 30, 00));
			pelicula10.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula10.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula10.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 16, 30, 00));

			pelicula12.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula11.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 11, 00, 00));

			pelicula9.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula9.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 17, 30, 00));
			pelicula9.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			
			sucursalCine2.getCartelera().add(pelicula7);
			sucursalCine2.getCartelera().add(pelicula8);
			sucursalCine2.getCartelera().add(pelicula9);
			sucursalCine2.getCartelera().add(pelicula10);
			sucursalCine2.getCartelera().add(pelicula11);
			sucursalCine2.getCartelera().add(pelicula12);
			
			pelicula13.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula13.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00));
			pelicula13.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 5, 30, 00));
			pelicula16.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula16.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 16, 30, 00));
			pelicula16.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 20, 30, 00));
			pelicula17.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula17.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula17.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 16, 30, 00));

			pelicula14.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00));
			pelicula15.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 11, 00, 00));

			pelicula18.crearSalaVirtual(LocalDateTime.of(2024, 4, 27, 10, 00, 00));
			pelicula18.crearSalaVirtual(LocalDateTime.of(2024, 4, 28, 17, 30, 00));
			pelicula18.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			
//			pelicula19.crearSalaVirtual(LocalDateTime.of(2024, 4, 29, 21, 30, 00));
			
			sucursalCine1.getCartelera().add(pelicula13);
			sucursalCine1.getCartelera().add(pelicula14);
			sucursalCine1.getCartelera().add(pelicula15);
			sucursalCine1.getCartelera().add(pelicula16);
			sucursalCine1.getCartelera().add(pelicula17);
			sucursalCine1.getCartelera().add(pelicula18);
			
			SucursalCine.actualizarPeliculasSalasDeCine();
			
//			for (int i = 0; i < pelicula17.getHorarios().get(LocalDateTime.of(2024, 4, 28, 13, 30, 00)).length; i++) {
//				for (int j = 0; j < pelicula17.getHorarios().get(LocalDateTime.of(2024, 4, 28, 13, 30, 00)).length; j++) {
//					pelicula17.modificarSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00), i + 1, j + 1 );
//				}
//			}
			
//			pelicula17.modificarSalaVirtual(LocalDateTime.of(2024, 4, 28, 13, 30, 00), 1, 1 );
			
			ticket1.setPelicula(pelicula1);
			ticket1.asignarPrecio();
			ticket1.setSalaDeCine(salaDeCine11);
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
			ticket2.setHorario(SucursalCine.getFechaActual());
			precio = ticket2.getPrecio();
			metodoPago2.realizarPago(precio, cliente2);
			ticket2.procesarPagoRealizado(cliente2);
			ticket2.factura(cliente2);
			pelicula1.modificarSalaVirtual(LocalDateTime.of(2024, 4, 28, 2, 30, 00), 1, 4);
			
			ticket3.setPelicula(pelicula2);
			ticket3.asignarPrecio();
			
			ticket4.setPelicula(pelicula1);
			ticket4.asignarPrecio();
			
			ticket6.setSalaDeCine(ticket6.getPelicula().obtenerSalaDeCineConCodigo(sucursalCine1));
			
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
			Membresia.stockMembresia(SucursalCine.getSucursalesCine());

//			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
//			/*cliente1.setMembresia(membresia1)*/
			
			//System.out.println();
			SucursalCine.setFechaActual(SucursalCine.getFechaActual().plusMinutes(14));
			
//			//cliente1.setMembresia(membresia4);
//			MetodoPago pago = ServicioEntretenimiento.encontrarMetodoPagoCliente("Banco Agrario", cliente1);
//			//System.out.println("\n"+cliente1.getMembresia().getTipoMembresia());
//			System.out.println(pago.getNombre()+"\n"+pago.getTipo());

			
			
			//cliente4.setMembresia(membresia5);
			cliente3.setMembresia(membresia2);
			cliente4.getTickets().add(ticket5);
//			ticket5.setDueno(cliente4);
//			ticket5.setPelicula(pelicula1);
//			ticket5.setSalaDeCine(salaDeCine1);
			
			MetodoPago.asignarMetodosDePago(cliente1);
			MetodoPago.asignarMetodosDePago(cliente2);
			MetodoPago.asignarMetodosDePago(cliente3);
			MetodoPago.asignarMetodosDePago(cliente4);
			
			cliente1.getHistorialDePeliculas().add(pelicula14);
			cliente1.getHistorialDePeliculas().add(pelicula14);
			cliente1.getHistorialDePeliculas().add(pelicula14);
			cliente1.getHistorialDePeliculas().add(pelicula7);
			cliente1.getHistorialDePeliculas().add(pelicula12);
			cliente1.getHistorialDePeliculas().add(pelicula12);
			cliente1.getHistorialDePeliculas().add(pelicula1);
			cliente1.getHistorialDePeliculas().add(pelicula13);
			cliente1.getHistorialDePeliculas().add(pelicula14);
			cliente1.getHistorialDePeliculas().add(pelicula7);
			cliente1.getHistorialDePeliculas().add(pelicula7);
			cliente1.getHistorialDePeliculas().add(pelicula7);
			cliente1.getHistorialDePeliculas().add(pelicula7);
			
			
			


			
			//Print tests
			//System.out.println();
			//System.out.println();
			
			//for (TarjetaCinemar tarjeta : Arkade.getTarjetasEnInventario()) {
				//System.out.println(tarjeta.getSaldo());
			//}
			
		}
		
		//MAIN
		System.out.println("Iniciar sesión");
		Cliente clienteProceso = iniciarSesion();
		
		System.out.println("\nIngresar a una de nuestras sedes");
		SucursalCine sucursalCineProceso = ingresarASucursal();
		
		System.out.println("\nHola " + clienteProceso.getNombre() + " Bienvenido al cine de marinilla");
		inicio(clienteProceso, sucursalCineProceso);
		
	}
	
      
	
	public static void inicio(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
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
			//NumberFormatException e							aca dejo esto por si lo queres modificar de nuevo
			//opcion = (int)readLong(); //edi cambie esto ya que cuando llamaba el metodo en mi funcionalidad me saltaba el error y se me imprimia el menu 2 veces no se porque, pero asi funciona melo.
		}catch(NumberFormatException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
			//sc.nextLine(); // Consumir el input incorrecto
		    //opcion = 0; // Asignar un valor válido para evitar el bucle infinito
		}
	}while(!(opcion > 0 & opcion <= 8));
	
	
	switch (opcion) {
		case 1: Funcionalidad1.reservarTicket(clienteProceso, sucursalCineProceso);inicio(clienteProceso, sucursalCineProceso); break;
		case 2: Funcionalidad1.ingresarSalaCineDesdeMenu(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 3: Funcionalidad2.compras(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 4: calificacion(clienteProceso, sucursalCineProceso);inicio(clienteProceso, sucursalCineProceso); break;
		case 5: Funcionalidad_4.ingresoZonaJuegos(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		//case 6: Funcionalidad5.adquirirMembresia(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 7: Funcionalidad1.salaDeEspera(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 8: salirDelSistema(); break;
		default: System.out.println("Opción invalida"); inicio(clienteProceso, sucursalCineProceso);
	  }
	
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Bloque funcionalidad 1
	
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------------------
	
		
		
	//Bloque funcionalidad 2
	
	
		
//******************************************************************************************************************************************	   
	
	//Bloque funcionalidad 3
	
	static void calificacion(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
		
	}
	 
		
		
//------------------------------------------------------------------------------------------------------------------		
		
	//BLOQUE PARA LA FUNCIONALIDAD 4

//------------------------------------------------------------------------------------------------------------------		
	
			
	//------------------------------------------------------------------------------------------------------------------	
	
	static void salirDelSistema() {
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
	}
	
	public static Cliente validarCliente() {
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
	
	public static String stringMayuscula(String input) {
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
	static SucursalCine ingresarASucursal() {
		
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
	
	
	
}

