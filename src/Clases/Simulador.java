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

    private static int distribucion(double lambda, double x) {
        return (int) Math.round((-1 / lambda) * Math.log(x));

    }

    private int generarNumPisoSolicitado(int pisoActual) {
        int pisoSolicitado = 0;


        pisoSolicitado = (int) Math.round(Math.random() * 8);

        while (pisoSolicitado == pisoActual) {
            pisoSolicitado = (int) Math.round(Math.random() * 8);
        }

        if (pisoSolicitado <= 3) {
            return 1;
        }
        if (pisoSolicitado == 4) {
            return 2;
        }
        if (pisoSolicitado == 5) {
            return 3;
        }
        if (pisoSolicitado == 6) {
            return 4;
        }
        if (pisoSolicitado == 7) {
            return 5;
        }
        if (pisoSolicitado == 8) {
            return 6;
        }
        return 0;


    }

    public void simular() {
        inicializarVariables();
        while (reloj <= TIEMPOMAX) {
            Evento ev = LEF.siguienteEvento();
            ejecutarEvento(ev);

        }
    }

    private void ejecutarEvento(Evento ev) {
        int oc = ev.getOc();
        String tipo = ev.getTipo();
        reloj = oc;
        if (tipo.equals("L")) {//legada
            generarLlegada();
            generarSolicitudes();

        } else {
            if (tipo.equals("A")) {//arranque
               
            } else {
                if (tipo.equals("P")) {//parada
                } else {
                    if (tipo.equals("V")) {//vaciado
                    }
                }
            }
        }



    }

    private void generarSolicitudes() {

        Usuario u ;
        int pisoDeseado;
        int pisoActual;
        
        
        u = piso1.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 1;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }


        u = piso2.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 2;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }
        u = piso3.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 3;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }



        u = piso4.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 4;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }


        u = piso5.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 5;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }


        u = piso6.siguenteUsuarioCola();
        if (u != null) {
            pisoDeseado = u.getPisoSolicitado();
            pisoActual = 6;
            ascensor.agregarParada(pisoActual, pisoDeseado);
            LEF.agregarEvento(new Evento("A", reloj));
        }

    }

    private void inicializarVariables() {
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

    private void generarLlegada() {
        int numPisoActual;
        //int veces = 100;
        //int v=0;
        //while(v < veces){
        int t;
        numPisoActual = (int) Math.round(Math.random() * 5) + 1;
        if (numPisoActual == 1) {
            t = distribucion(0.0025, numLlegadas / 100.0);
            int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
            Usuario u = new Usuario(1, numPisoSolicitado);
            u.setTi(reloj);
            piso1.agregarUsuarioCola(u);
        } else {
            if (numPisoActual == 2) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(2, numPisoSolicitado);
                u.setTi(reloj);
                piso2.agregarUsuarioCola(u);
            }
            if (numPisoActual == 3) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(3, numPisoSolicitado);
                u.setTi(reloj);
                piso3.agregarUsuarioCola(u);
            }
            if (numPisoActual == 4) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(4, numPisoSolicitado);
                u.setTi(reloj);
                piso4.agregarUsuarioCola(u);
            }
            if (numPisoActual == 5) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(5, numPisoSolicitado);
                u.setTi(reloj);
                piso5.agregarUsuarioCola(u);
            }
            if (numPisoActual == 6) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(6, numPisoSolicitado);
                u.setTi(reloj);
                piso6.agregarUsuarioCola(u);
            }
            t = distribucion(0.002, numLlegadas / 100.0);
        }

        LEF.agregarEvento(new Evento("L", reloj + t));
        reloj += t;
        numLlegadas++;
        numPisoActual++;
        //  }
    }

    public static void main(String[] args) {

        System.out.println(distribucion(0.0025, 1));

    }
}
