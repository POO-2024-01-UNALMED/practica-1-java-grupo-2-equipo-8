package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Date;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.TarjetaCinemar;
import gestionAplicacion.usuario.*;


public class ServicioEntretenimiento extends Servicio{
	
	//Atributos
	private String nombreServicio;
	//private int idServicio;
	private String horarioServicio;
	private static ArrayList<TarjetaCinemar> tarjetasEnInventario = new ArrayList<>();
	private double valorServicio;
	private static ArrayList<Long> codigosGenerados = new ArrayList<>();;
	private static ArrayList<Long> codigosUsados= new ArrayList<>();;
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
	
	public static boolean verificarTarjetasEnInventario() {
		boolean value= false;
		if (tarjetasEnInventario.size()>0) {
			value = true;
		}
		return value;
	}
	
	public static void asociarTarjetaCliente(Cliente cliente) {
		tarjetasEnInventario.get(0).setDueno(cliente);
		tarjetasEnInventario.get(0).setEstado(false);
		tarjetasEnInventario.get(0).setSaldo(0);
		cliente.setCuenta(tarjetasEnInventario.get(0));
		tarjetasEnInventario.remove(0);
	}
	
	
	//getters y setters
	public static ArrayList<TarjetaCinemar> getTarjetasEnInventario() {
		return tarjetasEnInventario;
	}

	public static void setTarjetasEnInventario(ArrayList<TarjetaCinemar> tarjetasEnInventario) {
		ServicioEntretenimiento.tarjetasEnInventario = tarjetasEnInventario;
	}
	
	
	
	
	
}
