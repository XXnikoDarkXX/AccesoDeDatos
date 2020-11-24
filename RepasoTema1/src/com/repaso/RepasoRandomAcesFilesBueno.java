package com.repaso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class RepasoRandomAcesFilesBueno {

	public static void main(String[] args) {
		RandomAccessFile archivo;
		FileSystem fs=FileSystems.getDefault();
		//Esto es por si existe el fichero prueba que se borre y se vuelva a crear
		if (Files.exists(fs.getPath("./prueba.data"))) {
			try {
				Files.delete(fs.getPath("./prueba.data"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			//Si queremos escribir enteros
			 archivo = new RandomAccessFile("./prueba.data","rw");
			 long contador=0;
			//Ejemplo de como escribir un entero
			for (int i = 0; i < 5; i++) {
				archivo.seek(contador);
				contador+=4;
				archivo.writeInt(i);
				
				
			}
			archivo.close();
			//leer enteros
			 archivo = new RandomAccessFile("./prueba.data","rw");
			//ejemplo de como leer los ficheros de tipo Int
			long contaPuntero=0;
			for (int i = 0; i <archivo.length()/4; i++) {
				archivo.seek(contaPuntero);
				System.out.println(archivo.readInt());
				contaPuntero+=4;
			}
			archivo.close();
			
			System.out.println("Lectura y escritura en Byte");
			
			 archivo = new RandomAccessFile("./prueba.data","rw");
			
		    long  contaByte=archivo.length();//para coger el contador a lo ultimo que escribimos
		    long lecturaByte=archivo.length();//para coger el contador de lectura a lo ultimo que escribimos
			for (int i = 5; i < 10; i++) {
				archivo.seek(contaByte);
				archivo.writeByte(i);
				contaByte++;
			}
			archivo.close();
			
			
			 archivo = new RandomAccessFile("./prueba.data","rw");
				
				
				for (int i =0 ; i < 5; i++) {
					archivo.seek(lecturaByte);
					System.out.println(archivo.readByte());
					lecturaByte++;
				}
				archivo.close();
				System.out.println("Lectura y escritura de String");
				String texto="nicolas";
				byte[]array=texto.getBytes();//metemos nuestro string en una array de bytes
				
				
				 archivo = new RandomAccessFile("./prueba.data","rw");
				 
				 long contadorTexto=archivo.length();
				 archivo.seek(contadorTexto);
				 archivo.write(array);//escribimos el array de bytes
				 
				 
				 archivo.close();
				 
				 archivo = new RandomAccessFile("./prueba.data","rw");
				 archivo.seek(contadorTexto);
				 byte[]arrayLeido=new byte[array.length];//es importante de que nuestro nuevo array tenga el mismo
				 //tamaño que el String que queremos leer
				archivo.read(arrayLeido);//hacemos la lectura esto lo que hace es cargarlo
				System.out.println(new String(arrayLeido));//lo metemos a un nuevo String lo leido
				 archivo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
