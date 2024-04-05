package gestionAplicacion.usuario;

public class TarjetaCinemar {
	
	private int saldo;
	private boolean estado;
	private Cliente dueno;
	
	public TarjetaCinemar(){}
	
	public TarjetaCinemar(int saldo, boolean estado, Cliente dueno) {
		super();
		this.saldo = saldo;
		this.estado = estado;
		this.dueno = dueno;
	}
	
	
	private void ingresarSaldo() {}
	private void mostarSaldo() {}
	private void hacerPago(int saldo) {}
	private static TarjetaCinemar crearTarjeta() {return new TarjetaCinemar();}
}
