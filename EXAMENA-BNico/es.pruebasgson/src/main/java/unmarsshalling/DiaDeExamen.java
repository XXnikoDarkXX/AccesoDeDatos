/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unmarsshalling;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicoc
 */
@XmlRootElement
public class DiaDeExamen {
    private String dia;
    private String curso;
    private ArrayList<Examen>examenes;

    public DiaDeExamen(String dia, String curso, ArrayList<Examen> examenes) {
        this.dia = dia;
        this.curso = curso;
        this.examenes = examenes;
    }
    
    public DiaDeExamen(){
     examenes=new ArrayList<Examen>();
     
    }
    @XmlElement
    public String getDia() {
        return dia;
    }
@XmlElement
    public String getCurso() {
        return curso;
    }
@XmlElementWrapper()    
@XmlElement
    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setExamenes(ArrayList<Examen> examenes) {
        this.examenes = examenes;
    }

    @Override
    public String toString() {
        return "DiaDeExamen{" + "dia=" + dia + ", curso=" + curso + ", examenes=" + examenes + '}';
    }
    
    
    
}
