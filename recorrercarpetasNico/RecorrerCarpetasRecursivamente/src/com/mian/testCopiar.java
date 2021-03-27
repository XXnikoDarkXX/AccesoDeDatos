package com.mian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class testCopiar {

	public static void main(String[] args) {
	
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros

		Path original = fs.getPath("C:\\Users\\nicoc\\Documents\\EclipseProyectos\\RecorrerCarpetasRecursivamente\\ser");
			Path copia = fs.getPath("C:\\Users\\nicoc\\Documents\\EclipseProyectos\\RecorrerCarpetasRecursivamente\\resultado");
			/*System.out.println(original.toAbsolutePath());
		System.out.println(copia.toAbsolutePath());
		System.out.println(original.toString());
		System.out.println(copia.toString());

		File origen = new File(original.toString());
		File destino = new File(copia.toString());
		try {
			InputStream in = new FileInputStream(origen);

			OutputStream out = new FileOutputStream(destino);
//realizamos la lectura y escritura mientras existan datos en el stream de lectura
			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0) {
			  out.write(buf, 0, len);
			}	
			
			
			
			in.close();
			out.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		File origen=new File(original.toString());
		
		File destino=new File (copia.toString());
		
		try {
			copiarPegarArchivo(origen,destino);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void copiarPegarArchivo(File src, File dst) throws IOException{
		try {
	 
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dst+src.getName());
	 
			byte[] b = new byte[1024];
			int l;
			while((l = in.read(b)) >0){
				out.write(b, 0, l);
			}
	 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 
	 
	 
	 //pasamos los path a un linkedList de File

	/*
	 LinkedHashMap  <File,Integer>ficherosTotales =new LinkedHashMap ();
	 File archivosArray[]=new File[listaArchivos.size()];
	 //mediante este for metemos la lista de Path a un HashMap de <File,Integer>
	for (int i = 0; i < listaArchivos.size(); i++) {
		File archivo=new File (listaArchivos.get(i).toString());
		int ocupaFichero=(int) (archivo.length()/1024);
		Integer tamaño=ocupaFichero;
		
		ficherosTotales.put(archivo,tamaño);
		archivosArray[i]=archivo;//lo pasamos a un array
	}*/
	/*
		//vamos a crear un array de tipo File con tamaño 10 y sacar los archivos mas pequeños del hashMap

	//El array archivosArray estan todos los Files 
	//y queremos sacar los 10 files mas pequeños e incluirselos en el otro array menosArchivo
	int menor=9999999;
	File menorArchivo;
	int mayor=-3;
	File mayorArchivo;
	File menosArchivo[]=new File[10];

	for (int i = 0; i < menosArchivo.length; i++) {
		menosArchivo[i]=archivosArray[0];
	}
	for (int i = 0; i <archivosArray.length; i++) {
		int ocupaFichero=(int) (archivosArray[i].length()/1024);
		if (ocupaFichero<=mayor) {
			for (int j = 0; j < menosArchivo.length; j++) {
				if (ocupaFichero<(menosArchivo[j].length()/1024)) {
					menosArchivo[j]=archivosArray[i];
					menor=ocupaFichero;
					menorArchivo=archivosArray[i];
					break;
				}
			}
			
			}else {
				mayorArchivo=archivosArray[i];
				mayor=ocupaFichero;
			
		}
		
		
	}

	System.out.println("Los 10 ficheros con menor tamaño son\n---------------");

	for (int j = 0; j < menosArchivo.length; j++) {
		System.out.println("Nombre: "+menosArchivo[j].toString()+" tamaño"+(menosArchivo[j].length()/1024));
	}


	*/
	

}
