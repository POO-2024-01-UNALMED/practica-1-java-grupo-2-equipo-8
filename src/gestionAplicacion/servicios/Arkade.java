package gestionAplicacion.servicios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import iuMain.Administrador;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.*;


public class Arkade implements Serializable{

	private static final long serialVersionUID = 1L;
	//Atributos
	private String nombreServicio;
	private double valorServicio;
	private static final double puntuacionMaxima = 10.0;
	private String generoServicio;
	
	//Constructores
	
	public Arkade(){SucursalCine.getJuegos().add(this);}
	
	public Arkade(String nombreServicio, double valorServicio, String generoServicio) {
		super();
		this.nombreServicio = nombreServicio;
		this.valorServicio = valorServicio;
		this.generoServicio = generoServicio;
		SucursalCine.getJuegos().add(this);
	}

	//metodos
	
	/**
	*Description: Se verifica si almenos hay alguna tarjeta disponible en el array de tarjetas en inventario.
	*@return <b>boolean</b> :  retorna true o false si hay o no tarjetas en inventario.
	*/
	
	public static boolean verificarTarjetasEnInventario(SucursalCine cine) {
		boolean value= false;
		if (cine.getInventarioTarjetasCinemar().size()>0) {
			value = true;
		}
		return value;
	}
	
	/**
	*Description: Toma la primera tarjeta cinemar disponible y le asocia el cliente, se le cambia el estado, y se le asigna saldo 0,
	*ademas, al Cliente se le asocia la tajeta cinemar y se elimina esa tarjeta del ArrayList de tarjetas en inventario
	*@param cliente :  se pasa el cliente a asociar la tarjeta Cinemar.
	
	*/
	public static void asociarTarjetaCliente(Cliente cliente) {
		cliente.getCineActual().getInventarioTarjetasCinemar().get(0).setDueno(cliente);
		cliente.getCineActual().getInventarioTarjetasCinemar().get(0).setSaldo(0);
		cliente.setCuenta(cliente.getCineActual().getInventarioTarjetasCinemar().get(0));
		cliente.getCineActual().getInventarioTarjetasCinemar().remove(0);
	}
	
	
	/**
	*Description: Este metodo se encarga de mostrar los diferentes juegos por pantalla
	*@return <b>String</b> :  Se retorna un string con los juegos a mostrar al usuario
	*/
	public static String mostrarJuegos(){
		String juegos = null;
		int i = 1;
		ArrayList<Double> precios = new ArrayList<>();
		
		precios.addAll(Arrays.asList(15000.0, 20000.0, 10000.0, 30000.0, 7500.0));
		
		for (Arkade juego : SucursalCine.getJuegos()) {
			if (juegos == null) {
				if (juego.getValorServicio()==precios.get(i-1)) {
					juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				}
				else {
					juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+"--> Precio anterior: "+precios.get(i-1)+".\n";
				}

				i++;
			}
			else {
				if (juego.getValorServicio()==precios.get(i-1)) {
					juegos += i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				}
				else {
					juegos += i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+" --> Precio anterior: "+precios.get(i-1)+".\n";
				}

				i++;
			}
		}
		juegos+= "6. Volver al inicio\n7. Salir\n";
		return "¿Cual juego desea jugar?\n"+juegos;
	}
	
