package gestionAplicacion.servicios;
import java.util.ArrayList;

public class Bono {
	
	private int codigo;
	private static ArrayList<Bono> bonosCreados = new ArrayList<>();
	private static ArrayList<Bono> bonosUsados = new ArrayList<>();
	private String producto;
	private String tamaño;
	private String tipoServicio;
	
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

	public static ArrayList<Bono> getBonosUsados() {
		return bonosUsados;
	}

	public static void setBonosUsados(ArrayList<Bono> bonosUsados) {
		Bono.bonosUsados = bonosUsados;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTamaño() {
		return tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Bono(){}
	
	public Bono(int codigo, String producto, String tamaño, String tipoServicio) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.tamaño = tamaño;
		this.tipoServicio = tipoServicio;
	}



	public boolean validarbono() {return true;}

}
