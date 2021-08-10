/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto.mintic.controlador;

import com.mycompany.reto.mintic.modelo.Producto;
import com.mycompany.reto.mintic.perrsistencia.Conect;
import com.mycompany.reto.mintic.vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Cotroller implements IController, ActionListener { 
    
    private Conect conexion;
   private Vista ventana;
    private Producto modelo;
    public Cotroller(Conect conexion, Vista ventana, Producto modelo) {
        this.conexion = conexion;
        this.ventana = ventana;
        this.modelo = modelo; 
          DefaultTableModel tablaModel= (DefaultTableModel) this.ventana.tabla.getModel();
           
        this.ventana.btnSave.addActionListener(this);
        this.ventana.btn_edit.addActionListener(this);
        this.ventana.btn_eliminar.addActionListener(this);
        this.ventana.btn_read.addActionListener(this);
        this.ventana.btn_readAll.addActionListener(this);

                 tablaModel.setRowCount(0);
              System.out.println(this.leer());
            for (Producto p : this.leer()) {
        
              tablaModel.addRow(new Object[]{
                p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getCategoria()
                        });
        }               
            this.ventana.tabla.setModel(tablaModel);
        
    } 
    
    
    
    
    

    @Override
    public void agregar(String nombre, int cantidad, double precio, String categoria) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String sql="INSERT INTO productos (nombre,cantidad,precio,categoria)"
            + "VALUES(\""+nombre+"\", "+cantidad+", "+precio+",\""+categoria+"\");";
                System.out.println(sql);
     this.conexion.ejecutarQuery(sql);
    
    
    }

    @Override
    public void eliminar(int id) {
            String sql="DELETE FROM productos"
                    + " where id="+id+";";
            System.out.println(sql);
            this.conexion.ejecutarQuery(sql);

    }

    @Override
    public void update(String nombre, int cantidad, double precio, String categoria,int id){ 
        String sql="UPDATE productos SET nombre=\""+nombre+"\", cantidad="+cantidad+", precio="+precio+", categoria=\""+categoria+"\""
                + " WHERE id="+id+";";
         System.out.println(sql);
              
        this.conexion.ejecutarQuery(sql);
   
        
        }

    @Override
    public List<Producto> leer() {
String sql="SELECT * FROM productos";
List<Producto> lista=new ArrayList<>();
Producto producto=new Producto();
        ResultSet rs=this.conexion.ejecutarQueryRS(sql);
        try {
            while(rs.next()){
                producto.setId(Integer.parseInt(rs.getString("id")));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                producto.setPrecio(Double.parseDouble(rs.getString("precio")));
                producto.setCategoria(rs.getString("categoria"));

                lista.add(producto);
                producto=new Producto();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lista);
        return lista;
    }

    @Override
public Producto leerID(int id, String nombre) {
   Producto p=this.modelo;
String sql=null;   
ResultSet rs=null;
   if(id>0){
        sql="SELECT * FROM productos WHERE id="+id+";";

   }
   
        System.out.println(sql);
        
    rs=this.conexion.ejecutarQueryRS(sql);
   
        try {
            while(rs.next()){
                p.setId(Integer.parseInt(rs.getString("id")));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(Double.parseDouble(rs.getString("precio")));
                       }  
        } catch (SQLException ex) {
            Logger.getLogger(Cotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       DefaultTableModel tablaModel= (DefaultTableModel) this.ventana.tabla.getModel();
        
       if(e.getSource().equals(this.ventana.btn_readAll)){
//                  tablaModel.setRowCount(0);
                this.actualizarTabla();
              System.out.println(this.leer());
  /*          for (Producto p : this.leer()) {
        
              tablaModel.addRow(new Object[]{
                p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getCategoria()
                        });
        }               
            this.ventana.tabla.setModel(tablaModel);
 */     
       
       } else if(e.getSource().equals(this.ventana.btnSave)){
           this.agregar(this.ventana.nombre_registar.getText().trim(), Integer.parseInt(this.ventana.cantidad_registro.getText().trim()), Double.parseDouble(this.ventana.precio_registro.getText().trim()), this.ventana.lista_registrol.getSelectedItem().toString());
          this.actualizarTabla();
           this.ventana.nombre_registar.setText("");
           this.ventana.precio_registro.setText("");
           this.ventana.cantidad_registro.setText("");
          
       }else if(e.getSource().equals(ventana.btn_eliminar)){
           this.eliminar(Integer.parseInt(this.ventana.id_consulta.getText().trim()));
           this.actualizarTabla();
       }else if(e.getSource().equals(this.ventana.btn_edit)){
          this.update(this.ventana.nombre_consulta.getText().trim(), Integer.parseInt(this.ventana.cantidad_consulta.getText().trim()), Double.parseDouble(this.ventana.precio_consulta.getText().trim()), this.ventana.categoria_consulta.getSelectedItem().toString(),
                  Integer.parseInt(this.ventana.id_consulta.getText().trim()));
          actualizarTabla();
           this.ventana.nombre_consulta.setText("");
           this.ventana.cantidad_consulta.setText("");
           this.ventana.id_consulta.setText("");
           this.ventana.precio_consulta.setText("");
       }
       else if(e.getSource().equals(this.ventana.btn_read)){
        Producto p=  this.leerID(Integer.parseInt(this.ventana.id_consulta.getText()), this.ventana.nombre_consulta.getText());
                  tablaModel.setRowCount(0); 
                    tablaModel.addRow(new Object[]{
                p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getCategoria()
                        }); 
         this.ventana.tabla.setModel(tablaModel);

  
       }
         
       

      
    } 
    
    public void actualizarTabla(){ 
               DefaultTableModel tablaModel= (DefaultTableModel) this.ventana.tabla.getModel();

         tablaModel.setRowCount(0);
              System.out.println(this.leer());
            for (Producto p : this.leer()) {
        
              tablaModel.addRow(new Object[]{
                p.getId(),p.getNombre(),p.getCantidad(),p.getPrecio(),p.getCategoria()
                        });
        }               
            this.ventana.tabla.setModel(tablaModel);
      
    }
    
    
    
}
