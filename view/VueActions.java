package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Parameters;
import util.Utils;
 
public class VueActions extends Vue {
    
    private final JButton btnBouger;
    private final JButton btnAssecher;
    private final JButton btnJouerCarte;
    private final JButton btnDonnerCarte;
    private final JButton btnRecupererTresor;
    private JLabel iconBouger;
    private JLabel iconAssecher;
    private JLabel iconJouerCarte;
    private JLabel iconDonnerCarte;
    private JLabel iconRecupererTresor;
    
    public VueActions(){
        /////////////////////////////////////////////
        // Fenetre de nom "Vue Action"
        // Largeur: 200   Hauteur: 300
        // Modificateur de coordonées par rapport 
        //  au centre de l'ecran: x: 0   y: 0
        /////////////////////////////////////////////
        super("Vue Action", 200, 300, 0, 0);
        
        
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        //////////////////////////////////////////
        ///// Panel des bouttons des actions /////
        //////////////////////////////////////////
        
        JPanel btnPanel = new JPanel(new GridLayout(5,1));
        mainPanel.add(btnPanel, BorderLayout.CENTER);
        
        btnBouger = new JButton("Bouger");
        btnBouger.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(Utils.Commandes.BOUGER);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnBouger);
        
        btnAssecher = new JButton("Assecher");
        btnAssecher.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(Utils.Commandes.ASSECHER);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnAssecher);
        
        btnJouerCarte = new JButton("Jouer Carte");
        btnJouerCarte.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(Utils.Commandes.CHOISIR_CARTE);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnJouerCarte);
        
        btnDonnerCarte = new JButton("Donner Carte");
        btnDonnerCarte.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(Utils.Commandes.DONNER);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnDonnerCarte);
        
        btnRecupererTresor = new JButton("Recuperer Tresor");
        btnRecupererTresor.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(Utils.Commandes.RECUPERER_TRESOR);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnRecupererTresor);
        
        ////////////////////////////////////////
        ///// Panel des icones des actions /////
        ////////////////////////////////////////
        
        JPanel iconPanel = new JPanel(new GridLayout(5,1));
        mainPanel.add(iconPanel, BorderLayout.WEST);
        
        this.iconBouger = new JLabel();
        this.iconBouger.setIcon(new ImageIcon(Parameters.ICONS +"iconMove.png"));
        iconPanel.add(this.iconBouger);
        
        this.iconAssecher = new JLabel();
        this.iconAssecher.setIcon(new ImageIcon(Parameters.ICONS +"iconDry.png"));
        iconPanel.add(this.iconAssecher);
        
        this.iconJouerCarte = new JLabel();
        this.iconJouerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconShift.png"));
        iconPanel.add(this.iconJouerCarte);
        
        this.iconDonnerCarte = new JLabel();
        this.iconDonnerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconGive.png"));
        iconPanel.add(this.iconDonnerCarte);
        
        this.iconRecupererTresor = new JLabel();
        this.iconRecupererTresor.setIcon(new ImageIcon(Parameters.ICONS +"iconGet.png"));
        iconPanel.add(this.iconRecupererTresor);
        
        
    }
 
    
    public void activerBouger(){
        this.btnBouger.setEnabled(true);
        this.iconBouger.setIcon(new ImageIcon(Parameters.ICONS +"iconMove.png"));
    }
    
    public void desactiverBouger(){
        this.btnBouger.setEnabled(false);
        this.iconBouger.setIcon(new ImageIcon(Parameters.ICONS +"iconMove_disabled.png"));
    }
    
    
    public void activerAssecher(){
        this.btnAssecher.setEnabled(true);
        this.iconAssecher.setIcon(new ImageIcon(Parameters.ICONS +"iconDry.png"));
    }
    
    public void desactiverAssecher(){
        this.btnAssecher.setEnabled(false);
        this.iconAssecher.setIcon(new ImageIcon(Parameters.ICONS +"iconDry_disabled.png"));
    }
    
    
    public void activerJouerCarte(){
        this.btnJouerCarte.setEnabled(true);
        this.iconJouerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconShift.png"));
    }
    
    public void desactiverJouerCarte(){
        this.btnJouerCarte.setEnabled(false);
        this.iconJouerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconShift_disabled.png"));
    }
    
    
    public void activerDonnerCarte(){
        this.btnDonnerCarte.setEnabled(true);
        this.iconDonnerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconGive.png"));
    }
    
    public void desactiverDonnerCarte(){
        this.btnDonnerCarte.setEnabled(false);
        this.iconDonnerCarte.setIcon(new ImageIcon(Parameters.ICONS +"iconGive_disabled.png"));
    }
    
    
    public void activerRecupererTresor(){
        this.btnRecupererTresor.setEnabled(true);
        this.iconRecupererTresor.setIcon(new ImageIcon(Parameters.ICONS +"iconGet.png"));
    }
    
    public void desactiverRecupererTresor(){
        this.btnRecupererTresor.setEnabled(false);
        this.iconRecupererTresor.setIcon(new ImageIcon(Parameters.ICONS +"iconGet_disabled.png"));
    }
    
    
}