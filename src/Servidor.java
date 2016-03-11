
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nilton
 */
public class Servidor {
    private int puerto = 2027;
    private int numeroConexiones=2;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private int[] movJugadores=new int[2];
    private int posJugador = 1;
    public void listen(){
        try {
            for (int i = 0; i < 2; i++) {
                movJugadores[i]=-1;
            }
            //Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(puerto,numeroConexiones);
            //Ciclo infinito para estar escuchando por nuevos clientes
            while(true){
                System.out.println("Escuchando....");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                int pos = posJugador % 2 == 0 ? 1 : 0;
                posJugador++;
                //Instanciamos un hilo que estara atendiendo al cliente y lo ponemos a escuchar
                Runnable  run = new HiloServidor(cliente,usuarios,pos,movJugadores);
                Thread hilo = new Thread(run);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        Servidor servidor= new Servidor();
        servidor.listen();
    }
}
