/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conta;
import Model.Usuario;
import View.Audio;
import View.Conversa;
import java.io.File;

/**
 *
 * @author Jessica
 */
public interface ObservadoConversa {

    void addObservador(ObservadorConversa obs);

    void nomeUsuario();

    Usuario getUsuario();

    void onClose();

    void onEnviarMsg(String msg);

    void enviarArquivo(File fileSelected);

    void onEnviarAudio();

    Conta getConta();
    
    void onEncerrarAudio();
    
    void setAudio(Audio audio);
    
    Audio getAudio();

}
