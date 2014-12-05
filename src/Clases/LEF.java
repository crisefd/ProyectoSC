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
        
         //System.out.println("ini size: " + size());
    }
    
    public void agregarEvento(Evento ev){
       // System.out.println("size antes de agregar: " + size());
        if(size() == 0){
          buscarInsertar(ev, 0, size());
        }else{
              buscarInsertar(ev, 0, size() - 1);
        }
        
    }
    
    public Evento siguienteEvento(){
       
            return remove(0);
       
    }
    
    private void buscarInsertar(Evento ev, int i, int f) {
        
         if(size() == 0){
            add(ev);
            return;
        }
        int tam = f - i + 1;
        int m = tam/2 - 1 + i;
          System.out.println("i = "+ i);
          System.out.println("m = "+ m);
          System.out.println("f = "+ f);
            //System.out.println("tam = "+ size());
       
        try{
            if (tam == 1){
                if (ev.getOc() < get(i).getOc()){
                    add(i, ev);
                }else{
                    add(ev);
                }
                return;
            }else{

                if(ev.getOc() < get(m).getOc()){
                    buscarInsertar(ev, i, m);
                }else{
                     buscarInsertar(ev, m + 1, f);
                }

            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("==================");
            System.out.println("i = "+ i);
            System.out.println("m = "+ m);
            System.out.println("f = "+ f);
            System.out.println("tam = "+ size());
            System.exit(1);
        }
        
        
    }
    
    public static void main(String[]args){
        LEF LEF = new LEF();
        
        int lim = 10;
        int i = 0;
        while(i< lim){
            int oc = (int)Math.round(Math.random()* 50);
            LEF.agregarEvento(new Evento("J", oc));
            
            i++;
        }
        
        for(Evento ev: LEF){
            System.out.println(""+ev.getOc());
        }
    }
    
}
