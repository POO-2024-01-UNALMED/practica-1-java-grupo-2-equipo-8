package iuMain;

import java.util.InputMismatchException; 
import java.util.Scanner;
import java.util.Random;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.Ticket;


public class Funcionalidad_4 {
	
	
	
	/**
	 * Description : Este método se encarga de toda la gestion de la funcionalidad 4, que incluye adquisicion y recarga de la tarjeta cinemar, ingreso a zona de juegos, ejecucion de juegos, generacion de bonos, etc.
	 * @param clienteActual : Este método recibe como parámetro el cliente actual que esta ejecutando el programa
	 * (De tipo Cliente)
	 * @param sucursalCineProceso : Este método recibe como parámetro la sucursal de cine en la que se esta ejecutando el programa
	 * (De tipo SucursalCine)
	 * @return <b>void</b> : No hay retorno
	 * */
	static void ingresoZonaJuegos(Cliente ClienteActual) {
		
		
		//Se muestra el menu de opciones que el usurio desea realizar 
		try {
			System.out.println("\n☺Recuerde que para entrar a los juegos es necesario tener la tarjeta cinemar☺\nDesea:\n1.Ingresar\n2.Volver al menú principal\n3.Salir");
			int opcion = (int)Administrador.readLong();
			if (opcion==2) {barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual);}//volver
			else if (opcion==1) {barraCarga("Ingresando");}//ingresar
			else if (opcion==3) {barraCarga("Saliendo");Administrador.salirDelSistema();}//salir
			else {System.out.println("\nOpcion Invalida");ingresoZonaJuegos(ClienteActual);}	
		
		}catch(InputMismatchException e) {
			System.out.println("Error, debe ingresar un único dato numérico entre los disponibles");
			Administrador.sc.nextLine(); 
			ingresoZonaJuegos(ClienteActual);
		}
		
		boolean casoValido = true;
		
