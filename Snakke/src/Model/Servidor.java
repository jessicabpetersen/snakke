/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para enviar dados ao servidor
 *
 * @author Jessica
 */
public class Servidor {

    private String endereco;
    private int porta;
    private static Servidor instance;
    Socket conexao = null;
    ObjectOutputStream oos;

    public static Servidor getInstance() {
        if (instance == null) {
            instance = new Servidor();
        }
        return instance;
    }

    public Servidor() {
            endereco = "10.60.185.47";
            porta = 56000;        
    }
    
    public String getEndereço() {
        return endereco;
    }

    public int getPorta() {
        return porta;
    }

    public Socket getConexao() {
        return conexao;
    }

    public boolean conectar() {
        try {
            conexao = new Socket(endereco, porta);
            oos = new ObjectOutputStream(conexao.getOutputStream());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean desconectar() {
        try {
            conexao.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public void enviarMsg(Mensagem msg){
        try {
          
            oos.writeObject(msg);
        } catch (IOException ex) {

        }
    }

    public void closeObjectOuto(){
        try {
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
