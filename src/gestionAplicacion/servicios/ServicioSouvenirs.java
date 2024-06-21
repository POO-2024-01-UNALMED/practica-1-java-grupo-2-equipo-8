package gestionAplicacion.servicios;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class ServicioSouvenirs extends Servicio{
	
	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre) {
		super(nombre);
	}
	
	@Override
	public ArrayList<Producto> actualizarInventario(){
		ArrayList<Producto> inventarioGeneral = getCliente().getCineActual().getInventarioCine();
		ArrayList<Producto> inventario = new ArrayList<Producto>();
		for(int i = 0;i<inventarioGeneral.size();i++) {
			if(inventarioGeneral.get(i).getTipoProducto().equalsIgnoreCase("Souvenir") && inventarioGeneral.size() > 0) {
				inventario.add(inventarioGeneral.get(i));
			}
		}
		return inventario;
	}

	@Override
	public void procesarPagoRealizado(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void factura(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean descuentoCompra(MetodoPago metodo) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
