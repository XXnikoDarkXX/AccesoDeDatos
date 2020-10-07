/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pruebajavanio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoc
 */
public class Main {

    /**
     * @Prueba para saber crear carpetas
     */
    public static void main(String[] args) {

        FileSystem fs = FileSystems.getDefault();//Creamos un File System para poder manejar ficheros
        System.out.println("abierto? " + fs.isOpen());//para saber si esta abierto el fs
        System.out.println("Solo lectura? " + fs.isReadOnly());//
        System.out.println("Separador: " + fs.getSeparator());//

        Path actual = fs.getPath("Imágenes"+fs.getSeparator()+"jpg/test");
        Path home = fs.getPath("C:\\Users\\nicoc\\");
        System.out.println("absoluta home: " + home.toAbsolutePath());

        System.out.println("Absoluta actual: " + actual.toAbsolutePath());
        System.out.println("relativa " +  home.toAbsolutePath().relativize(actual.toAbsolutePath()));//conseguimos la 
        //ruta relativa para ello debemos usar el home que es el inicio del usuario y usando .relaitive actual le quitamos
        //con el actual la parte del home C://User//nicoc y dejamos solo lo demás
        
        System.out.println("File name: "+actual.getFileName());
        Iterator it=actual.toAbsolutePath().iterator();
        System.out.println("Ruta actual despiezada :");
        while(it.hasNext()){
            System.out.println((Path)it.next());
        }
        
        try {
            //creamos el directorio totalmente
            Files.createDirectories(actual.toAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            
            
           
      
         
       }
  

}
