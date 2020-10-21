/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nicoc
 */
public class MainFlujoMaestro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        BufferedReader br=null;
        try {
            
            //Ejemplo de leer una linea de un txt mediante FileReader
            FileSystem fs=FileSystems.getDefault();
            Path actual=fs.getPath("prueba.txt");
            br = new BufferedReader(
                    new FileReader(actual.toString()));//creamos un tipo FileReader porque vamos a leer caracteres
            //y lo metemos en un buffer sirve para poder leer texto de un flujo de caracteres que es FileReader
            
            System.out.println("leído con Stream de texto: "+br.readLine());
            
            //leer entero el texto
            String cadena;
            while((cadena = br.readLine())!=null){
                System.out.println(br.readLine());
            }
            br.close();
            

            
            //Leer archivo de texto correctamente con UNA CLASE DE FLUJOS BINARIOS
            //FileInputStream lo convertimos a flujo de texto via InputStreamReader.
            //BufferedReader solo es un auxiliar para hacer mas facil la lectura que lo que hace es leer el flujo de caracteres
            
            
            System.out.print("leído con Stream binario convertido a texto:");
            FileInputStream ifs2=new FileInputStream(actual.toString());//clase para hacer flujos de binario
            InputStreamReader isr=new InputStreamReader(ifs2);//Clase para hacer la conversion del flujo a texto
            BufferedReader br2=new BufferedReader(isr);//clase auxiliar lo que hace es leer el flujo de texto del input
            System.out.println(br2.readLine());

            
            //Este seria el ejemplo de como leer y escribir un archivo binario correctamente, con una clase
            //especifica para leer imágenes, que trabaja en binario, como BufferedImage
            
            BufferedImage bi=ImageIO.read(new File("./imagen.jpg"));//creamos el flujo para leer imagenes
            BufferedImage resultado=new BufferedImage(bi.getWidth(),bi.getHeight(),//y creamos un resultado
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < bi.getWidth(); i++) {
                for (int j = 0; j < bi.getHeight(); j++) {
                    resultado.setRGB(i,j,bi.getRGB(i, j));
                }
            }
            ImageIO.write(resultado, "JPEG", new File("./resultado.jpg"));//lo escribimos

            //Como leer y escribir un archivo binario correctamente con clases para leer flujos de datos binarios,
            //como FileInputStream y FileOutputStream SIN LA CALSE DE BUFFEREDREADER NI READERIMAGE
            
                FileInputStream isrimg=new FileInputStream("./imagen.jpg");//Creamos la clase binaria
            FileOutputStream osw=new FileOutputStream("./imagenStreamBinario.jpg");
            int pixel=isrimg.read();
            while(pixel!=-1){ //-1 marca el final del buffer
                osw.write(pixel);
                pixel=isrimg.read();
            }
            osw.flush();
            osw.close();
            isrimg.close();

            
            
                
            } catch (IOException ex) {
                Logger.getLogger(MainFlujoMaestro.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
}
