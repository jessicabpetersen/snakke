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
public interface ObservadoAtualizar {

    void addObservador(ObservadorAtualizar obs);

    void mostrarDadosConta();

    boolean atualizarDados(String nome, String usuario, String senha, String email, String telefone);
}
