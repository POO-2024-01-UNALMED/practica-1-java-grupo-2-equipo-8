package gestionAplicacion.usuario;
 
import java.time.LocalDateTime;

import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.ServicioEntretenimiento;

public class Ticket implements IBuyable{
	
	private static int cantidadTicketsCreados = 0;
	private int idTicket;
	private Cliente dueno;
	private SalaCine salaDeCine;
	private Asiento asiento;
	private double precio;
	private Pelicula pelicula;
	private LocalDateTime horario;
	private int idPelicula;
	private String numeroAsiento;
	private String codigo;

	//Constructors
	public Ticket(){
		Ticket.cantidadTicketsCreados++;
		
	}
	
	public Ticket(Cliente dueno, SalaCine salaDeCine, Asiento asiento, double precio, Pelicula pelicula, LocalDateTime horario, int idPelicula) {
		this.dueno = dueno;
		this.salaDeCine = salaDeCine;
		this.asiento = asiento;
		this.precio = precio;
		this.pelicula = pelicula;
		this.horario = horario;
		this.idPelicula = idPelicula;
		Ticket.cantidadTicketsCreados++;
		this.idTicket = Ticket.cantidadTicketsCreados;
		this.codigo = this.generarCodigoTicket();
		ServicioEntretenimiento.getCodigosGenerados().add(this.codigo);
	}
	
	public Ticket(Cliente dueno, Pelicula pelicula, SalaCine salaCine) {
		this.dueno = dueno;
		this.pelicula = pelicula;
		this.idTicket = Ticket.cantidadTicketsCreados;
		this.salaDeCine = salaCine;
		Ticket.cantidadTicketsCreados++;
		this.codigo = this.generarCodigoTicket();
		ServicioEntretenimiento.getCodigosGenerados().add(this.codigo);

	}
	//Methods
	/**
	 * Description : Este método se encarga de verificar si se puede aplicar o no un descuento sobre el precio de la pelicula
	 * según si la cantidad de tickets creados corresponde a un cuadrado perfecto.
	 * @return : <b>double</b> : Retorna un double que posteriormente será aplicado por el metodo asignarPrecio
	 * cuya visibilidad es pública
	 * */
	private double clienteSuertudo() {
		double posibleCuadradoPerfecto = Math.sqrt(Ticket.getCantidadTicketsCreados()) % 1;
		boolean verificacion = (posibleCuadradoPerfecto == 0) ? true : false; 
		double precio = this.getPelicula().getPrecio();
		if(verificacion){
			if (this.getPelicula().getTipoDeFormato().equals("3D") || this.getPelicula().getTipoDeFormato().equals("4D") ) {
				precio = precio * 0.5;
			}else {
				precio = precio  * 0.8;
			}
		}
		return precio;
	}
	
	/**
	 * Description : Este método se encarga de aplicar el precio del ticket, para ello ejecuta el método clienteSuertudo(), que retorna el valor
	 * de aplicar o no el descuento.
	 * @return <b>void</b>: Este método no retorna nada, solo modifica el precio del ticket.
	 * */
	public void asignarPrecio() {
		this.setPrecio(clienteSuertudo());
	}
	
