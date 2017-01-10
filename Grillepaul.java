package model.cases;

import java.util.HashMap;
import util.Utils;

/**
 * Classe permettant de gérer la grille des tuiles du jeu
 Elle gère un unique attribut : un tableau de 6 x 6 tuiles
 Il y a 12 tuiles null et 24 tuiles réelles.
 * Les tuiles sont donc (ligne, colonne)
  null    null    (0,2)   (0,3)   null    null
  null    (1,1)   (1,2)   (1,3)   (1,4)   null
  (2,0)   (2,1)   (2,2)   (2,3)   (2,4)   (2,5)
  (3,0)   (3,1)   (3,2)   (3,3)   (3,4)   (3,5)
  null    (4,1)   (4,2)   (4,3)   (4,4)   null
  null    null    (5,2)   (5,3)   null    null
 * @author IUT2-Dept Info
 */
public class Grille {

    Tuile[][] tuiles ; // Les tuiles du jeu
    
    /** -------------------------------------------------------------------------------------------------------------
     * Constructeur
     */
    public Grille() {
        this.tuiles = new Tuile[6][6];
        for (int x=0; x<6; x++){
            for (int y=0; y<6; y++){
                // si la tuile ne fait pas partie du plateau
                if ((x==0&&y==0)||(x==0&&y==1)||
                    (x==0&&y==4)||(x==0&&y==5)||
                    (x==1&&y==0)||(x==1&&y==5)||
                    (x==4&&y==0)||(x==4&&y==5)||
                    (x==5&&y==0)||(x==5&&y==1)||
                    (x==5&&y==4)||(x==5&&y==5)){
                    tuiles[x][y]=null;
                }
            }
        }
    }

    public Tuile[][] getTuiles() {
        return tuiles;
    }

    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }
    
    public HashMap<Integer,Tuile> getTuilesAdjacentes(Tuile tuile){
        boolean trouve = false;
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        int x=0;
        int y=0;
        while (!trouve){
        /*for (int x=0; x<6; x++){
            for (int y=0; y<6; y++){*/
                if (!((x==0&&y==0)||(x==0&&y==1)||
                    (x==0&&y==4)||(x==0&&y==5)||
                    (x==1&&y==0)||(x==1&&y==5)||
                    (x==4&&y==0)||(x==4&&y==5)||
                    (x==5&&y==0)||(x==5&&y==1)||
                    (x==5&&y==4)||(x==5&&y==5))){
                    if (getTuiles()[x][y]==tuile){
                        trouve=true; 
                    }
                    else{
                        x++;
                        y++;
                    }
                }
            }
        if(getTuiles()[x][y-1]!=null){
            tuiles.put(getTuiles()[x][y-1].getId(), getTuiles()[x][y-1]);    
        }
         if(getTuiles()[x+1][y]!=null){
            tuiles.put(getTuiles()[x+1][y].getId(), getTuiles()[x+1][y]);    
        }
        if(getTuiles()[x][y+1]!=null){
            tuiles.put(getTuiles()[x][y+1].getId(), getTuiles()[x][y+1]);    
        }
        if(getTuiles()[x-1][y]!=null){
            tuiles.put(getTuiles()[x-1][y].getId(), getTuiles()[x-1][y]);    
        }
        return tuiles;
    }
    
    public HashMap<Integer,Tuile> getTuilesDiagonales(Tuile tuile){
        boolean trouve = false;
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        int x=0;
        int y=0;
        while (!trouve){
        /*for (int x=0; x<6; x++){
            for (int y=0; y<6; y++){*/
                if (!((x==0&&y==0)||(x==0&&y==1)||
                    (x==0&&y==4)||(x==0&&y==5)||
                    (x==1&&y==0)||(x==1&&y==5)||
                    (x==4&&y==0)||(x==4&&y==5)||
                    (x==5&&y==0)||(x==5&&y==1)||
                    (x==5&&y==4)||(x==5&&y==5))){
                    if (getTuiles()[x][y]==tuile){
                        trouve=true; 
                    }
                    else{
                        x++;
                        y++;
                    }
                }
            }
        if(getTuiles()[x-1][y-1]!=null){
            tuiles.put(getTuiles()[x-1][y-1].getId(), getTuiles()[x-1][y-1]);    
        }
         if(getTuiles()[x+1][y-1]!=null){
            tuiles.put(getTuiles()[x+1][y-1].getId(), getTuiles()[x+1][y-1]);    
        }
        if(getTuiles()[x+1][y+1]!=null){
            tuiles.put(getTuiles()[x+1][y+1].getId(), getTuiles()[x+1][y+1]);    
        }
        if(getTuiles()[x-1][y+1]!=null){
            tuiles.put(getTuiles()[x-1][y+1].getId(), getTuiles()[x-1][y+1]);    
        }
        return tuiles;
    }
    
    public HashMap<Integer,Tuile> getTuilesAdjacentesInondees(Tuile tuile){
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        HashMap<Integer,Tuile> tuilesAdj = new HashMap<>();
        tuilesAdj = getTuilesAdjacentes(tuile);
        for(Integer i : tuilesAdj.keySet()){
            if(tuilesAdj.get(i).getEtatActuel()==Utils.EtatTuile.INONDEE){
                tuiles.put(tuilesAdj.get(i).getId(), tuilesAdj.get(i));
            }
        }
        return tuiles;
    }
    
    public HashMap<Integer,Tuile> getTuilesAdjacentesAssechees(Tuile tuile){
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        HashMap<Integer,Tuile> tuilesAdj = new HashMap<>();
        tuilesAdj = getTuilesAdjacentes(tuile);
        for(Integer i : tuilesAdj.keySet()){
            if(tuilesAdj.get(i).getEtatActuel()==Utils.EtatTuile.ASSECHEE){
                tuiles.put(tuilesAdj.get(i).getId(), tuilesAdj.get(i));
            }
        }
        return tuiles;
    }
    
    public HashMap<Integer,Tuile> getTuilesAdjacentesCoulees(Tuile tuile){
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        HashMap<Integer,Tuile> tuilesAdj = new HashMap<>();
        tuilesAdj = getTuilesAdjacentes(tuile);
        for (Integer i : tuilesAdj.keySet()){
            if(tuilesAdj.get(i).getEtatActuel()==Utils.EtatTuile.COULEE){
                tuiles.put(tuilesAdj.get(i).getId(),tuilesAdj.get(i));
            }
        }
        return tuiles;
    }
    
}
            
