package iuMain;
import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.Cliente;

public class Funcionalidad3 {
	static void calificacion(Cliente clienteProceso, SucursalCine sucursalCineProceso){
		boolean verificar = true;
		int eleccion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("    Bienvenido a la calificacion de productos     ");
		 
		do {
			try {
				System.out.print("1.Calificar Comida.\n2.Calificar Pelicula\n3.Volver al menu.\nSeleccione una opcion: ");
				eleccion = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			if (eleccion == 3) {
				Administrador.inicio(clienteProceso, sucursalCineProceso);
				break;
			}
			else if (eleccion == 1 || eleccion == 2) {
				verificar = false;
			}
		}while(verificar);
		
		if (eleccion==1) {
			System.out.println("      Bienvenido al apartado de calificacion de comida     ");
			
		}
   }
    
 


























static void verificarClientePelicula(){
  }

}
































