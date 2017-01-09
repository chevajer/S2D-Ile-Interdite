package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import util.EtatTuile;
import util.Parameters;
import util.Utils;
 
public class VueGrille extends JPanel {
    
    private VueTuile[][] tuiles;
    private Image image;
    
    public VueGrille(VuePlateau plateau, int largeur, int hauteur){
        
        super(new GridLayout(6, 6));
        
        this.tuiles = new VueTuile[6][6];
        
        //chargement de l'image du plateau
        try{
            Image imageOri = ImageIO.read(new File(Parameters.IMAGES +"plateau.jpg"));
            this.image = imageOri.getScaledInstance(largeur, hauteur-20, Image.SCALE_DEFAULT);
            
        }catch(IOException ex){
            this.image = null;
            System.out.println("Erreur IHM: impossible de charger l'image: plateau.jpg");
        }
        
        for(int x=0; x<6; x++){
            for(int y=0; y<6; y++){
                
                //si la case n'esty pas une tuile du plateau, on met une case vide
                if((x==0&&y==0)||(x==0&&y==1)||(x==0&&y==4)||(x==0&&y==5)||
                   (x==1&&y==0)||(x==1&&y==5)||
                   (x==4&&y==0)||(x==4&&y==5)||
                   (x==5&&y==0)||(x==5&&y==1)||(x==5&&y==4)||(x==5&&y==5)){
                    
                    JPanel panel = new JPanel();
                    panel.setOpaque(false);
                    this.add(panel);
                    this.tuiles[x][y] = null;
                    
                   
                //si la case est une tuile de plateau  , on y met une tuile  
                }else{
                    
                    this.tuiles[x][y] = new VueTuile(130, 130);
                    this.tuiles[x][y].setName(x+","+y);
                    this.tuiles[x][y].setOpaque(false);
                    this.tuiles[x][y].addMouseListener(
                        new MouseListener(){    
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                plateau.avertirControleur(((VueTuile) e.getSource()).getName());
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {     
                            }
                            @Override
                            public void mouseReleased(MouseEvent e) {     
                            }
                            @Override
                            public void mouseEntered(MouseEvent e) {    
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {     
                            }
                        }
                    );
                    this.add(this.tuiles[x][y]);
                    
                }
            }
        }
   
    }
    
    
    
    //change l'image des tuiles en fonction d'un tableau String[6][6] donné
    public void setImagesTuiles(String[][] imagesTuile){
        for(int x=0; x<6; x++){
            for(int y=0; y<6; y++){
                if(this.tuiles[x][y] != null){
                    this.tuiles[x][y].setImage(imagesTuile[x][y]);
                }
            }
        }
    }
    
    
    //change l'etat d'une tuile en fonction des coordonnées de la tuile et de l'etat donné
    public void setEtatTuile(EtatTuile etat, int x, int y){
        this.tuiles[x][y].setEtat(etat);
    }
    
    
    //change les pions d'une tuile en fonction des coordonnées de la tuile et du tableau de couleurs de pion donné
    public void setPions(Utils.Pion[] pions, int x, int y){
        this.tuiles[x][y].setPions(pions);
    }
    
    
    //sert lors de l'affichage du JPanel sur l'ecran
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);  
    }
   
    
   
    
}