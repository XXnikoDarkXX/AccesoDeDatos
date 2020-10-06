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
     * @param args the command line arguments
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
        
        System.out.println("Existe actual? "+ Files.exists(actual));
        try {
            if (!Files.exists(actual)) {
                Files.createDirectories(actual);//crea unos directorios sin importar si estan creados o no las carpetas
            //puede suponer un peligro pues podemos crear carpetas sin saberlo
            //Files.createDirectory(actual);//Con este método si tenemos creadas la carpetas 
                System.out.println("Carpeta actual creada");
                Files.createFile(fs.getPath(actual.toString(),"prueba.txt"));//creamos un txt
            }else{
                System.out.println("Informacion de la carpeta "+actual);
                System.out.println("Ultima modificacion: "+Files.getLastModifiedTime(actual));
                System.out.println("Usuario: "+Files.getOwner(actual));
                System.out.println("Tamaño: "+(Files.size(actual)*(1024))+" MB");
                 Path carpetaImagenes=fs.getPath("Imágenes"+fs.getSeparator()+"jpg/test");
                //System.out.println(borrarCarpeta(carpetaImagenes));
           // Files.delete(carpetaImagenes);//borramos la carpeta test pero solo si no hay nada dentro
                System.out.println("Carpeta test borrado");
                //Para poder borrar la carpeta test podemos hacerlo mediante un for 
                
              
               Files.delete(carpetaImagenes);//borramos la carpeta test pero solo si no hay nada dentro
            }
            
            
            
           
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       }
    public static void borrarCarpeta(Path carpeta){
        String res="";
        try {
            
        DirectoryStream<Path> hijosActual=Files.newDirectoryStream(carpeta);
                for (Path entry:carpeta){
            res+="Borrando "+entry+"\n";
               
                Files.delete(entry);
           
                }

                 } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
