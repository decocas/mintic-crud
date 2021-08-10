/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto.mintic.controlador;

import com.mycompany.reto.mintic.modelo.Producto;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IController {
    public void agregar(String nombre, int cantidad, double precio, String categoria);
    
    
    public void eliminar(int id );  
    
    
    public void update(String nombre, int cantidad, double precio, String categoria,int id);
    
    public List<Producto> leer();
     
    public Producto leerID(int id, String nombre);
        
    
}
