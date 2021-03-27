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
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * 1 - Pida por teclado una ruta de carpeta existente en el Sistema operativo. 
 * Que vuelva a pedirla tantas veces como sea necesario, mientras que se le suministre una ruta que no exista,
 *  o que no sea una carpeta.
2 - Cree en la raíz del proyecto una carpeta llamada “resultado”. En caso de que ya existiera, deberá borrarse 
recursivamente, y volver a crearse de nuevo.
3 - Cree dentro de la carpeta resultado, un archivo “informe.txt” y otra carpeta llamada “archivos menores”.
4 - Recorra recursivamente todo el subárbol que cuelga de la carpeta indicada en el punto 1, haciendo lo siguiente. 
Usa dentro de la función recursiva las herramientas que quieras, de forma que cuando acabe todo el recorrido:
En la carpeta “archivos menores” se hayan copiado los 10 (como máximo) archivos más pequeños que había en ese árbol de directorios.
En “informe.txt”, se haga una lista de las rutas absolutas de los 10 archivos más grandes encontrados, junto a su tamaño en MB, 
ordenados por tamaño. Cada uno en una línea. Después, una lista de las rutas relativas de los 10 archivos más pequeños encontrados,
 junto a su tamaño en MB. Por último, un informe sobre cuántos archivos se han encontrado, y la media de tamaño de todos los archivos.
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

			LinkedList<Path> listaArchivos = new LinkedList();// Lista con todos los path(archivos) de la carpeta
																// elegida
			copiarArchivos(rutaCarpeta, listaArchivos);// para conseguir todos los archivos de la carpeta elegida
			double media = 0;

			Map<Double, File> listaOrdenada = new TreeMap();
			// insertamos los archivos ordenados en el treemap
			for (int i = 0; i < listaArchivos.size(); i++) {
				File archivo = new File(listaArchivos.get(i).toString());

				double ocupaFichero = (double) (archivo.length() / 1024);
				double ocupaMegas = (double) ocupaFichero / 1024;
				Double tamaño = ocupaMegas;
				media += ocupaMegas;
				listaOrdenada.put(tamaño, archivo);// se ordena por Integer

			}

			LinkedList<File> archivosPequeñosRuta10 = new LinkedList();
			// Imprimimos de menora mayor todos los archivos
			listaOrdenada.entrySet().forEach(
					entry -> System.out.println("Clave: " + entry.getKey() + "MB -> Valor: " + entry.getValue()));

			System.out.println("---------");
			int contaMenor = 0;// contador que usaremos para coger los 10 archivos mas pequeños
			// Usamos este for para coger los 10 archivos mas pequeños y lo guardamos en un
			// LinkedList
			for (Map.Entry<Double, File> entry : listaOrdenada.entrySet()) {

				Double key = entry.getKey();// clave TAMAÑO
				File value = entry.getValue();// valor FILE

				archivosPequeñosRuta10.add(value);
				contaMenor++;
				if (contaMenor == 10) {
					break;
				}

			}

			FileWriter info;

			try {
				info = new FileWriter("./resultado\\informe.txt", true);
				info.write("10'Archivos mas pequeños \n--------------------------\n");
				System.out.println("Mas pequeñosss - ---------");
				//vamos a imprimir los 10 mas pequeños y a escribirlo al informe.txt
				for (int i = 0; i < archivosPequeñosRuta10.size(); i++) {

					info.write(archivosPequeñosRuta10.get(i).getAbsolutePath() + " Tamaño "
							+ (double) (archivosPequeñosRuta10.get(i).length() / 1024) / 1024 + " MB\n");
					System.out.println(archivosPequeñosRuta10.get(i).getAbsolutePath() + " Tamaño "
							+ (double) (archivosPequeñosRuta10.get(i).length() / 1024) / 1024 + " MB");

				}
				System.out.println("-----------------------------------");
				info.write("\n");
				info.flush();
				info.close();
				LinkedList<File> archivosEnModoLinked = new LinkedList();

//metemos la coleccion ordenada en un linkedlist de tipo File
				for (Map.Entry<Double, File> entry : listaOrdenada.entrySet()) {
					Double key = entry.getKey();
					File value = entry.getValue();

					archivosEnModoLinked.add(value);

				}

				System.out.println("Archivos Ordenados en un LinkedList");
				for (int i = 0; i < archivosEnModoLinked.size(); i++) {
					System.out.println(archivosEnModoLinked.get(i).getAbsolutePath() + " Tamaño "
							+ (double) (archivosEnModoLinked.get(i).length() / 1024) / 1024 + " MB");

				}
				//obtenemos los 10 archivos mas grandes
				LinkedList<File> archivosGrandes = new LinkedList();

				int contaGrande = 10;
				for (int i = archivosEnModoLinked.size() - 1; i >= 0; i--) {
					archivosGrandes.add(archivosEnModoLinked.get(i));
					contaGrande--;
					if (contaGrande == 0) {
						break;
					}

				}

				System.out.println("10 Archivos mas Grandes------------------");

				info = new FileWriter("./resultado\\informe.txt", true);
				info.write("Archivos Mas grandes \n---------------------\n");
				for (int i = 0; i < archivosGrandes.size(); i++) {

					info.write(archivosGrandes.get(i).getAbsolutePath() + " Tamaño "
							+ (double) (archivosGrandes.get(i).length() / 2024) / 1024 + "MB\n");
					System.out.println(archivosGrandes.get(i).getAbsolutePath() + " Tamaño "
							+ (double) (archivosGrandes.get(i).length() / 2024) / 1024 + "MB");

				}

				info.write("\n----------------------------\n");
				info.write(
						"Numeros de archivos en " + rutaCarpeta.toAbsolutePath() + ": " + archivosEnModoLinked.size() + "\n");
				info.write("Media de tamaño de los archivos " + (double) media / archivosEnModoLinked.size() + " MB");
				info.flush();
				info.close();
				System.out.println(
						"Numeros de archivos en " + rutaCarpeta.toAbsolutePath() + ": " + archivosEnModoLinked.size() + "\n");
				System.out.println(
						"Media de tamaño de los archivos " + (double) media / archivosEnModoLinked.size() + " MB");
				
				

				copiarFicherosPequeños(archivosPequeñosRuta10);

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

		DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una
											// especie
		// de lista para guardar varios Path

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
	 * @param ruta  de donde copiaremos los archivos
	 * @param lista lista donde tendremos todos los archivos del subdirectorio
	 */
	public static void copiarArchivos(Path ruta, LinkedList<Path> lista) {
		DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una
											// especie
		// de lista para guardar varios Path
		String res = "";

		try {
			hijosCarpeta = Files.newDirectoryStream(ruta);

			for (Path entry : hijosCarpeta) {
				// si en document hay una carpeta buscamos en la carpeta
				if (Files.isDirectory(entry)) {
					System.out.println("Entrando en " + entry);
					// hacemos una misma llamada para ver cuando ficheros hay :)
					copiarArchivos(entry, lista);

				} else {// con este else copiamos los ficheros
					System.out.println("Copiamos archivo " + entry);
					lista.add(entry.toAbsolutePath());
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Funcion para copiar archivos lo que hacemos es obtener los 10 primeros archivos  y en un for lo vamos ir copiando
	 * a l path copia que es resultado\archivos chicos
	 * @param lista de tipo File donde tenemos los 10 archivos más pequeños
	 */
	public static void copiarFicherosPequeños(LinkedList<File>lista) {
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros
		for (int i = 0; i < lista.size(); i++) {
			//Creamos los archivos que queremos copiar
			File original =lista.get(i);
			//obtenemos la ruta del archivo original
			Path originalPath=original.toPath();
			//obtenemos la ruta de donde vamos a copiarlo + nombre del archivo
			Path copia=fs.getPath("./resultado\\archivos chicos\\"+original.getName());
			try {
				Files.copy(originalPath, copia,StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
