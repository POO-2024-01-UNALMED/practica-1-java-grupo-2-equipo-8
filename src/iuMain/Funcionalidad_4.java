package iuMain;

import gestionAplicacion.servicios.ServicioEntretenimiento;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TipoDeDocumento;

public class Funcionalidad_4 {
	
	static void ingresoZonaJuegos(Cliente ClienteActual) {

		System.out.println("\nRecuerde que para entrar a los juegos es necesario tener la tarjeta cinemar\n¿Desea Continuar o volver?\n1.Ingresar\n2.Volver al menú principal\n3.Salir");
		int opcion = (int)Administrador.readLong();
		if (opcion==2) {Administrador.inicio(ClienteActual);}
		else if (opcion==1) {}
		else if (opcion==3) {Administrador.salirDelSistema();}
		else {System.out.println("\nOpcion Invalida");ingresoZonaJuegos(ClienteActual);}
		boolean casoValido = true;	
			
	
		if (!ClienteActual.verificarCuenta()) {
			if (ServicioEntretenimiento.verificarTarjetasEnInventario()) {
				System.out.println("\nEl precio de la tarjeta Cinemar es de 5000 pesos\nEste valor sera descontado al saldo de su tarjeta");
				ServicioEntretenimiento.asociarTarjetaCliente(ClienteActual);
				ClienteActual.getCuenta().hacerPago(5000);
				System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+ClienteActual.getCuenta().getDueno().getNombre()+"\nSaldo: $"+ClienteActual.getCuenta().getSaldo());
			}
			else {System.out.println("\nLo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde"); Administrador.inicio(ClienteActual);}
			casoValido=true;
		}
		else {
			System.out.println("\nEstos son los datos de su tarjeta:\nDueño: "+ClienteActual.getCuenta().getDueno().getNombre()+"\nSaldo: $"+ClienteActual.getCuenta().getSaldo());
		}
		
			


