/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author 3017317
 */
import java.util.ArrayList;
public class Rook extends Piece {
    
    public Rook(boolean newColor) {
        super(newColor);
    }
      public ArrayList<int[]> possibleMoves(Square current, Square[][] board){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int row = current.getRow();
        int col = current.getCol();
  //4 directions in which a rook can move: up, down, left, and right
        int[][] directions={
            {-1,0},   //up
            {1,0},    //down
            {0,-1},   //left
            {0,1},    //right
        }
        for(int [] d: directions){
                 int newRow = row + d[0];
                int newCol = col + d[1];
  //the purpose of the while statement is to keep moving until the edge or you are blocked
                while(newRow>=0&& newRow<8 && newCol>=0 && newCol<=8){
                    Square next=board[newRow][newCol]; //gets square on the chessboard at a specific position and stores it 
                    if(!next.hasPiece()){
                        moves.add(new int[]{newRow,newCol};
                    }else{
                        // if enemy piece, you can capture
                        
                        if(next.getPiece()getColor()!= this.getColor()){
                            moves.add(new int[]{newRow, newCol}); //record coordinate as legal move for this piece
                        }
                        //stop scanning in this direction
                        break;
                
            }    
                    newRow += d[o];
                    newCol += d[1];
      }
    }
    return moves;

      }
}    
          




          
