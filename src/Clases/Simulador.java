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
public class Simulador {

    public static int reloj = 0;
    private int numLlegadas = 1;
    private final int TIEMPOMAX = 20000;
    private Ascensor ascensor;
    private Piso piso1, piso2, piso3, piso4, piso5, piso6;
    private LEF LEF;
    private int e=0;
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
         System.out.println("Inicio");
         int c = 0;
        while (reloj <= TIEMPOMAX) {
            System.out.println("=============================\n iteracion: "+ c);
            Evento ev = LEF.siguienteEvento();
            System.out.print("Evento atendido: ");
            System.out.print("tipo: "+ev.getTipo()+", ");
            System.out.print("oc: "+ev.getOc()+"\n");
            
            
            c++;
            ejecutarEvento(ev);

        }
        System.out.println("Fin");
    }

    private void ejecutarEvento(Evento ev) {
        int oc = ev.getOc();
        String tipo = ev.getTipo();
        reloj = oc;
        if (tipo.equals("L")) {//llegada
            int a = (int)Math.round(Math.random());
                
            if(e==0){generarEventoLlegada();}
            e=1; 
            generarEventosSolicitud();
            
            

        } else {
            if (tipo.equals("S")) {//solicitudes
               
                ascensor.realizarSigParada();
                generarEventosParadas();

            } else {
                if (tipo.equals("P")) {//parada
                    abordar_desabordar(ascensor.getPisoActual());          
                    generarEventosParadas();

                } 
            }
        }



    }
    
    

    private void generarEventosParadas() {

        int oc = Ascensor.tDesplazamiento * Math.abs(ascensor.getPisoactual() - ascensor.getPisoAnterior()) + Ascensor.tDesplazamiento;
        LEF.agregarEvento(new Evento("P", oc + reloj));

    }

    private void abordar_desabordar(int piso) {
        ascensor.actualizarPisoUsuarios();
        switch (piso) {

            case 1:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso1);
                break;
            case 2:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso2);
                break;
            case 3:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso3);
                break;
            case 4:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso4);
                break;
            case 5:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso1);
                break;
            case 6:
                ascensor.desabordarUsuarios();
                ascensor.abordarUsuarios(piso1);
                break;

        }
    }

    private void generarEventosSolicitud() {

        Usuario u;
        int pisoDeseado;
        int pisoActual;
        ArrayList<Usuario> cola;

        cola = piso1.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 1;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                //    piso1.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

        }



        cola = piso2.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 2;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                //   piso2.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

        }

        cola = piso3.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 3;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                //  piso3.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

        }


        cola = piso4.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 4;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                //  piso4.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

        }


        cola = piso5.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 5;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                // piso5.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

        }

        cola = piso6.getColaEspera();
        for (Usuario us : cola) {
            pisoDeseado = us.getPisoSolicitado();
            pisoActual = 6;
            int dir;
            if (pisoDeseado - pisoActual < 0) {
                dir = -1;
            } else {
                dir = 1;
            }


            if (dir == ascensor.getDireccion()) {
                //piso6.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
            }

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

    private void generarEventoLlegada() {
        int numPisoActual;
        //int veces = 100;
        //int v=0;
        //while(v < veces){
       // System.out.println("XX");
            
        int t;
        numPisoActual = (int) Math.floor(Math.random() * 6) + 1;
        //System.out.println("XX: "+numPisoActual);
        if (numPisoActual == 1) {
            t = distribucion(0.0025, numLlegadas / 100.0);
            int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
            Usuario u = new Usuario(1, numPisoSolicitado);
            u.setTi(reloj);
            piso1.agregarUsuarioCola(u);
           // System.out.println("1");
        } else {
            if (numPisoActual == 2) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(2, numPisoSolicitado);
                u.setTi(reloj);
                piso2.agregarUsuarioCola(u);
                // System.out.println("2");
            }
            if (numPisoActual == 3) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(3, numPisoSolicitado);
                u.setTi(reloj);
                piso3.agregarUsuarioCola(u);
                // System.out.println("3");
            }
            if (numPisoActual == 4) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(4, numPisoSolicitado);
                u.setTi(reloj);
                piso4.agregarUsuarioCola(u);
                // System.out.println("4");
            }
            if (numPisoActual == 5) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(5, numPisoSolicitado);
                u.setTi(reloj);
                piso5.agregarUsuarioCola(u);
                // System.out.println("5");
            }
            if (numPisoActual == 6) {
                int numPisoSolicitado = generarNumPisoSolicitado(numPisoActual);
                Usuario u = new Usuario(6, numPisoSolicitado);
                u.setTi(reloj);
                piso6.agregarUsuarioCola(u);
               //  System.out.println("6");
            }
            t = distribucion(0.002, numLlegadas / 100.0);
        }
         System.out.println("reloj: "+reloj+", t: "+ t);
        LEF.agregarEvento(new Evento("L", reloj + t));
      //  System.out.println("YY");
        //reloj += t;
        numLlegadas++;
       // numPisoActual++;
        //  }
    }

    public static void main(String[] args) {
        new Simulador().simular();
        //System.out.println(""+ distribucion(0.0025, 1/100.0));
            
        

    }
}