	/**
	 * @Override
	 * Description : Este método se encarga de tomar el valor del pedido o del ticket, aplicar el descuento del método de pago elegido por el cliente
	 * y restarle el monto máximo que se puede pagar con ese método de pago, si el método de pago cubre el valor a pagar, valor a pagar se cambia a 0.
	 * Además, este método se encarga de pasar la referencia del método de pago a los métodos de pago usados y quita la referencia de métodos de pago 
	 * disponibles asociados al cliente.
	 * @param precio : Se pide el precio del ticket a pagar, este se obtuvo anteriormente como variable durante el proceso de la funcionalidad
	 * @param metodoPago : Se usa el método de pago seleccionado por el cliente
	 * @param cliente : Se pide al cliente que va a efectuar el proceso de realizar pago  
	 * @return <b>double</b> : En caso de que el método de pago cubra el valor a pagar retorna 0, en caso de que no
	 * retorna el valor restante a pagar.
	 * */	
	public double realizarPago(double precio, MetodoPago metodoDePago, Cliente cliente) {
		//Creamos un atributo con scope de método donde obtenemos el precio del producto,
		//Aplicamos el descuentoAsociado al metodoDePago y le restamos el LimiteMaximoPago
		double valorPagar = ( precio * ( 1 - metodoDePago.getDescuentoAsociado() ) ) - metodoDePago.getLimiteMaximoPago();
		if (valorPagar < 0) {
			valorPagar = 0;
		}
		
		//Cuando el método usado sea efectivo, no se pasará a usados
		if ( metodoDePago.getNombre().equals("Efectivo")) {
			return valorPagar;
		}
		
		//Pasamos el metodoDePago a metodosDePagoUsados
		MetodoPago.getMetodosDePagoUsados().add(metodoDePago);
		//Eliminamos su referencia de los metodos de pago asociados al cliente
		cliente.getMetodosDePago().remove(metodoDePago);
		
		//Retornamos el valor tras efectuar el pago, puede generar un saldo pendiente a pagar o 0
		return valorPagar;
		
	}
	
	
	/**
	 * @Override
	 * Description: Este método se encarga de generar el último paso del proceso de pago, eliminando la referencia del
	 * método de pago usado y la pasa a métodos de pago disponibles asociados al clienete 
	 * y según el caso, se le pasa la referencia del pedido o ticket al usuario, además, en el caso del ticket, 
	 * se crea una referencia de este en el arraylist de los tickets de la sala de cine asociada 
	 * (Este método al igual que el anterior será ejecutado por un ticket o pedido).
	 * @param cliente : Se pide como parámetro el cliente que realiza el pago, con el fin de pasarle la referencia del
	 * ticket adquirido por este
	 * @return <b>void</b> : Este método no retorna nada, solo crea y elimina referencias del método de pago en métodos de pago
	 * disponibles y usados, y de ticket en la sala de cine asociada y en el cliente que compra el ticket
	 */
	public void procesarPagoRealizado(Cliente cliente) {
		//Se crea la referencia del método en métodos de pago asociado al cliente del método de pago usado
		for (MetodoPago metodoDePago : MetodoPago.getMetodosDePagoUsados()) {
			cliente.getMetodosDePago().add(metodoDePago);
		}
		
		//Se eliminan las referencias de los métodosDePagoUsados
		MetodoPago.getMetodosDePagoUsados().clear();
		
		//Se pasa la referencia del ticket al cliente que lo compró
		cliente.getTickets().add(this);
		
		//Se crea la referencia del ticket en el array de ticketsCreados de la sala de cine asociada a este
		this.getSalaDeCine().getTicketsCreados().add(this);
	}
	
	
	/**
	 * @Override
	 * Description: Este método se encarga de pasar la factura de la compra del ticket al array de facturas del usuario que realizó la compra
	 * además, retorna un string que contiene toda la información del ticket en forma de factura.
	 * @param cliente : Este método solicita un cliente de tipo Cliente como parámetro con el fin de asociarle a este la factura
	 * que verifica su compra realizada
	 * @return <b>factura</b> : Este método retorna un String que contiene la información de la factura con el fin de imprimirla en pantalla
	 * */
	public String factura(Cliente cliente) {
		String factura = "=== Factura de Ticket ===\n" +
				"Nombre dueño : " + this.getDueno().getNombre() + "\n" +
				"Documento : " + this.getDueno().getDocumento() + "\n" +
				"Pelicula : " + this.getPelicula().getNombre() + "\n" +
				"Número de sala : " + this.getPelicula().getNumeroDeSala() + "\n" +
				"Número de asiento : " + this.getNumeroAsiento();
		cliente.getFacturas().add(factura);
		return factura;
	}
	
	private String generarCodigoTicket() {
		String codigoTicket = this.getPelicula().getTipoDeFormato()+this.getDueno().getTipoDocumento()+this.getSalaDeCine().getNumeroSala();
		return codigoTicket;
	}
	
	/*public String toString() {
		return "=== Factura de Ticket ===\n" +
		"Nombre dueño : " + this.getDueno().getNombre() + "\n" +
		"Documento : " + this.getDueno().getDocumento() + "\n" +
		"Pelicula : " + this.getPelicula().getNombre() + "\n" +
		"Número de sala : " + this.getPelicula().getNumeroDeSala() + "\n" +
		"Número de asiento : " + this.getNumeroAsiento();
	}*/
	
	//Getters and Setters
	public Cliente getDueno() {
		return dueno;
	}

	public void setDueno(Cliente dueno) {
		this.dueno = dueno;
	}

	public SalaCine getSalaDeCine() {
		return salaDeCine;
	}

	public void setSalaDeCine(SalaCine salaDeCine) {
		this.salaDeCine = salaDeCine;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public static int getCantidadTicketsCreados() {
		return cantidadTicketsCreados;
	}

	public static void setCantidadTicketsCreados(int cantidadTicketsCreados) {
		Ticket.cantidadTicketsCreados = cantidadTicketsCreados;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
