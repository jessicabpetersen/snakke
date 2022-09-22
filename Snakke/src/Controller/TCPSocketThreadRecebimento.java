/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Arquivo;
import Model.Mensagem;
import Model.Usuario;
import View.Audio;
import View.Conversa;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class TCPSocketThreadRecebimento extends Thread {

    private Socket conexao;
    private ControllerApp controller;
    private Usuario usuario;
    private String[] imagens = {"jpg", "jpeg", "png"};
    private String[] texto = {"doc", "docx", "pdf", "xls", "txt"};

    public TCPSocketThreadRecebimento(Socket con, ControllerApp controller) {
        this.conexao = con;
        this.controller = controller;
        System.out.println("recebimento construtor");
    }

    @Override
    public void run() {
        try {
            InputStream io = conexao.getInputStream();
            ObjectInputStream object = new ObjectInputStream(io);
            Mensagem msg = (Mensagem) object.readObject();
            switch (msg.getAssunto()) {
                case 8:
                    msgTexto(msg);
                    break;
                case 9:
                    arquivo(msg, io);
                    break;
                case 10:
                    audio(msg);
                    break;
            }
            object.close();
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPSocketThreadRecebimento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPSocketThreadRecebimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void msgTexto(Mensagem msg) {
        String acrescentar = msg.getConta().getProprietario().getNome() + ": \n" + msg.getMensagem();
        boolean contatoAbberto = false;
        //janela ainda não estava aberta aberta
        if (!verificaJanelaAberta(msg)) {
            controller.listaOnClicada(msg.getConta().getProprietario().getNome());
            msgTexto(msg);
        } else {
            Conversa conversa = controller.getJanelaAberta(usuario.getUsuario());
            conversa.getjTextAreaConversa().append(acrescentar + "\n");
        }

    }

    public boolean verificaJanelaAberta(Mensagem msg) {
        for (Usuario usu : controller.getContatosAbertos()) {
            if (msg.getConta().getProprietario().getNome().equals(usu.getNome())) {
                this.usuario = usu;
                return true;
            }
        }
        return false;
    }

    public void arquivo(Mensagem msg, InputStream io) {
        String diretorio = System.getProperty("user.home") + "\\Downloads" + "\\" + msg.getArquivo().getNome();
        try {
            FileOutputStream fos = new FileOutputStream(diretorio);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int bytesRead = io.read(msg.getArquivo().getConteudo(), 0, msg.getArquivo().getConteudo().length);

            int current = bytesRead;

            do {
                bytesRead = io.read(msg.getArquivo().getConteudo(), current, (msg.getArquivo().getConteudo().length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            } while (bytesRead > -1);

            fos.write(msg.getArquivo().getConteudo());
            fos.close();
        } catch (SocketException se) {

        } catch (IOException ioe) {

        }

        msgNaTela(diretorio, msg);
    }

    public void msgNaTela(String diretorio, Mensagem msg) {
        if (!verificaJanelaAberta(msg)) {
            controller.listaOnClicada(msg.getConta().getProprietario().getNome());
            msgNaTela(diretorio, msg);
        } else {
            boolean algum = false;
            Conversa conversa = controller.getJanelaAberta(usuario.getUsuario());
            for (String imagen : imagens) {
                if (diretorio.contains(imagen)) {
                    conversa.getjTextAreaConversa().append(msg.getConta().getProprietario().getNome() + " enviou uma imagem:\n Local: " + diretorio + "\n");
                    algum = true;
                    break;
                }
            }
            for (String arquivo : texto) {
                if (diretorio.contains(arquivo)) {
                    conversa.getjTextAreaConversa().append(msg.getConta().getProprietario().getNome() + " envidou um arquivo de texto:\n Local: " + diretorio + "\n");
                    algum = true;
                    break;
                }
            }
            if (!algum) {
                conversa.getjTextAreaConversa().append(msg.getConta().getProprietario().getNome() + " enviou um arquivo:\n Local: " + diretorio + "\n");
            }
        }
    }

    public void audio(Mensagem msg) {
        if (msg.getMensagem().equals("iniciar")) {
            boolean contatoAbberto = false;
            //janela ainda não estava aberta aberta
            if (!verificaJanelaAberta(msg)) {
                controller.listaOnClicada(msg.getConta().getProprietario().getNome());
                audio(msg);
            } else {
                Conversa conversa = controller.getJanelaAberta(usuario.getUsuario());
                conversa.getjTextAreaConversa().append(msg.getConta().getProprietario().getNome() + " iniciará uma chamada de voz.\n");
                Audio audio = new Audio(msg.getConta().getProprietario(), conversa.getController());
            }
        } else {
            controller.encerraChamada(msg);
        }
    }
}
