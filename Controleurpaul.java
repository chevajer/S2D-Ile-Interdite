package controler;

import static java.awt.PageAttributes.MediaType.A;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import model.ObjetIdentifie;
import model.aventuriers.Aventurier;
import model.aventuriers.Explorateur;
import model.aventuriers.Ingenieur;
import model.aventuriers.Messager;
import model.aventuriers.Navigateur;
import model.aventuriers.Pilote;
import model.aventuriers.Plongeur;
import model.cartes.CarteHelicoptere;
import model.cartes.CarteInondation;
import model.cartes.CarteMonteeDesEaux;
import model.cartes.CarteSacsDeSable;
import model.cartes.CarteTirage;
import model.cartes.CarteTresor;
import model.cases.Grille;
import model.cases.Tuile;
import util.Utils;
import view.MessageBox;
import view.VueAventurier;
import view.VueInscription;
import view.VueNiveau;
import view.VuePlateau;
import util.Utils;
import util.Utils.EtatTuile;
import util.Utils.Pion;
import static util.Utils.melangerPositions;
import static util.Utils.melangerAventuriers;

/**
 *
 * @author IUT2-Dept Info
 */
public class Controleur implements Observer {
    /*IHM*/
    private ArrayList<VueAventurier> vuesAventurier;
    private VueInscription vueInscription;
    private VueNiveau vueNiveau;
    private MessageBox messagebox;
    private VuePlateau vuePlateau;
    /*MODELE*/
    /*niveau de l'eau*/
    private Integer indiceEchelleNiveauEau = 1;
    private Integer niveauEau = 2;
    /*Aventuriers*/
    private ArrayList<Aventurier> aventuriers;
    /*Cartes inondation*/
    private HashMap<Integer,CarteInondation> piocheCartesInondation;
    private HashMap<Integer,CarteInondation> defausseCartesInondation;
    /*Cartes Tirage*/
    private HashMap<Integer,CarteTirage> piocheCartesTirage;
    private HashMap<Integer,CarteTirage> defausseCartesTirage;
    /*PLATEAU*/
    private Grille grille;
    private boolean miseEnPlace = false;
    

    public Controleur() {
        //plateau
        this.grille = new Grille();
        //aventuriers
        this.aventuriers = new ArrayList<>();
        //cartes inondation
        this.piocheCartesInondation = new HashMap<>();
        this.defausseCartesInondation = new HashMap<>();
        //cartes tirage
        this.piocheCartesTirage = new HashMap<>();
        this.defausseCartesTirage = new HashMap<>();
           
        initialiserJeu();
        
    }


    @Override
    public void update(Observable o, Object arg) {
    }
    
    public void initialiserJeu(){
        initialiserAventuriers();
        initialiserPlateau();
        initialiserCartes();
        
        miseEnPlace();
        
        setMiseEnPlace(true);
    }
    
    public void initialiserAventuriers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Combien de joueurs ? ");
        //nb de joueurs ?
        int nbJoueurs = sc.nextInt();
        while (nbJoueurs<2 || nbJoueurs>4){
             System.out.println("Cb de joueurs ? ");
             nbJoueurs = sc.nextInt();
        }
        //creation de tous les roles
        Aventurier a1 = new Pilote(3, Pion.BLEU);
        getAventuriers().add(a1);
        Aventurier a2 = new Navigateur(4, Pion.JAUNE);
        getAventuriers().add(a2);
        Aventurier a3 = new Ingenieur(3, Pion.ROUGE);
        getAventuriers().add(a3);
        Aventurier a4 = new Explorateur(3, Pion.VERT);
        getAventuriers().add(a4);
        Aventurier a5 = new Messager(3, Pion.ORANGE);
        getAventuriers().add(a5);
        Aventurier a6 = new Plongeur(3, Pion.VIOLET);
        getAventuriers().add(a6);
        
