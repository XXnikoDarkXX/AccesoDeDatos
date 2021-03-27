/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.com.main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author mparamos
 */
public abstract class ConexionBD {
    private static Connection conexion;
    
    protected static Statement conectarBD(){
        try {
            conexion=DriverManager.getConnection(Constantes.conexionBD,
                    Constantes.usuarioBD, Constantes.passwordBD);
            return conexion.createStatement();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; //Si falla la conexión, devuelve null
    }
    
    protected static void desconectarBD(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
