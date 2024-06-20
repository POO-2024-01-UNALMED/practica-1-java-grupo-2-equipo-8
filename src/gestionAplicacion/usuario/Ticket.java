package gestionAplicacion.usuario;

import java.time.LocalDateTime;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Arkade;

public class Ticket implements IBuyable{
	
	private static int cantidadTicketsCreados = 1;
	private int idTicket;
	private Cliente dueno;
	private SalaCine salaDeCine;
	private Pelicula pelicula;
	private LocalDateTime horario;
	private String numeroAsiento;
	private double precio;

	//Constructors
	public Ticket(Pelicula pelicula, LocalDateTime horario, String numeroAsiento, SucursalCine sucursalCine) {
		this.pelicula = pelicula;
		this.idTicket = Ticket.cantidadTicketsCreados;
		this.numeroAsiento = numeroAsiento;
		this.horario = horario;
		this.precio = this.clienteSuertudo();
		this.salaDeCine = pelicula.getSalaPresentacion();
	}
	
	//Methods
	/**
	 * Description : Este método se encarga de verificar si se puede aplicar o no un descuento sobre el precio de la pelicula
	 * según si la cantidad de tickets creados corresponde a un cuadrado perfecto.
	 * @return : <b>double</b> : Retorna un double (De tipo double) que corresponde al precio del ticket en caso de aplicarse
	 * o no el descuento.
	 * */
	private double clienteSuertudo() {
		double posibleCuadradoPerfecto = Math.sqrt(Ticket.cantidadTicketsCreados) % 1;
		boolean verificacion = (posibleCuadradoPerfecto == 0) ? true : false; 
		double precio = this.pelicula.getPrecio();
		if(verificacion){
			if (this.pelicula.getTipoDeFormato().equals("3D") || this.pelicula.getTipoDeFormato().equals("4D") ) {
				precio = precio * 0.5;
			}else {
				precio = precio  * 0.2;
			}
		}
		return precio;
	}

	/**
	 * @Override
	 * Description: Este método se encarga de generar el último paso del proceso de pago y será ejecutado por un ticket luego de ser verificado el pago: 
	 * 1. Se vuelven a settear los metodos de pago que el cliente tendrá disponibles.
	 * 2. Se pasa la referencia del ticket al array de tickets del usuario.
	 * 3. Se pasa la referencia del cliente al atributo dueño del ticket.
	 * 4. Se aumenta la cantidad de tickets genereados en uno.
	 * 5. Se crea una referencia de este ticket en el arraylist de los tickets creados en el cine.
	 * 6. Se crea la factura y se le asoscia al cliente
	 * 7. Se crea el código de descuento para los juegos y se asocian al cliente y a los códigos de descuentos generados en la clase Arkade.
	 * @param cliente : Se pide como parámetro el cliente (De tipo Cliente) que realizó exitosamente el pago.
	 */
	public void procesarPagoRealizado(Cliente cliente) {
		//Se reestablecen los métodos de pago disponibles del cliente
		MetodoPago.asignarMetodosDePago(cliente);
		
		//Se pasa la referencia del ticket al cliente que lo compró
		cliente.getTickets().add(this);
		this.setDueno(cliente);
		
		//Se aumenta la cantidad de tickets creados
		Ticket.cantidadTicketsCreados++;
		
		//Se crea un apuntador del ticket en el array de tickets generados de la sucursal de cine
		cliente.getCine().getTicketsCreados().add(this);
		
		//Creamos la factura y se la asociamos al cliente
		this.factura(cliente);
		
		//Proceso para funcionalidad 4
		String codigoArkade = this.generarCodigoTicket();
		Arkade.getCodigosGenerados().add(codigoArkade);
		this.dueno.getCodigosDescuento().add(codigoArkade);
	}
	
	
	/**
	 * @Override
	 * Description: Este método se encarga de pasar la factura de la compra del ticket al array de facturas del usuario que realizó la compra
	 * además, retorna un string que contiene toda la información del ticket en forma de factura.
	 * @param cliente : Este método solicita un cliente de tipo Cliente como parámetro con el fin de asociarle a este la factura
	 * que verifica su compra realizada
	 * */
	public void factura(Cliente cliente) {
		String factura = "         Cinemar\n" +
				"=== Factura de Ticket ===\n" +
				"Nombre dueño : " + this.getDueno().getNombre() + "\n" +
				"Documento : " + this.getDueno().getDocumento() + "\n" +
				"Pelicula : " + this.getPelicula().getNombre() + "\n" +
				"Número de sala : " + this.getSalaDeCine().getNumeroSala() + "\n" +
				"Número de asiento : " + this.getNumeroAsiento() + "\n" +
				"Fecha Presentación: " + this.getHorario().toLocalDate() + "\n" +
				"Hora Presentación: " + this.getHorario().toLocalTime() + "\n" + 
				"Valor ticket (IVA incluido): " + this.getPrecio() + "\n" + 
				"Fecha de compra: " + SucursalCine.getFechaActual().withNano(0);
				
		cliente.getFacturas().add(factura);
	}
	
	private String generarCodigoTicket() {
		String codigoTicket = this.getPelicula().getTipoDeFormato()+this.getDueno().getTipoDocumento()+this.getPelicula().getSalaPresentacion().getNumeroSala()+"-"+this.getPelicula().getGenero();

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
	
}
