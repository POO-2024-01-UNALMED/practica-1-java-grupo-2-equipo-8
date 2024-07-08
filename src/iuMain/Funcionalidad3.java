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
		
		System.out.println("********Bienvenido a la calificacion de productos*********");
		 
		do {
			try {
				System.out.print("\n1.Calificar Comida.\n2.Calificar Pelicula\n3.Volver al menu.\nSeleccione una opcion: ");
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
			System.out.println("\n********Bienvenido al apartado de calificacion de comida********");
			if(clienteProceso.getHistorialDePedidos().size() > 0) {
				System.out.println("\n********Estos son los pedidos que has adquirido**********" + "\n" + clienteProceso.mostrarHistorialDePedidos());
				Producto opcionPedido=null;
				int calificacion1=0;
				do {
					try {
						System.out.print("\n*********Seleccione el producto que deseas calificar********");
						eleccion = Integer.parseInt(sc.nextLine());
						if (eleccion == 0) {
							break;
						}
						if (eleccion > clienteProceso.getHistorialDePedidos().size() || eleccion < 1) {
							System.out.print("\n******Error en la seleccion del producto******");
							continue;
						}
						opcionPedido = clienteProceso.getHistorialDePedidos().get(eleccion-1);
						System.out.print("\nIngrese la calificacion del 1 al 5 que le vas a dar a este producto: ");
						calificacion1 = Integer.parseInt(sc.nextLine());
						if (calificacion1>3) {
							System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + "por lo tanto esta comida esta catalogada como bien calificada" + "***********");
						}
						
						else if(calificacion1<2.99) {
							System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + "por lo tanto esta comida esta catalogada como mal calificada" + "***********");
							}
						else {
							System.out.println("Error al calificar la comida, recuerda que es del 1 al 5");
						}
						
						Producto prueba=opcionPedido;
						double calificacionGlobalPedidos;
						calificacionGlobalPedidos= (prueba.getValoracionComida() * prueba.getTotalEncuestasDeValoracionRealizadasComida()+calificacion1)/(prueba.getTotalEncuestasDeValoracionRealizadasComida()+1);
						
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					
					
				}while(verificar);
				}
			else {
				System.out.println("\n******Lastimosamente no has hecho compra de ningun alimento, por lo tanto no puedes calificar ninguno*******");
			}
		}  
		else if (eleccion==2) {
			System.out.println("\n********Bienvenido al apartado de calificacion de peliculas********");
			if(clienteProceso.getPeliculasDisponiblesParaCalificar().size() > 0) {
				System.out.println("\n********Estas son las peliculas que has visto**********" + "\n" + clienteProceso.mostrarHistorialDePelicula());
				Pelicula opcionPelicula=null;
				int calificacion=0;
				do {
					try {
						System.out.print("\n*********Seleccione la pelicula que deseas calificar********");
						eleccion = Integer.parseInt(sc.nextLine());
						if (eleccion == 0) {
							break;
						}
						if (eleccion > clienteProceso.getPeliculasDisponiblesParaCalificar().size() || eleccion < 1) {
							System.out.print("\n******Error en la seleccion de la pelicula******");
							continue;
						}
						opcionPelicula = clienteProceso.getPeliculasDisponiblesParaCalificar().get(eleccion-1);
						System.out.print("\nIngrese la calificacion del 1 al 5 que le vas a dar a esta pelicula: ");
						calificacion = Integer.parseInt(sc.nextLine());
						if (calificacion>=3) {
							System.out.println("\n*********Escogiste la pelicula: " + opcionPelicula.getNombre()+ " " + opcionPelicula.getTipoDeFormato()+ "  y le diste una valoracion de " + calificacion + ", por lo tanto esta pelicula esta catalogada como bien calificada" +"***********");
						}
						else if	(calificacion<=2.99) {
						System.out.println("\n*********Escogiste la pelicula: " + opcionPelicula.getNombre()+ " " + opcionPelicula.getTipoDeFormato()+ "  y le diste una valoracion de " + calificacion +", por lo tanto esta pelicula esta catalogada como mal calificada" + "***********");
						}
						else {
							System.out.println("Error al calificar la pelicula, recuerda que es del 1 al 5");
						}
				        
						Pelicula prueba1 = opcionPelicula;
						double calificacionGlobalPeliculas;
						calificacionGlobalPeliculas= (prueba1.getValoracion() * prueba1.getTotalEncuestasDeValoracionRealizadas()+calificacion)/(prueba1.getTotalEncuestasDeValoracionRealizadas()+1);
						prueba1.setTotalEncuestasDeValoracionRealizadas();
						
					
					
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					
					
				}while(verificar);
				}
			else {
				System.out.println("******Lastimosamente no has visto ninguna pelicula, por lo tanto no puedes calificar ninguna*******");
			}
				
		}
		
        
		else if (eleccion==3) {
			Administrador.inicio(clienteProceso);
			verificar=true;
				
	     }
		else {
			Funcionalidad3.calificacion(clienteProceso);
		}
		

	}
	
	
	
	

	
	
	
    
 




























}
































