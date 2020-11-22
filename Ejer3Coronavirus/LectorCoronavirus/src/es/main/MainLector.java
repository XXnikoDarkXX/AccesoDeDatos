package es.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class MainLector {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce codigo de provincia que deseas buscar");

		String codigoProvincia = sc.nextLine();
		RandomAccessFile archivo;
		try {
			archivo = new RandomAccessFile("../" + codigoProvincia + ".data", "rw");

			long contaPuntero = 0;

			// dividimos entre 4 el archivo.leng para que nos nos lo haga 4 veces mas
			for (int i = 0; i < archivo.length() / 4; i++) {
				archivo.seek(contaPuntero);
				System.out.println(archivo.readInt());
				contaPuntero += 4;
			}
			int diaInicial;

			int diaFinal;

			System.out.println("En total hay " + archivo.length() / 4 + ": DIAS");
			
			do {
				System.out.println("Dime un dia inicial recuerda que puedes hasta " + archivo.length() / 4);
				diaInicial = Integer.parseInt(sc.nextLine());
				System.out.println("Dime un diafinal para la estadistica");
				diaFinal = Integer.parseInt(sc.nextLine());
			} while (diaInicial > diaFinal || diaFinal > archivo.length() / 4);

			//leemos los dias buscados
			buscadorProvincia(diaInicial,diaFinal,codigoProvincia);
			archivo.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Funcion para buscar los casos minimos y casos maximo de coronavirus de un intervalos de dias
	 * @param diaInicio dia de inicio
	 * @param diaFinal dia final
	 * @param codigoProvincia codigo de la provincia que vamos a buscare
	 */
	public static void buscadorProvincia(int diaInicio, int diaFinal, String codigoProvincia) {

		RandomAccessFile archivo;
		try {
			archivo = new RandomAccessFile("../" + codigoProvincia + ".data", "rw");

			long contaPuntero = diaInicio*4;

			// dividimos entre 4 el archivo.leng para que nos nos lo haga 4 veces mas
			int menor=0;
			int mayor=0;
			for (int i = diaInicio; i < diaFinal; i++) {
				
				archivo.seek(contaPuntero);
				
				int numero=archivo.readInt();
				System.out.println(numero);
				contaPuntero += 4;
				if (menor>numero) {
					menor=numero;
				}
				
				
				if (mayor<numero) {
					mayor=numero;
				}
				
			}
			
			System.out.println("El minimo es de casos es: "+menor+" el maximo de casos: "+mayor);
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
