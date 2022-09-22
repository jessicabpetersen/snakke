/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Jessica
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String usuario;
    private boolean online;
    private String ip;

    public Usuario(String nome, boolean status, String usuario) {
        this.nome = nome;
        this.online = status;
        this.usuario = usuario;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
        }
    }

    public Usuario() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public boolean isOnline() {
        return online;
    }

    public String getNome() {
        return nome;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
