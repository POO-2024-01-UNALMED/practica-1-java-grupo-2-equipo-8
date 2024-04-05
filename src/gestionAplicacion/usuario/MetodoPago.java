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
	
	public MetodoPago(){}
	
	public MetodoPago(String nombre, double descuentoAsociado, double limiteMaximoPago, double valorServicio) {
		super();
		this.nombre = nombre;
		this.descuentoAsociado = descuentoAsociado;
		this.limiteMaximoPago = limiteMaximoPago;
		this.valorServicio = valorServicio;
	}
	public void cambiarDisponibilidadMetodoPago() {}
	public double aplicarDescuento() {return 3.1415926535;}
	public boolean usarMetodopago() {return true;}
	

}
