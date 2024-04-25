package gestionAplicacion.servicios;
import java.util.ArrayList;

public class Inventario {
	
	private String nombreProducto;
	private static ArrayList<Inventario> productosEnInventario = new ArrayList<>();
	private int precio;
	private int cantidadDisponible;
	private String descripcionTamañoProducto;
	private String tipoDeProducto;
	private int codigoProducto;
	
	public Inventario(){}
	
	public Inventario(String nombreProducto,int cantidadDisponible,int precio, String descripcionTamañoProducto,
			String tipoDeProducto, int codigoProducto) {
		super();
		this.nombreProducto = nombreProducto;
		this.cantidadDisponible = cantidadDisponible;
		this.precio = precio;
		this.descripcionTamañoProducto = descripcionTamañoProducto;
		this.tipoDeProducto = tipoDeProducto;
		this.codigoProducto = codigoProducto;
		productosEnInventario.add(this);
	}


	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public static ArrayList<Inventario> getProductosEnInventario() {
		return productosEnInventario;
	}

	public static void setProductosEnInventario(ArrayList<Inventario> productosEnInventario) {
		Inventario.productosEnInventario = productosEnInventario;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public String getDescripcionTamañoProducto() {
		return descripcionTamañoProducto;
	}

	public void setDescripcionTamañoProducto(String descripcionTamañoProducto) {
		this.descripcionTamañoProducto = descripcionTamañoProducto;
	}

	public String getTipoDeProducto() {
		return tipoDeProducto;
	}

	public void setTipoDeProducto(String tipoDeProducto) {
		this.tipoDeProducto = tipoDeProducto;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
}
