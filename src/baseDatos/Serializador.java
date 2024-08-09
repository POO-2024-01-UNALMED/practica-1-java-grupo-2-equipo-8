package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import gestionAplicacion.SucursalCine;
import gestionAplicacion.proyecciones.SalaCine;
import gestionAplicacion.usuario.Ticket;

public class Serializador {
	private static File rutaTemp = new File ("src\\baseDatos\\temp\\sucursales");
	private static File rutaTempLinux = new File ("src/baseDatos/temp/sucursales");
	
	// Este método es el encargado de serializar las listas que están creadas en la clase SucursalCine.
	public static void serializar(SucursalCine sucursalCine) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		File [] dirs = rutaTempLinux.listFiles();
		PrintWriter pw;
		
		//Iteramos sobre los directorios de nuestras distintas sucursales
		for (File dir : dirs) {
			if (dir.getAbsolutePath().contains(sucursalCine.getLugar())) {
				
				File [] docs = dir.listFiles();
				
				// Este método for borra el contenido de los archivos al momento de guardar los objetos para
				// evitar que haya redundancia en los archivos y futuras complicaciones para buscar.
				
				for (File file : docs) {
					try {
						// Al crear este objeto PrintWriter y pasarle como parámetro, la ruta de cada
						// archivo borra lo que haya en ellos automáticamente.
						pw = new PrintWriter(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//Serializa la informacion
				for (File file : docs) {
					
					if (file.getAbsolutePath().contains("lugar")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getLugar());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("salasDeCine")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getSalasDeCine());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("inventarioCine")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getInventarioCine());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else if (file.getAbsolutePath().contains("tpeliculas")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getCartelera());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("ticketsCreados")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getTicketsCreados());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("servicios")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getServicios());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("bonos")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getBonosCreados());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("tarjetasCinemar")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getInventarioTarjetasCinemar());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (file.getAbsolutePath().contains("cantidadTicketsCreados")) {
						try {
							fos = new FileOutputStream(file);
							oos = new ObjectOutputStream(fos);
							oos.writeObject(sucursalCine.getCantidadTicketsCreados());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
			}
		}
	}
	
	private static File rutaTemp2 = new File("src\\baseDatos\\temp\\staticAttributes");
	private static File rutaTempLinux2 = new File ("src/baseDatos/temp/staticAttributes");
	
	//Este método se encarga de serializar las listas estáticas de la clase SucursalCine
	public static void serializar() {
		
		//Creamos las variables que usaremos para este proceso
		File [] docs = rutaTempLinux2.listFiles();
		
		FileOutputStream fos;
		ObjectOutputStream oos;
		PrintWriter pw;
		
		//Limpiamos la información que había anteriormente en todos los txt's
		for (File file : docs) {
			try {
				pw = new PrintWriter(file);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		//Serializamos la información
		for (File file : docs) {
			
			if (file.getAbsolutePath().contains("peliculasDisponibles")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getPeliculasDisponibles());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("clientes")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getClientes());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("juegos")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getJuegos());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("fechaNuevoDia")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getFechaValidacionNuevoDiaDeTrabajo());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("metodosDePagoDisponibles")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getMetodosDePagoDisponibles());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (file.getAbsolutePath().contains("fechaLogicaNegocio")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getFechaValidacionNuevoDiaDeTrabajo());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("fechaActual")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getFechaActual());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (file.getAbsolutePath().contains("salasDeCineDisponibles")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getSalasDeCineDisponibles());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("ticketsDisponibles")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(SucursalCine.getTicketsDisponibles());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}

	
}

