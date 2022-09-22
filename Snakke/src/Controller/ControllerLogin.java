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
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jessica
 */
public class ControllerLogin implements ObservadoLogin {

    private List<ObservadorLogin> observadores = new ArrayList<>();
    Servidor servidor;
    Socket conexao = null;
    Conta conta;

    public ControllerLogin() {
        servidor = Servidor.getInstance();

    }

    public String esqueceuSenha(String email) {
        if (servidor.conectar()) {
            conexao = servidor.getConexao();
        }
        Mensagem msg = new Mensagem();
        msg.setAssunto(2);
        msg.getConta().setEmail(email);
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(conexao.getOutputStream());
            oos.writeObject(msg);
            oos.close();
        } catch (IOException ex) {
            return "Não foi possivel recuperar sua senha, tente novamente mais tarde";
        }

        servidor.desconectar();

        //receber msn  com login ou foi enviado p o email
        //email
        return "Sua mensagem foi enviada para seu email";
    }

    public boolean login(String usuario, String senha) {
//        testeLogin();
        ObjectOutputStream oos = null;
        InputStream imp = null;
        ObjectInputStream object = null;
        try {
            if (servidor.conectar()) {
                conexao = servidor.getConexao();
                imp = conexao.getInputStream();
            }
            ///ver como ENVIAR UM OBJETO
            Mensagem msg = new Mensagem();
            msg.setAssunto(3);
            Usuario a = new Usuario();
            a.setUsuario(usuario);
            Conta c = Conta.getInstance();
            c.setProprietario(a);
            msg.setConta(c);
            msg.getConta().setSenha(senha);
            servidor.enviarMsg(msg);
            //espera receber dados
            //se deu certo
            object = new ObjectInputStream(imp);
            Mensagem m = (Mensagem) object.readObject();
            if (m.getAssunto() == 3) {
                if (m.getMensagem().equals("ok")) {
                    c.setEmail(m.getConta().getEmail());
                    c.setSenha(m.getConta().getSenha());
                    c.setTelefone(m.getConta().getTelefone());
                    c.getProprietario().setNome(m.getConta().getProprietario().getNome());
                    c.getProprietario().setUsuario(m.getConta().getProprietario().getUsuario());
                    c.setContatos(m.getConta().getContatos());
                    c.getProprietario().setIp(InetAddress.getLocalHost().getHostAddress());
                    object.close();
                } else {
                    servidor.desconectar();
                    servidor.closeObjectOuto();
                    return false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            servidor.desconectar();
            return false;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            servidor.desconectar();
            return false;
        }
        return true;
    }

    public void testeLogin() {
        conta = Conta.getInstance();
    }

    @Override
    public void addObservador(ObservadorLogin obs) {
        observadores.add(obs);
    }

}
