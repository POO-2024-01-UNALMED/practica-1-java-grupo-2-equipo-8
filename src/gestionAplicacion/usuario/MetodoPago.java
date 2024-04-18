package gestionAplicacion.usuario;

import java.util.ArrayList;

import gestionAplicacion.servicios.Bono;

public class MetodoPago {
	
	//Atributos
	private String nombre;
	private double descuentoAsociado;
	private double limiteMaximoPago;
	private static ArrayList<MetodoPago> metodosDePagoDisponibles = new ArrayList<>();
	private static ArrayList<MetodoPago> metodosDePagoUsados = new ArrayList<>();
	private double valorServicio;
	private int tipo;
	
	
	//Constructores
	public MetodoPago(){
		metodosDePagoDisponibles.add(this);
	}
	
	
	public MetodoPago(String nombre, double limiteMaximoPago, double descuentoAsociado) {
		this();
		this.nombre = nombre;
		this.limiteMaximoPago = limiteMaximoPago;
		this.descuentoAsociado = descuentoAsociado;
		this.tipo = 0;
	}
	
	public MetodoPago(String nombre, double descuentoAsociado, double limiteMaximoPago, int tipo) {
		this();
		this.nombre = nombre;
		this.descuentoAsociado = descuentoAsociado;
		this.limiteMaximoPago = limiteMaximoPago;
		this.tipo = tipo;
	}

	public void cambiarDisponibilidadMetodoPago() {
		
	}
	
	//Métodos
	
	/**
	*<b>Description</b>: Este método se encarga de mostrar los métodos de pago disponibles con
	*sus descuentos.
	*El resultado puede cambiar si el cliente posee membresia y el tipo de esta.
	*@param cliente : Se usa el objeto de tipo Cliente para acceder a su lista de métodos de pago.
	*@return <b>string</b> : Se retorna un texto mostrando el nombre de los métodos de pago con
	*sus descuentos.
	*/
	public static String mostrarMetodosDePago (Cliente cliente) {
		String resultado = null;
		int i = 1;
		
		//Se recorre la lista de los medios de pagos disponibles en la lista del cliente.
		for (MetodoPago metodoPago : cliente.getMetodosDePago()) {
				if (resultado == null) {
					resultado = i + ". "+ metodoPago.getNombre()+ " Descuento: " + metodoPago.getDescuentoAsociado()*100 + "%" +"\n";
				}else {
					resultado = resultado + i + ". " + metodoPago.getNombre() +" Descuento: " + metodoPago.getDescuentoAsociado()*100+ "%" + "\n";
				}
				i++;
		}
		return resultado;
	}
	/**
	*<b>Description</b>: Este método sobrecargado se encarga de mostrar los métodos de pago disponibles con
	*sus limites maximos de pago para la funcionalidad 4.
	*El resultado puede cambiar si el cliente posee membresia y el tipo de esta.
	*@param metodosPagoCliente : Se usa el Array de los metodos de pago del cliente para mostrarlos por pantalla.
	*@return <b>string</b> : Se retorna un texto mostrando el nombre de los métodos de pago con
	*sus limites maximos de pago.
	*/
	public static String mostrarMetodosDePago (ArrayList<MetodoPago> metodosPagoCliente) {
		String resultado = null;
		int i = 1;
		
		//Se recorre la lista de los medios de pagos disponibles en la lista del cliente.
		for (MetodoPago metodoPago : metodosPagoCliente) {
				if (resultado == null) {
					resultado = i + ". "+ metodoPago.getNombre()+ " -- Recarga máxima: $" + metodoPago.getLimiteMaximoPago() +"\n";
				}else {
					resultado = resultado + i + ". " + metodoPago.getNombre() +" -- Recarga máxima: $"+ metodoPago.getLimiteMaximoPago() +"\n";
				}
				i++;
		}
		return resultado;
	}
	
