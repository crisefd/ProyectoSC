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
    public static void main(String[]args){
        
    }
    
    
}
