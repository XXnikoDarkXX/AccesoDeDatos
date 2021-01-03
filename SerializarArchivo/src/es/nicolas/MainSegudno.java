package es.nicolas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainSegudno {

	public static void main(String[] args) {

		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(".\\Árbol\\Peñascos\\Pequeños\\a veces se me olvida.txt"), "UTF8"));
			BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(".\\Árbol\\Peñascos\\Pequeños\\a veces se me olvida.txt"), "UTF8"));
			// creamos un tipo FileReader porque vamos a leer caracteres
			// y lo metemos en un buffer sirve para poder leer texto de un flujo de
			// caracteres que es FileReader

			String texto = "";

			// leer entero el texto
			
			String cadena;
			while ((cadena = br.readLine()) != null) {
			//	System.out.println(cadena);
				texto+="\n"+cadena;
			}
			br.close();
			br2.close();
			StringBuffer buscar = new StringBuffer("cenec");
			String patronBuscado = Pattern.quote(buscar.toString());

			StringBuffer reemplazar = new StringBuffer("centrollolandia");
			String patronReemplazo = Matcher.quoteReplacement(reemplazar.toString());

			Pattern pat = Pattern.compile(patronBuscado, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
			Matcher mat = pat.matcher(texto);

			if (mat.find()) {
				texto = mat.replaceAll(patronReemplazo);
				System.out.println("Resultado: " + texto);
			/*	FileWriter fw=new FileWriter(".\\Árbol\\Peñascos\\Pequeños\\a veces se me olvida.txt");
				fw.write(texto);
				fw.flush();
				fw.close();*/
				
			} else {
				System.out.println("No se encontro el texto");
				System.out.println(texto);
			}

	
			
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
