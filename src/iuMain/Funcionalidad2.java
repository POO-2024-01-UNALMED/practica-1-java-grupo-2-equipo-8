package iuMain;

import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.ServicioComida;
import gestionAplicacion.servicios.ServicioSouvenirs;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class Funcionalidad2 {
	public static void compras(Cliente clienteProceso,SucursalCine sucursalCineProceso){
		// Seleccion del servicio que se desea acceder
		clienteProceso.setCine(sucursalCineProceso);
		Scanner sc = new Scanner(System.in);
		boolean verificacion = true;
		ServicioSouvenirs souvenir = new ServicioSouvenirs();
		ServicioComida comida = new ServicioComida();
		int servicio = 0;
		int cantidad = 0;
		System.out.println(" ====== Bienvenido a los servicios de compras ======");
		
		//Le pedimos el servicio al cual desea acceder
		
		do {
			try {
				System.out.print("1.Servicio de comida.\n2.Servicio de souvenirs\n3.Volver al menu.\nSeleccione una opcion: ");
				servicio = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			if (servicio == 3) {
				Administrador.inicio(clienteProceso, sucursalCineProceso);
				break;
			}
			else if (servicio == 1 || servicio == 2) {
				verificacion = false;
			}
			
		}while(verificacion);
		
		//Servicio de comida
		
		if (servicio == 1){
			
			//Filtramos el inventario segun el servicio y la sucursal del cine
			
			System.out.print("\n 🍔🍔🍔🍔🍔Bienvenido al servicio de Comida🍔🍔🍔🍔🍔\n");
			comida.setCliente(clienteProceso);
			comida.setInventario(comida.actualizarInventario());
			
			
			//Mostramos los productos que hay disponibles en la sucursal y 
			//le pedimos que seleccione uno y la cantidad de producto que desea
			
			verificacion = true;
			boolean verificacion2 = true;
			do {
				try {
					System.out.print(comida.mostrarInventario());
					System.out.print("\n\nSelecciones una opcion de los productos: ");
					servicio = Integer.parseInt(sc.nextLine());
					if (servicio == 0) {
						break;
					}
					if (servicio > comida.getInventario().size() || servicio < 1) {
						System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
						continue;
					}
					servicio = servicio - 1;
					System.out.print("Ingrese el numero de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				
				// Se verifica si hay suficientes productos segun la cantidad que pidio el cliente
				
				Producto producto = comida.hacerPedido(servicio, cantidad);
				if(producto == null) {
					System.out.print("No hay suficientes productos de: "+comida.getInventario().get(servicio).getNombre()+
							comida.getInventario().get(servicio).getTamaño()+" \nEn el momento solo hay disponible: "+
							comida.getInventario().get(servicio).getCantidad());
				}
				else {
					System.out.print("\n 🎉🎉El pedido fue realizado con exito🎉🎉 \n");
					comida.agregarOrden(producto);
					System.out.print(comida.mostrarOrden());
					do {
						try {
							System.out.print("\n\nQuieres hacer otro pedido: \n1.SI \n2.NO"+
						"\nSeleccione una opcion: ");	
							servicio = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						verificacion2 = false;
						if(servicio == 2){
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
					servicio = Integer.parseInt(sc.nextLine());
					if (servicio != 1 && servicio != 2) {
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
			if(servicio == 1) {
				Producto productoBono = new Producto();
				do {
					try {
						System.out.println("\n🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁 REGALOS CON BONOS 🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁\n");
						System.out.print("Ingrese el codigo del bono: ");
						servicio = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					productoBono = comida.validarBono(servicio, "Comida");
					if (productoBono == null) {
						System.out.println("Codigo invalido porfavor verificar el codigo");
						
						do {
							try {
								System.out.print("\n¿Que deseas hacer?\n1.Salir\n2.Volver a intentar\nSeleccione una opcion: ");
								servicio = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							if (servicio == 1) {
								verificacion = false;
								break;
							}
							else if (servicio != 2) {
								System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
							}
						}while(servicio != 1 && servicio != 2);
						
					}
					else {
						System.out.println("\n\nBono validado con exito🎉🎉🎉🎉\n\n");
						System.out.print("El bono es de: "+productoBono.getNombre()+" "+productoBono.getTamaño());
						
						if(productoBono.comprobarBonoEnOrden(comida)) {
							verificacion = true;
							do {
								try {
									System.out.print("\n\\n¿Que deseas hacer con el producto?\n1.Desea agregarlo al pedido"+
											"\n2.Desea descontarlo del pedido\nSelecciona una opcion:");
									servicio = Integer.parseInt(sc.nextLine());
								}catch(NumberFormatException e) {
									System.out.println("\nError, debes ingresar un dato numérico\n");
									continue;
								}

								if (servicio == 1) {
									productoBono.setPrecio(0);
									productoBono.setNombre("Regalo de Bono "+productoBono.getNombre());
									comida.getOrden().add(productoBono);
									System.out.print(comida.mostrarOrden());
									verificacion = false;
								}
								else if (servicio == 2){
									comida.descontarProducto(productoBono);
									System.out.print(comida.mostrarOrden());
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
							comida.getOrden().add(productoBono);
							System.out.print(comida.mostrarOrden());
						}
						verificacion = true;
						do {
							try {
								
								System.out.print("\n¿Deseas reclamar otro Bono?\n1.SI\n2.NO\nSelecciona una opcion:");
								servicio = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}
							if (servicio == 1) {
							}
							else if (servicio == 2) {
								verificacion = false;
							}
							else if (servicio != 2) {
								System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
							}
						}while(servicio != 1 && servicio != 2);
					}
				}while(verificacion);
				
				System.out.print("\nSALIO DEL SISTEMA DE BONOS\n");
				
			}
			
			double total = comida.calcularTotal();
			verificacion = true;
			boolean descuento = true;
			do {
				try {
					
					System.out.println("\nMETODOS DE PEGO DISPONIBLES:\n");
					System.out.println(MetodoPago.mostrarMetodosDePago(clienteProceso.getMetodosDePago()));
					System.out.print("Seleccione una opcion: ");
					servicio = Integer.parseInt(sc.nextLine());
					
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				
				MetodoPago metodoDePago = MetodoPago.usarMetodopago(clienteProceso, servicio);
				
				if (!metodoDePago.getNombre().equalsIgnoreCase("Efectivo")) {
					for(int i = 0; i < comida.getOrden().size(); i++) {
						if (comida.getOrden().get(i).getTamaño().equalsIgnoreCase("Cangreburger") && (comida.getOrden().get(i).getPrecio() > 100000) && descuento) {
							descuento = false;
							total = total - (total*0.05);
							System.out.println("Felicidades tenes un descuento por del 5% por comprar mas de $100.000 en Cangreburgers");
							System.out.println("El valor a pagar ahora es de: $"+total);
						}
					}
				}
				
				total = metodoDePago.realizarPago(total,clienteProceso);
				
				if (total == 0) {
					System.out.println("La cuota fue cubierta en su totalidad");
					verificacion = false;
				}
				else {
					System.out.println("\nFALTA POR TERMINAR DE PAGAR : $" + total + " (╥_╥)(╥_╥)(╥_╥)");
					continue;
				}
				
				
			}while(verificacion);
			
			
			
		}
		else {
			System.out.print("Bienvenido al servicio de Souvenirs");
			
			float total = souvenir.calcularTotal();
		}
		
		
		
		
		
		
	}
	
}
