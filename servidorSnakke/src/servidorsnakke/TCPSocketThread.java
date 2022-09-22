package servidorsnakke;

import Model.Conta;
import Model.Mensagem;
import Model.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aparicio da silva
 */
public class TCPSocketThread extends Thread {

    private Socket conn;
    private Conta conta;
    DaoUsuario daouse = new DaoUsuario();
    DaoConta daocta = new DaoConta();

    public TCPSocketThread(Socket conn) {
        this.daouse = new DaoUsuario();
        this.daocta = new DaoConta();
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            InputStream io = conn.getInputStream();
        do{ 
            ObjectInputStream object = new ObjectInputStream(io);
            Mensagem msg = (Mensagem) object.readObject();            
            System.out.println("mensagem recebida ...");
            switch (msg.getAssunto()) {
                case 1:
                    cadastro(msg);
                    break;
                case 3:
                    login(msg);
                    break;
                case 4:
                    atualizaCadastro(msg);
                    break;
                case 5:
                    addContato(msg);
                    break;
                case 6:
                    removeContato(msg);
                    break;
            }
            }while(true);
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }

    private void cadastro(Mensagem msg) {
        Mensagem resposta = new Mensagem();
        this.conta = msg.getConta();
        this.conta.getProprietario().setOnline(true);
        resposta.setAssunto(1);
        resposta.setConta(this.conta);
        resposta.setMensagem("ok");
        //this.conta.getProprietario().setIp(this.conn.getLocalAddress().getHostAddress());
        this.daocta.salvar(this.conta);
        enviarmsg(resposta);

    }

    private void login(Mensagem msg) {
        Mensagem resposta = new Mensagem();
        this.conta = this.daocta.consultarPorConta(msg.getConta().getProprietario().getUsuario());
        if (this.conta.getSenha().equals(msg.getConta().getSenha())) {
            resposta.setAssunto(3);
            this.conta.getProprietario().setOnline(true);
            //this.conta.getProprietario().setIp(this.conn.getLocalAddress().getHostAddress());
            this.daocta.salvar(this.conta);
            resposta.setConta(this.conta);
            resposta.setMensagem("ok");
            enviarmsg(resposta);            
            System.out.println("logado ..");
        }else{
            resposta.setAssunto(3);
            resposta.setMensagem("erro");
            enviarmsg(resposta);
        }

    }

    private void atualizaCadastro(Mensagem msg) {
        this.daocta.salvar(msg.getConta());

    }

    private void addContato(Mensagem msg) {
        Usuario contato = this.daouse.consultarPorUsuario(msg.getMensagem());
        this.conta.addContato(contato);
        this.daocta.salvar(this.conta);        
    }

    private void removeContato(Mensagem msg) {
        Usuario contato = this.daouse.consultarPorUsuario(msg.getMensagem());
        this.conta.addContato(contato);
        this.daocta.salvar(this.conta);
    }

    private void enviarmsg(Mensagem msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream());
            oos.writeObject(msg);
            oos.close();
        } catch (IOException ex) {

        }
    }
    public void atualizaOf(){
        this.conta.getProprietario().setOnline(false);
        this.daocta.salvar(this.conta);
    }
    public void atualizaConta(){
        Mensagem resposta = new Mensagem();
        this.conta = this.daocta.consultarPorConta(this.conta .getProprietario().getUsuario());
            resposta.setAssunto(4);
            resposta.setConta(this.conta);
            resposta.setMensagem("ok");
            enviarmsg(resposta);            
            System.out.println("atualiza contatos ..");
    }
    public Socket getConn() {
        return conn;
    }

}
