/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

public class Ascensor {

    private int pisoActual, capacidadMax, montados;
    private int direccion;
    ArrayList<Integer> listaMontados= new ArrayList();

    public Ascensor(int pisoactual) {
        this.pisoActual = pisoactual;
    }
    

    
    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getPisoactual() {
        return pisoActual;
    }

    public void setPisoactual(int pisoactual) {
        this.pisoActual = pisoactual;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void recogerUsuarios(int usuarios){
        montados+=usuarios;
        listaMontados.add(montados);
    
    }
    
    public void bajarUsuarios(int usuarios){
      montados-=usuarios;
      listaMontados.add(montados);
    }
    
    public void direccionarse(int direccion){
        this.direccion=direccion;
    }
    
    public void cambiarPiso(){
        pisoActual+=direccion;
    }
    
    public int calcularPromedioMontados(){
        int total=0;
        
        for(int n: listaMontados){
           total+= n;
        }
        return (int) total/listaMontados.size();
    }
}