		do {
			if (!ClienteActual.verificarCuenta()) {
				try {
					System.out.println("\n•No tienes una Tarjetaya Cinemar asociada, ¿Deseas Adquirirla?\n1. SI\n2. NO");
					espera(1000);
					int option = (int)Administrador.readLong();
					if (option==1) {
						if (Arkade.verificarTarjetasEnInventario(ClienteActual.getCineActual())) { // se verifica si hay tarjetas disponibles por vender en el array
							barraCarga("Adquiriendo tarjeta");
							System.out.println("\n•El precio de la tarjeta Cinemar es de $5000\n•Este valor sera descontado al saldo de su tarjeta");
							espera(2000);
							//
							//Aca se asocia la primera tarjeta en el array de disponibles al cliente, se le descuenta el valor de la tarjeta y se imprime por pantalla
							Arkade.asociarTarjetaCliente(ClienteActual);
							ClienteActual.getCuenta().hacerPago(5000);
							System.out.println("\n•Su tarjeta:");
							espera(1000);
							imprimirTarjeta(ClienteActual.getCuenta().getDueno().getNombre(),ClienteActual.getCuenta().getSaldo());
							casoValido = false;
							//
						}
						else {System.out.println("\n•Lo sentimos, en este momento no hay tarjetas disponibles, vuelva mas tarde");Administrador.sc.nextLine(); Administrador.inicio(ClienteActual);}
					
					}
					else if (option ==2) {ingresoZonaJuegos(ClienteActual);}
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
						
		
		int eleccion1 = 0;
		boolean finCiclo = true;
		
		boolean Vcase = true;
		do {
			do {
				
				try {
					System.out.println("•¿Deseas recargar la tarjeta?");
					System.out.println("1. SI\n2. NO\n3. Volver al menú principal\n4. Salir");
					eleccion1 = (int)Administrador.readLong();
					if (eleccion1==1 || eleccion1==2 || eleccion1==3 || eleccion1==4) {
						casoValido = false;
					}
					else {
						System.out.println("•Opcion invalida");
						casoValido = true;
					}
	
				}catch(InputMismatchException e) {
					System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
					Administrador.sc.nextLine();
					casoValido = true;
				}
			}while(casoValido);

			
			//Inicio proceso de pago
			double recargaMaxima = 0;
			double valorRecarga = 0;
			
			for (MetodoPago metodo : ClienteActual.getMetodosDePago()) {
				recargaMaxima+= metodo.getLimiteMaximoPago();
			}
		
		
		
			if(eleccion1 == 1) {
				do {
					try {
						
							
							valorRecarga = 0;
							
							System.out.println("•El valor maximo a recargar por proceso es: $"+recargaMaxima+" intente no superar este valor");
							//System.out.println("•Ademas Cada metodo de pago tiene un monto maximo para recargar, en caso de superar este monto debera elegir otro metodo de pago para completar la recarga");
							System.out.print("•Digite el valor a recargar: ");
							valorRecarga = Administrador.readLong();
							
							if (!(valorRecarga<= recargaMaxima & valorRecarga>0)) {
								System.out.println("\n•El valor ingresado supera el limite maximo de recarga ");
							}
							
						
					}catch(InputMismatchException e) {
						System.out.println("\nError en el proceso de recarga, debe ingresar un dato numerico, vuelva a realizar el proceso");
						Administrador.sc.nextLine();
						casoValido = true;
					}
				}while(!(valorRecarga<= recargaMaxima & valorRecarga>0)); 
			
				casoValido = true;
				int opcionPago = 0;
				double precioRecargaProceso = valorRecarga;
				double totalPagado = 0;
				do {
					do {
						try {
							System.out.println("\n•Escoja su metodo de pago");
							System.out.println("•Cada metodo de pago tiene un monto maximo para recargar y un descuento asociado, en caso de superar este monto debera elegir otro metodo de pago para completar la recarga");
							System.out.println(MetodoPago.mostrarMetodosDePago(ClienteActual));
							opcionPago = (int)Administrador.readLong();
							
							if(!(opcionPago > 0 & opcionPago <= ClienteActual.getMetodosDePago().size() )) {
								System.out.println("•Opcion Invalida");
							}
						}catch(InputMismatchException e) {
							System.out.println("\nError en el proceso de recarga, debe ingresar un dato numerico, vuelva a realizar el proceso");
							Administrador.sc.nextLine();
							casoValido = true;
							opcionPago = 0;
						}
					}while(!(opcionPago > 0 & opcionPago <= ClienteActual.getMetodosDePago().size() ));
					
					MetodoPago metodoPagoProceso = null;
					metodoPagoProceso = ClienteActual.getMetodosDePago().get(opcionPago - 1);
					
					
					
					System.out.println("El método de pago escogido es: " + metodoPagoProceso.getNombre() 
					+ " ( Precio anterior: " + precioRecargaProceso+ " -> Precio actual: " + precioRecargaProceso * (1 - metodoPagoProceso.getDescuentoAsociado()) + " )");
		
					espera(2000);
		
		
					if(metodoPagoProceso.realizarPago(precioRecargaProceso, ClienteActual) == 0) {
						
						totalPagado+=precioRecargaProceso * (1 - metodoPagoProceso.getDescuentoAsociado());
						barraCarga("Procesando pago");
						System.out.println("Pago exitoso, se han recargado "+ valorRecarga+" y usted ha pagado "+ totalPagado+" equivalente a un descuento de "+ String.format("%.2f",(100-((totalPagado*100)/valorRecarga)))+ "%");
						MetodoPago.asignarMetodosDePago(ClienteActual);
						ClienteActual.getCuenta().ingresarSaldo(valorRecarga);
						System.out.println("\n•Su tarjeta:");
						imprimirTarjeta(ClienteActual.getNombre(), ClienteActual.getCuenta().getSaldo());
						totalPagado =0;
						int eleccionUser = 0;
						do {
							try {
								eleccionUser = 0;
								System.out.println("\nDesea: \n1. Ingresar a los juegos\n2. Volver a recargar tarjeta\n3. Volver al inicio\n4. Salir");
								eleccionUser = (int)Administrador.readLong();
								switch(eleccionUser) {
								case 1: barraCarga("Ingresando");casoValido = false; Vcase = false; break;
								case 2: barraCarga("Redirigiendo"); casoValido = false; Vcase = true ; break;
								case 3: barraCarga("Volviendo"); Administrador.inicio(ClienteActual); break;
								case 4: barraCarga("Saliendo"); Administrador.salirDelSistema(); break;
								}
							}catch(InputMismatchException e) {
								System.out.println("\nError en el proceso de recarga, debe ingresar un dato numerico, vuelva a realizar el proceso");
								Administrador.sc.nextLine();
								eleccionUser = 0;
							}
						}while(eleccionUser !=1 & eleccionUser !=2 & eleccionUser !=3 & eleccionUser !=4);
						
					}
					else {
						totalPagado+= metodoPagoProceso.getLimiteMaximoPago();
						precioRecargaProceso = metodoPagoProceso.realizarPago(precioRecargaProceso, ClienteActual);
						espera(1000);
						barraCarga("Procesando Pago");
						
						System.out.println("•Proceso exitoso, sin embargo, tiene un saldo pendiente por recargar de : " + precioRecargaProceso);
						System.out.println("•Nota: si no culmina con el pago completo, no se recargara nada a la tarjeta");
						casoValido = true;
					}
				}while(casoValido);
			}

	///////////////////////////////////////////////////////////////////////////////////////////////
			
	
			else if (eleccion1==2) {
				System.out.println("\n-----------------------------------------------------------------------------");
		        System.out.println("•Recuerde que debe tener saldo para acceder a los diferentes juegos•");
		        System.out.println("-----------------------------------------------------------------------------");
		        System.out.println("\n•Su tarjeta:");
		        espera(2000);
				imprimirTarjeta(ClienteActual.getNombre(),ClienteActual.getCuenta().getSaldo());
				finCiclo = true;
				while(finCiclo) {
					try {
						System.out.println("\nDesea: ");
						System.out.println("1. Ingresar a los juegos\n2. Recargar tarjeta cinemar\n3. Volver al menu principal\n4. Salir");
						int eleccion4 = (int)Administrador.readLong();
						switch (eleccion4) {
						case 1: barraCarga("Ingresando");Vcase= false;finCiclo= false; break;
						case 2: barraCarga("Redirigiendo");Vcase = true;finCiclo= false; break;
						case 3: barraCarga("Volviendo");Administrador.sc.nextLine();Administrador.inicio(ClienteActual);
						case 4: barraCarga("Saliendo");Administrador.salirDelSistema();
						default: finCiclo = true; break; 
						}
						
					}catch(InputMismatchException e) {
						System.out.println("\nError en el proceso de recarga, debe ingresar un dato numerico, vuelva a realizar el proceso");
						Administrador.sc.nextLine();
					}
				}
			}
			else if (eleccion1==3) {
				barraCarga("Volviendo");
				Administrador.sc.nextLine();
				Administrador.inicio(ClienteActual);
			}
			else if (eleccion1==4) {
				barraCarga("Saliendo");
				Administrador.salirDelSistema();
			}
			
			
		}while(Vcase);
		
		//fin proceso de pago
		
		
		
		
		//Inicio proceso de codigos de descuento
		boolean caso = true;
		boolean redimioCodigo = false;
		String generoCodigoPelicula = null;
		
		do {
			
			
			try {
				espera(1500);
				redimioCodigo = false;
				generoCodigoPelicula = null;
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
					generoCodigoPelicula = Ticket.encontrarGeneroCodigoPelicula(ClienteActual.getCodigosDescuento().get(eleccion6-1));
					ClienteActual.getCodigosDescuento().remove(eleccion6-1);
					System.out.println("•Perfeto, se le asignará un descuento del 20% al precio de los juegos con categoria "+ generoCodigoPelicula);
					espera(2500);
					barraCarga("Aplicando descuento");
					Arkade.AplicarDescuentoJuegos(generoCodigoPelicula);
					caso = false;
					redimioCodigo = true;
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
		
		
		
		//inicio proceso de juegos
		String game = null;
		String generoJuego = null;
		
		do {
			int eleccion7 = 0;
			try {
				generoJuego = null;
				System.out.println(Arkade.mostrarJuegos());
				eleccion7 =(int)Administrador.readLong();
				

			}catch(InputMismatchException e) {
				System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
				Administrador.sc.nextLine();
			}
			
			if (eleccion7 > 0 & eleccion7<=SucursalCine.getJuegos().size()) {
				if (ClienteActual.getCuenta().getSaldo()>=SucursalCine.getJuegos().get(eleccion7-1).getValorServicio()) {
					
					ClienteActual.getCuenta().hacerPago(SucursalCine.getJuegos().get(eleccion7-1).getValorServicio());
					System.out.println("•El juego esta por comenzar, el nuevo saldo de tu tarjeta cinemar es : " + ClienteActual.getCuenta().getSaldo());
					espera(2000);
					barraCarga("Iniciando");
					generoJuego = SucursalCine.getJuegos().get(eleccion7-1).getGeneroServicio();
					System.out.println("¡☺ EL JUEGO HA EMPEZADO ☺!\nAdivina la palabra relacionada con la categoria: "+generoJuego);
					switch(generoJuego) {
					
					case "Acción": game = juego(new String[]{"BUM"}); break;
					case "Terror": game = juego(new String[]{"BOO"}); break;
					case "Tecnología" : game = juego(new String[]{"POO"}); break;
					case "Comedia": game = juego(new String[]{"JAJA"}); break;
					case "Drama": game = juego(new String[]{"CRY"}); break;
						
					}
					
					boolean finWhile1 = true;
					try {
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
					}catch(InputMismatchException e) {
						System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
						Administrador.sc.nextLine();
					}
					
					
				}
				else {
					
					boolean finWhile = true;
					try {
						while (finWhile) {
							System.out.println("Tu tarjeta no tiene saldo suficiente, por favor vuelva a ingresar para recargarla\n1.Volver al menu principal\n2.Volver a ingresar para recargar tarjeta\n3.Salir");
							int option = (int)Administrador.readLong();
								switch(option) {
								case 1: Administrador.sc.nextLine();Administrador.inicio(ClienteActual);
								case 2: ingresoZonaJuegos(ClienteActual);
								case 3: Administrador.salirDelSistema();
								default:  break;
								}
							}
					}catch(InputMismatchException e) {
						System.out.println("\nError en el proceso, debe ingresar un dato numerico, vuelva a realizar el proceso");
						Administrador.sc.nextLine();
					}
					
					
				}
			}
			else if (eleccion7 == (SucursalCine.getJuegos().size()+1)) {
				barraCarga("Volviendo");
				Administrador.sc.nextLine();
				Administrador.inicio(ClienteActual);
			}
			else if (eleccion7 ==(SucursalCine.getJuegos().size()+2)) {
				barraCarga("Saliendo");
				Administrador.salirDelSistema();
			}
		}while(!caso);
		
		
		
		

		//inicio proceso de generacion de bonos
		Random random = new Random();
		double puntuacion = 0;
		if (game.equals("win")) {
			puntuacion = Arkade.getPuntuacionMaxima();
		}
		else if (game.equals("defeat")) {
			puntuacion = Math.round((random.nextDouble() * 10) * 100.0) / 100.0;
		}
		
		System.out.println("•Tu puntuacion es: "+puntuacion);
		
		Bono bonoCliente = null;
		String codigoBono = null;
		
		if (puntuacion==10.0) {
			if (redimioCodigo) {
				if (generoJuego.equals(generoCodigoPelicula)) {
					System.out.println("\nGanas un bono de comida por obtener la puntuacion maxima en un juego de tipo "+generoJuego+" y redimir un codigo de pelicula del mismo genero");
					barraCarga("Generando bono");
					espera(3000);
					bonoCliente = Arkade.generarBonoComidaJuegos(ClienteActual.getCineActual());
					
					if (!(bonoCliente == null)) {
						codigoBono = bonoCliente.getCodigo();
						ClienteActual.getCodigosBonos().add(codigoBono);
						ClienteActual.getBonos().add(bonoCliente);
						//Bono.getBonosCreados().add(bonoCliente); //Se añade al crear el bono por el constructor
						ClienteActual.getCineActual().getBonosCreados().add(bonoCliente);
						
						System.out.println("•Reclama el bono con el codigo en nuestro servicio de comida");
					}
				}
				else {
					System.out.println("\nGanas un bono de souvenirs por obtener la puntuacion maxima, !Felicidades¡");
					barraCarga("Generando bono");
					espera(3000);
					bonoCliente = Arkade.generarBonoSouvenirJuegos(ClienteActual.getCineActual());
					if (!(bonoCliente == null)) {
						codigoBono = bonoCliente.getCodigo();
						ClienteActual.getCodigosBonos().add(codigoBono);
						ClienteActual.getBonos().add(bonoCliente);
						//Bono.getBonosCreados().add(bonoCliente); //Se añade al crear el bono por el constructor
						ClienteActual.getCineActual().getBonosCreados().add(bonoCliente);
						
						System.out.println("•Reclama el bono con el codigo en nuestro servicio de souvenirs");
					}

				}
			}
			else {
				System.out.println("\nGanas un bono de souvenirs por obtener la puntuacion maxima, !Felicidades¡");
				barraCarga("Generando bono");
				espera(3000);
				bonoCliente = Arkade.generarBonoSouvenirJuegos(ClienteActual.getCineActual());
				if (!(bonoCliente == null)) {
					codigoBono = bonoCliente.getCodigo();
					ClienteActual.getCodigosBonos().add(codigoBono);
					ClienteActual.getBonos().add(bonoCliente);
					//Bono.getBonosCreados().add(bonoCliente); //Se añade al crear el bono por el constructor
					ClienteActual.getCineActual().getBonosCreados().add(bonoCliente);
					
					System.out.println("•Reclama el bono con el codigo en nuestro servicio de souvenirs");
				}
			}
		}
		

		System.out.println("\n☻☻☻Gracias por jugar con nosotros☻☻☻\n");
		barraCarga("Redireccionando al menú principal");
		Administrador.sc.nextLine();
		Administrador.inicio(ClienteActual);
		
	}//Fin de la funcionalidad
	
	
	
	///////////////////////////////////////////////
	//Metodos auxiliares para la funcionalidad
	//////////////////////////////////////////////
	
	
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




