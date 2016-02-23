
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
    ServerSocket server;
    Socket socket;
    int puerto = 8080;
    int numeroConexiones=2;
    DataOutputStream salida;

}
