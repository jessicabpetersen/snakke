/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Arquivo;
import Model.Conta;
import Model.Mensagem;
import Model.Usuario;
import View.Audio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class ControllerConversa implements ObservadoConversa {

    private List<ObservadorConversa> observadores = new ArrayList<>();
    Conta conta;
    Usuario usuario;
    Audio audio;

    public ControllerConversa(Usuario usu) {
        conta = Conta.getInstance();
        usuario = usu;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }
    
    

    public ControllerConversa() {
    }

    public List<ObservadorConversa> getObservadores() {
        return observadores;
    }

    @Override
    public Conta getConta() {
        return conta;
    }

    @Override
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void nomeUsuario() {
        for (ObservadorConversa obs : observadores) {
            obs.atualizarNome(usuario.getNome());
        }
    }

    @Override
    public void addObservador(ObservadorConversa obs) {
        this.observadores.add(obs);
    }

    @Override
    public void onClose() {

    }

    @Override
    public void onEnviarMsg(String smsg) {
        //enviar msg para o usuario
        Mensagem msg = new Mensagem();
        msg.setAssunto(8);
        msg.setConta(conta);
        msg.setMensagem(smsg);
        enviarTCP(msg);
    }

    public void enviarTCP(Mensagem msg) {
        try {
            TCPSocketThreadEnvio tcpEnvio = new TCPSocketThreadEnvio(msg, usuario.getIp());
            tcpEnvio.start();
        } catch (IOException i) {

        } finally {
            mostrarTela(msg.getMensagem());
        }
    }

    public void mostrarTela(String msg) {
        for (ObservadorConversa obs : observadores) {
            if (msg.contains("Enviado")) {

                obs.atualizarTela(msg + "\n");
            } else {
                obs.atualizarTela(conta.getProprietario().getNome() + ":\n" + msg + "\n");
            }
        }
    }

    @Override
    public void enviarArquivo(File fileSelected) {
        try {
            byte[] bFile = new byte[(int) fileSelected.length()];
            FileInputStream fis = new FileInputStream(fileSelected);
            fis.read(bFile);
            fis.close();

            long kbSize = fileSelected.length() / 1024;

            Arquivo arquivo = new Arquivo();
            arquivo.setConteudo(bFile);
            arquivo.setNome(fileSelected.getName());
            arquivo.setTamanhoKB(kbSize);
            Mensagem msg = new Mensagem();
            msg.setAssunto(9);
            msg.setConta(conta);
            msg.setArquivo(arquivo);

            TCPSocketThreadEnvio tcp = new TCPSocketThreadEnvio(msg, usuario.getIp());
            tcp.start();
            mostrarTela("Enviado o arquivo: " + arquivo.getNome());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerConversa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerConversa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onEnviarAudio() {
        Mensagem msg = new Mensagem();
        msg.setAssunto(10);
        msg.setConta(conta);
        msg.setMensagem("iniciar");
        try {
            TCPSocketThreadEnvio tcpEnvio = new TCPSocketThreadEnvio(msg, usuario.getIp());
            tcpEnvio.start();
        } catch (IOException i) {

        }
        for (ObservadorConversa obs : observadores) {
            obs.atualizarTela("Você está iniciando uma chamada de áudio \n");
        }

    }

    @Override
    public void onEncerrarAudio() {
        Mensagem msg = new Mensagem();
        msg.setAssunto(10);
        msg.setConta(conta);
        msg.setMensagem("encerrar");
        try {
            TCPSocketThreadEnvio tcpEnvio = new TCPSocketThreadEnvio(msg, usuario.getIp());
            tcpEnvio.start();
        } catch (IOException i) {

        }
        for (ObservadorConversa obs : observadores) {
            obs.atualizarTela("Você encerrou a chamada de áudio \n");
        }
    }

}
