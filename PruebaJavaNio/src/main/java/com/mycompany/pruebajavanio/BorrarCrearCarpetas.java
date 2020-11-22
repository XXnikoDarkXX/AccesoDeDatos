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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicoc
 */
public class BorrarCrearCarpetas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    
       
        FileSystem fs=FileSystems.getDefault();
        Path actual=fs.getPath("imagenes/jpg/patata");
        
        System.out.println(actual.toAbsolutePath());
        try{
            if(!Files.exists(actual)){
                Files.createDirectories(actual);
                System.out.println(actual.toAbsolutePath());
                
                System.out.println("Carpeta patata y su contenido creados.");
                Files.createFile(fs.getPath(actual.toString(),"prueba 1.txt"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 2.exe"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 3.doc"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 4.bat"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 5.sql"));
                Files.createDirectories(fs.getPath(actual.toString(), "carpeta hija"));
               Files.createFile(fs.getPath(actual.toString(), "carpeta hija/archivo nieto.txt"));
            }else{
                System.out.println("Información de la carpeta "+actual);
                System.out.println("Última modificación: "+Files.getLastModifiedTime(actual));
                System.out.println("Usuario: "+Files.getOwner(actual));
                System.out.println("Tamaño: "+(Files.size(actual)/(1024*1024))+" MB");
                System.out.println(borrarCarpeta(actual));
            }
                
        }catch (java.nio.file.DirectoryNotEmptyException e){
            System.out.println("No puedo borrar: La carpeta no está vacía");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }   
    
    }
    /**
     * Mediante esta funcion recursiva con el DirectoryStream vamos en bucle recorriendo las carpetas que haya
     * y dentro de un if mirando si las carpetas hijas tienen archivo pues la borramos llamando a la misma llamada
     * @param carpeta carpeta a la que queremos borrar
     * @return un String concatenando todas las carpetas que hayamos borrado
     * @throws IOException 
     */
     public static String borrarCarpeta(Path carpeta) throws IOException{
         String res="";
        DirectoryStream<Path> hijosCarpeta=Files.newDirectoryStream(carpeta); 
            for (Path entry: hijosCarpeta) {
                if (Files.isDirectory(entry)) {
                    res+=borrarCarpeta(entry);
                }else{
                    res+="Borrando "+entry+"\n";
                Files.delete(entry);
                }
                
            }
            res+="Borrando "+carpeta+"\n";
            Files.delete(carpeta);
            return res;
        }
    
    
   
    
}