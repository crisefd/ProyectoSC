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
    public static int latencia = 1;
    private boolean Estado; //true en el ascensor, false en la cola o desabordo.
    
    
    private void subirAscensor(int montados, int capacidadMax){
        
        //verifica la capcidad del ascensor y si la gente montada es menor aborda de lo contrario espera.
        Estado = montados<capacidadMax;
    }
    private void bajarAscensor(){
        //verifica el piso actual si es el solicitado desaborda de lo contrario espera en ascensor.
        Estado= pisoActual!=pisoSolicitado;
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
        return Estado;
    }
    
    public Usuario(int pa, int ps){
        pisoActual = pa;
        pisoSolicitado = ps;
        
    }
}