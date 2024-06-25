package gestionAplicacion.servicios;
import java.time.LocalDate;
import java.util.ArrayList;

import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class ServicioSouvenirs extends Servicio{
	
	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre) {
		super(nombre);
	}
	
	@Override
	public boolean descuentarPorCompra(MetodoPago metodo) {
		if (!metodo.getNombre().equalsIgnoreCase("Efectivo")) {
			for(int i = 0; i < orden.size(); i++) {
				if (orden.get(i).getTamaño().equalsIgnoreCase("Katana") && (orden.get(i).getPrecio() > 120000)) {
					valorPedido = valorPedido - (valorPedido*0.1);
					return true;
				}
		}
		return false;
		}
		return false;
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
		
		
	}

	@Override
	public void factura(Cliente cliente) {
		String factura;
		factura="                          CINEMAR \n"+
				"==================== Factura de Souvenir ====================\n"+
				" Nombre dueño : " + this.getCliente().getNombre() + "\n" +
				" Fecha de compra: "+ LocalDate.now() + "\n" +
				this.mostrarOrden()+ "\n" +
				" Total a pagar aplicando descuentos : $" + valorPedido+ "\n";
		this.getCliente().getFacturas().add(factura);
		
	}
	
}
