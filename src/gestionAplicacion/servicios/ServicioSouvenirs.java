package gestionAplicacion.servicios;
import java.util.ArrayList;

public class ServicioSouvenirs extends Servicio{
	
	public ServicioSouvenirs(){}
	
	public ServicioSouvenirs(String nombre) {
		super(nombre);
	}
	
	@Override
	public ArrayList<Producto> actualizarInventario(){
		ArrayList<Producto> inventarioGeneral = getCliente().getCine().getInventarioCine();
		ArrayList<Producto> inventario = new ArrayList<Producto>();
		for(int i = 0;i<inventarioGeneral.size();i++) {
			if(inventarioGeneral.get(i).getTipoProducto().equalsIgnoreCase("Souvenir") && inventarioGeneral.size() > 0) {
				inventario.add(inventarioGeneral.get(i));
			}
		}
		return inventario;
	}
	
}
