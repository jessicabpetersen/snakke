/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.Mensagem;
import Model.Servidor;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class ThreadServerRecebimento extends Thread {

    private static int porta = 56000;
    private Servidor servidor;
    private InputStream io;
    private ControllerApp controller;
    private Conta conta;
    private ObjectInputStream object;

    public ThreadServerRecebimento(Servidor servidor, ControllerApp controller) {
        this.controller = controller;
        this.servidor = servidor;
        conta = Conta.getInstance();
        try {
            io = servidor.getConexao().getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(ThreadServerRecebimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                object = new ObjectInputStream(io);
                Mensagem msg = (Mensagem) object.readObject();
                switch (msg.getAssunto()) {
                    case 4:
                        atualizarListaContatos(msg);
                        break;
                    case 5:
                        msgAddContato(msg);
                        break;
                    case 6:
                        msgRemoveContato(msg);
                        break;
                    case 7:
                        msgAtualizacaoDados(msg);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadServerRecebimento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServerRecebimento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void msgAtualizacaoDados(Mensagem msg){
        if(msg.getMensagem().equals("ok")){
            conta = msg.getConta();
            controller.atualizarNomeUsuario();
            msgTela("Seus dados foram atualizados com sucesso");
        }else{
            msgTela("Falha ao atualizar seus dados");
        }
    }
    
    public void msgAddContato(Mensagem msg){
        if(msg.getMensagem().equals("ok")){
            conta = msg.getConta();
            msgTela("Usuário adicionado aos contatos com sucesso");
        }else{
            msgTela("Falha ao adicionar usuário");
        }
    }
    
    public void msgRemoveContato(Mensagem msg){
        if(msg.getMensagem().equals("ok")){
            conta = msg.getConta();
            msgTela("Usuário removido com sucesso");
        }else{
            msgTela("Falha ao remover usuário");
        }
    }
    
    public void atualizarListaContatos(Mensagem msg) {
        conta = msg.getConta();
        controller.atualizarListas(msg.getConta().getContatos());
    }
    
    public void msgTela(String msg){
        controller.msgTela(msg);
    }

}
