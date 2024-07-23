package iuMain;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Membresia;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TarjetaCinemar;

public class Funcionalidad5 {
	
	static Scanner sc = new Scanner(System.in);
	static long readLong() { return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	
	static void adquirirMembresia(Cliente clienteProceso) {
		System.out.println("Bienvenido a nuestro plan de membresias en el cine de Marinilla, " + clienteProceso.getNombre() + ".");
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
				case 2: Administrador.inicio(clienteProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
				default: System.out.println("Opción invalida."); break;
			}
			
		}while(!casoValido);
		//Se da a escoger al usuario la membresia
		Membresia membresiaNueva = null;
		do {
			opcionMenu = 0;
			System.out.print(Membresia.verificarMembresiaActual(clienteProceso));
			System.out.print(Membresia.mostrarCategoria(clienteProceso, clienteProceso.getCineActual()) + "6. Volver al inicio. \nIngrese el número de la categoria deseada: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso); break;}
			else if (opcionMenu >0 && opcionMenu <6) {
				//Se verifica si se cumple con los requisitos para adquirir la membresia.
				boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(clienteProceso, opcionMenu, clienteProceso.getCineActual());
				System.out.print("\nCargando...\n");
				try {
				Thread.sleep(3000);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
					if (requisitosMembresia == false) {
						System.out.print("\nNo puedes adquirir esta membresía debido a que no cumples con los criterios establecidos para ello o no hay unidades en el momento.\n"
								+ "Redirigiendo al menú de membresias\n");
						continue;
					} else {
						membresiaNueva = Membresia.asignarMembresiaNueva(opcionMenu);
					}
			} else {
				continue;
			}
		} while (membresiaNueva == null);
		
		//Una vez se ha escogido la membresia, se pasa a realizar el pago
		double valorAPagar = membresiaNueva.getValorSuscripcionMensual();
		do {
			opcionMenu = 0;
			System.out.print("El precio de la membresia es de " + valorAPagar 
			+ ". Por favor, seleccione el método de pago a usar:\n"
			+ MetodoPago.mostrarMetodosDePago(clienteProceso) + "\n6. Volver al inicio \nIngrese la opción: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso);}
			MetodoPago metodoPagoSeleccionado = MetodoPago.usarMetodopago(clienteProceso, opcionMenu);
			try {
				if (metodoPagoSeleccionado.getDescuentoAsociado() != 0 && valorAPagar == membresiaNueva.getValorSuscripcionMensual()) {
					valorAPagar = valorAPagar - valorAPagar * metodoPagoSeleccionado.getDescuentoAsociado();
					System.out.print("Con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ ", el nuevo monto a pagar es " 
						+ valorAPagar +".\n1. Confirmar pago. \n2. Cambiar método de pago. \nPor favor, seleccione una opción: ");
					
				}else {
					System.out.print("\nEl monto a pagar con el método de pago " 
						+ metodoPagoSeleccionado.getNombre()+ " es " 
						+ valorAPagar + ". \n1. Confirmar pago. \n2. Cambiar método de pago. \nPor favor, seleccione una opción: ");
				}
			opcionMenu = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
			System.out.print("Error, debes ingresar un dato numérico");}
			
			if (opcionMenu == 1) {
				valorAPagar = metodoPagoSeleccionado.realizarPago(valorAPagar, clienteProceso);
			} else if (opcionMenu == 2) {
				valorAPagar = membresiaNueva.getValorSuscripcionMensual();
				continue;
			}
		}while (valorAPagar != 0); 
		
		System.out.print("\nEstamos procesando su pago...\n");
		try {
			Thread.sleep(3000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		membresiaNueva.procesarPagoRealizado(clienteProceso);
		System.out.print(
				"=== Factura de compra ===\n" +
				"Nombre dueño: " + clienteProceso.getNombre() + "\n" +
				"Documento: " + clienteProceso.getDocumento() + "\n" +
				membresiaNueva.factura());
		TarjetaCinemar tarjetaCinemarActual = clienteProceso.getCuenta();
		int tipoMembresia = 0;
		if (tarjetaCinemarActual != null) {
			tipoMembresia = clienteProceso.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				tarjetaCinemarActual.ingresarSaldo(10000);
			}else {
				tarjetaCinemarActual.ingresarSaldo(20000);
			}
		}else {
			boolean finalizarCompra = false;
			double saldoCuenta = 0.0;
			tipoMembresia = clienteProceso.getMembresia().getTipoMembresia();
			if (tipoMembresia == 1) {
				saldoCuenta = 5000.0;
			} else {
				saldoCuenta = 20000.0;
			}
			do {
				try{
					opcionMenu = 0;
					System.out.print("\nGracias por adquirir el programa de membresia. Su nueva membresia es " + clienteProceso.getMembresia().getNombre() + " de categoria" + clienteProceso.getMembresia().getCategoria()+"\nComo regalo, le otorgamos una tarjeta cinemar con "
							+ (int)saldoCuenta + " recargados.\n1. Confirmar.\n2. Rechazar. \nPor favor, seleccione una opción: ");
					opcionMenu = Integer.parseInt(sc.nextLine());
				}catch (NumberFormatException e){
					System.out.print("Error. Por favor, escriba un dato numérico");}
				if (opcionMenu == 1) {
					Arkade.asociarTarjetaCliente(clienteProceso);
					clienteProceso.getCuenta().ingresarSaldo(saldoCuenta);
					System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+clienteProceso.getCuenta().getDueno().getNombre()+"\nSaldo: $"+clienteProceso.getCuenta().getSaldo());
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
				try {
					Thread.sleep(3000);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
			} while (!finalizarCompra);
		}
	}
}

