package iuMain;

import java.util.Scanner;

import gestionAplicacion.SucursalCine;
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
		System.out.println(" ====== Bienvenido a los servicios de compras ======");
		do {
			try {
				System.out.println("1.Servicio de comida.\n2.Servicio de souvenirs\n3.Volver al menu.");
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
		if (servicio == 1){
			System.out.print("Bienvenido al servicio de Comida");
			comida.setCliente(clienteProceso);
			comida.setInventario(comida.actualizarInventario());
			System.out.print(comida.mostrarInventario());
		}
		else {
			System.out.print("Bienvenido al servicio de Souvenirs");
		}
		
	}
	
}
