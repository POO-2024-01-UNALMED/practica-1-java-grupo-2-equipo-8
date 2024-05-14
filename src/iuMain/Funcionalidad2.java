package iuMain;

import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.ServicioComida;
import gestionAplicacion.servicios.ServicioSouvenirs;
import gestionAplicacion.usuario.Cliente;

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
					System.out.print("\n🎉🎉El pedido fue realizado con exito🎉🎉\n");
					comida.getOrden().add(producto);
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
			
			}
		else {
			System.out.print("Bienvenido al servicio de Souvenirs");
		}
		
	}
	
}
