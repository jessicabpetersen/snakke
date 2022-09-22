/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.App;

/**
 *
 * @author Jessica
 */
public interface ObservadoApp {
    
    void addObservador(ObservadorApp obs);
    
    void listaOnClicada(String nome);
    
    void AtualizarDados();
    
    void adicionarContato(String usuario);
    
    void removerContato(String usuario);
    
    void atualizarTelaInicial();
    
    void iniciarEscuta();
    
}
