/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto.mintic.perrsistencia;

import com.mycompany.reto.mintic.controlador.Cotroller;
import com.mycompany.reto.mintic.modelo.Producto;
import com.mycompany.reto.mintic.vista.Vista;

/**
 *
 * @author Usuario
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            Vista vista= new Vista();
        Producto p=new Producto(); 
        Conect con=new Conect();
        Cotroller c=new Cotroller(con,vista,p);
         vista.setVisible(true);
//          new Vista().setVisible(true);

       //System.out.println(c.leer().toString());
/*       for(Producto pro:c.leer()){
        System.out.println(pro.toString());
   
       }
  */ 

//        System.out.println(c.leerID(0,"Jabón").getNombre());
        
        
            
        
       // Conect con =new Conect() ;
    }
    
}
