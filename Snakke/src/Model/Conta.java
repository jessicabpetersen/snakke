/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jessica
 */
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario proprietario;
    private String email;
    private String senha;
    private String telefone;
    private static Conta instance;
    private List<Usuario> contatos = new ArrayList<>();

    public synchronized static Conta getInstance() {
        if (instance == null) {
            instance = new Conta();
        }
        return instance;
    }

    public Conta() {
//        testeContatos();
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Conta(String usuario, String senha) {
        getProprietario().setUsuario(usuario);
        this.senha = senha;
//        testeContatos();
    }

    public void testeContatos() {

        Usuario dono = new Usuario("Jéssica", true, "jessicabp");
        this.email = "jessica@gmail.com";
        this.senha = "1234";
        this.telefone = "sada";
        this.proprietario = dono;
        Usuario usuario = new Usuario("Aparicio", true, "apariciods");
        usuario.setIp("192.168.0.100");
        addContato(usuario);
        usuario = new Usuario("Professor", true, "professor");
        usuario.setIp("192.168.0.102");
        addContato(usuario);
    }

    public void setContatos(List<Usuario> contatos) {
        this.contatos = contatos;
    }

    public void addContato(Usuario usu) {
        this.contatos.add(usu);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getContatos() {
        return contatos;
    }

    public void atualizarContatos(List<Usuario> lista) {
        this.contatos = lista;
    }

}
