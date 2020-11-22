package com.repaso;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class FlujosTexto {

	public static void main(String[] args) {

		try {

							// ESCRIBIR
			// Normalmente para escribir un txt usaremos
			// Usaremos BufferedWriter para escribir caracteres en un flujo, como puede ser
			// un FileWriter.
			BufferedWriter bw = new BufferedWriter(new FileWriter("ejemplo.txt"));
			// Si no queremos que nos machaque las cosas podemos usar un True al lado de la
			// ruta

			BufferedWriter escritor = new BufferedWriter(new FileWriter("ejemplo.txt", true));

			escritor.write("Esto es una prueba linea 1\n");
			escritor.write("Esto es una prueba linea 2\n");
			escritor.write("Esto es una prueba linea con tílde3 Y Ñ\n");
			escritor.flush();
			escritor.close();
			// es importante recordar que empezara a escribir en la linea donde hayas
			// escribido lo ultimo es por ello que hay que usar \n
			
						// LEER
			// Normalmente para leer usaremos el siguiente codigo:
			BufferedReader br = new BufferedReader(new FileReader("ejemplo.txt"));// creamos un BufferedReader que le
			// flujos de byte y le metemos a un fileReader que lee byte a byte

			String cadena = "";
			String texto = "";
			// usando el siguiente while cargamos a cadena una linea mediante el br.readline
			while ((cadena = br.readLine()) != null) {

				texto += cadena + "\n";
			}
			br.close();
			System.out.println(texto);

			

		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	 /**
	 * Escribir imagenes, tenemos varios proyectos entre ellos T1FlujoDatos
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
