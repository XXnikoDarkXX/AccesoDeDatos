package es.pruebasgson.main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import unmarsshalling.Biblioteca;

public class MainGSON {

	public static void main(String[] args) {
		GsonBuilder builder=new GsonBuilder();
		builder.setDateFormat("Y/M/d");
		builder.setPrettyPrinting();
		Gson gson=builder.create();
		ArrayList<Persona>personas=new ArrayList<Persona>();
		for (int i = 0; i < 50; i++) {
			personas.add(new Persona());
		}
		try {
		//Parte JAXB
		JAXBContext con=JAXBContext.newInstance(Persona.class);
		Marshaller mar=con.createMarshaller();
		//Ponemos mas bonito el xml
		
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		mar.marshal(personas.get(0), new FileWriter("./persona0.xml"));
		 
		//Parte Gson
		String personaJson=gson.toJson(personas);
		System.out.println(personaJson);

		FileWriter fw=new FileWriter("./salida.json");
		fw.write(personaJson);
		fw.flush();
		fw.close();
		try {
		FileReader fr=new FileReader("./videojuegos.json");
	Biblioteca biblio=	(Biblioteca)gson.fromJson(fr, Biblioteca.class);
		System.out.println(biblio);
		
		}catch (JsonSyntaxException jse){
			System.out.println("El fichero JSON no tiene el formato esperado");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
