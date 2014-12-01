/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author crisefd
 */
public class Evento { 
    private String tipo;
    private int ocurrencia;
    
    public Evento(String tipo, int ocurrencia){
        this.tipo = tipo;
        this.ocurrencia = ocurrencia;
              
    }
    public int getOc(){
        return ocurrencia;
    }
    public String getTipo(){
        return tipo;
    }
}
