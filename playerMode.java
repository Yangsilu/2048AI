/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ai2048;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author silu
 */
public class playerMode {


    public static void test(String[] args) {
        // TODO code application logic here
                ChessBoard c1 = new ChessBoard(2048);

       //char mov;
       
       while(c1.getState()==0) {
           System.out.println("your_move:?");
        
        //playerMode p1 = new playerMode
            Scanner objScanner = new Scanner(System.in);
            char mov = objScanner.next().charAt(0);

            if (mov=='a'){
                c1.leftMove();
               }
            
            else if (mov=='w') {
              c1.upMove();
            }
            else if (mov=='s'){
               c1.downMove();            
            }
            else{
                c1.rightMove();         
            }
            c1.printBoard();
           
       }        

        
    }
    
}
