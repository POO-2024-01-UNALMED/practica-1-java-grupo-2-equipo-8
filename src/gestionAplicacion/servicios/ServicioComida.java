package gestionAplicacion.servicios;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;


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

	@Override
	public boolean descuentoCompra(MetodoPago metodo) {
		if (!metodo.getNombre().equalsIgnoreCase("Efectivo")) {
			for(int i = 0; i < orden.size(); i++) {
				if (orden.get(i).getTamaño().equalsIgnoreCase("Cangreburger") && (orden.get(i).getPrecio() > 100000)) {
					valorPedido = valorPedido - (valorPedido*0.05);
					return true;
				}
		}
		return false;
		}
		return false;
	}

	@Override
	public void procesarPagoRealizado(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void factura(Cliente cliente) {
		
	}	
	
}
