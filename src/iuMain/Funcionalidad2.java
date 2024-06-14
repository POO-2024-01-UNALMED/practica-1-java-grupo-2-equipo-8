package iuMain;

import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class Funcionalidad2 {
	public static void compras(Cliente clienteProceso,SucursalCine sucursalCineProceso){
		// Seleccion del servicio que se desea acceder
		Scanner sc = new Scanner(System.in);
		clienteProceso.setCine(sucursalCineProceso);
		boolean verificacion = true;
		int servicio = 0;
		int cantidad = 0;
		System.out.println(" ====== Bienvenido a los servicios de compras ======");
		
		//Le pedimos el servicio al cual desea acceder
		
		do {
			try {
				for(int i = 0;i<sucursalCineProceso.getServicios().size();i++) {
					int n = i+1; 
					System.out.println(n+". "+sucursalCineProceso.getServicios().get(i).getNombre());
				}
				System.out.print("0.Volver al menu.\n\nSeleccione una opcion: ");
				servicio = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			if (servicio == 0) {
				Administrador.inicio(clienteProceso, sucursalCineProceso);
				break;
			}
			else if (servicio == 1 || servicio == 2) {
				servicio = servicio - 1;
				verificacion = false;
			}
			
		}while(verificacion);
		
		//Servicio de comida
		
			
			//Filtramos el inventario segun el servicio y la sucursal del cine
			
			System.out.print("\n Bienvenido al "+ sucursalCineProceso.getServicios().get(servicio).getNombre());
			sucursalCineProceso.getServicios().get(servicio).setCliente(clienteProceso);
			sucursalCineProceso.getServicios().get(servicio).setInventario(sucursalCineProceso.getServicios().get(servicio).actualizarInventario());
			
			
			//Mostramos los productos que hay disponibles en la sucursal y 
			//le pedimos que seleccione uno y la cantidad de producto que desea
			
			verificacion = true;
			boolean verificacion2 = true;
			int eleccion = 0;
			do {
				try {
					System.out.print("\n"+sucursalCineProceso.getServicios().get(servicio).mostrarInventario());
					if (sucursalCineProceso.getServicios().get(servicio).mostrarInventario() == "\nNO HAY PRODUCTOS DISPONIBLES :(\n") {
						Administrador.inicio(clienteProceso, sucursalCineProceso);
					}
					System.out.print("\n\nSelecciones una opcion de los productos: ");
					eleccion = Integer.parseInt(sc.nextLine());
					if (eleccion == 0) {
						break;
					}
					if (eleccion > sucursalCineProceso.getServicios().get(servicio).getInventario().size() || eleccion < 1) {
						System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
						continue;
					}
					eleccion = eleccion - 1;
					System.out.print("Ingrese el numero de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				
				// Se verifica si hay suficientes productos segun la cantidad que pidio el cliente
				
				Producto producto = sucursalCineProceso.getServicios().get(servicio).hacerPedido(eleccion, cantidad);
				if(producto == null) {
					System.out.print("No hay suficientes productos de: "+sucursalCineProceso.getServicios().get(servicio).getInventario().get(eleccion).getNombre()+
							sucursalCineProceso.getServicios().get(servicio).getInventario().get(eleccion).getTamaño()+" \nEn el momento solo hay disponible: "+
							sucursalCineProceso.getServicios().get(servicio).getInventario().get(eleccion).getCantidad());
				}
				else {
					System.out.print("\n 🎉🎉El pedido fue realizado con exito🎉🎉 \n");
					sucursalCineProceso.getServicios().get(servicio).agregarOrden(producto);
					System.out.print(sucursalCineProceso.getServicios().get(servicio).mostrarOrden());
					do {
						try {
							System.out.print("\n\nQuieres hacer otro pedido: \n1.SI \n2.NO"+
						"\nSeleccione una opcion: ");	
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						verificacion2 = false;
						if(eleccion == 2){
							verificacion = false;
						}
						
					}while(verificacion2);
				}
			}while(verificacion);
			
			//////////// Espacio para descuento por genero con horario para hacer con Andy///////////////////////
			
			
			
			
			/////////////////////////////////////////////////////////////////////////////////
			
			//Validacion de los bonos
			verificacion = true;
			do {
				try {
					System.out.print("¿Tienes algun bono de comida para reclamar?\n1.SI\n2.NO\nSeleccione una opcion:");
					eleccion = Integer.parseInt(sc.nextLine());
					if (eleccion != 1 && eleccion != 2) {
						System.out.println("Error, Debes de seleccionar una de las dos opciones");
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				verificacion = false;
			}while(verificacion);
			
			verificacion = true;
			String codigoBono;
			if(eleccion == 1) {
				Producto productoBono = new Producto();
				do {
					try {
						System.out.println("\n🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁 REGALOS CON BONOS 🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁\n");
						System.out.print("Ingrese el codigo del bono: ");
						codigoBono = sc.nextLine();
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					productoBono = sucursalCineProceso.getServicios().get(servicio).validarBono(codigoBono,sucursalCineProceso.getServicios().get(servicio));
					if (productoBono == null) {
						System.out.println("Codigo invalido porfavor verificar el codigo");
						
						do {
							try {
								System.out.print("\n¿Que deseas hacer?\n1.Salir\n2.Volver a intentar\nSeleccione una opcion: ");
								eleccion = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							if (eleccion == 1) {
								verificacion = false;
								break;
							}
							else if (eleccion != 2) {
								System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
							}
						}while(eleccion != 1 && eleccion != 2);
						
					}
					else {
						System.out.println("\n\nBono validado con exito🎉🎉🎉🎉\n\n");
						System.out.print("El bono es de: "+productoBono.getNombre()+" "+productoBono.getTamaño());
						
						if(productoBono.comprobarBonoEnOrden(sucursalCineProceso.getServicios().get(servicio))) {
							verificacion = true;
							do {
								try {
									System.out.print("\n\\n¿Que deseas hacer con el producto?\n1.Desea agregarlo al pedido"+
											"\n2.Desea descontarlo del pedido\nSelecciona una opcion:");
									eleccion = Integer.parseInt(sc.nextLine());
								}catch(NumberFormatException e) {
									System.out.println("\nError, debes ingresar un dato numérico\n");
									continue;
								}

								if (eleccion == 1) {
									productoBono.setPrecio(0);
									productoBono.setNombre("Regalo de Bono "+productoBono.getNombre());
									sucursalCineProceso.getServicios().get(servicio).getOrden().add(productoBono);
									System.out.print(sucursalCineProceso.getServicios().get(servicio).mostrarOrden());
									verificacion = false;
								}
								else if (eleccion == 2){
									sucursalCineProceso.getServicios().get(servicio).descontarProducto(productoBono);
									System.out.print(sucursalCineProceso.getServicios().get(servicio).mostrarOrden());
									verificacion = false;
								}
								else {
									System.out.print("\n\\nSELECCIONE UNA OPCION VALIDA\\n\n");
								}
							}while(verificacion);
						}
						else if (productoBono != null){
							productoBono.setPrecio(0);
							productoBono.setNombre("Regalo de bono "+productoBono.getNombre());
							sucursalCineProceso.getServicios().get(servicio).getOrden().add(productoBono);
							System.out.print(sucursalCineProceso.getServicios().get(servicio).mostrarOrden());
						}
						verificacion = true;
						do {
							try {
								
								System.out.print("\n¿Deseas reclamar otro Bono?\n1.SI\n2.NO\nSelecciona una opcion:");
								eleccion = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							if (eleccion == 1) {
							}
							else if (eleccion == 2) {
								verificacion = false;
							}
							else if (eleccion != 2) {
								System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
							}
						}while(eleccion != 1 && eleccion != 2);
					}
				}while(verificacion);
				
				System.out.print("\nSALIO DEL SISTEMA DE BONOS\n");
				
			}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
			sucursalCineProceso.getServicios().get(servicio).setValorPedido(sucursalCineProceso.getServicios().get(servicio).calcularTotal());
			verificacion = true;
			boolean descuento = true;
			do {
				try {
					
					System.out.println("\nMETODOS DE PEGO DISPONIBLES:\n");
					System.out.println(MetodoPago.mostrarMetodosDePago(clienteProceso.getMetodosDePago()));
					System.out.print("Seleccione una opcion: ");
					eleccion = Integer.parseInt(sc.nextLine());
					
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				
				MetodoPago metodoDePago = MetodoPago.usarMetodopago(clienteProceso, eleccion);
				
				if (!metodoDePago.getNombre().equalsIgnoreCase("Efectivo")) {
					for(int i = 0; i < sucursalCineProceso.getServicios().get(servicio).getOrden().size(); i++) {
						if (sucursalCineProceso.getServicios().get(servicio).getOrden().get(i).getTamaño().equalsIgnoreCase("Cangreburger") && (sucursalCineProceso.getServicios().get(servicio).getOrden().get(i).getPrecio() > 100000) && descuento) {
							descuento = false;
							sucursalCineProceso.getServicios().get(servicio).setValorPedido(sucursalCineProceso.getServicios().get(servicio).getValorPedido()-(sucursalCineProceso.getServicios().get(servicio).getValorPedido()*0.5));
							System.out.println("Felicidades tenes un descuento por del 5% por comprar mas de $100.000 en Cangreburgers");
							System.out.println("El valor a pagar ahora es de: $"+sucursalCineProceso.getServicios().get(servicio).getValorPedido());
						}
					}
				}
				
				sucursalCineProceso.getServicios().get(servicio).setValorPedido(metodoDePago.realizarPago(sucursalCineProceso.getServicios().get(servicio).getValorPedido(),clienteProceso));
				
				if (sucursalCineProceso.getServicios().get(servicio).getValorPedido() == 0) {
					System.out.println("La cuota fue cubierta en su totalidad");
					verificacion = false;
				}
				else {
					System.out.println("\nFALTA POR TERMINAR DE PAGAR : $" + sucursalCineProceso.getServicios().get(servicio).getValorPedido() + " (╥_╥)(╥_╥)(╥_╥)");
					continue;
				}
				
			}while(verificacion);
			
		
	}
	
}
