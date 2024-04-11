package gestionAplicacion.usuario;

public enum TipoDeDocumento {
	
	CC("Cedula de ciudadania"), TI("Tarjeta de identidad"), CE("Cedula de extranjeria");
	
	private String nombre;
	
	private TipoDeDocumento(String nombre) {this.nombre = nombre;}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
