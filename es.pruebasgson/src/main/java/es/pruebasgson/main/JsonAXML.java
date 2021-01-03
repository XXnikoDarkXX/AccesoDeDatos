package es.pruebasgson.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import unmarsshalling.Biblioteca;



public class JsonAXML {

	public static void main(String[] args) {
		
		
		GsonBuilder builder=new GsonBuilder();
		builder.setDateFormat("Y/M/d");
		builder.setPrettyPrinting();
		Gson gson=builder.create();
		ArrayList<Persona>personas=new ArrayList<Persona>();
		for (int i = 0; i < 50; i++) {
			personas.add(new Persona());
		}
		
		
		
				//Escribir Objeto a JSON
				String personaJson=gson.toJson(personas);//pasamos a un String convertido a gson
				//mediante gson.ToJson(personas)
				System.out.println(personaJson);
					
				//Escribimo el fichero en JSON
				FileWriter fw;
				try {
					
					
					
					fw = new FileWriter("./salida.json");
				
				fw.write(personaJson);
				fw.flush();
				fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				//Leer JSON Y Pasarlo a objeto
				
			
			
		
				FileReader fr;//creamos un fileReader donde lee videojujego
				try {
					fr=new FileReader("./videojuegos.json");
							Biblioteca biblio=	(Biblioteca)gson.fromJson(fr, Biblioteca.class);//sacamos el json al objeto biblio
					System.out.println(biblio);//lo imprimimos con el ToString
					
				
		//Escribir el XML desde JSON
				JAXBContext context=JAXBContext.newInstance(Biblioteca.class);
				Marshaller marBiblioteca=context.createMarshaller();
				marBiblioteca.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//hace que se imprima de forma bonita
				marBiblioteca.marshal(biblio, new FileWriter("./biblioteca.xml"));
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
