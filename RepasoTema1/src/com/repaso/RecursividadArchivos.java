package com.repaso;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class RecursividadArchivos {

	public static void main(String[] args) {
	
		//Tenenmos el proyecto CrearBorrarCarpetasEclipse donde nos explica los path y la clase Files
		//Para mas informacion sobre recursividad se puede revisar RecorrerCarpetasRecursivamente
		
						//Clase File esta clase nos permite saber tamaño de un fichero, así como copiar
		
		
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros

		// Creamos los archivos que queremos copiar
		try {
			Files.createDirectories(fs.getPath("./resultado"));
			
			File original = new File("./ejemplo.txt");
			System.out.println("El archivo pesa "+original.length() +" bytes");//sacar peso de un archivo
			// obtenemos la ruta del archivo original
			Path originalPath = fs.getPath(original.toString());
			// obtenemos la ruta de donde vamos a copiarlo + nombre del archivo
			Path copia = fs.getPath("./resultado\\" + original.getName());
				Files.copy(originalPath, copia, StandardCopyOption.REPLACE_EXISTING);
				
				
				 
				//USAR BUCLE PARA RECORRER TREEMAP, HASHMAP Ect mas informacion en RECORRERCARPETARECURSIVAMENTE
				Map<Double, Path> listaArchivos = new TreeMap();
				listaArchivos.put(3.3, fs.getPath("tdspts"));
				for (Entry<Double, Path> entry : listaArchivos.entrySet()) {

					Double key = entry.getKey();
					Path value = entry.getValue();
					System.out.println("La clave es "+key +" el valor es "+ value.toString());
				}
				
				
				
				
				
				
				
				
				
				
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	 /**
     * Mediante esta funcion recursiva con el DirectoryStream vamos en bucle recorriendo las carpetas que haya
     * y dentro de un if mirando si las carpetas hijas tienen archivo pues la borramos llamando a la misma llamada
     * @param carpeta carpeta a la que queremos borrar
     * @return un String concatenando todas las carpetas que hayamos borrado
     * @throws IOException 
     */
     public static String borrarCarpeta(Path carpeta) throws IOException{
         String res="";
        DirectoryStream<Path> hijosCarpeta=Files.newDirectoryStream(carpeta); 
            for (Path entry: hijosCarpeta) {
            	//Si entry es una carpeta
                if (Files.isDirectory(entry)) {
                	//Llamamos Recursivamente a la carpeta para que entre
                    res+=borrarCarpeta(entry);
                    
                }else{
                	//si es un archivo pues lo borramos
                    res+="Borrando "+entry+"\n";
                Files.delete(entry);
                }
                
            }
            res+="Borrando "+carpeta+"\n";
            Files.delete(carpeta);
            return res;
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

}
