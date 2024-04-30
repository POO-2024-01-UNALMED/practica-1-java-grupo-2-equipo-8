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
<<<<<<< Updated upstream
		productosEnInventario.add(this);
	}
	
	/**
	 * Description : Este metodo se encarga de ver los productos disponibles que hay en el inventario
	 * recorriento el arraylist (productosEnInventario) y verificando que la cantidad sea 
	 * cantidadDisponible de 0
	 * @return <b>String<b> : Este metodo retorna un texto con los productos disponibles y sus precios
	 **/
	public static String mostrarInventario( ) {
		String inventario = "Estos son nuestros productos disponibles:";
		int n = 1;
		for (int i=0;i<productosEnInventario.size();i++) {
			if (0<productosEnInventario.get(i).getCantidadDisponible() && productosEnInventario.get(i).getTipoDeProducto().equalsIgnoreCase("Souvenirs")){
				inventario = inventario+ "\n" + n + ". " + productosEnInventario.get(i).getNombreProducto() +
						"  " + productosEnInventario.get(i).getDescripcionTamañoProducto() + " : " +
						productosEnInventario.get(i).getPrecio() + "\n";
				n++;
			}	
		}
		return inventario;
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
	
	
=======
	}


>>>>>>> Stashed changes
}