		System.out.println(ClienteActual.getNombre()+"\n"+ClienteActual.getDocumento()+"\n");
		do {
			System.out.println("¿Deseas recargar la tarjeta?");
			System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir");
			int eleccion1 = (int)Administrador.readLong();
			boolean finCiclo = true;
			if (eleccion1==1) {
				
				System.out.println("Cada metodo de pago tiene un monto maximo para recargar, en caso de superar este monto debera elegir otro metodo de pago");
				MetodoPago.asignarMetodosDePago(ClienteActual);
				double maximo = ClienteActual.getMetodosDePago().get(0).getLimiteMaximoPago();
				double maximo2 = ClienteActual.getMetodosDePago().get(1).getLimiteMaximoPago();
				double maximo3 = ClienteActual.getMetodosDePago().get(2).getLimiteMaximoPago();
				double maximo4 = ClienteActual.getMetodosDePago().get(3).getLimiteMaximoPago();
				System.out.println(MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
				int eleccion2 = (int)Administrador.readLong();
				System.out.println("¿Cuanto desea recargar?\n");
				double eleccion3 = (double)Administrador.readLong();
				while (finCiclo) {
					
					switch (eleccion2) {
					
					case 1:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave = (int) Administrador.readLong();
						
						MetodoPago metodoPagoCliente = ServicioEntretenimiento.encontrarMetodoPagoCliente("Bancolombia", ClienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente.getLimiteMaximoPago()) {
							ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente.getLimiteMaximoPago();
							metodoPagoCliente.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
							eleccion2 = (int)Administrador.readLong();
						}
						else {
							ClienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 2:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave2 = (int) Administrador.readLong();
						
						MetodoPago metodoPagoCliente2 = ServicioEntretenimiento.encontrarMetodoPagoCliente("AV Villas", ClienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente2.getLimiteMaximoPago()) {
							ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente2.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente2.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente2.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente2.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente2.getLimiteMaximoPago();
							metodoPagoCliente2.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
							eleccion2 = (int)Administrador.readLong();
						}
						else {
							ClienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 3:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave3 = (int) Administrador.readLong();
						
						MetodoPago metodoPagoCliente3 = ServicioEntretenimiento.encontrarMetodoPagoCliente("Banco Agrario", ClienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente3.getLimiteMaximoPago()) {
							ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente3.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente3.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente3.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente3.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente3.getLimiteMaximoPago();
							metodoPagoCliente3.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
							eleccion2 = (int)Administrador.readLong();
						}
						else {
							ClienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					case 4:
						System.out.print("Ingrese contraseña (4 digitos): ");
						int clave4 = (int) Administrador.readLong();
						
						MetodoPago metodoPagoCliente4 = ServicioEntretenimiento.encontrarMetodoPagoCliente("Efectivo", ClienteActual.getMetodosDePago());
						
						if (eleccion3>metodoPagoCliente4.getLimiteMaximoPago()) {
							ClienteActual.getCuenta().ingresarSaldo(metodoPagoCliente4.getLimiteMaximoPago());
							System.out.println("\nSe han recargado: $"+metodoPagoCliente4.getLimiteMaximoPago()+" exitosamente,\nSin embargo el valor a recargar ha superado el limite permitido por "+metodoPagoCliente4.getNombre()+
									"\nPor favor otro metodo de pago para pagar los $"+(eleccion3-metodoPagoCliente4.getLimiteMaximoPago())+
									" restantes");
							
							eleccion3 = (double)eleccion3-metodoPagoCliente4.getLimiteMaximoPago();
							metodoPagoCliente4.setLimiteMaximoPago(0);
							System.out.println("\n"+MetodoPago.mostrarMetodosDePago(ClienteActual.getMetodosDePago()));
							eleccion2 = (int)Administrador.readLong();
						}
						else {
							ClienteActual.getCuenta().ingresarSaldo(eleccion3);
							System.out.println("\nSe han recargado: $"+eleccion3+" exitosamente");
							finCiclo = false;
						}
						break;
					default:
						System.out.println("Opcion invalida");
						finCiclo = false;
						break;
					}
					
						
						
				}
				
				ClienteActual.restablecerLimiteMaximo(maximo, maximo2, maximo3, maximo4);
				System.out.println("\nEl saldo de su tarjeta cinemar es: "+ClienteActual.getCuenta().getSaldo());
				while(!finCiclo) {
					System.out.println("Desea ingresar a los juegos, Volver a recargar la tarjeta, volver al inicio o Salir");
					System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
					int eleccion4 = (int)Administrador.readLong();
					switch (eleccion4) {
					case 1: casoValido= false;finCiclo= true; break;
					case 2: finCiclo= true; break;
					case 3: Administrador.inicio(ClienteActual);
					case 4: Administrador.salirDelSistema();
					default: finCiclo = false; break; 
				}
				}
				
			}
			else if (eleccion1==2) {
				System.out.println("Recuerde que debe tener saldo para acceder a los diferentes juegos\nSu saldo en Tarjeta Cinemar: "+ClienteActual.getCuenta().getSaldo());
				while(finCiclo) {
					System.out.println("Desea ingresar a los juegos, recargar la tarjeta, volver al inicio o Salir");
					System.out.println("1.Ingresar\n2.Recargar tarjeta cinemar\n3.Volver al menu principal\n4.Salir");
					int eleccion4 = (int)Administrador.readLong();
					switch (eleccion4) {
					case 1: casoValido= false;finCiclo= false; break;
					case 2: finCiclo= false; break;
					case 3: Administrador.inicio(ClienteActual);
					case 4: Administrador.salirDelSistema();
					default: finCiclo = true; break; 
					}
				}
			}
			else if (eleccion1==3) {
				Administrador.inicio(ClienteActual);
			}
			else if (eleccion1==4) {
				Administrador.salirDelSistema();
			}
			else {System.out.println("Opcion invalida");}
		}while(casoValido);
		
		boolean caso = true;
		do {
			System.out.println("\n¿Has realizado alguna compra de un tiquete de cine y no has redimido su codigo?\n1.SI\n2.NO\n3.Salir");
			int eleccion6 = (int)Administrador.readLong();
			if (eleccion6==1) {
				System.out.println("\nIngrese el codigo asociado a la factura de su ticket\nRecuerde que el codigo es el formato de la pelicula pegado a su tipo de documento y el numero de la salaDecine de el tiquete\nEjemplo: 4DCC12 (Todo en mayuscula)");
				String codigo = Administrador.readLn();
				if (ServicioEntretenimiento.comprobarCodigo(codigo)) {
					System.out.println("Codigo correcto, Se le asignó un descuento a el precio de los juegos");
					ServicioEntretenimiento.AplicarDescuentoJuegos();
					caso = false;
				}
				else {System.out.println("Codigo incorrecto, verifique el codigo");}
				
			}
			else if (eleccion6==2) {
				caso = false;
			}
			else if (eleccion6==3) {
				Administrador.salirDelSistema();
			}
			else {
				System.out.println("Opcion invalida");
			}
		}while(caso);
		
		do {
			System.out.println(ServicioEntretenimiento.mostrarJuegos());
			int eleccion7 =(int)Administrador.readLong();
			switch(eleccion7) {
			case 1:
				if (ClienteActual.getCuenta().getSaldo()>=Administrador.game1.getValorServicio()) {
					ClienteActual.getCuenta().hacerPago(Administrador.game1.getValorServicio());
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+Administrador.game1.getGeneroServicio());
					ServicioEntretenimiento.juego(new String[]{"ARMA", "COMBATE", "EXPLOSION","GUERRA","ADRENALINA"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)Administrador.readLong();
						switch(option) {
						case 1: Administrador.inicio(ClienteActual);
						case 2: ingresoZonaJuegos(ClienteActual);
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
					ServicioEntretenimiento.juego(new String[]{"FANTASMA", "MOUNSTRUO", "MUERTE","ZOMBI","CEMENTERIO"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)Administrador.readLong();
						switch(option) {
						case 1: Administrador.inicio(ClienteActual);
						case 2: ingresoZonaJuegos(ClienteActual);
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
					ServicioEntretenimiento.juego(new String[]{"OBJETO", "GUZMAN", "HERENCIA","CONSTRUCTOR","JAVA"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)Administrador.readLong();
						switch(option) {
						case 1: Administrador.inicio(ClienteActual);
						case 2: ingresoZonaJuegos(ClienteActual);
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
					ServicioEntretenimiento.juego(new String[]{"DIVERSION", "RISAS", "CHISTE","PAYASO","GRACIOSO"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)Administrador.readLong();
						switch(option) {
						case 1: Administrador.inicio(ClienteActual);
						case 2: ingresoZonaJuegos(ClienteActual);
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
					ServicioEntretenimiento.juego(new String[]{"SHOW", "EMOCIONES", "MUJERES","LAGRIMAS","CONMOVEDOR"});
					System.out.println("\nEl nuevo saldo de tu tajeta cinemar es: $"+ClienteActual.getCuenta().getSaldo());
				}
				else {
					boolean finWhile = true;
					while (finWhile) {
					System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver para recargar tarjeta\n3.Salir");
					int option = (int)Administrador.readLong();
						switch(option) {
						case 1: Administrador.inicio(ClienteActual);
						case 2: ingresoZonaJuegos(ClienteActual);
						case 3: Administrador.salirDelSistema();
						default:  break;
						}
					}
					
				}
				boolean finWhile5 = true;
				while (finWhile5) {
					System.out.println("\n¿Desea volver a jugar?\n1.SI\n2.NO");
					int eleccion8 = (int)Administrador.readLong();
					if (eleccion8==1) {
						finWhile5 = false;
					}
					else if (eleccion8==2) {
						caso = true;
						finWhile5= false;
					}
				}	
				break;
			default: break;
			}	
		}while(!caso);
	}
}
