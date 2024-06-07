package iuMain;

import java.util.InputMismatchException; 
import java.util.Scanner;
import java.util.Random;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;


public class Funcionalidad_4 {
	
	
	
	/**
	 * Description : Este método se encarga de toda la gestion de la funcionalidad 4, que incluye adquisicion y recarga de la tarjeta cinemar, ingreso a zona de juegos, ejecucion de juegos, generacion de bonos, etc.
	 * @param clienteActual : Este método recibe como parámetro el cliente actual que esta ejecutando el programa
	 * (De tipo Cliente)
	 * @param sucursalCineProceso : Este método recibe como parámetro la sucursal de cine en la que se esta ejecutando el programa
	 * (De tipo SucursalCine)
	 * @return <b>void</b> : No hay retorno
	 * */
	static void ingresoZonaJuegos(Cliente ClienteActual, SucursalCine sucursalCineProceso) {
		
		
		
		try {
			System.out.println("\n☺Recuerde que para entrar a los juegos es necesario tener la tarjeta cinemar☺\nDesea:\n1.Ingresar\n2.Volver al menú principal\n3.Salir");
			int opcion = (int)Administrador.readLong();
			if (opcion==2) {barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);}
			else if (opcion==1) {barraCarga("Ingresando");}
			else if (opcion==3) {barraCarga("Saliendo");Administrador.salirDelSistema();}
			else {System.out.println("\nOpcion Invalida");ingresoZonaJuegos(ClienteActual, sucursalCineProceso);}	
		
		}catch(InputMismatchException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
			Administrador.sc.nextLine(); 
			ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
		}
		
		boolean casoValido = true;
		
		do {
			if (!ClienteActual.verificarCuenta()) {
				try {
					System.out.println("\n•No tienes una Tarjeta Cinemar asociada, ¿Deseas Adquirirla?\n1.Si\n2.No");
					espera(1000);
					int option = (int)Administrador.readLong();
					if (option==1) {
						if (Arkade.verificarTarjetasEnInventario()) {
							barraCarga("Adquiriendo tarjeta");
							System.out.println("\n•El precio de la tarjeta Cinemar es de $5000\n•Este valor sera descontado al saldo de su tarjeta");
							espera(2000);
							//Aca se asocia la primera tarjeta en el array de disponibles al cliente, se le descuenta el valor de la tarjeta y se imprime por pantalla
							Arkade.asociarTarjetaCliente(ClienteActual);
							ClienteActual.getCuenta().hacerPago(5000);
							System.out.println("\n•Su tarjeta:");
							espera(1000);
							imprimirTarjeta(ClienteActual.getCuenta().getDueno().getNombre(),ClienteActual.getCuenta().getSaldo());
							casoValido = false;
						}
						else {System.out.println("\n•Lo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde");Administrador.sc.nextLine(); Administrador.inicio(ClienteActual, sucursalCineProceso);}
					
					}
					else if (option ==2) {ingresoZonaJuegos(ClienteActual, sucursalCineProceso);}
					else {System.out.println("\n•Opcion Invalida");}
				}catch(InputMismatchException e) {
					System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
					Administrador.sc.nextLine();
				}
			}
			else {
				System.out.println("\n•Su tarjeta:");
				espera(1000);
				imprimirTarjeta(ClienteActual.getCuenta().getDueno().getNombre(),ClienteActual.getCuenta().getSaldo());
				casoValido = false;
			}
		}while(casoValido);
		
			
		double maximo = ClienteActual.getMetodosDePago().get(0).getLimiteMaximoPago();
		double maximo2 = ClienteActual.getMetodosDePago().get(1).getLimiteMaximoPago();
		double maximo3 = ClienteActual.getMetodosDePago().get(2).getLimiteMaximoPago();
		double maximo4 = ClienteActual.getMetodosDePago().get(3).getLimiteMaximoPago();
		double maximo5 = 0;
		if (ClienteActual.getMetodosDePago().size()>4) {
			maximo5 = ClienteActual.getMetodosDePago().get(4).getLimiteMaximoPago();
		}
		
