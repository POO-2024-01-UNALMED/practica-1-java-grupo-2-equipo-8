package gestionAplicacion.usuario;


/**
*<b>Description</b>: Esta es la interfaz en la cual se harán los pagos. Por el momento, se usan
*el método de realizarPago, que retorna un double que representa el saldo pendiente
*del cliente luego de llamar al método de pago, 
*el método de factura, para mostrar en pantalla los detalles de la compra realizada, y 
*el método procesarPagoRealizada, en el cuál se encuentran lo procesos realizados luego de
*verificar que el pago fue realizado.
*/
public interface IBuyable {
	
	public abstract void procesarPagoRealizado(Cliente cliente);
	public abstract String factura();

}
