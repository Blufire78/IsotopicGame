/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;

import java.time.LocalTime;

/**
 *
 * @author jvere
 */
public class Joueur {
    private String pseudo;
    private int score,objectif;
    LocalTime timer;

    public Joueur(String pseudo, int score, int objectif, LocalTime timer) {
        this.pseudo = pseudo;
        this.score = score;
        this.objectif = objectif;
        this.timer = timer;
    }
    
    
    
    
}
