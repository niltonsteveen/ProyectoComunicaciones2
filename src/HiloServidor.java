
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nilton
 */
public class HiloServidor implements Runnable{
 //Declaramos las variables que utiliza el hilo para estar recibiendo y mandando mensajes
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean player;
    private int numMovimientos;
    private int[] movJugadores;
    private int posJugador;
    
    //Lista de los usuarios conectados al servidor
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    
    public HiloServidor(Socket soc,LinkedList users, int posJugador,int[] movJugadores){
        this.socket=soc;
        this.usuarios=users;
        this.movJugadores=movJugadores;
        this.posJugador=posJugador;
    }
    @Override
    public void run() {
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String jugador="";
            //jugador=jugador+"Eres el jugador: "+ (player ? "1;":"2;");
            jugador=jugador+"jugador: "+(posJugador);
            out.writeUTF(jugador);
            while(true){
                String movRecibido=in.readUTF();
                String recibido[]=movRecibido.split(";");
                /*
                    recibido[0]=numerodemovimientos
                    recibido[1]=estado del juego(false=aun no ha ganado//true=gano)
                    recibido[2]=numero del jugador
                */
                int movimientos=Integer.parseInt(recibido[0]);
                int otherUser=Integer.parseInt(recibido[2]);
                movJugadores[posJugador]=movimientos; 
                String mwol="";
                mwol+=posJugador+";";
                mwol+=movimientos+";";
                mwol+=otherUser+";";
                
                if(recibido[1].equals("true")){
                    
                    mwol+=otherUser+";";
                    
                }
                else if(recibido[1].equals("false")){
                    
                    mwol+="3";
                    
                }
                for (Socket usuario : usuarios) {
                    out = new DataOutputStream(usuario.getOutputStream());
                    out.writeUTF(mwol);
                }
            }
            
        }
        catch (Exception e) {
            for (int i = 0; i < usuarios.size(); i++) {
                if(usuarios.get(i) == socket){
                    usuarios.remove(i);
                    break;
                } 
            }
        }
    }   
}
