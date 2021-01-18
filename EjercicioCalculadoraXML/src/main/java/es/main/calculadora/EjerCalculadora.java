package es.main.calculadora;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class EjerCalculadora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLInputFactory iFactory = XMLInputFactory.newFactory();
		try {
			
			XMLStreamReader reader = iFactory.createXMLStreamReader(new FileReader("./calculadora.xml"));
			String operacionActual = "";
			float operando1 = 0;
			float operando2 = 0;
			Stack<Float> pila = new Stack<Float>();
			Stack<String>pilaComprobacion=new Stack<String>();
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
						operando1 = Float.parseFloat(reader.getText());
						pila.push(operando1);
					}
					break;

				case XMLStreamReader.START_ELEMENT:
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Si es una operacion, guardo cuál es en operacionActual
						operacionActual = reader.getLocalName();
						pilaComprobacion.push(operacionActual);
					}

					break;
				case XMLStreamReader.END_ELEMENT:
					// Cierro una operación que tenía abierta
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Es el momento de hacer la operacion
						switch (operacionActual) {
						case "suma":
							
							
							float operandoTotal = 0;
							float operando = 0;
							String aux = "";

							Iterator iterator = pila.iterator();
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
							}
							
							System.out.println(pila.isEmpty()?"":aux + " = " + operandoTotal);
							
							pila.clear();
							break;

						case "multiplicacion":
							operandoTotal = 1;
							operando = 0;
							aux = "";

							contador = 1;
							iterator = pila.iterator();
							
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
							
						
							System.out.println(pila.isEmpty()?"":aux + " = " + operandoTotal);
							pila.clear();
							break;
						case "division":
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
							
						
							System.out.println(pila.isEmpty()?"":aux + " = " + operandoTotal);
					
							pila.clear();
							break;
						case "modulo":
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
							
							System.out.println(pila.isEmpty()?"":aux + " = " + operandoTotal);
							pila.clear();
							break;
						case "resta":
							operandoTotal = 0;
							operando = 0;
							aux = "";

							contador = -1;
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
							}
							
						//	System.out.println(aux + " = " + operandoTotal);
							System.out.println(pila.isEmpty()?"":aux + " = " + operandoTotal);
						
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
	}
}