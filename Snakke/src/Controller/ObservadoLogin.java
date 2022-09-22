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
public interface ObservadoLogin {
    
    void addObservador(ObservadorLogin obs);
    
    boolean login(String login, String senha);
    
    String esqueceuSenha(String email);

}
