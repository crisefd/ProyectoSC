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
public class LEF extends ArrayList<Evento>{
    
    public LEF(){
        
        add(new Evento("L", 0));
    }
    
    public void agregarEvento(Evento ev){
        buscarInsertar(ev, 0, size() - 1);
        
    }
    
    public Evento siguienteEvento(){
        return remove(0);
    }
    
    private int buscarInsertar(Evento ev, int i, int f){
        int tam = f - i + 1;
        int m = tam/2 - 1 + i;
        if (tam == 1){
            if (ev.getOc() < get(i).getOc()){
                add(i, ev);
            }else{
                add(ev);
            }
            return 0;
        }else{
            
            if(ev.getOc() < get(m).getOc()){
                return buscarInsertar(ev, i, m);
            }else{
                return buscarInsertar(ev, m + 1, f);
            }
            
        }
        
        
    }
    
//    public static void main(String[]args){
//        LEF LEF = new LEF();
//        LEF.add(new Evento("H", 10));
//        LEF.add(new Evento("H", 13));
//        LEF.add(new Evento("H", 20));
//        
//        LEF.buscar_insertar(new Evento("H", 12), 0, 3);
//        
//        for(Evento ev: LEF){
//            System.out.println(""+ev.getOc());
//        }
//    }
    
}
