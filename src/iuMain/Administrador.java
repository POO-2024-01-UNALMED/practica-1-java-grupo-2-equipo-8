package iuMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
import baseDatos.Serializador;
import java.time.Duration;
import java.time.LocalDateTime;

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
	
	//Productos de la sucursal de Bucaramanga
	static Producto producto1M = new Producto("Hamburguesa","Grande","comida",25000,200,"Normal");
	static Producto producto2M = new Producto("Hamburguesa","Deadpool","comida",30000,200,"Comedia");
	static Producto producto3M = new Producto("Perro caliente","Grande","comida",20000,200,"Normal");
	static Producto producto4M = new Producto("Perro caliente","Bolt","comida",30000,200,"Comedia");
	static Producto producto5M = new Producto("Crispetas","Muerte","comida",15000,200,"Acción");
	static Producto producto6M = new Producto("Crispetas","Grandes","comida",16000,200,"Normal");
	static Producto producto7M = new Producto("Gaseosa","Grande","comida",6000,200,"Normal");
	static Producto producto8M = new Producto("Gaseosa","Pequeña","comida",3000,200,"Normal");
	
	//Productos de la sucursal de Marinilla
	static Producto producto1 = new Producto("Hamburguesa","Grande","comida",20000,200,"Normal");
	static Producto producto2 = new Producto("Hamburguesa","Cangreburger","comida",25000,200,"Comedia");
	static Producto producto3 = new Producto("Perro caliente","Grande","comida",15000,200,"Normal");
	static Producto producto4 = new Producto("Perro caliente","Don salchicha","comida",20000,200,"Comedia");
	static Producto producto5 = new Producto("Crispetas","cazador de Demonios","comida",14000,200,"Acción");
	static Producto producto6 = new Producto("Crispetas","Grandes","comida",13000,200,"Normal");
	static Producto producto7 = new Producto("Gaseosa","Grande","comida",4000,200,"Normal");
	static Producto producto8 = new Producto("Gaseosa","Pequeña","comida",2000,200,"Normal");
	
	static Producto producto1S = new Producto("Camisa","XL","souvenir",16000,200,"Normal");
	static Producto producto2S = new Producto("Camisa","Bob Esponja","souvenir",27000,200,"Comedia");
	static Producto producto3S = new Producto("Gorra","L","souvenir",11000,200,"Normal");
	static Producto producto4S = new Producto("Llavero","Katana","souvenir",22000,200,"Acción");
	static Producto producto5S = new Producto("Peluche","Pajaro loco","souvenir",29000,200,"Comedia");
	
	//Productos de la sucursal de Medellin
	static Producto producto1SM = new Producto("Camisa","XL","souvenir",19000,200,"Normal");
	static Producto producto2SM = new Producto("Camisa","Escuadron suicida","souvenir",30000,200,"Comedia");
	static Producto producto3SM = new Producto("Gorra","L","souvenir",12000,200,"Normal");
	static Producto producto4SM = new Producto("Llavero","Emociones","souvenir",30000,200,"Acción");
	static Producto producto5SM = new Producto("Peluche","Deku","souvenir",30000,200,"Comedia");
	
	//Clientes prueba
	static Cliente cliente1 = new Cliente("Andy", 18, 13434, TipoDeDocumento.CC);
	static Cliente cliente2 = new Cliente("Isa", 15, 4254543, TipoDeDocumento.TI);
	static Cliente cliente3 = new Cliente("Samu", 18, 646453523, TipoDeDocumento.CC);
	static Cliente cliente5= new Cliente("Santiago",18,1125274009,TipoDeDocumento.CC);
	static Cliente cliente4 = new Cliente("Juanjo", 18 ,987, TipoDeDocumento.CC);
	
	//Bonos de prueba
	static Producto productoBono = new Producto("Hamburguesa","Cangreburger","comida",30000,1,"Comedia");
	static Bono bono1 = new Bono("1234",productoBono,"comida", cliente2);
	static Producto productoBono2 = new Producto("Hamburguesa","Cangreburger","comida",30000,1,"Comedia");
	static Bono bono2 = new Bono("4321",productoBono,"comida", cliente1);
	
	static Arkade game1= new Arkade("Hang Man", 15000, "Acción");
	static Arkade game2= new Arkade("Hang Man", 20000, "Terror");
	static Arkade game3= new Arkade("Hang Man", 10000, "Tecnología");
	static Arkade game4= new Arkade("Hang Man", 30000, "Comedia");
	static Arkade game5= new Arkade("Hang Man", 7500, "Drama");
	
	
	static SalaCine salaDeCine1_1 = new SalaCine(1, "2D", sucursalCine1);
	static SalaCine salaDeCine1_2 = new SalaCine(2, "3D", sucursalCine1);
	static SalaCine salaDeCine1_3 = new SalaCine(3, "4D", sucursalCine1);
	static SalaCine salaDeCine1_4 = new SalaCine(4, "2D", sucursalCine1);
	static SalaCine salaDeCine1_5 = new SalaCine(5, "3D", sucursalCine1);
	static SalaCine salaDeCine1_6 = new SalaCine(6, "4D", sucursalCine1);
	static SalaCine salaDeCine1_7 = new SalaCine(7, "2D", sucursalCine1);
	static SalaCine salaDeCine1_8 = new SalaCine(8, "3D", sucursalCine1);
	static SalaCine salaDeCine1_9 = new SalaCine(9, "4D", sucursalCine1);

	static Pelicula pelicula1_1 = new Pelicula("Deadpool 3", 18000, "Comedia", Duration.ofMinutes(110), "+18", "2D", sucursalCine1);
	
	static Pelicula pelicula1_2 = new Pelicula("Misión Imposible 4", 13000, "Acción", Duration.ofMinutes(155), "+18", "2D", sucursalCine1);
	
	static Pelicula pelicula1_3 = new Pelicula("El conjuro 3", 18000, "Terror", Duration.ofMinutes(140), "+18", "2D", sucursalCine1);
	
	static Pelicula pelicula1_4 = new Pelicula("Your name", 18000, "Romance", Duration.ofMinutes(110), "+8", "2D", sucursalCine1);
	
	static Pelicula pelicula1_5 = new Pelicula("Furiosa: A Mad Max Saga", 17000, "Ciencia ficción", Duration.ofMinutes(148), "+7", "2D", sucursalCine1);
	
	static Pelicula pelicula1_6 = new Pelicula("Spy x Familiy Código: Blanco", 19000, "Infantil", Duration.ofMinutes(90), "+5", "2D", sucursalCine1);
	
	static SalaCine salaDeCine2_1 = new SalaCine(1, "2D", sucursalCine2);
	static SalaCine salaDeCine2_2 = new SalaCine(2, "3D", sucursalCine2);
	static SalaCine salaDeCine2_3 = new SalaCine(3, "4D", sucursalCine2);
	static SalaCine salaDeCine2_4 = new SalaCine(4, "2D", sucursalCine2);
	static SalaCine salaDeCine2_5 = new SalaCine(5, "3D", sucursalCine2);
	static SalaCine salaDeCine2_6 = new SalaCine(6, "4D", sucursalCine2);
	
	static Pelicula pelicula2_1 = new Pelicula("Jujutsu Kaisen Cero", 17000, "Acción", Duration.ofMinutes(90), "+12", "2D", sucursalCine2); 
	
	static Pelicula pelicula2_4 = new Pelicula("The Strangers: Chapter 1", 20000, "Terror", Duration.ofMinutes(114), "+18", "2D", sucursalCine2);
	
	static Pelicula pelicula2_7 = new Pelicula("El pájaro loco", 15000, "Infantil", Duration.ofMinutes(120), "+5", "2D", sucursalCine2); 
	
	static Pelicula pelicula2_10 = new Pelicula("One Life", 19000, "Historia", Duration.ofMinutes(110), "+8", "2D", sucursalCine2);
	
	static Pelicula pelicula2_12 = new Pelicula("Challengers", 15000, "Drama", Duration.ofMinutes(132), "+12", "2D", sucursalCine2);

	static Pelicula pelicula2_14 = new Pelicula("Bad Boys: Hasta la muerte", 17000, "Comedia", Duration.ofMinutes(109), "+18", "2D", sucursalCine2);
	
	static SalaCine salaDeCine3_1 = new SalaCine(1, "2D", sucursalCine3);
	static SalaCine salaDeCine3_2 = new SalaCine(2, "3D", sucursalCine3);
	static SalaCine salaDeCine3_3 = new SalaCine(3, "4D", sucursalCine3);
	static SalaCine salaDeCine3_4 = new SalaCine(4, "2D", sucursalCine3);
	static SalaCine salaDeCine3_5 = new SalaCine(5, "3D", sucursalCine3);
	static SalaCine salaDeCine3_6 = new SalaCine(6, "4D", sucursalCine3);

	static Pelicula pelicula3_1 = new Pelicula("El Paseo 9", 15000, "Comedia", Duration.ofMinutes(60), "+12", "2D", sucursalCine3); 
	
	static Pelicula pelicula3_2 = new Pelicula("Código Enigma", 17000, "Historia", Duration.ofMinutes(180), "+18", "2D", sucursalCine3);
	
	static Pelicula pelicula3_3 = new Pelicula("Oppenheimer", 15000, "Historia", Duration.ofMinutes(120), "+18", "2D", sucursalCine3);
	
	static Pelicula pelicula3_4 = new Pelicula("Jhon Wick 4", 17000, "Acción", Duration.ofMinutes(180), "+18", "2D", sucursalCine3);
	
	static Pelicula pelicula3_5 = new Pelicula("Intensamente 2", 15000, "Infantil", Duration.ofMinutes(105), "+5", "2D", sucursalCine3);
	
	static Pelicula pelicula3_6 = new Pelicula("BNHA temporada 7 movie", 12000, "Acción", Duration.ofMinutes(60), "+18", "2D", sucursalCine3);

	static Membresia membresia1 = new Membresia("Básico", 1, 5000, 10);
	static Membresia membresia2 = new Membresia("Heróico", 2, 10000, 15);
	static Membresia membresia3 = new Membresia("Global", 3, 15000, 20);
	static Membresia membresia4 = new Membresia("Challenger", 4, 25000, 25);
	static Membresia membresia5 = new Membresia("Radiante", 5, 30000, 30);
	
	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 200000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 120000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 300000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",5000000, 0);
	
	//static TarjetaCinemar tarjeta1 = new TarjetaCinemar(32000,cliente4);
	static TarjetaCinemar tarjeta1 = new TarjetaCinemar();static TarjetaCinemar tarjeta2 = new TarjetaCinemar();static TarjetaCinemar tarjeta3 = new TarjetaCinemar();	
	static TarjetaCinemar tarjeta4 = new TarjetaCinemar();static TarjetaCinemar tarjeta5 = new TarjetaCinemar();static TarjetaCinemar tarjeta6 = new TarjetaCinemar();
	static TarjetaCinemar tarjeta7 = new TarjetaCinemar();static TarjetaCinemar tarjeta8 = new TarjetaCinemar();static TarjetaCinemar tarjeta9 = new TarjetaCinemar();
	static TarjetaCinemar tarjeta10 = new TarjetaCinemar();static TarjetaCinemar tarjeta11 = new TarjetaCinemar();static TarjetaCinemar tarjeta12 = new TarjetaCinemar();
	static TarjetaCinemar tarjeta13 = new TarjetaCinemar();static TarjetaCinemar tarjeta14 = new TarjetaCinemar();static TarjetaCinemar tarjeta15 = new TarjetaCinemar();	
	
	
	public static void main(String[] args) {
		
		//Llamados métodos de instancias para hacer pruebas
		{
			
			sucursalCine1.getServicios().add(servicioComidaM);
			
			sucursalCine1.getInventarioCine().add(producto1M);
			sucursalCine1.getInventarioCine().add(producto2M);
			sucursalCine1.getInventarioCine().add(producto3M);
			sucursalCine1.getInventarioCine().add(producto4M);
			sucursalCine1.getInventarioCine().add(producto5M);
			sucursalCine1.getInventarioCine().add(producto6M);
			sucursalCine1.getInventarioCine().add(producto7M);
			sucursalCine1.getInventarioCine().add(producto8M);
			
			
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
			
			sucursalCine3.getInventarioCine().add(producto1SM);
			sucursalCine3.getInventarioCine().add(producto2SM);
			sucursalCine3.getInventarioCine().add(producto3SM);
			sucursalCine3.getInventarioCine().add(producto4SM);
			sucursalCine3.getInventarioCine().add(producto5SM);
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
			//Funcionalidad 1 
			SucursalCine.logicaSistemaReservarTicket();
			
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
			cliente5.getPeliculasDisponiblesParaCalificar().add(pelicula3_1);
			cliente5.getPeliculasDisponiblesParaCalificar().add(pelicula3_2);
			cliente5.getPeliculasDisponiblesParaCalificar().add(pelicula1_2);
			cliente5.getHistorialDePedidos().add(producto1);
			cliente5.getHistorialDePedidos().add(producto2);
			cliente5.getValoracionesPeliculas().add(pelicula1_3);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//cliente4.setCuenta(tarjeta1);
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
			MetodoPago.asignarMetodosDePago(cliente5);
			
			sucursalCine1.getInventarioTarjetasCinemar().addAll(Arrays.asList(tarjeta1, tarjeta2,tarjeta3,tarjeta4,tarjeta5));
			sucursalCine2.getInventarioTarjetasCinemar().addAll(Arrays.asList(tarjeta6, tarjeta7,tarjeta8,tarjeta9,tarjeta10));
			sucursalCine3.getInventarioTarjetasCinemar().addAll(Arrays.asList(tarjeta11, tarjeta12,tarjeta13,tarjeta14,tarjeta15));
			//System.out.println(Arkade.getTarjetasEnInventario().size());
			
			
			//for (TarjetaCinemar tarjeta : Arkade.getTarjetasEnInventario()) {
			//System.out.println(tarjeta.getSaldo());
		//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Print tests
			System.out.println();
			System.out.println();
			
		}
		
		//MAIN
		System.out.println("Iniciar sesión");
		Cliente clienteProceso = iniciarSesion();
		
		System.out.println("\nIngresar a una de nuestras sedes");
		clienteProceso.setCineActual(ingresarASucursal());
		
		Thread hiloLogicaProyecto = new Thread(clienteProceso.getCineActual());
		hiloLogicaProyecto.start();
		
		System.out.println("\nHola " + clienteProceso.getNombre() + " Bienvenido a Cinemar");
		inicio(clienteProceso);
		
		hiloLogicaProyecto.interrupt();
		salirDelSistema();
		
		
		
	}
	
      
	
	public static void inicio(Cliente clienteProceso) {
	int opcion = 0;
	do {
		
		try {
			
			opcion = 0;
			System.out.println("\n¿Qué operacion desea realizar?");
			System.out.println("1. Ingresar a sistema de proyecciones de películas");
			System.out.println("2. Ingrear a los servicios de compra"); 
			System.out.println("3. Ingresar a la zona de juegos");
			System.out.println("4. Adquirir o actualizar membresia");
			System.out.println("5. Hacer calificacion");
			System.out.println("6. Cambiar de sucursal");
			System.out.println("7. Salir");
			opcion = Integer.parseInt(sc.nextLine());
			
		}catch(NumberFormatException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
		}
		
	}while(!(opcion > 0 & opcion <= 7));
	
	
	switch (opcion) {
		case 1: ingresarASistemaDeProyecciones(clienteProceso); break;
		case 2: Funcionalidad2.compras(clienteProceso); inicio(clienteProceso); break;
		case 3: Funcionalidad_4.ingresoZonaJuegos(clienteProceso); inicio(clienteProceso); break;
		case 4: Funcionalidad5.adquirirMembresia(clienteProceso); inicio(clienteProceso); break;
		case 5: Funcionalidad3.calificacion(clienteProceso);inicio(clienteProceso); break;
		case 6: cambiarSucursalCine(clienteProceso); inicio(clienteProceso); break;
		case 7: /*salirDelSistema(); Necesito cerrar el hilo*/ break;
		default: System.out.println("Opción invalida"); inicio(clienteProceso);
	  }
	
	}
	
	static void salirDelSistema() {
		//Serialización
		
//		//Atributos estáticos
//		Serializador.serializar();
//		
//		//Atributos de instancia
//		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
//			Serializador.serializar(sede);
//		}
		
		//Fin del programa
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
	 * Description : Este método se encarga de cambiar la sucursal en la cual se encuentra el cliente
	 * @param clienteProceso : Este método recibe al cliente (De tipo cliente) que desea cambiar de sucursal
	 * */
	static void cambiarSucursalCine(Cliente clienteProceso) {
		
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
	
	/**
	 * Description: Este método se encarga mostrar en pantalla los procesos de la funcionalidad 1, para que el cliente elija uno de ellos, 
	 * una vez termine su interacción, el cliente regresará a este mismo menú, en caso de que quiera regresar al menú principal termina el
	 * ciclo y se ejecuta el menú inicial.
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo cliente) que realizará algún proceso
	 * del sistema de proyeciones.
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
	 * le pedimos al cliente que seleccione el asiento, se valida su disponibilidad, se realiza el proceso de pago y se asigna el ticket al cliente)
	 * (Para este proceso cuenta con un tiempo límite de 20 minutos).
	 * 
	 * 2. En caso de que haya decidido no comprar en ese horario o directamente la película no estaba en presentación, mostramos los horarios 
	 * de la película, el usuario selecciona uno de ellos y realizamos el proceso de reserva del ticket (Mostramos los asientos de la sala de 
	 * cine virtual asociada al horario previamente seleccionado, el cliente selecciona el asiento deseado, se valida su disponibilidad, 
	 * se realiza el proceso de pago y se asigna el ticket al cliente).
	 * 
	 * @param clienteProceso : Este método recibe como parámetro el cliente (De tipo cliente) que desea realizar la reserva de un ticket.
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
			
			//En caso de que el cliente elija volver al menú principal
			if (opcionMenu == 2) {
				break;
			}
			
			//Funcionalidad reserva de ticket
			
			//Mostramos una cartelera personalizada de acuerdo a la edad del cliente, si la película tiene horarios disponibles o se encuentra en presentación
			ArrayList<Pelicula> carteleraPersonalizadaProceso = Pelicula.filtrarCarteleraPorCliente(clienteProceso, clienteProceso.getCineActual());
			
			//Verificamos si el cliente tiene acceso para al menos una película
			if (carteleraPersonalizadaProceso.size() == 0) {
				System.out.println("No hay películas disponibles para reservar (Redireccionando al menú principal...)");
				Administrador.inicio(clienteProceso);
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
	static LocalDateTime seleccionarHorarioPelicula(Cliente clienteProceso, Pelicula peliculaProceso, ArrayList<LocalDateTime> horariosPeliculaProceso) {
		
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
	static String seleccionarAsiento(Cliente clienteProceso, LocalDateTime horarioProceso, Pelicula peliculaProceso) {
		
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
	static String seleccionarAsiento(SalaCine salaDeCinePresentacionProceso, Pelicula peliculaProceso) {
		
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
					default: System.out.println("\nOpción invalida");
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
			
			//Verificamos si el ticket no ha caducado
			if (ticketParaUsar.getHorario().isBefore(SucursalCine.getFechaActual())) {
				System.out.println("\nEl ticket seleccionado no puede ser usado, debido a que ha caducado o su película se encuentra en presentación\n");
				continue;
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
	static String disponibilidadHorariaFuncionalidad1(ArrayList<LocalDateTime> horariosPeliculaProceso) {
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
	 * 1. El horario de la película en presentación de la sala de cine más 20 minutos (15 minutos de la posibilidad de compra y 5 minutos 
	 * para realizar el proceso) es anterior a la fecha actual.
	 * 2. La película seleccionada por el usuario durante el proceso corresponde a la película que se encuentra en presentación.
	 * @param salaDeCineProceso : Este método recibe como parámetro la sala de cine (De tipo SalaCine) donde está siendo presentada la película 
	 * seleccionada por el cliente.
	 * @param peliculaProceso : Este método recibe como parámetro la pelicula (De tipo Pelicula) seleccionada por el usuario durante el proceso
	 * de reserva de ticket.
	 * @return <b>boolean</b> : Este método retorna el estado de la validación para tomar determinadas acciones respecto a esta.
	 * */
	static boolean verificarIntegridadHorarioSeleccionado(SalaCine salaDeCineProceso, Pelicula peliculaProceso) {
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
	static boolean verificarIntegridadHorarioSeleccionado(Pelicula peliculaProceso, LocalDateTime horarioProceso) {
		
		if (peliculaProceso.getHorarios().contains(horarioProceso)) {
			return true;
		}
		
		return false;
	}
	
	//4. Serializar (En proceso)
	//5. Hacer test de correcta serialización de la funcionalidad 1.
	//6. Empezar el Google Document con el manual de usuario y la documentación
	
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
	
	
}

