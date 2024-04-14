package gestionAplicacion.usuario;


/**
*<b>Description</b>: Esta es la interfaz en la cual se harán los pagos. Por el momento, se usan
*el método de realizarPago, que es un booleano para saber el cliente puede hacer el pago, y
*el método de factura, para mostrar en pantalla los detalles de la compra realizada
*/
public interface IBuyable {
	
	public boolean realizarPago();
	public String factura();

}
