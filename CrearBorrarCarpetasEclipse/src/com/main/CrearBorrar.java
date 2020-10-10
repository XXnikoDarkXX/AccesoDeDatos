package com.main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CrearBorrar {

	public static void main(String[] args) {
		
		FileSystem fs=FileSystems.getDefault();
        Path actual=fs.getPath("imagenes/jpg/patata");//creamos una ruta hasta patata
        
        System.out.println(actual.toAbsolutePath());
        try{
            if(!Files.exists(actual)){//si patata NO existe
                Files.createDirectories(actual);//creamos patata
                System.out.println(actual.toAbsolutePath());
                
                System.out.println("Carpeta patata y su contenido creados.");
                //vamos creando cosillas como documentos de texto, exe, sql word etc...
                Files.createFile(fs.getPath(actual.toString(),"prueba 1.txt"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 2.exe"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 3.doc"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 4.bat"));
                Files.createFile(fs.getPath(actual.toString(),"prueba 5.sql"));
                Files.createDirectories(fs.getPath(actual.toString(), "carpeta hija"));//creamos una carpeta
               Files.createFile(fs.getPath(actual.toString(), "carpeta hija/archivo nieto.txt"));//le metemos un txt a la carpeta
            }else{
                System.out.println("Información de la carpeta "+actual);//nos da la informacion de la carpeta
                System.out.println("Última modificación: "+Files.getLastModifiedTime(actual));
                System.out.println("Usuario: "+Files.getOwner(actual));
                System.out.println("Tamaño: "+(Files.size(actual)/(1024*1024))+" MB");
                System.out.println(borrarCarpeta(actual));//hacemos borrar carpeta
            }
                
        }catch (java.nio.file.DirectoryNotEmptyException e){
            System.out.println("No puedo borrar: La carpeta no está vacía");
        } catch (IOException ex) {
           ex.printStackTrace();
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