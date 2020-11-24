package com.repaso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RepasoRandomAcessFiles {

	public static void main(String[] args) {

		// Si quieres mas sobre RandomAcessFiles mirar proyecto LectorCoronavirus y
		// CoronavirusRandomAcess

		Scanner sc = new Scanner(System.in);
		RandomAccessFile archivo;
		try {
			archivo = new RandomAccessFile("./prueba.cenec", "rw");

			System.out.println("¿Quieres hacer lectura (l) o escritura (e)");

			switch (sc.nextLine().toLowerCase().charAt(0)) {
			case 'l': // caso de lectura

				System.out.println("Tamaño del archivo: " + archivo.length());

				System.out.println("Nº registros: " + archivo.length() / 38); // 38 bytes que tiene cada registro de
																				// nombre+apellido+nota1+nota2

				System.out.println("Dime el nº de registro a leer");
				long nRegLec = Long.parseLong(sc.nextLine());

				// Multiplico por 38 bytes de cada registro
				nRegLec *= 38;

				// poner el puntero en la posición indicada
				archivo.seek(nRegLec);

				// Leo en el array nombre los 15 bytes de la primera cadena de texto
				byte[] nombre = new byte[15];
				archivo.read(nombre);
				System.out.println(new String(nombre));
				byte[] apellidos = new byte[15];
				archivo.read(apellidos);
				System.out.println(new String(apellidos));
				System.out.println(archivo.readFloat());
				System.out.println(archivo.readFloat());

				break;
			case 'e': // caso de escritura
				// Leo el nº de registro a escribir
				System.out.println("Dime el nº de registro a escribir");
				long nReg = Long.parseLong(sc.nextLine());
				// Multiplico ese número por los 38 bytes que sé que tiene cada registro
				nReg *= 38;

				// Ahora pongo el puntero en la posición del inicio del "hueco" de 38 bytes
				// marcado por nReg
				archivo.seek(nReg);

				System.out.println("Dime nombre a escribir");
				archivo.writeBytes(normalizarA15(sc.nextLine()));
				System.out.println("Dime apellidos a escribir");
				archivo.writeBytes(normalizarA15(sc.nextLine()));
				System.out.println("Dime nota 1");
				archivo.writeFloat(Float.parseFloat(sc.nextLine()));
				System.out.println("Dime nota 2");
				archivo.writeFloat(Float.parseFloat(sc.nextLine()));
				break;
			default:
				System.out.println("Debiste escoger lectura o escritura");
				break;

			}

			archivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Función que recibido un String orig, lo trunca o amplía con espacios, hasta
	 * que tenga un tamaño de exactamente 15 bytes (NO CARACTERES: BYTES). SIEMPRE
	 * QUE NO HAYA ACENTOS NI CARACTERES RAROS.
	 * 
	 * @param orig String original
	 * @return String normalizado a tamaño 15 BYTES
	 * @bug BUG CONOCIDO: NO SE PUEDEN PONER MÁS DE 15 LETRAS NI ACENTOS EN ORIG.
	 */
	public static String normalizarA15(String orig) {
		// Si nuestro string tiene mas de 15 caracteres
		if (orig.getBytes().length > 15) {
			System.out.println("longitud: " + orig.getBytes().length);
			byte[] bytes = orig.getBytes();
			String ret = "";
			for (int i = 0; i < 15; i++) {
				ret += bytes[i];
			}
			return ret;
		}
		// Su nuestro texto tiene menos de 15 caracteres
		if (orig.getBytes().length < 15) {
			for (int i = orig.getBytes().length; i < 15; i++) {
				orig += " ";
				System.out.println("longitud: " + orig.getBytes().length);

			}
		}
		return orig;
	}

}
