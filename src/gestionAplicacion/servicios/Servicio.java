package gestionAplicacion.servicios;

public class Servicio {
	
	private static int idServicio;
	private String nombre;
	private String horario;
	private static double valorGananciasTotales;
	
	public Servicio(){}
	
	public Servicio(String nombre, String horario) {
		super();
		this.nombre = nombre;
		this.horario = horario;
	}
	
	public boolean realizarPago() {return true;}
	public String factura() {return "factura";}
	public void validarCliente() {}
	public static void sumarValorGananciasTotales() {}
}
