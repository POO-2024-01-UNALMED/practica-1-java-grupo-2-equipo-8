package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import gestionAplicacion.usuario.*;


public class Arkade {
	
	//Atributos
	private String nombreServicio;
	private String horarioServicio;
	private static ArrayList<TarjetaCinemar> tarjetasEnInventario = new ArrayList<>();
	private static ArrayList<Arkade> juegos = new ArrayList<>();
	private double valorServicio;
	private static ArrayList<String> codigosGenerados = new ArrayList<>();;
	private static ArrayList<String> codigosUsados= new ArrayList<>();;
	private final double puntuacionMaxima = 10.0;
	private double puntuacionUsuario;
	private String generoServicio;
	
	//Constructores
	public Arkade(){juegos.add(this);}
	
	public Arkade(String nombre, String horario, String nombreServicio, String horarioServicio,
			double valorServicio, double puntuacionUsuario, String generoServicio) {
		this.nombreServicio = nombreServicio;
		this.horarioServicio = horarioServicio;
		this.valorServicio = valorServicio;
		this.puntuacionUsuario = puntuacionUsuario;
		this.generoServicio = generoServicio;
		juegos.add(this);
	}
	
	
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
	*Description: Buscando el el array de metodos de pago del cliente, encuentra el que el cliente ha seleccionado de acuerdo al nombre.
	*@param nombreMetodoPago :  Es el String del nombre del metodo de pago seleccionado por el cliente
	*@param metodosPagoCliente :  Es el array de los metodos de pago que le pertenecen a ese cliente.
	*@return <b>metodoPagoCliente</b> :  se retorna el metodo de pago encontrado para el cliente.
	*/
	
	public static MetodoPago encontrarMetodoPagoCliente(String nombreMetodoPago, ArrayList<MetodoPago> metodosPagoCliente) {
		
		MetodoPago metodoPagoCliente = null;
		for (MetodoPago pago : metodosPagoCliente) {
			if (nombreMetodoPago.equals(pago.getNombre())){
				metodoPagoCliente = pago;
				break;
			}
		}
		return metodoPagoCliente;
	}
	
	/**
	*Description: Buscando el el array de codigos generados se verifica si el codigo pasado coincide con alguno de los generados, en caso de que si,
	*se añade a codigos usados y se quita de codigos generados
	*@param codigo :  Es el String del codigo a verificar
	*@return <b>boolean</b> :  se retorna true si el codigo existe y false si no existe.
	*/
	public static boolean comprobarCodigo(String codigo) {
		boolean existencia = false;
		for (String code : codigosGenerados) {
			if (code.equals(codigo)) {
				existencia = true;
				codigosUsados.add(code);
				codigosGenerados.remove(code);
			}
		}
		return existencia;
	}
	
	
	/**
	*Description: Este metodo se encarga de mostrar los diferentes juegos por pantalla
	*@return <b>String</b> :  Se retorna un string con los juegos a mostrar al usuario
	*/
	public static String mostrarJuegos(){
		String juegos = null;
		int i = 1;
		for (Arkade juego : Arkade.juegos) {
			if (juegos == null) {
				juegos = i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
				i++;
			}
			else {
				juegos+= i+". "+juego.nombreServicio+"--"+juego.generoServicio+"--"+juego.valorServicio+".\n";
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
	public static void AplicarDescuentoJuegos() {
		for (Arkade juego : Arkade.juegos) {
			juego.setValorServicio(juego.getValorServicio()-(juego.getValorServicio()*20/100));
		}
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

	public String getHorarioServicio() {
		return horarioServicio;
	}

	public double getValorServicio() {
		return valorServicio;
	}

	public static ArrayList<String> getCodigosGenerados() {
		return codigosGenerados;
	}

	public static ArrayList<String> getCodigosUsados() {
		return codigosUsados;
	}

	public double getPuntuacionMaxima() {
		return puntuacionMaxima;
	}

	public double getPuntuacionUsuario() {
		return puntuacionUsuario;
	}

	public String getGeneroServicio() {
		return generoServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public void setHorarioServicio(String horarioServicio) {
		this.horarioServicio = horarioServicio;
	}

	public void setValorServicio(double valorServicio) {
		this.valorServicio = valorServicio;
	}

	public static void setCodigosGenerados(ArrayList<String> codigosGenerados) {
		Arkade.codigosGenerados = codigosGenerados;
	}

	public static void setCodigosUsados(ArrayList<String> codigosUsados) {
		Arkade.codigosUsados = codigosUsados;
	}

	public void setPuntuacionUsuario(double puntuacionUsuario) {
		this.puntuacionUsuario = puntuacionUsuario;
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
