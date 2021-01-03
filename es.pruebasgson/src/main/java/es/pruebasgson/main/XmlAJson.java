package es.pruebasgson.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import unmarsshalling.Biblioteca;

public class XmlAJson {

	public static void main(String[] args) {
		
		GsonBuilder builder=new GsonBuilder();
		builder.setDateFormat("Y/M/d");
		builder.setPrettyPrinting();
		Gson gson=builder.create();
		ArrayList<Persona>personas=new ArrayList<Persona>();
		for (int i = 0; i < 50; i++) {
			personas.add(new Persona());
			
		}
		//Traer un xml a un objeto
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Biblioteca.class);
		
		
		Unmarshaller um=context.createUnmarshaller();
		Biblioteca traida=(Biblioteca) um.unmarshal(new FileReader("./biblioteca.xml"));//Simplemente traemos un archivo xml al objeto
		System.out.println(traida);
		
		
		
		
		//Pasar de un objeto a xml
		//Usamos la clase JAXBContext y la instanciamos con la clase donde vamos a exportar en este caso Persona
		JAXBContext con=JAXBContext.newInstance(Persona.class);
		Marshaller mar=con.createMarshaller();
		//Ponemos mas bonito el xml
		
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		mar.marshal(personas.get(0), new FileWriter("./personaPrueba.xml"));
		
		//pasar de xml a Json
		String personaJson=gson.toJson(personas);//pasamos a un String convertido a gson
		//mediante gson.ToJson(personas)
		System.out.println(personaJson);
			
		//Escribimo el fichero en JSON
		FileWriter fw=new FileWriter("./salida.json");
		
		fw.write(personaJson);
		fw.flush();
		fw.close();
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
