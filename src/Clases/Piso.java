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
    
   ArrayList <Usuario>colaEspera;
   ArrayList <Integer> tamanosColaEspera;
   final int  MAXCOLA=10;
   
   public Piso(){
       colaEspera  = new ArrayList();
       tamanosColaEspera = new ArrayList(); 
   }
    public void agregarUsuarioCola(Usuario u){
        if(colaEspera.size()<=MAXCOLA){
        colaEspera.add(u);
        tamanosColaEspera.add(colaEspera.size());    
        }
        
    }
    public Usuario siguenteUsuarioCola(){
        Usuario s;
        s=colaEspera.remove(0);
        tamanosColaEspera.add(colaEspera.size());
        return s;
    }
    
    public int calcularPromedioCola(){
        int total=0;
        
        for(int n: tamanosColaEspera){
           total+= n;
        }
        return (int) total/tamanosColaEspera.size();
    }
    
}
