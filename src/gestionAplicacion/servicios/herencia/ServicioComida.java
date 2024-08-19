package gestionAplicacion.servicios.herencia;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class ServicioComida extends Servicio {
	
	public ServicioComida() {
	}

	public ServicioComida(String nombre) {
		super(nombre);
	}
	
	/**
	 *@Override
	 * Description: Este metodo filtra y actualiza los productos que hay en el inventerio dependiendo de la sucursal de cine y del tipo del producto
	 * @return <b>inventarii</b> : Genera un inventario con los productos disponibles del servicio segun su localidad para tener una carta mas
	 *         eficiente a la hora de mostrarla al cliente
	 */
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

	/**
	* @Override
	*Description: Me verifica si el metodo de pago tiene un descuento asociado y si 
	*cumple la condicion para generar su descuento
	*@param metodo : Recibe un parametro de tipo metodo de pago el cual 
	*nos sirve para saber si tiene descuento o no
	*@return <b>boolean</b> :Retorna un boolean para informarle al usuario que si se hizo el descuento
	*/
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

	/**	@Override
	 * Description: Este metodo me restablece los metodos de pago del cliente,
	 *  ademas de restablecerme la orden y el valor del pedido
	 * @param cliente : se resive un cliente para poder restablecerte los metodos de pago
	 */
	public void procesarPagoRealizado(Cliente cliente) {
		
		MetodoPago.asignarMetodosDePago(cliente);
		
		ArrayList<Producto> orden1 = new ArrayList<>();
		orden = orden1;
		valorPedido = 0.0;
		
	}

	/**	@Override
	 * Description: Me genera una factura la cual me muestra toda la orden y con su informacion
	 *  y fecha de compra
	 * @return <b>fartura</b> : Genera un String con la fecha actual, el nombre del cliente,
	 * y el total menos sus descuentos
	 */
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
