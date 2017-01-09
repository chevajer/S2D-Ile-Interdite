package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import util.EtatTuile;
import util.Utils;

/**
 *
 * @author IUT2-Dept Info
 */
public class VuePlateau extends Vue {
    
    private VueGrille grille;
    
    public VuePlateau(){
        /////////////////////////////////////////////
        // Fenetre de nom "Plateau"
        // Largeur: 900   Hauteur: 900
        // Modificateur de coordonées par rapport 
        //  au centre de l'ecran: x: 0   y: 0
        /////////////////////////////////////////////
        super("Plateau", 900, 900, 0, 0);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        ///////////////////////////////////////////
        ///// la grille contenant les tuilles /////
        ///////////////////////////////////////////
        
        this.grille = new VueGrille(this, 900, 900); 
        mainPanel.add(this.grille, BorderLayout.CENTER);
    }
    
    //envoi au controleur un message donné en parametre
    //NOTE: utilisable uniquement par vueTuile, sinon, ne pas utiliser
    public void avertirControleur(String message) {
        setChanged();
        notifyObservers(message);
        clearChanged();
    }
    
    //change l'image des tuiles en fonction d'un tableau String[6][6] donné
    public void setImagesTuiles(String[][] imagesTuile){
        this.grille.setImagesTuiles(imagesTuile);
    }
    
    //change l'etat d'une tuile en fonction des coordonnées de la tuile et de l'etat donné
    public void setEtatTuile(EtatTuile etat, int x, int y){
        this.grille.setEtatTuile(etat, x, y);
    }
    
    //change les pions d'une tuile en fonction des coordonnées de la tuile et du tableau de couleurs de pion donné
    public void setPions(Utils.Pion[] pions, int x, int y){
        this.grille.setPions(pions, x, y);
    }
    
   
}
