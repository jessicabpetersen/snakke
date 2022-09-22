/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.DefaultListModel;

/**
 *
 * @author Jessica
 */
public interface ObservadorApp {
    
    void atualizarNome(String nome);
    
    void atualizarListaOn(DefaultListModel listaOn);
    void atualizarListaOff(DefaultListModel listaOff);
    
    void addMsgTela(String msg);
}