	/**
	*<b>Description</b>: Este método se encarga de asignar los métodos de pago disponibles por 
	*su tipo de membresia a su lista de métodos de pago.
	*que tiene el cliente.
	*@param cliente : Se usa el objeto de tipo Cliente para revisar su membresia y poder asignar
	*los métodos de pago.
	*@return <b>Lista de métodos de pago</b> : Se retorna una lista mostrando los métodos de pago luego
	*de realizar el filtrado por la membresia.
	*/
	public static ArrayList<MetodoPago> asignarMetodosDePago(Cliente cliente) {
		//Se limpia la lista de métodos de pago, esto en caso de que el cliente haya adquirido una
		//membresia.
		cliente.getMetodosDePago().clear();
		
		//Se revisa si el cliente posee una membresia.
		Membresia tipoMembresia = cliente.getMembresia();
		int tipoMembresiaInt= 0;
		if (tipoMembresia != null) {
			tipoMembresiaInt = tipoMembresia.getTipoMembresia();
		}
		
		//Se realiza un ciclo para filtrar los métodos de pago por el tipoMembresia del cliente
		//y se añaden sus lista de métodos de pago.
		for (MetodoPago metodoPago : MetodoPago.getMetodosDePagoDisponibles()) {
			if (tipoMembresiaInt == metodoPago.getTipo()) {
				cliente.getMetodosDePago().add(metodoPago);
			}
		}
		return cliente.getMetodosDePago();
	}
	
	/**
	*<b>Description</b>: Este método se encarga de crear varias instancias de los métodos de
	*pago con distinto tipo. Esto para usarse en la funcionalidad 5.
	*@param metodopago : Se usa el objeto de MetodoPago para crear sus instancias.
	*@return <b>void</b> : No se retorna dato. Se toman los atributos del objeto para
	*crear varias instancias. Estos valores son modificados dependiendo del número de tipo en
	*el ciclo for.
	*/
	public static void metodoPagoPorTipo (MetodoPago metodopago) {
		//Se realiza un ciclo para crear varias instancias de los métodos de pago variando sus atributos.
		for (int i=1; i < 3; i++) {
			String nombre = metodopago.getNombre();
			int tipo = metodopago.getTipo() + i;
			double descuentoAsociado = metodopago.getDescuentoAsociado() + 0.05 * tipo;
			double limiteMaximoPago = metodopago.getLimiteMaximoPago() + 25000 * tipo;
			new MetodoPago(nombre, descuentoAsociado, limiteMaximoPago, tipo);
		}	
	}
	
	
 	public double aplicarDescuento(Cliente cliente, MetodoPago metodopago) {
 		
 		
 		return 3.1415926535;
 		}
 	
	public boolean usarMetodopago() {
		
		
		
		return true;
		}
	
	//Getters and setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public static ArrayList<MetodoPago> getMetodosDePagoDisponibles() {
		return metodosDePagoDisponibles;
	}


	public static void setMetodosDePagoDisponibles(ArrayList<MetodoPago> metodosDePagoDisponibles) {
		MetodoPago.metodosDePagoDisponibles = metodosDePagoDisponibles;
	}


	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public double getDescuentoAsociado() {
		return descuentoAsociado;
	}


	public void setDescuentoAsociado(double descuentoAsociado) {
		this.descuentoAsociado = descuentoAsociado;
	}


	public double getLimiteMaximoPago() {
		return limiteMaximoPago;
	}


	public void setLimiteMaximoPago(double limiteMaximoPago) {
		this.limiteMaximoPago = limiteMaximoPago;
	}


	public static ArrayList<MetodoPago> getMetodosDePagoUsados() {
		return metodosDePagoUsados;
	}


	public static void setMetodosDePagoUsados(ArrayList<MetodoPago> metodosDePagoUsados) {
		MetodoPago.metodosDePagoUsados = metodosDePagoUsados;
	}


	public double getValorServicio() {
		return valorServicio;
	}


	public void setValorServicio(double valorServicio) {
		this.valorServicio = valorServicio;
	}

}
