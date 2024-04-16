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
	*Description: Buscando el el array de metodos de pago disponibles, encuentra el que el cliente ha seleccionado de acuerdo al nombre y al tipo de membresia que tiene
	*@param nombreMetodoPago :  Es el String del nombre del metodo de pago seleccionado por el cliente
	*@param cliente :  Es el cliente asociado al proceso
	*@return <b>metodoPagoCliente</b> :  se retorna el metodo de pago encontrado para el cliente.
	*/
	
	public static MetodoPago encontrarMetodoPagoCliente(String nombreMetodoPago, Cliente cliente) {
		MetodoPago metodoPagoCliente = null;
		for (MetodoPago pago : MetodoPago.getMetodosDePagoDisponibles()) {
			if (pago.getNombre().equals(nombreMetodoPago)) {
				if (cliente.getMembresia()==null){
					if (pago.getTipo()==0) {
						metodoPagoCliente = pago;
						break;
					}
				}
				else if (cliente.getMembresia().getTipoMembresia()==1 || cliente.getMembresia().getTipoMembresia()==2) {
					if (pago.getTipo()==1) {
						metodoPagoCliente = pago;
						break;
					}
				}
				else if (cliente.getMembresia().getTipoMembresia()==3) {
					if (pago.getTipo()==2) {
						metodoPagoCliente = pago;
						break;
					}
				}
			}
		}
		return metodoPagoCliente;
	}
	
	
	//getters y setters
	public static ArrayList<TarjetaCinemar> getTarjetasEnInventario() {
		return tarjetasEnInventario;
	}

	public static void setTarjetasEnInventario(ArrayList<TarjetaCinemar> tarjetasEnInventario) {
		ServicioEntretenimiento.tarjetasEnInventario = tarjetasEnInventario;
	}


	
	
	
	
	
}
