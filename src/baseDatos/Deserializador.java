package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import matricula.Departamento;
import matricula.Asignatura;
import matricula.Alumno;

public class Deserializador {
	// Este atributo es para definir la ruta al directoria temp que contiene las clases.
	private static File rutaTemp = new File ("src\\baseDatos\\temp");
	// Este método se encarga de cargar las listas de objectos que hay almacenados (serializados).
	public static void deserializar (Departamento dpto) {
		File [] docs = rutaTemp.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;
		
		for (File file : docs) {
			if (file.getAbsolutePath().contains("asignaturas")) {
				try {
					fis = new FileInputStream (file);
					ois = new ObjectInputStream (fis);
					
					dpto.setAsignaturas((List<Asignatura>) ois.readObject());
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
				
			} else if (file.getAbsolutePath().contains("alumnos")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					dpto.setAlumnos((List<Alumno>) ois.readObject());
					
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
			}
		}
	}
}
