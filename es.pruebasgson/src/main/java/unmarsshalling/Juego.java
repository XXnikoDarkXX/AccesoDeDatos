package unmarsshalling;

import java.util.HashSet;

public class Juego {
	private String titulo;
	private short anio;
	private HashSet <String>plataformasEnQueLoTengo;
	
	
	
	public Juego(String titulo, short anio, HashSet<String> plataformasEnQueLoTengo) {
		
		this.titulo = titulo;
		this.anio = anio;
		this.plataformasEnQueLoTengo = plataformasEnQueLoTengo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public short getAnio() {
		return anio;
	}


	public void setAnio(short anio) {
		this.anio = anio;
	}


	public HashSet<String> getPlataformaEnQueLoTengo() {
		return plataformasEnQueLoTengo;
	}


	public void setPlataformaEnQueLoTengo(HashSet<String> plataformasEnQueLoTengo) {
		this.plataformasEnQueLoTengo = plataformasEnQueLoTengo;
	}


	@Override
	public String toString() {
		return "Juego [titulo=" + titulo + ", anio=" + anio + ", plataformasEnQueLoTengo=" + plataformasEnQueLoTengo
				+ "]";
	}
	
	
	
	
}
