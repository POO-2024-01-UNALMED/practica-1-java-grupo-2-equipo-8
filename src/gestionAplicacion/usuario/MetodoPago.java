package gestionAplicacion.usuario;

import java.io.Serializable;
import java.util.ArrayList;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.servicios.Bono;

public class MetodoPago implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Atributos
	private String nombre;
	private double descuentoAsociado;
	private double limiteMaximoPago;
	//private static ArrayList<MetodoPago> metodosDePagoUsados = new ArrayList<>();
	private int tipo;
	
	
	//Constructores
	public MetodoPago(){
		SucursalCine.getMetodosDePagoDisponibles().add(this);
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
					resultado = i + ". "+ metodoPago.getNombre()+ " - Descuento: " + (int)(metodoPago.getDescuentoAsociado()*100) + "% - Límite Máximo de pago: " + metodoPago.limiteMaximoPago + "\n";
				}else {
					if (metodoPago.getNombre() == "Puntos") {
						resultado = resultado + i + ". "+ metodoPago.getNombre() + " Saldo: " + (int)(metodoPago.getLimiteMaximoPago());
						continue;}
					resultado = resultado + i + ". " + metodoPago.getNombre() + " - Descuento: " + (int)(metodoPago.getDescuentoAsociado()*100) + "% -  Límite Máximo de pago: " + metodoPago.limiteMaximoPago + "\n";
					
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
		MetodoPago puntos = null;
		for (MetodoPago metodoPagoCliente : cliente.getMetodosDePago()) {
			if (metodoPagoCliente.getNombre() == "Puntos") {
				puntos = metodoPagoCliente;
			}
		}
		cliente.getMetodosDePago().clear();
		
		//Se revisa si el cliente posee una membresia y de ser el caso, se asigna el canje de puntos.
		//como método de pago. Luego de eso, se eliminar 
		Membresia tipoMembresia = cliente.getMembresia();
		int tipoMembresiaInt= 0;
		if (tipoMembresia != null) {
			tipoMembresiaInt = tipoMembresia.getTipoMembresia();
			if (puntos == null) {
				switch (tipoMembresiaInt) {
				case 1: puntos = new MetodoPago("Puntos", 0.0, 5000, tipoMembresiaInt);break;
				case 2: puntos = new MetodoPago("Puntos", 0.0, 10000, tipoMembresiaInt);break;
				}
			} else {
				SucursalCine.getMetodosDePagoDisponibles().add(puntos);
			}
		}
		
		//Se realiza un ciclo para filtrar los métodos de pago por el tipoMembresia del cliente
		//y se añaden sus lista de métodos de pago.
		for (MetodoPago metodoPago : SucursalCine.getMetodosDePagoDisponibles()) {
			if (tipoMembresiaInt == metodoPago.getTipo()) {
				cliente.getMetodosDePago().add(metodoPago);
				
			}
		}
		//Se elimina la referencia del canje de puntos en la lista de métodos de pago estatica.
		SucursalCine.getMetodosDePagoDisponibles().remove(puntos);
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
	
	/**
	*<b>Description</b>: Este método de asignar el método de pago para ser usado.
	*@param int : Se usa el número de la selección para poder escoger el método de pago.
	*@param cliente : Se usa el objeto de cliente para acceder a los métodos de pago.
	*@return <b>MetodoPago</b> : Se retorna el método de pago que coincide con la opción seleccionada.
	*/
	public static MetodoPago usarMetodopago(Cliente cliente, int metodoPagoAUsar) {
		MetodoPago usar = null;
		//Se busca en los métodos de pago del cliente y se hace un apuntador al método de pago que
		//coincida con el indice del método de pago más 1.
		for (MetodoPago metodopago : cliente.getMetodosDePago()) {
			if (metodoPagoAUsar == cliente.getMetodosDePago().indexOf(metodopago) + 1) {
				usar = metodopago;
				break;
			}
		} return usar;
	}
	
	/**
	 * @Override
	 * Description : Este método se encarga de tomar el valor a pagar, aplicar el descuento del método de pago elegido por el cliente
	 * y restarle el monto máximo que se puede pagar con ese método de pago, si el método de pago cubre el valor a pagar, éste se cambia se cambia a 0.
	 * Además, este método se encarga de pasar la referencia del método de pago a los métodos de pago usados y quita la referencia de métodos de pago 
	 * disponibles asociados al cliente.
	 * @param precio : Se pide el valor a pagar, este se obtuvo anteriormente como variable durante el proceso de la funcionalidad
	 * @param cliente : Se pide al cliente que va a efectuar el proceso de realizar pago  
	 * @return <b>double</b> : En caso de que el método de pago cubra el valor a pagar retorna 0, en caso de que no
	 * retorna el valor restante a pagar.
	 * */
	public double realizarPago(double precio, Cliente cliente) {
		//Creamos un atributo con scope de método donde obtenemos el precio del producto,
		//Aplicamos el descuentoAsociado al metodoDePago y le restamos el LimiteMaximoPago
		double valorPagar = ( precio * ( 1 - this.getDescuentoAsociado() ) ) - this.getLimiteMaximoPago();
		if (valorPagar < 0) {
			valorPagar = 0;
		}
				
		//Cuando el método usado sea efectivo, no se pasará a usados
		if (this.getNombre().equals("Efectivo")) {
			return valorPagar;
		}
		//Cuando el método sea Puntos, se realiza el descuento de esos puntos en el saldo.
		if (this.getNombre().equals("Puntos")) {
			this.setLimiteMaximoPago(this.getLimiteMaximoPago()- precio);
			if (this.getLimiteMaximoPago() < 0) {
				this.setLimiteMaximoPago(0);
			}
			return valorPagar;
		}
		
		//Se verifica si el cliente tiene membresia para realizar la acumulación de puntos
		Membresia membresia = cliente.getMembresia();
		double saldoPuntos = 0.0;
		if (membresia != null && !this.getNombre().equals("Puntos")) {
			int tipoMembresia = cliente.getMembresia().getTipoMembresia();
			MetodoPago puntos = null;
			for (MetodoPago metodoPago: cliente.getMetodosDePago()) {
				if (metodoPago.getNombre().equals("Puntos")) {
					puntos = metodoPago;
					break;
				}
			}
			saldoPuntos = puntos.getLimiteMaximoPago();			
			switch (tipoMembresia) {
			
			case 1: puntos.setLimiteMaximoPago(saldoPuntos + (precio * 0.05)); break;
			case 2: puntos.setLimiteMaximoPago(saldoPuntos + (precio * 0.10)); break;
			}
			
		}

		//Pasamos el metodoDePago a metodosDePagoUsados
		//MetodoPago.getMetodosDePagoUsados().add(this);
//		//Pasamos el metodoDePago a metodosDePagoUsados
//		MetodoPago.getMetodosDePagoUsados().add(this);
		
		//Eliminamos su referencia de los metodos de pago asociados al cliente
		cliente.getMetodosDePago().remove(this);
				
		//Retornamos el valor tras efectuar el pago, puede generar un saldo pendiente a pagar o 0
		return valorPagar;
				
	}

	
	//Getters and setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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


//	public static ArrayList<MetodoPago> getMetodosDePagoUsados() {
//		return metodosDePagoUsados;
//	}
//
//
//	public static void setMetodosDePagoUsados(ArrayList<MetodoPago> metodosDePagoUsados) {
//		MetodoPago.metodosDePagoUsados = metodosDePagoUsados;
//	}
}

	