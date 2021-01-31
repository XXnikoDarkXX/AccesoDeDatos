package es.main.calculadora.segundaparte;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class PilaProbando {

	public static void main(String[] args) {
		XMLInputFactory iFactory = XMLInputFactory.newFactory();
		XMLStreamReader reader;
		try {
			reader = iFactory.createXMLStreamReader(new FileReader("./calculadora.xml"));
			float resultadoTotal = 0;
			String operacionActual = "";
			float operando1 = 0;
			float operando2 = 0;
			byte operandoActual = 0;
			String comandoAnteriorNivel1 = "";
			Stack<Float> nivel1 = new Stack<Float>();// este stack lo usaremos para calcular los niveles 1
			Stack<Float> nivel2 = new Stack<Float>();// este stack lo usaremos para calcular los niveles 2
			String operacionesNivel1 = "";// Para saber que tipo operacion debe hacer el nivel 2
			String operacionesNivel2 = "";
			boolean paradaComando = false;

			String opActualn1 = ""; // son los numeros que hay
			String opActualn2 = "";

			while (reader.hasNext()) {

				switch (reader.getEventType()) {
				case XMLStreamReader.CHARACTERS:
					if (!reader.getText().trim().equals("")) {
						// meto el valor que encuentro en una de las variables
						if (operacionesNivel2.isEmpty()) {
							nivel1.push(Float.parseFloat(reader.getText()));

						} else {
							nivel2.push(Float.parseFloat(reader.getText()));
						}

					}
					break;
				case XMLStreamReader.START_ELEMENT:
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						System.out.println("Encontrado apertura de etiqueta: " + reader.getLocalName());
						// Si es una operacion, guardo cuál es en operacionActual

						if (operacionesNivel1.isEmpty()) {
							operacionesNivel1 = reader.getLocalName();

						} else {
							operacionesNivel2 = reader.getLocalName();
						}

					}

					break;
				case XMLStreamReader.END_ELEMENT:
					// Cierro una operación que tenía abierta
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						System.out.println((reader.getLocalName()) != "operando"
								? "Encontrado el cierre de etiqueta: " + reader.getLocalName()
								: "");
						opActualn1="";
						opActualn2 = "";
						// Es el momento de hacer la operacion
						switch (reader.getLocalName()) {

						case "suma":
							if (operacionesNivel2.isEmpty()) {
								// estoy en nivel 1
								float resultado = 0;

								Iterator iterator = nivel1.iterator();
								while (iterator.hasNext()) {
									float numero = (float) iterator.next();
									resultado += numero;
									opActualn1 += "+" + numero;
								}
								if (comandoAnteriorNivel1.equals("")) {
									resultadoTotal = resultado;
								}
								System.out.println(opActualn1+"="+resultado);
								opActualn1="";
								switch (comandoAnteriorNivel1) {
								case "suma":
									resultadoTotal = resultadoTotal + resultado;
									break;

								}

								System.out.println(opActualn1 + "=" + resultadoTotal);
								nivel1.clear();
								// comandoAnteriorNivel1 = operacionesNivel1;
								comandoAnteriorNivel1 = "suma";
								operacionesNivel1 = "";
							} else {
								// estoy en el nivel 2
								float resultado = 0;
								opActualn2 += "(";
								Iterator iterator = nivel2.iterator();
								while (iterator.hasNext()) {
									float numero = (float) iterator.next();
									resultado += numero;
									opActualn2 += "+" + numero;
								}
								opActualn2 += ")";
								System.out.println(opActualn2 + "=" + resultado);

								if (resultado != 0) {// Si resultado es diferente de 0 lo metemos a la pila de
														// nivel1(esto para que no de erroes)
									nivel1.push(resultado);
								//	opActualn1 += resultado;
								}

								nivel2.clear();
								operacionesNivel2 = "";
							}
							break;
						case "resta":
							if (operacionesNivel2.isEmpty()) {
								// estoy en nivel 1
								float resultado = 0;

								boolean control = false;
								Iterator iterator = nivel1.iterator();
								while (iterator.hasNext()) {

									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn1 += resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado - numero;
									opActualn1 += "-" + numero;
								}
								System.out.println(opActualn1+"="+resultado);
								opActualn1="";
								if (comandoAnteriorNivel1.equals("")) {
									resultadoTotal = resultado;
								}
								switch (comandoAnteriorNivel1) {
								case "suma":
									opActualn1+=resultadoTotal+"+"+resultado;
									resultadoTotal = resultadoTotal + resultado;
									break;
								case "resta":
									resultadoTotal = resultadoTotal - resultado;
									break;
								case "multiplicacion":
									resultadoTotal = resultadoTotal * resultado;
									break;
								case "division":
									resultadoTotal = resultadoTotal / resultado;
									break;

								}

								System.out.println(opActualn1 + "=" + resultadoTotal);
								nivel1.clear();
								// comandoAnteriorNivel1 = operacionesNivel1;
								comandoAnteriorNivel1 = "suma";
								operacionesNivel1 = "";
							} else {
								// estoy en el nivel 2
								float resultado = 0;
								boolean control = false;// booleano de control que sirve para realizar correctamente los
														// calculos
								Iterator iterator = nivel2.iterator();
								while (iterator.hasNext()) {
									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn2 += "(" + resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado -= numero;
									opActualn2 += "-" + numero;
								}

								System.out.println(opActualn2 += ")" + "=" + resultado);
									
								if (resultado != 0) {// Si resultado es diferente de 0 lo metemos a la pila de
														// nivel1(esto para que no de erroes)
									nivel1.push(resultado);
							//		opActualn1 += resultado;
								}

								nivel2.clear();
								operacionesNivel2 = "";
							}
							break;
						case "division":

							if (operacionesNivel2.isEmpty()) {
								// estoy en nivel 1
								float resultado = 0;

								boolean control = false;
								Iterator iterator = nivel1.iterator();
								while (iterator.hasNext()) {

									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn1 += resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado / numero;
									opActualn1 += "/" + numero;
								}

								if (comandoAnteriorNivel1.equals("")) {
									resultadoTotal = resultado;
								

								}
								System.out.println(opActualn1+"="+resultado);
								opActualn1="";
								switch (comandoAnteriorNivel1) {
								case "suma":
									opActualn1=resultadoTotal+"+"+resultado;
									resultadoTotal = resultadoTotal + resultado;
									break;
								case "resta":
									resultadoTotal = resultadoTotal - resultado;
									break;
								case "multiplicacion":
									resultadoTotal = resultadoTotal * resultado;
									break;
								case "division":
									resultadoTotal = resultadoTotal / resultado;
									break;

								}

								System.out.println(opActualn1 + "=" + resultadoTotal);
								nivel1.clear();
								// comandoAnteriorNivel1 = operacionesNivel1;
								comandoAnteriorNivel1 = "suma";
								operacionesNivel1 = "";
							} else {
								// estoy en el nivel 2
								float resultado = 0;
								boolean control = false;
								Iterator iterator = nivel2.iterator();
								while (iterator.hasNext()) {
									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn2 += "(" + resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado / numero;
									opActualn2 += "/" + numero;
								}
								System.out.println(opActualn2 += ")" + "=" + resultado);
								//opActualn1 = opActualn2 + opActualn1;
								if (resultado != 0) {// Si resultado es diferente de 0 lo metemos a la pila de
														// nivel1(esto para que no de erroes)
									nivel1.push(resultado);
									opActualn1 += resultado;

								}

								nivel2.clear();
								operacionesNivel2 = "";
							}
							break;
						case "multiplicacion":

							if (operacionesNivel2.isEmpty()) {
								// estoy en nivel 1
								float resultado = 0;

								boolean control = false;
								Iterator iterator = nivel1.iterator();
								while (iterator.hasNext()) {

									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn1 += resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado * numero;
									opActualn1 += "*" + numero;
								}

								if (comandoAnteriorNivel1.equals("")) {
									resultadoTotal = resultado;
							//		opActualn1 += resultado;

								}
								System.out.println(opActualn1+"="+resultado);
								opActualn1="";
								switch (comandoAnteriorNivel1) {
								case "suma":
									opActualn1+=resultadoTotal+"+"+resultado;
									resultadoTotal = resultadoTotal + resultado;
									break;
								case "resta":
									resultadoTotal = resultadoTotal - resultado;
									break;
								case "multiplicacion":
									resultadoTotal = resultadoTotal * resultado;
									break;
								case "division":
									resultadoTotal = resultadoTotal / resultado;
									break;

								}

								System.out.println(opActualn1 + "=" + resultadoTotal);
								nivel1.clear();
								comandoAnteriorNivel1 = "suma";
								operacionesNivel1 = "";
							} else {
								// estoy en el nivel 2
								float resultado = 0;
								boolean control = false;
								Iterator iterator = nivel2.iterator();
								while (iterator.hasNext()) {
									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn2 += "(" + resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado * numero;
									opActualn2 += "*" + numero;
								}
								System.out.println(opActualn2 += ")" + "=" + resultado);
						//		opActualn1 = opActualn2 + opActualn1;
								if (resultado != 0) {// Si resultado es diferente de 0 lo metemos a la pila de
														// nivel1(esto para que no de erroes)
									nivel1.push(resultado);
								//	opActualn1 += resultado;

								}

								nivel2.clear();
								operacionesNivel2 = "";
							}

							break;
						case "modulo":
							if (operacionesNivel2.isEmpty()) {
								// estoy en nivel 1
								float resultado = 0;

								boolean control = false;
								Iterator iterator = nivel1.iterator();
								while (iterator.hasNext()) {

									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn1 += resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado % numero;
									opActualn1 += "%" + numero;
								}

								if (comandoAnteriorNivel1.equals("")) {
									resultadoTotal = resultado;
								

								}
								System.out.println(opActualn1+"="+resultado);
								opActualn1="";
								switch (comandoAnteriorNivel1) {
								case "suma":
									opActualn1=resultadoTotal+"+"+resultado;
									resultadoTotal = resultadoTotal + resultado;
									break;
								case "resta":
									resultadoTotal = resultadoTotal - resultado;
									break;
								case "multiplicacion":
									resultadoTotal = resultadoTotal * resultado;
									break;
								case "division":
									resultadoTotal = resultadoTotal / resultado;
									break;

								}

								System.out.println(opActualn1 + "=" + resultadoTotal);
								nivel1.clear();
								// comandoAnteriorNivel1 = operacionesNivel1;
								comandoAnteriorNivel1 = "suma";
								operacionesNivel1 = "";
							} else {
								// estoy en el nivel 2
								float resultado = 0;
								boolean control = false;
								Iterator iterator = nivel2.iterator();
								while (iterator.hasNext()) {
									if (control == false) {
										control = true;
										resultado = (float) iterator.next();
										opActualn2 += "(" + resultado;
										continue;

									}
									float numero = (float) iterator.next();
									resultado = resultado % numero;
									opActualn2 += "%" + numero;
								}
								System.out.println(opActualn2 += ")" + "=" + resultado);
								//opActualn1 = opActualn2 + opActualn1;
								if (resultado != 0) {// Si resultado es diferente de 0 lo metemos a la pila de
														// nivel1(esto para que no de erroes)
									nivel1.push(resultado);
									opActualn1 += resultado;

								}

								nivel2.clear();
								operacionesNivel2 = "";
							}

						}

					}
					break;
				}

				reader.next();

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
