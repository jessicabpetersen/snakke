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
public class Arquivo implements Serializable {
   private static final long serialVersionUID = 1L;
              
   private String nome;
   private byte[] conteudo;
   private transient long tamanhoKB;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public long getTamanhoKB() {
        return tamanhoKB;
    }

    public void setTamanhoKB(long tamanhoKB) {
        this.tamanhoKB = tamanhoKB;
    }
   
}
