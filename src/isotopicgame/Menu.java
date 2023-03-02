/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isotopicgame;

import java.util.Scanner;

/**
 *
 * @author vtabaste
 */
public class Menu {
    
    private String window;
    
    public Menu(){
        this.window = "OPTIONS";
    }

    public String getWindow() {
        return window;
    }
    
    
    public void options(){
        System.out.println("MENU");
        System.out.println("1. Scoreboard \n 2. Son");
        Scanner sc;
        sc = new Scanner(System.in);
        int rep = sc.nextInt();
        
        
    }
    
    
}
