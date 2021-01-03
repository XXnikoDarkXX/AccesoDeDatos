package es.nico.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import es.nico.objetos.RutaEliminadas;





public class Main {

	public static void main(String[] args) {
		Path ficheroBloqueo = FileSystems.getDefault().getPath("./serial.data");
		// Si no existe el fichero bloqueo lo creo
		if (Files.exists(ficheroBloqueo)) {
			try {
				Files.delete(ficheroBloqueo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Path ficheroCompleto = FileSystems.getDefault().getPath("./eliminadas.txt");
			// Si no existe el fichero bloqueo lo creo
			if (Files.exists(ficheroCompleto)) {
				try {
					Files.delete(ficheroCompleto);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		FileSystem fs = FileSystems.getDefault();
		Path carpeta = fs.getPath("./Árbol v3");
		RutaEliminadas serial = new RutaEliminadas();
		String rutaAnterior = "";
		recorrerArbol(carpeta, serial, rutaAnterior);
		serial.GuardarSerial("./serial.data");

	}

	public static void recorrerArbol(Path arbol, RutaEliminadas serial, String rutaAnterior) {

		FileSystem fs = FileSystems.getDefault();

		DirectoryStream<Path> hijosCarpeta;// Creamos el directoryStream
		try {

			hijosCarpeta = Files.newDirectoryStream(arbol);// hacemos esto para crear diferentes Path en los
			// subdirectorios de carpeta

			// y en el segundo borrar los ficheros
			for (Path entry : hijosCarpeta) {
				// Si entra en un directorio volvemos a llamar a la funcion para ver lo que
				// tiene
				// caso recursivo
				if (Files.isDirectory(entry)) {
					System.out.println("entrando en" + entry.toString());
					rutaAnterior = entry.toString();
					recorrerArbol(entry, serial, rutaAnterior);
					// Si encuentra un archivo
					// caso base
				} else {
					System.out.println("Entrando en: " + entry);

					Path rutaArchivo = entry.getFileName();
					System.out.println("El hijo es: " + rutaArchivo);

					File archivo = new File(rutaArchivo.toString());
					// Mediante este if comprobamos en el nombre que extension tiene
					if (archivo.getName().endsWith("jpg") || (archivo.getName().endsWith("png"))) {
						System.out.println("Es un jpg o png y es " + rutaArchivo + " le hacemos rojo");

						File prueba = new File(entry.toString());

						double tamanioArchivo = (double) prueba.length() / 1024;// de bytes a kb
						tamanioArchivo = tamanioArchivo / 1024;// lo pasamos a mg
						String nombreArchivo = archivo.getName();
						if (tamanioArchivo > 1.00) {
							System.out.println("Borrando " + archivo.getName());
							 Files.delete(entry);
							Path rutaMenor = entry;
							System.out.println("La ruta anterior es " + rutaAnterior);

							BufferedWriter escrito = new BufferedWriter(
									new FileWriter(rutaAnterior + "\\" + nombreArchivo + ".txt", true));
							escrito.write("Esto ya no esta aqui");
							escrito.flush();
							escrito.close();
							Path ruta = fs.getPath(rutaAnterior + "\\" + nombreArchivo + ".txt");
							Path rutaNada = fs.getPath(rutaAnterior);

							System.out.println(rutaNada.toAbsolutePath());
							BufferedWriter eliminadas = new BufferedWriter(new FileWriter("./eliminadas.txt", true));

							eliminadas.write(ruta.toAbsolutePath().toString() + "\n");
							eliminadas.flush();
							eliminadas.close();

							serial.setLista(prueba.length(), ruta.toAbsolutePath().toString());

						}

					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
