package view;
 
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import util.EtatTuile;
import util.Parameters;
import util.Utils;

public class VueTuile extends JPanel {
    
    private String nom;
    private int largeur;
    private int hauteur;
    private Image image;
    private ArrayList<Image> imagesPion;
    
    public VueTuile(int largeur, int hauteur){
        super();
        this.nom = "";
        this.largeur = largeur;
        this.hauteur = hauteur;  
        this.imagesPion = new ArrayList<Image>();
    }
    
    
    
   //change l'etat de la tuile en fonction du parametre donné
    public void setEtat(EtatTuile etat){
        try{
            //la case est normale
            if(etat==EtatTuile.NORMAL){
                Image imageOri = ImageIO.read(new File(Parameters.TUILES + this.nom +".png"));
                this.image = imageOri.getScaledInstance(this.largeur, this.hauteur, Image.SCALE_DEFAULT);

            //la case est coulée
            }else if(etat==EtatTuile.INONDE){
                Image imageOri = ImageIO.read(new File(Parameters.TUILES + this.nom +"_Inonde.png"));
                this.image = imageOri.getScaledInstance(this.largeur, this.hauteur, Image.SCALE_DEFAULT);

            //la case est detruite
            }else if(etat==EtatTuile.DETRUIT){
                this.image = null;
            }
            repaint();  
            
        //si l'image n'a pas pu etre chargée   
        }catch(IOException ex){
            this.image = null;
            System.out.println("Erreur IHM: impossible de charger l'image: "+ nom +".png pour le cas "+ etat);
        }
    }
    
    
    
    //change l'image de la tuile en fonction du parametre donné
    //NOTE:
    //   - le nom de l'image doit etre donné sans son extention et sans le "_Inonde"
    //   - l'image doit etre en .png
    //   - l'image doit se trouver dans le repertoire /images/tuiles/
    public void setImage(String nom){
        this.nom =nom;
        
        try{
            Image imageOri = ImageIO.read(new File(Parameters.TUILES + this.nom +".png"));
            this.image = imageOri.getScaledInstance(this.largeur, this.hauteur, Image.SCALE_DEFAULT);
            repaint();
            
        }catch(IOException ex){
            this.image = null;
            System.out.println("Erreur IHM: impossible de charger l'image: "+ nom +".png");
        }
    }
    
    
    
    //affiche les pions sur cette tuile en fonction du tableau de couleurs de pion donné
    public void setPions(Utils.Pion[] pions){
        //vide les pions deja present
        this.imagesPion.clear();
        
        for(int i=0; i<pions.length; i++){
          try{
            Image imageOri = ImageIO.read(new File(Parameters.PIONS + pions[i].getPath()));
            this.imagesPion.add( imageOri.getScaledInstance(this.largeur/2, this.hauteur/2, Image.SCALE_DEFAULT));

            }catch(IOException ex){
                this.image = null;
                System.out.println("Erreur IHM: impossible de charger l'image: "+ pions[i].getPath());
            } 
        }
        repaint();
    }
    
    
    //sert lors de l'affichage du JPanel sur l'ecran
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //affiche l'image de la tuile
        if(this.image!=null){
            g.drawImage(this.image, 10, 10, this);   
        }
        //affiche l'image des pions (si il y en as)
        for(int i=0; i<this.imagesPion.size(); i++){
            if(i==0){
                g.drawImage(this.imagesPion.get(i), 10, 10, this);
            }else if(i==1){
                g.drawImage(this.imagesPion.get(i), 40, 40, this);
            }else if(i==2){
                g.drawImage(this.imagesPion.get(i), 70, 10, this);
            }else if(i==3){
                g.drawImage(this.imagesPion.get(i), 100, 40, this);
            }else{
                System.out.println("Erreur IHM: trop de joueurs sur la meme case");
            }
        }
    }
    
    

}