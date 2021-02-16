/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cenecmalaga.miproyecto.pruebadao;

import daos.DAO;
import daos.Mascota;
import daos.Persona;
import daos.SerVivo;
import excepciones.DNIYaExisteException;
import excepciones.PersonaNoEncontradaException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mparamos
 */
public class Main {
    public static void main(String[] args){
        try {
			System.out.println(DAO.get(1,Mascota.class) );
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        byte opcionSeleccionada = -1;
        Scanner sc = new Scanner(System.in);
        
        do{
            
            System.out.println("Menú acciones:\n\t0 - Salir"
            		+ "\n\t1 - Registrar usuario"
            		+ "\n\t2 - Recuperar usuario"
            		+ "\n\t3 - Editar nombre"
            		+ "\n\t4 - Editar apellidos"
            		+ "\n\t5 - Editar fecha de Nacimiento"
            		+"\n\t6 - Editar DNI"
            		+"\n\t7 - Ver todas las personas");
            opcionSeleccionada = Byte.parseByte(sc.nextLine());
            
            switch(opcionSeleccionada){
                
                case 0:
                    
                    System.out.println("¡Adiós!");
                    
                    break;
                    
                case 1:
                    
                    registrarUsuario();
                    break;
                    
                case 2:
                    
                    Persona p = recuperarUsuario();
                    System.out.println("La persona recuperada es:\n"+p);
                    
                    break;
                case 3:
                	p=recuperarUsuario();
                	System.out.println("Dime nuevo nombre para "+p.getNombre()+" "+p.getApellidos());
					try {
						p.setNombre(sc.nextLine());
					} catch (SQLException e) {
						System.out.println("No se pudo cambiar "+e.getMessage());
					}
                	break;
                case 4:
                	p=recuperarUsuario();
                	System.out.println("Dime nuevos apellidos para "+p.getNombre()+" "+p.getApellidos());
					try {
						p.setApellidos(sc.nextLine());
					} catch (SQLException e) {
						System.out.println("No se pudo cambiar "+e.getMessage());
					}
                	break;
                case 5:
                	p=recuperarUsuario();
                	System.out.println("Dime nueva fecha de nacimiento para "+p.getNombre()+" "+p.getApellidos());
					try {
						p.setfNacimiento(LocalDate.parse(sc.nextLine()));
					} catch (SQLException e) {
						System.out.println("No se pudo cambiar"+e.getMessage());
					}
                	break;
                case 6:
                	p=recuperarUsuario();
                	System.out.println("Dime nuevo dni para "+p.getNombre()+" "+p.getApellidos());
					try {
						p.setDni(sc.nextLine());
					} catch (SQLException e) {
						System.out.println("No se pudo cambiar S"+e.getMessage());
						e.printStackTrace();
					} catch (DNIYaExisteException e) {
						// TODO Auto-generated catch block
						System.out.println("No se puede cambiar el dni: "+e.getMessage());
					}
                	break;
                case 7:
                	ArrayList<Persona> todos=Persona.todas();
                	for (int i = 0; i < todos.size(); i++) {
						System.out.println(todos.get(i));
					}	
                	break;
            }
            
            
        }while(opcionSeleccionada!=0);
    }
    
    
    public static void registrarUsuario(){
        
        try {
            //TODO CONVERTIR EN FUNCIÓN RECURSIVA
            Scanner sc=new Scanner(System.in);
            System.out.println("Vamos a registrar a una nueva Persona.");
            System.out.println("Dime su DNI:");
            String dni=sc.nextLine();
            System.out.println("Dime su nombre:");
            String nombre=sc.nextLine();
            System.out.println("Dime sus apellidos:");
            String apellidos=sc.nextLine();
            System.out.println("Dime su fecha de nacimiento:");
            LocalDate nacimiento=LocalDate.parse(sc.nextLine());
            
            Persona p=new Persona(nombre,apellidos,nacimiento,dni);
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
        
        public static Persona recuperarUsuario(){
        
        Scanner sc=new Scanner(System.in);
        try {
            
            System.out.println("Dime el dni de la persona");
            return new Persona(sc.nextLine());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (PersonaNoEncontradaException ex) {
            System.out.println(ex.getMessage());
            return recuperarUsuario();
        }
        //No debería llegar aquí nunca.
        return null;
    }
        
      
            
        
    
}
