package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import matricula.Departamento;

public class Serializador {
	private static File rutaTemp = new File ("src\\baseDatos\\temp");
	
	// Este método es el encargado de serializar las listas que están creadas en la clase SucursalCine.
	public static void serializar(Departamento dpto) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		File [] docs = rutaTemp.listFiles();
		PrintWriter pw;
		
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
		
		for (File file : docs) {
			
			if (file.getAbsolutePath().contains("asignaturas")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(dpto.getAsignaturas());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (file.getAbsolutePath().contains("alumnos")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(dpto.getAsignaturas());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
