package gestionAplicacion.servicios;
import java.util.ArrayList;
import gestionAplicacion.usuario.Cliente;

public class ServicioComida extends Servicio{
	
	private ArrayList<Producto> ordenComida;
	private double valorPedido;
	
	public ServicioComida(){}
	
	public ServicioComida(String nombre, Cliente cliente, ArrayList<Producto> ordenComida,
			double valorPedido) {
		super(nombre);
		this.ordenComida = ordenComida;
		this.valorPedido = valorPedido;
	}
	
	public ArrayList<Producto> actualizarInventario(){
		ArrayList<Producto> inventarioGeneral = getCliente().getCine().getInventarioCine();
		ArrayList<Producto> inventario = null;
		for(int i = 0;i<inventarioGeneral.size();i++) {
			if(inventarioGeneral.get(i).getTipoProducto().equalsIgnoreCase("Comida") && inventarioGeneral.size() > 0) {
				inventario.add(inventario.get(i));
			}
		}
		return inventario;
	}
	
	public ArrayList<Producto> getOrdenComida() {
		return ordenComida;
	}

	public void setOrdenComida(ArrayList<Producto> ordenComida) {
		this.ordenComida = ordenComida;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

}
