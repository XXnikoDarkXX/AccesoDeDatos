package es.pruebasgson.main;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

public class Persona {
	private String nombre;
	private short anioNacimiento;
	private char genero;
	private String dni;
 final	private LocalDateTime fechaRegistro;
 final private Date fechaFalsa;
	private TreeMap<asignatura,Float>asignaturas;
	private HashSet<asignatura>convalidaciones;
	public enum asignatura{
	PMDM,
	AD,
	PSP,
	EIE,
	DI,
	SGE
	}
	public Persona(String nombre, short anioNacimiento, char genero, String dni) {
		super();
		this.nombre = nombre;
		this.anioNacimiento = anioNacimiento;
		this.genero = genero;
		this.dni = dni;
		fechaRegistro=LocalDateTime.now();
		fechaFalsa=Date.valueOf(fechaRegistro.toLocalDate());
	asignaturas=new TreeMap<asignatura,Float>();
	convalidaciones=new HashSet<asignatura>();
	}
	
	
	public Persona() {
		 String[] nombresHombres={"Raúl","Antonio","Francisco","Álvaro","Manuel","Alberto","Nico","Juanlu","Javier","Daniel","Norberto","Alex"};
	        String[] nombresMujer={"María","Cristina","Yuliia","Ana","Marta","Sandra","Laura","Esther","Isabel","Rocío","Carla","Noelia","Raquel"};
		Random r=new Random();
	if(r.nextBoolean()) {
		genero='h';
		nombre=nombresHombres[r.nextInt(nombresHombres.length)];
		
	}else {
		genero='m';
		nombre=nombresMujer[r.nextInt(nombresMujer.length)];
	}
	
	anioNacimiento=(short)(r.nextInt(80)+1940);
	dni="";
	for (int i = 0; i < 8; i++) {
		dni+=r.nextInt(10);
	}
	dni+=(char)(r.nextInt(25)+65);
	asignaturas=new TreeMap<asignatura,Float>();
	asignaturas.put(asignatura.PMDM, (float)r.nextInt(11));
	asignaturas.put(asignatura.AD, (float)r.nextInt(11));
	asignaturas.put(asignatura.PSP, (float)r.nextInt(11));
	asignaturas.put(asignatura.SGE, (float)r.nextInt(11));
	//asignaturas.put(asignatura.EIE, (float)r.nextInt(11));
	//asignaturas.put(asignatura.DI, (float)r.nextInt(11));
	
	convalidaciones=new HashSet<asignatura>();
	convalidaciones.add(asignatura.EIE);
	convalidaciones.add(asignatura.DI);
	fechaRegistro=LocalDateTime.now();
	fechaFalsa=Date.valueOf(fechaRegistro.toLocalDate());
}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public short getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(short anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
	
	
}
