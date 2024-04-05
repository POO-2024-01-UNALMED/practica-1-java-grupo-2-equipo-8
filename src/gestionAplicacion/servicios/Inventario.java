package gestionAplicacion.servicios;
import java.util.ArrayList;

public class Inventario {
	
	private String nombreProducto;
	private static ArrayList<Inventario> productosEnInventario = new ArrayList<>();
	private int cantidadDisponible;
	private String cuidad;
	private String descripcionTamañoProducto;
	private String tipoDeProducto;
	private int codigoProducto;
	
	public Inventario(){}
	
	public Inventario(String nombreProducto, int cantidadDisponible, String cuidad, String descripcionTamañoProducto,
			String tipoDeProducto, int codigoProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.cantidadDisponible = cantidadDisponible;
		this.cuidad = cuidad;
		this.descripcionTamañoProducto = descripcionTamañoProducto;
		this.tipoDeProducto = tipoDeProducto;
		this.codigoProducto = codigoProducto;
	}



	public void cambiarInventarioCiudad() {}
	
}
