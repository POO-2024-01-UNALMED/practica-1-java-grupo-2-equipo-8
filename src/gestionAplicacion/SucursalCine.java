package gestionAplicacion;

import java.time.LocalDateTime;
import java.util.ArrayList;

import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Pedido;
import gestionAplicacion.usuario.Ticket;

public class SucursalCine {
	private String lugar;
	private LocalDateTime fechaActual;
	private ArrayList<SalaCine> salasDeCine = new ArrayList<>();
	private ArrayList<Pedido> inventarioCine = new ArrayList<>();
	private ArrayList<Pelicula> cartelera = new ArrayList<>();
	private ArrayList<Ticket> ticketsCreados = new ArrayList<>();
	//private ArrayList<Servicio> servicios = new ArrayList<>();
	private static ArrayList<SucursalCine> sucursalesCine = new ArrayList<>();
	private ArrayList<Bono> bonosCreados = new ArrayList<>();
	//private static ArrayList<Cliente> clientes = new ArrayList<>();
	
	//Methods
	
	/**
	 * Description : Este método se encarga de crear un string que se imprimirá en pantalla para visualizar las 
	 * sucursales de nuestra franquicia a nivel nacional
	 * @return <b>String</b> : Retorna un string con el lugar de nuestras distintas dependencias, con el fin de que el cliente elija a cual de estas desea
	 * ingresar
	 * */
	public static String mostrarSucursalCine(){
		String resultado = null;
		int i = 1;
		for (SucursalCine sucursal : SucursalCine.getSucursalesCine()) {
			if (resultado == null) {
				resultado = "\n" + i + ". Sucursal Cinemar en "  + sucursal.getLugar();
			}else {
				resultado = resultado + "\n" + i + ". Sucursal Cinemar en "  + sucursal.getLugar();
			}
			i++;
		}
		return resultado;
		
	}
	
	//Constructor
	
	public SucursalCine(String lugar) {
		this.lugar = lugar;
		SucursalCine.getSucursalesCine().add(this);
	}
	
	public SucursalCine(String lugar, LocalDateTime fechaActual, ArrayList<SalaCine> salasDeCine,
			ArrayList<Pedido> inventarioCine, ArrayList<Pelicula> peliculasDisponibles,
			ArrayList<Ticket> ticketsCreados, ArrayList<Bono> bonosCreados) {
		super();
		this.lugar = lugar;
		this.fechaActual = fechaActual;
		this.salasDeCine = salasDeCine;
		this.inventarioCine = inventarioCine;
		this.cartelera = peliculasDisponibles;
		this.ticketsCreados = ticketsCreados;
		this.bonosCreados = bonosCreados;
		SucursalCine.getSucursalesCine().add(this);
	}
	
	//Getters and Setters
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public LocalDateTime getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(LocalDateTime fechaActual) {
		this.fechaActual = fechaActual;
	}
	public ArrayList<SalaCine> getSalasDeCine() {
		return salasDeCine;
	}
	public void setSalasDeCine(ArrayList<SalaCine> salasDeCine) {
		this.salasDeCine = salasDeCine;
	}
	public ArrayList<Pedido> getInventarioCine() {
		return inventarioCine;
	}
	public void setInventarioCine(ArrayList<Pedido> inventarioCine) {
		this.inventarioCine = inventarioCine;
	}
	public ArrayList<Pelicula> getCartelera() {
		return cartelera;
	}
	public void setCartelera(ArrayList<Pelicula> cartelera) {
		this.cartelera = cartelera;
	}
	public ArrayList<Ticket> getTicketsCreados() {
		return ticketsCreados;
	}
	public void setTicketsCreados(ArrayList<Ticket> ticketsCreados) {
		this.ticketsCreados = ticketsCreados;
	}
	public static ArrayList<SucursalCine> getSucursalesCine() {
		return sucursalesCine;
	}
	public static void setSucursalesCine(ArrayList<SucursalCine> sucursalesCine) {
		SucursalCine.sucursalesCine = sucursalesCine;
	}
	public ArrayList<Bono> getBonosCreados() {
		return bonosCreados;
	}
	public void setBonosCreados(ArrayList<Bono> bonosCreados) {
		this.bonosCreados = bonosCreados;
	}
	
	
	

}
