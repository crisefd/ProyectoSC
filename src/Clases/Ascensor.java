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
    ArrayList<Piso> PasajerosSuben, PasajerosBajan, Destinos;

    public Ascensor(int pisoactual) {
        this.pisoActual = pisoactual;
        PasajerosSuben = new ArrayList<Piso>();
        PasajerosBajan = new ArrayList<Piso>();
        Destinos = new ArrayList<Piso>();
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

    public ArrayList<Piso> getDestinos() {
        return Destinos;
    }

    public void setDestinos(ArrayList<Piso> Destinos) {
        this.Destinos = Destinos;
    }

    public ArrayList<Piso> getPasajerosBajan() {
        return PasajerosBajan;
    }

    public void setPasajerosBajan(ArrayList<Piso> PasajerosBajan) {
        this.PasajerosBajan = PasajerosBajan;
    }

    public ArrayList<Piso> getPasajerosSuben() {
        return PasajerosSuben;
    }

    public void setPasajerosSuben(ArrayList<Piso> PasajerosSuben) {
        this.PasajerosSuben = PasajerosSuben;
    }
    
    public void recogerUsuarios(int usuarios){
      montados+=usuarios;
    }
    
    public void bajarUsuarios(int usuarios){
      montados-=usuarios;
    }
    
    public void direccionarse(int direccion){
        this.direccion=direccion;
    }
    
    public void cambiarPiso(){
        pisoActual+=direccion;
    }
}
