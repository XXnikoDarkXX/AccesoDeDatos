package com.mian;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * 1 - Pida por teclado una ruta de carpeta existente en el Sistema operativo. 
 * Que vuelva a pedirla tantas veces como sea necesario, mientras que se le suministre una ruta que no exista,
 *  o que no sea una carpeta.
2 - Cree en la ra�z del proyecto una carpeta llamada �resultado�. En caso de que ya existiera, deber� borrarse 
recursivamente, y volver a crearse de nuevo.
3 - Cree dentro de la carpeta resultado, un archivo �informe.txt� y otra carpeta llamada �archivos menores�.
4 - Recorra recursivamente todo el sub�rbol que cuelga de la carpeta indicada en el punto 1, haciendo lo siguiente. 
Usa dentro de la funci�n recursiva las herramientas que quieras, de forma que cuando acabe todo el recorrido:
En la carpeta �archivos menores� se hayan copiado los 10 (como m�ximo) archivos m�s peque�os que hab�a en ese �rbol de directorios.
En �informe.txt�, se haga una lista de las rutas absolutas de los 10 archivos m�s grandes encontrados, junto a su tama�o en MB, 
ordenados por tama�o. Cada uno en una l�nea. Despu�s, una lista de las rutas relativas de los 10 archivos m�s peque�os encontrados,
 junto a su tama�o en MB. Por �ltimo, un informe sobre cu�ntos archivos se han encontrado, y la media de tama�o de todos los archivos.
Ejemplo de informe.txt:

 */
