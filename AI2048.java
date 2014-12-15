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
        System.out.print("Please Choose the mode: 1 -- People, 2 -- AI, 3 --BatAI :");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String b = bf.readLine();
            if (b.equals("1") || b.equals("2") ||b.equals("3")) {
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
            if (mode.equals("2")) {
                this.testAI();
            } else {
                this.testBatAI();
            }
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
        AISolver solver = new AISolver(0.2, 2, 1);
        int count = 0;
        while (cb.getState() == 0) {
            cb.printBoard();
            solver.setBoard(cb.getChessBoard());
            b = solver.minMaxAI();
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
    
    private void testBatAI() {
        System.out.print("Please type in n, d, r, heu, target:");
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        double r = scanner.nextDouble();
        int heu = scanner.nextInt();
        int target = scanner.nextInt();
        long start = System.currentTimeMillis();
        int success = 0;
        int step = 0;
        int[] stop = new int[13];
        
        for (int i = 0; i < n; i++) {
            
            System.out.println(i);
            ChessBoard cb = new ChessBoard(target);
            AISolver solver = new AISolver(r, d, heu);
            int count = 0;
            while (cb.getState() == 0) {
                solver.setBoard(cb.getChessBoard());
                String b = solver.minMaxAI();
                count++;
                if (b.equals("w")) cb.upMove();
                if (b.equals("s")) cb.downMove();
                if (b.equals("a")) cb.leftMove();
                if (b.equals("d")) cb.rightMove();
            }
            
            if (cb.getState() == 1) {
                success++;
                step += count;
            }
            int max = cb.chessMax();
            int num = 2;
            for (int j = 0; j < 13; j++) {
                if (num == max) stop[j]++;
                num *= 2;
            }
            
        }
        
        long end = System.currentTimeMillis();
        System.out.print("The success number is ");
        System.out.println(success);
        
        System.out.print("Each test keys cost ");
        System.out.print(((double)(end - start)) / n);
        System.out.println(" ms.");
        
        System.out.print("Avg steps is ");
        System.out.println(step / success);
        
        int num = 2;
        for (int j = 0; j < 13; j++) {
  //          System.out.print("Stop in ");
            System.out.print(stop[j]);
            System.out.print("  ");
  //          System.out.print(" is ");
  //          System.out.println(stop[j]);
            num *= 2;
        }
    }
}
