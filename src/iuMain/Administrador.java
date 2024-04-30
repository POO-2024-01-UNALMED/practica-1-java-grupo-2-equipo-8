package iuMain;
import java.util.ArrayList;
import java.util.Scanner;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
import gestionAplicacion.usuario.Membresia;

public class Administrador {
	
	static Scanner sc = new Scanner(System.in);
	
	static long readLong() { return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	
	static TarjetaCinemar cuenta1 = new TarjetaCinemar();
	static TarjetaCinemar cuenta2 = new TarjetaCinemar();
	static TarjetaCinemar cuenta3 = new TarjetaCinemar();
	
	static Cliente cliente1 = new Cliente("Andy", 18, 13434132, TipoDeDocumento.CC);
	static Cliente cliente2 = new Cliente("Isa", 15, 4254543, TipoDeDocumento.TI);
	static Cliente cliente3 = new Cliente("Samu", 18, 646453523, TipoDeDocumento.CC);
	
	static Pelicula pelicula1 = new Pelicula("KNJ temparada 4 movie", 30000, "Acción", "1 hora", "+18", "4D"); 
	static SalaCine salaDeCine1 = new SalaCine();

	static Pelicula pelicula2 = new Pelicula("Kong vs Godzilla Turbo Remix", 7000, "Acción", "2 horas", "+18","2D"); 

	static Membresia membresia1 = new Membresia("Basico", 1, 5000, 10);
	static Membresia membresia2 = new Membresia("Heroico", 2, 10000, 15);
	static Membresia membresia3 = new Membresia("Global", 3, 15000, 20);
	static Membresia membresia4 = new Membresia("Challenger", 4, 25000, 25);
	static Membresia membresia5 = new Membresia("Radiante", 5, 30000, 30);
	
	static Ticket ticket1 = new Ticket();
	static Ticket ticket2 = new Ticket();
	static Ticket ticket3 = new Ticket();
	static Ticket ticket4 = new Ticket();

	static MetodoPago metodoPago1 = new MetodoPago("Bancolombia", 100000, 0.10);
	static MetodoPago metodoPago2 = new MetodoPago("AV Villas", 60000, 0.05);
	static MetodoPago metodoPago3 = new MetodoPago("Banco Agrario", 150000, 0.15);
	static MetodoPago metodoPago4 = new MetodoPago("Efectivo",500000, 0);
	
	
	
	
	
	public static void main(String[] args) {
		//Llamados métodos de instancias para hacer pruebas
		{
			
//			salaDeCine1.crearAsientosSalaDeCine();
//			salaDeCine1.setNumeroSala(1);
//			
//			pelicula1.crearSalaVirtual("10AM");
//			pelicula1.crearSalaVirtual("3PM");
//			pelicula1.crearSalaVirtual("8PM");
//			pelicula1.setNumeroDeSala(1);
//			
//			salaDeCine1.setPeliculaEnPresentacion(pelicula1);
//			
//			ticket1.setPelicula(pelicula1);
//			ticket1.asignarPrecio();
//			ticket1.setSalaDeCine(salaDeCine1);
//			ticket1.setDueno(cliente1);
//			ticket1.setNumeroAsiento("4-4");
//			ticket1.procesarPagoRealizado(cliente2);
//			salaDeCine1.cambiarDisponibilidadAsiento(4, 4);
//			ticket1.realizarPago(metodoPago1);
//			
//			ticket2.setPelicula(pelicula1);
//			ticket2.asignarPrecio();
//			ticket2.setSalaDeCine(salaDeCine1);
//			ticket2.setDueno(cliente2);
//			ticket2.setNumeroAsiento("4-4");
//			ticket2.procesarPagoRealizado(cliente1);
//			pelicula1.modificarSalaVirtual("3PM",4, 4);
//			
//			ticket3.setPelicula(pelicula2);
//			ticket3.asignarPrecio();
//			
//			ticket4.setPelicula(pelicula1);
//			ticket4.asignarPrecio();
//
			Membresia.asignarTipoMembresia();
			MetodoPago.metodoPagoPorTipo(metodoPago1);
			MetodoPago.metodoPagoPorTipo(metodoPago2);
			MetodoPago.metodoPagoPorTipo(metodoPago3);
//			cliente1.setMembresia(membresia1);
//			//System.out.println(MetodoPago.mostrarMetodosDePago(cliente1));
//			/*cliente1.setMembresia(membresia1)*/
//			
//			System.out.println(MetodoPago.getMetodosDePagoDisponibles().size());
					
		}
		
		
		
		
		
		System.out.println("Bienvenido al cine de Marinilla");
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
		opcion = (int)readLong();
		
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
	static void reservarTicket() {System.out.println("Reservando tiquete");}
	
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
		System.out.println("¿Quieres reclamar un bono, hacer un pedido o salir?");
		
		
	}
	
		
	   
	
	
	static void comprarSouvenirs() {
		System.out.print("Estas seguro de acceder al servicio de souvenir:\n1.SI.\n2.NO.\nSeleccina una opcion:");
		int eleccion = (int)readLong();
		if (eleccion==2) {inicio();}
		else if (eleccion==1){}
		else {
			System.out.println("Opcion Invalida");
			comprarSouvenirs();
		}
		ServicioSouvenirs servicioSouvenirs = new ServicioSouvenirs();
		TipoDeDocumento documentoCliente = null;
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
		Cliente cliente1;
		do {
			System.out.print("Ingrese el numero de documento: ");
			long numeroDocumentoCliente = readLong();
			cliente1=Cliente.revisarDatosCliente(numeroDocumentoCliente);
			if (cliente1==null) {
				System.out.print("Ingrese su edad: ");
				int edadCliente = (int)readLong();
				System.out.print("Ingrese su nombre: ");
				String nombreCliente = readLn();
				cliente1 = new Cliente(nombreCliente,null,null,null,edadCliente,null,numeroDocumentoCliente,0,documentoCliente,null,null,null);
				servicioSouvenirs.setCliente(cliente1);
				casoValido=true;
			}
			else {
				System.out.println("¿Eres "+cliente1.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				int eleccion1 = (int)readLong();
				if (eleccion1==1) {
					servicioSouvenirs.setCliente(cliente1);
					casoValido=true;
					}
				else if(eleccion1==2){
					System.out.println("Verifica el numero de documento\n");
				}
				else {
					System.out.println("Opcion invalida\n");
				}
			}
		}while(!casoValido);
		
		/*do {
			System.out.print("Que deseas hacer:\n1.Reclamar un Bono.\n2.Hacer un pedido.\nSeleccione una opcion:");
			int eleccion2 = (int)readLong();
			if(eleccion2 == 1) {
				System.out.print("Ingresa el codigo del bono: ");
				long codigo = readLong();
				Bono bono = servicioSouvenirs.verificarBono(codigo, cliente1);
				if (null!= bono) {
					System.out.print("El bono que tienes asociado es de un:"+bono.getProducto());
				}
				
			}
		}while(casoValido);
		*/
		
		
		
		
		
		}
	
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
			System.out.println("Seleccione el tipo de documento:\n1."+TipoDeDocumento.CC+"-"+TipoDeDocumento.CC.getNombre()+"\n2."+TipoDeDocumento.TI+"-"+TipoDeDocumento.TI.getNombre()+"\n3."+TipoDeDocumento.CE+"-"+TipoDeDocumento.CE.getNombre()+"\n4.Regresar\n5.Volver al menú principal\n6.Salir");
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
		do {
			System.out.println("¿Deseas recargar la tarjeta?");
			System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir");
			int eleccion1 = (int)readLong();
			if (eleccion1==1) {
				
				System.out.println("Cada metodo de pago tiene un monto maximo para recargar, en caso de superar este monto debera elegir otro metodo de pago");
				System.out.println(MetodoPago.mostrarMetodosDePago(clienteActual));
				int eleccion2 = (int)readLong();
				System.out.println("¿Cuanto desea recargar?\n");
				double eleccion3 = (double)readLong();
				switch (eleccion2) {
				
				case 1:
					System.out.print("Ingrese contraseña (4 digitos): ");
					int clave = (int) readLong();
					
//					if (eleccion3>metodoPago1.getLimiteMaximoPago()) {
//						clienteActual.getCuenta().ingresarSaldo(metodoPago1.getLimiteMaximoPago());
//						System.out.println("El valor a recargar ha superado el limite permitido por "+metodoPago1.getNombre()+
//								"\nPor favor escoja otro metodo de pago para pagar los $"+(eleccion3-metodoPago1.getLimiteMaximoPago())+
//								" restantes");
//					}
				}
				casoValido= false;
			}
			else if (eleccion1==2) {
				System.out.println("Recuerde que debe tener saldo para acceder a los diferentes juegos\nSu saldo en Tarjeta Cinemar: "+clienteActual.getCuenta().getSaldo());
				casoValido = false;
			}
			else if (eleccion1==3) {
				inicio();
			}
			else if (eleccion1==4) {
				salirDelSistema();
			}
			else {System.out.println("Opcion invalida");}
		}while(casoValido);
	}
	
	static void adquirirMembresia() {
		System.out.println(Membresia.mostrarCategoria());
		System.out.println("Escoga la categoria de su membresia o escriba 5 para volver");
		int opcion = (int)readLong();
		if (opcion==5) {inicio();}
		else if (opcion < 5 && opcion > 0) {
			System.out.println(Membresia.verificarRestriccionMembresia(cliente1, opcion));
		}
	} 
	
	static void salirDelSistema() {
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
		

		
	}
}
