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
        // TODO code application logic here4
        
      Conect c =new Conect();
      Producto p=new Producto();
      Vista vista=new Vista();
      
      Cotroller control=new Cotroller(c,vista,p);
      vista.setTitle("PROEJCT MINTIC CRUD");
      vista.setVisible(true);
            
        
      
}
    
}
