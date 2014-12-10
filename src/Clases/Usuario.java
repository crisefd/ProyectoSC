/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Shaitan
 * 
 */
public class Usuario {
    
    private int pisoActual, pisoSolicitado;
    private int ti, tiempoEsperaCola;
    public static final int latencia = 1;
    private boolean estado; //true en el ascensor, false en la cola o desabordo.
    
    
    public boolean subirAscensor(int montados, int capacidadMax, int tf){
        
        if(montados < capacidadMax){
            tiempoEsperaCola = tf- ti;
            Simulador.tEsperaTotal += tiempoEsperaCola;
            estado = true;
        }else{
            estado = false;
        }
        //verifica la capcidad del ascensor y si la gente montada es menor aborda de lo contrario espera.
       return  estado;
        
    }
    private void bajarAscensor(){
        //verifica el piso actual si es el solicitado desaborda de lo contrario espera en ascensor.
        estado= pisoActual!=pisoSolicitado;
    }
    
    public boolean seBaja(){
        if(pisoActual== pisoSolicitado){
            return true;
        }
        return false;
    }
    private void solicitarAscensor(){
       // Simulador.recibirSolicitud(pisoSolicitado);
    }
    public void setPisoActual(int pisoActualIN){
        pisoActual=pisoActualIN;
    }
    public int getPisoSolicitado(){
        return pisoSolicitado;
    }
    public boolean getEstado(){
        return estado;
    }
    
    public Usuario(int pa, int ps){
        pisoActual = pa;
        pisoSolicitado = ps;
        
    }
    
    public Usuario(int ti){
        this.ti = ti;
    }
   
    public void setTi(int ti){
        this.ti = ti;
    }
    
    
}