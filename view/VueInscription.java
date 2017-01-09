package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import util.MessageInscription;

/**
 *
 * @author IUT2-Dept Info
 */
public class VueInscription extends Vue {
    
    private final JButton btnJouer;
    private final JTextField champNomJ1;
    private final JTextField champNomJ2;
    private final JLabel txtJ3;
    private final JTextField champNomJ3;
    private final JLabel txtJ4;
    private final JTextField champNomJ4;
    private final JRadioButton radio2J;
    private final JRadioButton radio3J;
    private final JRadioButton radio4J;
    
    public VueInscription() {
        /////////////////////////////////////////////
        // Fenetre de nom "Inscription des joueurs"
        // Largeur: 400   Hauteur: 200
        // Modificateur de coordonées par rapport 
        //  au centre de l'ecran: x: 0   y: 0
        /////////////////////////////////////////////
        super("Inscription des joueurs", 400, 200, 0, 0);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        ////////////////////////////////////////////////////////////////
        ////////// Panel des champs pour les noms des joueurs //////////
        ////////////////////////////////////////////////////////////////
        
        JPanel nomPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(nomPanel, BorderLayout.CENTER);
        
        nomPanel.add(new JLabel("Joueur 1", JLabel.CENTER));
        this.champNomJ1 = new JTextField();
        nomPanel.add(this.champNomJ1);
        
        nomPanel.add(new JLabel("Joueur 2", JLabel.CENTER));
        this.champNomJ2 = new JTextField();
        nomPanel.add(this.champNomJ2);
        
        this.txtJ3 = new JLabel("Joueur 3", JLabel.CENTER);
        nomPanel.add(this.txtJ3);
        this.champNomJ3 = new JTextField();
        nomPanel.add(this.champNomJ3);
        
        this.txtJ4 = new JLabel("Joueur 4", JLabel.CENTER);
        nomPanel.add(this.txtJ4);
        this.champNomJ4 = new JTextField();
        nomPanel.add(this.champNomJ4);
        
        ////////////////////////////////////////////////////////////////////////
        ////////// Panel des bouttons radio pour le nombre de joueurs //////////
        ////////////////////////////////////////////////////////////////////////
        
        JPanel numPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(numPanel, BorderLayout.WEST);
        
        //L'ensemble des bouttons radio
        ButtonGroup radioGroupNum = new ButtonGroup();
        
        radio2J = new JRadioButton("2 joueurs");
        radio2J.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(MessageInscription.JOUEURS_2);
                   clearChanged();
               }
           }
        );
        //////////////////////////////////////////
        ///// Boutton "2 joueurs" deja coché /////
        radio2J.setSelected(true);
        //////////////////////////////////////////
        numPanel.add(radio2J);
        radioGroupNum.add(radio2J);
        
        radio3J = new JRadioButton("3 joueurs");
        radio3J.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(MessageInscription.JOUEURS_3);
                   clearChanged();
               }
           }
        );
        numPanel.add(radio3J);
        radioGroupNum.add(radio3J);
        
        radio4J = new JRadioButton("4 joueurs");
        radio4J.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(MessageInscription.JOUEURS_4);
                   clearChanged();
               }
           }
        );
        numPanel.add(radio4J);
        radioGroupNum.add(radio4J);
        
        ////////////////////////////////////////////
        ////////// Panel du boutton jouer //////////
        ///////////////////////////////////////////
        
        JPanel btnPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
      
        btnPanel.add(new JLabel(""));   
        btnPanel.add(new JLabel(""));
        btnJouer = new JButton("Jouer");
        btnJouer.addActionListener(
           new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                   setChanged();
                   notifyObservers(MessageInscription.JOUER);
                   clearChanged();
               }
           }
        );
        btnPanel.add(btnJouer);
        
        /////////////////////////////////////////////////////////////////////////////////////////
        ////////// Initialisation de l'etat initial, deux joueurs a inscrire seulement //////////
        /////////////////////////////////////////////////////////////////////////////////////////
        
        this.setNumJoueurs(MessageInscription.JOUEURS_2);
        
    }
    
    
    
    // rend les noms saisis par les joueurs sous forme d'un tableau de string
    // le nombre d'elements correspond au nombre de joueurs inscrit (ente 2 et 4)
    public String[] getNoms(){
        String noms[] = null;
                
        //si il y a 4 joueurs inscrits
        if(this.champNomJ4.isVisible()){
            noms = new String[4];
            noms[0] = this.champNomJ1.getText();
            noms[1] = this.champNomJ2.getText();
            noms[2] = this.champNomJ3.getText();
            noms[3] = this.champNomJ4.getText();
            
        //si il y a 3 joueurs inscrits
        }else if(this.champNomJ3.isVisible()){
            noms = new String[3];
            noms[0] = this.champNomJ1.getText();
            noms[1] = this.champNomJ2.getText();
            noms[2] = this.champNomJ3.getText();
            
        //si il y a 2 joueurs inscrits    
        }else{
            noms = new String[2]; 
            noms[0] = this.champNomJ1.getText();
            noms[1] = this.champNomJ2.getText();
        }
        
        return noms;
    }
    
    
    
    //change le nombre de joueurs que l'utilisateur peut inscrire en fonction du parametre nb
    public void setNumJoueurs(MessageInscription nb){
        if(nb == MessageInscription.JOUEURS_2){
            this.txtJ3.setVisible(false);
            this.champNomJ3.setVisible(false);
            this.txtJ4.setVisible(false);
            this.champNomJ4.setVisible(false);
            
        }else if(nb == MessageInscription.JOUEURS_3){
            this.txtJ3.setVisible(true);
            this.champNomJ3.setVisible(true);
            this.txtJ4.setVisible(false);
            this.champNomJ4.setVisible(false);
            
        }else if(nb == MessageInscription.JOUEURS_4){
            this.txtJ3.setVisible(true);
            this.champNomJ3.setVisible(true);
            this.txtJ4.setVisible(true);
            this.champNomJ4.setVisible(true);
            
        }
    }
    
}
