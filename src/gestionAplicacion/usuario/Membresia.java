package gestionAplicacion.usuario;
import java.util.ArrayList;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.servicios.Bono;
import iuMain.Administrador;

public class Membresia {
	
	private String nombre;
	private int categoria;
	private static ArrayList<Membresia> tiposDeMembresia = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private double descuentoAsociado;
	private int valorSuscripcionMensual;
	private int valorDescuentoAplicado;
	private int duracionMembresiaDias;
	private int tipo;
	
	public Membresia(String nombre, int categoria, int valorSuscripcionMensual, int duracionMembresiaDias, int tipo) {
		this();
		this.nombre = nombre;
		this.categoria = categoria;
		this.valorSuscripcionMensual = valorSuscripcionMensual;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipo = tipo;
	}

	public Membresia(){
		tiposDeMembresia.add(this);
	}
	
	public Membresia(String nombre, int categoria, ArrayList<Cliente> clientes, double descuentoAsociado,
			int valorSuscripcionMensual, int valorDescuentoAplicado, int duracionMembresiaDias, int tipo) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.clientes = clientes;
		this.descuentoAsociado = descuentoAsociado;
		this.valorSuscripcionMensual = valorSuscripcionMensual;
		this.valorDescuentoAplicado = valorDescuentoAplicado;
		this.duracionMembresiaDias = duracionMembresiaDias;
		this.tipo = tipo;
		tiposDeMembresia.add(this);
	}
	
	
	public void crearBonoRecargaTarjetaCinemar() {
		
	}
	
	/**
	*<b>Description</b>: Este método se encarga de verificar si el cliente tiene memebresia activa
	*@param cliente : Se pide al cliente para revisar su atributo de tipo Membresia
	*@return <b>string con switch</b> : Se retorna un texto indicando si tiene membresia o no y da
	*las opciones para seguir con la funcionalidad o no.
	*/
	public String verificarMembresia(Cliente cliente) {
		
		if (cliente.getMembresia() == null) {
			return "No tiene membresia, ¿Desea adquirir una? SI/NO";
			
			/*switch (opcion) {
			
			case "SI": adquirirSuscripcion(); break;
			/*case "NO": inicio(); break;*/
			
		} else {
			return "Actualizar o mejorar cuenta SI/NO";
			
			/*switch (opcion) {
			
			case "SI": actualizarSuscripcion(); break;
			/*case "NO": inicio(); break;*/}
		}
		
		
	
	public double asignarDescuento() {
		
		
		
		
		return 2.73;} 
	
	
	public boolean actualizarSuscripcion() {
		
		
		
		return true;}
	
	
	public void adquirirSuscripcion() {
		
		
	}
	public ArrayList<Pelicula> recomendacionPeliculas(){
		
		
		return new ArrayList<>();}
	
	
	public double aumentaLimiteDePago() {
		
		
		return 2.73;}
	
	
	public void recargarRegaloTarjetaCinemar() {
		
		
	}
	
	/**
	*<b>Description</b>: Este método se encarga de mostrar las categorias de membresias disponibles
	*@param none : No se pide parametros
	*@return <b>string</b> : Se retorna un texto mostrando el nombre de las categorias.
	*/
	public static String mostrarCategoria() {
		String resultado = null;
		int i = 1;
		for (Membresia membresia : Membresia.getTiposDeMembresia()) {
			if (resultado == null) {
				resultado = i + ". "+ membresia.getNombre() + "\n";
			}else {
				resultado = resultado + i + ". " + membresia.getNombre() + "\n";
			}
			i++;
		}
		/*System.out.println(resultado)*/;
		return resultado;
	}

	/**
	*<b>Description</b>: Este método verifica a que categorias puede acceder el cliente.
	*@param cliente : Se pide al cliente para revisar su historial de peliculas para la 
	*verificación.
	*@param categoriaSeleccionada : Se pide el número de la categoria que quiera adquirir.
	*@return <b>boolean</b> : Se retorna un dato booleano que indica si el cliente puede 
	*adquirir la categoria de membresia seleccionada.
	*/
	public static boolean verificarRestriccionMembresia(Cliente cliente, int categoriaSeleccionada) {
		boolean esValido = false;
		
		switch (categoriaSeleccionada) {
		
		case 1, 2, 3: esValido = true; break;
		case 4: esValido = (cliente.getHistorialDePeliculas().size() >= 10) ? true : false; break;
		case 5: esValido = (cliente.getHistorialDePeliculas().size() >= 20) ? true : false; break;
		}
		
		return esValido;
	}
	/**
	*<b>Description</b>: Este método asigna el tipo a la categoria de la membresia para
	*ser usado posteriormente en otras funcionalidades.
	*@param none : No se solicitan parametros.
	*@return <b>void</b> : No retorna ningún dato ya que solo actualiza el tipo 
	*de las membresias existentes.
	*/
	public static void asignarTipoMembresia () {
		for (Membresia membresia : Membresia.getTiposDeMembresia()) {
			if (1>= membresia.getCategoria() && membresia.getCategoria() <=3) {
				membresia.setTipo(1);
			} else {
				membresia.setTipo(2);
			}
		}
	}

	//Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracionMembresiaDias() {
		return duracionMembresiaDias;
	}
	
	public void setDuracionMembresiaDias(int duracionMembresiaDias) {
		this.duracionMembresiaDias = duracionMembresiaDias;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public static ArrayList<Membresia> getTiposDeMembresia() {
		return tiposDeMembresia;
	}

	public static void setTiposDeMembresia(ArrayList<Membresia> tiposDeMembresia) {
		Membresia.tiposDeMembresia = tiposDeMembresia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	
	
}




