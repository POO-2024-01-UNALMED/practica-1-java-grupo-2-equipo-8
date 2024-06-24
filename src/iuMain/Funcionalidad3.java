package iuMain;
import java.util.ArrayList;
import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;

public class Funcionalidad3 {
	static void calificacion(Cliente clienteProceso){
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
				Administrador.inicio(clienteProceso);
				break;
			}
			else if (eleccion == 1 || eleccion == 2) {
				verificar = false;
			}
		}while(verificar);
		
		if (eleccion==1) {
			System.out.println("      Bienvenido al apartado de calificacion de comida     ");
		}  
		if (eleccion==2) {
			System.out.println("      Bienvenido al apartado de calificacion de peliculas   ");
			if(clienteProceso.getHistorialDePeliculas().size() > 0) {
				System.out.println("----------Estas son las peliculas que has visto--------" + "\n" + clienteProceso.mostrarHistorialDePelicula());
				}
			else {
				System.out.println("NO HAS VISTO NINGUNA PELICULA, POR LO TANTO NO PUEDES CALIFICAR NINGUNA PELICULA");
			}
				
		}
		
        
		if (eleccion==3) {
			Administrador.inicio(clienteProceso);
			verificar=true;
	     }
	
		String opcionPelicula=null;
		int calificacion=0;
		do {
			try {
				System.out.print("\nSeleccione la pelicula que deseas calificar: ");
				eleccion = Integer.parseInt(sc.nextLine());
				if (eleccion == 0) {
					break;
				}
				if (eleccion > clienteProceso.getHistorialDePeliculas().size() || eleccion < 1) {
					System.out.print("ERROR EN LA SELECCION DE LA PELICULA");
					continue;
				}
				//opcionPelicula = clienteProceso.mostrarHistorialDePelicula()(eleccion-1);
				System.out.print("\nIngrese la calificacion del 1 al 5 que le vas a dar a esta pelicula: ");
				calificacion = Integer.parseInt(sc.nextLine());
				System.out.println("Escogiste la pelicula:");
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			
			
		}while(verificar);

	}
	
	
	
	

	
	
	
    
 


























static void verificarClientePelicula(){
  }

}
































