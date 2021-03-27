package com.mian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class CopiarFicherosRecursivos {

	public static void main(String[] args) {
		
		
		
		Scanner sc = new Scanner(System.in);
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros
		System.out.println("Escribe la ruta de donde sacaremos los archivos");
		String carpetaElegida = sc.nextLine();
		Path ruta = fs.getPath(carpetaElegida);
		
LinkedList<Path> listaArchivos=new LinkedList();//Lista con todos los path(archivos) de la carpeta elegida
 copiarArchivos(ruta,listaArchivos);//para conseguir todos los archivos de la carpeta elegida
double media=0;

Map<Double,File> listaOrdenada=new TreeMap();
//insertamos los archivos ordenados en el treemap
for (int i = 0; i < listaArchivos.size(); i++) {
File archivo=new File (listaArchivos.get(i).toString());
Path resultado = fs.getPath("resultado");
double ocupaFichero=(double)(archivo.length()/1024);
double ocupaMegas=(double)ocupaFichero/1024;
Double tamaño=ocupaMegas;
media+=ocupaMegas;
listaOrdenada.put(tamaño,archivo);//se ordena por Integer

}
LinkedList<File> archivosPequeñosRuta10=new LinkedList();
//Imprimimos de menora mayor todos los archivos
listaOrdenada.entrySet().forEach(entry -> System.out.println("Clave: " + entry.getKey() + "MB -> Valor: " + entry.getValue()));

System.out.println("---------");
int contaMenor=0;//contador que usaremos para coger los 10 archivos mas pequeños

for(Map.Entry<Double,File> entry : listaOrdenada.entrySet()) {
	
	  Double key = entry.getKey();//clave  TAMAÑO
	  File value = entry.getValue();//valor FILE

	  archivosPequeñosRuta10.add(value);
	  contaMenor++;
	  if (contaMenor==10) {
			break;
		}
	  
	}
FileWriter info;
try {
	info = new FileWriter("./resultado\\informe.txt",true);
	info.write("10'Archivos mas pequeños \n--------------------------\n");
System.out.println("Mas pequeñosss - ---------");
for (int i = 0; i < archivosPequeñosRuta10.size(); i++) {
	
	info.write(archivosPequeñosRuta10.get(i).getAbsolutePath()+" Tamaño "+(double)(archivosPequeñosRuta10.get(i).length()/1024)/1024+" MB\n");
	System.out.println(archivosPequeñosRuta10.get(i).getAbsolutePath()+" Tamaño "+(double)(archivosPequeñosRuta10.get(i).length()/1024)/1024+" MB");

	
	
}
System.out.println("-----------------------------------");
info.write("\n");
info.flush();
info.close();
LinkedList<File> archivosEnModoLinked=new LinkedList();

//metemos la coleccion ordenada en un linkedlist de tipo File
for(Map.Entry<Double,File> entry : listaOrdenada.entrySet()) {
	  Double key = entry.getKey();
	  File value = entry.getValue();

	  archivosEnModoLinked.add(value);
	 
	 
	}

System.out.println("Archivos Ordenados en un LinkedList");
for (int i = 0; i < archivosEnModoLinked.size(); i++) {
	System.out.println(archivosEnModoLinked.get(i).getAbsolutePath()+" Tamaño "+(double)(archivosEnModoLinked.get(i).length()/1024)/1024+" MB");

	
}
LinkedList<File> archivosGrandes=new LinkedList();

int contaGrande=10;
for (int i = archivosEnModoLinked.size()-1; i>= 0; i--) {
	archivosGrandes.add(archivosEnModoLinked.get(i));
	contaGrande--;
	if (contaGrande==0) {
		break;
	}
	
	
}



System.out.println("10 Archivos mas Grandes------------------");


info = new FileWriter("./resultado\\informe.txt",true);
info.write("Archivos Mas grandes \n---------------------\n");
		for (int i = 0; i < archivosGrandes.size(); i++) {
		
		
		info.write(archivosGrandes.get(i).getAbsolutePath()+" Tamaño "+(double)(archivosGrandes.get(i).length()/2024)/1024+"MB\n");
		System.out.println(archivosGrandes.get(i).getAbsolutePath()+" Tamaño "+(double)(archivosGrandes.get(i).length()/2024)/1024+"MB");
		
		

	

	
		}
		
		
		info.write("\n----------------------------\n");
		info.write("Numeros de archivos en "+ruta.toAbsolutePath()+": "+archivosEnModoLinked.size()+"\n");
		info.write("Media de tamaño de los archivos "+(double)media/archivosEnModoLinked.size()+" MB");
		info.flush();
		info.close();
		System.out.println("Numeros de archivos en "+ruta.toAbsolutePath()+": "+archivosEnModoLinked.size()+"\n");
		System.out.println("Media de tamaño de los archivos "+(double)media/archivosEnModoLinked.size()+" MB");
} catch (IOException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}


copiarFicherosPequeños(archivosPequeñosRuta10);


//creamos primero el archivo
File original=new File("C:\\Users\\nicoc\\Documents\\EclipseProyectos\\RecorrerCarpetasRecursivamente\\original.txt");
//obtenemos la ruta del archivo
Path originalPath=original.toPath();
//obtenemos la ruta de donde vamos a copiarlo + nombre del archivo
Path copiado=fs.getPath("C:\\Users\\nicoc\\Documents\\EclipseProyectos\\RecorrerCarpetasRecursivamente\\resultado\\archivos chicos\\"+original.getName());
try {
	Files.copy(originalPath, copiado,StandardCopyOption.REPLACE_EXISTING);
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
 

 
	

	
	/**
	 * Funcion recursiva para todos los archivos de un subdirectorio o ruta que pongas se almacena en un LinkedList
	 * @param ruta
	 * @param lista
	 */
	public static void copiarArchivos(Path ruta,LinkedList<Path> lista) {
		DirectoryStream<Path> hijosCarpeta;// Este objeto sirve para poder iterar directorios lo convertimos en una
											// especie
		// de lista para guardar varios Path
		String res = "";
		//LinkedList<Path> lista = new LinkedList();//metemos todas las listas en un directorio

		try {
			hijosCarpeta = Files.newDirectoryStream(ruta);

			for (Path entry : hijosCarpeta) {
				//si en document hay una carpeta buscamos en la carpeta
				if (Files.isDirectory(entry)) {
					System.out.println("Entrando en "+entry);
					//hacemos una misma llamada para ver cuando ficheros hay :)
				copiarArchivos(entry,lista);
					
				}else {//con este else copiamos los ficheros
					System.out.println("Copiamos archivo "+entry);
					lista.add(entry.toAbsolutePath());
				}
		
			

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Total de archivos en Documents "+lista.size());
		
	
		

	}
	
	
	
	}
	
	


	
	
	

	
	
	


