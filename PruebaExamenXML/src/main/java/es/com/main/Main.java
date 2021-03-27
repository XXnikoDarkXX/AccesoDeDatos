package es.com.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import excepciones.IDYaExisteException;
import excepciones.MascotaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import javanet.staxutils.IndentingXMLStreamWriter;

public class Main {

	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	
byte opcion;
	do {
		System.out.println("\t0 - Salir "
				+"\n\t1 - Leer XML y guardar"
				+"\n\t2 - Escribir XML"
				+"\n\t3 - Actualizar nombre"
				+"\n\t4 - Actualizar especie"
				+"\n\t5 - Actualizar raza"
				+"\n\t6 - Actualizar propietario"
				+"\n\t7 - Actualizar id");
		opcion=Byte.parseByte(sc.nextLine());
	
	
	switch(opcion) {
	case 0:
		System.out.println("Salistes!!");
		break;
		
	case 1:
		System.out.println("Dime el fichero que quieres");
		String fichero=sc.nextLine();
		MascotaDAO m=leerXML(fichero);
		System.out.println(m);
		break;
		
	case 2:
		System.out.println("Dime el nombre para el fichero asegura que termine en xml");
		 fichero=sc.nextLine();
		 System.out.println("Dime la id para la mascota");
		 int id=Integer.parseInt(sc.nextLine());
		 
		 System.out.println("Dime el nombre para la mascota");
		 String nombre=sc.nextLine();
		 System.out.println("Dime la especie de la mascota");
		 
		 String especie=sc.nextLine();
		 
		 System.out.println("Dime la raza para la mascota");
		 
		 String raza=sc.nextLine();
		 
		 System.out.println("Dime el nombre del propietario");
		 String propietario=sc.nextLine();
		 crearXML( fichero, id,nombre,especie, raza,  propietario);
		
		break;
		
	case 3:
		System.out.println("Dime la id de la mascota que deseas actualizar");
		id=Integer.parseInt(sc.nextLine());
		 try {
			m=new MascotaDAO(id) ;
		System.out.println("Dime el nuevo nombre para la mascota");
			nombre=sc.nextLine();
			m.setNombre(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		break;
		
	case 4:
		System.out.println("Dime la id de la mascota que deseas actualizar");
		id=Integer.parseInt(sc.nextLine());
		 try {
			m=new MascotaDAO(id) ;
		System.out.println("Dime la nueva especie para la mascota");
			especie=sc.nextLine();
			m.setEspecie(especie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		break;
	case 5:
		
		System.out.println("Dime la id de la mascota que deseas actualizar");
		id=Integer.parseInt(sc.nextLine());
		 try {
			m=new MascotaDAO(id) ;
		System.out.println("Dime la nueva raza para la mascota");
			raza=sc.nextLine();
			m.setRaza(raza);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
	case 6:
		System.out.println("Dime la id de la mascota que deseas actualizar");
		id=Integer.parseInt(sc.nextLine());
		 try {
			m=new MascotaDAO(id) ;
		System.out.println("Dime el nuevo propietario para la mascota");
			propietario=sc.nextLine();
			m.setPropietario(propietario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
	case 7:
		System.out.println("Dime la id de la mascota que deseas actualizar");
		id=Integer.parseInt(sc.nextLine());
		 try {
			m=new MascotaDAO(id) ;
		System.out.println("Dime la nueva id");
			id=Integer.parseInt(sc.nextLine());
			try {
				m.setId(id);
				
				
			} catch (IDYaExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MascotaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
	default: 
		System.out.println("Opcion no valida");
		
		break;
	}
	
	
	}while(opcion!=0);
	
	}

	public static MascotaDAO leerXML(String fichero) {
		
		XMLInputFactory iFactory=XMLInputFactory.newDefaultFactory();
		XMLStreamReader reader;
		String nombre="";
		String especie="";
		String raza="";
		String propietario="";
		int id=0;
		try {
			reader = iFactory.createXMLStreamReader(new FileReader(fichero));
		
		//vamos a iterar el bucle hasta que no encuentre un evento
		//que puede ser etiquetas , elementos, subEtiquetas...ç
		String etiqueta="";
		
		while(reader.hasNext()) {
			//esto lo logramos con un switch
			switch(reader.getEventType()) {
			
			case XMLStreamReader.CHARACTERS:
				//usamos este if porque como tenemos intro en el xml
				//lo encontraba con este if vamos solo a los caracteres filtrando los intro
				//lo llamamos con el IdentingXMLStreamerWriter
				if (!reader.getText().trim().equals("")) {
					if (etiqueta.equals("nombre")) {
						 nombre=reader.getText();
					}else if(etiqueta.equals("id")){
						id=Integer.parseInt(reader.getText());
						
					}else if (etiqueta.equals("especie")) {
						especie=reader.getText();
						
					}else if (etiqueta.equals("raza")) {
						raza=reader.getText();
					
					}else if (etiqueta.equals("propietario")) {
						propietario=reader.getText();
					}
					
				}
				
				break;
			case XMLStreamReader.START_ELEMENT:
				System.out.println("Encontrado apertura de etiqueta: "+reader.getLocalName());
				//mediante este if econtramos los atributos de las etiquetas
				
				if(!reader.getLocalName().equals("documentacion")&&
                        !reader.getLocalName().equals("mascota")){
                    //Si es una operacion, guardo cuál es en operacionActual
                    etiqueta=reader.getLocalName();
                }
				break;
			
			case XMLStreamReader.END_ELEMENT:
				if(!reader.getLocalName().equals("documentacion")&&
                        !reader.getLocalName().equals("mascota")){
                    //Si es una operacion, guardo cuál es en operacionActual
                    
                }
				break;
			
			default:
				System.out.println("Se encontró la etiqueta de tipo: "+reader.getEventType());
				break;
				
				
			}
			reader.next();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MascotaDAO mascota;
		try {
			mascota = new MascotaDAO(id,nombre,especie,raza,propietario);
			return mascota;
		} catch (IDYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
	public static void crearXML(String fichero,int id,String nombre,String especie,String raza, String propietario) {
		String d=""+id;
try {
	if (Files.deleteIfExists(Paths.get(fichero))) {
				
			}

XMLOutputFactory factory=	XMLOutputFactory.newFactory();

		//mediante new IndentingXMLStreamWriter( hacemos bonito el ir creando xml
		XMLStreamWriter writer=new IndentingXMLStreamWriter(factory.createXMLStreamWriter(
				new FileWriter(fichero)));
	
		//Escribimos la cabecera necesaria del xml (xmlversion1.0)
				writer.writeStartDocument();
				writer.writeStartElement("mascota");//crea <raiz sin cerrar
				//metemos dentro de raiz una etiqueta quedando <raiz><ele..
				writer.writeStartElement("documentacion");
				
				
				writer.writeStartElement("id");
			
				writer.writeCharacters(d);
				writer.writeEndElement();
				
				writer.writeStartElement("nombre");
				writer.writeCharacters(nombre);
				writer.writeEndElement();
				
				
				writer.writeStartElement("especie");
				writer.writeCharacters(especie);
				writer.writeEndElement();
				
				writer.writeStartElement("raza");
				writer.writeCharacters(raza);
				writer.writeEndElement();
				
				writer.writeStartElement("propietario");
				writer.writeCharacters(propietario);
				writer.writeEndElement();
				
				
				writer.writeEndElement();
			
				writer.writeEndDocument();//creo que tambien ciera las demas etiquetas a parte de la raiz
				writer.flush();
				writer.close();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (XMLStreamException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
			
	}
	
	
}
