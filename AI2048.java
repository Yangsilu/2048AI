/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ai2048;

import java.util.*;
import java.io.*;

/**
 *
 * @author Jason
 */
public class AI2048 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Please Choose the mode: 1 -- People, 2 -- AI:");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String b = bf.readLine();
            if (b.equals("1") || b.equals("2")) {
                AI2048 game = new AI2048();
                game.run(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void run(String mode) {
        if (mode.equals("1")) {
            this.testPeople();
        } else {
            this.testAI();
        }
    }
    
    private void testPeople() {
        System.out.print("Please Choose the Target Number(e.g. 2048):");
        Scanner scanner=new Scanner(System.in);
        String b = scanner.next();
        ChessBoard cb = new ChessBoard(Integer.valueOf(b));
        while (cb.getState() == 0) {
            cb.printBoard();
            System.out.print("Please Choose the move:");
            scanner=new Scanner(System.in);
            b = scanner.next();
            if (b.equals("w")) cb.upMove();
            if (b.equals("s")) cb.downMove();
            if (b.equals("a")) cb.leftMove();
            if (b.equals("d")) cb.rightMove();
        }
        cb.printBoard();
    }
    
    private void testAI() {
        System.out.print("Please Choose the Target Number(e.g. 2048):");
        Scanner scanner=new Scanner(System.in);
        String b = scanner.next();
        ChessBoard cb = new ChessBoard(Integer.valueOf(b));
        AISolver solver = new AISolver();
        int count = 0;
        while (cb.getState() == 0) {
            cb.printBoard();
            solver.setBoard(cb.getChessBoard());
            b = solver.minMaxAI(0.5);
            System.out.println("AI Choose the move " + b);
            count++;

           /* try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }     
            */
            if (b.equals("w")) cb.upMove();
            if (b.equals("s")) cb.downMove();
            if (b.equals("a")) cb.leftMove();
            if (b.equals("d")) cb.rightMove();
        }
        cb.printBoard();
        if (cb.getState() == 1) System.out.println("Used " + count + " Steps to Win!");
        if (cb.getState() == -1) System.out.println("Used " + count + " Steps to Lose!");
    }
}
