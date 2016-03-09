
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
    private int numeroConexiones=20;
    private LinkedList usuarios = new LinkedList();
    public void listen(){
        try {
            //Creamos el socket servidor
            ServerSocket servidor = new ServerSocket(puerto,numeroConexiones);
            //Ciclo infinito para estar escuchando por nuevos clientes
            while(true){
                System.out.println("Escuchando....");
                //Cuando un cliente se conecte guardamos el socket en nuestra lista
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                //Instanciamos un hilo que estara atendiendo al cliente y lo ponemos a escuchar
                /*Runnable  run = new HiloServidor(cliente,usuarios);
                Thread hilo = new Thread(run);
                hilo.start();*/
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
