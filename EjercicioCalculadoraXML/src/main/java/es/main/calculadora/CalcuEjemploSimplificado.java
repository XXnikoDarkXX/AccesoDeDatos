package es.main.calculadora;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class CalcuEjemploSimplificado {

	public static void main(String[] args) {
		String total="";
		XMLInputFactory iFactory = XMLInputFactory.newFactory();
		Stack<String>pilaComprobacion=new Stack<String>();
		try {
		
			XMLStreamReader reader = iFactory.createXMLStreamReader(new FileReader("./calculadora.xml"));
			String operacionActual = "";//la operacion actual
			float operando1 = 0;
			String comandoAnterior="";
			Stack<Float> pila = new Stack<Float>();
			
			boolean controlParentesis=false;
			byte operandoActual = 1;
			while (reader.hasNext()) {
				switch (reader.getEventType()) {
				case XMLStreamReader.CHARACTERS:
					if (!reader.getText().trim().equals("")) {
						// meto el valor que encuentro en una de las variables
						/*
						 * if(operandoActual==1){ operando1=Float.parseFloat(reader.getText()); }else{
						 * operando2=Float.parseFloat(reader.getText()); } operandoActual++;
						 */
						switch(operacionActual) {
						case "suma":
							operando1 = Float.parseFloat(reader.getText());
							pila.push(operando1);
							pilaComprobacion.push("+");
							pilaComprobacion.push(String.valueOf(operando1));
							
							total+="+"+operando1;
							System.out.println(total);
							break;
						case "multiplicacion":
							operando1 = Float.parseFloat(reader.getText());
							pila.push(operando1);
							pilaComprobacion.push("*");
							pilaComprobacion.push(String.valueOf(operando1));
							
							total+="*"+operando1;
							System.out.println(total);
							break;
						case "division":
							operando1 = Float.parseFloat(reader.getText());
							pila.push(operando1);
							pilaComprobacion.push("/");
							pilaComprobacion.push(String.valueOf(operando1));
							total+="/"+operando1;
							System.out.println(total);
							break;
						case "resta":
							operando1 = Float.parseFloat(reader.getText());
							
							pila.push(operando1);
							pilaComprobacion.push("-");
							pilaComprobacion.push(String.valueOf(operando1));
							total+="-"+operando1;
							System.out.println(total);
							break;
						case "modulo":
							operando1 = Float.parseFloat(reader.getText());
							pila.push(operando1);
							pilaComprobacion.push("%");
							
							pilaComprobacion.push(String.valueOf(operando1));
							total+="%"+operando1;
							System.out.println(total);
							break;
						}
						
					}
					break;

				case XMLStreamReader.START_ELEMENT:
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Si es una operacion, guardo cuál es en operacionActual
						System.out.println("Encontrado apertura de etiqueta: "+reader.getLocalName());
						operacionActual = reader.getLocalName();
						if (controlParentesis==false) {
							comandoAnterior=operacionActual;
						}
						
						if (controlParentesis==true) {
							switch(comandoAnterior) {
							case "suma":
								
								pilaComprobacion.push("+");
								pilaComprobacion.push("(");
								controlParentesis=false;
								System.out.println("+(");
					
								break;
							case "multiplicacion":
								
								pilaComprobacion.push("*");
								pilaComprobacion.push("(");
								System.out.println("*("+operando1);
								controlParentesis=false;
					
								break;
							case "division":
							
								pilaComprobacion.push("/");
								pilaComprobacion.push("(");
								controlParentesis=false;
								System.out.println("/"+operando1);
					
								break;
							case "resta":
							
								pilaComprobacion.push("-");
								pilaComprobacion.push("(");
								controlParentesis=false;
								System.out.println("-"+operando1);
						//		comandoAnterior=operacionActual;
								break;
							case "modulo":
								pilaComprobacion.push("%");
								pilaComprobacion.push("(");
								controlParentesis=false;
								System.out.println("%"+operando1);
					
								break;
							}
						}
				
						
						controlParentesis=true;
						
					
					}

					break;
				case XMLStreamReader.END_ELEMENT:
					// Cierro una operación que tenía abierta
					
					
					System.out.println((reader.getLocalName()) != "operando"?"Encontrado el cierre de etiqueta: "+reader.getLocalName():"");
					
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Es el momento de hacer la operacion
						switch (operacionActual) {
						case "suma":
					
							pilaComprobacion.push(")");
							float operandoTotal = 0;
							float operando = 0;
							String aux = "";
							
						/*	Iterator iterator = pila.iterator();
							int contador = 1;
							while (iterator.hasNext()) {
								operando = (float) iterator.next();
								operandoTotal += operando;

								if (contador < pila.size()) {
									aux += operando + " + ";
								} else {
									aux += operando;

								}
								contador++;
							}*/
							
					
							comandoAnterior="";
							pila.clear();
							break;

						case "multiplicacion":
				
							
							pilaComprobacion.push(")");
							operandoTotal = 1;
							operando = 0;
							aux = "";

						int	contador = 1;
							Iterator iterator = pila.iterator();
							
							while (iterator.hasNext()) {
								operando = (float) iterator.next();
								operandoTotal *= operando;

								if (contador < pila.size()) {
									aux += operando + " * ";
								} else {
									aux += operando;

								}
								contador++;
							}
							
						
					
							comandoAnterior="";
							pila.clear();
							break;
						case "division":
			
							
							pilaComprobacion.push(")");
							operandoTotal = 0;
							operando = 0;
							aux = "";

							contador = -1;
							iterator = pila.iterator();
							
							while (iterator.hasNext()) {
								
								operando = (float) iterator.next();
								if (contador==-1&&pila.size()>2) {
									
									operandoTotal=operando;
									aux += operando + " / ";
									contador=1;
									contador++;
									continue;
								}
									
								operandoTotal/=operando;
								if (contador < pila.size()) {
									aux += operando + " / ";
								} else {
									aux += operando;

								}
								contador++;
							}
							
						
	
							comandoAnterior="";
							pila.clear();
							break;
						case "modulo":
						
							
							pilaComprobacion.push(")");
							operandoTotal = 0;
							operando = 0;
							aux = "";

							contador = -1;
							iterator = pila.iterator();
							
							while (iterator.hasNext()) {
								
								operando = (float) iterator.next();
								if (contador==-1&&pila.size()>2) {
									
									operandoTotal=operando;
									aux += operando + " % ";
									contador=1;
									contador++;
									continue;
								}
									
								operandoTotal%=operando;
								if (contador < pila.size()) {
									aux += operando + " % ";
								} else {
									aux += operando;

								}
								contador++;
							}
							
							comandoAnterior="";
							pila.clear();
							break;
						case "resta":
				
							
							pilaComprobacion.push(")");
							operandoTotal = 0;
							operando = 0;
							aux = "";

							/*contador = -1;
							iterator = pila.iterator();
							
							while (iterator.hasNext()) {
								
								operando = (float) iterator.next();
								if (contador==-1&&pila.size()>2) {
									
									operandoTotal=operando;
									aux += operando + " - ";
									contador=1;
									contador++;
									continue;
								}
									
								operandoTotal-=operando;
								if (contador < pila.size()) {
									aux += operando + " - ";
								} else {
									aux += operando;

								}
								contador++;
							}*/
							
							comandoAnterior="";
							pila.clear();
							break;
						}
						operandoActual = 1;
					}
					break;

				}
				reader.next();
			}
			
			
			
		
			
			

		} catch (IOException ex) {

		} catch (XMLStreamException ex) {
			

		}
		Iterator iterator = pilaComprobacion.iterator();
		String pepe="";
		while (iterator.hasNext()) {
		String	operando = (String) iterator.next();
		pepe+=operando;
		}
		System.out.println(pepe);
	}
	
	
}
