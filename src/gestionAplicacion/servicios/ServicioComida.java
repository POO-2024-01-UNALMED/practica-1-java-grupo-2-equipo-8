package gestionAplicacion.servicios;
import java.util.ArrayList;


public class ServicioComida extends Servicio{
	
	public ServicioComida(){}
	
	public ServicioComida(String nombre) {
		super(nombre);
	}
	
	/**
	*Description: Este metodo filtra y actualiza los productos que hay en el inventerio dependiendo de la
	*sucursal de cine y del tipo del producto
	*@return <b>inventarii</b> : Genera un inventario con los productos disponibles del servicio segun su 
	*localidad para tener una carta mas eficiente a la hora de mostrarla al cliente
	*/
	
	@Override
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
	

}
