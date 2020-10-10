package com.main;

public class Datos {

	public static void main(String[] args) {
		  Runtime rt = Runtime.getRuntime();
	        double [] unmonton=new double[43000000];
	        System.out.println("Memoria Total: "+(rt.totalMemory()/1024)+" kb");
	        System.out.println("Memoria libre: "+(rt.freeMemory()/1024)+" kb");

	}

}
