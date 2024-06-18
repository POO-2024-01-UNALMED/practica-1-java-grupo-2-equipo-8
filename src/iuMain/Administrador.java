package iuMain;

import java.util.Scanner;
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
	
	static ServicioComida servicioComidaM = new ServicioComida("comida");
	static ServicioSouvenirs servicioSouvenirsM = new ServicioSouvenirs("souvenir");
	
	static SucursalCine sucursalCine1 = new SucursalCine("Bucaramanga");
	static SucursalCine sucursalCine2 = new SucursalCine("Marinilla");
	static SucursalCine sucursalCine3 = new SucursalCine("Medellín");
	
	static Producto producto1 = new Producto("Hamburguesa","Grande","comida",25000,200,null);
	static Producto producto2 = new Producto("Hamburguesa","Cangreburger","comida",30000,200,"Comedia");
	static Producto producto3 = new Producto("Perro caliente","Grande","comida",20000,200,null);
	static Producto producto4 = new Producto("Perro caliente","Don salchicha","comida",30000,200,"Comedia");
	static Producto producto5 = new Producto("Crispetas","cazador de Demonios","comida",15000,200,"Accion");
	static Producto producto6 = new Producto("Crispetas","Grandes","comida",16000,200,null);
	static Producto producto7 = new Producto("Gaseosa","Grande","comida",6000,200,null);
	static Producto producto8 = new Producto("Gaseosa","Pequeña","comida",3000,200,null);
	
	static Producto producto1S = new Producto("Camisa","XL","souvenir",19000,200,null);
	static Producto producto2S = new Producto("Camisa","Bob Esponja","souvenir",30000,200,"Comedia");
	static Producto producto3S = new Producto("Gorra","L","souvenir",12000,200,null);
	static Producto producto4S = new Producto("Llavero","Katana","souvenir",30000,200,"Accion");
	static Producto producto5S = new Producto("Peluche","Pajaro loco","souvenir",30000,200,"comedia");
	
	static Producto productoBono = new Producto("Hamburguesa","Cangreburger","comida",30000,1,"Comedia");
	static Bono bono1 = new Bono("1234",productoBono,"comida");
	
	static Arkade game1= new Arkade("Hang Man", 15000, "Acción");
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
	
	static SalaCine salaDeCine1_1 = new SalaCine(1, "2D", sucursalCine1);
	static SalaCine salaDeCine1_2 = new SalaCine(2, "3D", sucursalCine1);
	static SalaCine salaDeCine1_3 = new SalaCine(3, "4D", sucursalCine1);
	static SalaCine salaDeCine1_4 = new SalaCine(4, "2D", sucursalCine1);
	static SalaCine salaDeCine1_5 = new SalaCine(5, "3D", sucursalCine1);
	static SalaCine salaDeCine1_6 = new SalaCine(6, "4D", sucursalCine1);
	static SalaCine salaDeCine1_7 = new SalaCine(7, "2D", sucursalCine1);
	static SalaCine salaDeCine1_8 = new SalaCine(8, "3D", sucursalCine1);
	static SalaCine salaDeCine1_9 = new SalaCine(9, "4D", sucursalCine1);

	static Pelicula pelicula1_1 = new Pelicula("Star Wars: Episode 1", 50000, "Aventura", Duration.ofMinutes(110), "+7", "4D", 3, sucursalCine1); 
	static Pelicula pelicula1_2 = new Pelicula("Star Wars: Episode 1", 27000, "Aventura", Duration.ofMinutes(110), "+7", "3D", 2, sucursalCine1); 
	static Pelicula pelicula1_3 = new Pelicula("Star Wars: Episode 1", 18000, "Aventura", Duration.ofMinutes(110), "+7", "2D", 1, sucursalCine1); 
	
	static Pelicula pelicula1_4 = new Pelicula("Misión Imposible 4", 55000, "Acción", Duration.ofMinutes(155), "+18", "4D", 6, sucursalCine1);
	static Pelicula pelicula1_5 = new Pelicula("Misión Imposible 4", 32000, "Acción", Duration.ofMinutes(155), "+18", "3D", 5, sucursalCine1);
	static Pelicula pelicula1_6 = new Pelicula("Misión Imposible 4", 13000, "Acción", Duration.ofMinutes(155), "+18", "2D", 4, sucursalCine1);
	
	static Pelicula pelicula1_7 = new Pelicula("El conjuro 3", 60000, "Terror", Duration.ofMinutes(140), "+18", "4D", 9, sucursalCine1); 
	static Pelicula pelicula1_8 = new Pelicula("El conjuro 3", 31000, "Terror", Duration.ofMinutes(140), "+18", "3D", 8, sucursalCine1); 
	static Pelicula pelicula1_9 = new Pelicula("El conjuro 3", 18000, "Terror", Duration.ofMinutes(140), "+18", "2D", 7, sucursalCine1); 
	
	static Pelicula pelicula1_10 = new Pelicula("Your name", 29000, "Romance", Duration.ofMinutes(110), "+8", "3D", 5, sucursalCine1);
	static Pelicula pelicula1_11 = new Pelicula("Your name", 18000, "Romance", Duration.ofMinutes(110), "+8", "2D", 4, sucursalCine1);
	
	static Pelicula pelicula1_12 = new Pelicula("Furiosa: A Mad Max Saga", 68000, "Ciencia ficción", Duration.ofMinutes(148), "+7", "4D", 3, sucursalCine1);
	static Pelicula pelicula1_13 = new Pelicula("Furiosa: A Mad Max Saga", 30000, "Ciencia ficción", Duration.ofMinutes(148), "+7", "3D", 2, sucursalCine1);
	static Pelicula pelicula1_14 = new Pelicula("Furiosa: A Mad Max Saga", 17000, "Ciencia ficción", Duration.ofMinutes(148), "+7", "2D", 1, sucursalCine1);
	
	static Pelicula pelicula1_15 = new Pelicula("Spy x Familiy Código: Blanco", 269000, "Infantil", Duration.ofMinutes(90), "+5", "4D", 9, sucursalCine1);
	static Pelicula pelicula1_16 = new Pelicula("Spy x Familiy Código: Blanco", 30000, "Infantil", Duration.ofMinutes(90), "+5", "3D", 8, sucursalCine1);
	static Pelicula pelicula1_17 = new Pelicula("Spy x Familiy Código: Blanco", 19000, "Infantil", Duration.ofMinutes(90), "+5", "2D", 7, sucursalCine1);
	
	static SalaCine salaDeCine2_1 = new SalaCine(1, "2D", sucursalCine2);
	static SalaCine salaDeCine2_2 = new SalaCine(2, "3D", sucursalCine2);
	static SalaCine salaDeCine2_3 = new SalaCine(3, "4D", sucursalCine2);
	static SalaCine salaDeCine2_4 = new SalaCine(4, "2D", sucursalCine2);
	static SalaCine salaDeCine2_5 = new SalaCine(5, "3D", sucursalCine2);
	static SalaCine salaDeCine2_6 = new SalaCine(6, "4D", sucursalCine2);
	
	static Pelicula pelicula2_1 = new Pelicula("Jujutsu Kaisen Cero", 60000, "Acción", Duration.ofMinutes(60), "+12", "4D", 3, sucursalCine2); 
	static Pelicula pelicula2_2 = new Pelicula("Jujutsu Kaisen Cero", 32000, "Acción", Duration.ofMinutes(60), "+12", "3D", 2, sucursalCine2); 
	static Pelicula pelicula2_3 = new Pelicula("Jujutsu Kaisen Cero", 17000, "Acción", Duration.ofMinutes(60), "+12", "2D", 1, sucursalCine2); 
	
	static Pelicula pelicula2_4 = new Pelicula("The Strangers: Chapter 1", 65000, "Terror", Duration.ofMinutes(114), "+18", "4D", 6, sucursalCine2);
	static Pelicula pelicula2_5 = new Pelicula("The Strangers: Chapter 1", 37000, "Terror", Duration.ofMinutes(114), "+18", "3D", 5, sucursalCine2);
	static Pelicula pelicula2_6 = new Pelicula("The Strangers: Chapter 1", 20000, "Terror", Duration.ofMinutes(114), "+18", "2D", 4, sucursalCine2);
	
	static Pelicula pelicula2_7 = new Pelicula("El pájaro loco", 55000, "Infantil", Duration.ofMinutes(120), "+5", "4D", 3, sucursalCine2); 
	static Pelicula pelicula2_8 = new Pelicula("El pájaro loco", 27000, "Infantil", Duration.ofMinutes(120), "+5", "3D", 2, sucursalCine2); 
	static Pelicula pelicula2_9 = new Pelicula("El pájaro loco", 15000, "Infantil", Duration.ofMinutes(120), "+5", "2D", 1, sucursalCine2); 
	
	static Pelicula pelicula2_10 = new Pelicula("One Life", 27000, "Historia", Duration.ofMinutes(110), "+8", "3D", 5, sucursalCine2);
	static Pelicula pelicula2_11 = new Pelicula("One Life", 19000, "Historia", Duration.ofMinutes(110), "+8", "2D", 4, sucursalCine2);
	
	static Pelicula pelicula2_12 = new Pelicula("Challengers", 25000, "Drama", Duration.ofMinutes(132), "+12", "3D", 2, sucursalCine2);
	static Pelicula pelicula2_13 = new Pelicula("Challengers", 15000, "Drama", Duration.ofMinutes(132), "+12", "2D", 1, sucursalCine2);

	static Pelicula pelicula2_14 = new Pelicula("Bad Boys: Hasta la muerte", 77000, "Acción", Duration.ofMinutes(109), "+18", "4D", 6, sucursalCine2);
	static Pelicula pelicula2_15 = new Pelicula("Bad Boys: Hasta la muerte", 31000, "Acción", Duration.ofMinutes(109), "+18", "3D", 5, sucursalCine2);
	static Pelicula pelicula2_16 = new Pelicula("Bad Boys: Hasta la muerte", 17000, "Acción", Duration.ofMinutes(109), "+18", "2D", 4, sucursalCine2);
	
	static SalaCine salaDeCine3_1 = new SalaCine(1, "2D", sucursalCine3);
	static SalaCine salaDeCine3_2 = new SalaCine(2, "3D", sucursalCine3);
	static SalaCine salaDeCine3_3 = new SalaCine(3, "4D", sucursalCine3);
	static SalaCine salaDeCine3_4 = new SalaCine(4, "2D", sucursalCine3);
	static SalaCine salaDeCine3_5 = new SalaCine(5, "3D", sucursalCine3);
	static SalaCine salaDeCine3_6 = new SalaCine(6, "4D", sucursalCine3);

	static Pelicula pelicula3_1 = new Pelicula("KNJ temporada 4 movie", 60000, "Aventura", Duration.ofMinutes(60), "+12", "4D", 3, sucursalCine3); 
	static Pelicula pelicula3_2 = new Pelicula("KNJ temporada 4 movie", 25000, "Aventura", Duration.ofMinutes(60), "+12", "3D", 2, sucursalCine3); 
	static Pelicula pelicula3_3 = new Pelicula("KNJ temporada 4 movie", 15000, "Aventura", Duration.ofMinutes(60), "+12", "2D", 1, sucursalCine3); 
	
	static Pelicula pelicula3_4 = new Pelicula("Código Enigma", 27000, "Historia", Duration.ofMinutes(180), "+18", "3D", 5, sucursalCine3);
	static Pelicula pelicula3_5 = new Pelicula("Código Enigma", 17000, "Historia", Duration.ofMinutes(180), "+18", "2D", 4, sucursalCine3);
	
	static Pelicula pelicula3_6 = new Pelicula("Oppenheimer", 25000, "Historia", Duration.ofMinutes(120), "+18", "3D", 2, sucursalCine3); 
	static Pelicula pelicula3_7 = new Pelicula("Oppenheimer", 15000, "Historia", Duration.ofMinutes(120), "+18", "2D", 1, sucursalCine3); 
	
	static Pelicula pelicula3_8 = new Pelicula("Jhon Wick 4", 65000, "Acción", Duration.ofMinutes(180), "+18", "4D", 6, sucursalCine3);
	static Pelicula pelicula3_9 = new Pelicula("Jhon Wick 4", 31000, "Acción", Duration.ofMinutes(180), "+18", "3D", 5, sucursalCine3);
	static Pelicula pelicula3_10 = new Pelicula("Jhon Wick 4", 17000, "Acción", Duration.ofMinutes(180), "+18", "2D", 4, sucursalCine3);
	
	static Pelicula pelicula3_11 = new Pelicula("Intensamente 2", 57000, "Infantil", Duration.ofMinutes(105), "+5", "4D", 3, sucursalCine3);
	static Pelicula pelicula3_12 = new Pelicula("Intensamente 2", 27000, "Infantil", Duration.ofMinutes(105), "+5", "3D", 2, sucursalCine3);
	static Pelicula pelicula3_13 = new Pelicula("Intensamente 2", 15000, "Infantil", Duration.ofMinutes(105), "+5", "2D", 1, sucursalCine3);
	
	static Pelicula pelicula3_14 = new Pelicula("BNHA temporada 7 movie", 58000, "Acción", Duration.ofMinutes(60), "+18", "4D", 6, sucursalCine3);
	static Pelicula pelicula3_15 = new Pelicula("BNHA temporada 7 movie", 30000, "Acción", Duration.ofMinutes(60), "+18", "3D", 5, sucursalCine3); 
	static Pelicula pelicula3_16 = new Pelicula("BNHA temporada 7 movie", 12000, "Acción", Duration.ofMinutes(60), "+18", "2D", 4, sucursalCine3);

	static Membresia membresia1 = new Membresia("Básico", 1, 5000, 10);
	static Membresia membresia2 = new Membresia("Heróico", 2, 10000, 15);
	static Membresia membresia3 = new Membresia("Global", 3, 15000, 20);
	static Membresia membresia4 = new Membresia("Challenger", 4, 25000, 25);
	static Membresia membresia5 = new Membresia("Radiante", 5, 30000, 30);
	
	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 200000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 120000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 300000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",5000000, 0);
	
	static TarjetaCinemar tarjeta1 = new TarjetaCinemar(32000, false, cliente4);
	
	public static void main(String[] args) {
		
		//Llamados métodos de instancias para hacer pruebas
		{
			
			sucursalCine1.getServicios().add(servicioComidaM);
			
			sucursalCine1.getInventarioCine().add(producto1);
			sucursalCine1.getInventarioCine().add(producto2);
			sucursalCine1.getInventarioCine().add(producto3);
			sucursalCine1.getInventarioCine().add(producto4);
			sucursalCine1.getInventarioCine().add(producto5);
			sucursalCine1.getInventarioCine().add(producto6);
			sucursalCine1.getInventarioCine().add(producto7);
			sucursalCine1.getInventarioCine().add(producto8);
			
			
			sucursalCine2.getServicios().add(servicioComidaM);
			sucursalCine2.getServicios().add(servicioSouvenirsM);
			
			sucursalCine2.getInventarioCine().add(producto1);
			sucursalCine2.getInventarioCine().add(producto2);
			sucursalCine2.getInventarioCine().add(producto3);
			sucursalCine2.getInventarioCine().add(producto4);
			sucursalCine2.getInventarioCine().add(producto5);
			sucursalCine2.getInventarioCine().add(producto6);
			sucursalCine2.getInventarioCine().add(producto7);
			sucursalCine2.getInventarioCine().add(producto8);
			
			sucursalCine2.getInventarioCine().add(producto1S);
			sucursalCine2.getInventarioCine().add(producto2S);
			sucursalCine2.getInventarioCine().add(producto3S);
			sucursalCine2.getInventarioCine().add(producto4S);
			sucursalCine2.getInventarioCine().add(producto5S);
			
			sucursalCine3.getServicios().add(servicioSouvenirsM);
			
			sucursalCine3.getInventarioCine().add(producto1S);
			sucursalCine3.getInventarioCine().add(producto2S);
			sucursalCine3.getInventarioCine().add(producto3S);
			sucursalCine3.getInventarioCine().add(producto4S);
			sucursalCine3.getInventarioCine().add(producto5S);
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//Funcionalidad 1 
			SucursalCine.crearHorariosPeliculasPorSucursal();
			SucursalCine.actualizarPeliculasSalasDeCine();
			
			//Prueba películas recomendadas
//			cliente1.getHistorialDePeliculas().add(pelicula3_10);
//			cliente1.getHistorialDePeliculas().add(pelicula3_11);
//			cliente1.getHistorialDePeliculas().add(pelicula3_12);
//			cliente1.getHistorialDePeliculas().add(pelicula2_16);
//			cliente1.getHistorialDePeliculas().add(pelicula2_4);
//			cliente1.getHistorialDePeliculas().add(pelicula1_1);
//			cliente1.getHistorialDePeliculas().add(pelicula2_15);
//			cliente1.getHistorialDePeliculas().add(pelicula2_1);
//			cliente1.getHistorialDePeliculas().add(pelicula1_2);
//			cliente1.getHistorialDePeliculas().add(pelicula3_1);
//			cliente1.getHistorialDePeliculas().add(pelicula3_4);
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			cliente4.setCuenta(tarjeta1);
			Membresia.asignarTipoMembresia();
//			for (MetodoPago metodoPago : MetodoPago.getMetodosDePagoDisponibles()) {
//				MetodoPago.metodoPagoPorTipo(metodoPago);
//			}
			MetodoPago.metodoPagoPorTipo(metodoPago1);
			MetodoPago.metodoPagoPorTipo(metodoPago2);
			MetodoPago.metodoPagoPorTipo(metodoPago3);
			MetodoPago.metodoPagoPorTipo(metodoPago4);

//			cliente1.setMembresia(membresia1);

			cliente1.setMembresia(membresia5);
			Membresia.stockMembresia(SucursalCine.getSucursalesCine());

//			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
//			/*cliente1.setMembresia(membresia1)*/
			
			//System.out.println();
			
			
//			//cliente1.setMembresia(membresia4);
//			MetodoPago pago = ServicioEntretenimiento.encontrarMetodoPagoCliente("Banco Agrario", cliente1);
//			//System.out.println("\n"+cliente1.getMembresia().getTipoMembresia());
//			System.out.println(pago.getNombre()+"\n"+pago.getTipo());

			
			
			//cliente4.setMembresia(membresia5);
			cliente3.setMembresia(membresia2);
//			ticket5.setDueno(cliente4);
//			ticket5.setPelicula(pelicula1);
//			ticket5.setSalaDeCine(salaDeCine1);
			
			MetodoPago.asignarMetodosDePago(cliente1);
			MetodoPago.asignarMetodosDePago(cliente2);
			MetodoPago.asignarMetodosDePago(cliente3);
			MetodoPago.asignarMetodosDePago(cliente4);
			

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Print tests
			System.out.println();
			System.out.println();
			
			//for (TarjetaCinemar tarjeta : Arkade.getTarjetasEnInventario()) {
				//System.out.println(tarjeta.getSaldo());
			//}
			
		}
		
		//MAIN
		System.out.println("Iniciar sesión");
		Cliente clienteProceso = iniciarSesion();
		
		System.out.println("\nIngresar a una de nuestras sedes");
		SucursalCine sucursalCineProceso = ingresarASucursal();
		clienteProceso.setCine(sucursalCineProceso);
		
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
			System.out.println("7. Hacer calificacion");
			System.out.println("8. Ingresar a sala de espera");
			System.out.println("9. Salir");
			opcion = Integer.parseInt(sc.nextLine());
			//NumberFormatException e							aca dejo esto por si lo queres modificar de nuevo
			//opcion = (int)readLong(); //edi cambie esto ya que cuando llamaba el metodo en mi funcionalidad me saltaba el error y se me imprimia el menu 2 veces no se porque, pero asi funciona melo.
		}catch(NumberFormatException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
			//sc.nextLine(); // Consumir el input incorrecto
		    //opcion = 0; // Asignar un valor válido para evitar el bucle infinito
		}
	}while(!(opcion > 0 & opcion <= 9));
	
	
	switch (opcion) {
		case 1: Funcionalidad1.reservarTicket(clienteProceso, sucursalCineProceso);inicio(clienteProceso, sucursalCineProceso); break;
		case 2: Funcionalidad1.ingresarSalaCineDesdeMenu(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 3: Funcionalidad2.compras(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		//case 4: 
		case 5: Funcionalidad_4.ingresoZonaJuegos(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		//case 6: Funcionalidad5.adquirirMembresia(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		//case 7: Funcionalidad3.calificacion(clienteProceso, sucursalCineProceso);inicio(clienteProceso, sucursalCineProceso); break;
		case 8: Funcionalidad1.salaDeEspera(clienteProceso, sucursalCineProceso); inicio(clienteProceso, sucursalCineProceso); break;
		case 9: salirDelSistema(); break;
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
	
	//Bloque funcionalidad 5
	
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
	
	/**
	 * Description : Este método se encarga de evaluar si la hora a la que accede el usuario se encuentra en nuestro horario de atención,
	 * En caso de de que no, se pregunta si desea ingresar el día de mañana a las 10am, en caso de que si, se actualiza la fecha actual,
	 * en caso de que no, finaliza el programa.
	 * @param cliente : Este método recibe un cliente (De tipo Cliente) el cual ejecuta el menú de inicio
	 * @return <b>void</b> : Este método no retorna nada.
	 * */
	static void evaluarRestriccionHoraria(Cliente cliente) {
		if ( !(SucursalCine.getFechaActual().toLocalTime().isBefore(SucursalCine.FIN_HORARIO_LABORAL) &&
				(SucursalCine.getFechaActual().toLocalTime().isAfter(SucursalCine.INICIO_HORARIO_LABORAL) ||
						SucursalCine.getFechaActual().toLocalTime().equals(SucursalCine.INICIO_HORARIO_LABORAL)) ) ) {
			
			System.out.println("\nActualmente son las: " + SucursalCine.getFechaActual().toLocalTime() + 
			", \nnuestro horario de atención de este servicio es desde las " + SucursalCine.INICIO_HORARIO_LABORAL +
			", hasta las " + SucursalCine.FIN_HORARIO_LABORAL + " todos los días.");
			
			int opcionMenu;
			do {
				opcionMenu = 0;
				try {
					System.out.println("¿Desea ingresar el día de mañana a las " + SucursalCine.INICIO_HORARIO_LABORAL + " ?\n1.Si\n2.No");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Error, Debe ingresar un único dato numérico entre los disponibles");
				}
			}while(!(opcionMenu == 1 || opcionMenu == 2));
			
			if (opcionMenu == 1) {
				SucursalCine.setFechaActual(SucursalCine.getFechaActual().plusDays(1).withHour(10));
				SucursalCine.actualizarPeliculasSalasDeCine();
				System.out.println("La fecha actual se ha actualizado de forma exitosa (" + SucursalCine.getFechaActual() + ")");
			}else {
				salirDelSistema();
				//Cambiar por menú de inicio
			}
		}
	}
	
	
}