        //on melange les roles et on ne prend que les nbJoueurs premiers
        ArrayList<Aventurier> tousAventuriers = melangerAventuriers(getAventuriers());
        for (int i=0; i<nbJoueurs; i++){
            getAventuriers().add(tousAventuriers.get(i));
//            System.out.println("Roles : ");
//            System.out.println("- "+getAventuriers().get(i).getRole().toString());
        }      
    }
    
    public void initialiserCartes(){
        //cartes inondations
        HashMap<Integer,CarteInondation> cartesInondations = new HashMap<>();
        Integer[] indicesCartesInondations = new Integer[24];
        
        int i = 0;
        for (int x=0; x<6; x++){
            for (int y=0; y<6; y++){
                if (!((x==0&&y==0)||(x==0&&y==1)||
                    (x==0&&y==4)||(x==0&&y==5)||
                    (x==1&&y==0)||(x==1&&y==5)||
                    (x==4&&y==0)||(x==4&&y==5)||
                    (x==5&&y==0)||(x==5&&y==1)||
                    (x==5&&y==4)||(x==5&&y==5))){
                    CarteInondation c = new CarteInondation(getGrille().getTuiles()[x][y]);
                    cartesInondations.put(c.getId(), c);
                    indicesCartesInondations[i]=c.getId();
                    i++;
                }
            }
        }
        melangerPositions(indicesCartesInondations);
        for (int x=0; x<24; x++){
            piocheCartesInondation.put(indicesCartesInondations[x],cartesInondations.get(indicesCartesInondations[x]));
        }
        
        //cartes tirages
        HashMap<Integer,CarteTirage> cartesTirages = new HashMap<>();
        Integer[] indicesCartesTirages = new Integer[27];
        //tresors
        i=0;
        for (int x=0; x<20; x++){
            CarteTresor c = new CarteTresor();
            //on ajoute à la pioche
            cartesTirages.put(c.getId(),c);
            switch(x){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    c.setTresor(Utils.Tresor.PIERRE);
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    c.setTresor(Utils.Tresor.ZEPHYR);
                    break;
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    c.setTresor(Utils.Tresor.CRISTAL);
                    break;
                default:
                    c.setTresor(Utils.Tresor.CALICE); 
            }
        }
        //helicopteres
        for (int x=0; x<3; x++){
            CarteHelicoptere c = new CarteHelicoptere();
            cartesTirages.put(c.getId(), c);
        }
        //sacs de sable
        for (int x=0; x<2; x++){
            CarteSacsDeSable c = new CarteSacsDeSable();
            cartesTirages.put(c.getId(), c);
        }
        //montee des eaux
        for (int x=0; x<2; x++){
            CarteMonteeDesEaux c = new CarteMonteeDesEaux();
            cartesTirages.put(c.getId(), c);
        }
        //melange des cartes tirages
        i=0;
        for (Integer id : cartesTirages.keySet()){
            indicesCartesTirages[i]=id;
            i++;
        }
        melangerPositions(indicesCartesTirages);
        for (int x=0; x<27; x++){
            getPiocheCartesTirage().put(indicesCartesTirages[x], cartesTirages.get(indicesCartesTirages[x]));
        }
    }          
    
    public void initialiserPlateau(){
        
        HashMap<Integer,Tuile> tuiles = new HashMap<>();
        Integer[] indiceTuile = new Integer[24];
        
        for (int i=0; i<24; i++){
            /*créations des tuiles*/
            Tuile t = new Tuile();   
            switch(t.getId()){
                case 1:
                    t.setDepart(Utils.Pion.BLEU);
                    t.setHeliport(true);
                    if (isHere(Pion.BLEU)){
                        t.addJoueurCourant(getAventurierPion(Pion.BLEU));
                        getAventurierPion(Pion.BLEU).setPositionCourante(t);
                    }
                    break;
                case 2:
                    t.setDepart(Utils.Pion.JAUNE);
                    if (isHere(Pion.JAUNE)){
                        t.addJoueurCourant(getAventurierPion(Pion.JAUNE));
                        getAventurierPion(Pion.JAUNE).setPositionCourante(t);
                    }
                    break;
                case 3:
                    t.setDepart(Utils.Pion.ROUGE);
                    if (isHere(Pion.ROUGE)){
                        t.addJoueurCourant(getAventurierPion(Pion.ROUGE));
                        getAventurierPion(Pion.ROUGE).setPositionCourante(t);
                    }
                    break;
                case 4:
                    t.setDepart(Utils.Pion.VERT);
                    if (isHere(Pion.VERT)){
                        t.addJoueurCourant(getAventurierPion(Pion.VERT));
                        getAventurierPion(Pion.VERT).setPositionCourante(t);
                    }
                    break;
                case 5:
                    t.setDepart(Utils.Pion.ORANGE);
                    if (isHere(Pion.ORANGE)){
                        t.addJoueurCourant(getAventurierPion(Pion.ORANGE));
                        getAventurierPion(Pion.ORANGE).setPositionCourante(t);
                    }
                    break;
                case 6:
                    t.setDepart(Utils.Pion.VIOLET);
                    if (isHere(Pion.VIOLET)){
                        t.addJoueurCourant(getAventurierPion(Pion.VIOLET));
                        getAventurierPion(Pion.VIOLET).setPositionCourante(t);
                    }
                    break;
                case 7:
                case 8:
                    t.setTresor(Utils.Tresor.CRISTAL);
                    break;
                case 9:
                case 10:
                    t.setTresor(Utils.Tresor.ZEPHYR);
                    break;
                case 11:
                case 12:
                    t.setTresor(Utils.Tresor.PIERRE);
                    break;
                case 13:
                case 14:
                    t.setTresor(Utils.Tresor.CALICE);
                    break;
                default: /*rien*/
            }
            tuiles.put(t.getId(), t);
            indiceTuile[i]=t.getId();
        }
        /*on melange le tableau*/
        melangerPositions(indiceTuile);
        int k = 0;
        for (int x=0; x<6; x++){
            for (int y=0; y<6; y++){
                if (!((x==0&&y==0)||(x==0&&y==1)||
                    (x==0&&y==4)||(x==0&&y==5)||
                    (x==1&&y==0)||(x==1&&y==5)||
                    (x==4&&y==0)||(x==4&&y==5)||
                    (x==5&&y==0)||(x==5&&y==1)||
                    (x==5&&y==4)||(x==5&&y==5))){
                    getGrille().getTuiles()[x][y]=tuiles.get(indiceTuile[k]);
                    //System.out.println("num apres melange "+getGrille().getTuiles()[x][y].getId());
                    //System.out.println("dep ? "+getGrille().getTuiles()[x][y].getDepart().toString());
                    k++;
                }
            }
        }
    }
    
    public void miseEnPlace(){
        //piocher 6 cartes inondation
        for (int x=0; x<6; x++){
            piocherUneCarteInondation();
        }
        //distribuez 2 cartes tirage a chaque joueur
        for (Aventurier a : getAventuriers()){
            piocherUneCarteTirage(a);
            piocherUneCarteTirage(a);
        }
        
    }
    
    /*try{throw new Exception}
    catch (Exception ex){
    
}
   */ 
    public void piocherUneCarteInondation(){
        //on verifie si la pile n'est pas vide
        if (piocheCartesInondation.isEmpty()){
            plusDeCartesInondation();
            //melange !!
        }
        //tirer une carte de manière aléatoire
        ArrayList<Integer> keysAsArray = new ArrayList<>(piocheCartesInondation.keySet());
        Random r = new Random();
        CarteInondation carteInondationAleatoire = new CarteInondation((piocheCartesInondation.get(keysAsArray.get(r.nextInt(keysAsArray.size())))).getTuile());
        //on change l'etat d la tuile
        if (carteInondationAleatoire.getTuile().getEtatActuel()==EtatTuile.ASSECHEE){
            carteInondationAleatoire.getTuile().setEtatActuel(EtatTuile.INONDEE);
            //on l'ajoute a la défausse
            getDefausseCartesInondation().put(carteInondationAleatoire.getId(), carteInondationAleatoire);
        }
        else{
            carteInondationAleatoire.getTuile().setEtatActuel(EtatTuile.COULEE);
            if (carteInondationAleatoire.getTuile().isHeliport()){
                defaitePartie();
            }
        }
        //on retire la carte inondation de la pioche
        piocheCartesInondation.remove(carteInondationAleatoire.getId());
        
    }
    
    public void piocherUneCarteTirage(Aventurier a){
        //on verifie si la pile n'est pas vide
        if (getPiocheCartesTirage().isEmpty()){
            plusDeCartesTirage();
            //melange !!
        }
        ArrayList<Integer> keysAsArray = new ArrayList<>(getPiocheCartesTirage().keySet());
        Random r = new Random();
        CarteTirage carteTirageAleatoire = getPiocheCartesTirage().get(keysAsArray.get(r.nextInt(keysAsArray.size())));
        //test si c'est une carte montee du niveau de l'eau
        if (isMiseEnPlace()){
            if (carteTirageAleatoire.getId()==74 || carteTirageAleatoire.getId()==75){
                monteeDuNiveau();
            }
            else{
                a.addCarte(carteTirageAleatoire);     
                getPiocheCartesTirage().remove(carteTirageAleatoire.getId());
            }
        }
        else{
            if (carteTirageAleatoire.getId()==74 || carteTirageAleatoire.getId()==75){
                piocherUneCarteTirage(a);
            }
            else{
                a.addCarte(carteTirageAleatoire);     
                getPiocheCartesTirage().remove(carteTirageAleatoire.getId());
            }
            
            getDefausseCartesTirage().put(carteTirageAleatoire.getId(), carteTirageAleatoire);
        }
    }
    
    public void monteeDuNiveau(){
        setIndiceEchelleNiveauEau(getIndiceEchelleNiveauEau()+1);
        switch(getIndiceEchelleNiveauEau()){
                case 3:
                    setNiveauEau(getNiveauEau()+1);
                    break;
                case 6:
                    setNiveauEau(getNiveauEau()+1);
                    break;
                case 8:
                    setNiveauEau(getNiveauEau()+1);
                    break;
                case 10:
                    setNiveauEau(getNiveauEau()+1);
                    //DEFAITE
                    break;
                default://rien
        }
    }
    
    public void phaseInondation(){
        for(int x=0; x<getNiveauEau(); x++){
            piocherUneCarteInondation();
        }
    }
    
    public void phaseTirage(Aventurier a){
        for(int x=0; x<2; x++){
            piocherUneCarteTirage(a);
        }
    }
    
    public void plusDeCartesInondation(){
        setPiocheCartesInondation(getDefausseCartesInondation());
        getDefausseCartesInondation().clear();
        melangerPiocheInondation();
    }
    
    public void plusDeCartesTirage(){
        setPiocheCartesTirage(getDefausseCartesTirage());
        getDefausseCartesTirage().clear();
        melangerPiocheTirage();
    }
    
    public void melangerPiocheInondation(){
        Integer[] indicesInondation = new Integer[26];
        HashMap<Integer,CarteInondation> pioche = new HashMap<>();
        int x=0;
        for (Integer id : piocheCartesInondation.keySet()){
            indicesInondation[x]=id;
            x++;
        }
        melangerPositions(indicesInondation);
        for(Integer id : indicesInondation){
            pioche.put(id,piocheCartesInondation.get(id));
        }
        setPiocheCartesInondation(pioche);
    }
    
    public void melangerPiocheTirage(){
        Integer[] indicesTirage = new Integer[0];
        HashMap<Integer,CarteTirage> pioche = new HashMap<>();
        int x=0;
        for (Integer id : getPiocheCartesTirage().keySet()){
            indicesTirage[x]=id;
            x++;
        }
        melangerPositions(indicesTirage);
        for(Integer id : indicesTirage){
            pioche.put(id,getPiocheCartesTirage().get(id));
        }
        setPiocheCartesTirage(pioche);
    }
    
    
    public void defaitePartie(){
        System.out.println("PERDU");
        System.exit (0);//on arrete
    }

    public Grille getGrille() {
        return grille;
    }

    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }
    
    public HashMap<Integer, CarteInondation> getPiocheCartesInondation() {
        return piocheCartesInondation;
    }

    public HashMap<Integer, CarteInondation> getDefausseCartesInondation() {
        return defausseCartesInondation;
    }

    public HashMap<Integer, CarteTirage> getPiocheCartesTirage() {
        return piocheCartesTirage;
    }

    public HashMap<Integer, CarteTirage> getDefausseCartesTirage() {
        return defausseCartesTirage;
    }

    public Integer getNiveauEau() {
        return niveauEau;
    }

    public Integer getIndiceEchelleNiveauEau() {
        return indiceEchelleNiveauEau;
    }

    public boolean isMiseEnPlace() {
        return miseEnPlace;
    }
    
    public boolean isHere(Pion pion){
        boolean isHere = false;
        for (Aventurier a : getAventuriers()){
            if (a.getRole()==pion){
                isHere=true;
            }
        }
        return isHere;
    }
    
    public Aventurier getAventurierPion(Pion pion){
        Aventurier aventurier=null;
        for (Aventurier a : getAventuriers()){
            if (a.getRole()==pion){
                aventurier=a;
            }
        }
        return aventurier;
    }
    
    
    
    

    public void setAventuriers(ArrayList<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
    }

    public void setPiocheCartesInondation(HashMap<Integer, CarteInondation> piocheCartesInondation) {
        this.piocheCartesInondation = piocheCartesInondation;
    }

    public void setDefausseCartesInondation(HashMap<Integer, CarteInondation> defausseCartesInondation) {
        this.defausseCartesInondation = defausseCartesInondation;
    }

    public void setPiocheCartesTirage(HashMap<Integer, CarteTirage> piocheCartesTirage) {
        this.piocheCartesTirage = piocheCartesTirage;
    }

    public void setDefausseCartesTirage(HashMap<Integer, CarteTirage> defausseCartesTirage) {
        this.defausseCartesTirage = defausseCartesTirage;
    }

    public void setNiveauEau(Integer niveauEau) {
        this.niveauEau = niveauEau;
    }

    public void setIndiceEchelleNiveauEau(Integer indiceEchelleNiveauEau) {
        this.indiceEchelleNiveauEau = indiceEchelleNiveauEau;
    }

    public void setMiseEnPlace(boolean miseEnPlace) {
        this.miseEnPlace = miseEnPlace;
    }
    
    
    
}