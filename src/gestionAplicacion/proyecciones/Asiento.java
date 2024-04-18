package gestionAplicacion.proyecciones;

public class Asiento {

	private int numeroAsiento;
	private boolean disponibilidad = true;
	private static int AsientosCreados;
	
	public Asiento(){}
	
	public Asiento(int numeroAsiento, boolean disponibilidad) {
		this.numeroAsiento = ++AsientosCreados;
		this.disponibilidad = disponibilidad;
	}

	public int getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(int numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	
}

	
