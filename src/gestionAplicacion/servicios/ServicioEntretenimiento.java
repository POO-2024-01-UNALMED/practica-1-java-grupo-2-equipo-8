package gestionAplicacion.servicios;
import java.util.ArrayList;
import java.util.Date;
import gestionAplicacion.usuario.TarjetaCinemar;

public class ServicioEntretenimiento {
	
	private String nombreServicio;
	private int idServicio;
	private Date horarioServicio;
	private static ArrayList<TarjetaCinemar> targetasEnInventario;
	private static ArrayList<TarjetaCinemar> targetasEnPrestamo;
	private double valorServicio;
	private static ArrayList<Long> codigosGenerados;
	private static ArrayList<Long> codigosUsados;
	private final double puntuacionMaxima = 10.0;
	private double puntuacionUsuario;
	private String generoServicio;
	
	
	public void solicitarTargeta(){}
	public void ingresarSaldo(int saldo) {}
	public void usarServicio() {}
	public void leerFactura() {}
	public boolean verificarPuntuacion() {return true;}
	public Pedido obtenerBonoSouvenir() {return new Pedido();}
	public Pedido obtenerBonoComida() {return new Pedido();}
	public boolean verificarRelacionPeliculaServicio() {return true;}
	public String asignarPremio() {return new String();}
	
	
	
	
}
