package gestionAplicacion.servicios.herencia;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class ServicioComida extends Servicio {
	
	public static final Duration TIEMPO_DE_ESPERA = Duration.ofMinutes(15);
	private boolean[] asignacionDeMesas = new boolean[10];
	
	public ServicioComida() {
	}

	public ServicioComida(String nombre) {
		super(nombre);
	}
	
	
	
	public void organizarMesas() {
		for (int i=0;i<5;i++) {
			int indiceCambio = ((int) (Math.random()*10))%10;
			if (asignacionDeMesas[indiceCambio]) {
				continue;
			}
			else {
				asignacionDeMesas[indiceCambio]=false;
			}
		}
	}
	

	/**
	 * Description: Este metodo filtra y actualiza los productos que hay en el
	 * inventerio dependiendo de la sucursal de cine y del tipo del producto
	 * 
	 * @return <b>inventarii</b> : Genera un inventario con los productos
	 *         disponibles del servicio segun su localidad para tener una carta mas
	 *         eficiente a la hora de mostrarla al cliente
	 */

	@Override
	public ArrayList<Producto> actualizarInventario() {
		ArrayList<Producto> inventarioGeneral = getCliente().getCineActual().getInventarioCine();
		ArrayList<Producto> inventario = new ArrayList<Producto>();
		for (int i = 0; i < inventarioGeneral.size(); i++) {
			if (inventarioGeneral.get(i).getTipoProducto().equalsIgnoreCase("Comida") && inventarioGeneral.size() > 0) {
				inventario.add(inventarioGeneral.get(i));
			}
		}
		return inventario;
	}

	@Override
	public boolean descuentarPorCompra(MetodoPago metodo) {
		if (!metodo.getNombre().equalsIgnoreCase("Efectivo")) {
			for (int i = 0; i < orden.size(); i++) {
				if (orden.get(i).getTamaño().equalsIgnoreCase("Cangreburger") && (orden.get(i).getPrecio() > 100000)) {
					valorPedido = valorPedido - (valorPedido * 0.05);
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public void procesarPagoRealizado(Cliente cliente) {
		
		MetodoPago.asignarMetodosDePago(cliente);
		
		ArrayList<Producto> orden1 = new ArrayList<>();
		orden = orden1;
		valorPedido = 0.0;
		
	}

	@Override
	public String factura() {
		String factura;
		factura="                          CINEMAR \n"+
				"==================== Factura de Comida ====================\n"+
				" Nombre dueño : " + this.getCliente().getNombre() + "\n" +
				" Fecha de compra: "+ LocalDate.now() + "\n" +
				this.mostrarOrden()+ "\n" +
				" Total a pagar aplicando descuentos : $" + valorPedido+ "\n"+
				"===========================================================\n";
		return factura;

	}

}
