package gestionAplicacion.proyecciones;

import java.io.Serializable;

public class Asiento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numeroAsiento;
	private boolean disponibilidad = true;
	private static int AsientosCreados;
	
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

	
