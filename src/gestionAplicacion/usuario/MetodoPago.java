package gestionAplicacion.usuario;

import java.util.ArrayList;

import gestionAplicacion.servicios.Bono;

public class MetodoPago {
	
	private String nombre;
	private double descuentoAsociado;
	private double limiteMaximoPago;
	private static ArrayList<MetodoPago> metodosDePagoDisponibles = new ArrayList<>();
	private static ArrayList<MetodoPago> metodosDePagoUsados = new ArrayList<>();
	private double valorServicio;
	private int tipo;
	
	public MetodoPago(){
		metodosDePagoDisponibles.add(this);
	}
	
	
	public MetodoPago(String nombre, double limiteMaximoPago) {
		this();
		this.nombre = nombre;
		this.limiteMaximoPago = limiteMaximoPago;
	}

	public MetodoPago(String nombre, double descuentoAsociado, double limiteMaximoPago, double valorServicio) {
		this();
		this.nombre = nombre;
		this.descuentoAsociado = descuentoAsociado;
		this.limiteMaximoPago = limiteMaximoPago;
		this.valorServicio = valorServicio;
	}
	public void cambiarDisponibilidadMetodoPago() {
		
	}
	
	/**
	*<b>Description</b>: Este método se encarga de mostrar los métodos de pago disponibles
	*@param none : No se pide parametros
	*@return <b>string</b> : Se retorna un texto mostrando el nombre de los métodos de pago.
	*/
	public static String mostrarMetodosDePago () {
		String resultado = null;
		int i = 1;
		for (MetodoPago metodoPago : metodosDePagoDisponibles) {
			if (resultado == null) {
				resultado = i + ". "+ metodoPago.getNombre() + "\n";
			}else {
				resultado = resultado + i + ". " + metodoPago.getNombre() + "\n";
			}
			i++;
		}
		return resultado;
	}
	
	
 	public double aplicarDescuento() {return 3.1415926535;}
	public boolean usarMetodopago() {return true;}
	
	//Getters and setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
