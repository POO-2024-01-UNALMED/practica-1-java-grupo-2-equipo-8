package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Date;

import gestionAplicacion.usuario.*;


public class ServicioEntretenimiento extends Servicio{
	
	//Atributos
	private String nombreServicio;
	//private int idServicio;
	private String horarioServicio;
	private static ArrayList<TarjetaCinemar> tarjetasEnInventario = new ArrayList<>();
	private double valorServicio;
	private static ArrayList<String> codigosGenerados = new ArrayList<>();;
	private static ArrayList<String> codigosUsados= new ArrayList<>();;
	private final double puntuacionMaxima = 10.0;
	private double puntuacionUsuario;
	private String generoServicio;
	
	//Constructores
	public ServicioEntretenimiento(){}
	
	public ServicioEntretenimiento(String nombre, String horario, String nombreServicio, String horarioServicio,
			double valorServicio, double puntuacionUsuario, String generoServicio) {
		super(nombre, horario);
		this.nombreServicio = nombreServicio;
		this.horarioServicio = horarioServicio;
		this.valorServicio = valorServicio;
		this.puntuacionUsuario = puntuacionUsuario;
		this.generoServicio = generoServicio;
	}
	
	//metodos
	public void ingresarSaldo(int saldo) {}
	public void usarServicio() {}
	public void leerFactura() {}
	public boolean verificarPuntuacion() {return true;}
	public Pedido obtenerBonoSouvenir() {return new Pedido();}
	public Pedido obtenerBonoComida() {return new Pedido();}
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
	//getters y setters
	public static ArrayList<TarjetaCinemar> getTarjetasEnInventario() {
		return tarjetasEnInventario;
	}

	public static void setTarjetasEnInventario(ArrayList<TarjetaCinemar> tarjetasEnInventario) {
		ServicioEntretenimiento.tarjetasEnInventario = tarjetasEnInventario;
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
		ServicioEntretenimiento.codigosGenerados = codigosGenerados;
	}

	public static void setCodigosUsados(ArrayList<String> codigosUsados) {
		ServicioEntretenimiento.codigosUsados = codigosUsados;
	}

	public void setPuntuacionUsuario(double puntuacionUsuario) {
		this.puntuacionUsuario = puntuacionUsuario;
	}

	public void setGeneroServicio(String generoServicio) {
		this.generoServicio = generoServicio;
	}

	
	
	
	
	
	
}
