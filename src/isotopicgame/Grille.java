/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;


import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jvere
 */
public class Grille {

    private int taille;
    private Atome[][] grille;
    private int objectif;

    public Grille(int taille, int objectif) {
        this.taille = taille;
        this.objectif = objectif;
        grille = new Atome[taille][taille];

    }

    public void afficher() {

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != null) {
                    System.out.print("[" + grille[i][j].getNumMasse() + "]");

                    // un atome est représenté par son nombre de masse
                } else {
                    System.out.print(" " + 0 + " ");

                    // il n'y a pas d'atome le nombre de masse est 0
                }
            }

            System.out.print("\n");
        }
    }

    public void creation() {
        Random gen = new Random();
        
        //Génération d'atomes aléatoires
        Atome atome1 = new Atome(2);
        Atome atome2 = new Atome(4);
        Atome atome3 = new Atome(8);
        Atome atome4 = new Atome(16);

        int a;
        a = gen.nextInt(2);

        if (a==1){
            grille[gen.nextInt(taille)][gen.nextInt(taille)] = atome1;
        }
        else if(a ==0){
            grille[gen.nextInt(taille)][gen.nextInt(taille)] = atome2;   
        }
        
        // coliision droite
        
//        grille[0][2] = atome3;
//        grille[0][1] = atome3;
//        grille[0][taille-1] = atome3;


        //grille[gen.nextInt(taille)][gen.nextInt(taille)] = atome1;
        //grille[gen.nextInt(taille)][gen.nextInt(taille)] = atome2;
        
        // test de superposition en colonne
        
//        grille[taille - 3][3] = atome3;
//        grille[taille - 4][3] = atome4;
//        grille[taille - 1][3] = atome5;
//        grille[0][0] = atome5;
//        grille[taille-1][0] = atome5;


        
        //test de la superposition de deux atome dans une ligne
//        grille[taille - 4][3] = atome3;
//        grille[taille - 4][4] = atome4;
//        grille[taille - 4][1] = atome5;
//        grille[taille - 1][taille-1] = atome5;
        //grille[0][0] = atome5; atome qui ne bouge pas pour déplacement gauche
        
    }

    public void terrain() {
        // génération aléatoire d'un atome de numMasse 2 ou 4
        Random gen = new Random();
                
        Atome atome1 = new Atome(2);
        Atome atome2 = new Atome(4);
        int a;
        a = gen.nextInt(2);
        int x,y;
        x = gen.nextInt(taille);
        y = gen.nextInt(taille);
        
        //ATTENTION BOUCLE INFINIE SI LA GRILLE EST PLEINE
        while(grille[x][y] != null){
            x = gen.nextInt( taille);
            y = gen.nextInt(taille);
        }
            
        if (a==1){
            grille[x][y] = atome1;
        }
        else if(a ==0){
        grille[x][y] = atome2;   
        }    
        
       /*Gestion de la création d'atomes instables 
       On considère qu'un atome peut-être instable à partir de Z = 8,
        A chaque fusion au dessus de Z = 8, l'atome a un pourcentage de chance de se tranformer en un atome instable
        On pose ce pourcentage à 30%
       */
        int pourcentage_inst = 30, n; //Pourcentage de chance qu'un atome stable se tranforme en un atome instable
        int tempsVie;
        Isotope newIsotope;
        for (x = 0; x < taille; x++){
            for (y = 0; y < taille; y ++){

                //Si il y a un atome
                if(grille[x][y] != null){
                    //Si l'atome a un numéro atomique >= 8, alors il a une chance de devenir instable
                    if (grille[x][y].getNumMasse() >= 8){
                        n = gen.nextInt(100) + 1;
                        //L'atome devient instable
                        if (n <= pourcentage_inst){
                            tempsVie = gen.nextInt(10) + 10; //Le temps de vie est d'au minimum 10 coups et au max 20
                            newIsotope = new Isotope(tempsVie,grille[x][y].getNumMasse());
                            grille[x][y] = newIsotope;
                            System.out.println("l'atome en position x="+x+" y="+y+" est devenu instable");
                        } 
                    }
                }
           }
           
       }
    }

    public boolean VerifWin() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != null) {
                    if (grille[i][j].getNumMasse() == objectif) {
                        return true;
                    }
                }

            }
        }

        return false;

    }

    public void déplacementHaut() {
        // le sens de parcour de la grille et opposé à celui du mouvement des atomes pour éviter qu'il se bloque entre eux
        for (int j = 0; j < taille; j++) {
            for (int i = 0; i < taille; i++) {
                int nbatome = 0; //compteur d'atome
                int lignehaut = 0; // colonne la plus haute vide
                int colonnehaut = 0; //ligne la plus haute vide
                for (i = 0; i < taille - 1; i++) {
                    if (grille[i][j] == null) {
                        lignehaut = i;
                        colonnehaut = j; // sert a rien !!
                        System.out.println(" lignehaut :" + lignehaut + " colonne haut :" + colonnehaut);
                        break;
                    }

                }
                // x et y les coordonnées de l'atome unique
                int x = 0;
                int y = 0;
                for (i = 0; i < taille; i++) {
                    // on compte le nombre d'atome dans la colonne j
                    if (grille[i][j] != null) {
                        nbatome = nbatome + 1;
                        // on prend les coordonnées de l'unique atome si il y en a un seul
                        x = i;
                        y = j;
                    }
                }
                // isolons un cas simple ou l'atome et seul sur sa colonne donc il va automatiquement ne rien recontrer et prends la position la plus haute
                if (nbatome == 1) {
                    
                    // on bouge l'atome si celui ci n'est pas déjà a la position finale en haut
                    if(x !=0){
                        //System.out.println("le nombre d'atome est :" + nbatome);
                    Atome tamp;
                    tamp = grille[x][y];
                    grille[x][y] = null;
                    grille[lignehaut][j] = tamp;  
                    }  
                } // on traite le cas ou l'atome n'est plus seul sans colisions
                else if (nbatome > 1) {
                    //System.out.println("le nombre d'atomeS est :" + nbatome);
                    // on cherche à faire bouger tous les atomes 1 par 1
                    for (int z = 0; z <= nbatome; z++) {
                        // la ligne la plus haute est mise à jour à chaque fois que l'on bouge un atome et haut
                        for (i = lignehaut; i < taille; i++) {
                            if (grille[i][j] != null) {
                                Atome tamp;
                                tamp = grille[i][j];
                                grille[i][j] = null;
                                grille[lignehaut][j] = tamp;
                                lignehaut = lignehaut + 1;
                                //System.out.println("la nouvelle ligne la plus haute est :" + lignehaut + " pour la colonne :" + j);
                            }
                        }

                        //System.out.println("les :" + nbatome + " ont bougés");
                        // on traite le cas ou il n'y a pas qu'un seule atome dans la colonne
                    }
                }
            }
        }
    }

    public void déplacementGauche() {
        // le sens de parcour de la grille et opposé à celui du mouvement des atomes pour éviter qu'il se bloque entre eux
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                int nbatome = 0; //compteur d'atome
                int lignegauche = 0; // colonne la plus à gauche
                int colonnegauche = 0; //ligne la plus haute vide
                for (j = 0; j < taille - 1; j++) {
                    if (grille[i][j] == null) {
                        lignegauche = i;
                        colonnegauche = j;
                        //System.out.println(" lignehaut :" + lignegauche + " colonne haut :" + colonnegauche);
                        break;
                    }

                }
                // x et y les coordonnées de l'atome unique
                int x = 0;
                int y = 0;
                for (j = 0; j < taille; j++) {
                    // on compte le nombre d'atome dans la ligne i 
                    if (grille[i][j] != null) {
                        nbatome = nbatome + 1;
                        // on prend les coordonnées de l'unique atome si il y en a un seul
                        x = i;
                        y = j;
                    }
                }
                // isolons un cas simple ou l'atome et seul sur sa colonne donc il va automatiquement ne rien recontrer et prends la position la plus haute
                if (nbatome == 1) {
                    
                    // on ne bouge as l'atome si il est déjà a la position finale
                    if(y !=0){
                        //System.out.println("le nombre d'atome est :" + nbatome);
                        Atome tamp;
                        tamp = grille[x][y];
                        grille[x][y] = null;
                        grille[i][colonnegauche] = tamp;  
                    }
                    
                    
                } // on traite le cas ou l'atome n'est plus seul sans colisions
                else if (nbatome > 1) {
                    //System.out.println("le nombre d'atomeS est :" + nbatome);
                    // on cherche à faire bouger tous les atomes 1 par 1
                    for (int z = 0; z <= nbatome; z++) {
                        // la ligne la plus haute est mise à jour à chaque fois que l'on bouge un atome et haut
                        for (j = colonnegauche; j < taille; j++) {
                            if (grille[i][j] != null) {
                                Atome tamp;
                                tamp = grille[i][j];
                                grille[i][j] = null;
                                grille[i][colonnegauche] = tamp;
                                colonnegauche = colonnegauche + 1;
                                //System.out.println("la nouvelle colonne la plus à droite est :" + colonnegauche + " pour la ligne :" + i);
                            }
                        }

                        //System.out.println("les :" + nbatome + " ont bougés");
                        // on traite le cas ou il n'y a pas qu'un seule atome dans la colonne
                    }
                }
            }
        }

    }

    public void déplacementBas() {
        // le sens de parcour de la grille et opposé à celui du mouvement des atomes pour éviter qu'il se bloque entre eux

//        int nbcase;
//        nbcase = grille.length;
//        System.out.println("le nombre de case du tableau est :"+ nbcase);
        int compteur;
        compteur = taille - 1;
        //System.out.println("compteur = " + compteur);

        for (int j = compteur; j >= 0; j--) {
            for (int i = compteur; i >= 0; i--) {

                //System.out.println("la taille du tableau est "+taille);
                int nbatome = 0; //compteur d'atome
                int lignebas = 0; // colonne la plus basse vide
                int colonnebas = 0; //ligne la plus basse vide

                // transformer en boucle while
                for (i = compteur; i >= 0; i--) {
                    if (grille[i][j] == null) {
                        lignebas = i;
                        colonnebas = j;
                        //System.out.println(" ligne bas :" + lignebas + " pour la colonne:" + colonnebas);
                        break;
                    }
                }
                // x et y les coordonnées de l'atome unique
                int x = 0;
                int y = 0;
                //System.out.println("on y arrive 1 = ");
                for (i = 0; i < taille; i++) {

                    // on compte le nombre d'atome dans la colonne j
                    if (grille[i][j] != null) {
                        nbatome = nbatome + 1;
                        //System.out.println("on y arrive 3 = ");
                        // on prend les coordonnées de l'unique atome si il y en a un seul
                        x = i;
                        y = j;
                    }
                }
                // isolons un cas simple ou l'atome et seul sur sa colonne donc il va automatiquement ne rien recontrer et prends la position la plus basse
                if (nbatome == 1) {
                    
                    // on bouge l'atome si celui ci n'est sur la dernière ligne (sa position finale)
                    if( x!= taille-1){
                        //System.out.println("le nombre d'atome est :" + nbatome + "dans la colonne" + j);
                    Atome tamp;
                    tamp = grille[x][y];
                    grille[x][y] = null;
                    grille[lignebas][colonnebas] = tamp;
                        
                    }
                    
                } // on traite le cas ou l'atome n'est plus seul sans colisions
                else if (nbatome > 1) {
                    //System.out.println("le nombre d'atomeS est :" + nbatome + "dans la colonne " + j);
                    // on cherche à faire bouger tous les atomes 1 par 1
                    for (int z = 0; z <= nbatome; z++) {
                        // la ligne la plus haute est mise à jour à chaque fois que l'on bouge un atome et haut
                        for (i = lignebas; i >= 0; i--) {
                            if (grille[i][j] != null) {
                                Atome tamp;
                                tamp = grille[i][j];
                                grille[i][j] = null;
                                grille[lignebas][colonnebas] = tamp;
                                lignebas = lignebas - 1;
                                //System.out.println("la nouvelle ligne la plus haute est :" + lignebas + " pour la colonne :" + j);
                            }
                        }

                        //System.out.println("les :" + nbatome + " ont bougés");
                        // on traite le cas ou il n'y a pas qu'un seule atome dans la colonne
                    }
                } else;
                //System.out.println("pas d'atomme dans la colonne" +j);
                break;
            }
        }
    }

    public void déplacementDroite() {
        // le sens de parcour de la grille et opposé à celui du mouvement des atomes pour éviter qu'il se bloque entre eux
        int compteur;
        compteur = taille - 1;

        for (int i = 0; i < taille; i++) {
            for (int j = compteur; j >= 0; j--) {
                int nbatome = 0; //compteur d'atome
                int lignedroite = 0; // colonne la plus à droite vide
                int colonnedroite = 0; //ligne la plus à doite vide
                for (j = compteur; j >= 0; j--) {
                    if (grille[i][j] == null) {
                        lignedroite = i;
                        colonnedroite = j;
                        //System.out.println(" lignedoite :" + lignedroite + " colonnedroite :" + colonnedroite);
                        break;
                    }
                }
                // x et y les coordonnées de l'atome unique
                int x = 0;
                int y = 0;
                for (j = 0; j < taille; j++) {
                    // on compte le nombre d'atome dans la ligen i 
                    if (grille[i][j] != null) {
                        nbatome = nbatome + 1;
                        // on prend les coordonnées de l'unique atome si il y en a un seul
                        x = i;
                        y = j;
                    }
                }
                // isolons un cas simple ou l'atome et seul sur sa colonne donc il va automatiquement ne rien recontrer et prends la position la plus haute
                if (nbatome == 1) {
                    
                    // on bouge que si l'atome n'est pas déjà a la bonne position
                    if( y != taille-1){
                    //System.out.println("le nombre d'atome est :" + nbatome);
                    Atome tamp;
                    tamp = grille[x][y];
                    grille[x][y] = null;
                    grille[i][colonnedroite] = tamp;
                        
                    }
                    
                } // on traite le cas ou l'atome n'est plus seul sans colisions
                else if (nbatome > 1) {
                    //System.out.println("le nombre d'atomeS est :" + nbatome);
                    // on cherche à faire bouger tous les atomes 1 par 1
                    for (int z = 0; z <= nbatome; z++) {
                        // la ligne la plus haute est mise à jour à chaque fois que l'on bouge un atome et haut
                        for (j = colonnedroite; j >= 0; j--) {
                            if (grille[i][j] != null) {
                                Atome tamp;
                                tamp = grille[i][j];
                                grille[i][j] = null;
                                grille[lignedroite][colonnedroite] = tamp;
                                colonnedroite = colonnedroite - 1;
                                //System.out.println("la nouvelle colonne la plus à droite est :" + colonnedroite + " pour la ligne :" + i);
                            }
                        }

                        //System.out.println("les :" + nbatome + " ont bougés");
                        // on traite le cas ou il n'y a pas qu'un seul atome dans la colonne
                    }
                } else;
                //System.out.println("il n'y a pas d'atome dans cette colonne  ");
                break;
            }
        }

    }

    public void mouvement() {
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("Mouvement Possible \n  haut : 1 \n  bas : 2 \n  droite : 3 \n  gauche : 4");
        int rep = sc.nextInt();
        
        //Gestion de l'orientation du mouvement 
        switch (rep) {

            case (1):
                déplacementHaut();
                System.out.println("grille avant collisionHaut :");
                afficher();
                collisionHaut();
                System.out.println("grille après collisionHaut :");
                afficher();
                System.out.println("Aprés avoir re déplacement haut");
                déplacementHaut();

                break;
            case (2):
                déplacementBas();
                System.out.println("grille avant collisionBas :");
                afficher();
                collisionBas();
                System.out.println("grille après collisionBas :");
                afficher();
                déplacementBas();
                System.out.println("Aprés avoir re déplacement bas");
                break;
            case (3):
                déplacementDroite();
                System.out.println("grille avant collisionDroit :");
                afficher();
                collisionDroit();
                System.out.println("grille après collisionDroit :");
                afficher();
                déplacementDroite();
                System.out.println("Aprés avoir rebougé");
                break;
            case (4):
                déplacementGauche();
                System.out.println("grille avant collisionGauche :");
                afficher();
                collisionGauche();
                System.out.println("grille après collisiongauche :");
                afficher();
                déplacementGauche();
                System.out.println("grille après le re déplacement gauche :");
                break;

            default:
                System.out.println("choix non valide");
        
        }

        //Gestion des atomes instables
        for(int i = 0; i<taille; i++){
            for(int j = 0; j<taille; j++){
                if (grille[i][j] != null && grille[i][j].getAtomeType() == "Instable"){
                    grille[i][j].reduireTempsVie();
                    System.out.println("Il reste "+grille[i][j].getTempsVie()+" coups à l'atome en position x = "+i+" y = "+j);
                    if (grille[i][j].getIsDisparu()){
                        grille[i][j].reduireTempsVie();
                        System.out.println("Il reste "+grille[i][j].getTempsVie()+" coups à l'atome en position x = "+i+" y = "+j);
                    }
                }
                
            } 
        }
    }

    public void collisionHaut() {
        for (int i = 1; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != null & i > 0 & grille[i - 1][j] != null) {
                    if (grille[i - 1][j].getNumMasse() == grille[i][j].getNumMasse()) {
                        int sommeMasse;
                        sommeMasse = grille[i - 1][j].getNumMasse() * 2;
                        Atome tamp = new Atome(sommeMasse);
                        grille[i][j] = null;
                        grille[i - 1][j] = tamp;

                    }
                }

            }
        }

    }

    public void collisionBas() {
        for (int i = taille - 2; i >= 0; i--) {
            for (int j = 0; j < taille; j++) {
                if (grille[i][j] != null & i < taille - 1 & grille[i + 1][j] != null) {
                    if (grille[i + 1][j].getNumMasse() == grille[i][j].getNumMasse()) {
                        int sommeMasse;
                        sommeMasse = grille[i + 1][j].getNumMasse() * 2;
                        Atome tamp = new Atome(sommeMasse);
                        grille[i][j] = null;
                        grille[i + 1][j] = tamp;
                    }
                }

            }
        }

    }

    public void collisionDroit() {
        for (int i = 0; i < taille ; i++) {
            for (int j = taille - 2; j > 0; j--) {
                if (grille[i][j] != null & j > 0 & grille[i][j + 1] != null) {
                    if (grille[i][j + 1].getNumMasse() == grille[i][j].getNumMasse()) {
                        int sommeMasse;
                        sommeMasse = grille[i][j + 1].getNumMasse() * 2;
                        Atome tamp = new Atome(sommeMasse);
                        grille[i][j] = null;
                        grille[i][j + 1] = tamp;
                    }
                }

            }
        }

    }
    
    public void collisionGauche() {
        for (int i = 0; i < taille; i++) {
            for (int j = 1; j < taille - 1; j++) {
                if (grille[i][j] != null & j > 0 & grille[i][j - 1] != null) {
                    if (grille[i][j - 1].getNumMasse() == grille[i][j].getNumMasse()) {
                        int sommeMasse;
                        sommeMasse = grille[i][j - 1].getNumMasse() * 2;
                        Atome tamp = new Atome(sommeMasse);
                        grille[i][j] = null;
                        grille[i][j - 1] = tamp;
                    }
                }

            }
        }

    }

}
