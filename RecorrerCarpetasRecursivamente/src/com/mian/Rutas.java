package com.mian;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Rutas {

	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		FileSystem fs = FileSystems.getDefault();// Creamos un File System para poder manejar ficheros
		// Preguntar si la ruta que pide por teclado debe de ser relativa o absoluta
		Path rutaCarpeta;// Ruta imaginaria
		do {

			System.out.println("Escribe una ruta de una carpeta existente en el pc");
			String carpeta = sc.nextLine();
			rutaCarpeta = fs.getPath(carpeta);
		} while (!Files.isDirectory(rutaCarpeta));// comprobamos si es real la ruta
		System.out.println(rutaCarpeta.toString());
		Path actual=fs.getPath("C:\\Users\\nicoc\\Documents\\GitHub");
		actual=rutaCarpeta.relativize(actual.toAbsolutePath());
		
		System.out.println(actual);
	}

}
