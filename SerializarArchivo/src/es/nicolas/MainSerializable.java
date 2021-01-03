package es.nicolas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import es.nicolas.serializable.Archivo;
import es.nicolas.serializable.Archivos;

public class MainSerializable {

	public static void main(String[] args) {

		Archivos serializados = new Archivos();
		recorrerArbolRecursivo(serializados);
		
		serializados.GuardarSerial("./otros.cenec");
	}

	public static void recorrerArbolRecursivo(Archivos serial) {

		FileSystem fs = FileSystems.getDefault();
		Path carpeta = fs.getPath("./Árbol");
		DirectoryStream<Path> hijosCarpeta;// Creamos el directoryStream
		recorrerHijo(carpeta, serial);
		
		FileOutputStream fos;
		
	}

	public static void recorrerHijo(Path hijo, Archivos serial) {

		FileSystem fs = FileSystems.getDefault();
		Path carpeta = fs.getPath("./Árbol");
		DirectoryStream<Path> hijosCarpeta;// Creamos el directoryStream
		try {

			hijosCarpeta = Files.newDirectoryStream(hijo);// hacemos esto para crear diferentes Path en los
			// subdirectorios de carpeta

			// y en el segundo borrar los ficheros
			for (Path entry : hijosCarpeta) {

				if (Files.isDirectory(entry)) {
					System.out.println("entrando en" + entry.toString());
					recorrerHijo(entry, serial);
				} else {
					System.out.println("Entrando en: " + entry);

					Path rutaArchivo = entry.getFileName();
					System.out.println("El hijo es: " + rutaArchivo);

					File archivo = new File(rutaArchivo.toString());
					// Encontramos
					if (archivo.getName().endsWith("txt")) {
						System.out.println("Es un txt y es " + rutaArchivo);
						
						EscribirCenec(entry.toString());
						
					} else if (archivo.getName().endsWith("jpg")) {
						System.out.println("Es un jpg y es " + rutaArchivo);
					} else {
						System.out.println("Te añado a la lista de objetosArchivos");
						Archivo arc=new Archivo(entry.toString());
						serial.setListaArchivo(arc);
					}

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void EscribirCenec(String ruta) {
	
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF8"));
			
			// creamos un tipo FileReader porque vamos a leer caracteres
			// y lo metemos en un buffer sirve para poder leer texto de un flujo de
			// caracteres que es FileReader
			String texto=br.readLine();
			String cadena;
			while ((cadena = br.readLine()) != null) {
			//	System.out.println(cadena);
				texto+="\n"+cadena;
			}
			br.close();
			
			StringBuffer buscar = new StringBuffer("cenec");
			String patronBuscado = Pattern.quote(buscar.toString());

			StringBuffer reemplazar = new StringBuffer("centrollolandia");
			String patronReemplazo = Matcher.quoteReplacement(reemplazar.toString());
			
			Pattern pat = Pattern.compile(patronBuscado, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher mat = pat.matcher(texto);

			if (mat.find()) {
				texto = mat.replaceAll(patronReemplazo);
				System.out.println("Resultado: " + texto);
				FileWriter fw=new FileWriter(ruta.toString());
				fw.write(texto);
				fw.flush();
				fw.close();
				
			} else {
				System.out.println("No se encontro el texto");
				System.out.println(texto);
			}

	
			
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
