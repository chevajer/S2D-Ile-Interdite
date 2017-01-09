/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import javax.swing.JFrame;

/**
 *
 * @author chevajer
 */
public abstract class Vue extends Observable {
    
    protected final JFrame window;
    
    public Vue(String titre, int largeur, int hauteur, int x, int y){
        this.window = new JFrame(titre);
        this.window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.window.setSize(largeur, hauteur);
        //la fenetre as comme position par défaut le centre de l'ecran
        //cette position est modifiée par x et y
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.window.setLocation(dim.width/2-window.getSize().width/2 + x, dim.height/2-window.getSize().height/2 + y);
        this.window.setResizable(false);
    }
    
    
    //affiche la fenetre a l'écran 
    public void afficher() {
        this.window.setVisible(true);
    }
    
    
    //cache la fenetre
    public void cacher() {
        window.dispose();
    }
    
}
