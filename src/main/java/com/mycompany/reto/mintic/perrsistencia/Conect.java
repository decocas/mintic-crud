/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto.mintic.perrsistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class Conect {
    
        private final String URL="jdbc:sqlite:reto_5.db";

    private Connection con;

    public Conect() {
    
    concetar();
    }
    
    
    
    private void concetar(){
        con=null;
        
        try{
           con=DriverManager.getConnection(URL);
            if(con==null){
                System.out.println("error de conexion");
            }else{
                
                System.out.println(" conexion exitosa!!");
            }
                  
            
            
        }catch(SQLException e){
            System.err.println("ocurrio un erro en la conexion "+e.getMessage());
        }
       
        
    } 
    
    
    
    public void ejecutarQuery(String query){
        try{
            Statement stmt = con.createStatement();
            stmt.execute(query);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    
    public ResultSet ejecutarQueryRS(String query){
                  ResultSet rs=null;

        
        try{
            Statement stmt = con.createStatement();
            rs=stmt.executeQuery(query);

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
       
              return rs;

            
       
    
    }
    
    
    
    
    
    
}
