/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Jessica
 */
public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 1: cadastro
     * 2: esqueceu senha
     * 3: login
     * 4: atualizar contatos
     * 5: adicionar contato
     * 6: remover contato
     * 7: atualizar meus dados
     * 8: envio de msg de texto
     * 9: envio de arquivo
     * 10: envio de audio
     */
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
