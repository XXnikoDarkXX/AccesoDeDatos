/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nico.main;

import es.nico.serializable.Archivo;
import es.nico.serializable.Archivos;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/**
 *
 * @author nicoc
 */
public class MainSerial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       
        FileSystem fs = FileSystems.getDefault();
        Path carpeta = fs.getPath("./Árbol");
         Archivos serializados = new Archivos();//objeto archivos
        recorrerArbol(carpeta,serializados);
        //Metemos nuestro archivo serial
        serializados.GuardarSerial("./otros.cenec");
    }

    
    /**
     * Funcion que recorre todo el subdirectorio de Arbol recursivamente y en funcion de que tipo sea el archivo recorrido
     * eje. si es jpg lo reescribira con un tono rojizo y si es un jpg y encuentra la palabra cenec le cambia a centrollolandia
     * Y si el archivo no es ningun tipo de los anteriores lo mete en un objeto de tipo Archivos y lo serializza
     * @param arbol Directorio que vamos a recorrer  
     * @param serial Objeto de tipo Archivos donde metemos los archivos que no sean jpg ni txt
     */
    public static void recorrerArbol(Path arbol, Archivos serial) {

        
        DirectoryStream<Path> hijosCarpeta;// Creamos el directoryStream
        try {

            hijosCarpeta = Files.newDirectoryStream(arbol);// hacemos esto para crear diferentes Path en los
            // subdirectorios de carpeta

            // y en el segundo borrar los ficheros
            for (Path entry : hijosCarpeta) {
                //Si entra en un directorio volvemos a llamar a la funcion para ver lo que tiene
                //caso recursivo
                if (Files.isDirectory(entry)) {
                    System.out.println("entrando en" + entry.toString());
                    recorrerArbol(entry, serial);
                    //Si encuentra un archivo
                    //caso base
                } else {
                    System.out.println("Entrando en: " + entry);

                    Path rutaArchivo = entry.getFileName();
                    System.out.println("El hijo es: " + rutaArchivo);
                    
                    File archivo = new File(rutaArchivo.toString());
                    // Mediante este if comprobamos en el nombre que extension tiene
                    if (archivo.getName().endsWith("txt")) {
                        System.out.println("Es un txt y es " + rutaArchivo);

                        EscribirCenec(entry.toString());

                    } else if (archivo.getName().endsWith("jpg")) {
                        System.out.println("Es un jpg y es " + rutaArchivo +"le hacemos rojo");
                        imagenRojizo(entry);
                    } else {
                        System.out.println("Te añado a la lista de objetosArchivos");
                        Archivo arc = new Archivo(entry.toString(),(int)archivo.length());
                        
                       serial.setListaArchivo(arc);//Objetos Archvios
                    }

                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /**
     * Metodo el cual leemos un arhivo de txt y comprobamos si tiene la palabra cenec con expresiones regulares, en caso de
     * que si lo contenga se lo cambiamos a centollolandia
     * @param ruta ruta del archivo
     */
    public static void EscribirCenec(String ruta) {

        BufferedReader br = null;
        try {
            
            br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF8"));
            // creamos un tipo InputStreamReader para poder leer en UTF8
            // y lo metemos en un buffer sirve para poder leer texto de un flujo de
            // caracteres asi lo convertmimos
            String texto = br.readLine();//le metemos aqui este br.readline para que luego al escribir no me haga espacio
            //al principio
            String cadena;
            while ((cadena = br.readLine()) != null) {
              
                texto += "\n" + cadena;
            }
            br.close();
            //vamos a usar StringBuffer para poder luego cambiar la palabra cenec si lo tiene
            StringBuffer buscar = new StringBuffer("cenec");
            String patronBuscado = Pattern.quote(buscar.toString());//Usamos un Pattern que convierte el cenec en expresion
            //regular
            //Usamos otro StringBuffer con la palabra que vamos intercambiar
            StringBuffer reemplazar = new StringBuffer("centollolandia");
            String patronReemplazo = Matcher.quoteReplacement(reemplazar.toString());
            //Usamos otro Patter 
            //mediante esta funcion buscamos la expresion cenec con case_insentivie
            Pattern pat = Pattern.compile(patronBuscado, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            //con el math que es un encanjador realizamos operaciones que es cambiar la palabre de la variable texto
            Matcher mat = pat.matcher(texto);
            //en este if ya estamos encontrando la palabra cenec en caso de que lo encontremos.
            if (mat.find()) {
                //usamos mat.replaceAll para reemplazar la palabra y le metemos patronReemplazo que es centrollolandia
                texto = mat.replaceAll(patronReemplazo);
                System.out.println("Resultado: " + texto);
                //y lo volvemos a escribir
                FileWriter fw = new FileWriter(ruta.toString());
                fw.write(texto);
                fw.flush();
                fw.close();

            } else {
                System.out.println("No se encontro en el texto la palabra cenec");
                System.out.println(texto);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainSerial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainSerial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainSerial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    /**
     * Metodo para cambiar tono rojizo a la foto
     * @param ruta de la foto
     */
    public static void imagenRojizo(Path ruta){
          try {
            //Cojemos la ruta del archivo
            File clase=new File(ruta.toString());
            
            //lo metemos en un bufferedImage para poder leer las imagenes
            BufferedImage imagen=  ImageIO.read(clase);
            //Usamos un doble for uno para el ancho de la imagen y otro para el alto de la imagen
            for (int i = 0; i < imagen.getWidth(); i++) {
                for (int j = 0; j < imagen.getHeight(); j++) {
                   
                        //cogemos el color de la imagen cogiendo pixel a pixel
                        int color=imagen.getRGB(i, j);
                        //Estableces el color de la imagen aun tono rojizo
                        imagen.setRGB(i, j,color&Integer.parseInt("101110000000000000010010",2));
                        
                }
            }
            
            //La escribimos
            ImageIO.write(imagen, "JPEG", new File(ruta.toString()));
        } catch (IOException ex) {
          ex.printStackTrace();
          
        }
    }

}
