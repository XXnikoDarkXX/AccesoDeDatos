package es.examenxml.marshalling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.Telefono;

public class MainExamenXML {

	public static void main(String[] args) {
		
		
		//creo la clase necesaria para hacerle un umarsalling y leer el xlm a objeto + paso el toString
		
		
		
			try {
				JAXBContext  context = JAXBContext.newInstance(Telefono.class);
			
		
		//Pasamos a objeto el xml
		Unmarshaller um=context.createUnmarshaller();
		Telefono telefonoTraido=(Telefono) um.unmarshal(new FileReader("./telefono.xml"));//Simplemente traemos un archivo xml al objeto
		System.out.println(telefonoTraido);
		
		
		
		GsonBuilder builder=new GsonBuilder();
		builder.setDateFormat("Y/M/d");
		builder.setPrettyPrinting();
		Gson gson=builder.create();
		
		//pasar de xml a Json
		String telefono=gson.toJson(telefonoTraido);//pasamos a un String convertido a gson
	
		System.out.println(telefono);
			
		//Escribimo el fichero en JSON
		FileWriter fw=new FileWriter("./salida.json");
		
		fw.write(telefono);
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
