package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import iuMain.Administrador;
import gestionAplicacion.SucursalCine;
import gestionAplicacion.usuario.*;


public class Arkade {
	
	//Atributos
	private String nombreServicio;
	//private String horarioServicio;
	private static ArrayList<TarjetaCinemar> tarjetasEnInventario = new ArrayList<>();
	private static ArrayList<Arkade> juegos = new ArrayList<>();
	private double valorServicio;
	//private static ArrayList<String> codigosGenerados = new ArrayList<>();;
	//private static ArrayList<String> codigosUsados= new ArrayList<>();;
	private static final double puntuacionMaxima = 10.0;
	//private double puntuacionUsuario;
	private String generoServicio;
	
	//Constructores
	public Arkade(){juegos.add(this);}
	
	public Arkade(String nombreServicio, double valorServicio, String generoServicio) {
		super();
		this.nombreServicio = nombreServicio;
		this.valorServicio = valorServicio;
		this.generoServicio = generoServicio;
		juegos.add(this);
	}

	//metodos
	//public void ingresarSaldo(int saldo) {
		
	//}
	public void usarServicio() {}
	public void leerFactura() {}
	public boolean verificarPuntuacion() {return true;}
	public Producto obtenerBonoSouvenir() {return new Producto();}
	public Producto obtenerBonoComida() {return new Producto();}
	public boolean verificarRelacionPeliculaServicio() {return true;}
	public String asignarPremio() {return "premio";}
	public Bono crearBono() {return new Bono();}
	
	
	/**
	*Description: Se verifica si almenos hay alguna tarjeta disponible en el array de tarjetas en inventario.
	*@return <b>boolean</b> :  retorna true o false si hay o no tarjetas en inventario.
	*/
	
	public static boolean verificarTarjetasEnInventario() {
		boolean value= false;
		if (tarjetasEnInventario.size()>0) {
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
		tarjetasEnInventario.get(0).setDueno(cliente);
		tarjetasEnInventario.get(0).setEstado(false);
		tarjetasEnInventario.get(0).setSaldo(0);
		cliente.setCuenta(tarjetasEnInventario.get(0));
		tarjetasEnInventario.remove(0);
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
		
		for (Arkade juego : Arkade.juegos) {
			if (juegos == null) {
				if (juego.getValorServicio()==precios.get(i-1)) {
					juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				}
				else {
					juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+"--> Precio anterior: "+precios.get(i-1)+".\n";
				}
				//juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				i++;
			}
			else {
				if (juego.getValorServicio()==precios.get(i-1)) {
					juegos += i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				}
				else {
					juegos += i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+" --> Precio anterior: "+precios.get(i-1)+".\n";
				}
				//juegos+= i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				i++;
			}
		}
		juegos+= "6. Volver al inicio\n7. Salir\n";
		return "¿Cual juego desea jugar?\n"+juegos;
	}
	
	/**
	*Description: Este metodo se encarga de aplicar un descuento del 20% al valor de los juegos 
	*@return <b>void</b> :  No hay retorno
	*/
	public static void AplicarDescuentoJuegos(String genero) {
		for (Arkade juego : Arkade.juegos) {
			if (juego.getGeneroServicio().equals(genero))
			juego.setValorServicio(juego.getValorServicio()-(juego.getValorServicio()*20/100));
		}
	}
	
	public static void reestablecerPrecioJuegos() {
		juegos.get(0).setValorServicio(15000);
		juegos.get(1).setValorServicio(20000);
		juegos.get(2).setValorServicio(10000);
		juegos.get(3).setValorServicio(30000);
		juegos.get(4).setValorServicio(7500);
	}
	
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
	public static ArrayList<TarjetaCinemar> getTarjetasEnInventario() {
		return tarjetasEnInventario;
	}

	public static void setTarjetasEnInventario(ArrayList<TarjetaCinemar> tarjetasEnInventario) {
		Arkade.tarjetasEnInventario = tarjetasEnInventario;
	}

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

	public static ArrayList<Arkade> getJuegos() {
		return juegos;
	}

	public static void setJuegos(ArrayList<Arkade> juegos) {
		Arkade.juegos = juegos;
	}


	
	
	
	
	
	
	
	
}
