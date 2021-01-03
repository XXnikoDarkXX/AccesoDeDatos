package es.nico.ejercicio4.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


import objetos.Coleccion;


public class MainEjercicio4 {

	public static void main(String[] args) {

		FileSystem fs = FileSystems.getDefault();
		Path carpeta = fs.getPath("./Árbol v2");

		HashMap<String, Coleccion> mapa = new HashMap<String, Coleccion>();

		recorrerArbol(carpeta, mapa);
		
		
		escribirXml(mapa);
		

	}
	/**
	 * Funcion recursiva donde filtramos y obtenemos una variable de tipo Coleccion de un elemento JSON 
	 * Tambien filtramos los json a aquellos que sigan la estructura Coleccion
	 * @param arbol listado de carpetas y archivos que recorremos recursivamente
	 * @param mapa un HashMap donde tenemos como llave el nombre del archivo y la coleccion
	 */
	public static void recorrerArbol(Path arbol, HashMap<String, Coleccion> mapa) {
		DirectoryStream<Path> hijosCarpeta;// Creamos el directoryStream
		
		try {

			hijosCarpeta = Files.newDirectoryStream(arbol);// hacemos esto para crear diferentes Path en los
			// subdirectorios de carpeta

			// y en el segundo borrar los ficheros
			for (Path entry : hijosCarpeta) {
				// Si entra en un directorio volvemos a llamar a la funcion para ver lo que tiene
				// caso recursivo
				if (Files.isDirectory(entry)) {
					System.out.println("entrando en" + entry.toString());
					recorrerArbol(entry, mapa);
			
					// caso base
				} else {// Si encuentra un archivo
					System.out.println("Entrando en: " + entry);

					Path rutaArchivo = entry.getFileName();
					System.out.println("El hijo es: " + rutaArchivo);

					File archivo = new File(rutaArchivo.toString());
					// Mediante este if comprobamos en el nombre que extension tiene y debe ser JSON
					if (archivo.getName().endsWith("json")) {
						System.out.println("Es un JsoN y es " + rutaArchivo);
						
						try {
							GsonBuilder builder=new GsonBuilder();
							builder.setPrettyPrinting();
							Gson gson=builder.create();
							FileReader fr=new FileReader(entry.toString());
							Coleccion coleccion=new Coleccion();
							coleccion	=(Coleccion)gson.fromJson(fr, Coleccion.class);
							System.out.println(coleccion);
							
							//Usamos este filtro para filtrar los JSON que sigan la estructura coleccion
							if (coleccion.esColeccion()==true) {
								//se lo añadimos al mapa
							
								mapa.put(archivo.getName(), coleccion);
							}else {
								System.out.println("Se encontró el archivo "+rutaArchivo.toString()+" pero no contiene una coleccion");
							}
							
							}catch (JsonSyntaxException jse){
								System.out.println("El fichero JSON no tiene el formato esperado");
							}
					} else {
						// System.out.println("");

					}

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Funcion para escribir xml, cogiendo el nombre del fichero como la llave del mapa y escribiendo el xml como la clave del mapa(coleccion)
	 * @param mapa hashMap donde tenemos la llave String, y la clave Coleccion
	 */
	public static void escribirXml(HashMap<String,Coleccion>mapa) {
		for (Entry<String, Coleccion> entry : mapa.entrySet()) {

			String key = entry.getKey();//sacamos el nombre que es la llave
			Coleccion value = entry.getValue();//sacamos el valor que es la coleccion
			
			key=key.replace(".json", "");
			
			try {

				JAXBContext con;
				con = JAXBContext.newInstance(Coleccion.class);
			
			Marshaller mar=con.createMarshaller();
			//Ponemos mas bonito el xml
			
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//Escribimos en un fileWriter el nombre del xml
			mar.marshal(value, new FileWriter("./"+key+".xml"));
			
			
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

				
		
	}

}
