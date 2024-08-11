package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.Pelicula;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.servicios.Arkade;
import gestionAplicacion.servicios.Bono;
import gestionAplicacion.servicios.Producto;
import gestionAplicacion.servicios.Servicio;
import gestionAplicacion.usuario.Cliente;
import gestionAplicacion.usuario.MetodoPago;
import gestionAplicacion.usuario.TarjetaCinemar;
import gestionAplicacion.usuario.Ticket;

public class Deserializador {
	// Este atributo es para definir la ruta al directoria temp que contiene las clases.
	private static File rutaTemp = new File ("src\\baseDatos\\temp\\sucursales");
	private static File rutaTempLinux = new File ("src/baseDatos/temp/sucursales");
	// Este método se encarga de cargar las listas de objetos que hay almacenados (serializados).
	
	/**
	 * Description : Este método se encarga de deserializar los atributos de instancia de sus correspondientes
	 * instancias de SucursalCine, para esto iteramos sobre los directorios que contienen la información
	 * de cada sucursal por separado, creamos una instancia de esa sucursal, iteramos sobre cada *.txt
	 * obteniendo así la información a deserializar, le asignamos sus atributos de instancia,
	 * solucionamos algunos errores de referencias a objetos (Para hacer correctamente las validaciones durante 
	 * la ejecución del programa) y repetimos este proceso por cada directorio/sucursal. 
	 * 
	 * Atributos que deserializa:
	 * 1. Lugar.
	 * 2. Salas de cine (Corrige errores de referencia).
	 * 3. Peliculas (Corrige errores de referencia).
	 * 4. Bonos.
	 * 5. Cantidad de tickets creados.
	 * 6. Inventario.
	 * 7. Servicios.
	 * 8. Tarjetas cinemar.
	 * 9. Tickets creados.
	 * 
	 * Motivo de corrección de referencias: Al deserializar cambia el espacio en memoria del objeto deserializado
	 * (Investigar esto, ya que ocurre al menos la primera vez que se realiza este proceso) (Crea un nuevo objeto)
	 * y lo guarda en la sucursal correctamente; sin embargo, todos los otros apuntadores a este objeto (En caso de tenerlos)
	 * están apuntando a su espacio en memoria antiguo, si bien encuentran la información relacionada a este objeto,
	 * el objeto correctamente deserializado se encuntra en un espacio en memoria nuevo y esto genera conflictos al realizar validaciones. 
	 * */
	public static void deserializar () {
		File [] dirs = rutaTemp.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;
		
		//Iteramos sobre los directorios de nuestras distintas sucursales
		for (File dir : dirs) {
			
			SucursalCine sucursalCine = new SucursalCine();
				
			File [] docs = dir.listFiles();
			
			//Deserializador
			for (File file : docs) {
				
				if (file.getAbsolutePath().contains("lugar")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setLugar((String) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("salasDeCine")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setSalasDeCine((ArrayList<SalaCine>) ois.readObject());
						for (SalaCine salaDeCine : sucursalCine.getSalasDeCine()) {
							//Reasigna la referencia de la sucursal a la que pertenece (Debido a que acabamos de construir su nueva sucursal).
							//Settea como nulos su película y horario para que se actualize su referencia durante la ejecución del programa.
							salaDeCine.setUbicacionSede(sucursalCine);
							salaDeCine.setPeliculaEnPresentacion(null);
							salaDeCine.setHorarioPeliculaEnPresentacion(null);

						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("tpeliculas")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setCartelera((ArrayList<Pelicula>) ois.readObject());
						for (Pelicula pelicula : sucursalCine.getCartelera()) {
							//Reasigna las referencias de la sucursal a la que pertenece y la sala donde será presentada. 
							//(Las salas ya han sido deserializadas por lo tanto se le asignará la que usará durante la ejecución del programa).
							pelicula.setSucursalCartelera(sucursalCine);
							pelicula.setSalaPresentacion( sucursalCine.obtenerSalaCinePorId(pelicula.getSalaPresentacion().getIdSalaCine()) );
							
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("inventarioCine")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setInventarioCine((ArrayList<Producto>) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("ticketsCreados")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setTicketsCreados((ArrayList<Ticket>) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("servicios")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setServicios((ArrayList<Servicio>) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("bonos")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setBonosCreados((ArrayList<Bono>) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("tarjetasCinemar")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setInventarioTarjetasCinemar((ArrayList<TarjetaCinemar>) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (file.getAbsolutePath().contains("cantidadTicketsCreados")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setCantidadTicketsCreados((int) ois.readObject());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} 
			}
			
		}
	}
	
	//Creamos la ruta donde se encuentran los archivos .txt que guardan la información de los atributos estáticos
	private static File rutaTemp2 = new File ("src\\baseDatos\\temp\\staticAttributes");
	private static File rutaTempLinux2 = new File ("src/baseDatos/temp/staticAttributes");
	//Desearilzamos la información de los atributos estáticos
	/**
	 * Description: Este método se encarga de deserializar los atributos estáticos de la clase sucursal cine, 
	 * accede al directorio que contiene los atributos estáticos, itera sobre cada *.txt e ingresa la respectiva 
	 * información deserializada a la clase, además, corrige algunos errores en las referencias a objetos 
	 * previamente deserializados.
	 * 
	 * Atributos que deserializa:
	 * 1. Clientes (Mantiene el registro de los clientes que han usado la sucursal).
	 * 2. Juegos.
	 * 3. Fecha nuevo día (Para ejecutar la lógica diaria del negocio).
	 * 4. Métodos de pago disponibles
	 * 5. Fecha lógica de negocio (Para ejecutar la lógica semanal del negocio).
	 * 6. Fecha actual (Mantiene la hora en la cual se está ejecutando el programa).
	 * 7. Tickets Disponibles (Renueva las referencias de sus atributos para hacer correctamente las validaciones).
	 * */
	public static void deserializarEstaticos () {
		//Definimos las variables que usaremos durante el proceso
		File [] docs = rutaTemp2.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;

		for (File file : docs) {
			
			if (file.getAbsolutePath().contains("clientes")){
				try{
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setClientes((ArrayList<Cliente>) ois.readObject());
					for (Cliente cliente : SucursalCine.getClientes()) {
						//Limpia los tickets que tienen, estos serán recuperados luedo de deserializar los ticketsCreados.
						cliente.getTickets().clear();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("juegos")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setJuegos((ArrayList<Arkade>) ois.readObject());
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} else if (file.getAbsolutePath().contains("fechaNuevoDia")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setFechaValidacionNuevoDiaDeTrabajo((LocalDate) ois.readObject());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} else if (file.getAbsolutePath().contains("metodosDePagoDisponibles")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setMetodosDePagoDisponibles((ArrayList<MetodoPago>) ois.readObject());
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("fechaLogicaNegocio")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setFechaRevisionLogicaDeNegocio((LocalDate) ois.readObject());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("fechaActual")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setFechaActual((LocalDateTime) ois.readObject());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("ticketsDisponibles")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setTicketsDisponibles((ArrayList<Ticket>) ois.readObject());
					for (Ticket ticket : SucursalCine.getTicketsDisponibles()) {
						//Reasigna los atributos cliente, sucursal, película y sala de cine para realizar validaciones de uso correctamente.
						ticket.agregarTIcketClienteSerializado();
						ticket.setSucursalCompra( SucursalCine.obtenerSucursalPorId(ticket.getPelicula().getSucursalCartelera().getIdSucursal()) );
						ticket.setPelicula( ticket.getSucursalCompra().obtenerPeliculaPorId( ticket.getPelicula().getIdPelicula() ));
						ticket.setSalaDeCine( ticket.getPelicula().getSalaPresentacion() );
						
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
				
		}
		
		//Renueva referencias de atributos de instacia que necesitan de atributos estáticos
		asignarReferenciasDeserializador();
		
	}
	
	/**
	 * Description: Este método se encarga de reasignar las referencias correctamente de los atributos de instancia a cada
	 * sucursal en caso de que estos dependan de alguno de los atributos estáticos, que por orden de deserialización, 
	 * se deserializan antes. Todo esto con el fin de conservar la persistencia de datos.
	 * 
	 * 1. Asigna a cada sucursal los tickets propios de cada una, luego de corregir sus referencias
	 * a cliente, película y sala de presentación en el deserializador estático (Ver método)
	 * */
	private static void asignarReferenciasDeserializador() {
		for (SucursalCine sede : SucursalCine.getSucursalesCine()) {
			
			//Borramos los tickets con las referencias antiguas
			sede.getTicketsCreados().clear();
			
			for (Ticket ticket : SucursalCine.getTicketsDisponibles()) {
				//Validamos si la sede es la misma y su horario de presentación es igual o posterior a la hora actual
				if (ticket.getSucursalCompra().equals(sede) && 
					!ticket.getHorario().toLocalDate().isBefore(SucursalCine.getFechaActual().toLocalDate())) {
					sede.getTicketsCreados().add(ticket);
				}
			}
			
		}
		
	}

}

