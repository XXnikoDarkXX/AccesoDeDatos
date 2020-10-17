/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author nicoc
 */
public class MainFLujo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       BufferedReader br=null;
        try {
            
            
            FileSystem fs=FileSystems.getDefault();
            Path actual=fs.getPath("prueba.txt");
            br = new BufferedReader(
                    new FileReader(actual.toString()));
            
            System.out.println(actual.toAbsolutePath());
            Stream<String> st=br.lines();//lo llena
            Stream <String> st2=br.lines();
           
            System.out.println(st.toArray()[0]);//to array lo consume es como un buffer que va consumiendo  y sacando letra  a letra el texto
             
            System.out.println("tamaño: "+st2.toArray().length);
            
        
        
        } catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
             
            }
        }
    }
    
}
