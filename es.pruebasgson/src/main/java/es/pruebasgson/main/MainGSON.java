package es.pruebasgson.main;

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
		//Parte JAXB crear xml
			
		//Usamos la clase JAXBContext y la instanciamos con la clase donde vamos a exportar en este caso Persona
		JAXBContext con=JAXBContext.newInstance(Persona.class);
		Marshaller mar=con.createMarshaller();
		//Ponemos mas bonito el xml
		
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		mar.marshal(personas.get(0), new FileWriter("./persona0.xml"));
		
		//Parte Gson 
		String personaJson=gson.toJson(personas);//pasamos a un String convertido a gson
		//mediante gson.ToJson(personas)
		System.out.println(personaJson);
			
		//Escribimo el fichero en JSON
		FileWriter fw=new FileWriter("./salida.json");
		
		fw.write(personaJson);
		fw.flush();
		fw.close();
		try {
			//Leer Json y ponerla en una variable
		FileReader fr=new FileReader("./videojuegos.json");//creamos un FileReader donde lee el Json
	Biblioteca biblio=	(Biblioteca)gson.fromJson(fr, Biblioteca.class);//sacamos el json al objeto biblio
		System.out.println(biblio);//lo imprimimos con el ToString
		
		//Exportar biblioteca a XML fichero
		JAXBContext context=JAXBContext.newInstance(Biblioteca.class);
		Marshaller marBiblioteca=context.createMarshaller();
		marBiblioteca.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//hace que se imprima de forma bonita
		marBiblioteca.marshal(biblio, new FileWriter("./biblioteca.xml"));
		
	
	/*	//traer xml a objeto
		Unmarshaller um=context.createUnmarshaller();
		Biblioteca traida=(Biblioteca) um.unmarshal(new FileReader("./biblioteca.xml"));//Simplemente traemos un archivo xml al objeto
		System.out.println("espetate");
		*/
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
