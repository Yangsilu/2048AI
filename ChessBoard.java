
package ai2048;

import java.util.*;
import java.lang.Math;

/**
 *
 * @author Jason
 */
public class ChessBoard {
    
    final private int[][] board = new int[4][4];
    private int target = 0;
    private int state = 0;
    
    public ChessBoard() {
        target = 2048;
        this.createChess(2);
        this.createChess(2);
    }
    
    public ChessBoard(int tar) {
        target = tar;
        this.createChess(2);
        this.createChess(2);
    }
    
    private void rotateBoard(int times) {
        for (int i = 0; i < times; i++) {   //rotate the chess board
            int tmp;
            for (int j = 0; j < 2; j++)
                 for (int k = 0; k < 2; k++) {
                     tmp = board[j][k];
                     board[j][k] = board[3-k][j];
                     board[3-k][j] = board[3-j][3-k];
                     board[3-j][3-k] = board[k][3-j];
                     board[k][3-j] = tmp;
                 }
        }
    }
    
    private boolean moveChess(int derection) {     //0-up, 1-left, 2-down, 3-right  //加得分系统
        int[][] tmp = new int[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                tmp[i][j] = board[i][j];
        
        this.rotateBoard(derection);
        
        for (int i = 0; i < 4; i++) {   //make the move
            List<Integer> merge = new ArrayList<Integer>();
            for (int j = 0; j < 4; j++) 
                if (board[j][i] != 0) merge.add(board[j][i]);
            for (int j = 0; j < merge.size()-1; j++) {
                if ((int)merge.get(j) == (int)merge.get(j+1) && merge.get(j) != 0) {
                    merge.set(j, merge.get(j+1) * 2);
                    merge.set(j+1, 0);
                }
            }
            for (int j = 0; j < 4; j++) 
                board[j][i] = 0;
            int it = 0;
            for (Integer j: merge) 
                if (j != 0) {
                    board[it][i] = j;
                    it++;
                }
        }
        
        this.rotateBoard(4 - derection);
        
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (tmp[i][j] != board[i][j]) return true;
        
        return false;
    }
    
    private void createChess(int num) { //TODO 2  good
        int count=0;
        for (int i = 3; i >= 0; i--)
            for (int j = 3; j >= 0; j--)
                if (board[i][j] == 0) {
                    count++;
//                    board[i][j] = num;
//                    return;
                }
        count=(int)(count*Math.random());
         for (int i = 3; i >= 0; i--)
            for (int j = 3; j >= 0; j--)
                if (board[i][j] == 0) {
                    
                    if(count==0){
                        board[i][j] = num;
                        return;
                    }
                    count--;
//                    
                }
        
    }
    
    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.printf("%8d", board[i][j]);
            System.out.println();
        }
        System.out.println();
    }
    
    public int[][] getChessBoard() {
        return this.board;
    }
    
    public int getState() { //-1 means lose, 0 means processing, 1 means win
        return this.state;
    }
    
    private boolean checkWin() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == target) {
                    this.state = 1;
                    return true;
                }
        return false;
    }
    
    private boolean checkLose() {
        boolean flag = true;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == 0) flag = false;
        for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 3; j++)
                if (board[i][j] == board[i][j+1] && board[i][j] != 0) flag = false;
        for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 3; j++)
                if (board[j][i] == board[j+1][i] && board[j][i] != 0) flag = false;
        if (flag) this.state = -1;
        return flag;
    }
    
    private void makeMove(int derection) {
        if (state != 0) {
            if (state == 1) System.out.println("You won this game already!");
            if (state == -1) System.out.println("You lost this game already!");
        } else {
            boolean flag = this.moveChess(derection);
            if (this.checkWin()) {
                System.out.println("NB! You Win!");
            } else {
                if (flag) this.createChess(2);
                if (this.checkLose())  System.out.println("SB! You Lose!");
            }
        }
    }
    
    public void upMove() {
        this.makeMove(0);
    }
    
    public void leftMove() {
        this.makeMove(1);
    }
    
    public void downMove() {
        this.makeMove(2);
    }
    
    public void rightMove() {
        this.makeMove(3);
    }
    
}
