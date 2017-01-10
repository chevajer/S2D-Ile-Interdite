package model.cases;

import java.util.HashMap;
import model.ObjetIdentifie;
import model.aventuriers.Aventurier;
import util.Utils.EtatTuile;
import util.Utils.Pion;
import util.Utils.Tresor;

/**
 *
 * @author IUT2-Dept Info
 */
public class Tuile extends ObjetIdentifie {
    
    private EtatTuile etatActuel;
    private HashMap<Integer,Aventurier> aventuriersCourants;
    private Pion depart;
    private Tresor tresor;
    private boolean heliport = false;
    
    public Tuile(){
        this.etatActuel=EtatTuile.ASSECHEE;
        //this.aventuriersCourants.clear();
        this.depart=null;
        this.tresor=null;
        /*depart et tresor en fc de l'id*/
    }
    
    

    public EtatTuile getEtatActuel() {
        return etatActuel;
    }

    public Pion getDepart() {
        return depart;
    }

    public Tresor getTresor() {
        return tresor;
    }

    public boolean isHeliport() {
        return heliport;
    }
    
    public void setEtatActuel(EtatTuile etatActuel) {
        this.etatActuel = etatActuel;
    }

    public void setDepart(Pion depart) {
        this.depart = depart;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }

    public void setHeliport(boolean heliport) {
        this.heliport = heliport;
    }
    
    /*enlever aventurier*/
    
    public void removeAventurierCourant(Aventurier aventurier){
        aventuriersCourants.remove(aventurier.getId());
    }
        
    public void removeAventuriersCourants(HashMap<Integer,Aventurier> aventuriers){
        for (Integer i : aventuriers.keySet()){
            removeAventurierCourant(aventuriers.get(i)); 
        }
    }
    
    /*ajouter aventuriers*/
        
    public void addJoueurCourant(Aventurier aventurier){
        aventuriersCourants.put(aventurier.getId(), aventurier);
    }
    
    public void addJoueursCourants(HashMap<Integer,Aventurier> aventuriers){
        for (Integer i : aventuriers.keySet()){
            addJoueurCourant(aventuriers.get(i));    
        }       
    }
    
    /*public HashMap<Integer,Tuile> getTuilesAdjacentes(){
        
    }
    
    public HashMap<Integer,Tuile> getTuilesDiagonales(){
        
    }
    */
    
        
    }
  
