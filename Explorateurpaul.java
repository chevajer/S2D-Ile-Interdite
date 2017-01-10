/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventuriers;

import java.util.HashMap;
import model.cases.Grille;
import model.cases.Tuile;
import util.Utils;

/**
 *
 * @author bavazzpa
 */
public class Explorateur extends Aventurier{
    
    public Explorateur(float nbAction, Utils.Pion role) {
        super(nbAction, role);
    }
    
    @Override
    public HashMap<Integer,Tuile> getDeplacementsPossibles(Grille grille){
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        tuiles.putAll(grille.getTuilesAdjacentes(getPositionCourante()));
        tuiles.putAll(grille.getTuilesDiagonales(getPositionCourante()));
        return tuiles;
    }
    
}
