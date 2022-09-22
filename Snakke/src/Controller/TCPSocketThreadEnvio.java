/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Mensagem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class TCPSocketThreadEnvio extends Thread {

    private Socket conexao;
    private Mensagem msg;
    private static int porta = 56001;
    ObjectOutputStream oos;

    public TCPSocketThreadEnvio(Mensagem msg, String endereço) throws IOException {
        System.out.println("tentando conectar");
        conexao = new Socket(endereço, porta);
        System.out.println("achou");
        oos = new ObjectOutputStream(conexao.getOutputStream());
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            oos.writeObject(msg);
            oos.close();
            conexao.close();
        } catch (IOException ex) {

        }
    }

}
