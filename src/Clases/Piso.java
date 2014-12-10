/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author crisefd
 */
public class Piso {
    
   private ArrayList <Usuario>colaEspera;
   private ArrayList <Integer> tamanosColaEspera;
   private int numRechazados = 0;
   private int numAtendidos = 0;
   final int  MAXCOLA=10;
   
   int numeroPiso;

   
   
   public Piso(int numeroPiso){
       colaEspera  = new ArrayList();
       tamanosColaEspera = new ArrayList();
       this.numeroPiso=numeroPiso;
   }
    public void agregarUsuarioCola(Usuario u){
        if(colaEspera.size()<=MAXCOLA){
            
            colaEspera.add(u);
            tamanosColaEspera.add(colaEspera.size()); 
            numAtendidos++;
            //System.out.println(">>>>>>> "+numAtendidos);
        }else{
            numRechazados++;
        }
        
    }
    
    public int getNumRechazados(){
        return numRechazados;
    }
    
    public int getNumAtendidos(){
        return numAtendidos;
    }
    
    public void borrarUsuarioColaEspera(Usuario u){
        colaEspera.remove(u);
    }
    
    public ArrayList<Usuario> getColaEspera(){
        System.out.println("Usuarios en la cola del piso "+numeroPiso+": "+colaEspera.size());
        return colaEspera;
    }
    public Usuario siguenteUsuarioCola(){
        Usuario s;
        try{
        s=colaEspera.remove(0);
        tamanosColaEspera.add(colaEspera.size());
         return s;
        }catch(Exception ex){
            return null;
        }
       
    }
    
    public int calcularPromedioCola(){
        int total=0;
        
        for(int n: tamanosColaEspera){
           total+= n;
        }
        int promedio = 0;
        try{
            promedio = (int)total/tamanosColaEspera.size();
        }catch(ArithmeticException ex){
            promedio = 0;
        }
        return promedio;
    }
   public int getNumeroPiso(){
       return numeroPiso;
   } 
   
   public void setNumeroPiso(int numeroPiso){
       this.numeroPiso=numeroPiso;
   }
}
