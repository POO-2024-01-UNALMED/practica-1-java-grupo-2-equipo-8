package gestionAplicacion.proyecciones;

public class Asiento {

	private int numeroAsiento;
	private boolean disponibilidad = true;
	
	public Asiento(){}
	
	public Asiento(int numeroAsiento, boolean disponibilidad) {
		super();
		this.numeroAsiento = numeroAsiento;
		this.disponibilidad = disponibilidad;
	}



	private void cambiarDisponibilidad() {this.disponibilidad=false;}
}
