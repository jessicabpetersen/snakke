/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class Escuta extends Thread {

    private ServerSocket server;
    private Socket conexao = null;
    private static int porta = 56001;
    private ControllerApp controller;

    public Escuta(ControllerApp controller) throws IOException {
        server = new ServerSocket(porta);
        server.setReuseAddress(true);
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Aguardando conexão");
                conexao = server.accept();
                System.out.println("conexao: "+ conexao.getInetAddress().getHostAddress());
                
                TCPSocketThreadRecebimento tcpReceber = new TCPSocketThreadRecebimento(conexao, controller);
                tcpReceber.start();
            } catch (Exception e) {

            }
        }
    }
}
