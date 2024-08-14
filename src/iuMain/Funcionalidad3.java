package iuMain;
import java.util.ArrayList;
import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.herencia.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
public class Funcionalidad3 {
	static void calificacion(Cliente clienteProceso){
		boolean verificar = true;
		int eleccion = 0;
		int eleccion1=0;
		int eleccion2=0;
		int eleccion3=0;
		/** Description: Esta funcionalidad 3 se va a encargar de hacer la respectiva calificacion de peliculas y productos dependiendo
		 * de los gustos del cliente, ya que con estas calificaciones vamos a hacer un proceso interno de logica de negocio 
		 * dentro del cine, para poder saber que peliculas o productos estan funcionando bien o por consecuencia, cuales 
		 * estan funcionando mal
		*/
		Scanner sc = new Scanner(System.in);
		//Le damos la bienvenida al cliente
		System.out.println("********Bienvenido a la calificacion de productos*********");
		
		/** Description: Este ciclo es para preguntarle al cliente que es lo que desea hacer dentro de la funcionalidad, si calificar
		 * una pelicula, un producto o volver al menu principal
		 */
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
		/**Description: En esta parte hacemos un condicional dependiendo dla opcion que el cliente escoja, si el cliente escoje
		 * la eleccion uno se le da la bienvenida para la calificacion de comidas y por el contrario si la eleccion es dos,
		 * se le da la bienvenida al apartado de calificacion de peliculas.
		 * Metodo mostrarHsitorialDePedidos: Este metodo le muestra a los usuarios los pedidos que han hecho, y por ende tambien verifica
		 * si el cliente si ha hecho almenos una compra, esto es para evitar que un cliente pueda calificar un producto que no ha 
		 * consumido. Cuando se verifica que el cliente ha consumido un producto se le da a escojer que producto desea calificar,
		 * y el cliente le da una valoracion del 1 al 5 dependiendo de los gustos del usuario.
		 *  
		 */
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
						
						Producto prueba = opcionPedido;
						
						double calificacionGlobalPedidos;
						calificacionGlobalPedidos= (prueba.getValoracionComida() * prueba.getTotalEncuestasDeValoracionRealizadasComida()+calificacion1)/(prueba.getTotalEncuestasDeValoracionRealizadasComida()+1);
						prueba.setTotalEncuestasDeValoracionRealizadasComida(prueba.getTotalEncuestasDeValoracionRealizadasComida()+1);
						prueba.setValoracionComida(calificacionGlobalPedidos);
						
						if (prueba.verificarInventarioProducto(clienteProceso.getCineActual())== true ) {
							continue;
						}
						else {
							System.out.println("De esta comida todavia hay unidades en inventario");
						}
						System.out.print("\nComo calificaste un producto te queremos hacer la oferta de un combo especial, deseas verlo?/1.Si/2.No : ");
						eleccion1 = Integer.parseInt(sc.nextLine());
						if (eleccion1==1) {
							mostrarCombo();
						}
						else {
							System.out.println("Gracias por tu tiempo... Adios ");
						}
						
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
		
		/**Description: En esta parte hacemos un condicional dependiendo dla opcion que el cliente escoja, si el cliente escoje
		 * la eleccion uno se le da la bienvenida para la calificacion de comidas y por el contrario si la eleccion es dos,
		 * se le da la bienvenida al apartado de calificacion de peliculas.
		 * Metodo mostrarHistorialDePeliculas: Este metodo le muestra a los usuarios los pedidos que han hecho, y por ende tambien verifica
		 * si el cliente si ha hecho almenos una compra, esto es para evitar que un cliente pueda calificar una pelicula que no ha 
		 * visto. Cuando se verifica que el cliente ha visto una pelicula se le da a escojer que pelicula desea calificar,
		 * y el cliente le da una valoracion del 1 al 5 dependiendo de los gustos del usuario.
		 */
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
						prueba1.setTotalEncuestasDeValoracionRealizadas(prueba1.getTotalEncuestasDeValoracionRealizadas()+1);
						prueba1.verificarHorariosPeliculas();
						prueba1.setValoracion(calificacionGlobalPeliculas);
						
						if ( prueba1.verificarHorariosPeliculas()== true ) {
							continue;
						}
						else {
							System.out.println("Esta pelicula todavia tiene horarios");
						}
						System.out.print("\nComo calificaste una pelicula te queremos hacer la oferta de un combo especial, deseas verlo?/1.Si/2.No : ");
						eleccion1 = Integer.parseInt(sc.nextLine());
						if (eleccion1==1) {
							Pelicula peliculaCombo=clienteProceso.getCineActual().mejorPelicula();
							Producto productoCombo1=clienteProceso.getCineActual().peorProducto();
							System.out.println("Estos son los productos escogidos para darte el combo especial: " + "La pelicula" +
							peliculaCombo.getNombre() + "y el producto " + productoCombo1.getNombre() + productoCombo1.getTamaño()) ;
							double precioTotal=0;
							precioTotal=peliculaCombo.getPrecio()+productoCombo1.getPrecio();
							System.out.println("Este combo tiene un precio de: " + precioTotal + ",deseas adquirirlo? /1.Si/2.No:  ");
							eleccion2 = Integer.parseInt(sc.nextLine());
							
							do {
								try {
									
									System.out.println("\nMETODOS DE PEGO DISPONIBLES:\n");
									System.out.println(MetodoPago.mostrarMetodosDePago(clienteProceso));
									System.out.print("Seleccione una opcion: ");
									eleccion = Integer.parseInt(sc.nextLine());
									
								}catch(NumberFormatException e) {
									System.out.println("\nError, debes ingresar un dato numérico\n");
									continue;
								}
								
								MetodoPago metodoDePago = MetodoPago.usarMetodopago(clienteProceso, eleccion);
								System.out.println("\n----------------------------------------------------------------------------------");
								System.out.println("\n         Gracias por utilizar: "+ metodoDePago.getNombre() +" para hacer tu pago");
								
								
								
								
								clienteProceso.setValorPedido(metodoDePago.realizarPago(clienteProceso.getValorPedido(),clienteProceso));	
							}
						}
						else {
							System.out.println("Gracias por tu tiempo... Adios ");
							Administrador.inicio(clienteProceso);
						}
					
					
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
		
        //Si la eleccion es 3, se devuelve al menu principal
		
		else if (eleccion==3) {
			Administrador.inicio(clienteProceso);
			verificar=true;
				
	     }
		else {
			Funcionalidad3.calificacion(clienteProceso);
		}
		

	}
	
	
	
	

	
	
	
    
 




























}
































