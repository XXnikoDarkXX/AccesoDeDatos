package es.main.calculadora;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class CalculadoraUltimaPruebas {

	public static void main(String[] args) {
		String total = "";
		XMLInputFactory iFactory = XMLInputFactory.newFactory();
		Stack<String> pilaComprobacion = new Stack<String>();
		try {

			XMLStreamReader reader = iFactory.createXMLStreamReader(new FileReader("./calculadora.xml"));
			String operacionActual = "";// la operacion actual
			float operando1 = 0;
			String comandoAnterior = "";

			boolean controlParentesis = false;
			byte parentesisTotales = 0;
			while (reader.hasNext()) {
				switch (reader.getEventType()) {
				case XMLStreamReader.CHARACTERS:
					if (!reader.getText().trim().equals("")) {
						// meto el valor que encuentro en una de las variables
						/*
						 * if(operandoActual==1){ operando1=Float.parseFloat(reader.getText()); }else{
						 * operando2=Float.parseFloat(reader.getText()); } operandoActual++;
						 */
						switch (operacionActual) {
						case "suma":
							operando1 = Float.parseFloat(reader.getText());

							pilaComprobacion.push("+");
							pilaComprobacion.push(String.valueOf(operando1));

							total += "+" + operando1;
							System.out.println(total);
							break;
						case "multiplicacion":
							operando1 = Float.parseFloat(reader.getText());

							pilaComprobacion.push("*");
							pilaComprobacion.push(String.valueOf(operando1));

							total += "*" + operando1;
							System.out.println(total);
							break;
						case "division":
							operando1 = Float.parseFloat(reader.getText());
							pilaComprobacion.push("/");
							pilaComprobacion.push(String.valueOf(operando1));
							total += "/" + operando1;
							System.out.println(total);
							break;
						case "resta":
							operando1 = Float.parseFloat(reader.getText());

							pilaComprobacion.push("-");
							pilaComprobacion.push(String.valueOf(operando1));
							total += "-" + operando1;
							System.out.println(total);
							break;
						case "modulo":
							operando1 = Float.parseFloat(reader.getText());
							pilaComprobacion.push("%");

							pilaComprobacion.push(String.valueOf(operando1));
							total += "%" + operando1;
							System.out.println(total);
							break;
						}

					}
					break;

				case XMLStreamReader.START_ELEMENT:
					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Si es una operacion, guardo cuál es en operacionActual
						System.out.println("Encontrado apertura de etiqueta: " + reader.getLocalName());
						operacionActual = reader.getLocalName();
						// si es falso el control entonces el comandoAnterior=operacionAcutal
						

						if (controlParentesis == true && parentesisTotales >= 0) {
							parentesisTotales++;
							switch (comandoAnterior) {
							case "suma":

								pilaComprobacion.push("+");
								pilaComprobacion.push("(");
								controlParentesis = false;
								System.out.println("+(");
								total+=("+(");
								System.out.println(total);
								break;
							case "multiplicacion":

								pilaComprobacion.push("*");
								pilaComprobacion.push("(");
								System.out.println("*(" + operando1);
								controlParentesis = false;
								total+=("*(");
								break;
							case "division":
								pilaComprobacion.push("/");
								pilaComprobacion.push("(");
								controlParentesis = false;
								System.out.println("/" + operando1);
								total+=("/(");
								break;
							case "resta":

								pilaComprobacion.push("-");
								pilaComprobacion.push("(");
								controlParentesis = false;
								System.out.println("-" + operando1);
								// comandoAnterior=operacionActual;
								total+=("-(");
								System.out.println(total);
								break;
							case "modulo":
								pilaComprobacion.push("%");
								pilaComprobacion.push("(");
								controlParentesis = false;
								System.out.println("%" + operando1);
								total+=("%(");
								System.out.println(total);
								break;
							}
							
						}
						if (controlParentesis == false) {
							if (parentesisTotales==0) {
								comandoAnterior = operacionActual;
							}
							
							controlParentesis=true;
							
						}
						
					}

					break;
				case XMLStreamReader.END_ELEMENT:
					// Cierro una operación que tenía abierta

					System.out.println((reader.getLocalName()) != "operando"
							? "Encontrado el cierre de etiqueta: " + reader.getLocalName()
							: "");

					if (!reader.getLocalName().equals("operando") && !reader.getLocalName().equals("operaciones")) {
						// Es el momento de hacer la operacion
						if (parentesisTotales > 0) {

							switch (operacionActual) {
							case "suma":

								pilaComprobacion.push(")");
								float operandoTotal = 0;
								float operando = 0;
								String aux = "";

								operacionActual=comandoAnterior ;
								total += ")";
								break;
								

							case "multiplicacion":

								pilaComprobacion.push(")");
								operandoTotal = 1;
								operando = 0;
								aux = "";

								operacionActual=comandoAnterior ;
								total += ")";
								break;
							case "division":

								pilaComprobacion.push(")");
								operandoTotal = 0;
								operando = 0;
								aux = "";

								operacionActual=comandoAnterior ;
								total += ")";
								break;
							case "modulo":

								pilaComprobacion.push(")");
								operandoTotal = 0;
								operando = 0;
								aux = "";
								total += ")";
								operacionActual=comandoAnterior ;
								break;
							case "resta":

								pilaComprobacion.push(")");
								operandoTotal = 0;
								operando = 0;
								aux = "";
								total += ")";

								operacionActual=comandoAnterior ;

								break;
							}
							parentesisTotales--;
						}

					}
					break;

				}
				reader.next();
			}

		} catch (IOException ex) {

		} catch (XMLStreamException ex) {

		}
		Iterator iterator = pilaComprobacion.iterator();
		String contenidoPila = "";
		while (iterator.hasNext()) {
			String operando = (String) iterator.next();
			contenidoPila += operando;
		}
		System.out.println(contenidoPila);
	//	calcular(contenidoPila);
	}
/*	public calcularPila int (Stack<String> pilaComprobacion) {
		Iterator iterator = pilaComprobacion.iterator();
		String contenidoPila = "";
		
		LinkedList<String>sinParentesis=new LinkedList<String>();
		LinkedList<String>ConParentesis=new LinkedList<String>();
		String parente="";
		String sinParente="";
		String operandoAnterior="";
		String controlParentesis="";
		String total;
		while (iterator.hasNext()) {
			String operando = (String) iterator.next();
			if (operando.equals("+")||operando.equals("-")||operando.equals("/")||operando.equals("%")) {
				operandoAnterior=operando;
			}
			if (operando.equals("(")||operando.equals(")")) {
				controlParentesis=operando;
			}
			switch(operando) {
			
			case operandoAnterior="+":
			case controlParentesis="(":
			String
			}
		
			operandoAnterior=operando;
		}
		*/
		
	
	public static void cogerNumeros  (String total) {
		String parentesis="";
		for (int i = 0; i < total.length(); i++) {
		if (total.charAt(i)=='(') {
			parentesis+=total.charAt(i);
			for (int j = 0; j < total.indexOf(i); j++) {
				if (total.charAt(j)==')') {
					parentesis+=(')');
					
					i=j;
					break;
				}else {
					parentesis+=total.charAt(j);
				}
			}
		}	
		}
		
	}
	
	
	public int calcular (String parentesis) {
		String operador="";
		
		for (int i = 0; i < parentesis.length(); i++) {
			if (parentesis.charAt(i)=='(') {
				for (int j = 0; j < parentesis.indexOf(i); j++) {
					switch(parentesis.charAt(i)) {
			//		case '+'
					}
				}
			}
		}
		
		return 0;
		
	}
	

}
	
	
	
	
	


