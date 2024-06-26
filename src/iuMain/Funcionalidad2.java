package iuMain;

import java.util.Scanner;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class Funcionalidad2 {
	public static void compras(Cliente clienteProceso){
		// Seleccion del servicio que se desea acceder
		Scanner sc = new Scanner(System.in);
		//clienteProceso.setCineActual(sucursalCineProceso);
		Servicio serviciProceso;
		boolean verificacion = true;
		int servicio = 0;
		int cantidad = 0;
		System.out.println("\n ====== Bienvenido a los servicios de compra ====== \n");
		
		//Le pedimos el servicio al cual desea acceder
		
		do {
			try {
				for(int i = 0;i<clienteProceso.getCineActual().getServicios().size();i++) {
					int n = i+1; 
					System.out.println(n+". "+"Servicio "+clienteProceso.getCineActual().getServicios().get(i).getNombre()+" 🏪🏪🏪🏪🏪");
				}
				System.out.print("0.Volver al menu.\n\nSeleccione una opcion: ");
				servicio = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			if (servicio == 0) {
				Administrador.inicio(clienteProceso);
				break;
			}
			else if (servicio == 1 || servicio == 2) {
				servicio = servicio - 1;
				verificacion = false;
			}
			
		}while(verificacion);
		
		//Servicio de comida
		
			
		//Filtramos el inventario segun el servicio y la sucursal del cine
		serviciProceso = clienteProceso.getCineActual().getServicios().get(servicio);
		System.out.print("\n Bienvenido al servicio "+ serviciProceso.getNombre()+" 📽️📽️📽️");
		serviciProceso.setCliente(clienteProceso);
		serviciProceso.setInventario(clienteProceso.getCineActual().getServicios().get(servicio).actualizarInventario());
		
		
		//Mostramos los productos que hay disponibles en la sucursal y 
		//le pedimos que seleccione uno y la cantidad de producto que desea
		
		verificacion = true;
		boolean verificacion2 = true;
		int eleccion = 0;
		do {
			try {
				System.out.print("\n"+serviciProceso.mostrarInventario());
				if(serviciProceso.getOrden().size()>0) {
					System.out.print("\n"+(serviciProceso.getInventario().size()+1)+". Eliminar un producto de la orden");
				}
				if (serviciProceso.mostrarInventario() == "\nNO HAY PRODUCTOS DISPONIBLES :(\n") {
					Administrador.inicio(clienteProceso);
				}
				System.out.print("\n\nSelecciones una opcion de los productos: ");
				eleccion = Integer.parseInt(sc.nextLine());
				if (eleccion == 0) {
					break;
				}
				
				if (eleccion > serviciProceso.getInventario().size()+1 || eleccion < 1) {
					System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
					continue;
				}
				
				if (eleccion < serviciProceso.getInventario().size()+1 ) {
					eleccion = eleccion - 1;
					System.out.print("\nIngrese el numero de productos que deseas llevar: ");
					cantidad = Integer.parseInt(sc.nextLine());
				}
				
				if (eleccion == serviciProceso.getInventario().size()+1) {
					
					int eleccion2;
					verificacion = true;
					do {
						try {
							System.out.print("\n"+serviciProceso.mostrarOrden());
							System.out.print("\nSeleccione el producto que quieres eliminar: ");
							eleccion2 = Integer.parseInt(sc.nextLine());
							System.out.print("\n\nSeleccione la cantidad de productos que deasea quitar de su orden: ");
							cantidad = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion2 > serviciProceso.getOrden().size()+1 ) {
							System.out.print("ERROR EN LA SELECCION DEL PRODUCTO");
							continue;
						}
						else if(cantidad > serviciProceso.getOrden().get(eleccion2-1).getCantidad()) {
							System.out.print("ERROR EN LA CANTIDAD DE PRODUCTOS QUE DESEA ELIMINAR");
						}
						else if (eleccion2 == 0) {
							verificacion = false;
						}
						else {
							if (cantidad == serviciProceso.getOrden().get(eleccion2-1).getCantidad()) {
								serviciProceso.getOrden().remove(eleccion2-1);
								verificacion = false;
							}
							else {
								double total;
								total = serviciProceso.getOrden().get(eleccion2-1).getPrecio() / serviciProceso.getOrden().get(eleccion2-1).getCantidad();
								serviciProceso.getOrden().get(eleccion2-1).setCantidad(serviciProceso.getOrden().get(eleccion2-1).getCantidad() - cantidad);
								serviciProceso.getOrden().get(eleccion2-1).setPrecio(total * serviciProceso.getOrden().get(eleccion2-1).getCantidad());
								verificacion = false;
							}
						}
						
					}while(verificacion);
				}
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			
			verificacion = true;
			// Se verifica si hay suficientes productos segun la cantidad que pidio el cliente
			
			if (eleccion < serviciProceso.getInventario().size() ) {
				
				Producto producto = serviciProceso.hacerPedido(eleccion, cantidad);
				if(producto == null) {
					System.out.print("\nNo hay suficientes productos de: "+serviciProceso.getInventario().get(eleccion).getNombre()+
							serviciProceso.getInventario().get(eleccion).getTamaño()+" (╥_╥)(╥_╥)(╥_╥) \n\nEn el momento solo hay disponible: "+
							serviciProceso.getInventario().get(eleccion).getCantidad());
				}
				else {
					System.out.print("  --------------------------------------------------- \n");
					System.out.print(" | 🎉🎉🎉🎉El pedido fue realizado con exito🎉🎉🎉🎉 |\n");
					System.out.print("  ---------------------------------------------------  \n");
					serviciProceso.agregarOrden(producto);
					System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
					System.out.print(serviciProceso.mostrarOrden());
					do {
						try {
							System.out.print("\n\n"+"¿Quieres hacer otro pedido? \n1.SI \n2.NO"+
						"\nSeleccione una opcion: ");	
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						verificacion2 = false;
						if(eleccion == 2){
							verificacion = false;
						}
						
					}while(verificacion2);
				}
			}
			
			if (eleccion == serviciProceso.getInventario().size()+1) {
				System.out.print("  --------------------------------------------------- \n");
				System.out.print(" |  🎉🎉🎉🎉 El pedido eliminado con exito 🎉🎉🎉🎉  |\n");
				System.out.print("  ---------------------------------------------------  \n");
				System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
				System.out.print(serviciProceso.mostrarOrden());
				do {
					try {
						System.out.print("\n\n"+"¿Quieres hacer otro pedido? \n1.SI \n2.NO"+
					"\nSeleccione una opcion: ");	
						eleccion = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nError, debes ingresar un dato numérico\n");
						continue;
					}
					verificacion2 = false;
					if(eleccion == 2){
						verificacion = false;
					}
					
				}while(verificacion2);
			}
		}while(verificacion);
		
		//////////// Espacio para descuento por genero con horario para hacer con Andy///////////////////////
		
		if(serviciProceso.getOrden().size()>0) {
			Producto productoDescuento = serviciProceso.descuentarPorGenero(clienteProceso.getCineActual());
			
			if (productoDescuento != null){
				
				System.out.print("                ------------------------------------------------------------------- \n");
				System.out.print("               |            🎉🎉Felicidades obtuviste un descuento 🎉🎉            |\n");
				System.out.print("               |Por comprar un producto del mismo genero que el tiket que compraste|\n");
				System.out.print("                ------------------------------------------------------------------- \n");
				System.out.print("Todos los produtos de :"+ productoDescuento.getNombre() + productoDescuento.getTamaño() +" Obtuvieron un descuento del 10% en esta compra \n");
				System.out.print("Precio anterior --> $"+productoDescuento.getPrecio()+" Precio actual --> $");
				productoDescuento.setPrecio(productoDescuento.getPrecio()*0.9);
				System.out.println(productoDescuento.getPrecio());
				System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
				System.out.print(serviciProceso.mostrarOrden());
				
			}
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////
		
		//Validacion de los bonos
		boolean verificacionR = true;
		verificacion = true;
		do {
			serviciProceso.actualizarBonos();
			if (0 < serviciProceso.getBonosCliente().size()) {
				do {
					try {
						System.out.println(serviciProceso.mostrarBonos());
						System.out.print("Seleccione una opcion: ");
						eleccion = Integer.parseInt(sc.nextLine());
						if (eleccion > serviciProceso.getBonosCliente().size()) {
							System.out.println("\nError, debes escoger una opcion correcta\n");
							continue;
						}
					}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
					verificacion = false;
				}while(verificacion);
				
				if (eleccion == 0) {
					break;
				}
				Producto productoBono1 = serviciProceso.validarBono(serviciProceso.getBonosCliente().get(eleccion-1).getCodigo(),clienteProceso.getCineActual().getServicios().get(servicio));
				
				if(productoBono1.comprobarBonoEnOrden(serviciProceso)) {
					verificacion = true;
					do {
						try {
							System.out.print("\n"+"¿Que deseas hacer con el producto?\n1.Desea agregarlo al pedido"+
									"\n2.Desea descontarlo del pedido\nSelecciona una opcion:");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}

						if (eleccion == 1) {
							productoBono1.setPrecio(0);
							productoBono1.setNombre("Regalo de Bono "+productoBono1.getNombre());
							serviciProceso.getOrden().add(productoBono1);
							System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
							System.out.print(serviciProceso.mostrarOrden());
							verificacion = false;
						}
						else if (eleccion == 2){
							serviciProceso.descontarProducto(productoBono1);
							System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
							System.out.print(serviciProceso.mostrarOrden());
							verificacion = false;
						}
						else {
							System.out.print("\n\\nSELECCIONE UNA OPCION VALIDA\\n\n");
						}
					}while(verificacion);
				}
				else if (productoBono1 != null){
					productoBono1.setPrecio(0);
					productoBono1.setNombre("Regalo de bono "+productoBono1.getNombre());
					serviciProceso.getOrden().add(productoBono1);
					System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
					System.out.println(serviciProceso.mostrarOrden());
				}
				if ( 0 < serviciProceso.getBonosCliente().size()-1) {
					do {
						try {
							
							System.out.print("\n\n¿Deseas reclamar otro Bono?\n1.SI\n2.NO\nSelecciona una opcion:");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion == 2) {
							verificacionR = false;
						}
						else if (eleccion != 1 && eleccion != 2) {
							System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
						}
					}while(eleccion != 1 && eleccion != 2);
				}
				else {
					verificacionR = false;
				}
			}
			else {
				verificacionR = false;
			}
			
		}while(verificacionR);
		
		verificacion = true;
		do {
			try {
				System.out.print("\n\n    SISTEMA DE BONOS 🎁🎁🎁🎁🎁");
				System.out.print("\n\n¿Tienes algun codigo para reclamar?\n1.SI\n2.NO\nSeleccione una opcion:");
				eleccion = Integer.parseInt(sc.nextLine());
				if (eleccion != 1 && eleccion != 2) {
					System.out.println("Error, Debes de seleccionar una de las dos opciones");
					continue;
				}
			}catch(NumberFormatException e) {
				System.out.println("\nError, debes ingresar un dato numérico\n");
				continue;
			}
			verificacion = false;
		}while(verificacion);
		
		String codigoBono;
		if(eleccion == 1) {
			Producto productoBono = new Producto();
			do {
				try {
					System.out.println("\n🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁 REGALOS CON BONOS 🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁🎁\n");
					System.out.print("Ingrese el codigo del bono: ");
					codigoBono = sc.nextLine();
				}catch(NumberFormatException e) {
					System.out.println("\nError, debes ingresar un dato numérico\n");
					continue;
				}
				productoBono = serviciProceso.validarBono(codigoBono,clienteProceso.getCineActual().getServicios().get(servicio));
				if (productoBono == null) {
					System.out.println("\n Codigo invalido, verificar el codigo (╥_╥)(╥_╥)(╥_╥)");
					
					do {
						try {
							System.out.print("\n"+"¿Que deseas hacer?\n1.Salir\n2.Volver a intentar\nSeleccione una opcion: ");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion == 1) {
							verificacion = false;
							break;
						}
						else if (eleccion != 2) {
							System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
						}
					}while(eleccion != 1 && eleccion != 2);
					
				}
				else {
					System.out.print("  ----------------------------------------- ");
					System.out.println("\n | 🎉🎉🎉🎉Bono validado con exito🎉🎉🎉🎉 | ");
					System.out.print("  ----------------------------------------- \n");
					System.out.println("   El bono es de: "+productoBono.getNombre()+" "+productoBono.getTamaño());
					
					
					if(productoBono.comprobarBonoEnOrden(serviciProceso)) {
						verificacion = true;
						do {
							try {
								System.out.print("\n"+"¿Que deseas hacer con el producto?\n1.Desea agregarlo al pedido"+
										"\n2.Desea descontarlo del pedido\nSelecciona una opcion:");
								eleccion = Integer.parseInt(sc.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nError, debes ingresar un dato numérico\n");
								continue;
							}

							if (eleccion == 1) {
								productoBono.setPrecio(0);
								productoBono.setNombre("Regalo de Bono "+productoBono.getNombre());
								serviciProceso.getOrden().add(productoBono);
								System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
								System.out.print(serviciProceso.mostrarOrden());
								verificacion = false;
							}
							else if (eleccion == 2){
								serviciProceso.descontarProducto(productoBono);
								System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
								System.out.print(serviciProceso.mostrarOrden());
								verificacion = false;
							}
							else {
								System.out.print("\n\\nSELECCIONE UNA OPCION VALIDA\\n\n");
							}
						}while(verificacion);
					}
					else if (productoBono != null){
						productoBono.setPrecio(0);
						productoBono.setNombre("Regalo de bono "+productoBono.getNombre());
						serviciProceso.getOrden().add(productoBono);
						System.out.print("\n 🛒🛒🛒Los productos que llevas en el momento son:🛒🛒🛒 \n");
						System.out.println(serviciProceso.mostrarOrden());
					}
					verificacion = true;
					do {
						try {
							
							System.out.print("\n\n¿Deseas reclamar otro Bono?\n1.SI\n2.NO\nSelecciona una opcion:");
							eleccion = Integer.parseInt(sc.nextLine());
						}catch(NumberFormatException e) {
							System.out.println("\nError, debes ingresar un dato numérico\n");
							continue;
						}
						if (eleccion == 1) {
						}
						else if (eleccion == 2) {
							verificacion = false;
						}
						else if (eleccion != 2) {
							System.out.print("\nSELECCIONE UNA OPCION VALIDA\n");
						}
					}while(eleccion != 1 && eleccion != 2);
				}
			}while(verificacion);
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
		serviciProceso.setValorPedido(serviciProceso.calcularTotal());
		double valor = 0;
		double valor1 = 0;
		double descuento = 0;
		verificacion = true;
		boolean condicion = true;
		System.out.print("\n------EL PEDDIDO ESTA LISTO SOLO FALTA PAGAR: $"+serviciProceso.getValorPedido()+" ------\n");
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
			valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
			System.out.println("          Ahora el valor a pagar es de: $"+valor+"\n");
			
			
			if (condicion) {
				condicion = false;
				valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
				if (serviciProceso.descuentarPorCompra(metodoDePago)) {
					System.out.print("        ------------------------------------------------------------------- \n");
					System.out.print("       |  🎉🎉Felicidades obtuviste un descuento sorpresa en tu compra🎉🎉 |\n");
					System.out.print("        ------------------------------------------------------------------- \n");
					valor = serviciProceso.getValorPedido() * (1 - metodoDePago.getDescuentoAsociado());
					System.out.println("       Ahora tu cuenta quedo en: $" + valor);
				}
				valor1 = serviciProceso.getValorPedido();
			}
			descuento = descuento + (serviciProceso.getValorPedido() * metodoDePago.getDescuentoAsociado());
			serviciProceso.setValorPedido(metodoDePago.realizarPago(serviciProceso.getValorPedido(),clienteProceso));
			
			
			if (serviciProceso.getValorPedido() == 0) {
				valor1 = valor1 - descuento;
				serviciProceso.setValorPedido(valor1);
				System.out.println("LA CUOTA FUE CUBIARTA EN SU TOTALIDAD 🎉🎉🎉🎉");
				System.out.println("\nEstamos generando su factura, por favor espere...\n");
				try {
					Thread.sleep(3000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				serviciProceso.factura(clienteProceso);
				System.out.print(clienteProceso.getFacturas().get(clienteProceso.getFacturas().size()-1));
				System.out.print("\n\n          Redireccionando al menu principal\n\n");
				try {
					Thread.sleep(3000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				serviciProceso.procesarPagoRealizado(clienteProceso);
				verificacion = false;
			}
			else {
				System.out.println("\n----------------------------------------------------------------------------------");
				System.out.println("\nFALTA POR TERMINAR DE PAGAR : $" + serviciProceso.getValorPedido() + " (╥_╥)(╥_╥)(╥_╥)");
				continue;
			}
			
		}while(verificacion);
		
		
	}
	
}
