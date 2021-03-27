package es.nico.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			RandomAccessFile archivo = new RandomAccessFile("./prueba.cenec", "rw");

			System.out.println("¿Quires hacer lectura (l) o escritura (e)");
			switch (sc.nextLine().toLowerCase().charAt(0)) {
			case 'l'://Caso lectura
				System.out.println("Tamaño del archivo: "+archivo.length());
				System.out.println(archivo.readUTF());
				System.out.println(archivo.readUTF());
			/*	System.out.println(archivo.readFloat());
				System.out.println(archivo.readFloat());
				System.out.println(archivo.readUTF());
				System.out.println(archivo.readUTF());
				System.out.println(archivo.readFloat());
				System.out.println(archivo.readFloat());*/ 
				
				break;
				
			case 'e'://Caso de escritura
			
				archivo.writeUTF(normalizarA15("Miguel"));
				archivo.writeUTF(normalizarA15("Fernandez"));
			/*	archivo.writeFloat(8.25f);
				archivo.writeFloat(7.75f);
				archivo.writeUTF(normalizarA15("Ataulfo"));
				archivo.writeUTF(normalizarA15("Bocachancla"));
				archivo.writeFloat(2.25f);
				archivo.writeFloat(6.44f);
				*/
				break;

			default:
				System.out.println("Debiste escoger lectura o escritura");
				break;
			}
			//Es un flujo así que habrá que cerrarlo
			archivo.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Funcion que recibido un String orig, lo trunca o amplica con espacios hasta que tenga un tamaoo exatamente 15 letras
	 * 
	 * @param orig String original
	 * @return
	 */
	public static String normalizarA15(String orig) {
		//vamos a forzar el texto que ponga siempre sea 15
	if (orig.length()>15) {
		String ret=" ";
		byte[]
		for (int i = 0; i <15 ; i++) {
			ret+=byte[i];
		}
	}
	
	if (orig.length()<15) {
		for (int i = orig.length(); i <=15 ; i++) {
			orig+=" ";
			
		}
	}
	
	return orig;	
	}

}
