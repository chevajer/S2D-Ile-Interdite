package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Parameters;
 
public class VueAventurier extends Vue {
    
    private JLabel nom;
    private JLabel role;
    private ArrayList<JLabel> cartes;
    
    public VueAventurier(){
        /////////////////////////////////////////////
        // Fenetre de nom "Vue Aventurier"
        // Largeur: 270   Hauteur: 450
        // Modificateur de coordonées par rapport 
        //  au centre de l'ecran: x: 0   y: 0
        /////////////////////////////////////////////
        super("Vue Aventurier", 270, 450, 0, 0);
        
        this.nom = new JLabel("");
        this.role = new JLabel("");
        this.cartes = new ArrayList<JLabel>();
        for(int i=0; i<9; i++){
            this.cartes.add(new JLabel());  
        }
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        /////////////////////////////////////////////////////
        ///// Panel qui affiche les details des joueurs /////
        /////////////////////////////////////////////////////
        
        JPanel detailPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(detailPanel, BorderLayout.NORTH);
        
        detailPanel.add(this.nom);
        detailPanel.add(this.role);
        detailPanel.add(new JLabel("___________________________________________"));
        detailPanel.add(new JLabel(""));
        
        //////////////////////////////////////////////////
        ///// Panel qui affiche les cartes du joueur /////
        //////////////////////////////////////////////////
        
        JPanel cartesPanel = new JPanel(new GridLayout(3, 3));
        mainPanel.add(cartesPanel, BorderLayout.CENTER);
        
        for(int i=0; i<9; i++){
            cartesPanel.add(this.cartes.get(i));
        }
        
    }
    
    public void setNom(String nom){
        this.nom.setText(nom);
    }
    
    public void setRole(String role){
        this.role.setText(role);
    }
    
    //Affiche les cartes données en parametre
    //NOTE: le tableau donné doit avoir au moin 6 elements
    //      chaque element indique le nombre de vartes d'un certain type:
    //        element 1: carte Helicoptere
    //        element 2: carte Sac de Sable
    //        element 3: carte Calice
    //        element 4: carte Cristal
    //        element 5: carte Pierre
    //        element 6: carte Zephyr
    public void setNbCartes(int[] detailCartes){
        int typecarte = 0;
        for(int i=0; i<9; i++){
            while(typecarte<6 && detailCartes[typecarte]==0){
                typecarte++;
            }
            
            if(typecarte<6){
                  
                ImageIcon ic = null;
                if(typecarte==0){
                    ic = new ImageIcon(util.Parameters.CARTES +"Helicoptere.png");
                }else if(typecarte==1){
                    ic = new ImageIcon(util.Parameters.CARTES +"SacsDeSable.png");
                }else if(typecarte==2){
                    ic = new ImageIcon(util.Parameters.CARTES +"Calice.png");
                }else if(typecarte==3){
                    ic = new ImageIcon(util.Parameters.CARTES +"Cristal.png");
                }else if(typecarte==4){
                    ic = new ImageIcon(util.Parameters.CARTES +"Pierre.png");
                }else if(typecarte==5){
                    ic = new ImageIcon(util.Parameters.CARTES +"Zephyr.png");
                }
                
                detailCartes[typecarte]--;
                
                Image im = ic.getImage();
                Image newI = im.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
                this.cartes.get(i).setIcon(new ImageIcon(newI)); 
                this.cartes.get(i).setVisible(true);
                
            }else{
                this.cartes.get(i).setIcon(new ImageIcon());
            }
            
        }
    }
}


/*

 ImageIcon ic = new ImageIcon(util.Parameters.CARTES +"Fond bleu.png");
            Image im = ic.getImage();
            Image newI = im.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            this.cartes.get(i).setIcon(new ImageIcon(newI)); 
            this.cartes.get(i).setVisible(false);


*/