	/**
	*Description: Este metodo se encarga de aplicar un descuento del 20% al valor de los juegos con un genero pasado en el parametro
	*@param genero :  se pasa el como parametro el genero de el juego a aplicar el descuento
	*@return <b>void</b> :  No hay retorno
	*/
	public static void AplicarDescuentoJuegos(String genero) {
		for (Arkade juego : SucursalCine.getJuegos()) {
			if (juego.getGeneroServicio().equals(genero))
			juego.setValorServicio(juego.getValorServicio()-(juego.getValorServicio()*20/100));
		}
	}
	
	
	/**
	*Description: Este metodo se encarga de restablecer el valor del precio de todos los juegos
	*@return <b>void</b> :  No hay retorno
	*/
	public static void reestablecerPrecioJuegos() {
		SucursalCine.getJuegos().get(0).setValorServicio(15000);
		SucursalCine.getJuegos().get(1).setValorServicio(20000);
		SucursalCine.getJuegos().get(2).setValorServicio(10000);
		SucursalCine.getJuegos().get(3).setValorServicio(30000);
		SucursalCine.getJuegos().get(4).setValorServicio(7500);
	}
	
	
	/**
	*Description: Este metodo se encarga primeramente de seleccionar los productos de tipo comida del inventario de la sucursal, luego genera un codigo aleatorio de 7 digitos para el bono
	*y ademas escoge de esos productos seleccionados uno de manera aleatoria para ser asociado al bono y lo descuenta de la cantidad de disponibles, finalmente imprime por pantalla el bono al usuario
	*@param sucursal :  se pasa el como parametro la sucursal a la cual se le solicita el inventario
	*@return <b>Bono</b> :  Se retorna el bono creado
	*/
	public static Bono generarBonoComidaJuegos(SucursalCine sucursal) {
	    ArrayList<Producto> productosComida = new ArrayList<>();
	    for (Producto producto : sucursal.getInventarioCine()) {
	        if (producto.getTipoProducto().equals("comida")) {
	            productosComida.add(producto);
	        }
	    }
	    
	    if (productosComida.isEmpty()) {
	        System.out.println("•Error al generar bono, no hay productos de comida disponibles, lo sentimos.");
	        return null;
	    }

	    Random random = new Random();

	    
	    int numeroAleatorio = random.nextInt(productosComida.size());
	    String code = generarCodigoAleatorio(7);
	    Bono bono = new Bono(code,productosComida.get(numeroAleatorio),productosComida.get(numeroAleatorio).getTipoProducto());
	    productosComida.get(numeroAleatorio).setCantidad(productosComida.get(numeroAleatorio).getCantidad()-1);
	    
	    System.out.println("\n        ╔══════════════════════════╗");
	    System.out.println("        ║        Bono Comida       ║");
	    System.out.println("        ╠══════════════════════════╣");
	    String linea = "        ║ Producto: " + productosComida.get(numeroAleatorio).getNombre();
	    for (int i = linea.length(); i < 36; i++) {
	        if (i == 35) {
	            linea = linea + "║";
	        } else {
	            linea = linea + " ";
	        }
	    }
	    System.out.println(linea);
	    String line = "        ║ Codigo:   " + code;
	    for (int i = line.length(); i < 38; i++) {
	        if (i == 35) {
	            line = line + "║";
	        } else {
	            line = line + " ";
	        }
	    }
	    System.out.println(line);
	    System.out.println("        ╚══════════════════════════╝\n");
	    
	    return bono;
	}
	
	
	/**
	*Description: Este metodo se encarga primeramente de seleccionar los productos de tipo souvenir del inventario de la sucursal, luego genera un codigo aleatorio de 7 digitos para el bono
	*y ademas escoge de esos productos seleccionados uno de manera aleatoria para ser asociado al bono y lo descuenta de la cantidad de disponibles, finalmente imprime por pantalla el bono al usuario
	*@param sucursal :  se pasa el como parametro la sucursal a la cual se le solicita el inventario
	*@return <b>Bono</b> :  Se retorna el bono creado
	*/
	public static Bono generarBonoSouvenirJuegos(SucursalCine sucursal) {
		ArrayList<Producto> productosSouvenirs = new ArrayList<>();
	    for (Producto producto : sucursal.getInventarioCine()) {
	        if (producto.getTipoProducto().equals("souvenir")) {
	        	productosSouvenirs.add(producto);
	        }
	    }
	    
	    if (productosSouvenirs.isEmpty()) {
	        System.out.println("•Error al generar bono, no hay productos de souvenir disponibles, lo sentimos.");
	        return null;
	    }

	    Random random = new Random();

	    
	    int numeroAleatorio = random.nextInt(productosSouvenirs.size());
	    String code = generarCodigoAleatorio(7);
	    Bono bono = new Bono(code,productosSouvenirs.get(numeroAleatorio),productosSouvenirs.get(numeroAleatorio).getTipoProducto());
	    productosSouvenirs.get(numeroAleatorio).setCantidad(productosSouvenirs.get(numeroAleatorio).getCantidad()-1);
	    
	    System.out.println("\n        ╔══════════════════════════╗");
	    System.out.println("        ║        Bono Souvenir     ║");
	    System.out.println("        ╠══════════════════════════╣");
	    String linea = "        ║ Producto: " + productosSouvenirs.get(numeroAleatorio).getNombre();
	    for (int i = linea.length(); i < 36; i++) {
	        if (i == 35) {
	            linea = linea + "║";
	        } else {
	            linea = linea + " ";
	        }
	    }
	    System.out.println(linea);
	    String line = "        ║ Codigo:   " + code;
	    for (int i = line.length(); i < 38; i++) {
	        if (i == 35) {
	            line = line + "║";
	        } else {
	            line = line + " ";
	        }
	    }
	    System.out.println(line);
	    System.out.println("        ╚══════════════════════════╝\n");
	    
	    return bono;
	}
	
	
	/**
	*Description: Este metodo se encarga de generar un codigo aleatorio para los bonos creados.
	*@param longitud :  se pasa el como parametro la longitud que se desea el codigo
	*@return <b>Bono</b> :  Se retorna el bono creado
	*/
	public static String generarCodigoAleatorio(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(index));
        }

        return codigo.toString();
    }
	
	
	
	
	//getters y setters

	public String getNombreServicio() {
		return nombreServicio;
	}


	public double getValorServicio() {
		return valorServicio;
	}
	

	public static double getPuntuacionMaxima() {
		return puntuacionMaxima;
	}

	public String getGeneroServicio() {
		return generoServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}


	public void setValorServicio(double valorServicio) {
		this.valorServicio = valorServicio;
	}


	public void setGeneroServicio(String generoServicio) {
		this.generoServicio = generoServicio;
	}


	
}
