package gestionAplicacion.servicios;
import java.util.ArrayList;

public class Bono {
	
	private int codigo;
	private static ArrayList<Bono> bonosCreados = new ArrayList<>();
	private static ArrayList<Bono> bonosUsados = new ArrayList<>();
	private String producto;
	private String tamaño;
	private String tipoServicio;
	
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
