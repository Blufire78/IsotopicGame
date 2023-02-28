/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;

/**
 *
 * @author jvere
 */
public class Atome {
    private int numMasse;

    public Atome(int numMasse) {
        this.numMasse = numMasse;
        
    }

    public int getNumMasse() {
        return numMasse;
    }

    public void reduireTempsVie(){
        
    }
    
    public boolean isDisparu(){
        return false;
    }
    
    public int getTempsVie(){
        return 0;
    }
    
    public String getAtomeType(){
        return "Stable";
    }
}
