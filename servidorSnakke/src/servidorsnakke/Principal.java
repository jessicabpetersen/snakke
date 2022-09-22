package servidorsnakke;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author aparicio da silva
 */
public class Principal extends Thread {
    private List<TCPSocketThread> conectados;

    public Principal() {
        this.conectados = new ArrayList<TCPSocketThread>();
    }
    @Override
    public void run() {
        try {
            int porta = 56000;
            ServerSocket server = new ServerSocket(porta);
            server.setReuseAddress(true);
            while (true) {
                System.out.println("Aguardando conexao de cliente...");
                Socket conn = server.accept();
                TCPSocketThread socketThread = new TCPSocketThread(conn);
                socketThread.start();
                this.conectados.add(socketThread);
                System.out.println("conectado...");
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void atualizar(){
        for( TCPSocketThread x :conectados){
           if( x.getConn().isClosed()){
               x.atualizaOf();
               conectados.remove(x);
           }
               x.atualizaConta();
           
        }
        atualizar();
    }
    
}