		do {
			
			try {
				System.out.println("•¿Deseas recargar la tarjeta?");
				System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir");
				int eleccion1 = (int)Administrador.readLong();
				boolean finCiclo = true;
				if (eleccion1==1) {
					
					double recargaMaxima = 0;
					for (MetodoPago metodo : ClienteActual.getMetodosDePago()) {
						recargaMaxima+= metodo.getLimiteMaximoPago();
					}
					
					System.out.println("•Escoja su metodo de pago");
					System.out.println("•El valor maximo a recargar por proceso es: $"+recargaMaxima+" intente no superar este valor");
					System.out.println("•Ademas Cada metodo de pago tiene un monto maximo para recargar, en caso de superar este monto debera elegir otro metodo de pago para completar la recarga");
					MetodoPago.asignarMetodosDePago(ClienteActual);
					System.out.println(MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
					int eleccion2 = (int)Administrador.readLong();
					System.out.print("•¿Cuanto desea recargar?: ");
					double montoRecarga = (double)Administrador.readLong();
					if (montoRecarga>recargaMaxima) {System.out.println("•El valor a recargar no puede superar $"+recargaMaxima+"\n•Se recargarán solo $"+recargaMaxima+", para recargar mas vuelva a hacer el proceso\n");montoRecarga = recargaMaxima;}
					espera(2000);
					while (finCiclo) {
						
						switch (eleccion2) {
						
						case 1:
							
							MetodoPago metodoPagoCliente = Arkade.encontrarMetodoPagoCliente("Bancolombia", ClienteActual.getMetodosDePago());
							
							if (metodoPagoCliente.getLimiteMaximoPago()==0) {
								System.out.println("•No puedes utilizar mas este metodo de pago ya que no tiene mas cupo disponible\n•Por favor elije otro para pagar los $"+(montoRecarga-metodoPagoCliente.getLimiteMaximoPago())+" restantes");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
							}
							
							boolean Break = true;
							int issue = 0;
							do {
								System.out.println("•Por favor confirma el pago\n1.Confirmar\n2.Cancelar");
								int confirmacion = (int) Administrador.readLong();
								if (confirmacion==1) {Break = false;}
								else if (confirmacion==2) {barraCarga("Cancelando");Break= false;finCiclo = false;issue=1;}
								else {System.out.println("•Opcion invalida");}
							}while(Break);
							if (issue==1) {break;}
							barraCarga("Procesando recarga");
							
							if (montoRecarga>metodoPagoCliente.getLimiteMaximoPago()) {
								ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente.getLimiteMaximoPago());
								System.out.println("\n•Se han recargado: $"+metodoPagoCliente.getLimiteMaximoPago()+" exitosamente\n•Sin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente.getNombre()+
										"\n•Por favor escoja otro metodo de pago para pagar los $"+(montoRecarga-metodoPagoCliente.getLimiteMaximoPago())+
										" restantes");
								
								montoRecarga = (double)montoRecarga-metodoPagoCliente.getLimiteMaximoPago();
								metodoPagoCliente.setLimiteMaximoPago(0);
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
							}
							else {
								ClienteActual.getCuenta().ingresarSaldo(montoRecarga);
								System.out.println("\n•Se han recargado: $"+montoRecarga+" exitosamente");
								finCiclo = false;
							}
							break;
						case 2:
							
							MetodoPago metodoPagoCliente2 = Arkade.encontrarMetodoPagoCliente("AV Villas", ClienteActual.getMetodosDePago());
							
							if (metodoPagoCliente2.getLimiteMaximoPago()==0) {
								System.out.println("•No puedes utilizar mas este metodo de pago ya que no tiene mas cupo disponible\n•Por favor elije otro para pagar los $"+(montoRecarga-metodoPagoCliente2.getLimiteMaximoPago())+" restantes");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
							}
							
							boolean Break2 = true;
							int issue2 = 0;
							do {
								System.out.println("•Por favor confirma el pago\n1.Confirmar\n2.Cancelar");
								int confirmacion = (int) Administrador.readLong();
								if (confirmacion==1) {Break2 = false;}
								else if (confirmacion==2) {barraCarga("Cancelando");Break2= false;finCiclo = false;issue2=1;}
								else {System.out.println("•Opcion invalida");}
							}while(Break2);
							if (issue2==1) {break;}
							barraCarga("Procesando recarga");
							
							if (montoRecarga>metodoPagoCliente2.getLimiteMaximoPago()) {
								ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente2.getLimiteMaximoPago());
								System.out.println("\n•Se han recargado: $"+metodoPagoCliente2.getLimiteMaximoPago()+" exitosamente\n•Sin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente2.getNombre()+
										"\n•Por favor escoja otro metodo de pago para pagar los $"+(montoRecarga-metodoPagoCliente2.getLimiteMaximoPago())+
										" restantes");
								
								montoRecarga = (double)montoRecarga-metodoPagoCliente2.getLimiteMaximoPago();
								metodoPagoCliente2.setLimiteMaximoPago(0);
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
							}
							else {
								ClienteActual.getCuenta().ingresarSaldo(montoRecarga);
								System.out.println("\n•Se han recargado: $"+montoRecarga+" exitosamente");
								finCiclo = false;
							}
							break;
						case 3:
	
							
							MetodoPago metodoPagoCliente3 = Arkade.encontrarMetodoPagoCliente("Banco Agrario", ClienteActual.getMetodosDePago());
							
							if (metodoPagoCliente3.getLimiteMaximoPago()==0) {
								System.out.println("•No puedes utilizar mas este metodo de pago ya que no tiene mas cupo disponible\n•Por favor elije otro para pagar los $"+(montoRecarga-metodoPagoCliente3.getLimiteMaximoPago())+" restantes");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
							}
							
							boolean Break3 = true;
							int issue3 = 0;
							do {
								System.out.println("•Por favor confirma el pago\n1.Confirmar\n2.Cancelar");
								int confirmacion = (int) Administrador.readLong();
								if (confirmacion==1) {Break3 = false;}
								else if (confirmacion==2) {barraCarga("Cancelando");Break3= false;finCiclo = false;issue3=1;}
								else {System.out.println("•Opcion invalida");}
							}while(Break3);
							if (issue3==1) {break;}
							barraCarga("Procesando recarga");
							
							if (montoRecarga>metodoPagoCliente3.getLimiteMaximoPago()) {
								ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente3.getLimiteMaximoPago());
								System.out.println("\n•Se han recargado: $"+metodoPagoCliente3.getLimiteMaximoPago()+" exitosamente\n•Sin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente3.getNombre()+
										"\n•Por favor escoja otro metodo de pago para pagar los $"+(montoRecarga-metodoPagoCliente3.getLimiteMaximoPago())+
										" restantes");
								
								montoRecarga = (double)montoRecarga-metodoPagoCliente3.getLimiteMaximoPago();
								metodoPagoCliente3.setLimiteMaximoPago(0);
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
							}
							else {
								ClienteActual.getCuenta().ingresarSaldo(montoRecarga);
								System.out.println("\n•Se han recargado: $"+montoRecarga+" exitosamente");
								finCiclo = false;
							}
							break;
						case 4:
	
							
							MetodoPago metodoPagoCliente4 = Arkade.encontrarMetodoPagoCliente("Efectivo", ClienteActual.getMetodosDePago());
							
							if (metodoPagoCliente4.getLimiteMaximoPago()==0) {
								System.out.println("•No puedes utilizar mas este metodo de pago ya que no tiene mas cupo disponible\n•Por favor elije otro para pagar los $"+(montoRecarga-metodoPagoCliente4.getLimiteMaximoPago())+" restantes");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
							}
							
							boolean Break4 = true;
							int issue4 = 0;
							do {
								System.out.println("•Por favor confirma el pago\n1.Confirmar\n2.Cancelar");
								int confirmacion = (int) Administrador.readLong();
								if (confirmacion==1) {Break4 = false;}
								else if (confirmacion==2) {barraCarga("Cancelando");Break4= false;finCiclo = false;issue4=1;}
								else {System.out.println("•Opcion invalida");}
							}while(Break4);
							if (issue4==1) {break;}
							barraCarga("Procesando recarga");
							
							if (montoRecarga>metodoPagoCliente4.getLimiteMaximoPago()) {
								ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente4.getLimiteMaximoPago());
								System.out.println("\n•Se han recargado: $"+metodoPagoCliente4.getLimiteMaximoPago()+" exitosamente\n•Sin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente4.getNombre()+
										"\n•Por favor escoja otro metodo de pago para pagar los $"+(montoRecarga-metodoPagoCliente4.getLimiteMaximoPago())+
										" restantes");
								
								montoRecarga = (double)montoRecarga-metodoPagoCliente4.getLimiteMaximoPago();
								metodoPagoCliente4.setLimiteMaximoPago(0);
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
							}
							else {
								ClienteActual.getCuenta().ingresarSaldo(montoRecarga);
								System.out.println("\n•Se han recargado: $"+montoRecarga+" exitosamente");
								finCiclo = false;
							}
							break;
						case 5:
							
							MetodoPago metodoPagoCliente5 = Arkade.encontrarMetodoPagoCliente("Puntos", ClienteActual.getMetodosDePago());
							
							if (metodoPagoCliente5 == null) {
								System.out.println("•Metodo de pago invalido, por favor seleccione otro");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
								}
							if (metodoPagoCliente5.getLimiteMaximoPago()==0) {
								System.out.println("•No puedes utilizar mas este metodo de pago ya que no tiene mas cupo disponible\n•Por favor elije otro para pagar los $"+(montoRecarga-metodoPagoCliente5.getLimiteMaximoPago())+" restantes");
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
								break;
							}
							
							boolean Break5 = true;
							int issue5 = 0;
							do {
								System.out.println("•Por favor confirma el pago\n1.Confirmar\n2.Cancelar");
								int confirmacion = (int) Administrador.readLong();
								if (confirmacion==1) {Break5 = false;}
								else if (confirmacion==2) {barraCarga("Cancelando");Break5= false;finCiclo = false;issue5=1;}
								else {System.out.println("•Opcion invalida");}
							}while(Break5);
							if (issue5==1) {break;}
							barraCarga("Procesando recarga");
							
							if (montoRecarga>metodoPagoCliente5.getLimiteMaximoPago()) {
								ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente5.getLimiteMaximoPago());
								System.out.println("\n•Se han recargado: $"+metodoPagoCliente5.getLimiteMaximoPago()+" exitosamente\n•Sin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente5.getNombre()+
										"\n•Por favor escoja otro metodo de pago para pagar los $"+(montoRecarga-metodoPagoCliente5.getLimiteMaximoPago())+
										" restantes");
								
								montoRecarga = (double)montoRecarga-metodoPagoCliente5.getLimiteMaximoPago();
								metodoPagoCliente5.setLimiteMaximoPago(0);
								System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
								eleccion2 = (int)Administrador.readLong();
							}
							else {
								ClienteActual.getCuenta().ingresarSaldo(montoRecarga);
								System.out.println("\n•Se han recargado: $"+montoRecarga+" exitosamente");
								finCiclo = false;
							}
							break;
							
						default:
							System.out.println("•Metodo de pago invalido");
							finCiclo = false;
							break;
						}
						
							
							
					}
					
					ClienteActual.restablecerLimiteMaximo(maximo, maximo2, maximo3, maximo4, maximo5);
					System.out.println("\n•Su tarjeta:");
					espera(2000);
					imprimirTarjeta(ClienteActual.getNombre(),ClienteActual.getCuenta().getSaldo());
	
					while(!finCiclo) {
						System.out.println("Desea: ");
						System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
						int eleccion4 = (int)Administrador.readLong();
						switch (eleccion4) {
						case 1: barraCarga("Ingresando");casoValido= false;finCiclo= true; break;
						case 2: barraCarga("Redirigiendo");casoValido = true ; finCiclo= true; break;
						case 3: barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);
						case 4: barraCarga("Saliendo");Administrador.salirDelSistema();
						default: finCiclo = false; break; 
					}
					}
					
				}
				else if (eleccion1==2) {
					System.out.println("\n-----------------------------------------------------------------------------");
			        System.out.println("•Recuerde que debe tener saldo para acceder a los diferentes juegos•");
			        System.out.println("-----------------------------------------------------------------------------");
			        System.out.println("\n•Su tarjeta:");
			        espera(2000);
					imprimirTarjeta(ClienteActual.getNombre(),ClienteActual.getCuenta().getSaldo());
					while(finCiclo) {
						System.out.println("\nDesea: ");
						System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
						int eleccion4 = (int)Administrador.readLong();
						switch (eleccion4) {
						case 1: barraCarga("Ingresando");casoValido= false;finCiclo= false; break;
						case 2: barraCarga("Redirigiendo");casoValido = true;finCiclo= false; break;
						case 3: barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);
						case 4: barraCarga("Saliendo");Administrador.salirDelSistema();
						default: finCiclo = true; break; 
						}
					}
				}
				else if (eleccion1==3) {
					barraCarga("Volviendo");
					Administrador.sc.nextLine();
					Administrador.inicio(ClienteActual, sucursalCineProceso);
				}
				else if (eleccion1==4) {
					barraCarga("Saliendo");
					Administrador.salirDelSistema();
				}
				else {System.out.println("Opcion invalida");}
			}catch(InputMismatchException e) {
				System.out.println("\nError en el proceso de recarga, debe ingresar un dato numerico, vuelva a realizar el proceso");
				ClienteActual.restablecerLimiteMaximo(maximo, maximo2, maximo3, maximo4, maximo5);
				Administrador.sc.nextLine();
				casoValido = true;
			}
		}while(casoValido);
		
		boolean caso = true;
		
		do {
			
			
			try {
				
				Arkade.reestablecerPrecioJuegos();
				if (ClienteActual.getCodigosDescuento().size()==0){
					System.out.println("\n•No tienes codigos de descuento asociados\n");
					caso = false;
					break;
				}
				System.out.println("\n•Estos son los codigos de descuento que tienes por la compra de tiquetes en nuestro cine\n¿Cual deseas redimir?\n");
				ClienteActual.mostrarCodigosDescuento();
				int eleccion6 = (int)Administrador.readLong();
				
				
				if (eleccion6 > 0 && eleccion6 <= ClienteActual.getCodigosDescuento().size()) {
					ClienteActual.getCodigosDescuento().remove(eleccion6-1);
					System.out.println("•Perfeto, se le asignará un descuento del 20% al precio de los juegos");
					espera(2500);
					barraCarga("Aplicando descuento");
					Arkade.AplicarDescuentoJuegos();
					caso = false;
				}
				else if (eleccion6 == (ClienteActual.getCodigosDescuento().size()+1)) {
					barraCarga("Ingresando a juegos");
					caso = false;
				}
				else if (eleccion6 == (ClienteActual.getCodigosDescuento().size()+2)){
					barraCarga("Saliendo");
					Administrador.salirDelSistema();
				}

				else {
					System.out.println("Opcion invalida");
				}
			}catch(InputMismatchException e) {
				System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
				Administrador.sc.nextLine();
			}

		}while(caso);
		
		
		String game = null;
		do {
			
			try {
				
				System.out.println(Arkade.mostrarJuegos());
				int eleccion7 =(int)Administrador.readLong();
				switch(eleccion7) {
				case 1:
					if (ClienteActual.getCuenta().getSaldo()>=Administrador.game1.getValorServicio()) {
						ClienteActual.getCuenta().hacerPago(Administrador.game1.getValorServicio());
						System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game1.getGeneroServicio());
						game = juego(new String[]{"BUM"});
						System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
					}
					else {
						boolean finWhile = true;
						while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);
							case 2: ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
						
					}
					boolean finWhile1 = true;
					while (finWhile1) {
						System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
						int eleccion8 = (int)Administrador.readLong();
						if (eleccion8==1) {
							finWhile1 = false;
						}
						else if (eleccion8==2) {
							caso = true;
							finWhile1= false;
						}
					}	
					break;
				case 2:
					if (ClienteActual.getCuenta().getSaldo()>=Administrador.game2.getValorServicio()) {
						ClienteActual.getCuenta().hacerPago(Administrador.game2.getValorServicio());
						System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game2.getGeneroServicio());
						game = juego(new String[]{"BOO"});
						System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
					}
					else {
						boolean finWhile = true;
						while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);
							case 2: ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
						
					}
					boolean finWhile2 = true;
					while (finWhile2) {
						System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
						int eleccion8 = (int)Administrador.readLong();
						if (eleccion8==1) {
							finWhile2 = false;
						}
						else if (eleccion8==2) {
							caso = true;
							finWhile2= false;
						}
					}	
					break;
				
				case 3:
					if (ClienteActual.getCuenta().getSaldo()>=Administrador.game3.getValorServicio()) {
						ClienteActual.getCuenta().hacerPago(Administrador.game3.getValorServicio());
						System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game3.getGeneroServicio());
						game = juego(new String[]{"POO"});
						System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
					}
					else {
						boolean finWhile = true;
						while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine();Administrador.inicio(ClienteActual, sucursalCineProceso);
							case 2: ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
						
					}
					boolean finWhile3 = true;
					while (finWhile3) {
						System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
						int eleccion8 = (int)Administrador.readLong();
						if (eleccion8==1) {
							finWhile3 = false;
						}
						else if (eleccion8==2) {
							caso = true;
							finWhile3= false;
						}
					}	
					break;
				case 4:
					if (ClienteActual.getCuenta().getSaldo()>=Administrador.game4.getValorServicio()) {
						ClienteActual.getCuenta().hacerPago(Administrador.game4.getValorServicio());
						System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game4.getGeneroServicio());
						game = juego(new String[]{"JAJA"});
						System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
					}
					else {
						boolean finWhile = true;
						while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine(); Administrador.inicio(ClienteActual, sucursalCineProceso);
							case 2: ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
						
					}
					boolean finWhile4 = true;
					while (finWhile4) {
						System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
						int eleccion8 = (int)Administrador.readLong();
						if (eleccion8==1) {
							finWhile4 = false;
						}
						else if (eleccion8==2) {
							caso = true;
							finWhile4= false;
						}
					}	
					break;
				case 5:
					if (ClienteActual.getCuenta().getSaldo()>=Administrador.game5.getValorServicio()) {
						ClienteActual.getCuenta().hacerPago(Administrador.game5.getValorServicio());
						System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game5.getGeneroServicio());
						game = juego(new String[]{"CRY"});
						System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
					}
					else {
						boolean finWhile = true;
						while (finWhile) {
						System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
						int option = (int)Administrador.readLong();
							switch(option) {
							case 1: Administrador.sc.nextLine(); Administrador.inicio(ClienteActual, sucursalCineProceso);
							case 2: ingresoZonaJuegos(ClienteActual, sucursalCineProceso);
							case 3: Administrador.salirDelSistema();
							default:  break;
							}
						}
						
					}
					boolean finWhile5 = true;
					while (finWhile5) {
						try {
							System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
							int eleccion8 = (int)Administrador.readLong();
							if (eleccion8==1) {
								finWhile5 = false;
							}
							else if (eleccion8==2) {
								caso = true;
								finWhile5= false;
							}
						}catch(InputMismatchException e) {
								System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
								Administrador.sc.nextLine();
						}
					}	
					break;
				case 6: barraCarga("Volviendo"); Administrador.sc.nextLine(); Administrador.inicio(ClienteActual, sucursalCineProceso); break;
				case 7 : barraCarga("Saliendo"); Administrador.salirDelSistema(); break;
				default: break;
				}
			}catch(InputMismatchException e) {
				System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
				Administrador.sc.nextLine();

			}
		}while(!caso);
		
		//System.out.println(game);
		
		Random random = new Random();
		double puntuacion = 0;
		if (game.equals("win")) {
			puntuacion = 10;
		}
		else if (game.equals("defeat")) {
			puntuacion = Math.round((random.nextDouble() * 10) * 100.0) / 100.0;
		}
		
		System.out.println("•Tu puntuacion es: "+puntuacion);
		
		Administrador.sc.nextLine();
	}
	
	
	
	
	/**
	 * Description : Este método se encarga de simular una barra de carga haciendo una serie de prints por pantalla
	 * @param word : Este método recibe como parámetro una palabra que se muestra en la barra de carga
	 * (De tipo String)
	 * @return <b>void</b> : No hay retorno
	 * */
	public static void barraCarga(String word) {
		System.out.println("               "+word+"....");
        
        for (int i=0;i<42 ;i++ ) {
        	try {
                Thread.sleep(19);
                System.out.print("|");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" ");
	}
	
	
	/**
	 * Description : Este método se encarga de imprimir por pantalla la tarjetaCinemar del usuario, con su nombre y saldo correspondiente
	 * @param nombre : Este método recibe como parámetro el nombre del usuario
	 * (De tipo String)
	 * @param saldo : Este método recibe como parámetro el saldo de la tarjeta del usuario
	 * (De tipo double)
	 * @return <b>void</b> : No hay retorno
	 * */
	public static void imprimirTarjeta(String nombre, double saldo) {
    	System.out.println("\n        ╔══════════════════════════╗");
        System.out.println("        ║      Tarjeta Cinemar     ║");
        System.out.println("        ╠══════════════════════════╣");
        String linea = "        ║ Dueño: "+nombre;
        for (int i = linea.length();i<36; i++) {
        	if (i ==35) {
        		linea = linea+"║";
        	}
        	else {linea = linea+" ";} 
        }
        System.out.println(linea);
        String line = "        ║ Saldo: $"+saldo;
        for (int i = line.length();i<38; i++) {
        	if (i ==35) {
        		line = line+"║";
        	}
        	else {line = line+" ";} 
        }
        System.out.println(line);
        System.out.println("        ╚══════════════════════════╝\n");
    }
	
	
	/**
	 * Description : Este método se encarga de retrasar determinado tiempo la ejecucion del programa mediante una espera
	 * @param time : Este método recibe como parámetro el numero de milisegundos a esperar
	 * (De tipo int)
	 * @return <b>void</b> : No hay retorno
	 * */
	public static void espera(int time) {
		try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Description : Este método se encarga de ejecutar el juego de la funcionalidad 4, el cual es un HangMan en donde se escoje una palabra aleatoria del parametro
	 * @param PALABRAS : Este método recibe como parámetro una lista de la palabras con las que se jugara para adivinarlas
	 * (De tipo String)
	 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si el usuario acertó palabra o no
	 * */
	public static String juego(String[] PALABRAS) {
        String match = null;
   
        Scanner scanner = new Scanner(System.in);
        String palabraSecreta = PALABRAS[(int) (Math.random() * PALABRAS.length)];
        char[] palabraAdivinada = new char[palabraSecreta.length()];
        String letrasUsadas = ""; // Para rastrear letras ya usadas
        int intentosRestantes = 7;

        for (int i = 0; i < palabraAdivinada.length; i++) {
            palabraAdivinada[i] = '_';
        }

        while (intentosRestantes > 0 && !adivinado(palabraAdivinada)) {
            System.out.println("Intentos restantes: " + intentosRestantes);
            System.out.print("Palabra: ");
            for (char c : palabraAdivinada) {
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("Ingresa una letra: ");
            String input = scanner.nextLine().toUpperCase();
            if (input.length() != 1 || !esLetraValida(input.charAt(0))) {
                System.out.println("Carácter inválido. Intenta otra vez.");
                intentosRestantes--;
                continue;
            }

            char letra = input.charAt(0);

            // Verificar si la letra ya fue usada
            if (letrasUsadas.contains(String.valueOf(letra))) {
                System.out.println("Ya has usado esa letra. Intenta otra.");
                continue;
            }

            letrasUsadas += letra;

            if (palabraSecreta.indexOf(letra) >= 0) {
                for (int i = 0; i < palabraSecreta.length(); i++) {
                    if (palabraSecreta.charAt(i) == letra) {
                        palabraAdivinada[i] = letra;
                    }
                }
            } else {
                System.out.println("¡Incorrecto! La letra '" + letra + "' no está en la palabra.");
                intentosRestantes--;
            }
        }

        if (adivinado(palabraAdivinada)) {
            System.out.println("¡Felicidades! ¡Has adivinado la palabra!: " + palabraSecreta);
            match = "win";
        } else {
            System.out.println("¡Oh no! Te has quedado sin intentos. La palabra era: " + palabraSecreta);
            match = "defeat";
        }
        return match;
    }
	
	
	
	/**
	 * Description : Este método se encarga verificar si la lista de char dada en el parametro no contiene el caracter "_" para verificar si la palabra fue adivinada
	 * @param palabraAdivinada : Este método recibe como parámetro una lista
	 * (De tipo char)
	 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si cumple la condicion
	 * */
	private static boolean adivinado(char[] palabraAdivinada) {
        for (char c : palabraAdivinada) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
	
	
	/**
	 * Description : Este método se encarga verificar si el parametro es una letra del alfabeto español, incluyendo ñ
	 * @param caracter : Este método recibe como parámetro un caracter que es ingresado por el usuario
	 * (De tipo char)
	 * @return <b>boolean</b> : Este método retorna un boolean true or false dependiendo de si cumple la condicion de ser una letra del alfabeto
	 * */
	public static boolean esLetraValida(char caracter) {
       
        return (caracter >= 'A' && caracter <= 'Z') || caracter == 'Ñ';
    }
}




