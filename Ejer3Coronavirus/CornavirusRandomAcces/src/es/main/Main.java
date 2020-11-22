package es.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FileSystem fs=FileSystems.getDefault();
		String codigoProvincia = "";
		System.out.println("Introduce codigo de provincia");

		codigoProvincia = sc.nextLine();
		try {
			BufferedReader br = new BufferedReader(new FileReader("./datos_provincias.csv"));

			String texto = "";
			
			String cadena;
			RandomAccessFile archivo ;
			int contaDias = 0;
			int contador = 0;
			while ((cadena = br.readLine()) != null) {
				//dentro de este if borraremos el archivo para que no nos concatene todo el rato lo que ya tenemos
				if (contador==0) {
					contador++;
					if (Files.exists(fs.getPath("../"+codigoProvincia+".data"))) {
						Files.delete(fs.getPath("../"+codigoProvincia+".data"));
					}
					
				}
				Pattern pat = Pattern.compile(codigoProvincia);// Creo el pattern que va a ser la palabra de la provincia
				Matcher mat = pat.matcher(cadena);// creo que el matcher para luego buscar la palabra
				// si encuentro la provincia a texto le concateno la cadena que es el
				// br.readline
				
				if (mat.find()) {
						
					texto += cadena;
					
					
					//Filtramos la pronvicia elegida ya que con el patter me coge todos los caracteres
					String provinciaEntrada=sacarNombreProvincia(cadena);
					
					if (provinciaEntrada.equalsIgnoreCase(codigoProvincia)) {
						
						System.out.println(cadena);
						SacarDatos(cadena, codigoProvincia);
						contaDias++;
						
					}
						
				} else {

				}
			}

		System.out.println("--------------------------------");
		//voy a probar la lectura
		archivo = new RandomAccessFile("../"+codigoProvincia+".data","rw");
		long contaPuntero=0;
		System.out.println("En total hay"+archivo.length());
		//dividimos entre 4 el archivo.leng para que nos nos lo haga 4 veces mas
		for (int i = 0; i < archivo.length()/4; i++) {
			archivo.seek(contaPuntero);
			System.out.println(archivo.readInt());
			contaPuntero+=4;
		}
		
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void SacarDatos(String cadena, String provincia) {
		
		String[] array = cadena.split(",");
		String datos = "";
		
		datos = array[2];
		int valor=Integer.parseInt(datos);
System.out.println(valor);
	/*	for (Map.Entry<Integer, Integer> entry : lista.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}*/
try {
		
		
			RandomAccessFile archivo = new RandomAccessFile("../"+provincia+".data","rw");
			//metemos el puntero
			long contaPuntero=0;
			contaPuntero=archivo.length();
			archivo.seek(contaPuntero);
			archivo.writeInt(valor);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Funcion que creamos para sacar el nombre de una linea
	 * @param linea todos los datos de la provincia
	 * @return solo el nombre de la provincia
	 */
	public static String sacarNombreProvincia(String linea) {
		
		String[] array = linea.split(",");
		String resultado = "";
		
	return	resultado = array[0];
	}
	
	
	
	

	
}
