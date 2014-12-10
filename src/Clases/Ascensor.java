/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ascensor {

    private int pisoActual, capacidadMax = 6, montados = 0;
    private int direccion = 0;
    ArrayList<Integer> variacionesMontados = new ArrayList();
    public static  int tDesplazamiento = 30, tArranque = 5;
    private boolean banderasSub[] = new boolean[6];
    private boolean banderasBaj[] = new boolean[6];
    private ListaParadas listaParadas;
    private int pisoAnterior;
    private ArrayList<Usuario> listaUsuariosMontados = new ArrayList<>();
    private int numParadas = 0;
    
    
    //private ArrayList<Integer> listaParadas = new ArrayList<>();

    public Ascensor(int pisoactual) {
        this.pisoActual = pisoactual;
        listaParadas = new ListaParadas();
    }
    
    public Ascensor(int pisoactual, int capacidadMax, int tArranque, int tDesplazamiento) {
        this.pisoActual = pisoactual;
        this.tArranque = tArranque;
        this.tDesplazamiento = tDesplazamiento;
        this.capacidadMax = capacidadMax;
        listaParadas = new ListaParadas();
    }
    
    public void realizarSigParada(){
        System.out.println("Direccion de la sig parada: "+direccion);
        int dir = direccion;
        pisoAnterior = pisoActual;
        pisoActual = listaParadas.recorrerParadas();
        System.out.println("Piso actual actualizado: "+pisoActual);
        try{
            if (dir == 1){
                banderasSub[pisoActual - 1] = false;
            }else{
                if(dir == -1){
                    banderasBaj[pisoActual - 1] = false;
                }
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println("piso actual cuando ocurrio ex: "+pisoActual);
            System.exit(1);
        }
        
    }
    
    public int getDireccion(){
        return direccion;
    }
    
    public int calcularEspacioDisponible(){
        return capacidadMax - montados;
    }
    
    public int getPisoAnterior(){
        return pisoAnterior;
    }
    
    public int getPisoActual(){
        return pisoActual;
    }
    
    public int getMontados(){
        return listaUsuariosMontados.size();
    }
    
    

//    public void agregarParada(int numPiso){
//        buscarInsertar(numPiso, 0, listaParadas.size() - 1);
//    }
    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

//    public int getPisoactual() {
//        return pisoActual;
//    }

    public void setPisoActual(int pisoactual) {
        this.pisoActual = pisoactual;
    }

   

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    private void abordarAux(int usuarios) {
        montados += usuarios;
        variacionesMontados.add(montados);

    }

    public void bajarUsuarios(int usuarios) {
        montados -= usuarios;
        variacionesMontados.add(montados);
    }

    public void direccionarse(int direccion) {
        this.direccion = direccion;
    }

    public void cambiarPiso() {
        pisoActual += direccion;
    }

    public int calcularPromedioMontados() {
        int total = 0;

        for (int n : variacionesMontados) {
            total += n;
        }
        return (int) total / variacionesMontados.size();
    }

    private void invertirDireccion(){
        if(direccion == -1){
            direccion = 1;
        }else{
            if(direccion == 1){
                direccion = -1;
            }
        }
    }
    public void actualizarPisoUsuarios(){
        for (int i = 0; i < listaUsuariosMontados.size(); i++) {
            listaUsuariosMontados.get(i).setPisoActual(pisoActual);
        }
    }
    public int desabordarUsuarios(){
        int i = 0; int lim = listaUsuariosMontados.size();
        while(i < lim){
            if(listaUsuariosMontados.get(i).seBaja()){
                listaUsuariosMontados.remove(i);
                variacionesMontados.add(listaUsuariosMontados.size());
                i--;
                lim--;
            }
            i++;
        }
        
        return listaUsuariosMontados.size();
        
    }
    public double calcularCapacidadPromedio(){
        double res = 0;
        for(Integer n: variacionesMontados){
            res += n;
            
        }
        return res/variacionesMontados.size();
    }
    public int abordarUsuarios(Piso piso){
        
        ArrayList<Usuario> cola = piso.getColaEspera();
        
        for(int i = 0; i< cola.size(); i++){
            Usuario u = cola.get(i);
            //montados++;
           
            boolean subio = u.subirAscensor(listaUsuariosMontados.size(), 
                              capacidadMax, Simulador.reloj);
            if (subio){
                listaUsuariosMontados.add(u);
                variacionesMontados.add(listaUsuariosMontados.size());
                piso.borrarUsuarioColaEspera(u);
            }
            
             
            numParadas--;
            //System.out.println("Usuarios ascensor: "+ getMontados());
           
            
        }
        if(numParadas == 0){
            direccion = 0;
        }
        
        return listaUsuariosMontados.size();
    }
    
    
    public void agregarParada(int pisoActualUsuario, int pisoDeseadoUsuario){
        numParadas++;
        listaParadas.agregar( pisoActualUsuario,  pisoDeseadoUsuario);
    }
    
    
    private class ListaParadas {

        private Integer paradas[] = {7, 7, 7, 7, 7, 7, -1, 0, 0, 0, 0, 0, 0};

       public int recorrerParadas(){
           if(direccion == 1){
               int i = 0;
               while(true){
                   if(paradas[i] == -1){
                       invertirDireccion();
                       break;
                   }
                   if(paradas[i] < 7 && paradas[i] > 0){
                       int r = paradas[i];
                       paradas[i] = 0;
                       return r;
                   }
                   System.out.println("bucle 1");
                   i++;
               }
           }else{
               if(direccion == -1){
                   int i = 7;
                   while(true){
                       if(i == 13){
                           invertirDireccion();
                           break;
                       }
                       if(paradas[i] < 7 && paradas[i] > 0){
                           int r = paradas[i];
                           paradas[i] = 7;
                           return r;
                       }
                       i++;
                        System.out.println("bucle 2");
                   }
                   
               }
           }
           
           return pisoActual;
       }
        private void agregarArr(int parada, int dire) {

            if (dire == 1) {
                for (int i = 0; i < 6; i++) {
                    if ((paradas[i] == 7 || paradas[i] == 0) && !banderasSub[i]) {
                        paradas[i] = parada;
                        banderasSub[i] = true;
                        break;
                    }
                }
            } else {
                for (int i = 7; i < 13; i++) {
                    if ((paradas[i] == 0 || paradas[i] == 7) && !banderasBaj[i - 7]) {
                        paradas[i] = parada;
                        banderasBaj[i - 7] = true;
                        break;
                    }
                }
            }
        }

        public void agregar(int pisoActualUsuario, int pisoDeseadoUsuario) {

            int dirUsuario;
            if (pisoActualUsuario - pisoDeseadoUsuario < 0) {
                dirUsuario = 1;//subida
                if (direccion == 0){
                    direccion = dirUsuario;
                }
            } else {
                dirUsuario = -1;//bajada
                if (direccion == 0){
                    direccion = dirUsuario;
                }
            }
            
            if (dirUsuario == direccion && direccion == 1 && pisoDeseadoUsuario > pisoActual) {
                Integer[] a = new Integer[6];
                agregarArr(pisoActualUsuario, 1);
                agregarArr(pisoDeseadoUsuario, 1);
                System.arraycopy(paradas, 0, a, 0, 6);
                Arrays.sort(a);
                System.arraycopy(a, 0, paradas, 0, 6);
            } else {
                if (dirUsuario == direccion && direccion == 1 && pisoDeseadoUsuario < pisoActual) {
                    Integer[] a = new Integer[6];
                    Integer[] b = new Integer[6];
                    agregarArr(pisoActualUsuario, -1);
                    agregarArr(pisoDeseadoUsuario, 1);
                    System.arraycopy(paradas, 7, b, 0, 6);
                    Arrays.sort(b, Collections.reverseOrder());
                    System.arraycopy(b, 0, paradas, 7, 6);

                    System.arraycopy(paradas, 0, a, 0, 6);
                    Arrays.sort(a);
                    System.arraycopy(a, 0, paradas, 0, 6);


                } else {
                    if (dirUsuario == direccion && direccion == -1 && pisoDeseadoUsuario < pisoActual) {
                        Integer[] b = new Integer[6];
                        agregarArr(pisoActualUsuario, -1);
                        agregarArr(pisoDeseadoUsuario, -1);

                        System.arraycopy(paradas, 7, b, 0, 6);
                        Arrays.sort(b, Collections.reverseOrder());
                        System.arraycopy(b, 0, paradas, 7, 6);


                    } else {

                        if (dirUsuario == direccion && direccion == -1 && pisoDeseadoUsuario > pisoActual) {
                            Integer[] a = new Integer[6];
                            Integer[] b = new Integer[6];
                            agregarArr(pisoActualUsuario, 1);
                            agregarArr(pisoDeseadoUsuario, -1);
                            System.arraycopy(paradas, 0, b, 0, 6);
                            Arrays.sort(b, Collections.reverseOrder());
                            System.arraycopy(b, 0, paradas, 7, 6);

                            System.arraycopy(paradas, 0, a, 0, 6);
                            Arrays.sort(a);
                            System.arraycopy(a, 0, paradas, 0, 6);


                        } else {
                            if (dirUsuario == 1 && direccion == -1) {
                                Integer[] a = new Integer[6];
                                agregarArr(pisoActualUsuario, 1);
                                agregarArr(pisoDeseadoUsuario, 1);
                                System.arraycopy(paradas, 0, a, 0, 6);
                                Arrays.sort(a);
                                System.arraycopy(a, 0, paradas, 0, 6);
                            } else {
                                if (dirUsuario == -1 && direccion == 1) {
                                    Integer[] b = new Integer[6];
                                    agregarArr(pisoActualUsuario, -1);
                                    agregarArr(pisoDeseadoUsuario, -1);

                                    System.arraycopy(paradas, 7, b, 0, 6);
                                    Arrays.sort(b, Collections.reverseOrder());
                                    System.arraycopy(b, 0, paradas, 7, 6);


                                }
                            }
                        }

                    }
                }
            }

        }
    }
}
