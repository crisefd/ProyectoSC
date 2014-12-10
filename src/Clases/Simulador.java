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
    private final int TIEMPOMAX = 1000;
    private Ascensor ascensor;
    public static int tEsperaTotal = 0;
    
    private Piso piso1, piso2, piso3, piso4, piso5, piso6;
    private LEF LEF;
    private int usuariosAbordados = 0, usuariosDesabordados = 0;
    
//    private int e=0;
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
            System.out.println("Tamano LEF: "+ LEF.size());
           // System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
            Evento ev = LEF.siguienteEvento();
            System.out.print("Evento atendido: ");
            System.out.print("tipo: "+ev.getTipo()+", ");
            System.out.print("oc: "+ev.getOc()+"\n");
            
            
            c++;
            ejecutarEvento(ev);

        }
        System.out.println(">>>>>>>>>>>>>>>> VARIABLES DE DESEMPEÑO <<<<<<<<<<<<<<<<<<<<<<<< ");
        System.out.println("Tiempo promedio de espera: "+ (double)tEsperaTotal / (double)numLlegadas);
        System.out.println("Tamano promedio de cola de piso 1: "+ piso1.calcularPromedioCola());
        System.out.println("Tamano promedio de cola de piso 2: "+ piso2.calcularPromedioCola());
        System.out.println("Tamano promedio de cola de piso 3: "+ piso3.calcularPromedioCola());
        System.out.println("Tamano promedio de cola de piso 4: "+ piso4.calcularPromedioCola());
        System.out.println("Tamano promedio de cola de piso 5: "+ piso5.calcularPromedioCola());
        System.out.println("Tamano promedio de cola de piso 6: "+ piso6.calcularPromedioCola());
        System.out.println("Porcentaje de atendidos: "+ calcularAtendidos()*100.0/(double)(numLlegadas - 1));
        System.out.println("Capacidad ocupada promedio: "+ ascensor.calcularCapacidadPromedio());
        System.out.println("Fin");
    }
    
    private int calcularAtendidos(){
        int res = 0;
        res += piso1.getNumAtendidos();
        res += piso2.getNumAtendidos();
        res += piso3.getNumAtendidos();
        res += piso4.getNumAtendidos();
        res += piso5.getNumAtendidos();
        res += piso6.getNumAtendidos();
        //System.out.println("Total atendidos: " + res);
       // System.out.println("Total que solicitaron: "+ numLlegadas);
        return res;
    }

    private void ejecutarEvento(Evento ev) {
        int oc = ev.getOc();
        String tipo = ev.getTipo();
        reloj = oc;
        if (tipo.equals("L")) {//llegada
            generarEventoLlegada();
            generarEventosSolicitud();
     
        } else {
            if (tipo.equals("S")) {//solicitudes
               
                ascensor.realizarSigParada();
               // System.out.println("")
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

        int oc = usuariosAbordados + Ascensor.tDesplazamiento * Math.abs(ascensor.getPisoActual() - 
                ascensor.getPisoAnterior()) + Ascensor.tArranque + usuariosDesabordados;
        LEF.agregarEvento(new Evento("P", oc + reloj));

    }

    private void abordar_desabordar(int piso) {
        System.out.println("Ascensor parando en piso "+piso);
        ascensor.actualizarPisoUsuarios();
        usuariosDesabordados = 0;
        usuariosAbordados = 0;
        
        switch (piso) {

            case 1:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso1);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
                break;
            case 2:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso2);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
                break;
            case 3:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso3);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
                break;
            case 4:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso4);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
                break;
            case 5:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso5);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
                break;
            case 6:
                usuariosDesabordados = ascensor.desabordarUsuarios();
                usuariosAbordados = ascensor.abordarUsuarios(piso6);
                System.out.println("Usuarios ascensor: "+ ascensor.getMontados());
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

           
            if (dir == ascensor.getDireccion() || ascensor.getDireccion() == 0) {
                //    piso1.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
                // System.out.println("A");
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


            if (dir == ascensor.getDireccion()|| ascensor.getDireccion() == 0) {
                //   piso2.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
                // System.out.println("B");
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


            if (dir == ascensor.getDireccion() || ascensor.getDireccion() == 0) {
                //  piso3.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
               //  System.out.println("C");
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


            if (dir == ascensor.getDireccion() || ascensor.getDireccion() == 0) {
                //  piso4.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
                // System.out.println("D");
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


            if (dir == ascensor.getDireccion() || ascensor.getDireccion() == 0) {
                // piso5.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
                // System.out.println("E");
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


            if (dir == ascensor.getDireccion() || ascensor.getDireccion() == 0) {
                //piso6.borrarUsuarioColaEspera(us);
                ascensor.agregarParada(pisoActual, pisoDeseado);
                LEF.agregarEvento(new Evento("S", reloj));
                 System.out.println("F");
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
            t = distribucion(0.04, numLlegadas / 100.0);
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
            t = distribucion(0.02, numLlegadas / 100.0);
        }
        // System.out.println("reloj: "+reloj+", t: "+ t);
         System.out.println("Llegada en piso: "+numPisoActual);
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
