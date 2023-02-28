/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;

/**
 *
 * @author jvere
 */
public class Isotope extends Atome{
    private int tempsVie; //Nombre de coups restants avant la disparition de l'atome

    public Isotope(int tempsVie, int numMasse) {
        super(numMasse);
        this.tempsVie = tempsVie;
    }
    
    public void reduireTempsVie(){
        this.tempsVie --;
    }
    
    public String getAtomeType(){
        return "Instable";
    }
}
