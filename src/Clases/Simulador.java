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
public class Simulador {
    
    private int reloj;
    private final int TIEMPOMAX = 500;
    private Ascensor ascensor;
    private Piso piso1, piso2, piso3, piso4, piso5, piso6;
    private LEF LEF;

    
    private int distribucionExponencial(double lambda, int x){
        return (int)Math.round(lambda* Math.exp(lambda*-1* x));
        
    }
    private int generarNumPiso(){ 
        int n = (int)Math.round(Math.random()*8);
        
        if (n <= 3){
            return 1;
        }
        if (n == 4){
            return 2;
        }
        if (n == 5){
            return 3;
        }
        if (n == 6){
            return 4;
        }
        if (n == 7){
            return 5;
        }
        if (n == 8){
            return 6;
        }
        return 0;
        

    }
    
    public void simular(){
        while(reloj <= TIEMPOMAX){
            
            
        }
    }
    
    private void inicializarVariables(){
        LEF = new LEF();
        reloj = 0;
        ascensor = new Ascensor(1);
        piso1 = new Piso(1);
        piso2 = new Piso(2);
        piso3 = new Piso(3);
        piso4 = new Piso(4);
        piso5 = new Piso(5);
        piso6 = new Piso(6);
        
    }
    
    private void generarEventos(){
        
    }
    public static void main(String[]args){
        
    }
    
    
}
