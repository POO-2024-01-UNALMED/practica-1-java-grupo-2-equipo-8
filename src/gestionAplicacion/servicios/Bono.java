package gestionAplicacion.servicios;
import java.util.ArrayList;

public class Bono {
	
	private int codigo;
	private static ArrayList<Bono> bonosCreados = new ArrayList<>();
	private Producto producto;
	private String tipoServicio;
	
	public Bono(){}
	
	public Bono(int codigo, Producto producto, String tipoServicio) {
		this.codigo = codigo;
		this.producto = producto;
		this.tipoServicio = tipoServicio;
		bonosCreados.add(this);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public static ArrayList<Bono> getBonosCreados() {
		return bonosCreados;
	}

	public static void setBonosCreados(ArrayList<Bono> bonosCreados) {
		Bono.bonosCreados = bonosCreados;
	}


	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}




