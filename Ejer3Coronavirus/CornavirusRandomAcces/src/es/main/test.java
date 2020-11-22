package es.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {

		// try {
		/*
		 * BufferedReader br = new BufferedReader(new FileReader("./leerlineas.txt"));
		 * 
		 * String texto ="";//le metemos aqui este br.readline para que luego al
		 * escribir no me haga espacio //al principio String cadena;
		 * 
		 * 
		 * while ((cadena = br.readLine()) != null) { int contador=0;
		 * 
		 * Pattern pat = Pattern.compile("[texto]{5}+"); Matcher mat =
		 * pat.matcher(cadena); if (mat.find()) { System.out.println("SI");
		 * texto=cadena; } else { System.out.println("NO"); } }
		 * System.out.println(texto);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		
		
		
		/*Scanner sc=new Scanner(System.in);
        RandomAccessFile archivo;
		try {
			archivo = new RandomAccessFile("./prueba.cenec","rw");
		
        System.out.println("¿Quieres hacer lectura (l) o escritura (e)");
        switch(sc.nextLine().toLowerCase().charAt(0)){
            case 'l': //caso de lectura
                System.out.println("Tamaño del archivo: "+archivo.length());
                System.out.println("Nº registros: "+archivo.length()/38); //38 bytes que tiene cada registro de nombre+apellido+nota1+nota2
                
                System.out.println("Dime el nº de registro a leer");
                long nRegLec=Long.parseLong(sc.nextLine());
                
                //Multiplico por 38 bytes de cada registro
                nRegLec*=38;
                
                //poner el puntero en la posición indicada
                archivo.seek(nRegLec);
              //Leo en el array nombre los 15 bytes de la primera cadena de texto
                byte[] nombre=new byte[15];
                archivo.read(nombre);
                System.out.println(new String(nombre));
                byte[] apellidos=new byte[15];
                archivo.read(apellidos);
                System.out.println(new String(apellidos));
                System.out.println(archivo.readFloat());
                System.out.println(archivo.readFloat());
                
                break;
            case 'e':  //caso de escritura
                //Leo el nº de registro a escribir
                System.out.println("Dime el nº de registro a escribir");
                long nReg=Long.parseLong(sc.nextLine());
                //Multiplico ese número por los 38 bytes que sé que tiene cada registro
                nReg*=38;
                
                //Ahora pongo el puntero en la posición del inicio del "hueco" de 38 bytes
                //marcado por nReg
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

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		Scanner sc=new Scanner(System.in);	
		
	 System.out.println("Dime el nº de registro a escribir");
        
         //Multiplico ese número por los 38 bytes que sé que tiene cada registro
         
		
		try {
			RandomAccessFile archivo = new RandomAccessFile("./prueba.data","rw");
			long contaPuntero=0;
			for (int i = 0; i < 5; i++) {
				archivo.seek(contaPuntero);
				archivo.writeInt(i);
				contaPuntero=archivo.length();
			}
			contaPuntero=0;
			for (int i = 0; i < 5; i++) {
				archivo.seek(contaPuntero);
				System.out.println(archivo.readInt());
				contaPuntero+=4;
				
			}
			System.out.println(archivo.length());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void leerEnteros() {
		
	}



}
