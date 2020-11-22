/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebajavanio;

/**
 *
 * @author nicoc
 */
public class Datos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        double [] unmonton=new double[43000000];
        System.out.println("Memoria libre: "+(rt.freeMemory()/1024)+" kb");
    }

}
