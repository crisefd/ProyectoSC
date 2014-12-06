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
    public static final int tDesplazamiento = 30, tArranque = 5;
    private boolean banderasSub[] = new boolean[6];
    private boolean banderasBaj[] = new boolean[6];
    private ListaParadas listaParadas;
    private int pisoAnterior;
    private ArrayList<Usuario> listaUsuariosMontados = new ArrayList<>();
    
    
    //private ArrayList<Integer> listaParadas = new ArrayList<>();

    public Ascensor(int pisoactual) {
        this.pisoActual = pisoactual;
        listaParadas = new ListaParadas();
    }
    
    public void realizarSigParada(){
        
        int dir = direccion;
        pisoAnterior = pisoActual;
        pisoActual = listaParadas.funcion();
        
        if (dir == 1){
            banderasSub[pisoActual - 1] = false;
        }else{
            if(dir == -1){
                banderasBaj[pisoActual - 1] = false;
            }
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

    public int getPisoactual() {
        return pisoActual;
    }

    public void setPisoactual(int pisoactual) {
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
    public void desabordarUsuarios(){
        int i = 0; int lim = listaUsuariosMontados.size();
        while(i < lim){
            if(listaUsuariosMontados.get(i).seBaja()){
                listaUsuariosMontados.remove(i);
                i--;
                lim--;
            }
            i++;
        }
    }
    public void abordarUsuarios(Piso piso){
        
        ArrayList<Usuario> cola = piso.getColaEspera();
        
        for(Usuario u: cola){
            u.subirAscensor(montados, capacidadMax, Simulador.reloj);
            listaUsuariosMontados.add(u);
            piso.borrarUsuarioColaEspera(u);
            
        }
    }
    
    
    public void agregarParada(int pisoActualUsuario, int pisoDeseadoUsuario){
        listaParadas.agregar( pisoActualUsuario,  pisoDeseadoUsuario);
    }
    
    
    private class ListaParadas {

        private Integer arreglo[] = {7, 7, 7, 7, 7, 7, -1, 0, 0, 0, 0, 0, 0};

       public int funcion(){
           if(direccion == 1){
               int i = 0;
               while(true){
                   if(arreglo[i] == -1){
                       invertirDireccion();
                       break;
                   }
                   if(arreglo[i] != 7 && arreglo[i] != 0){
                       int r = arreglo[i];
                       arreglo[i] = 0;
                       return r;
                   }
                   
               }
           }else{
               if(direccion == -1){
                   int i = 7;
                   while(true){
                       if(i == 13){
                           invertirDireccion();
                           break;
                       }
                       if(arreglo[i] != 7 && arreglo[i] != 0){
                           int r = arreglo[i];
                           arreglo[i] = 7;
                           return r;
                       }
                       
                   }
                   
               }
           }
           
           return 10;
       }
        private void agregarArr(int parada, int dire) {

            if (dire == 1) {
                for (int i = 0; i < 6; i++) {
                    if ((arreglo[i] == 7 || arreglo[i] == 0) && !banderasSub[i]) {
                        arreglo[i] = parada;
                        banderasSub[i] = true;
                        break;
                    }
                }
            } else {
                for (int i = 6; i < 13; i++) {
                    if ((arreglo[i] == 0 || arreglo[i] == 7) && !banderasBaj[i]) {
                        arreglo[i] = parada;
                        banderasBaj[i] = true;
                        break;
                    }
                }
            }
        }

        public void agregar(int pisoActualUsuario, int pisoDeseadoUsuario) {

            int dirUsuario;
            if (pisoActualUsuario - pisoDeseadoUsuario < 0) {
                dirUsuario = 1;//subida
            } else {
                dirUsuario = -1;//bajada
            }
            
            if (dirUsuario == direccion && direccion == 1 && pisoDeseadoUsuario > pisoActual) {
                Integer[] a = new Integer[6];
                agregarArr(pisoActualUsuario, 1);
                agregarArr(pisoDeseadoUsuario, 1);
                System.arraycopy(arreglo, 0, a, 0, 6);
                Arrays.sort(a);
                System.arraycopy(a, 0, arreglo, 0, 6);
            } else {
                if (dirUsuario == direccion && direccion == 1 && pisoDeseadoUsuario < pisoActual) {
                    Integer[] a = new Integer[6];
                    Integer[] b = new Integer[6];
                    agregarArr(pisoActualUsuario, -1);
                    agregarArr(pisoDeseadoUsuario, 1);
                    System.arraycopy(arreglo, 7, a, 7, 6);
                    Arrays.sort(a, Collections.reverseOrder());
                    System.arraycopy(a, 7, arreglo, 7, 6);

                    System.arraycopy(arreglo, 0, a, 0, 6);
                    Arrays.sort(b);
                    System.arraycopy(a, 0, arreglo, 0, 6);


                } else {
                    if (dirUsuario == direccion && direccion == -1 && pisoDeseadoUsuario < pisoActual) {
                        Integer[] a = new Integer[6];
                        agregarArr(pisoActualUsuario, -1);
                        agregarArr(pisoDeseadoUsuario, -1);

                        System.arraycopy(arreglo, 7, a, 7, 6);
                        Arrays.sort(a, Collections.reverseOrder());
                        System.arraycopy(a, 7, arreglo, 7, 6);


                    } else {

                        if (dirUsuario == direccion && direccion == -1 && pisoDeseadoUsuario > pisoActual) {
                            Integer[] a = new Integer[6];
                            Integer[] b = new Integer[6];
                            agregarArr(pisoActualUsuario, 1);
                            agregarArr(pisoDeseadoUsuario, -1);
                            System.arraycopy(arreglo, 7, a, 7, 6);
                            Arrays.sort(a, Collections.reverseOrder());
                            System.arraycopy(a, 7, arreglo, 7, 6);

                            System.arraycopy(arreglo, 0, a, 0, 6);
                            Arrays.sort(b);
                            System.arraycopy(a, 0, arreglo, 0, 6);


                        } else {
                            if (dirUsuario == 1 && direccion == -1) {
                                Integer[] a = new Integer[6];
                                agregarArr(pisoActualUsuario, 1);
                                agregarArr(pisoDeseadoUsuario, 1);
                                System.arraycopy(arreglo, 0, a, 0, 6);
                                Arrays.sort(a);
                                System.arraycopy(a, 0, arreglo, 0, 6);
                            } else {
                                if (dirUsuario == -1 && direccion == 1) {
                                    Integer[] a = new Integer[6];
                                    agregarArr(pisoActualUsuario, -1);
                                    agregarArr(pisoDeseadoUsuario, -1);

                                    System.arraycopy(arreglo, 7, a, 7, 6);
                                    Arrays.sort(a, Collections.reverseOrder());
                                    System.arraycopy(a, 7, arreglo, 7, 6);


                                }
                            }
                        }

                    }
                }
            }

        }
    }
}
