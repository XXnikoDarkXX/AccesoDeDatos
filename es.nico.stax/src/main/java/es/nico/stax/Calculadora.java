package es.nico.stax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
public class Calculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  XMLInputFactory iFactory=XMLInputFactory.newFactory();
		  try {
		         
	     
	            XMLStreamReader reader=iFactory.createXMLStreamReader(new FileReader("./calculadora.xml"));
	            String operacionActual="";
	            float operando1=0;
	            float operando2=0;
	            byte operandoActual=1;
	            while(reader.hasNext()){
	                switch(reader.getEventType()){
	                    case XMLStreamReader.CHARACTERS:
	                        if(!reader.getText().trim().equals("")){
	                            //meto el valor que encuentro en una de las variables
	                            if(operandoActual==1){
	                                operando1=Float.parseFloat(reader.getText());
	                            }else{
	                                operando2=Float.parseFloat(reader.getText());
	                            }
	                            operandoActual++;
	                        }
	                        break;
	                    case XMLStreamReader.START_ELEMENT:
	                        if(!reader.getLocalName().equals("operando")&&
	                                !reader.getLocalName().equals("operaciones")){
	                            //Si es una operacion, guardo cuál es en operacionActual
	                            operacionActual=reader.getLocalName();
	                        }
	                        
	                        break;
	                    case XMLStreamReader.END_ELEMENT:
	                        //Cierro una operación que tenía abierta
	                        if(!reader.getLocalName().equals("operando")&&
	                                !reader.getLocalName().equals("operaciones")){
	                            //Es el momento de hacer la operacion
	                            switch(operacionActual){
	                                case "suma":
	                                    System.out.println("suma "+operando1+"+"+operando2+"="+(operando1+operando2));
	                                    break;
	                                case "multiplicacion":
	                                    System.out.println("multiplicacion "+operando1+"*"+operando2+"="+(operando1*operando2));
	                                    break;
	                                case "division":
	                                	 System.out.println("division "+operando1+"/"+operando2+"="+(operando1/operando2));
		                                  
	                                	break;
	                                case "modulo":
	                                	 System.out.println("modulo "+operando1+"%"+operando2+"="+(operando1%operando2));
		                                  
	                                	break;
	                                case "resta":
	                                	 System.out.println("resta "+operando1+"-"+operando2+"="+(operando1-operando2));
		                                  
	                                	break;
	                            }
	                            operandoActual=1;
	                        }
	                        break;
	                }
	                reader.next();
	            }
	       
	        } catch (IOException ex) {
	        
	        } catch (XMLStreamException ex) {
	           
	        }
	}
}