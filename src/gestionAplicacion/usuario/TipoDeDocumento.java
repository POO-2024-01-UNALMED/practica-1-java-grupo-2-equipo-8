package gestionAplicacion.usuario;

public enum TipoDeDocumento {
	
	CC("Cedula de ciudadania"), TI("Targera de identidad"), CE("Cedula de extranjeria");
	
	private String nombre;
	
	private TipoDeDocumento(String nombre) {this.nombre = nombre;}
		
}
