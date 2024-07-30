package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	// Este método se encarga de cargar las listas de objectos que hay almacenados (serializados).
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
					
				}else if (file.getAbsolutePath().contains("peliculas")) {
					try {
						fis = new FileInputStream(file);
						ois = new ObjectInputStream(fis);
						sucursalCine.setCartelera((ArrayList<Pelicula>) ois.readObject());
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
					
				} 
			}
			
		}
	}
	
	//Creamos la ruta donde se encuentran los archivos .txt que guardan la información de los atributos estáticos
	private static File rutaTemp2 = new File ("src\\baseDatos\\temp\\staticAttributes");
	private static File rutaTempLinux2 = new File ("src/baseDatos/temp/staticAttributes");
	//Desearilzamos la información de los atributos estáticos
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
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("peliculasDisponibles")){
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					SucursalCine.setPeliculasDisponibles((ArrayList<Pelicula>) ois.readObject());
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
			}
				
		}
	}
}

