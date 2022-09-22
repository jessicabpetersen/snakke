/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Audio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jessica
 */
public class ThreadTempo extends Thread{
    
    Audio audio;
    boolean chamadaAtiva;
    
    public ThreadTempo(Audio audio){
        this.audio = audio;
        chamadaAtiva = true;
    }
    
    public void encerrarChamada(){
        chamadaAtiva = false;
    }

    @Override
    public void run() {
        int tempo = 0;
        while(chamadaAtiva){
            try {
                Thread.sleep(998);
                tempo++;
                if((tempo/60) > 9){
                    if((tempo%60)>9){
                        audio.getjLabelTempo().setText((tempo/60)+":"+(tempo%60));
                    }else{
                        audio.getjLabelTempo().setText((tempo/60)+":0"+(tempo%60));
                    }
                }else{
                    if((tempo%60)>9){
                        audio.getjLabelTempo().setText("0"+(tempo/60)+":"+(tempo%60));
                    }else{
                        audio.getjLabelTempo().setText("0"+(tempo/60)+":0"+(tempo%60));
                    }
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadTempo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
