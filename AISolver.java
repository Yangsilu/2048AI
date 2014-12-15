/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ai2048;

import java.util.List;

/**
 *
 * @author Jason
 */
public class AISolver {
    int[][] board = new int[4][4];
    double r;
    int d;
    int mode;
    
    public AISolver(double hr, int hd, int hm) {
        r = hr;
        d = hd;
        mode = 2;
        if (hm == 1) mode = 8;
    }
    
    public void setBoard(int[][] state) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                board[i][j] = state[i][j];
    }
    
    public String minMaxAI() {
        double max = 0;
        int w = -1;
//        BoardState l1 = new BoardState(board, r);
//        for (int i = 0; i < 4; i++) {
//            double min = Double.MAX_VALUE;
//            
//            if (!l1.moveChess(i)) continue;
//            
//            BoardState l2 = new BoardState(l1.getNextState(), r);
//            List<List<Integer>> empty = l2.getEmpty();
//            for (List<Integer> j: empty) {
//                int x = j.get(0);
//                int y = j.get(1);
//                l2.createChess(x, y);
//                BoardState l3 = new BoardState(l2.getNextState(), r);
//                min = Math.min(min, l3.getHeuristicScore());
//            }
//            if (empty.isEmpty()) min = 0;
//            if (min > max) {
//                w = i;
//                max = min;
//            }
//        }
        BoardState tb = new BoardState(board, r);
        double[] zeren=minimax(board,d,r);
        for(int i=0;i<4;i++){
            if(max<=zeren[i] && tb.moveChess(i)){
                max=zeren[i];
                w=i;
            }
        }
        
        
        if (w == 0) return "w";
        if (w == 1) return "a";
        if (w == 2) return "s";
        if (w == 3) return "d";
        return "cao";
    }
    
//    public String MinMax(int[][] chess, int level, char min_max_indica  ){
//        //level: current table is level
//        
//        level--;
//        
//        
//        return "c";
//    }
    public double[] minimax(int[][] mat, int depth,double r) {//start from a table with new 2;
        double[] NodeScore= new double[4];
        if (depth==0 ){
            for(int i=0;i<4;i++){
                BoardState l=new BoardState(mat, r);
                if (l.moveChess(i)){
                    BoardState l2 = new BoardState(l.getNextState(), r);
                    NodeScore[i]=l2.getHeuristicScore(mode);
                }else{
                    NodeScore[i]=Double.MIN_VALUE;
                }
                
            }
        }else{
            
            for(int i=0;i<4;i++){
                BoardState l=new BoardState(mat, r);
                if (!l.moveChess(i)){
                    NodeScore[i]=Double.MIN_VALUE;
                }else{
                    
                    double mins=Double.MAX_VALUE;
                    
                    List<List<Integer>> empty = l.getEmpty();
                    for (List<Integer> j: empty) {
                        int x = j.get(0);
                        int y = j.get(1);
                        
                        BoardState l2 = new BoardState(l.getNextState(), r);
                        
                        l2.createChess(x, y);
                        double minOfmax=Double.MIN_VALUE;
                        double[] minOfmaxlist=minimax( l2.getNextState(), depth-1 ,r);
                        for(int silu=0;silu<4;silu++){
                            if (minOfmaxlist[silu]>minOfmax)
                                minOfmax=minOfmaxlist[silu];
                        }
                        if (empty.isEmpty()) minOfmax = Double.MIN_VALUE;
                        mins = Math.min(mins,minOfmax);
                    }
                    NodeScore[i]=mins;
                }
                
            }
            
        }//
        
        return NodeScore;
    }
}
