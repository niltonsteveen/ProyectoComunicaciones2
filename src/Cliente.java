
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nilton
 */
public class Cliente implements Runnable{
    private Socket cliente;
    private DataOutputStream out;
    private DataInputStream in;
    private int puerto=2027;
    private String host = "localhost";
    
    private puzzleMain pM;
    private String mensaje;
    public Cliente(puzzleMain pM){
        try{
            this.pM=pM;
            cliente = new Socket(host,puerto);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{
            mensaje=in.readUTF();
            
            String player=mensaje.split(" ")[1];//numero del jugador
            pM.setNombreJugador(player);
            
            while(true){
                mensaje=in.readUTF();
                /*
                mensaje[0]:posjugador;
                mesnaje[1]:#movimientos
                mensaje[2]:numero del jugador si gano o "NADIE" si nadie ha ganado
            */
                String mensajes[]=mensaje.split(";");
                int mov=Integer.parseInt(mensajes[1]);
                String estado=mensajes[3];
                if(!player.equals(mensajes[2])){
                    pM.setJugadasContrincante(mov);
                }
                if(player.equals(estado)){
                    JOptionPane.showMessageDialog(pM, "GANASTEEEEEE!");
                }
                else if(!player.equals(estado)&&!estado.equals("3")){
                    JOptionPane.showMessageDialog(pM, "PERDISTE BUUUUU!");
                } 
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void enviarWin(boolean win, int cont, int posJugador) {
        try {
                String  datos = "";
                datos += cont + ";";
                datos += win + ";";
                datos+=posJugador+";";
                out.writeUTF(datos);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
