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
    
    private int reloj = 0, numLlegadas = 0;
    private final int TIEMPOMAX = 500;
    private Ascensor ascensor;
    private Piso piso1, piso2, piso3, piso4, piso5, piso6;
    private LEF LEF;

    
    private int distribucionExponencial(double lambda, int x){
        return (int)Math.round(lambda* Math.exp(lambda*-1* x));
        
    }
    private int generarNumPisoSolicitado(int pisoActual){ 
        int pisoSolicitado = 0;
        
        
         pisoSolicitado = (int)Math.round(Math.random()*8);
         
         while(pisoSolicitado == pisoActual){
             pisoSolicitado = (int)Math.round(Math.random()*8);
         }
        
        if (pisoSolicitado <= 3){
            return 1;
        }
        if (pisoSolicitado == 4){
            return 2;
        }
        if (pisoSolicitado == 5){
            return 3;
        }
        if (pisoSolicitado == 6){
            return 4;
        }
        if (pisoSolicitado == 7){
            return 5;
        }
        if (pisoSolicitado == 8){
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
    
    private void generarLlegadas(){
        int numPisoActual;
        int veces = 25;
        int v=0;
        while(v <= veces){
            int t;
            numPisoActual = (int)Math.round(Math.random()*5) + 1;
            if(numPisoActual == 1){
                t = distribucionExponencial(0.0025, numLlegadas);
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                piso1.agregarUsuarioCola(new Usuario(1, numPisoSolicitado));
            }else{
                if (numPisoActual == 2){
                    int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                    piso2.agregarUsuarioCola(new Usuario(2, numPisoSolicitado));
                }
                if (numPisoActual == 3){
                    int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                    piso3.agregarUsuarioCola(new Usuario(3, numPisoSolicitado));
                }
                if (numPisoActual == 4){
                    int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                    piso4.agregarUsuarioCola(new Usuario(4, numPisoSolicitado));            
                }
                if (numPisoActual == 5){
                    int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                    piso5.agregarUsuarioCola(new Usuario(5, numPisoSolicitado));    
                }
                if (numPisoActual == 6){
                    int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                    piso6.agregarUsuarioCola(new Usuario(6, numPisoSolicitado));    
                }
                t = distribucionExponencial(0.002, numLlegadas);
            }
            
            LEF.agregarEvento(new Evento("L", reloj + t));
            numLlegadas++;
            numPisoActual++;
        }
    }
    
    private void direccionarAscensor(){
        
    }
    public static void main(String[]args){
        
    }
    
    
}
