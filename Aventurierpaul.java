package model.aventuriers;

import java.util.HashMap;
import java.util.Scanner;
import model.ObjetIdentifie;
import model.cartes.CarteTirage;
import model.cases.Grille;
import model.cases.Tuile;
import util.Utils.Pion;
import util.Utils.Tresor;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier extends ObjetIdentifie {
    
    private float nbAction;
    private Pion role;
    private HashMap<Integer,CarteTirage> possede;
    private Tuile positionCourante;
    
    public Aventurier(float nbAction,Pion role){
        this.nbAction=nbAction;
        this.role=role;
        this.positionCourante=null;       
}
    
    public void removeCarte(CarteTirage carte){
        possede.remove(carte.getId());
    }
    
    public void addCarte(CarteTirage carte){
        possede.put(carte.getId(), carte);
    }
    
    public void defausseCarte(Tresor carte){
        
   
    }

    public Pion getRole() {
        return role;
    }

    public Tuile getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Tuile positionCourante) {
        this.positionCourante = positionCourante;
    }
    
    //action de se deplacer
    public void seDeplacer(Grille grille){
        setPositionCourante(choisirTuileDestination(getDeplacementsPossibles(grille)));
        choisirTuileDestination(getDeplacementsPossibles(grille)).addJoueurCourant(this);
    }
    
    //retourne une collection de tuiles sur lesquelles on peut se deplacer
    public HashMap<Integer,Tuile> getDeplacementsPossibles(Grille grille){
        return grille.getTuilesAdjacentes(positionCourante);
    }
    
    //choix d'une tuile a partir d'une collection de tuile
    public Tuile choisirTuileDestination(HashMap<Integer,Tuile> tuiles){
        int id = 0;
        return tuiles.get(id);     
    }
    
}
