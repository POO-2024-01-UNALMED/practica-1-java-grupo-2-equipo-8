package iuMain;
import java.util.Scanner;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.*;
import gestionAplicacion.usuario.*;
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
	
	static Pelicula pelicula1 = new Pelicula();
	static SalaCine salaDeCine1 = new SalaCine();
	
	

	
	
	public static void main(String[] args) {
		//Llamados métodos de instancias para hacer pruebas
		{
			pelicula1.crearSalaVirtual("10AM");
			pelicula1.crearSalaVirtual("3PM");
			pelicula1.crearSalaVirtual("8PM");
			
			salaDeCine1.crearAsientosSalaDeCine();
		}
		
		
		
		
		
		System.out.println("Bienvenido al cine de marinilla");
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
	
	static void comprarComida() {System.out.println("Comprando comida");}
	
	static void comprarSouvenirs() {System.out.println("Comprando souvenirs");}
	
	static void ingresoZonaJuegos() {

		System.out.println("\nPara entrar a los juegos es necesario tener la tarjeta cinemar\n¿Desea ingresar o volver?\n1.Ingresar\n2.Volver\n3.Salir");
		int opcion = (int)readLong();
		if (opcion==2) {inicio();}
		else if (opcion==1) {}
		else if (opcion==3) {salirDelSistema();}
		else {System.out.println("\nOpcion Invalida");ingresoZonaJuegos();}
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
				if (ServicioEntretenimiento.verificarTarjetasEnInventario()) {
					System.out.println("\nEl precio de la tarjeta Cinemar es de 5000 pesos\nEste valor sera descontado al saldo de su tarjeta");
					ServicioEntretenimiento.asociarTarjetaCliente(cliente1);
					cliente1.getCuenta().hacerPago(5000);
					System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente1.getCuenta().getDueno().getNombre()+"\nSaldo: "+cliente1.getCuenta().getSaldo());
				}
				else {System.out.println("\nLo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde"); inicio();}
				casoValido=true;
			}
			else {
				System.out.println("¿Eres "+cliente1.getNombre()+"?");
				System.out.println("1. SI\n2. NO");
				int eleccion = (int)readLong();
				if (eleccion==1) {
					if (!cliente1.verificarCuenta()) {
						if (ServicioEntretenimiento.verificarTarjetasEnInventario()) {
							System.out.println("\nEl precio de la tarjeta Cinemar es de 5000 pesos\nEste valor sera descontado al saldo de su tarjeta");
							ServicioEntretenimiento.asociarTarjetaCliente(cliente1);
							cliente1.getCuenta().hacerPago(5000);
							System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente1.getCuenta().getDueno().getNombre()+"\nSaldo: "+cliente1.getCuenta().getSaldo());
						}
						else {System.out.println("Lo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde"); inicio();}
					}
					else {
						System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+cliente1.getCuenta().getDueno().getNombre()+"\nSaldo: "+cliente1.getCuenta().getSaldo());
					}
					casoValido=true;
				}
				else if (eleccion==2) {
					System.out.println("Verifica el numero de documento\n");
				}
				else {System.out.println("Opcion invalida\n");}
			}
		}while(!casoValido);
		System.out.println("¿Deseas recargar la tarjeta?");
		//System.out.println(Cliente.getClientes().size());
		
	}
	
	static void adquirirMembresia() {System.out.println("Obteniendo membresia");}
	
	static void salirDelSistema() {
		System.out.println("¡Adios, vuelva pronto!");
		System.exit(0);
		
	}
}
