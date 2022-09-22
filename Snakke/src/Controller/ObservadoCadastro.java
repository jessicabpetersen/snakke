/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Jessica
 */
public interface ObservadoCadastro {
    
    void addObservador(ObservadorCadastro obs);
    
    void voltar();
    
    boolean cadastro(String nome, String usuario, String email, String senha, String telefone);
    
}
