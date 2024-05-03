package gestionAplicacion.proyecciones;

public class Asiento {
	
	private String numeroAsiento;
	private boolean disponibilidad = true;
	private static int AsientosCreados;
	
	public Asiento(){
		++AsientosCreados;
	}
	
	public Asiento(int fila, int columna){
		++AsientosCreados;
		this.numeroAsiento = (fila + 1) + "-" + (columna + 1);
	}
	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public static int getAsientosCreados() {
		return AsientosCreados;
	}

	public static void setAsientosCreados(int asientosCreados) {
		AsientosCreados = asientosCreados;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
}

	
