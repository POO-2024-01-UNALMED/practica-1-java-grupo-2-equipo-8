package gestionAplicacion.servicios;
import java.util.ArrayList;
import gestionAplicacion.usuario.Cliente;

public class ServicioComida extends Servicio{
	
	
	private double valorPedido;
	
	public ServicioComida(){}
	
	public ServicioComida(String nombre, Cliente cliente, ArrayList<Producto> ordenComida,
			double valorPedido) {
		super(nombre);
		this.valorPedido = valorPedido;
	}
	
	/**
	*Description: Este metodo filtra y actualiza los productos que hay en el inventerio dependiendo de la
	*sucursal de cine y del tipo del producto
	*@return <b>inventarii</b> : Genera un inventario con los productos disponibles del servicio segun su 
	*localidad para tener una carta mas eficiente a la hora de mostrarla al cliente
	*/
	
	public ArrayList<Producto> actualizarInventario(){
		ArrayList<Producto> inventarioGeneral = getCliente().getCine().getInventarioCine();
		ArrayList<Producto> inventario = new ArrayList<Producto>();
		for(int i = 0;i<inventarioGeneral.size();i++) {
			if(inventarioGeneral.get(i).getTipoProducto().equalsIgnoreCase("Comida") && inventarioGeneral.size() > 0) {
				inventario.add(inventarioGeneral.get(i));
			}
		}
		return inventario;
	}
	
	
	

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

}
