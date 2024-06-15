package gestionAplicacion.usuario;

import java.time.LocalDateTime;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.*;
import gestionAplicacion.servicios.Arkade;

public class Ticket implements IBuyable{
	
	private static int cantidadTicketsCreados = 0;
	private int idTicket;
	private Cliente dueno;
	private SalaCine salaDeCine;
	private double precio;
	private Pelicula pelicula;
	private LocalDateTime horario;
	private int idPelicula; //De la película tomar el Id
	private String numeroAsiento;
	private String codigo; //No cambiar

	//Constructors
	public Ticket(){
		Ticket.cantidadTicketsCreados++;
	}
	
	public Ticket(Cliente dueno, SalaCine salaDeCine, double precio, Pelicula pelicula, LocalDateTime horario, String numeroAsiento) {
		this.dueno = dueno;
		this.salaDeCine = salaDeCine;
		this.precio = precio;
		this.pelicula = pelicula;
		this.horario = horario;
		this.numeroAsiento = numeroAsiento;
		Ticket.cantidadTicketsCreados++;
		this.idTicket = Ticket.cantidadTicketsCreados;
		this.codigo = this.generarCodigoTicket();
		//Arkade.getCodigosGenerados().add(this.codigo);
		this.dueno.getCodigosDescuento().add(this.codigo);
	}
	
	public Ticket(Cliente dueno, Pelicula pelicula, LocalDateTime horario, String numeroAsiento, SucursalCine sucursalCine) {
		this.dueno = dueno;
		this.pelicula = pelicula;
		this.idTicket = Ticket.cantidadTicketsCreados;
		Ticket.cantidadTicketsCreados++;
		this.codigo = this.generarCodigoTicket();
		//Arkade.getCodigosGenerados().add(this.codigo);
		this.dueno.getCodigosDescuento().add(this.codigo);
		this.numeroAsiento = numeroAsiento;
		this.horario = horario;

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
				precio = precio  * 0.2;
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
	 * Description: Este método se encarga de generar el último paso del proceso de pago, eliminando la referencia del
	 * método de pago usado y se vuelven a settear los metodos de pago que el cliente tendrá disponibles,
	 * se le pasa la referencia del ticket al usuario y se crea una referencia de este en el arraylist de los tickets creados en el cine
	 * (Este método al igual que el anterior será ejecutado por un ticket luego de ser verificado el pago).
	 * @param cliente : Se pide como parámetro el cliente que realiza el pago, con el fin de pasarle la referencia del
	 * ticket adquirido por este
	 * @return <b>void</b> : Este método no retorna nada, solo crea y elimina referencias del método de pago en métodos de pago
	 * disponibles y usados, y de ticket en la sala de cine asociada y en el cliente que compra el ticket
	 */
	public void procesarPagoRealizado(Cliente cliente) {
		//Se reestablecen los métodos de pago disponibles del cliente
		MetodoPago.asignarMetodosDePago(cliente);
		
		//Se eliminan las referencias de los métodosDePagoUsados
		MetodoPago.getMetodosDePagoUsados().clear();
		
		//Se pasa la referencia del ticket al cliente que lo compró
		cliente.getTickets().add(this);
		
		//Se crea la referencia del ticket en el array de ticketsCreados de la sala de cine asociada a este
		SalaCine.getTicketsCreados().add(this);
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
				"Número de sala : " + this.getSalaDeCine().getNumeroSala() + "\n" +
				"Número de asiento : " + this.getNumeroAsiento() + "\n" +
				"Fecha Presentación: " + this.getHorario().toLocalDate() + "\n" +
				"Hora Presentación: " + this.getHorario().toLocalTime() + "\n" + 
				"Valor ticket (IVA incluido): " + this.getPrecio() + "\n" + 
				"Fecha de compra: " + SucursalCine.getFechaActual();
				
		cliente.getFacturas().add(factura);
		return factura;
	}
	
	private String generarCodigoTicket() {
		String codigoTicket = this.getPelicula().getTipoDeFormato()+this.getDueno().getTipoDocumento()+this.getPelicula().getNumeroDeSala()+"-"+this.getPelicula().getGenero();

		return codigoTicket;
	}
	
	public static String encontrarGeneroCodigoPelicula(String codigo) {
		int indiceGuion = codigo.indexOf("-");

        if (indiceGuion != -1 && indiceGuion != codigo.length() - 1) {

            return codigo.substring(indiceGuion + 1);
        } else {

            return "";
        }
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
