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
    
    public void agregar(Evento ev){
        
        
    }
    
    private int searchPos(Evento ev, int i, int f){
        int pos = 0;
        int mid = (int)this.size()/2 - 1;
        
        if((mid + 1)*2 == 3){
            
        }
        
            if (ev.ocurrencia<this.get(mid).ocurrencia){
                 
            }
        
        
        return pos;
    }
    
}
