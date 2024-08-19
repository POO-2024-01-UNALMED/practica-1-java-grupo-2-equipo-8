package gestionAplicacion.servicios.herencia;
import java.time.LocalDate;
import java.util.ArrayList;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;

public class ServicioSouvenirs extends Servicio{
	
	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre) {
		super(nombre);
	}
	
	/**
	* @Override
	*Description: Me verifica si el metodo de pago tiene un descuento asociado y si 
	*cumple la condicion para generar su descuento
	*@param metodo : Recibe un parametro de tipo metodo de pago el cual 
	*nos sirve para saber si tiene descuento o no
	*@return <b>boolean</b> :Retorna un boolean para informarle al usuario que si se hizo el descuento
	*/
	public boolean descontarPorCompra(MetodoPago metodo) {
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
		
		MetodoPago.asignarMetodosDePago(cliente);
		
		ArrayList<Producto> orden1 = new ArrayList<>();
		orden = orden1;
		valorPedido = 0.0;
	}

	@Override
	public String factura() {
		String factura;
		factura="                          CINEMAR \n"+
				"==================== Factura de Souvenir ====================\n"+
				" Nombre dueño : " + this.getCliente().getNombre() + "\n" +
				" Fecha de compra: "+ LocalDate.now() + "\n" +
				this.mostrarOrden()+ "\n" +
				" Total a pagar aplicando descuentos : $" + valorPedido+ "\n"+
				"=============================================================\n";
		return factura;

		
	}
	
}
