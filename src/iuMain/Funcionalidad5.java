package iuMain;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.ServicioEntretenimiento;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.Membresia;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TarjetaCinemar;
import gestionAplicacion.usuario.TipoDeDocumento;

public class Funcionalidad5 {
	
	static Scanner sc = new Scanner(System.in);
	static long readLong() { return sc.nextLong();}
	
	static String readLn () {
		sc.nextLine();
		return sc.nextLine();
	}
	
	
	static void adquirirMembresia(Cliente clienteProceso, SucursalCine sucursalCineProceso) {
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
				case 2: Administrador.inicio(clienteProceso, sucursalCineProceso); casoValido = true; break;
				case 3: Administrador.salirDelSistema(); casoValido = true; break;
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
				case 4: adquirirMembresia(clienteProceso, sucursalCineProceso);casoValido=false;break;
				case 5: Administrador.inicio(clienteProceso, sucursalCineProceso);casoValido=false;break;
				case 6: Administrador.salirDelSistema();
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
			if (opcionMenu == 6) {Administrador.inicio(clienteProceso, sucursalCineProceso); break;}
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
			if (opcionMenu == 5) {Administrador.inicio(clienteProceso, sucursalCineProceso);}
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
		

}
