package gestionAplicacion.servicios;

public class Producto {
	private String tipoProducto;
	private String nombre;
	private String tamaño;
	private int precio;
	private int cantidad;

	public Producto() {}
	
	public Producto(String nombre,String tamaño,String tipoProducto,int precio,int cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.tamaño = tamaño;
		this.tipoProducto = tipoProducto;
		this.cantidad = cantidad;
	}
	
	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
