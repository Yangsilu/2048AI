/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ai2048;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason
 */
public class BoardState {
    
    final int[][] board = new int[4][4];
    final int[][] tmp = new int[4][4];
    final double r;
    
    public BoardState(int[][] state, double hr) {
        r = hr;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                board[i][j] = state[i][j];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                tmp[i][j] = board[i][j];
    }
    
    private void rotateBoard(int times) {
        for (int i = 0; i < times; i++) {   //rotate the chess board
            int tmpNum;
            for (int j = 0; j < 2; j++)
                 for (int k = 0; k < 2; k++) {
                     tmpNum = tmp[j][k];
                     tmp[j][k] = tmp[3-k][j];
                     tmp[3-k][j] = tmp[3-j][3-k];
                     tmp[3-j][3-k] = tmp[k][3-j];
                     tmp[k][3-j] = tmpNum;
                 }
        }
    }
    
    public boolean moveChess(int derection) {     //0-up, 1-left, 2-down, 3-right
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                tmp[i][j] = board[i][j];
        
        this.rotateBoard(derection);
        
        for (int i = 0; i < 4; i++) {   //make the move
            List<Integer> merge = new ArrayList<Integer>();
            for (int j = 0; j < 4; j++) 
                if (tmp[j][i] != 0) merge.add(tmp[j][i]);
            for (int j = 0; j < merge.size()-1; j++) {
                if ((int)merge.get(j) == (int)merge.get(j+1) && merge.get(j) != 0) {
                    merge.set(j, merge.get(j+1) * 2);
                    merge.set(j+1, 0);
                }
            }
            for (int j = 0; j < 4; j++) 
                tmp[j][i] = 0;
            int it = 0;
            for (Integer j: merge) 
                if (j != 0) {
                    tmp[it][i] = j;
                    it++;
                }
        }
        
        this.rotateBoard(4 - derection);
        
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (tmp[i][j] != board[i][j]) return true;
        
        return false;
    }
    
    public boolean createChess(int x, int y) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                tmp[i][j] = board[i][j];
        if (tmp[x][y] == 0) {
            tmp[x][y] = 2;
            return true;
        }
        return false;
    }
 
    public List<List<Integer>> getEmpty() {
        List<Integer> pair = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (tmp[i][j] == 0) {
                    pair.add(i);
                    pair.add(j);
                    ans.add(pair);
                    pair = new ArrayList<Integer>();
                }
        return ans;
    }
    
    public int[][] getNextState() {
        return tmp;
    }
    
    public int[][] getOriginState() {
        return board;
    }
    
    public double getHeuristicScore(){
        double score=0;
        for(int i=1;i<=16;i++){
            score=score+this.getStateHeuristic(i);
        }
        score=score / 8.0;
        return score;
    }
    
    public double getStateHeuristic(int direction) {
    //start: 0 ,1,2,3
    //direction:0:,1
        double value = 0;
        double rn = 1;
       
        for (int count=0;count<16;count++){
                
            int i,j;
            i=count%4;
            j=count/4;
            if(direction==1){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                value += (rn * board[i][j]);
            }
            if(direction==2){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                value += (rn * board[j][i]);
            }
            if(direction==3){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                j=3-j;
                value += (rn * board[j][i]);
                
            }
            if(direction==4){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                i=3-i;
                value += (rn * board[j][i]);
            }
            if(direction==5){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                j=3-j;
                value += (rn * board[i][j]);
            }
            if(direction==6){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                i=3-i;
                value += (rn * board[i][j]);
            }
            if(direction==7){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                i=3-i;
                j=3-j;
                
                value += (rn * board[i][j]);
            }
            if(direction==8){ 
                if(j==1 || j==3){
                    i=3-i;
                }
                i=3-i;
                j=3-j;
                
                value += (rn * board[j][i]);
            }
            
            
                
                rn *= r;
            
        
        }  
        
        
        return value;
    
    }
}
