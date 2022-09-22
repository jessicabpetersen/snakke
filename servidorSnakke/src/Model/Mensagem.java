package Model;

import java.io.Serializable;
/**
 *
 * @author Jessica
 */
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int assunto;
    private Conta conta;
    private String mensagem;
    private Arquivo arquivo;

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public int getAssunto() {
        return assunto;
    }

    public void setAssunto(int assunto) {
        this.assunto = assunto;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
