/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unmarsshalling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicoc
 */
@XmlRootElement
public class Examen {
    @XmlElement
    private String curso;
    @XmlElement
    private String asignatura;
    @XmlElement
    private String horaInicio;
    
    public Examen(){
        
    }

    public Examen(String curso, String asignatura, String horaInicio) {
        this.curso = curso;
        this.asignatura = asignatura;
        this.horaInicio = horaInicio;
    }
    
    
}
