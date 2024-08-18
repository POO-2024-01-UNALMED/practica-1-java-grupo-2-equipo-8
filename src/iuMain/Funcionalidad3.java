package iuMain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.herencia.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.Ticket;
public class Funcionalidad3 {
	static void calificacion(Cliente clienteProceso){
		boolean verificar = true;
		int eleccion = 0;
		int eleccion1=0;
		int eleccion2=0;
		
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
						if (calificacion1>=3) {
							System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + " por lo tanto esta comida esta catalogada como bien calificada" + "***********");
							continue;
						}
						
						else if(calificacion1<=2.99) {
							System.out.println("\n*********Escogiste: " + opcionPedido.getNombre() + " " + opcionPedido.getTamaño() + "  y le diste una valoracion de " + calificacion1 + " por lo tanto esta comida esta catalogada como mal calificada" + "***********");
							continue;
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
							System.out.print("\nComo calificaste un producto te queremos hacer la oferta de un combo especial, deseas verlo?/1.Si/2.No : ");
							eleccion1 = Integer.parseInt(sc.nextLine());
							if (eleccion1==1) {
								Pelicula peliculaCombo=clienteProceso.getCineActual().peorPelicula();
								LocalDateTime opcionHorarioPelicula=peliculaCombo.filtrarHorariosPeliculas();
								String numAsientoProceso= peliculaCombo.seleccionarAsientoAleatorio(opcionHorarioPelicula);
								Producto productoCombo1=clienteProceso.getCineActual().mejorProducto();
								String codigoBono=productoCombo1.generarCodigoAleatorio(7);
								System.out.println("Estos son los productos escogidos para darte el combo especial: " + "La pelicula" +
								peliculaCombo.getNombre() + "y el producto " + productoCombo1.getNombre() + productoCombo1.getTamaño()) ;
								
								double precioTotal=0;
								precioTotal=peliculaCombo.getPrecio()+productoCombo1.getPrecio();
								System.out.println("Este combo tiene un precio de: " + precioTotal + ",deseas adquirirlo? /1.Si/2.No:  ");
								eleccion2 = Integer.parseInt(sc.nextLine());
								if(eleccion2==1) {
									//Iniciamos el proceso de pago
									System.out.println("\n		Proceso de pago");
									System.out.println("=====================================================");
									
									boolean pagoRealizado = false;
									boolean casoValido = false;
									boolean casoValidoConfirmacion = false;
									
									MetodoPago metodoPagoProceso = null;
									double precioComboProceso = productoCombo1.getPrecio()+peliculaCombo.getPrecio();
									double precioAcumuladoComboProceso = 0;
									int opcionMenu=0;
									//Selccionar el método de pago para realizar el pago y realizar el pago
									do {
										do {
											opcionMenu = 0;
											try {
												System.out.println("\nEl valor a pagar por el combo es: " + precioComboProceso
												+ "\nEste es el listado de los métodos de pago disponibles:\n" 
												+ MetodoPago.mostrarMetodosDePago(clienteProceso));
												System.out.print("\nElige una de las opciones disponibles para realizar el pago: " );
												opcionMenu = Integer.parseInt(sc.nextLine());
											}catch(NumberFormatException e) {
												System.out.println("\nError, debe ingresar un único dato númerico entre los disponibles");
											}
											
											if (opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size()) {
												//Se selecciona el método de pago
												metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
												casoValido = true;
												
											}else {
												
												System.out.println("\nSeleccione un método de pago entre los disponibles");
												
											}
										}while( !casoValido );
										
										do {
											opcionMenu = 0;
											try {
												System.out.println("\nEl método de pago escogido es: " + metodoPagoProceso.getNombre() 
												+ " ( Precio anterior: " + precioComboProceso + " -> Precio actual: " + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )"
												+ "\n1. Correcto\n2. Cambiar Método de pago");
												opcionMenu = Integer.parseInt(sc.nextLine());
											}catch(NumberFormatException e) {
												System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
											}
											
											switch(opcionMenu) {
											case 1: casoValidoConfirmacion = true; break;
											case 2: casoValidoConfirmacion = true; break;
											default: System.out.println("Opcion Invalida"); casoValidoConfirmacion = false;
											}
											
										}while(!casoValidoConfirmacion);
										
										if (opcionMenu == 2 || opcionMenu == 0) {
											continue;
										}
										
										
										
										//Realizamos el pago y sumamos el precio acumulado para mostrar el valor real del ticket
										precioAcumuladoComboProceso = precioAcumuladoComboProceso + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
										precioComboProceso = metodoPagoProceso.realizarPago(precioComboProceso, clienteProceso);
										
										//Ponemos un delay en pantalla
										System.out.println("\nEstamos procesando su pago, por favor espere...\n");
										try {
											Thread.sleep(3000);
										}catch(InterruptedException e) {
											e.printStackTrace();
										}
									
										//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
										
											
											//Verificamos si el pago fue cubierto en su totalidad
											if (precioComboProceso == 0) {
												
												System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
												
												//Creamos nuevas instancias
												Ticket ticketProceso=new Ticket(peliculaCombo,opcionHorarioPelicula,numAsientoProceso,clienteProceso.getCineActual());
												Bono bonoProceso=new Bono(codigoBono,productoCombo1,productoCombo1.getTipoProducto(),clienteProceso);
												//Realizamos el proceso correspondiente luego de ser verificado
												ticketProceso.procesarPagoRealizado(clienteProceso);
												clienteProceso.getBonos().add(bonoProceso);
												clienteProceso.getCineActual().getBonosCreados().add(bonoProceso);

												System.out.println("-------Factura--------");
												System.out.println("--Este es tu combo!---");
												System.out.println(peliculaCombo.getNombre()+ "y" + productoCombo1.getNombre());
												System.out.println("------Felicidades, gracias por confiar en nosotros----");
												System.out.println("");
												pagoRealizado = true;
												
											}else {
												
												//Repetimos el proceso hasta validar el pago
												System.out.println("Tiene un saldo pendiente de : " + precioComboProceso);
												
											}
											
										
									
									}while(!pagoRealizado);
								}
							}
							else {
								System.out.println("Gracias por tu tiempo... Adios ");
							}
							
						}
						else {
							System.out.println("De esta comida todavia hay unidades en inventario");
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
						
						if ( !prueba1.verificarHorariosPeliculas()== true ) {
							continue;
						}
						else {
							System.out.println("Esta pelicula todavia tiene horarios");
						}
						System.out.print("\nComo calificaste una pelicula te queremos hacer la oferta de un combo especial, deseas verlo?/1.Si/2.No : ");
						eleccion1 = Integer.parseInt(sc.nextLine());
						if (eleccion1==1) {
							
							Pelicula peliculaCombo=clienteProceso.getCineActual().mejorPelicula();
							LocalDateTime opcionHorarioPelicula=peliculaCombo.filtrarHorariosPeliculas();
							String numAsientoProceso= peliculaCombo.seleccionarAsientoAleatorio(opcionHorarioPelicula);
							Producto productoCombo1=clienteProceso.getCineActual().peorProducto();
							String codigoBono=productoCombo1.generarCodigoAleatorio(5);
							System.out.println("Estos son los productos escogidos para darte el combo especial: " + "La pelicula" +
							peliculaCombo.getNombre() + "y el producto " + productoCombo1.getNombre() + productoCombo1.getTamaño()) ;
							
							double precioTotal=0;
							precioTotal=peliculaCombo.getPrecio()+productoCombo1.getPrecio();
							System.out.println("Este combo tiene un precio de: " + precioTotal + ",deseas adquirirlo? /1.Si/2.No:  ");
							eleccion2 = Integer.parseInt(sc.nextLine());
							if(eleccion2==1) {
								//Iniciamos el proceso de pago
								System.out.println("\n		Proceso de pago");
								System.out.println("=====================================================");
								
								boolean pagoRealizado = false;
								boolean casoValido = false;
								boolean casoValidoConfirmacion = false;
								
								MetodoPago metodoPagoProceso = null;
								double precioComboProceso = productoCombo1.getPrecio()+peliculaCombo.getPrecio();
								double precioAcumuladoComboProceso = 0;
								int opcionMenu=0;
								//Selccionar el método de pago para realizar el pago y realizar el pago
								do {
									do {
										opcionMenu = 0;
										try {
											System.out.println("\nEl valor a pagar por el combo es: " + precioComboProceso
											+ "\nEste es el listado de los métodos de pago disponibles:\n" 
											+ MetodoPago.mostrarMetodosDePago(clienteProceso));
											System.out.print("\nElige una de las opciones disponibles para realizar el pago: " );
											opcionMenu = Integer.parseInt(sc.nextLine());
										}catch(NumberFormatException e) {
											System.out.println("\nError, debe ingresar un único dato númerico entre los disponibles");
										}
										
										if (opcionMenu > 0 & opcionMenu <= clienteProceso.getMetodosDePago().size()) {
											//Se selecciona el método de pago
											metodoPagoProceso = clienteProceso.getMetodosDePago().get(opcionMenu - 1);
											casoValido = true;
											
										}else {
											
											System.out.println("\nSeleccione un método de pago entre los disponibles");
											
										}
									}while( !casoValido );
									
									do {
										opcionMenu = 0;
										try {
											System.out.println("\nEl método de pago escogido es: " + metodoPagoProceso.getNombre() 
											+ " ( Precio anterior: " + precioComboProceso + " -> Precio actual: " + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )"
											+ "\n1. Correcto\n2. Cambiar Método de pago");
											opcionMenu = Integer.parseInt(sc.nextLine());
										}catch(NumberFormatException e) {
											System.out.println("Error, debes ingresar un único dato numérico entre los disponibles");
										}
										
										switch(opcionMenu) {
										case 1: casoValidoConfirmacion = true; break;
										case 2: casoValidoConfirmacion = true; break;
										default: System.out.println("Opcion Invalida"); casoValidoConfirmacion = false;
										}
										
									}while(!casoValidoConfirmacion);
									
									if (opcionMenu == 2 || opcionMenu == 0) {
										continue;
									}
									
									
									
									//Realizamos el pago y sumamos el precio acumulado para mostrar el valor real del ticket
									precioAcumuladoComboProceso = precioAcumuladoComboProceso + precioComboProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
									precioComboProceso = metodoPagoProceso.realizarPago(precioComboProceso, clienteProceso);
									
									//Ponemos un delay en pantalla
									System.out.println("\nEstamos procesando su pago, por favor espere...\n");
									try {
										Thread.sleep(3000);
									}catch(InterruptedException e) {
										e.printStackTrace();
									}
								
									//Realizamos el pago, según si el cliente decidió comprar un asiento de una película en presentación o en otro horario distinto
									
										
										//Verificamos si el pago fue cubierto en su totalidad
										if (precioComboProceso == 0) {
											
											System.out.println("Pago realizado, La compra de su ticket fue exitosa\n");
											
											//Setteamos el precio del ticket
											Ticket ticketProceso=new Ticket(peliculaCombo,opcionHorarioPelicula,numAsientoProceso,clienteProceso.getCineActual());
											Bono bonoProceso=new Bono(codigoBono,productoCombo1,productoCombo1.getTipoProducto(),clienteProceso);
											//Realizamos el proceso correspondiente luego de ser verificado
											ticketProceso.procesarPagoRealizado(clienteProceso);
											clienteProceso.getBonos().add(bonoProceso);
											clienteProceso.getCineActual().getBonosCreados().add(bonoProceso);
										
											
											System.out.println("-------Factura--------");
											System.out.println("--Este es tu combo!---");
											System.out.println(peliculaCombo.getNombre()+ "y" + productoCombo1.getNombre());
											System.out.println("------Felicidades, gracias por confiar en nosotros----");
											pagoRealizado = true;
											
										}else {
											
											//Repetimos el proceso hasta validar el pago
											System.out.println("Tiene un saldo pendiente de : " + precioComboProceso);
											
										}
										
									
								
								}while(!pagoRealizado);
							}
									
						
						else {
							System.out.println("Gracias por tu tiempo... Adios ");
							Administrador.inicio(clienteProceso);
						}
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
	

	
	
	
	
	
    
 




		
























































