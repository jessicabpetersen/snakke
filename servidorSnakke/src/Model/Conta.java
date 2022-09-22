package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 *
 * @author aparicio da silva
 */
@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario proprietario;
    private String email;
    private String senha;
    private String telefone;
    private static Conta instance;
    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name="contatos",joinColumns =@JoinColumn(name = "conta"),inverseJoinColumns = @JoinColumn(name = "usuario"))
    private List<Usuario> contatos;

  
    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }
      

    public void addContato(Usuario usu) {
        if(this.contatos == null){
            this.contatos = new ArrayList<Usuario>();
        }
        this.contatos.add(usu);
    }

    
    public List<Usuario> getContatos() {
        return contatos;
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

    public void setContatos(List<Usuario> contatos) {
        this.contatos = contatos;
    }


}
