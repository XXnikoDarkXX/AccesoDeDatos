package es.nico.stax;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import javanet.staxutils.IndentingXMLStreamWriter;

public class Main {

	public static void main(String[] args) {
	XMLOutputFactory factory=	XMLOutputFactory.newFactory();
	
	try {
		//mediante new IndentingXMLStreamWriter( hacemos bonito el ir creando xml
		XMLStreamWriter writer=new IndentingXMLStreamWriter(factory.createXMLStreamWriter(
				new FileWriter("./prueba.xml")));
		
		//Escribimos la cabecera necesaria del xml (xmlversion1.0)
		writer.writeStartDocument();
		writer.writeStartElement("raiz");//crea <raiz sin cerrar
		//metemos dentro de raiz una etiqueta quedando <raiz><ele..
		writer.writeStartElement("elementoNivel1");
		writer.writeAttribute("atributo1", "valor1");//escribimos el atributo y luego el valor
		
		writer.writeCData("Hola soy el elemento de nivel 1");
		
		writer.writeEndElement();
		writer.writeStartElement("elementoNivel1");
		writer.writeStartElement("elementoNivel2");
		writer.writeCharacters("2.0f");//escribimos valores dentro del elemento
		writer.writeEndElement();
		writer.writeEmptyElement("elementoNivel2");//hacemos la etiqueta vacia <elementoNivel2/>
		writer.writeEndDocument();//creo que tambien ciera las demas etiquetas a parte de la raiz
		writer.flush();
		writer.close();
		
		//Lectura
		
		XMLInputFactory iFactory=XMLInputFactory.newDefaultFactory();
		XMLStreamReader reader=iFactory.createXMLStreamReader(new FileReader("./prueba.xml"));
		//vamos a iterar el bucle hasta que no encuentre un evento
		//que puede ser etiquetas , elementos, subEtiquetas...
		while(reader.hasNext()) {
			//esto lo logramos con un switch
			switch(reader.getEventType()) {
			
			case XMLStreamReader.CHARACTERS:
				//usamos este if porque como tenemos intro en el xml
				//lo encontraba con este if vamos solo a los caracteres filtrando los intro
				//lo llamamos con el IdentingXMLStreamerWriter
				if (!reader.getText().trim().equals("")) {
					System.out.println("Encontrado caracteres: "+reader.getText());
					
				}
				
				break;
			case XMLStreamReader.START_ELEMENT:
				System.out.println("Encontrado apertura de etiqueta: "+reader.getLocalName());
				//mediante este if econtramos los atributos de las etiquetas
				if (reader.getAttributeCount()>0) {
					for (int i = 0; i < reader.getAttributeCount(); i++) {
						System.out.println("Atributo: "+reader.getAttributeLocalName(i));
						System.out.println("Valor: "+reader.getAttributeValue(i));
					}
				}
				break;
			
			case XMLStreamReader.END_ELEMENT:
				System.out.println("Encontrado el cierre de etiqueta: "+reader.getLocalName());
				break;
			
			default:
				System.out.println("Se encontró la etiqueta de tipo: "+reader.getEventType());
				break;
				
				
			}
			reader.next();
		}
	
	} catch (XMLStreamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}

}
