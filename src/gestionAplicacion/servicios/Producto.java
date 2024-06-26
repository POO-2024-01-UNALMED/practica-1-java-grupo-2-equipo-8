package gestionAplicacion.servicios;

public class Producto {
	//Atributos
	private String tipoProducto;
	private String genero;
	private String nombre;
	private String tamaño;
	private double precio;
	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	public int getTotalEncuestasDeValoracionRealizadas() {
		return totalEncuestasDeValoracionRealizadas;
	}

	public void setTotalEncuestasDeValoracionRealizadas(int totalEncuestasDeValoracionRealizadas) {
		this.totalEncuestasDeValoracionRealizadas = totalEncuestasDeValoracionRealizadas;
	}

	private int cantidad;
	private double valoracion;
	private int totalEncuestasDeValoracionRealizadas;
	
	public void descontarPrecioDeBono() {
		
	}
	
	//Ligadura Estatica
	
	public boolean comprobarBonoEnOrden(Servicio servicio) {
		boolean verificacion = false;
		for(int i = 0; i < servicio.getOrden().size();i++) {
			if(servicio.getOrden().get(i).getNombre() == this.getNombre() && servicio.getOrden().get(i).getTamaño() == this.getTamaño()) {
				verificacion = true;
				break;
			}
		}
		return verificacion;
		
	}
	
	//Constructores
	public Producto() {}
	
	public Producto(String nombre,String tamaño,int cantidad) {
		this.nombre = nombre;
		this.tamaño = tamaño;
		this.cantidad = cantidad;
		this.valoracion= 4.0;
		this.totalEncuestasDeValoracionRealizadas = 25;
	}
	
	public Producto(String nombre,String tamaño,String tipoProducto,double precio,int cantidad,String genero) {
		this.genero= genero;
		this.nombre = nombre;
		this.precio = precio;
		this.tamaño = tamaño;
		this.tipoProducto = tipoProducto;
		this.cantidad = cantidad;
		this.valoracion= 4.0;
		this.totalEncuestasDeValoracionRealizadas = 25;
	}
	
	//Constructor para objetos de Membresia.
	public Producto(String tipoProducto, String nombre, double precio, int cantidad) {
		this.tipoProducto = tipoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.valoracion= 4.0;
		this.totalEncuestasDeValoracionRealizadas = 25;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
