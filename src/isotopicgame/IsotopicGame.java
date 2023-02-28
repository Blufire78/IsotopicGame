/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isotopicgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jvere
 */
public class IsotopicGame {

    /**
     * @param args the command line arguments
     * 
     *  // Reprendre la partie ou créer une nouvelle dans les menu
    // faire apparaitre un élément à chaque tour
    // faire disparaitre les isotopes
    // vérifier si la partie et finit (le cas ou il atteint son objectif ou le cas ou le joueur ne peu plus joueur / et fais une pause en faisant une suvegarde du fichier)
    // faire 4 méthodes déplacemment haut / bas/droit/gauche
    // rendre la version intermédaire sans interface graphique

    * menu avec un vrai chrono
    * bruitage 
    * 
    * On ne peut pas faire bouger les atomes par groupe car cela empèche la collision entre eux
    * Mettre un try catch pour la valeur de fin pou que celle ci soit un multiple de deux avec le modulo
    * Mettre un try catch pour la valeur rentrer par le joueur soit bien un entier de 1 a 4
    * ajouter le timer
    * ajouter le fait que si la grille est pleine et on ne peut plus faire de coliision alors la parte est finie
    * 
     */
    
        public static void main(String[] args) throws IOException {
            
            //Récupération des différents fichiers
            
                //Fichier Joueurs
            ArrayList<Joueur> joueurs = Joueur.depuisFichier();  //On récupère la liste des joueurs et leurs informations
            
            Scanner sc;
            sc = new Scanner(System.in);
            Joueur joueur;
            System.out.println("Entrez votre pseudo: ");
            String pseudo = sc.nextLine();
            
            if(Joueur.exist(pseudo, joueurs)){
                joueur = Joueur.chercherJoueur(pseudo, joueurs);
                joueur.changerObjectif(); //
            }
            else{
                System.out.println("Quel est votre objectif de numéro atomique: ");
                joueur = new Joueur(pseudo, sc.nextInt());
            }
            
            System.out.println("création de la partie");
            System.out.println("Entrer la taille du tableau");
            int taille = sc.nextInt();
           
            
            //Random gen = new Random();
            
            
            // test random
            //int x = gen.nextInt(taille);
            //System.out.println(x);
            int objectif = joueur.getObjectif();

            Grille grille = new Grille(taille,objectif);
            grille.creation();  // j'arriva pas à actualiser la taille de la grille
            grille.afficher();
            
            while( grille.VerifWin() != true){
                grille.mouvement();
                grille.terrain();
                grille.afficher();
                grille.VerifWin();
                
            }
            
            // pour l'instant on dit qu'il gagne tout le temps
            System.out.println("Félicitation objectif atteint !!!");
            joueur.versFichier(joueurs);
                       
        }
        
    }
    
