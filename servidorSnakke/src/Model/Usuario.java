package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author aparicio da silva
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String usuario;
    private String nome;
    private boolean online;
    private String ip;

    public Usuario() {
        this.nome = null;
        this.online = false;
        this.ip = null;
        this.usuario = null;
    }

    public Usuario(String nome, boolean status, String ip, String usuario) {
        this.nome = nome;
        this.online = status;
        this.ip = ip;
        this.usuario = usuario;
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

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