public class RecorrerCarpetas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros
		// Preguntar si la ruta que pide por teclado debe de ser relativa o absoluta
		Path rutaCarpeta;// Ruta imaginaria
		do {

			System.out.println("Escribe una ruta de una carpeta existente en el pc");
			String carpeta = sc.nextLine();
			rutaCarpeta = fs.getPath(carpeta);
		} while (!Files.isDirectory(rutaCarpeta));// comprobamos si es real la ruta

		System.out.println(
				"la ruta de la carpeta que has escrito es correcta y es" + "\n" + rutaCarpeta.toAbsolutePath());

		// Creamos una carpeta llamada resultado recursivamente
		Path resultado = fs.getPath("resultado");
		System.out.println("Creando la carpeta" + resultado.toAbsolutePath());
		borrarCrearRecursivo(resultado);

		// Creamos archivo txt en las carpetas resultado y carpeta seleccionada

		try {
			Files.createFile(fs.getPath(resultado.toString(), "informe.txt"));
			Files.createDirectory(fs.getPath(resultado.toString() + fs.getSeparator() + "archivos chicos"));

			Map<Double, Path> listaArchivos = new TreeMap(); // elegida
			copiarArchivos(rutaCarpeta, listaArchivos);// para conseguir todos los archivos de la carpeta elegida
			double media = 0;

			// Imprimimos de menora mayor todos los archivos
			listaArchivos.entrySet().forEach(
					entry -> System.out.println("Clave: " + entry.getKey() + "MB -> Valor: " + entry.getValue()));

			System.out.println("---------");

			int contaPequeno = 0;
			// para conseguir la media
			for (Entry<Double, Path> entry : listaArchivos.entrySet()) {
				Double key = entry.getKey();
				Path value = entry.getValue();
				File archivo = new File(value.toString());
				double ocupaFichero = (double) (archivo.length() / 1024);
				double ocupaMegas = (double) ocupaFichero / 1024;

				media += ocupaMegas;

			}
			FileWriter info;

			try {
				info = new FileWriter("./resultado\\informe.txt", true);
				info.write("10'Archivos mas peque�os \n--------------------------\n");
				System.out.println("Mas peque�osss - ---------");
				System.out.println("-----------------------------------");
				info.write("\n");
				info.flush();
				info.close();

				// Usamos este for para coger los archivos lo guardamos
				info = new FileWriter("./resultado\\informe.txt", true);
				// Conseguir los 10 mas pequenos
				for (Entry<Double, Path> entry : listaArchivos.entrySet()) {

					Double key = entry.getKey();
					Path value = entry.getValue();
					File archivo = new File(value.toString());
					double ocupaFichero = (double) (archivo.length() / 1024);
					double ocupaMegas = (double) ocupaFichero / 1024;
					System.out
							.println(archivo.toPath() + " Tama�o " + (double) (archivo.length() / 2024) / 1024 + "MB");

					info.write(
							value.toAbsolutePath() + " Tama�o " + (double) (archivo.length() / 2024) / 1024 + "MB\n");
					contaPequeno++;
					if (contaPequeno == 10) {
						break;
					}
				}

				info.write("\n");
				info.flush();
				info.close();

				// hacemos esta funcion para conseguir un treemap al reves y le pasaamos todos
				// los datos del normal para poder iterar
				// de mayor a menor
				TreeMap<Double, Path> listaAlReves = new TreeMap<Double, Path>((Collections.reverseOrder()));

				for (Entry<Double, Path> entry : listaArchivos.entrySet()) {
					double key = entry.getKey();
					Path value = entry.getValue();
					listaAlReves.put(key, value);
				}
				System.out.println("10 Archivos mas Grandes------------------");

				info = new FileWriter("./resultado\\informe.txt", true);

				info.write("Archivos Mas grandes \n---------------------\n");
				int contaGrande = 0;
				for (Entry<Double, Path> entry : listaAlReves.entrySet()) {

					Double key = entry.getKey();
					Path value = entry.getValue();
					File archivo = new File(value.toString());
					double ocupaFichero = (double) (archivo.length() / 1024);
					double ocupaMegas = (double) ocupaFichero / 1024;
					System.out
							.println(archivo.toPath() + " Tama�o " + (double) (archivo.length() / 2024) / 1024 + "MB");

					info.write(
							value.toAbsolutePath() + " Tama�o " + (double) (archivo.length() / 2024) / 1024 + "MB\n");
					contaGrande++;
					if (contaGrande == 10) {
						break;
					}
				}

				info.write("\n----------------------------\n");
				info.write(
						"Numeros de archivos en " + rutaCarpeta.toAbsolutePath() + ": " + listaArchivos.size() + "\n");
				info.write("Media de tama�o de los archivos " + (double) media / listaArchivos.size() + " MB");
				info.flush();
				info.close();
				System.out.println(
						"Numeros de archivos en " + rutaCarpeta.toAbsolutePath() + ": " + listaArchivos.size() + "\n");
				System.out.println("Media de tama�o de los archivos " + (double) media / listaArchivos.size() + " MB");

				copiarFicherosPeque�os(listaArchivos);

			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Mediante esta funcion creamos la carpeta en la ubicacion indicada si esta
	 * creada usamos la recursivad borrando la carpeta pasada por parametros y la
	 * volvemos a crear y en caso de que la carpeta tenga otras carpetas usaremos
	 * otra funcion recursiva para ir borrando sus archivos
	 * 
	 * @param carpeta ruta de la carpeta a la cual vamos a crear
	 */
	public static void borrarCrearRecursivo(Path carpeta) {

		String res = "";
		// si la carpeta no existe la creamos

		// caso base
		if (!Files.isDirectory(carpeta)) {
			try {
				Files.createDirectories(carpeta);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hemos creado la carpeta" + carpeta.toAbsolutePath());
			// Caso recursivo

		} else {

			DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una
												// especie
			// de lista para guardar varios Path
			try {

				hijosCarpeta = Files.newDirectoryStream(carpeta);// hacemos esto para crear diferentes Path en los
				// subdirectorios de carpeta

				// y en el segundo borrar los ficheros
				for (Path entry : hijosCarpeta) {

					if (Files.isDirectory(entry)) {
						System.out.println("entrando en" + entry);
						borrarhijoRecursivo(entry);
					} else {
						res += "Borrando " + entry + "\n";
						System.out.println("borrando " + entry);
						Files.delete(entry);
					}

				}
				System.out.println("Borrando carpeta " + carpeta);
				Files.delete(carpeta);

				// llamamos otra vez a la funcion recursivamente para crear la carpeta
				borrarCrearRecursivo(carpeta);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Con este metodo borrarmos las carpetas hijas que tengan archivos y otras
	 * carpetas a su vez usando recursividad
	 * 
	 * @param carpetaHija carpeta hija
	 */
	public static void borrarhijoRecursivo(Path carpetaHija) {

		DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una especie
		//de lista para guardar varios Path

		try {

			hijosCarpeta = Files.newDirectoryStream(carpetaHija);// hacemos esto para crear diferentes Path en los
			// subdirectorios de carpeta

			// y en el segundo borrar los ficheros
			for (Path entry : hijosCarpeta) {

				if (Files.isDirectory(entry)) {
					System.out.println("entrando en" + entry);
					borrarhijoRecursivo(entry);
				} else {

					System.out.println("borrando" + entry);
					Files.delete(entry);
				}

			}
			System.out.println("Borrando carpeta " + carpetaHija.toAbsolutePath());
			Files.delete(carpetaHija);

			// llamamos otra vez a la funcion recursivamente para crear la carpeta

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Funcion para recorrer recursivamete un subdirectorio elegido y copiar todas
	 * las rutas de los archivos en un linkedList
	 * 
	 * @param ruta          de donde copiaremos los archivos
	 * @param listaArchivos lista donde tendremos todos los archivos del
	 *                      subdirectorio
	 */
	public static void copiarArchivos(Path ruta, Map<Double, Path> listaArchivos) {
		DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una
		// especie de lista para guardar varios Path

		try {
			hijosCarpeta = Files.newDirectoryStream(ruta);

			for (Path entry : hijosCarpeta) {
				// si en document hay una carpeta buscamos en la carpeta
				if (Files.isDirectory(entry)) {
					System.out.println("Entrando en " + entry);
					// hacemos una misma llamada para ver cuando ficheros hay :)
					copiarArchivos(entry, listaArchivos);

				} else {// con este else copiamos los ficheros
					System.out.println("Copiamos archivo " + entry);
					double tama�o = Files.size(entry);

					listaArchivos.put(tama�o, entry.toAbsolutePath());
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Funcion para copiar archivos lo que hacemos es obtener los 10 primeros
	 * archivos y en un for lo vamos ir copiando a l path copia que es
	 * resultado\archivos chicos
	 * 
	 * @param lista de tipo File donde tenemos los 10 archivos m�s peque�os
	 */
	public static void copiarFicherosPeque�os(Map<Double, Path> lista) {
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros

		int conta = 0;
		for (Entry<Double, Path> entry : lista.entrySet()) {

			Double key = entry.getKey();
			Path value = entry.getValue();
			// Creamos los archivos que queremos copiar
			File original = new File(value.toString());
			// obtenemos la ruta del archivo original
			Path originalPath = value;
			// obtenemos la ruta de donde vamos a copiarlo + nombre del archivo
			Path copia = fs.getPath("./resultado\\archivos chicos\\" + original.getName());
			try {
				Files.copy(originalPath, copia, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			conta++;
			if (conta == 10) {
				break;
			}
		}

	}

}
