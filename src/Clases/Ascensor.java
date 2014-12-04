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

    private int pisoActual, capacidadMax = 6, montados;
    private int direccion;
    ArrayList<Integer> listaMontados = new ArrayList();
    public static int tDesplazamiento = 30, tArranque = 5;
    private boolean banderasSub[] = new boolean[6];
    private boolean banderasBaj[] = new boolean[6];
    private ListaParadas listaParadas;
    //private ArrayList<Integer> listaParadas = new ArrayList<>();

    public Ascensor(int pisoactual) {
        this.pisoActual = pisoactual;
        listaParadas = new ListaParadas();
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

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void recogerUsuarios(int usuarios) {
        montados += usuarios;
        listaMontados.add(montados);

    }

    public void bajarUsuarios(int usuarios) {
        montados -= usuarios;
        listaMontados.add(montados);
    }

    public void direccionarse(int direccion) {
        this.direccion = direccion;
    }

    public void cambiarPiso() {
        pisoActual += direccion;
    }

    public int calcularPromedioMontados() {
        int total = 0;

        for (int n : listaMontados) {
            total += n;
        }
        return (int) total / listaMontados.size();
    }

//    private int buscarInsertar(int numPiso, int i, int f){
//        int tam = f - i + 1;
//        int m = tam/2 - 1 + i;
//        if (tam == 1){
//            if (numPiso < listaParadas.get(i)){
//                listaParadas.add(i, numPiso);
//            }else{
//                listaParadas.add(numPiso);
//            }
//            return 0;
//        }else{
//            
//            if(numPiso < listaParadas.get(m)){
//                return buscarInsertar(numPiso, i, m);
//            }else{
//                return buscarInsertar(numPiso, m + 1, f);
//            }
//            
//        }
    // }
    
    public void agregarParada(int pisoActualUsuario, int pisoDeseadoUsuario){
        listaParadas.agregar( pisoActualUsuario,  pisoDeseadoUsuario);
    }
    private class ListaParadas {

        private Integer arreglo[] = {7, 7, 7, 7, 7, 7, -1, 0, 0, 0, 0, 0, 0};

        public ListaParadas() {
            //arreglo[6] = -1;
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
                    Arrays.sort(a);
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
                        Arrays.sort(a);
                        System.arraycopy(a, 7, arreglo, 7, 6);


                    } else {

                        if (dirUsuario == direccion && direccion == -1 && pisoDeseadoUsuario > pisoActual) {
                            Integer[] a = new Integer[6];
                            Integer[] b = new Integer[6];
                            agregarArr(pisoActualUsuario, 1);
                            agregarArr(pisoDeseadoUsuario, -1);
                            System.arraycopy(arreglo, 7, a, 7, 6);
                            Arrays.sort(a);
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
                                    Arrays.sort(a);
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
