/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.Mensagem;
import Model.Servidor;
import Model.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class ControllerCadastro implements ObservadoCadastro {

    private List<ObservadorCadastro> observadores = new ArrayList<>();
    Socket conexao = null;
    Servidor servidor;

    @Override
    public void addObservador(ObservadorCadastro obs) {
        this.observadores.add(obs);
    }

    public ControllerCadastro() {
        servidor = Servidor.getInstance();
    }

    @Override
    public void voltar() {
        for (ObservadorCadastro obs : observadores) {
            obs.voltar();
        }
    }

    @Override
    public boolean cadastro(String nome, String usuario, String email, String senha, String telefone) {
        ObjectOutputStream oos = null;
        InputStream io = null;
        ObjectInputStream object = null;
        try {
            if (servidor.conectar()) {
                conexao = servidor.getConexao();
                oos = new ObjectOutputStream(conexao.getOutputStream());
                io = conexao.getInputStream();
            }
            ///ver como ENVIAR UM OBJETO
            Mensagem msg = new Mensagem();
            msg.setAssunto(1);
            Conta a = Conta.getInstance();
            a.setEmail(email);
            a.setSenha(senha);
            a.setTelefone(telefone);
            Usuario usu = new Usuario();
            usu.setUsuario(usuario);
            usu.setNome(nome);
            a.setProprietario(usu);
            msg.setConta(a);
            oos.writeObject(msg);
            
            object = new ObjectInputStream(io);
            Mensagem msgok = (Mensagem) object.readObject();
            if (msgok.getAssunto() == 1) {
                if (msgok.getMensagem().contains("ok")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
