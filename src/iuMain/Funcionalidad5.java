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
	
	
	static void adquirirMembresia(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
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
			System.out.print(Membresia.mostrarCategoria(clienteProceso, sucursalCineProceso) + "6. Volver al inicio. \nIngrese el número de la categoria deseada: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso, sucursalCineProceso); break;}
			else if (opcionMenu >0 && opcionMenu <6) {
				if (clienteProceso.getMembresia()!= null) {
					int categoriaCliente = clienteProceso.getMembresia().getCategoria();
					if (categoriaCliente == opcionMenu) {
						System.out.print("Ya posee esta categoria. Redirigiendo al menú de membresias\n");
						continue;
					}
				}
				boolean requisitosMembresia = Membresia.verificarRestriccionMembresia(clienteProceso, opcionMenu, sucursalCineProceso);
					if (requisitosMembresia == false) {
						System.out.print("No puedes adquirir esta membresía debido a que no cumples con los criterios establecidos para ello o no hay unidades en el momento.\n"
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
		double valorAPagar= membresiaNueva.getValorSuscripcionMensual();
		double precio = 0;
		LinkedHashMap<MetodoPago, Double> pagosEnTransaccion = new LinkedHashMap<>();
		Entry<MetodoPago, Double> primerPagoUsado = null;
		String descuentoActivo = null;
		do {
			opcionMenu = 0;
			System.out.print("El precio de la membresia es de " + valorAPagar 
			+ ". Por favor, seleccione el método de pago a usar:\n"
			+ MetodoPago.mostrarMetodosDePago(clienteProceso) + "\n6. Volver al inicio \nIngrese la opción: ");
			opcionMenu = Integer.parseInt(sc.nextLine());
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso, sucursalCineProceso);}
			MetodoPago metodoPagoSeleccionado = MetodoPago.usarMetodopago(clienteProceso, opcionMenu);
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
				valorAPagar = metodoPagoSeleccionado.realizarPago(precio, clienteProceso);
				break;
			}
		}while (valorAPagar != 0); 
		membresiaNueva.procesarPagoRealizado(clienteProceso);
		System.out.print(clienteProceso.getFacturas().get( clienteProceso.getFacturas().size() - 1 ));
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
				
			} while (!finalizarCompra);
		}
	}
		

}
