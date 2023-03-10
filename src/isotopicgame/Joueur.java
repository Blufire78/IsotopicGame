/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jvere
 */
public class Joueur {
    private String pseudo;
    private int score,objectif, timer;
    private static final String NOM_FICHIER_JOUEURS = "Joueurs.txt";
    private Grille grille; //Grille relative au joueur
    
    /* Constructeurs
    ----------------------------------------------------------------------
    */
    public Joueur(String pseudo, int objectif, Grille grille) {
        this.pseudo = pseudo;
        this.score = 0;
        this.objectif = objectif;
        this.timer = 0;
        this.grille = grille;
        this.grille.creation();
    }

    public Joueur(String pseudo, int objectif, int score, int timer, Grille grille) {
        this.pseudo = pseudo;
        this.score = score;
        this.objectif = objectif;
        this.timer = timer;
        this.grille = grille;
    }
    /*
    ----------------------------------------------------------------------
    */
    
    
    
    /* Méthodes getter
    ----------------------------------------------------------------------
    */
    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public int getObjectif() {
        return objectif;
    }

    public Grille getGrille() {
        return grille;
    }
    
    
    /*
    ----------------------------------------------------------------------
    */
    
    public String jouerTour(){
        //Fonction qui sert à jouer un tour
        String retour = this.grille.mouvement();
        this.grille.terrain();
        return retour;
    }
    
    public boolean VerifWin() {
        for (int i = 0; i < this.grille.getTaille(); i++) {
            for (int j = 0; j < this.grille.getTaille(); j++) {
                if (this.grille.getGrille()[i][j] != null) {
                    if (this.grille.getGrille()[i][j].getNumMasse() == this.objectif) {
                        return true;
                    }
                }

            }
        }

        return false;

    }
    
    public static boolean exist(String pseudo, ArrayList <Joueur> joueurs){
        //Vérifie si le joueur est existant ou non
        pseudo = pseudo.toLowerCase(); //On met le pseudo entré par l'utilisateur en minuscule pour le comparer aux autres et éviter les majuscules 
        System.out.println(pseudo);
        for (Joueur joueur: joueurs){
            if (joueur.getPseudo().toLowerCase().equals(pseudo)){
                return true;
            }
        }
        return false;  
    }
    
    public void versFichier(ArrayList<Joueur> joueurs) throws IOException{
        //Ajoute les joueurs et leurs informations dans le fichier Joueurs

        FileWriter fich = new FileWriter(NOM_FICHIER_JOUEURS); //Créer ou ouvre un fichier en mode écriture (si le fichier existe, le supprime et en créer un nouveau)
        for (Joueur joueur : joueurs) {
            String ch = joueur.versFichier();
            fich.write(ch);   //Ecris dans le fichier
        }
        fich.close();
    }
        
    public String versFichier() throws IOException{
        //Retourne les informations d'un joueur à ajouter dans le fichier Joueurs
        return this.pseudo + ";" + this.score + ";" + this.objectif + ";" + this.timer + System.lineSeparator();
    }
    public static ArrayList<Joueur> depuisFichier() throws FileNotFoundException, IOException{
        ArrayList <Joueur> joueurs = new ArrayList<Joueur>();
        try{
            FileReader fich = new FileReader(NOM_FICHIER_JOUEURS);
            BufferedReader br = new BufferedReader(fich);
            String line = br.readLine();
            String[] lineArray;
            while (line != null){
                lineArray = line.split(";");
                joueurs.add(new Joueur(lineArray[0], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[3]), Grille.depuisFichier().get(0)));            
                line = br.readLine();
            }
            fich.close();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return joueurs;
    }
    
    public static Joueur chercherJoueur(String pseudo, ArrayList <Joueur> joueurs){
        pseudo = pseudo.toLowerCase(); //On met le pseudo entré par l'utilisateur en minuscule pour le comparer aux autres et éviter les majuscules 
        Joueur joueurRetourne = new Joueur("",0, new Grille(1)); //On initialise faussement le joueur à retourner
        for (Joueur joueur: joueurs){
            if (joueur.getPseudo().toLowerCase().equals(pseudo)){
                joueurRetourne = joueur;
            }
        }
        return joueurRetourne;
    }
    
    public boolean verifObjectif(){
        // Vérifie si un joueur a atteint son objectif
        if (this.score >= this.objectif){
            return true;
        }
        return false;
    }
    
    public void changerObjectif(){
        if (this.verifObjectif()){
            Scanner sc;
            sc = new Scanner(System.in);
            System.out.println("Vous avez déjà atteint votre objectif précédemment. Souhaitez-vous l'augmenter? \n1.Oui \n2.Non");
            
            
            switch(sc.nextInt()){
                case(1):
                    System.out.println("D'accord, à combien voulez-vous le placer:");
                    this.objectif = sc.nextInt();
                    System.out.println("Très bien, objectif placé à "+this.objectif);
                    break;
                
                case(2):
                    System.out.println("Très bien. Vous restez sur un objectif de "+this.objectif);
                    break;
      
                default:
                    System.out.println("choix non valide");
            }
            
        }
    }
    
    
    
    
}
