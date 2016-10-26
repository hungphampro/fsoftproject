/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import view.inc.PnLeft;

/**
 *
 * @author HungPham
 */
public class Music extends Thread {

    File filename;

    public Music(File filename) {
        this.filename = filename;
    }

    public void go(File filename) {
        try {
            FileInputStream fis = null;

            fis = new FileInputStream(filename);
            try {
                Player play = new Player(fis);
                play.play();
            } catch (JavaLayerException ex) {
                Logger.getLogger(PnLeft.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run(){
        go(filename);
    }
}
