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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

/**
 *
 * @author nicoc
 */
public class MainFlujo2 {

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
            System.out.println("leído con Stream de texto: "+br.readLine());
             br.close();
            System.out.print("leído con Stream binario:");
            FileInputStream ifs=new FileInputStream(actual.toString());
             FileInputStream ifs2=new FileInputStream(actual.toString());
            InputStreamReader isr=new InputStreamReader(ifs2);
            BufferedReader br2=new BufferedReader(isr);
            System.out.println(br2.readLine());
            ifs2.close();
            isr.close();
            br2.close();
            
            //Imagen leida como es debido: con un BufferedImage, que tiene por debajo un flujo de datos
            BufferedImage bi=ImageIO.read(new File("./imagen.jpg"));
            BufferedImage resultado=new BufferedImage(bi.getWidth(),bi.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < bi.getWidth(); i++) {
                for (int j = 0; j < bi.getHeight(); j++) {
                    resultado.setRGB(i,j,bi.getRGB(i, j));
                }
            }
            ImageIO.write(resultado, "JPEG", new File("./resultado.jpg"));
            
        
            //Imagen leída y escrita con un flujo de datos estándar
            FileInputStream isrimg=new FileInputStream("./imagen.jpg");
            FileOutputStream osw=new FileOutputStream("./imagenStreamBinario.jpg");
            int pixel=isrimg.read();
            while(pixel!=-1){ //-1 marca el final del buffer
                osw.write(pixel);
                pixel=isrimg.read();
            }
            osw.flush();
            osw.close();
            isrimg.close();
            
            
            //leer la imagen con un flujo de datos de texto, y a escribirla como binaria
            FileReader frimg=new FileReader("./imagen.jpg");
            FileOutputStream osw2=new FileOutputStream("./imagenStreamBinarioLecturaTexto.jpg");
            int pixel2=frimg.read();
            while(pixel2!=-1){ //-1 marca el final del buffer
                osw2.write(pixel2);
                pixel2=frimg.read();
            }
            osw2.flush();
            osw2.close();
            frimg.close();
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
             
            }
        }
    }
    
}
