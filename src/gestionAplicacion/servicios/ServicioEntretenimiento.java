package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Date;
import gestionAplicacion.usuario.TarjetaCinemar;

public class ServicioEntretenimiento extends Servicio{
	
	private String nombreServicio;
	//private int idServicio;
	private String horarioServicio;
	private static ArrayList<TarjetaCinemar> targetasEnInventario;
	private double valorServicio;
	private static ArrayList<Long> codigosGenerados;
	private static ArrayList<Long> codigosUsados;
	private final double puntuacionMaxima = 10.0;
	private double puntuacionUsuario;
	private String generoServicio;
	
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
	
	public void ingresarSaldo(int saldo) {}
	public void usarServicio() {}
	public void leerFactura() {}
	public boolean verificarPuntuacion() {return true;}
	public Pedido obtenerBonoSouvenir() {return new Pedido();}
	public Pedido obtenerBonoComida() {return new Pedido();}
	public boolean verificarRelacionPeliculaServicio() {return true;}
	public String asignarPremio() {return "premio";}
	public Bono crearBono() {return new Bono();}
	
	
	
	
}
